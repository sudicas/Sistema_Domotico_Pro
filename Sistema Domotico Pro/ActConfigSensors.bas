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

End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	
	'Activity.AddMenuItem(ValoresComunes.GetLanString ("reg130"), "mnuHistorico")
	
	Activity.LoadLayout ("FrmSensores")
	ListView1.height = 100%y
	ListView1.Width =Activity.Width 
	ListView1.Top =0

	
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
	ListView1.TwoLinesAndBitmap.Label.Gravity = Bit.OR(Gravity.LEFT, Gravity.BOTTOM)
	
	ListView1.TwoLinesAndBitmap.SecondLabel .Left =ListView1.TwoLinesAndBitmap.Label .Left
	ListView1.TwoLinesAndBitmap.SecondLabel.height   =h-ListView1.TwoLinesAndBitmap.Label.height
	ListView1.TwoLinesAndBitmap.SecondLabel.top   = ListView1.TwoLinesAndBitmap.SecondLabel.height
	ListView1.TwoLinesAndBitmap.SecondLabel.Gravity = Bit.OR(Gravity.LEFT, Gravity.CENTER_VERTICAL)



	
End Sub

Sub Activity_Resume

	If ValoresComunes.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then
		ActualizaValores
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

	For i =0 To ValoresComunes.Sensores .Length  -1
	  	
		 If ValoresComunes.Sensores (i).Rango >0 AND  ValoresComunes.Sensores (i).Rango < 250  Then ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Sensores (i).Nombre, ValoresComunes.GetLanString ("SNV")  ,ValoresComunes.IconoSensor(i) ,i)
		'ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.NombreSensor.Get (i),Sensores.Get (i) ,ValoresComunes.Sensor ,i)
	Next
End Sub



Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim Lst As List
	Lst.Initialize 
	Lst.Add (ValoresComunes.GetLanString ("reg111"))
	Lst.Add(ValoresComunes.GetLanString ("reg112"))
	Lst.Add(ValoresComunes.GetLanString ("reg113"))
	'Lst.Add(ValoresComunes.GetLanString ("reg114"))
	Lst.Add(ValoresComunes.GetLanString ("Cancel"))

	
	
	Dim Seleccion As Int
	Seleccion =InputList(Lst,ValoresComunes.GetLanString ("reg75"),0)
	Select Case Seleccion
		Case 0
		Dim Dialog As InputDialog 
		
			Dialog.InputType =  Dialog.INPUT_TYPE_TEXT 
			Dialog.Input = ValoresComunes.Sensores (Position).Nombre 
			Dim Result As Int
			Result = Dialog.Show ( ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg111"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
			If Result=  DialogResponse.POSITIVE  Then
				ValoresComunes.Sensores (Position).nombre =Dialog.Input  
				ValoresComunes.GuardarConfigSensores
			End If
		Case 1
			Dim Dialog As InputDialog 
			
			Dialog.InputType =  Dialog.INPUT_TYPE_TEXT 
			Dialog.Input = ValoresComunes.Sensores (Position).Descripcion  
			Dim Result As Int
			Result = Dialog.Show ( ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg112"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
			If Result=  DialogResponse.POSITIVE  Then
				ValoresComunes.Sensores(Position).Descripcion  =Dialog.Input  
				ValoresComunes.GuardarConfigSensores
			End If
		Case 2
			Dim Lst As List
			Lst.Initialize 
			Lst.Add ("Temperature")		
			Lst.Add("Humidity ")
			Lst.Add("Lux")
			Lst.Add("Generic Sensor")
			Lst.Add("Generic float")
			Lst.Add("tank")
			
			Dim Val As Int 
		    Val =InputList(Lst,ValoresComunes.GetLanString ("reg75"), ValoresComunes.Sensores(Position).Rango -1 )
			If Val>-1 Then
				Select Val
					Case 5
					 	ValoresComunes.Sensores(Position).Rango   =100
					Case Else 
						ValoresComunes.Sensores(Position).Rango   =Val+1
				End Select
									
				ValoresComunes.GuardarConfigSensores
			End If
		
		
			'#define Sensor_      1
			'#define Sensor_        2
			'#define Sensor_   	        3
			'#define Sensor_Generic          4
		

		'Case 3
			'ValoresComunes.Circuitos(Position).nombre =""
			'ValoresComunes.Circuitos(Position).Descripcion  =""
			'ValoresComunes.Circuitos(Position).Rango =1
	End Select
	'ValoresSalidas(Position)=Seleccion
	ActualizaValores
End Sub



