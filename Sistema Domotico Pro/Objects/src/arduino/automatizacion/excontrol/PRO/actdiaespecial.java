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

public class actdiaespecial extends Activity implements B4AActivity{
	public static actdiaespecial mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actdiaespecial");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actdiaespecial).");
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
		activityBA = new BA(this, layout, processBA, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actdiaespecial");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "arduino.automatizacion.excontrol.PRO.actdiaespecial", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actdiaespecial) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actdiaespecial) Resume **");
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
		return actdiaespecial.class;
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
        BA.LogInfo("** Activity (actdiaespecial) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (actdiaespecial) Resume **");
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
public static byte _tipoespecial = (byte)0;
public static anywheresoftware.b4a.objects.SocketWrapper.UDPSocket _udpsocket1 = null;
public anywheresoftware.b4a.objects.collections.List _fechas = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _cmdguardar = null;
public anywheresoftware.b4a.objects.ButtonWrapper _cmdnew = null;
public anywheresoftware.b4a.objects.ButtonWrapper _cmdterminar = null;
public static byte[] _tramaenviada = null;
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

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
int _he = 0;
 //BA.debugLineNum = 38;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 39;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = Fals";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 42;BA.debugLine="Activity.LoadLayout (\"FrmConfig2\")";
mostCurrent._activity.LoadLayout("FrmConfig2",mostCurrent.activityBA);
 //BA.debugLineNum = 44;BA.debugLine="Dim he As Int = 40dip";
_he = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40));
 //BA.debugLineNum = 46;BA.debugLine="ListView1.Height = Activity.Height - he";
mostCurrent._listview1.setHeight((int) (mostCurrent._activity.getHeight()-_he));
 //BA.debugLineNum = 47;BA.debugLine="ListView1.Width =Activity.Width";
mostCurrent._listview1.setWidth(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 51;BA.debugLine="CmdGuardar.Top=Activity.Height-he";
mostCurrent._cmdguardar.setTop((int) (mostCurrent._activity.getHeight()-_he));
 //BA.debugLineNum = 52;BA.debugLine="CmdGuardar.Left = Activity.Width   - (he+4dip)";
mostCurrent._cmdguardar.setLeft((int) (mostCurrent._activity.getWidth()-(_he+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4)))));
 //BA.debugLineNum = 53;BA.debugLine="CmdGuardar.Height =he";
mostCurrent._cmdguardar.setHeight(_he);
 //BA.debugLineNum = 54;BA.debugLine="CmdGuardar.Width =he' Activity.Width";
mostCurrent._cmdguardar.setWidth(_he);
 //BA.debugLineNum = 55;BA.debugLine="CmdGuardar.Text = \"\"'ValoresComunes.GetLanString";
