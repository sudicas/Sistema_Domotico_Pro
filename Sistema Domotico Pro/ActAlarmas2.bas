Type=Activity
Version=7.01
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: True
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim UDPSocket1 As UDPSocket 
	Dim pw As PhoneWakeState 'Controlar suspension pantalla
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim Timetable As List 
	Dim CircuitoMostrado As Byte 
	 
	
	'Dim CmdGuardar As Button
	Dim CmdSeleccion As Button
	'Dim CmdNew As Button
	Dim ListView1 As ListView 
	
	Dim TramaEnviada() As Byte
	Dim ImgCargando As ImageView 
	Dim AniCargando As  Animation
	
	Dim PaqueteEnviado As Boolean
	
	Dim dlg1 As ColorPickerDialog 
	Dim dlg2 As ColorPickerDialog 
	
	Dim listCir As ListView 
	Dim ListCon As ListView
	Dim listEscenas As ListView 
	Dim listFunciones As ListView
	Dim pnl As Panel
	Dim TabConfig As TabHost
	
		'Controles dialogo barra
	Dim CmdBarOk As Button 
	Dim CmdBarCancel As Button
	Dim PanBarra As Panel
	Dim Barra As mbVSeekBar
	'Dim BarCir As Byte 
	
	Dim hr As Horario

	Dim EnableMenu  As Boolean
End Sub
Sub Activity_Create(FirstTime As Boolean)

		If ValoresComunes.Centrales .IsInitialized = False  Then Return
		Activity.RemoveAllViews
		
		Activity.LoadLayout ("FrmHorario")
		
		Activity.AddMenuItem(ValoresComunes.GetLanString ("reg185"), "mnuNew")
		Activity.AddMenuItem(ValoresComunes.GetLanString ("reg128"), "mnuSave")
	
		ListView1.Height = PerYToCurrent(89)
		ListView1.Width =Activity.Width 
		
		
		CmdSeleccion.Top=PerYToCurrent(90)
		CmdSeleccion.Height =PerYToCurrent(10)
		CmdSeleccion.Width =Activity.Width
		
'		CmdGuardar.Top=PerYToCurrent(85)
'		CmdGuardar.Height =PerYToCurrent(15)
'		CmdGuardar.Width =PerXToCurrent(50)
'		CmdGuardar.Text = ValoresComunes.GetLanString ("reg128")
'		
'		CmdNew.Top=PerYToCurrent(85)
'		CmdNew.Height =PerYToCurrent(15)
'		CmdNew.Width =PerXToCurrent(50)
'		CmdNew.Left =PerXToCurrent(50)
'		CmdNew.Text = ValoresComunes.GetLanString ("reg185")
		
		'Configuracion tamaño listas
	Dim h As Int
	h=75dip
	
	
	ListView1.TwoLinesAndBitmap .ItemHeight =h
	ListView1.TwoLinesAndBitmap.ImageView.Width=h
	ListView1.TwoLinesAndBitmap.ImageView.height=h
	
	ListView1.TwoLinesAndBitmap.Label .Left =h + h/9
	ListView1.TwoLinesAndBitmap.Label.height  =h/1.8
	ListView1.TwoLinesAndBitmap.Label.Gravity = Bit.Or(Gravity.LEFT, Gravity.BOTTOM)
	
	ListView1.TwoLinesAndBitmap.SecondLabel .Left =ListView1.TwoLinesAndBitmap.Label .Left
	ListView1.TwoLinesAndBitmap.SecondLabel.height   =h-ListView1.TwoLinesAndBitmap.Label.height
	ListView1.TwoLinesAndBitmap.SecondLabel.top   = ListView1.TwoLinesAndBitmap.SecondLabel.height
	ListView1.TwoLinesAndBitmap.SecondLabel.Gravity = Bit.Or(Gravity.LEFT, Gravity.CENTER_VERTICAL)

	ValoresComunes.LoadPaleta(dlg1  ,dlg2)
	
	IniBarDialog
End Sub

