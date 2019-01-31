package arduino.automatizacion.excontrol.PRO;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class valorescomunes {
private static valorescomunes mostCurrent = new valorescomunes();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static boolean _closeapp = false;
public static String _oldmail = "";
public static String _oldpass = "";
public static String _oldextip = "";
public static arduino.automatizacion.excontrol.PRO.valorescomunes._arduino _central = null;
public static anywheresoftware.b4a.objects.collections.Map _centralesimg = null;
public static anywheresoftware.b4a.objects.collections.Map _lanstring = null;
public static arduino.automatizacion.excontrol.PRO.valorescomunes._scene[] _scenes = null;
public static arduino.automatizacion.excontrol.PRO.valorescomunes._scene[] _condicionados = null;
public static arduino.automatizacion.excontrol.PRO.valorescomunes._scene[] _functions = null;
public static anywheresoftware.b4a.objects.collections.List _setpoint = null;
public static anywheresoftware.b4a.objects.collections.List _comandoscomunes = null;
public static anywheresoftware.b4a.objects.collections.List _descripcionescomandoscomunes = null;
public static anywheresoftware.b4a.objects.collections.List _comandos = null;
public static anywheresoftware.b4a.objects.collections.List _descripcionescomandos = null;
public static anywheresoftware.b4a.objects.collections.List _nombresensor = null;
public static arduino.automatizacion.excontrol.PRO.valorescomunes._circuito[] _circuitos = null;
public static arduino.automatizacion.excontrol.PRO.valorescomunes._circuito[] _sensores = null;
public static String _ip = "";
public static int _puerto = 0;
public static anywheresoftware.b4a.objects.collections.List _almname = null;
public static byte[] _passswordbyte = null;
public static anywheresoftware.b4a.objects.collections.List _centrales = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgbombillaal = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgventiladoral = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgvalvulaal = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgriegoal = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgpuertaal = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgpilotoal = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgacal = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgenchufeal = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgcalefaccional = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgconnames = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgdelete = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgscene2 = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgalarma = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgsensor = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgvoice = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgcargando = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgreloj = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgrelook = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgcalendario = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgicono = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgfechahora = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgconfigup = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgconfigdo = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgon = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgcomando = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgconfig = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgarduino = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgcheckon = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgcheckoff = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgbombillaoff = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgbombillaon = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgbombillades = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgenchufedes = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgenchufeoff = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgenchufeon = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgpuertades = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgpuertaoff = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgpuertaon = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgriegodes = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgriegooff = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgriegoon = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgacondicionadodes = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgacondicionadooff = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgacondicionadoon = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgcalefacciondes = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgcalefaccionoff = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgcalefaccionon = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgtemperaturades = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgtemperatura = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgtoldodes = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgtoldooff = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgtoldoon = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgpersianades = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgpersiana = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgchart = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgfunciones = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgalmon = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgalmoff = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgalmdes = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgvalvulaon = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgvalvulaoff = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgvalvulades = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgventiladoron = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgventiladoroff = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgventiladordes = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgpilotoon = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgpilotooff = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgpilotodes = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgdepositodes = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgdeposito = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgsensortemp = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgsensorhumedad = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgsensorhumedadd = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imglux = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgluxd = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgsensor2 = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgsensor2d = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgsalir = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgfc = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgsetpointlt = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgmnucir = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgmnual = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgmnucon = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgmnuhome = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgmnuscene = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgmnucom = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgmnusensor = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgmnu = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgfreetxt = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgconfiglt = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imghome = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgsalon = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgcama = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgbaño = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imggarden = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgcar = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgcocina = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgsalonlt = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgcamalt = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgbañolt = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imggardenlt = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgcarlt = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgcocinalt = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgmnuend = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgsave = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgback = null;
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _imgnew = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public arduino.automatizacion.excontrol.PRO.main _main = null;
public arduino.automatizacion.excontrol.PRO.actcentral _actcentral = null;
public arduino.automatizacion.excontrol.PRO.actmosaico _actmosaico = null;
public arduino.automatizacion.excontrol.PRO.actcomandos _actcomandos = null;
public arduino.automatizacion.excontrol.PRO.actconfigsetponint _actconfigsetponint = null;
public arduino.automatizacion.excontrol.PRO.actconsignas _actconsignas = null;
public arduino.automatizacion.excontrol.PRO.actcircuit _actcircuit = null;
public arduino.automatizacion.excontrol.PRO.actsensors _actsensors = null;
public arduino.automatizacion.excontrol.PRO.actenablehorarios _actenablehorarios = null;
public arduino.automatizacion.excontrol.PRO.actdiaespecial _actdiaespecial = null;
public arduino.automatizacion.excontrol.PRO.actconescena _actconescena = null;
public arduino.automatizacion.excontrol.PRO.acthorarios2 _acthorarios2 = null;
public arduino.automatizacion.excontrol.PRO.actalarmas2 _actalarmas2 = null;
public arduino.automatizacion.excontrol.PRO.actvoice _actvoice = null;
public arduino.automatizacion.excontrol.PRO.acthistorico _acthistorico = null;
public arduino.automatizacion.excontrol.PRO.actcamara _actcamara = null;
public arduino.automatizacion.excontrol.PRO.actnotification _actnotification = null;
public arduino.automatizacion.excontrol.PRO.actscene _actscene = null;
public arduino.automatizacion.excontrol.PRO.actfunciones _actfunciones = null;
public arduino.automatizacion.excontrol.PRO.actcondicionados _actcondicionados = null;
public arduino.automatizacion.excontrol.PRO.actselectsensores _actselectsensores = null;
public arduino.automatizacion.excontrol.PRO.actconfigsensors _actconfigsensors = null;
public arduino.automatizacion.excontrol.PRO.actmenu _actmenu = null;
public arduino.automatizacion.excontrol.PRO.actconfignotification _actconfignotification = null;
public arduino.automatizacion.excontrol.PRO.actwifis _actwifis = null;
public arduino.automatizacion.excontrol.PRO.actfreetxt _actfreetxt = null;
public arduino.automatizacion.excontrol.PRO.actconfigvoice _actconfigvoice = null;
public arduino.automatizacion.excontrol.PRO.actconfigfreetxt _actconfigfreetxt = null;
public arduino.automatizacion.excontrol.PRO.actcentrales _actcentrales = null;
public arduino.automatizacion.excontrol.PRO.actconfigescenas _actconfigescenas = null;
public arduino.automatizacion.excontrol.PRO.actconfigfunciones _actconfigfunciones = null;
public arduino.automatizacion.excontrol.PRO.actconfigcondicionados _actconfigcondicionados = null;
public arduino.automatizacion.excontrol.PRO.actconfigcir _actconfigcir = null;
public arduino.automatizacion.excontrol.PRO.actpersianas _actpersianas = null;
public arduino.automatizacion.excontrol.PRO.actconfigcomandoscomu _actconfigcomandoscomu = null;
public arduino.automatizacion.excontrol.PRO.actconfigcomandos _actconfigcomandos = null;
public arduino.automatizacion.excontrol.PRO.actpersonalicon _actpersonalicon = null;
public static class _sp{
public boolean IsInitialized;
public String Nombre;
public int Minimo;
public int Maximo;
public int Icon;
public void Initialize() {
IsInitialized = true;
Nombre = "";
Minimo = 0;
Maximo = 0;
Icon = 0;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static class _scene{
public boolean IsInitialized;
public String Nombre;
public String Descripcion;
public void Initialize() {
IsInitialized = true;
Nombre = "";
Descripcion = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static class _circuito{
public boolean IsInitialized;
public String Nombre;
public String Descripcion;
public int Rango;
public int Value;
public int DeviceNumber;
public void Initialize() {
IsInitialized = true;
Nombre = "";
Descripcion = "";
Rango = 0;
Value = 0;
DeviceNumber = 0;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static class _arduino{
public boolean IsInitialized;
public String Nombre;
public String Descripcion;
public String IpIn;
public String IpOut;
public int PuertoIn;
public int PuertoOut;
public int Icon;
public String mail;
public String Password;
public boolean ConexionSegura;
public void Initialize() {
IsInitialized = true;
Nombre = "";
Descripcion = "";
IpIn = "";
IpOut = "";
PuertoIn = 0;
PuertoOut = 0;
Icon = 0;
mail = "";
Password = "";
ConexionSegura = false;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _acondicionadoal(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 340;BA.debugLine="Sub AcondicionadoAl()As Bitmap";
 //BA.debugLineNum = 341;BA.debugLine="If ImgAcAl.IsInitialized = False Then ImgAcAl.Ini";
if (_imgacal.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgacal.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"acondicionadoAl.png");};
 //BA.debugLineNum = 342;BA.debugLine="Return 	ImgAcAl";
if (true) return _imgacal;
 //BA.debugLineNum = 343;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _acondicionadodes(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 461;BA.debugLine="Sub Acondicionadodes()As Bitmap";
 //BA.debugLineNum = 462;BA.debugLine="If ImgAcondicionadodes.IsInitialized = False Then";
if (_imgacondicionadodes.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgacondicionadodes.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"AcondicionadoDes.png");};
 //BA.debugLineNum = 463;BA.debugLine="Return 	ImgAcondicionadodes";
if (true) return _imgacondicionadodes;
 //BA.debugLineNum = 464;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _acondicionadooff(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 466;BA.debugLine="Sub AcondicionadoOff()As Bitmap";
 //BA.debugLineNum = 467;BA.debugLine="If ImgAcondicionadoOff.IsInitialized = False Then";
if (_imgacondicionadooff.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgacondicionadooff.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"AcondicionadoOff.png");};
 //BA.debugLineNum = 468;BA.debugLine="Return 	ImgAcondicionadoOff";
if (true) return _imgacondicionadooff;
 //BA.debugLineNum = 469;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _acondicionadoon(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 471;BA.debugLine="Sub AcondicionadoOn()As Bitmap";
 //BA.debugLineNum = 472;BA.debugLine="If ImgAcondicionadoOn.IsInitialized = False Then";
if (_imgacondicionadoon.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgacondicionadoon.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"AcondicionadoOn.png");};
 //BA.debugLineNum = 473;BA.debugLine="Return 	ImgAcondicionadoOn";
if (true) return _imgacondicionadoon;
 //BA.debugLineNum = 474;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _alarma(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 579;BA.debugLine="Sub Alarma()As Bitmap";
 //BA.debugLineNum = 580;BA.debugLine="If ImgAlarma.IsInitialized = False Then ImgAlarma";
if (_imgalarma.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgalarma.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Alarma.png");};
 //BA.debugLineNum = 581;BA.debugLine="Return 	ImgAlarma";
if (true) return _imgalarma;
 //BA.debugLineNum = 582;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _almdes(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 627;BA.debugLine="Sub AlmDes()As Bitmap";
 //BA.debugLineNum = 628;BA.debugLine="If ImgAlmDes.IsInitialized = False Then ImgAlmDes";
if (_imgalmdes.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgalmdes.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"alarmades.png");};
 //BA.debugLineNum = 629;BA.debugLine="Return 	ImgAlmDes";
if (true) return _imgalmdes;
 //BA.debugLineNum = 630;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _almoff(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 623;BA.debugLine="Sub AlmOff()As Bitmap";
 //BA.debugLineNum = 624;BA.debugLine="If ImgAlmOff.IsInitialized = False Then ImgAlmOff";
if (_imgalmoff.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgalmoff.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"alarmaoff.png");};
 //BA.debugLineNum = 625;BA.debugLine="Return 	ImgAlmOff";
if (true) return _imgalmoff;
 //BA.debugLineNum = 626;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _almon(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 619;BA.debugLine="Sub AlmOn()As Bitmap";
 //BA.debugLineNum = 620;BA.debugLine="If ImgAlmOn.IsInitialized = False Then ImgAlmOn.I";
if (_imgalmon.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgalmon.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"alarmaon.png");};
 //BA.debugLineNum = 621;BA.debugLine="Return ImgAlmOn";
if (true) return _imgalmon;
 //BA.debugLineNum = 622;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _arduino(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 563;BA.debugLine="Sub Arduino()As Bitmap";
 //BA.debugLineNum = 564;BA.debugLine="If ImgArduino.IsInitialized = False Then ImgArdui";
if (_imgarduino.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgarduino.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Arduino.png");};
 //BA.debugLineNum = 565;BA.debugLine="Return 	ImgArduino";
if (true) return _imgarduino;
 //BA.debugLineNum = 566;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _aseo(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 761;BA.debugLine="Sub Aseo()As Bitmap";
 //BA.debugLineNum = 762;BA.debugLine="If ImgBaño.IsInitialized = False Then ImgBaño.Ini";
if (_imgbaño.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgbaño.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Bao.png");};
 //BA.debugLineNum = 763;BA.debugLine="Return 	ImgBaño";
if (true) return _imgbaño;
 //BA.debugLineNum = 764;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _aseolt(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 719;BA.debugLine="Sub Aseolt()As Bitmap";
 //BA.debugLineNum = 720;BA.debugLine="If ImgBañolt.IsInitialized = False Then ImgBañolt";
if (_imgbañolt.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgbañolt.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Baolt.png");};
 //BA.debugLineNum = 721;BA.debugLine="Return 	ImgBañolt";
if (true) return _imgbañolt;
 //BA.debugLineNum = 722;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _bombillaal(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 358;BA.debugLine="Sub BombillaAl()As Bitmap";
 //BA.debugLineNum = 359;BA.debugLine="If ImgBombillaAl.IsInitialized = False Then ImgBo";
if (_imgbombillaal.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgbombillaal.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bombillaal.png");};
 //BA.debugLineNum = 360;BA.debugLine="Return ImgBombillaAl";
if (true) return _imgbombillaal;
 //BA.debugLineNum = 361;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _bombillades(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 419;BA.debugLine="Sub Bombillades()As Bitmap";
 //BA.debugLineNum = 420;BA.debugLine="If ImgBombillades.IsInitialized = False Then ImgB";
if (_imgbombillades.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgbombillades.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"BombillaDes.png");};
 //BA.debugLineNum = 421;BA.debugLine="Return 	ImgBombillades";
if (true) return _imgbombillades;
 //BA.debugLineNum = 422;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _bombillaoff(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 411;BA.debugLine="Sub BombillaOff()As Bitmap";
 //BA.debugLineNum = 412;BA.debugLine="If ImgBombillaOff.IsInitialized = False Then ImgB";
if (_imgbombillaoff.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgbombillaoff.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"BombillaOff.png");};
 //BA.debugLineNum = 413;BA.debugLine="Return 	ImgBombillaOff";
if (true) return _imgbombillaoff;
 //BA.debugLineNum = 414;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _bombillaon(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 415;BA.debugLine="Sub BombillaOn()As Bitmap";
 //BA.debugLineNum = 416;BA.debugLine="If ImgBombillaOn.IsInitialized = False Then ImgBo";
if (_imgbombillaon.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgbombillaon.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"BombillaOn.png");};
 //BA.debugLineNum = 417;BA.debugLine="Return 	ImgBombillaOn";
if (true) return _imgbombillaon;
 //BA.debugLineNum = 418;BA.debugLine="End Sub";
return null;
}
public static String  _buldmenus(anywheresoftware.b4a.BA _ba,arduino.automatizacion.excontrol.PRO.slidemenu _slmenu,int _pos) throws Exception{
int _c = 0;
 //BA.debugLineNum = 2014;BA.debugLine="Sub BuldMenus(SlMenu As SlideMenu, Pos As Int)";
 //BA.debugLineNum = 2017;BA.debugLine="SlMenu.AddItem(LanString .GetDefault(\"reg187\",\"Me";
_slmenu._additem(BA.ObjectToString(_lanstring.GetDefault((Object)("reg187"),(Object)("Menu"))),_mnuimg(_ba),(Object)(1));
 //BA.debugLineNum = 2019;BA.debugLine="If Pos <>2 Then SlMenu.AddItem(LanString .GetDefa";
if (_pos!=2) { 
_slmenu._additem(BA.ObjectToString(_lanstring.GetDefault((Object)("reg151"),(Object)("Circuit"))),_mnucircuitos(_ba),(Object)(2));};
 //BA.debugLineNum = 2020;BA.debugLine="If Pos <>3 Then SlMenu.AddItem(LanString .GetDefa";
if (_pos!=3) { 
_slmenu._additem(BA.ObjectToString(_lanstring.GetDefault((Object)("reg152"),(Object)("Scene"))),_mnuscene(_ba),(Object)(3));};
 //BA.debugLineNum = 2021;BA.debugLine="If Pos <>4 Then SlMenu.AddItem(LanString .GetDefa";
if (_pos!=4) { 
_slmenu._additem(BA.ObjectToString(_lanstring.GetDefault((Object)("reg135"),(Object)("Notification"))),_mnualarmas(_ba),(Object)(4));};
 //BA.debugLineNum = 2022;BA.debugLine="If Pos <>5 Then SlMenu.AddItem(LanString .GetDefa";
if (_pos!=5) { 
_slmenu._additem(BA.ObjectToString(_lanstring.GetDefault((Object)("reg131"),(Object)("Conditioned"))),_mnucondicionado(_ba),(Object)(5));};
 //BA.debugLineNum = 2023;BA.debugLine="If Pos <>6 Then SlMenu.AddItem(LanString .GetDefa";
if (_pos!=6) { 
_slmenu._additem(BA.ObjectToString(_lanstring.GetDefault((Object)("reg132"),(Object)("Commands"))),_mnucomandos(_ba),(Object)(6));};
 //BA.debugLineNum = 2024;BA.debugLine="If Pos <>7 Then";
if (_pos!=7) { 
 //BA.debugLineNum = 2026;BA.debugLine="SlMenu.AddItem(LanString .GetDefault(\"Sensor\",\"S";
_slmenu._additem(BA.ObjectToString(_lanstring.GetDefault((Object)("Sensor"),(Object)("Sensor"))),_mnusensor(_ba),(Object)(7));
 }else {
 //BA.debugLineNum = 2028;BA.debugLine="SlMenu.AddItem(LanString .GetDefault(\"graphics\",";
_slmenu._additem(BA.ObjectToString(_lanstring.GetDefault((Object)("graphics"),(Object)("graphics"))),_mnusensor(_ba),(Object)(98));
 };
 //BA.debugLineNum = 2030;BA.debugLine="If Pos <>8  Then";
if (_pos!=8) { 
 //BA.debugLineNum = 2031;BA.debugLine="If File.Exists ( File.DirInternal ,\"Sensores\" &";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Sensores"+_central.Nombre)) { 
_slmenu._additem(BA.ObjectToString(_lanstring.GetDefault((Object)("Status"),(Object)("Status"))),_mnutxt(_ba),(Object)(8));};
 };
 //BA.debugLineNum = 2033;BA.debugLine="If Pos <>9  Then";
if (_pos!=9) { 
 //BA.debugLineNum = 2034;BA.debugLine="If SetPoint.Size >0   Then SlMenu.AddItem(GetLan";
if (_setpoint.getSize()>0) { 
_slmenu._additem(_getlanstringdefault(_ba,"reg133","Setpoint"),_mnusetpointlt(_ba),(Object)(9));};
 };
 //BA.debugLineNum = 2037;BA.debugLine="If Pos <>11 Then";
if (_pos!=11) { 
 //BA.debugLineNum = 2038;BA.debugLine="SlMenu.AddItem(GetLanStringDefault (\"Function\"";
_slmenu._additem(_getlanstringdefault(_ba,"Function","Function"),_mnufuncionesp(_ba),(Object)(11));
 };
 //BA.debugLineNum = 2045;BA.debugLine="SlMenu.AddItem(GetLanStringDefault(\"close\",\"Close";
_slmenu._additem(_getlanstringdefault(_ba,"close","Close"),_mnuend(_ba),(Object)(10));
 //BA.debugLineNum = 2050;BA.debugLine="If Centrales.Size >1 Then";
if (_centrales.getSize()>1) { 
 //BA.debugLineNum = 2051;BA.debugLine="SlMenu.AddItem(\"\", Null, 99)";
_slmenu._additem("",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),(Object)(99));
 //BA.debugLineNum = 2052;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 2053;BA.debugLine="For C=0 To Centrales.Size-1";
{
final int step25 = 1;
final int limit25 = (int) (_centrales.getSize()-1);
_c = (int) (0) ;
for (;(step25 > 0 && _c <= limit25) || (step25 < 0 && _c >= limit25) ;_c = ((int)(0 + _c + step25))  ) {
 //BA.debugLineNum = 2054;BA.debugLine="If Centrales.Get (C) <> Central.Nombre Then SlM";
if ((_centrales.Get(_c)).equals((Object)(_central.Nombre)) == false) { 
_slmenu._additem(BA.ObjectToString(_centrales.Get(_c)),_getimgdevice(_ba,BA.ObjectToString(_centrales.Get(_c)),anywheresoftware.b4a.keywords.Common.False),(Object)(100+_c));};
 }
};
 };
 //BA.debugLineNum = 2062;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _calefaccional(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 344;BA.debugLine="Sub CalefaccionAl()As Bitmap";
 //BA.debugLineNum = 345;BA.debugLine="If ImgCalefaccionAl.IsInitialized = False Then Im";
if (_imgcalefaccional.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgcalefaccional.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"calefaccionAl.png");};
 //BA.debugLineNum = 346;BA.debugLine="Return 	ImgCalefaccionAl";
if (true) return _imgcalefaccional;
 //BA.debugLineNum = 347;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _calefacciondes(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 475;BA.debugLine="Sub Calefacciondes()As Bitmap";
 //BA.debugLineNum = 476;BA.debugLine="If ImgCalefacciondes.IsInitialized = False Then I";
if (_imgcalefacciondes.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgcalefacciondes.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"CalefaccionDes.png");};
 //BA.debugLineNum = 477;BA.debugLine="Return 	ImgCalefacciondes";
if (true) return _imgcalefacciondes;
 //BA.debugLineNum = 478;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _calefaccionoff(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 479;BA.debugLine="Sub CalefaccionOff()As Bitmap";
 //BA.debugLineNum = 480;BA.debugLine="If ImgCalefaccionOff.IsInitialized = False Then I";
if (_imgcalefaccionoff.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgcalefaccionoff.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"CalefaccionOff.png");};
 //BA.debugLineNum = 481;BA.debugLine="Return 	ImgCalefaccionOff";
if (true) return _imgcalefaccionoff;
 //BA.debugLineNum = 482;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _calefaccionon(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 484;BA.debugLine="Sub CalefaccionOn()As Bitmap";
 //BA.debugLineNum = 485;BA.debugLine="If ImgCalefaccionOn.IsInitialized = False Then Im";
if (_imgcalefaccionon.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgcalefaccionon.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"CalefaccionOn.png");};
 //BA.debugLineNum = 486;BA.debugLine="Return 	ImgCalefaccionOn";
if (true) return _imgcalefaccionon;
 //BA.debugLineNum = 487;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _calendario(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 520;BA.debugLine="Sub Calendario()As Bitmap";
 //BA.debugLineNum = 521;BA.debugLine="If ImgCalendario.IsInitialized = False Then ImgCa";
