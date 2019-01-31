Type=Activity
Version=7.01
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
#End Region
'https://www.b4x.com/android/forum/threads/okhttp-replaces-the-http-library.54723/
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

	'Dim HttpClient1 As OkHttpClient
	Dim UpdateCentral As Boolean
	Dim Forzar3g As Boolean
	Dim Conectado As Boolean
	Dim CentraltoConnect As Int
	Dim wifi As MLwifi
		
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim ListView1 As ListView

	Private Cmd1 As ImageView
	Private Cmd2 As ImageView
	Private Cmd3 As ImageView
	Private Cmd4 As ImageView
	Private Cmd5 As ImageView
	Private Cmd6 As ImageView
	Private Cmd7 As ImageView
	Private Cmd8 As ImageView
	Private Cmd9 As ImageView
	Private Cmd10 As ImageView
	Private Cmd11 As ImageView
	Private Cmd12 As ImageView
	Private Label1 As Label
	Private Label2 As Label
	Private Label3 As Label
	Private Label10 As Label
	Private Label4 As Label
	Private Label5 As Label
	Private Label6 As Label
	Private Label7 As Label
	Private Label8 As Label
	Private Label9 As Label
	Private Label11 As Label
	Private Label12 As Label

	
	Private TabConfig As TabHost
	Private Cmd01 As ImageView
	Private Cmd010 As ImageView
	Private Cmd011 As ImageView
	Private Cmd012 As ImageView
	Private Cmd02 As ImageView
	Private Cmd03 As ImageView
	Private Cmd04 As ImageView
	Private Cmd05 As ImageView
	Private Cmd06 As ImageView
	Private Cmd07 As ImageView
	Private Cmd08 As ImageView
	Private Cmd09 As ImageView
	Private Label01 As Label
	Private Label010 As Label
	Private Label011 As Label
	Private Label012 As Label
	Private Label02 As Label
	Private Label03 As Label
	Private Label04 As Label
	Private Label05 As Label
	Private Label06 As Label
	Private Label07 As Label
	Private Label08 As Label
	Private Label09 As Label
	
	
	
	'variable ping
 

	
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	If ValoresComunes.Centrales .IsInitialized = False  Then Return

	
	
	
	Activity.LoadLayout("FrmTab")
	
	ListView1.Initialize ("ListView1")
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
	
	
	
	
	TabConfig.Width = Activity.Width
	TabConfig.Height =Activity.Height
	
	If ValoresComunes.Centrales.Size > 1 Then
		TabConfig.AddTabWithIcon2 (""  ,ValoresComunes.MnuHome,  ValoresComunes.MnuHome, ListView1) 'load the layout file of each page
		'ValoresComunes.GetLanStringDefault ("dev","Devices")
	Else
		
	End If
	
	
	TabConfig.AddTabWithIcon ("" ,ValoresComunes.MnuCircuitos, ValoresComunes.MnuCircuitos,  "FrmMosaico.bal") 'load the layout file of each page
	TabConfig.AddTabWithIcon ("", ValoresComunes.MnuConfigLt, ValoresComunes.MnuConfigLt,    "FrmMosaico2.bal") 'load the layout file of each page
	' ValoresComunes.GetLanStringDefault ("reg104","Settings")
	
	'Imagenes
	
	Cmd1.Bitmap =ValoresComunes.Bombillaon
	Cmd2.Bitmap =ValoresComunes.Temperatura
	Cmd3.Bitmap =ValoresComunes.scene2 'cambio
	
	Cmd4.Bitmap = ValoresComunes.SensorGenerico
	
	
	'Cmd4.Bitmap =ValoresComunes
	
	Cmd5.Bitmap =ValoresComunes.Funciones
	Cmd6.Bitmap =ValoresComunes.CheckOn
	Cmd7.Bitmap =ValoresComunes.AlmOff
	Cmd8.Bitmap =ValoresComunes.Comando
	Cmd9.Bitmap =ValoresComunes.ImgGrafico
	Cmd10.Bitmap =ValoresComunes.Sensor
	Cmd11.Bitmap =ValoresComunes.voice
	Cmd12.Bitmap =ValoresComunes.ImgEnd
	
	Label1.Text = ValoresComunes.GetLanString ("reg151").ToUpperCase
	Label2.Text = ValoresComunes.GetLanString ("Sensor").ToUpperCase
	Label3.Text = ValoresComunes.GetLanString ("reg152").ToUpperCase
	
	Label4.Text = ValoresComunes.GetLanStringDefault ("reg133","Setpoint").ToUpperCase
	Label5.Text = ValoresComunes.GetLanStringDefault ("Function","Function").ToUpperCase
	Label6.Text = ValoresComunes.GetLanString ("reg131").ToUpperCase
	
	Label7.Text = ValoresComunes.GetLanString ("reg135").ToUpperCase
	Label8.Text = ValoresComunes.GetLanString ("reg132").ToUpperCase
	Label9.Text = ValoresComunes.GetLanStringDefault ("Chart","Chart").ToUpperCase
	
	Label10.Text = ValoresComunes.GetLanStringDefault ("Status","Status").ToUpperCase
	Label11.Text = ValoresComunes.GetLanString ("reg155").ToUpperCase
	Label12.Text = ValoresComunes.GetLanStringDefault("close","Close").ToUpperCase
	
	
	
	Cmd01.Bitmap =ValoresComunes.Reloj
	Label01.Text = ValoresComunes.GetLanString ("reg153").ToUpperCase
	
	Cmd02.Bitmap =ValoresComunes.scene2
	Label02.Text =ValoresComunes.GetLanString ("reg152").ToUpperCase
	
	Cmd03.Bitmap =ValoresComunes.ConfigUp
	Label03.Text = ValoresComunes.GetLanStringDefault ("Backup","Backup").ToUpperCase
	
	
	Cmd04.Bitmap =ValoresComunes.Persiana
	Label04.Text = ValoresComunes.GetLanStringDefault ("sTs","Shutter").ToUpperCase
	
	Cmd05.Bitmap =ValoresComunes.Bombillaon 'cambio
	Label05.Text =ValoresComunes.GetLanStringDefault ("reg151","Circuit").ToUpperCase
	
	Cmd06.Bitmap =ValoresComunes.Temperatura
	Label06.Text =ValoresComunes.GetLanString ("Sensor").ToUpperCase
	
	
	Cmd07.Bitmap =ValoresComunes.CheckOn
	Label07.Text =ValoresComunes.GetLanString ("reg131").ToUpperCase
	
	Cmd08.Bitmap =ValoresComunes.Comando
	Label08.Text =ValoresComunes.GetLanString ("reg132").ToUpperCase
	
	
	
	Cmd09.Bitmap =ValoresComunes.AlmOff
	Label09.Text =ValoresComunes.GetLanString ("reg135").ToUpperCase
	
	Cmd010.Bitmap =ValoresComunes.Voice
	Label010.Text = ValoresComunes.GetLanString ("reg155").ToUpperCase
	
	'Cmd011.Bitmap =ValoresComunes.Home
	'Label011.Text = ValoresComunes.GetLanStringDefault ("dev","Devices").ToUpperCase
	
	Cmd011.Bitmap = ValoresComunes.SensorGenerico
	Label011.Text = ValoresComunes.GetLanStringDefault ("reg133","Setpoint").ToUpperCase
	
	Cmd012.Bitmap =ValoresComunes.Config
	Label012.Text =ValoresComunes.GetLanString ("reg104").ToUpperCase
	If Activity.Height > Activity.Width Then
		PantallaVertical
	Else
		PantallaHorizontal
	End If
	
	
