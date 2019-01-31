Type=Activity
Version=7.01
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
	Dim SensorSelect(30 ) As   Boolean
	Dim Fecha As Long
	Dim FechaFin As Long
	Dim UDPSocket1 As UDPSocket
	'Dim Timer1 As Timer
	Dim mdates As List
	Dim Iniciando As Boolean

	Dim Valores As List
	 
	
	Private ph As Phone 'Para controlar horientación
	Dim pw As PhoneWakeState 'Controlar suspension pantalla

	
	
	Dim TempValores As List
	Dim currentFecha As Long
	Dim TramaEnviada() As Byte
	Dim NumeroEnvio As Int
	
	
	
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	
	'Dim series2 As LineSeries
		
	
	'Dim graphview As graph
	Dim  pnlBars As Panel
	
	Dim MaxIndex As Int
	Dim MaxValue As Double
	
	
	'animacion carga
	Dim ImgCargando As ImageView
	Dim AniCargando As  Animation
	
	Dim PaqueteEnviado  As Boolean
	
	
End Sub


Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("FrmDegradado")
	If ValoresComunes.Centrales .IsInitialized = False  Or  ValoresComunes.CloseApp =True Then 	Return
	
	If Main.MyLan.IsInitialized = False Then  Main.MyLan.Initialize( 0, "")
	
	
	
	If UDPSocket1.IsInitialized = False Then  ValoresComunes.IniUDP(UDPSocket1)
	
	If FirstTime Then
		
	End If


	
End Sub

Sub Activity_Resume
	If ValoresComunes.CloseApp =True Then
		Activity.Finish
		Return
	
	Else
		ph.SetScreenOrientation(0) 'Forzar Horientazión
		
		
		If Activity.Width < Activity.Height Then Return
		
		pw.KeepAlive( True) 'set false if brightness is not needed
		
		
		If File.ExternalWritable =False  Then
		 
			Msgbox("Sd card required","")
			Activity.Finish
			Return
		Else
			Dim s As String
			
			
			s = File.DirRootExternal & "/HT"

			If File.Exists(s, "") = False Then
				File.MakeDir(File.DirRootExternal,"HT")
			End If
		End If
	
	
		' 1 = Portrait
		' 2 = Landscape
		' 9 = Reverse Portrait
		' 8 = Reverse Landscape
	   
		'pw.ReleaseKeepAlive ' Liberar mantener viva la actividad
		
		
		
		If Iniciando Then
			'ProgressDialogShow(ValoresComunes.GetLanString ("reg80"))
			Iniciando=False
			
			
			Valores.Initialize
			mdates.Initialize
			TempValores.Initialize
			currentFecha=Fecha
			NumeroEnvio=1
			
			
			If ImgCargando.IsInitialized = False Then
				ImgCargando.Initialize ("ImgCargando")
				ImgCargando.Bitmap = ValoresComunes.Cargando
				ImgCargando.Visible =False
				ImgCargando.Gravity = Gravity.FILL
				Activity.AddView (ImgCargando,  PerXToCurrent(40)	, PerYToCurrent(35),PerXToCurrent(20) ,PerXToCurrent(20) )
			End If
			
			AniCargando.InitializeRotateCenter( "AniCargando", 0, 180, ImgCargando)
			AniCargando.Duration = 1000
			AniCargando.Duration  =   1000
			AniCargando.RepeatCount =5
			AniCargando.RepeatMode = AniCargando.REPEAT_REVERSE
			
			'AniCargando.RepeatCount =5
			'AniCargando.RepeatMode = AniCargando.REPEAT_REVERSE
		
		

			TestFiles
			
			
			
			'Else


			'PintaHistorico
		End If
		
	End If
End Sub
Sub AniCargando_AnimationEnd

	'AniCargando.Start (ImgCargando)
	If PaqueteEnviado Then
		SendTrama(TramaEnviada)
	End If
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	If UserClosed Then
		'Timer1.Enabled =False
		pw.ReleaseKeepAlive
		UDPSocket1.Close
		Activity.Finish
	Else
		
	End If

	

	'StartActivity(ActMosaico)
