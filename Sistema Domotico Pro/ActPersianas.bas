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
	Dim UDPSocket1 As UDPSocket 
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim CmdGuardar As Button
	Dim CmdTerminar As Button
	Dim ListView1 As ListView
	
	
	Dim TramaEnviada() As Byte
	Dim ImgCargando As ImageView 
	Dim AniCargando As  Animation
	
	Dim PaqueteEnviado As Boolean
	Dim Valores(60) As  Int 
	
	
	
End Sub
Sub Activity_Create(FirstTime As Boolean)
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	Activity.LoadLayout ("frmconfig")
	Activity.Title =ValoresComunes.GetLanString ("SSU")
	
	Dim he As Int = 40dip
	
	ListView1.Height =  Activity.Height-he
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
	
End Sub
Sub AniCargando_AnimationEnd
	
   If PaqueteEnviado = True Then 
		AniCargando.Start (ImgCargando)
		SendTrama(TramaEnviada)
	End If
End Sub
Sub Activity_Resume	
	If ValoresComunes.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then
		If Main.MyLan.IsInitialized = False Then Main.MyLan.Initialize( 0, "")
		If ImgCargando.IsInitialized = False Then
			ImgCargando.Initialize ("ImgCargando")
			ImgCargando.Bitmap = ValoresComunes.Cargando 
			ImgCargando.Gravity = Gravity.FILL 
			Activity.AddView (ImgCargando,  PerXToCurrent(40)	, PerYToCurrent(35),PerXToCurrent(20) ,PerXToCurrent(20) )
		End If
		If UDPSocket1.IsInitialized = False Then  ValoresComunes.IniUDP(UDPSocket1) 
		AniCargando.InitializeRotateCenter( "AniCargando", 0, 180, ImgCargando)
		AniCargando.Duration = 1000
	    AniCargando.RepeatCount =5
	    AniCargando.RepeatMode = AniCargando.REPEAT_REVERSE  
		
		
		Dim T As String 
		If ValoresComunes.central.ConexionSegura Then
			T="TIMPERSIANA" & ValoresComunes.Central.Password   
		Else
			T="TIMPERSIANA" 
		End If

		SendTrama(T.GetBytes ("UTF8") )
	Else
		Activity.Finish 
		StartActivity(Main)
	End If
End Sub

Sub Activity_Pause (UserClosed As Boolean)

	UDPSocket1.Close 
	Activity.Finish
End Sub



Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim Lst As List
	Lst.Initialize 
	Lst.Add (ValoresComunes.GetLanString ("reg162"))
	Lst.Add (ValoresComunes.GetLanString ("reg163"))
	Lst.Add (ValoresComunes.GetLanString ("reg164"))
	Lst.Add(ValoresComunes.GetLanString ("Cancel"))
	
	Dim NewVal As Int 
	Dim Opcion As Int
	Opcion =InputList( Lst,ValoresComunes.GetLanString ("reg75"), 0)
	
	If Opcion =3 Or Opcion <0 Then	Return
	
	If Opcion = 2 Then
		'Reset de posicion
		Dim Rsp As Int
		Rsp = Msgbox2(ValoresComunes.GetLanString ("reg165"),ValoresComunes.GetLanString ("reg77"),ValoresComunes.GetLanString ("Y"),ValoresComunes.GetLanString ("N"),"",Null)
		If Rsp = DialogResponse.POSITIVE Then
			Dim LongTrama As Int 
			If ValoresComunes.central.ConexionSegura Then
				LongTrama= 16
			Else
				LongTrama= 8
			End If
			Dim data(LongTrama) As Byte
			'RESTPER
			data(0)= 82	'R
			data(1)= 69	'E
			data(2)= 83	'S
			data(3)= 84	'T
			data(4)= 80	'P
			data(5)= 69	'E
			data(6)= 82	'R
			data(7)= Position +1
			If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
			SendTrama(data)
		End If
	Else 
		Dim Dialogo As NumberDialog 
		Dialogo.Digits =3
					
		Dialogo.Number = Valores((Opcion*10)+Value)
		
		Dim Result As Int
		Result = Dialogo.Show (ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		
		If Result=  DialogResponse.POSITIVE  Then
			NewVal =Dialogo.Number
			If NewVal > 200 Then 
				Msgbox(ValoresComunes.GetLanString ("reg166"), ValoresComunes.GetLanString ("reg120"))
				Return
			End If
		Else
			Return			
		End If
	End If
	If Opcion =0 Then
		Valores(Value)=NewVal
	End If
	If Opcion =1 Then
		Valores(Value+30)=NewVal
	End If
	
	
	
	RefrescaPantalla
End Sub
Sub CmdGuardar_Click
	
	Dim Rsp As Int
	Rsp = Msgbox2(ValoresComunes.GetLanString ("reg76"),ValoresComunes.GetLanString ("reg77"),ValoresComunes.GetLanString ("Y"),ValoresComunes.GetLanString ("N"),"",Null)
	If Rsp = DialogResponse.POSITIVE Then
		Dim LongTrama As Int 
		If ValoresComunes.central.ConexionSegura Then
			LongTrama= 73
		Else
			LongTrama= 65
		End If
		Dim data(LongTrama) As Byte
		data(0)= 87	'W
		data(1)= 80	'P
		data(2)= 69	'E
		data(3)= 82	'R
		data(4)= 83	'S
		
		
		Dim I As Int
		For I = 0 To 59
			data(I + 5) = Valores(I)+1
		Next
	
	
	
		

		If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
		SendTrama(data)
	End If
End Sub
Sub RefrescaPantalla
	ListView1.Clear 
	Dim i As Int 
	Dim NumPer As Int
	Dim V As Int
	NumPer=0

	For i =0 To 29
		If ValoresComunes.Circuitos (i).Rango=34 Or  ValoresComunes.Circuitos (i).Rango=35 Then
	
			ListView1.AddTwoLines2 (ValoresComunes.Circuitos (i).Nombre, "UP = " & Valores(NumPer) & " <> DOWN= " & Valores(NumPer +30),NumPer)
			NumPer=NumPer+1
		End If
	Next
	
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
Sub SendTrama(data() As Byte )
	Dim Resultado As Boolean 
	Dim Reintento As Int
	
	CmdGuardar.Enabled =False
	AniCargando.Start (ImgCargando)
	PaqueteEnviado =False
	ListView1.Enabled =False	
	
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
Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
		Dim msg As String
	    msg = BytesToString(Packet.data, Packet.Offset, Packet.Length, "UTF8")
		
		If msg.Contains ("LECPE") Then
			
			Dim p As Int
			For p =5 To Packet.Length -1
				Valores(p-5)=Packet.data (p)-1
				
				If Valores(p-5) < 0 Then
					Valores(p-5)=Valores(p-5) +256
				End If
			Next
			
			RefrescaPantalla

			
		Else
			If msg ="REPETIRMSG" Then
				SendTrama(TramaEnviada)
				Return
			Else
				''ToastMessageShow(msg,True)
			End If
		End If
		PaqueteEnviado = False
		CmdGuardar.Enabled =True
		ListView1.Enabled =True
		AniCargando.Stop(ImgCargando)
		ImgCargando.Visible =False 
	Catch
	    
	End Try
	    
End Sub
Sub CmdTerminar_Click
	Activity.Finish
End Sub