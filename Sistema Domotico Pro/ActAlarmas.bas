Type=Activity
Version=4.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

'Activity module
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim UDPSocket1 As UDPSocket 
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim Timetable As List 
	Dim CircuitoMostrado As Byte 
	 
	
	Dim CmdGuardar As Button
	Dim CmdSeleccion As Button
	Dim CmdNew As Button
	Dim ListView1 As ListView 
	
	Dim TramaEnviada() As Byte
	Dim ImgCargando As ImageView 
	Dim AniCargando As  Animation
	
	Dim PaqueteEnviado As Boolean
End Sub
Sub Activity_Create(FirstTime As Boolean)

		If ValoresComunes.Centrales .IsInitialized = False  Then Return
		Activity.LoadLayout ("FrmHorario")
		ListView1.Height = PerYToCurrent(83)
		ListView1.Width =Activity.Width 
		
		
		CmdSeleccion.Top=PerYToCurrent(80)
		CmdSeleccion.Height =PerYToCurrent(10)
		CmdSeleccion.Width =Activity.Width
		
		CmdGuardar.Top=PerYToCurrent(90)
		CmdGuardar.Height =PerYToCurrent(10)
		CmdGuardar.Width =PerXToCurrent(50)
		CmdGuardar.Text = ValoresComunes.GetLanString ("reg128")
		
		CmdNew.Top=PerYToCurrent(90)
		CmdNew.Height =PerYToCurrent(10)
		CmdNew.Width =PerXToCurrent(50)
		CmdNew.Left =PerXToCurrent(50)
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

End Sub

Sub Activity_Resume
	If ValoresComunes.Centrales .IsInitialized = True Then		
		If Main.MyLan.IsInitialized = False Then Main.MyLan.Initialize( 0, "")
		
		If ImgCargando.IsInitialized = False Then
			ImgCargando.Initialize ("ImgCargando")
			ImgCargando.Bitmap = ValoresComunes.Cargando 
			Activity.AddView (ImgCargando,  PerXToCurrent(40)	, PerYToCurrent(35),PerXToCurrent(20) ,PerXToCurrent(20) )
		End If
		
		
		If UDPSocket1.IsInitialized = False Then  ValoresComunes.IniUDP(UDPSocket1) 
		Timetable.Initialize 
		CircuitoMostrado=100
		
		Activity.Title =ValoresComunes.GetLanString ("TC")
		
		CmdGuardar.Enabled =False
		CmdNew.Enabled =False
		CmdSeleccion.Enabled =False
				
		
		
		AniCargando.InitializeRotateCenter( "AniCargando", 0, 180, ImgCargando)
		AniCargando.Duration = 1000
	    AniCargando.RepeatCount =5
	    AniCargando.RepeatMode = AniCargando.REPEAT_REVERSE  
		
		
		Dim data() As Byte
		Dim trama As String 
		If ValoresComunes.ConexionSegura Then
			trama = "RETRIGGER" & ValoresComunes.Password 
		Else
			trama = "RETRIGGER"
		End If
		
		data =trama.GetBytes ("UTF8")
		SendTrama(data)	
	Else
		Activity.Finish 
		StartActivity(Main)
	End If


End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
Sub AniCargando_AnimationEnd
	
   If PaqueteEnviado = True Then 
		AniCargando.Start (ImgCargando)
		SendTrama(TramaEnviada)
	End If
End Sub



Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim R As Int
	R=Msgbox2(ValoresComunes.GetLanString ("reg65"),ValoresComunes.GetLanString ("reg66"),ValoresComunes.GetLanString ("Y"),ValoresComunes.GetLanString ("N"),"",Null)
	If R = DialogResponse.POSITIVE Then 
		Timetable.RemoveAt (Value)
		RefrescaPantalla
	End If	
