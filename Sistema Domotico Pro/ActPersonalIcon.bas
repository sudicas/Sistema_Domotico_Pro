Type=Activity
Version=7.01
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
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
	ListView1.TwoLinesAndBitmap.Label.Gravity = Bit.OR(Gravity.LEFT, Gravity.BOTTOM)
	
	ListView1.TwoLinesAndBitmap.SecondLabel .Left =ListView1.TwoLinesAndBitmap.Label .Left
	ListView1.TwoLinesAndBitmap.SecondLabel.height   =h-ListView1.TwoLinesAndBitmap.Label.height
	ListView1.TwoLinesAndBitmap.SecondLabel.top   = ListView1.TwoLinesAndBitmap.SecondLabel.height
	ListView1.TwoLinesAndBitmap.SecondLabel.Gravity = Bit.OR(Gravity.LEFT, Gravity.CENTER_VERTICAL)
End Sub
Sub RefPantalla
	ListView1.Clear 
	Dim c As Int
	For c=0 To 6
		'ListView1.AddTwoLinesAndBitmap  ("Personal Icon" & c, "", ValoresComunes.PersonalCentralIcon(c))
	Next
	
	
End Sub
Sub ListView1_ItemClick (Position As Int, Value As Object)

	Dim d As FileDialog 
	d.ShowOnlyFolders= False
	d.FileFilter=".png"
	Dim Result As Int
	Result= d.Show (ValoresComunes.GetLanStringDefault  ("SePI","Select Custom Icon"), ValoresComunes.GetLanString ("Ok"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		
	If Result=  DialogResponse.POSITIVE  Then
		
			Dim ruta As String
			ruta =  d.FilePath & "/" & d.ChosenName
			If ruta.Length >0 AND d.ChosenName.Length > 0   Then
				File.Copy (d.FilePath,d.ChosenName,File.DirDefaultExternal,"PCICO" & Position &".png")
				ToastMessageShow(ValoresComunes.GetLanString ("reg139"),True)
				RefPantalla
			End If
			
			
		End If
		
End Sub
Sub Activity_Resume



	RefPantalla
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


