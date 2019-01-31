Type=Activity
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim Timer1 As Timer
	Dim UDPSocket1 As UDPSocket 
	Dim Sensores As List 
	
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Dim SensorEnLectura As Int 
	Dim ListView1 As ListView
	Dim ContTim As Int 
	'Dim PaqueteEnviado As Boolean 
 
	'Dim adview1 As AdView
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	If File.Exists ( File.DirInternal ,"Sensores"& ValoresComunes.central.Nombre ) = False   Then
		Msgbox(ValoresComunes.GetLanString ("reg167"),ValoresComunes.GetLanString ("SSE"))
		Activity.Finish 
	Else
		
		Activity.LoadLayout ("FrmSensores")
		ListView1.height = Activity.height' - height
		ListView1.Width =Activity.Width 
		ListView1.Top =0
		Timer1.Initialize ("Timer1",100)
		
		'adview1.Initialize2("Ad", "ca-app-pub-6421049843515203/5265561373", adview1.SIZE_SMART_BANNER)
		'Activity.AddView( adview1, 0dip, 0dip, 100%x, height)
		'adview1.LoadAd
		
		
		'Configuracion tamaño listas
		Dim h As Int
		h=75dip
		
		
		ListView1.TwoLinesAndBitmap .ItemHeight =h
		ListView1.TwoLinesAndBitmap.ImageView.Width=h
		ListView1.TwoLinesAndBitmap.ImageView.height=h
		
		ListView1.TwoLinesAndBitmap.Label .Left =h + h/9
		ListView1.TwoLinesAndBitmap.Label.height  =h/1.8
		ListView1.TwoLinesAndBitmap.Label.Gravity = Bit.OR(Gravity.LEFT, Gravity.BOTTOM)
		
		ListView1.TwoLinesAndBitmap.SecondLabel .Left =ListView1.TwoLinesAndBitmap.Label .Left
		ListView1.TwoLinesAndBitmap.SecondLabel.height   =h-ListView1.TwoLinesAndBitmap.Label.height
		ListView1.TwoLinesAndBitmap.SecondLabel.top   = ListView1.TwoLinesAndBitmap.SecondLabel.height
		ListView1.TwoLinesAndBitmap.SecondLabel.Gravity = Bit.OR(Gravity.LEFT, Gravity.CENTER_VERTICAL)
	End If
	

	
End Sub

Sub Activity_Resume

	If Main.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then
		If UDPSocket1.IsInitialized = False Then  ValoresComunes.IniUDP(UDPSocket1) 
		
		If ValoresComunes.NombreSensor .IsInitialized  = False Then
			ValoresComunes.CargaNombreSensores 
		End If
		If Sensores.IsInitialized = False Then
			Sensores.Initialize 		
			Sensores.AddAll (ValoresComunes.NombreSensor)	
		End If
		If Sensores.Size <> ValoresComunes.NombreSensor .Size Then
			Sensores.Clear 
			Sensores.AddAll (ValoresComunes.NombreSensor)
		End If
		If Timer1.IsInitialized = False Then
			Timer1.Initialize ("Timer1",100)
		Else
			Timer1.Interval = 100
		End If
		 
		Timer1.Enabled = True
		SensorEnLectura=1
		LecturaDatos
	Else
		Activity.Finish 
		StartActivity(Main)
	End If
End Sub
Sub Timer1_Tick
	 ContTim=ContTim+1
	If  ContTim >50 Then LecturaDatos

	

End Sub
Sub Activity_Pause (UserClosed As Boolean)
	Timer1.Enabled =False
	UDPSocket1.Close 
End Sub
Sub ActualizaValores()
	ListView1.Clear 
	Dim i As Int
	For i =0 To Sensores.Size -1
		ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.NombreSensor.Get (i),Sensores.Get (i) ,ValoresComunes.Sensor ,i)
	Next
End Sub
Sub LecturaDatos()
	Dim LongTrama As Int 
	If ValoresComunes.central.ConexionSegura Then
		LongTrama= 18
	Else
		LongTrama= 10
	End If
	
	Dim data(LongTrama) As Byte
	data(0)=71
	data(1)=69
	data(2)=84
	data(3)=83
	data(4)=69
	data(5)=78
	data(6)=83
	data(7)=79
	data(8)=82
	data(9)=SensorEnLectura
	If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
	SendTrama(data)
	SensorEnLectura=SensorEnLectura+1
	If SensorEnLectura> Sensores.Size Then 
		SensorEnLectura=1
		ContTim = -290
	End If
	
	
   	
End Sub

Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
	  	Dim msg As String
	    msg = BytesToString(Packet.Data, Packet.Offset, Packet.Length, "UTF8")
			
		If msg.Contains ("SENSOR") Then		
			
			Sensores.Set  (Packet.Data (6)-1,msg.SubString (7))
			
			ActualizaValores
			
			If ContTim> -1 Then 
				ContTim=0
				LecturaDatos
			End If
		End If
		
		If msg= "COMPLETED" Then			
			ToastMessageShow(ValoresComunes.GetLanString ("reg139"),True)
		End If
		If msg ="REPETIRMSG" Then
				LecturaDatos
		End If
		'PaqueteEnviado = False

	Catch
	    
	End Try	   
	
End Sub 
Sub SendTrama(data() As Byte )
	Dim Resultado As Boolean 
	Dim Reintento As Int
	
	'PaqueteEnviado =False
	ContTim=0
	
	Do 	While Resultado= False AND Reintento < 40
		Dim ServerSocket1 As ServerSocket 
		
		Dim MyIp As String
		If ActMosaico.Forzar3g Then
			MyIp=ServerSocket1.GetMyIP
		Else
			MyIp=ServerSocket1.GetMyWifiIP 
		End If
		If MyIp  <> "127.0.0.1" Then	
			Resultado = True
		Else
			Reintento = Reintento +1 
			ValoresComunes.Delay (200)
		End If

	Loop
	If Resultado = True Then
		Resultado =False
		Reintento =0
		Do 	While Resultado= False AND Reintento < 40
			Resultado = SendingTrama(data)
			Reintento = Reintento +1 
			If Resultado=False Then ValoresComunes.Delay (200)
		Loop
		
	End If
	
	If Resultado = False  Then
		'Fallo de conexion
		ActMosaico.Conectado =False
		StartActivity(ActMosaico)
		'ToastMessageShow("Network not available", True)
		'ValoresComunes.Delay (1000)		
		
	End If
	'PaqueteEnviado=True

End Sub

Sub SendingTrama (data() As Byte) As Boolean 
	Try
	    Dim Packet As UDPPacket
	
		Packet.Initialize(data, ValoresComunes.ip,  ValoresComunes.Puerto )
	    UDPSocket1.Send(Packet)	
		Return True
	Catch
	    Return False
	End Try
End Sub