End Sub

Sub series_SeriesClicked (xdata As String, ydata As String)
	Dim h As String
	h=DateTime.GetMinute(xdata)
	If h.Length =1 Then
		h=":0" & h
	Else
		h=":" & h
	End If
	
	h= DateTime.GetHour  (xdata) & h
	h= DateTime.Date (xdata)& Chr(10)  &  h
	'h=h & DateTime.GetHour(xdata) & ":" &
	
	ToastMessageShow(h & Chr(10) & "Value = " & ydata, False)
	
	
	'Log("X Data: " & xdata & " " & "Y Data: " & ydata)
End Sub

Sub LecturaDatos(ContadorPaquetes As Int )

	
	
	Dim Año, Mes , Dia As Int
	Año=DateTime.GetYear  (currentFecha)
	Mes=DateTime.GetMonth  (currentFecha)
	Dia=DateTime.GetDayOfMonth  (currentFecha)
	
	
	
	Dim LongTrama As Int
	'LineRead=ContadorPaquetes
	If ValoresComunes.central.ConexionSegura Then
		LongTrama= 16
	Else
		LongTrama= 8
	End If
	Dim data(LongTrama) As Byte
	data(0)=72'H
	data(1)=73'I
	data(2)=83'S
	data(3)=84'T
	data(4)=Año-2000
	data(5)=Mes
	data(6)=Dia
	data(7)=ContadorPaquetes

	If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
	SendTrama(data)
	

End Sub
Sub SendTrama(data() As Byte )
	Dim Resultado As Boolean
	Dim Reintento As Int
	
	
	TramaEnviada= data
	If ImgCargando.Visible =False Then
		ImgCargando.Visible=True
		AniCargando.Start (ImgCargando)
		
	End If
	
	
	Do 	While Resultado= False And Reintento < 40
		Dim ServerSocket1 As ServerSocket
		
		Dim MyIp As String
		If ActMosaico.Forzar3g Then
			MyIp=ServerSocket1.GetMyIP
		Else
			MyIp=ServerSocket1.GetMyWifiIP
		End If
		If MyIp  <> "127.0.0.1" Then
			Resultado = True
		Else
			Reintento = Reintento +1
			Sleep (200)
		End If

	Loop
	If Resultado = True Then
		Resultado =False
		Reintento =0
		Do 	While Resultado= False And Reintento < 40
			Resultado = SendingTrama(data)
			Reintento = Reintento +1
			If Resultado=False Then Sleep(200)
		Loop
	End If
	

	If Resultado = False  Then
		ActMosaico.Conectado =False
		StartActivity(ActMosaico)
	Else
		PaqueteEnviado=True
		AniCargando.Start (ImgCargando)
	End If
End Sub
Sub SendingTrama (data() As Byte) As Boolean
	Try
		Dim Packet As UDPPacket
		Packet.Initialize(data, ValoresComunes.ip, ValoresComunes.Puerto )
		UDPSocket1.Send(Packet)
		Return True
	Catch
		Return False
	End Try
