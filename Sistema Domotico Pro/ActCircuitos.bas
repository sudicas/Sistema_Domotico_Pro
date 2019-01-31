Type=Activity
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@


Sub process_globals
    Dim UDPSocket1 As UDPSocket 
	Dim Timer1 As Timer
	Dim SelectScene As Int 

End Sub

Sub Globals
	
	Dim ListView1 As ListView
	
	Dim Valores(33) As Byte
	Dim UltimaTrama() As Byte 
	Dim ContTim As Int 
	Dim ImgCargando As ImageView
	Dim AniCargando As  Animation
	Dim PaqueteEnviado As Boolean 
	
	'Dim AdView1 As AdView
	

End Sub

Sub mnuCentrales_Click
	StartActivity(Main)
End Sub

Sub mnuCondicionado_Click
	StartActivity(ActCondicionados)
End Sub
Sub mnuSensor_Click
	If File.Exists ( File.DirInternal ,"Sensores" & ValoresComunes.central.Nombre )  Then
		StartActivity(ActSensores)
	Else
		ToastMessageShow(ValoresComunes.GetLanString ("reg127") , True)
	End If
	
End Sub
Sub mnuCommands_Click
	StartActivity(ActComandos)
End Sub
Sub mnuConsigna_Click
	StartActivity(ActConsignas)
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
		data(I+5)=Valores(I)+1
	Next
	ActivarEsperaRespuesta
	If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
    SendTrama(data)	
End Sub
Sub mnuSelectScenes_Click
	Dim Lst As List 
	Lst.Initialize 
	Dim C As Int
	For C =0 To 9
		Lst.Add (ValoresComunes.Scenes (C).Nombre )
	Next
	
	Dim Opcion As Int
	SeleccionarEscena(InputList( Lst,ValoresComunes.GetLanString ("reg138"), 0))
	
	
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
	
	ActivarEsperaRespuesta
	If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
    SendTrama(data)
End Sub
Sub Activity_Create(FirstTime As Boolean)
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	If Main.MyLan.IsInitialized = False Then  Main.MyLan.Initialize( 0, "")
	
	Activity.LoadLayout ("FrmCircuitos")
	
	Activity.AddMenuItem(ValoresComunes.GetLanString ("reg129"), "mnuSelectScenes")
	Activity.AddMenuItem(ValoresComunes.GetLanString ("reg130"), "mnuSaveScenes")
	
	Activity.AddMenuItem(ValoresComunes.GetLanString ("reg131"), "mnuCondicionado")
	Activity.AddMenuItem(ValoresComunes.GetLanString ("reg132"), "mnuCommands")
	Activity.AddMenuItem(ValoresComunes.GetLanString ("Sensor"), "mnuSensor")
	Activity.AddMenuItem(ValoresComunes.GetLanString ("reg133"), "mnuConsigna")
	
	'If ValoresComunes.Centrales .IsInitialized Then
			'If ValoresComunes.Centrales.Size  > 1 Then Activity.AddMenuItem(ValoresComunes.GetLanString ("reg134"), "mnuCentrales")
		'Else
			 
	'End If
		
	Activity.AddMenuItem(ValoresComunes.GetLanString ("reg134"), "mnuCentrales")
	
    If FirstTime Then	

       	Timer1.Initialize ("Timer1",100)
    End If
	

	ImgCargando.Top = 100%y - ImgCargando.height
	ListView1.height = 100%y
	ListView1.Width =Activity.Width 
	ListView1.Top =0
	

	'AdView1.Initialize2("Ad", "ca-app-pub-6421049843515203/5265561373", AdView1.SIZE_SMART_BANNER)
	'Activity.AddView( AdView1, 0dip, 0dip, 100%x, height)
	'AdView1.LoadAd
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


