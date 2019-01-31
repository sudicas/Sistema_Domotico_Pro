Type=StaticCode
Version=7.01
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim CloseApp As Boolean 
	
	Type Sp(Nombre As String , Minimo As Int, Maximo As Int, Icon As Int)
	Type Scene(Nombre As String , Descripcion As String)
	Type Circuito(Nombre As String , Descripcion As String, Rango As Int, Value As Int,DeviceNumber As Int )
	Type Arduino(Nombre As String, Descripcion As String , IpIn As String , IpOut As String , PuertoIn As Int, PuertoOut As Int , Icon As Int, mail As String, Password As String , ConexionSegura As Boolean )
'	Type Camara (Nombre As String,IpIN As String, Puerto As Int, User As String, pass As String , Type As Byte  )
	
	
	Dim OldMail As String  
	Dim OldPass As String 
	Dim OldExtIp As String 
	
	Dim Central As Arduino
	Dim CentralesImg As Map 
	Dim LanString As Map 
	Dim Scenes(10) As Scene
	Dim Condicionados(10) As Scene
	Dim Functions(10) As Scene
	Dim SetPoint As List 
	
	
	Dim ComandosComunes As List
	Dim DescripcionesComandosComunes As List
	Dim Comandos As List
	Dim DescripcionesComandos As List
	Dim NombreSensor As List 

	
	Dim Circuitos(30) As Circuito
	Dim Sensores(15) As Circuito 
	
	Dim Ip As String
	Dim Puerto As Int 
	
	Dim AlmName As List 
	'Dim IpExterna As String
	
	
	
	Dim PassswordByte() As Byte 
	
	'Dim Central.nombre As String
	Dim Centrales As List 
	'Dim Versiones As Map 
	
	Dim ImgBombillaAl, ImgVentiladorAl, ImgValvulaAl, ImgRiegoAl, ImgPuertaAl, ImgPilotoAl, ImgAcAl, ImgEnchufeAl, ImgCalefaccionAl As Bitmap 
	Dim ImgConNames, ImgDelete, ImgScene2,ImgAlarma,ImgSensor,ImgVoice,ImgCargando,ImgReloj,ImgreloOk,ImgCalendario,ImgIcono,Imgfechahora,ImgConfigUp,Imgconfigdo,ImgOn,ImgComando,ImgConfig,ImgArduino,ImgCheckOn, ImgCheckOff , ImgBombillaOff, ImgBombillaOn, ImgBombillades, ImgEnchufedes, ImgEnchufeOff, ImgEnchufeOn,ImgPuertades,ImgPuertaOff,ImgPuertaOn,ImgRiegodes,ImgRiegoOff,ImgRiegoOn,ImgAcondicionadodes,ImgAcondicionadoOff,ImgAcondicionadoOn,ImgCalefacciondes,ImgCalefaccionOff,ImgCalefaccionOn,ImgTemperaturades,ImgTemperatura,ImgToldodes,ImgToldooff,ImgToldoOn,Imgpersianades,Imgpersiana As  Bitmap
	Dim ImgChart,imgFunciones,ImgAlmOn,ImgAlmOff,ImgAlmDes,ImgValvulaOn,ImgValvulaOff,ImgValvulaDes,ImgVentiladorOn,ImgVentiladorOff,ImgVentiladorDes,ImgPilotoOn, ImgPilotoOff, ImgPilotoDes As Bitmap 
	Dim ImgDepositoDes, ImgDeposito, ImgSensorTemp, ImgSensorHumedad,ImgSensorHumedadd, ImgLux,ImgLuxd,  ImgSensor2,ImgSensor2d, ImgSalir As Bitmap 
	
	
		'menu navegación
	'*****************************************************************
	Dim ImgFC,ImgSetPointLt,ImgMnuCir, ImgMnuAl, ImgMnuCon, ImgMnuHome,ImgMnuScene , ImgMnuCom , ImgMnuSensor , ImgMnu , ImgFreeTxt, ImgConfigLt As Bitmap 
	
	'iconos centrales
	Dim ImgHome,ImgSalon,ImgCama,ImgBaño,ImgGarden,ImgCar,ImgCocina As Bitmap 
	Dim ImgSalonlt,ImgCamalt,ImgBañolt,ImgGardenlt,ImgCarlt,ImgCocinalt,ImgMnuEnd As Bitmap 

	'ICONOS BOTOENS
	Dim ImgSave, ImgBack, ImgNew As Bitmap
	
End Sub
Sub GuardaSetpoint
	Dim C As Int
	Dim lname  As List 
	Dim lmax As List
	Dim lmin As List 
	Dim licon As List 
	
	lname.Initialize
	lmax.Initialize 
	lmin.Initialize 
	licon.Initialize 
	
	Dim s As Sp
	
	For C =0 To SetPoint.Size -1
		s=SetPoint.Get (C)
		lname.Add (s.Nombre )
		lmax.Add (s.Maximo )
		lmin.Add (s.Minimo )
		licon.Add (s.Icon )
	Next
	File.WriteList ( File.DirInternal ,"Consignas" & Central.nombre ,lname  ) 
	File.WriteList ( File.DirInternal ,"ConsignasMax" & Central.nombre ,lmax  ) 
	File.WriteList ( File.DirInternal ,"ConsignasMin" & Central.nombre ,lmin  ) 
	File.WriteList ( File.DirInternal ,"ConsignasIcon" & Central.nombre ,licon  ) 
End Sub

Sub CargaSetPoint
	If SetPoint.IsInitialized =False Then 
		SetPoint.Initialize 
	Else
		SetPoint.Clear 
	End If
	
	Dim l As List
	Dim C As Int
	
	If  File.Exists ( File.DirInternal ,"Consignas" & Central.nombre   )  Then
		
		l = File.ReadList (File.DirInternal ,"Consignas" & Central.nombre   )
		
		
		For C=0 To l.Size -1
			'Type Sp(Nombre As String , Minimo As Int, Maximo As Int, Icon As Int)
			Dim s As  Sp
			s.Nombre =l.Get (C)
			s.Maximo =65000
			s.Minimo =0
			'IconoSensor(i)
			s.Icon =4
			'IconoSensor(i)
			'If Sensores(Number).Rango = 1 Then 	Return SensorTemp
			'If Sensores(Number).Rango = 2 Then Return SensorHumedad
			' If Sensores(Number).Rango = 3 Then Return SensorLux
			SetPoint.Add (s)
		Next	
	End If
	
	If  File.Exists ( File.DirInternal ,"ConsignasMax" & Central.nombre   )  Then
		
		l = File.ReadList (File.DirInternal ,"ConsignasMax" & Central.nombre   )
		
		
		For C=0 To l.Size -1
			If SetPoint.Size > C Then 
				Dim e As Sp
				e= SetPoint.Get (C)
				e.Maximo = l.Get (C)
			
			End If			
		Next	
	End If
	
	If  File.Exists ( File.DirInternal ,"ConsignasMin" & Central.nombre   )  Then
		
		l = File.ReadList (File.DirInternal ,"ConsignasMin" & Central.nombre   )
		
		
		For C=0 To l.Size -1
			If SetPoint.Size > C Then 
				Dim e As Sp
				e= SetPoint.Get (C)
				e.Minimo  = l.Get (C)
			
			End If			
		Next	
	End If
	
	If  File.Exists ( File.DirInternal ,"ConsignasIcon" & Central.nombre   )  Then
		
		l = File.ReadList (File.DirInternal ,"ConsignasIcon" & Central.nombre   )
		
		
		For C=0 To l.Size -1
			If SetPoint.Size > C Then 
				Dim e As Sp
				e= SetPoint.Get (C)
				e.Icon   = l.Get (C)
			
			End If			
		Next	
	End If
	
	
	
End Sub
Sub GetImgDevice(Name As String, Big As Boolean  ) As Bitmap 
	If CentralesImg.IsInitialized =False Then 	CentralesImg.Initialize
	If CentralesImg.ContainsKey (Name) Then
	
		Return GetCentralImg(CentralesImg.Get (Name),  Big )
	Else
		Return GetCentralImg(0, Big )
	End If
	
		 
End Sub
Sub SetImgDevice(Name As String, Value As Int ) 
	ActMosaico.UpdateCentral=True
	If CentralesImg.IsInitialized =False Then  
		CentralesImg.Initialize
		
	End If
	CentralesImg.Put (Name,Value)
End Sub
Sub IniciarIconosCentrales

	Dim l As List 
			
	For li = 0 To Centrales.Size -1
		
		'If File.Exists (File.DirInternal , + "Conect") Then
		If File.Exists ( File.DirInternal ,"Conect" & Centrales.Get (li))   Then
			l= File.ReadList (File.DirInternal ,"Conect" & Centrales.Get (li))
			SetImgDevice (Centrales.Get (li),l.Get (5))
			'ListView1.AddTwoLinesAndBitmap (ValoresComunes.Centrales .Get(li),ValoresComunes.GetLanString ("PC"),ValoresComunes.GetCentralImg(l.Get (5), True)	 )
		
		Else 
			SetImgDevice (Centrales.Get (li),0)
		End If			
	Next
End Sub
Sub IniUDP(UDPSocket1 As UDPSocket)
	
	Dim Resultado As Boolean 
	Dim Reintento As Int

	
	Do 	While Resultado= False And Reintento < 40
		Resultado = IntentoIniUDP(UDPSocket1)
		Reintento = Reintento +1 
		If Resultado=False Then Sleep (200)
	Loop

End Sub

Sub IntentoIniUDP(UDPSocket1 As UDPSocket) As Boolean 
	Try
	    'UDPSocket1.Initialize("UDP"  , 0 , 8000 )
		UDPSocket1.Initialize("UDP"   ,  0  , 500)
		Return True
	Catch
	    Return False
	End Try
End Sub