End Sub
Sub PintaHistorico()
	ImgCargando.Visible =False
	AniCargando.Stop (ImgCargando)
	
	
	MaxIndex=0
	MaxValue=0
	
	ProgressDialogHide
	
	DateTime.DateFormat = "yyyy-MM-dd"
	DateTime.TimeFormat = "HH:mm"
	Dim series(ValoresComunes.Sensores.Length ) As LineSeries
	Dim Horas(Valores.Size) As Double
	
	
	
	graphview.Initialize("Charts")
	'Set Horizontal Label axis color
	graphview.SetHorizontalAxisTitle("Time", Colors.Red)
	'set Vertical Label axis color
	graphview.SetVerticalAxisTitle("Value", Colors.Red)
	'graphview.SetGridStyletoHorizontal
	graphview.SetGraphViewTextSize (20)
	
	Dim C As Int

	'Dim lh As Int
	For C=0 To Valores.Size - 1

		Dim l As List
		l= Valores.Get (C)
		'lh=l.Get (0)
		
		Dim ho As String
		'ho=DateTime.Date(currentFecha)
		'
	
		
		
		
		If l.Get (0)<10 Then
			ho=  "0" & l.Get (0) &":"
		Else
			ho=   l.Get (0) &":"
		End If
		If l.Get (1)<10 Then
			ho= ho &  "0" & l.Get (1)
		Else
			ho=  ho  & l.Get (1)
		End If
		
		'Dim datesplit() As String  'define an array to hold the date and time after we split it
		'datesplit = Regex.Split(" " , ho) 'split Date and  Time at the Space between Date and Time ex.  "2015-1-16 14:30"
		Dim f As String
		f=DateTime.Date(mdates.Get (C))
		Horas(C)= DateTime.DateTimeParse(f , ho)   'convert date and time to epoch values so that the graph library can use them

		

		'Horas(c) =l.Get (0) + (l.Get (1)/100)'DateTime.DateTimeParse( DateTime.Date(currentFecha) , ho)   'convert date and time to epoch values so that the graph library can use them
		'Horas(c) =DateTime.DateTimeParse( DateTime.Date(currentFecha) , ho)   'convert date and time to epoch values so that the graph library can use them
	Next
	
	'Control linea recta
	Dim Plano As Boolean
	
	Plano =True
	
	For C =0 To ValoresComunes.Sensores.Length -1
		Dim yy(Valores.Size ) As Double
		If SensorSelect(C)=True Then
			Dim p As Int
			For p=0 To Valores.Size -1
				Dim l As List
				l= Valores.Get (p)
				
				If ValoresComunes.Sensores(C).Rango = 1  Or ValoresComunes.Sensores(C).Rango =2 Or ValoresComunes.Sensores(C).Rango =5  Then'Temp y humedad
					yy(p)=l.Get (C +2 )/10
						
				Else
					yy(p)=l.Get (C +2 )
				End If
				
			
				

				If yy(p)> MaxValue Then
					If p>0  Then Plano= False
					MaxValue=yy(p)
					MaxIndex=C
				End If
			
			Next
			series(C).Initialize("series", ValoresComunes.Sensores (C).Nombre ,  GetColor(C) , False,  Horas,  yy)
			
		End If
	Next

	For C =0 To ValoresComunes.Sensores.Length -1
		
		If C<> MaxIndex And SensorSelect(C)=True Then Then
			
			graphview.AddLineSeries( True  , False, Array As LineSeries(series(C)))
		End If
	Next
	graphview.AddLineSeries(True , False, Array As LineSeries(series(MaxIndex)))
	
	If Plano Then
		Dim yp(Valores.Size ) As Double
		Dim  k As Int
		For k=0 To Valores.Size -1
			yp(k)=MaxValue + 10
		Next
		Dim seriesPlana As LineSeries
		seriesPlana.Initialize("series","Max" ,  Colors.White  , False,  Horas,  yp)
		graphview.AddLineSeries(True , False, Array As LineSeries(seriesPlana))
	End If
		
	
	Activity.AddView(graphview, 0, 0, 100%x, 100%y)
	
End Sub
Sub CargaSdData
	Dim l As List
	Dim C As Int
	
	l=File.ReadList (   File.DirRootExternal   & "/HT"  , ValoresComunes.Central .Nombre & currentFecha & ".txt")
	
	
	For C=0 To l.Size -1
		mdates.Add (currentFecha)' esto se cambia de posicion para el nuevo dia en los historicos
		
		Dim s As String
		Dim n As Int
		Dim result As String
		Dim nl As List
	
		s= l.Get (C)
		nl.Initialize
	
		For n=0 To s.Length -1
			Dim ch As Char
			ch= s.CharAt (n)
			If IsNumber (ch) Then
				result = result & ch
			Else If ch ="," Then
				Dim val As Int
				val= result
				result=""
				nl.Add (val)
			End If
		
		Next
		Dim vl As Int
		vl=result
		nl.Add (vl)
		
		 
		Valores.Add (nl)
		
		'Valores.Add (l.Get (C))
	Next
	If currentFecha=FechaFin Then
		PintaHistorico
	Else
		currentFecha=DateTime.Add(currentFecha ,0,0,1)
		TestFiles
	End If
		
	
	
