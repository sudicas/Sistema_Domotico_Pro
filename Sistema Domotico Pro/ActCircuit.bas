Type=Activity
Version=7.01
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
#End Region

Sub process_globals
	Dim UDPSocket1 As UDPSocket
	Dim Timer1 As Timer
	Dim SelectScene As Int

End Sub

Sub Globals
	Dim ListView1 As ListView
	'Dim  As SlidingMenuStd
	Dim sm As SlideMenu
	
	'Dim Valores(60) As Int
	Dim UltimaTrama() As Byte
	Dim ContTim As Int
	Dim ImgCargando As ImageView
	Dim AniCargando As  Animation

	
	'Dim AdView1 As AdView
	Dim dlg1 As ColorPickerDialog
	Dim dlg2 As ColorPickerDialog
	
	'Controles dialogo barra
	Dim CmdBarOk As Button
	Dim CmdBarCancel As Button
	Dim PanBarra As Panel
	Dim Barra As mbVSeekBar
	Dim BarCir As Byte

End Sub




Sub mnuSaveScenes_Click
	
	Dim Lst As List
	Lst.Initialize
	Dim C As Int
	For C =0 To 9
		Lst.Add (ValoresComunes.Scenes (C).Nombre )
	Next
	
	Dim Opcion As Int
	Opcion =InputList( Lst,ValoresComunes.GetLanString ("reg138"), 0)
	
	If Opcion < 0 Then Return
	
	'WESC
	Dim LongTrama As Int
	If ValoresComunes.central.ConexionSegura Then
		LongTrama= 43
	Else
		LongTrama= 35
	End If
	Dim data(LongTrama) As Byte
	data(0)=87
	data(1)=69
	data(2)=83
	data(3)=67
	data(4)= Opcion +1
	Dim I As Int
	
	For I =0 To 29
		data(I+5)= ValoresComunes.Circuitos (I).Value +1
	Next
	ActivarEsperaRespuesta
	If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
	SendTrama(data)
End Sub
Sub SeleccionarEscena(opcion As Int)
	If opcion < 0 Then Return
	
   
	'SSCE
	Dim LongTrama As Int
	If ValoresComunes.central.ConexionSegura Then
		LongTrama= 13
	Else
		LongTrama= 5
	End If
	Dim data(LongTrama) As Byte
	data(0)=83
	data(1)=83
	data(2)=67
	data(3)=69
	data(4)= opcion +1
	
	'ActivarEsperaRespuesta
	If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
	SendTrama(data)
End Sub

Sub Activity_Create(FirstTime As Boolean)



	If ValoresComunes.Centrales .IsInitialized = False  Then 	Return
	
	
	
	If Main.MyLan.IsInitialized = False Then  Main.MyLan.Initialize( 0, "")
	
	Activity.LoadLayout ("FrmCircuitos")
	
	
	'Activity.AddMenuItem(ValoresComunes.GetLanString ("reg129"), "mnuSelectScenes")
	Activity.AddMenuItem(ValoresComunes.GetLanString ("reg130"), "mnuSaveScenes")
	'Activity.AddMenuItem(ValoresComunes.GetLanString ("reg104"), "mnuConfig")
	'Activity.AddMenuItem(ValoresComunes.GetLanString ("reg131"), "mnuCondicionado")
	'Activity.AddMenuItem(ValoresComunes.GetLanString ("reg132"), "mnuCommands")
	'Activity.AddMenuItem(ValoresComunes.GetLanString ("Sensor"), "mnuSensor")
	'Activity.AddMenuItem(ValoresComunes.GetLanString ("reg133"), "mnuConsigna")
	'Activity.AddMenuItem(ValoresComunes.GetLanString ("reg135"), "mnuNotification")
	
	'If ValoresComunes.Centrales .IsInitialized Then
	'		If ValoresComunes.Centrales.Size  > 1 Then Activity.AddMenuItem(ValoresComunes.GetLanString ("reg134"), "mnuCentrales")
	'Else
	'		 Activity.AddMenuItem(ValoresComunes.GetLanString ("reg134"), "mnuCentrales")
	'End If
		
	'Activity.AddMenuItem(ValoresComunes.GetLanString ("reg134"), "mnuCentrales")
	
	If FirstTime Then

		Timer1.Initialize ("Timer1",100)
	End If
	
	
	
	
	
	
	
	ListView1.height = 100%y - 52dip
	ListView1.Width =Activity.Width
	ListView1.Top =52dip
	

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
	'AdView1.Initialize2("Ad", "ca-app-pub-6421049843515203/5265561373", AdView1.SIZE_SMART_BANNER)
	'Activity.AddView( AdView1, 0dip, 0dip, 100%x, height)
	'AdView1.LoadAd

	'Cargamos Paletas
	ValoresComunes.LoadPaleta(dlg1  ,dlg2)
	
	ImgCargando.Top = 100%y - ImgCargando.height
	
	sm.Initialize(Activity, Me,  "SlideMenu",  42dip, 250dip )
	
	ValoresComunes.BuldMenus (sm,2)
	
	IniBarDialog
		
