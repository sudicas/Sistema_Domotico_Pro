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

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Dim CmdGuardar As Button
	Dim ListView1 As ListView
	Private CmdTerminar As Button
End Sub
Sub Activity_Create(FirstTime As Boolean)
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	Activity.LoadLayout ("frmconfig")
	Activity.Title =ValoresComunes.GetLanString ("SS")
	

	
	Dim he As Int = 40dip
	
	ListView1.Height = Activity.Height - he  'PerYToCurrent(83)
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

Sub Activity_Resume	
	If ValoresComunes.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then
		RefrescaPantalla
	Else
		Activity.Finish 
		StartActivity(Main)
	End If
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Activity.Finish 
End Sub



Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim Lst As List
	Lst.Initialize 
	Lst.Add (ValoresComunes.GetLanString ("reg111"))
	Lst.Add(ValoresComunes.GetLanString ("reg112"))
	Lst.Add(ValoresComunes.GetLanString ("SS"))
	Lst.Add(ValoresComunes.GetLanStringDefault ("DiT","Invisible element"))

	Dim Opcion As Int
	Opcion =InputList( Lst,ValoresComunes.GetLanString ("reg75"), 0)
	
	If Opcion =0 Then
		Dim Dialog As InputDialog 
		
		Dialog.InputType =  Dialog.INPUT_TYPE_TEXT 
		Dialog.Input = ValoresComunes.Scenes  (Position).Nombre 
		Dim Result As Int
		Result = Dialog.Show ( ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg111"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		If Result=  DialogResponse.POSITIVE  Then
			ValoresComunes.Scenes(Position).nombre =Dialog.Input  
		End If
	End If
	If Opcion =1 Then
		Dim Dialog As InputDialog 
		
		Dialog.InputType =  Dialog.INPUT_TYPE_TEXT 
		Dialog.Input = ValoresComunes.Scenes  (Position).Descripcion  
		Dim Result As Int
		Result = Dialog.Show ( ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg112"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		If Result=  DialogResponse.POSITIVE  Then
			ValoresComunes.Scenes(Position).Descripcion  =Dialog.Input  
		End If
	End If
	If Opcion =2 Then
		'Configurar Scene
		ActConEscena.NumeroScene = Position +1
			StartActivity(ActConEscena)
		
		Return
	End If
	
	If Opcion=3 Then
		ValoresComunes.Scenes(Position).nombre =""
		ValoresComunes.Scenes(Position).Descripcion  =""
	End If
	RefrescaPantalla
End Sub
Sub CmdGuardar_Click
	ValoresComunes.GuardaEscenas 
	ToastMessageShow(ValoresComunes.GetLanString ("reg139"), True)
End Sub
Sub RefrescaPantalla
	ListView1.Clear 
	Dim c As Int 
	For c=0 To 9
		'ListView1.AddTwoLines ( )
		ListView1.AddTwoLinesAndBitmap (ValoresComunes.Scenes (c).Nombre ,ValoresComunes.Scenes(c).Descripcion ,ValoresComunes.Scene2   )
	Next
	
End Sub 

Sub CmdTerminar_Click
	Activity.Finish
End Sub