End Sub
Sub CmdSeleccion_Click
	Dim lst As List
	Dim lstVal As List
	lstVal.Initialize 
	lst.Initialize 
	For I =0 To 29
		If ValoresComunes.Circuitos (I).Nombre <> "" Then
			lst.Add (ValoresComunes.Circuitos (I).Nombre)
			lstVal.Add (I)
		End If
	Next	
	For I =0 To 9
		If ValoresComunes.Scenes  (I).Nombre <> "" Then
			lst.Add (ValoresComunes.Scenes  (I).Nombre)
			lstVal.Add (I+30)
		End If
	Next	
	For I =0 To 9
		If ValoresComunes.Condicionados   (I).Nombre <> "" Then
			lst.Add (ValoresComunes.Condicionados  (I).Nombre)
			lstVal.Add (I+40)
		End If
	Next
	lst.Add (ValoresComunes.GetLanString ("reg89"))
	Dim Pos As Int
	If CircuitoMostrado < 50 Then  Pos= CircuitoMostrado
	Pos =InputList(lst,ValoresComunes.GetLanString ("reg75"), Pos)
	
	If Pos <0 Then Return
	If Pos = lst.Size -1 Then 
		CircuitoMostrado=100
		CmdSeleccion.Text = ValoresComunes.GetLanString ("reg89")
	Else
		CircuitoMostrado= lstVal.Get (Pos)
		CmdSeleccion.Text = lst.Get (Pos)
	End If
	
	RefrescaPantalla
End Sub
Sub CmdGuardar_Click

	Dim Rsp As Int
	
	Rsp = Msgbox2(ValoresComunes.GetLanString ("reg76"),ValoresComunes.GetLanString ("reg77"),ValoresComunes.GetLanString ("Y"),ValoresComunes.GetLanString ("N"),"",Null)

	If Rsp = DialogResponse.POSITIVE Then
	'WTGR
		Dim LongTrama As Int 
		If ValoresComunes.ConexionSegura Then
			LongTrama= 60
		Else
			LongTrama= 52
		End If
		Dim data(LongTrama) As Byte
		data(0)=87
		data(1)=84
		data(2)=71
		data(3)=82
	
		Dim c,pos As Int
		pos=4
		For c =0 To 11
			If c < Timetable.Size Then
				Dim H As Horario 
				H= Timetable.Get (c)
				data(pos)= H.Hora
				data(pos+1)= H.Minuto 
				data(pos+2)= H.Circuito 
				data(pos+3)= H.Salida 
				
			Else
				data(pos)= 66
				data(pos+1)= 66
				data(pos+2)= 66
				data(pos+3)= 66
			End If
			pos = pos + 4
		Next
		If ValoresComunes.ConexionSegura Then ValoresComunes.CompletarTrama (data)
		SendTrama(data)
	End If


	
End Sub


