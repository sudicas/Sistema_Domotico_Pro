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

	Dim CmdGuardar As Button
	Dim CmdNew As Button
	Dim ListView1 As ListView
	Private CmdTerminar As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	'Activity.LoadLayout("Layout1")
	Activity.LoadLayout ("FrmConfig2")
	Activity.Title =ValoresComunes.GetLanStringDefault ("CsS","Configure Status Screen")

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
	
End Sub


Sub Activity_Resume
	If ValoresComunes.Centrales .IsInitialized = True Then
		If ValoresComunes.NombreSensor.IsInitialized = False Then
			If File.Exists ( File.DirInternal ,"Sensores"& ValoresComunes.Central.Nombre  )    Then
			
				ValoresComunes.CargaNombreSensores 
			Else
				ValoresComunes.NombreSensor .Initialize 
				
			End If
		End If
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
		Dim Dialog As InputDialog 
			
		Dialog.InputType =  Dialog.INPUT_TYPE_TEXT 
		Dialog.Input = ValoresComunes.NombreSensor .Get (Position)
		Dim Result As Int
		Result = Dialog.Show ( "New Value","Edit Name","Accept","Cancel","",Null)
		If Result=  DialogResponse.POSITIVE  Then
			ValoresComunes.NombreSensor .set (Position,Dialog.Input )
		End If
		RefrescaPantalla
End Sub
Sub CmdGuardar_Click
	File.WriteList(File.DirInternal ,"Sensores" & ValoresComunes.central.Nombre   ,ValoresComunes.NombreSensor)
	ToastMessageShow("Full saved",True)
End Sub
Sub CmdNew_Click


		Dim Dialog As InputDialog 
				
		Dialog.InputType =  Dialog.INPUT_TYPE_TEXT 
		Dialog.Input = ""
		Dim Result As Int
		Result = Dialog.Show ( "New Value","Edit Name","Accept","Cancel","",Null)
	
		If Result=  DialogResponse.POSITIVE  Then
			ValoresComunes.NombreSensor .Add (Dialog.Input)
			If ActFreeTxt.Sensores.IsInitialized Then ActFreeTxt.Sensores .Add (Dialog.Input)
			
			ListView1.AddTwoLinesAndBitmap   (Dialog.Input,"",ValoresComunes.Sensor )
		End If
	
End Sub
Sub RefrescaPantalla
	ListView1.Clear 
	Dim c As Int 
	
	
	For c=0 To ValoresComunes.NombreSensor.Size -1

		ListView1.AddTwoLinesAndBitmap  (ValoresComunes.NombreSensor .Get (c),"",ValoresComunes.Sensor ) 
	Next
	
End Sub 




Sub CmdTerminar_Click
	Activity.Finish
End Sub