End Sub
Sub ListView1_ItemClick (Position As Int, Value As Object)
	IniciarConexionCentral(Position )

End Sub
Private Sub PintaPantalla2
	Cmd01.height=Cmd1.height
	Cmd01.Width=Cmd1.Width
	Cmd01.Top=Cmd1.Top
	Cmd01.Left=Cmd1.Left
	
	Label01.Width=Label1.Width
	Label01.Top=Label1.Top
	Label01.Left=Label1.Left


	Cmd02.height=Cmd2.height
	Cmd02.Width=Cmd2.Width
	Cmd02.Top=Cmd2.Top
	Cmd02.Left=Cmd2.Left
	
	Label02.Width=Label2.Width
	Label02.Top=Label2.Top
	Label02.Left=Label2.Left

	Cmd03.height=Cmd3.height
	Cmd03.Width=Cmd3.Width
	Cmd03.Top=Cmd3.Top
	Cmd03.Left=Cmd3.Left
	
	Label03.Width=Label3.Width
	Label03.Top=Label3.Top
	Label03.Left=Label3.Left
	
	Cmd04.height=Cmd4.height
	Cmd04.Width=Cmd4.Width
	Cmd04.Top=Cmd4.Top
	Cmd04.Left=Cmd4.Left
	
	Label04.Width=Label4.Width
	Label04.Top=Label4.Top
	Label04.Left=Label4.Left
	
	Cmd05.height=Cmd5.height
	Cmd05.Width=Cmd5.Width
	Cmd05.Top=Cmd5.Top
	Cmd05.Left=Cmd5.Left
	
	Label05.Width=Label5.Width
	Label05.Top=Label5.Top
	Label05.Left=Label5.Left
	
	Cmd06.height=Cmd6.height
	Cmd06.Width=Cmd6.Width
	Cmd06.Top=Cmd6.Top
	Cmd06.Left=Cmd6.Left
	
	Label06.Width=Label6.Width
	Label06.Top=Label6.Top
	Label06.Left=Label6.Left
	
	Cmd07.height=Cmd7.height
	Cmd07.Width=Cmd7.Width
	Cmd07.Top=Cmd7.Top
	Cmd07.Left=Cmd7.Left
	
	Label07.Width=Label7.Width
	Label07.Top=Label7.Top
	Label07.Left=Label7.Left
	
	Cmd08.height=Cmd8.height
	Cmd08.Width=Cmd8.Width
	Cmd08.Top=Cmd8.Top
	Cmd08.Left=Cmd8.Left
	
	Label08.Width=Label8.Width
	Label08.Top=Label8.Top
	Label08.Left=Label8.Left
		
	Cmd09.height=Cmd9.height
	Cmd09.Width=Cmd9.Width
	Cmd09.Top=Cmd9.Top
	Cmd09.Left=Cmd9.Left
	
	Label09.Width=Label9.Width
	Label09.Top=Label9.Top
	Label09.Left=Label9.Left
	
	Cmd010.height=Cmd10.height
	Cmd010.Width=Cmd10.Width
	Cmd010.Top=Cmd10.Top
	Cmd010.Left=Cmd10.Left
	
	Label010.Width=Label10.Width
	Label010.Top=Label10.Top
	Label010.Left=Label10.Left
	
	Cmd011.height=Cmd11.height
	Cmd011.Width=Cmd11.Width
	Cmd011.Top=Cmd11.Top
	Cmd011.Left=Cmd11.Left
	
	Label011.Width=Label11.Width
	Label011.Top=Label11.Top
	Label011.Left=Label11.Left
	
	Cmd012.height=Cmd12.height
	Cmd012.Width=Cmd12.Width
	Cmd012.Top=Cmd12.Top
	Cmd012.Left=Cmd12.Left
	
	Label012.Width=Label12.Width
	Label012.Top=Label12.Top
	Label012.Left=Label12.Left