Sub CmdNew_Click
 
	If Timetable.Size >11 Then
		Msgbox(ValoresComunes.GetLanString ("reg91"),ValoresComunes.GetLanString ("reg69"))
		Return
	End If
	Dim Dialog As TimeDialog 
	Dim H As Horario 
	
	Dim Lst As List
	Dim LstValores As List 
	Lst.Initialize 
	LstValores.Initialize 
	
	
	For I =0 To 29
		If ValoresComunes.Circuitos (I).Nombre <> "" Then
			Lst.Add   (ValoresComunes.Circuitos (I).Nombre)
			LstValores.Add (I)
		End If
	Next	
	For I =0 To 9
		If ValoresComunes.Scenes  (I).Nombre <> "" Then
			Lst.Add   (ValoresComunes.Scenes (I).Nombre)
			LstValores.Add (30+I)
		End If
	Next
	
	For I =0 To 9
		If ValoresComunes.Condicionados   (I).Nombre <> "" Then
			Lst.Add   (ValoresComunes.Condicionados (I).Nombre)
			LstValores.Add (40+I)
		End If
	Next
	Dim Pos As Int
	If CircuitoMostrado < 50 Then  Pos= CircuitoMostrado
	Pos =InputList(Lst,ValoresComunes.GetLanString ("reg75"), Pos)
	If Pos>=0 Then
		H.Circuito = LstValores.Get (Pos)
	Else
		Return
	End If
	
	
	Dim NewVal As Int

	Select  H.Circuito
	Case 0,1,2,3,4,5:	'Circuitos Alumbrado
		If  ValoresComunes.Circuitos (H.Circuito).Rango =1 Then
			Dim Lst As List
			Lst.Initialize 
			Lst.Add (ValoresComunes.GetLanString ("reg71"))
			Lst.Add (ValoresComunes.GetLanString ("reg70"))
			NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), 0)
		Else
			Dim Lst As List
			Lst.Initialize 
			Lst.Add (ValoresComunes.GetLanString ("reg71"))
			Dim r As Int
			For  r =1 To ValoresComunes.Circuitos (H.Circuito).Rango
				Lst.Add(ValoresComunes.GetLanString ("reg70") & " " & r & "/" & ValoresComunes.Circuitos (H.Circuito).Rango)
			Next

			NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), 0)
		End If
	Case 6, 7,8,9,10,11,12,13:	'Enchufes y riego
		Dim Lst As List
		Lst.Initialize
		Select ValoresComunes.Circuitos (H.Circuito).Rango 
		
			Case 1:	'Enchufes				 
				Lst.Add (ValoresComunes.GetLanString ("reg71"))
				Lst.Add (ValoresComunes.GetLanString ("reg70"))
				
			Case 2: 
				Lst.Add (ValoresComunes.GetLanString ("reg87"))
				Lst.Add (ValoresComunes.GetLanString ("reg88"))
		End Select
		NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), 0)

	Case 14, 15,16:	'A.Acondicionados
		Dim Lst As List
		Lst.Initialize 
		Lst.Add (ValoresComunes.GetLanString ("reg71"))
		Lst.Add (ValoresComunes.GetLanString ("reg70"))
		NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), 0)

	Case 17, 18,19:	'Calefaccion
		Dim Lst As List
		Lst.Initialize 
		Lst.Add (ValoresComunes.GetLanString ("reg71"))
		Lst.Add (ValoresComunes.GetLanString ("reg70"))
		NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), 0)
	
	Case 20, 21,22:	'Temp. Consigna
		Dim Dialogo As NumberDialog 
		Dialogo.Digits =2					
		Dialogo.Number = 20
		
		Dim Result As Int
		Result = Dialogo.Show (ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		
		If Result=  DialogResponse.POSITIVE  Then
			NewVal =Dialogo.Number			
		Else
			Return			
		End If
	Case 23, 24,25,26,27,28,29:	'Persiana
		If  ValoresComunes.Circuitos (H.Circuito ).Rango =1 Then
			Dim Lst As List
			Lst.Initialize 
			Lst.Add (ValoresComunes.GetLanString ("reg82"))
			Lst.Add (ValoresComunes.GetLanString ("reg80"))
			NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), 0)
			If NewVal = 1 Then NewVal=100
		Else
			Dim cd As CustomDialog
			Dim pnl As Panel
			Dim Barra As SeekBar 
			Dim ret As Int
			pnl.Initialize("pnl")
			Barra.Initialize (Barra)	
			
			Barra.Max =100
			Barra.Value =50
			pnl.AddView(Barra , 0, PerYToCurrent(10),  PerXToCurrent(70) , PerYToCurrent(20))

			cd.AddView(pnl, 5%x, 0%y, 77%x, 70%y) ' sizing relative to the screen size is probably best
			ret = cd.Show(ValoresComunes.GetLanString ("reg93"), ValoresComunes.GetLanString ("reg83"), ValoresComunes.GetLanString ("Cancel"), "", Null)		
			If ret = DialogResponse.POSITIVE Then
				NewVal= Barra.Value 
			Else
				Return	
			End If
		End If	
		Case 30, 31,32,33,34,35,36,37,38,39:	'escena
			NewVal= H.Circuito - 29
		
		Case 40, 41,42,43,44,45,46,47,48,49:	'Condicionado
			Dim Lst As List
			Lst.Initialize 
			Lst.Add (ValoresComunes.GetLanString ("reg71"))
			Lst.Add (ValoresComunes.GetLanString ("reg70"))
			NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), 0)
	End Select
	If NewVal < 0 Then Return
	H.Salida =NewVal
	
	'Definimos valor de salida
	
	
	Dim Resp As Int
	Dialog.Is24Hours =True
	Resp= Dialog.Show (ValoresComunes.GetLanString ("reg79"),"", ValoresComunes.GetLanString ("Ok"),ValoresComunes.GetLanString ("Cancel"),"",Null   )
	If Resp = DialogResponse.POSITIVE Then
		H.Hora = Dialog.Hour 
		H.Minuto = Dialog.Minute 
		
		Dim p As Int
		If Timetable.Size =0 Then 
			Timetable.Add (H)
		Else
			For p=0 To Timetable.Size -1
				Dim Hcmp As Horario 
				Hcmp = Timetable.Get (p)
				If (Hcmp.Hora > H.Hora  )    Then
					
					Timetable.InsertAt   (p,H)
					RefrescaPantalla
					Return
				End If	
				If (Hcmp.Hora = H.Hora  AND Hcmp.Minuto   >= H.Minuto)    Then
					
					Timetable.InsertAt   (p,H)
					RefrescaPantalla
					Return
				End If	
			Next
			Timetable.Add (H )
		End If
		RefrescaPantalla
	End If	
