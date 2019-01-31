Type=Activity
Version=7.01
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region Module Attributes
	#FullScreen: True
	#IncludeTitle: False
#End Region

Sub process_globals
    
	Dim Valores(60) As   Byte
	Dim UDPSocket1 As UDPSocket 

End Sub

Sub Globals
	
	Dim ListView1 As ListView
	
	Dim CmdGuardar As Button
	Dim CmdTerminar As Button
	
	Dim TramaEnviada() As Byte
	Dim ImgCargando As ImageView 
	Dim AniCargando As  Animation

	Dim PaqueteEnviado As Boolean 

End Sub

Sub mnuAllOn_Click
	Dim c As Int
	For c =0 To 59
		Valores(c)=1
	Next
	ActualizaValores
End Sub

Sub mnuAllOff_Click

	Dim c As Int
	For c =0 To 59
		Valores(c)=0
	Next
	ActualizaValores
End Sub
Sub Activity_Create(FirstTime As Boolean)
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
    If FirstTime Then
		
      
    End If
	
	Dim he As Int = 40dip
	
	Activity.AddMenuItem(ValoresComunes.GetLanString ("reg149"), "mnuAllOff")
	Activity.AddMenuItem(ValoresComunes.GetLanString ("reg150"), "mnuAllOn")
	
	'UDPSocket1.Initialize("UDP", 0, 8000)
	
	Activity.LoadLayout ("frmconfig")
	
	Activity.Title =ValoresComunes.GetLanString ("ET")
	ListView1.Height = Activity.Height - he
	ListView1.Width =Activity.Width
	
	CmdGuardar.Top=Activity.Height-he
	CmdGuardar.Left = Activity.Width   - (he+4dip)
	CmdGuardar.Height =he
	CmdGuardar.Width =he' Activity.Width
	CmdGuardar.Text = ""'ValoresComunes.GetLanString ("reg128")
	CmdGuardar.SetBackgroundImage(ValoresComunes.CmdImgSave)
	
	CmdTerminar.Top=Activity.Height-he
	CmdTerminar.Height =he
	CmdTerminar.Width =he
	CmdTerminar.Left =Activity.Width   - ((he  * 2)   +8dip)
	CmdTerminar.SetBackgroundImage(ValoresComunes.CmdImgBack)
	CmdTerminar.Text = ""
	
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

End Sub
Sub Activity_Pause (UserClosed As Boolean)
	UDPSocket1.Close 
	Activity.Finish 
End Sub
Sub Activity_Resume
	If ValoresComunes.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then
	
		
		If Main.MyLan.IsInitialized = False Then Main.MyLan.Initialize( 0, "")
		If UDPSocket1.IsInitialized = False Then	ValoresComunes.IniUDP(UDPSocket1)   
		
		If ImgCargando.IsInitialized = False Then
			ImgCargando.Initialize ("ImgCargando")
			ImgCargando.Bitmap = ValoresComunes.Cargando 
			ImgCargando.Gravity = Gravity.FILL 
			Activity.AddView (ImgCargando,  PerXToCurrent(40)	, PerYToCurrent(35),PerXToCurrent(20) ,PerXToCurrent(20) )
			
		End If

		CmdGuardar.Enabled =False
		
		
		
		AniCargando.InitializeRotateCenter( "AniCargando", 0, 180, ImgCargando)
		AniCargando.Duration  =   1000
	    AniCargando.RepeatCount =5
	    AniCargando.RepeatMode = AniCargando.REPEAT_REVERSE 
		
		Dim data() As Byte
		Dim trama As String 
		If ValoresComunes.central.ConexionSegura Then
			trama = "ENABLEHOR" & ValoresComunes.Central.Password   
		Else
			trama = "ENABLEHOR"
		End If

		data =trama.GetBytes ("UTF8")
		SendTrama(data)	
	Else
		Activity.Finish 
		StartActivity(Main)
	End If  
End Sub

Sub AniCargando_AnimationEnd
	
    If PaqueteEnviado = True Then 
		AniCargando.Start (ImgCargando)
		SendTrama(TramaEnviada)
	End If
End Sub

Sub ActualizaValores()
	ListView1.Clear 
	Dim Nombre, descripcion As String 
	Dim i As Int 
	For i =0 To 59
		
		If i<30 Then
			Nombre=ValoresComunes.Circuitos (i).Nombre
			descripcion=ValoresComunes.Circuitos (i).descripcion 
		Else If i<40 Then
				Nombre=ValoresComunes.Scenes(i-30).Nombre
				descripcion=ValoresComunes.Scenes(i-30).descripcion
		Else If i<50 Then
				Nombre=ValoresComunes.Condicionados (i-40).Nombre
				descripcion=ValoresComunes.Condicionados(i-40).descripcion
		Else If i<60 Then
				Nombre=ValoresComunes.Functions  (i-50).Nombre
				descripcion=ValoresComunes.Functions(i-50).descripcion	
		End If

		If Nombre <> "" Then
			If Valores(i)=0   Then
					ListView1.AddTwoLinesAndBitmap2 (Nombre ,descripcion ,ValoresComunes.CheckOff,i)
				Else
					ListView1.AddTwoLinesAndBitmap2 ( Nombre ,descripcion ,ValoresComunes.CheckOn,i)
			End If	
		End If	
	Next
End Sub 


Sub ListView1_ItemClick (Position As Int, Value As Object)

	If Valores(Value)=1 Then 
		Valores(Value)=0
	Else
		Valores(Value)=1
	End If
	ActualizaValores
		
End Sub

Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
		Dim msg As String
	    msg = BytesToString(Packet.Data, Packet.Offset, Packet.Length, "UTF8")

		If msg.Contains ("ENHOR") Then
			Dim  c As Int
			For c =0 To 59
				Valores(c)=0
			Next
			For c = 5 To Packet.Length -1
				Valores(c - 5)= Packet.Data (c)-1 
				If Valores(c - 5) <> 1 Then Valores(c - 5)= 0
			Next 
			
			ActualizaValores
			
			
		Else If msg ="REPETIRMSG" Then
				SendTrama(TramaEnviada)
				Return
				
		Else If msg.Contains ("COMPLETED")=False Then
		
		
			Return	
			
		End If
		
		' completed!!!!
		
		PaqueteEnviado = False
		CmdGuardar.Enabled =True
		AniCargando.Stop(ImgCargando)
		ImgCargando.Visible =False 
	
	Catch
	    
	End Try
    
End Sub 

Sub SendTrama(data() As Byte )
	Dim Resultado As Boolean 
	Dim Reintento As Int
	
	CmdGuardar.Enabled =False	
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
			Sleep (200)
		End If

	Loop
	If Resultado = True Then
		Resultado =False
		Reintento =0
		Do 	While Resultado= False AND Reintento < 40
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

Sub CmdTerminar_Click
	Activity.Finish 
End Sub
Sub CmdGuardar_Click

	'WHOR
	Dim LongTrama As Int 
	If ValoresComunes.central.ConexionSegura Then
		LongTrama= 72
	Else
		LongTrama= 64
	End If
    Dim data(LongTrama) As Byte
	
	data(0)=87	
	data(1)=72
	data(2)=79
	data(3)=82
	Dim i As Int
	
	For i =0 To 59
		data(i+4)=Valores(i)+1
	Next
	If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
	SendTrama(data)
End Sub