End Sub
Sub PantallaHorizontal
	Dim a,t,l,s As Int


	Dim TabExtra As TabHostExtras
	Dim TabSize As Int
	
	TabSize =TabExtra.getTabHeight(TabConfig)
	
	
	a=  (TabConfig.Height- TabSize)  /4
	t= (TabConfig.Height- TabSize) /3.2
	l=TabConfig.Width /4
	s=( TabConfig.Width - (a*4))/8.5
	's=s/8



	
	Cmd1.height =a
	Cmd1.Width =a
	Cmd1.Top =0
	Cmd1.Left =s
	Label1.Width =a +s
	Label1.Top =a
	Label1.Left =s/2
	
	
	'Boton 2
	Cmd2.height = a
	Cmd2.Width =a
	Cmd2.Top =0
	Cmd2.Left =l+s
	Label2.Width =a +s
	Label2.Top =a
	Label2.Left =l+(s/2)
	
	'Boton 3
	Cmd3.height = a
	Cmd3.Width =a
	Cmd3.Top =0
	Cmd3.Left =l*2+s
	Label3.Width =a +s
	Label3.Top =a
	Label3.Left =(l*2)+(s/2)
	
	'Boton 4
	Cmd4.height = a
	Cmd4.Width =a
	Cmd4.Top =0
	Cmd4.Left =3*l+s
	Label4.Width =a +s
	Label4.Top =a
	Label4.Left =(l*3)+(s/2)
	
	'*****************fila 2**********************
	
	'Boton 5
	Cmd5.height =a
	Cmd5.Width =a
	Cmd5.Top =t
	Cmd5.Left =s
	
	
	Label5.Width =a +s
	Label5.Top =a+t
	Label5.Left =s/2
	
	'Boton 6
	Cmd6.height = a
	Cmd6.Width =a
	Cmd6.Top =t
	Cmd6.Left =l+s
	
	Label6.Width =a +s
	Label6.Top =a+t
	Label6.Left =l+(s/2)
	
	'Boton 7
	Cmd7.height = a
	Cmd7.Width =a
	Cmd7.Top =t
	Cmd7.Left =l*2+s
	
	Label7.Width =a +s
	Label7.Top =a+t
	Label7.Left =(l*2)+(s/2)
	'Boton 8
	Cmd8.height = a
	Cmd8.Width =a
	Cmd8.Top =t
	Cmd8.Left =3*l+s
	
	Label8.Width =a +s
	Label8.Top =a+t
	Label8.Left =(l*3)+(s/2)

	
	'/******************fila 3*************************************/
	'Boton 9
	Cmd9.height =a
	Cmd9.Width =a
	Cmd9.Top =t*2
	Cmd9.Left =s
	
	
	Label9.Width =a +s
	Label9.Top =a+t*2
	Label9.Left =s/2
	
	'Boton 10
	Cmd10.height = a
	Cmd10.Width =a
	Cmd10.Top =t*2
	Cmd10.Left =l+s
	
	Label10.Width =a +s
	Label10.Top =a+t*2
	Label10.Left =l+(s/2)
	
	
	
	
	'Boton 7
	Cmd11.height = a
	Cmd11.Width =a
	Cmd11.Top =t*2
	Cmd11.Left =l*2+s
	
	Label11.Width =a +s
	Label11.Top =a+t*2
	Label11.Left =(l*2)+(s/2)
	'Boton 8
	Cmd12.height = a
	Cmd12.Width =a
	Cmd12.Top =t*2
	Cmd12.Left =3*l+s
	
	Label12.Width =a +s
	Label12.Top =a+t*2
	Label12.Left =(l*3)+(s/2)
	
	
	PintaPantalla2
	
