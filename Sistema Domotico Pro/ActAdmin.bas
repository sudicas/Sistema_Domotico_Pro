Type=Activity
Version=4.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region Module Attributes
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

	Dim ListView1 As ListView
	
	Dim TramaEnviada() As Byte 
End Sub

Sub Activity_Create(FirstTime As Boolean)
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
	Activity.LoadLayout ("FrmAdministrador")
	Activity.Title =ValoresComunes.GetLanString ("reg104")
	ListView1.Height = Activity.Height 
	ListView1.Width =Activity.Width 


	'UDPSocket1.Initialize("UDP", 0, 8000)

	
End Sub

Sub Activity_Resume
	If ValoresComunes.Centrales .IsInitialized = True Then		
		If Main.MyLan.IsInitialized = False Then Main.MyLan.Initialize( 0, "")
		If UDPSocket1.IsInitialized = False Then  ValoresComunes.IniUDP(UDPSocket1) 
		ActualizaPantalla	
	Else
		Activity.Finish 
		StartActivity(Main)
	End If
End Sub
Sub ActualizaPantalla
	ListView1.Clear 
	
	'ListView1.AddTwoLines  ("Arduino IP address  In", ValoresComunes.IpIn  )
	'ListView1.AddTwoLines ("Arduino IP address  Out",ValoresComunes.IpOut )
	'ListView1.AddTwoLines ("Set In Port",ValoresComunes.PuertoIn )
	'ListView1.AddTwoLines ("Set Out Port",ValoresComunes.PuertoOut )
	ListView1.AddSingleLine (ValoresComunes.GetLanString ("reg97"))
	ListView1.AddSingleLine (ValoresComunes.GetLanString ("reg98"))
	ListView1.AddSingleLine (ValoresComunes.GetLanString ("reg99"))
	ListView1.AddTwoLines (ValoresComunes.GetLanString ("reg100"), ValoresComunes.Mail )
	ListView1.AddSingleLine (ValoresComunes.GetLanString ("reg101") )
	If ValoresComunes.central.ConexionSegura Then
		ListView1.AddSingleLine (ValoresComunes.GetLanString ("reg102"))
	Else 
		ListView1.AddSingleLine (ValoresComunes.GetLanString ("reg103"))
	End If
	
	
	
	
End Sub
Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub ListView1_ItemClick (Position As Int, Value As Object)
	Select Position
		
			
		Case 0:'Borrar Timetable
			Dim Rsp As Int 
			Rsp=Msgbox2(ValoresComunes.GetLanString ("reg110"),ValoresComunes.GetLanString ("reg77"),ValoresComunes.GetLanString ("Y"),ValoresComunes.GetLanString ("N"),"",Null  )
			If Rsp=  DialogResponse.POSITIVE Then
				Dim S As String
				If ValoresComunes.central.ConexionSegura Then
					S="CLEARHORARIO" & ValoresComunes.Central.Password  
				Else
					S="CLEARHORARIO" 
				End If
				
				Dim DATA() As Byte 
				DATA= S.GetBytes ("UTF8")
				SendTrama(DATA)
				ProgressDialogShow(ValoresComunes.GetLanString ("reg109"))
			End If

			
		Case 1:'Borrar Dias Especiales
			Dim Rsp As Int 
			Rsp=Msgbox2(ValoresComunes.GetLanString ("reg108"),ValoresComunes.GetLanString ("reg77"),ValoresComunes.GetLanString ("Y"),ValoresComunes.GetLanString ("N"),"",Null )
			If Rsp=  DialogResponse.POSITIVE Then
				Dim S As String
				If ValoresComunes.central.ConexionSegura Then
					S="CLEARESPCDAY" & ValoresComunes.Central.Password  
				Else
					S="CLEARESPCDAY" 
				End If
				
				Dim DATA() As Byte 
				DATA= S.GetBytes ("UTF8")
				SendTrama(DATA)
				ProgressDialogShow(ValoresComunes.GetLanString ("reg109"))
			End If
			
		Case 2:'Configurar Circuitos
			StartActivity(ActConfigCir)
			
		Case 3:'Mail
			Dim Dialog As InputDialog 
		
			Dialog.InputType =  Dialog.INPUT_TYPE_TEXT 
			Dialog.Input = ValoresComunes.Mail  
			Dim Result As Int
			Result = Dialog.Show ( ValoresComunes.GetLanString ("reg106"),ValoresComunes.GetLanString ("reg104"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
			If Result=  DialogResponse.POSITIVE  Then
				ValoresComunes.Mail  =Dialog.Input 
				ValoresComunes.GuardaConexionExterna 
				ActualizaPantalla
			End If
			
		Case 4	'Password
			Dim Dialog As InputDialog 
		
			Dialog.InputType =  Dialog.INPUT_TYPE_TEXT 
			Dialog.Input = ValoresComunes.Central.Password     
			Dim Result As Int
			Result = Dialog.Show ( ValoresComunes.GetLanString ("reg105"),ValoresComunes.GetLanString ("reg104"),ValoresComunes.GetLanString ("reg83"),ValoresComunes.GetLanString ("Cancel"),"",Null)
			If Result=  DialogResponse.POSITIVE  Then
				ValoresComunes.Central.Password     =Dialog.Input 
				ValoresComunes.GuardaConexionExterna
				ValoresComunes.PassswordByte =ValoresComunes.Central.Password  .GetBytes ("UTF8")	
				ActualizaPantalla
			End If
		
			
		Case 5	'Conexion Segura	
			Dim lst As List 
			lst.Initialize 
			lst.Add ("ON")
			lst.Add ("OFF")
			Dim Val As Int 
			If ValoresComunes.central.ConexionSegura Then
				Val= InputList(lst,ValoresComunes.GetLanString ("reg107"),  0)
			Else 
				Val= InputList(lst,ValoresComunes.GetLanString ("reg107"),  1)
			End If 
			If Val = 0  Then ValoresComunes.central.ConexionSegura = True 
			If Val=1 Then ValoresComunes.central.ConexionSegura = False
			ValoresComunes.GuardaConexionExterna 
			ActualizaPantalla
	End Select	
End Sub
Sub SendTrama(data() As Byte )
	Dim Resultado As Boolean 
	Dim Reintento As Int
	
	TramaEnviada=data
	
	
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
Sub UDP_PacketArrived (Packet As UDPPacket)
	Try
	    Dim msg As String
	    msg = BytesToString(Packet.Data, Packet.Offset, Packet.Length, "UTF8")
		If msg ="REPETIRMSG" Then
				SendTrama(TramaEnviada)
			Else
				If msg.Contains ("BORRADOS") Then ProgressDialogHide
				ToastMessageShow(ValoresComunes.GetLanString ("reg139"),False)
		End If	
	
	Catch
	    
	End Try

End Sub 


	