if (_imgcalendario.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgcalendario.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Calendario.png");};
 //BA.debugLineNum = 522;BA.debugLine="Return 	ImgCalendario";
if (true) return _imgcalendario;
 //BA.debugLineNum = 523;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _cama(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 765;BA.debugLine="Sub Cama()As Bitmap";
 //BA.debugLineNum = 766;BA.debugLine="If ImgCama.IsInitialized = False Then ImgCama.Ini";
if (_imgcama.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgcama.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Cama.png");};
 //BA.debugLineNum = 767;BA.debugLine="Return 	ImgCama";
if (true) return _imgcama;
 //BA.debugLineNum = 768;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _camalt(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 723;BA.debugLine="Sub Camalt()As Bitmap";
 //BA.debugLineNum = 724;BA.debugLine="If ImgCamalt.IsInitialized = False Then ImgCamalt";
if (_imgcamalt.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgcamalt.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Camalt.png");};
 //BA.debugLineNum = 725;BA.debugLine="Return 	ImgCamalt";
if (true) return _imgcamalt;
 //BA.debugLineNum = 726;BA.debugLine="End Sub";
return null;
}
public static String  _cambiarnombrecentral(anywheresoftware.b4a.BA _ba,String _oldname,String _newname) throws Exception{
int _pos = 0;
 //BA.debugLineNum = 1314;BA.debugLine="Sub CambiarNombreCentral(OldName As String,NewName";
 //BA.debugLineNum = 1315;BA.debugLine="If OldName=NewName Then Return";
if ((_oldname).equals(_newname)) { 
if (true) return "";};
 //BA.debugLineNum = 1316;BA.debugLine="If File.Exists (File.DirInternal ,\"Conect\" & OldN";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conect"+_oldname)) { 
 //BA.debugLineNum = 1317;BA.debugLine="File.Copy (File.DirInternal ,\"Conect\" & OldName,";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conect"+_oldname,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conexion"+_newname);
 //BA.debugLineNum = 1318;BA.debugLine="File.Delete (File.DirInternal ,\"Conect\" & OldNam";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conect"+_oldname);
 };
 //BA.debugLineNum = 1320;BA.debugLine="If File.Exists (File.DirInternal ,\"Conexion\" & Ol";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conexion"+_oldname)) { 
 //BA.debugLineNum = 1321;BA.debugLine="File.Copy (File.DirInternal ,\"Conexion\" & OldNam";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conexion"+_oldname,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conexion"+_newname);
 //BA.debugLineNum = 1322;BA.debugLine="File.Delete (File.DirInternal ,\"Conexion\" & OldN";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conexion"+_oldname);
 };
 //BA.debugLineNum = 1325;BA.debugLine="If File.Exists (File.DirInternal ,\"Comandos\" & Ol";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Comandos"+_oldname)) { 
 //BA.debugLineNum = 1326;BA.debugLine="File.Copy (File.DirInternal ,\"Comandos\" & OldNam";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Comandos"+_oldname,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Comandos"+_newname);
 //BA.debugLineNum = 1327;BA.debugLine="File.Delete (File.DirInternal ,\"Comandos\" & OldN";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Comandos"+_oldname);
 };
 //BA.debugLineNum = 1329;BA.debugLine="If File.Exists (File.DirInternal ,\"Sensores\" & Ol";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Sensores"+_oldname)) { 
 //BA.debugLineNum = 1330;BA.debugLine="File.Copy (File.DirInternal ,\"Sensores\" & OldNam";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Sensores"+_oldname,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Sensores"+_newname);
 //BA.debugLineNum = 1331;BA.debugLine="File.Delete (File.DirInternal ,\"Sensores\" & OldN";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Sensores"+_oldname);
 };
 //BA.debugLineNum = 1333;BA.debugLine="If File.Exists (File.DirInternal ,\"Condicionados\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Condicionados"+_oldname)) { 
 //BA.debugLineNum = 1334;BA.debugLine="File.Copy (File.DirInternal ,\"Condicionados\" & O";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Condicionados"+_oldname,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Condicionados"+_newname);
 //BA.debugLineNum = 1335;BA.debugLine="File.Delete (File.DirInternal ,\"Condicionados\" &";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Condicionados"+_oldname);
 };
 //BA.debugLineNum = 1337;BA.debugLine="If File.Exists (File.DirInternal ,\"ConfigCir\" & O";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigCir"+_oldname)) { 
 //BA.debugLineNum = 1338;BA.debugLine="File.Copy (File.DirInternal ,\"ConfigCir\" & OldNa";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigCir"+_oldname,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigCir"+_newname);
 //BA.debugLineNum = 1339;BA.debugLine="File.Delete (File.DirInternal ,\"ConfigCir\" & Old";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigCir"+_oldname);
 };
 //BA.debugLineNum = 1341;BA.debugLine="If File.Exists (File.DirInternal ,\"ConfigEsc\" & O";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigEsc"+_oldname)) { 
 //BA.debugLineNum = 1342;BA.debugLine="File.Copy (File.DirInternal ,\"ConfigEsc\" & OldNa";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigEsc"+_oldname,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigEsc"+_newname);
 //BA.debugLineNum = 1343;BA.debugLine="File.Delete (File.DirInternal ,\"ConfigEsc\" & Old";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigEsc"+_oldname);
 };
 //BA.debugLineNum = 1346;BA.debugLine="If File.Exists (File.DirInternal ,\"SSID\" & OldNam";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SSID"+_oldname)) { 
 //BA.debugLineNum = 1347;BA.debugLine="File.Copy (File.DirInternal ,\"SSID\" & OldName,Fi";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SSID"+_oldname,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SSID"+_newname);
 //BA.debugLineNum = 1348;BA.debugLine="File.Delete (File.DirInternal ,\"SSID\" & OldName)";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SSID"+_oldname);
 };
 //BA.debugLineNum = 1352;BA.debugLine="If File.Exists (File.DirInternal ,\"Consignas\" & O";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Consignas"+_oldname)) { 
 //BA.debugLineNum = 1353;BA.debugLine="File.Copy (File.DirInternal ,\"Consignas\" & OldNa";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Consignas"+_oldname,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Consignas"+_newname);
 //BA.debugLineNum = 1354;BA.debugLine="File.Delete (File.DirInternal ,\"Consignas\" & Old";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Consignas"+_oldname);
 };
 //BA.debugLineNum = 1357;BA.debugLine="If File.Exists (File.DirInternal ,\"ConsignasMax\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasMax"+_oldname)) { 
 //BA.debugLineNum = 1358;BA.debugLine="File.Copy (File.DirInternal ,\"ConsignasMax\" & Ol";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasMax"+_oldname,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasMax"+_newname);
 //BA.debugLineNum = 1359;BA.debugLine="File.Delete (File.DirInternal ,\"ConsignasMax\" &";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasMax"+_oldname);
 };
 //BA.debugLineNum = 1361;BA.debugLine="If File.Exists (File.DirInternal ,\"ConsignasMin\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasMin"+_oldname)) { 
 //BA.debugLineNum = 1362;BA.debugLine="File.Copy (File.DirInternal ,\"ConsignasMin\" & Ol";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasMin"+_oldname,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasMin"+_newname);
 //BA.debugLineNum = 1363;BA.debugLine="File.Delete (File.DirInternal ,\"ConsignasMin\" &";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasMin"+_oldname);
 };
 //BA.debugLineNum = 1365;BA.debugLine="If File.Exists (File.DirInternal ,\"ConsignasIcon\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasIcon"+_oldname)) { 
 //BA.debugLineNum = 1366;BA.debugLine="File.Copy (File.DirInternal ,\"ConsignasIcon\" & O";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasIcon"+_oldname,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasIcon"+_newname);
 //BA.debugLineNum = 1367;BA.debugLine="File.Delete (File.DirInternal ,\"ConsignasIcon\" &";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasIcon"+_oldname);
 };
 //BA.debugLineNum = 1370;BA.debugLine="If File.Exists (File.DirInternal ,\"Camara\" & OldN";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Camara"+_oldname)) { 
 //BA.debugLineNum = 1371;BA.debugLine="File.Copy (File.DirInternal ,\"Camara\" & OldName,";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Camara"+_oldname,anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Camara"+_newname);
 //BA.debugLineNum = 1372;BA.debugLine="File.Delete (File.DirInternal ,\"Camara\" & OldNam";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Camara"+_oldname);
 };
 //BA.debugLineNum = 1377;BA.debugLine="If Centrales.IsInitialized Then";
if (_centrales.IsInitialized()) { 
 //BA.debugLineNum = 1378;BA.debugLine="Dim pos As Int";
_pos = 0;
 //BA.debugLineNum = 1379;BA.debugLine="pos= Centrales.IndexOf (OldName)";
_pos = _centrales.IndexOf((Object)(_oldname));
 //BA.debugLineNum = 1380;BA.debugLine="If pos >=0 Then Centrales.RemoveAt (pos)";
if (_pos>=0) { 
_centrales.RemoveAt(_pos);};
 };
 //BA.debugLineNum = 1383;BA.debugLine="End Sub";
return "";
}
public static String  _cargaalarmas(anywheresoftware.b4a.BA _ba) throws Exception{
int _c = 0;
 //BA.debugLineNum = 1241;BA.debugLine="Sub CargaAlarmas";
 //BA.debugLineNum = 1242;BA.debugLine="If File.Exists ( File.DirInternal ,\"Alm\" & Centra";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Alm"+_central.Nombre)) { 
 //BA.debugLineNum = 1243;BA.debugLine="AlmName = File.ReadList(File.DirInternal  ,\"Alm\"";
_almname = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Alm"+_central.Nombre);
 }else {
 //BA.debugLineNum = 1245;BA.debugLine="AlmName.Initialize";
_almname.Initialize();
 //BA.debugLineNum = 1246;BA.debugLine="Dim c As Int";
_c = 0;
 //BA.debugLineNum = 1248;BA.debugLine="For c=0 To 19";
{
final int step6 = 1;
final int limit6 = (int) (19);
_c = (int) (0) ;
for (;(step6 > 0 && _c <= limit6) || (step6 < 0 && _c >= limit6) ;_c = ((int)(0 + _c + step6))  ) {
 //BA.debugLineNum = 1249;BA.debugLine="AlmName.Add (\"Alarma \" & c)";
_almname.Add((Object)("Alarma "+BA.NumberToString(_c)));
 }
};
 //BA.debugLineNum = 1251;BA.debugLine="File.WriteList(File.DirInternal  ,\"Alm\" & Centra";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Alm"+_central.Nombre,_almname);
 };
 //BA.debugLineNum = 1255;BA.debugLine="End Sub";
return "";
}
public static String  _cargacircuitos(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _c = 0;
int _ci = 0;
arduino.automatizacion.excontrol.PRO.valorescomunes._circuito _cir = null;
int _i = 0;
 //BA.debugLineNum = 1115;BA.debugLine="Sub CargaCircuitos";
 //BA.debugLineNum = 1119;BA.debugLine="If  File.Exists ( File.DirInternal ,\"ConfigCir\" &";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigCir"+_central.Nombre)) { 
 //BA.debugLineNum = 1120;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 1121;BA.debugLine="Lst = File.ReadList(File.DirInternal ,\"ConfigCir";
_lst = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigCir"+_central.Nombre);
 //BA.debugLineNum = 1122;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 1123;BA.debugLine="Dim ci As Int";
_ci = 0;
 //BA.debugLineNum = 1124;BA.debugLine="C=0";
_c = (int) (0);
 //BA.debugLineNum = 1125;BA.debugLine="ci =0";
_ci = (int) (0);
 //BA.debugLineNum = 1126;BA.debugLine="Do While ci < 30";
while (_ci<30) {
 //BA.debugLineNum = 1127;BA.debugLine="Dim cir As Circuito";
_cir = new arduino.automatizacion.excontrol.PRO.valorescomunes._circuito();
 //BA.debugLineNum = 1128;BA.debugLine="cir.Nombre = Lst.Get  (C)";
_cir.Nombre = BA.ObjectToString(_lst.Get(_c));
 //BA.debugLineNum = 1129;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 1130;BA.debugLine="cir.Descripcion =Lst.Get (C)";
_cir.Descripcion = BA.ObjectToString(_lst.Get(_c));
 //BA.debugLineNum = 1131;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 1132;BA.debugLine="If IsNumber(Lst.Get (C)) Then";
if (anywheresoftware.b4a.keywords.Common.IsNumber(BA.ObjectToString(_lst.Get(_c)))) { 
 //BA.debugLineNum = 1133;BA.debugLine="cir.Rango =Lst.Get (C)";
_cir.Rango = (int)(BA.ObjectToNumber(_lst.Get(_c)));
 }else {
 //BA.debugLineNum = 1135;BA.debugLine="cir.Rango =0";
_cir.Rango = (int) (0);
 };
 //BA.debugLineNum = 1137;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 1138;BA.debugLine="If C < Lst.Size Then";
if (_c<_lst.getSize()) { 
 //BA.debugLineNum = 1139;BA.debugLine="If IsNumber(Lst.Get (C)) Then";
if (anywheresoftware.b4a.keywords.Common.IsNumber(BA.ObjectToString(_lst.Get(_c)))) { 
 //BA.debugLineNum = 1140;BA.debugLine="cir.DeviceNumber  =Lst.Get (C)";
_cir.DeviceNumber = (int)(BA.ObjectToNumber(_lst.Get(_c)));
 //BA.debugLineNum = 1141;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 }else {
 //BA.debugLineNum = 1143;BA.debugLine="cir.DeviceNumber =0";
_cir.DeviceNumber = (int) (0);
 };
 };
 //BA.debugLineNum = 1147;BA.debugLine="Circuitos(ci)=cir";
_circuitos[_ci] = _cir;
 //BA.debugLineNum = 1149;BA.debugLine="ci=ci+1";
_ci = (int) (_ci+1);
 }
;
 }else {
 //BA.debugLineNum = 1153;BA.debugLine="Dim I As Int";
_i = 0;
 //BA.debugLineNum = 1154;BA.debugLine="For I =0 To 29";
{
final int step33 = 1;
final int limit33 = (int) (29);
_i = (int) (0) ;
for (;(step33 > 0 && _i <= limit33) || (step33 < 0 && _i >= limit33) ;_i = ((int)(0 + _i + step33))  ) {
 //BA.debugLineNum = 1155;BA.debugLine="Dim cir As Circuito";
_cir = new arduino.automatizacion.excontrol.PRO.valorescomunes._circuito();
 //BA.debugLineNum = 1156;BA.debugLine="cir.Nombre =\"Circuit Nº \"  & I";
_cir.Nombre = "Circuit Nº "+BA.NumberToString(_i);
 //BA.debugLineNum = 1157;BA.debugLine="cir.Descripcion =\"Circuit Description\"";
_cir.Descripcion = "Circuit Description";
 //BA.debugLineNum = 1158;BA.debugLine="cir.Rango =1000";
_cir.Rango = (int) (1000);
 //BA.debugLineNum = 1159;BA.debugLine="cir.DeviceNumber =1";
_cir.DeviceNumber = (int) (1);
 //BA.debugLineNum = 1160;BA.debugLine="Circuitos(I)= cir";
_circuitos[_i] = _cir;
 }
};
 //BA.debugLineNum = 1162;BA.debugLine="GuardarConfigCircuitos";
_guardarconfigcircuitos(_ba);
 };
 //BA.debugLineNum = 1164;BA.debugLine="End Sub";
return "";
}
public static String  _cargacomandos(anywheresoftware.b4a.BA _ba) throws Exception{
int _c = 0;
anywheresoftware.b4a.objects.collections.List _lst = null;
arduino.automatizacion.excontrol.PRO.valorescomunes._scene _con = null;
arduino.automatizacion.excontrol.PRO.valorescomunes._scene _com = null;
 //BA.debugLineNum = 932;BA.debugLine="Sub CargaComandos";
 //BA.debugLineNum = 934;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 935;BA.debugLine="If Comandos.IsInitialized Then";
if (_comandos.IsInitialized()) { 
 //BA.debugLineNum = 936;BA.debugLine="Comandos.Clear";
_comandos.Clear();
 }else {
 //BA.debugLineNum = 938;BA.debugLine="Comandos.Initialize";
_comandos.Initialize();
 };
 //BA.debugLineNum = 941;BA.debugLine="If  File.Exists ( File.DirInternal ,\"Comandos\" &";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Comandos"+_central.Nombre)) { 
 //BA.debugLineNum = 942;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 943;BA.debugLine="Lst = File.ReadList(File.DirInternal ,\"Comandos\"";
_lst = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Comandos"+_central.Nombre);
 //BA.debugLineNum = 946;BA.debugLine="C=0";
_c = (int) (0);
 //BA.debugLineNum = 948;BA.debugLine="Do While C <  Lst.Size";
while (_c<_lst.getSize()) {
 //BA.debugLineNum = 949;BA.debugLine="Dim Con As Scene";
_con = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
 //BA.debugLineNum = 950;BA.debugLine="Con.Nombre = Lst.Get  (C)";
_con.Nombre = BA.ObjectToString(_lst.Get(_c));
 //BA.debugLineNum = 951;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 952;BA.debugLine="Con.Descripcion =Lst.Get (C)";
_con.Descripcion = BA.ObjectToString(_lst.Get(_c));
 //BA.debugLineNum = 953;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 955;BA.debugLine="Comandos.Add (Con )";
_comandos.Add((Object)(_con));
 }
;
 }else {
 //BA.debugLineNum = 960;BA.debugLine="For C =0 To 9";
{
final int step20 = 1;
final int limit20 = (int) (9);
_c = (int) (0) ;
for (;(step20 > 0 && _c <= limit20) || (step20 < 0 && _c >= limit20) ;_c = ((int)(0 + _c + step20))  ) {
 //BA.debugLineNum = 961;BA.debugLine="Dim com As Scene";
_com = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
 //BA.debugLineNum = 963;BA.debugLine="com.Nombre =\"COMMAND nº \" & (C+1)";
_com.Nombre = "COMMAND nº "+BA.NumberToString((_c+1));
 //BA.debugLineNum = 964;BA.debugLine="com.Descripcion =\"General Command\"";
_com.Descripcion = "General Command";
 //BA.debugLineNum = 965;BA.debugLine="Comandos.Add (com )";
_comandos.Add((Object)(_com));
 }
};
 //BA.debugLineNum = 968;BA.debugLine="GuardaComandos";
_guardacomandos(_ba);
 };
 //BA.debugLineNum = 971;BA.debugLine="CargaDescripcionesComandos";
_cargadescripcionescomandos(_ba);
 //BA.debugLineNum = 972;BA.debugLine="End Sub";
return "";
}
public static String  _cargacomandoscomunes(anywheresoftware.b4a.BA _ba) throws Exception{
int _c = 0;
anywheresoftware.b4a.objects.collections.List _lst = null;
arduino.automatizacion.excontrol.PRO.valorescomunes._scene _con = null;
arduino.automatizacion.excontrol.PRO.valorescomunes._scene _com = null;
 //BA.debugLineNum = 862;BA.debugLine="Sub CargaComandosComunes";
 //BA.debugLineNum = 864;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 865;BA.debugLine="If ComandosComunes.IsInitialized Then";
if (_comandoscomunes.IsInitialized()) { 
 //BA.debugLineNum = 866;BA.debugLine="ComandosComunes.Clear";
_comandoscomunes.Clear();
 }else {
 //BA.debugLineNum = 868;BA.debugLine="ComandosComunes.Initialize";
_comandoscomunes.Initialize();
 };
 //BA.debugLineNum = 871;BA.debugLine="If  File.Exists ( File.DirInternal ,\"ComandosComu";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ComandosComunes")) { 
 //BA.debugLineNum = 872;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 873;BA.debugLine="Lst = File.ReadList(File.DirInternal ,\"ComandosC";
_lst = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ComandosComunes");
 //BA.debugLineNum = 876;BA.debugLine="C=0";
_c = (int) (0);
 //BA.debugLineNum = 878;BA.debugLine="Do While C <  Lst.Size";
while (_c<_lst.getSize()) {
 //BA.debugLineNum = 879;BA.debugLine="Dim Con As Scene";
_con = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
 //BA.debugLineNum = 880;BA.debugLine="Con.Nombre = Lst.Get  (C)";
_con.Nombre = BA.ObjectToString(_lst.Get(_c));
 //BA.debugLineNum = 881;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 882;BA.debugLine="Con.Descripcion =Lst.Get (C)";
_con.Descripcion = BA.ObjectToString(_lst.Get(_c));
 //BA.debugLineNum = 883;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 885;BA.debugLine="ComandosComunes.Add (Con )";
_comandoscomunes.Add((Object)(_con));
 }
;
 }else {
 //BA.debugLineNum = 890;BA.debugLine="For C =0 To 9";
{
final int step20 = 1;
final int limit20 = (int) (9);
_c = (int) (0) ;
for (;(step20 > 0 && _c <= limit20) || (step20 < 0 && _c >= limit20) ;_c = ((int)(0 + _c + step20))  ) {
 //BA.debugLineNum = 891;BA.debugLine="Dim com As Scene";
_com = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
 //BA.debugLineNum = 893;BA.debugLine="com.Nombre =\"COMMAND ORDERS nº \" & (C+1)";
_com.Nombre = "COMMAND ORDERS nº "+BA.NumberToString((_c+1));
 //BA.debugLineNum = 894;BA.debugLine="com.Descripcion =\"Common Orders\"";
_com.Descripcion = "Common Orders";
 //BA.debugLineNum = 895;BA.debugLine="ComandosComunes.Add (com )";
_comandoscomunes.Add((Object)(_com));
 }
};
 //BA.debugLineNum = 898;BA.debugLine="GuardaComandosComunes";
_guardacomandoscomunes(_ba);
 };
 //BA.debugLineNum = 901;BA.debugLine="CargaDescripcionesComandosComunes";
_cargadescripcionescomandoscomunes(_ba);
 //BA.debugLineNum = 902;BA.debugLine="End Sub";
return "";
}
public static String  _cargacondicionados(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _c = 0;
int _c2 = 0;
arduino.automatizacion.excontrol.PRO.valorescomunes._scene _con = null;
 //BA.debugLineNum = 1016;BA.debugLine="Sub CargaCondicionados";
 //BA.debugLineNum = 1018;BA.debugLine="If  File.Exists ( File.DirInternal ,\"Condicionado";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Condicionados"+_central.Nombre)) { 
 //BA.debugLineNum = 1019;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 1020;BA.debugLine="Lst = File.ReadList(File.DirInternal ,\"Condicion";
_lst = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Condicionados"+_central.Nombre);
 //BA.debugLineNum = 1021;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 1022;BA.debugLine="Dim c2 As Int";
_c2 = 0;
 //BA.debugLineNum = 1023;BA.debugLine="C=0";
_c = (int) (0);
 //BA.debugLineNum = 1024;BA.debugLine="c2=0";
_c2 = (int) (0);
 //BA.debugLineNum = 1025;BA.debugLine="Do While c2 < 10";
while (_c2<10) {
 //BA.debugLineNum = 1026;BA.debugLine="Dim Con As Scene";
_con = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
 //BA.debugLineNum = 1027;BA.debugLine="Con.Nombre = Lst.Get  (C)";
_con.Nombre = BA.ObjectToString(_lst.Get(_c));
 //BA.debugLineNum = 1028;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 1029;BA.debugLine="Con.Descripcion =Lst.Get (C)";
_con.Descripcion = BA.ObjectToString(_lst.Get(_c));
 //BA.debugLineNum = 1030;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 1031;BA.debugLine="Condicionados(c2)=Con";
_condicionados[_c2] = _con;
 //BA.debugLineNum = 1032;BA.debugLine="c2=c2+1";
_c2 = (int) (_c2+1);
 }
;
 }else {
 //BA.debugLineNum = 1036;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 1037;BA.debugLine="For C =0 To 9";
{
final int step19 = 1;
final int limit19 = (int) (9);
_c = (int) (0) ;
for (;(step19 > 0 && _c <= limit19) || (step19 < 0 && _c >= limit19) ;_c = ((int)(0 + _c + step19))  ) {
 //BA.debugLineNum = 1038;BA.debugLine="Dim Con As Scene";
_con = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
 //BA.debugLineNum = 1039;BA.debugLine="Con.Nombre =\"Condition nº \" & (C+1)";
_con.Nombre = "Condition nº "+BA.NumberToString((_c+1));
 //BA.debugLineNum = 1040;BA.debugLine="Con.Descripcion =\"Descripcion \" & (C+1)";
_con.Descripcion = "Descripcion "+BA.NumberToString((_c+1));
 //BA.debugLineNum = 1041;BA.debugLine="Condicionados(C)= Con";
_condicionados[_c] = _con;
 }
};
 //BA.debugLineNum = 1045;BA.debugLine="GuardaCondicionados";
_guardacondicionados(_ba);
 };
 //BA.debugLineNum = 1049;BA.debugLine="End Sub";
return "";
}
public static String  _cargadescripcionescomandos(anywheresoftware.b4a.BA _ba) throws Exception{
int _c = 0;
arduino.automatizacion.excontrol.PRO.valorescomunes._scene _cmd = null;
 //BA.debugLineNum = 973;BA.debugLine="Sub CargaDescripcionesComandos";
 //BA.debugLineNum = 974;BA.debugLine="If DescripcionesComandos.IsInitialized Then";
if (_descripcionescomandos.IsInitialized()) { 
 //BA.debugLineNum = 975;BA.debugLine="DescripcionesComandos.Clear";
_descripcionescomandos.Clear();
 }else {
 //BA.debugLineNum = 977;BA.debugLine="DescripcionesComandos.Initialize";
_descripcionescomandos.Initialize();
 };
 //BA.debugLineNum = 979;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 980;BA.debugLine="Dim cmd As Scene";
_cmd = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
 //BA.debugLineNum = 981;BA.debugLine="For C =0 To Comandos.Size -1";
{
final int step8 = 1;
final int limit8 = (int) (_comandos.getSize()-1);
_c = (int) (0) ;
for (;(step8 > 0 && _c <= limit8) || (step8 < 0 && _c >= limit8) ;_c = ((int)(0 + _c + step8))  ) {
 //BA.debugLineNum = 982;BA.debugLine="cmd = Comandos.Get (C)";
_cmd = (arduino.automatizacion.excontrol.PRO.valorescomunes._scene)(_comandos.Get(_c));
 //BA.debugLineNum = 983;BA.debugLine="If DescripcionesComandos.IndexOf(cmd.Descripcion";
if (_descripcionescomandos.IndexOf((Object)(_cmd.Descripcion))==-1) { 
_descripcionescomandos.Add((Object)(_cmd.Descripcion));};
 }
};
 //BA.debugLineNum = 987;BA.debugLine="End Sub";
return "";
}
public static String  _cargadescripcionescomandoscomunes(anywheresoftware.b4a.BA _ba) throws Exception{
int _c = 0;
arduino.automatizacion.excontrol.PRO.valorescomunes._scene _cmd = null;
 //BA.debugLineNum = 917;BA.debugLine="Sub CargaDescripcionesComandosComunes";
 //BA.debugLineNum = 918;BA.debugLine="If DescripcionesComandosComunes.IsInitialized The";
if (_descripcionescomandoscomunes.IsInitialized()) { 
 //BA.debugLineNum = 919;BA.debugLine="DescripcionesComandosComunes.Clear";
_descripcionescomandoscomunes.Clear();
 }else {
 //BA.debugLineNum = 921;BA.debugLine="DescripcionesComandosComunes.Initialize";
_descripcionescomandoscomunes.Initialize();
 };
 //BA.debugLineNum = 923;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 924;BA.debugLine="Dim cmd As Scene";
_cmd = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
 //BA.debugLineNum = 925;BA.debugLine="For C =0 To  ComandosComunes.Size -1";
{
final int step8 = 1;
final int limit8 = (int) (_comandoscomunes.getSize()-1);
_c = (int) (0) ;
for (;(step8 > 0 && _c <= limit8) || (step8 < 0 && _c >= limit8) ;_c = ((int)(0 + _c + step8))  ) {
 //BA.debugLineNum = 926;BA.debugLine="cmd = ComandosComunes.Get (C)";
_cmd = (arduino.automatizacion.excontrol.PRO.valorescomunes._scene)(_comandoscomunes.Get(_c));
 //BA.debugLineNum = 927;BA.debugLine="If DescripcionesComandosComunes.IndexOf(cmd.Desc";
if (_descripcionescomandoscomunes.IndexOf((Object)(_cmd.Descripcion))==-1) { 
_descripcionescomandoscomunes.Add((Object)(_cmd.Descripcion));};
 }
};
 //BA.debugLineNum = 931;BA.debugLine="End Sub";
return "";
}
public static String  _cargafuciones(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _c = 0;
int _c2 = 0;
arduino.automatizacion.excontrol.PRO.valorescomunes._scene _esc = null;
 //BA.debugLineNum = 1202;BA.debugLine="Sub CargaFuciones";
 //BA.debugLineNum = 1205;BA.debugLine="If  File.Exists ( File.DirInternal ,\"ConfigFunc\"&";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigFunc"+_central.Nombre)) { 
 //BA.debugLineNum = 1206;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 1207;BA.debugLine="Lst = File.ReadList(File.DirInternal ,\"ConfigFun";
_lst = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigFunc"+_central.Nombre);
 //BA.debugLineNum = 1208;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 1209;BA.debugLine="Dim c2 As Int";
_c2 = 0;
 //BA.debugLineNum = 1210;BA.debugLine="C=0";
_c = (int) (0);
 //BA.debugLineNum = 1211;BA.debugLine="c2=0";
_c2 = (int) (0);
 //BA.debugLineNum = 1212;BA.debugLine="Do While c2 < 10";
while (_c2<10) {
 //BA.debugLineNum = 1213;BA.debugLine="Dim esc As Scene";
_esc = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
 //BA.debugLineNum = 1214;BA.debugLine="esc.Nombre = Lst.Get  (C)";
_esc.Nombre = BA.ObjectToString(_lst.Get(_c));
 //BA.debugLineNum = 1215;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 1216;BA.debugLine="esc.Descripcion =Lst.Get (C)";
_esc.Descripcion = BA.ObjectToString(_lst.Get(_c));
 //BA.debugLineNum = 1217;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 1218;BA.debugLine="Functions(c2)=esc";
_functions[_c2] = _esc;
 //BA.debugLineNum = 1219;BA.debugLine="c2=c2+1";
_c2 = (int) (_c2+1);
 }
;
 }else {
 //BA.debugLineNum = 1223;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 1224;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 1225;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 1226;BA.debugLine="For C =0 To 9";
{
final int step21 = 1;
final int limit21 = (int) (9);
_c = (int) (0) ;
for (;(step21 > 0 && _c <= limit21) || (step21 < 0 && _c >= limit21) ;_c = ((int)(0 + _c + step21))  ) {
 //BA.debugLineNum = 1227;BA.debugLine="Dim esc As Scene";
_esc = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
 //BA.debugLineNum = 1228;BA.debugLine="esc.Nombre =\"Functions nº \" & (C+1)";
_esc.Nombre = "Functions nº "+BA.NumberToString((_c+1));
 //BA.debugLineNum = 1229;BA.debugLine="esc.Descripcion =\"Descripcion functions \" & (C+";
_esc.Descripcion = "Descripcion functions "+BA.NumberToString((_c+1));
 //BA.debugLineNum = 1230;BA.debugLine="Functions(C)= esc";
_functions[_c] = _esc;
 //BA.debugLineNum = 1233;BA.debugLine="Lst.Add (esc.Nombre )";
_lst.Add((Object)(_esc.Nombre));
 //BA.debugLineNum = 1234;BA.debugLine="Lst.Add (esc.Descripcion )";
_lst.Add((Object)(_esc.Descripcion));
 }
};
 //BA.debugLineNum = 1238;BA.debugLine="File.WriteList(File.DirInternal ,\"ConfigFunc\" &";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigFunc"+_central.Nombre,_lst);
 };
 //BA.debugLineNum = 1240;BA.debugLine="End Sub";
