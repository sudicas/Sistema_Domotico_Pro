package arduino.automatizacion.excontrol.PRO;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class acthistorico extends Activity implements B4AActivity{
	public static acthistorico mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.acthistorico");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (acthistorico).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, true))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.acthistorico");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "arduino.automatizacion.excontrol.PRO.acthistorico", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (acthistorico) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (acthistorico) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return acthistorico.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (acthistorico) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (acthistorico) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static boolean[] _sensorselect = null;
public static long _fecha = 0L;
public static long _fechafin = 0L;
public static anywheresoftware.b4a.objects.SocketWrapper.UDPSocket _udpsocket1 = null;
public static anywheresoftware.b4a.objects.collections.List _mdates = null;
public static boolean _iniciando = false;
public static anywheresoftware.b4a.objects.collections.List _valores = null;
public static anywheresoftware.b4a.phone.Phone _ph = null;
public static anywheresoftware.b4a.phone.Phone.PhoneWakeState _pw = null;
public static anywheresoftware.b4a.objects.collections.List _tempvalores = null;
public static long _currentfecha = 0L;
public static byte[] _tramaenviada = null;
public static int _numeroenvio = 0;
public com.genesis.graphview.graphview _graphview = null;
public static int _maxindex = 0;
public static double _maxvalue = 0;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgcargando = null;
public anywheresoftware.b4a.objects.AnimationWrapper _anicargando = null;
public static boolean _paqueteenviado = false;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public arduino.automatizacion.excontrol.PRO.main _main = null;
public arduino.automatizacion.excontrol.PRO.actcentral _actcentral = null;
public arduino.automatizacion.excontrol.PRO.valorescomunes _valorescomunes = null;
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

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 58;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 61;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = Fals";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.False || mostCurrent._valorescomunes._closeapp==anywheresoftware.b4a.keywords.Common.True) { 
if (true) return "";};
 //BA.debugLineNum = 63;BA.debugLine="If Main.MyLan.IsInitialized = False Then  Main.My";
if (mostCurrent._main._mylan.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._main._mylan.Initialize(processBA,(int) (0),"");};
 //BA.debugLineNum = 67;BA.debugLine="If UDPSocket1.IsInitialized = False Then  Valores";
if (_udpsocket1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._valorescomunes._iniudp(mostCurrent.activityBA,_udpsocket1);};
 //BA.debugLineNum = 69;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 };
 //BA.debugLineNum = 75;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 169;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 170;BA.debugLine="If UserClosed Then";
if (_userclosed) { 
 //BA.debugLineNum = 172;BA.debugLine="pw.ReleaseKeepAlive";
_pw.ReleaseKeepAlive();
 //BA.debugLineNum = 173;BA.debugLine="UDPSocket1.Close";
_udpsocket1.Close();
 //BA.debugLineNum = 174;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else {
 };
 //BA.debugLineNum = 182;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
String _s = "";
 //BA.debugLineNum = 77;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 78;BA.debugLine="If ValoresComunes.CloseApp =True Then";
if (mostCurrent._valorescomunes._closeapp==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 79;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 80;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 83;BA.debugLine="ph.SetScreenOrientation(0) 'Forzar Horientazión";
_ph.SetScreenOrientation(processBA,(int) (0));
 //BA.debugLineNum = 86;BA.debugLine="If Activity.Width < Activity.Height Then Return";
if (mostCurrent._activity.getWidth()<mostCurrent._activity.getHeight()) { 
if (true) return "";};
 //BA.debugLineNum = 88;BA.debugLine="pw.KeepAlive( True) 'set false if brightness is";
_pw.KeepAlive(processBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 91;BA.debugLine="If File.ExternalWritable =False  Then";
if (anywheresoftware.b4a.keywords.Common.File.getExternalWritable()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 93;BA.debugLine="Msgbox(\"Sd card required\",\"\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Sd card required"),BA.ObjectToCharSequence(""),mostCurrent.activityBA);
 //BA.debugLineNum = 94;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 95;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 97;BA.debugLine="Dim s As String";
_s = "";
 //BA.debugLineNum = 100;BA.debugLine="s = File.DirRootExternal & \"/HT\"";
_s = anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/HT";
 //BA.debugLineNum = 102;BA.debugLine="If File.Exists(s, \"\") = False Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(_s,"")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 103;BA.debugLine="File.MakeDir(File.DirRootExternal,\"HT\")";
anywheresoftware.b4a.keywords.Common.File.MakeDir(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"HT");
 };
 };
 //BA.debugLineNum = 117;BA.debugLine="If Iniciando Then";
if (_iniciando) { 
 //BA.debugLineNum = 119;BA.debugLine="Iniciando=False";
_iniciando = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 122;BA.debugLine="Valores.Initialize";
_valores.Initialize();
 //BA.debugLineNum = 123;BA.debugLine="mdates.Initialize";
_mdates.Initialize();
 //BA.debugLineNum = 124;BA.debugLine="TempValores.Initialize";
_tempvalores.Initialize();
 //BA.debugLineNum = 125;BA.debugLine="currentFecha=Fecha";
_currentfecha = _fecha;
 //BA.debugLineNum = 126;BA.debugLine="NumeroEnvio=1";
_numeroenvio = (int) (1);
 //BA.debugLineNum = 129;BA.debugLine="If ImgCargando.IsInitialized = False Then";
if (mostCurrent._imgcargando.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 130;BA.debugLine="ImgCargando.Initialize (\"ImgCargando\")";
mostCurrent._imgcargando.Initialize(mostCurrent.activityBA,"ImgCargando");
 //BA.debugLineNum = 131;BA.debugLine="ImgCargando.Bitmap = ValoresComunes.Cargando";
mostCurrent._imgcargando.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._cargando(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 132;BA.debugLine="ImgCargando.Visible =False";
mostCurrent._imgcargando.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 133;BA.debugLine="ImgCargando.Gravity = Gravity.FILL";
mostCurrent._imgcargando.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 134;BA.debugLine="Activity.AddView (ImgCargando,  PerXToCurrent(";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._imgcargando.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (40),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA));
 };
 //BA.debugLineNum = 137;BA.debugLine="AniCargando.InitializeRotateCenter( \"AniCargand";
