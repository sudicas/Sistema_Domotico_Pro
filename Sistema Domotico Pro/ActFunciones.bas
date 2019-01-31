Type=Activity
Version=7.01
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: false
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	
	Dim UDPSocket1 As UDPSocket 
End Sub

Sub Globals
	Dim sm As SlideMenu
	
	
	Dim ListView1 As ListView
	

	
	Dim TramaEnviada() As Byte
	Dim ImgCargando As ImageView 
	Dim AniCargando As  Animation

	Dim PaqueteEnviado As Boolean 

End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("FrmBase")
	'Activity.Title = "SS Sliding Menu"
	'Activity.Height = 100%y + 42dip
	
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	If FirstTime Then
		
    End If
	
	'Activity.LoadLayout ("frmnotification")
	
	ListView1.Initialize("ListView1")
	
	
	
	'ListView1.height = Activity.Height - 52dip
	'ListView1.Top =52dip
	'ListView1.Width = Activity.Width 
	'ListView1.Top =height
	
	
	
	
	'AdView1.Initialize2("Ad", "ca-app-pub-6421049843515203/5265561373", AdView1.SIZE_SMART_BANNER)
	'Activity.AddView( AdView1, 0dip, 0dip, 100%x, height)
	'AdView1.LoadAd
	

	
	
	
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
	ValoresComunes.BuldMenus (sm,11)
	

End Sub

Sub Activity_Resume
	If ValoresComunes.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	
	
	If ValoresComunes.Centrales .IsInitialized = True Then
		
		
		
	
		If Main.MyLan.IsInitialized = False Then Main.MyLan.Initialize( 0, "")
		If UDPSocket1.IsInitialized = False Then	ValoresComunes.IniUDP(UDPSocket1)   
		'Activity.Title =ValoresComunes.GetLanString ("reg132")
		'SlMenu.BarsOff
		'SlMenu.MenuHide
		
		If ImgCargando.IsInitialized = False Then
			ImgCargando.Initialize ("ImgCargando")
			ImgCargando.Bitmap = ValoresComunes.Cargando 
			ImgCargando.Visible =False 
			ImgCargando.Gravity = Gravity.FILL 
			Activity.AddView (ImgCargando,  PerXToCurrent(40)	, PerYToCurrent(35),PerXToCurrent(20) ,PerXToCurrent(20) )
		End If
		
		
		
		RefPantalla
		
		
		
		AniCargando.InitializeRotateCenter( "AniCargando", 0, 180, ImgCargando)
		AniCargando.Duration  =   1000
	    AniCargando.RepeatCount =5
	    AniCargando.RepeatMode = AniCargando.REPEAT_REVERSE 
		
		
	Else
		Activity.Finish 
		StartActivity(Main)
	End If
End Sub
Sub Activity_Pause (UserClosed As Boolean)

	UDPSocket1.Close 
	Activity.Finish 
End Sub



Sub AniCargando_AnimationEnd
	
    If PaqueteEnviado = True Then 
		AniCargando.Start (ImgCargando)
		SendTrama(TramaEnviada)
	End If
End Sub
Sub RefPantalla
	ListView1.Clear 
	Dim i As Int 
	For i =0 To  ValoresComunes.Functions .Length -1
		Dim cmd As Scene 
		cmd = ValoresComunes.Functions (i)
		If cmd.Nombre <> "" Then ListView1.AddTwoLinesAndBitmap2 ( cmd.Nombre ,cmd.Descripcion  ,ValoresComunes.Funciones ,i)

	Next
End Sub



Sub ListView1_ItemClick (Position As Int, Value As Object)
	
	'COMANDO
	Dim LongTrama As Int 
	If ValoresComunes.central.ConexionSegura Then
		LongTrama= 16
	Else
		LongTrama= 8
	End If
    Dim data(LongTrama) As Byte
	data(0)=68	'D
	data(1)=79	'O
	data(2)=67	'C
	data(3)=79	'O
	data(4)=77	'M
	data(5)=65	'A
	data(6)=78	'N
	
	data(7)=Value + 1
	If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
	SendTrama(data)
End Sub

Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
		Dim msg As String
	    msg = BytesToString(Packet.Data, Packet.Offset, Packet.Length, "UTF8")


		
		If msg ="REPETIRMSG" Then
			SendTrama(TramaEnviada)
			Return
		Else If msg.Contains ("COMPLETED")  Then
			PaqueteEnviado = False
			AniCargando.Stop(ImgCargando)
			ImgCargando.Visible =False 
		
			'ToastMessageShow(msg,True)

		End If
		
	
	Catch
	    
	End Try
    
End Sub 

Sub SendTrama(data() As Byte )
	Dim Resultado As Boolean 
	Dim Reintento As Int

	ImgCargando.Visible =True	 
	AniCargando.Start (ImgCargando)
	
	PaqueteEnviado =False
	TramaEnviada= data
	
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
			Sleep (200)
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
		ActMosaico.Conectado =False
		StartActivity(ActMosaico)
	Else
		PaqueteEnviado=True
	End If
End Sub
Sub SendingTrama (data() As Byte) As Boolean 
	Try
	    Dim Packet As UDPPacket
		Packet.Initialize(data, ValoresComunes.ip, ValoresComunes.Puerto)
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
					StartActivity(ActFreeTxt)
					Activity.Finish
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
				'StartActivity(ActFunciones)
				'Activity.Finish
				
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