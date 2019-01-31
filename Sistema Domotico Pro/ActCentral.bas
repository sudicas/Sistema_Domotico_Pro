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
	Public InitialName As String
End Sub


Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private ListView1 As ListView
	Dim Name As String
	Dim Description As String
	Dim Icon As Int
	Dim IpI As String
	Dim IpO As String
	Dim PortIn As Int
	Dim PortOut As Int
	Dim Mail As String
	Dim Pass As String
	Dim ConexionSegura As Boolean
	
	
	'Dim Version As Int
	
	

	Private CmdNew As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	Activity.LoadLayout ("frmcentrales")
	
	ListView1.Height = PerYToCurrent(83)
	ListView1.Width =Activity.Width
	
	CmdNew.Top=PerYToCurrent(85)
	CmdNew.Height =PerYToCurrent(15)
	CmdNew.Width =Activity.Width
	CmdNew.Text = ValoresComunes.GetLanString ("reg185")
	
	

End Sub

Sub Activity_Resume
	If ValoresComunes.CloseApp =True Then
		Activity.Finish
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then
		
		CmdNew.Text =ValoresComunes.GetLanString ("reg128")

		If InitialName="" Then
			IniciaNewCentral
			
		Else
			Name=InitialName
			If File.Exists ( File.DirInternal ,"Conexion" & InitialName)   Then
				Dim lst As List
				lst = File.ReadList(File.DirInternal  ,"Conexion" & InitialName )
				IpI= lst.Get (0)
				IpO= lst.Get (1)
				PortIn= lst.Get (2)
				PortOut= lst.Get (3)
				Description=ValoresComunes.GetLanStringDefault  ("PC","Push to connect")
				Mail =""
				Pass =""
				ConexionSegura =False
				Icon=0
				File.Delete ( File.DirInternal ,"Conexion" & InitialName)
				GuardaCentral
				
				
				'Version=ValoresComunes.Versiones .Get (InitialName)
		
			Else
			
				If File.Exists ( File.DirInternal ,"Conect" & InitialName)   Then
					Dim lst As List
					lst = File.ReadList(File.DirInternal  ,"Conect" & InitialName )
					IpI= lst.Get (0)
					IpO= lst.Get (1)
					PortIn= lst.Get (2)
					PortOut= lst.Get (3)
					Description=lst.Get (4)
					
					Icon=lst.Get (5)
					Mail =lst.Get (6)
					Pass =lst.Get (7)
					ConexionSegura =lst.Get (8)
					
					File.Delete ( File.DirInternal ,"Conexion" & InitialName)
					GuardaCentral
				Else
					IniciaNewCentral
					Name=InitialName
				End If
			End If

		End If
		RefrescaPantalla
	Else
		Activity.Finish
		StartActivity(Main)
		
	End If
End Sub
Sub IniciaNewCentral
	Dim disponible As Boolean
	disponible = False
	Dim c As Int
	c=1
	Do While disponible = False
		Name= "Unit "&c
		If File.Exists (File.DirInternal ,"Conect" & Name)= False Then  disponible= True
		c=c+1
	Loop
	IpI= "192.168.1.200"
	IpO= "192.168.1.200"
	PortIn= 5000
	PortOut= 5000
	Description=ValoresComunes.GetLanStringDefault  ("PC","Push to connect")
	Mail =""
	Pass =""
	ConexionSegura =False
	Icon=0
		