End Sub
Sub SendTrama(data() As Byte )
	Dim Resultado As Boolean 
	Dim Reintento As Int
	
	
	CmdGuardar.Enabled =False
	CmdNew.Enabled =False
	CmdSeleccion.Enabled =False
	AniCargando.Start (ImgCargando)
	PaqueteEnviado =False
	
	TramaEnviada= data
	
	
	Do 	While Resultado= False AND Reintento < 40
		Dim ServerSocket1 As ServerSocket 
		Dim MyIp As String
		If Main.ConexionExterna Then
			MyIp=ServerSocket1.GetMyIP
		Else
			MyIp=ServerSocket1.GetMyWifiIP 
		End If
		If MyIp  <> "127.0.0.1" Then	
			Resultado = True
		Else
			Reintento = Reintento +1 
			ValoresComunes.Delay (200)
		End If

	Loop
	If Resultado = True Then
		Resultado =False
		Reintento =0
		Do 	While Resultado= False AND Reintento < 40
			Resultado = SendingTrama(data)
			Reintento = Reintento +1 
			If Resultado=False Then ValoresComunes.Delay (200)
		Loop
	End If
	

	If Resultado = False  Then
		Main.ConexionEstablecida = False
		StartActivity(Main)
	Else
		PaqueteEnviado=True
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
Sub RefrescaPantalla
	ListView1.Clear 
	Dim p As Int
	For p =0 To Timetable.Size -1
		Dim H As Horario 
		H= Timetable.Get (p)
		If CircuitoMostrado>59 Then
				AddCirToScr(p,H)
		Else
			If H.Circuito = CircuitoMostrado Then AddCirToScr(p,H)
		End If
	Next	
