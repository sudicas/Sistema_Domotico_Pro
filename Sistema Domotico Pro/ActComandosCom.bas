Type=Activity
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region


Sub process_globals
    Dim UDPSocket1 As UDPSocket 

End Sub

Sub Globals
	
	Dim ListView1 As ListView
	

	
	Dim TramaEnviada() As Byte
	Dim ImgCargando As ImageView 
	Dim AniCargando As  Animation

	Dim PaqueteEnviado As Boolean 

	Dim CmdSelect As Button
	
	'Dim AdView1 As AdView
	Dim NumeroCental As Int
End Sub

Sub Activity_Create(FirstTime As Boolean)
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	If FirstTime Then
		
    End If
	
	Activity.LoadLayout ("FrmComandos")
	
	
	
	ListView1.height =PerYToCurrent(85) 'PerYToCurrent(84)- height 
	ListView1.Width = Activity.Width 
	ListView1.Top =0
	
	
	
	
	CmdSelect.Top=PerYToCurrent(85)
	CmdSelect.height =PerYToCurrent(15)
	CmdSelect.Width = Activity.Width 
	
	
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
	
		
End Sub

Sub Activity_Resume
	If Main.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then
		If ValoresComunes.ComandosComunes .IsInitialized = False Then ValoresComunes.CargaComandosComunes 
		CmdSelect.Text= ValoresComunes.descripcionesComandosComunes .Get (0)	
	
		If Main.MyLan.IsInitialized = False Then Main.MyLan.Initialize( 0, "")
		If UDPSocket1.IsInitialized = False Then	ValoresComunes.IniUDP(UDPSocket1)   
		Activity.Title =ValoresComunes.GetLanString ("reg132")
		
		If ImgCargando.IsInitialized = False Then
			ImgCargando.Initialize ("ImgCargando")
			ImgCargando.Bitmap = ValoresComunes.Cargando 
			ImgCargando.Visible =False 
			Activity.AddView (ImgCargando,  PerXToCurrent(40)	, PerYToCurrent(35),PerXToCurrent(20) ,PerXToCurrent(20) )
		End If
		
		
		RefPantalla
		AniCargando.InitializeRotateCenter( "AniCargando", 0, 180, ImgCargando)
		AniCargando.Duration  =   1500
	    AniCargando.RepeatCount  = 2
	    AniCargando.RepeatMode = AniCargando.REPEAT_REVERSE 
		
		

	Else
		Activity.Finish 
		StartActivity(Main)
	End If
	  
End Sub

Sub AniCargando_AnimationEnd
	
    If PaqueteEnviado = True Then 
		'If ActMosaico.Forzar3g=False Then
			'ActMosaico.Forzar3g=True
			AniCargando.Start (ImgCargando)
			SendTrama(TramaEnviada)
		'Else		
				'Dim result As Int
				'If ActMosaico.Forzar3g Then
				'	result = Msgbox2("The external connection  is not responding."&  Chr(10) &"Do you start internal connection mode?", ValoresComunes.GetLanString ("reg137"), ValoresComunes.GetLanString ("Ok"), "", ValoresComunes.GetLanString ("Cancel"), Null)
				
				'Else
				'	result = Msgbox2("The connection in local mode is not responding."&  Chr(10) &"Do you start external connection mode?", ValoresComunes.GetLanString ("reg137"), ValoresComunes.GetLanString ("Ok"), "", ValoresComunes.GetLanString ("Cancel"), Null)
				
				'End If
				
				'If result = DialogResponse.Positive Then 
					
				'	ActMosaico.Conectado  = False
				'	If ActMosaico.Forzar3g Then
				'		ActMosaico.Forzar3g = False
		
				'	Else
				'		ActMosaico.Forzar3g = True
				'	End If
				'	StartActivity(Main)
				'Else
				'	AniCargando.Start (ImgCargando)
				'	SendTrama(TramaEnviada)
				'	ActMosaico.Forzar3g = False
				'End If		
		'End If
	End If
End Sub
Sub RefPantalla
	ListView1.Clear 
	Dim i As Int 
	For i =0 To  ValoresComunes.ComandosComunes.Size -1
		Dim cmd As Scene 
		cmd = ValoresComunes.ComandosComunes.Get (i)
		If cmd.Nombre <> "" AND cmd.Descripcion = CmdSelect.Text  Then
			ListView1.AddTwoLinesAndBitmap2 ( cmd.Nombre ,cmd.Descripcion  ,ValoresComunes.Home ,i)
		End If	
	Next
End Sub



Sub ListView1_ItemClick (Position As Int, Value As Object)
	
	'COMANDO COMUN
	Dim LongTrama As Int 
	If ValoresComunes.central.ConexionSegura Then
		LongTrama= 16
	Else
		LongTrama= 8
	End If
    Dim data(LongTrama) As Byte
	data(0)=67	'C
	data(1)=79	'O
	data(2)=77	'M
	data(3)=67	'C
	data(4)=79	'O
	data(5)=77	'M
	data(6)=77	'M
	data(7)=Value + 1
	If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
	
	NumeroCental =1
	
	
	'ValoresComunes.Central.Nombre   = ValoresComunes.Centrales .Get (NumeroCentral)
	
	ValoresComunes.Central.nombre = ValoresComunes.Centrales .Get (0)
	ValoresComunes.CargaIp 
	'IniciarRed
	
		
	SendTrama(data)
End Sub

Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
		Dim msg As String
	    msg = BytesToString(Packet.Data, Packet.Offset, Packet.Length, "UTF8")


		
		If msg ="REPETIRMSG" Then
			SendTrama(TramaEnviada)
			Return
		Else
		
		
			If msg = "COMCOMOK" Then
				If NumeroCental < ValoresComunes.Centrales .Size Then
					ValoresComunes.Central.nombre = ValoresComunes.Centrales .Get (NumeroCental)
					ValoresComunes.CargaIp 
					NumeroCental = NumeroCental + 1
					SendTrama(TramaEnviada)
					Return
				Else

					'ToastMessageShow(ValoresComunes.GetLanString ("reg139"),True)
				End If
			End If	
		End If
		'ActMosaico.Forzar3g = False
		PaqueteEnviado = False
		AniCargando.Stop(ImgCargando)
		ImgCargando.Visible =False 
	
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





Sub CmdSelect_Click
	Dim Pos As Int
	
	Pos =InputList(ValoresComunes.descripcionesComandosComunes,ValoresComunes.GetLanString ("reg75"), Pos)
	If Pos>=0 Then
		CmdSelect.Text  = ValoresComunes.descripcionesComandosComunes.Get (Pos)
		RefPantalla
	End If
End Sub