End Sub
Sub RefrescaPantalla
	ListView1.Clear
	ListView1.AddTwoLinesAndBitmap2(Name,ValoresComunes.GetLanString ("reg111"),ValoresComunes.Config,0)
	ListView1.AddTwoLinesAndBitmap2(Description, ValoresComunes.GetLanString ("reg112"),ValoresComunes.Config,1)
	ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.LanString .GetDefault("Icon","Select Icon"),"",ValoresComunes.GetCentralImg(Icon,True),2)


	
	ListView1.AddTwoLinesAndBitmap2(IpI,ValoresComunes.GetLanString ("reg168"),ValoresComunes.Config,3)
	ListView1.AddTwoLinesAndBitmap2(IpO,ValoresComunes.GetLanString ("reg115"),ValoresComunes.Config,4)
	ListView1.AddTwoLinesAndBitmap2(PortIn,ValoresComunes.GetLanString ("reg116"),ValoresComunes.Config,5)
	ListView1.AddTwoLinesAndBitmap2(PortOut,ValoresComunes.GetLanString ("reg117"),ValoresComunes.Config,6)
	
	ListView1.AddTwoLinesAndBitmap2( Mail,ValoresComunes.GetLanString ("reg100"),ValoresComunes.Config,7)
	If Pass.Length >7  Then
		ListView1.AddTwoLinesAndBitmap2("********",ValoresComunes.GetLanString ("reg101"),ValoresComunes.Config,8)
	Else
		ListView1.AddTwoLinesAndBitmap2("",ValoresComunes.GetLanString ("reg101"),ValoresComunes.Config,8)
	End If
	
	If ConexionSegura Then
		ListView1.AddTwoLinesAndBitmap2(ValoresComunes.GetLanString ("reg102"),"",ValoresComunes.CheckOn ,9)
	Else
		ListView1.AddTwoLinesAndBitmap2(ValoresComunes.GetLanString ("reg103"),"",ValoresComunes.CheckOff ,9)
	End If
	
	ListView1.AddTwoLinesAndBitmap2(ValoresComunes.GetLanStringDefault ("RSC","Reload system configuration"),"",ValoresComunes.Config,10)
	ListView1.AddTwoLinesAndBitmap2(ValoresComunes.GetLanStringDefault ("SiW","Set internal Wifi"),"",ValoresComunes.Config,11)
	
End Sub

Sub RefrescaIcoPantalla
	ListView1.Clear
	Dim c As Int
	For c=0 To 6
		ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.GetLanStringDefault("Icon","Select Icon"),"",ValoresComunes.GetCentralImg(c,True),c+100)
	Next
	
	'For c =200 To 205
	'ListView1.AddTwoLinesAndBitmap  (ValoresComunes.GetLanStringDefault ("SePI","Select Custom Icon"), "", ValoresComunes.PersonalCentralIcon(c),c+200)
	
	'Next
	'ListView1.AddTwoLinesAndBitmap  (ValoresComunes.GetLanStringDefault (("SetPI","Configure user icon"), "", ValoresComunes.home,1000)
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Activity.Finish
End Sub