End Sub


Sub Timer1_Tick
	ContTim=ContTim+1
	If ImgCargando.Visible = False Then
	
		If ContTim >50 Then
			
			LecturaDatos
		End If
	Else
			
		If ContTim=30 Then
			SendTrama(UltimaTrama)
			'ContTim=0
			'Return
		End If
			
	
	End If
End Sub
Sub Activity_Pause (UserClosed As Boolean)
	
	Timer1.Enabled =False
	UDPSocket1.Close
	Activity.Finish
	'StartActivity(ActMosaico)
End Sub
Sub Activity_Resume

	'StartActivity(ActSelectSensores)
	If ValoresComunes.CloseApp =True Then
		Activity.Finish
		Return
	End If

	If ValoresComunes.Centrales .IsInitialized = True Then

			
		'Activity.Title = "kk"
		
		ImgCargando.Bitmap= ValoresComunes.Cargando
		If Main.MyLan.IsInitialized = False Then  Main.MyLan.Initialize( 0, "")
		
		If Timer1.Interval <> 100 Then Timer1.Initialize ("Timer1",100)
		Timer1.Enabled = True
		
		AniCargando.InitializeRotateCenter( "AniCargando", 0, 180, ImgCargando)
		AniCargando.Duration = 1000
		AniCargando.RepeatCount = -1
		AniCargando.RepeatMode = AniCargando.REPEAT_REVERSE

		If UDPSocket1.IsInitialized =False Then ValoresComunes.IniUDP(UDPSocket1)
		
		ListView1.Visible=False
		ActivarEsperaRespuesta
		
		If ValoresComunes.Circuitos (0).Rango =1000 Then
			Dim data() As Byte
			Dim Trama As String
			If ValoresComunes.central.ConexionSegura Then
				Trama = "CARG" & ValoresComunes.Central.Password
			Else
				Trama = "CARG"
			End If
			
			data = Trama.GetBytes("UTF8")
			SendTrama(data)
			
		Else
			
			If SelectScene =0 Then
				
				LecturaDatos
				
			Else
				SeleccionarEscena(SelectScene-1)
				SelectScene=0
			End If
			
			
		End If
		
		
	Else
		Activity.Finish
		StartActivity(Main)
	End If
End Sub
Sub LecturaDatos()

	Dim data() As Byte
	Dim Trama As String
	If ValoresComunes.central.ConexionSegura Then
		Trama = "VACT" & ValoresComunes.Central.Password
	Else
		Trama = "VACT"
	End If
	
	data = Trama.GetBytes("UTF8")
	
	'ActivarEsperaRespuesta
	SendTrama(data)
End Sub
Sub LecturaSensores()
	
	
	Dim data() As Byte
	Dim Trama As String
	If ValoresComunes.central.ConexionSegura Then
		Trama = "EESE" & ValoresComunes.Central.Password
	Else
		Trama = "EESE"
	End If
	
	data = Trama.GetBytes("UTF8")
	
	'ActivarEsperaRespuesta
	SendTrama(data)
   	
End Sub

