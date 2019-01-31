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

	Dim ListView1 As ListView
	Dim CmdGuardar As Button
	
	Private CmdTerminar As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	Activity.LoadLayout ("frmconfig")
	
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
End Sub

Sub Activity_Resume
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



Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim Lst As List
	Lst.Initialize 
	Lst.Add (ValoresComunes.GetLanString ("reg111"))
	Lst.Add(ValoresComunes.GetLanString ("reg112"))
	Lst.Add(ValoresComunes.GetLanString ("reg113"))
	Lst.Add(ValoresComunes.GetLanString ("reg114"))
	Lst.Add(ValoresComunes.GetLanString ("Cancel"))
	
	Dim Seleccion As Int
	Seleccion =InputList(Lst,ValoresComunes.GetLanString ("reg75"),0)
	Select Case Seleccion
		Case 0
		Dim Dialog As InputDialog 
		
			Dialog.InputType =  Dialog.INPUT_TYPE_TEXT 
			Dialog.Input = ValoresComunes.Circuitos (Position).Nombre 
			Dim Result As Int
			Result = Dialog.Show ( ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg111"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
			If Result=  DialogResponse.POSITIVE  Then
				ValoresComunes.Circuitos(Position).nombre =Dialog.Input  
			End If
		Case 1
			Dim Dialog As InputDialog 
			
			Dialog.InputType =  Dialog.INPUT_TYPE_TEXT 
			Dialog.Input = ValoresComunes.Circuitos (Position).Descripcion  
			Dim Result As Int
			Result = Dialog.Show ( ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg112"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
			If Result=  DialogResponse.POSITIVE  Then
				ValoresComunes.Circuitos(Position).Descripcion  =Dialog.Input  
			End If
		Case 2
			Dim Dialogo As NumberDialog 
			Dialogo.Digits =3
			Dialogo.Number =ValoresComunes.Circuitos (Position).Rango 
			
			Dim Result As Int
			Result = Dialogo.Show (ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
			
			If Result=  DialogResponse.POSITIVE  Then
				ValoresComunes.Circuitos(Position).Rango =Dialogo.Number 
			End If
		Case 3
			ValoresComunes.Circuitos(Position).nombre =""
			ValoresComunes.Circuitos(Position).Descripcion  =""
			ValoresComunes.Circuitos(Position).Rango =1
	End Select
	'ValoresSalidas(Position)=Seleccion
	ActualizaValores
End Sub
Sub ActualizaValores()
	ListView1.Clear
	Dim c As Int 
	For c =0 To 29
	
	
	
	
		ListView1.AddTwoLines (ValoresComunes.Circuitos (c).Nombre ,ValoresComunes.Circuitos (c).Descripcion )
	Next
End Sub
Sub CmdGuardar_Click
	
		ValoresComunes.GuardarConfigCircuitos		
		ToastMessageShow(ValoresComunes.GetLanString ("reg139"), True)


End Sub

Sub CmdTerminar_Click
	Activity.Finish
End Sub