return "";
}
public static String  _cargaip(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
 //BA.debugLineNum = 781;BA.debugLine="Sub CargaIp";
 //BA.debugLineNum = 782;BA.debugLine="Dim lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 783;BA.debugLine="If File.Exists ( File.DirInternal ,\"Conect\" & Cen";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conect"+_central.Nombre)) { 
 //BA.debugLineNum = 784;BA.debugLine="Dim lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 785;BA.debugLine="lst = File.ReadList(File.DirInternal  ,\"Conect\"";
_lst = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conect"+_central.Nombre);
 //BA.debugLineNum = 787;BA.debugLine="Central.IpIn = lst.Get (0)";
_central.IpIn = BA.ObjectToString(_lst.Get((int) (0)));
 //BA.debugLineNum = 789;BA.debugLine="Central.ipout=   lst.Get (1)";
_central.IpOut = BA.ObjectToString(_lst.Get((int) (1)));
 //BA.debugLineNum = 790;BA.debugLine="Central.PuertoIn = lst.Get (2)";
_central.PuertoIn = (int)(BA.ObjectToNumber(_lst.Get((int) (2))));
 //BA.debugLineNum = 791;BA.debugLine="Central.PuertoOut = lst.Get (3)";
_central.PuertoOut = (int)(BA.ObjectToNumber(_lst.Get((int) (3))));
 //BA.debugLineNum = 792;BA.debugLine="Central.Descripcion =lst.Get (4)";
_central.Descripcion = BA.ObjectToString(_lst.Get((int) (4)));
 //BA.debugLineNum = 793;BA.debugLine="Central.Icon=lst.Get (5)";
_central.Icon = (int)(BA.ObjectToNumber(_lst.Get((int) (5))));
 //BA.debugLineNum = 794;BA.debugLine="Central.Mail =lst.Get (6)";
_central.mail = BA.ObjectToString(_lst.Get((int) (6)));
 //BA.debugLineNum = 795;BA.debugLine="Central.Password =lst.Get (7)";
_central.Password = BA.ObjectToString(_lst.Get((int) (7)));
 //BA.debugLineNum = 798;BA.debugLine="Central.ConexionSegura =lst.Get (8)";
_central.ConexionSegura = BA.ObjectToBoolean(_lst.Get((int) (8)));
 //BA.debugLineNum = 800;BA.debugLine="PassswordByte =Central.Password.GetBytes (\"UTF8";
_passswordbyte = _central.Password.getBytes("UTF8");
 }else {
 //BA.debugLineNum = 806;BA.debugLine="If File.Exists ( File.DirInternal ,\"Conexion\" &";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conexion"+_central.Nombre)) { 
 //BA.debugLineNum = 808;BA.debugLine="lst = File.ReadList(File.DirInternal  ,\"Conexion";
_lst = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conexion"+_central.Nombre);
 //BA.debugLineNum = 809;BA.debugLine="Central.IpIn= lst.Get (0)";
_central.IpIn = BA.ObjectToString(_lst.Get((int) (0)));
 //BA.debugLineNum = 810;BA.debugLine="Central.ipout= lst.Get (1)";
_central.IpOut = BA.ObjectToString(_lst.Get((int) (1)));
 //BA.debugLineNum = 811;BA.debugLine="Central.PuertoIn= lst.Get (2)";
_central.PuertoIn = (int)(BA.ObjectToNumber(_lst.Get((int) (2))));
 //BA.debugLineNum = 812;BA.debugLine="Central.PuertoOut= lst.Get (3)";
_central.PuertoOut = (int)(BA.ObjectToNumber(_lst.Get((int) (3))));
 //BA.debugLineNum = 813;BA.debugLine="Central.Descripcion=LanString.GetDefault  (\"PC\",";
_central.Descripcion = BA.ObjectToString(_lanstring.GetDefault((Object)("PC"),(Object)("Push to connect")));
 //BA.debugLineNum = 814;BA.debugLine="Central.Mail =\"\"";
_central.mail = "";
 //BA.debugLineNum = 815;BA.debugLine="Central.Password =\"\"";
_central.Password = "";
 //BA.debugLineNum = 816;BA.debugLine="Central.ConexionSegura =False";
_central.ConexionSegura = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 817;BA.debugLine="Central.Icon=0";
_central.Icon = (int) (0);
 //BA.debugLineNum = 822;BA.debugLine="PassswordByte =Central.Password.GetBytes (\"UTF8\"";
_passswordbyte = _central.Password.getBytes("UTF8");
 //BA.debugLineNum = 824;BA.debugLine="File.Delete ( File.DirInternal ,\"Conexion\" & Cen";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conexion"+_central.Nombre);
 //BA.debugLineNum = 825;BA.debugLine="GuardaCentral(Central)";
_guardacentral(_ba,_central);
 }else {
 //BA.debugLineNum = 831;BA.debugLine="Central.IpIn = \"192.168.1.200\"";
_central.IpIn = "192.168.1.200";
 //BA.debugLineNum = 832;BA.debugLine="Central.IpOut = \"192.168.1.200\"";
_central.IpOut = "192.168.1.200";
 //BA.debugLineNum = 833;BA.debugLine="Central.PuertoIn = 5000";
_central.PuertoIn = (int) (5000);
 //BA.debugLineNum = 834;BA.debugLine="Central.PuertoOut = 5000";
_central.PuertoOut = (int) (5000);
 //BA.debugLineNum = 835;BA.debugLine="Central.Descripcion =LanString.GetDefault  (\"PC";
_central.Descripcion = BA.ObjectToString(_lanstring.GetDefault((Object)("PC"),(Object)("Push to connect")));
 //BA.debugLineNum = 836;BA.debugLine="Central.Mail =\"\"";
_central.mail = "";
 //BA.debugLineNum = 837;BA.debugLine="Central.Password =\"\"";
_central.Password = "";
 //BA.debugLineNum = 838;BA.debugLine="Central.ConexionSegura =False";
_central.ConexionSegura = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 839;BA.debugLine="Central.Icon=0";
_central.Icon = (int) (0);
 //BA.debugLineNum = 840;BA.debugLine="GuardaCentral(Central)";
_guardacentral(_ba,_central);
 //BA.debugLineNum = 841;BA.debugLine="PassswordByte =Central.Password.GetBytes (\"UTF8";
_passswordbyte = _central.Password.getBytes("UTF8");
 };
 };
 //BA.debugLineNum = 845;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _cargando(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 558;BA.debugLine="Sub Cargando()As Bitmap";
 //BA.debugLineNum = 559;BA.debugLine="If ImgCargando.IsInitialized = False Then ImgCarg";
if (_imgcargando.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgcargando.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"cargando.png");};
 //BA.debugLineNum = 560;BA.debugLine="Return 	ImgCargando";
if (true) return _imgcargando;
 //BA.debugLineNum = 561;BA.debugLine="End Sub";
return null;
}
public static String  _carganombresensores(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 1002;BA.debugLine="Sub CargaNombreSensores";
 //BA.debugLineNum = 1004;BA.debugLine="If File.Exists ( File.DirInternal ,\"Sensores\"& Ce";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Sensores"+_central.Nombre)) { 
 //BA.debugLineNum = 1005;BA.debugLine="NombreSensor = File.ReadList(File.DirInternal  ,";
_nombresensor = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Sensores"+_central.Nombre);
 }else {
 //BA.debugLineNum = 1008;BA.debugLine="If NombreSensor.IsInitialized  = False Then";
if (_nombresensor.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 1009;BA.debugLine="NombreSensor.Initialize";
_nombresensor.Initialize();
 }else {
 //BA.debugLineNum = 1011;BA.debugLine="NombreSensor.Clear";
_nombresensor.Clear();
 };
 };
 //BA.debugLineNum = 1015;BA.debugLine="End Sub";
return "";
}
public static String  _cargascenes(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _c = 0;
int _c2 = 0;
arduino.automatizacion.excontrol.PRO.valorescomunes._scene _esc = null;
 //BA.debugLineNum = 1256;BA.debugLine="Sub CargaScenes";
 //BA.debugLineNum = 1259;BA.debugLine="If  File.Exists ( File.DirInternal ,\"ConfigEsc\"&";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigEsc"+_central.Nombre)) { 
 //BA.debugLineNum = 1260;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 1261;BA.debugLine="Lst = File.ReadList(File.DirInternal ,\"ConfigEsc";
_lst = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigEsc"+_central.Nombre);
 //BA.debugLineNum = 1262;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 1263;BA.debugLine="Dim c2 As Int";
_c2 = 0;
 //BA.debugLineNum = 1264;BA.debugLine="C=0";
_c = (int) (0);
 //BA.debugLineNum = 1265;BA.debugLine="c2=0";
_c2 = (int) (0);
 //BA.debugLineNum = 1266;BA.debugLine="Do While c2 < 10";
while (_c2<10) {
 //BA.debugLineNum = 1267;BA.debugLine="Dim esc As Scene";
_esc = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
 //BA.debugLineNum = 1268;BA.debugLine="esc.Nombre = Lst.Get  (C)";
_esc.Nombre = BA.ObjectToString(_lst.Get(_c));
 //BA.debugLineNum = 1269;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 1270;BA.debugLine="esc.Descripcion =Lst.Get (C)";
_esc.Descripcion = BA.ObjectToString(_lst.Get(_c));
 //BA.debugLineNum = 1271;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 1272;BA.debugLine="Scenes(c2)=esc";
_scenes[_c2] = _esc;
 //BA.debugLineNum = 1273;BA.debugLine="c2=c2+1";
_c2 = (int) (_c2+1);
 }
;
 }else {
 //BA.debugLineNum = 1277;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 1278;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 1279;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 1280;BA.debugLine="For C =0 To 9";
{
final int step21 = 1;
final int limit21 = (int) (9);
_c = (int) (0) ;
for (;(step21 > 0 && _c <= limit21) || (step21 < 0 && _c >= limit21) ;_c = ((int)(0 + _c + step21))  ) {
 //BA.debugLineNum = 1281;BA.debugLine="Dim esc As Scene";
_esc = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
 //BA.debugLineNum = 1282;BA.debugLine="esc.Nombre =\"Scene nº \" & (C+1)";
_esc.Nombre = "Scene nº "+BA.NumberToString((_c+1));
 //BA.debugLineNum = 1283;BA.debugLine="esc.Descripcion =\"Descripcion Scene \" & (C+1)";
_esc.Descripcion = "Descripcion Scene "+BA.NumberToString((_c+1));
 //BA.debugLineNum = 1284;BA.debugLine="Scenes(C)= esc";
_scenes[_c] = _esc;
 //BA.debugLineNum = 1287;BA.debugLine="Lst.Add (esc.Nombre )";
_lst.Add((Object)(_esc.Nombre));
 //BA.debugLineNum = 1288;BA.debugLine="Lst.Add (esc.Descripcion )";
_lst.Add((Object)(_esc.Descripcion));
 }
};
 //BA.debugLineNum = 1292;BA.debugLine="File.WriteList(File.DirInternal ,\"ConfigEsc\" & C";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigEsc"+_central.Nombre,_lst);
 };
 //BA.debugLineNum = 1294;BA.debugLine="End Sub";
return "";
}
public static String  _cargasensores(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _c = 0;
int _ci = 0;
arduino.automatizacion.excontrol.PRO.valorescomunes._circuito _cir = null;
int _i = 0;
 //BA.debugLineNum = 1064;BA.debugLine="Sub CargaSensores";
 //BA.debugLineNum = 1068;BA.debugLine="If  File.Exists ( File.DirInternal ,\"ConfigSenso\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigSenso"+_central.Nombre)) { 
 //BA.debugLineNum = 1069;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 1070;BA.debugLine="Lst = File.ReadList(File.DirInternal ,\"ConfigSen";
_lst = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigSenso"+_central.Nombre);
 //BA.debugLineNum = 1071;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 1072;BA.debugLine="Dim ci As Int";
_ci = 0;
 //BA.debugLineNum = 1073;BA.debugLine="C=0";
_c = (int) (0);
 //BA.debugLineNum = 1074;BA.debugLine="ci =0";
_ci = (int) (0);
 //BA.debugLineNum = 1075;BA.debugLine="Do While ci < 15";
while (_ci<15) {
 //BA.debugLineNum = 1076;BA.debugLine="Dim cir As Circuito";
_cir = new arduino.automatizacion.excontrol.PRO.valorescomunes._circuito();
 //BA.debugLineNum = 1077;BA.debugLine="cir.Nombre = Lst.Get  (C)";
_cir.Nombre = BA.ObjectToString(_lst.Get(_c));
 //BA.debugLineNum = 1078;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 1079;BA.debugLine="cir.Descripcion =Lst.Get (C)";
_cir.Descripcion = BA.ObjectToString(_lst.Get(_c));
 //BA.debugLineNum = 1080;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 1081;BA.debugLine="If IsNumber(Lst.Get (C)) Then";
if (anywheresoftware.b4a.keywords.Common.IsNumber(BA.ObjectToString(_lst.Get(_c)))) { 
 //BA.debugLineNum = 1082;BA.debugLine="cir.Rango =Lst.Get (C)";
_cir.Rango = (int)(BA.ObjectToNumber(_lst.Get(_c)));
 }else {
 //BA.debugLineNum = 1084;BA.debugLine="cir.Rango =0";
_cir.Rango = (int) (0);
 };
 //BA.debugLineNum = 1086;BA.debugLine="Sensores(ci)=cir";
_sensores[_ci] = _cir;
 //BA.debugLineNum = 1087;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 1088;BA.debugLine="ci=ci+1";
_ci = (int) (_ci+1);
 }
;
 }else {
 //BA.debugLineNum = 1092;BA.debugLine="Dim I As Int";
_i = 0;
 //BA.debugLineNum = 1093;BA.debugLine="For I =0 To 14";
{
final int step25 = 1;
final int limit25 = (int) (14);
_i = (int) (0) ;
for (;(step25 > 0 && _i <= limit25) || (step25 < 0 && _i >= limit25) ;_i = ((int)(0 + _i + step25))  ) {
 //BA.debugLineNum = 1094;BA.debugLine="Dim cir As Circuito";
_cir = new arduino.automatizacion.excontrol.PRO.valorescomunes._circuito();
 //BA.debugLineNum = 1095;BA.debugLine="cir.Nombre =\"Sensor Nº \"  & I";
_cir.Nombre = "Sensor Nº "+BA.NumberToString(_i);
 //BA.debugLineNum = 1096;BA.debugLine="cir.Descripcion =\"Sensor Description\"";
_cir.Descripcion = "Sensor Description";
 //BA.debugLineNum = 1097;BA.debugLine="cir.Rango =1000";
_cir.Rango = (int) (1000);
 //BA.debugLineNum = 1098;BA.debugLine="Sensores(I)= cir";
_sensores[_i] = _cir;
 }
};
 //BA.debugLineNum = 1100;BA.debugLine="GuardarConfigSensores";
_guardarconfigsensores(_ba);
 };
 //BA.debugLineNum = 1102;BA.debugLine="End Sub";
return "";
}
public static String  _cargasetpoint(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.List _l = null;
int _c = 0;
arduino.automatizacion.excontrol.PRO.valorescomunes._sp _s = null;
arduino.automatizacion.excontrol.PRO.valorescomunes._sp _e = null;
 //BA.debugLineNum = 97;BA.debugLine="Sub CargaSetPoint";
 //BA.debugLineNum = 98;BA.debugLine="If SetPoint.IsInitialized =False Then";
if (_setpoint.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 99;BA.debugLine="SetPoint.Initialize";
_setpoint.Initialize();
 }else {
 //BA.debugLineNum = 101;BA.debugLine="SetPoint.Clear";
_setpoint.Clear();
 };
 //BA.debugLineNum = 104;BA.debugLine="Dim l As List";
_l = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 105;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 107;BA.debugLine="If  File.Exists ( File.DirInternal ,\"Consignas\" &";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Consignas"+_central.Nombre)) { 
 //BA.debugLineNum = 109;BA.debugLine="l = File.ReadList (File.DirInternal ,\"Consignas\"";
_l = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Consignas"+_central.Nombre);
 //BA.debugLineNum = 112;BA.debugLine="For C=0 To l.Size -1";
{
final int step10 = 1;
final int limit10 = (int) (_l.getSize()-1);
_c = (int) (0) ;
for (;(step10 > 0 && _c <= limit10) || (step10 < 0 && _c >= limit10) ;_c = ((int)(0 + _c + step10))  ) {
 //BA.debugLineNum = 114;BA.debugLine="Dim s As  Sp";
_s = new arduino.automatizacion.excontrol.PRO.valorescomunes._sp();
 //BA.debugLineNum = 115;BA.debugLine="s.Nombre =l.Get (C)";
_s.Nombre = BA.ObjectToString(_l.Get(_c));
 //BA.debugLineNum = 116;BA.debugLine="s.Maximo =65000";
_s.Maximo = (int) (65000);
 //BA.debugLineNum = 117;BA.debugLine="s.Minimo =0";
_s.Minimo = (int) (0);
 //BA.debugLineNum = 119;BA.debugLine="s.Icon =4";
_s.Icon = (int) (4);
 //BA.debugLineNum = 124;BA.debugLine="SetPoint.Add (s)";
_setpoint.Add((Object)(_s));
 }
};
 };
 //BA.debugLineNum = 128;BA.debugLine="If  File.Exists ( File.DirInternal ,\"ConsignasMax";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasMax"+_central.Nombre)) { 
 //BA.debugLineNum = 130;BA.debugLine="l = File.ReadList (File.DirInternal ,\"ConsignasM";
_l = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasMax"+_central.Nombre);
 //BA.debugLineNum = 133;BA.debugLine="For C=0 To l.Size -1";
{
final int step21 = 1;
final int limit21 = (int) (_l.getSize()-1);
_c = (int) (0) ;
for (;(step21 > 0 && _c <= limit21) || (step21 < 0 && _c >= limit21) ;_c = ((int)(0 + _c + step21))  ) {
 //BA.debugLineNum = 134;BA.debugLine="If SetPoint.Size > C Then";
if (_setpoint.getSize()>_c) { 
 //BA.debugLineNum = 135;BA.debugLine="Dim e As Sp";
_e = new arduino.automatizacion.excontrol.PRO.valorescomunes._sp();
 //BA.debugLineNum = 136;BA.debugLine="e= SetPoint.Get (C)";
_e = (arduino.automatizacion.excontrol.PRO.valorescomunes._sp)(_setpoint.Get(_c));
 //BA.debugLineNum = 137;BA.debugLine="e.Maximo = l.Get (C)";
_e.Maximo = (int)(BA.ObjectToNumber(_l.Get(_c)));
 };
 }
};
 };
 //BA.debugLineNum = 143;BA.debugLine="If  File.Exists ( File.DirInternal ,\"ConsignasMin";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasMin"+_central.Nombre)) { 
 //BA.debugLineNum = 145;BA.debugLine="l = File.ReadList (File.DirInternal ,\"ConsignasM";
_l = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasMin"+_central.Nombre);
 //BA.debugLineNum = 148;BA.debugLine="For C=0 To l.Size -1";
{
final int step31 = 1;
final int limit31 = (int) (_l.getSize()-1);
_c = (int) (0) ;
for (;(step31 > 0 && _c <= limit31) || (step31 < 0 && _c >= limit31) ;_c = ((int)(0 + _c + step31))  ) {
 //BA.debugLineNum = 149;BA.debugLine="If SetPoint.Size > C Then";
if (_setpoint.getSize()>_c) { 
 //BA.debugLineNum = 150;BA.debugLine="Dim e As Sp";
_e = new arduino.automatizacion.excontrol.PRO.valorescomunes._sp();
 //BA.debugLineNum = 151;BA.debugLine="e= SetPoint.Get (C)";
_e = (arduino.automatizacion.excontrol.PRO.valorescomunes._sp)(_setpoint.Get(_c));
 //BA.debugLineNum = 152;BA.debugLine="e.Minimo  = l.Get (C)";
_e.Minimo = (int)(BA.ObjectToNumber(_l.Get(_c)));
 };
 }
};
 };
 //BA.debugLineNum = 158;BA.debugLine="If  File.Exists ( File.DirInternal ,\"ConsignasIco";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasIcon"+_central.Nombre)) { 
 //BA.debugLineNum = 160;BA.debugLine="l = File.ReadList (File.DirInternal ,\"ConsignasI";
_l = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasIcon"+_central.Nombre);
 //BA.debugLineNum = 163;BA.debugLine="For C=0 To l.Size -1";
{
final int step41 = 1;
final int limit41 = (int) (_l.getSize()-1);
_c = (int) (0) ;
for (;(step41 > 0 && _c <= limit41) || (step41 < 0 && _c >= limit41) ;_c = ((int)(0 + _c + step41))  ) {
 //BA.debugLineNum = 164;BA.debugLine="If SetPoint.Size > C Then";
if (_setpoint.getSize()>_c) { 
 //BA.debugLineNum = 165;BA.debugLine="Dim e As Sp";
_e = new arduino.automatizacion.excontrol.PRO.valorescomunes._sp();
 //BA.debugLineNum = 166;BA.debugLine="e= SetPoint.Get (C)";
_e = (arduino.automatizacion.excontrol.PRO.valorescomunes._sp)(_setpoint.Get(_c));
 //BA.debugLineNum = 167;BA.debugLine="e.Icon   = l.Get (C)";
_e.Icon = (int)(BA.ObjectToNumber(_l.Get(_c)));
 };
 }
};
 };
 //BA.debugLineNum = 175;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _checkoff(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 406;BA.debugLine="Sub CheckOff()As Bitmap";
 //BA.debugLineNum = 407;BA.debugLine="If ImgCheckOff.IsInitialized = False Then ImgChec";
if (_imgcheckoff.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgcheckoff.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"CheckOff.png");};
 //BA.debugLineNum = 408;BA.debugLine="Return 	ImgCheckOff";
if (true) return _imgcheckoff;
 //BA.debugLineNum = 409;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _checkon(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 402;BA.debugLine="Sub CheckOn()As Bitmap";
 //BA.debugLineNum = 403;BA.debugLine="If ImgCheckOn.IsInitialized = False Then ImgCheck";
if (_imgcheckon.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgcheckon.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"CheckOn.png");};
 //BA.debugLineNum = 404;BA.debugLine="Return ImgCheckOn";
if (true) return _imgcheckon;
 //BA.debugLineNum = 405;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _cmdimgback(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 738;BA.debugLine="Sub CmdImgBack()As Bitmap";
 //BA.debugLineNum = 739;BA.debugLine="If ImgBack.IsInitialized = False Then ImgBack.Ini";
if (_imgback.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgback.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"mnu.png");};
 //BA.debugLineNum = 740;BA.debugLine="Return 	ImgBack";
if (true) return _imgback;
 //BA.debugLineNum = 741;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _cmdimgnew(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 742;BA.debugLine="Sub CmdImgNew()As Bitmap";
 //BA.debugLineNum = 743;BA.debugLine="If ImgNew.IsInitialized = False Then ImgNew.Initi";
if (_imgnew.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgnew.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"new.png");};
 //BA.debugLineNum = 744;BA.debugLine="Return 	ImgNew";
if (true) return _imgnew;
 //BA.debugLineNum = 745;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _cmdimgsave(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 734;BA.debugLine="Sub CmdImgSave()As Bitmap";
 //BA.debugLineNum = 735;BA.debugLine="If ImgSave.IsInitialized = False Then ImgSave.Ini";