mostCurrent._anicargando.InitializeRotateCenter(mostCurrent.activityBA,"AniCargando",(float) (0),(float) (180),(android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 138;BA.debugLine="AniCargando.Duration = 1000";
mostCurrent._anicargando.setDuration((long) (1000));
 //BA.debugLineNum = 139;BA.debugLine="AniCargando.Duration  =   1000";
mostCurrent._anicargando.setDuration((long) (1000));
 //BA.debugLineNum = 140;BA.debugLine="AniCargando.RepeatCount =5";
mostCurrent._anicargando.setRepeatCount((int) (5));
 //BA.debugLineNum = 141;BA.debugLine="AniCargando.RepeatMode = AniCargando.REPEAT_REV";
mostCurrent._anicargando.setRepeatMode(mostCurrent._anicargando.REPEAT_REVERSE);
 //BA.debugLineNum = 148;BA.debugLine="TestFiles";
_testfiles();
 };
 };
 //BA.debugLineNum = 159;BA.debugLine="End Sub";
return "";
}
public static String  _anicargando_animationend() throws Exception{
 //BA.debugLineNum = 160;BA.debugLine="Sub AniCargando_AnimationEnd";
 //BA.debugLineNum = 163;BA.debugLine="If PaqueteEnviado Then";
if (_paqueteenviado) { 
 //BA.debugLineNum = 164;BA.debugLine="SendTrama(TramaEnviada)";
_sendtrama(_tramaenviada);
 };
 //BA.debugLineNum = 167;BA.debugLine="End Sub";
return "";
}
public static String  _cargasddata() throws Exception{
anywheresoftware.b4a.objects.collections.List _l = null;
int _c = 0;
String _s = "";
int _n = 0;
String _result = "";
anywheresoftware.b4a.objects.collections.List _nl = null;
char _ch = '\0';
int _val = 0;
int _vl = 0;
 //BA.debugLineNum = 418;BA.debugLine="Sub CargaSdData";
 //BA.debugLineNum = 419;BA.debugLine="Dim l As List";
_l = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 420;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 422;BA.debugLine="l=File.ReadList (   File.DirRootExternal   & \"/HT";
_l = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/HT",mostCurrent._valorescomunes._central.Nombre+BA.NumberToString(_currentfecha)+".txt");
 //BA.debugLineNum = 425;BA.debugLine="For C=0 To l.Size -1";
{
final int step4 = 1;
final int limit4 = (int) (_l.getSize()-1);
_c = (int) (0) ;
for (;(step4 > 0 && _c <= limit4) || (step4 < 0 && _c >= limit4) ;_c = ((int)(0 + _c + step4))  ) {
 //BA.debugLineNum = 426;BA.debugLine="mdates.Add (currentFecha)' esto se cambia de pos";
_mdates.Add((Object)(_currentfecha));
 //BA.debugLineNum = 428;BA.debugLine="Dim s As String";
_s = "";
 //BA.debugLineNum = 429;BA.debugLine="Dim n As Int";
_n = 0;
 //BA.debugLineNum = 430;BA.debugLine="Dim result As String";
_result = "";
 //BA.debugLineNum = 431;BA.debugLine="Dim nl As List";
_nl = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 433;BA.debugLine="s= l.Get (C)";
_s = BA.ObjectToString(_l.Get(_c));
 //BA.debugLineNum = 434;BA.debugLine="nl.Initialize";
_nl.Initialize();
 //BA.debugLineNum = 436;BA.debugLine="For n=0 To s.Length -1";
{
final int step12 = 1;
final int limit12 = (int) (_s.length()-1);
_n = (int) (0) ;
for (;(step12 > 0 && _n <= limit12) || (step12 < 0 && _n >= limit12) ;_n = ((int)(0 + _n + step12))  ) {
 //BA.debugLineNum = 437;BA.debugLine="Dim ch As Char";
_ch = '\0';
 //BA.debugLineNum = 438;BA.debugLine="ch= s.CharAt (n)";
_ch = _s.charAt(_n);
 //BA.debugLineNum = 439;BA.debugLine="If IsNumber (ch) Then";
if (anywheresoftware.b4a.keywords.Common.IsNumber(BA.ObjectToString(_ch))) { 
 //BA.debugLineNum = 440;BA.debugLine="result = result & ch";
_result = _result+BA.ObjectToString(_ch);
 }else if(_ch==BA.ObjectToChar(",")) { 
 //BA.debugLineNum = 442;BA.debugLine="Dim val As Int";
_val = 0;
 //BA.debugLineNum = 443;BA.debugLine="val= result";
_val = (int)(Double.parseDouble(_result));
 //BA.debugLineNum = 444;BA.debugLine="result=\"\"";
_result = "";
 //BA.debugLineNum = 445;BA.debugLine="nl.Add (val)";
_nl.Add((Object)(_val));
 };
 }
};
 //BA.debugLineNum = 449;BA.debugLine="Dim vl As Int";
_vl = 0;
 //BA.debugLineNum = 450;BA.debugLine="vl=result";
_vl = (int)(Double.parseDouble(_result));
 //BA.debugLineNum = 451;BA.debugLine="nl.Add (vl)";
_nl.Add((Object)(_vl));
 //BA.debugLineNum = 454;BA.debugLine="Valores.Add (nl)";
_valores.Add((Object)(_nl.getObject()));
 }
};
 //BA.debugLineNum = 458;BA.debugLine="If currentFecha=FechaFin Then";
if (_currentfecha==_fechafin) { 
 //BA.debugLineNum = 459;BA.debugLine="PintaHistorico";
_pintahistorico();
 }else {
 //BA.debugLineNum = 461;BA.debugLine="currentFecha=DateTime.Add(currentFecha ,0,0,1)";
_currentfecha = anywheresoftware.b4a.keywords.Common.DateTime.Add(_currentfecha,(int) (0),(int) (0),(int) (1));
 //BA.debugLineNum = 462;BA.debugLine="TestFiles";
_testfiles();
 };
 //BA.debugLineNum = 467;BA.debugLine="End Sub";
return "";
}
public static int  _getcolor(int _number) throws Exception{
 //BA.debugLineNum = 586;BA.debugLine="Sub GetColor(Number As Int ) As Int";
 //BA.debugLineNum = 587;BA.debugLine="Select Case Number";
switch (_number) {
case 0: {
 //BA.debugLineNum = 590;BA.debugLine="Return Colors.Blue";
if (true) return anywheresoftware.b4a.keywords.Common.Colors.Blue;
 break; }
case 1: {
 //BA.debugLineNum = 592;BA.debugLine="Return Colors.Magenta";
if (true) return anywheresoftware.b4a.keywords.Common.Colors.Magenta;
 break; }
case 2: {
 //BA.debugLineNum = 594;BA.debugLine="Return Colors.Green";
if (true) return anywheresoftware.b4a.keywords.Common.Colors.Green;
 break; }
case 3: {
 //BA.debugLineNum = 596;BA.debugLine="Return Colors.Red";
if (true) return anywheresoftware.b4a.keywords.Common.Colors.Red;
 break; }
case 4: {
 //BA.debugLineNum = 598;BA.debugLine="Return Colors.Yellow";
if (true) return anywheresoftware.b4a.keywords.Common.Colors.Yellow;
 break; }
case 5: {
 //BA.debugLineNum = 600;BA.debugLine="Return Colors.Cyan";
if (true) return anywheresoftware.b4a.keywords.Common.Colors.Cyan;
 break; }
case 6: {
 //BA.debugLineNum = 602;BA.debugLine="Return Colors.White";
if (true) return anywheresoftware.b4a.keywords.Common.Colors.White;
 break; }
}
;
 //BA.debugLineNum = 605;BA.debugLine="Return Colors.Blue";
if (true) return anywheresoftware.b4a.keywords.Common.Colors.Blue;
 //BA.debugLineNum = 607;BA.debugLine="End Sub";
return 0;
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 34;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 41;BA.debugLine="Dim graphview As graph";
mostCurrent._graphview = new com.genesis.graphview.graphview();
 //BA.debugLineNum = 44;BA.debugLine="Dim MaxIndex As Int";
_maxindex = 0;
 //BA.debugLineNum = 45;BA.debugLine="Dim MaxValue As Double";
_maxvalue = 0;
 //BA.debugLineNum = 49;BA.debugLine="Dim ImgCargando As ImageView";
mostCurrent._imgcargando = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Dim AniCargando As  Animation";
mostCurrent._anicargando = new anywheresoftware.b4a.objects.AnimationWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Dim PaqueteEnviado  As Boolean";
_paqueteenviado = false;
 //BA.debugLineNum = 55;BA.debugLine="End Sub";
return "";
}
public static String  _lecturadatos(int _contadorpaquetes) throws Exception{
int _año = 0;
int _mes = 0;
int _dia = 0;
int _longtrama = 0;
byte[] _data = null;
 //BA.debugLineNum = 203;BA.debugLine="Sub LecturaDatos(ContadorPaquetes As Int )";
 //BA.debugLineNum = 207;BA.debugLine="Dim Año, Mes , Dia As Int";
_año = 0;
_mes = 0;
_dia = 0;
 //BA.debugLineNum = 208;BA.debugLine="Año=DateTime.GetYear  (currentFecha)";
_año = anywheresoftware.b4a.keywords.Common.DateTime.GetYear(_currentfecha);
 //BA.debugLineNum = 209;BA.debugLine="Mes=DateTime.GetMonth  (currentFecha)";
_mes = anywheresoftware.b4a.keywords.Common.DateTime.GetMonth(_currentfecha);
 //BA.debugLineNum = 210;BA.debugLine="Dia=DateTime.GetDayOfMonth  (currentFecha)";
_dia = anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfMonth(_currentfecha);
 //BA.debugLineNum = 214;BA.debugLine="Dim LongTrama As Int";
_longtrama = 0;
 //BA.debugLineNum = 216;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 217;BA.debugLine="LongTrama= 16";
_longtrama = (int) (16);
 }else {
 //BA.debugLineNum = 219;BA.debugLine="LongTrama= 8";
_longtrama = (int) (8);
 };
 //BA.debugLineNum = 221;BA.debugLine="Dim data(LongTrama) As Byte";