mostCurrent._cmdguardar.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 56;BA.debugLine="CmdGuardar.SetBackgroundImage(ValoresComunes.CmdI";
mostCurrent._cmdguardar.SetBackgroundImage((android.graphics.Bitmap)(mostCurrent._valorescomunes._cmdimgsave(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 58;BA.debugLine="CmdTerminar.Top=Activity.Height-he";
mostCurrent._cmdterminar.setTop((int) (mostCurrent._activity.getHeight()-_he));
 //BA.debugLineNum = 59;BA.debugLine="CmdTerminar.Height =he";
mostCurrent._cmdterminar.setHeight(_he);
 //BA.debugLineNum = 60;BA.debugLine="CmdTerminar.Width =he";
mostCurrent._cmdterminar.setWidth(_he);
 //BA.debugLineNum = 61;BA.debugLine="CmdTerminar.Left =Activity.Width   - ((he  * 2)";
mostCurrent._cmdterminar.setLeft((int) (mostCurrent._activity.getWidth()-((_he*2)+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)))));
 //BA.debugLineNum = 62;BA.debugLine="CmdTerminar.SetBackgroundImage(ValoresComunes.Cmd";
mostCurrent._cmdterminar.SetBackgroundImage((android.graphics.Bitmap)(mostCurrent._valorescomunes._cmdimgback(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 63;BA.debugLine="CmdTerminar.Text = \"\"";
mostCurrent._cmdterminar.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 65;BA.debugLine="CmdNew.Top=Activity.Height-he";
mostCurrent._cmdnew.setTop((int) (mostCurrent._activity.getHeight()-_he));
 //BA.debugLineNum = 66;BA.debugLine="CmdNew.Height =he";
mostCurrent._cmdnew.setHeight(_he);
 //BA.debugLineNum = 67;BA.debugLine="CmdNew.Width =he";
mostCurrent._cmdnew.setWidth(_he);
 //BA.debugLineNum = 68;BA.debugLine="CmdNew.Left =Activity.Width   - ((he  * 3)   +12d";
mostCurrent._cmdnew.setLeft((int) (mostCurrent._activity.getWidth()-((_he*3)+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)))));
 //BA.debugLineNum = 69;BA.debugLine="CmdNew.SetBackgroundImage(ValoresComunes.CmdImgNe";
mostCurrent._cmdnew.SetBackgroundImage((android.graphics.Bitmap)(mostCurrent._valorescomunes._cmdimgnew(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 70;BA.debugLine="CmdNew.Text = \"\"";
mostCurrent._cmdnew.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 72;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 122;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 123;BA.debugLine="UDPSocket1.Close";
_udpsocket1.Close();
 //BA.debugLineNum = 124;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 125;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
String _s = "";
byte[] _data = null;
 //BA.debugLineNum = 73;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 74;BA.debugLine="If ValoresComunes.CloseApp =True Then";
if (mostCurrent._valorescomunes._closeapp==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 75;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 76;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 78;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = True";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 79;BA.debugLine="If ImgCargando.IsInitialized = False Then";
if (mostCurrent._imgcargando.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 81;BA.debugLine="ImgCargando.Initialize (\"ImgCargando\")";
mostCurrent._imgcargando.Initialize(mostCurrent.activityBA,"ImgCargando");
 //BA.debugLineNum = 82;BA.debugLine="ImgCargando.Bitmap = ValoresComunes.Cargando";
mostCurrent._imgcargando.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._cargando(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 83;BA.debugLine="ImgCargando.Gravity = Gravity.FILL";
mostCurrent._imgcargando.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 84;BA.debugLine="Activity.AddView (ImgCargando,  PerXToCurrent(4";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._imgcargando.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (40),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA));
 };
 //BA.debugLineNum = 86;BA.debugLine="If Main.MyLan.IsInitialized = False Then Main.My";
if (mostCurrent._main._mylan.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._main._mylan.Initialize(processBA,(int) (0),"");};
 //BA.debugLineNum = 87;BA.debugLine="If UDPSocket1.IsInitialized = False Then  Valore";
if (_udpsocket1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._valorescomunes._iniudp(mostCurrent.activityBA,_udpsocket1);};
 //BA.debugLineNum = 89;BA.debugLine="Fechas.Initialize";
mostCurrent._fechas.Initialize();
 //BA.debugLineNum = 90;BA.debugLine="Dim s As String";
_s = "";
 //BA.debugLineNum = 92;BA.debugLine="CmdGuardar.Enabled =False";
mostCurrent._cmdguardar.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 93;BA.debugLine="CmdNew.Enabled =False";
mostCurrent._cmdnew.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 98;BA.debugLine="AniCargando.InitializeRotateCenter( \"AniCargando";
mostCurrent._anicargando.InitializeRotateCenter(mostCurrent.activityBA,"AniCargando",(float) (0),(float) (180),(android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 99;BA.debugLine="AniCargando.Duration = 1000";
mostCurrent._anicargando.setDuration((long) (1000));
 //BA.debugLineNum = 100;BA.debugLine="AniCargando.RepeatCount =5";
mostCurrent._anicargando.setRepeatCount((int) (5));
 //BA.debugLineNum = 101;BA.debugLine="AniCargando.RepeatMode = AniCargando.REPEAT_R";
mostCurrent._anicargando.setRepeatMode(mostCurrent._anicargando.REPEAT_REVERSE);
 //BA.debugLineNum = 105;BA.debugLine="If TipoEspecial=2 Then";
if (_tipoespecial==2) { 
 //BA.debugLineNum = 107;BA.debugLine="s=\"READDAY2\"";
_s = "READDAY2";
 }else {
 //BA.debugLineNum = 109;BA.debugLine="s=\"READDAY1\"";
_s = "READDAY1";
 };
 //BA.debugLineNum = 111;BA.debugLine="If ValoresComunes.central.ConexionSegura Then s";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
_s = _s+mostCurrent._valorescomunes._central.Password;};
 //BA.debugLineNum = 112;BA.debugLine="Dim DATA() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 113;BA.debugLine="DATA= s.GetBytes (\"UTF8\")";
_data = _s.getBytes("UTF8");
 //BA.debugLineNum = 114;BA.debugLine="SendTrama(DATA)";
_sendtrama(_data);
 }else {
 //BA.debugLineNum = 116;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 117;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 };
 //BA.debugLineNum = 120;BA.debugLine="End Sub";
return "";
}
public static String  _anicargando_animationend() throws Exception{
 //BA.debugLineNum = 126;BA.debugLine="Sub AniCargando_AnimationEnd";
 //BA.debugLineNum = 128;BA.debugLine="If PaqueteEnviado = True Then";
if (_paqueteenviado==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 129;BA.debugLine="AniCargando.Start (ImgCargando)";
mostCurrent._anicargando.Start((android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 130;BA.debugLine="SendTrama(TramaEnviada)";
_sendtrama(_tramaenviada);
 };
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return "";
}
public static String  _cmdguardar_click() throws Exception{
int _rsp = 0;
int _longtrama = 0;
byte[] _data = null;
int _i = 0;
 //BA.debugLineNum = 172;BA.debugLine="Sub CmdGuardar_Click";
 //BA.debugLineNum = 176;BA.debugLine="Dim Rsp As Int";
_rsp = 0;
 //BA.debugLineNum = 177;BA.debugLine="Rsp = Msgbox2(ValoresComunes.GetLanString (\"reg76";
_rsp = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg76")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg77")),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Y"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"N"),"",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 //BA.debugLineNum = 178;BA.debugLine="If Rsp = DialogResponse.POSITIVE Then";
if (_rsp==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 183;BA.debugLine="Dim LongTrama As Int";
_longtrama = 0;
 //BA.debugLineNum = 184;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 185;BA.debugLine="LongTrama= 66";
_longtrama = (int) (66);
 }else {
 //BA.debugLineNum = 187;BA.debugLine="LongTrama= 58";
_longtrama = (int) (58);
 };
 //BA.debugLineNum = 189;BA.debugLine="Dim data(LongTrama) As Byte";
_data = new byte[_longtrama];
;
 //BA.debugLineNum = 190;BA.debugLine="data(0)=87";
_data[(int) (0)] = (byte) (87);
 //BA.debugLineNum = 191;BA.debugLine="data(1)=82";
_data[(int) (1)] = (byte) (82);
 //BA.debugLineNum = 192;BA.debugLine="data(2)=73";
_data[(int) (2)] = (byte) (73);
 //BA.debugLineNum = 193;BA.debugLine="data(3)=68";
_data[(int) (3)] = (byte) (68);
 //BA.debugLineNum = 194;BA.debugLine="data(4)=65";
_data[(int) (4)] = (byte) (65);
 //BA.debugLineNum = 195;BA.debugLine="data(5)=89";
_data[(int) (5)] = (byte) (89);
 //BA.debugLineNum = 196;BA.debugLine="data(6)=69";
_data[(int) (6)] = (byte) (69);
 //BA.debugLineNum = 198;BA.debugLine="data(7)=TipoEspecial";
_data[(int) (7)] = _tipoespecial;
 //BA.debugLineNum = 200;BA.debugLine="Dim I As Int";
_i = 0;
 //BA.debugLineNum = 202;BA.debugLine="For I =0 To Fechas.Size -1";
{
final int step20 = 1;
final int limit20 = (int) (mostCurrent._fechas.getSize()-1);
_i = (int) (0) ;
for (;(step20 > 0 && _i <= limit20) || (step20 < 0 && _i >= limit20) ;_i = ((int)(0 + _i + step20))  ) {
 //BA.debugLineNum = 203;BA.debugLine="data((I*2)+8)=DateTime.GetMonth (Fechas.Get(I))";
_data[(int) ((_i*2)+8)] = (byte) (anywheresoftware.b4a.keywords.Common.DateTime.GetMonth(BA.ObjectToLongNumber(mostCurrent._fechas.Get(_i))));
 //BA.debugLineNum = 204;BA.debugLine="data((I*2)+9)=DateTime.GetDayOfMonth  (Fechas.G";
_data[(int) ((_i*2)+9)] = (byte) (anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfMonth(BA.ObjectToLongNumber(mostCurrent._fechas.Get(_i))));
 }
};
 //BA.debugLineNum = 206;BA.debugLine="If ValoresComunes.central.ConexionSegura Then Va";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
mostCurrent._valorescomunes._completartrama(mostCurrent.activityBA,_data);};
 //BA.debugLineNum = 207;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 };
 //BA.debugLineNum = 211;BA.debugLine="End Sub";
return "";
}
public static String  _cmdnew_click() throws Exception{
anywheresoftware.b4a.agraham.dialogs.InputDialog.DateDialog _dialog = null;
int _resp = 0;
int _p = 0;
 //BA.debugLineNum = 133;BA.debugLine="Sub CmdNew_Click";
 //BA.debugLineNum = 134;BA.debugLine="If Fechas.Size >24 Then";
if (mostCurrent._fechas.getSize()>24) { 
 //BA.debugLineNum = 135;BA.debugLine="Msgbox(ValoresComunes.GetLanString (\"reg143\"),Va";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg143")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg69")),mostCurrent.activityBA);
 //BA.debugLineNum = 136;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 138;BA.debugLine="Dim Dialog As DateDialog";
_dialog = new anywheresoftware.b4a.agraham.dialogs.InputDialog.DateDialog();
 //BA.debugLineNum = 139;BA.debugLine="Dialog.DateTicks = DateTime.Now";
_dialog.setDateTicks(anywheresoftware.b4a.keywords.Common.DateTime.getNow());
 //BA.debugLineNum = 141;BA.debugLine="Dim Resp As Int";
_resp = 0;
 //BA.debugLineNum = 142;BA.debugLine="Resp= Dialog.Show (ValoresComunes.GetLanString (\"";
_resp = _dialog.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg144"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Ok"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 143;BA.debugLine="If Resp = DialogResponse.POSITIVE Then";
if (_resp==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 144;BA.debugLine="If Dialog.DateTicks < DateTime.Add (DateTime.Now";
if (_dialog.getDateTicks()<anywheresoftware.b4a.keywords.Common.DateTime.Add(anywheresoftware.b4a.keywords.Common.DateTime.getNow(),(int) (0),(int) (0),(int) (-1))) { 
 //BA.debugLineNum = 145;BA.debugLine="Msgbox(ValoresComunes.GetLanString (\"reg145\"),V";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg145")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg120")),mostCurrent.activityBA);
 //BA.debugLineNum = 146;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 149;BA.debugLine="If Fechas.Size =0 Then";
if (mostCurrent._fechas.getSize()==0) { 
 //BA.debugLineNum = 150;BA.debugLine="Fechas.Add (Dialog.DateTicks )";
mostCurrent._fechas.Add((Object)(_dialog.getDateTicks()));
 }else {
 //BA.debugLineNum = 152;BA.debugLine="Dim p As Int";
_p = 0;
 //BA.debugLineNum = 153;BA.debugLine="For p=0 To Fechas.Size -1";
{
final int step18 = 1;
final int limit18 = (int) (mostCurrent._fechas.getSize()-1);
_p = (int) (0) ;
for (;(step18 > 0 && _p <= limit18) || (step18 < 0 && _p >= limit18) ;_p = ((int)(0 + _p + step18))  ) {
 //BA.debugLineNum = 155;BA.debugLine="If Fechas.Get (p)= Dialog.DateTicks Then";
if ((mostCurrent._fechas.Get(_p)).equals((Object)(_dialog.getDateTicks()))) { 
 //BA.debugLineNum = 156;BA.debugLine="Msgbox(ValoresComunes.GetLanString (\"reg146\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg146")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg147")),mostCurrent.activityBA);
 //BA.debugLineNum = 157;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 159;BA.debugLine="If Fechas.Get (p)> Dialog.DateTicks Then";
if ((double)(BA.ObjectToNumber(mostCurrent._fechas.Get(_p)))>_dialog.getDateTicks()) { 
 //BA.debugLineNum = 160;BA.debugLine="Fechas.InsertAt   (p,Dialog.DateTicks)";
mostCurrent._fechas.InsertAt(_p,(Object)(_dialog.getDateTicks()));
 //BA.debugLineNum = 161;BA.debugLine="RefrescaPantalla";
_refrescapantalla();
 //BA.debugLineNum = 162;BA.debugLine="Return";
if (true) return "";
 };
 }
};
 //BA.debugLineNum = 166;BA.debugLine="Fechas.Add (Dialog.DateTicks )";
mostCurrent._fechas.Add((Object)(_dialog.getDateTicks()));
 };
 //BA.debugLineNum = 168;BA.debugLine="RefrescaPantalla";
_refrescapantalla();
 };
 //BA.debugLineNum = 170;BA.debugLine="End Sub";
return "";
}
public static String  _cmdterminar_click() throws Exception{
 //BA.debugLineNum = 357;BA.debugLine="Sub CmdTerminar_Click";
 //BA.debugLineNum = 358;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 359;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 22;BA.debugLine="Dim Fechas As List";
mostCurrent._fechas = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 24;BA.debugLine="Dim ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Dim CmdGuardar As Button";
mostCurrent._cmdguardar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Dim CmdNew As Button";
mostCurrent._cmdnew = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private CmdTerminar As Button";
mostCurrent._cmdterminar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Dim TramaEnviada() As Byte";
_tramaenviada = new byte[(int) (0)];
;
 //BA.debugLineNum = 30;BA.debugLine="Dim ImgCargando As ImageView";
mostCurrent._imgcargando = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Dim AniCargando As  Animation";
mostCurrent._anicargando = new anywheresoftware.b4a.objects.AnimationWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Dim PaqueteEnviado As Boolean";
_paqueteenviado = false;
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
int _r = 0;
 //BA.debugLineNum = 213;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 214;BA.debugLine="Dim R As Int";
_r = 0;
 //BA.debugLineNum = 215;BA.debugLine="R=Msgbox2(ValoresComunes.GetLanString (\"reg148\"),";
_r = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg148")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg66")),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Y"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"N"),"",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 //BA.debugLineNum = 216;BA.debugLine="If R = DialogResponse.POSITIVE Then";
if (_r==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 217;BA.debugLine="Fechas.RemoveAt (Position)";
mostCurrent._fechas.RemoveAt(_position);
 //BA.debugLineNum = 218;BA.debugLine="RefrescaPantalla";
_refrescapantalla();
 };
 //BA.debugLineNum = 220;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="Dim TipoEspecial As Byte";
_tipoespecial = (byte)0;
 //BA.debugLineNum = 13;BA.debugLine="Dim UDPSocket1 As UDPSocket";
_udpsocket1 = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket();
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return "";
}
public static String  _refrescapantalla() throws Exception{
int _c = 0;
 //BA.debugLineNum = 221;BA.debugLine="Sub RefrescaPantalla()";
 //BA.debugLineNum = 222;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 224;BA.debugLine="Dim c As Int";
_c = 0;
 //BA.debugLineNum = 225;BA.debugLine="DateTime.DateFormat=\"dd/MM/yyyy\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("dd/MM/yyyy");
 //BA.debugLineNum = 226;BA.debugLine="For c =0 To Fechas.Size -1";
{
final int step4 = 1;
final int limit4 = (int) (mostCurrent._fechas.getSize()-1);
_c = (int) (0) ;
for (;(step4 > 0 && _c <= limit4) || (step4 < 0 && _c >= limit4) ;_c = ((int)(0 + _c + step4))  ) {
 //BA.debugLineNum = 228;BA.debugLine="ListView1.AddSingleLine  (DateTime.Date(Fechas.G";
mostCurrent._listview1.AddSingleLine(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Date(BA.ObjectToLongNumber(mostCurrent._fechas.Get(_c)))));
 }
};
 //BA.debugLineNum = 230;BA.debugLine="End Sub";
return "";
}
public static boolean  _sendingtrama(byte[] _data) throws Exception{
anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet = null;
 //BA.debugLineNum = 276;BA.debugLine="Sub SendingTrama (data() As Byte) As Boolean";
 //BA.debugLineNum = 277;BA.debugLine="Try";
try { //BA.debugLineNum = 278;BA.debugLine="Dim Packet As UDPPacket";
_packet = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket();
 //BA.debugLineNum = 279;BA.debugLine="Packet.Initialize(data, ValoresComunes.ip, Valor";
_packet.Initialize(_data,mostCurrent._valorescomunes._ip,mostCurrent._valorescomunes._puerto);
 //BA.debugLineNum = 280;BA.debugLine="UDPSocket1.Send(Packet)";
_udpsocket1.Send(_packet);
 //BA.debugLineNum = 281;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 283;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 285;BA.debugLine="End Sub";
return false;
}
public static void  _sendtrama(byte[] _data) throws Exception{
ResumableSub_SendTrama rsub = new ResumableSub_SendTrama(null,_data);
rsub.resume(processBA, null);
}
public static class ResumableSub_SendTrama extends BA.ResumableSub {
public ResumableSub_SendTrama(arduino.automatizacion.excontrol.PRO.actdiaespecial parent,byte[] _data) {
this.parent = parent;
this._data = _data;
}
arduino.automatizacion.excontrol.PRO.actdiaespecial parent;
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
 //BA.debugLineNum = 232;BA.debugLine="Dim Resultado As Boolean";
_resultado = false;
 //BA.debugLineNum = 233;BA.debugLine="Dim Reintento As Int";
_reintento = 0;
 //BA.debugLineNum = 235;BA.debugLine="CmdGuardar.Enabled =False";
parent.mostCurrent._cmdguardar.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 236;BA.debugLine="CmdNew.Enabled =False";
parent.mostCurrent._cmdnew.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 237;BA.debugLine="AniCargando.Start (ImgCargando)";
parent.mostCurrent._anicargando.Start((android.view.View)(parent.mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 238;BA.debugLine="PaqueteEnviado =False";
parent._paqueteenviado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 240;BA.debugLine="TramaEnviada= data";
parent._tramaenviada = _data;
 //BA.debugLineNum = 242;BA.debugLine="Do 	While Resultado= False And Reintento < 40";
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
 //BA.debugLineNum = 243;BA.debugLine="Dim ServerSocket1 As ServerSocket";
_serversocket1 = new anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper();
 //BA.debugLineNum = 244;BA.debugLine="Dim MyIp As String";
_myip = "";
 //BA.debugLineNum = 245;BA.debugLine="If ActMosaico.Forzar3g Then";
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
 //BA.debugLineNum = 246;BA.debugLine="MyIp=ServerSocket1.GetMyIP";
_myip = _serversocket1.GetMyIP();
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 248;BA.debugLine="MyIp=ServerSocket1.GetMyWifiIP";
_myip = _serversocket1.GetMyWifiIP();
 if (true) break;
;
 //BA.debugLineNum = 250;BA.debugLine="If MyIp  <> \"127.0.0.1\" Then";

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
 //BA.debugLineNum = 251;BA.debugLine="Resultado = True";
_resultado = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 13:
//C
this.state = 14;
 //BA.debugLineNum = 253;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 254;BA.debugLine="Sleep (200)";
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
 //BA.debugLineNum = 258;BA.debugLine="If Resultado = True Then";

case 15:
//if
this.state = 28;
if (_resultado==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 17;
}if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 259;BA.debugLine="Resultado =False";
_resultado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 260;BA.debugLine="Reintento =0";
_reintento = (int) (0);
 //BA.debugLineNum = 261;BA.debugLine="Do 	While Resultado= False And Reintento < 40";
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
 //BA.debugLineNum = 262;BA.debugLine="Resultado = SendingTrama(data)";
_resultado = _sendingtrama(_data);
 //BA.debugLineNum = 263;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 264;BA.debugLine="If Resultado=False Then Sleep (200)";
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
 //BA.debugLineNum = 269;BA.debugLine="If Resultado = False  Then";

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
 //BA.debugLineNum = 270;BA.debugLine="ActMosaico.Conectado =False";
parent.mostCurrent._actmosaico._conectado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 271;BA.debugLine="StartActivity(ActMosaico)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._actmosaico.getObject()));
 if (true) break;

case 32:
//C
this.state = 33;
 //BA.debugLineNum = 273;BA.debugLine="PaqueteEnviado=True";
parent._paqueteenviado = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 33:
//C
this.state = -1;
;
 //BA.debugLineNum = 275;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _udp_packetarrived(anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet) throws Exception{
String _msg = "";
byte[] _valores = null;
int _p = 0;
anywheresoftware.b4a.objects.collections.List _fc = null;
int _c = 0;
long _f = 0L;
int _pos = 0;
long _valmax = 0L;
 //BA.debugLineNum = 286;BA.debugLine="Sub UDP_PacketArrived (Packet As UDPPacket)";
 //BA.debugLineNum = 287;BA.debugLine="Try";
try { //BA.debugLineNum = 288;BA.debugLine="Dim msg As String";
_msg = "";
 //BA.debugLineNum = 289;BA.debugLine="msg = BytesToString(Packet.Data, Packet.Offse";
_msg = anywheresoftware.b4a.keywords.Common.BytesToString(_packet.getData(),_packet.getOffset(),_packet.getLength(),"UTF8");
 //BA.debugLineNum = 292;BA.debugLine="If msg.Contains (\"CFDA\") Then";
if (_msg.contains("CFDA")) { 
 //BA.debugLineNum = 293;BA.debugLine="Fechas.Clear";
mostCurrent._fechas.Clear();
 //BA.debugLineNum = 295;BA.debugLine="Dim Valores(50) As Byte";
_valores = new byte[(int) (50)];
;
 //BA.debugLineNum = 296;BA.debugLine="Dim p As Int";
_p = 0;
 //BA.debugLineNum = 297;BA.debugLine="For p =0 To 49";
{
final int step8 = 1;
final int limit8 = (int) (49);
_p = (int) (0) ;
for (;(step8 > 0 && _p <= limit8) || (step8 < 0 && _p >= limit8) ;_p = ((int)(0 + _p + step8))  ) {
 //BA.debugLineNum = 298;BA.debugLine="Valores(p)=Packet.Data (p+4)";
_valores[_p] = _packet.getData()[(int) (_p+4)];
 }
};
 //BA.debugLineNum = 300;BA.debugLine="Dim Fc As List";
_fc = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 301;BA.debugLine="Dim c As Int";
_c = 0;
 //BA.debugLineNum = 303;BA.debugLine="Fc.Initialize";
_fc.Initialize();
 //BA.debugLineNum = 304;BA.debugLine="DateTime.DateFormat=\"MM/dd/yyyy\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("MM/dd/yyyy");
 //BA.debugLineNum = 305;BA.debugLine="For c =0 To 48 Step 2";
{
final int step15 = (int) (2);
final int limit15 = (int) (48);
_c = (int) (0) ;
for (;(step15 > 0 && _c <= limit15) || (step15 < 0 && _c >= limit15) ;_c = ((int)(0 + _c + step15))  ) {
 //BA.debugLineNum = 306;BA.debugLine="If Valores(c)>0 And Valores(c)<13 And Valores";
if (_valores[_c]>0 && _valores[_c]<13 && _valores[(int) (_c+1)]>0 && _valores[(int) (_c+1)]<32) { 
 //BA.debugLineNum = 307;BA.debugLine="Dim F As Long";
_f = 0L;
 //BA.debugLineNum = 310;BA.debugLine="F = DateTime.DateParse (Valores(c) & \"/\" & Va";
_f = anywheresoftware.b4a.keywords.Common.DateTime.DateParse(BA.NumberToString(_valores[_c])+"/"+BA.NumberToString(_valores[(int) (_c+1)])+"/"+BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.GetYear(anywheresoftware.b4a.keywords.Common.DateTime.getNow())));
 //BA.debugLineNum = 311;BA.debugLine="If F < DateTime.Now Then F= DateTime.Add (F,1";
if (_f<anywheresoftware.b4a.keywords.Common.DateTime.getNow()) { 
_f = anywheresoftware.b4a.keywords.Common.DateTime.Add(_f,(int) (1),(int) (0),(int) (0));};
 //BA.debugLineNum = 312;BA.debugLine="Fc.Add (F)";
_fc.Add((Object)(_f));
 };
 }
};
 //BA.debugLineNum = 315;BA.debugLine="Do While Fc.Size >0";
while (_fc.getSize()>0) {
 //BA.debugLineNum = 316;BA.debugLine="Dim c As Int";
_c = 0;
 //BA.debugLineNum = 317;BA.debugLine="Dim Pos As Int";
_pos = 0;
 //BA.debugLineNum = 318;BA.debugLine="Dim ValMax As Long";
_valmax = 0L;
 //BA.debugLineNum = 319;BA.debugLine="For c =0 To Fc.Size -1";
{
final int step27 = 1;
final int limit27 = (int) (_fc.getSize()-1);
_c = (int) (0) ;
for (;(step27 > 0 && _c <= limit27) || (step27 < 0 && _c >= limit27) ;_c = ((int)(0 + _c + step27))  ) {
 //BA.debugLineNum = 320;BA.debugLine="If ValMax< Fc.Get (c) Then";
if (_valmax<(double)(BA.ObjectToNumber(_fc.Get(_c)))) { 
 //BA.debugLineNum = 321;BA.debugLine="ValMax=Fc.Get (c)";
_valmax = BA.ObjectToLongNumber(_fc.Get(_c));
 //BA.debugLineNum = 322;BA.debugLine="Pos=c";
_pos = _c;
 };
 }
};
 //BA.debugLineNum = 325;BA.debugLine="Fc.RemoveAt (Pos)";
_fc.RemoveAt(_pos);
 //BA.debugLineNum = 326;BA.debugLine="Fechas.InsertAt   (0,ValMax)";
mostCurrent._fechas.InsertAt((int) (0),(Object)(_valmax));
 }
;
 //BA.debugLineNum = 328;BA.debugLine="RefrescaPantalla";
_refrescapantalla();
 }else if((_msg).equals("REPETIRMSG")) { 
 //BA.debugLineNum = 332;BA.debugLine="SendTrama(TramaEnviada)";
_sendtrama(_tramaenviada);
 //BA.debugLineNum = 333;BA.debugLine="Return";
if (true) return "";
 }else if((_msg).equals("WRIDAYE")) { 
 //BA.debugLineNum = 335;BA.debugLine="ToastMessageShow(ValoresComunes.GetLanString (\"";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg139")),anywheresoftware.b4a.keywords.Common.True);
 }else if(_msg.contains("COMPLETED")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 338;BA.debugLine="SendTrama(TramaEnviada)";
_sendtrama(_tramaenviada);
 //BA.debugLineNum = 339;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 343;BA.debugLine="PaqueteEnviado = False";
_paqueteenviado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 344;BA.debugLine="CmdNew.Enabled =True";
mostCurrent._cmdnew.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 345;BA.debugLine="CmdGuardar.Enabled =True";
mostCurrent._cmdguardar.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 346;BA.debugLine="AniCargando.Stop(ImgCargando)";
mostCurrent._anicargando.Stop((android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 347;BA.debugLine="ImgCargando.Visible =False";
mostCurrent._imgcargando.setVisible(anywheresoftware.b4a.keywords.Common.False);
 } 
       catch (Exception e52) {
			processBA.setLastException(e52); };
 //BA.debugLineNum = 353;BA.debugLine="End Sub";
return "";
}
}
