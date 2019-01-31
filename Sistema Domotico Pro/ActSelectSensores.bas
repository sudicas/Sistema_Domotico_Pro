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
    



End Sub

Sub Globals
	Dim sm As SlideMenu
	Dim ListView1 As ListView
	
	Dim CmdGuardar As Button
	Dim CmdTerminar As Button


End Sub

Sub Activity_Create(FirstTime As Boolean)
	If ValoresComunes.Centrales .IsInitialized = False  Then Return
    If FirstTime Then
		
      
    End If
	
	Activity.LoadLayout ("frmcondicionados")
	
	Dim he As Int = 40dip
	
	
	ListView1.Top =52dip
	ListView1.Height = Activity.Height - 52dip - he
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
	
	sm.Initialize(Activity, Me,  "SlideMenu", 42dip, 250dip)
	ValoresComunes.BuldMenus (sm,98)
	
End Sub
Sub Activity_Pause (UserClosed As Boolean)

	Activity.Finish 
End Sub

Sub Activity_Resume
	If ValoresComunes.CloseApp =True Then
		Activity.Finish 
		Return
	End If
	If ValoresComunes.Centrales .IsInitialized = True Then


		CmdGuardar.Enabled =True
		CmdTerminar.Enabled =True
		ActualizaValores	
	Else
		Activity.Finish 
	End If

End Sub


Sub ActualizaValores()
	ListView1.Clear

	Dim i As Int
	For i =0 To ValoresComunes.Sensores .Length -1

		If ValoresComunes.Sensores  (i).Nombre <> "" And ValoresComunes.Sensores (i).Rango >0   And  ValoresComunes.Sensores (i).Rango < 250 Then
			If ActHistorico.SensorSelect(i)=True   Then
				ListView1.AddTwoLinesAndBitmap2 ( ValoresComunes.Sensores  (i).Nombre ,ValoresComunes.Sensores (i).Descripcion  ,ValoresComunes.CheckOn,i)
			Else
				ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.Sensores (i).Nombre ,ValoresComunes.Sensores (i).Descripcion  ,ValoresComunes.CheckOff,i)
			End If
		End If
	Next
End Sub




Sub ListView1_ItemClick (Position As Int, Value As Object)

	If ActHistorico.SensorSelect(Value)=True Then 
		ActHistorico.SensorSelect(Value)=False
	Else
		
		ActHistorico.SensorSelect(Value)=True
	End If
	ActualizaValores
		
End Sub

Sub CmdTerminar_Click
	Activity.Finish 
End Sub
Sub CmdGuardar_Click
	Dim Dialog As DateDialog 
	Dialog.DateTicks = DateTime.Now 
	
	Dim Resp As Int
	Resp= Dialog.Show (ValoresComunes.GetLanStringDefault ("IniD","Start date"),ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("Ok"),ValoresComunes.GetLanString ("Cancel"),"",Null )
	If Resp = DialogResponse.POSITIVE Then
		
		ActHistorico.Fecha=Dialog.DateTicks 
	Else
		Return		
	End If		
	
	Resp= Dialog.Show (ValoresComunes.GetLanStringDefault ("FinD","Finishing date"),ValoresComunes.GetLanString ("reg90"),ValoresComunes.GetLanString ("Ok"),ValoresComunes.GetLanString ("Cancel"),"",Null )
		If Resp = DialogResponse.POSITIVE Then
		If ActHistorico.Fecha > Dialog.DateTicks Then
		
			ActHistorico.FechaFin =ActHistorico.Fecha 
			ActHistorico.Fecha =Dialog.DateTicks
		Else
			ActHistorico.FechaFin =Dialog.DateTicks 
		End If
		
		ActHistorico.Iniciando =True
		StartActivity(ActHistorico)
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
				'Activity.Finish
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