Type=Activity
Version=7.01
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
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
	Dim sm As SlideMenu
	'Dim PaqueteEnviado As Boolean 
 
	'Dim adview1 As AdView
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("FrmBase")
	
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	If File.Exists ( File.DirInternal ,"Sensores"& ValoresComunes.central.Nombre ) = False   Then
		Msgbox(ValoresComunes.GetLanString ("reg167"),ValoresComunes.GetLanString ("SSE"))
		Activity.Finish 
	Else
		
		Activity.LoadLayout ("FrmSensores")
		ListView1.height = 100%y - 52dip
		ListView1.Width =Activity.Width 
		ListView1.Top =52dip
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
		ListView1.TwoLinesAndBitmap.Label.Gravity = Bit.Or(Gravity.LEFT, Gravity.BOTTOM)
		
		ListView1.TwoLinesAndBitmap.SecondLabel .Left =ListView1.TwoLinesAndBitmap.Label .Left
		ListView1.TwoLinesAndBitmap.SecondLabel.height   =h-ListView1.TwoLinesAndBitmap.Label.height
		ListView1.TwoLinesAndBitmap.SecondLabel.top   = ListView1.TwoLinesAndBitmap.SecondLabel.height
		ListView1.TwoLinesAndBitmap.SecondLabel.Gravity = Bit.Or(Gravity.LEFT, Gravity.CENTER_VERTICAL)
		
		If UDPSocket1.IsInitialized = False Then  ValoresComunes.IniUDP(UDPSocket1) 
		
		sm.Initialize(Activity, Me,  "SlideMenu", 42dip, 250dip)
		ValoresComunes.BuldMenus (sm,8)
		
	End If
	

	
End Sub

Sub Activity_Resume

	If ValoresComunes.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then
		
			
		
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
	If  ContTim >40 Then LecturaDatos

	

End Sub
Sub Activity_Pause (UserClosed As Boolean)
	Timer1.Enabled =False
	UDPSocket1.Close 
	Activity.Finish 
End Sub
Sub ActualizaValores()
	ListView1.Clear 
	Dim i As Int
	For i =0 To Sensores.Size -1
		If ValoresComunes.NombreSensor.Get (i) <> "" Then ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.NombreSensor.Get (i),Sensores.Get (i) ,ValoresComunes.Sensor ,i)
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
	
	
	
	
   	
End Sub

Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
	  	Dim msg As String
	    msg = BytesToString(Packet.Data, Packet.Offset, Packet.Length, "UTF8")
			
		If msg.Contains ("SENSOR") Then		
			
			Sensores.Set  (Packet.Data (6)-1,msg.SubString (7))
			
			ActualizaValores
			SensorEnLectura=SensorEnLectura+1
			If SensorEnLectura> Sensores.Size Then 
				SensorEnLectura=1
			Else
				LecturaDatos
			End If
			ContTim=0
			

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
	
	Do 	While Resultado= False And Reintento < 40
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
			Sleep(200)
		End If

	Loop
	If Resultado = True Then
		Resultado =False
		Reintento =0
		Do 	While Resultado= False And Reintento < 40
			Resultado = SendingTrama(data)
			Reintento = Reintento +1 
			If Resultado=False Then Sleep(200)
		Loop
		
	End If
	
	If Resultado = False  Then
		'Fallo de conexion
		ActMosaico.Conectado =False
		StartActivity(ActMosaico)
		'ToastMessageShow("Network not available", True)
		'sleep(1000)		
		
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

Sub SlideMenu_Click(Item As Object)
	If Item>99 And  Item < 200 Then
		ActMosaico.Conectado =False
		ActMosaico.CentraltoConnect  =Item-100
		StartActivity(ActMosaico)
		Activity.Finish
		
		'Activity.Finish
		'If Conectado= False Then IniciarConexionCentral(CentraltoConnect)
	Else If Item>199 And  Item < 299 Then
		
	Else
			
		Select Case Item
		
			Case 1
				StartActivity(ActMosaico)
				Activity.Finish
			Case 2
				StartActivity(ActCircuit)
				Activity.Finish
			Case 3
				StartActivity(ActScene)
				Activity.Finish
			Case 4
				StartActivity(ActNotification)
				Activity.Finish
			Case 5
				StartActivity(ActCondicionados)
				Activity.Finish
			Case 6'comandos
				StartActivity(ActComandos)
				Activity.Finish
			Case 7
				StartActivity(ActSensors)
				Activity.Finish
			Case 8
				If File.Exists ( File.DirInternal ,"Sensores" & ValoresComunes.Central.nombre)  Then
					'StartActivity(ActFreeTxt)
					'Activity.Finish
				Else
					ToastMessageShow(ValoresComunes.GetLanString ("reg127") , True)
				End If
			Case 9
				StartActivity(ActConsignas)
				Activity.Finish
			Case 10
				ValoresComunes.CloseApp =True
				Activity.Finish
			Case 11
				StartActivity(ActFunciones)
				Activity.Finish
				
			Case 98
				StartActivity(ActSelectSensores)
				Activity.Finish
		End Select
	End If
	'SlMenu.BarsOff
	'ToastMessageShow("Item clicked = " & ItemNo, False)
End Sub
Sub Activity_KeyPress (KeyCode As Int) As Boolean
	'Pressing the back key while the slidemenu is open will close it
	If KeyCode = KeyCodes.KEYCODE_BACK And sm.isVisible Then
		sm.Hide
		Return True
	End If

	'Pressing the menu key will open/close the slidemenu
	If KeyCode = KeyCodes.KEYCODE_MENU Then
		If sm.isVisible Then sm.Hide Else sm.Show
		Return True
	End If
End Sub
Sub btnShow_Click
	sm.Show
End Sub