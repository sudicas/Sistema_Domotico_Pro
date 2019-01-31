Type=Activity
Version=7.01
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
	#FullScreen: True
	#IncludeTitle: False
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim Timer1 As Timer
	Dim UDPSocket1 As UDPSocket 
	'Dim CambioNombre As Boolean 
	
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim ListView1 As ListView
	Dim sm As SlideMenu
	
	Dim ContTim As Int 
 
	'Dim adview1 As AdView
 
	'Dim ValoresComunes.AlmName As List 
	Dim Valores(20) As Int
	Private ImgCargando As ImageView
	Dim AniCargando As  Animation
	Dim UltimaTrama() As Byte
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("FrmBase")
	If ValoresComunes.Centrales .IsInitialized = False  Then Return

	

	'Activity.LoadLayout ("frmnotification")
	ListView1.Initialize("ListView1")
	
	'ListView1.height = Activity.height  
	'ListView1.Width =Activity.Width 
	'ListView1.Top =0
	
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
	
	Activity.AddView (ListView1,0,52dip ,Activity.Width  ,100%y - 52dip  )
	sm.Initialize(Activity, Me,  "SlideMenu", 42dip, 250dip)
	ValoresComunes.BuldMenus (sm,4)
	
End Sub

Sub Activity_Resume
	If ValoresComunes.CloseApp =True Then
		Activity.Finish 
		Return
	End If

	If ValoresComunes.Centrales .IsInitialized = True Then
		If UDPSocket1.IsInitialized = False Then  ValoresComunes.IniUDP(UDPSocket1) 

		
		
		If ImgCargando.IsInitialized = False Then
			ImgCargando.Initialize ("ImgCargando")
			ImgCargando.Bitmap = ValoresComunes.Cargando 
			ImgCargando.Visible =False 
			ImgCargando.Gravity = Gravity.FILL 
			Activity.AddView (ImgCargando,  PerXToCurrent(40)	, PerYToCurrent(35),PerXToCurrent(20) ,PerXToCurrent(20) )
		End If
		AniCargando.InitializeRotateCenter( "AniCargando", 0, 180, ImgCargando)
		AniCargando.Duration = 1000
	    AniCargando.RepeatCount =-1
	    AniCargando.RepeatMode = AniCargando.REPEAT_REVERSE 
		'ImgCargando.Bitmap= ValoresComunes.Cargando 
		
		
		
		
		If Timer1.IsInitialized = False Then
			Timer1.Initialize ("Timer1",100)
		Else
			Timer1.Interval = 100
		End If
		If ValoresComunes.AlmName.IsInitialized =False Then	ValoresComunes.CargaAlarmas 
	
		
		ImgCargando.Visible =False
		Timer1.Enabled = True
		StartTimeOut
		LecturaDatos
	Else
		Activity.Finish 
		StartActivity(Main)
	End If
End Sub
Sub Timer1_Tick
	 ContTim=ContTim+1
	If  ContTim >50 Then 
		If ImgCargando.Visible Then
			SendTrama(UltimaTrama)
		Else
			LecturaDatos
		End If
	End If

End Sub


Sub Activity_Pause (UserClosed As Boolean)
	Timer1.Enabled =False
	UDPSocket1.Close 
	Activity.Finish 
End Sub
Sub ActualizaValores()
	ListView1.Clear 
	Dim i As Int
	For i =0 To 19
		
		Select Case Valores(i)				
			
			Case 1
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.AlmName.Get (i),ValoresComunes.GetLanString ("reg159") ,ValoresComunes.AlmDes ,i)
			Case 2
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.AlmName.Get (i),ValoresComunes.GetLanString ("reg157"),ValoresComunes.AlmOff ,i)
			
			Case 3
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.AlmName.Get (i),ValoresComunes.GetLanString ("reg158") ,ValoresComunes.AlmOn ,i)
			Case 4
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.AlmName.Get (i),ValoresComunes.GetLanString ("reg158") ,ValoresComunes.AlmOn ,i)
			
		End Select
	
	Next

End Sub
Sub LecturaDatos()
	Dim data() As Byte
	Dim Trama As String
	If ValoresComunes.central.ConexionSegura Then
		Trama = "ALRM" & ValoresComunes.Central.Password     
	Else
		Trama = "ALRM"
	End If
	
    data = Trama.GetBytes("UTF8")
	
   	SendTrama(data)   	
