Type=Activity
Version=7.01
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region Module Attributes
	#FullScreen: True
	#IncludeTitle: False
#End Region

Sub process_globals
	Dim UDPSocket1 As UDPSocket
	Dim Valores(30) As   Int
	Dim NumeroScene As Byte

End Sub

Sub Globals
	
	Dim ListView1 As ListView
	
	Dim CmdGuardar As Button
	Dim CmdTerminar As Button
	
	Dim TramaEnviada() As Byte
	Dim ImgCargando As ImageView
	Dim AniCargando As  Animation

	Dim PaqueteEnviado As Boolean
	
	Dim dlg1 As ColorPickerDialog
	Dim dlg2 As ColorPickerDialog
	
	'Controles dialogo barra
	Dim CmdBarOk As Button
	Dim CmdBarCancel As Button
	Dim PanBarra As Panel
	Dim Barra As mbVSeekBar
	Dim BarCir As Byte
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	If FirstTime Then
 
      
	End If
	
	
	'UDPSocket1.Initialize("UDP", 0, 8000)
	
	Dim he As Int = 40dip	
	
	Activity.LoadLayout ("frmconfig")
	Activity.Title = ValoresComunes.Scenes (NumeroScene-1).Nombre
	ListView1.Height = Activity.Height - he
	ListView1.Width =Activity.Width

	
	CmdGuardar.Top=Activity.Height-he
	CmdGuardar.Left = Activity.Width   - (he+4dip)
	CmdGuardar.Height =he
	CmdGuardar.Width =he' Activity.Width
	CmdGuardar.Text = ""'ValoresComunes.GetLanString ("reg128")
	CmdGuardar.SetBackgroundImage(ValoresComunes.CmdImgSave)
	
	CmdTerminar.Top=Activity.Height-he
	CmdTerminar.Height =he
	CmdTerminar.Width =he
	CmdTerminar.Left =Activity.Width   - ((he  * 2)   +8dip)
	CmdTerminar.SetBackgroundImage(ValoresComunes.CmdImgBack)
	CmdTerminar.Text = ""
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
	
	IniBarDialog 'Iniciamos dialog barra
	
End Sub
Sub Activity_Pause (UserClosed As Boolean)
	
	UDPSocket1.Close
	Activity.Finish
End Sub
Sub Activity_Resume
	If ValoresComunes.Centrales .IsInitialized = True Then
		
		If Main.MyLan.IsInitialized = False Then Main.MyLan.Initialize( 0, "")
		If UDPSocket1.IsInitialized = False Then
			ValoresComunes.IniUDP(UDPSocket1)
		End If
		
		If ImgCargando.IsInitialized = False Then
			ImgCargando.Initialize ("ImgCargando")
			ImgCargando.Bitmap = ValoresComunes.Cargando
			ImgCargando.Gravity = Gravity.FILL
			Activity.AddView (ImgCargando,  PerXToCurrent(40)	, PerYToCurrent(35),PerXToCurrent(20) ,PerXToCurrent(20) )
		End If
	
		CmdGuardar.Enabled =False
		
		
		
		AniCargando.InitializeRotateCenter( "AniCargando", 0, 180, ImgCargando)
		AniCargando.Duration  =   1000
		AniCargando.RepeatCount =5
		AniCargando.RepeatMode = AniCargando.REPEAT_REVERSE
		Dim LongTrama As Int
		If ValoresComunes.central.ConexionSegura Then
			LongTrama= 13
		Else
			LongTrama= 5
		End If
		Dim data(LongTrama) As Byte
		data(0)=82
		data(1)=69
		data(2)=83
		data(3)=67
		If NumeroScene <1 Or NumeroScene >10 Then NumeroScene=1
		data(4)= NumeroScene
		If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
		SendTrama(data)
	Else
		Activity.Finish
		StartActivity(Main)
	End If
End Sub
Sub AniCargando_AnimationEnd
	
	If PaqueteEnviado = True Then
		AniCargando.Start (ImgCargando)
		SendTrama(TramaEnviada)
	End If
End Sub