_data = new byte[_longtrama];
;
 //BA.debugLineNum = 222;BA.debugLine="data(0)=72'H";
_data[(int) (0)] = (byte) (72);
 //BA.debugLineNum = 223;BA.debugLine="data(1)=73'I";
_data[(int) (1)] = (byte) (73);
 //BA.debugLineNum = 224;BA.debugLine="data(2)=83'S";
_data[(int) (2)] = (byte) (83);
 //BA.debugLineNum = 225;BA.debugLine="data(3)=84'T";
_data[(int) (3)] = (byte) (84);
 //BA.debugLineNum = 226;BA.debugLine="data(4)=Año-2000";
_data[(int) (4)] = (byte) (_año-2000);
 //BA.debugLineNum = 227;BA.debugLine="data(5)=Mes";
_data[(int) (5)] = (byte) (_mes);
 //BA.debugLineNum = 228;BA.debugLine="data(6)=Dia";
_data[(int) (6)] = (byte) (_dia);
 //BA.debugLineNum = 229;BA.debugLine="data(7)=ContadorPaquetes";
_data[(int) (7)] = (byte) (_contadorpaquetes);
 //BA.debugLineNum = 231;BA.debugLine="If ValoresComunes.central.ConexionSegura Then Val";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
mostCurrent._valorescomunes._completartrama(mostCurrent.activityBA,_data);};
 //BA.debugLineNum = 232;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 //BA.debugLineNum = 235;BA.debugLine="End Sub";