End Sub
Sub PantallaVertical
	'Boton 1
	Dim TabExtra As TabHostExtras
	Dim TabSize As Int
	
	TabSize =TabExtra.getTabHeight(TabConfig)
	

	Dim a,t,l,s As Int
	
	'a=  (TabConfig.Height- TabSize)  /5.6
	a=  (Activity.Width)  /4
	t= (TabConfig.Height- TabSize) /4
	
	
	'a= TabConfig.Width /4
	't= TabConfig.Height /4
	'l=TabConfig.Width /3
	's= TabConfig.Width - (a*3)
	
	'l=Activity.Width /3
	s= Activity.Width - (a*3)
	s=s/7
	l=(2*s)+a
	
	Cmd1.height =a
	Cmd1.Width =a
	Cmd1.Top =0
	Cmd1.Left =s
	
	Label1.Width =a+s
	Label1.Top =a
	Label1.Left =s/2
	

	
	
	'Boton 2
	Cmd2.height = a
	Cmd2.Width =a
	Cmd2.Top =0
	Cmd2.Left =l+s
	Label2.Width =a+s
	Label2.Top =a
	'Label2.Left =(s/2)
	Label2.Left =l+(s/2)
	'Label4.Width =a+s
	'Label4.Top =a+t
	'Label4.Left =(s/2)
	'Boton 3
	Cmd3.height = a
	Cmd3.Width =a
	Cmd3.Top =0
	Cmd3.Left =(l*2)+s
	Label3.Width =a+s
	Label3.Top =a
	Label3.Left =(l*2)+(s/2)
	'Boton 4
	Cmd4.height = a
	Cmd4.Width =a
	Cmd4.Top =t
	Cmd4.Left =s
	Label4.Width =a+s
	Label4.Top =a+t
	Label4.Left =(s/2)
	'Boton 5
	Cmd5.height = a
	Cmd5.Width =a
	Cmd5.Top =t
	Cmd5.Left =l+s
	Label5.Width =a+s
	Label5.Top =a+t
	Label5.Left =l+(s/2)
	'Boton 6
	Cmd6.height = a
	Cmd6.Width =a
	Cmd6.Top =t
	Cmd6.Left =(l*2)+s
	Label6.Width =a+s
	Label6.Top =a+t
	Label6.Left =(l*2)+(s/2)
	
	'Boton 7
	Cmd7.height = a
	Cmd7.Width =a
	Cmd7.Top =t*2
	Cmd7.Left =s
	Label7.Width =a+s
	Label7.Top =t*2+a
	Label7.Left =s/2
	'Boton 8
	Cmd8.height =a
	Cmd8.Width =a
	Cmd8.Top =t*2
	Cmd8.Left =l+s
	Label8.Width =a+s
	Label8.Top =t*2+a
	Label8.Left =(s/2)+l
	'Boton 9
	Cmd9.height = a
	Cmd9.Width =a
	Cmd9.Top =t*2
	Cmd9.Left =(l*2)+s
	
	Label9.Width =a+s
	Label9.Top =t*2+a
	Label9.Left =(l*2)+(s/2)
	'Boton10
	Cmd10.height = a
	Cmd10.Width =a
	Cmd10.Top =t*3
	Cmd10.Left =s
	
	
	
	Label10.Width =a+s
	Label10.Top =t*3+a
	Label10.Left =s/2

	'Boton 11
	Cmd11.height = a
	Cmd11.Width =a
	Cmd11.Top =t*3
	Cmd11.Left =l+s
	
	Label11.Width =a+s
	Label11.Top =t*3+a
	Label11.Left =(s/2)+l
	'Boton 12
	Cmd12.height = a
	Cmd12.Width =a
	Cmd12.Top =t*3
	Cmd12.Left =(l*2)+s
	
	Label12.Width =a+s
	Label12.Top =t*3+a
	Label12.Left =(s/2)+(l*2)
	
	PintaPantalla2