if (_imgsave.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgsave.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Save.png");};
 //BA.debugLineNum = 736;BA.debugLine="Return 	ImgSave";
if (true) return _imgsave;
 //BA.debugLineNum = 737;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _cochera(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 752;BA.debugLine="Sub Cochera()As Bitmap";
 //BA.debugLineNum = 753;BA.debugLine="If ImgCar.IsInitialized = False Then ImgCar.Initi";
if (_imgcar.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgcar.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Car.png");};
 //BA.debugLineNum = 754;BA.debugLine="Return 	ImgCar";
if (true) return _imgcar;
 //BA.debugLineNum = 755;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _cocheralt(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 710;BA.debugLine="Sub Cocheralt()As Bitmap";
 //BA.debugLineNum = 711;BA.debugLine="If ImgCarlt.IsInitialized = False Then ImgCarlt.I";
if (_imgcarlt.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgcarlt.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Carlt.png");};
 //BA.debugLineNum = 712;BA.debugLine="Return 	ImgCarlt";
if (true) return _imgcarlt;
 //BA.debugLineNum = 713;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _cocina(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 748;BA.debugLine="Sub Cocina()As Bitmap";
 //BA.debugLineNum = 749;BA.debugLine="If ImgCocina.IsInitialized = False Then ImgCocina";
if (_imgcocina.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgcocina.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Cocina.png");};
 //BA.debugLineNum = 750;BA.debugLine="Return 	ImgCocina";
if (true) return _imgcocina;
 //BA.debugLineNum = 751;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _cocinalt(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 704;BA.debugLine="Sub Cocinalt()As Bitmap";
 //BA.debugLineNum = 705;BA.debugLine="If ImgCocinalt.IsInitialized = False Then ImgCoci";
if (_imgcocinalt.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgcocinalt.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Cocinalt.png");};
 //BA.debugLineNum = 706;BA.debugLine="Return 	ImgCocinalt";
if (true) return _imgcocinalt;
 //BA.debugLineNum = 707;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _comando(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 549;BA.debugLine="Sub Comando()As Bitmap";
 //BA.debugLineNum = 550;BA.debugLine="If ImgComando.IsInitialized = False Then ImgComan";
if (_imgcomando.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgcomando.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Comando.png");};
 //BA.debugLineNum = 551;BA.debugLine="Return 	ImgComando";
if (true) return _imgcomando;
 //BA.debugLineNum = 552;BA.debugLine="End Sub";
return null;
}
public static String  _completartrama(anywheresoftware.b4a.BA _ba,byte[] _data) throws Exception{
int _p = 0;
 //BA.debugLineNum = 1296;BA.debugLine="Sub CompletarTrama( data() As Byte)";
 //BA.debugLineNum = 1299;BA.debugLine="Dim  p As Int";
_p = 0;
 //BA.debugLineNum = 1300;BA.debugLine="p = data.Length - 8";
_p = (int) (_data.length-8);
 //BA.debugLineNum = 1302;BA.debugLine="data (p)= PassswordByte(0)";
_data[_p] = _passswordbyte[(int) (0)];
 //BA.debugLineNum = 1303;BA.debugLine="data (p+1)= PassswordByte(1)";
_data[(int) (_p+1)] = _passswordbyte[(int) (1)];
 //BA.debugLineNum = 1304;BA.debugLine="data (p+2)= PassswordByte(2)";
_data[(int) (_p+2)] = _passswordbyte[(int) (2)];
 //BA.debugLineNum = 1305;BA.debugLine="data (p+3)= PassswordByte(3)";
_data[(int) (_p+3)] = _passswordbyte[(int) (3)];
 //BA.debugLineNum = 1306;BA.debugLine="data (p+4)= PassswordByte(4)";
_data[(int) (_p+4)] = _passswordbyte[(int) (4)];
 //BA.debugLineNum = 1307;BA.debugLine="data (p+5)= PassswordByte(5)";
_data[(int) (_p+5)] = _passswordbyte[(int) (5)];
 //BA.debugLineNum = 1308;BA.debugLine="data (p+6)= PassswordByte(6)";
_data[(int) (_p+6)] = _passswordbyte[(int) (6)];
 //BA.debugLineNum = 1309;BA.debugLine="data (p+7)= PassswordByte(7)";
_data[(int) (_p+7)] = _passswordbyte[(int) (7)];
 //BA.debugLineNum = 1310;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _config(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 567;BA.debugLine="Sub Config()As Bitmap";
 //BA.debugLineNum = 568;BA.debugLine="If ImgConfig.IsInitialized = False Then ImgConfig";
if (_imgconfig.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgconfig.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Config.png");};
 //BA.debugLineNum = 569;BA.debugLine="Return 	ImgConfig";
if (true) return _imgconfig;
 //BA.debugLineNum = 570;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _configdo(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 540;BA.debugLine="Sub configdo()As Bitmap";
 //BA.debugLineNum = 541;BA.debugLine="If Imgconfigdo.IsInitialized = False Then Imgconf";
if (_imgconfigdo.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgconfigdo.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"configdo.png");};
 //BA.debugLineNum = 542;BA.debugLine="Return 	Imgconfigdo";
if (true) return _imgconfigdo;
 //BA.debugLineNum = 543;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _confignames(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 251;BA.debugLine="Sub ConfigNames()As Bitmap";
 //BA.debugLineNum = 252;BA.debugLine="If ImgConNames.IsInitialized = False Then ImgConN";
if (_imgconnames.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgconnames.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"configName.png");};
 //BA.debugLineNum = 253;BA.debugLine="Return ImgConNames";
if (true) return _imgconnames;
 //BA.debugLineNum = 254;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _configup(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 545;BA.debugLine="Sub ConfigUp()As Bitmap";
 //BA.debugLineNum = 546;BA.debugLine="If ImgConfigUp.IsInitialized = False Then ImgConf";
if (_imgconfigup.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgconfigup.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ConfigUp.png");};
 //BA.debugLineNum = 547;BA.debugLine="Return 	ImgConfigUp";
if (true) return _imgconfigup;
 //BA.debugLineNum = 548;BA.debugLine="End Sub";
return null;
}
public static String  _creaarchivoconfigidioma(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
 //BA.debugLineNum = 237;BA.debugLine="Sub CreaArchivoConfigIdioma";
 //BA.debugLineNum = 239;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 240;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 241;BA.debugLine="Lst.Add (\"en\")";
_lst.Add((Object)("en"));
 //BA.debugLineNum = 242;BA.debugLine="Lst.Add (\"MODE\")	'PALABRA PARA SELECCION ESCENA";
_lst.Add((Object)("MODE"));
 //BA.debugLineNum = 243;BA.debugLine="Lst.Add (\"ON\") 'Palbra para activar";
_lst.Add((Object)("ON"));
 //BA.debugLineNum = 244;BA.debugLine="Lst.Add (\"OFF\")";
_lst.Add((Object)("OFF"));
 //BA.debugLineNum = 245;BA.debugLine="Lst.Add (\"UP\")";
_lst.Add((Object)("UP"));
 //BA.debugLineNum = 246;BA.debugLine="Lst.Add (\"DOWN\")";
_lst.Add((Object)("DOWN"));
 //BA.debugLineNum = 248;BA.debugLine="File.WriteList(File.DirInternal ,\"ConfigIdioma\"";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigIdioma",_lst);
 //BA.debugLineNum = 249;BA.debugLine="End Sub";
return "";
}
public static String  _crearmapaidioma(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 1386;BA.debugLine="Sub CrearMapaIdioma";
 //BA.debugLineNum = 1387;BA.debugLine="LanString.Initialize";
_lanstring.Initialize();
 //BA.debugLineNum = 1388;BA.debugLine="LanString.Put (\"ConReset\",\"Connection Reset\")";
_lanstring.Put((Object)("ConReset"),(Object)("Connection Reset"));
 //BA.debugLineNum = 1389;BA.debugLine="LanString.Put (\"mnuOrders\",\"Common Orders\")";
_lanstring.Put((Object)("mnuOrders"),(Object)("Common Orders"));
 //BA.debugLineNum = 1390;BA.debugLine="LanString.Put (\"cdt\",\"download configuration to";
_lanstring.Put((Object)("cdt"),(Object)("download configuration tools?"));
 //BA.debugLineNum = 1391;BA.debugLine="LanString.Put (\"pp\" ,\"Project Page\")";
_lanstring.Put((Object)("pp"),(Object)("Project Page"));
 //BA.debugLineNum = 1392;BA.debugLine="LanString.Put (\"Y\",\"Yes\")";
_lanstring.Put((Object)("Y"),(Object)("Yes"));
 //BA.debugLineNum = 1393;BA.debugLine="LanString.Put (\"N\",\"No\")";
_lanstring.Put((Object)("N"),(Object)("No"));
 //BA.debugLineNum = 1394;BA.debugLine="LanString.Put (\"Cancel\",\"Cancel\")";
_lanstring.Put((Object)("Cancel"),(Object)("Cancel"));
 //BA.debugLineNum = 1395;BA.debugLine="LanString.Put (\"Ok\",\"Ok\")";
_lanstring.Put((Object)("Ok"),(Object)("Ok"));
 //BA.debugLineNum = 1398;BA.debugLine="LanString.Put (\"PC\",\"Push to connect\")";
_lanstring.Put((Object)("PC"),(Object)("Push to connect"));
 //BA.debugLineNum = 1400;BA.debugLine="LanString.Put (\"CNA\",\"CONNECTION UNAVIABLE\")";
_lanstring.Put((Object)("CNA"),(Object)("CONNECTION UNAVIABLE"));
 //BA.debugLineNum = 1401;BA.debugLine="LanString.Put (\"NEE\",\"NETWORK ERROR\")";
_lanstring.Put((Object)("NEE"),(Object)("NETWORK ERROR"));
 //BA.debugLineNum = 1402;BA.debugLine="LanString.Put (\"SDA\",\"SENDING DATA...\")";
_lanstring.Put((Object)("SDA"),(Object)("SENDING DATA..."));
 //BA.debugLineNum = 1403;BA.debugLine="LanString.Put (\"ERIP\",\"Error recepcion IP\")";
_lanstring.Put((Object)("ERIP"),(Object)("Error recepcion IP"));
 //BA.debugLineNum = 1406;BA.debugLine="LanString.Put (\"TC\",\"Trigger Clock\")";
_lanstring.Put((Object)("TC"),(Object)("Trigger Clock"));
 //BA.debugLineNum = 1407;BA.debugLine="LanString.Put (\"STC\",\"Settings Trigger Clock\")";
_lanstring.Put((Object)("STC"),(Object)("Settings Trigger Clock"));
 //BA.debugLineNum = 1408;BA.debugLine="LanString.Put (\"ST\",\"Settings Timetable\")";
_lanstring.Put((Object)("ST"),(Object)("Settings Timetable"));
 //BA.debugLineNum = 1409;BA.debugLine="LanString.Put (\"SWP\",\"Settings Weekly programmi";
_lanstring.Put((Object)("SWP"),(Object)("Settings Weekly programming"));
 //BA.debugLineNum = 1410;BA.debugLine="LanString.Put (\"ET\",\"Enabled Timetable\")";
_lanstring.Put((Object)("ET"),(Object)("Enabled Timetable"));
 //BA.debugLineNum = 1411;BA.debugLine="LanString.Put (\"ECT\",\"Enables circuits timetabl";
_lanstring.Put((Object)("ECT"),(Object)("Enables circuits timetables"));
 //BA.debugLineNum = 1412;BA.debugLine="LanString.Put (\"SSD\",\"Settings Special day \")";
_lanstring.Put((Object)("SSD"),(Object)("Settings Special day "));
 //BA.debugLineNum = 1413;BA.debugLine="LanString.Put (\"SPD\",\"Selection Special day \")";
_lanstring.Put((Object)("SPD"),(Object)("Selection Special day "));
 //BA.debugLineNum = 1414;BA.debugLine="LanString.put(\"SPDI\",\"Special day\")";
_lanstring.Put((Object)("SPDI"),(Object)("Special day"));
 //BA.debugLineNum = 1415;BA.debugLine="LanString.Put (\"SDT\",\"Sync Date Time\")";
_lanstring.Put((Object)("SDT"),(Object)("Sync Date Time"));
 //BA.debugLineNum = 1416;BA.debugLine="LanString.Put (\"SIT\",\"Synchronize Date and Time";
_lanstring.Put((Object)("SIT"),(Object)("Synchronize Date and Time"));
 //BA.debugLineNum = 1417;BA.debugLine="LanString.Put (\"SST\",\"See State Time\")";
_lanstring.Put((Object)("SST"),(Object)("See State Time"));
 //BA.debugLineNum = 1418;BA.debugLine="LanString.Put (\"ITS\",\"Installation Time State\")";
_lanstring.Put((Object)("ITS"),(Object)("Installation Time State"));
 //BA.debugLineNum = 1419;BA.debugLine="LanString.Put (\"SS\",\"Settings Scenes\")";
_lanstring.Put((Object)("SS"),(Object)("Settings Scenes"));
 //BA.debugLineNum = 1420;BA.debugLine="LanString.Put (\"SNV\",\"Settings of names and val";
_lanstring.Put((Object)("SNV"),(Object)("Settings of names and values"));
 //BA.debugLineNum = 1421;BA.debugLine="LanString.Put (\"CBU\",\"Create backup\")";
_lanstring.Put((Object)("CBU"),(Object)("Create backup"));
 //BA.debugLineNum = 1422;BA.debugLine="LanString.Put (\"GCF\",\"Generates a configuration";
_lanstring.Put((Object)("GCF"),(Object)("Generates a configuration file"));
 //BA.debugLineNum = 1423;BA.debugLine="LanString.Put (\"RBU\",\"Recover backup copy\")";
_lanstring.Put((Object)("RBU"),(Object)("Recover backup copy"));
 //BA.debugLineNum = 1424;BA.debugLine="LanString.Put (\"LCF\",\"Loads a configuration fil";
_lanstring.Put((Object)("LCF"),(Object)("Loads a configuration file"));
 //BA.debugLineNum = 1425;BA.debugLine="LanString.Put (\"SCN\",\"Set Circuit Names\")";
_lanstring.Put((Object)("SCN"),(Object)("Set Circuit Names"));
 //BA.debugLineNum = 1426;BA.debugLine="LanString.Put (\"MND\",\"Change Settings\")";
_lanstring.Put((Object)("MND"),(Object)("Change Settings"));
 //BA.debugLineNum = 1427;BA.debugLine="LanString.Put (\"SSU\",\"Setting blinds\")";
_lanstring.Put((Object)("SSU"),(Object)("Setting blinds"));
 //BA.debugLineNum = 1428;BA.debugLine="LanString.Put (\"SUD\",\"Sets up and down time\")";
_lanstring.Put((Object)("SUD"),(Object)("Sets up and down time"));
 //BA.debugLineNum = 1429;BA.debugLine="LanString.Put (\"SCD\",\"Setting Conditioned\")";
_lanstring.Put((Object)("SCD"),(Object)("Setting Conditioned"));
 //BA.debugLineNum = 1430;BA.debugLine="LanString.Put (\"SCO\",\"Setting Commands\")";
_lanstring.Put((Object)("SCO"),(Object)("Setting Commands"));
 //BA.debugLineNum = 1432;BA.debugLine="LanString.Put (\"VCO\",\"Voice Control\")";
_lanstring.Put((Object)("VCO"),(Object)("Voice Control"));
 //BA.debugLineNum = 1433;BA.debugLine="LanString.Put (\"Sensor\",\"Sensor\")";
_lanstring.Put((Object)("Sensor"),(Object)("Sensor"));
 //BA.debugLineNum = 1434;BA.debugLine="LanString.Put (\"SSE\",\"Setting Sensor\")";
_lanstring.Put((Object)("SSE"),(Object)("Setting Sensor"));
 //BA.debugLineNum = 1435;BA.debugLine="LanString.Put (\"SAU\",\"Settings Arduino Unit\")";
_lanstring.Put((Object)("SAU"),(Object)("Settings Arduino Unit"));
 //BA.debugLineNum = 1436;BA.debugLine="LanString.Put (\"NHA\",\"New home automation Switc";
_lanstring.Put((Object)("NHA"),(Object)("New home automation Switchboard"));
 //BA.debugLineNum = 1437;BA.debugLine="LanString.Put (\"COR\",\"Setting Common Orders\")";
_lanstring.Put((Object)("COR"),(Object)("Setting Common Orders"));
 //BA.debugLineNum = 1438;BA.debugLine="LanString.Put (\"MCD\",\"Modifies Common Orders\")";
_lanstring.Put((Object)("MCD"),(Object)("Modifies Common Orders"));
 //BA.debugLineNum = 1439;BA.debugLine="LanString.Put (\"MNU\",\"Manager Menu\")";
_lanstring.Put((Object)("MNU"),(Object)("Manager Menu"));
 //BA.debugLineNum = 1441;BA.debugLine="LanString.Put (\"VIS\",\"Visit the project page\")";
_lanstring.Put((Object)("VIS"),(Object)("Visit the project page"));
 //BA.debugLineNum = 1442;BA.debugLine="LanString.Put (\"dcg\",\"Download code generator\")";
_lanstring.Put((Object)("dcg"),(Object)("Download code generator"));
 //BA.debugLineNum = 1445;BA.debugLine="LanString.Put (\"reg51\",\"Timetable Monday\")";
_lanstring.Put((Object)("reg51"),(Object)("Timetable Monday"));
 //BA.debugLineNum = 1446;BA.debugLine="LanString.Put (\"reg52\",\"Timetable  Tuesday\")";
_lanstring.Put((Object)("reg52"),(Object)("Timetable  Tuesday"));
 //BA.debugLineNum = 1447;BA.debugLine="LanString.Put (\"reg53\",\"Timetable  Wednesday\")";
_lanstring.Put((Object)("reg53"),(Object)("Timetable  Wednesday"));
 //BA.debugLineNum = 1448;BA.debugLine="LanString.Put (\"reg54\",\"Timetable  Thursday\")";
_lanstring.Put((Object)("reg54"),(Object)("Timetable  Thursday"));
 //BA.debugLineNum = 1449;BA.debugLine="LanString.Put (\"reg55\",\"Timetable  Friday\")";
_lanstring.Put((Object)("reg55"),(Object)("Timetable  Friday"));
 //BA.debugLineNum = 1450;BA.debugLine="LanString.Put (\"reg56\",\"Timetable  Saturday\")";
_lanstring.Put((Object)("reg56"),(Object)("Timetable  Saturday"));
 //BA.debugLineNum = 1451;BA.debugLine="LanString.Put (\"reg57\",\"Timetable  Sunday\")";
_lanstring.Put((Object)("reg57"),(Object)("Timetable  Sunday"));
 //BA.debugLineNum = 1452;BA.debugLine="LanString.Put (\"reg58\",\"Monday\")";
_lanstring.Put((Object)("reg58"),(Object)("Monday"));
 //BA.debugLineNum = 1453;BA.debugLine="LanString.Put (\"reg59\",\"Tuesday\")";
_lanstring.Put((Object)("reg59"),(Object)("Tuesday"));
 //BA.debugLineNum = 1454;BA.debugLine="LanString.Put (\"reg60\",\"Wednesday\")";
_lanstring.Put((Object)("reg60"),(Object)("Wednesday"));
 //BA.debugLineNum = 1455;BA.debugLine="LanString.Put (\"reg61\",\"Thursday\")";
_lanstring.Put((Object)("reg61"),(Object)("Thursday"));
 //BA.debugLineNum = 1456;BA.debugLine="LanString.Put (\"reg62\",\"Friday\")";
_lanstring.Put((Object)("reg62"),(Object)("Friday"));
 //BA.debugLineNum = 1457;BA.debugLine="LanString.Put (\"reg63\",\"Saturday\")";
_lanstring.Put((Object)("reg63"),(Object)("Saturday"));
 //BA.debugLineNum = 1458;BA.debugLine="LanString.Put (\"reg64\",\"Sunday\")";
_lanstring.Put((Object)("reg64"),(Object)("Sunday"));
 //BA.debugLineNum = 1459;BA.debugLine="LanString.Put (\"reg65\",\"Confirm delete?\")";
_lanstring.Put((Object)("reg65"),(Object)("Confirm delete?"));
 //BA.debugLineNum = 1460;BA.debugLine="LanString.Put (\"reg66\",\"Confirmation!!\")";
_lanstring.Put((Object)("reg66"),(Object)("Confirmation!!"));
 //BA.debugLineNum = 1461;BA.debugLine="LanString.Put (\"reg68\",\"You can not configure m";
_lanstring.Put((Object)("reg68"),(Object)("You can not configure more than 80 programs per day"));
 //BA.debugLineNum = 1465;BA.debugLine="LanString.Put (\"reg69\",\"Info\")";
_lanstring.Put((Object)("reg69"),(Object)("Info"));
 //BA.debugLineNum = 1466;BA.debugLine="LanString.Put (\"reg70\",\"Turn on\")";
_lanstring.Put((Object)("reg70"),(Object)("Turn on"));
 //BA.debugLineNum = 1467;BA.debugLine="LanString.Put (\"reg71\",\"Turn off\")";
_lanstring.Put((Object)("reg71"),(Object)("Turn off"));
 //BA.debugLineNum = 1469;BA.debugLine="LanString.Put (\"reg72\",\"Turn on 1/3\")";
_lanstring.Put((Object)("reg72"),(Object)("Turn on 1/3"));
 //BA.debugLineNum = 1470;BA.debugLine="LanString.Put (\"reg73\",\"Turn on 2/3\")";
_lanstring.Put((Object)("reg73"),(Object)("Turn on 2/3"));
 //BA.debugLineNum = 1471;BA.debugLine="LanString.Put (\"reg74\",\"Turn on 3/3\")";
_lanstring.Put((Object)("reg74"),(Object)("Turn on 3/3"));
 //BA.debugLineNum = 1472;BA.debugLine="LanString.Put (\"reg75\",\"Select option\")";
_lanstring.Put((Object)("reg75"),(Object)("Select option"));
 //BA.debugLineNum = 1473;BA.debugLine="LanString.Put (\"reg76\",\"Confirm saving?\")";
_lanstring.Put((Object)("reg76"),(Object)("Confirm saving?"));
 //BA.debugLineNum = 1476;BA.debugLine="LanString.Put (\"reg77\",\"SECURITY QUESTION\")";
_lanstring.Put((Object)("reg77"),(Object)("SECURITY QUESTION"));
 //BA.debugLineNum = 1477;BA.debugLine="LanString.Put (\"reg78\",\"Select where to save\")";
_lanstring.Put((Object)("reg78"),(Object)("Select where to save"));
 //BA.debugLineNum = 1479;BA.debugLine="LanString.Put (\"reg79\",\"Set Time\")";
_lanstring.Put((Object)("reg79"),(Object)("Set Time"));
 //BA.debugLineNum = 1480;BA.debugLine="LanString.Put (\"reg80\",\"Download\")";
_lanstring.Put((Object)("reg80"),(Object)("Download"));
 //BA.debugLineNum = 1481;BA.debugLine="LanString.Put (\"reg82\",\"Upload\")";
_lanstring.Put((Object)("reg82"),(Object)("Upload"));
 //BA.debugLineNum = 1482;BA.debugLine="LanString.Put (\"reg83\",\"Accept\")";
_lanstring.Put((Object)("reg83"),(Object)("Accept"));
 //BA.debugLineNum = 1483;BA.debugLine="LanString.Put (\"reg84\",\"Time In minutes\")";
_lanstring.Put((Object)("reg84"),(Object)("Time In minutes"));
 //BA.debugLineNum = 1484;BA.debugLine="LanString.Put (\"reg85\",\"Max 240 Minutes\")";
_lanstring.Put((Object)("reg85"),(Object)("Max 240 Minutes"));
 //BA.debugLineNum = 1485;BA.debugLine="LanString.Put (\"reg86\",\"Out time\")";
_lanstring.Put((Object)("reg86"),(Object)("Out time"));
 //BA.debugLineNum = 1486;BA.debugLine="LanString.Put (\"reg87\",\"Close Door\")";
_lanstring.Put((Object)("reg87"),(Object)("Close Door"));
 //BA.debugLineNum = 1487;BA.debugLine="LanString.Put (\"reg88\",\"Open Door.\")";
_lanstring.Put((Object)("reg88"),(Object)("Open Door."));
 //BA.debugLineNum = 1489;BA.debugLine="LanString.Put (\"reg89\",\"ALL CIRCUITS\")";
_lanstring.Put((Object)("reg89"),(Object)("ALL CIRCUITS"));
 //BA.debugLineNum = 1490;BA.debugLine="LanString.Put (\"reg90\",\"New Value\")";
_lanstring.Put((Object)("reg90"),(Object)("New Value"));
 //BA.debugLineNum = 1491;BA.debugLine="LanString.Put (\"reg91\",\"You can not configure m";
_lanstring.Put((Object)("reg91"),(Object)("You can not configure more than 12 programs per day"));
 //BA.debugLineNum = 1492;BA.debugLine="LanString.Put (\"reg92\",\"Select Value\")";
_lanstring.Put((Object)("reg92"),(Object)("Select Value"));
 //BA.debugLineNum = 1493;BA.debugLine="LanString.Put (\"reg93\",\"Opening blinds\")";
_lanstring.Put((Object)("reg93"),(Object)("Opening blinds"));
 //BA.debugLineNum = 1494;BA.debugLine="LanString.Put (\"reg94\",\"More Color\")";
_lanstring.Put((Object)("reg94"),(Object)("More Color"));
 //BA.debugLineNum = 1495;BA.debugLine="LanString.Put (\"reg95\",\"Random Color\")";
_lanstring.Put((Object)("reg95"),(Object)("Random Color"));
 //BA.debugLineNum = 1496;BA.debugLine="LanString.Put (\"reg96\",\"Choose Color\")";
_lanstring.Put((Object)("reg96"),(Object)("Choose Color"));
 //BA.debugLineNum = 1498;BA.debugLine="LanString.Put (\"reg97\",\"Delete Timetables\")";
_lanstring.Put((Object)("reg97"),(Object)("Delete Timetables"));
 //BA.debugLineNum = 1499;BA.debugLine="LanString.Put (\"reg98\",\"Delete Special days\")";
_lanstring.Put((Object)("reg98"),(Object)("Delete Special days"));
 //BA.debugLineNum = 1500;BA.debugLine="LanString.Put (\"reg99\",\"Circuit configuration\")";
_lanstring.Put((Object)("reg99"),(Object)("Circuit configuration"));
 //BA.debugLineNum = 1501;BA.debugLine="LanString.Put (\"reg100\",\"Set your mail\")";
_lanstring.Put((Object)("reg100"),(Object)("Set your mail"));
 //BA.debugLineNum = 1502;BA.debugLine="LanString.Put (\"reg101\",\"Set your password\")";
_lanstring.Put((Object)("reg101"),(Object)("Set your password"));
 //BA.debugLineNum = 1504;BA.debugLine="LanString.Put (\"reg102\",\"Secure Connection On\")";
_lanstring.Put((Object)("reg102"),(Object)("Secure Connection On"));
 //BA.debugLineNum = 1505;BA.debugLine="LanString.Put (\"reg103\",\"Secure Connection Off\"";
_lanstring.Put((Object)("reg103"),(Object)("Secure Connection Off"));
 //BA.debugLineNum = 1506;BA.debugLine="LanString.Put (\"reg104\",\"Settings\")";
_lanstring.Put((Object)("reg104"),(Object)("Settings"));
 //BA.debugLineNum = 1508;BA.debugLine="LanString.Put (\"reg105\",\"Your password...\")";
_lanstring.Put((Object)("reg105"),(Object)("Your password..."));
 //BA.debugLineNum = 1509;BA.debugLine="LanString.Put (\"reg106\",\"Your mail...\")";
_lanstring.Put((Object)("reg106"),(Object)("Your mail..."));
 //BA.debugLineNum = 1510;BA.debugLine="LanString.Put (\"reg107\",\"On off secure connecti";
_lanstring.Put((Object)("reg107"),(Object)("On off secure connection"));
 //BA.debugLineNum = 1511;BA.debugLine="LanString.Put (\"reg108\",\"Really remove Special";
_lanstring.Put((Object)("reg108"),(Object)("Really remove Special Days?"));
 //BA.debugLineNum = 1512;BA.debugLine="LanString.Put (\"reg109\",\"Deleting...\")";
_lanstring.Put((Object)("reg109"),(Object)("Deleting..."));
 //BA.debugLineNum = 1514;BA.debugLine="LanString.Put (\"reg110\",\"Are you sure erase all";
_lanstring.Put((Object)("reg110"),(Object)("Are you sure erase all data?"));
 //BA.debugLineNum = 1517;BA.debugLine="LanString.Put (\"reg111\",\"Edit Name\")";
_lanstring.Put((Object)("reg111"),(Object)("Edit Name"));
 //BA.debugLineNum = 1520;BA.debugLine="LanString.Put (\"reg112\",\"Edit Description\")";
_lanstring.Put((Object)("reg112"),(Object)("Edit Description"));
 //BA.debugLineNum = 1521;BA.debugLine="LanString.Put (\"reg113\",\"Change Range\")";
_lanstring.Put((Object)("reg113"),(Object)("Change Range"));
 //BA.debugLineNum = 1522;BA.debugLine="LanString.Put (\"reg114\",\"Remove Circuit\")";
_lanstring.Put((Object)("reg114"),(Object)("Remove Circuit"));
 //BA.debugLineNum = 1527;BA.debugLine="LanString.Put (\"reg115\",\"External Ip\")";
_lanstring.Put((Object)("reg115"),(Object)("External Ip"));
 //BA.debugLineNum = 1528;BA.debugLine="LanString.Put (\"reg116\",\"Internal UDP Port\")";
_lanstring.Put((Object)("reg116"),(Object)("Internal UDP Port"));
 //BA.debugLineNum = 1529;BA.debugLine="LanString.Put (\"reg117\",\"External UDP Port\")";
_lanstring.Put((Object)("reg117"),(Object)("External UDP Port"));
 //BA.debugLineNum = 1530;BA.debugLine="LanString.Put (\"reg118\",\"Arduino program Versio";
_lanstring.Put((Object)("reg118"),(Object)("Arduino program Version"));
 //BA.debugLineNum = 1531;BA.debugLine="LanString.Put (\"reg119\",\"Device Name\")";
_lanstring.Put((Object)("reg119"),(Object)("Device Name"));
 //BA.debugLineNum = 1532;BA.debugLine="LanString.Put (\"reg120\",\"Error\")";
_lanstring.Put((Object)("reg120"),(Object)("Error"));
 //BA.debugLineNum = 1533;BA.debugLine="LanString.Put (\"reg121\",\"This name is in use!!\"";
_lanstring.Put((Object)("reg121"),(Object)("This name is in use!!"));
 //BA.debugLineNum = 1534;BA.debugLine="LanString.Put (\"reg122\",\"Port In\")";
_lanstring.Put((Object)("reg122"),(Object)("Port In"));
 //BA.debugLineNum = 1535;BA.debugLine="LanString.Put (\"reg123\",\"Port Out\")";
_lanstring.Put((Object)("reg123"),(Object)("Port Out"));
 //BA.debugLineNum = 1536;BA.debugLine="LanString.Put (\"reg124\",\"Setting Central\")";
_lanstring.Put((Object)("reg124"),(Object)("Setting Central"));
 //BA.debugLineNum = 1537;BA.debugLine="LanString.Put (\"reg125\",\"Delete Device\")";
_lanstring.Put((Object)("reg125"),(Object)("Delete Device"));
 //BA.debugLineNum = 1539;BA.debugLine="LanString.Put (\"reg126\",\"No less than one\")";
_lanstring.Put((Object)("reg126"),(Object)("No less than one"));
 //BA.debugLineNum = 1540;BA.debugLine="LanString.Put (\"reg127\",\"Configure first!!!!\")";
_lanstring.Put((Object)("reg127"),(Object)("Configure first!!!!"));
 //BA.debugLineNum = 1543;BA.debugLine="LanString.Put (\"reg128\",\"SAVE\")";
_lanstring.Put((Object)("reg128"),(Object)("SAVE"));
 //BA.debugLineNum = 1544;BA.debugLine="LanString.Put (\"reg129\",\"Scene selection\")";
_lanstring.Put((Object)("reg129"),(Object)("Scene selection"));
 //BA.debugLineNum = 1545;BA.debugLine="LanString.Put (\"reg130\",\"Save Scene\")";
_lanstring.Put((Object)("reg130"),(Object)("Save Scene"));
 //BA.debugLineNum = 1546;BA.debugLine="LanString.Put (\"reg131\",\"Conditioned\")";
_lanstring.Put((Object)("reg131"),(Object)("Conditioned"));
 //BA.debugLineNum = 1547;BA.debugLine="LanString.Put (\"reg132\",\"Commands\")";
_lanstring.Put((Object)("reg132"),(Object)("Commands"));
 //BA.debugLineNum = 1548;BA.debugLine="LanString.Put (\"reg133\",\"Setpoint\")";
_lanstring.Put((Object)("reg133"),(Object)("Setpoint"));
 //BA.debugLineNum = 1549;BA.debugLine="LanString.Put (\"reg134\",\"Select Central\")";
_lanstring.Put((Object)("reg134"),(Object)("Select Central"));
 //BA.debugLineNum = 1550;BA.debugLine="LanString.Put (\"reg135\",\"Notification\")";
_lanstring.Put((Object)("reg135"),(Object)("Notification"));
 //BA.debugLineNum = 1551;BA.debugLine="LanString.Put (\"reg136\",\"Are you out of your ho";
_lanstring.Put((Object)("reg136"),(Object)("Are you out of your home?"));
 //BA.debugLineNum = 1552;BA.debugLine="LanString.Put (\"reg137\",\"Connection mode\")";
_lanstring.Put((Object)("reg137"),(Object)("Connection mode"));
 //BA.debugLineNum = 1555;BA.debugLine="LanString.Put (\"reg138\",\"Select Scene\")";
_lanstring.Put((Object)("reg138"),(Object)("Select Scene"));
 //BA.debugLineNum = 1556;BA.debugLine="LanString.Put (\"reg139\",\"COMPLETED\")";
_lanstring.Put((Object)("reg139"),(Object)("COMPLETED"));
 //BA.debugLineNum = 1559;BA.debugLine="LanString.Put (\"reg140\",\"Maintains Existing Sta";
_lanstring.Put((Object)("reg140"),(Object)("Maintains Existing State"));
 //BA.debugLineNum = 1560;BA.debugLine="LanString.Put (\"reg141\",\"Current Temperature\")";
_lanstring.Put((Object)("reg141"),(Object)("Current Temperature"));
 //BA.debugLineNum = 1562;BA.debugLine="LanString.Put (\"reg142\",\"Invalid value\")'ACTUAL";
_lanstring.Put((Object)("reg142"),(Object)("Invalid value"));
 //BA.debugLineNum = 1563;BA.debugLine="LanString.Put (\"reg143\",\"You can not configure";
_lanstring.Put((Object)("reg143"),(Object)("You can not configure more than 25 days"));
 //BA.debugLineNum = 1564;BA.debugLine="LanString.Put (\"reg144\",\"New Special day\")";
_lanstring.Put((Object)("reg144"),(Object)("New Special day"));
 //BA.debugLineNum = 1565;BA.debugLine="LanString.Put (\"reg145\",\"You can not enter a da";
_lanstring.Put((Object)("reg145"),(Object)("You can not enter a date prior to the current"));
 //BA.debugLineNum = 1566;BA.debugLine="LanString.Put (\"reg146\",\"That day is already pr";
_lanstring.Put((Object)("reg146"),(Object)("That day is already programmed"));
 //BA.debugLineNum = 1567;BA.debugLine="LanString.Put (\"reg147\",\"Duplicate Date\")";
_lanstring.Put((Object)("reg147"),(Object)("Duplicate Date"));
 //BA.debugLineNum = 1568;BA.debugLine="LanString.Put (\"reg148\",\"Delete special day?\")";
_lanstring.Put((Object)("reg148"),(Object)("Delete special day?"));
 //BA.debugLineNum = 1569;BA.debugLine="LanString.Put (\"reg149\",\"All Off\")";
_lanstring.Put((Object)("reg149"),(Object)("All Off"));
 //BA.debugLineNum = 1570;BA.debugLine="LanString.Put (\"reg150\",\"All On\")";
_lanstring.Put((Object)("reg150"),(Object)("All On"));
 //BA.debugLineNum = 1571;BA.debugLine="LanString.Put (\"reg151\",\"Circuit\")";
_lanstring.Put((Object)("reg151"),(Object)("Circuit"));
 //BA.debugLineNum = 1572;BA.debugLine="LanString.Put (\"reg152\",\"Scene\")";
_lanstring.Put((Object)("reg152"),(Object)("Scene"));
 //BA.debugLineNum = 1573;BA.debugLine="LanString.Put (\"reg153\",\"Time\")";
_lanstring.Put((Object)("reg153"),(Object)("Time"));
 //BA.debugLineNum = 1574;BA.debugLine="LanString.Put (\"reg154\",\"Common\")";
_lanstring.Put((Object)("reg154"),(Object)("Common"));
 //BA.debugLineNum = 1575;BA.debugLine="LanString.Put (\"reg155\",\"Voice\")";
_lanstring.Put((Object)("reg155"),(Object)("Voice"));
 //BA.debugLineNum = 1576;BA.debugLine="LanString.Put (\"reg156\",\"Reconnect\")";
_lanstring.Put((Object)("reg156"),(Object)("Reconnect"));
 //BA.debugLineNum = 1577;BA.debugLine="LanString.Put (\"reg157\",\"State Off\" )";
_lanstring.Put((Object)("reg157"),(Object)("State Off"));
 //BA.debugLineNum = 1578;BA.debugLine="LanString.Put (\"reg158\",\"State On\")";
_lanstring.Put((Object)("reg158"),(Object)("State On"));
 //BA.debugLineNum = 1579;BA.debugLine="LanString.Put (\"reg159\",\"State Disabled\")";
_lanstring.Put((Object)("reg159"),(Object)("State Disabled"));
 //BA.debugLineNum = 1580;BA.debugLine="LanString.Put (\"reg160\",\"Enabled Notification\")";
_lanstring.Put((Object)("reg160"),(Object)("Enabled Notification"));
 //BA.debugLineNum = 1581;BA.debugLine="LanString.Put (\"reg161\",\"Disable Notification\")";
_lanstring.Put((Object)("reg161"),(Object)("Disable Notification"));
 //BA.debugLineNum = 1582;BA.debugLine="LanString.Put (\"reg162\",\"Setting Up Time\")";
_lanstring.Put((Object)("reg162"),(Object)("Setting Up Time"));
 //BA.debugLineNum = 1583;BA.debugLine="LanString.Put (\"reg163\",\"Setting Dow Time\")";
_lanstring.Put((Object)("reg163"),(Object)("Setting Dow Time"));
 //BA.debugLineNum = 1584;BA.debugLine="LanString.Put (\"reg164\",\"Restart Position\")";
_lanstring.Put((Object)("reg164"),(Object)("Restart Position"));
 //BA.debugLineNum = 1585;BA.debugLine="LanString.Put (\"reg165\",\"Confirm Restart Positi";
_lanstring.Put((Object)("reg165"),(Object)("Confirm Restart Position?"));
 //BA.debugLineNum = 1586;BA.debugLine="LanString.Put (\"reg166\",\"Time Max. = 200Sg\")";
_lanstring.Put((Object)("reg166"),(Object)("Time Max. = 200Sg"));
 //BA.debugLineNum = 1587;BA.debugLine="LanString.Put (\"reg167\",\"No sensors configured\"";
_lanstring.Put((Object)("reg167"),(Object)("No sensors configured"));
 //BA.debugLineNum = 1588;BA.debugLine="LanString.Put (\"reg168\",\"Internal Ip\")";
_lanstring.Put((Object)("reg168"),(Object)("Internal Ip"));
 //BA.debugLineNum = 1593;BA.debugLine="LanString.Put (\"reg169\",\"Comando de voz soporta";
_lanstring.Put((Object)("reg169"),(Object)("Comando de voz soportado"));
 //BA.debugLineNum = 1594;BA.debugLine="LanString.Put (\"reg170\",\"comandos de voz no sop";
_lanstring.Put((Object)("reg170"),(Object)("comandos de voz no soportados"));
 //BA.debugLineNum = 1595;BA.debugLine="LanString.Put (\"reg171\",\"Diga comando\")";
_lanstring.Put((Object)("reg171"),(Object)("Diga comando"));
 //BA.debugLineNum = 1596;BA.debugLine="LanString.Put (\"reg172\",\"No se reconoce el coma";
_lanstring.Put((Object)("reg172"),(Object)("No se reconoce el comando"));
 //BA.debugLineNum = 1597;BA.debugLine="LanString.Put (\"reg173\",\"ALUMBRADO\")";
_lanstring.Put((Object)("reg173"),(Object)("ALUMBRADO"));
 //BA.debugLineNum = 1598;BA.debugLine="LanString.Put (\"reg174\",\"TELEVISION\")";
_lanstring.Put((Object)("reg174"),(Object)("TELEVISION"));
 //BA.debugLineNum = 1599;BA.debugLine="LanString.Put (\"reg175\",\"aire acondicionado\")";
_lanstring.Put((Object)("reg175"),(Object)("aire acondicionado"));
 //BA.debugLineNum = 1601;BA.debugLine="LanString.Put (\"reg176\",\"Word to Select scene\")";
_lanstring.Put((Object)("reg176"),(Object)("Word to Select scene"));
 //BA.debugLineNum = 1602;BA.debugLine="LanString.Put (\"reg177\",\"Word to turn off\")'Wor";
_lanstring.Put((Object)("reg177"),(Object)("Word to turn off"));
 //BA.debugLineNum = 1603;BA.debugLine="LanString.Put (\"reg178\",\"Word to turn on\")'\"Wor";
_lanstring.Put((Object)("reg178"),(Object)("Word to turn on"));
 //BA.debugLineNum = 1604;BA.debugLine="LanString.Put (\"reg179\",\"Word to up blind\")'\"Wo";
_lanstring.Put((Object)("reg179"),(Object)("Word to up blind"));
 //BA.debugLineNum = 1605;BA.debugLine="LanString.Put (\"reg180\",\"Word to down blind\")'\"";
_lanstring.Put((Object)("reg180"),(Object)("Word to down blind"));
 //BA.debugLineNum = 1609;BA.debugLine="LanString.Put (\"reg181\",\"Idioma Español\")";
_lanstring.Put((Object)("reg181"),(Object)("Idioma Español"));
 //BA.debugLineNum = 1610;BA.debugLine="LanString.Put (\"reg182\",\"Lenguaje de control po";
_lanstring.Put((Object)("reg182"),(Object)("Lenguaje de control por voz"));
 //BA.debugLineNum = 1611;BA.debugLine="LanString.Put (\"reg183\",\"New word\")";
_lanstring.Put((Object)("reg183"),(Object)("New word"));
 //BA.debugLineNum = 1615;BA.debugLine="LanString.Put (\"reg184\",\"END\")";
_lanstring.Put((Object)("reg184"),(Object)("END"));
 //BA.debugLineNum = 1616;BA.debugLine="LanString.Put (\"reg185\",\"ADD NEW\")";
_lanstring.Put((Object)("reg185"),(Object)("ADD NEW"));
 //BA.debugLineNum = 1620;BA.debugLine="LanString.Put (\"reg186\",\"Functions\")";
_lanstring.Put((Object)("reg186"),(Object)("Functions"));
 //BA.debugLineNum = 1621;BA.debugLine="LanString.Put (\"reg187\",\"Menu\")";
_lanstring.Put((Object)("reg187"),(Object)("Menu"));
 //BA.debugLineNum = 1622;BA.debugLine="LanString.Put (\"graphics\",\"Graphics\")";
_lanstring.Put((Object)("graphics"),(Object)("Graphics"));
 //BA.debugLineNum = 1623;BA.debugLine="LanString.Put (\"Icon\",\"Select Icon\")";
_lanstring.Put((Object)("Icon"),(Object)("Select Icon"));
 //BA.debugLineNum = 1624;BA.debugLine="LanString.Put (\"dev\",\"Devices\")";
_lanstring.Put((Object)("dev"),(Object)("Devices"));
 //BA.debugLineNum = 1625;BA.debugLine="LanString.Put (\"passr\",\"password required\")";
_lanstring.Put((Object)("passr"),(Object)("password required"));
 //BA.debugLineNum = 1626;BA.debugLine="LanString.Put (\"ICW\",\"Internal connection Wifi\"";
_lanstring.Put((Object)("ICW"),(Object)("Internal connection Wifi"));
 //BA.debugLineNum = 1627;BA.debugLine="LanString.Put (\"ECW\",\"Extermal connection Wifi\"";
_lanstring.Put((Object)("ECW"),(Object)("Extermal connection Wifi"));
 //BA.debugLineNum = 1628;BA.debugLine="LanString.Put (\"CMW\",\"Connection mode  for this";
_lanstring.Put((Object)("CMW"),(Object)("Connection mode  for this wifi network"));
 //BA.debugLineNum = 1629;BA.debugLine="LanString.Put (\"RSC\",\"Reload system configurati";
_lanstring.Put((Object)("RSC"),(Object)("Reload system configuration"));
 //BA.debugLineNum = 1632;BA.debugLine="LanString.Put (\"Status\",\"Status\")";
_lanstring.Put((Object)("Status"),(Object)("Status"));
 //BA.debugLineNum = 1633;BA.debugLine="LanString.Put (\"SetSta\",\"Configure states scree";
_lanstring.Put((Object)("SetSta"),(Object)("Configure states screen"));
 //BA.debugLineNum = 1634;BA.debugLine="LanString.Put (\"LsTT\",\"Language Settings\")";
_lanstring.Put((Object)("LsTT"),(Object)("Language Settings"));
 //BA.debugLineNum = 1635;BA.debugLine="LanString.Put (\"SltL\",\"Select language\")";
_lanstring.Put((Object)("SltL"),(Object)("Select language"));
 //BA.debugLineNum = 1636;BA.debugLine="LanString.Put (\"IniD\",\"Start date\")";
_lanstring.Put((Object)("IniD"),(Object)("Start date"));
 //BA.debugLineNum = 1637;BA.debugLine="LanString.Put (\"FinD\",\"Finishing date\")";
_lanstring.Put((Object)("FinD"),(Object)("Finishing date"));
 //BA.debugLineNum = 1638;BA.debugLine="LanString.Put (\"Function\",\"Function\")";
_lanstring.Put((Object)("Function"),(Object)("Function"));
 //BA.debugLineNum = 1639;BA.debugLine="LanString.Put (\"close\",\"Close\")";
_lanstring.Put((Object)("close"),(Object)("Close"));
 //BA.debugLineNum = 1640;BA.debugLine="LanString.Put (\"Chart\",\"Chart\")";
_lanstring.Put((Object)("Chart"),(Object)("Chart"));
 //BA.debugLineNum = 1641;BA.debugLine="LanString.Put (\"Backup\",\"Backup\")";
_lanstring.Put((Object)("Backup"),(Object)("Backup"));
 //BA.debugLineNum = 1642;BA.debugLine="LanString.Put (\"sTs\",\"blinds\")";
_lanstring.Put((Object)("sTs"),(Object)("blinds"));
 //BA.debugLineNum = 1643;BA.debugLine="LanString.Put (\"CsS\",\"Configure Status Screen\")";
_lanstring.Put((Object)("CsS"),(Object)("Configure Status Screen"));
 //BA.debugLineNum = 1644;BA.debugLine="LanString.Put(\"CuF\",\"Configure User Functons\")";
_lanstring.Put((Object)("CuF"),(Object)("Configure User Functons"));
 //BA.debugLineNum = 1646;BA.debugLine="LanString.Put(\"Mva\",\"Maximum value\")";
_lanstring.Put((Object)("Mva"),(Object)("Maximum value"));
 //BA.debugLineNum = 1647;BA.debugLine="LanString.Put(\"Miv\",\"Minimum value\")";
_lanstring.Put((Object)("Miv"),(Object)("Minimum value"));
 //BA.debugLineNum = 1648;BA.debugLine="LanString.Put(\"DiT\",\"Invisible element\")";
_lanstring.Put((Object)("DiT"),(Object)("Invisible element"));
 //BA.debugLineNum = 1649;BA.debugLine="LanString.Put (\"ipc\",\"IP cameras\")";
_lanstring.Put((Object)("ipc"),(Object)("IP cameras"));
 //BA.debugLineNum = 1650;BA.debugLine="LanString.Put (\"User\",\"User\")";
_lanstring.Put((Object)("User"),(Object)("User"));
 //BA.debugLineNum = 1656;BA.debugLine="File.WriteMap (File.DirInternal   ,\"LanString\"";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"LanString",_lanstring);
 //BA.debugLineNum = 1657;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _delete(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 635;BA.debugLine="Sub Delete()As Bitmap";
 //BA.debugLineNum = 636;BA.debugLine="If ImgDelete.IsInitialized = False Then ImgDelete";
if (_imgdelete.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgdelete.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"delete.png");};
 //BA.debugLineNum = 637;BA.debugLine="Return 	ImgDelete";
if (true) return _imgdelete;
 //BA.debugLineNum = 638;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _enchufeal(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 336;BA.debugLine="Sub EnchufeAl()As Bitmap";
 //BA.debugLineNum = 337;BA.debugLine="If ImgEnchufeAl.IsInitialized = False Then ImgEnc";
if (_imgenchufeal.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgenchufeal.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"enchufeAl.png");};
 //BA.debugLineNum = 338;BA.debugLine="Return 	ImgEnchufeAl";
if (true) return _imgenchufeal;
 //BA.debugLineNum = 339;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _enchufedes(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 423;BA.debugLine="Sub Enchufedes()As Bitmap";
 //BA.debugLineNum = 424;BA.debugLine="If ImgEnchufedes.IsInitialized = False Then ImgEn";
if (_imgenchufedes.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgenchufedes.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"EnchufeDes.png");};
 //BA.debugLineNum = 425;BA.debugLine="Return 	ImgEnchufedes";
if (true) return _imgenchufedes;
 //BA.debugLineNum = 426;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _enchufeoff(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 427;BA.debugLine="Sub EnchufeOff()As Bitmap";
 //BA.debugLineNum = 428;BA.debugLine="If ImgEnchufeOff.IsInitialized = False Then ImgEn";
if (_imgenchufeoff.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgenchufeoff.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"EnchufeOff.png");};
 //BA.debugLineNum = 429;BA.debugLine="Return 	ImgEnchufeOff";
if (true) return _imgenchufeoff;
 //BA.debugLineNum = 430;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _enchufeon(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 431;BA.debugLine="Sub EnchufeOn()As Bitmap";
 //BA.debugLineNum = 432;BA.debugLine="If ImgEnchufeOn.IsInitialized = False Then ImgEnc";
if (_imgenchufeon.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgenchufeon.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"EnchufeOn.png");};
 //BA.debugLineNum = 433;BA.debugLine="Return 	ImgEnchufeOn";
if (true) return _imgenchufeon;
 //BA.debugLineNum = 434;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _fechahora(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 532;BA.debugLine="Sub fechahora()As Bitmap";
 //BA.debugLineNum = 533;BA.debugLine="If Imgfechahora.IsInitialized = False Then Imgfec";
if (_imgfechahora.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgfechahora.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"fechahora.png");};
 //BA.debugLineNum = 534;BA.debugLine="Return 	Imgfechahora";