Sub CreaArchivoConfigIdioma
	
	Dim Lst As List 
	Lst.Initialize 
	Lst.Add ("en")
	Lst.Add ("MODE")	'PALABRA PARA SELECCION ESCENA
	Lst.Add ("ON") 'Palbra para activar
	Lst.Add ("OFF")
	Lst.Add ("UP")
	Lst.Add ("DOWN")

	File.WriteList(File.DirInternal ,"ConfigIdioma"  ,Lst)
End Sub

Sub ConfigNames()As Bitmap 
	If ImgConNames.IsInitialized = False Then ImgConNames.Initialize( File.DirAssets, "configName.png")
	Return ImgConNames	
End Sub 
Sub SensorHumedad()As Bitmap 
	If ImgSensorHumedad.IsInitialized = False Then ImgSensorHumedad.Initialize( File.DirAssets, "s_humedad.png")
	Return ImgSensorHumedad	
End Sub 
Sub SensorHumedaddes()As Bitmap 
	If ImgSensorHumedadd.IsInitialized = False Then ImgSensorHumedadd.Initialize( File.DirAssets, "s_humedaddes.png")
	Return ImgSensorHumedadd
End Sub
Sub MnuCircuitos()As Bitmap 
	If ImgMnuCir.IsInitialized = False Then ImgMnuCir.Initialize( File.DirAssets, "cir.png")
	Return 	ImgMnuCir
End Sub
Sub MnuFuncionEsp() As Bitmap
	'ImgFC

	If ImgFC.IsInitialized = False Then ImgFC.Initialize( File.DirAssets, "fep.png")
	Return 	ImgFC
End Sub
Sub MnuSetPointLt()As Bitmap 
	If ImgSetPointLt.IsInitialized = False Then ImgSetPointLt.Initialize( File.DirAssets, "set.png")
	Return 	ImgSetPointLt
End Sub
Sub MnuEnd()As Bitmap 
	If ImgMnuEnd.IsInitialized = False Then ImgMnuEnd.Initialize( File.DirAssets, "mnuend.png")
	Return 	ImgMnuEnd
End Sub
Sub MnuConfigLt()As Bitmap 
	If ImgConfigLt.IsInitialized = False Then ImgConfigLt.Initialize( File.DirAssets, "SettingLt.png")
	Return 	ImgConfigLt
End Sub
Sub MnuTxt()As Bitmap 
	If ImgFreeTxt.IsInitialized = False Then ImgFreeTxt.Initialize( File.DirAssets, "freeTxtt.png")
	Return 	ImgFreeTxt
End Sub

Sub MnuAlarmas()As Bitmap 
	If ImgMnuAl.IsInitialized = False Then ImgMnuAl.Initialize( File.DirAssets, "ala.png")
	Return 	ImgMnuAl
End Sub
Sub MnuCondicionado()As Bitmap 
	If ImgMnuCon.IsInitialized = False Then ImgMnuCon.Initialize( File.DirAssets, "cond.png")
	Return 	ImgMnuCon
End Sub

Sub MnuHome()As Bitmap 
	If ImgMnuHome.IsInitialized = False Then ImgMnuHome.Initialize( File.DirAssets, "hom.png")
	Return 	ImgMnuHome
End Sub
Sub MnuScene()As Bitmap 
	If ImgMnuScene.IsInitialized = False Then ImgMnuScene.Initialize( File.DirAssets, "esc.png")
	Return 	ImgMnuScene
End Sub
Sub MnuComandos()As Bitmap 
	If ImgMnuCom.IsInitialized = False Then ImgMnuCom.Initialize( File.DirAssets, "com.png")
	Return 	ImgMnuCom
End Sub



Sub MnuSensor()As Bitmap 
	If ImgMnuSensor.IsInitialized = False Then ImgMnuSensor.Initialize( File.DirAssets, "sen.png")
	Return 	ImgMnuSensor
End Sub
Sub MnuImg()As Bitmap 
	If ImgMnu.IsInitialized = False Then ImgMnu.Initialize( File.DirAssets, "acess.png")
	Return 	ImgMnu
End Sub


Sub RiegoAl()As Bitmap 
	If ImgRiegoAl.IsInitialized = False Then ImgRiegoAl.Initialize( File.DirAssets, "riegoAl.png")
	Return 	ImgRiegoAl
End Sub
Sub PuertaAl()As Bitmap 
	If ImgPuertaAl.IsInitialized = False Then ImgPuertaAl.Initialize( File.DirAssets, "puertaAl.png")
	Return 	ImgPuertaAl
End Sub
Sub PilotoAl()As Bitmap 
	If ImgPilotoAl.IsInitialized = False Then ImgPilotoAl.Initialize( File.DirAssets, "Al.png")
	Return 	ImgPilotoAl
End Sub
Sub EnchufeAl()As Bitmap 
	If ImgEnchufeAl.IsInitialized = False Then ImgEnchufeAl.Initialize( File.DirAssets, "enchufeAl.png")
	Return 	ImgEnchufeAl
End Sub
Sub AcondicionadoAl()As Bitmap 
	If ImgAcAl.IsInitialized = False Then ImgAcAl.Initialize( File.DirAssets, "acondicionadoAl.png")
	Return 	ImgAcAl
End Sub
Sub CalefaccionAl()As Bitmap 
	If ImgCalefaccionAl.IsInitialized = False Then ImgCalefaccionAl.Initialize( File.DirAssets, "calefaccionAl.png")
	Return 	ImgCalefaccionAl
End Sub

Sub ValvulaAl()As Bitmap 
	If ImgValvulaAl.IsInitialized = False Then ImgValvulaAl.Initialize( File.DirAssets, "valvulaAl.png")
	Return ImgValvulaAl	
End Sub

Sub VentiladorAl()As Bitmap 
	If ImgVentiladorAl.IsInitialized = False Then ImgVentiladorAl.Initialize( File.DirAssets, "ventiladoral.png")
	Return ImgVentiladorAl	
End Sub
Sub BombillaAl()As Bitmap 
	If ImgBombillaAl.IsInitialized = False Then ImgBombillaAl.Initialize( File.DirAssets, "bombillaal.png")
	Return ImgBombillaAl	
End Sub

Sub SensorTemp()As Bitmap 
	If ImgSensorTemp.IsInitialized = False Then ImgSensorTemp.Initialize( File.DirAssets, "s_temperatura.png")
	Return ImgSensorTemp	
End Sub 
Sub SensorLux()As Bitmap 
	If ImgLux.IsInitialized = False Then ImgLux.Initialize( File.DirAssets, "lux.png")
	Return ImgLux	
End Sub 
Sub SensorLuxDes()As Bitmap 
	If ImgLuxd.IsInitialized = False Then ImgLuxd.Initialize( File.DirAssets, "luxdes.png")
	Return ImgLuxd	
End Sub
Sub SensorGenericoDes()As Bitmap 
	If ImgSensor2d.IsInitialized = False Then ImgSensor2d.Initialize( File.DirAssets, "s_genericodes.png")
	Return ImgSensor2d	
End Sub 
Sub SensorGenerico()As Bitmap 
	If ImgSensor2.IsInitialized = False Then ImgSensor2.Initialize( File.DirAssets, "s_generico.png")
	Return ImgSensor2	
End Sub 
Sub SensorDepositoDes()As Bitmap 
	If ImgDepositoDes.IsInitialized = False Then ImgDepositoDes.Initialize( File.DirAssets, "DepositoOnDes.png")
	Return ImgDepositoDes	
End Sub 
Sub SensorDeposito()As Bitmap 
	If ImgDeposito.IsInitialized = False Then ImgDeposito.Initialize( File.DirAssets, "DepositoOn.png")
	Return ImgDeposito	
End Sub 


Sub ImgGrafico()As Bitmap 
	If ImgChart.IsInitialized = False Then ImgChart.Initialize( File.DirAssets, "chart.png")
	Return ImgChart	
End Sub
Sub Funciones()As Bitmap 
	If imgFunciones.IsInitialized = False Then imgFunciones.Initialize( File.DirAssets, "funciones.png")
	Return imgFunciones	
End Sub 

Sub CheckOn()As Bitmap 
	If ImgCheckOn.IsInitialized = False Then ImgCheckOn.Initialize( File.DirAssets, "CheckOn.png")
	Return ImgCheckOn	
End Sub 
Sub CheckOff()As Bitmap 
	If ImgCheckOff.IsInitialized = False Then ImgCheckOff.Initialize(File.DirAssets, "CheckOff.png")
	Return 	ImgCheckOff
End Sub 

Sub BombillaOff()As Bitmap 
	If ImgBombillaOff.IsInitialized = False Then ImgBombillaOff.Initialize(File.DirAssets, "BombillaOff.png")
	Return 	ImgBombillaOff
End Sub 
Sub BombillaOn()As Bitmap 
	If ImgBombillaOn.IsInitialized = False Then ImgBombillaOn.Initialize(File.DirAssets, "BombillaOn.png")
	Return 	ImgBombillaOn
End Sub 
Sub Bombillades()As Bitmap 
	If ImgBombillades.IsInitialized = False Then ImgBombillades.Initialize(File.DirAssets, "BombillaDes.png")
	Return 	ImgBombillades
End Sub 
Sub Enchufedes()As Bitmap 
	If ImgEnchufedes.IsInitialized = False Then ImgEnchufedes.Initialize(File.DirAssets, "EnchufeDes.png")
	Return 	ImgEnchufedes
End Sub 
Sub EnchufeOff()As Bitmap 
	If ImgEnchufeOff.IsInitialized = False Then ImgEnchufeOff.Initialize(File.DirAssets, "EnchufeOff.png")
	Return 	ImgEnchufeOff