Sub ActualizaValores()
	'If ListView1.Enabled = False Then Return
	ListView1.Clear

	
	Dim i As Int
	For i =0 To 29
		'Dim dv As Int
		'Dim ra As Int
				
		'dv= ValoresComunes.Circuitos(i).DeviceNumber
		'ra=ValoresComunes.Circuitos(i).Rango
		If ValoresComunes.Circuitos (i).Nombre <> "" And ValoresComunes.Circuitos (i).Rango > 0  And ValoresComunes.Circuitos (i).Rango < 1000 Then
					
			
			'Sondas y consignas
			'******************************************************
			If ValoresComunes.Circuitos (i).Rango=29 Then 'Sondas +
				Dim Value  As Double
				Value=ValoresComunes.Sensores (  ValoresComunes.Circuitos (i).DeviceNumber ).Value  / 10
				ListView1.AddTwoLinesAndBitmap2  (ValoresComunes.Circuitos (i).Nombre & " : "& ValoresComunes.Circuitos (i).Value  & "ºC"  ,ValoresComunes.Sensores (ValoresComunes.Circuitos (i).DeviceNumber ).Nombre  & " " & Value & "ºC" ,ValoresComunes.Temperatura,i)
		
			Else If ValoresComunes.Circuitos (i).Rango=30 Then 'Termostato Casa
				Dim Val1  As Double
				Dim Val2  As Double
				
				
				Val1= (ValoresComunes.Circuitos (i).Value +150)/10
				Val2=ValoresComunes.Sensores (  ValoresComunes.Circuitos (i).DeviceNumber ).Value  / 10
				ListView1.AddTwoLinesAndBitmap2  (ValoresComunes.Circuitos (i).Nombre & " : "& Val1  & "ºC"  ,ValoresComunes.Sensores (ValoresComunes.Circuitos (i).DeviceNumber ).Nombre  & " " & Val2 & "ºC" ,ValoresComunes.Temperatura,i)
			
			Else If ValoresComunes.Circuitos (i).Rango=31 Then 'Sondas -
				Dim Value  As Double
				Value=ValoresComunes.Sensores (  ValoresComunes.Circuitos (i).DeviceNumber ).Value  / 10
				ListView1.AddTwoLinesAndBitmap2  (ValoresComunes.Circuitos (i).Nombre & " : -"& ValoresComunes.Circuitos (i).Value  & "ºC"  ,ValoresComunes.Sensores (ValoresComunes.Circuitos (i).DeviceNumber ).Nombre  & " " & Value & "ºC" ,ValoresComunes.Temperatura,i)
			
			Else If ValoresComunes.Circuitos (i).Rango=33 Then 'Sensor View -
				Dim pos As Int
				pos=ValoresComunes.Circuitos (i).DeviceNumber
				
				If pos<ValoresComunes.Sensores.Length  And pos >-1 Then
					Dim Value  As Double
					
					If ValoresComunes.Sensores(pos).Rango = 1  Or ValoresComunes.Sensores(pos).Rango =2  Or ValoresComunes.Sensores(pos).Rango =5  Then'Temp y humedad y float
						Value=ValoresComunes.Sensores (  pos ).Value  / 10
					Else
						Value=ValoresComunes.Sensores (  pos ).Value
					End If

					ListView1.AddTwoLinesAndBitmap2  (ValoresComunes.Circuitos (i).Nombre & " : "& Value  & ValoresComunes.UnidadSensor(pos)  , ValoresComunes.Circuitos (i).Descripcion,ValoresComunes.IconoSensor(pos ),i)
				End If
				
				'tigger
			Else If ValoresComunes.Circuitos (i).Rango =57 Or ValoresComunes.Circuitos (i).Rango =58 Then
				
				
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion,ValoresComunes.IconoCircuito(i,ValoresComunes.Circuitos (i).Value ),i)
			
				'Consignas
			Else If ValoresComunes.Circuitos (i).Rango>52 And ValoresComunes.Circuitos (i).Rango<57 Then
				Dim l1 As String
				Dim se As Circuito
				Dim unidad As String
				
				If ValoresComunes.Circuitos(i).DeviceNumber < ValoresComunes.Sensores .Length Then
					se=ValoresComunes.Sensores( ValoresComunes.Circuitos(i).DeviceNumber )
					'ValoresComunes.UnidadSensor(i)
					
					unidad=ValoresComunes.UnidadSensor(ValoresComunes.Circuitos (i).DeviceNumber )
					
					
					
					Select Case ValoresComunes.Circuitos (i).Rango
					
						Case 53
							l1=ValoresComunes.Circuitos (i).Nombre  & " : " & ValoresComunes.Circuitos (i).Value & unidad
						Case 54
							l1=ValoresComunes.Circuitos (i).Nombre  & " : " & ValoresComunes.Circuitos (i).Value & unidad
						
						Case 55
							l1=ValoresComunes.Circuitos (i).Nombre  & " : " &  (ValoresComunes.Circuitos (i).Value * 10) & unidad
						Case 56
							l1=ValoresComunes.Circuitos (i).Nombre  & " : " &  (ValoresComunes.Circuitos (i).Value * 100) & unidad
						Case 57
							l1=ValoresComunes.Circuitos (i).Nombre  & " : " & (ValoresComunes.Circuitos (i).Value * 1000) & unidad
					End Select
					Dim Value  As Double
					
					
					If se.Rango = 1  Or se.Rango =2   Or se.Rango =5 Then'Temp y humedad
						Value=se.Value /10
					Else
						Value=se.Value
					End If
					
					
					ListView1.AddTwoLinesAndBitmap2 (l1 ,se.Nombre &" : " & Value  & unidad,ValoresComunes.IconoSensor(ValoresComunes.Circuitos (i).DeviceNumber ) ,i)
				Else
					Select Case ValoresComunes.Circuitos (i).Rango
				
						Case 53
							l1=ValoresComunes.Circuitos (i).Nombre  & " : " & ValoresComunes.Circuitos (i).Value
						Case 54
							l1=ValoresComunes.Circuitos (i).Nombre  & " : " & ValoresComunes.Circuitos (i).Value
					
						Case 55
							l1=ValoresComunes.Circuitos (i).Nombre  & " : " &  (ValoresComunes.Circuitos (i).Value * 10)
						Case 56
							l1=ValoresComunes.Circuitos (i).Nombre  & " : " &  (ValoresComunes.Circuitos (i).Value * 100)
						Case 57
							l1=ValoresComunes.Circuitos (i).Nombre  & " : " & (ValoresComunes.Circuitos (i).Value * 1000)
					End Select
					ListView1.AddTwoLinesAndBitmap2 (l1,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.SensorGenerico,i)
				End If
				
				
				
				
				
	
			Else If ValoresComunes.Circuitos (i).Rango=34 Or ValoresComunes.Circuitos (i).Rango=36 Then
				'Persianas
				'******************************************************
				ListView1.AddTwoLinesAndBitmap2  (ValoresComunes.Circuitos (i).Nombre & " - "& ValoresComunes.Circuitos (i).Value  & "%"  ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.persiana,i)
			
			Else
				Dim s As String
				If ValoresComunes.Circuitos (i).Value=250 Then
					s=ValoresComunes.GetLanString ("reg140")
				Else
					s= ValoresComunes.Circuitos (i).Descripcion
				End If
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,s,ValoresComunes.IconoCircuito(i,ValoresComunes.Circuitos (i).Value ),i)
				
			End If
		End If
		
	Next
	
	