End Sub
Sub Activity_Resume
	If ValoresComunes.CloseApp =True Then
		Activity.Finish
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then
		If Conectado= False Then IniciarConexionCentral(CentraltoConnect)
		
		'Activity.Title = "ExControl"
		ListView1.Clear
		Dim li As Int
		
		If ListView1.Size <> ValoresComunes.Centrales .Size Or UpdateCentral Then
			ValoresComunes.IniciarIconosCentrales
		End If
		
		
		ListView1.Clear

		
		For li = 0 To ValoresComunes.Centrales.Size -1
			ListView1.AddTwoLinesAndBitmap (ValoresComunes.Centrales .Get(li),ValoresComunes.GetLanString ("PC"),ValoresComunes.GetImgDevice(ValoresComunes.Centrales .Get(li), True) )
			
		Next
		
		
	Else
		Activity.Finish
		'StartActivity(Main)
	End If
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub



Sub Cmd12_Click
	ValoresComunes.CloseApp =True
	Activity.Finish

	
End Sub
Sub Cmd11_Click
	StartActivity(ActVoice)
	'StartActivity(Main)
	
End Sub
Sub Cmd10_Click
	'Voice
	'SetPoint
	
	If File.Exists ( File.DirInternal ,"Sensores" & ValoresComunes.Central.nombre)  Then
		StartActivity(ActFreeTxt)
	Else
		ToastMessageShow(ValoresComunes.GetLanString ("reg127") , True)
	End If
	
