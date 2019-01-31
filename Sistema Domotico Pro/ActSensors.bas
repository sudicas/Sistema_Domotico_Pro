Type=Activity
Version=7.01
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: false
#End Region


Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim Timer1 As Timer
	Dim UDPSocket1 As UDPSocket 
	
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Dim ListView1 As ListView
	Dim ContTim As Int 
 	Dim UltimaTrama() As Byte 
	'Dim adview1 As AdView
	Dim sm As SlideMenu
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("FrmBase")
	
	
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	
	'Activity.AddMenuItem(ValoresComunes.GetLanString ("reg130"), "mnuHistorico")
	
	Activity.LoadLayout ("FrmSensores")
	ListView1.height = 100%y -52dip' - height
	ListView1.Width =Activity.Width 
	ListView1.Top =52dip
	Timer1.Initialize ("Timer1",100)
	
	'adview1.Initialize2("Ad", "ca-app-pub-6421049843515203/5265561373", adview1.SIZE_SMART_BANNER)
	'Activity.AddView( adview1, 0dip, 0dip, 100%x, height)
	'adview1.LoadAd
	
	
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

	sm.Initialize(Activity, Me,  "SlideMenu", 42dip, 250dip)
	ValoresComunes.BuldMenus (sm,7)
End Sub
Sub LecturaDatos()
	
	
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
Sub Activity_Resume

	If ValoresComunes.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then
		If UDPSocket1.IsInitialized = False Then  ValoresComunes.IniUDP(UDPSocket1) 		
		 
		Timer1.Enabled = True

		
		
		If ValoresComunes.Sensores (0).Rango =1000  Then
			Dim data() As Byte
			Dim Trama As String
			If ValoresComunes.central.ConexionSegura Then
				Trama = "RESE" & ValoresComunes.Central.Password     
			Else
				Trama = "RESE"
			End If
			
		    data = Trama.GetBytes("UTF8")
		   	SendTrama(data)
		Else

			LecturaDatos
		End If
	Else
		Activity.Finish 
		StartActivity(Main)
	End If
End Sub
Sub Timer1_Tick
	 ContTim=ContTim+1
	If  ContTim >50 Then SendTrama(UltimaTrama)

	

End Sub
Sub Activity_Pause (UserClosed As Boolean)
	Timer1.Enabled =False
	UDPSocket1.Close 
	Activity.Finish 
End Sub
Sub ActualizaValores()
	ListView1.Clear 
	Dim i As Int

	For i =0 To ValoresComunes.Sensores .Length  -1
		Dim Value  As Double
		If ValoresComunes.Sensores(i).Rango = 1  Or ValoresComunes.Sensores(i).Rango =2   Or ValoresComunes.Sensores(i).Rango =5 Then'Temp y humedad
			Value=ValoresComunes.Sensores (  i ).Value  / 10
		Else
			Value=ValoresComunes.Sensores (  i ).Value
		End If
	  	
		 If ValoresComunes.Sensores (i).Rango >0 And  ValoresComunes.Sensores (i).Rango < 250  Then ListView1.AddTwoLinesAndBitmap2 ((Value) & ValoresComunes.UnidadSensor(i),  ValoresComunes.Sensores (i).Nombre ,ValoresComunes.IconoSensor(i) ,i)
		'ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.NombreSensor.Get (i),Sensores.Get (i) ,ValoresComunes.Sensor ,i)
	Next
End Sub


Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
	  	Dim msg As String
	    msg = BytesToString(Packet.data, Packet.Offset, Packet.Length, "UTF8")
			
		If msg.Contains ("VESC") Then		
			
			
			Dim S As Int 
			Dim C As Int
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

		
		Else If msg.Contains ("VASC") Then		
			
			Dim  C As Int
			For C = 4 To Packet.Length -1
				ValoresComunes.Sensores  (C-4).rango = Packet.data (C)
			Next
			ValoresComunes.GuardarConfigSensores
			ActualizaValores
			LecturaDatos
			Return
		Else If msg= "COMPLETED" Then			
			ToastMessageShow(ValoresComunes.GetLanString ("reg139"),True)
		
		Else If msg ="REPETIRMSG" Then
				LecturaDatos
		End If
	

	Catch
	 
	End Try	   
	
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
		'Fallo de conexion
		ActMosaico.Conectado =False
		StartActivity(ActMosaico)
		
	End If


End Sub

Sub SendingTrama (data() As Byte) As Boolean 
	Try
	    Dim Packet As UDPPacket
	
		Packet.Initialize(data, ValoresComunes.ip,  ValoresComunes.Puerto )
	    UDPSocket1.Send(Packet)	
		Return True
	Catch
	    Return False
	End Try
End Sub

Sub BtnSlidingMenu_Click(ItemNo As Object)

	If ItemNo>99 And  ItemNo < 200 Then
		ActMosaico.Conectado =False
		ActMosaico.CentraltoConnect  =ItemNo-100
		StartActivity(ActMosaico)
		Activity.Finish 
		
		'Activity.Finish 
		'If Conectado= False Then IniciarConexionCentral(CentraltoConnect)
	Else If ItemNo>199 And  ItemNo < 299 Then
	 
	
	Else
	
		
		Select Case ItemNo
		
		 Case 1
		 	StartActivity(ActMosaico)
			Activity.Finish 
		 Case 2
		 	StartActivity(ActCircuit)
			Activity.Finish 
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
		 	'StartActivity(ActSensors)
			'Activity.Finishf 
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
		  Case 98
		 	StartActivity(ActSelectSensores)
			Activity.Finish 
		End Select
	End If
	'ToastMessageShow("Item clicked = " & ItemNo, False)
End Sub


Sub ListView1_ItemLongClick (Position As Int, Value As Object)
		Dim dialog As InputDialog
		
		dialog.InputType =  dialog.INPUT_TYPE_TEXT 
		dialog.Input = ValoresComunes.Sensores (Value).Nombre
		Dim Result As Int
		Result = dialog.Show ( ValoresComunes.GetLanString ("reg111"), ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
		If Result=  DialogResponse.POSITIVE  Then
			ValoresComunes.Sensores (Value).Nombre=dialog.Input 
			ValoresComunes.GuardarConfigSensores
			ActualizaValores
		End If
	
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
				StartActivity(ActCircuit)
				Activity.Finish
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
				'StartActivity(ActSensors)
				'Activity.Finish
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
				StartActivity(ActSelectSensores)
				Activity.Finish
		End Select
	End If
	'SlMenu.BarsOff
	'ToastMessageShow("Item clicked = " & ItemNo, False)
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
Sub btnShow_Click
	sm.Show
End Sub