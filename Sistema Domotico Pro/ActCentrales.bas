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

	Private CmdNew As Button
	Private ListView1 As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	'If ValoresComunes.Centrales .IsInitialized = False  Then Return
	Activity.LoadLayout ("frmcentrales")
	
	ListView1.Height = PerYToCurrent(83)
	ListView1.Width =Activity.Width 
	
	CmdNew.Top=PerYToCurrent(85)
	CmdNew.Height =PerYToCurrent(15)
	CmdNew.Width =Activity.Width
	CmdNew.Text = ValoresComunes.GetLanString ("reg185")
	
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
	
	RefrescaPantalla
End Sub

Sub Activity_Resume
	If ValoresComunes.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	
	If ValoresComunes.Centrales .IsInitialized Then
		
		If ActMosaico.UpdateCentral Then RefrescaPantalla
	
	Else
		If File.Exists ( File.DirInternal ,"Centrales")  = False  Then
			Activity.Finish 
		Else
			ValoresComunes.Centrales=  File.ReadList (File.DirInternal , "Centrales" )
		End If
	End If

End Sub
Sub RefrescaPantalla
	
	
	'If ListView1.Size <> ValoresComunes.Centrales .Size Then		
		Dim li As Int
		'Dim l As List
		ListView1.Clear 
		For li = 0 To ValoresComunes.Centrales.Size -1
			ListView1.AddTwoLinesAndBitmap (ValoresComunes.Centrales .Get(li),ValoresComunes.GetLanString("SNV"),ValoresComunes.GetImgDevice(ValoresComunes.Centrales .Get(li),True))
				
				'If File.Exists (File.DirInternal , + "Conect") Then
				'If File.Exists ( File.DirInternal ,"Conect" & ValoresComunes.Centrales.Get (li))   Then
				'	l= File.ReadList (File.DirInternal ,"Conect" & ValoresComunes.Centrales.Get (li))
				'	ListView1.AddTwoLinesAndBitmap (ValoresComunes.Centrales .Get(li),ValoresComunes.GetLanString ("PC"),ValoresComunes.GetCentralImg(l.Get (5), True)	 )
				
				'Else 
				'	ListView1.AddTwoLinesAndBitmap (ValoresComunes.Centrales .Get(li),ValoresComunes.GetLanString ("PC"),ValoresComunes.Home )
				'End If
				
				
				
			Next
	'End If
End Sub
Sub Activity_Pause (UserClosed As Boolean)
	Activity.Finish 
End Sub
Sub DeleteCentral(Name As String)

	If File.Exists (File.DirInternal ,"Conect" & Name) Then 
		File.Delete (File.DirInternal ,"Conect" & Name )
	End If
	
	If File.Exists (File.DirInternal ,"Conexion" & Name) Then 
		File.Delete (File.DirInternal ,"Conexion" & Name )
	End If
	If File.Exists (File.DirInternal ,"Comandos" & Name) Then 
		File.Delete (File.DirInternal ,"Comandos" & Name)
	End If
	If File.Exists (File.DirInternal ,"Sensores" & Name) Then
		File.Delete (File.DirInternal ,"Sensores" & Name)
	End If
	If File.Exists (File.DirInternal ,"Condicionados" & Name) Then 
		File.Delete (File.DirInternal ,"Condicionados" & Name)
	End If
	If File.Exists (File.DirInternal ,"ConfigCir" & Name) Then 
		File.Delete (File.DirInternal ,"ConfigCir" & Name)
	End If
	If File.Exists (File.DirInternal ,"ConfigEsc" & Name) Then 
		File.Delete (File.DirInternal ,"ConfigEsc" & Name)
	End If
	If File.Exists (File.DirInternal ,"SSID" & Name) Then 
		File.Delete (File.DirInternal ,"SSID" & Name)
	End If

	
	
	
	If File.Exists ( File.DirInternal ,"Consignas"& Name)    Then
		File.Delete (File.DirInternal ,"Consignas" & Name)
	End If
	If File.Exists ( File.DirInternal ,"ConsignasMax"& Name)    Then
		File.Delete (File.DirInternal ,"ConsignasMax" & Name)
	End If
	If File.Exists ( File.DirInternal ,"ConsignasMin"& Name)    Then
		File.Delete (File.DirInternal ,"ConsignasMin" & Name)
	End If
	If File.Exists ( File.DirInternal ,"ConsignasIcon"& Name)    Then
		File.Delete (File.DirInternal ,"ConsignasIcon" & Name)
	End If
	If File.Exists ( File.DirInternal ,"Camara"& Name)    Then
		File.Delete (File.DirInternal ,"Camara" & Name)
	End If
	

	
	'borrar central de lista
	Dim i As Int
	i= ValoresComunes.Centrales .IndexOf (Name)
	If i>-1 Then
		ValoresComunes.Centrales .RemoveAt (i)
		ValoresComunes.GuardarCentrales
	End If
	
End Sub

Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim l As List 
	l.Initialize 
	l.Add (ValoresComunes.GetLanString ("reg124"))
	l.Add (ValoresComunes.GetLanString ("reg125"))
	'l.Add ("Connect Central")
	Dim Result As Int 
	Result =InputList(l,ValoresComunes.GetLanString ("reg75"), 0)
	
	If Result = 0 Then
		ActCentral.InitialName = Value
		StartActivity(ActCentral)
	Else If Result=1 Then
		If ValoresComunes.Centrales .Size > 1 Then
			DeleteCentral(Value)
			RefrescaPantalla
		Else
			Msgbox(ValoresComunes.GetLanString ("reg126"),ValoresComunes.GetLanString ("reg120"))
		End If
	End If
End Sub
Sub CmdNew_Click
	ActCentral.InitialName =""
	StartActivity(ActCentral)
End Sub