Type=Activity
Version=4.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
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
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
    If FirstTime Then
 
      
    End If
	
	
	'UDPSocket1.Initialize("UDP", 0, 8000)
	
	Activity.LoadLayout ("FrmConfigEsc")
	Activity.Title = ValoresComunes.Scenes (NumeroScene-1).Nombre 
	ListView1.Height = PerYToCurrent(83)
	ListView1.Width =Activity.Width 
	
	CmdGuardar.Top=PerYToCurrent(85)
	CmdGuardar.Height =PerYToCurrent(15)
	CmdGuardar.Width =PerXToCurrent(50)
	CmdGuardar.Text = ValoresComunes.GetLanString ("reg128")
	
	CmdTerminar.Top=PerYToCurrent(85)
	CmdTerminar.Height =PerYToCurrent(15)
	CmdTerminar.Width =PerXToCurrent(50)
	CmdTerminar.Left =PerXToCurrent(50)
	CmdTerminar.Text = ValoresComunes.GetLanString ("reg184")
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
		If UDPSocket1.IsInitialized = False Then  
			ValoresComunes.IniUDP(UDPSocket1) 
		End If
		
		If ImgCargando.IsInitialized = False Then
			ImgCargando.Initialize ("ImgCargando")
			ImgCargando.Bitmap = ValoresComunes.Cargando 
			Activity.AddView (ImgCargando,  PerXToCurrent(40)	, PerYToCurrent(35),PerXToCurrent(20) ,PerXToCurrent(20) )
		End If
	
		CmdGuardar.Enabled =False
		
		
		
		AniCargando.InitializeRotateCenter( "AniCargando", 0, 180, ImgCargando)
		AniCargando.Duration  =   1000
	    AniCargando.RepeatCount =5
	    AniCargando.RepeatMode = AniCargando.REPEAT_REVERSE 
		Dim LongTrama As Int 
		If ValoresComunes.ConexionSegura Then
			LongTrama= 13
		Else
			LongTrama= 5
		End If
		Dim data(LongTrama) As Byte
		data(0)=82
		data(1)=69
		data(2)=83
		data(3)=67
		If NumeroScene <1 OR NumeroScene >10 Then NumeroScene=1
		data(4)= NumeroScene
		If ValoresComunes.ConexionSegura Then ValoresComunes.CompletarTrama (data)
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
		
		If ValoresComunes.Circuitos (i).Nombre <> "" AND ValoresComunes.Circuitos (i).Rango > 0 AND ValoresComunes.Circuitos (i).Rango < 1000 Then
			
			'******************************************************
			'Temperatura Consiga
			'******************************************************

			If ValoresComunes.Circuitos (i).Rango=29 Then

				If Valores(i)>100  OR Valores(i)<0 Then
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre & " - ####ºC"  ,ValoresComunes.GetLanString ("reg140") ,ValoresComunes.TemperaturaDes,i)
					Valores(i)=250
				Else
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre & " - "& Valores(i) & "ºC"  ,ValoresComunes.GetLanString ("reg92") ,ValoresComunes.Temperatura,i)
				End If
				
				
 			Else If  ValoresComunes.Circuitos (i).Rango=34 OR ValoresComunes.Circuitos (i).Rango=36 Then
				'******************************************************
				'Persianas
				'******************************************************
				If Valores(i)>100  OR Valores(i)<0 Then
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
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.IconoCircuito(i,Valores(i)),i)
				

			End If			
		End If
		
	Next
End Sub 


Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim NewVal As Int
	NewVal=-1
	Select  ValoresComunes.Circuitos (Value).Rango 
	'Case 1,7,8,13,4,19,24,39,15,25,43,51:	'Circuitos DIGITALES
	Case 1,3,7,8,13,19,24,39,15,25,43,51:	'Circuitos DIGITALES
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
		Dim cd As CustomDialog
		Dim pnl As Panel
		Dim Barra As SeekBar 
		Dim ret As Int
		pnl.Initialize("pnl")
		Barra.Initialize (Barra)	
		
		Barra.Max =100
		Barra.Value =Valores(Value)
		pnl.AddView(Barra , 0, PerYToCurrent(10),  PerXToCurrent(70) , PerYToCurrent(20))

		cd.AddView(pnl, 5%x, 0%y, 77%x, 70%y) ' sizing relative to the screen size is probably best
		ret = cd.Show(ValoresComunes.GetLanString ("reg92"), ValoresComunes.GetLanString ("reg83"), ValoresComunes.GetLanString ("Cancel"), "", Null)		
		If ret = DialogResponse.POSITIVE Then
			NewVal= Barra.Value 
		Else
			Return	
		End If
	Case 14,52:	'Temporizados 
		Dim Dialogo As NumberDialog 
		Dialogo.Digits =3
		
		Dialogo.Number = Valores(Value)
		
		Dim Result As Int
		Result = Dialogo.Show (ValoresComunes.GetLanString ("reg84"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		
		If Result=  DialogResponse.POSITIVE  Then
			If Dialogo.Number < 221 AND Dialogo.Number >=0 Then
				NewVal =Dialogo.Number
			Else
				Msgbox(ValoresComunes.GetLanString ("reg85"),ValoresComunes.GetLanString ("reg86"))
				Return
			End If
			
		End If
	Case 29:	'Temp. Consigna
		Dim Dialogo As NumberDialog 
		Dialogo.Digits =2
					
		Dialogo.Number = Valores(Value)
		
		Dim Result As Int
		Result = Dialogo.Show (ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		
		If Result=  DialogResponse.POSITIVE  Then
			NewVal =Dialogo.Number
		Else 
			Return
		End If

	Case 34:	'Persiana
		Dim cd As CustomDialog
		Dim pnl As Panel
		Dim Barra As SeekBar 
		Dim ret As Int
		pnl.Initialize("pnl")
		Barra.Initialize (Barra)	
		
		Barra.Max =100
		Barra.Value =Valores(Value)
		pnl.AddView(Barra , 0, PerYToCurrent(10),  PerXToCurrent(70) , PerYToCurrent(20))

		cd.AddView(pnl, 5%x, 0%y, 77%x, 70%y) ' sizing relative to the screen size is probably best
		ret = cd.Show(ValoresComunes.GetLanString ("reg93"), ValoresComunes.GetLanString ("reg83"), ValoresComunes.GetLanString ("Cancel"), "", Null)		
		If ret = DialogResponse.POSITIVE Then
			NewVal= Barra.Value 
		Else
			Return	
		End If
			
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
				ToastMessageShow(msg,True)
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
	If ValoresComunes.ConexionSegura Then
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
	If ValoresComunes.ConexionSegura Then ValoresComunes.CompletarTrama (data)
	SendTrama(data)
End Sub


