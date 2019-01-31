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

'Activity module
Sub Globals
	Dim SlidingMenu As SlidingMenuStd
	'Dim NfcF As NfcForeground
	'Dim NFC1 As NFC
	
	Dim TestButton As Button
	
	'Action bar settings
	Dim ActBarText As String = "Sliding Menu"
	Dim ActBarTextColor As Int = Colors.White
	Dim ActBarcolor As Int = Colors.RGB(0, 140, 149) 'Colors.RGB(2, 136, 209) 'Colors.RGB(255, 0, 0) 'Colors.RGB(0, 205, 255)
	Dim ActBarLineColor As Int = Colors.Transparent '.RGB(216, 216, 216)
	Dim ActBarCornerRadius As Int = 0dip	
	
	'Menu settings
	Dim MenuWidth As Int = 200dip
	Dim MenuFullLength As Boolean = True	
	Dim MenuColor As Int = Colors.RGB(0, 140, 149)
	Dim MenuTextColor As Int = Colors.White
	Dim MenuTextSize As Int = 20

	'Extra settings
	Dim ShowSlideButton As Boolean = True
	Dim SlideDuration As Int = 200
	Dim FadeAlphaShade As Int = 125
End Sub

Sub Activity_Create(FirstTime As Boolean)		
'	NfcF.EnableForeground		'Enable Foreground Dispatch System
	
'	Activity.LoadLayout("MainLayout")
'	Activity.Title = "Simply Software Sliding Menu"
'	Activity.Height = 100%y + 42dip
	
	TestButton.Initialize(Null)
	TestButton.Text = "Tap me!!!"
	'Activity.AddView(TestButton, 100%x/2-75dip, 100%y/2-75dip, 150dip, 150dip)
	Activity.AddView(TestButton, Activity.Width / 2 - 75dip, Activity.Height / 2 - 75dip, 150dip, 150dip)
	
	BuildMenu					'Lets go and create the sliding menu	
	SlidingMenu.BarsOff
End Sub

Sub Activity_Resume
'	NfcF.EnableForeground
	
'	If NFC1.IsNdefIntent(Activity.GetStartingIntent) Then
'
'		Dim List_NdefRecords As List
'			List_NdefRecords = NFC1.GetNdefRecords(Activity.GetStartingIntent)
'
'		Dim NdefRecord2 As NdefRecord
'			NdefRecord2 = List_NdefRecords.Get(0)
'			
'		Dim NfcText As String
'		
'		If List_NdefRecords.Size > 0 Then
'			NfcText = NdefRecord2.GetAsTextType
'			SlidingMenu.ActBarMsg.text = NfcText 'Sub_NFC_Found(NfcText)
'		Else
'			Msgbox("Wrong data format in NFC tag", "xxxxx")
'		End If
'	End If
	
	SlidingMenu.BarsOff
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	'NfcF.DisableForeground
End Sub

	'EVENT HANDLER FOR MENU BUTTON CLICK(DO NOT DELETE OR RENAME THIS SUB)
Sub BtnSlidingMenu_Click (ItemNo As Object)
	ToastMessageShow("Item clicked: " & ItemNo, False)
End Sub

#Region  BUILD MENU AND ACTION BAR 

Sub BuildMenu
	'BUILD YOUR SLIDING MENU HERE
	SlidingMenu.Initialize(Activity, 42dip, MenuWidth, MenuColor, MenuTextSize, MenuTextColor)
	SlidingMenu.AddItem("Item 1", LoadBitmap(File.DirAssets, "esc.png"), 1)
	SlidingMenu.AddItem("Item 2", LoadBitmap(File.DirAssets, "esc.png"), 2)
	SlidingMenu.AddItem("Item 3", LoadBitmap(File.DirAssets, "esc.png"), 3)

	
	'1. CREATE ACTION BAR AND TOP LEFT BUTTON 
	SlidingMenu.InitializeActionBar(Activity, ActBarcolor, ActBarLineColor, ActBarCornerRadius, ShowSlideButton)

	'2. ACTION BAR TEXT COLOR
	SlidingMenu.ActBarMsg.TextColor = ActBarTextColor
	
	'3. TOP MESSAGE FOR ACTION BAR(IF NEEDED)
	SlidingMenu.ActBarMsg.Text = ActBarText
	
	'4. ACTIVATE SWIPE ACTION FOR MENU
	SlidingMenu.InitializeSwipe(Activity, Me, SlideDuration, FadeAlphaShade, MenuFullLength)		
End Sub

#End Region

#Region  CHECK FOR BACK KEY PRESS 

	'SOFTKEY PRESS
Sub Activity_KeyPress(KeyCode As Int) As Boolean
	If KeyCode = KeyCodes.KEYCODE_BACK AND SlidingMenu.PnlSlide.Visible = True Then
		SlidingMenu.MenuHide
		Return True	
	Else
		Dim KillApp As Int
		'Log(KeyCode)
		
	    KillApp = Msgbox2("Exit " & Activity.Title & "?","Exit?", "Yes", "", "No", Null)
	    
		If KillApp = DialogResponse.POSITIVE Then
	        ExitApplication
			Return False
	    Else
			'SlidingMenu.BarsOff
	        Return True
	    End If
	End If
	
	'Return KeyCode	
End Sub

#End Region