if (true) return _imgfechahora;
 //BA.debugLineNum = 535;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _funciones(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 397;BA.debugLine="Sub Funciones()As Bitmap";
 //BA.debugLineNum = 398;BA.debugLine="If imgFunciones.IsInitialized = False Then imgFun";
if (_imgfunciones.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgfunciones.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"funciones.png");};
 //BA.debugLineNum = 399;BA.debugLine="Return imgFunciones";
if (true) return _imgfunciones;
 //BA.debugLineNum = 400;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _garden(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 757;BA.debugLine="Sub Garden()As Bitmap";
 //BA.debugLineNum = 758;BA.debugLine="If ImgGarden.IsInitialized = False Then ImgGarden";
if (_imggarden.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imggarden.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Garden.png");};
 //BA.debugLineNum = 759;BA.debugLine="Return 	ImgGarden";
if (true) return _imggarden;
 //BA.debugLineNum = 760;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _gardenlt(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 715;BA.debugLine="Sub Gardenlt()As Bitmap";
 //BA.debugLineNum = 716;BA.debugLine="If ImgGardenlt.IsInitialized = False Then ImgGard";
if (_imggardenlt.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imggardenlt.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Gardenlt.png");};
 //BA.debugLineNum = 717;BA.debugLine="Return 	ImgGardenlt";
if (true) return _imggardenlt;
 //BA.debugLineNum = 718;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _getcentralimg(anywheresoftware.b4a.BA _ba,int _iconnumber,boolean _big) throws Exception{
 //BA.debugLineNum = 640;BA.debugLine="Sub GetCentralImg(IconNumber As Int, Big As Boolea";
 //BA.debugLineNum = 641;BA.debugLine="If IconNumber>100 Then";
if (_iconnumber>100) { 
 //BA.debugLineNum = 642;BA.debugLine="Return Home";
if (true) return _home(_ba);
 }else {
 //BA.debugLineNum = 644;BA.debugLine="Select Case IconNumber";
switch (_iconnumber) {
case 0: {
 //BA.debugLineNum = 646;BA.debugLine="If Big Then";
if (_big) { 
 //BA.debugLineNum = 647;BA.debugLine="Return Home";
if (true) return _home(_ba);
 }else {
 //BA.debugLineNum = 649;BA.debugLine="Return MnuHome";
if (true) return _mnuhome(_ba);
 };
 break; }
case 1: {
 //BA.debugLineNum = 653;BA.debugLine="If Big Then";
if (_big) { 
 //BA.debugLineNum = 654;BA.debugLine="Return Cocina";
if (true) return _cocina(_ba);
 }else {
 //BA.debugLineNum = 656;BA.debugLine="Return Cocinalt";
if (true) return _cocinalt(_ba);
 };
 break; }
case 2: {
 //BA.debugLineNum = 659;BA.debugLine="If Big Then";
if (_big) { 
 //BA.debugLineNum = 660;BA.debugLine="Return Cochera";
if (true) return _cochera(_ba);
 }else {
 //BA.debugLineNum = 662;BA.debugLine="Return Cocheralt";
if (true) return _cocheralt(_ba);
 };
 break; }
case 3: {
 //BA.debugLineNum = 667;BA.debugLine="If Big Then";
if (_big) { 
 //BA.debugLineNum = 668;BA.debugLine="Return Garden";
if (true) return _garden(_ba);
 }else {
 //BA.debugLineNum = 670;BA.debugLine="Return Gardenlt";
if (true) return _gardenlt(_ba);
 };
 break; }
case 4: {
 //BA.debugLineNum = 674;BA.debugLine="If Big Then";
if (_big) { 
 //BA.debugLineNum = 675;BA.debugLine="Return Aseo";
if (true) return _aseo(_ba);
 }else {
 //BA.debugLineNum = 677;BA.debugLine="Return Aseolt";
if (true) return _aseolt(_ba);
 };
 break; }
case 5: {
 //BA.debugLineNum = 681;BA.debugLine="If Big Then";
if (_big) { 
 //BA.debugLineNum = 682;BA.debugLine="Return Cama";
if (true) return _cama(_ba);
 }else {
 //BA.debugLineNum = 684;BA.debugLine="Return Camalt";
if (true) return _camalt(_ba);
 };
 break; }
case 6: {
 //BA.debugLineNum = 688;BA.debugLine="If Big Then";
if (_big) { 
 //BA.debugLineNum = 689;BA.debugLine="Return Salon";
if (true) return _salon(_ba);
 }else {
 //BA.debugLineNum = 691;BA.debugLine="Return Salonlt";
if (true) return _salonlt(_ba);
 };
 break; }
}
;
 //BA.debugLineNum = 696;BA.debugLine="If Big Then";
if (_big) { 
 //BA.debugLineNum = 697;BA.debugLine="Return Home";
if (true) return _home(_ba);
 }else {
 //BA.debugLineNum = 699;BA.debugLine="Return MnuHome";
if (true) return _mnuhome(_ba);
 };
 };
 //BA.debugLineNum = 703;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _getimgdevice(anywheresoftware.b4a.BA _ba,String _name,boolean _big) throws Exception{
 //BA.debugLineNum = 176;BA.debugLine="Sub GetImgDevice(Name As String, Big As Boolean  )";
 //BA.debugLineNum = 177;BA.debugLine="If CentralesImg.IsInitialized =False Then 	Centra";
if (_centralesimg.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_centralesimg.Initialize();};
 //BA.debugLineNum = 178;BA.debugLine="If CentralesImg.ContainsKey (Name) Then";
if (_centralesimg.ContainsKey((Object)(_name))) { 
 //BA.debugLineNum = 180;BA.debugLine="Return GetCentralImg(CentralesImg.Get (Name),  B";
if (true) return _getcentralimg(_ba,(int)(BA.ObjectToNumber(_centralesimg.Get((Object)(_name)))),_big);
 }else {
 //BA.debugLineNum = 182;BA.debugLine="Return GetCentralImg(0, Big )";
if (true) return _getcentralimg(_ba,(int) (0),_big);
 };
 //BA.debugLineNum = 186;BA.debugLine="End Sub";
return null;
}
public static String  _getlanstring(anywheresoftware.b4a.BA _ba,String _val) throws Exception{
 //BA.debugLineNum = 1658;BA.debugLine="Sub GetLanString(Val As String ) As String";
 //BA.debugLineNum = 1659;BA.debugLine="If LanString.IsInitialized = False Then";
if (_lanstring.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 1660;BA.debugLine="If File.Exists ( File.DirInternal ,\"LanString\")";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"LanString")) { 
 //BA.debugLineNum = 1661;BA.debugLine="LanString = File.ReadMap (File.DirInternal,\"Lan";
_lanstring = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"LanString");
 }else {
 //BA.debugLineNum = 1663;BA.debugLine="CrearMapaIdioma";
_crearmapaidioma(_ba);
 };
 };
 //BA.debugLineNum = 1668;BA.debugLine="Return LanString.GetDefault(Val,\"?\")";