Sub ListView1_ItemClick (Position As Int, Value As Object)

	If Value <100 Then
		Dim Result As Int
		
		Select  Value
			Case 0:	'NOmbre
				Dim dial As InputDialog
				dial.Input = Name
			
				Result = dial.Show ( ValoresComunes.GetLanString ("reg119"),ValoresComunes.GetLanString ("reg104"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
				If Result= DialogResponse.POSITIVE Then
					If dial.Input ="" Then Return
					If ValoresComunes.Centrales .IsInitialized Then
						If ValoresComunes.Centrales .IndexOf (dial.Input )<>-1 Then
							Msgbox(ValoresComunes.GetLanString ("reg121"),ValoresComunes.GetLanString ("reg120"))
							Return
						End If
					End If
					Name=dial.Input
				End If
			Case 1:	'Descripcion
				Dim dial As InputDialog
				dial.Input = Description
			
				Result = dial.Show ( ValoresComunes.GetLanString ("reg112"),ValoresComunes.GetLanString ("reg104"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
				If Result= DialogResponse.POSITIVE Then
					If dial.Input ="" Then Return
					Description=dial.Input
				End If
			Case 2:	'icon
				RefrescaIcoPantalla
				Return
			Case 3:	'Ip In
				Dim dial As InputDialog
				dial.Input = IpI
			
				Result = dial.Show ( ValoresComunes.GetLanString ("reg168") ,ValoresComunes.GetLanString ("reg104"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
				If Result= DialogResponse.POSITIVE Then
					IpI =dial.Input
				End If
			
			Case 4:	'Ip Out
				
			
				Dim dial As InputDialog
				dial.Input = IpO
			
				Result = dial.Show ( ValoresComunes.GetLanString ("reg115"),ValoresComunes.GetLanString ("reg104"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
				If Result= DialogResponse.POSITIVE Then
					IpO =dial.Input
				End If
			Case 5: 	'Puerto in
				Dim di As NumberDialog
				di.Digits =5
				di.Number = PortIn
				Result= di.Show (ValoresComunes.GetLanString ("reg122"),ValoresComunes.GetLanString ("Ok"),ValoresComunes.GetLanString ("Cancel"),"",Null )
				If Result= DialogResponse.POSITIVE Then
					PortIn =di.Number
				End If
			Case 6:	'Puerto Out
				Dim di As NumberDialog
				di.Digits =5
				di.Number = PortOut
				Result= di.Show (ValoresComunes.GetLanString ("reg123"),ValoresComunes.GetLanString ("Ok"),ValoresComunes.GetLanString ("Cancel"),"",Null)
				If Result= DialogResponse.POSITIVE Then
					PortOut =di.Number
				End If
			
			Case 7:	'mail
				Dim dial As InputDialog
				dial.Input = Mail
			
				Result = dial.Show ( ValoresComunes.GetLanString ("reg100"),ValoresComunes.GetLanString ("reg104"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
				If Result= DialogResponse.POSITIVE Then
					Mail=dial.Input
				End If
			Case 8:	'pass
				Dim dial As InputDialog
				dial.Input = ""
			
				Result = dial.Show ( ValoresComunes.GetLanString ("reg101"),ValoresComunes.GetLanString ("reg104"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
				If Result= DialogResponse.POSITIVE Then
					If dial.Input .Length =8 Then
						Pass=dial.Input
					Else
						Msgbox("Len = 8",ValoresComunes.GetLanString ("reg120"))
					End If
			
				End If
			Case 9
		
			
				If ConexionSegura Then
					ConexionSegura=False
				Else
					If Pass.Length =8 Then
						ConexionSegura=True
					Else
						Msgbox(ValoresComunes.GetLanStringDefault("passr", "password required"  ),ValoresComunes.GetLanString("reg120"))
					End If
				End If
			
			
			Case 10
				ValoresComunes.Circuitos (0).Rango =1000
				ValoresComunes.Sensores  (0).Rango =1000
				ValoresComunes.GuardarConfigCircuitos
				ValoresComunes.GuardarConfigSensores
			
				ToastMessageShow(ValoresComunes.GetLanStringDefault("reg139", "COMPLETED"  ),True)
				StartActivity(Main)
				Activity.Finish
			
			Case 11
				StartActivity(ActWifis)
		End Select
		RefrescaPantalla
	Else
		Icon =Value-100
		RefrescaPantalla
	End If
	
	
End Sub
Sub GuardaCentral
	If ValoresComunes.Centrales .IndexOf (Name)=-1 Then
		ValoresComunes.Centrales .Add (Name)
	End If
	
	
	
	
	If InitialName<>"" Then
		ValoresComunes.CambiarNombreCentral(InitialName,Name)
	End If
	
	
	
	
	'datos de conexion
	Dim c As Arduino
	c.Nombre  =Name
	c.IpIn =IpI
	'cabio para version free
	'*************************************************************************	
	c.IpOut =IpO
	
	c.PuertoIn =PortIn
	c.PuertoOut =PortOut
	c.descripcion =Description
	c.Icon =Icon
	c.Mail =Mail
	c.Password  =Pass
	c.ConexionSegura=ConexionSegura
	ValoresComunes.GuardaCentral (c)
		
	ValoresComunes.GuardarCentrales
	
	
End Sub
Sub CmdNew_Click
	GuardaCentral
	ValoresComunes.SetImgDevice(Name,Icon)
	ActMosaico.UpdateCentral=True
	Activity.Finish
End Sub
