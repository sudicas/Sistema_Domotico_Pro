Type=Activity
Version=7.01
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: false
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

	

	Dim HC As OkHttpClient

	Dim Target As OutputStream
	
	
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim Configuraciones As List 
	Dim CmdGuardar As Button
	Dim ListView1 As ListView
	Private CmdTerminar As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	If  File.Exists ( File.DirInternal ,"ConfigIdioma") = False  Then ValoresComunes.CreaArchivoConfigIdioma
	Configuraciones = File.ReadList(File.DirInternal ,"ConfigIdioma" )
	
	Activity.LoadLayout ("frmconfig")
	
	Dim he As Int = 40dip
	
	ListView1.Height = Activity.Height  - he
	ListView1.Width = Activity.Width
	
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
	
	

	HC.Initialize("HC")

	
End Sub



Sub RefPantalla
	ListView1.Clear 		
		
	ListView1.AddTwoLines(ValoresComunes.GetLanString ("reg181"),ValoresComunes.GetLanString ("reg182"))
	
	'ListView1.AddTwoLines (Configuraciones.Get (2),Configuraciones.Get (0))
	ListView1.AddTwoLines (Configuraciones.Get (1),ValoresComunes.GetLanString ("reg176"))
	ListView1.AddTwoLines (Configuraciones.Get (2),ValoresComunes.GetLanString ("reg177"))
	ListView1.AddTwoLines (Configuraciones.Get (3),ValoresComunes.GetLanString ("reg178"))
	ListView1.AddTwoLines (Configuraciones.Get (4),ValoresComunes.GetLanString ("reg179"))
	ListView1.AddTwoLines (Configuraciones.Get (5),ValoresComunes.GetLanString ("reg180"))
End Sub

Sub Activity_Resume	
	If ValoresComunes.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then
		RefPantalla
	Else
		Activity.Finish 
		StartActivity(Main)
	End If
End Sub


Sub Activity_Pause (UserClosed As Boolean)
	Activity.Finish 
End Sub


Sub ListView1_ItemClick (Position As Int, Value As Object)
	If Position=0 Then
		Dim Lst As List
		Lst.Initialize
		
		Lst.Add ("Idioma Español")
		Lst.Add ("English language")
		Lst.Add ("Lingua italiana")
		Lst.Add ("português")
		Lst.Add ("français")
		
		
		Lst.Add ("Deutsch")
		Lst.Add ("Nederlands")
		Lst.Add ("Catala-Valencia")
		Lst.Add ("Euskera")
		Lst.Add ("Galego")

		
		
		Dim Result As Int			
		Result =InputList(Lst,"Select language", 0)
		Select Case Result
			Case 0:
				Configuraciones.Set (0,"es")
			Case 1:
				Configuraciones.Set (0,"en")
			Case 2:
				Configuraciones.Set (0,"it")
			Case 3:
				Configuraciones.Set (0,"pt")
			Case 4:
				Configuraciones.Set (0,"fr")
				
			Case 5:
				Configuraciones.Set (0,"de")
			Case 6:
				Configuraciones.Set (0,"nl")
			Case 7:
				Configuraciones.Set (0,"ca")
			Case 8:
				Configuraciones.Set (0,"eu")
			Case 9:
				Configuraciones.Set (0,"gl")
				
		End Select
		If Result>=0 Then 
			Dim Ruta As String
			Ruta="http://domotica-arduino.es/Idioma/" & Configuraciones.Get (0) & ".txt" 
			
			Target = File.OpenOutput(File.DirInternalCache, "NewIdioma.txt", False)
			ProgressDialogShow(ValoresComunes.GetLanString ("reg80"))				
			
			Dim request As OkHttpRequest
		    request.InitializeGet(Ruta)
		    HC.Execute(request, 1)
	
		End If
	Else
		Dim Dialog As InputDialog 
		
			Dialog.InputType =  Dialog.INPUT_TYPE_TEXT 
			Dialog.Input = Configuraciones.Get (Position)
			Dim Result As Int
			Result = Dialog.Show ( ValoresComunes.GetLanString ("reg183"), ValoresComunes.GetLanString ("reg104"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
			If Result=  DialogResponse.POSITIVE  Then
				Configuraciones.Set (Position,Dialog.Input.ToUpperCase )
				RefPantalla
			End If
	End If
	
End Sub
Sub CmdGuardar_Click
	File.WriteList(File.DirInternal ,"ConfigIdioma"  ,Configuraciones)
	ToastMessageShow(ValoresComunes.GetLanString ("reg139"),True)
End Sub

Sub HC_ResponseError (Reason As String, StatusCode As Int, TaskId As Int)
    ToastMessageShow("Error downloading file: " & Reason, True)

End Sub

Sub HC_ResponseSuccess (Response As OkHttpResponse, TaskId As Int)

    Response.GetAsynchronously("Response", Target, True, TaskId)
	
End Sub
Sub Response_StreamFinish (Success As Boolean, TaskId As Int)
	
	ProgressDialogHide
    If Success = False Then 
		
		Log(LastException.Message)
		Msgbox( ValoresComunes.GetLanString ("reg120"),ValoresComunes.GetLanString ("reg120"))
	Else
		ValoresComunes.LanString = File.ReadMap (File.DirInternalCache,"NewIdioma.txt")
		File.WriteMap (File.DirInternal   ,"LanString"  ,ValoresComunes.LanString)
		RefPantalla
	End If
End Sub



Sub CmdTerminar_Click
	Activity.Finish
End Sub