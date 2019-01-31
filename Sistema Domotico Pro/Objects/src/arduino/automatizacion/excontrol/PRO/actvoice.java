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

public class actvoice extends Activity implements B4AActivity{
	public static actvoice mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actvoice");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actvoice).");
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
		activityBA = new BA(this, layout, processBA, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actvoice");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "arduino.automatizacion.excontrol.PRO.actvoice", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actvoice) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actvoice) Resume **");
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
		return actvoice.class;
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
        BA.LogInfo("** Activity (actvoice) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (actvoice) Resume **");
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
public static anywheresoftware.b4a.phone.Phone.VoiceRecognition _vr = null;
public static anywheresoftware.b4a.obejcts.TTS _tts1 = null;
public static anywheresoftware.b4a.objects.SocketWrapper.UDPSocket _udpsocket1 = null;
public static byte[] _tramaenviada = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgcargando = null;
public anywheresoftware.b4a.objects.AnimationWrapper _anicargando = null;
public static boolean _paqueteenviado = false;
public anywheresoftware.b4a.objects.collections.List _configuraciones = null;
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

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_click() throws Exception{
 //BA.debugLineNum = 381;BA.debugLine="Sub Activity_Click";
 //BA.debugLineNum = 382;BA.debugLine="VR.Listen 'calls the voice recognition external";
_vr.Listen(processBA);
 //BA.debugLineNum = 383;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
String _s = "";
anywheresoftware.b4a.objects.IntentWrapper _i = null;
 //BA.debugLineNum = 36;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 37;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = Fa";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 40;BA.debugLine="If  File.Exists ( File.DirInternal ,\"ConfigIdioma";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigIdioma")==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._valorescomunes._creaarchivoconfigidioma(mostCurrent.activityBA);};
 //BA.debugLineNum = 41;BA.debugLine="Configuraciones = File.ReadList(File.DirInternal";
mostCurrent._configuraciones = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"ConfigIdioma");
 //BA.debugLineNum = 43;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 44;BA.debugLine="VR.Initialize(\"VR\")";
_vr.Initialize("VR");
 //BA.debugLineNum = 45;BA.debugLine="TTS1.Initialize(\"TTS1\")";
_tts1.Initialize(processBA,"TTS1");
 };
 //BA.debugLineNum = 47;BA.debugLine="If VR.IsSupported Then";
