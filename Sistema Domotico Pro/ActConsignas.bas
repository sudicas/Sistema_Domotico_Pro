Type=Activity
Version=7.01
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
#End Region

'Activity module
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim UDPSocket1 As UDPSocket 
	'Dim CentralCargada As Int
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Dim ListView1 As ListView
	Dim sm As SlideMenu
	'Dim NombreConsginas As List 
	Dim Valores(10) As Int 
	
	
	Dim TramaEnviada() As Byte
	Dim ImgCargando As ImageView 
	Dim AniCargando As  Animation
	
	Dim PaqueteEnviado As Boolean
End Sub
Sub Activity_Create(FirstTime As Boolean)
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	Activity.LoadLayout ("frmadministrador")
	'Activity.LoadLayout("FrmBase")
	Activity.Title =ValoresComunes.GetLanString ("reg133")
	
	
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
	
	sm.Initialize(Activity, Me,  "SlideMenu", 42dip, 250dip)
	ValoresComunes.BuldMenus (sm,9)
		
End Sub

Sub Activity_Resume	
	If ValoresComunes.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then
		
	
		If ValoresComunes.SetPoint .Size >0 Then
			If ImgCargando.IsInitialized = False Then
				ImgCargando.Initialize ("ImgCargando")
				ImgCargando.Bitmap = ValoresComunes.Cargando 
				ImgCargando.Gravity = Gravity.FILL 
				Activity.AddView (ImgCargando,  PerXToCurrent(40)	, PerYToCurrent(35),PerXToCurrent(20) ,PerXToCurrent(20) )
			End If
			If UDPSocket1.IsInitialized = False Then  ValoresComunes.IniUDP(UDPSocket1) 
			AniCargando.InitializeRotateCenter( "AniCargando", 0, 180, ImgCargando)
			AniCargando.Duration = 1000
		    AniCargando.RepeatCount =5
		    AniCargando.RepeatMode = AniCargando.REPEAT_REVERSE  

			
			
			Dim T As String 
			If ValoresComunes.central.ConexionSegura Then
				T="SETPOINT" & ValoresComunes.Central.Password   
			Else
				T="SETPOINT" 
			End If

			SendTrama(T.GetBytes ("UTF8") )
		Else 
			Activity.Finish 
			StartActivity(Main)
		End If
	
	
		
		
	Else
		Activity.Finish 
		StartActivity(Main)
	End If
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	
	UDPSocket1.Close 
	Activity.Finish 
End Sub




Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim Spo As Sp
	Spo= ValoresComunes.SetPoint.Get  (Position)


	Dim Dialogo As NumberDialog 
	Dim Ma As String = Spo.Maximo 
	
	Dialogo.Digits =Ma.Length 
	
	
	Dialogo.Number = Valores(Position)
	
	Dim Result As Int
	Result = Dialogo.Show(ValoresComunes.GetLanStringDefault("reg90","New Value"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)	
	
	If Result = DialogResponse.POSITIVE Then
		
		
		If Dialogo.number < Spo.Maximo  +1  And   Dialogo.number > Spo.Minimo -1 Then
			Valores(Position)= Dialogo.Number 
			Dim LongTrama As Int 
			If ValoresComunes.central.ConexionSegura Then
				LongTrama= 15
			Else
				LongTrama= 7
			End If
			
			Dim data(LongTrama) As Byte
			data(0)= 87	'W
			data(1)= 67	'C
			data(2)= 79	'O
			data(3)= 87	'W
			data(4)= Position + 1
			
			Dim VAL As Short =Bit.And( Dialogo.Number ,  0xFF00 )
			VAL=Bit.ShiftRight (VAL,8)
			data(5)= VAL
			If data(5)<255 Then data(5)=data(5)+1
			
			data(6)= Bit.And( Dialogo.Number ,  0x00FF )'
			If data(6)<255 Then data(6)=data(6)+1
			'Dialogo.Number + 1
			'Dialogo.Number 
			
			
			'FloatsToBytes (vals() As Float) As Byte()
		
			If ValoresComunes.central.ConexionSegura Then ValoresComunes.CompletarTrama (data)
			SendTrama(data)
		Else
			Msgbox(ValoresComunes.GetLanString ("reg142"),ValoresComunes.GetLanString ("reg120"))
		End If
	End If
			
End Sub

Sub RefrescaPantalla
	ListView1.Clear 
	Dim c As Int 
	For c=0 To ValoresComunes.SetPoint .Size -1
		Dim s As Sp
		s= ValoresComunes.SetPoint.get (c)
		ListView1.AddTwoLinesAndBitmap2 ( s.Nombre  ,Valores(c), ValoresComunes.icon (s.Icon ),  c)
		
	Next

	
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
Sub SendTrama(data() As Byte )
	Dim Resultado As Boolean 
	Dim Reintento As Int

	AniCargando.Start (ImgCargando)
	PaqueteEnviado =False
	ListView1.Enabled =False	
	
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
Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
		Dim msg As String
	    msg = BytesToString(Packet.data, Packet.Offset, Packet.Length, "UTF8")
		
		If msg.Contains ("SEPOI") Then
			
			'Dim p As Int
			'For p =5 To Packet.Length -1
			'	Valores(p-5)=Packet.data (p)-1
				
			'	If Valores(p-5) < 0 Then
			'		Valores(p-5)=Valores(p-5) +256
			'	End If
			'Next
			
			Dim c As Int 
			Dim s As Int 
			s=0
			c=5
			Do While c<  Packet.Length
	
				Dim B1 As Short 
				Dim B2 As Short 
				
				
				B1= Bit.And (Packet.data (c) , 0xff)   
				If B1<255 Then B1=B1-1
				
						
			    'Valores (s) =Bit.AND(B1,0xFF)
				c=c+1
				
				B2=Bit.And (Packet.data (c) , 0xff) 
				If B2<255 Then B2=B2-1
				c=c+1
				
				B1= Bit.Or(Bit.ShiftLeft ( B2,8),B1)
				Valores (s) =B1
				
				'Data(i) = Bit.AND(Value,0xFF)
				'DaVta(i+1) = Bit.AND(Bit.ShiftRight(Value,8),0xFF)
				
				s=s+1
				
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

		ListView1.Enabled =True
		AniCargando.Stop(ImgCargando)
		ImgCargando.Visible =False 
	Catch
	    
	End Try
	    
End Sub 
Sub AniCargando_AnimationEnd
	
   If PaqueteEnviado = True Then 
		AniCargando.Start (ImgCargando)
		SendTrama(TramaEnviada)
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
				'StartActivity(ActConsignas)
				'Activity.Finish
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