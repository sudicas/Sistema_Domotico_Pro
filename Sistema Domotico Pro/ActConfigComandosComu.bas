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
	Dim ListView1 As ListView
	Dim CmdNew As Button
	Private CmdTerminar As Button
End Sub
Sub Activity_Create(FirstTime As Boolean)
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	Activity.LoadLayout ("FrmConfig2")
	Activity.Title =ValoresComunes.GetLanString ("COR")
	
	
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
		If ValoresComunes.ComandosComunes .IsInitialized = False Then ValoresComunes.CargaComandosComunes 
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

	
	Dim Opcion As Int
	Opcion =InputList( Lst,ValoresComunes.GetLanString ("reg75"), 0)
	Dim cmd As Scene 
	cmd = ValoresComunes.ComandosComunes .Get (Position)
	If Opcion =0 Then
		Dim Dialog As InputDialog 
			
		Dialog.InputType =  Dialog.INPUT_TYPE_TEXT 
		Dialog.Input = cmd.Nombre  
		Dim Result As Int
		Result = Dialog.Show ( ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg111"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		If Result=  DialogResponse.POSITIVE  Then
			cmd.nombre =Dialog.Input  
		End If
	End If
	If Opcion =1 Then
		Dim Dialog As InputDialog 
		
		Dialog.InputType =  Dialog.INPUT_TYPE_TEXT 
		Dialog.Input = cmd.Descripcion  
		Dim Result As Int
		Result = Dialog.Show ( ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg112"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		If Result=  DialogResponse.POSITIVE  Then
			cmd.Descripcion  =Dialog.Input  
		End If
	End If

	RefrescaPantalla
End Sub
Sub CmdGuardar_Click
	ValoresComunes.GuardaComandosComunes   
	ToastMessageShow(ValoresComunes.GetLanString ("reg139"), True)
End Sub
Sub RefrescaPantalla
	ListView1.Clear 
	Dim c As Int 
	
	
	For c=0 To ValoresComunes.ComandosComunes.Size -1
		Dim cmd As Scene 
		cmd = ValoresComunes.ComandosComunes .Get (c)
		ListView1.AddTwoLinesAndBitmap  (cmd.Nombre ,cmd.Descripcion,ValoresComunes.Home  )
	Next
	
End Sub 
Sub CmdNew_Click
	Dim cmd As Scene 
	
	Dim Dial As InputDialog 
	Dial.InputType =  Dial.INPUT_TYPE_TEXT 
	Dial.Input =""
	Dim Result As Int

	Result = Dial.Show ( ValoresComunes.GetLanString ("reg111"),ValoresComunes.GetLanString ("reg104"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)	
	If Result <> DialogResponse.POSITIVE   Then 
		Return
	Else
		cmd.Nombre  = Dial.Input 
	End If
	Dial.Input =""
	Result = Dial.Show ( ValoresComunes.GetLanString ("reg112"),ValoresComunes.GetLanString ("reg104"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)	
	If Result <> DialogResponse.POSITIVE   Then 
		Return
	Else
		cmd.Descripcion  = Dial.Input 
	End If
	
	
	ValoresComunes.ComandosComunes.Add (cmd)
	ValoresComunes.GuardaComandosComunes  
	RefrescaPantalla
End Sub

Sub CmdTerminar_Click
	Activity.Finish
End Sub