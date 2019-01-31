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

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim series1 As LineSeries
	Dim series2 As LineSeries
		
		
	Dim graphview As graph
	
	Dim Valores1 As List 
	Dim Valores2 As List
	


End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	
	Valores1.Initialize 
	Valores2.Initialize 
	
	Dim temp1 As Double 
	Dim temp2 As Double
	Dim c As Int
	
	temp1=20
	temp2=18
	
	For c =0 To 99
		Valores1.Add (temp1)
		Valores2.Add (temp2)
		
		If temp1<30 Then
			temp1=temp1+1
		Else
			temp1=22	
		End If
		If temp2<30 Then
			temp2=temp2+1
		Else
			temp2=22	
		End If
	Next 
	'Valores1.To
	
	Dim xx(100) As Double
	Dim yy(Valores1.Size ) As Double
	Dim yy2(Valores1.Size) As Double 
	
	For c =0 To Valores1.Size -1
		yy(c)= Valores1.Get (c)
	Next 
	For c =0 To Valores2.Size -1
		yy2(c)= Valores2.Get (c)
	Next 
	Msgbox("ok","")
	'generate X Axis data and place in xx array
	For  j = 0 To xx.Length -1
		xx(j) = j
	Next
	
	'Initialize Series with eventName, Title, Color, Dates(true/false) and x axis data and y axis data
	series1.Initialize("series1", "Temperatura 2", Colors.Blue , False, xx,  yy)
	series2.Initialize("series2", "Temperatura 1", Colors.black , False, xx, yy2)
	'Initialize graphview with Title Name
	graphview.Initialize("Simple Line Series Example")
	'Set Horizontal Label axis color
	graphview.SetHorizontalAxisTitle("Data Points", Colors.Red)
	'set Vertical Label axis color
	graphview.SetVerticalAxisTitle("Voltage pk-pk", Colors.Red)
	'Add line series to graphview
	graphview.AddLineSeries(False, False, Array As LineSeries(series1))
	graphview.AddLineSeries(False, False, Array As LineSeries(series2))
	'add graphview to activity
	Activity.AddView(graphview, 0, 0, 100%x, 100%y)
	

End Sub

Sub Activity_Resume
	If Main.CloseApp =True Then
		Activity.Finish 
		Return
	End If
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub series1_SeriesClicked (xdata As String, ydata As String)
	ToastMessageShow("X Data: " & xdata & " " & "Y Data: " & ydata, False)
	Log("X Data: " & xdata & " " & "Y Data: " & ydata)
End Sub


