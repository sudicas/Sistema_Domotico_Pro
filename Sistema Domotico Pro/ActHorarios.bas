Type=Activity
Version=4.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region Module Attributes
	#FullScreen: False
	#IncludeTitle: True
#End Region


Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim TipoDia As Byte
	Dim UDPSocket1 As UDPSocket 
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	'Type Horario(Hora As Byte  , Minuto As Byte , Circuito As Byte , Salida As Byte )
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
	'Dim PaquetesPartidos As Boolean 
	
	Dim Valores(320) As Int 
	
	Dim dlg1 As ColorPickerDialog 
	Dim dlg2 As ColorPickerDialog 
	
	Dim listCir As ListView 
	Dim ListCon As ListView
	Dim listEscenas As ListView 
	Dim listFunciones As ListView
	Dim pnl As Panel
	Dim TabConfig As TabHost
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.RemoveAllViews 

	'UDPSocket1.Initialize("UDP", 0, 8000)
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	Activity.LoadLayout ("FrmHorario")
	ListView1.Height = PerYToCurrent(70)
	ListView1.Width =Activity.Width 
	
	
	CmdSeleccion.Top=PerYToCurrent(70)
	CmdSeleccion.Height =PerYToCurrent(15)
	CmdSeleccion.Width =Activity.Width
	
	CmdGuardar.Top=PerYToCurrent(85)
	CmdGuardar.Height =PerYToCurrent(15)
	CmdGuardar.Width =PerXToCurrent(50)
	CmdGuardar.Text = ValoresComunes.GetLanString ("reg128")
	
	CmdNew.Top=PerYToCurrent(85)
	CmdNew.Height =PerYToCurrent(15)
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
	
	ValoresComunes.LoadPaleta(dlg1  ,dlg2)
End Sub

Sub Activity_Resume
	If ValoresComunes.Centrales .IsInitialized = True Then
		If Main.MyLan.IsInitialized = False Then Main.MyLan.Initialize( 0, "")
		If UDPSocket1.IsInitialized = False Then  ValoresComunes.IniUDP(UDPSocket1) 
		
		If ImgCargando.IsInitialized = False Then
			ImgCargando.Initialize ("ImgCargando")
			ImgCargando.Bitmap = ValoresComunes.Cargando 
			Activity.AddView (ImgCargando,  PerXToCurrent(40)	, PerYToCurrent(35),PerXToCurrent(20) ,PerXToCurrent(20) )
		End If
		
		Timetable.Initialize 
		CircuitoMostrado=100
		
			Select TipoDia
			Case 0:	'Monday
				 Activity.Title =ValoresComunes.GetLanString ("reg51")
				 
			Case 1:	'Tuesday
				Activity.Title =ValoresComunes.GetLanString ("reg52")
			Case 2:	'Wednesday
				Activity.Title =ValoresComunes.GetLanString ("reg53")
			Case 3:	'Thursday
				Activity.Title =ValoresComunes.GetLanString ("reg54")
			Case 4:	'Friday
				Activity.Title =ValoresComunes.GetLanString ("reg55")
			Case 5:	'Saturday
				Activity.Title =ValoresComunes.GetLanString ("reg56")
			Case 6:	'Sunday
				Activity.Title =ValoresComunes.GetLanString ("reg57")
			Case 7:	'Esp1
				Activity.Title =ValoresComunes.GetLanString ("SPDI") & " 1" 
			Case 8:	'Esp2
				Activity.Title =ValoresComunes.GetLanString ("SPDI") & " 2" 
		End Select
		
		CmdGuardar.Enabled =False
		CmdNew.Enabled =False
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
			
			
			If ValoresComunes.Condicionados (I).Nombre <> "" Then	ListCon.AddTwoLinesAndBitmap2 (ValoresComunes.Condicionados (I).Nombre ,ValoresComunes.Condicionados (I).Descripcion  ,ValoresComunes.CheckOff,I+40)				
			
			If ValoresComunes.Scenes (I).Nombre<>"" Then 	listEscenas.AddTwoLinesAndBitmap2 (ValoresComunes.Scenes (I).Nombre ,ValoresComunes.Scenes(I).Descripcion ,ValoresComunes.Scene2 ,I+30)
			
			listFunciones.AddTwoLinesAndBitmap2 ("Mi funcion pesonal nº " & I  ,"Este horario hace lo que el user quiera",ValoresComunes.Funciones ,I+50)
			
		Next
		
		For I =0 To 29
			If ValoresComunes.Circuitos  (I).Nombre <> "" AND ValoresComunes.Circuitos (I).Rango > 0 Then	listCir.AddTwoLinesAndBitmap2 (ValoresComunes.circuitos (I).Nombre ,ValoresComunes.Circuitos (I).Descripcion  ,ValoresComunes.IconoCircuito (I,1),I)
		Next
		
		Dim LongTrama As Int 
		If ValoresComunes.ConexionSegura Then
			LongTrama= 16
		Else
			LongTrama= 8
		End If
		Dim data(LongTrama) As Byte
		
		data(0)=82
		data(1)=69
		data(2)=65
		data(3)=68
		data(4)=72
		data(5)=79
		data(6)=82
		data(7)=TipoDia
		If ValoresComunes.ConexionSegura Then ValoresComunes.CompletarTrama (data)
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
	
	lst.Add (ValoresComunes.LanString.GetDefault  ("reg89","ALL CIRCUITS"))'ALL CIRCUITS
	lst.Add (ValoresComunes.LanString.GetDefault ("reg151","Circuit"))'Circuit
	lst.Add (ValoresComunes.LanString.GetDefault ("reg152","Scene"))'Scene
	lst.Add (ValoresComunes.LanString.GetDefault ("reg131","Conditioned"))'Conditioned
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
				If ValoresComunes.Circuitos (I).Nombre <> "" AND ValoresComunes.Circuitos (I).Rango >0 Then
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
Sub CmdGuardar_Click

	Dim Rsp As Int
	
	Rsp = Msgbox2(ValoresComunes.GetLanString ("reg76"),ValoresComunes.GetLanString ("reg77"),ValoresComunes.GetLanString ("Y"),ValoresComunes.GetLanString ("N"),"",Null)
	
	If Rsp = DialogResponse.POSITIVE Then SaveHorario(1)
	