Sub Timer1_Tick
	 ContTim=ContTim+1
	If ImgCargando.Visible = False Then
		If ContTim >100 Then 
			LecturaDatos
		End If
	Else
	If  ContTim > 25 Then
			If PaqueteEnviado=True Then 
				If  Main.ConexionExterna=True Then
					SendTrama(UltimaTrama)
					
				Else
					If Main.ErrorEnvio=False Then
						Main.ErrorEnvio=True
					Else
					
					
						Dim result As Int
						result = Msgbox2(ValoresComunes.GetLanString ("reg136")  , ValoresComunes.GetLanString ("reg137"), ValoresComunes.GetLanString ("Y"), "", ValoresComunes.GetLanString ("N"), Null)
						
						If result = DialogResponse.Positive Then 	
							Main.ConexionEstablecida = False
							Main.ConexionExterna = True
							
							StartActivity(Main)
						Else
						
							SendTrama(UltimaTrama)
							Main.ErrorEnvio=False
						End If
					End If
				End If
			
			End If
		End If
	End If	
End Sub
Sub Activity_Pause (UserClosed As Boolean)
	Timer1.Enabled =False
	UDPSocket1.Close 
	StartActivity(ActMosaico)
End Sub
Sub Activity_Resume
	If ValoresComunes.Centrales .IsInitialized = True Then
		
		Activity.Title = ValoresComunes.Central.Nombre 
		
		ImgCargando.Bitmap= ValoresComunes.Cargando 
		If Main.MyLan.IsInitialized = False Then  Main.MyLan.Initialize( 0, "")
		
		If Timer1.Interval <> 100 Then Timer1.Initialize ("Timer1",100)
		Timer1.Enabled = True
		
		AniCargando.InitializeRotateCenter( "AniCargando", 0, 180, ImgCargando)
		AniCargando.Duration = 1000
	    AniCargando.RepeatCount =-1
	    AniCargando.RepeatMode = AniCargando.REPEAT_REVERSE 

		If UDPSocket1.IsInitialized = False Then  ValoresComunes.IniUDP(UDPSocket1) 
		
		
		If SelectScene =0 Then
			LecturaDatos
				
		Else
			
			SeleccionarEscena(SelectScene-1)
			SelectScene=0
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
		Trama = "VACT" & ValoresComunes.central.Password   
	Else
		Trama = "VACT"
	End If
	
    data = Trama.GetBytes("UTF8")
	
	ActivarEsperaRespuesta
   	SendTrama(data)
End Sub
Sub ActualizaValores()
	ListView1.Clear 
	
	Dim i As Int 
	For i =0 To 29
		If ValoresComunes.Circuitos (i).Nombre <> "" Then
			'******************************************************
			'Alumbrado
			'******************************************************
			If i<6 Then	
				If Valores(i)=0 Then
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.BombillaOff,i)

				Else
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.BombillaOn,i)
				End If
			End If
			'******************************************************
			'Circuitos Digitales
			'******************************************************
			If i>5 AND i < 12 Then	
				Select ValoresComunes.Circuitos (i).Rango
					Case 1:	'Enchufes
						If Valores(i)=0 Then
							ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.EnchufeOff,i)

						Else
							ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.EnchufeOn,i)
						End If
					Case 2:	'Doors
					
						If Valores(i)=0 Then
							ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.PuertaOff,i)

						Else
							ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.PuertaOn,i)
						End If
				End Select

			End If
			'******************************************************
			'Riego
			'******************************************************
			If i=12 OR i =13  Then	
				If Valores(i)=0 Then
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.RiegoOff,i)

				Else
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.RiegoOn,i)
				End If
			End If
			'******************************************************
			'A.Acondicionado
			'******************************************************
			If i=14 OR i =15 OR i =16 Then	
				If Valores(i)=0 Then
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.AcondicionadoOff,i)

				Else
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.AcondicionadoOn,i)
				End If
			End If
			'******************************************************
			'Calefaccion
			'******************************************************
			If i=17 OR i =18 OR i =19 Then	
				If Valores(i)=0 Then
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.CalefaccionOff,i)

				Else
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.CalefaccionOn,i)
				End If
			End If
			'******************************************************
			'Temperatura Consiga
			'******************************************************

			If i>19 AND i<23 Then
				Dim Val As Int
				Val = Bit.AND (Valores(i+10), 0xff)*2
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre & " : "& Valores(i) & "ºC"  ,ValoresComunes.GetLanString ("reg141")  & " "& (Val/10) & "ºC" ,ValoresComunes.Temperatura,i)
				
			End If
			'******************************************************
			'Persianas
			'******************************************************
			If i>22 AND i <30 Then	
				If  ValoresComunes.Circuitos (i).Rango =1 Then
					If Valores(i)=0 Then
						ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre  ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.Toldooff,i)
					Else
						ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre  ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.ToldoOn,i)
					End If
				Else
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre & " - "& Valores(i) & "%"  ,ValoresComunes.Circuitos (i).Descripcion ,ValoresComunes.persiana,i)
				End If	
					
			End If
		End If
		
	Next
