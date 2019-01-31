Type=Activity
Version=1.80
FullScreen=False
IncludeTitle=True
@EndOfDesignText@
Sub process_globals
    
	Dim Valores(22) As Byte 
End Sub

Sub Globals

	Dim ListView1 As ListView
	Dim UDPSocket1 As UDPSocket

End Sub
Sub mnuConfig_Click
	'ComArduino.CambioPantalla=""
	StartActivity(ActConfigCir)
End Sub
Sub mnuEscenas_Click
	
	StartActivity( ActConfigEscenas)
End Sub
Sub Activity_Create(FirstTime As Boolean)
    If FirstTime Then
		
		ValoresComunes.CargaCircuitos 
		ValoresComunes.CargaEscenas 
		ValoresComunes.IniUDP(UDPSocket1)
    End If
	
	
	'UDPSocket1.Initialize("UDP", 0, 8000)
	
	Activity.LoadLayout ("FrmCircuitos")
	ListView1.Height = Activity.Height 
	ListView1.Width =Activity.Width 
	
	Activity.AddMenuItem("Configuracion Circuitos", "mnuConfig")
	Activity.AddMenuItem("Configuracion Escenas", "mnuEscenas")
	
   
End Sub
Sub Activity_Resume
	 Dim data() As Byte
    data = "VACT".GetBytes("UTF8")
	SendTrama(data)
End Sub

Sub ActualizaValores()
	ListView1.Clear 
	
	Dim i As Int 
	For i =0 To 19
		If ValoresComunes.Circuitos (i).Nombre <> "" Then
			'******************************************************
			'Alumbrado
			'******************************************************
			If i<4 Then	
				If Valores(i)=0 Then
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,LoadBitmap (File.DirAssets, "BombillaOff.png"),i)

				Else
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,LoadBitmap (File.DirAssets, "BombillaOn.png"),i)
				End If
			End If
			'******************************************************
			'Enchufes
			'******************************************************
			If i>3 AND i < 10 Then	
				If Valores(i)=0 Then
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,LoadBitmap (File.DirAssets, "EnchufeOff.png"),i)

				Else
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,LoadBitmap (File.DirAssets, "EnchufeOn.png"),i)
				End If
			End If
			'******************************************************
			'A.Acondicionado
			'******************************************************
			If i=10 OR i =11 Then	
				If Valores(i)=0 Then
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,LoadBitmap (File.DirAssets, "AcondicionadoOff.png"),i)

				Else
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,LoadBitmap (File.DirAssets, "AcondicionadoOn.png"),i)
				End If
			End If
			'******************************************************
			'Calefaccion
			'******************************************************
			If i=12 OR i =13 Then	
				If Valores(i)=0 Then
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,LoadBitmap (File.DirAssets, "CalefaccionOff.png"),i)

				Else
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre ,ValoresComunes.Circuitos (i).Descripcion ,LoadBitmap (File.DirAssets, "CalefaccionOn.png"),i)
				End If
			End If
			'******************************************************
			'Temperatura Consiga
			'******************************************************
			If i=14  Then	
							
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre & " - "& Valores(i) & "ºC"  ,"Temperatura Actual - "& Valores(20) & "ºC" ,LoadBitmap (File.DirAssets, "Temperatura.png"),i)
			End If
			If i=15  Then	
							
					ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Circuitos (i).Nombre & " - "& Valores(i) & "ºC"  ,"Temperatura Actual - "& Valores(21) & "ºC" ,LoadBitmap (File.DirAssets, "Temperatura.png"),i)
			End If
		End If
		
	Next


End Sub 


Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim NewVal As Int
	Select  Value
	Case 0,1,2,3:	'Circuitos Alumbrado
		If  ValoresComunes.Circuitos (Value).Rango =1 Then
			If Valores(Value)=0 Then
				NewVal=1
			Else
				NewVal=0
			End If
		Else
			Dim Lst As List
			Lst.Initialize 
			Lst.Add ("Desactivar Ado.")
			Lst.Add("Activar 1/3")
			Lst.Add("Activar 2/3")
			Lst.Add("Activar 3/3")
			NewVal =InputList(Lst,"Seleccione opción", Valores(Value))
		End If
	Case 4, 5, 6, 7,8,9:	'Enchufes
		If Valores(Value)=0 Then
				NewVal=1
			Else
				NewVal=0
		End If

	Case 10, 11:	'A.Acondicionados
		If Valores(Value)=0 Then
				NewVal=1
			Else
				NewVal=0
		End If


	Case 12, 13:	'Calefaccion
		If Valores(Value)=0 Then
				NewVal=1
			Else
				NewVal=0
		End If
	
	Case 14, 15:	'Temp. Consigna
		Dim Dialogo As NumberDialog 
		Dialogo.Digits =2
		'Dialogo.Decimal=1
		
		'Dim Temp As Float 
		'Temp = Valores(Value)/10
					
		Dialogo.Number = Valores(Value)
		
		Dim Result As Int
		Result = Dialogo.Show ("Nuevo Valor","Aceptar","Cancelar","",Null)
		
		If Result=  DialogResponse.POSITIVE  Then
			NewVal =Dialogo.Number
		End If
	End Select
	
	Valores(Value)=NewVal
	

    Dim data(6) As Byte
	data(0)=83
	data(1)=86
	data(2)=65
	data(3)=76
	data(4)= Value +1 
	data (5)=NewVal+1

    SendTrama(data)
	
	ActualizaValores
End Sub
Sub ListView1_ItemLongClick (Position As Int, Value As Object)

    Dim data(5) As Byte
	data(0)=82
	data(1)=69
	data(2)=83
	data(3)=67
	data(4)= 6
	SendTrama(data)


End Sub
Sub UDP_PacketArrived (Packet As UDPPacket)
    Dim msg As String
    msg = BytesToString(Packet.Data, Packet.Offset, Packet.Length, "UTF8")
	If msg.Contains ("VESC") Then
		Dim  c As Int
		For c = 4 To Packet.Length -1
			Valores(c - 4)= Packet.Data (c)-1 
		Next 
		
		ActualizaValores
	End If
	

End Sub 

Sub SendTrama(data() As Byte )
	Dim Resultado As Boolean 
	Dim Reintento As Int
	
	Do 	While Resultado= False AND Reintento < 20
		Resultado = SendingTrama(data)
		Reintento = Reintento +1 
		If Resultado=False Then ValoresComunes.Delay (100)
	Loop
	If Reintento > 19 Then
		'Fallo de conexion
		ToastMessageShow("Error de conexion", True)
		ExitApplication
        Return
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