if (true) return BA.ObjectToString(_lanstring.GetDefault((Object)(_val),(Object)("?")));
 //BA.debugLineNum = 1670;BA.debugLine="End Sub";
return "";
}
public static String  _getlanstringdefault(anywheresoftware.b4a.BA _ba,String _val,String _default) throws Exception{
 //BA.debugLineNum = 1671;BA.debugLine="Sub GetLanStringDefault(Val As String, default As";
 //BA.debugLineNum = 1672;BA.debugLine="If LanString.IsInitialized = False Then";
if (_lanstring.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 1673;BA.debugLine="If File.Exists ( File.DirInternal ,\"LanString\")";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"LanString")) { 
 //BA.debugLineNum = 1674;BA.debugLine="LanString = File.ReadMap (File.DirInternal,\"Lan";
_lanstring = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"LanString");
 }else {
 //BA.debugLineNum = 1676;BA.debugLine="CrearMapaIdioma";
_crearmapaidioma(_ba);
 };
 };
 //BA.debugLineNum = 1681;BA.debugLine="Return LanString.GetDefault(Val,default)";
if (true) return BA.ObjectToString(_lanstring.GetDefault((Object)(_val),(Object)(_default)));
 //BA.debugLineNum = 1683;BA.debugLine="End Sub";
return "";
}
public static String  _guardacentral(anywheresoftware.b4a.BA _ba,arduino.automatizacion.excontrol.PRO.valorescomunes._arduino _cent) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
 //BA.debugLineNum = 846;BA.debugLine="Sub GuardaCentral(Cent As Arduino)";
 //BA.debugLineNum = 847;BA.debugLine="Dim lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 849;BA.debugLine="lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 850;BA.debugLine="lst.Add (Cent.IpIn )";
_lst.Add((Object)(_cent.IpIn));
 //BA.debugLineNum = 851;BA.debugLine="lst.Add (Cent.IpOut )";
_lst.Add((Object)(_cent.IpOut));
 //BA.debugLineNum = 852;BA.debugLine="lst.Add (Cent.PuertoIn )";
_lst.Add((Object)(_cent.PuertoIn));
 //BA.debugLineNum = 853;BA.debugLine="lst.Add (Cent.PuertoOut )";
_lst.Add((Object)(_cent.PuertoOut));
 //BA.debugLineNum = 854;BA.debugLine="lst.Add (Cent.Descripcion)";
_lst.Add((Object)(_cent.Descripcion));
 //BA.debugLineNum = 855;BA.debugLine="lst.Add (Cent.Icon)";
_lst.Add((Object)(_cent.Icon));
 //BA.debugLineNum = 856;BA.debugLine="lst.Add (Cent.Mail)";
_lst.Add((Object)(_cent.mail));
 //BA.debugLineNum = 857;BA.debugLine="lst.Add (Cent.Password )";
_lst.Add((Object)(_cent.Password));
 //BA.debugLineNum = 858;BA.debugLine="lst.Add (Cent.ConexionSegura)";
_lst.Add((Object)(_cent.ConexionSegura));
 //BA.debugLineNum = 859;BA.debugLine="File.WriteList(File.DirInternal ,\"Conect\" & Cent.";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conect"+_cent.Nombre,_lst);
 //BA.debugLineNum = 861;BA.debugLine="End Sub";
return "";
}
public static String  _guardacomandos(anywheresoftware.b4a.BA _ba) throws Exception{
int _c = 0;
anywheresoftware.b4a.objects.collections.List _lst = null;
arduino.automatizacion.excontrol.PRO.valorescomunes._scene _cmd = null;
 //BA.debugLineNum = 988;BA.debugLine="Sub GuardaComandos";
 //BA.debugLineNum = 989;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 990;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 991;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 992;BA.debugLine="For C =0 To Comandos.Size -1";
{
final int step4 = 1;
final int limit4 = (int) (_comandos.getSize()-1);
_c = (int) (0) ;
for (;(step4 > 0 && _c <= limit4) || (step4 < 0 && _c >= limit4) ;_c = ((int)(0 + _c + step4))  ) {
 //BA.debugLineNum = 993;BA.debugLine="Dim cmd As Scene";
_cmd = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
 //BA.debugLineNum = 994;BA.debugLine="cmd = Comandos.Get (C)";
_cmd = (arduino.automatizacion.excontrol.PRO.valorescomunes._scene)(_comandos.Get(_c));
 //BA.debugLineNum = 995;BA.debugLine="Lst.Add (cmd.Nombre )";
_lst.Add((Object)(_cmd.Nombre));
 //BA.debugLineNum = 996;BA.debugLine="Lst.Add (cmd.Descripcion )";
_lst.Add((Object)(_cmd.Descripcion));
 }
};
 //BA.debugLineNum = 999;BA.debugLine="File.WriteList(File.DirInternal ,\"Comandos\"  & C";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Comandos"+_central.Nombre,_lst);
 //BA.debugLineNum = 1000;BA.debugLine="CargaDescripcionesComandos";
_cargadescripcionescomandos(_ba);
 //BA.debugLineNum = 1001;BA.debugLine="End Sub";
return "";
}
public static String  _guardacomandoscomunes(anywheresoftware.b4a.BA _ba) throws Exception{
int _c = 0;
anywheresoftware.b4a.objects.collections.List _lst = null;
arduino.automatizacion.excontrol.PRO.valorescomunes._scene _cmd = null;
 //BA.debugLineNum = 903;BA.debugLine="Sub GuardaComandosComunes";
 //BA.debugLineNum = 904;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 905;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 906;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 907;BA.debugLine="For C =0 To ComandosComunes.Size -1";
{
final int step4 = 1;
final int limit4 = (int) (_comandoscomunes.getSize()-1);
_c = (int) (0) ;
for (;(step4 > 0 && _c <= limit4) || (step4 < 0 && _c >= limit4) ;_c = ((int)(0 + _c + step4))  ) {
 //BA.debugLineNum = 908;BA.debugLine="Dim cmd As Scene";
_cmd = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
 //BA.debugLineNum = 909;BA.debugLine="cmd = ComandosComunes.Get (C)";
_cmd = (arduino.automatizacion.excontrol.PRO.valorescomunes._scene)(_comandoscomunes.Get(_c));
 //BA.debugLineNum = 910;BA.debugLine="Lst.Add (cmd.Nombre )";
_lst.Add((Object)(_cmd.Nombre));
 //BA.debugLineNum = 911;BA.debugLine="Lst.Add (cmd.Descripcion )";
_lst.Add((Object)(_cmd.Descripcion));
 }
};
 //BA.debugLineNum = 914;BA.debugLine="File.WriteList(File.DirInternal ,\"ComandosComune";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ComandosComunes",_lst);
 //BA.debugLineNum = 915;BA.debugLine="CargaDescripcionesComandosComunes";
_cargadescripcionescomandoscomunes(_ba);
 //BA.debugLineNum = 916;BA.debugLine="End Sub";
return "";
}
public static String  _guardacondicionados(anywheresoftware.b4a.BA _ba) throws Exception{
int _c = 0;
anywheresoftware.b4a.objects.collections.List _lst = null;
 //BA.debugLineNum = 1050;BA.debugLine="Sub GuardaCondicionados";
 //BA.debugLineNum = 1051;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 1052;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 1053;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 1054;BA.debugLine="For C =0 To 9";
{
final int step4 = 1;
final int limit4 = (int) (9);
_c = (int) (0) ;
for (;(step4 > 0 && _c <= limit4) || (step4 < 0 && _c >= limit4) ;_c = ((int)(0 + _c + step4))  ) {
 //BA.debugLineNum = 1056;BA.debugLine="Lst.Add (Condicionados(C).Nombre )";
_lst.Add((Object)(_condicionados[_c].Nombre));
 //BA.debugLineNum = 1057;BA.debugLine="Lst.Add (Condicionados(C).Descripcion )";
_lst.Add((Object)(_condicionados[_c].Descripcion));
 }
};
 //BA.debugLineNum = 1060;BA.debugLine="File.WriteList(File.DirInternal ,\"Condicionados\"";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Condicionados"+_central.Nombre,_lst);
 //BA.debugLineNum = 1062;BA.debugLine="End Sub";
return "";
}
public static String  _guardaescenas(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _c = 0;
 //BA.debugLineNum = 1178;BA.debugLine="Sub GuardaEscenas";
 //BA.debugLineNum = 1179;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 1180;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 1181;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 1182;BA.debugLine="For C =0 To 9";
{
final int step4 = 1;
final int limit4 = (int) (9);
_c = (int) (0) ;
for (;(step4 > 0 && _c <= limit4) || (step4 < 0 && _c >= limit4) ;_c = ((int)(0 + _c + step4))  ) {
 //BA.debugLineNum = 1183;BA.debugLine="Lst.Add (Scenes(C).Nombre)";
_lst.Add((Object)(_scenes[_c].Nombre));
 //BA.debugLineNum = 1184;BA.debugLine="Lst.Add (Scenes(C).Descripcion )";
_lst.Add((Object)(_scenes[_c].Descripcion));
 }
};
 //BA.debugLineNum = 1186;BA.debugLine="File.WriteList(File.DirInternal ,\"ConfigEsc\" & Ce";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigEsc"+_central.Nombre,_lst);
 //BA.debugLineNum = 1187;BA.debugLine="End Sub";
return "";
}
public static String  _guardafunciones(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _c = 0;
 //BA.debugLineNum = 1192;BA.debugLine="Sub GuardaFunciones";
 //BA.debugLineNum = 1193;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 1194;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 1195;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 1196;BA.debugLine="For C =0 To 9";
{
final int step4 = 1;
final int limit4 = (int) (9);
_c = (int) (0) ;
for (;(step4 > 0 && _c <= limit4) || (step4 < 0 && _c >= limit4) ;_c = ((int)(0 + _c + step4))  ) {
 //BA.debugLineNum = 1197;BA.debugLine="Lst.Add (Functions(C).Nombre)";
_lst.Add((Object)(_functions[_c].Nombre));
 //BA.debugLineNum = 1198;BA.debugLine="Lst.Add (Functions(C).Descripcion )";
_lst.Add((Object)(_functions[_c].Descripcion));
 }
};
 //BA.debugLineNum = 1200;BA.debugLine="File.WriteList(File.DirInternal ,\"ConfigFunc\" & C";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigFunc"+_central.Nombre,_lst);
 //BA.debugLineNum = 1201;BA.debugLine="End Sub";
return "";
}
public static String  _guardarcentrales(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 1188;BA.debugLine="Sub GuardarCentrales";
 //BA.debugLineNum = 1190;BA.debugLine="File.WriteList(File.DirInternal ,\"Centrales\" ,Ce";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Centrales",_centrales);
 //BA.debugLineNum = 1191;BA.debugLine="End Sub";
return "";
}
public static String  _guardarconfigcircuitos(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _i = 0;
 //BA.debugLineNum = 1165;BA.debugLine="Sub GuardarConfigCircuitos";
 //BA.debugLineNum = 1167;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 1168;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 1169;BA.debugLine="Dim I As Int";
_i = 0;
 //BA.debugLineNum = 1170;BA.debugLine="For I =0 To 29";
{
final int step4 = 1;
final int limit4 = (int) (29);
_i = (int) (0) ;
for (;(step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4) ;_i = ((int)(0 + _i + step4))  ) {
 //BA.debugLineNum = 1171;BA.debugLine="Lst.Add (Circuitos(I).Nombre )";
_lst.Add((Object)(_circuitos[_i].Nombre));
 //BA.debugLineNum = 1172;BA.debugLine="Lst.Add (Circuitos(I).Descripcion  )";
_lst.Add((Object)(_circuitos[_i].Descripcion));
 //BA.debugLineNum = 1173;BA.debugLine="Lst.Add (Circuitos(I).rango )";
_lst.Add((Object)(_circuitos[_i].Rango));
 //BA.debugLineNum = 1174;BA.debugLine="Lst.Add (Circuitos(I).DeviceNumber  )";
_lst.Add((Object)(_circuitos[_i].DeviceNumber));
 }
};
 //BA.debugLineNum = 1176;BA.debugLine="File.WriteList(File.DirInternal ,\"ConfigCir\" & C";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigCir"+_central.Nombre,_lst);
 //BA.debugLineNum = 1177;BA.debugLine="End Sub";
return "";
}
public static String  _guardarconfigsensores(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _i = 0;
 //BA.debugLineNum = 1103;BA.debugLine="Sub GuardarConfigSensores";
 //BA.debugLineNum = 1105;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 1106;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 1107;BA.debugLine="Dim I As Int";
_i = 0;
 //BA.debugLineNum = 1108;BA.debugLine="For I =0 To 14";
{
final int step4 = 1;
final int limit4 = (int) (14);
_i = (int) (0) ;
for (;(step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4) ;_i = ((int)(0 + _i + step4))  ) {
 //BA.debugLineNum = 1109;BA.debugLine="Lst.Add (Sensores(I).Nombre )";
_lst.Add((Object)(_sensores[_i].Nombre));
 //BA.debugLineNum = 1110;BA.debugLine="Lst.Add (Sensores(I).Descripcion  )";
_lst.Add((Object)(_sensores[_i].Descripcion));
 //BA.debugLineNum = 1111;BA.debugLine="Lst.Add (Sensores(I).rango )";
_lst.Add((Object)(_sensores[_i].Rango));
 }
};
 //BA.debugLineNum = 1113;BA.debugLine="File.WriteList(File.DirInternal ,\"ConfigSenso\" &";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigSenso"+_central.Nombre,_lst);
 //BA.debugLineNum = 1114;BA.debugLine="End Sub";
return "";
}
public static String  _guardasetpoint(anywheresoftware.b4a.BA _ba) throws Exception{
int _c = 0;
anywheresoftware.b4a.objects.collections.List _lname = null;
anywheresoftware.b4a.objects.collections.List _lmax = null;
anywheresoftware.b4a.objects.collections.List _lmin = null;
anywheresoftware.b4a.objects.collections.List _licon = null;
arduino.automatizacion.excontrol.PRO.valorescomunes._sp _s = null;
 //BA.debugLineNum = 70;BA.debugLine="Sub GuardaSetpoint";
 //BA.debugLineNum = 71;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 72;BA.debugLine="Dim lname  As List";
_lname = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 73;BA.debugLine="Dim lmax As List";
_lmax = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 74;BA.debugLine="Dim lmin As List";
_lmin = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 75;BA.debugLine="Dim licon As List";
_licon = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 77;BA.debugLine="lname.Initialize";
_lname.Initialize();
 //BA.debugLineNum = 78;BA.debugLine="lmax.Initialize";
_lmax.Initialize();
 //BA.debugLineNum = 79;BA.debugLine="lmin.Initialize";
_lmin.Initialize();
 //BA.debugLineNum = 80;BA.debugLine="licon.Initialize";
_licon.Initialize();
 //BA.debugLineNum = 82;BA.debugLine="Dim s As Sp";
_s = new arduino.automatizacion.excontrol.PRO.valorescomunes._sp();
 //BA.debugLineNum = 84;BA.debugLine="For C =0 To SetPoint.Size -1";
{
final int step11 = 1;
final int limit11 = (int) (_setpoint.getSize()-1);
_c = (int) (0) ;
for (;(step11 > 0 && _c <= limit11) || (step11 < 0 && _c >= limit11) ;_c = ((int)(0 + _c + step11))  ) {
 //BA.debugLineNum = 85;BA.debugLine="s=SetPoint.Get (C)";
_s = (arduino.automatizacion.excontrol.PRO.valorescomunes._sp)(_setpoint.Get(_c));
 //BA.debugLineNum = 86;BA.debugLine="lname.Add (s.Nombre )";
_lname.Add((Object)(_s.Nombre));
 //BA.debugLineNum = 87;BA.debugLine="lmax.Add (s.Maximo )";
_lmax.Add((Object)(_s.Maximo));
 //BA.debugLineNum = 88;BA.debugLine="lmin.Add (s.Minimo )";
_lmin.Add((Object)(_s.Minimo));
 //BA.debugLineNum = 89;BA.debugLine="licon.Add (s.Icon )";
_licon.Add((Object)(_s.Icon));
 }
};
 //BA.debugLineNum = 91;BA.debugLine="File.WriteList ( File.DirInternal ,\"Consignas\" &";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Consignas"+_central.Nombre,_lname);
 //BA.debugLineNum = 92;BA.debugLine="File.WriteList ( File.DirInternal ,\"ConsignasMax\"";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasMax"+_central.Nombre,_lmax);
 //BA.debugLineNum = 93;BA.debugLine="File.WriteList ( File.DirInternal ,\"ConsignasMin\"";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasMin"+_central.Nombre,_lmin);
 //BA.debugLineNum = 94;BA.debugLine="File.WriteList ( File.DirInternal ,\"ConsignasIcon";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConsignasIcon"+_central.Nombre,_licon);
 //BA.debugLineNum = 95;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _home(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 769;BA.debugLine="Sub Home()As Bitmap";
 //BA.debugLineNum = 770;BA.debugLine="If ImgHome.IsInitialized = False Then ImgHome.Ini";
if (_imghome.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imghome.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Home.png");};
 //BA.debugLineNum = 771;BA.debugLine="Return 	ImgHome";
if (true) return _imghome;
 //BA.debugLineNum = 772;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _icon(anywheresoftware.b4a.BA _ba,int _value) throws Exception{
 //BA.debugLineNum = 1726;BA.debugLine="Sub Icon(Value As Int) As Bitmap";
 //BA.debugLineNum = 1727;BA.debugLine="If Value = 1 Then";
if (_value==1) { 
 //BA.debugLineNum = 1728;BA.debugLine="Return SensorTemp";
if (true) return _sensortemp(_ba);
 }else if(_value==2) { 
 //BA.debugLineNum = 1730;BA.debugLine="Return SensorHumedad";
if (true) return _sensorhumedad(_ba);
 }else if(_value==3) { 
 //BA.debugLineNum = 1732;BA.debugLine="Return SensorLux";
if (true) return _sensorlux(_ba);
 }else if(_value==100) { 
 //BA.debugLineNum = 1734;BA.debugLine="Return SensorDeposito";
if (true) return _sensordeposito(_ba);
 };
 //BA.debugLineNum = 1736;BA.debugLine="Return SensorGenerico";
if (true) return _sensorgenerico(_ba);
 //BA.debugLineNum = 1737;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _icono(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 536;BA.debugLine="Sub Icono()As Bitmap";
 //BA.debugLineNum = 537;BA.debugLine="If ImgIcono.IsInitialized = False Then ImgIcono.I";
if (_imgicono.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgicono.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Icono.png");};
 //BA.debugLineNum = 538;BA.debugLine="Return 	ImgIcono";
if (true) return _imgicono;
 //BA.debugLineNum = 539;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _iconocircuito(anywheresoftware.b4a.BA _ba,int _number,int _value) throws Exception{
 //BA.debugLineNum = 1763;BA.debugLine="Sub IconoCircuito(Number As Int, Value As Int) As";
 //BA.debugLineNum = 1764;BA.debugLine="If Circuitos(Number).Rango<7 Then";
if (_circuitos[_number].Rango<7) { 
 //BA.debugLineNum = 1765;BA.debugLine="If Value=250   Then";
if (_value==250) { 
 //BA.debugLineNum = 1766;BA.debugLine="Return Bombillades";
if (true) return _bombillades(_ba);
 }else if(_value==249) { 
 //BA.debugLineNum = 1768;BA.debugLine="Return BombillaAl";
if (true) return _bombillaal(_ba);
 }else if(_value==0) { 
 //BA.debugLineNum = 1770;BA.debugLine="Return BombillaOff";
if (true) return _bombillaoff(_ba);
 }else {
 //BA.debugLineNum = 1772;BA.debugLine="Return BombillaOn";
if (true) return _bombillaon(_ba);
 };
 };
 //BA.debugLineNum = 1780;BA.debugLine="If Circuitos(Number).Rango=7  Then";
if (_circuitos[_number].Rango==7) { 
 //BA.debugLineNum = 1781;BA.debugLine="If  Value =250 Then";
if (_value==250) { 
 //BA.debugLineNum = 1782;BA.debugLine="Return Enchufedes";
if (true) return _enchufedes(_ba);
 }else if(_value==249) { 
 //BA.debugLineNum = 1784;BA.debugLine="Return EnchufeAl";
if (true) return _enchufeal(_ba);
 }else if(_value==0) { 
 //BA.debugLineNum = 1786;BA.debugLine="Return EnchufeOff";
if (true) return _enchufeoff(_ba);
 }else {
 //BA.debugLineNum = 1788;BA.debugLine="Return EnchufeOn";
if (true) return _enchufeon(_ba);
 };
 };
 //BA.debugLineNum = 1794;BA.debugLine="If Circuitos(Number).Rango=39 Then";
if (_circuitos[_number].Rango==39) { 
 //BA.debugLineNum = 1795;BA.debugLine="If  Value =250 Then";
if (_value==250) { 
 //BA.debugLineNum = 1796;BA.debugLine="Return Puertades";
if (true) return _puertades(_ba);
 }else if(_value==249) { 
 //BA.debugLineNum = 1798;BA.debugLine="Return PuertaAl";
if (true) return _puertaal(_ba);
 }else if(_value==0) { 
 //BA.debugLineNum = 1800;BA.debugLine="Return  PuertaOff";
if (true) return _puertaoff(_ba);
 }else {
 //BA.debugLineNum = 1802;BA.debugLine="Return  PuertaOn";
if (true) return _puertaon(_ba);
 };
 };
 //BA.debugLineNum = 1812;BA.debugLine="If Circuitos(Number).Rango=13 Or  Circuitos(Numbe";
if (_circuitos[_number].Rango==13 || _circuitos[_number].Rango==14) { 
 //BA.debugLineNum = 1813;BA.debugLine="If  Value =250 Then";
if (_value==250) { 
 //BA.debugLineNum = 1814;BA.debugLine="Return  Riegodes";
if (true) return _riegodes(_ba);
 }else if(_value==249) { 
 //BA.debugLineNum = 1816;BA.debugLine="Return RiegoAl";
if (true) return _riegoal(_ba);
 }else if(_value==0) { 
 //BA.debugLineNum = 1818;BA.debugLine="Return  RiegoOff";
if (true) return _riegooff(_ba);
 }else {
 //BA.debugLineNum = 1820;BA.debugLine="Return  RiegoOn";
if (true) return _riegoon(_ba);
 };
 };
 //BA.debugLineNum = 1826;BA.debugLine="If Circuitos(Number).Rango=15 Then";
if (_circuitos[_number].Rango==15) { 
 //BA.debugLineNum = 1827;BA.debugLine="If  Value =250 Then";
if (_value==250) { 
 //BA.debugLineNum = 1828;BA.debugLine="Return  ValvulaDes";
if (true) return _valvulades(_ba);
 }else if(_value==249) { 
 //BA.debugLineNum = 1830;BA.debugLine="Return ValvulaAl";
if (true) return _valvulaal(_ba);
 }else if(_value==0) { 
 //BA.debugLineNum = 1832;BA.debugLine="Return  ValvulaOff";
if (true) return _valvulaoff(_ba);
 }else {
 //BA.debugLineNum = 1834;BA.debugLine="Return  ValvulaOn";
if (true) return _valvulaon(_ba);
 };
 };
 //BA.debugLineNum = 1841;BA.debugLine="If Circuitos(Number).Rango=19 Then";
if (_circuitos[_number].Rango==19) { 
 //BA.debugLineNum = 1842;BA.debugLine="If  Value =250 Then";
if (_value==250) { 
 //BA.debugLineNum = 1843;BA.debugLine="Return  Acondicionadodes";
if (true) return _acondicionadodes(_ba);
 }else if(_value==249) { 
 //BA.debugLineNum = 1845;BA.debugLine="Return 	AcondicionadoAl";
if (true) return _acondicionadoal(_ba);
 }else if(_value==0) { 
 //BA.debugLineNum = 1847;BA.debugLine="Return  AcondicionadoOff";
if (true) return _acondicionadooff(_ba);
 }else {
 //BA.debugLineNum = 1849;BA.debugLine="Return  AcondicionadoOn";
if (true) return _acondicionadoon(_ba);
 };
 };
 //BA.debugLineNum = 1855;BA.debugLine="If Circuitos(Number).Rango=24 Then";
if (_circuitos[_number].Rango==24) { 
 //BA.debugLineNum = 1856;BA.debugLine="If  Value =250 Then";
if (_value==250) { 
 //BA.debugLineNum = 1858;BA.debugLine="Return  Calefacciondes";
if (true) return _calefacciondes(_ba);
 }else if(_value==249) { 
 //BA.debugLineNum = 1860;BA.debugLine="Return CalefaccionAl";
if (true) return _calefaccional(_ba);
 }else if(_value==0) { 
 //BA.debugLineNum = 1862;BA.debugLine="Return  CalefaccionOff";
if (true) return _calefaccionoff(_ba);
 }else {
 //BA.debugLineNum = 1864;BA.debugLine="Return  CalefaccionOn";
if (true) return _calefaccionon(_ba);
 };
 };
 //BA.debugLineNum = 1869;BA.debugLine="If Circuitos(Number).Rango=25 Then";
if (_circuitos[_number].Rango==25) { 
 //BA.debugLineNum = 1870;BA.debugLine="If  Value =250 Then";
if (_value==250) { 
 //BA.debugLineNum = 1871;BA.debugLine="Return  Calefacciondes";
if (true) return _calefacciondes(_ba);
 }else if(_value==249) { 
 //BA.debugLineNum = 1873;BA.debugLine="Return CalefaccionAl";
if (true) return _calefaccional(_ba);
 }else if(_value==0) { 
 //BA.debugLineNum = 1875;BA.debugLine="Return  CalefaccionOff";
if (true) return _calefaccionoff(_ba);
 }else {
 //BA.debugLineNum = 1877;BA.debugLine="Return  CalefaccionOn";
if (true) return _calefaccionon(_ba);
 };
 };
 //BA.debugLineNum = 1885;BA.debugLine="If Circuitos(Number).Rango>52 And Circuitos(Numbe";
if (_circuitos[_number].Rango>52 && _circuitos[_number].Rango<57) { 
 //BA.debugLineNum = 1887;BA.debugLine="Select Case Sensores( Circuitos(Number).DeviceNu";
switch (BA.switchObjectToInt(_sensores[_circuitos[_number].DeviceNumber].Rango,(int) (1),(int) (2),(int) (3),(int) (4),(int) (5),(int) (100))) {
case 0: {
 //BA.debugLineNum = 1890;BA.debugLine="If Value=250 Then";
if (_value==250) { 
 //BA.debugLineNum = 1891;BA.debugLine="Return  Temperaturades";
if (true) return _temperaturades(_ba);
 }else {
 //BA.debugLineNum = 1894;BA.debugLine="Return  Temperatura";
if (true) return _temperatura(_ba);
 };
 break; }
case 1: {
 //BA.debugLineNum = 1897;BA.debugLine="If Value=250 Then";
if (_value==250) { 
 //BA.debugLineNum = 1898;BA.debugLine="Return SensorHumedaddes";
if (true) return _sensorhumedaddes(_ba);
 }else {
 //BA.debugLineNum = 1901;BA.debugLine="Return SensorHumedad";
if (true) return _sensorhumedad(_ba);
 };
 break; }
case 2: {
 //BA.debugLineNum = 1905;BA.debugLine="If Value=250 Then";
if (_value==250) { 
 //BA.debugLineNum = 1906;BA.debugLine="Return SensorLuxDes";
if (true) return _sensorluxdes(_ba);
 }else {
 //BA.debugLineNum = 1909;BA.debugLine="Return SensorLux";
if (true) return _sensorlux(_ba);
 };
 break; }
case 3: {
 //BA.debugLineNum = 1913;BA.debugLine="If Value=250 Then";
if (_value==250) { 
 //BA.debugLineNum = 1914;BA.debugLine="Return SensorGenericoDes";
if (true) return _sensorgenericodes(_ba);
 }else {
 //BA.debugLineNum = 1917;BA.debugLine="Return SensorGenerico";
if (true) return _sensorgenerico(_ba);
 };
 break; }
case 4: {
 //BA.debugLineNum = 1921;BA.debugLine="If Value=250 Then";
if (_value==250) { 
 //BA.debugLineNum = 1922;BA.debugLine="Return SensorGenericoDes";
if (true) return _sensorgenericodes(_ba);
 }else {
 //BA.debugLineNum = 1925;BA.debugLine="Return SensorGenerico";
if (true) return _sensorgenerico(_ba);
 };
 break; }
case 5: {
 //BA.debugLineNum = 1929;BA.debugLine="If Value=250 Then";
if (_value==250) { 
 //BA.debugLineNum = 1930;BA.debugLine="Return SensorDepositoDes";
if (true) return _sensordepositodes(_ba);
 }else {
 //BA.debugLineNum = 1933;BA.debugLine="Return SensorDeposito";
if (true) return _sensordeposito(_ba);
 };
 break; }
}
;
 };
 //BA.debugLineNum = 1940;BA.debugLine="If Circuitos(Number).Rango>42 And Circuitos(Numbe";
if (_circuitos[_number].Rango>42 && _circuitos[_number].Rango<46) { 
 //BA.debugLineNum = 1941;BA.debugLine="If  Value =250 Then";
if (_value==250) { 
 //BA.debugLineNum = 1942;BA.debugLine="Return  VentiladorDes";
if (true) return _ventiladordes(_ba);
 }else if(_value==249) { 
 //BA.debugLineNum = 1944;BA.debugLine="Return VentiladorAl";
if (true) return _ventiladoral(_ba);
 }else if(_value==0) { 
 //BA.debugLineNum = 1946;BA.debugLine="Return  VentiladorOff";
if (true) return _ventiladoroff(_ba);
 }else {
 //BA.debugLineNum = 1948;BA.debugLine="Return  VentiladorOn";
if (true) return _ventiladoron(_ba);
 };
 };
 //BA.debugLineNum = 1956;BA.debugLine="If Circuitos(Number).Rango=29 Or Circuitos(Number";
if (_circuitos[_number].Rango==29 || _circuitos[_number].Rango==30 || _circuitos[_number].Rango==31) { 
 //BA.debugLineNum = 1958;BA.debugLine="If Value=255 Then";
if (_value==255) { 
 //BA.debugLineNum = 1959;BA.debugLine="Return  Temperaturades";
if (true) return _temperaturades(_ba);
 }else {
 //BA.debugLineNum = 1962;BA.debugLine="Return  Temperatura";
if (true) return _temperatura(_ba);
 };
 };
 //BA.debugLineNum = 1969;BA.debugLine="If Circuitos(Number).Rango=34 Or Circuitos(Number";
if (_circuitos[_number].Rango==34 || _circuitos[_number].Rango==36) { 
 //BA.debugLineNum = 1971;BA.debugLine="If Value>100  Or Value<0 Then";
if (_value>100 || _value<0) { 
 //BA.debugLineNum = 1972;BA.debugLine="Return  persianades";
if (true) return _persianades(_ba);
 }else {
 //BA.debugLineNum = 1975;BA.debugLine="Return  Persiana";
if (true) return _persiana(_ba);
 };
 };
 //BA.debugLineNum = 1981;BA.debugLine="If Circuitos(Number).Rango=35 Then";
if (_circuitos[_number].Rango==35) { 
 //BA.debugLineNum = 1982;BA.debugLine="If Value>100  Or Value<0 Then";
if (_value>100 || _value<0) { 
 //BA.debugLineNum = 1983;BA.debugLine="Return  Toldodes";
if (true) return _toldodes(_ba);
 }else {
 //BA.debugLineNum = 1986;BA.debugLine="If Value=0 Then";
if (_value==0) { 
 //BA.debugLineNum = 1987;BA.debugLine="Return  Toldooff";
if (true) return _toldooff(_ba);
 }else {
 //BA.debugLineNum = 1989;BA.debugLine="Return  ToldoOn";
if (true) return _toldoon(_ba);
 };
 };
 };
 //BA.debugLineNum = 1997;BA.debugLine="If Circuitos(Number).Rango>50 Then";
if (_circuitos[_number].Rango>50) { 
 //BA.debugLineNum = 1998;BA.debugLine="If  Value =250 Then";
if (_value==250) { 
 //BA.debugLineNum = 1999;BA.debugLine="Return  PilotoDes";
if (true) return _pilotodes(_ba);
 }else if(_value==249) { 
 //BA.debugLineNum = 2001;BA.debugLine="Return PilotoAl";
if (true) return _pilotoal(_ba);
 }else if(_value==0) { 
 //BA.debugLineNum = 2003;BA.debugLine="Return  PilotoOff";
if (true) return _pilotooff(_ba);
 }else {
 //BA.debugLineNum = 2006;BA.debugLine="Return  PilotoOn";
if (true) return _pilotoon(_ba);
 };
 };
 //BA.debugLineNum = 2010;BA.debugLine="Return  PilotoDes";
if (true) return _pilotodes(_ba);
 //BA.debugLineNum = 2011;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _iconosensor(anywheresoftware.b4a.BA _ba,int _number) throws Exception{
 //BA.debugLineNum = 1738;BA.debugLine="Sub IconoSensor(Number As Int) As Bitmap";
 //BA.debugLineNum = 1739;BA.debugLine="If Sensores(Number).Rango = 1 Then";
if (_sensores[_number].Rango==1) { 
 //BA.debugLineNum = 1740;BA.debugLine="Return SensorTemp";
if (true) return _sensortemp(_ba);
 }else if(_sensores[_number].Rango==2) { 
 //BA.debugLineNum = 1742;BA.debugLine="Return SensorHumedad";
if (true) return _sensorhumedad(_ba);
 }else if(_sensores[_number].Rango==3) { 
 //BA.debugLineNum = 1744;BA.debugLine="Return SensorLux";
if (true) return _sensorlux(_ba);
 }else if(_sensores[_number].Rango==100) { 
 //BA.debugLineNum = 1746;BA.debugLine="Return SensorDeposito";
if (true) return _sensordeposito(_ba);
 };
 //BA.debugLineNum = 1748;BA.debugLine="Return SensorGenerico";
if (true) return _sensorgenerico(_ba);
 //BA.debugLineNum = 1749;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _imgend(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 508;BA.debugLine="Sub ImgEnd()As Bitmap";
 //BA.debugLineNum = 509;BA.debugLine="If ImgSalir.IsInitialized = False Then ImgSalir.I";
if (_imgsalir.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgsalir.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Salir.png");};
 //BA.debugLineNum = 510;BA.debugLine="Return 	ImgSalir";
if (true) return _imgsalir;
 //BA.debugLineNum = 511;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _imggrafico(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 393;BA.debugLine="Sub ImgGrafico()As Bitmap";
 //BA.debugLineNum = 394;BA.debugLine="If ImgChart.IsInitialized = False Then ImgChart.I";
if (_imgchart.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgchart.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"chart.png");};
 //BA.debugLineNum = 395;BA.debugLine="Return ImgChart";
if (true) return _imgchart;
 //BA.debugLineNum = 396;BA.debugLine="End Sub";
return null;
}
public static String  _iniciariconoscentrales(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.List _l = null;
int _li = 0;
 //BA.debugLineNum = 195;BA.debugLine="Sub IniciarIconosCentrales";
 //BA.debugLineNum = 197;BA.debugLine="Dim l As List";
_l = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 199;BA.debugLine="For li = 0 To Centrales.Size -1";
{
final int step2 = 1;
final int limit2 = (int) (_centrales.getSize()-1);
_li = (int) (0) ;
for (;(step2 > 0 && _li <= limit2) || (step2 < 0 && _li >= limit2) ;_li = ((int)(0 + _li + step2))  ) {
 //BA.debugLineNum = 202;BA.debugLine="If File.Exists ( File.DirInternal ,\"Conect\" & Ce";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conect"+BA.ObjectToString(_centrales.Get(_li)))) { 
 //BA.debugLineNum = 203;BA.debugLine="l= File.ReadList (File.DirInternal ,\"Conect\" &";
_l = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conect"+BA.ObjectToString(_centrales.Get(_li)));
 //BA.debugLineNum = 204;BA.debugLine="SetImgDevice (Centrales.Get (li),l.Get (5))";
_setimgdevice(_ba,BA.ObjectToString(_centrales.Get(_li)),(int)(BA.ObjectToNumber(_l.Get((int) (5)))));
 }else {
 //BA.debugLineNum = 208;BA.debugLine="SetImgDevice (Centrales.Get (li),0)";
_setimgdevice(_ba,BA.ObjectToString(_centrales.Get(_li)),(int) (0));
 };
 }
};
 //BA.debugLineNum = 211;BA.debugLine="End Sub";
return "";
}
public static void  _iniudp(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.SocketWrapper.UDPSocket _udpsocket1) throws Exception{
ResumableSub_IniUDP rsub = new ResumableSub_IniUDP(null,_ba,_udpsocket1);
rsub.resume((_ba.processBA == null ? _ba : _ba.processBA), null);
}
public static class ResumableSub_IniUDP extends BA.ResumableSub {
public ResumableSub_IniUDP(arduino.automatizacion.excontrol.PRO.valorescomunes parent,anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.SocketWrapper.UDPSocket _udpsocket1) {
this.parent = parent;
this._ba = _ba;
this._udpsocket1 = _udpsocket1;
}
arduino.automatizacion.excontrol.PRO.valorescomunes parent;
anywheresoftware.b4a.BA _ba;
anywheresoftware.b4a.objects.SocketWrapper.UDPSocket _udpsocket1;
boolean _resultado = false;
int _reintento = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
                return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 214;BA.debugLine="Dim Resultado As Boolean";
_resultado = false;
 //BA.debugLineNum = 215;BA.debugLine="Dim Reintento As Int";
_reintento = 0;
 //BA.debugLineNum = 218;BA.debugLine="Do 	While Resultado= False And Reintento < 40";
if (true) break;

case 1:
//do while
this.state = 10;
while (_resultado==anywheresoftware.b4a.keywords.Common.False && _reintento<40) {
this.state = 3;
if (true) break;
}
if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 219;BA.debugLine="Resultado = IntentoIniUDP(UDPSocket1)";
_resultado = _intentoiniudp(_ba,_udpsocket1);
 //BA.debugLineNum = 220;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 221;BA.debugLine="If Resultado=False Then Sleep (200)";
if (true) break;

case 4:
//if
this.state = 9;
if (_resultado==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 6;
;}if (true) break;

case 6:
//C
this.state = 9;
anywheresoftware.b4a.keywords.Common.Sleep(_ba,this,(int) (200));
this.state = 11;
return;
case 11:
//C
this.state = 9;
;
if (true) break;

case 9:
//C
this.state = 1;
;
 if (true) break;

case 10:
//C
this.state = -1;
;
 //BA.debugLineNum = 224;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static boolean  _intentoiniudp(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.SocketWrapper.UDPSocket _udpsocket1) throws Exception{
 //BA.debugLineNum = 226;BA.debugLine="Sub IntentoIniUDP(UDPSocket1 As UDPSocket) As Bool";
 //BA.debugLineNum = 227;BA.debugLine="Try";
try { //BA.debugLineNum = 229;BA.debugLine="UDPSocket1.Initialize(\"UDP\"   ,  0  , 500)";
_udpsocket1.Initialize((_ba.processBA == null ? _ba : _ba.processBA),"UDP",(int) (0),(int) (500));
 //BA.debugLineNum = 230;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e5) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e5); //BA.debugLineNum = 232;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 234;BA.debugLine="End Sub";
return false;
}
public static String  _loadpaleta(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.agraham.dialogs.InputDialog.ColorPickerDialog _dlg1,anywheresoftware.b4a.agraham.dialogs.InputDialog.ColorPickerDialog _dlg2) throws Exception{
 //BA.debugLineNum = 1685;BA.debugLine="Sub LoadPaleta(dlg1 As ColorPickerDialog ,dlg2 As";
 //BA.debugLineNum = 1688;BA.debugLine="dlg1.SetPaletteAt (0,  8388736)";
_dlg1.SetPaletteAt((int) (0),(int) (8388736));
 //BA.debugLineNum = 1689;BA.debugLine="dlg1.SetPaletteAt (1,  15631086)";
_dlg1.SetPaletteAt((int) (1),(int) (15631086));
 //BA.debugLineNum = 1690;BA.debugLine="dlg1.SetPaletteAt (2,  9055202)";
_dlg1.SetPaletteAt((int) (2),(int) (9055202));
 //BA.debugLineNum = 1691;BA.debugLine="dlg1.SetPaletteAt (3, 6970061 )";
_dlg1.SetPaletteAt((int) (3),(int) (6970061));
 //BA.debugLineNum = 1692;BA.debugLine="dlg1.SetPaletteAt (4,  8087790)";
_dlg1.SetPaletteAt((int) (4),(int) (8087790));
 //BA.debugLineNum = 1693;BA.debugLine="dlg1.SetPaletteAt (5,  255)";
_dlg1.SetPaletteAt((int) (5),(int) (255));
 //BA.debugLineNum = 1694;BA.debugLine="dlg1.SetPaletteAt (6, 65535 )";
_dlg1.SetPaletteAt((int) (6),(int) (65535));
 //BA.debugLineNum = 1695;BA.debugLine="dlg1.SetPaletteAt (7,  11591910)";
_dlg1.SetPaletteAt((int) (7),(int) (11591910));
 //BA.debugLineNum = 1696;BA.debugLine="dlg1.SetPaletteAt (8,  49151)";
_dlg1.SetPaletteAt((int) (8),(int) (49151));
 //BA.debugLineNum = 1697;BA.debugLine="dlg1.SetPaletteAt (9,  2142890)";
_dlg1.SetPaletteAt((int) (9),(int) (2142890));
 //BA.debugLineNum = 1698;BA.debugLine="dlg1.SetPaletteAt (10, 6737322 )";
_dlg1.SetPaletteAt((int) (10),(int) (6737322));
 //BA.debugLineNum = 1699;BA.debugLine="dlg1.SetPaletteAt (11,10025880  )";
_dlg1.SetPaletteAt((int) (11),(int) (10025880));
 //BA.debugLineNum = 1700;BA.debugLine="dlg1.SetPaletteAt (12, 64154 )";
_dlg1.SetPaletteAt((int) (12),(int) (64154));
 //BA.debugLineNum = 1701;BA.debugLine="dlg1.SetPaletteAt (13, 32768 )";
_dlg1.SetPaletteAt((int) (13),(int) (32768));
 //BA.debugLineNum = 1702;BA.debugLine="dlg1.SetPaletteAt (14,  65280)";
_dlg1.SetPaletteAt((int) (14),(int) (65280));
 //BA.debugLineNum = 1705;BA.debugLine="dlg2.SetPaletteAt (0,  14423100)";
_dlg2.SetPaletteAt((int) (0),(int) (14423100));
 //BA.debugLineNum = 1706;BA.debugLine="dlg2.SetPaletteAt (5,  16711680)";
_dlg2.SetPaletteAt((int) (5),(int) (16711680));
 //BA.debugLineNum = 1707;BA.debugLine="dlg2.SetPaletteAt (10,  16737094)";
_dlg2.SetPaletteAt((int) (10),(int) (16737094));
 //BA.debugLineNum = 1709;BA.debugLine="dlg2.SetPaletteAt (1, 13047173 )";
_dlg2.SetPaletteAt((int) (1),(int) (13047173));
 //BA.debugLineNum = 1710;BA.debugLine="dlg2.SetPaletteAt (6,  16716947)";
_dlg2.SetPaletteAt((int) (6),(int) (16716947));
 //BA.debugLineNum = 1711;BA.debugLine="dlg2.SetPaletteAt (11,  16761035)";
_dlg2.SetPaletteAt((int) (11),(int) (16761035));
 //BA.debugLineNum = 1713;BA.debugLine="dlg2.SetPaletteAt (2,  16729344)";
_dlg2.SetPaletteAt((int) (2),(int) (16729344));
 //BA.debugLineNum = 1714;BA.debugLine="dlg2.SetPaletteAt (7,  16747520)";
_dlg2.SetPaletteAt((int) (7),(int) (16747520));
 //BA.debugLineNum = 1715;BA.debugLine="dlg2.SetPaletteAt (12,  16752762)";
_dlg2.SetPaletteAt((int) (12),(int) (16752762));
 //BA.debugLineNum = 1717;BA.debugLine="dlg2.SetPaletteAt (3, 16776960)";
_dlg2.SetPaletteAt((int) (3),(int) (16776960));
 //BA.debugLineNum = 1718;BA.debugLine="dlg2.SetPaletteAt (8, 12092939)";
_dlg2.SetPaletteAt((int) (8),(int) (12092939));
 //BA.debugLineNum = 1719;BA.debugLine="dlg2.SetPaletteAt (13,  15787660)";
_dlg2.SetPaletteAt((int) (13),(int) (15787660));
 //BA.debugLineNum = 1722;BA.debugLine="dlg2.SetPaletteAt (4,  10824234)";
_dlg2.SetPaletteAt((int) (4),(int) (10824234));
 //BA.debugLineNum = 1723;BA.debugLine="dlg2.SetPaletteAt (9, 16032864)";
_dlg2.SetPaletteAt((int) (9),(int) (16032864));
 //BA.debugLineNum = 1724;BA.debugLine="dlg2.SetPaletteAt (14, 16113331)";
_dlg2.SetPaletteAt((int) (14),(int) (16113331));
 //BA.debugLineNum = 1725;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _mnualarmas(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 290;BA.debugLine="Sub MnuAlarmas()As Bitmap";
 //BA.debugLineNum = 291;BA.debugLine="If ImgMnuAl.IsInitialized = False Then ImgMnuAl.I";
if (_imgmnual.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgmnual.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ala.png");};
 //BA.debugLineNum = 292;BA.debugLine="Return 	ImgMnuAl";
if (true) return _imgmnual;
 //BA.debugLineNum = 293;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _mnucircuitos(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 263;BA.debugLine="Sub MnuCircuitos()As Bitmap";
 //BA.debugLineNum = 264;BA.debugLine="If ImgMnuCir.IsInitialized = False Then ImgMnuCir";
if (_imgmnucir.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgmnucir.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"cir.png");};
 //BA.debugLineNum = 265;BA.debugLine="Return 	ImgMnuCir";
if (true) return _imgmnucir;
 //BA.debugLineNum = 266;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _mnucomandos(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 307;BA.debugLine="Sub MnuComandos()As Bitmap";
 //BA.debugLineNum = 308;BA.debugLine="If ImgMnuCom.IsInitialized = False Then ImgMnuCom";
if (_imgmnucom.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgmnucom.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"com.png");};
 //BA.debugLineNum = 309;BA.debugLine="Return 	ImgMnuCom";
if (true) return _imgmnucom;
 //BA.debugLineNum = 310;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _mnucondicionado(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 294;BA.debugLine="Sub MnuCondicionado()As Bitmap";
 //BA.debugLineNum = 295;BA.debugLine="If ImgMnuCon.IsInitialized = False Then ImgMnuCon";
if (_imgmnucon.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgmnucon.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"cond.png");};
 //BA.debugLineNum = 296;BA.debugLine="Return 	ImgMnuCon";
if (true) return _imgmnucon;
 //BA.debugLineNum = 297;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _mnuconfiglt(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 281;BA.debugLine="Sub MnuConfigLt()As Bitmap";
 //BA.debugLineNum = 282;BA.debugLine="If ImgConfigLt.IsInitialized = False Then ImgConf";
if (_imgconfiglt.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgconfiglt.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"SettingLt.png");};
 //BA.debugLineNum = 283;BA.debugLine="Return 	ImgConfigLt";