if (_vr.IsSupported()) { 
 //BA.debugLineNum = 48;BA.debugLine="ToastMessageShow(ValoresComunes.GetLanString (\"r";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg169")),anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 51;BA.debugLine="ToastMessageShow(ValoresComunes.GetLanString (\"r";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg170")),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 52;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 64;BA.debugLine="Dim S As String";
_s = "";
 //BA.debugLineNum = 65;BA.debugLine="S= Configuraciones.Get (0)";
_s = BA.ObjectToString(mostCurrent._configuraciones.Get((int) (0)));
 //BA.debugLineNum = 67;BA.debugLine="TTS1.SetLanguage(S, S.ToUpperCase   )";
_tts1.SetLanguage(_s,_s.toUpperCase());
 //BA.debugLineNum = 68;BA.debugLine="VR.Language=S";
_vr.setLanguage(_s);
 //BA.debugLineNum = 69;BA.debugLine="VR.Prompt =ValoresComunes.GetLanString (\"reg171\")";
_vr.setPrompt(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg171"));
 //BA.debugLineNum = 71;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 72;BA.debugLine="i.Initialize(\"android.speech.action.RECOGNIZE_SPE";
_i.Initialize("android.speech.action.RECOGNIZE_SPEECH","");
 //BA.debugLineNum = 73;BA.debugLine="i.PutExtra(\"android.speech.extras.SPEECH_INPUT_PO";
_i.PutExtra("android.speech.extras.SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS",(Object)(400));
 //BA.debugLineNum = 75;BA.debugLine="VR.Listen2(i)";
_vr.Listen2(processBA,(android.content.Intent)(_i.getObject()));
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 28;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 29;BA.debugLine="If UserClosed Then";
if (_userclosed) { 
 //BA.debugLineNum = 31;BA.debugLine="UDPSocket1.Close";
_udpsocket1.Close();
 //BA.debugLineNum = 32;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 84;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 85;BA.debugLine="If ValoresComunes.CloseApp =True Then";
if (mostCurrent._valorescomunes._closeapp==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 86;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 87;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 93;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = True";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 94;BA.debugLine="If Main.MyLan.IsInitialized = False Then Main.My";
if (mostCurrent._main._mylan.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._main._mylan.Initialize(processBA,(int) (0),"");};
 //BA.debugLineNum = 95;BA.debugLine="If UDPSocket1.IsInitialized = False Then  Valore";
if (_udpsocket1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._valorescomunes._iniudp(mostCurrent.activityBA,_udpsocket1);};
 //BA.debugLineNum = 97;BA.debugLine="If ImgCargando.IsInitialized = False Then";
if (mostCurrent._imgcargando.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 98;BA.debugLine="ImgCargando.Initialize (\"ImgCargando\")";
mostCurrent._imgcargando.Initialize(mostCurrent.activityBA,"ImgCargando");
 //BA.debugLineNum = 99;BA.debugLine="ImgCargando.Bitmap = ValoresComunes.Cargando";
mostCurrent._imgcargando.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._cargando(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 100;BA.debugLine="ImgCargando.Visible =False";
mostCurrent._imgcargando.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 101;BA.debugLine="ImgCargando.Gravity = Gravity.FILL";
mostCurrent._imgcargando.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 102;BA.debugLine="Activity.AddView (ImgCargando,  PerXToCurrent(4";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._imgcargando.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (40),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA));
 };
 //BA.debugLineNum = 106;BA.debugLine="AniCargando.InitializeRotateCenter( \"AniCargando";
mostCurrent._anicargando.InitializeRotateCenter(mostCurrent.activityBA,"AniCargando",(float) (0),(float) (180),(android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 107;BA.debugLine="AniCargando.Duration = 1000";
mostCurrent._anicargando.setDuration((long) (1000));
 //BA.debugLineNum = 108;BA.debugLine="AniCargando.RepeatCount =5";
mostCurrent._anicargando.setRepeatCount((int) (5));
 //BA.debugLineNum = 109;BA.debugLine="AniCargando.RepeatMode = AniCargando.REPEAT_R";
mostCurrent._anicargando.setRepeatMode(mostCurrent._anicargando.REPEAT_REVERSE);
 }else {
 //BA.debugLineNum = 114;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 115;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 };
 //BA.debugLineNum = 117;BA.debugLine="End Sub";
return "";
}
public static String  _adaptarcomando(String _comando) throws Exception{
 //BA.debugLineNum = 135;BA.debugLine="Sub AdaptarComando(Comando As String ) As String";
 //BA.debugLineNum = 136;BA.debugLine="Comando = Comando.ToUpperCase";
_comando = _comando.toUpperCase();
 //BA.debugLineNum = 138;BA.debugLine="Comando = Comando.Replace (\"Á\",\"A\")";
_comando = _comando.replace("Á","A");
 //BA.debugLineNum = 139;BA.debugLine="Comando = Comando.Replace (\"É\",\"E\")";
_comando = _comando.replace("É","E");
 //BA.debugLineNum = 140;BA.debugLine="Comando = Comando.Replace (\"Í\",\"I\")";
_comando = _comando.replace("Í","I");
 //BA.debugLineNum = 141;BA.debugLine="Comando = Comando.Replace (\"Ó\",\"O\")";
_comando = _comando.replace("Ó","O");
 //BA.debugLineNum = 142;BA.debugLine="Comando = Comando.Replace (\"Ú\",\"U\")";
_comando = _comando.replace("Ú","U");
 //BA.debugLineNum = 144;BA.debugLine="Return Comando";
if (true) return _comando;
 //BA.debugLineNum = 145;BA.debugLine="End Sub";
return "";
}
public static String  _anicargando_animationend() throws Exception{
 //BA.debugLineNum = 77;BA.debugLine="Sub AniCargando_AnimationEnd";
 //BA.debugLineNum = 79;BA.debugLine="If PaqueteEnviado = True Then";
if (_paqueteenviado==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 80;BA.debugLine="AniCargando.Start (ImgCargando)";
mostCurrent._anicargando.Start((android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 81;BA.debugLine="SendTrama(TramaEnviada)";
_sendtrama(_tramaenviada);
 };
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 18;BA.debugLine="Dim TramaEnviada() As Byte";
_tramaenviada = new byte[(int) (0)];
;
 //BA.debugLineNum = 19;BA.debugLine="Dim ImgCargando As ImageView";
mostCurrent._imgcargando = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Dim AniCargando As  Animation";
mostCurrent._anicargando = new anywheresoftware.b4a.objects.AnimationWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Dim PaqueteEnviado As Boolean";
_paqueteenviado = false;
 //BA.debugLineNum = 24;BA.debugLine="Dim Configuraciones As List";
mostCurrent._configuraciones = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public static String  _mostranosereconoce() throws Exception{
 //BA.debugLineNum = 128;BA.debugLine="Sub MostraNoSeReconoce";
 //BA.debugLineNum = 130;BA.debugLine="TTS1.Speak( ValoresComunes.GetLanString (\"reg172\"";
_tts1.Speak(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg172"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 131;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 134;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 8;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim VR As VoiceRecognition";
_vr = new anywheresoftware.b4a.phone.Phone.VoiceRecognition();
 //BA.debugLineNum = 10;BA.debugLine="Dim TTS1 As TTS";
_tts1 = new anywheresoftware.b4a.obejcts.TTS();
 //BA.debugLineNum = 11;BA.debugLine="Dim UDPSocket1 As UDPSocket";
_udpsocket1 = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket();
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _reconocercomando(String _comando) throws Exception{
int _pos = 0;
String _name = "";
int _longtrama = 0;
byte[] _data = null;
arduino.automatizacion.excontrol.PRO.valorescomunes._scene _cmd = null;
arduino.automatizacion.excontrol.PRO.valorescomunes._circuito _cir = null;
int _newval = 0;
anywheresoftware.b4a.objects.collections.List _lstcomand = null;
int _ncmd = 0;
 //BA.debugLineNum = 146;BA.debugLine="Sub ReconocerComando(Comando As String)";
 //BA.debugLineNum = 147;BA.debugLine="ToastMessageShow(Comando, True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(_comando),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 149;BA.debugLine="If Comando.Contains (Configuraciones.Get (1)) Th";
if (_comando.contains(BA.ObjectToString(mostCurrent._configuraciones.Get((int) (1))))) { 
 //BA.debugLineNum = 150;BA.debugLine="Dim pos As Int";
_pos = 0;
 //BA.debugLineNum = 151;BA.debugLine="For pos =0 To 9";
{
final int step4 = 1;
final int limit4 = (int) (9);
_pos = (int) (0) ;
for (;(step4 > 0 && _pos <= limit4) || (step4 < 0 && _pos >= limit4) ;_pos = ((int)(0 + _pos + step4))  ) {
 //BA.debugLineNum = 152;BA.debugLine="Dim Name As String";
_name = "";
 //BA.debugLineNum = 153;BA.debugLine="Name = AdaptarComando(ValoresComunes.Scenes (p";
_name = _adaptarcomando(mostCurrent._valorescomunes._scenes[_pos].Nombre);
 //BA.debugLineNum = 154;BA.debugLine="If Comando.Contains (Name) Then";
if (_comando.contains(_name)) { 
 //BA.debugLineNum = 155;BA.debugLine="Dim LongTrama As Int";
_longtrama = 0;
 //BA.debugLineNum = 156;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 157;BA.debugLine="LongTrama= 13";
_longtrama = (int) (13);
 }else {
 //BA.debugLineNum = 159;BA.debugLine="LongTrama= 5";
_longtrama = (int) (5);
 };
 //BA.debugLineNum = 161;BA.debugLine="Dim data(LongTrama) As Byte";
_data = new byte[_longtrama];
;
 //BA.debugLineNum = 162;BA.debugLine="data(0)=83";
_data[(int) (0)] = (byte) (83);
 //BA.debugLineNum = 163;BA.debugLine="data(1)=83";
_data[(int) (1)] = (byte) (83);
 //BA.debugLineNum = 164;BA.debugLine="data(2)=67";
_data[(int) (2)] = (byte) (67);
 //BA.debugLineNum = 165;BA.debugLine="data(3)=69";
_data[(int) (3)] = (byte) (69);
 //BA.debugLineNum = 166;BA.debugLine="data(4)= pos +1";
_data[(int) (4)] = (byte) (_pos+1);
 //BA.debugLineNum = 167;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
mostCurrent._valorescomunes._completartrama(mostCurrent.activityBA,_data);};
 //BA.debugLineNum = 168;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 //BA.debugLineNum = 171;BA.debugLine="Return";
if (true) return "";
 };
 }
};
 };
 //BA.debugLineNum = 178;BA.debugLine="Dim pos As Int";
_pos = 0;
 //BA.debugLineNum = 179;BA.debugLine="For pos =0 To ValoresComunes.Comandos.Size -1";
{
final int step27 = 1;
final int limit27 = (int) (mostCurrent._valorescomunes._comandos.getSize()-1);
_pos = (int) (0) ;
for (;(step27 > 0 && _pos <= limit27) || (step27 < 0 && _pos >= limit27) ;_pos = ((int)(0 + _pos + step27))  ) {
 //BA.debugLineNum = 180;BA.debugLine="Dim Cmd As Scene";
_cmd = new arduino.automatizacion.excontrol.PRO.valorescomunes._scene();
 //BA.debugLineNum = 181;BA.debugLine="Cmd = ValoresComunes.Comandos.Get  (pos)";
_cmd = (arduino.automatizacion.excontrol.PRO.valorescomunes._scene)(mostCurrent._valorescomunes._comandos.Get(_pos));
 //BA.debugLineNum = 182;BA.debugLine="If Comando.Contains (AdaptarComando(Cmd.Nombre";
if (_comando.contains(_adaptarcomando(_cmd.Nombre))) { 
 //BA.debugLineNum = 183;BA.debugLine="Dim LongTrama As Int";
_longtrama = 0;
 //BA.debugLineNum = 184;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 185;BA.debugLine="LongTrama= 16";
_longtrama = (int) (16);
 }else {
 //BA.debugLineNum = 187;BA.debugLine="LongTrama= 8";
_longtrama = (int) (8);
 };
 //BA.debugLineNum = 189;BA.debugLine="Dim data(LongTrama) As Byte";
_data = new byte[_longtrama];
;
 //BA.debugLineNum = 190;BA.debugLine="data(0)=67	'C";
_data[(int) (0)] = (byte) (67);
 //BA.debugLineNum = 191;BA.debugLine="data(1)=79	'O";
_data[(int) (1)] = (byte) (79);
 //BA.debugLineNum = 192;BA.debugLine="data(2)=77	'M";
_data[(int) (2)] = (byte) (77);
 //BA.debugLineNum = 193;BA.debugLine="data(3)=65	'A";
_data[(int) (3)] = (byte) (65);
 //BA.debugLineNum = 194;BA.debugLine="data(4)=78	'N";
_data[(int) (4)] = (byte) (78);
 //BA.debugLineNum = 195;BA.debugLine="data(5)=68	'D";
_data[(int) (5)] = (byte) (68);
 //BA.debugLineNum = 196;BA.debugLine="data(6)=79	'O";
_data[(int) (6)] = (byte) (79);
 //BA.debugLineNum = 197;BA.debugLine="data(7)=pos + 1";
_data[(int) (7)] = (byte) (_pos+1);
 //BA.debugLineNum = 198;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
mostCurrent._valorescomunes._completartrama(mostCurrent.activityBA,_data);};
 //BA.debugLineNum = 199;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 //BA.debugLineNum = 201;BA.debugLine="Return";
if (true) return "";
 };
 }
};
 //BA.debugLineNum = 207;BA.debugLine="Dim cir As Circuito";
_cir = new arduino.automatizacion.excontrol.PRO.valorescomunes._circuito();
 //BA.debugLineNum = 208;BA.debugLine="Dim pos As Int";
_pos = 0;
 //BA.debugLineNum = 209;BA.debugLine="Dim NewVal As Int";
_newval = 0;
 //BA.debugLineNum = 210;BA.debugLine="NewVal = -1";
_newval = (int) (-1);
 //BA.debugLineNum = 212;BA.debugLine="For pos =0 To ValoresComunes.Circuitos .Length -";
{
final int step55 = 1;
final int limit55 = (int) (mostCurrent._valorescomunes._circuitos.length-1);
_pos = (int) (0) ;
for (;(step55 > 0 && _pos <= limit55) || (step55 < 0 && _pos >= limit55) ;_pos = ((int)(0 + _pos + step55))  ) {
 //BA.debugLineNum = 213;BA.debugLine="cir = ValoresComunes.Circuitos(pos)";
_cir = mostCurrent._valorescomunes._circuitos[_pos];
 //BA.debugLineNum = 215;BA.debugLine="Dim LstComand As List";
_lstcomand = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 216;BA.debugLine="LstComand.Initialize";
_lstcomand.Initialize();
 //BA.debugLineNum = 217;BA.debugLine="Dim Name As String";
_name = "";
 //BA.debugLineNum = 218;BA.debugLine="Name = AdaptarComando(cir.Nombre)";
_name = _adaptarcomando(_cir.Nombre);
 //BA.debugLineNum = 219;BA.debugLine="If Name<>\"\" Then";
if ((_name).equals("") == false) { 
 //BA.debugLineNum = 220;BA.debugLine="LstComand.Add (Name)";
_lstcomand.Add((Object)(_name));
 //BA.debugLineNum = 222;BA.debugLine="If Name.Contains (\"ADO.\") Then LstComand.Add (";
if (_name.contains("ADO.")) { 
_lstcomand.Add((Object)(_name.replace("ADO.",mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg173"))));};
 //BA.debugLineNum = 223;BA.debugLine="If Name.Contains (\"TV.\") Then	LstComand.Add (N";
if (_name.contains("TV.")) { 
_lstcomand.Add((Object)(_name.replace("TV.",mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg174"))));};
 //BA.debugLineNum = 224;BA.debugLine="If Name.Contains (\"A.C.\") Then	LstComand.Add (";
if (_name.contains("A.C.")) { 
_lstcomand.Add((Object)(_name.replace("A.C.",mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg175"))));};
 //BA.debugLineNum = 226;BA.debugLine="Dim ncmd As Int";
_ncmd = 0;
 //BA.debugLineNum = 227;BA.debugLine="For ncmd =0 To LstComand.Size-1";
{
final int step67 = 1;
final int limit67 = (int) (_lstcomand.getSize()-1);
_ncmd = (int) (0) ;
for (;(step67 > 0 && _ncmd <= limit67) || (step67 < 0 && _ncmd >= limit67) ;_ncmd = ((int)(0 + _ncmd + step67))  ) {
 //BA.debugLineNum = 229;BA.debugLine="If Comando.Contains(LstComand.Get (ncmd) ) Th";
if (_comando.contains(BA.ObjectToString(_lstcomand.Get(_ncmd)))) { 
 //BA.debugLineNum = 232;BA.debugLine="Select  cir.Rango";
switch (BA.switchObjectToInt(_cir.Rango,(int) (2),(int) (1),(int) (7),(int) (8),(int) (13),(int) (4),(int) (19),(int) (24),(int) (39),(int) (15),(int) (25),(int) (43),(int) (51),(int) (20),(int) (21),(int) (22),(int) (34),(int) (35))) {
case 0: {
 //BA.debugLineNum = 235;BA.debugLine="If Comando.Contains (Configuraciones.Get (2)";
if (_comando.contains(BA.ObjectToString(mostCurrent._configuraciones.Get((int) (2))))) { 
 //BA.debugLineNum = 236;BA.debugLine="NewVal=3";
_newval = (int) (3);
 };
 //BA.debugLineNum = 238;BA.debugLine="If Comando.Contains (Configuraciones.Get (3)";
if (_comando.contains(BA.ObjectToString(mostCurrent._configuraciones.Get((int) (3))))) { 
 //BA.debugLineNum = 239;BA.debugLine="NewVal= 0";
_newval = (int) (0);
 };
 break; }
case 1: 
case 2: 
case 3: 
case 4: 
case 5: 
case 6: 
case 7: 
case 8: 
case 9: 
case 10: 
case 11: 
case 12: {
 //BA.debugLineNum = 244;BA.debugLine="If Comando.Contains (Configuraciones.Get (2)";
if (_comando.contains(BA.ObjectToString(mostCurrent._configuraciones.Get((int) (2))))) { 
 //BA.debugLineNum = 245;BA.debugLine="NewVal= 1 'ValoresComunes.Circuitos (pos).R";
_newval = (int) (1);
 };
 //BA.debugLineNum = 247;BA.debugLine="If Comando.Contains (Configuraciones.Get (3)";
if (_comando.contains(BA.ObjectToString(mostCurrent._configuraciones.Get((int) (3))))) { 
 //BA.debugLineNum = 248;BA.debugLine="NewVal= 0";
_newval = (int) (0);
 };
 break; }
case 13: 
case 14: 
case 15: {
 break; }
case 16: 
case 17: {
 //BA.debugLineNum = 256;BA.debugLine="If Comando.Contains (Configuraciones.Get (4)";
if (_comando.contains(BA.ObjectToString(mostCurrent._configuraciones.Get((int) (4))))) { 
 //BA.debugLineNum = 257;BA.debugLine="NewVal=100";
_newval = (int) (100);
 };
 //BA.debugLineNum = 259;BA.debugLine="If Comando.Contains (Configuraciones.Get (5)";
if (_comando.contains(BA.ObjectToString(mostCurrent._configuraciones.Get((int) (5))))) { 
 //BA.debugLineNum = 260;BA.debugLine="NewVal= 0";
_newval = (int) (0);
 };
 break; }
}
;
 //BA.debugLineNum = 267;BA.debugLine="If NewVal > -1 Then";
if (_newval>-1) { 
 //BA.debugLineNum = 269;BA.debugLine="Dim LongTrama As Int";
_longtrama = 0;
 //BA.debugLineNum = 270;BA.debugLine="If ValoresComunes.central.ConexionSegura The";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 271;BA.debugLine="LongTrama= 14";
_longtrama = (int) (14);
 }else {
 //BA.debugLineNum = 273;BA.debugLine="LongTrama= 6";
_longtrama = (int) (6);
 };
 //BA.debugLineNum = 275;BA.debugLine="Dim data(LongTrama) As Byte";
_data = new byte[_longtrama];
;
 //BA.debugLineNum = 276;BA.debugLine="data(0)=83";
_data[(int) (0)] = (byte) (83);
 //BA.debugLineNum = 277;BA.debugLine="data(1)=86";
_data[(int) (1)] = (byte) (86);
 //BA.debugLineNum = 278;BA.debugLine="data(2)=65";
_data[(int) (2)] = (byte) (65);
 //BA.debugLineNum = 279;BA.debugLine="data(3)=76";
_data[(int) (3)] = (byte) (76);
 //BA.debugLineNum = 280;BA.debugLine="data(4)= pos +1";
_data[(int) (4)] = (byte) (_pos+1);
 //BA.debugLineNum = 281;BA.debugLine="data (5)=NewVal+1";
_data[(int) (5)] = (byte) (_newval+1);
 //BA.debugLineNum = 282;BA.debugLine="If ValoresComunes.central.ConexionSegura The";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
mostCurrent._valorescomunes._completartrama(mostCurrent.activityBA,_data);};
 //BA.debugLineNum = 285;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 //BA.debugLineNum = 287;BA.debugLine="Return";
if (true) return "";
 };
 };
 }
};
 };
 }
};
 //BA.debugLineNum = 297;BA.debugLine="MostraNoSeReconoce";
_mostranosereconoce();
 //BA.debugLineNum = 299;BA.debugLine="End Sub";
return "";
}
public static boolean  _sendingtrama(byte[] _data) throws Exception{
anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet = null;
 //BA.debugLineNum = 370;BA.debugLine="Sub SendingTrama (data() As Byte) As Boolean";
 //BA.debugLineNum = 371;BA.debugLine="Try";
try { //BA.debugLineNum = 372;BA.debugLine="Dim Packet As UDPPacket";
_packet = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket();
 //BA.debugLineNum = 374;BA.debugLine="Packet.Initialize(data, ValoresComunes.ip,  Valo";
_packet.Initialize(_data,mostCurrent._valorescomunes._ip,mostCurrent._valorescomunes._puerto);
 //BA.debugLineNum = 375;BA.debugLine="UDPSocket1.Send(Packet)";
_udpsocket1.Send(_packet);
 //BA.debugLineNum = 376;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 378;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 380;BA.debugLine="End Sub";
return false;
}
public static void  _sendtrama(byte[] _data) throws Exception{
ResumableSub_SendTrama rsub = new ResumableSub_SendTrama(null,_data);
rsub.resume(processBA, null);
}
public static class ResumableSub_SendTrama extends BA.ResumableSub {
public ResumableSub_SendTrama(arduino.automatizacion.excontrol.PRO.actvoice parent,byte[] _data) {
this.parent = parent;
this._data = _data;
}
arduino.automatizacion.excontrol.PRO.actvoice parent;
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
 //BA.debugLineNum = 327;BA.debugLine="Dim Resultado As Boolean";
_resultado = false;
 //BA.debugLineNum = 328;BA.debugLine="Dim Reintento As Int";
_reintento = 0;
 //BA.debugLineNum = 329;BA.debugLine="PaqueteEnviado =False";
parent._paqueteenviado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 330;BA.debugLine="TramaEnviada= data";
parent._tramaenviada = _data;
 //BA.debugLineNum = 331;BA.debugLine="ImgCargando.Visible =True";
parent.mostCurrent._imgcargando.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 332;BA.debugLine="AniCargando.Start (ImgCargando)";
parent.mostCurrent._anicargando.Start((android.view.View)(parent.mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 335;BA.debugLine="Do 	While Resultado= False And Reintento < 40";
if (true) break;

case 1:
//do while
this.state = 15;
while (_resultado==anywheresoftware.b4a.keywords.Common.False && _reintento<40) {
this.state = 3;
if (true) break;
}
if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 336;BA.debugLine="Dim ServerSocket1 As ServerSocket";
_serversocket1 = new anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper();
 //BA.debugLineNum = 337;BA.debugLine="Dim MyIp As String";
_myip = "";
 //BA.debugLineNum = 338;BA.debugLine="If ActMosaico.Forzar3g Then";
if (true) break;

case 4:
//if
this.state = 9;
if (parent.mostCurrent._actmosaico._forzar3g) { 
this.state = 6;
}else {
this.state = 8;
}if (true) break;

case 6:
//C
this.state = 9;
 //BA.debugLineNum = 339;BA.debugLine="MyIp=ServerSocket1.GetMyIP";
_myip = _serversocket1.GetMyIP();
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 341;BA.debugLine="MyIp=ServerSocket1.GetMyWifiIP";
_myip = _serversocket1.GetMyWifiIP();
 if (true) break;
;
 //BA.debugLineNum = 343;BA.debugLine="If MyIp  <> \"127.0.0.1\" Then";

case 9:
//if
this.state = 14;
if ((_myip).equals("127.0.0.1") == false) { 
this.state = 11;
}else {
this.state = 13;
}if (true) break;

case 11:
//C
this.state = 14;
 //BA.debugLineNum = 344;BA.debugLine="Resultado = True";
_resultado = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 13:
//C
this.state = 14;
 //BA.debugLineNum = 346;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 347;BA.debugLine="Sleep (200)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (200));
this.state = 34;
return;
case 34:
//C
this.state = 14;
;
 if (true) break;

case 14:
//C
this.state = 1;
;
 if (true) break;
;
 //BA.debugLineNum = 351;BA.debugLine="If Resultado = True Then";

case 15:
//if
this.state = 28;
if (_resultado==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 17;
}if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 352;BA.debugLine="Resultado =False";
_resultado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 353;BA.debugLine="Reintento =0";
_reintento = (int) (0);
 //BA.debugLineNum = 354;BA.debugLine="Do 	While Resultado= False And Reintento < 40";
if (true) break;

case 18:
//do while
this.state = 27;
while (_resultado==anywheresoftware.b4a.keywords.Common.False && _reintento<40) {
this.state = 20;
if (true) break;
}
if (true) break;

case 20:
//C
this.state = 21;
 //BA.debugLineNum = 355;BA.debugLine="Resultado = SendingTrama(data)";
_resultado = _sendingtrama(_data);
 //BA.debugLineNum = 356;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 357;BA.debugLine="If Resultado=False Then Sleep (200)";
if (true) break;

case 21:
//if
this.state = 26;
if (_resultado==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 23;
;}if (true) break;

case 23:
//C
this.state = 26;
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (200));
this.state = 35;
return;
case 35:
//C
this.state = 26;
;
if (true) break;

case 26:
//C
this.state = 18;
;
 if (true) break;

case 27:
//C
this.state = 28;
;
 if (true) break;
;
 //BA.debugLineNum = 362;BA.debugLine="If Resultado = False  Then";

case 28:
//if
this.state = 33;
if (_resultado==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 30;
}else {
this.state = 32;
}if (true) break;

case 30:
//C
this.state = 33;
 //BA.debugLineNum = 363;BA.debugLine="ActMosaico.Conectado =False";
parent.mostCurrent._actmosaico._conectado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 364;BA.debugLine="StartActivity(ActMosaico)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._actmosaico.getObject()));
 if (true) break;

case 32:
//C
this.state = 33;
 //BA.debugLineNum = 366;BA.debugLine="PaqueteEnviado=True";
parent._paqueteenviado = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 33:
//C
this.state = -1;
;
 //BA.debugLineNum = 368;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _udp_packetarrived(anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet) throws Exception{
String _msg = "";
 //BA.debugLineNum = 301;BA.debugLine="Sub UDP_PacketArrived (Packet As UDPPacket)";
 //BA.debugLineNum = 302;BA.debugLine="Try";
try { //BA.debugLineNum = 303;BA.debugLine="Dim msg As String";
_msg = "";
 //BA.debugLineNum = 304;BA.debugLine="msg = BytesToString(Packet.Data, Packet.Offse";
_msg = anywheresoftware.b4a.keywords.Common.BytesToString(_packet.getData(),_packet.getOffset(),_packet.getLength(),"UTF8");
 //BA.debugLineNum = 307;BA.debugLine="If msg= \"COMPLETED\" Or msg.Contains (\"EVAL\") The";
if ((_msg).equals("COMPLETED") || _msg.contains("EVAL")) { 
 //BA.debugLineNum = 309;BA.debugLine="PaqueteEnviado = False";
_paqueteenviado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 310;BA.debugLine="AniCargando.Stop(ImgCargando)";
mostCurrent._anicargando.Stop((android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 311;BA.debugLine="ImgCargando.Visible =False";
mostCurrent._imgcargando.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 313;BA.debugLine="ToastMessageShow(ValoresComunes.GetLanString (\"";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg139")),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 314;BA.debugLine="TTS1.Speak(ValoresComunes.GetLanString (\"reg139";
_tts1.Speak(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg139"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 315;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 317;BA.debugLine="If msg =\"REPETIRMSG\" Then";
if ((_msg).equals("REPETIRMSG")) { 
 //BA.debugLineNum = 318;BA.debugLine="SendTrama(TramaEnviada)";
_sendtrama(_tramaenviada);
 };
 } 
       catch (Exception e16) {
			processBA.setLastException(e16); };
 //BA.debugLineNum = 325;BA.debugLine="End Sub";
return "";
}
public static String  _vr_result(boolean _success,anywheresoftware.b4a.objects.collections.List _texts) throws Exception{
 //BA.debugLineNum = 118;BA.debugLine="Sub VR_Result (Success As Boolean, Texts As List)";
 //BA.debugLineNum = 119;BA.debugLine="If VR.IsSupported = False Then Activity.Finish";
if (_vr.IsSupported()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._activity.Finish();};
 //BA.debugLineNum = 120;BA.debugLine="If Success = True Then";
if (_success==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 123;BA.debugLine="ReconocerComando(AdaptarComando(Texts.Get(0)))";
_reconocercomando(_adaptarcomando(BA.ObjectToString(_texts.Get((int) (0)))));
 }else {
 //BA.debugLineNum = 125;BA.debugLine="MostraNoSeReconoce";
_mostranosereconoce();
 };
 //BA.debugLineNum = 127;BA.debugLine="End Sub";
return "";
}
}