Sub Activity_Resume
	If ValoresComunes.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then		
		If Main.MyLan.IsInitialized = False Then Main.MyLan.Initialize( 0, "")
		
		If ImgCargando.IsInitialized = False Then
			ImgCargando.Initialize ("ImgCargando")
			ImgCargando.Bitmap = ValoresComunes.Cargando 
			ImgCargando.Gravity = Gravity.FILL 
			Activity.AddView (ImgCargando,  PerXToCurrent(40)	, PerYToCurrent(35),PerXToCurrent(20) ,PerXToCurrent(20) )
		End If
		
		
		If UDPSocket1.IsInitialized = False Then  ValoresComunes.IniUDP(UDPSocket1) 
		Timetable.Initialize 
		CircuitoMostrado=100
		
		Activity.Title = ValoresComunes.GetLanString ("TC")  
		
		EnableMenu =False

		CmdSeleccion.Enabled =False
				
		
		
		AniCargando.InitializeRotateCenter( "AniCargando", 0, 180, ImgCargando)
		AniCargando.Duration = 1000
	    AniCargando.RepeatCount =5
	    AniCargando.RepeatMode = AniCargando.REPEAT_REVERSE  
		
		
		listCir.Initialize ("listNewHor")
		ListCon.Initialize ("listNewHor")
		listEscenas.Initialize ("listNewHor")
		listFunciones.Initialize ("listNewHor")
		TabConfig.Initialize ("TabConfig")	
		pnl.Initialize("pnl")
		pnl.Color= Colors.Black 
	
		TabConfig.AddTabWithIcon2 ("", ValoresComunes.Bombillades, ValoresComunes.Bombillades, listCir) 
		TabConfig.AddTabWithIcon2 ("", ValoresComunes.scene2, ValoresComunes.scene2, listEscenas) 
		TabConfig.AddTabWithIcon2 ("", ValoresComunes.CheckOn , ValoresComunes.CheckOn, ListCon) 
		TabConfig.AddTabWithIcon2 ("", ValoresComunes.Funciones  , ValoresComunes.Funciones, listFunciones) 
		
		pnl.AddView(TabConfig , 0,  0,   PerXToCurrent(70) , PerYToCurrent(20))
		
		Dim I As Int 
		For I =0 To 9
			
			If ValoresComunes.Scenes (I).Nombre<>"" Then 	listEscenas.AddTwoLinesAndBitmap2 (ValoresComunes.Scenes (I).Nombre ,ValoresComunes.Scenes(I).Descripcion ,ValoresComunes.Scene2 ,I+30)
			If ValoresComunes.Condicionados (I).Nombre <> "" Then	ListCon.AddTwoLinesAndBitmap2 (ValoresComunes.Condicionados (I).Nombre ,ValoresComunes.Condicionados (I).Descripcion  ,ValoresComunes.CheckOff,I+40)	
			If ValoresComunes.Functions (I).Nombre<>"" Then 	listFunciones.AddTwoLinesAndBitmap2 (ValoresComunes.Functions (I).Nombre ,ValoresComunes.Functions(I).Descripcion ,ValoresComunes.Funciones ,I+50)
						
		Next
		
		For I =0 To 29
			If ValoresComunes.Circuitos  (I).Nombre <> "" And ValoresComunes.Circuitos (I).Rango > 0 And ValoresComunes.Circuitos (I).Rango <> 33  Then	listCir.AddTwoLinesAndBitmap2 (ValoresComunes.circuitos (I).Nombre ,ValoresComunes.Circuitos (I).Descripcion  ,ValoresComunes.IconoCircuito (I,1),I)
		Next
		
		
		Dim data() As Byte
		Dim trama As String 
		If ValoresComunes.central.ConexionSegura Then
			trama = "RETRIGGER" & ValoresComunes.Central.Password   
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
	If UserClosed Then
		'Timer1.Enabled =False
		UDPSocket1.Close 
		pw.ReleaseKeepAlive 
		Activity.Finish 
	End If
End Sub
Sub AniCargando_AnimationEnd
	
   If PaqueteEnviado = True Then 
		AniCargando.Start (ImgCargando)
		SendTrama(TramaEnviada)
	End If
End Sub