return "";
}
public static String  _pintahistorico() throws Exception{
com.genesis.graphview.graphview.lineseries[] _series = null;
double[] _horas = null;
int _c = 0;
anywheresoftware.b4a.objects.collections.List _l = null;
String _ho = "";
String _f = "";
boolean _plano = false;
double[] _yy = null;
int _p = 0;
double[] _yp = null;
int _k = 0;
com.genesis.graphview.graphview.lineseries _seriesplana = null;
 //BA.debugLineNum = 295;BA.debugLine="Sub PintaHistorico()";
 //BA.debugLineNum = 296;BA.debugLine="ImgCargando.Visible =False";
mostCurrent._imgcargando.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 297;BA.debugLine="AniCargando.Stop (ImgCargando)";
mostCurrent._anicargando.Stop((android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 300;BA.debugLine="MaxIndex=0";
_maxindex = (int) (0);
 //BA.debugLineNum = 301;BA.debugLine="MaxValue=0";
_maxvalue = 0;
 //BA.debugLineNum = 303;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 305;BA.debugLine="DateTime.DateFormat = \"yyyy-MM-dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd");
 //BA.debugLineNum = 306;BA.debugLine="DateTime.TimeFormat = \"HH:mm\"";
anywheresoftware.b4a.keywords.Common.DateTime.setTimeFormat("HH:mm");
 //BA.debugLineNum = 307;BA.debugLine="Dim series(ValoresComunes.Sensores.Length ) As Li";
_series = new com.genesis.graphview.graphview.lineseries[mostCurrent._valorescomunes._sensores.length];
{
int d0 = _series.length;
for (int i0 = 0;i0 < d0;i0++) {
_series[i0] = new com.genesis.graphview.graphview.lineseries();
}
}
;
 //BA.debugLineNum = 308;BA.debugLine="Dim Horas(Valores.Size) As Double";
_horas = new double[_valores.getSize()];
;
 //BA.debugLineNum = 312;BA.debugLine="graphview.Initialize(\"Charts\")";
mostCurrent._graphview.Initialize(mostCurrent.activityBA,"Charts");
 //BA.debugLineNum = 314;BA.debugLine="graphview.SetHorizontalAxisTitle(\"Time\", Colors.R";
mostCurrent._graphview.SetHorizontalAxisTitle(mostCurrent.activityBA,"Time",anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 316;BA.debugLine="graphview.SetVerticalAxisTitle(\"Value\", Colors.Re";
mostCurrent._graphview.SetVerticalAxisTitle(mostCurrent.activityBA,"Value",anywheresoftware.b4a.keywords.Common.Colors.Red);
 //BA.debugLineNum = 318;BA.debugLine="graphview.SetGraphViewTextSize (20)";
mostCurrent._graphview.SetGraphViewTextSize(mostCurrent.activityBA,(float) (20));
 //BA.debugLineNum = 320;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 323;BA.debugLine="For C=0 To Valores.Size - 1";
{
final int step15 = 1;
final int limit15 = (int) (_valores.getSize()-1);
_c = (int) (0) ;
for (;(step15 > 0 && _c <= limit15) || (step15 < 0 && _c >= limit15) ;_c = ((int)(0 + _c + step15))  ) {
 //BA.debugLineNum = 325;BA.debugLine="Dim l As List";
_l = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 326;BA.debugLine="l= Valores.Get (C)";
_l.setObject((java.util.List)(_valores.Get(_c)));
 //BA.debugLineNum = 329;BA.debugLine="Dim ho As String";
_ho = "";
 //BA.debugLineNum = 336;BA.debugLine="If l.Get (0)<10 Then";
if ((double)(BA.ObjectToNumber(_l.Get((int) (0))))<10) { 
 //BA.debugLineNum = 337;BA.debugLine="ho=  \"0\" & l.Get (0) &\":\"";
_ho = "0"+BA.ObjectToString(_l.Get((int) (0)))+":";
 }else {
 //BA.debugLineNum = 339;BA.debugLine="ho=   l.Get (0) &\":\"";
_ho = BA.ObjectToString(_l.Get((int) (0)))+":";
 };
 //BA.debugLineNum = 341;BA.debugLine="If l.Get (1)<10 Then";
if ((double)(BA.ObjectToNumber(_l.Get((int) (1))))<10) { 
 //BA.debugLineNum = 342;BA.debugLine="ho= ho &  \"0\" & l.Get (1)";
_ho = _ho+"0"+BA.ObjectToString(_l.Get((int) (1)));
 }else {
 //BA.debugLineNum = 344;BA.debugLine="ho=  ho  & l.Get (1)";
_ho = _ho+BA.ObjectToString(_l.Get((int) (1)));
 };
 //BA.debugLineNum = 349;BA.debugLine="Dim f As String";
_f = "";
 //BA.debugLineNum = 350;BA.debugLine="f=DateTime.Date(mdates.Get (C))";
_f = anywheresoftware.b4a.keywords.Common.DateTime.Date(BA.ObjectToLongNumber(_mdates.Get(_c)));
 //BA.debugLineNum = 351;BA.debugLine="Horas(C)= DateTime.DateTimeParse(f , ho)   'conv";
_horas[_c] = anywheresoftware.b4a.keywords.Common.DateTime.DateTimeParse(_f,_ho);
 }
};
 //BA.debugLineNum = 360;BA.debugLine="Dim Plano As Boolean";
_plano = false;
 //BA.debugLineNum = 362;BA.debugLine="Plano =True";
_plano = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 364;BA.debugLine="For C =0 To ValoresComunes.Sensores.Length -1";
{
final int step35 = 1;
final int limit35 = (int) (mostCurrent._valorescomunes._sensores.length-1);
_c = (int) (0) ;
for (;(step35 > 0 && _c <= limit35) || (step35 < 0 && _c >= limit35) ;_c = ((int)(0 + _c + step35))  ) {
 //BA.debugLineNum = 365;BA.debugLine="Dim yy(Valores.Size ) As Double";
_yy = new double[_valores.getSize()];
;
 //BA.debugLineNum = 366;BA.debugLine="If SensorSelect(C)=True Then";
if (_sensorselect[_c]==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 367;BA.debugLine="Dim p As Int";
_p = 0;
 //BA.debugLineNum = 368;BA.debugLine="For p=0 To Valores.Size -1";
{
final int step39 = 1;
final int limit39 = (int) (_valores.getSize()-1);
_p = (int) (0) ;
for (;(step39 > 0 && _p <= limit39) || (step39 < 0 && _p >= limit39) ;_p = ((int)(0 + _p + step39))  ) {
 //BA.debugLineNum = 369;BA.debugLine="Dim l As List";
_l = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 370;BA.debugLine="l= Valores.Get (p)";
_l.setObject((java.util.List)(_valores.Get(_p)));
 //BA.debugLineNum = 372;BA.debugLine="If ValoresComunes.Sensores(C).Rango = 1  Or Va";
if (mostCurrent._valorescomunes._sensores[_c].Rango==1 || mostCurrent._valorescomunes._sensores[_c].Rango==2 || mostCurrent._valorescomunes._sensores[_c].Rango==5) { 
 //BA.debugLineNum = 373;BA.debugLine="yy(p)=l.Get (C +2 )/10";
_yy[_p] = (double)(BA.ObjectToNumber(_l.Get((int) (_c+2))))/(double)10;
 }else {
 //BA.debugLineNum = 376;BA.debugLine="yy(p)=l.Get (C +2 )";
_yy[_p] = (double)(BA.ObjectToNumber(_l.Get((int) (_c+2))));
 };
 //BA.debugLineNum = 382;BA.debugLine="If yy(p)> MaxValue Then";
if (_yy[_p]>_maxvalue) { 
 //BA.debugLineNum = 383;BA.debugLine="If p>0  Then Plano= False";
if (_p>0) { 
_plano = anywheresoftware.b4a.keywords.Common.False;};
 //BA.debugLineNum = 384;BA.debugLine="MaxValue=yy(p)";
_maxvalue = _yy[_p];
 //BA.debugLineNum = 385;BA.debugLine="MaxIndex=C";
_maxindex = _c;
 };
 }
};
 //BA.debugLineNum = 389;BA.debugLine="series(C).Initialize(\"series\", ValoresComunes.S";
_series[_c].Initialize(mostCurrent.activityBA,"series",mostCurrent._valorescomunes._sensores[_c].Nombre,_getcolor(_c),anywheresoftware.b4a.keywords.Common.False,_horas,_yy);
 };
 }
};
 //BA.debugLineNum = 394;BA.debugLine="For C =0 To ValoresComunes.Sensores.Length -1";
{
final int step56 = 1;
final int limit56 = (int) (mostCurrent._valorescomunes._sensores.length-1);
_c = (int) (0) ;
for (;(step56 > 0 && _c <= limit56) || (step56 < 0 && _c >= limit56) ;_c = ((int)(0 + _c + step56))  ) {
 //BA.debugLineNum = 396;BA.debugLine="If C<> MaxIndex And SensorSelect(C)=True Then Th";
if (_c!=_maxindex && _sensorselect[_c]==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 398;BA.debugLine="graphview.AddLineSeries( True  , False, Array A";
mostCurrent._graphview.AddLineSeries(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.False,new com.genesis.graphview.graphview.lineseries[]{_series[_c]});
 };
 }
};
 //BA.debugLineNum = 401;BA.debugLine="graphview.AddLineSeries(True , False, Array As Li";
mostCurrent._graphview.AddLineSeries(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.False,new com.genesis.graphview.graphview.lineseries[]{_series[_maxindex]});
 //BA.debugLineNum = 403;BA.debugLine="If Plano Then";
if (_plano) { 
 //BA.debugLineNum = 404;BA.debugLine="Dim yp(Valores.Size ) As Double";
_yp = new double[_valores.getSize()];
;
 //BA.debugLineNum = 405;BA.debugLine="Dim  k As Int";
_k = 0;
 //BA.debugLineNum = 406;BA.debugLine="For k=0 To Valores.Size -1";
{
final int step65 = 1;
final int limit65 = (int) (_valores.getSize()-1);
_k = (int) (0) ;
for (;(step65 > 0 && _k <= limit65) || (step65 < 0 && _k >= limit65) ;_k = ((int)(0 + _k + step65))  ) {
 //BA.debugLineNum = 407;BA.debugLine="yp(k)=MaxValue + 10";
_yp[_k] = _maxvalue+10;
 }
};
 //BA.debugLineNum = 409;BA.debugLine="Dim seriesPlana As LineSeries";
_seriesplana = new com.genesis.graphview.graphview.lineseries();
 //BA.debugLineNum = 410;BA.debugLine="seriesPlana.Initialize(\"series\",\"Max\" ,  Colors.";
_seriesplana.Initialize(mostCurrent.activityBA,"series","Max",anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.False,_horas,_yp);
 //BA.debugLineNum = 411;BA.debugLine="graphview.AddLineSeries(True , False, Array As L";
mostCurrent._graphview.AddLineSeries(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.False,new com.genesis.graphview.graphview.lineseries[]{_seriesplana});
 };
 //BA.debugLineNum = 415;BA.debugLine="Activity.AddView(graphview, 0, 0, 100%x, 100%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._graphview.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 417;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim SensorSelect(30 ) As   Boolean";
_sensorselect = new boolean[(int) (30)];
;
 //BA.debugLineNum = 10;BA.debugLine="Dim Fecha As Long";
_fecha = 0L;
 //BA.debugLineNum = 11;BA.debugLine="Dim FechaFin As Long";
_fechafin = 0L;
 //BA.debugLineNum = 12;BA.debugLine="Dim UDPSocket1 As UDPSocket";
_udpsocket1 = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket();
 //BA.debugLineNum = 14;BA.debugLine="Dim mdates As List";
_mdates = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 15;BA.debugLine="Dim Iniciando As Boolean";
_iniciando = false;
 //BA.debugLineNum = 17;BA.debugLine="Dim Valores As List";
_valores = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 20;BA.debugLine="Private ph As Phone 'Para controlar horientación";
_ph = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 21;BA.debugLine="Dim pw As PhoneWakeState 'Controlar suspension pa";
_pw = new anywheresoftware.b4a.phone.Phone.PhoneWakeState();
 //BA.debugLineNum = 25;BA.debugLine="Dim TempValores As List";
_tempvalores = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 26;BA.debugLine="Dim currentFecha As Long";
_currentfecha = 0L;
 //BA.debugLineNum = 27;BA.debugLine="Dim TramaEnviada() As Byte";
_tramaenviada = new byte[(int) (0)];
;
 //BA.debugLineNum = 28;BA.debugLine="Dim NumeroEnvio As Int";
_numeroenvio = 0;
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return "";
}
public static boolean  _sendingtrama(byte[] _data) throws Exception{
anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet = null;
 //BA.debugLineNum = 285;BA.debugLine="Sub SendingTrama (data() As Byte) As Boolean";
 //BA.debugLineNum = 286;BA.debugLine="Try";
try { //BA.debugLineNum = 287;BA.debugLine="Dim Packet As UDPPacket";
_packet = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket();
 //BA.debugLineNum = 288;BA.debugLine="Packet.Initialize(data, ValoresComunes.ip, Valor";
_packet.Initialize(_data,mostCurrent._valorescomunes._ip,mostCurrent._valorescomunes._puerto);
 //BA.debugLineNum = 289;BA.debugLine="UDPSocket1.Send(Packet)";
_udpsocket1.Send(_packet);
 //BA.debugLineNum = 290;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 292;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 294;BA.debugLine="End Sub";
return false;
}
public static void  _sendtrama(byte[] _data) throws Exception{
ResumableSub_SendTrama rsub = new ResumableSub_SendTrama(null,_data);
rsub.resume(processBA, null);
}
public static class ResumableSub_SendTrama extends BA.ResumableSub {
public ResumableSub_SendTrama(arduino.automatizacion.excontrol.PRO.acthistorico parent,byte[] _data) {
this.parent = parent;
this._data = _data;
}
arduino.automatizacion.excontrol.PRO.acthistorico parent;
byte[] _data;
boolean _resultado = false;
int _reintento = 0;
anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper _serversocket1 = null;
String _myip = "";

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
                return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 237;BA.debugLine="Dim Resultado As Boolean";
_resultado = false;
 //BA.debugLineNum = 238;BA.debugLine="Dim Reintento As Int";
_reintento = 0;
 //BA.debugLineNum = 241;BA.debugLine="TramaEnviada= data";
parent._tramaenviada = _data;
 //BA.debugLineNum = 242;BA.debugLine="If ImgCargando.Visible =False Then";
if (true) break;

case 1:
//if
this.state = 4;
if (parent.mostCurrent._imgcargando.getVisible()==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 243;BA.debugLine="ImgCargando.Visible=True";
parent.mostCurrent._imgcargando.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 244;BA.debugLine="AniCargando.Start (ImgCargando)";
parent.mostCurrent._anicargando.Start((android.view.View)(parent.mostCurrent._imgcargando.getObject()));
 if (true) break;
;
 //BA.debugLineNum = 249;BA.debugLine="Do 	While Resultado= False And Reintento < 40";

case 4:
//do while
this.state = 18;
while (_resultado==anywheresoftware.b4a.keywords.Common.False && _reintento<40) {
this.state = 6;
if (true) break;
}
if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 250;BA.debugLine="Dim ServerSocket1 As ServerSocket";
_serversocket1 = new anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper();
 //BA.debugLineNum = 252;BA.debugLine="Dim MyIp As String";
_myip = "";
 //BA.debugLineNum = 253;BA.debugLine="If ActMosaico.Forzar3g Then";
if (true) break;

case 7:
//if
this.state = 12;
if (parent.mostCurrent._actmosaico._forzar3g) { 
this.state = 9;
}else {
this.state = 11;
}if (true) break;

case 9:
//C
this.state = 12;
 //BA.debugLineNum = 254;BA.debugLine="MyIp=ServerSocket1.GetMyIP";
_myip = _serversocket1.GetMyIP();
 if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 256;BA.debugLine="MyIp=ServerSocket1.GetMyWifiIP";
_myip = _serversocket1.GetMyWifiIP();
 if (true) break;
;
 //BA.debugLineNum = 258;BA.debugLine="If MyIp  <> \"127.0.0.1\" Then";

case 12:
//if
this.state = 17;
if ((_myip).equals("127.0.0.1") == false) { 
this.state = 14;
}else {
this.state = 16;
}if (true) break;

case 14:
//C
this.state = 17;
 //BA.debugLineNum = 259;BA.debugLine="Resultado = True";
_resultado = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 16:
//C
this.state = 17;
 //BA.debugLineNum = 261;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 262;BA.debugLine="Sleep (200)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (200));
this.state = 37;
return;
case 37:
//C
this.state = 17;
;
 if (true) break;

case 17:
//C
this.state = 4;
;
 if (true) break;
;
 //BA.debugLineNum = 266;BA.debugLine="If Resultado = True Then";

case 18:
//if
this.state = 31;
if (_resultado==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 20;
}if (true) break;

case 20:
//C
this.state = 21;
 //BA.debugLineNum = 267;BA.debugLine="Resultado =False";
_resultado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 268;BA.debugLine="Reintento =0";
_reintento = (int) (0);
 //BA.debugLineNum = 269;BA.debugLine="Do 	While Resultado= False And Reintento < 40";
if (true) break;

case 21:
//do while
this.state = 30;
while (_resultado==anywheresoftware.b4a.keywords.Common.False && _reintento<40) {
this.state = 23;
if (true) break;
}
if (true) break;

case 23:
//C
this.state = 24;
 //BA.debugLineNum = 270;BA.debugLine="Resultado = SendingTrama(data)";
_resultado = _sendingtrama(_data);
 //BA.debugLineNum = 271;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 272;BA.debugLine="If Resultado=False Then Sleep(200)";
if (true) break;

case 24:
//if
this.state = 29;
if (_resultado==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 26;
;}if (true) break;

case 26:
//C
this.state = 29;
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (200));
this.state = 38;
return;
case 38:
//C
this.state = 29;
;
if (true) break;

case 29:
//C
this.state = 21;
;
 if (true) break;

case 30:
//C
this.state = 31;
;
 if (true) break;
;
 //BA.debugLineNum = 277;BA.debugLine="If Resultado = False  Then";

case 31:
//if
this.state = 36;
if (_resultado==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 33;
}else {
this.state = 35;
}if (true) break;

case 33:
//C
this.state = 36;
 //BA.debugLineNum = 278;BA.debugLine="ActMosaico.Conectado =False";
parent.mostCurrent._actmosaico._conectado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 279;BA.debugLine="StartActivity(ActMosaico)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._actmosaico.getObject()));
 if (true) break;

case 35:
//C
this.state = 36;
 //BA.debugLineNum = 281;BA.debugLine="PaqueteEnviado=True";
parent._paqueteenviado = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 282;BA.debugLine="AniCargando.Start (ImgCargando)";
parent.mostCurrent._anicargando.Start((android.view.View)(parent.mostCurrent._imgcargando.getObject()));
 if (true) break;

case 36:
//C
this.state = -1;
;
 //BA.debugLineNum = 284;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _series_seriesclicked(String _xdata,String _ydata) throws Exception{
String _h = "";
 //BA.debugLineNum = 184;BA.debugLine="Sub series_SeriesClicked (xdata As String, ydata A";
 //BA.debugLineNum = 185;BA.debugLine="Dim h As String";
_h = "";
 //BA.debugLineNum = 186;BA.debugLine="h=DateTime.GetMinute(xdata)";
_h = BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.GetMinute((long)(Double.parseDouble(_xdata))));
 //BA.debugLineNum = 187;BA.debugLine="If h.Length =1 Then";
if (_h.length()==1) { 
 //BA.debugLineNum = 188;BA.debugLine="h=\":0\" & h";
_h = ":0"+_h;
 }else {
 //BA.debugLineNum = 190;BA.debugLine="h=\":\" & h";
_h = ":"+_h;
 };
 //BA.debugLineNum = 193;BA.debugLine="h= DateTime.GetHour  (xdata) & h";
_h = BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.GetHour((long)(Double.parseDouble(_xdata))))+_h;
 //BA.debugLineNum = 194;BA.debugLine="h= DateTime.Date (xdata)& Chr(10)  &  h";
_h = anywheresoftware.b4a.keywords.Common.DateTime.Date((long)(Double.parseDouble(_xdata)))+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (10)))+_h;
 //BA.debugLineNum = 197;BA.debugLine="ToastMessageShow(h & Chr(10) & \"Value = \" & ydata";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(_h+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (10)))+"Value = "+_ydata),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 201;BA.debugLine="End Sub";
return "";
}
public static String  _testfiles() throws Exception{
 //BA.debugLineNum = 469;BA.debugLine="Sub TestFiles";
 //BA.debugLineNum = 471;BA.debugLine="If File.Exists (File.DirRootExternal    & \"/HT\" ,";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/HT",mostCurrent._valorescomunes._central.Nombre+BA.NumberToString(_currentfecha)+".txt")) { 
 //BA.debugLineNum = 472;BA.debugLine="CargaSdData";
_cargasddata();
 }else {
 //BA.debugLineNum = 475;BA.debugLine="TempValores.Clear";
_tempvalores.Clear();
 //BA.debugLineNum = 476;BA.debugLine="NumeroEnvio=1";
_numeroenvio = (int) (1);
 //BA.debugLineNum = 477;BA.debugLine="LecturaDatos(NumeroEnvio)";
_lecturadatos(_numeroenvio);
 };
 //BA.debugLineNum = 485;BA.debugLine="End Sub";