End Sub


Sub ListView1_ItemLongClick (Position As Int, Value As Object)
	StartActivity(ActVoice)
End Sub
Sub GetRGBcolor() As Int
	Dim res As Int
	res=DialogResponse.CANCEL
	
	Do While res=DialogResponse.CANCEL
		dlg1.RGB=8388736
		res = dlg1.Show(ValoresComunes.GetLanString ("reg96"),  ValoresComunes.GetLanString ("Ok"),  ValoresComunes.GetLanString ("reg94"), ValoresComunes.GetLanString ("Cancel"), Null)
		If res=DialogResponse.POSITIVE Then
			Dim v As Int
			v= dlg1.RGB
			Dim B As Int
			For B=0 To 14
				If v = dlg1.GetPaletteAt(B) Then	Return B+1
			Next
			
		Else If res= DialogResponse.CANCEL Then
			dlg2.RGB=14423100
			res = dlg2.Show(ValoresComunes.GetLanString ("reg96"),  ValoresComunes.GetLanString ("Ok"),   ValoresComunes.GetLanString ("reg94"), ValoresComunes.GetLanString ("Cancel"), Null)
			
			If res=DialogResponse.POSITIVE Then
				Dim v As Int
				v= dlg2.RGB
				Dim B As Int
				For B=0 To 14
					If v = dlg2.GetPaletteAt(B) Then	Return B+16
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
  
   
	Dim NewVal As Int
	NewVal=-1
	
	Select  ValoresComunes.Circuitos (Value).Rango
		Case 1,3,7,8,13,19,24,39,15,25,43,51,57,58:	'Circuitos DIGITALES
			If ValoresComunes.Circuitos(Value).Value =0 Then
				NewVal=1
			Else
				NewVal=0
			End If
		Case 44:	'Circuitos 2
			Dim Lst As List
			Lst.Initialize
			Lst.Add (ValoresComunes.GetLanString ("reg71"))
			Lst.Add(ValoresComunes.GetLanString ("reg72"))
			Lst.Add(ValoresComunes.GetLanString ("reg73"))

		
			NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), ValoresComunes.Circuitos(Value).Value )
		Case 2,45:	'Circuitos 3
			Dim Lst As List
			Lst.Initialize
			Lst.Add (ValoresComunes.GetLanString ("reg71"))
			Lst.Add(ValoresComunes.GetLanString ("reg72"))
			Lst.Add(ValoresComunes.GetLanString ("reg73"))
			Lst.Add(ValoresComunes.GetLanString ("reg74"))
		
			NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), ValoresComunes.Circuitos(Value).Value)
	
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
			'kk barra
			Barra_On(Value)
			Return
		Case 14,52:	'Temporizados
			Dim Dialogo As NumberDialog
			Dialogo.Digits =3
	
			'NewVal= Valores(Value)
			Dialogo.Number = ValoresComunes.Circuitos(Value).Value
		
			Dim Result As Int
			Result = Dialogo.Show (ValoresComunes.GetLanString ("reg84"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		
			If Result=  DialogResponse.POSITIVE  Then
				If Dialogo.Number < 241 And Dialogo.Number >=0 Then
					NewVal =Dialogo.Number
				Else
					Msgbox(ValoresComunes.GetLanString ("reg85"),ValoresComunes.GetLanString ("reg86"))
					Return
				End If
			Else
				Return
			End If
	
		Case 53:	'set point 100
			Dim Dialogo As NumberDialog
			Dialogo.Digits =3
					
			Dialogo.Number = ValoresComunes.Circuitos(Value).Value
		
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
					
			Dialogo.Number = ValoresComunes.Circuitos(Value).Value
		
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
					
			Dialogo.Number = ValoresComunes.Circuitos(Value).Value *10
		
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
					
			Dialogo.Number = ValoresComunes.Circuitos(Value).Value *100
		
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
					
			Dialogo.Number = ValoresComunes.Circuitos(Value).Value
		
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
					
			Dialogo.Number = (ValoresComunes.Circuitos(Value).Value + 150)
		
			Dim Result As Int
			Result = Dialogo.Show (ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		
			If Result=  DialogResponse.POSITIVE  Then
			
				If Dialogo.Number< 321 And Dialogo.number> 149 Then
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
					
			Dialogo.Number = ValoresComunes.Circuitos(Value).Value * -1
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
		Case 34:	'Persiana
			Barra_On(Value)
			Return
			
		Case 35,36:	'toldo
			If ValoresComunes.Circuitos(Value).Value =0 Then
				NewVal=100
			Else
				NewVal=0
			End If
		
		

		
	End Select
	
	
	'SVAL
	If NewVal<0 Then Return
	
	
	Dim LongTrama As Int
	If ValoresComunes.central.ConexionSegura Then
		LongTrama= 14
	Else
		LongTrama= 6
	End If
	Dim data(LongTrama) As Byte
	data(0)=83
	data(1)=86
	data(2)=65
	data(3)=76
	data(4)= Value +1
	data (5)=NewVal+1
	
	

	

	ActivarEsperaRespuesta
	If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
	SendTrama(data)

End Sub

Sub ActivarEsperaRespuesta()
	ImgCargando.Visible =True
	AniCargando.Start (ImgCargando)
	
	ListView1.Enabled =False
	
End Sub

Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
		Dim msg As String
		msg = BytesToString(Packet.data, Packet.Offset, Packet.Length, "UTF8")
		

		ContTim=0
		If msg.Contains ("VVAL") Then		'Solo refresco, sin cambios
			
			Dim  C As Int
			Dim V As Int
			
			For C = 4 To Packet.Length -1
				If C<34 Then
					V=Packet.data (C)-1
					
				
				
					If V < 0 Then
						V=V +256
					End If
					ValoresComunes.Circuitos(C-4).Value =V
				
				
					'ValoresComunes.Circuitos(C - 4).Value = Packet.data (C)-1
					'If ValoresComunes.Circuitos(C-4).Value  < 0 Then
					'		ValoresComunes.Circuitos(C-4).Value =ValoresComunes.Circuitos(C-4).Value  +256
					'	End If
				End If
			Next
			If ListView1.Visible = False Then
				desactivarEspera
				
			End If
			
			ActualizaValores
			
							
			'If UltimaTrama(0)<> 86 OR UltimaTrama(1)<>65 OR UltimaTrama(2)<>67 OR UltimaTrama(3)<>84 Then
				
			'	desactivarEspera
			'Else
			'	ActualizaValores
			'End If
			LecturaSensores
			
		Else If msg.Contains ("VESC") Then
			
			Dim  C As Int
			Dim S As Int
	
			C=4
	
			Do While C<  Packet.Length
	
				Dim B1 As Short
				Dim B2 As Short
				
				
				B1= Bit.And (Packet.data (C) , 0xff)
				If B1<255 Then B1=B1-1
				
						
				' ValoresComunes.Sensores (S).Value =Bit.AND(B1,0xFF)
				C=C+1
				
				B2=Bit.And (Packet.data (C) , 0xff)
				If B2<255 Then B2=B2-1
				C=C+1
				B1= Bit.Or(Bit.ShiftLeft ( B2,8),B1)
				ValoresComunes.Sensores (S).Value =B1
				
				'Data(i) = Bit.AND(Value,0xFF)
				'DaVta(i+1) = Bit.AND(Bit.ShiftRight(Value,8),0xFF)
				
				S=S+1
				
			Loop

			
			
			
			ActualizaValores
			Return
			
		Else If msg.Contains ("EVAL") Then
			
			Dim  C As Int
			For C = 4 To Packet.Length -1
				If C<34 Then
					ValoresComunes.Circuitos(C - 4).Value = Packet.data (C)-1
					If ValoresComunes.Circuitos(C-4).Value  < 0 Then
						ValoresComunes.Circuitos(C-4).Value =ValoresComunes.Circuitos(C-4).Value  +256
					End If
				End If
			Next
			desactivarEspera
		Else If msg.Contains ("VALC") Then		'descarga rango de circuito
			
			Dim  C As Int
			For C = 4 To Packet.Length -1
				If C<34 Then	ValoresComunes.Circuitos (C-4).rango = Packet.data (C)-1
			Next
			ValoresComunes.GuardarConfigCircuitos
			ActualizaValores
			Dim data() As Byte
			Dim Trama As String
			If ValoresComunes.central.ConexionSegura Then
				Trama = "REDN" & ValoresComunes.Central.Password
			Else
				Trama = "REDN"
			End If
			
			data = Trama.GetBytes("UTF8")
			
			'ActivarEsperaRespuesta
			SendTrama(data)
			Return
		Else If msg.Contains ("VADN") Then	''device number
			
			Dim  C As Int
			For C = 4 To Packet.Length -1
				If C<34 Then	ValoresComunes.Circuitos (C-4).DeviceNumber  = Packet.data (C)-1
			Next
			ValoresComunes.GuardarConfigCircuitos
			ActualizaValores
			LecturaDatos
			Return
		Else If msg= "COMPLETED" Then
			ToastMessageShow(ValoresComunes.GetLanString ("reg139"),True)
		Else If msg ="REPETIRMSG" Then
			SendTrama(UltimaTrama)
			Return
		End If

		
		'ActMosaico.Forzar3g=False
	Catch
		'Msgbox("error udp","")
	End Try
	
End Sub
Sub desactivarEspera()
	AniCargando.Stop(ImgCargando)
	ImgCargando.Visible =False
	ListView1.Enabled =True
	If ListView1.Visible=False Then ListView1.Visible=True
	ActualizaValores
End Sub
Sub SendTrama(data() As Byte )
	Dim Resultado As Boolean
	Dim Reintento As Int
	

	UltimaTrama=data
	ContTim=0
	
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
		'Fallo de conexion
		'Main.ConexionEstablecida = False
		'StartActivity(Main)
		'ToastMessageShow("Network not available", True)
		'Sleep (1000)
		'Else
		'	PaqueteEnviado=True
	End If
	'

End Sub
Sub Activity_KeyPress (KeyCode As Int) As Boolean
	'Pressing the back key while the slidemenu is open will close it
	
	If KeyCode = KeyCodes.KEYCODE_BACK And sm.isVisible Then
		sm.Hide
		Return True
	End If

	'Pressing the menu key will open/close the slidemenu
	If KeyCode = KeyCodes.KEYCODE_MENU Then
		If sm.isVisible Then sm.Hide Else sm.Show
		Return True
	End If
End Sub
Sub SendingTrama (data() As Byte) As Boolean
	Try
		Dim Packet As UDPPacket



		'Msgbox(KK,KK2)


		Packet.Initialize(data , ValoresComunes.ip,  ValoresComunes.Puerto )
		UDPSocket1.Send(Packet)
		Return True
	Catch
		Return False
	End Try
End Sub

Sub SlideMenu_Click(Item As Object)
	If Item>99 And  Item < 200 Then
		ActMosaico.Conectado =False
		ActMosaico.CentraltoConnect  =Item-100
		StartActivity(ActMosaico)
		Activity.Finish
		
		'Activity.Finish
		'If Conectado= False Then IniciarConexionCentral(CentraltoConnect)
	Else If Item>199 And  Item < 299 Then
		
	Else
			
		Select Case Item
		
			Case 1
				StartActivity(ActMosaico)
				Activity.Finish
			Case 2
				'StartActivity(ActCircuit)
				'Activity.Finish
			Case 3
				StartActivity(ActScene)
				Activity.Finish
			Case 4
				StartActivity(ActNotification)
				Activity.Finish
			Case 5
				StartActivity(ActCondicionados)
				Activity.Finish
			Case 6'comandos
				StartActivity(ActComandos)
				Activity.Finish
			Case 7
				StartActivity(ActSensors)
				Activity.Finish
			Case 8
				If File.Exists ( File.DirInternal ,"Sensores" & ValoresComunes.Central.nombre)  Then
					StartActivity(ActFreeTxt)
					Activity.Finish
				Else
					ToastMessageShow(ValoresComunes.GetLanString ("reg127") , True)
				End If
			Case 9
				StartActivity(ActConsignas)
				Activity.Finish
			Case 10
				ValoresComunes.CloseApp =True
				Activity.Finish
			Case 11
				StartActivity(ActFunciones)
				Activity.Finish
				
			Case 98
				'StartActivity(ActSelectSensores)
				Activity.Finish
		End Select
	End If
	
End Sub



Sub btnShow_Click
	sm.Show
End Sub

Sub Barra_On(Pos As Byte)
	ListView1.Enabled  =False
	BarCir=Pos
	Barra.Value = ValoresComunes.Circuitos(Pos).Value
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
	PanBarra.RemoveView
	ListView1.Enabled =True
	
	Dim LongTrama As Int
	If ValoresComunes.central.ConexionSegura Then
		LongTrama= 14
	Else
		LongTrama= 6
	End If
	Dim data(LongTrama) As Byte
	data(0)=83
	data(1)=86
	data(2)=65
	data(3)=76
	data(4)= BarCir +1
	data (5)=Barra.currentValue   +1

	ActivarEsperaRespuesta
	If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
	SendTrama(data)

End Sub
Sub CmdBarCancel_Click
	ListView1.Enabled =True
	PanBarra.RemoveView
End Sub