End Sub

Sub Cmd9_Click
	StartActivity(ActSelectSensores)
End Sub
Sub Cmd8_Click
	'Comandos
	StartActivity(ActComandos)
End Sub
Sub Cmd7_Click
	'Notificaciones

	StartActivity(ActNotification)
End Sub
Sub Cmd6_Click

	'Condicionados
	StartActivity(ActCondicionados)
End Sub
Sub Cmd5_Click
	StartActivity(ActFunciones)
	
End Sub
Sub Cmd4_Click
	
	If ValoresComunes.SetPoint .Size > 0 Then
		StartActivity(ActConsignas)
	Else
		
		ToastMessageShow(ValoresComunes.GetLanString ("reg127") , True)
	End If
End Sub
Sub Cmd3_Click
	StartActivity(ActScene)
	
End Sub
Sub Cmd2_Click
	
	StartActivity(ActSensors)
	
End Sub
Sub Cmd1_Click
	'Circuitos
	
	StartActivity(ActCircuit)
End Sub


Sub Cmd012_Click
	'Dim p As PhoneIntents
	'StartActivity(p.OpenBrowser("http://domotica-Arduino.es"))
	ActMenu.TypeConfig =3
	StartActivity(ActMenu)
End Sub
Sub Cmd011_Click
	'StartActivity(ActCentrales)
	StartActivity(ActConfigSetponint)
End Sub
Sub Cmd010_Click
	StartActivity(ActConfigVoice)
End Sub
Sub Cmd09_Click
	'ActNotification.CambioNombre =True
	StartActivity(ActConfigNotification)
	
	'ActMenu.TypeConfig =2
	'StartActivity(ActMenu)
End Sub

Sub Cmd08_Click
	StartActivity(ActConfigComandos)
End Sub
Sub Cmd07_Click
	StartActivity(ActConfigCondicionados)
	
End Sub
Sub Cmd06_Click
	'Nueva Pantalla Sensores
	StartActivity(ActConfigSensors)
End Sub
Sub Cmd05_Click
	StartActivity(ActConfigCir)
End Sub
Sub Cmd04_Click
	StartActivity(ActPersianas)
End Sub
Sub Cmd03_Click
	'StartActivity(ActConfigCir)
	ActMenu.TypeConfig =2
	StartActivity(ActMenu)
End Sub
Sub Cmd02_Click
	StartActivity( ActConfigEscenas)
End Sub
Sub Cmd01_Click
	'Horarios
	ActMenu.TypeConfig =1
	StartActivity(ActMenu)
End Sub

Sub JobDone (Job As HttpJob)
	
End Sub

Sub CargaIpServidor
	
End Sub