End Sub
Sub AddCirToScr(p As Int , H As Horario)
	
	Dim Hr As String
	Hr= H.Minuto 
	If Hr.Length <2 Then
		Hr= H.Hora & ":0" & Hr
	Else
		Hr= H.Hora & ":" & Hr
	End If 
	
	'******************************************************
	'Alumbrado
	'******************************************************
	If H.Circuito < 6 Then	
		If H.Salida =0 Then
			ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Circuitos (H.Circuito).Nombre ,ValoresComunes.BombillaOff,p)

		Else
			ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Circuitos (H.Circuito).Nombre ,ValoresComunes.BombillaOn,p)
		End If
	End If
	'******************************************************
	'Salidas digitales
	'******************************************************
	If H.Circuito>5 AND H.Circuito < 12 Then
		Select ValoresComunes.Circuitos (H.Circuito).Rango 
			Case 1:	'Enchufes
				If H.Salida =0 Then
					ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Circuitos (H.Circuito).Nombre ,ValoresComunes.EnchufeOff,p)

				Else
					ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Circuitos (H.Circuito).Nombre ,ValoresComunes.EnchufeOn,p)
				End If
			Case 2	'Doors
			
				If H.Salida =0 Then
					ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Circuitos (H.Circuito).Nombre ,ValoresComunes.PuertaOff,p)

				Else
					ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Circuitos (H.Circuito).Nombre ,ValoresComunes.PuertaOn,p)
				End If
		End Select
	
	End If
	'******************************************************
	'Riego
	'******************************************************
	If H.Circuito=12 OR H.Circuito =13 Then	
		If H.Salida=0 Then
			ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Circuitos (H.Circuito).Nombre ,ValoresComunes.RiegoOff,p)

		Else
			ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Circuitos (H.Circuito).Nombre ,ValoresComunes.RiegoOn,p)
		End If
	End If
		'******************************************************
	'A.Acondicionado
	'******************************************************
	If H.Circuito=14 OR H.Circuito =15 OR H.Circuito =16   Then	
		If H.Salida=0 Then
			ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Circuitos (H.Circuito).Nombre ,ValoresComunes.AcondicionadoOff,p)

		Else
			ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Circuitos (H.Circuito).Nombre ,ValoresComunes.AcondicionadoOn,p)
		End If
	End If
	'******************************************************
	'Calefaccion
	'******************************************************
	If H.Circuito=17 OR H.Circuito =18 OR H.Circuito =19 Then	
		If H.Salida=0 Then
			ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Circuitos (H.Circuito).Nombre ,ValoresComunes.CalefaccionOff,p)

		Else
			ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Circuitos (H.Circuito).Nombre ,ValoresComunes.CalefaccionOn,p)
		End If
	End If
	'******************************************************
	'Temperatura Consiga
	'******************************************************
	If H.Circuito=20 OR H.Circuito=21 OR H.Circuito=22  Then	
					
			ListView1.AddTwoLinesAndBitmap2 (Hr& " - "& H.Salida  & "ºC" ,ValoresComunes.Circuitos (H.Circuito).Nombre  ,ValoresComunes.Temperatura,p)
	End If
	'******************************************************
	'Persianas
	'******************************************************
	If H.Circuito>22 AND H.Circuito <30 Then
		If  ValoresComunes.Circuitos (H.Circuito ).Rango =1 Then			
			If H.Salida =0 Then
				ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Circuitos (H.Circuito ).Nombre   ,ValoresComunes.Toldooff,p)
			Else
				ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Circuitos (H.Circuito ).Nombre  ,ValoresComunes.ToldoOn,p)
			End If	
		Else
			ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Circuitos (H.Circuito).Nombre& " - "& H.Salida & "%"   ,ValoresComunes.persiana,p)
		End If				
	End If
	'******************************************************
	'Secenas
	'******************************************************
	If H.Circuito>29 AND H.Circuito <40 Then
		ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Scenes  (H.Circuito -  30).Nombre ,ValoresComunes.Ambient,p)
	End If
	'******************************************************
	'Condicionados
	'******************************************************
	If H.Circuito>39 AND H.Circuito <50 Then
		If H.Salida=1 Then
			ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Condicionados  (H.Circuito -40).Nombre ,ValoresComunes.CheckOn ,p)

		Else
			ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Condicionados (H.Circuito -40).Nombre ,ValoresComunes.CheckOff ,p)
		End If
	End If
End Sub
Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
		Dim msg As String
	    msg = BytesToString(Packet.data, Packet.Offset, Packet.Length, "UTF8")
		
			
		If msg.Contains ("TIGR") Then
			Timetable.Clear 
			Dim Valores(48) As Byte 
			Dim p As Int
			For p =4 To Packet.Length -1
				Valores(p-4)=Packet.data (p)-1
			Next
			
			p=0
			
			Do While p < 45
				If Valores(p)<24 AND Valores(p)>=0 AND Valores(p+1)>=0 AND Valores(p+1)<60 AND Valores(p+2)>= 0 AND Valores(p+2)<=50 AND Valores(p+3)>=0 AND Valores(p+3) <=100 Then
					Dim Hor As Horario 
					Hor.Hora = Valores(p)
					Hor.minuto = Valores(p+1)
					Hor.Circuito =Valores(p+2)
					Hor.Salida = Valores(p+3)
					Timetable.Add (Hor)
				End If
				p=p+4
			Loop
			RefrescaPantalla

			
		Else
			If msg ="REPETIRMSG" Then
				SendTrama(TramaEnviada)
				Return
			Else
			
				ToastMessageShow(msg,True)
			End If
		End If
		PaqueteEnviado = False
		CmdGuardar.Enabled =True
		CmdNew.Enabled =True
		CmdSeleccion.Enabled =True
		AniCargando.Stop(ImgCargando)
		ImgCargando.Visible =False 
	
	Catch
	    
	End Try
    
End Sub 