Sub ActualizaValores()
	ListView1.Clear

	
	
	Dim i As Int
	For i =0 To 29
		
		If ValoresComunes.Circuitos (i).Nombre <> "" And ValoresComunes.Circuitos (i).Rango > 0 And ValoresComunes.Circuitos (i).Rango < 1000 Then
			
			'******************************************************
			'Temperatura Consiga
			'******************************************************

			If ValoresComunes.Circuitos (i).Rango=29 Then

				If Valores(i)>240  Or Valores(i)<0 Then
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre & " : ####ºC"  ,ValoresComunes.GetLanString ("reg140") ,ValoresComunes.TemperaturaDes,i)
					Valores(i)=250
				Else
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre & " : "& Valores(i) & "ºC"  ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.Temperatura,i)
				End If
				
			
			Else If ValoresComunes.Circuitos (i).Rango=30 Then 'Termostato Casa
			
				If Valores(i)>170  Or Valores(i)<0 Then
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre & " : ####ºC"  ,ValoresComunes.Circuitos (i).Descripcion,ValoresComunes.TemperaturaDes,i)
					Valores(i)=250
				Else
					Dim Val1  As Double
					Val1= (Valores(i) +150)/10
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre & " : "& Val1 & "ºC"  ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.Temperatura,i)
				
				
				End If
				
			Else If ValoresComunes.Circuitos (i).Rango=31 Then 'Sondas -
				If Valores(i)>240  Or Valores(i)<0 Then
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre & " : ####ºC"  ,ValoresComunes.GetLanString ("reg140") ,ValoresComunes.TemperaturaDes,i)
					Valores(i)=250
				Else
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre & " : -"& Valores(i) & "ºC"  ,ValoresComunes.GetLanString ("reg92") ,ValoresComunes.Temperatura,i)
				End If
			Else If ValoresComunes.Circuitos (i).Rango=33 Then 'Sensor view ignorar -
				Valores(i)=255
				'Consignas
			Else If ValoresComunes.Circuitos (i).Rango>52 And ValoresComunes.Circuitos (i).Rango<57 Then
				Dim l1 As String
				Dim se As Circuito
				Dim unidad As String
				
				
				
				se=ValoresComunes.Sensores( ValoresComunes.Circuitos(i).DeviceNumber )
				'ValoresComunes.UnidadSensor(i)
				
				unidad=ValoresComunes.UnidadSensor(ValoresComunes.Circuitos (i).DeviceNumber )
				
				
				
				Select Case ValoresComunes.Circuitos (i).Rango
				
					Case 53
						l1=ValoresComunes.Circuitos (i).Nombre  & " : " & Valores (i) & unidad
					Case 54
						l1=ValoresComunes.Circuitos (i).Nombre  & " : " & Valores (i) & unidad
					
					Case 55
						l1=ValoresComunes.Circuitos (i).Nombre  & " : " &  (Valores (i) * 10) & unidad
					Case 56
						l1=ValoresComunes.Circuitos (i).Nombre  & " : " &  (Valores (i) * 100) & unidad
					Case 57
						l1=ValoresComunes.Circuitos (i).Nombre  & " : " & (Valores (i) * 1000) & unidad
				End Select
				
				
				ListView1.AddTwoLinesAndBitmap2 (l1 ,se.Nombre ,ValoresComunes.IconoCircuito(i,Valores(i)),i)
	
			Else If  ValoresComunes.Circuitos (i).Rango=34 Or ValoresComunes.Circuitos (i).Rango=36 Then
				'******************************************************
				'Persianas
				'******************************************************
				If Valores(i)>100  Or Valores(i)<0 Then
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre   ,ValoresComunes.GetLanString ("reg140") ,ValoresComunes.persianaDes,i)
					Valores(i)=250
				Else
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre & " - "& Valores(i) & "%"  ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.persiana,i)
				End If
			
 
			Else
				Dim s As String
				If Valores(i)=250 Then
					s=ValoresComunes.GetLanString ("reg140")
				Else
					s= ValoresComunes.Circuitos (i).Descripcion
				End If
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,s ,ValoresComunes.IconoCircuito(i,Valores(i)),i)
				

			End If
			
		Else
			Valores(i)=250
		End If
		
	Next