End Sub

Sub TestFiles

	If File.Exists (File.DirRootExternal    & "/HT" ,ValoresComunes.Central .Nombre & currentFecha & ".txt") Then
		CargaSdData
	Else
			
		TempValores.Clear
		NumeroEnvio=1
		LecturaDatos(NumeroEnvio)
		'Return
	End If
		

		

	
End Sub
Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
		
		
		Dim msg As String
		msg = BytesToString(Packet.data, Packet.Offset, Packet.Length, "UTF8")
		
		
		If msg.StartsWith  ("HT") Then
			PaqueteEnviado=False
			
			
			Dim C,S As Int
			C=4
			Dim Val As List
			Val.Initialize
			Val.Add (Packet.data (2)-1)'HORA
			Val.Add (Packet.data (3)-1)'MINUTO
			'mdates.Add (currentFecha) esto se cambia de posicion para el nuevo dia en los historicos
			
			If Val.Get (0)<0 Or Val.Get (1)<0 Then  'Carga completa
				If DateTime.Date(currentFecha)=  DateTime.Date(DateTime.Now  )  Then 'Fecha actual
					Dim k As Int
					For k=0 To TempValores.Size -1
						mdates.Add (currentFecha)' esto se cambia de posicion para el nuevo dia en los historicos
						Valores.Add (TempValores.Get (k))
					Next
					PintaHistorico
					Return
				Else
					File.WriteList ( File.DirRootExternal  & "/HT", ValoresComunes.Central .Nombre &   currentFecha & ".txt",TempValores)
					TestFiles
					Return
				End If
			
			
				'If currentFecha = FechaFin Then
				'	PintaHistorico
				'	Return
				'Else

				   
					
				
				'	currentFecha=DateTime.Add(currentFecha ,0,0,1)
				'	NumeroEnvio=1

				'	LecturaDatos(NumeroEnvio)
				'	Return
				'End If
				
				
			End If
			
			
			Do While C<  Packet.Length
				
				
				Dim B1 As Short
				Dim B2 As Short
				
				
				B1= Bit.And (Packet.data (C) , 0xff)
				If B1<255 Then B1=B1-1
				

				C=C+1
				
				B2=Bit.And (Packet.data (C) , 0xff)
				If B2<255 Then B2=B2-1
				C=C+1
				B1= Bit.Or(Bit.ShiftLeft ( B2,8),B1)
				
				Val.Add (B1)
				
				'Data(i) = Bit.AND(Value,0xFF)
				'DaVta(i+1) = Bit.AND(Bit.ShiftRight(Value,8),0xFF)
				
				S=S+1
				
			
				
			Loop
			'mdates.Add (currentFecha)' esto se cambia de posicion para el nuevo dia en los historicos
			TempValores.Add (Val)
			NumeroEnvio=NumeroEnvio+1
			LecturaDatos(NumeroEnvio)
		
		Else If  msg.Contains ("NOFOUND") Then
			Msgbox("NO CONFIGURADO","")
			Activity.Finish
		End If

	
	Catch
	    
	End Try


End Sub
Sub GetColor(Number As Int ) As Int
	Select Case Number
	
		Case 0
			Return Colors.Blue
		Case 1
			Return Colors.Magenta
		Case 2
			Return Colors.Green
		Case 3
			Return Colors.Red
		Case 4
			Return Colors.Yellow
		Case 5
			Return Colors.Cyan
		Case 6
			Return Colors.White
	
	End Select
	Return Colors.Blue
