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
	Dim CmdNew As Button
	Dim ListView1 As ListView
	Dim MyCam As Map 
	Dim Name As String 
End Sub
Sub Activity_Create(FirstTime As Boolean)
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	Activity.LoadLayout ("frmcentrales")
	Activity.Title =ValoresComunes.GetLanString ("SCD")
	
	
	ListView1.Height = PerYToCurrent(83)
	ListView1.Width =Activity.Width 
	
	CmdNew.Top=PerYToCurrent(85)
	CmdNew.Height =PerYToCurrent(15)
	CmdNew.Width =Activity.Width
	CmdNew.Text = ValoresComunes.GetLanString ("reg128")
	
	MyCam.Initialize 
	Dim c As Int 
	
	For c=0 To ValoresComunes.Camaras.Size -1
	'	Dim ca As Camara 
	'	ca= valorescomunes.Camaras .GetValueAt (c)
	'	Camaras.Put (ca.Nombre ,ca)
	Next
	
	
End Sub

Sub Activity_Resume
	If Main.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then
		MyCam.Initialize 
		If ValoresComunes.Camaras.Size > 0 Then
			Dim c As Int 
			For c =0 To  ValoresComunes.Camaras .Size -1
				MyCam.Put (ValoresComunes.Camaras .GetKeyAt (c),ValoresComunes.Camaras .GetValueAt (c))
			Next
		End If
		'MyCam = ValoresComunes.Camaras 
		RefrescaPantalla
	Else
		Activity.Finish 
		StartActivity(Main)
	End If	
End Sub
Sub RefrescaPantalla
	ListView1.Clear 
	Dim c As Int 
	For c=0 To MyCam.Size  -1
		Dim ca As Camara 
		ca = MyCam.GetValueAt  (c)
		ListView1.AddTwoLinesAndBitmap2  (ca.Nombre  ,ValoresComunes.GetLanStringDefault ("ipc","IP cameras"),ValoresComunes.ImgCamara ,c )
	Next
	ListView1.AddTwoLinesAndBitmap2  (ValoresComunes.GetLanStringDefault ("reg185","ADD NEW")  ,ValoresComunes.GetLanStringDefault ("ipc","IP cameras"),ValoresComunes.ImgCamara,10000  )
	
	Name=""
End Sub 
Sub Activity_Pause (UserClosed As Boolean)

End Sub
Sub RefrescaPantalla2(CamName As String )
	ListView1.Clear 
	
	If MyCam.ContainsKey (CamName) Then
		Dim ca As Camara
		ca= MyCam.Get (CamName)
		ListView1.AddTwoLines2 (ValoresComunes.GetLanString ("reg111"),ca.Nombre,100)
		ListView1.AddTwoLines2 (ValoresComunes.GetLanString ("reg168"),ca.IpIN ,101)
		ListView1.AddTwoLines2 (ValoresComunes.GetLanString ("reg122"),ca.Puerto  ,102)
		
		ListView1.AddTwoLines2 (ValoresComunes.GetLanStringDefault  ("User","User"),ca.User  ,103)
		ListView1.AddTwoLines2 (ValoresComunes.GetLanString ("reg101"),ca.pass   ,104)
		ListView1.AddTwoLines2 (ValoresComunes.GetLanString ("reg125"),""  ,105)
		
		
			'LanString.Put ("reg65","Confirm delete?")
		Name =ca.Nombre 
	Else
		RefrescaPantalla
	End If
	
	
	
End Sub 
Sub ListView1_ItemClick (Position As Int, Value As Object)
	If Value<100 Then
		Dim ca As Camara 
		ca=MyCam.GetValueAt (Value)
		RefrescaPantalla2(ca.Nombre )
	Else If Value <1000 Then
		If MyCam.ContainsKey (Name) Then
			Dim cam As Camara 
			cam= MyCam.Get (Name)
			Select  Value
			 Case 100:	'NOmbre
				Dim dial As InputDialog 
				dial.Input = cam.Nombre 
				Dim Result As Int 
				Result = dial.Show ( ValoresComunes.GetLanString ("reg119"),ValoresComunes.GetLanString ("reg104"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)	
				If Result= DialogResponse.POSITIVE Then
					If dial.Input <>"" Then 
												
						MyCam.Remove (Name)
						cam.Nombre = dial.Input 
						MyCam.Put (cam.nombre, cam)
						Name=cam.Nombre 
					End If
					 
				End If			
			 		
			 Case 101:	'Ip In
			 	Dim dial As InputDialog 
				dial.Input = cam.IpIN 
				
				Result = dial.Show ( ValoresComunes.GetLanString ("reg168") ,ValoresComunes.GetLanString ("reg104"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)	
				If Result= DialogResponse.POSITIVE Then
					cam.IpIN  =dial.Input 
					MyCam.Put (cam.nombre, cam)
				End If				
							
				
			 Case 102: 	'Puerto 
				Dim di As NumberDialog 
				di.Digits =5
				di.Number = cam.Puerto 
				Result= di.Show (ValoresComunes.GetLanString ("reg122"),ValoresComunes.GetLanString ("Ok"),ValoresComunes.GetLanString ("Cancel"),"",Null )
				If Result= DialogResponse.POSITIVE Then
					cam.Puerto  =di.Number 
					MyCam.Put (cam.nombre, cam)
				End If
			Case 103:	'user
				Dim dial As InputDialog 
				dial.Input = cam.User 
				Dim Result As Int 
				Result = dial.Show ( ValoresComunes.GetLanString ("reg119"),ValoresComunes.GetLanString ("reg104"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)	
				If Result= DialogResponse.POSITIVE Then
					If dial.Input <>"" Then 								
						cam.User  = dial.Input 
						MyCam.Put (cam.nombre, cam)					
					End If			 
				End If	
			Case 104:	'pass
				Dim dial As InputDialog 
				dial.Input = cam.pass 
				Dim Result As Int 
				Result = dial.Show ( ValoresComunes.GetLanString ("reg119"),ValoresComunes.GetLanString ("reg104"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)	
				If Result= DialogResponse.POSITIVE Then
					If dial.Input <>"" Then 								
						cam.pass   = dial.Input 
						MyCam.Put (cam.nombre, cam)					
					End If			 
				End If	
			Case 105:'DELETE
				Dim R As Int
				R=Msgbox2(ValoresComunes.GetLanString ("reg65"),ValoresComunes.GetLanString ("reg66"),ValoresComunes.GetLanString ("Y"),ValoresComunes.GetLanString ("N"),"",Null)
				If R = DialogResponse.POSITIVE Then 
					MyCam.Remove (cam.Nombre )
				End If	
			End Select	
			
			RefrescaPantalla2(Name)	
		End If
		
	Else If Value = 10000 Then
		Dim ca As Camara 
		
		
		ca.nombre ="new cam" & (MyCam.Size +1)
		ca.IpIN ="192.168.0.200"
		ca.Puerto =80
		ca.User  =""
		ca.pass ="admin"
		ca.Type =1
		Do While MyCam.ContainsKey (ca.nombre)
			ca.Nombre =ca.nombre+"x"
		 Loop
		MyCam.Put (ca.nombre,ca)
		RefrescaPantalla2(ca.Nombre )
	End If
End Sub
Sub CmdNew_Click
	If Name="" Then
		ValoresComunes.Camaras = MyCam
		ValoresComunes.GuardaCamaras
		Activity.Finish 
		
	Else
		RefrescaPantalla
	End If
	
End Sub