End Sub 
Sub EnchufeOn()As Bitmap 
	If ImgEnchufeOn.IsInitialized = False Then ImgEnchufeOn.Initialize(File.DirAssets, "EnchufeOn.png")
	Return 	ImgEnchufeOn
End Sub 

Sub Puertades()As Bitmap 
	If ImgPuertades.IsInitialized = False Then ImgPuertades.Initialize(File.DirAssets, "PuertaDes.png")
	Return 	ImgPuertades
End Sub 
Sub PuertaOff()As Bitmap 
	If ImgPuertaOff.IsInitialized = False Then ImgPuertaOff.Initialize(File.DirAssets, "PuertaOff.png")
	Return 	ImgPuertaOff
End Sub 
Sub PuertaOn()As Bitmap 
	If ImgPuertaOn.IsInitialized = False Then ImgPuertaOn.Initialize(File.DirAssets, "PuertaOn.png")
	Return 	ImgPuertaOn
End Sub 
Sub Riegodes()As Bitmap 
	If ImgRiegodes.IsInitialized = False Then ImgRiegodes.Initialize(File.DirAssets, "RiegoDes.png")
	Return 	ImgRiegodes
End Sub 
Sub RiegoOff()As Bitmap 
	If ImgRiegoOff.IsInitialized = False Then ImgRiegoOff.Initialize(File.DirAssets, "RiegoOff.png")
	Return 	ImgRiegoOff
End Sub 

Sub RiegoOn()As Bitmap 
	If ImgRiegoOn.IsInitialized = False Then ImgRiegoOn.Initialize(File.DirAssets, "RiegoOn.png")
	Return 	ImgRiegoOn
End Sub 
Sub Acondicionadodes()As Bitmap 
	If ImgAcondicionadodes.IsInitialized = False Then ImgAcondicionadodes.Initialize(File.DirAssets, "AcondicionadoDes.png")
	Return 	ImgAcondicionadodes
End Sub 

Sub AcondicionadoOff()As Bitmap 
	If ImgAcondicionadoOff.IsInitialized = False Then ImgAcondicionadoOff.Initialize(File.DirAssets, "AcondicionadoOff.png")
	Return 	ImgAcondicionadoOff
End Sub 

Sub AcondicionadoOn()As Bitmap 
	If ImgAcondicionadoOn.IsInitialized = False Then ImgAcondicionadoOn.Initialize(File.DirAssets, "AcondicionadoOn.png")
	Return 	ImgAcondicionadoOn
End Sub 
Sub Calefacciondes()As Bitmap 
	If ImgCalefacciondes.IsInitialized = False Then ImgCalefacciondes.Initialize(File.DirAssets, "CalefaccionDes.png")
	Return 	ImgCalefacciondes
End Sub 
Sub CalefaccionOff()As Bitmap 
	If ImgCalefaccionOff.IsInitialized = False Then ImgCalefaccionOff.Initialize(File.DirAssets, "CalefaccionOff.png")
	Return 	ImgCalefaccionOff
End Sub 

Sub CalefaccionOn()As Bitmap 
	If ImgCalefaccionOn.IsInitialized = False Then ImgCalefaccionOn.Initialize(File.DirAssets, "CalefaccionOn.png")
	Return 	ImgCalefaccionOn
End Sub 
Sub Temperatura()As Bitmap 
	If ImgTemperatura.IsInitialized = False Then ImgTemperatura.Initialize(File.DirAssets, "Temperatura.png")
	Return ImgTemperatura	
End Sub 
Sub Temperaturades()As Bitmap 
	If ImgTemperaturades.IsInitialized = False Then ImgTemperaturades.Initialize(File.DirAssets, "TemperaturaDes.png")
	Return 	ImgTemperaturades
End Sub 
Sub ToldoOn()As Bitmap 
	If ImgToldoOn.IsInitialized = False Then ImgToldoOn.Initialize(File.DirAssets, "ToldoOn.png")
	Return 	ImgToldoOn
End Sub 
Sub Toldooff()As Bitmap 
	If ImgToldooff.IsInitialized = False Then ImgToldooff.Initialize(File.DirAssets, "Toldooff.png")
	Return 	ImgToldooff
End Sub 
Sub Toldodes()As Bitmap 
	If ImgToldodes.IsInitialized = False Then ImgToldodes.Initialize(File.DirAssets, "Toldodes.png")
	Return 	ImgToldodes
End Sub 
Sub ImgEnd()As Bitmap 
	If ImgSalir.IsInitialized = False Then ImgSalir.Initialize(File.DirAssets, "Salir.png")
	Return 	ImgSalir
End Sub 
Sub Persiana()As Bitmap 
	If Imgpersiana.IsInitialized = False Then Imgpersiana.Initialize(File.DirAssets, "persiana.png")
	Return 	Imgpersiana
End Sub 
Sub persianades()As Bitmap 
	If Imgpersianades.IsInitialized = False Then Imgpersianades.Initialize(File.DirAssets, "persianaDes.png")
	Return 	Imgpersianades
End Sub 
Sub Calendario()As Bitmap 
	If ImgCalendario.IsInitialized = False Then ImgCalendario.Initialize(File.DirAssets, "Calendario.png")
	Return 	ImgCalendario
End Sub 
Sub reloOk()As Bitmap 
	If ImgreloOk.IsInitialized = False Then ImgreloOk.Initialize(File.DirAssets, "reloOk.png")
	Return 	ImgreloOk
End Sub 
Sub Reloj()As Bitmap 
	If ImgReloj.IsInitialized = False Then ImgReloj.Initialize(File.DirAssets, "Reloj.png")
	Return 	ImgReloj
End Sub
Sub fechahora()As Bitmap 
	If Imgfechahora.IsInitialized = False Then Imgfechahora.Initialize(File.DirAssets, "fechahora.png")
	Return 	Imgfechahora
End Sub 
Sub Icono()As Bitmap 
	If ImgIcono.IsInitialized = False Then ImgIcono.Initialize(File.DirAssets, "Icono.png")
	Return 	ImgIcono
End Sub 
Sub configdo()As Bitmap 
	If Imgconfigdo.IsInitialized = False Then Imgconfigdo.Initialize(File.DirAssets, "configdo.png")
	Return 	Imgconfigdo
End Sub 

Sub ConfigUp()As Bitmap 
	If ImgConfigUp.IsInitialized = False Then ImgConfigUp.Initialize(File.DirAssets, "ConfigUp.png")
	Return 	ImgConfigUp
End Sub 
Sub Comando()As Bitmap 
	If ImgComando.IsInitialized = False Then ImgComando.Initialize(File.DirAssets, "Comando.png")
	Return 	ImgComando
End Sub 

Sub On()As Bitmap 
	If ImgOn.IsInitialized = False Then ImgOn.Initialize(File.DirAssets, "On.png")
	Return 	ImgOn
End Sub 
Sub Cargando()As Bitmap 
	If ImgCargando.IsInitialized = False Then ImgCargando.Initialize( File.DirAssets, "cargando.png")
	Return 	ImgCargando
End Sub 

Sub Arduino()As Bitmap 
	If ImgArduino.IsInitialized = False Then ImgArduino.Initialize(File.DirAssets, "Arduino.png")
	Return 	ImgArduino
End Sub 
Sub Config()As Bitmap 
	If ImgConfig.IsInitialized = False Then ImgConfig.Initialize(File.DirAssets, "Config.png")
	Return 	ImgConfig
End Sub 
Sub Sensor()As Bitmap 
	If ImgSensor.IsInitialized = False Then ImgSensor.Initialize(File.DirAssets, "Sensores.png")
	Return 	ImgSensor
End Sub 
Sub Voice()As Bitmap 
	If ImgVoice.IsInitialized = False Then ImgVoice.Initialize(File.DirAssets, "Voice.png")
	Return 	ImgVoice
End Sub 
Sub Alarma()As Bitmap 
	If ImgAlarma.IsInitialized = False Then ImgAlarma.Initialize(File.DirAssets, "Alarma.png")
	Return 	ImgAlarma
End Sub 
Sub ValvulaDes()As Bitmap 
	If ImgValvulaDes.IsInitialized = False Then ImgValvulaDes.Initialize( File.DirAssets , "valvulades.png")
	Return 	ImgValvulaDes
End Sub 
Sub ValvulaOff()As Bitmap 
	If ImgValvulaOff.IsInitialized = False Then ImgValvulaOff.Initialize( File.DirAssets , "valvulaoff.png")
	Return ImgValvulaOff 	
End Sub 
Sub ValvulaOn()As Bitmap 
	If ImgValvulaOn.IsInitialized = False Then ImgValvulaOn.Initialize( File.DirAssets , "valvulaon.png")
	Return 	ImgValvulaOn
End Sub 
Sub VentiladorDes()As Bitmap 
	If ImgVentiladorDes.IsInitialized = False Then ImgVentiladorDes.Initialize( File.DirAssets , "ventiladordes.png")
	Return 	ImgVentiladorDes
End Sub 
Sub VentiladorOff()As Bitmap 
	If ImgVentiladorOff.IsInitialized = False Then ImgVentiladorOff.Initialize( File.DirAssets , "ventiladoroff.png")
	Return 	ImgVentiladorOff
End Sub 
Sub VentiladorOn()As Bitmap 
	If ImgVentiladorOn.IsInitialized = False Then ImgVentiladorOn.Initialize( File.DirAssets , "ventiladoron.png")
	Return 	ImgVentiladorOn
End Sub 
Sub PilotoDes()As Bitmap 
	If ImgPilotoDes.IsInitialized = False Then ImgPilotoDes.Initialize( File.DirAssets , "des.png")
	Return 	ImgPilotoDes
End Sub 
Sub PilotoOff()As Bitmap 
	If ImgPilotoOff.IsInitialized = False Then ImgPilotoOff.Initialize( File.DirAssets , "off.png")
	Return 	ImgPilotoOff
