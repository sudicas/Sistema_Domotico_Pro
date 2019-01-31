Type=Activity
Version=7.01
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region Module Attributes
	#FullScreen: True
	#IncludeTitle: False
#End Region

'Activity module
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim TipoEspecial As Byte 
	
	'Dim NombreCentralControlada As String
	Dim UDPSocket1 As UDPSocket
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	
	 
	Dim Fechas As List
	
	Dim ListView1 As ListView
	Dim CmdGuardar As Button
	Dim CmdNew As Button
	Private CmdTerminar As Button
	
	Dim TramaEnviada() As Byte
	Dim ImgCargando As ImageView 
	Dim AniCargando As  Animation
	Dim PaqueteEnviado As Boolean 
	'Dim NumeroCental As Int
	
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	If ValoresComunes.Centrales .IsInitialized = False  Then Return	
	'UDPSocket1.Initialize("UDP", 0, 8000)
	
	Activity.LoadLayout ("FrmConfig2")

	Dim he As Int = 40dip
	
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
	
	CmdNew.Top=Activity.Height-he
	CmdNew.Height =he
	CmdNew.Width =he
	CmdNew.Left =Activity.Width   - ((he  * 3)   +12dip)
	CmdNew.SetBackgroundImage(ValoresComunes.CmdImgNew)
	CmdNew.Text = ""
	'CmdNew.Text = ValoresComunes.GetLanString ("reg185")
End Sub
Sub Activity_Resume
	If ValoresComunes.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then
		If ImgCargando.IsInitialized = False Then
				
			ImgCargando.Initialize ("ImgCargando")
			ImgCargando.Bitmap = ValoresComunes.Cargando 
			ImgCargando.Gravity = Gravity.FILL 
			Activity.AddView (ImgCargando,  PerXToCurrent(40)	, PerYToCurrent(35),PerXToCurrent(20) ,PerXToCurrent(20) )
		End If
		If Main.MyLan.IsInitialized = False Then Main.MyLan.Initialize( 0, "")
		If UDPSocket1.IsInitialized = False Then  ValoresComunes.IniUDP(UDPSocket1) 
		
		Fechas.Initialize 
		Dim s As String
		
		CmdGuardar.Enabled =False
		CmdNew.Enabled =False
		
		
		
		
		AniCargando.InitializeRotateCenter( "AniCargando", 0, 180, ImgCargando)
		AniCargando.Duration = 1000
	    AniCargando.RepeatCount =5
	    AniCargando.RepeatMode = AniCargando.REPEAT_REVERSE  
		
		
		
		If TipoEspecial=2 Then 
			
			s="READDAY2" 
		Else
			s="READDAY1"
		End If
		If ValoresComunes.central.ConexionSegura Then s = s & ValoresComunes.Central.Password    
		Dim DATA() As Byte 
		DATA= s.GetBytes ("UTF8")
		SendTrama(DATA)
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
Sub CmdNew_Click
	If Fechas.Size >24 Then
		Msgbox(ValoresComunes.GetLanString ("reg143"),ValoresComunes.GetLanString ("reg69"))
		Return
	End If
	Dim Dialog As DateDialog 
	Dialog.DateTicks = DateTime.Now 
	
	Dim Resp As Int
	Resp= Dialog.Show (ValoresComunes.GetLanString ("reg144"),ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("Ok"),ValoresComunes.GetLanString ("Cancel"),"",Null )
	If Resp = DialogResponse.POSITIVE Then
		If Dialog.DateTicks < DateTime.Add (DateTime.Now ,0,0, -1) Then
			Msgbox(ValoresComunes.GetLanString ("reg145"),ValoresComunes.GetLanString ("reg120"))
			Return
		End If
		
		If Fechas.Size =0 Then 
			Fechas.Add (Dialog.DateTicks )
		Else
			Dim p As Int
			For p=0 To Fechas.Size -1
			
				If Fechas.Get (p)= Dialog.DateTicks Then
					Msgbox(ValoresComunes.GetLanString ("reg146"),ValoresComunes.GetLanString ("reg147"))
					Return
				End If
				If Fechas.Get (p)> Dialog.DateTicks Then
					Fechas.InsertAt   (p,Dialog.DateTicks)
					RefrescaPantalla
					Return
				End If
				
			Next
			Fechas.Add (Dialog.DateTicks )
		End If
		RefrescaPantalla
	End If		