if (true) return _imgconfiglt;
 //BA.debugLineNum = 284;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _mnuend(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 277;BA.debugLine="Sub MnuEnd()As Bitmap";
 //BA.debugLineNum = 278;BA.debugLine="If ImgMnuEnd.IsInitialized = False Then ImgMnuEnd";
if (_imgmnuend.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgmnuend.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"mnuend.png");};
 //BA.debugLineNum = 279;BA.debugLine="Return 	ImgMnuEnd";
if (true) return _imgmnuend;
 //BA.debugLineNum = 280;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _mnufuncionesp(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 267;BA.debugLine="Sub MnuFuncionEsp() As Bitmap";
 //BA.debugLineNum = 270;BA.debugLine="If ImgFC.IsInitialized = False Then ImgFC.Initial";
if (_imgfc.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgfc.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"fep.png");};
 //BA.debugLineNum = 271;BA.debugLine="Return 	ImgFC";
if (true) return _imgfc;
 //BA.debugLineNum = 272;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _mnuhome(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 299;BA.debugLine="Sub MnuHome()As Bitmap";
 //BA.debugLineNum = 300;BA.debugLine="If ImgMnuHome.IsInitialized = False Then ImgMnuHo";
if (_imgmnuhome.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgmnuhome.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"hom.png");};
 //BA.debugLineNum = 301;BA.debugLine="Return 	ImgMnuHome";
if (true) return _imgmnuhome;
 //BA.debugLineNum = 302;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _mnuimg(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 318;BA.debugLine="Sub MnuImg()As Bitmap";
 //BA.debugLineNum = 319;BA.debugLine="If ImgMnu.IsInitialized = False Then ImgMnu.Initi";
if (_imgmnu.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgmnu.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"acess.png");};
 //BA.debugLineNum = 320;BA.debugLine="Return 	ImgMnu";
if (true) return _imgmnu;
 //BA.debugLineNum = 321;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _mnuscene(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 303;BA.debugLine="Sub MnuScene()As Bitmap";
 //BA.debugLineNum = 304;BA.debugLine="If ImgMnuScene.IsInitialized = False Then ImgMnuS";
if (_imgmnuscene.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgmnuscene.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"esc.png");};
 //BA.debugLineNum = 305;BA.debugLine="Return 	ImgMnuScene";
if (true) return _imgmnuscene;
 //BA.debugLineNum = 306;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _mnusensor(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 314;BA.debugLine="Sub MnuSensor()As Bitmap";
 //BA.debugLineNum = 315;BA.debugLine="If ImgMnuSensor.IsInitialized = False Then ImgMnu";
if (_imgmnusensor.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgmnusensor.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"sen.png");};
 //BA.debugLineNum = 316;BA.debugLine="Return 	ImgMnuSensor";
if (true) return _imgmnusensor;
 //BA.debugLineNum = 317;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _mnusetpointlt(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 273;BA.debugLine="Sub MnuSetPointLt()As Bitmap";
 //BA.debugLineNum = 274;BA.debugLine="If ImgSetPointLt.IsInitialized = False Then ImgSe";
if (_imgsetpointlt.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgsetpointlt.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"set.png");};
 //BA.debugLineNum = 275;BA.debugLine="Return 	ImgSetPointLt";
if (true) return _imgsetpointlt;
 //BA.debugLineNum = 276;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _mnutxt(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 285;BA.debugLine="Sub MnuTxt()As Bitmap";
 //BA.debugLineNum = 286;BA.debugLine="If ImgFreeTxt.IsInitialized = False Then ImgFreeT";
if (_imgfreetxt.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgfreetxt.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"freeTxtt.png");};
 //BA.debugLineNum = 287;BA.debugLine="Return 	ImgFreeTxt";
if (true) return _imgfreetxt;
 //BA.debugLineNum = 288;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _on(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 554;BA.debugLine="Sub On()As Bitmap";
 //BA.debugLineNum = 555;BA.debugLine="If ImgOn.IsInitialized = False Then ImgOn.Initial";
if (_imgon.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgon.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"On.png");};
 //BA.debugLineNum = 556;BA.debugLine="Return 	ImgOn";
if (true) return _imgon;
 //BA.debugLineNum = 557;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _persiana(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 512;BA.debugLine="Sub Persiana()As Bitmap";
 //BA.debugLineNum = 513;BA.debugLine="If Imgpersiana.IsInitialized = False Then Imgpers";
if (_imgpersiana.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgpersiana.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"persiana.png");};
 //BA.debugLineNum = 514;BA.debugLine="Return 	Imgpersiana";
if (true) return _imgpersiana;
 //BA.debugLineNum = 515;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _persianades(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 516;BA.debugLine="Sub persianades()As Bitmap";
 //BA.debugLineNum = 517;BA.debugLine="If Imgpersianades.IsInitialized = False Then Imgp";
if (_imgpersianades.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgpersianades.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"persianaDes.png");};
 //BA.debugLineNum = 518;BA.debugLine="Return 	Imgpersianades";