End Sub 
Sub PilotoOn()As Bitmap 
	If ImgPilotoOn.IsInitialized = False Then ImgPilotoOn.Initialize( File.DirAssets , "on.png")
	Return ImgPilotoOn
End Sub 
Sub AlmOn()As Bitmap 
	If ImgAlmOn.IsInitialized = False Then ImgAlmOn.Initialize( File.DirAssets, "alarmaon.png")
	Return ImgAlmOn	
End Sub 
Sub AlmOff()As Bitmap 
	If ImgAlmOff.IsInitialized = False Then ImgAlmOff.Initialize( File.DirAssets, "alarmaoff.png")
	Return 	ImgAlmOff
End Sub 
Sub AlmDes()As Bitmap 
	If ImgAlmDes.IsInitialized = False Then ImgAlmDes.Initialize( File.DirAssets, "alarmades.png")
	Return 	ImgAlmDes
End Sub 
Sub Scene2()As Bitmap 
	If ImgScene2.IsInitialized = False Then ImgScene2.Initialize( File.DirAssets, "scene2.png")
	Return 	ImgScene2
End Sub 
Sub Delete()As Bitmap 
	If ImgDelete.IsInitialized = False Then ImgDelete.Initialize( File.DirAssets, "delete.png")
	Return 	ImgDelete
End Sub 

Sub GetCentralImg(IconNumber As Int, Big As Boolean )As Bitmap 
	If IconNumber>100 Then
		Return Home
	Else
		Select Case IconNumber
		Case 0
			If Big Then
				Return Home
			Else
				Return MnuHome
			End If
		
		Case 1
			If Big Then
				Return Cocina
			Else
				Return Cocinalt
			End If
		Case 2
			If Big Then
				Return Cochera
			Else
				Return Cocheralt
			End If
			
			
		Case 3
			If Big Then
				Return Garden
			Else
				Return Gardenlt
			End If
			
		Case 4
			If Big Then
				Return Aseo
			Else
				Return Aseolt
			End If
			
		Case 5
			If Big Then
				Return Cama
			Else
				Return Camalt
			End If
			
		Case 6
			If Big Then
				Return Salon
			Else
				Return Salonlt
			End If
			

		End Select
		If Big Then
			Return Home
		Else
			Return MnuHome
		End If
	End If
	
End Sub
Sub Cocinalt()As Bitmap 
	If ImgCocinalt.IsInitialized = False Then ImgCocinalt.Initialize( File.DirAssets, "Cocinalt.png")
	Return 	ImgCocinalt
End Sub
'ImgCamlt

Sub Cocheralt()As Bitmap 
	If ImgCarlt.IsInitialized = False Then ImgCarlt.Initialize( File.DirAssets, "Carlt.png")
	Return 	ImgCarlt
End Sub

Sub Gardenlt()As Bitmap 
	If ImgGardenlt.IsInitialized = False Then ImgGardenlt.Initialize( File.DirAssets, "Gardenlt.png")
	Return 	ImgGardenlt
End Sub
Sub Aseolt()As Bitmap 
	If ImgBañolt.IsInitialized = False Then ImgBañolt.Initialize( File.DirAssets, "Baolt.png")
	Return 	ImgBañolt
End Sub
Sub Camalt()As Bitmap 
	If ImgCamalt.IsInitialized = False Then ImgCamalt.Initialize( File.DirAssets, "Camalt.png")
	Return 	ImgCamalt
End Sub 

Sub Salonlt()As Bitmap 
	If ImgSalonlt.IsInitialized = False Then ImgSalonlt.Initialize( File.DirAssets, "Salonlt.png")
	Return 	ImgSalonlt
End Sub 


Sub CmdImgSave()As Bitmap
	If ImgSave.IsInitialized = False Then ImgSave.Initialize( File.DirAssets, "Save.png")
	Return 	ImgSave
End Sub
Sub CmdImgBack()As Bitmap
	If ImgBack.IsInitialized = False Then ImgBack.Initialize( File.DirAssets, "mnu.png")
	Return 	ImgBack
End Sub
Sub CmdImgNew()As Bitmap
	If ImgNew.IsInitialized = False Then ImgNew.Initialize( File.DirAssets, "new.png")
	Return 	ImgNew
End Sub


Sub Cocina()As Bitmap 
	If ImgCocina.IsInitialized = False Then ImgCocina.Initialize( File.DirAssets, "Cocina.png")
	Return 	ImgCocina
End Sub
Sub Cochera()As Bitmap 
	If ImgCar.IsInitialized = False Then ImgCar.Initialize( File.DirAssets, "Car.png")
	Return 	ImgCar
End Sub

Sub Garden()As Bitmap 
	If ImgGarden.IsInitialized = False Then ImgGarden.Initialize( File.DirAssets, "Garden.png")
	Return 	ImgGarden
End Sub
Sub Aseo()As Bitmap 
	If ImgBaño.IsInitialized = False Then ImgBaño.Initialize( File.DirAssets, "Bao.png")
	Return 	ImgBaño
End Sub
Sub Cama()As Bitmap 
	If ImgCama.IsInitialized = False Then ImgCama.Initialize( File.DirAssets, "Cama.png")
	Return 	ImgCama
End Sub 
Sub Home()As Bitmap 
	If ImgHome.IsInitialized = False Then ImgHome.Initialize( File.DirAssets, "Home.png")
	Return 	ImgHome
End Sub 
Sub Salon()As Bitmap 
	If ImgSalon.IsInitialized = False Then ImgSalon.Initialize( File.DirAssets, "Salon.png")
	Return 	ImgSalon
End Sub 

'Type Camara (Nombre As String,IpIn As String, Puerto As String, ConectSting As String )


Sub CargaIp
	Dim lst As List
	If File.Exists ( File.DirInternal ,"Conect" & Central.nombre)   Then
			Dim lst As List 
			lst = File.ReadList(File.DirInternal  ,"Conect" & Central.nombre )
			
			Central.IpIn = lst.Get (0)
		
			Central.ipout=   lst.Get (1)
			Central.PuertoIn = lst.Get (2)
			Central.PuertoOut = lst.Get (3)
			Central.Descripcion =lst.Get (4)
			Central.Icon=lst.Get (5)
			Central.Mail =lst.Get (6)
			Central.Password =lst.Get (7)
			
			
			Central.ConexionSegura =lst.Get (8)
			
			PassswordByte =Central.Password.GetBytes ("UTF8")
			

	Else
	'Apaño eliminar en proxima version
	
		If File.Exists ( File.DirInternal ,"Conexion" & Central.nombre)   Then
										
		lst = File.ReadList(File.DirInternal  ,"Conexion" & Central.nombre )
		Central.IpIn= lst.Get (0)
		Central.ipout= lst.Get (1)
		Central.PuertoIn= lst.Get (2)
		Central.PuertoOut= lst.Get (3)
		Central.Descripcion=LanString.GetDefault  ("PC","Push to connect")
		Central.Mail =""
		Central.Password ="" 
		Central.ConexionSegura =False
		Central.Icon=0
		
		
		
	
		PassswordByte =Central.Password.GetBytes ("UTF8")
		
		File.Delete ( File.DirInternal ,"Conexion" & Central.nombre)
		GuardaCentral(Central)
		
	
		
			
		Else
			Central.IpIn = "192.168.1.200"
			Central.IpOut = "192.168.1.200"
			Central.PuertoIn = 5000
			Central.PuertoOut = 5000
			Central.Descripcion =LanString.GetDefault  ("PC","Push to connect")
			Central.Mail =""
			Central.Password ="" 
			Central.ConexionSegura =False
			Central.Icon=0			
			GuardaCentral(Central)
			PassswordByte =Central.Password.GetBytes ("UTF8")
		End If
	End If	
	
End Sub
Sub GuardaCentral(Cent As Arduino)
	Dim lst As List 
	
	lst.Initialize  
	lst.Add (Cent.IpIn )
	lst.Add (Cent.IpOut )
	lst.Add (Cent.PuertoIn )
	lst.Add (Cent.PuertoOut )
	lst.Add (Cent.Descripcion)
	lst.Add (Cent.Icon)
	lst.Add (Cent.Mail)
	lst.Add (Cent.Password )
	lst.Add (Cent.ConexionSegura)
	File.WriteList(File.DirInternal ,"Conect" & Cent.nombre ,lst)
	
End Sub
Sub CargaComandosComunes

	Dim C As Int
	If ComandosComunes.IsInitialized Then
		ComandosComunes.Clear 
	Else
		ComandosComunes.Initialize 
	End If
	
	If  File.Exists ( File.DirInternal ,"ComandosComunes" )  Then
		Dim Lst As List
		Lst = File.ReadList(File.DirInternal ,"ComandosComunes"  )
		

		C=0

		Do While C <  Lst.Size 
			Dim Con As Scene 
			Con.Nombre = Lst.Get  (C)
			C=C+1
			Con.Descripcion =Lst.Get (C)
			C=C+1
			
			ComandosComunes.Add (Con )
		Loop

	Else
		
		For C =0 To 9
			Dim com As Scene 
			'com.Initialize 
			com.Nombre ="COMMAND ORDERS nº " & (C+1)
			com.Descripcion ="Common Orders"
			ComandosComunes.Add (com )
			
		Next
		GuardaComandosComunes
	End If	

	CargaDescripcionesComandosComunes
End Sub
Sub GuardaComandosComunes
	Dim C As Int
		Dim Lst As List 
		Lst.Initialize 
		For C =0 To ComandosComunes.Size -1
			Dim cmd As Scene 
			cmd = ComandosComunes.Get (C)
			Lst.Add (cmd.Nombre )
			Lst.Add (cmd.Descripcion )
					
		Next
		File.WriteList(File.DirInternal ,"ComandosComunes"   ,Lst)
		CargaDescripcionesComandosComunes