End Sub

Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
	  	Dim msg As String
	    msg = BytesToString(Packet.Data, Packet.Offset, Packet.Length, "UTF8")
			
		If msg.Contains ("ESAL") Then		
			
			Dim  C As Int
			For C = 4 To Packet.Length -1
				Valores(C - 4)= Packet.Data (C)
				
			Next 
			
			ActualizaValores
						
		End If
		If msg="WIALMO" Then
			'EndTimeOut
			ActualizaValores
			ToastMessageShow(ValoresComunes.GetLanString ("reg139"),True)	
		End If
		If msg= "COMPLETED" Then			
			ToastMessageShow(ValoresComunes.GetLanString ("reg139"),True)
		End If
		If msg ="REPETIRMSG" Then
				LecturaDatos
		End If
	
		If ImgCargando.Visible Then EndTimeOut
	Catch
	    
	End Try	   
	
End Sub 
Sub SendTrama(data() As Byte )
	Dim Resultado As Boolean 
	Dim Reintento As Int
	UltimaTrama=data	

	

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
			Sleep (100)
		End If

	Loop
	If Resultado = True Then
		Resultado =False
		Reintento =0
		Do 	While Resultado= False And Reintento < 40
			Resultado = SendingTrama(data)
			Reintento = Reintento +1 
			If Resultado=False Then Sleep (200)
		Loop
		
	End If
	
	If Resultado = False  Then
		'Fallo de conexion
		ActMosaico.Conectado =False
		StartActivity(ActMosaico)
		'ToastMessageShow("Network not available", True)
		'Sleep (1000)		
		
	End If


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

Sub CambiarEstado(Position As Int, Activar As Boolean )
	Dim LongTrama As Int 
		If ValoresComunes.central.ConexionSegura Then
			LongTrama= 17
		Else
			LongTrama= 9
		End If
		
		Dim data(LongTrama) As Byte
		data(0)=83	
		data(1)=69
		data(2)=84
		data(3)=78
		data(4)=79
		data(5)=84
		data(6)=73
		
		data(7)= Position +1 
		If Activar Then
			Valores(Position)=2
			data(8)= 2
		Else
			Valores(Position)=1
			data(8)= 1
		End If
		StartTimeOut
		If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
	    SendTrama(data)
End Sub
Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim l As List 
	l.Initialize 
	l.Add (ValoresComunes.GetLanString ("reg160"))
	l.Add (ValoresComunes.GetLanString ("reg161"))
	l.Add (ValoresComunes.GetLanString ("reg111"))
	l.Add (ValoresComunes.GetLanStringDefault("DiT","Invisible element"))

	
	Dim v As Int
	v =InputList(l,ValoresComunes.GetLanString ("reg75"), Valores(Value))
	
	If v<0 Then Return
	Select Case v
		Case 0
			CambiarEstado(Value, True )
		
		Case 1
			CambiarEstado(Value, False )
		
		Case 2
			Dim dial As InputDialog 
			dial.Input = ValoresComunes.AlmName.Get (Position)
			Dim result As Int 
			result = dial.Show ( "New Name ",ValoresComunes.GetLanString ("reg104"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)	
			If result= DialogResponse.POSITIVE Then
				If dial.Input ="" Then Return
				ValoresComunes.AlmName.Set (Value,dial.Input )
				File.WriteList(File.DirInternal  ,"Alm" & ValoresComunes.central.Nombre ,ValoresComunes.AlmName)
				ActualizaValores
			End If	
		Case 3	
			ValoresComunes.AlmName.Set (Value,"" )
			File.WriteList(File.DirInternal  ,"Alm" & ValoresComunes.central.Nombre ,ValoresComunes.AlmName)
			ActualizaValores
	End Select
		
	
End Sub
Sub StartTimeOut
	ImgCargando.Visible =True
	AniCargando.Start (ImgCargando)	
	ListView1.Enabled =False
End Sub
Sub EndTimeOut
	ImgCargando.Visible =False
	AniCargando.Stop  (ImgCargando)
	ListView1.Enabled =True
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