if (true) return _imgpersianades;
 //BA.debugLineNum = 519;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _pilotoal(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 332;BA.debugLine="Sub PilotoAl()As Bitmap";
 //BA.debugLineNum = 333;BA.debugLine="If ImgPilotoAl.IsInitialized = False Then ImgPilo";
if (_imgpilotoal.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgpilotoal.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Al.png");};
 //BA.debugLineNum = 334;BA.debugLine="Return 	ImgPilotoAl";
if (true) return _imgpilotoal;
 //BA.debugLineNum = 335;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _pilotodes(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 607;BA.debugLine="Sub PilotoDes()As Bitmap";
 //BA.debugLineNum = 608;BA.debugLine="If ImgPilotoDes.IsInitialized = False Then ImgPil";
if (_imgpilotodes.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgpilotodes.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"des.png");};
 //BA.debugLineNum = 609;BA.debugLine="Return 	ImgPilotoDes";
if (true) return _imgpilotodes;
 //BA.debugLineNum = 610;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _pilotooff(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 611;BA.debugLine="Sub PilotoOff()As Bitmap";
 //BA.debugLineNum = 612;BA.debugLine="If ImgPilotoOff.IsInitialized = False Then ImgPil";
if (_imgpilotooff.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgpilotooff.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"off.png");};
 //BA.debugLineNum = 613;BA.debugLine="Return 	ImgPilotoOff";
if (true) return _imgpilotooff;
 //BA.debugLineNum = 614;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _pilotoon(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 615;BA.debugLine="Sub PilotoOn()As Bitmap";
 //BA.debugLineNum = 616;BA.debugLine="If ImgPilotoOn.IsInitialized = False Then ImgPilo";
if (_imgpilotoon.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgpilotoon.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"on.png");};
 //BA.debugLineNum = 617;BA.debugLine="Return ImgPilotoOn";
if (true) return _imgpilotoon;
 //BA.debugLineNum = 618;BA.debugLine="End Sub";
return null;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Dim CloseApp As Boolean";
_closeapp = false;
 //BA.debugLineNum = 8;BA.debugLine="Type Sp(Nombre As String , Minimo As Int, Maximo";
;
 //BA.debugLineNum = 9;BA.debugLine="Type Scene(Nombre As String , Descripcion As Stri";
;
 //BA.debugLineNum = 10;BA.debugLine="Type Circuito(Nombre As String , Descripcion As S";
;
 //BA.debugLineNum = 11;BA.debugLine="Type Arduino(Nombre As String, Descripcion As Str";
;
 //BA.debugLineNum = 15;BA.debugLine="Dim OldMail As String";
_oldmail = "";
 //BA.debugLineNum = 16;BA.debugLine="Dim OldPass As String";
_oldpass = "";
 //BA.debugLineNum = 17;BA.debugLine="Dim OldExtIp As String";
_oldextip = "";
 //BA.debugLineNum = 19;BA.debugLine="Dim Central As Arduino";
_central = new arduino.automatizacion.excontrol.PRO.valorescomunes._arduino();
 //BA.debugLineNum = 20;BA.debugLine="Dim CentralesImg As Map";
_centralesimg = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 21;BA.debugLine="Dim LanString As Map";
_lanstring = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 22;BA.debugLine="Dim Scenes(10) As Scene";
_scenes = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene[(int) (10)];
{
int d0 = _scenes.length;
for (int i0 = 0;i0 < d0;i0++) {
_scenes[i0] = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
}
}
;
 //BA.debugLineNum = 23;BA.debugLine="Dim Condicionados(10) As Scene";
_condicionados = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene[(int) (10)];
{
int d0 = _condicionados.length;
for (int i0 = 0;i0 < d0;i0++) {
_condicionados[i0] = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
}
}
;
 //BA.debugLineNum = 24;BA.debugLine="Dim Functions(10) As Scene";
_functions = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene[(int) (10)];
{
int d0 = _functions.length;
for (int i0 = 0;i0 < d0;i0++) {
_functions[i0] = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
}
}
;
 //BA.debugLineNum = 25;BA.debugLine="Dim SetPoint As List";
_setpoint = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 28;BA.debugLine="Dim ComandosComunes As List";
_comandoscomunes = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 29;BA.debugLine="Dim DescripcionesComandosComunes As List";
_descripcionescomandoscomunes = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 30;BA.debugLine="Dim Comandos As List";
_comandos = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 31;BA.debugLine="Dim DescripcionesComandos As List";
_descripcionescomandos = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 32;BA.debugLine="Dim NombreSensor As List";
_nombresensor = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 35;BA.debugLine="Dim Circuitos(30) As Circuito";
_circuitos = new arduino.automatizacion.excontrol.PRO.valorescomunes._circuito[(int) (30)];
{
int d0 = _circuitos.length;
for (int i0 = 0;i0 < d0;i0++) {
_circuitos[i0] = new arduino.automatizacion.excontrol.PRO.valorescomunes._circuito();
}
}
;
 //BA.debugLineNum = 36;BA.debugLine="Dim Sensores(15) As Circuito";
_sensores = new arduino.automatizacion.excontrol.PRO.valorescomunes._circuito[(int) (15)];
{
int d0 = _sensores.length;
for (int i0 = 0;i0 < d0;i0++) {
_sensores[i0] = new arduino.automatizacion.excontrol.PRO.valorescomunes._circuito();
}
}
;
 //BA.debugLineNum = 38;BA.debugLine="Dim Ip As String";
_ip = "";
 //BA.debugLineNum = 39;BA.debugLine="Dim Puerto As Int";
_puerto = 0;
 //BA.debugLineNum = 41;BA.debugLine="Dim AlmName As List";
_almname = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 46;BA.debugLine="Dim PassswordByte() As Byte";
_passswordbyte = new byte[(int) (0)];
;
 //BA.debugLineNum = 49;BA.debugLine="Dim Centrales As List";
_centrales = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 52;BA.debugLine="Dim ImgBombillaAl, ImgVentiladorAl, ImgValvulaAl,";
_imgbombillaal = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgventiladoral = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgvalvulaal = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgriegoal = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgpuertaal = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgpilotoal = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgacal = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgenchufeal = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgcalefaccional = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Dim ImgConNames, ImgDelete, ImgScene2,ImgAlarma,I";
_imgconnames = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgdelete = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgscene2 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgalarma = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgsensor = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgvoice = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgcargando = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgreloj = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgrelook = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgcalendario = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgicono = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgfechahora = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgconfigup = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgconfigdo = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgon = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgcomando = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgconfig = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgarduino = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgcheckon = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgcheckoff = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgbombillaoff = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgbombillaon = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgbombillades = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgenchufedes = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgenchufeoff = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgenchufeon = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgpuertades = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgpuertaoff = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgpuertaon = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgriegodes = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgriegooff = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgriegoon = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgacondicionadodes = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgacondicionadooff = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgacondicionadoon = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgcalefacciondes = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgcalefaccionoff = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgcalefaccionon = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgtemperaturades = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgtemperatura = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgtoldodes = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgtoldooff = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgtoldoon = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgpersianades = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgpersiana = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Dim ImgChart,imgFunciones,ImgAlmOn,ImgAlmOff,ImgA";
_imgchart = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgfunciones = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgalmon = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgalmoff = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgalmdes = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgvalvulaon = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgvalvulaoff = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgvalvulades = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgventiladoron = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgventiladoroff = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgventiladordes = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgpilotoon = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgpilotooff = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgpilotodes = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Dim ImgDepositoDes, ImgDeposito, ImgSensorTemp, I";
_imgdepositodes = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgdeposito = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgsensortemp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgsensorhumedad = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgsensorhumedadd = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imglux = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgluxd = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgsensor2 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgsensor2d = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgsalir = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Dim ImgFC,ImgSetPointLt,ImgMnuCir, ImgMnuAl, ImgM";
_imgfc = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgsetpointlt = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgmnucir = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgmnual = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgmnucon = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgmnuhome = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgmnuscene = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgmnucom = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgmnusensor = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgmnu = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgfreetxt = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgconfiglt = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 63;BA.debugLine="Dim ImgHome,ImgSalon,ImgCama,ImgBaño,ImgGarden,Im";
_imghome = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgsalon = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgcama = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgbaño = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imggarden = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgcar = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgcocina = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 64;BA.debugLine="Dim ImgSalonlt,ImgCamalt,ImgBañolt,ImgGardenlt,Im";
_imgsalonlt = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgcamalt = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgbañolt = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imggardenlt = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgcarlt = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgcocinalt = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgmnuend = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 67;BA.debugLine="Dim ImgSave, ImgBack, ImgNew As Bitmap";
_imgsave = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgback = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_imgnew = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _puertaal(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 328;BA.debugLine="Sub PuertaAl()As Bitmap";
 //BA.debugLineNum = 329;BA.debugLine="If ImgPuertaAl.IsInitialized = False Then ImgPuer";
if (_imgpuertaal.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgpuertaal.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"puertaAl.png");};
 //BA.debugLineNum = 330;BA.debugLine="Return 	ImgPuertaAl";
if (true) return _imgpuertaal;
 //BA.debugLineNum = 331;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _puertades(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 436;BA.debugLine="Sub Puertades()As Bitmap";
 //BA.debugLineNum = 437;BA.debugLine="If ImgPuertades.IsInitialized = False Then ImgPue";
if (_imgpuertades.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgpuertades.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"PuertaDes.png");};
 //BA.debugLineNum = 438;BA.debugLine="Return 	ImgPuertades";
if (true) return _imgpuertades;
 //BA.debugLineNum = 439;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _puertaoff(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 440;BA.debugLine="Sub PuertaOff()As Bitmap";
 //BA.debugLineNum = 441;BA.debugLine="If ImgPuertaOff.IsInitialized = False Then ImgPue";
if (_imgpuertaoff.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgpuertaoff.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"PuertaOff.png");};
 //BA.debugLineNum = 442;BA.debugLine="Return 	ImgPuertaOff";
if (true) return _imgpuertaoff;
 //BA.debugLineNum = 443;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _puertaon(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 444;BA.debugLine="Sub PuertaOn()As Bitmap";
 //BA.debugLineNum = 445;BA.debugLine="If ImgPuertaOn.IsInitialized = False Then ImgPuer";
if (_imgpuertaon.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgpuertaon.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"PuertaOn.png");};
 //BA.debugLineNum = 446;BA.debugLine="Return 	ImgPuertaOn";
if (true) return _imgpuertaon;
 //BA.debugLineNum = 447;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _reloj(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 528;BA.debugLine="Sub Reloj()As Bitmap";
 //BA.debugLineNum = 529;BA.debugLine="If ImgReloj.IsInitialized = False Then ImgReloj.I";
if (_imgreloj.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgreloj.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Reloj.png");};
 //BA.debugLineNum = 530;BA.debugLine="Return 	ImgReloj";
if (true) return _imgreloj;
 //BA.debugLineNum = 531;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _relook(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 524;BA.debugLine="Sub reloOk()As Bitmap";
 //BA.debugLineNum = 525;BA.debugLine="If ImgreloOk.IsInitialized = False Then ImgreloOk";
if (_imgrelook.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgrelook.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"reloOk.png");};
 //BA.debugLineNum = 526;BA.debugLine="Return 	ImgreloOk";
if (true) return _imgrelook;
 //BA.debugLineNum = 527;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _riegoal(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 324;BA.debugLine="Sub RiegoAl()As Bitmap";
 //BA.debugLineNum = 325;BA.debugLine="If ImgRiegoAl.IsInitialized = False Then ImgRiego";
if (_imgriegoal.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgriegoal.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"riegoAl.png");};
 //BA.debugLineNum = 326;BA.debugLine="Return 	ImgRiegoAl";
if (true) return _imgriegoal;
 //BA.debugLineNum = 327;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _riegodes(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 448;BA.debugLine="Sub Riegodes()As Bitmap";
 //BA.debugLineNum = 449;BA.debugLine="If ImgRiegodes.IsInitialized = False Then ImgRieg";
if (_imgriegodes.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgriegodes.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"RiegoDes.png");};
 //BA.debugLineNum = 450;BA.debugLine="Return 	ImgRiegodes";
if (true) return _imgriegodes;
 //BA.debugLineNum = 451;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _riegooff(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 452;BA.debugLine="Sub RiegoOff()As Bitmap";
 //BA.debugLineNum = 453;BA.debugLine="If ImgRiegoOff.IsInitialized = False Then ImgRieg";
if (_imgriegooff.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgriegooff.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"RiegoOff.png");};
 //BA.debugLineNum = 454;BA.debugLine="Return 	ImgRiegoOff";
if (true) return _imgriegooff;
 //BA.debugLineNum = 455;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _riegoon(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 457;BA.debugLine="Sub RiegoOn()As Bitmap";
 //BA.debugLineNum = 458;BA.debugLine="If ImgRiegoOn.IsInitialized = False Then ImgRiego";
if (_imgriegoon.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgriegoon.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"RiegoOn.png");};
 //BA.debugLineNum = 459;BA.debugLine="Return 	ImgRiegoOn";
if (true) return _imgriegoon;
 //BA.debugLineNum = 460;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _salon(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 773;BA.debugLine="Sub Salon()As Bitmap";
 //BA.debugLineNum = 774;BA.debugLine="If ImgSalon.IsInitialized = False Then ImgSalon.I";
if (_imgsalon.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgsalon.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Salon.png");};
 //BA.debugLineNum = 775;BA.debugLine="Return 	ImgSalon";
if (true) return _imgsalon;
 //BA.debugLineNum = 776;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _salonlt(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 728;BA.debugLine="Sub Salonlt()As Bitmap";
 //BA.debugLineNum = 729;BA.debugLine="If ImgSalonlt.IsInitialized = False Then ImgSalon";
if (_imgsalonlt.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgsalonlt.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Salonlt.png");};
 //BA.debugLineNum = 730;BA.debugLine="Return 	ImgSalonlt";
if (true) return _imgsalonlt;
 //BA.debugLineNum = 731;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _scene2(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 631;BA.debugLine="Sub Scene2()As Bitmap";
 //BA.debugLineNum = 632;BA.debugLine="If ImgScene2.IsInitialized = False Then ImgScene2";
if (_imgscene2.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgscene2.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"scene2.png");};
 //BA.debugLineNum = 633;BA.debugLine="Return 	ImgScene2";
if (true) return _imgscene2;
 //BA.debugLineNum = 634;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _sensor(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 571;BA.debugLine="Sub Sensor()As Bitmap";
 //BA.debugLineNum = 572;BA.debugLine="If ImgSensor.IsInitialized = False Then ImgSensor";
if (_imgsensor.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgsensor.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Sensores.png");};
 //BA.debugLineNum = 573;BA.debugLine="Return 	ImgSensor";
if (true) return _imgsensor;
 //BA.debugLineNum = 574;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _sensordeposito(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 387;BA.debugLine="Sub SensorDeposito()As Bitmap";
 //BA.debugLineNum = 388;BA.debugLine="If ImgDeposito.IsInitialized = False Then ImgDepo";
if (_imgdeposito.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgdeposito.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DepositoOn.png");};
 //BA.debugLineNum = 389;BA.debugLine="Return ImgDeposito";
if (true) return _imgdeposito;
 //BA.debugLineNum = 390;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _sensordepositodes(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 383;BA.debugLine="Sub SensorDepositoDes()As Bitmap";
 //BA.debugLineNum = 384;BA.debugLine="If ImgDepositoDes.IsInitialized = False Then ImgD";
if (_imgdepositodes.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgdepositodes.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"DepositoOnDes.png");};
 //BA.debugLineNum = 385;BA.debugLine="Return ImgDepositoDes";
if (true) return _imgdepositodes;
 //BA.debugLineNum = 386;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _sensorgenerico(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 379;BA.debugLine="Sub SensorGenerico()As Bitmap";
 //BA.debugLineNum = 380;BA.debugLine="If ImgSensor2.IsInitialized = False Then ImgSenso";
if (_imgsensor2.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgsensor2.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"s_generico.png");};
 //BA.debugLineNum = 381;BA.debugLine="Return ImgSensor2";
if (true) return _imgsensor2;
 //BA.debugLineNum = 382;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _sensorgenericodes(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 375;BA.debugLine="Sub SensorGenericoDes()As Bitmap";
 //BA.debugLineNum = 376;BA.debugLine="If ImgSensor2d.IsInitialized = False Then ImgSens";
if (_imgsensor2d.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgsensor2d.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"s_genericodes.png");};
 //BA.debugLineNum = 377;BA.debugLine="Return ImgSensor2d";
if (true) return _imgsensor2d;
 //BA.debugLineNum = 378;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _sensorhumedad(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 255;BA.debugLine="Sub SensorHumedad()As Bitmap";
 //BA.debugLineNum = 256;BA.debugLine="If ImgSensorHumedad.IsInitialized = False Then Im";
if (_imgsensorhumedad.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgsensorhumedad.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"s_humedad.png");};
 //BA.debugLineNum = 257;BA.debugLine="Return ImgSensorHumedad";
if (true) return _imgsensorhumedad;
 //BA.debugLineNum = 258;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _sensorhumedaddes(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 259;BA.debugLine="Sub SensorHumedaddes()As Bitmap";
 //BA.debugLineNum = 260;BA.debugLine="If ImgSensorHumedadd.IsInitialized = False Then I";
if (_imgsensorhumedadd.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgsensorhumedadd.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"s_humedaddes.png");};
 //BA.debugLineNum = 261;BA.debugLine="Return ImgSensorHumedadd";
if (true) return _imgsensorhumedadd;
 //BA.debugLineNum = 262;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _sensorlux(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 367;BA.debugLine="Sub SensorLux()As Bitmap";
 //BA.debugLineNum = 368;BA.debugLine="If ImgLux.IsInitialized = False Then ImgLux.Initi";
if (_imglux.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imglux.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lux.png");};
 //BA.debugLineNum = 369;BA.debugLine="Return ImgLux";
if (true) return _imglux;
 //BA.debugLineNum = 370;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _sensorluxdes(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 371;BA.debugLine="Sub SensorLuxDes()As Bitmap";
 //BA.debugLineNum = 372;BA.debugLine="If ImgLuxd.IsInitialized = False Then ImgLuxd.Ini";
if (_imgluxd.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgluxd.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"luxdes.png");};
 //BA.debugLineNum = 373;BA.debugLine="Return ImgLuxd";
if (true) return _imgluxd;
 //BA.debugLineNum = 374;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _sensortemp(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 363;BA.debugLine="Sub SensorTemp()As Bitmap";
 //BA.debugLineNum = 364;BA.debugLine="If ImgSensorTemp.IsInitialized = False Then ImgSe";
if (_imgsensortemp.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgsensortemp.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"s_temperatura.png");};
 //BA.debugLineNum = 365;BA.debugLine="Return ImgSensorTemp";
if (true) return _imgsensortemp;
 //BA.debugLineNum = 366;BA.debugLine="End Sub";
return null;
}
public static String  _setimgdevice(anywheresoftware.b4a.BA _ba,String _name,int _value) throws Exception{
 //BA.debugLineNum = 187;BA.debugLine="Sub SetImgDevice(Name As String, Value As Int )";
 //BA.debugLineNum = 188;BA.debugLine="ActMosaico.UpdateCentral=True";
mostCurrent._actmosaico._updatecentral = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 189;BA.debugLine="If CentralesImg.IsInitialized =False Then";
if (_centralesimg.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 190;BA.debugLine="CentralesImg.Initialize";
_centralesimg.Initialize();
 };
 //BA.debugLineNum = 193;BA.debugLine="CentralesImg.Put (Name,Value)";
_centralesimg.Put((Object)(_name),(Object)(_value));
 //BA.debugLineNum = 194;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _temperatura(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 488;BA.debugLine="Sub Temperatura()As Bitmap";
 //BA.debugLineNum = 489;BA.debugLine="If ImgTemperatura.IsInitialized = False Then ImgT";
if (_imgtemperatura.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgtemperatura.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Temperatura.png");};
 //BA.debugLineNum = 490;BA.debugLine="Return ImgTemperatura";
if (true) return _imgtemperatura;
 //BA.debugLineNum = 491;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _temperaturades(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 492;BA.debugLine="Sub Temperaturades()As Bitmap";
 //BA.debugLineNum = 493;BA.debugLine="If ImgTemperaturades.IsInitialized = False Then I";
if (_imgtemperaturades.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgtemperaturades.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"TemperaturaDes.png");};
 //BA.debugLineNum = 494;BA.debugLine="Return 	ImgTemperaturades";
if (true) return _imgtemperaturades;
 //BA.debugLineNum = 495;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _toldodes(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 504;BA.debugLine="Sub Toldodes()As Bitmap";
 //BA.debugLineNum = 505;BA.debugLine="If ImgToldodes.IsInitialized = False Then ImgTold";
if (_imgtoldodes.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgtoldodes.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Toldodes.png");};
 //BA.debugLineNum = 506;BA.debugLine="Return 	ImgToldodes";
if (true) return _imgtoldodes;
 //BA.debugLineNum = 507;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _toldooff(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 500;BA.debugLine="Sub Toldooff()As Bitmap";
 //BA.debugLineNum = 501;BA.debugLine="If ImgToldooff.IsInitialized = False Then ImgTold";
if (_imgtoldooff.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgtoldooff.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Toldooff.png");};
 //BA.debugLineNum = 502;BA.debugLine="Return 	ImgToldooff";
if (true) return _imgtoldooff;
 //BA.debugLineNum = 503;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _toldoon(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 496;BA.debugLine="Sub ToldoOn()As Bitmap";
 //BA.debugLineNum = 497;BA.debugLine="If ImgToldoOn.IsInitialized = False Then ImgToldo";
if (_imgtoldoon.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgtoldoon.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ToldoOn.png");};
 //BA.debugLineNum = 498;BA.debugLine="Return 	ImgToldoOn";
if (true) return _imgtoldoon;
 //BA.debugLineNum = 499;BA.debugLine="End Sub";
return null;
}
public static String  _unidadsensor(anywheresoftware.b4a.BA _ba,int _number) throws Exception{
 //BA.debugLineNum = 1750;BA.debugLine="Sub UnidadSensor(Number As Int) As String";
 //BA.debugLineNum = 1751;BA.debugLine="If Sensores(Number).Rango = 1 Then";
if (_sensores[_number].Rango==1) { 
 //BA.debugLineNum = 1752;BA.debugLine="Return \" ºC\"";
if (true) return " ºC";
 }else if(_sensores[_number].Rango==2) { 
 //BA.debugLineNum = 1754;BA.debugLine="Return \" %H\"";
if (true) return " %H";
 }else if(_sensores[_number].Rango==3) { 
 //BA.debugLineNum = 1756;BA.debugLine="Return \" Lux\"";
if (true) return " Lux";
 }else if(_sensores[_number].Rango==4) { 
 //BA.debugLineNum = 1759;BA.debugLine="Return \"\"";
if (true) return "";
 };
 //BA.debugLineNum = 1761;BA.debugLine="Return \"\"";
if (true) return "";
 //BA.debugLineNum = 1762;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _valvulaal(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 349;BA.debugLine="Sub ValvulaAl()As Bitmap";
 //BA.debugLineNum = 350;BA.debugLine="If ImgValvulaAl.IsInitialized = False Then ImgVal";
if (_imgvalvulaal.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgvalvulaal.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"valvulaAl.png");};
 //BA.debugLineNum = 351;BA.debugLine="Return ImgValvulaAl";
if (true) return _imgvalvulaal;
 //BA.debugLineNum = 352;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _valvulades(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 583;BA.debugLine="Sub ValvulaDes()As Bitmap";
 //BA.debugLineNum = 584;BA.debugLine="If ImgValvulaDes.IsInitialized = False Then ImgVa";
if (_imgvalvulades.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgvalvulades.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"valvulades.png");};
 //BA.debugLineNum = 585;BA.debugLine="Return 	ImgValvulaDes";
if (true) return _imgvalvulades;
 //BA.debugLineNum = 586;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _valvulaoff(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 587;BA.debugLine="Sub ValvulaOff()As Bitmap";
 //BA.debugLineNum = 588;BA.debugLine="If ImgValvulaOff.IsInitialized = False Then ImgVa";
if (_imgvalvulaoff.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgvalvulaoff.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"valvulaoff.png");};
 //BA.debugLineNum = 589;BA.debugLine="Return ImgValvulaOff";
if (true) return _imgvalvulaoff;
 //BA.debugLineNum = 590;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _valvulaon(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 591;BA.debugLine="Sub ValvulaOn()As Bitmap";
 //BA.debugLineNum = 592;BA.debugLine="If ImgValvulaOn.IsInitialized = False Then ImgVal";
if (_imgvalvulaon.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgvalvulaon.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"valvulaon.png");};
 //BA.debugLineNum = 593;BA.debugLine="Return 	ImgValvulaOn";
if (true) return _imgvalvulaon;
 //BA.debugLineNum = 594;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _ventiladoral(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 354;BA.debugLine="Sub VentiladorAl()As Bitmap";
 //BA.debugLineNum = 355;BA.debugLine="If ImgVentiladorAl.IsInitialized = False Then Img";
if (_imgventiladoral.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgventiladoral.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ventiladoral.png");};
 //BA.debugLineNum = 356;BA.debugLine="Return ImgVentiladorAl";
if (true) return _imgventiladoral;
 //BA.debugLineNum = 357;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _ventiladordes(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 595;BA.debugLine="Sub VentiladorDes()As Bitmap";
 //BA.debugLineNum = 596;BA.debugLine="If ImgVentiladorDes.IsInitialized = False Then Im";
if (_imgventiladordes.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgventiladordes.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ventiladordes.png");};
 //BA.debugLineNum = 597;BA.debugLine="Return 	ImgVentiladorDes";
if (true) return _imgventiladordes;
 //BA.debugLineNum = 598;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _ventiladoroff(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 599;BA.debugLine="Sub VentiladorOff()As Bitmap";
 //BA.debugLineNum = 600;BA.debugLine="If ImgVentiladorOff.IsInitialized = False Then Im";
if (_imgventiladoroff.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgventiladoroff.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ventiladoroff.png");};
 //BA.debugLineNum = 601;BA.debugLine="Return 	ImgVentiladorOff";
if (true) return _imgventiladoroff;
 //BA.debugLineNum = 602;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _ventiladoron(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 603;BA.debugLine="Sub VentiladorOn()As Bitmap";
 //BA.debugLineNum = 604;BA.debugLine="If ImgVentiladorOn.IsInitialized = False Then Img";
if (_imgventiladoron.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgventiladoron.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ventiladoron.png");};
 //BA.debugLineNum = 605;BA.debugLine="Return 	ImgVentiladorOn";
if (true) return _imgventiladoron;
 //BA.debugLineNum = 606;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper  _voice(anywheresoftware.b4a.BA _ba) throws Exception{
 //BA.debugLineNum = 575;BA.debugLine="Sub Voice()As Bitmap";
 //BA.debugLineNum = 576;BA.debugLine="If ImgVoice.IsInitialized = False Then ImgVoice.I";
if (_imgvoice.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_imgvoice.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"Voice.png");};
 //BA.debugLineNum = 577;BA.debugLine="Return 	ImgVoice";
if (true) return _imgvoice;
 //BA.debugLineNum = 578;BA.debugLine="End Sub";
return null;
}
}