End Sub
Sub CargaDescripcionesComandosComunes
	If DescripcionesComandosComunes.IsInitialized Then
		DescripcionesComandosComunes.Clear 
	Else
		DescripcionesComandosComunes.Initialize 
	End If
	Dim C As Int
	Dim cmd As Scene 
	For C =0 To  ComandosComunes.Size -1
  		cmd = ComandosComunes.Get (C)
		If DescripcionesComandosComunes.IndexOf(cmd.Descripcion )= -1 Then DescripcionesComandosComunes.Add (cmd.Descripcion )
  	Next
		

End Sub
Sub CargaComandos

	Dim C As Int
	If Comandos.IsInitialized Then
		Comandos.Clear 
	Else
		Comandos.Initialize 
	End If
	
	If  File.Exists ( File.DirInternal ,"Comandos" & Central.nombre)  Then
		Dim Lst As List
		Lst = File.ReadList(File.DirInternal ,"Comandos"  & Central.nombre)
		

		C=0

		Do While C <  Lst.Size 
			Dim Con As Scene 
			Con.Nombre = Lst.Get  (C)
			C=C+1
			Con.Descripcion =Lst.Get (C)
			C=C+1
			
			Comandos.Add (Con )
		Loop

	Else
		
		For C =0 To 9
			Dim com As Scene 
			'com.Initialize 
			com.Nombre ="COMMAND nº " & (C+1)
			com.Descripcion ="General Command"
			Comandos.Add (com )
			
		Next
		GuardaComandos
	End If	

	CargaDescripcionesComandos
End Sub
Sub CargaDescripcionesComandos
	If DescripcionesComandos.IsInitialized Then
		DescripcionesComandos.Clear 
	Else
		DescripcionesComandos.Initialize 
	End If
	Dim C As Int
	Dim cmd As Scene 
	For C =0 To Comandos.Size -1
  		cmd = Comandos.Get (C)
		If DescripcionesComandos.IndexOf(cmd.Descripcion )= -1 Then DescripcionesComandos.Add (cmd.Descripcion )
  	Next
		

End Sub
Sub GuardaComandos
	Dim C As Int
		Dim Lst As List 
		Lst.Initialize 
		For C =0 To Comandos.Size -1
			Dim cmd As Scene 
			cmd = Comandos.Get (C)
			Lst.Add (cmd.Nombre )
			Lst.Add (cmd.Descripcion )
					
		Next
		File.WriteList(File.DirInternal ,"Comandos"  & Central.nombre  ,Lst)
		CargaDescripcionesComandos
End Sub
Sub CargaNombreSensores

	If File.Exists ( File.DirInternal ,"Sensores"& Central.nombre)    Then
		NombreSensor = File.ReadList(File.DirInternal  ,"Sensores"& Central.nombre )
		
	Else 
		If NombreSensor.IsInitialized  = False Then
			NombreSensor.Initialize 
		Else
			NombreSensor.Clear
		End If
	
	End If
End Sub
Sub CargaCondicionados

	If  File.Exists ( File.DirInternal ,"Condicionados"& Central.nombre)  Then
		Dim Lst As List
		Lst = File.ReadList(File.DirInternal ,"Condicionados"& Central.nombre )
		Dim C As Int
		Dim c2 As Int
		C=0
		c2=0
		Do While c2 < 10
			Dim Con As Scene 
			Con.Nombre = Lst.Get  (C)
			C=C+1
			Con.Descripcion =Lst.Get (C)
			C=C+1
			Condicionados(c2)=Con
			c2=c2+1
		Loop

	Else
		Dim C As Int
		For C =0 To 9
			Dim Con As Scene 
			Con.Nombre ="Condition nº " & (C+1)
			Con.Descripcion ="Descripcion " & (C+1)
			Condicionados(C)= Con

			
		Next
		GuardaCondicionados
	End If	
	
	
End Sub
Sub GuardaCondicionados
	Dim C As Int
		Dim Lst As List 
		Lst.Initialize 
		For C =0 To 9
			
			Lst.Add (Condicionados(C).Nombre )
			Lst.Add (Condicionados(C).Descripcion )
					
		Next
		File.WriteList(File.DirInternal ,"Condicionados" & Central.nombre ,Lst)
		
End Sub

Sub CargaSensores


	
	If  File.Exists ( File.DirInternal ,"ConfigSenso" & Central.nombre)  Then
		Dim Lst As List
		Lst = File.ReadList(File.DirInternal ,"ConfigSenso" & Central.nombre)
		Dim C As Int
		Dim ci As Int
		C=0
		ci =0
		Do While ci < 15
			Dim cir As Circuito 
			cir.Nombre = Lst.Get  (C)
			C=C+1
			cir.Descripcion =Lst.Get (C)
			C=C+1
			If IsNumber(Lst.Get (C)) Then
				cir.Rango =Lst.Get (C)				
			Else
				cir.Rango =0
			End If
			Sensores(ci)=cir	
			C=C+1
			ci=ci+1
		Loop

	Else
		Dim I As Int
		For I =0 To 14
			Dim cir As Circuito 
			cir.Nombre ="Sensor Nº "  & I
			cir.Descripcion ="Sensor Description"		
			cir.Rango =1000
			Sensores(I)= cir
		Next
		GuardarConfigSensores
	End If	
End Sub
Sub GuardarConfigSensores

	Dim Lst As List 
		Lst.Initialize 
		Dim I As Int
		For I =0 To 14
			Lst.Add (Sensores(I).Nombre )
			Lst.Add (Sensores(I).Descripcion  )
			Lst.Add (Sensores(I).rango )
		Next
		File.WriteList(File.DirInternal ,"ConfigSenso" & Central.nombre,Lst)
End Sub
Sub CargaCircuitos


	
	If  File.Exists ( File.DirInternal ,"ConfigCir" & Central.nombre)  Then
		Dim Lst As List
		Lst = File.ReadList(File.DirInternal ,"ConfigCir" & Central.nombre)
		Dim C As Int
		Dim ci As Int
		C=0
		ci =0
		Do While ci < 30
			Dim cir As Circuito 
			cir.Nombre = Lst.Get  (C)
			C=C+1
			cir.Descripcion =Lst.Get (C)
			C=C+1
			If IsNumber(Lst.Get (C)) Then
				cir.Rango =Lst.Get (C)				
			Else
				cir.Rango =0
			End If
			C=C+1
			If C < Lst.Size Then
				If IsNumber(Lst.Get (C)) Then
					cir.DeviceNumber  =Lst.Get (C)	
					C=C+1
				Else
					cir.DeviceNumber =0
				End If
			End If
			
			Circuitos(ci)=cir	
			
			ci=ci+1
		Loop

	Else
		Dim I As Int
		For I =0 To 29
			Dim cir As Circuito 
			cir.Nombre ="Circuit Nº "  & I
			cir.Descripcion ="Circuit Description"		
			cir.Rango =1000
			cir.DeviceNumber =1
			Circuitos(I)= cir
		Next
		GuardarConfigCircuitos
	End If	
End Sub
Sub GuardarConfigCircuitos

	Dim Lst As List 
		Lst.Initialize 
		Dim I As Int
		For I =0 To 29
			Lst.Add (Circuitos(I).Nombre )
			Lst.Add (Circuitos(I).Descripcion  )
			Lst.Add (Circuitos(I).rango )
			Lst.Add (Circuitos(I).DeviceNumber  )
		Next
		File.WriteList(File.DirInternal ,"ConfigCir" & Central.nombre,Lst)
End Sub 
Sub GuardaEscenas
	Dim Lst As List 
	Lst.Initialize 
	Dim C As Int 
	For C =0 To 9
		Lst.Add (Scenes(C).Nombre)
		Lst.Add (Scenes(C).Descripcion ) 
	Next
	File.WriteList(File.DirInternal ,"ConfigEsc" & Central.nombre, Lst)
End Sub
Sub GuardarCentrales
	
		File.WriteList(File.DirInternal ,"Centrales" ,Centrales)
End Sub 
Sub GuardaFunciones
	Dim Lst As List 
	Lst.Initialize 
	Dim C As Int 
	For C =0 To 9
		Lst.Add (Functions(C).Nombre)
		Lst.Add (Functions(C).Descripcion ) 
	Next
	File.WriteList(File.DirInternal ,"ConfigFunc" & Central.nombre, Lst)
End Sub
Sub CargaFuciones

	
	If  File.Exists ( File.DirInternal ,"ConfigFunc"& Central.nombre)  Then
		Dim Lst As List
		Lst = File.ReadList(File.DirInternal ,"ConfigFunc"& Central.nombre )
		Dim C As Int
		Dim c2 As Int
		C=0
		c2=0
		Do While c2 < 10
			Dim esc As Scene 
			esc.Nombre = Lst.Get  (C)
			C=C+1
			esc.Descripcion =Lst.Get (C)
			C=C+1
			Functions(c2)=esc
			c2=c2+1
		Loop

	Else
		Dim C As Int
		Dim Lst As List 
		Lst.Initialize 
		For C =0 To 9
			Dim esc As Scene 
			esc.Nombre ="Functions nº " & (C+1)
			esc.Descripcion ="Descripcion functions " & (C+1)
			Functions(C)= esc
			
			
			Lst.Add (esc.Nombre )
			Lst.Add (esc.Descripcion )
			
			
		Next
		File.WriteList(File.DirInternal ,"ConfigFunc" & Central.nombre ,Lst)
	End If	
End Sub
Sub CargaAlarmas
	If File.Exists ( File.DirInternal ,"Alm" & Central.Nombre)   Then
		AlmName = File.ReadList(File.DirInternal  ,"Alm" & Central.Nombre )
	Else
		AlmName.Initialize 
		Dim c As Int 
				
		For c=0 To 19 
			AlmName.Add ("Alarma " & c)
		Next
		File.WriteList(File.DirInternal  ,"Alm" & Central.Nombre ,AlmName)
		
	End If
	
