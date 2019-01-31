Type=Class
Version=7.01
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'********************************
'*** vertical ICSLike Seekbar ***
'***  version 1.0 - by mabool ***
'********************************

Sub Class_Globals
	Private mbBackground As Panel 
	Private mbBtn As Panel
	Private mbActivity As Activity
	Private mbEventName As String
	Private mbModule As Object
	Private mbLabel As Label	
	Private halfBtnHeight As Double
	Private textVisible As Boolean
	Private bgPaint As ABPaint
	Private mbLine As ABExtDrawing
	Private cvs_disabled As Canvas
	Private cvs_normal As Canvas
	Private cvs_pressed As Canvas
	Private lineColor As Int
	Private btnColor As Int
	Private radius As Double
	Private cvsmbBackground As Canvas
	Private areaRect As Rect
	Public currentValue As Int
	Public maxtoUp As Boolean
	Public  maxValue As Int
	Public stepValue As Int = 1 'default is 1
End Sub

'Initializes the object. You can add parameters to this method if needed.
Public Sub Initialize(myActivity As Activity,Module As Object,EventName As String,myLeft As Int,myTop As Int,myWidth As Int,myHeight As Int,myStrokewidth As Double,myStrokecolor As Int,myBtncolor As Int,myTextvisible As Boolean,initValue As Int,myMax As Int,mymaxtoUp As Boolean) As Object

	mbActivity = myActivity
	mbEventName = EventName
	mbModule = Module
	textVisible = myTextvisible
	maxValue = myMax
	lineColor = myStrokecolor
	btnColor = myBtncolor
	radius = myWidth/2
	maxtoUp = mymaxtoUp
	
'   *** background ***	
	mbBackground.Initialize("mbBackground")
	mbBackground.color = Colors.Transparent
	mbBackground.Tag ="mbvs"
	mbActivity.AddView(mbBackground,myLeft,myTop,myWidth,myHeight)
	areaRect.Initialize(0,0,mbBackground.Width, mbBackground.Height)
	cvsmbBackground.Initialize(mbBackground)

	bgPaint.Initialize
	bgPaint.SetAntiAlias(True)
	bgPaint.SetStyle(bgPaint.Style_STROKE)
	bgPaint.SetStrokeWidth(myStrokewidth)
	bgPaint.SetAlpha(145)
	bgPaint.SetColor(lineColor)
	mbLine.drawLine(cvsmbBackground,radius, radius, radius, mbBackground.Height - radius, bgPaint)
'   *** end background ***	
'
'   *** button ***	
	mbBtn.Initialize("mbBtn")
	mbBtn.color = Colors.Transparent

	mbBackground.AddView(mbBtn, 0,0, myWidth,myWidth)

'   *** painting canvas ***	
	Dim btnDrawing As ABExtDrawing
	Dim btnPaint As ABPaint
	btnPaint.Initialize
	btnPaint.SetAntiAlias(True)

'	*** create bitmap disabled ***	
	Dim bmp_disabled As Bitmap
	bmp_disabled.InitializeMutable(myWidth,myWidth)
	cvs_disabled.Initialize2(bmp_disabled)
	btnPaint.SetStyle(btnPaint.Style_FILL)
	btnPaint.SetColor(Colors.RGB(136,136,136))
	btnPaint.SetAlpha(77)
	btnDrawing.drawCircle(cvs_disabled,radius,radius,radius,btnPaint)
	btnPaint.SetColor(myBtncolor)
	btnPaint.SetAlpha(255)
	btnDrawing.drawCircle(cvs_disabled,radius,radius,myWidth/9 ,btnPaint)


'	*** create bitmap pressed ***	
	Dim bmp_pressed As Bitmap
	bmp_pressed.InitializeMutable(myWidth,myWidth)
	cvs_pressed.Initialize2(bmp_pressed)
	btnPaint.SetStyle(btnPaint.Style_FILL)
	btnPaint.SetColor(myBtncolor)
	btnPaint.SetAlpha(154)
	btnDrawing.drawCircle(cvs_pressed,radius,radius,radius ,btnPaint)
	btnPaint.SetStyle(btnPaint.Style_STROKE)
	btnPaint.SetStrokeWidth(2dip)
	btnDrawing.drawCircle(cvs_pressed,radius,radius,radius-2dip ,btnPaint)
	If myTextvisible = False Then
		btnPaint.SetAlpha(255)
	btnPaint.SetStyle(btnPaint.Style_FILL)
	btnDrawing.drawCircle(cvs_pressed,radius,radius,myWidth/6 ,btnPaint)
	End If
'	*** create bitmap normal ***	
	cvs_normal.Initialize(mbBtn)
	btnPaint.SetStyle(btnPaint.Style_FILL)
	btnPaint.SetColor(myBtncolor)
	btnPaint.SetAlpha(154)
	btnDrawing.drawCircle(cvs_normal,radius,radius,radius ,btnPaint)