End Sub


Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim NewVal As Int
	NewVal=-1
	Select  ValoresComunes.Circuitos (Value).Rango
		'Case 1,7,8,13,4,19,24,39,15,25,43,51:	'Circuitos DIGITALES
		Case 1,3,7,8,13,19,24,39,15,25,43,51,57,58:	'Circuitos DIGITALES
			If Valores(Value)=0 Then
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

		
			NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), Valores(Value))
		Case 2,45:	'Circuitos Alumbrado
			Dim Lst As List
			Lst.Initialize
			Lst.Add (ValoresComunes.GetLanString ("reg71"))
			Lst.Add(ValoresComunes.GetLanString ("reg72"))
			Lst.Add(ValoresComunes.GetLanString ("reg73"))
			Lst.Add(ValoresComunes.GetLanString ("reg74"))
			NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), Valores(Value))
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
			Barra_On(Value)
			Return
	

		Case 14,52:	'Temporizados
			Dim Dialogo As NumberDialog
			Dialogo.Digits =3
		
			If Valores(Value)<241 Then
				Dialogo.Number = Valores(Value)
			End If
		
			Dim Result As Int
			Result = Dialogo.Show (ValoresComunes.GetLanString ("reg84"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		
			If Result=  DialogResponse.POSITIVE  Then
				If Dialogo.Number < 241 And Dialogo.Number >=0 Then
					NewVal =Dialogo.Number
				Else
					Msgbox(ValoresComunes.GetLanString ("reg85"),ValoresComunes.GetLanString ("reg86"))
					Return
				End If
			
			End If
		
		Case 53:	'set point 100
			Dim Dialogo As NumberDialog
			Dialogo.Digits =3
					
	
			If Valores(Value)<101 Then
				Dialogo.Number = Valores(Value)
			End If
		
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
			If Valores(Value)<201 Then
				Dialogo.Number =Valores(Value)' ValoresComunes.Circuitos(Value).Value
			End If
		
		
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
			If Valores(Value)<2001 Then
				Dialogo.Number = Valores(Value)*10
			End If
				
			'ValoresComunes.Circuitos(Value).Value *10
		
			Dim Result As Int
			Result = Dialogo.Show (ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		
			If Result=  DialogResponse.POSITIVE  Then
				If Dialogo.Number< 201 Then
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
					
			If Valores(Value)<201 Then
				Dialogo.Number = Valores(Value)*100
			End If
		
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
		
			If Valores(Value)<241 Then
				Dialogo.Number = Valores(Value)
			End If

		
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

			If Valores(Value)< 171 And Valores(Value)> 0 Then
				Dialogo.Number = (Valores(Value) + 150)
			Else
				Dialogo.Number =220
			End If
					
	
		
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
					
			If Valores(Value)<241 Then
				Dialogo.Number = Valores(Value)* -1
			Else
				Dialogo.Number =-1
			End If

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
			If Valores(Value)=0 Then
				NewVal=100
			Else
				NewVal=0
			End If
		
	End Select
	
	If NewVal < 0  Then 	Return
	Valores(Value)=NewVal
	ActualizaValores
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
			Dim b As Int
			For b=0 To 14
				If v = dlg1.GetPaletteAt(b) Then	Return b+1
			Next
			
		Else If res= DialogResponse.CANCEL Then
			dlg2.RGB=14423100
			res = dlg2.Show(ValoresComunes.GetLanString ("reg96"),  ValoresComunes.GetLanString ("Ok"),   ValoresComunes.GetLanString ("reg94"), ValoresComunes.GetLanString ("Cancel"), Null)
			
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
Sub ListView1_ItemLongClick (Position As Int, Value As Object)

	Valores(Value)=250
	ActualizaValores
	
End Sub
Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
		Dim msg As String
		msg = BytesToString(Packet.Data, Packet.Offset, Packet.Length, "UTF8")

		
		If msg.Contains ("VESC") Then
			Dim  c As Int
			For c = 5 To Packet.Length -1
				Valores(c - 5)= Packet.Data (c)-1
				
				If Valores(c-5) < 0 Then
					Valores(c-5)=Valores(c-5) +256
				End If
			Next
			
			ActualizaValores
		Else
		
			If msg ="REPETIRMSG" Then
				SendTrama(TramaEnviada)
				Return
			Else
				'ToastMessageShow(msg,True)
			End If
		End If
		PaqueteEnviado = False
		CmdGuardar.Enabled =True
		AniCargando.Stop(ImgCargando)
		ImgCargando.Visible =False
	
	Catch
	    
	End Try
End Sub

Sub SendTrama(data() As Byte )
	Dim Resultado As Boolean
	Dim Reintento As Int
	
	CmdGuardar.Enabled =False
	ImgCargando.Visible =True
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
		Packet.Initialize(data, ValoresComunes.ip, ValoresComunes.Puerto)
		UDPSocket1.Send(Packet)
		Return True
	Catch
		Return False
	End Try
End Sub

Sub CmdTerminar_Click
	Activity.Finish
End Sub
Sub CmdGuardar_Click

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
	data(4)=NumeroScene
	Dim I As Int
	
	For I =0 To 29
		data(I+5)=Valores(I)+1
	Next
	If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
	SendTrama(data)
End Sub
Sub Barra_On(Pos As Byte)
	ListView1.Enabled =False
	CmdGuardar.Enabled =False
	CmdTerminar.Enabled =False
	
	BarCir=Pos
	If Valores(Pos)<101 Then
		Barra.Value = Valores(Pos)
	Else
		Barra.Value =0
	End If
	
	
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
	CmdGuardar.Enabled =True
	CmdTerminar.Enabled =True
	PanBarra.RemoveView
	Valores(BarCir)=Barra.currentValue
	ActualizaValores
	
	

End Sub
Sub CmdBarCancel_Click
	ListView1.Enabled =True
	CmdGuardar.Enabled =True
	CmdTerminar.Enabled =True
	PanBarra.RemoveView
End Sub