End Sub
Sub CargaScenes

	
	If  File.Exists ( File.DirInternal ,"ConfigEsc"& Central.nombre)  Then
		Dim Lst As List
		Lst = File.ReadList(File.DirInternal ,"ConfigEsc"& Central.nombre )
		Dim C As Int
		Dim c2 As Int
		C=0
		c2=0
		Do While c2 < 10
			Dim esc As Scene 
			esc.Nombre = Lst.Get  (C)
			C=C+1
			esc.Descripcion =Lst.Get (C)
			C=C+1
			Scenes(c2)=esc
			c2=c2+1
		Loop

	Else
		Dim C As Int
		Dim Lst As List 
		Lst.Initialize 
		For C =0 To 9
			Dim esc As Scene 
			esc.Nombre ="Scene nº " & (C+1)
			esc.Descripcion ="Descripcion Scene " & (C+1)
			Scenes(C)= esc
			
			
			Lst.Add (esc.Nombre )
			Lst.Add (esc.Descripcion )
			
			
		Next
		File.WriteList(File.DirInternal ,"ConfigEsc" & Central.nombre ,Lst)
	End If	
End Sub

Sub CompletarTrama( data() As Byte) 

	
	Dim  p As Int
	p = data.Length - 8
	
	data (p)= PassswordByte(0)
	data (p+1)= PassswordByte(1)
	data (p+2)= PassswordByte(2)
	data (p+3)= PassswordByte(3)
	data (p+4)= PassswordByte(4)
	data (p+5)= PassswordByte(5)
	data (p+6)= PassswordByte(6)
	data (p+7)= PassswordByte(7)
End Sub



Sub CambiarNombreCentral(OldName As String,NewName As String)
	If OldName=NewName Then Return
	If File.Exists (File.DirInternal ,"Conect" & OldName) Then 
		File.Copy (File.DirInternal ,"Conect" & OldName,File.DirInternal ,"Conexion" & NewName) 
		File.Delete (File.DirInternal ,"Conect" & OldName )
	End If
	If File.Exists (File.DirInternal ,"Conexion" & OldName) Then 
		File.Copy (File.DirInternal ,"Conexion" & OldName,File.DirInternal ,"Conexion" & NewName) 
		File.Delete (File.DirInternal ,"Conexion" & OldName )
	End If

	If File.Exists (File.DirInternal ,"Comandos" & OldName) Then 
		File.Copy (File.DirInternal ,"Comandos" & OldName,File.DirInternal ,"Comandos" & NewName) 
		File.Delete (File.DirInternal ,"Comandos" & OldName)
	End If
	If File.Exists (File.DirInternal ,"Sensores" & OldName) Then
		File.Copy (File.DirInternal ,"Sensores" & OldName,File.DirInternal ,"Sensores" & NewName) 
		File.Delete (File.DirInternal ,"Sensores" & OldName)
	End If
	If File.Exists (File.DirInternal ,"Condicionados" & OldName) Then 
		File.Copy (File.DirInternal ,"Condicionados" & OldName,File.DirInternal ,"Condicionados" & NewName) 
		File.Delete (File.DirInternal ,"Condicionados" & OldName)
	End If
	If File.Exists (File.DirInternal ,"ConfigCir" & OldName) Then 
		File.Copy (File.DirInternal ,"ConfigCir" & OldName,File.DirInternal ,"ConfigCir" & NewName) 
		File.Delete (File.DirInternal ,"ConfigCir" & OldName)
	End If
	If File.Exists (File.DirInternal ,"ConfigEsc" & OldName) Then 
		File.Copy (File.DirInternal ,"ConfigEsc" & OldName,File.DirInternal ,"ConfigEsc" & NewName) 
		File.Delete (File.DirInternal ,"ConfigEsc" & OldName)
	End If
	
	If File.Exists (File.DirInternal ,"SSID" & OldName) Then 
		File.Copy (File.DirInternal ,"SSID" & OldName,File.DirInternal ,"SSID" & NewName) 
		File.Delete (File.DirInternal ,"SSID" & OldName)
	End If
	
	
	If File.Exists (File.DirInternal ,"Consignas" & OldName) Then 
		File.Copy (File.DirInternal ,"Consignas" & OldName,File.DirInternal ,"Consignas" & NewName) 
		File.Delete (File.DirInternal ,"Consignas" & OldName)
	End If
	
	If File.Exists (File.DirInternal ,"ConsignasMax" & OldName) Then 
		File.Copy (File.DirInternal ,"ConsignasMax" & OldName,File.DirInternal ,"ConsignasMax" & NewName) 
		File.Delete (File.DirInternal ,"ConsignasMax" & OldName)
	End If
	If File.Exists (File.DirInternal ,"ConsignasMin" & OldName) Then 
		File.Copy (File.DirInternal ,"ConsignasMin" & OldName,File.DirInternal ,"ConsignasMin" & NewName) 
		File.Delete (File.DirInternal ,"ConsignasMin" & OldName)
	End If	
	If File.Exists (File.DirInternal ,"ConsignasIcon" & OldName) Then 
		File.Copy (File.DirInternal ,"ConsignasIcon" & OldName,File.DirInternal ,"ConsignasIcon" & NewName) 
		File.Delete (File.DirInternal ,"ConsignasIcon" & OldName)
	End If

	If File.Exists (File.DirInternal ,"Camara" & OldName) Then 
		File.Copy (File.DirInternal ,"Camara" & OldName,File.DirInternal ,"Camara" & NewName) 
		File.Delete (File.DirInternal ,"Camara" & OldName)
	End If
	
	
	
	If Centrales.IsInitialized Then
		Dim pos As Int 
		pos= Centrales.IndexOf (OldName)
		If pos >=0 Then Centrales.RemoveAt (pos)
	End If

End Sub