'	*** draw center only if no text
	If myTextvisible = False Then
		btnPaint.SetAlpha(255)
		btnDrawing.drawCircle(cvs_normal,radius,radius,myWidth/6 ,btnPaint)
	End If
'   *** end painting canvas ***	

	mbActivity.Invalidate
	
	If textVisible = True Then
		mbLabel.Initialize("")
		mbBtn.AddView(mbLabel,0,0,mbBtn.Width,mbBtn.Height)	
		mbLabel.Gravity = Bit.OR(Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL)
		CustomizeText(Colors.White,14,Typeface.DEFAULT)
		mbLabel.Text = returnValue
	End If

	setValue (initValue)

End Sub

Private Sub mbBackground_Touch (Action As Int, X As Float, Y As Float)
			mbBtn.Top = Y - radius
	Select Action
		Case mbActivity.ACTION_DOWN
			mbBtn.SetBackgroundImage(cvs_pressed.Bitmap)
		Case mbActivity.ACTION_UP
			mbBtn.SetBackgroundImage(cvs_normal.Bitmap)
			setValue(Round(returnValue/stepValue) * stepValue)
	End Select


' *** check limits ***
			If mbBtn.Top < 0 Then
				mbBtn.Top = 0
			End If
			If (mbBtn.Top + mbBtn.Height) > mbBackground.Height Then
				mbBtn.Top =  mbBackground.Height - mbBtn.Height 	
			End If

' *** clear lines in background ***
				Redraw_Background(True)
			currentValue = returnValue
			If textVisible = True Then
				mbLabel.text = returnValue
			End If
			If SubExists(mbModule, mbEventName) Then
				CallSub2(mbModule, mbEventName,returnValue)
			End If
End Sub
Public Sub Redraw_Background(Enabled As Boolean)
	cvsmbBackground.drawRect(areaRect, Colors.Transparent, True, 0)
	mbBackground.Invalidate
	bgPaint.SetColor(lineColor)
	bgPaint.SetAlpha(145)
	'draw back line
	mbLine.drawLine(cvsmbBackground,radius, radius, radius, mbBackground.Height - radius, bgPaint)
	If Enabled = True Then 'if seekbar is enabled 
		bgPaint.SetColor(btnColor)
		bgPaint.SetAlpha(255)
			If (textVisible = True) Then 'if text is visible the colored line begins outside the radius 

				If maxtoUp = True Then ' max is set to top 
					If mbBtn.Top < (mbBackground.Height - mbBtn.Height - radius) Then
						mbLine.drawLine(cvsmbBackground,radius, mbBtn.Top + mbBtn.Height, radius,mbBackground.Height-radius , bgPaint)
					End If		

				Else 'max is set to bottom
					If mbBtn.Top > radius Then
						mbLine.drawLine(cvsmbBackground,radius, radius, radius,mbBtn.Top , bgPaint)
					End If
				End If
				
			Else ' if text is NOT visible
					If maxtoUp = True Then ' max is at the top 
						mbLine.drawLine(cvsmbBackground,radius, mbBtn.Top + radius, radius,mbBackground.Height-radius , bgPaint)
					Else  ' max is at the bottom 
						mbLine.drawLine(cvsmbBackground,radius, radius, radius,mbBtn.Top + radius , bgPaint)
					End If
			End If
	End If
End Sub
Public Sub CustomizeText(fontColor As Int,FontSize As Int,myTypeface As Typeface)
	If textVisible = True Then
		mbLabel.Typeface = myTypeface
		mbLabel.TextColor = fontColor
		mbLabel.TextSize = FontSize
	End If	
	End Sub
Public Sub returnValue
	If maxtoUp = True Then ' max is at the top 
		Return  maxValue - Round (mbBtn.Top / ((mbBackground.Height - mbBtn.Height) / maxValue))
	Else
		Return  Round (mbBtn.Top / ((mbBackground.Height - mbBtn.Height) / maxValue))
	End If
End Sub
Public Sub setValue(Value As Int)
	currentValue = Value

	If maxtoUp = True Then ' max is at the top 
		mbBtn.Top = mbBackground.Height-mbBtn.Height - Round(Value * ((mbBackground.Height-mbBtn.Height) / maxValue))
	Else
		mbBtn.Top = Round(Value * ((mbBackground.Height-mbBtn.Height) / maxValue))
	End If
	Redraw_Background(True)
	If textVisible = True Then
		mbLabel.text = returnValue
	End If
End Sub
Public Sub Enable
	mbBtn.SetBackgroundImage(cvs_normal.Bitmap)
	Redraw_Background(True)
	mbBackground.Enabled = True
	If textVisible = True Then
		mbLabel.Visible = True
	End If		
End Sub
Public Sub Disable
	mbBtn.SetBackgroundImage(cvs_disabled.Bitmap)
	Redraw_Background(False)
	mbBackground.Enabled = False
	If textVisible = True Then
		mbLabel.Visible = False
	End If	
End Sub