Sub IniciarRed()
	Dim Reintento As Int
	Dim Resultado As Boolean
	
	
	Do 	While Resultado= False And Reintento < 60
		Dim ServerSocket1 As ServerSocket
	
		If ServerSocket1.GetMyIP  <> "127.0.0.1" Then
			Resultado = True
		Else
			Reintento = Reintento +1
			Sleep (100)
		End If

	Loop
	If Resultado = False Then
		Msgbox(ValoresComunes.GetLanString ("CNA"),ValoresComunes.GetLanString ("NEE"))
		ValoresComunes.CloseApp =True
		Activity.Finish
	

	
	End If
	
	If ServerSocket1.GetMyWifiIP  <> "127.0.0.1"   Then
		
		
		If File.Exists ( File.DirInternal ,"SSID" & ValoresComunes.Central.nombre)   Then
			Dim l As List = File.ReadList (File.DirInternal ,"SSID" & ValoresComunes.Central.nombre)
			
			'gestion wifi
			If l.IndexOf (wifi.ssid)>-1 Then
				IniciarConexionInterna
				
			Else
				SelectConexionType
				
			End If
			
		
		Else
			SelectConexionType
		
		End If


		
	Else
		IniciarConexionExterna
	End If
	
End Sub
Sub SelectConexionType
	Dim R As Int
	Dim Lst As List
	Lst.Initialize
	Lst.Add (ValoresComunes.GetLanStringDefault  ("ICW","Internal connection Wifi"))
	Lst.Add(ValoresComunes.GetLanStringDefault ("ECW","Extermal connection Wifi"))
	R=-1
	Do	While R<0
		R =InputList(Lst,ValoresComunes.GetLanStringDefault ("CMW","Connection mode  for this wifi network "), 0)
	Loop

			

			
	If R=0 Then
		AddNewSsid
		IniciarConexionInterna
	Else
		IniciarConexionExterna
				
	End If
End Sub
Sub IniciarConexionExterna
	ProgressDialogShow("Starting...")
	Forzar3g = True
	'CargaIpServer
	CargaIpServidor
End Sub
Sub IniciarConexionInterna
	Forzar3g = False
	'IniciarMenu
	ValoresComunes.ip= ValoresComunes.Central .IpIn
	ValoresComunes.Puerto= ValoresComunes.Central.PuertoIn
End Sub
Sub AddNewSsid( )
	Dim l As List
	If File.Exists ( File.DirInternal ,"SSID" & ValoresComunes.Central.nombre)   Then
		l = File.ReadList (File.DirInternal ,"SSID" & ValoresComunes.Central.nombre)
	Else
		l.Initialize
	End If
	
	l.Add (wifi.ssid)
	File.WriteList (File.DirInternal ,"SSID" & ValoresComunes.Central.nombre,l)
			
End Sub
Sub IniciarConexionCentral(NumeroCentral As Int)

	
	Conectado=True
	
	
	
	ValoresComunes.Central.Nombre   = ValoresComunes.Centrales .Get (NumeroCentral)
	ValoresComunes.CargaCircuitos
	ValoresComunes.CargaAlarmas
	ValoresComunes.CargaSensores
	ValoresComunes.CargaScenes
	ValoresComunes.CargaFuciones
	ValoresComunes.CargaCondicionados
	ValoresComunes.CargaComandos
	ValoresComunes.CargaNombreSensores
	ValoresComunes.CargaSetPoint

	ValoresComunes.CargaIp
	IniciarRed
		
	'Dim p As Phone
	'Dim sb As StringBuilder
	'sb.Initialize
	'p.Shell("ping -c1 " &  ValoresComunes.ip, Null, sb, Null)
	
	'Log(sb)
	
	If TabConfig.TabCount < 3 Then
		TabConfig.CurrentTab = 0
	Else
		TabConfig.CurrentTab = 1
	End If

	
End Sub
Sub Activity_KeyPress (KeyCode As Int) As Boolean

	If KeyCode = KeyCodes.KEYCODE_BACK Then
		'
		ValoresComunes.CloseApp =True
		Activity.Finish
		Return True

	End If
End Sub
Sub TabHost1_TabChanged
	
End Sub
Sub TabConfig_Click
	
End Sub