Sub CrearMapaIdioma
	LanString.Initialize 
			LanString.Put ("ConReset","Connection Reset")
			LanString.Put ("mnuOrders","Common Orders")
			LanString.Put ("cdt","download configuration tools?")
			LanString.Put ("pp" ,"Project Page")
			LanString.Put ("Y","Yes")
			LanString.Put ("N","No")
			LanString.Put ("Cancel","Cancel")
			LanString.Put ("Ok","Ok")
			
			
			LanString.Put ("PC","Push to connect")
			
			LanString.Put ("CNA","CONNECTION UNAVIABLE")
			LanString.Put ("NEE","NETWORK ERROR")
			LanString.Put ("SDA","SENDING DATA...")
			LanString.Put ("ERIP","Error recepcion IP")


			LanString.Put ("TC","Trigger Clock")
			LanString.Put ("STC","Settings Trigger Clock")
			LanString.Put ("ST","Settings Timetable")
			LanString.Put ("SWP","Settings Weekly programming")
			LanString.Put ("ET","Enabled Timetable")
			LanString.Put ("ECT","Enables circuits timetables")
			LanString.Put ("SSD","Settings Special day ")
			LanString.Put ("SPD","Selection Special day ")
			LanString.put("SPDI","Special day")
			LanString.Put ("SDT","Sync Date Time")
			LanString.Put ("SIT","Synchronize Date and Time")
			LanString.Put ("SST","See State Time")
			LanString.Put ("ITS","Installation Time State")
			LanString.Put ("SS","Settings Scenes")
			LanString.Put ("SNV","Settings of names and values")
			LanString.Put ("CBU","Create backup")
			LanString.Put ("GCF","Generates a configuration file")
			LanString.Put ("RBU","Recover backup copy")
			LanString.Put ("LCF","Loads a configuration file")
			LanString.Put ("SCN","Set Circuit Names")
			LanString.Put ("MND","Change Settings")
			LanString.Put ("SSU","Setting blinds")
			LanString.Put ("SUD","Sets up and down time")
			LanString.Put ("SCD","Setting Conditioned")
			LanString.Put ("SCO","Setting Commands")

			LanString.Put ("VCO","Voice Control")
			LanString.Put ("Sensor","Sensor")
			LanString.Put ("SSE","Setting Sensor")
			LanString.Put ("SAU","Settings Arduino Unit")
			LanString.Put ("NHA","New home automation Switchboard")
			LanString.Put ("COR","Setting Common Orders")
			LanString.Put ("MCD","Modifies Common Orders")
			LanString.Put ("MNU","Manager Menu")

			LanString.Put ("VIS","Visit the project page")
			LanString.Put ("dcg","Download code generator")
			'LanString.Put ("stt1","Timetable Special ")
			
			LanString.Put ("reg51","Timetable Monday")
			LanString.Put ("reg52","Timetable  Tuesday")
			LanString.Put ("reg53","Timetable  Wednesday")
			LanString.Put ("reg54","Timetable  Thursday")
			LanString.Put ("reg55","Timetable  Friday")
			LanString.Put ("reg56","Timetable  Saturday")
			LanString.Put ("reg57","Timetable  Sunday")
			LanString.Put ("reg58","Monday")
			LanString.Put ("reg59","Tuesday")
			LanString.Put ("reg60","Wednesday")
			LanString.Put ("reg61","Thursday")
			LanString.Put ("reg62","Friday")
			LanString.Put ("reg63","Saturday")
			LanString.Put ("reg64","Sunday")
			LanString.Put ("reg65","Confirm delete?")
			LanString.Put ("reg66","Confirmation!!")
			LanString.Put ("reg68","You can not configure more than 80 programs per day")



			LanString.Put ("reg69","Info")
			LanString.Put ("reg70","Turn on")
			LanString.Put ("reg71","Turn off")
			
			LanString.Put ("reg72","Turn on 1/3")
			LanString.Put ("reg73","Turn on 2/3")
			LanString.Put ("reg74","Turn on 3/3")
			LanString.Put ("reg75","Select option")
			LanString.Put ("reg76","Confirm saving?")
			
			
			LanString.Put ("reg77","SECURITY QUESTION")
			LanString.Put ("reg78","Select where to save")

			LanString.Put ("reg79","Set Time")
			LanString.Put ("reg80","Download")
			LanString.Put ("reg82","Upload")
			LanString.Put ("reg83","Accept")
			LanString.Put ("reg84","Time In minutes")
			LanString.Put ("reg85","Max 240 Minutes")
			LanString.Put ("reg86","Out time")
			LanString.Put ("reg87","Close Door")
			LanString.Put ("reg88","Open Door.")
			
			LanString.Put ("reg89","ALL CIRCUITS")
			LanString.Put ("reg90","New Value")
			LanString.Put ("reg91","You can not configure more than 12 programs per day")
			LanString.Put ("reg92","Select Value")
			LanString.Put ("reg93","Opening blinds")
			LanString.Put ("reg94","More Color")
			LanString.Put ("reg95","Random Color")
			LanString.Put ("reg96","Choose Color")
			
			LanString.Put ("reg97","Delete Timetables")
			LanString.Put ("reg98","Delete Special days")
			LanString.Put ("reg99","Circuit configuration")
			LanString.Put ("reg100","Set your mail")
			LanString.Put ("reg101","Set your password")
			
			LanString.Put ("reg102","Secure Connection On")
			LanString.Put ("reg103","Secure Connection Off")
			LanString.Put ("reg104","Settings")
			
			LanString.Put ("reg105","Your password...")
			LanString.Put ("reg106","Your mail...")
			LanString.Put ("reg107","On off secure connection")
			LanString.Put ("reg108","Really remove Special Days?")
			LanString.Put ("reg109","Deleting...")
			
			LanString.Put ("reg110","Are you sure erase all data?")
			
			
			LanString.Put ("reg111","Edit Name")
			
			
			LanString.Put ("reg112","Edit Description")
			LanString.Put ("reg113","Change Range")
			LanString.Put ("reg114","Remove Circuit")
			
			
			'Pendientes en bd
			
			LanString.Put ("reg115","External Ip")
			LanString.Put ("reg116","Internal UDP Port")
			LanString.Put ("reg117","External UDP Port")
			LanString.Put ("reg118","Arduino program Version")
			LanString.Put ("reg119","Device Name")
			LanString.Put ("reg120","Error")
			LanString.Put ("reg121","This name is in use!!")
			LanString.Put ("reg122","Port In")
			LanString.Put ("reg123","Port Out")
			LanString.Put ("reg124","Setting Central")
			LanString.Put ("reg125","Delete Device")
			'LanString.Put ("reg65","Confirm delete?")
			LanString.Put ("reg126","No less than one")
			LanString.Put ("reg127","Configure first!!!!") 'ACTUALIZAR EN BASE DE DATOS
			
			
			LanString.Put ("reg128","SAVE")
			LanString.Put ("reg129","Scene selection")
			LanString.Put ("reg130","Save Scene")
			LanString.Put ("reg131","Conditioned")
			LanString.Put ("reg132","Commands")
			LanString.Put ("reg133","Setpoint")
			LanString.Put ("reg134","Select Central")
			LanString.Put ("reg135","Notification")
			LanString.Put ("reg136","Are you out of your home?")
			LanString.Put ("reg137","Connection mode")
			
			
			LanString.Put ("reg138","Select Scene")
			LanString.Put ("reg139","COMPLETED")


			LanString.Put ("reg140","Maintains Existing State")
			LanString.Put ("reg141","Current Temperature")
			
			LanString.Put ("reg142","Invalid value")'ACTUALIZAR EN BASE DE DATOS
			LanString.Put ("reg143","You can not configure more than 25 days")
			LanString.Put ("reg144","New Special day")
			LanString.Put ("reg145","You can not enter a date prior to the current")
			LanString.Put ("reg146","That day is already programmed")
			LanString.Put ("reg147","Duplicate Date")
			LanString.Put ("reg148","Delete special day?")
			LanString.Put ("reg149","All Off")
			LanString.Put ("reg150","All On")
			LanString.Put ("reg151","Circuit")
			LanString.Put ("reg152","Scene")
			LanString.Put ("reg153","Time")
			LanString.Put ("reg154","Common")
			LanString.Put ("reg155","Voice")
			LanString.Put ("reg156","Reconnect")
			LanString.Put ("reg157","State Off" )
			LanString.Put ("reg158","State On")
			LanString.Put ("reg159","State Disabled")
			LanString.Put ("reg160","Enabled Notification")
			LanString.Put ("reg161","Disable Notification")
			LanString.Put ("reg162","Setting Up Time")
			LanString.Put ("reg163","Setting Dow Time")
			LanString.Put ("reg164","Restart Position")
			LanString.Put ("reg165","Confirm Restart Position?")
			LanString.Put ("reg166","Time Max. = 200Sg")
			LanString.Put ("reg167","No sensors configured")
			LanString.Put ("reg168","Internal Ip")
			
			'Pendientes en bd Miercoles 11
			
			
			LanString.Put ("reg169","Comando de voz soportado")'Voice Command supported
			LanString.Put ("reg170","comandos de voz no soportados")
			LanString.Put ("reg171","Diga comando")
			LanString.Put ("reg172","No se reconoce el comando")
			LanString.Put ("reg173","ALUMBRADO")
			LanString.Put ("reg174","TELEVISION")
			LanString.Put ("reg175","aire acondicionado")
			
			LanString.Put ("reg176","Word to Select scene")'" Word to Select scene"
			LanString.Put ("reg177","Word to turn off")'Word to activate
			LanString.Put ("reg178","Word to turn on")'"Word to deactivate"
			LanString.Put ("reg179","Word to up blind")'"Word to climb shutters"
			LanString.Put ("reg180","Word to down blind")'"Word to down shutters"
			
			
			
			LanString.Put ("reg181","Idioma Español")
			LanString.Put ("reg182","Lenguaje de control por voz")
			LanString.Put ("reg183","New word")
			
			
			
			LanString.Put ("reg184","END")
			LanString.Put ("reg185","ADD NEW")
			
			
			'Pendientes en bd lunes 20
			LanString.Put ("reg186","Functions")
			LanString.Put ("reg187","Menu")
			LanString.Put ("graphics","Graphics")
			LanString.Put ("Icon","Select Icon")
			LanString.Put ("dev","Devices")
			LanString.Put ("passr","password required")
			LanString.Put ("ICW","Internal connection Wifi")
			LanString.Put ("ECW","Extermal connection Wifi")
			LanString.Put ("CMW","Connection mode  for this wifi network")
			LanString.Put ("RSC","Reload system configuration")
			'LanString.Put ("SePI","Select Custom Icon")
			'LanString.Put ("SetPI","Configure user icon")
			LanString.Put ("Status","Status")
			LanString.Put ("SetSta","Configure states screen")
			LanString.Put ("LsTT","Language Settings")
			LanString.Put ("SltL","Select language")
			LanString.Put ("IniD","Start date")
			LanString.Put ("FinD","Finishing date")
			LanString.Put ("Function","Function")
			LanString.Put ("close","Close")
			LanString.Put ("Chart","Chart")
			LanString.Put ("Backup","Backup")
			LanString.Put ("sTs","blinds")
			LanString.Put ("CsS","Configure Status Screen")
			LanString.Put("CuF","Configure User Functons")
			
			LanString.Put("Mva","Maximum value")
			LanString.Put("Miv","Minimum value")
			LanString.Put("DiT","Invisible element")
			LanString.Put ("ipc","IP cameras")
			LanString.Put ("User","User")
			
			'OJO ACTUALIZAR EN BASE DE DATOS


			
			File.WriteMap (File.DirInternal   ,"LanString"  ,LanString)
End Sub 
Sub GetLanString(Val As String ) As String 
	If LanString.IsInitialized = False Then
		If File.Exists ( File.DirInternal ,"LanString")   Then 	
			LanString = File.ReadMap (File.DirInternal,"LanString")
		Else
			CrearMapaIdioma
		End If
	End If	
	

	Return LanString.GetDefault(Val,"?")
	
End Sub
Sub GetLanStringDefault(Val As String, default As String  ) As String 
	If LanString.IsInitialized = False Then
		If File.Exists ( File.DirInternal ,"LanString")   Then 	
			LanString = File.ReadMap (File.DirInternal,"LanString")
		Else
			CrearMapaIdioma
		End If
	End If	
	

	Return LanString.GetDefault(Val,default)
	
End Sub

Sub LoadPaleta(dlg1 As ColorPickerDialog ,dlg2 As ColorPickerDialog )  
	 
	
	dlg1.SetPaletteAt (0,  8388736)
	dlg1.SetPaletteAt (1,  15631086)
	dlg1.SetPaletteAt (2,  9055202)				
	dlg1.SetPaletteAt (3, 6970061 )
	dlg1.SetPaletteAt (4,  8087790)
	dlg1.SetPaletteAt (5,  255)
	dlg1.SetPaletteAt (6, 65535 )
	dlg1.SetPaletteAt (7,  11591910)
	dlg1.SetPaletteAt (8,  49151)
	dlg1.SetPaletteAt (9,  2142890)		
	dlg1.SetPaletteAt (10, 6737322 )				
	dlg1.SetPaletteAt (11,10025880  )
	dlg1.SetPaletteAt (12, 64154 )
	dlg1.SetPaletteAt (13, 32768 )
	dlg1.SetPaletteAt (14,  65280)
				
	'Carga paleta 2
	dlg2.SetPaletteAt (0,  14423100)
	dlg2.SetPaletteAt (5,  16711680)
	dlg2.SetPaletteAt (10,  16737094)
				
	dlg2.SetPaletteAt (1, 13047173 )				
	dlg2.SetPaletteAt (6,  16716947)
	dlg2.SetPaletteAt (11,  16761035)
				
	dlg2.SetPaletteAt (2,  16729344)
	dlg2.SetPaletteAt (7,  16747520)
	dlg2.SetPaletteAt (12,  16752762)
				
	dlg2.SetPaletteAt (3, 16776960)
	dlg2.SetPaletteAt (8, 12092939)
	dlg2.SetPaletteAt (13,  15787660)
				
				
	dlg2.SetPaletteAt (4,  10824234)
	dlg2.SetPaletteAt (9, 16032864)
	dlg2.SetPaletteAt (14, 16113331)	
