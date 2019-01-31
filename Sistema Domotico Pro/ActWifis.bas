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
	Dim l As List 
	Dim ListView1 As ListView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	If ValoresComunes.Centrales .IsInitialized = False  Then Return	

	
	ListView1.Initialize ("ListView1")
	Activity.AddView (ListView1 ,0,0,Activity.Width,Activity.Height)

	

	
End Sub
Sub Activity_Resume
	If ValoresComunes.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True  Then
		If File.Exists ( File.DirInternal ,"SSID" & ValoresComunes.Central.nombre)   Then	
			l = File.ReadList (File.DirInternal ,"SSID" & ValoresComunes.Central.nombre)
			If l.Size =0 Then
				Msgbox("No Wifi Active!!!","")
				Activity.Finish 
				Return
			Else
				RefrescarPantalla
			End If
						
		Else
			Msgbox("No Wifi Active!!!","")
			Activity.Finish 
			Return
		End If
	Else
		Activity.Finish 
		StartActivity(Main)
	End If
	

End Sub
Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim R As Int
	R=Msgbox2(ValoresComunes.GetLanString ("reg66"),ValoresComunes.GetLanString ("reg65"),ValoresComunes.GetLanString ("Y"),ValoresComunes.GetLanString ("N"),"",Null)
	If R = DialogResponse.POSITIVE Then 
		l.RemoveAt (Position)
		If l.Size >0 Then
			File.WriteList  (File.DirInternal ,"SSID" & ValoresComunes.Central.nombre,l)
			RefrescarPantalla
			
		Else
			File.Delete (File.DirInternal ,"SSID" & ValoresComunes.Central.nombre)
			Activity.Finish 
			
		End If
		
		
		
	End If	
End Sub
Sub RefrescarPantalla
	Dim c As Int
	ListView1.Clear 
	For c =0 To l.Size -1
		ListView1.AddTwoLinesAndBitmap2 ( l.Get (c), "",ValoresComunes.Delete ,l.GET(c))
	Next
End Sub
Sub Activity_Pause (UserClosed As Boolean)
	Activity.Finish 
End Sub