End Sub 
Sub ListView1_ItemLongClick (Position As Int, Value As Object)
	StartActivity(ActVoice)
End Sub

Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim NewVal As Int
	NewVal=-1
	Select  Value
	Case 0,1,2,3,4,5:	'Circuitos Alumbrado
		If  ValoresComunes.Circuitos (Value).Rango =1 Then
			If Valores(Value)=0 Then
				NewVal=1
			Else
				NewVal=0
			End If
		Else
			Dim Lst As List
			Lst.Initialize 
			Lst.Add (ValoresComunes.GetLanString ("reg71"))
			Dim r As Int
			For  r =1 To ValoresComunes.Circuitos (Value).Rango
				Lst.Add(ValoresComunes.GetLanString ("reg70") & " " & r & "/" & ValoresComunes.Circuitos (Value).Rango)
			Next
			NewVal =InputList(Lst,ValoresComunes.GetLanString ("reg75"), Valores(Value))
		End If
	Case 6, 7,8,9,10,11,12,13:	'Enchufes y riego
		If Valores(Value)=0 Then
				NewVal=1
			Else
				NewVal=0
		End If

	Case 14, 15,16:	'A.Acondicionados
		If Valores(Value)=0 Then
				NewVal=1
			Else
				NewVal=0
		End If


	Case 17, 18,19:	'Calefaccion
		If Valores(Value)=0 Then
				NewVal=1
			Else
				NewVal=0
		End If
	
	Case 20, 21,22:	'Temp. Consigna
		Dim Dialogo As NumberDialog 
		Dialogo.Digits =2
		'Dialogo.Decimal=1
		NewVal= Valores(Value)
		'Dim Temp As Float 
		'Temp = Valores(Value)/10
					
		Dialogo.Number = Valores(Value)
		
		Dim Result As Int
		Result = Dialogo.Show (ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		
		If Result=  DialogResponse.POSITIVE  Then
			NewVal =Dialogo.Number
		End If
	Case 23, 24,25,26,27,28,29:	'Persiana
		If  ValoresComunes.Circuitos (Value).Rango =1 Then
			If Valores(Value)=0 Then
				NewVal=100
			Else
				NewVal=0
			End If
		Else
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
	    msg = BytesToString(Packet.Data, Packet.Offset, Packet.Length, "UTF8")
			
		If msg.Contains ("EVAL") Then		
			
			Dim  C As Int
			For C = 4 To Packet.Length -1
				Valores(C - 4)= Packet.Data (C)-1 
				
			Next 
			
			ActualizaValores
		End If
		
		If msg= "COMPLETED" Then			
			ToastMessageShow(ValoresComunes.GetLanString ("reg139"),True)
		End If
		If msg ="REPETIRMSG" Then
				SendTrama(UltimaTrama)
				Return
		End If
		PaqueteEnviado = False
		AniCargando.Stop(ImgCargando)
		ImgCargando.Visible =False
		ListView1.Enabled =True
		Main.ErrorEnvio=False
	Catch
	    
	End Try	   
	
End Sub 

Sub SendTrama(data() As Byte )
	Dim Resultado As Boolean 
	Dim Reintento As Int
	
	PaqueteEnviado =False
	UltimaTrama=data	
	ContTim=0
	
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
		'Fallo de conexion
		Main.ConexionEstablecida = False
		StartActivity(Main)
		'ToastMessageShow("Network not available", True)
		'ValoresComunes.Delay (1000)		
	Else
		PaqueteEnviado=True
	End If
	'

End Sub

Sub SendingTrama (data() As Byte) As Boolean 
	Try
	    Dim Packet As UDPPacket
		'Msgbox( ValoresComunes.ip,ValoresComunes.Puerto)
		Packet.Initialize(data , ValoresComunes.ip,  ValoresComunes.Puerto )
	    UDPSocket1.Send(Packet)	
		Return True
	Catch
	    Return False
	End Try
End Sub