End Sub
Sub listNewHor_ItemClick (Position As Int, Value As Object)
	
	'Dim a As Int
	
	Activity.RemoveViewAt (Activity.NumberOfViews-1) 

	
	Dim Dialog As TimeDialog 
	Dim H As Horario 
	
	Dim Pos As Int
	If CircuitoMostrado < 50 Then  Pos= CircuitoMostrado
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
		Case 1,3,7,8,13,19,24,39,15,25,43,51:	'Circuitos DIGITALES
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
			ret = cd.Show(ValoresComunes.GetLanString ("reg92"), ValoresComunes.GetLanString ("reg83"), ValoresComunes.GetLanString ("Cancel"), "", Null)		
			If ret = DialogResponse.POSITIVE Then
				NewVal= Barra.Value 
			Else
				Return	
			End If
		Case 29:	'Temp. Consigna
		
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
		Case 14,52:	'Temporizados 
			Dim Dialogo As NumberDialog 
			Dialogo.Digits =3
		
				
			Dialogo.Number = 0
			
			Dim Result As Int
			Result = Dialogo.Show (ValoresComunes.GetLanString ("reg84"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
			
			If Result=  DialogResponse.POSITIVE  Then
				If Dialogo.Number < 221 AND Dialogo.Number >0 Then
					NewVal =Dialogo.Number
				Else
					Msgbox(ValoresComunes.GetLanString ("reg85"),ValoresComunes.GetLanString ("reg86"))
					Return
				End If
				
			End If
		Case 34:	'Persiana		
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


Sub CmdNew_Click
 
	If Timetable.Size >79 Then
		Msgbox(ValoresComunes.GetLanString ("reg68"),ValoresComunes.GetLanString ("reg69"))
		Return
	End If


	Activity.AddView (pnl, 1%x, 0%y,  98%x, 100%y) ' sizing relative to the screen size is probably best
	TabConfig.Width = pnl.Width 
	TabConfig.Height =pnl.Height 
	
	'listEscenas.Width = pnl.Width 
	'listFunciones.Width = pnl.Width 
	
	
	
	'TabConfig.AddTabWithIcon ("", ValoresComunes.Bombillades, ValoresComunes.Bombillades, "FrmMosaico") 'load the layout file of each page
	'TabConfig.AddTabWithIcon ("", ValoresComunes.scene2, ValoresComunes.scene2, "FrmMosaico2") 'load the layout file of each page
	


	'listCir.Width = pnl.Width 
	'ListCon.Width = pnl.Width 
	
	
	
	
	
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
	
	If H.Circuito < 30  Then
		'******************************************************
		'Alumbrado 
		'******************************************************
		If ValoresComunes.Circuitos (H.Circuito).Rango=29 Then	
						
			ListView1.AddTwoLinesAndBitmap2 (Hr & " - "& H.Salida  & "ºC" ,ValoresComunes.Circuitos (H.Circuito).Nombre  ,ValoresComunes.Temperatura,p)
		Else
			ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Circuitos (H.Circuito).Nombre ,ValoresComunes.IconoCircuito (H.Circuito,H.Salida),p)		
		End If
		Return		
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
		Return
	End If
	
	'******************************************************
	'Funciones comunes
	'******************************************************
	If H.Circuito>49 AND H.Circuito <60 Then
		ListView1.AddTwoLinesAndBitmap2 (Hr,ValoresComunes.Functions  (H.Circuito -  50).Nombre ,ValoresComunes.Funciones ,p)
	End If
End Sub
Sub SaveHorario(PacketNumber As Int)
'WRITHOR VIEJA TRAMA
	'HORWRI
	Dim LongTrama As Int 
	If ValoresComunes.ConexionSegura Then
		LongTrama= 96
	Else
		'LongTrama= 28
		LongTrama= 88
	End If
	Dim data(LongTrama) As Byte
	data(0)=72
	data(1)=79
	data(2)=82
	data(3)=87
	data(4)=82
	data(5)=73
	data(6)=PacketNumber
	data(7)=TipoDia		
	
	Dim I,c,pos As Int
	I=(data(6)-1)*20
	pos=8
	For c =I To I+19
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
End Sub
Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
		Dim msg As String
	    msg = BytesToString(Packet.data, Packet.Offset, Packet.Length, "UTF8")
		
			
		 If msg.Contains ("HWRT") Then
			If Packet.data (4)<4 Then
				SaveHorario(Packet.data (4)+1)
				Return
			End If
		Else If msg.Contains ("EHR") Then
			'PaquetesPartidos=True
			Dim Pos As Int 
			Pos= Packet.data (3)-1
			Pos =(Pos*80)-4
			
			Dim p As Int
			For p =4 To Packet.Length -1
				Valores(Pos+p)=Packet.data (p)-1
				If Valores(p-4) < 0 Then
					Valores(p-4)=Valores(p-4) +256
				End If
			Next
			
			If Packet.data (3)=4 Then
				p=0
				Timetable.Clear 
				
				Do While p < 317
					If Valores(p)<24 AND Valores(p)>=0 AND Valores(p+1)>=0 AND Valores(p+1)<60 AND Valores(p+2)>= 0 AND Valores(p+2)<=50 AND Valores(p+3)>=0 AND Valores(p+3) <=220 Then
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
				'Pedimos siguiente paquete de horarios
				Dim LongTrama As Int 
				If ValoresComunes.ConexionSegura Then
					LongTrama= 16
				Else
					LongTrama= 8
				End If
		
				Dim data(LongTrama) As Byte
				data(0)=72
				data(1)=79
				data(2)=82
				data(3)=69
				data(4)=65
				data(5)=68
				data(6)=Packet.data (3)+1
				data(7)=TipoDia
				If ValoresComunes.ConexionSegura Then ValoresComunes.CompletarTrama (data)
				SendTrama(data)
				Return
			End If
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