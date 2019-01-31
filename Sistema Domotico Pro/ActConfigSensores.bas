Type=Activity
Version=4.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
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
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	'Activity.LoadLayout("Layout1")
	Activity.LoadLayout ("FrmConfigSensores")
	Activity.Title =ValoresComunes.GetLanString ("SSE")
	ListView1.Height = PerYToCurrent(83)
	ListView1.Width =Activity.Width 
	
	CmdGuardar.Top=PerYToCurrent(85)
	CmdGuardar.Height =PerYToCurrent(15)
	CmdGuardar.Width =Activity.Width / 2
	CmdGuardar.Left =Activity.Width / 2
	CmdGuardar.Text = ValoresComunes.GetLanString ("reg128")
	
	CmdNew.Top=PerYToCurrent(85)
	CmdNew.Height =PerYToCurrent(15)
	CmdNew.Width =Activity.Width / 2
	CmdNew.Left  =0
	CmdNew.Text = ValoresComunes.GetLanString ("reg185")
	
End Sub


Sub Activity_Resume
	If Main.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then
		If ValoresComunes.NombreSensor.IsInitialized = False Then
			If File.Exists ( File.DirInternal ,"Sensores"& ValoresComunes.Central.nombre )    Then
			
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

End Sub



Sub ListView1_ItemClick (Position As Int, Value As Object)
		Dim Dialog As InputDialog 
			
		Dialog.InputType =  Dialog.INPUT_TYPE_TEXT 
		Dialog.Input = ValoresComunes.NombreSensor .Get (Position)
		Dim Result As Int
		Result = Dialog.Show ( ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg111"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		If Result=  DialogResponse.POSITIVE  Then
			ValoresComunes.NombreSensor .set (Position,Dialog.Input )
		End If
		RefrescaPantalla
End Sub
Sub CmdGuardar_Click
	File.WriteList(File.DirInternal ,"Sensores" & ValoresComunes.Central.nombre  ,ValoresComunes.NombreSensor)
	ToastMessageShow(ValoresComunes.GetLanString ("reg139"), True)
End Sub
Sub CmdNew_Click


		Dim Dialog As InputDialog 
				
		Dialog.InputType =  Dialog.INPUT_TYPE_TEXT 
		Dialog.Input = ""
		Dim Result As Int
		Result = Dialog.Show ( ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg111"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
	
		If Result=  DialogResponse.POSITIVE  Then
			ValoresComunes.NombreSensor .Add (Dialog.Input)
			If ActFreeTxt.Sensores.IsInitialized Then ActFreeTxt.Sensores .Add (Dialog.Input)
			
			ListView1.AddSingleLine  (Dialog.Input  )
		End If
	
End Sub
Sub RefrescaPantalla
	ListView1.Clear 
	Dim c As Int 
	
	
	For c=0 To ValoresComunes.NombreSensor.Size -1

		ListView1.AddSingleLine  (ValoresComunes.NombreSensor .Get (c) )
	Next
	
End Sub 