End Sub
Sub Icon(Value As Int) As Bitmap 
	If Value = 1 Then
		Return SensorTemp
	Else If Value = 2 Then
		Return SensorHumedad
	Else If Value = 3 Then
		Return SensorLux
	Else If Value =100 Then
		Return SensorDeposito
	End If
	Return SensorGenerico
End Sub
Sub IconoSensor(Number As Int) As Bitmap 
	If Sensores(Number).Rango = 1 Then
		Return SensorTemp
	Else If Sensores(Number).Rango = 2 Then
		Return SensorHumedad
	Else If Sensores(Number).Rango = 3 Then
		Return SensorLux
	Else If Sensores(Number).Rango = 100 Then
		Return SensorDeposito
	End If
	Return SensorGenerico
End Sub
Sub UnidadSensor(Number As Int) As String 
	If Sensores(Number).Rango = 1 Then
		Return " ºC"
	Else If Sensores(Number).Rango = 2 Then
		Return " %H"
	Else If Sensores(Number).Rango = 3 Then
		Return " Lux"
	
	Else If Sensores(Number).Rango = 4 Then
		Return ""
	End If
	Return ""
End Sub
Sub IconoCircuito(Number As Int, Value As Int) As Bitmap 
	If Circuitos(Number).Rango<7 Then
		If Value=250   Then
			Return Bombillades	
		Else If Value=249 Then
			Return BombillaAl	
		Else If Value=0   Then
			Return BombillaOff
		Else
			Return BombillaOn
			
		End If
	End If
			
			
	'Circuitos Enchufes
	
	If Circuitos(Number).Rango=7  Then
		If  Value =250 Then
			 Return Enchufedes
		Else If Value=249 Then
			Return EnchufeAl	
		Else If Value=0  Then
			Return EnchufeOff
		Else
			Return EnchufeOn
		End If
	End If
	
	'Puertas
	
	If Circuitos(Number).Rango=39 Then
		If  Value =250 Then
			Return Puertades
		Else If Value=249 Then
			Return PuertaAl	
		Else If Value=0  Then
			Return  PuertaOff
		Else
			Return  PuertaOn

		End If
	End If
	
	

	'******************************************************
	'Riego
	'******************************************************
	If Circuitos(Number).Rango=13 Or  Circuitos(Number).Rango=14 Then
		If  Value =250 Then
			Return  Riegodes
		Else If Value=249 Then
			Return RiegoAl	
		Else If Value=0 Then
			Return  RiegoOff
		Else
			Return  RiegoOn			
		End If	
	End If
	'******************************************************
	'Valvula
	'******************************************************
	If Circuitos(Number).Rango=15 Then
		If  Value =250 Then
			Return  ValvulaDes		
		Else If Value=249 Then
			Return ValvulaAl
		Else If Value=0 Then
			Return  ValvulaOff
		Else
			Return  ValvulaOn
			
		End If					
	End If
	'******************************************************
	'A.Acondicionado
	'******************************************************
	If Circuitos(Number).Rango=19 Then
		If  Value =250 Then
			Return  Acondicionadodes
		Else If Value=249 Then
			Return 	AcondicionadoAl
		Else If Value=0  Then
			Return  AcondicionadoOff
		Else
			Return  AcondicionadoOn			
		End If
	End If
	'******************************************************
	'Calefaccion
	'******************************************************
	If Circuitos(Number).Rango=24 Then	
		If  Value =250 Then
		
			Return  Calefacciondes		
		Else If Value =249 Then
			Return CalefaccionAl
		Else If Value=0  Then
			Return  CalefaccionOff
		Else
			Return  CalefaccionOn		
		End If	
	End If
	'Circuitos Radiante
	
	If Circuitos(Number).Rango=25 Then
		If  Value =250 Then
			Return  Calefacciondes	
		Else If Value =249 Then
			Return CalefaccionAl
		Else If Value=0  Then
			Return  CalefaccionOff
		Else
			Return  CalefaccionOn
			
		End If				
	End If
	
	'Consignas Sensores
	'#define SetPoint_100	54    #define SetPoint_200	55 #define SetPoint_2000	56 #define SetPoint_20000	57  
	
	If Circuitos(Number).Rango>52 And Circuitos(Number).Rango<57 Then

		Select Case Sensores( Circuitos(Number).DeviceNumber ).rango
		
		Case 1
			If Value=250 Then
			Return  Temperaturades
			
			Else
				Return  Temperatura
			End If
		Case 2
			If Value=250 Then
				Return SensorHumedaddes
			
			Else
				Return SensorHumedad
			End If
			
		Case 3
			If Value=250 Then
				Return SensorLuxDes
			
			Else
				Return SensorLux	
			End If
			
		Case 4
			If Value=250 Then
				Return SensorGenericoDes
			
			Else
				Return SensorGenerico
			End If
			
		Case 5
			If Value=250 Then
				Return SensorGenericoDes
			
			Else
				Return SensorGenerico
			End If
			
		Case 100
			If Value=250 Then
				Return SensorDepositoDes
			
			Else
				Return SensorDeposito
			End If
		End Select
	End If
	
	'Circuitos Ventilador
	
	If Circuitos(Number).Rango>42 And Circuitos(Number).Rango<46 Then
		If  Value =250 Then
			Return  VentiladorDes
		Else If Value =249 Then
			Return VentiladorAl	
		Else If Value=0 Then
			Return  VentiladorOff
		Else
			Return  VentiladorOn
		End If
			
	End If
	'******************************************************
	'Temperatura Consiga
	'******************************************************

	If Circuitos(Number).Rango=29 Or Circuitos(Number).Rango=30 Or Circuitos(Number).Rango=31   Then

		If Value=255 Then
			Return  Temperaturades
			
		Else
			Return  Temperatura
		End If

	End If
	'******************************************************
	'Persianas
	'******************************************************
	If Circuitos(Number).Rango=34 Or Circuitos(Number).Rango=36 Then

		If Value>100  Or Value<0 Then
				Return  persianades
				
			Else
				Return  Persiana
			End If
	End If
	'******************************************************
	'toldos
	'******************************************************
	If Circuitos(Number).Rango=35 Then
		If Value>100  Or Value<0 Then
			Return  Toldodes
			
		Else
			If Value=0 Then
				Return  Toldooff
			Else
				Return  ToldoOn
			End If
		End If		
			
	End If
	'******************************************************
	'Pilotos
	'******************************************************
	If Circuitos(Number).Rango>50 Then
		If  Value =250 Then
			Return  PilotoDes
		Else If Value =249 Then
			Return PilotoAl
		Else If Value=0 Then
			Return  PilotoOff

		Else
			Return  PilotoOn
		End If
	End If
				
	Return  PilotoDes
End Sub


Sub BuldMenus(SlMenu As SlideMenu, Pos As Int)
	'sm.AddItem("Item #1", LoadBitmap(File.DirAssets, "bomb.png"), 1)
	
	SlMenu.AddItem(LanString .GetDefault("reg187","Menu"),  MnuImg, 1)
	
	If Pos <>2 Then SlMenu.AddItem(LanString .GetDefault("reg151","Circuit"), MnuCircuitos, 2)
	If Pos <>3 Then SlMenu.AddItem(LanString .GetDefault("reg152","Scene"), MnuScene, 3)
	If Pos <>4 Then SlMenu.AddItem(LanString .GetDefault("reg135","Notification"), MnuAlarmas, 4)
	If Pos <>5 Then SlMenu.AddItem(LanString .GetDefault("reg131","Conditioned"), MnuCondicionado, 5)
	If Pos <>6 Then SlMenu.AddItem(LanString .GetDefault("reg132","Commands"), MnuComandos, 6)
	If Pos <>7 Then
		
		SlMenu.AddItem(LanString .GetDefault("Sensor","Sensor"), MnuSensor, 7)
	Else
		SlMenu.AddItem(LanString .GetDefault("graphics","graphics"), MnuSensor, 98)
	End If
	If Pos <>8  Then
		If File.Exists ( File.DirInternal ,"Sensores" & Central.nombre)  Then SlMenu.AddItem(LanString .GetDefault("Status","Status"), MnuTxt, 8)
	End If
	If Pos <>9  Then
		If SetPoint.Size >0   Then SlMenu.AddItem(GetLanStringDefault ("reg133","Setpoint"), MnuSetPointLt, 9)
		
	End If
	If Pos <>11 Then
		  SlMenu.AddItem(GetLanStringDefault ("Function","Function"), MnuFuncionEsp, 11)
		'Label5.Text = ValoresComunes.GetLanStringDefault ("Function","Function").ToUpperCase
	End If
	
	

	
	SlMenu.AddItem(GetLanStringDefault("close","Close"), MnuEnd, 10)
			


	
	If Centrales.Size >1 Then
		SlMenu.AddItem("", Null, 99)
		Dim C As Int
		For C=0 To Centrales.Size-1
			If Centrales.Get (C) <> Central.Nombre Then SlMenu.AddItem(Centrales.Get (C), GetImgDevice(Centrales .Get(C),False), 100+ C)
		Next
	
	End If
	
	

	
End Sub