return "";
}
public static String  _udp_packetarrived(anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet) throws Exception{
String _msg = "";
int _c = 0;
int _s = 0;
anywheresoftware.b4a.objects.collections.List _val = null;
int _k = 0;
short _b1 = (short)0;
short _b2 = (short)0;
 //BA.debugLineNum = 486;BA.debugLine="Sub UDP_PacketArrived (Packet As UDPPacket)";
 //BA.debugLineNum = 487;BA.debugLine="Try";
try { //BA.debugLineNum = 490;BA.debugLine="Dim msg As String";
_msg = "";
 //BA.debugLineNum = 491;BA.debugLine="msg = BytesToString(Packet.data, Packet.Offset,";
_msg = anywheresoftware.b4a.keywords.Common.BytesToString(_packet.getData(),_packet.getOffset(),_packet.getLength(),"UTF8");
 //BA.debugLineNum = 494;BA.debugLine="If msg.StartsWith  (\"HT\") Then";
if (_msg.startsWith("HT")) { 
 //BA.debugLineNum = 495;BA.debugLine="PaqueteEnviado=False";
_paqueteenviado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 498;BA.debugLine="Dim C,S As Int";
_c = 0;
_s = 0;
 //BA.debugLineNum = 499;BA.debugLine="C=4";
_c = (int) (4);
 //BA.debugLineNum = 500;BA.debugLine="Dim Val As List";
_val = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 501;BA.debugLine="Val.Initialize";
_val.Initialize();
 //BA.debugLineNum = 502;BA.debugLine="Val.Add (Packet.data (2)-1)'HORA";
_val.Add((Object)(_packet.getData()[(int) (2)]-1));
 //BA.debugLineNum = 503;BA.debugLine="Val.Add (Packet.data (3)-1)'MINUTO";
_val.Add((Object)(_packet.getData()[(int) (3)]-1));
 //BA.debugLineNum = 506;BA.debugLine="If Val.Get (0)<0 Or Val.Get (1)<0 Then  'Carga";
if ((double)(BA.ObjectToNumber(_val.Get((int) (0))))<0 || (double)(BA.ObjectToNumber(_val.Get((int) (1))))<0) { 
 //BA.debugLineNum = 507;BA.debugLine="If DateTime.Date(currentFecha)=  DateTime.Date";
if ((anywheresoftware.b4a.keywords.Common.DateTime.Date(_currentfecha)).equals(anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))) { 
 //BA.debugLineNum = 508;BA.debugLine="Dim k As Int";
_k = 0;
 //BA.debugLineNum = 509;BA.debugLine="For k=0 To TempValores.Size -1";
{
final int step15 = 1;
final int limit15 = (int) (_tempvalores.getSize()-1);
_k = (int) (0) ;
for (;(step15 > 0 && _k <= limit15) || (step15 < 0 && _k >= limit15) ;_k = ((int)(0 + _k + step15))  ) {
 //BA.debugLineNum = 510;BA.debugLine="mdates.Add (currentFecha)' esto se cambia de";
_mdates.Add((Object)(_currentfecha));
 //BA.debugLineNum = 511;BA.debugLine="Valores.Add (TempValores.Get (k))";
_valores.Add(_tempvalores.Get(_k));
 }
};
 //BA.debugLineNum = 513;BA.debugLine="PintaHistorico";
_pintahistorico();
 //BA.debugLineNum = 514;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 516;BA.debugLine="File.WriteList ( File.DirRootExternal  & \"/HT";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal()+"/HT",mostCurrent._valorescomunes._central.Nombre+BA.NumberToString(_currentfecha)+".txt",_tempvalores);
 //BA.debugLineNum = 517;BA.debugLine="TestFiles";
_testfiles();
 //BA.debugLineNum = 518;BA.debugLine="Return";
if (true) return "";
 };
 };
 //BA.debugLineNum = 541;BA.debugLine="Do While C<  Packet.Length";
while (_c<_packet.getLength()) {
 //BA.debugLineNum = 544;BA.debugLine="Dim B1 As Short";
_b1 = (short)0;
 //BA.debugLineNum = 545;BA.debugLine="Dim B2 As Short";
_b2 = (short)0;
 //BA.debugLineNum = 548;BA.debugLine="B1= Bit.And (Packet.data (C) , 0xff)";
_b1 = (short) (anywheresoftware.b4a.keywords.Common.Bit.And((int) (_packet.getData()[_c]),(int) (0xff)));
 //BA.debugLineNum = 549;BA.debugLine="If B1<255 Then B1=B1-1";
if (_b1<255) { 
_b1 = (short) (_b1-1);};
 //BA.debugLineNum = 552;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 554;BA.debugLine="B2=Bit.And (Packet.data (C) , 0xff)";
_b2 = (short) (anywheresoftware.b4a.keywords.Common.Bit.And((int) (_packet.getData()[_c]),(int) (0xff)));
 //BA.debugLineNum = 555;BA.debugLine="If B2<255 Then B2=B2-1";
if (_b2<255) { 
_b2 = (short) (_b2-1);};
 //BA.debugLineNum = 556;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 557;BA.debugLine="B1= Bit.Or(Bit.ShiftLeft ( B2,8),B1)";
_b1 = (short) (anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Bit.ShiftLeft((int) (_b2),(int) (8)),(int) (_b1)));
 //BA.debugLineNum = 559;BA.debugLine="Val.Add (B1)";
_val.Add((Object)(_b1));
 //BA.debugLineNum = 564;BA.debugLine="S=S+1";
_s = (int) (_s+1);
 }
;
 //BA.debugLineNum = 570;BA.debugLine="TempValores.Add (Val)";
_tempvalores.Add((Object)(_val.getObject()));
 //BA.debugLineNum = 571;BA.debugLine="NumeroEnvio=NumeroEnvio+1";
_numeroenvio = (int) (_numeroenvio+1);
 //BA.debugLineNum = 572;BA.debugLine="LecturaDatos(NumeroEnvio)";
_lecturadatos(_numeroenvio);
 }else if(_msg.contains("NOFOUND")) { 
 //BA.debugLineNum = 575;BA.debugLine="Msgbox(\"NO CONFIGURADO\",\"\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("NO CONFIGURADO"),BA.ObjectToCharSequence(""),mostCurrent.activityBA);
 //BA.debugLineNum = 576;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 } 
       catch (Exception e48) {
			processBA.setLastException(e48); };
 //BA.debugLineNum = 585;BA.debugLine="End Sub";
return "";
}
}
