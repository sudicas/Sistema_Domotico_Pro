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
'Activity module
Sub Process_Globals
    Dim VR As VoiceRecognition
    Dim TTS1 As TTS
	Dim UDPSocket1 As UDPSocket 
End Sub

Sub Globals
	
	
	
	Dim TramaEnviada() As Byte
	Dim ImgCargando As ImageView 
	Dim AniCargando As  Animation
	
	Dim PaqueteEnviado As Boolean
	
	Dim Configuraciones As List 
	
	'Dim ImgMicro As ImageView
End Sub
Sub Activity_Pause (UserClosed As Boolean)
	If UserClosed Then

		UDPSocket1.Close 
		Activity.Finish 
	End If

End Sub
Sub Activity_Create(FirstTime As Boolean)
   If ValoresComunes.Centrales .IsInitialized = False  Then Return
    'Activity.LoadLayout("FrmVoz")
	
	If  File.Exists ( File.DirInternal ,"ConfigIdioma") = False  Then ValoresComunes.CreaArchivoConfigIdioma
	Configuraciones = File.ReadList(File.DirInternal ,"ConfigIdioma" )

	If FirstTime Then
        VR.Initialize("VR")
        TTS1.Initialize("TTS1")
    End If	
    If VR.IsSupported Then
		ToastMessageShow(ValoresComunes.GetLanString ("reg169"), False)  
		 
    Else
		ToastMessageShow(ValoresComunes.GetLanString ("reg170"), False) 
       Activity.Finish 
    End If

	'ImgMicro.Bitmap = LoadBitmap(File.DirAssets, "voicebig.png")
	'Dim tamaño As Int
	'tamaño = Activity.Height  /2
	'ImgMicro.Width = tamaño
	'ImgMicro.Height =tamaño
	'ImgMicro.Left = (Activity.Width - tamaño )/2
	'ImgMicro.Top = tamaño/2
	
	
	Dim S As String 
	S= Configuraciones.Get (0)
	
	TTS1.SetLanguage(S, S.ToUpperCase   )
	VR.Language=S
	VR.Prompt =ValoresComunes.GetLanString ("reg171")
	
	Dim i As Intent
	i.Initialize("android.speech.action.RECOGNIZE_SPEECH", "")
	i.PutExtra("android.speech.extras.SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS", 400 )

	VR.Listen2(i)
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
	    AniCargando.RepeatCount =5
	    AniCargando.RepeatMode = AniCargando.REPEAT_REVERSE  
		
		
		
	Else
		Activity.Finish 
		StartActivity(Main) 
	End If 
End Sub
Sub VR_Result (Success As Boolean, Texts As List)
	If VR.IsSupported = False Then Activity.Finish 
    If Success = True Then
        
        'TTS1.Speak( Texts.Get(0), True   )
		ReconocerComando(AdaptarComando(Texts.Get(0)))
	Else
		MostraNoSeReconoce		
    End If
End Sub
Sub MostraNoSeReconoce

	TTS1.Speak( ValoresComunes.GetLanString ("reg172"), True   )
	Activity.Finish 
	

End Sub
Sub AdaptarComando(Comando As String ) As String
	Comando = Comando.ToUpperCase 
	
	Comando = Comando.Replace ("Á","A")
	Comando = Comando.Replace ("É","E")
	Comando = Comando.Replace ("Í","I")
	Comando = Comando.Replace ("Ó","O")
	Comando = Comando.Replace ("Ú","U")
	
	Return Comando