Sub GetRGBcolor() As Int
	Dim res As Int
	res=DialogResponse.CANCEL 
	
	Do While res=DialogResponse.CANCEL
		dlg1.RGB=8388736
		res = dlg1.Show(ValoresComunes.GetLanString ("reg97"),  ValoresComunes.GetLanString ("Ok"),  ValoresComunes.GetLanString ("reg94"), ValoresComunes.GetLanString ("Cancel"), Null)
		If res=DialogResponse.POSITIVE Then
			Dim v As Int
			v= dlg1.RGB 
			Dim b As Int
			For b=0 To 14
				If v = dlg1.GetPaletteAt(b) Then	Return b+1								
			Next
			
		Else If res= DialogResponse.CANCEL Then
			dlg2.RGB=14423100
			res = dlg2.Show(ValoresComunes.GetLanString ("reg97"),  ValoresComunes.GetLanString ("Ok"),   ValoresComunes.GetLanString ("reg94"), ValoresComunes.GetLanString ("Cancel"), Null)
			
			If res=DialogResponse.POSITIVE Then
				Dim v As Int
				v= dlg2.RGB 
				Dim b As Int
				For b=0 To 14
					If v = dlg2.GetPaletteAt(b) Then	Return b+16								
				Next
								
			Else If  res <> DialogResponse.CANCEL Then
				Return -1
			End If
		
			
		Else
			Return -1
		End If
	Loop
	Return -1
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
	Dim Pos As Int
	
	'LanString.Put ("reg89","ALL CIRCUITS")
	'LanString.Put ("reg151","Circuit")
	'LanString.Put ("reg152","Scene")
	'LanString.Put ("reg131","Conditioned")
	'LanString.Put ("reg186","Functions")
	
	lst.Add (ValoresComunes.GetLanStringDefault  ("reg89","ALL CIRCUITS"))'ALL CIRCUITS
	lst.Add (ValoresComunes.GetLanStringDefault ("reg151","Circuit"))'Circuit
	lst.Add (ValoresComunes.GetLanStringDefault ("reg152","Scene"))'Scene
	lst.Add (ValoresComunes.GetLanStringDefault ("reg131","Conditioned"))'Conditioned
	lst.Add (ValoresComunes.LanString .GetDefault("reg186","Functions"))'Functions
	Pos =InputList(lst,ValoresComunes.GetLanString ("reg75"), Pos)
	
	If Pos <0 Then Return
	
	lst.Clear 
	
	Select Case Pos
		Case 0
			CircuitoMostrado=100
			CmdSeleccion.Text = ValoresComunes.GetLanString ("reg89")
			RefrescaPantalla
			Return
		Case 1
			For I =0 To 29'circuitos
				If ValoresComunes.Circuitos (I).Nombre <> "" And ValoresComunes.Circuitos (I).Rango >0 Then
					lst.Add (ValoresComunes.Circuitos (I).Nombre)
					lstVal.Add (I)
				End If
			Next
		Case 2'escena
			For I =0 To 9
				If ValoresComunes.Scenes  (I).Nombre <> "" Then
					lst.Add (ValoresComunes.Scenes  (I).Nombre)
					lstVal.Add (I+30)
				End If
			Next	
		Case 3'condicionado
			For I =0 To 9
				If ValoresComunes.Condicionados   (I).Nombre <> "" Then
					lst.Add (ValoresComunes.Condicionados  (I).Nombre)
					lstVal.Add (I+40)
				End If
			Next
		Case 4'funciones
			For I =0 To 9
				If ValoresComunes.Functions(I).Nombre <> "" Then
					lst.Add (ValoresComunes.Functions   (I).Nombre)
					lstVal.Add (I+50)
				End If
			Next
	
	End Select


	
	Pos =InputList(lst,ValoresComunes.GetLanString ("reg75"), 0)
	
	If Pos <0 Then Return
	CircuitoMostrado= lstVal.Get (Pos)
	CmdSeleccion.Text = lst.Get (Pos)
	
	RefrescaPantalla
End Sub

