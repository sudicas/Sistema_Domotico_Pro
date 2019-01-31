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



	
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim ListView1 As ListView
	Dim sm As SlideMenu
	

End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("FrmBase")
	If ValoresComunes.Centrales .IsInitialized = False  Then Return

	

	'Activity.LoadLayout ("frmnotification")
	ListView1.Initialize("ListView1")
	

	
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
	
	Activity.AddView (ListView1,0,52dip ,Activity.Width  ,Activity.Height - 52dip  )
	

	sm.Initialize(Activity, Me,  "SlideMenu", 42dip, 250dip)
	
	ValoresComunes.BuldMenus (sm,3)
	
End Sub

Sub Activity_Resume
	If ValoresComunes.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	
	If ValoresComunes.Centrales .IsInitialized = True Then
		
		ActualizaValores
		'SlMenu.BarsOff
		'SlMenu.MenuHide
		
	Else
		Activity.Finish 
		StartActivity(Main)
	End If
End Sub

Sub Activity_Pause (UserClosed As Boolean)

	Activity.Finish 
End Sub
Sub ActualizaValores()
	ListView1.Clear 
	Dim i As Int
	For i =0 To ValoresComunes.Scenes .Length -1
	    Dim S As Scene 
		S= ValoresComunes.Scenes (i)
		If S.Nombre.Length >0 Then
			ListView1.AddTwoLinesAndBitmap2 (S.Nombre ,S.Descripcion ,ValoresComunes.Scene2   ,i)
		End If
		
	Next
	'ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.NombreSensor.Get (i),Sensores.Get (i) ,ValoresComunes.Sensor ,i)
	
End Sub



Sub ListView1_ItemClick (Position As Int, Value As Object)

	

	ActCircuit.SelectScene=Value+1
		
	StartActivity(ActCircuit)

	
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
				'StartActivity(ActScene)
				'Activity.Finish
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
				StartActivity(ActFunciones)
				Activity.Finish
				
			Case 98
				'StartActivity(ActSelectSensores)
				Activity.Finish
		End Select
	End If
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