End Sub 
Sub ReconocerComando(Comando As String)
	ToastMessageShow(Comando, True)
		
		If Comando.Contains (Configuraciones.Get (1)) Then	'Comando seleccion Scene
			Dim pos As Int
			For pos =0 To 9
				Dim Name As String
				Name = AdaptarComando(ValoresComunes.Scenes (pos).Nombre)
				If Comando.Contains (Name) Then
					Dim LongTrama As Int 
					If ValoresComunes.central.ConexionSegura Then
						LongTrama= 13
					Else
						LongTrama= 5
					End If
					Dim data(LongTrama) As Byte
					data(0)=83
					data(1)=83
					data(2)=67
					data(3)=69
					data(4)= pos +1
					If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
				    SendTrama(data)		
					'MostrarCompletado
					
					Return
				End If
			Next 
		End If
				
		'Comandos
		
		Dim pos As Int
		For pos =0 To ValoresComunes.Comandos.Size -1
			Dim Cmd As Scene
			Cmd = ValoresComunes.Comandos.Get  (pos)
			If Comando.Contains (AdaptarComando(Cmd.Nombre )) Then
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
				data(3)=65	'A
				data(4)=78	'N
				data(5)=68	'D
				data(6)=79	'O
				data(7)=pos + 1
				If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
			    SendTrama(data)
				'MostrarCompletado				
				Return
			End If
		Next 
		
		
		
		Dim cir As Circuito 
		Dim pos As Int
		Dim NewVal As Int
		NewVal = -1
		
		For pos =0 To ValoresComunes.Circuitos .Length -1
			cir = ValoresComunes.Circuitos(pos)
			
			Dim LstComand As List 
			LstComand.Initialize 		
			Dim Name As String
			Name = AdaptarComando(cir.Nombre)
			If Name<>"" Then
				LstComand.Add (Name)
			
				If Name.Contains ("ADO.") Then LstComand.Add (Name.Replace ("ADO." ,ValoresComunes.GetLanString ("reg173")))
				If Name.Contains ("TV.") Then	LstComand.Add (Name.Replace ("TV." ,ValoresComunes.GetLanString ("reg174")))
				If Name.Contains ("A.C.") Then	LstComand.Add (Name.Replace ("A.C." ,ValoresComunes.GetLanString ("reg175")))			

				Dim ncmd As Int
				For ncmd =0 To LstComand.Size-1
					'LstComand.Set (ncmd ,AdaptarComando(LstComand.Get (ncmd)))
					If Comando.Contains(LstComand.Get (ncmd) ) Then
					'Circuito localizado
					
					Select  cir.Rango 
					
					Case 2:	'Circuitos Alumbrado
						If Comando.Contains (Configuraciones.Get (2)) Then
							NewVal=3
						End If
						If Comando.Contains (Configuraciones.Get (3)) Then
							NewVal= 0
						End If
						
						
					Case 1,7,8,13,4,19,24,39,15,25,43,51:	'Circuitos DIGITALES
						If Comando.Contains (Configuraciones.Get (2)) Then
							NewVal= 1 'ValoresComunes.Circuitos (pos).Rango
						End If
						If Comando.Contains (Configuraciones.Get (3)) Then
							NewVal= 0
						End If

					
					
					Case 20, 21,22:	'Temp. Consigna
						
					Case 34,35:	'Persiana o toldo
						If Comando.Contains (Configuraciones.Get (4)) Then
							NewVal=100
						End If
						If Comando.Contains (Configuraciones.Get (5)) Then
							NewVal= 0
						End If	
					
					End Select
					
					
					'SVAL
					If NewVal > -1 Then 
						
						Dim LongTrama As Int 
						If ValoresComunes.central.ConexionSegura Then
							LongTrama= 14
						Else
							LongTrama= 6
						End If
					    Dim data(LongTrama) As Byte
						data(0)=83
						data(1)=86
						data(2)=65
						data(3)=76
						data(4)= pos +1 
						data (5)=NewVal+1
						If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
						'Msgbox(NewVal,pos)

					    SendTrama(data)
						'MostrarCompletado	
						Return
					End If
				End If
				Next
			
			End If
			
			
			
		Next
		MostraNoSeReconoce
		'VR.Listen
End Sub 

Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
		Dim msg As String
	    msg = BytesToString(Packet.Data, Packet.Offset, Packet.Length, "UTF8")
		

		If msg= "COMPLETED" Or msg.Contains ("EVAL") Then
			
			PaqueteEnviado = False
			AniCargando.Stop(ImgCargando)
			ImgCargando.Visible =False
			
			ToastMessageShow(ValoresComunes.GetLanString ("reg139"),True)
			TTS1.Speak(ValoresComunes.GetLanString ("reg139") , True   )
			Activity.Finish 		
		End If
		If msg ="REPETIRMSG" Then
				SendTrama(TramaEnviada)
		End If
	
	Catch
	    
	End Try
	    
End Sub 
Sub SendTrama(data() As Byte )
	Dim Resultado As Boolean 
	Dim Reintento As Int
	PaqueteEnviado =False
	TramaEnviada= data
	ImgCargando.Visible =True
	AniCargando.Start (ImgCargando)
	'ImgCargando.Visible =True
	
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
	
		Packet.Initialize(data, ValoresComunes.ip,  ValoresComunes.Puerto )
	    UDPSocket1.Send(Packet)	
		Return True
	Catch
	    Return False
	End Try
End Sub
Sub Activity_Click
	 VR.Listen 'calls the voice recognition external activity. Result event will be raised.
End Sub