Sub listNewHor_ItemClick (Position As Int, Value As Object)
	
	'Dim a As Int
	
	'Activity.RemoveViewAt (Activity.NumberOfViews-1) 
	pnl.RemoveView 
	EnableMenu=True
	
	Dim H As Horario 
	
	Dim Pos As Int
	If CircuitoMostrado < 60 Then  Pos= CircuitoMostrado
	'Pos =InputList(Lst,ValoresComunes.GetLanString ("reg75"), Pos)
	If Pos>=0 Then
		H.Circuito = Value
	Else
		Return
	End If
	
	Dim NewVal As Int
	NewVal=-1
	
	If H.Circuito <30 Then
		Select  ValoresComunes.Circuitos (H.Circuito).Rango 
		Case 1:	'Circuitos Alumbrado
			Dim Lst As List
			Lst.Initialize 
			Lst.Add (ValoresComunes.GetLanString ("reg71"))
			Lst.Add (ValoresComunes.GetLanString ("reg70"))
			NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), 0)
		Case 44:	'Circuitos 2
			Dim Lst As List
			Lst.Initialize 
			Lst.Add (ValoresComunes.GetLanString ("reg71"))		
			Lst.Add(ValoresComunes.GetLanString ("reg72"))
			Lst.Add(ValoresComunes.GetLanString ("reg73"))	
			NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), 0)
			
		Case 2,45:	'Circuitos 3 	
			
			Dim Lst As List
			Lst.Initialize 
			Lst.Add (ValoresComunes.GetLanString ("reg71"))		
			Lst.Add(ValoresComunes.GetLanString ("reg72"))
			Lst.Add(ValoresComunes.GetLanString ("reg73"))
			Lst.Add(ValoresComunes.GetLanString ("reg74"))

			NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), 0)
			
		'Case 7,8,13,4,19,24,39,15,25,43,51:	'Circuitos DIGITALES
		Case 1,3,7,8,13,19,24,39,15,25,43,51,57,58:	'Circuitos DIGITALES
			Dim Lst As List
			Lst.Initialize 
			Lst.Add (ValoresComunes.GetLanString ("reg71"))
			Lst.Add (ValoresComunes.GetLanString ("reg70"))
			NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), 0)		
			
			
		Case 4:	'Rgb
			Dim Lst As List
			Lst.Initialize 
			Lst.Add (ValoresComunes.GetLanString ("reg71"))		
			Lst.Add(ValoresComunes.GetLanString ("reg95"))
			Lst.Add(ValoresComunes.GetLanString ("reg96"))
			
			NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), 0)
			
			If NewVal>0 Then
				If NewVal=1 Then
					NewVal=199
				Else
					NewVal=GetRGBcolor				
				End If
			End If	
			'Msgbox(NewVal,"")
			If NewVal<0 Then Return	
			
		Case 5:	'Barra 0-100%
				hr=H
			Barra_On(Value)
			Return
			
			
		Case 53:	'set point 100
			Dim Dialogo As NumberDialog 
			Dialogo.Digits =3
						
			Dialogo.Number = 0
			
			Dim Result As Int
			Result = Dialogo.Show (ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
			
			If Result=  DialogResponse.POSITIVE  Then
				If Dialogo.Number< 101 Then
					NewVal =Dialogo.Number
				Else 
					Msgbox("Max 100","error")
					Return
				End If
			Else
				Return
			End If	
			
		Case 54:	'set point 200
			Dim Dialogo As NumberDialog 
			Dialogo.Digits =3
						
			Dialogo.Number = 0
			
			Dim Result As Int
			Result = Dialogo.Show (ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
			
			If Result=  DialogResponse.POSITIVE  Then
				If Dialogo.Number< 201 Then
					NewVal =Dialogo.Number
				Else 
					Msgbox("Max 200","error")
					Return
				End If
			Else
				Return
			End If	
		Case 55:	'set point 2000
			Dim Dialogo As NumberDialog 
			Dialogo.Digits =4
						
			Dialogo.Number = 0
			
			Dim Result As Int
			Result = Dialogo.Show (ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
			
			If Result=  DialogResponse.POSITIVE  Then
				If Dialogo.Number< 2001 Then
					NewVal =Dialogo.Number/10
				Else 
					Msgbox("Max 2000","error")
					Return
				End If
			Else
				Return
			End If			
		Case 56:	'set point 20000
			Dim Dialogo As NumberDialog 
			Dialogo.Digits =5
						
			Dialogo.Number = 0
			
			Dim Result As Int
			Result = Dialogo.Show (ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
			
			If Result=  DialogResponse.POSITIVE  Then
				If Dialogo.Number< 20001 Then
					NewVal =Dialogo.Number/100
				Else 
					Msgbox("Max 20000","error")
					Return
				End If
			Else
				Return
			End If		
		
		
		Case 29:	'Temp. Consigna
		
			Dim Dialogo As NumberDialog 
			Dialogo.Digits =3
			Dialogo.Number = 20
			
			Dim Result As Int
			Result = Dialogo.Show (ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
			
			If Result=  DialogResponse.POSITIVE  Then
			
				If Dialogo.Number< 241 Then
					NewVal =Dialogo.Number
				Else 
					Msgbox("Max 240","error")
					Return
				End If

				
			Else
				Return			
			End If
			
			
			
	Case 30:	'Home Temp. Consigna
		Dim Dialogo As NumberDialog 
		Dialogo.Digits =3
		Dialogo.Decimal =1 
					
		Dialogo.Number = 220
		
		Dim Result As Int
		Result = Dialogo.Show (ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		
		If Result=  DialogResponse.POSITIVE  Then
			
			If Dialogo.Number< 321 AND Dialogo.number> 149 Then
				NewVal =(Dialogo.Number-150)
			Else 
				Msgbox("Only 15º to 32º","error")
				Return
			End If
			
		Else
			Return
		End If
	Case 31:	'Temp. Consigna -
		Dim Dialogo As NumberDialog 
		Dialogo.Digits =4
					
		Dialogo.Number = -1
		Dialogo.ShowSign = True
		Dim Result As Int
		Result = Dialogo.Show (ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		
		If Result=  DialogResponse.POSITIVE  Then
			If Dialogo.Number < 1  And Dialogo.Number  > -241 Then
				NewVal =Dialogo.Number * -1
			Else 
				Msgbox("Max -240","error")
				Return
			End If
		Else
			Return
		End If
		Case 14,52:	'Temporizados 
			Dim Dialogo As NumberDialog 
			Dialogo.Digits =3
		
				
			Dialogo.Number = 0
			
			Dim Result As Int
			Result = Dialogo.Show (ValoresComunes.GetLanString ("reg84"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
			
			If Result=  DialogResponse.POSITIVE  Then
				If Dialogo.Number < 241 And Dialogo.Number >0 Then
					NewVal =Dialogo.Number
				Else
					Msgbox(ValoresComunes.GetLanString ("reg85"),ValoresComunes.GetLanString ("reg86"))
					Return
				End If
				
			End If
		Case 34:	'Persiana		
			hr=H
			Barra_On(Value)
			Return
		
		Case 35,36:	'toldo

			Dim Lst As List
			Lst.Initialize 
			Lst.Add (ValoresComunes.GetLanString ("reg80"))
			Lst.Add (ValoresComunes.GetLanString ("reg82"))
			NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), 0)
			If NewVal = 1 Then 
				NewVal=100
			Else			
				NewVal=0
			End If
		
		End Select
	Else
		Select  H.Circuito
		Case 30, 31,32,33,34,35,36,37,38,39:	'escena
			NewVal= H.Circuito - 29
			
		Case 40, 41,42,43,44,45,46,47,48,49:	'Condicionado
			Dim Lst As List
			Lst.Initialize 
			Lst.Add (ValoresComunes.GetLanString ("reg71"))
			Lst.Add (ValoresComunes.GetLanString ("reg70"))
			NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), 0)
			
			
		Case 50, 51,52,53,54,55,56,57,58,59:	'funciones usuario

			NewVal= H.Circuito - 49
			
			
		Case Else 
			Return
		End Select
	End If
	
	
	
	If NewVal < 0 Then Return
	H.Salida =NewVal
	FinalizaNewHorario( H )
	'Definimos valor de salida
	
	

End Sub

Sub FinalizaNewHorario( H As Horario )
	Dim Dialog As TimeDialog 
	Dim Resp As Int
	Dialog.Is24Hours =True
	

	
	Dialog.Hour =  DateTime.GetHour(DateTime.Now)
	Dialog.Minute =  DateTime.GetMinute ( DateTime.Now)
	
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
				If (Hcmp.Hora = H.Hora  And Hcmp.Minuto   >= H.Minuto)    Then
					
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
	
	
	EnableMenu =False

	CmdSeleccion.Enabled =False
	AniCargando.Start (ImgCargando)
	PaqueteEnviado =False
	
	TramaEnviada= data
	
	
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
			If Resultado=False Then Sleep (200)
		Loop
	End If
	

	If Resultado = False  Then
		ActMosaico.Conectado =False
		StartActivity(ActMosaico)
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
	
	Dim hor As String
	hor= H.Minuto 
	If hor.Length <2 Then
		hor= H.Hora & ":0" & hor
	Else
		hor= H.Hora & ":" & hor
	End If
	
	If H.Circuito < 30  Then
		'******************************************************
		'Alumbrado 
		'******************************************************
		If ValoresComunes.Circuitos (H.Circuito).Rango=29 Then	
						
			ListView1.AddTwoLinesAndBitmap2 (hor & " : "& H.Salida  & "ºC" ,ValoresComunes.Circuitos (H.Circuito).Nombre  ,ValoresComunes.Temperatura,p)
		
		Else If	 ValoresComunes.Circuitos (H.Circuito).Rango=30 Then	'Termostato Casa				
			Dim val1   As Double
			val1= (H.Salida +150)/10
			
			ListView1.AddTwoLinesAndBitmap2 (hor & " : "& val1  & "ºC" ,ValoresComunes.Circuitos (H.Circuito).Nombre  ,ValoresComunes.Temperatura,p)
		Else If	 ValoresComunes.Circuitos (H.Circuito).Rango=31 Then	'temperatura negativa			
			ListView1.AddTwoLinesAndBitmap2 (hor & " : - "& H.Salida  & "ºC" ,ValoresComunes.Circuitos (H.Circuito).Nombre  ,ValoresComunes.Temperatura,p)
		Else If	 ValoresComunes.Circuitos (H.Circuito).Rango=53 Or  ValoresComunes.Circuitos (H.Circuito).Rango=54 Then	'set point 100
			
			
			ListView1.AddTwoLinesAndBitmap2 (hor & " : "& H.Salida ,ValoresComunes.Circuitos (H.Circuito).Nombre ,ValoresComunes.IconoCircuito (H.Circuito,H.Salida),p)		
		Else If	 ValoresComunes.Circuitos (H.Circuito).Rango=55 Then	'set point 100
			
			
			ListView1.AddTwoLinesAndBitmap2 (hor & " : "& (H.Salida*10) ,ValoresComunes.Circuitos (H.Circuito).Nombre ,ValoresComunes.IconoCircuito (H.Circuito,H.Salida),p)		
		Else If	 ValoresComunes.Circuitos (H.Circuito).Rango=56 Then	'set point 100
			
			
			ListView1.AddTwoLinesAndBitmap2 (hor & " : "& (H.Salida*100) ,ValoresComunes.Circuitos (H.Circuito).Nombre ,ValoresComunes.IconoCircuito (H.Circuito,H.Salida),p)		
		
		Else
			ListView1.AddTwoLinesAndBitmap2 (hor,ValoresComunes.Circuitos (H.Circuito).Nombre ,ValoresComunes.IconoCircuito (H.Circuito,H.Salida),p)		
		End If
		Return		
	End If
	
	
	
	'******************************************************
	'Secenas
	'******************************************************
	If H.Circuito>29 And H.Circuito <40 Then
		ListView1.AddTwoLinesAndBitmap2 (hor,ValoresComunes.Scenes  (H.Circuito -  30).Nombre ,ValoresComunes.Home,p)
	End If
	'******************************************************
	'Condicionados
	'******************************************************
	If H.Circuito>39 And H.Circuito <50 Then
		If H.Salida=1 Then
			ListView1.AddTwoLinesAndBitmap2 (hor,ValoresComunes.Condicionados  (H.Circuito -40).Nombre ,ValoresComunes.CheckOn ,p)

		Else
			ListView1.AddTwoLinesAndBitmap2 (hor,ValoresComunes.Condicionados (H.Circuito -40).Nombre ,ValoresComunes.CheckOff ,p)
		End If
		Return
	End If
	
	'******************************************************
	'Funciones comunes
	'******************************************************
	If H.Circuito>49 And H.Circuito <60 Then
		ListView1.AddTwoLinesAndBitmap2 (hor,ValoresComunes.Functions  (H.Circuito -  50).Nombre ,ValoresComunes.Funciones ,p)
	End If
End Sub
Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
		Dim msg As String
	    msg = BytesToString(Packet.data, Packet.Offset, Packet.Length, "UTF8")
		
			
		If msg.Contains ("TIGR") Then
			Timetable.Clear 
			Dim Valores(48) As Int 
			Dim p As Int
			For p =4 To Packet.Length -1
				Valores(p-4)=Packet.data (p)-1
				If Valores(p-4) < 0 Then
					Valores(p-4)=Valores(p-4) +256
				End If
			Next
			
			p=0
			
			Do While p < 45
				If Valores(p)<24 And Valores(p)>=0 And Valores(p+1)>=0 And Valores(p+1)<60 And Valores(p+2)>= 0 And Valores(p+2)<=60 And Valores(p+3)>=0 And Valores(p+3) <=240 Then
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
			
				'ToastMessageShow(msg,True)
			End If
		End If
		PaqueteEnviado = False
		EnableMenu =True

		CmdSeleccion.Enabled =True
		AniCargando.Stop(ImgCargando)
		ImgCargando.Visible =False 
	
	Catch
	    
	End Try
    
End Sub 

Sub Barra_On(Pos As Byte)
	ListView1.Enabled =False
	EnableMenu =False
	CmdSeleccion.Enabled =False
	Barra.Value = 50
	Activity.AddView (PanBarra,0,0, Activity.Width ,Activity.Height )
	
End Sub
Sub IniBarDialog(  )
	CmdBarOk.Initialize("CmdBarOk")
	CmdBarCancel.Initialize ("CmdBarCancel")
	
	
	CmdBarOk.Text =ValoresComunes.GetLanString ("reg83")
	CmdBarCancel.Text =ValoresComunes.GetLanString ("Cancel")
	PanBarra.Initialize("PanBarra")
	PanBarra.Color =  Colors.Black 
	Barra.Initialize( PanBarra,  Me,"bar_Click",  50%x - 36dip,  5%y,  72dip,75%y,33dip,Colors.DarkGray,Colors.RGB(0x00,0x99,0xcc), True,70,100,True)
	Barra.CustomizeText(Colors.RGB(0x33,0xb5,0xe5),22,Typeface.DEFAULT_BOLD)
	Barra.stepValue =1
	
	PanBarra.AddView (CmdBarOk, 5%x,85%y,44%x,14%y)
	PanBarra.AddView (CmdBarCancel,  51%x ,85%y, 44%x,14%y)

End Sub
Sub CmdBarOk_Click
	ListView1.Enabled =True
	EnableMenu =True
	CmdSeleccion.Enabled =True
	hr.Salida =Barra.currentValue
	PanBarra.RemoveView 
	
	FinalizaNewHorario( hr)	
End Sub
Sub CmdBarCancel_Click
	ListView1.Enabled =True
	EnableMenu =True
	CmdSeleccion.Enabled =True

	PanBarra.RemoveView
End Sub
Sub mnuNew_Click
	If EnableMenu Then
		If Timetable.Size >11 Then
			Msgbox(ValoresComunes.GetLanString ("reg91"),ValoresComunes.GetLanString ("reg69"))
			Return
		End If
	
		Activity.AddView (pnl, 1%x, 0%y,  98%x, 100%y) ' sizing relative to the screen size is probably best
		TabConfig.Width = pnl.Width
		TabConfig.Height =pnl.Height
	
		'TabConfig.CurrentTab = 1
		TabConfig.CurrentTab = 0
		EnableMenu=False
	End If
End Sub
Sub mnuSave_Click
	If EnableMenu Then
		Dim Rsp As Int
	
		Rsp = Msgbox2(ValoresComunes.GetLanString ("reg76"),ValoresComunes.GetLanString ("reg77"),ValoresComunes.GetLanString ("Y"),ValoresComunes.GetLanString ("N"),"",Null)

		If Rsp = DialogResponse.POSITIVE Then
			'WTGR
			Dim LongTrama As Int
			If ValoresComunes.central.ConexionSegura Then
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
			If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
			SendTrama(data)
		End If

	End If
End Sub