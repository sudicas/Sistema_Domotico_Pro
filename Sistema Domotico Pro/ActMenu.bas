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
	Dim TypeConfig As Int
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Dim ListView1 As ListView
	
	Dim TramaEnviada() As Byte
	'Dim NombreCentralControlada As String
	'Dim NumeroCental As Int
End Sub

Sub Activity_Create(FirstTime As Boolean)
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	Activity.LoadLayout ("FrmMenu")
	
	
	ListView1.height = 100%y
	ListView1.Width =Activity.Width

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

Sub Activity_Resume
	If ValoresComunes.CloseApp =True Then
		Activity.Finish
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then
		If Main.MyLan.IsInitialized = False Then Main.MyLan.Initialize( 0, "")
		If UDPSocket1.IsInitialized = False Then  ValoresComunes.IniUDP(UDPSocket1)
		
		ListView1.Clear
		Select Case TypeConfig
			Case 0
				ListView1.AddTwoLinesAndBitmap2  ( ValoresComunes.GetLanString ("TC"),ValoresComunes.GetLanString ("STC"),ValoresComunes.Alarma,0 )
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("ST"),ValoresComunes.GetLanString ("SWP"),ValoresComunes.Reloj,1)
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("ET"),ValoresComunes.GetLanString ("ECT"),ValoresComunes.reloOk,2)
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SSD") & 1,ValoresComunes.GetLanString ("SPD")& 1,ValoresComunes.Calendario,3)
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SSD") & 2,ValoresComunes.GetLanString ("SPD")& 2,ValoresComunes.Calendario,4)
				
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("reg97"),ValoresComunes.GetLanString ("SPD"),ValoresComunes.delete,21)
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("reg98") ,ValoresComunes.GetLanString ("SPD"),ValoresComunes.delete,22)

				
			
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SDT"),ValoresComunes.GetLanString ("SIT"),ValoresComunes.fechahora,6)
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SST"),ValoresComunes.GetLanString ("ITS"),ValoresComunes.fechahora,7)
				
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SS"),ValoresComunes.GetLanString ("SNV"),ValoresComunes.Scene2,5)
				
				
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("CBU"),ValoresComunes.GetLanString ("GCF"), ValoresComunes.ConfigUp,8)
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("RBU"),ValoresComunes.GetLanString ("RBU"), ValoresComunes.configdo,9)
				
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SCN"),ValoresComunes.GetLanString ("MND"), ValoresComunes.On,10)
				
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SSU"),ValoresComunes.GetLanString ("SUD"),ValoresComunes.persiana,11)
				
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SCD"),ValoresComunes.GetLanString ("MND"),ValoresComunes.CheckOn,12)
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SCO"),ValoresComunes.GetLanString ("MND"),ValoresComunes.Comando,13)
				
				
			
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanStringDefault("LsTT","Language Settings"),ValoresComunes.GetLanStringDefault ("SltL","Select language"),ValoresComunes.Voice,14)
				
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanStringDefault ("Status","Status"),ValoresComunes.GetLanStringDefault  ("SetSta","Configure states screen"),ValoresComunes.Sensor,15 )
				
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SAU"),ValoresComunes.GetLanString ("NHA"),ValoresComunes.Home,16  )
				'ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("COR"),ValoresComunes.GetLanString ("MCD"),ValoresComunes.Home,17  )
				
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("MNU"),ValoresComunes.GetLanString ("MNU"),ValoresComunes.Config,18)
				
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("pp"),ValoresComunes.GetLanString ("VIS"),ValoresComunes.Icono,19)
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("dcg"),ValoresComunes.GetLanString ("cdt"),ValoresComunes.Arduino,20)
			
			Case 1 'Horarios
				ListView1.AddTwoLinesAndBitmap2  ( ValoresComunes.GetLanString ("TC"),ValoresComunes.GetLanString ("STC"),ValoresComunes.Alarma,0 )
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("ST"),ValoresComunes.GetLanString ("SWP"),ValoresComunes.Reloj,1)
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("ET"),ValoresComunes.GetLanString ("ECT"),ValoresComunes.reloOk,2)
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SSD") & 1,ValoresComunes.GetLanString ("SPD")& 1,ValoresComunes.Calendario,3)
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SSD") & 2,ValoresComunes.GetLanString ("SPD")& 2,ValoresComunes.Calendario,4)
				
			
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SDT"),ValoresComunes.GetLanString ("SIT"),ValoresComunes.fechahora,6)
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SST"),ValoresComunes.GetLanString ("ITS"),ValoresComunes.fechahora,7)
				
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("reg97") ,"",ValoresComunes.delete,21)
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("reg98") ,"",ValoresComunes.delete,22)
			Case 2
				'backup
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("CBU"),ValoresComunes.GetLanString ("GCF"), ValoresComunes.ConfigUp,8)
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("RBU"),ValoresComunes.GetLanString ("RBU"), ValoresComunes.configdo,9)
			
			Case 3
				'las otras
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanStringDefault ("CsS","Configure Status Screen"),ValoresComunes.GetLanString ("SNV"), ValoresComunes.Sensor,30)
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanStringDefault ("CuF","Configure User Functons"),ValoresComunes.GetLanString ("SNV"), ValoresComunes.Funciones ,31)
				'ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("COR"),ValoresComunes.GetLanString ("MCD"),ValoresComunes.Home,17  )
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanStringDefault ("dev","devices"),ValoresComunes.GetLanStringDefault ("reg124","Setting Central"),ValoresComunes.Home,32  )
				'ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanStringDefault ("ipc","IP cameras"),ValoresComunes.GetLanStringDefault ("reg104","Settings"),ValoresComunes.ImgCamara,33  )
				
				'ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SCN"),ValoresComunes.GetLanString ("MND"), ValoresComunes.On,10)
				
				'ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SSU"),ValoresComunes.GetLanString ("SUD"),ValoresComunes.persiana,11)
				
				'ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SCD"),ValoresComunes.GetLanString ("MND"),ValoresComunes.CheckOn,12)
				'ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("SCO"),ValoresComunes.GetLanString ("MND"),ValoresComunes.Comando,13)
				
				'ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanStringDefault("LsTT","Language Settings"),ValoresComunes.GetLanStringDefault ("SltL","Select language"),ValoresComunes.Voice,14)
				
				'ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("Sensor"),ValoresComunes.GetLanString ("SSE"),ValoresComunes.Sensor,15 )
				'ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanString ("reg135"),ValoresComunes.GetLanString ("MND"),ValoresComunes.AlmOff ,23 )
			

			
			Case 4
			
			Case 5
			
		End Select
		
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
	Select  Value
		Case 0:	'Alarma
			StartActivity(ActAlarmas2)
	 	
	 	
		Case 1:	'Timetable
			Dim Lst As List
			Lst.Initialize
			
			Lst.Add (ValoresComunes.GetLanString ("reg51"))
			Lst.Add (ValoresComunes.GetLanString ("reg52"))
			Lst.Add (ValoresComunes.GetLanString ("reg53"))
			Lst.Add (ValoresComunes.GetLanString ("reg54"))
			Lst.Add (ValoresComunes.GetLanString ("reg55"))
			Lst.Add (ValoresComunes.GetLanString ("reg56"))
			Lst.Add (ValoresComunes.GetLanString ("reg57"))
			Lst.Add (ValoresComunes.GetLanString ("SPDI") & " 1"  )
			Lst.Add (ValoresComunes.GetLanString ("SPDI") & " 2"  )
			Lst.Add (ValoresComunes.GetLanString ("Cancel"))
			Dim Result As Int
			Result =InputList(Lst,ValoresComunes.GetLanString ("reg75"), 0)
			If Result > -1 And Result < 9 Then
				Dim li As List
				li.Initialize
				li.Add (Result)
				
				ActHorarios2.Days  = li
				StartActivity(ActHorarios2)
			End If
			
		Case 2:	'Timetable Abilitados
			StartActivity(ActEnableHorarios)
		Case 3:	'Especial 1
			ActDiaEspecial.TipoEspecial =1
			StartActivity(ActDiaEspecial)
		Case 4:	'Especial 2
			ActDiaEspecial.TipoEspecial =2
			StartActivity(ActDiaEspecial)
		Case 5:	'Scenes
			StartActivity( ActConfigEscenas)
		Case 6:	'Configura Fecha Hora

			Dim LongTrama As Int
			If ValoresComunes.central.ConexionSegura Then
				LongTrama= 20
			Else
				LongTrama= 12
			End If
			Dim DATA(LongTrama) As Byte
			DATA(0)=83
			DATA(1)=69
			DATA(2)=84
			DATA(3)=70
			DATA(4)=72
			'setDateDs1307(byte second, byte minute, byte hour, byte dayOfWeek, byte dayOfMonth, byte month, byte year)
			DATA(5)= DateTime.GetSecond (DateTime.Now )
			DATA(6)= DateTime.GetMinute  (DateTime.Now )
			DATA(7)=DateTime.GetHour   (DateTime.Now )
			DATA(8)= DateTime.GetDayOfWeek (DateTime.now)-1
			If DATA(8)=0 Then DATA(8)=7
			DATA(9)=DateTime.GetDayOfMonth (DateTime.Now )
			DATA(10)=DateTime.GetMonth (DateTime.Now )
			DATA(11)=DateTime.GetYear  (DateTime.Now )-2000
			If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (DATA)
			SendTrama(DATA)
		
	 
		
		Case 7:	'Estado Actual
			Dim DATA() As Byte
			Dim Trama As String
			If ValoresComunes.central.ConexionSegura Then
				Trama = "ESTADOINST" & ValoresComunes.Central.Password
			Else
				Trama = "ESTADOINST"
			End If
	
			DATA = Trama.GetBytes("UTF8")
			SendTrama(DATA)
		Case 8:	'Crea copia de seguridad
			Dim d As FileDialog
			d.FilePath = File.DirDefaultExternal
			d.ShowOnlyFolders= True
			Dim Result As Int
			Result= d.Show (ValoresComunes.GetLanString ("reg78"), ValoresComunes.GetLanString ("Ok"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		
			If Result=  DialogResponse.POSITIVE  Then
				Dim ruta As String
				ruta= d.FilePath
				Dim myZip As  ABZipUnzip
			
				myZip.ABZipDirectory( File.DirInternal , d.FilePath & "/OLMHCONFIG.piz")
				ToastMessageShow(ValoresComunes.GetLanString ("reg139"),True)
			End If
		Case 9
			Dim d As FileDialog
			d.FilePath = File.DirDefaultExternal
			d.ShowOnlyFolders= False
			d.FileFilter=".piz"
			Dim Result As Int
		
		
		
		
			Result= d.Show ( "Select backup copy", "Ok","Cancel",""  ,Null)
		
			If Result=  DialogResponse.POSITIVE  Then
			
				Dim myZip As  ABZipUnzip
				Dim ruta As String
				ruta =  d.FilePath & "/" & d.ChosenName
				If ruta.Length >0 And d.ChosenName.Length > 0   Then
					myZip.ABUnzip ( ruta, File.DirInternal)
					'ValoresComunes.StartFileSystem
					'ValoresComunes.Centrales=  File.ReadList (File.DirInternal , "Centrales" )
					'ValoresComunes.CargaIp
					'ActConsignas.CentralCargada = 150
					'ValoresComunes.CargaCircuitos
					'ValoresComunes.CargaScenes
					'ValoresComunes.CargaFuciones
	
					'ValoresComunes.CargaComandos
					'ValoresComunes.CargaComandosComunes
					'ValoresComunes.CargaCondicionados
					'ValoresComunes.CargaConexionExterna
					'ValoresComunes.CargaNombreSensores
					'ValoresComunes.CargaSensores
					'ValoresComunes.CargaCamara
		
					ToastMessageShow(ValoresComunes.GetLanString ("reg139"),True)
					ValoresComunes.CloseApp =True
					Activity.Finish
				End If
		
			End If
		Case 10:	'Configura Circuitos
			StartActivity(ActConfigCir)
		Case 11:	'Configura Circuitos
			StartActivity(ActPersianas)
		Case 12:	'	condicionado
			StartActivity(ActConfigCondicionados)
		Case 13:	'comando
			StartActivity(ActConfigComandos)
		Case 14 'Voice control
			StartActivity(ActConfigVoice)
		Case 15 'Sensores
			StartActivity(ActConfigSensors)
		
		Case 16:	'Config Central
			StartActivity(ActCentrales)
		Case 17:	'Administrador
			StartActivity(ActConfigComandosComu)
		Case 18:	'Administrador
			'StartActivity(ActAdmin)
		
		Case 19:	'Pagina Proyecto
			Dim p As PhoneIntents
			StartActivity(p.OpenBrowser("http://domotica-Arduino.es"))
		Case 20:	'Codigo arduino
			Dim p As PhoneIntents
			StartActivity(p.OpenBrowser("http://domotica-arduino.es/download-arduino-home-automation-arduino-apps/"))
		
		Case 21:'Borrar Timetable
			Dim Rsp As Int
			Rsp=Msgbox2(ValoresComunes.GetLanString ("reg110"),ValoresComunes.GetLanString ("reg77"),ValoresComunes.GetLanString ("Y"),ValoresComunes.GetLanString ("N"),"",Null  )
			If Rsp=  DialogResponse.POSITIVE Then
				Dim S As String
				If ValoresComunes.central.ConexionSegura Then
					S="CLEARHORARIO" & ValoresComunes.Central.Password
				Else
					S="CLEARHORARIO"
				End If
			
				Dim DATA() As Byte
				DATA= S.GetBytes ("UTF8")
				SendTrama(DATA)
				ProgressDialogShow(ValoresComunes.GetLanString ("reg109"))
			End If

		
		Case 22:'Borrar Dias Especiales
			Dim Rsp As Int
			Rsp=Msgbox2(ValoresComunes.GetLanString ("reg108"),ValoresComunes.GetLanString ("reg77"),ValoresComunes.GetLanString ("Y"),ValoresComunes.GetLanString ("N"),"",Null )
			If Rsp=  DialogResponse.POSITIVE Then
				Dim S As String
				If ValoresComunes.central.ConexionSegura Then
					S="CLEARESPCDAY" & ValoresComunes.Central.Password
				Else
					S="CLEARESPCDAY"
				End If
			
				Dim DATA() As Byte
				DATA= S.GetBytes ("UTF8")
				SendTrama(DATA)
				ProgressDialogShow(ValoresComunes.GetLanString ("reg109"))
			End If
		Case 23:'alarmas
			'ActNotification.CambioNombre =True
			StartActivity(ActConfigNotification)
		
		Case 30:'
	
			StartActivity(ActConfigFreeTxt)
			
		Case 31:'
	
			StartActivity(ActConfigFunciones)
		Case 32:'
	
			StartActivity(ActCentrales)

	End Select
	 
	
End Sub
Sub SendTrama(data() As Byte )
	Dim Resultado As Boolean
	Dim Reintento As Int
	
	TramaEnviada=data
	
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
		'Main.ConexionEstablecida = False
		'StartActivity(Main)
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
		
		If msg.Contains ("ESTACT") Then

			Dim Txt As String
			Select Packet.Data (6)
				Case 2:		'Monday
					Txt =ValoresComunes.GetLanString ("reg51") & " -"
				Case 3:
					Txt =ValoresComunes.GetLanString ("reg52") & " -"
				Case 4:
					Txt =ValoresComunes.GetLanString ("reg53") & " -"
				Case 5:
					Txt =ValoresComunes.GetLanString ("reg54") & " -"
				Case 6:
					Txt =ValoresComunes.GetLanString ("reg55") & " -"
				Case 7:
					Txt =ValoresComunes.GetLanString ("reg56") & " -"
				Case 8:
					Txt =ValoresComunes.GetLanString ("reg57") & " -"
				Case 9:
					Txt = ValoresComunes.GetLanString ("SPDI") & "1 -"
				Case 10:
					Txt =ValoresComunes.GetLanString ("SPDI") & "2 -"
			End Select
				
			Txt= Txt & (Packet.Data (7)-1) & ":" & (Packet.Data (8)-1) & CRLF & (Packet.Data (9)-1)  & "/" & (Packet.Data (10)-1) & "/" & (Packet.Data (11)-1)
				
					
			ToastMessageShow(Txt,True)
			 
		Else If msg.Contains ("BORRADOS") Then
			ProgressDialogHide
			ToastMessageShow(ValoresComunes.GetLanString ("reg139"),False)
		Else
			If msg ="REPETIRMSG" Then
				SendTrama(TramaEnviada)
			Else
			
				If msg = "SETFHOK" Then
					ToastMessageShow(ValoresComunes.GetLanString ("reg139"),True)
				Else
					'ToastMessageShow(msg,True)
				End If
			End If
		End If
	
	Catch
	    
	End Try
End Sub