End Sub

Sub CmdGuardar_Click
	
	
	
	Dim Rsp As Int
	Rsp = Msgbox2(ValoresComunes.GetLanString ("reg76"),ValoresComunes.GetLanString ("reg77"),ValoresComunes.GetLanString ("Y"),ValoresComunes.GetLanString ("N"),"",Null)
	If Rsp = DialogResponse.POSITIVE Then
	

	
	
		Dim LongTrama As Int 
		If ValoresComunes.central.ConexionSegura Then
			LongTrama= 66
		Else
			LongTrama= 58
		End If
	    Dim data(LongTrama) As Byte
		data(0)=87
		data(1)=82
		data(2)=73
		data(3)=68
		data(4)=65
		data(5)=89
		data(6)=69
		
		data(7)=TipoEspecial
		
		Dim I As Int
		
		For I =0 To Fechas.Size -1 
			data((I*2)+8)=DateTime.GetMonth (Fechas.Get(I))
			data((I*2)+9)=DateTime.GetDayOfMonth  (Fechas.Get(I))
		Next
		If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
		SendTrama(data)
	End If
	
	
End Sub

Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim R As Int
	R=Msgbox2(ValoresComunes.GetLanString ("reg148"),ValoresComunes.GetLanString ("reg66"),ValoresComunes.GetLanString ("Y"),ValoresComunes.GetLanString ("N"),"",Null)
	If R = DialogResponse.POSITIVE Then 
		Fechas.RemoveAt (Position)
		RefrescaPantalla
	End If	
End Sub
Sub RefrescaPantalla()
	ListView1.Clear 
	
	Dim c As Int
	DateTime.DateFormat="dd/MM/yyyy"
	For c =0 To Fechas.Size -1
		
		ListView1.AddSingleLine  (DateTime.Date(Fechas.Get (c)))		
	Next
End Sub
Sub SendTrama(data() As Byte )
	Dim Resultado As Boolean 
	Dim Reintento As Int
	
	CmdGuardar.Enabled =False
	CmdNew.Enabled =False
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
		Packet.Initialize(data, ValoresComunes.ip, ValoresComunes.Puerto )
	    UDPSocket1.Send(Packet)	
		Return True
	Catch
	    Return False
	End Try
End Sub
Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
		Dim msg As String
	    msg = BytesToString(Packet.Data, Packet.Offset, Packet.Length, "UTF8")
		
				
		If msg.Contains ("CFDA") Then
			Fechas.Clear 
			
			Dim Valores(50) As Byte 
			Dim p As Int
			For p =0 To 49
				Valores(p)=Packet.Data (p+4)
			Next
			Dim Fc As List 
			Dim c As Int 
			
			Fc.Initialize 
			DateTime.DateFormat="MM/dd/yyyy"
			For c =0 To 48 Step 2
				If Valores(c)>0 And Valores(c)<13 And Valores (c+1)>0 And Valores(c+1)<32 Then
					Dim F As Long 


					F = DateTime.DateParse (Valores(c) & "/" & Valores(c+1) &   "/" & DateTime.GetYear (DateTime.Now)  )
					If F < DateTime.Now Then F= DateTime.Add (F,1,0,0)
					Fc.Add (F)
				End If
			Next
			Do While Fc.Size >0 
				Dim c As Int
				Dim Pos As Int
				Dim ValMax As Long 
				For c =0 To Fc.Size -1
					If ValMax< Fc.Get (c) Then
						ValMax=Fc.Get (c)
						Pos=c
					End If
				Next
				Fc.RemoveAt (Pos)
				Fechas.InsertAt   (0,ValMax)
			Loop
			RefrescaPantalla


		Else If msg ="REPETIRMSG" Then
				SendTrama(TramaEnviada)
				Return
		Else If msg = "WRIDAYE" Then
			ToastMessageShow(ValoresComunes.GetLanString ("reg139"),True)
						
		Else If msg.Contains ("COMPLETED")=False Then
			SendTrama(TramaEnviada)
			Return
		End If
			

		PaqueteEnviado = False
		CmdNew.Enabled =True
		CmdGuardar.Enabled =True
		AniCargando.Stop(ImgCargando)
		ImgCargando.Visible =False 
	
	Catch
	    
	End Try
    
End Sub 



Sub CmdTerminar_Click
	Activity.Finish
End Sub