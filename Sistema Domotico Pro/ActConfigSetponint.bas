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
	
	Dim Val As List
	
	Private CmdTerminar As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	Activity.LoadLayout ("FrmConfig2")
	'Activity.AddMenuItem(ValoresComunes.GetLanString ("reg130"), "mnuHistorico")
	
	
	Activity.Title =ValoresComunes.GetLanString ("SNV")
	


	
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
	If ValoresComunes.CloseApp =True Then
		Activity.Finish 
		Return
	Else
		If ValoresComunes.SetPoint .IsInitialized Then
			Val=ValoresComunes.SetPoint 
			RefrescaPantalla
		Else
			ValoresComunes.CloseApp =True
			Activity.Finish 
			Return
		End If
		
	End If	
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Activity.Finish 
End Sub

Sub RefrescaPantalla
	ListView1.Clear 
	Dim c As Int 
	For c=0 To ValoresComunes.SetPoint .Size -1
		Dim s As Sp
		s= Val.get (c)
		If s.Nombre <> "" Then	ListView1.AddTwoLinesAndBitmap2 ( s.Nombre  ,ValoresComunes.GetLanString ("SNV"), ValoresComunes.Icon (s.Icon ),  c)
		
	Next

	
End Sub 


Sub CmdGuardar_Click
	ValoresComunes.SetPoint =Val
	ValoresComunes.GuardaSetpoint  
	ToastMessageShow(ValoresComunes.GetLanString ("reg139"), True)
End Sub

Sub CmdNew_Click
	Dim s As  Sp
	s.Nombre =ValoresComunes.GetLanString ("reg133")
	s.Minimo =0
	s.Maximo =65000
	s.Icon =4
	
	Val.Add (s)
	
	
	RefrescaPantalla
End Sub

Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim Lst As List
	Lst.Initialize 
	Lst.Add (ValoresComunes.GetLanString ("reg111"))'Nombre
	Lst.Add(ValoresComunes.GetLanString ("reg113"))'Rango
	Lst.Add(ValoresComunes.GetLanStringDefault ("Mva","Maximum value"))
	Lst.Add(ValoresComunes.GetLanStringDefault ("Miv","Minimum value"))
	Lst.Add (ValoresComunes.GetLanString ("reg65"))
	
	
	'Lst.Add(ValoresComunes.GetLanString ("reg114"))
	Lst.Add(ValoresComunes.GetLanString ("Cancel"))
	
	Dim Seleccion As Int
	Seleccion =InputList(Lst,ValoresComunes.GetLanString ("reg75"),0)
	Select Case Seleccion
		Case 0
		Dim Dialog As InputDialog 
			Dim s As Sp
			s=Val.Get (Position)
			Dialog.InputType =  Dialog.INPUT_TYPE_TEXT 
			Dialog.Input = s.Nombre 
			Dim Result As Int
			Result = Dialog.Show ( ValoresComunes.GetLanString ("reg90" ),ValoresComunes.GetLanString ("reg111"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
			If Result=  DialogResponse.POSITIVE  Then
			
				s.Nombre  =Dialog.Input  
				
			End If

		Case 1
			Dim Lst As List
			Lst.Initialize 
			Lst.Add ("Temperature")		
			Lst.Add("Humidity ")
			Lst.Add("Lux")
			Lst.Add("Generic Sensor")
		
			Dim Va As Int 
		    Va =InputList(Lst,ValoresComunes.GetLanString ("reg75"),  ValoresComunes.Circuitos(Value).Value )
			If Va>-1 Then
				Dim s As Sp
				s=Val.Get (Position)
				s.Icon =Va+1
				
			End If
		
		
			'#define Sensor_      1
			'#define Sensor_        2
			'#define Sensor_   	        3
			'#define Sensor_Generic          4
		

		Case 2'Rango superior
			Dim s As Sp
			s=Val.Get (Position)
			
			Dim Dialogo As NumberDialog 
			
			
			Dialogo.Digits =5
			
			
			Dialogo.Number = s.Maximo 
			
			Dim Result As Int
			Result = Dialogo.Show(ValoresComunes.GetLanStringDefault("reg90","New Value"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)	
			
			If Result = DialogResponse.POSITIVE Then
				s.Maximo = Dialogo.Number 
			End If
		
		
		
		Case 3'Rango superior
			Dim s As Sp
			s=Val.Get (Position)
			
			Dim Dialogo As NumberDialog 
			
			
			Dialogo.Digits =5
			
			
			Dialogo.Number = s.Minimo  
			
			Dim Result As Int
			Result = Dialogo.Show(ValoresComunes.GetLanStringDefault("reg90","New Value"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)	
			
			If Result = DialogResponse.POSITIVE Then
				s.Minimo  = Dialogo.Number 
			End If
		Case 4 'borrar
			Val.RemoveAt (Position)
			
	End Select
	'ValoresSalidas(Position)=Seleccion
	RefrescaPantalla
End Sub

Sub CmdTerminar_Click
	Activity.Finish
End Sub