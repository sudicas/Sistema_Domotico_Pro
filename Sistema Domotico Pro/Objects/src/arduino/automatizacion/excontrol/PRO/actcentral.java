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

public class actcentral extends Activity implements B4AActivity{
	public static actcentral mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actcentral");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actcentral).");
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
		activityBA = new BA(this, layout, processBA, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actcentral");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "arduino.automatizacion.excontrol.PRO.actcentral", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actcentral) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actcentral) Resume **");
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
		return actcentral.class;
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
        BA.LogInfo("** Activity (actcentral) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (actcentral) Resume **");
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
public static String _initialname = "";
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public static String _name = "";
public static String _description = "";
public static int _icon = 0;
public static String _ipi = "";
public static String _ipo = "";
public static int _portin = 0;
public static int _portout = 0;
public static String _mail = "";
public static String _pass = "";
public static boolean _conexionsegura = false;
public anywheresoftware.b4a.objects.ButtonWrapper _cmdnew = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public arduino.automatizacion.excontrol.PRO.main _main = null;
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
 //BA.debugLineNum = 37;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 41;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = Fals";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 42;BA.debugLine="Activity.LoadLayout (\"frmcentrales\")";
mostCurrent._activity.LoadLayout("frmcentrales",mostCurrent.activityBA);
 //BA.debugLineNum = 44;BA.debugLine="ListView1.Height = PerYToCurrent(83)";
mostCurrent._listview1.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (83),mostCurrent.activityBA));
 //BA.debugLineNum = 45;BA.debugLine="ListView1.Width =Activity.Width";
mostCurrent._listview1.setWidth(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 47;BA.debugLine="CmdNew.Top=PerYToCurrent(85)";
mostCurrent._cmdnew.setTop(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 //BA.debugLineNum = 48;BA.debugLine="CmdNew.Height =PerYToCurrent(15)";
mostCurrent._cmdnew.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (15),mostCurrent.activityBA));
 //BA.debugLineNum = 49;BA.debugLine="CmdNew.Width =Activity.Width";
mostCurrent._cmdnew.setWidth(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 50;BA.debugLine="CmdNew.Text = ValoresComunes.GetLanString (\"reg18";
mostCurrent._cmdnew.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg185")));
 //BA.debugLineNum = 54;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 186;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 187;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 188;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
 //BA.debugLineNum = 56;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 57;BA.debugLine="If ValoresComunes.CloseApp =True Then";
if (mostCurrent._valorescomunes._closeapp==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 58;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 59;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 61;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = True";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 63;BA.debugLine="CmdNew.Text =ValoresComunes.GetLanString (\"reg12";
mostCurrent._cmdnew.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg128")));
 //BA.debugLineNum = 65;BA.debugLine="If InitialName=\"\" Then";
if ((_initialname).equals("")) { 
 //BA.debugLineNum = 66;BA.debugLine="IniciaNewCentral";
_inicianewcentral();
 }else {
 //BA.debugLineNum = 69;BA.debugLine="Name=InitialName";
mostCurrent._name = _initialname;
 //BA.debugLineNum = 70;BA.debugLine="If File.Exists ( File.DirInternal ,\"Conexion\" &";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conexion"+_initialname)) { 
 //BA.debugLineNum = 71;BA.debugLine="Dim lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 72;BA.debugLine="lst = File.ReadList(File.DirInternal  ,\"Conexi";
_lst = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conexion"+_initialname);
 //BA.debugLineNum = 73;BA.debugLine="IpI= lst.Get (0)";
mostCurrent._ipi = BA.ObjectToString(_lst.Get((int) (0)));
 //BA.debugLineNum = 74;BA.debugLine="IpO= lst.Get (1)";
mostCurrent._ipo = BA.ObjectToString(_lst.Get((int) (1)));
 //BA.debugLineNum = 75;BA.debugLine="PortIn= lst.Get (2)";
_portin = (int)(BA.ObjectToNumber(_lst.Get((int) (2))));
 //BA.debugLineNum = 76;BA.debugLine="PortOut= lst.Get (3)";
_portout = (int)(BA.ObjectToNumber(_lst.Get((int) (3))));
 //BA.debugLineNum = 77;BA.debugLine="Description=ValoresComunes.GetLanStringDefault";
mostCurrent._description = mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"PC","Push to connect");
 //BA.debugLineNum = 78;BA.debugLine="Mail =\"\"";
mostCurrent._mail = "";
 //BA.debugLineNum = 79;BA.debugLine="Pass =\"\"";
mostCurrent._pass = "";
 //BA.debugLineNum = 80;BA.debugLine="ConexionSegura =False";
_conexionsegura = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 81;BA.debugLine="Icon=0";
_icon = (int) (0);
 //BA.debugLineNum = 82;BA.debugLine="File.Delete ( File.DirInternal ,\"Conexion\" & I";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conexion"+_initialname);
 //BA.debugLineNum = 83;BA.debugLine="GuardaCentral";
_guardacentral();
 }else {
 //BA.debugLineNum = 90;BA.debugLine="If File.Exists ( File.DirInternal ,\"Conect\" &";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conect"+_initialname)) { 
 //BA.debugLineNum = 91;BA.debugLine="Dim lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 92;BA.debugLine="lst = File.ReadList(File.DirInternal  ,\"Conec";
_lst = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conect"+_initialname);
 //BA.debugLineNum = 93;BA.debugLine="IpI= lst.Get (0)";
mostCurrent._ipi = BA.ObjectToString(_lst.Get((int) (0)));
 //BA.debugLineNum = 94;BA.debugLine="IpO= lst.Get (1)";
mostCurrent._ipo = BA.ObjectToString(_lst.Get((int) (1)));
 //BA.debugLineNum = 95;BA.debugLine="PortIn= lst.Get (2)";
_portin = (int)(BA.ObjectToNumber(_lst.Get((int) (2))));
 //BA.debugLineNum = 96;BA.debugLine="PortOut= lst.Get (3)";
_portout = (int)(BA.ObjectToNumber(_lst.Get((int) (3))));
 //BA.debugLineNum = 97;BA.debugLine="Description=lst.Get (4)";
mostCurrent._description = BA.ObjectToString(_lst.Get((int) (4)));
 //BA.debugLineNum = 99;BA.debugLine="Icon=lst.Get (5)";
_icon = (int)(BA.ObjectToNumber(_lst.Get((int) (5))));
 //BA.debugLineNum = 100;BA.debugLine="Mail =lst.Get (6)";
mostCurrent._mail = BA.ObjectToString(_lst.Get((int) (6)));
 //BA.debugLineNum = 101;BA.debugLine="Pass =lst.Get (7)";
mostCurrent._pass = BA.ObjectToString(_lst.Get((int) (7)));
 //BA.debugLineNum = 102;BA.debugLine="ConexionSegura =lst.Get (8)";
_conexionsegura = BA.ObjectToBoolean(_lst.Get((int) (8)));
 //BA.debugLineNum = 104;BA.debugLine="File.Delete ( File.DirInternal ,\"Conexion\" &";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conexion"+_initialname);
 //BA.debugLineNum = 105;BA.debugLine="GuardaCentral";
_guardacentral();
 }else {
 //BA.debugLineNum = 107;BA.debugLine="IniciaNewCentral";
_inicianewcentral();
 //BA.debugLineNum = 108;BA.debugLine="Name=InitialName";
mostCurrent._name = _initialname;
 };
 };
 };
 //BA.debugLineNum = 113;BA.debugLine="RefrescaPantalla";
_refrescapantalla();
 }else {
 //BA.debugLineNum = 115;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 116;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 };
 //BA.debugLineNum = 119;BA.debugLine="End Sub";
return "";
}
public static String  _cmdnew_click() throws Exception{
 //BA.debugLineNum = 353;BA.debugLine="Sub CmdNew_Click";
 //BA.debugLineNum = 354;BA.debugLine="GuardaCentral";
_guardacentral();
 //BA.debugLineNum = 355;BA.debugLine="ValoresComunes.SetImgDevice(Name,Icon)";
mostCurrent._valorescomunes._setimgdevice(mostCurrent.activityBA,mostCurrent._name,_icon);
 //BA.debugLineNum = 356;BA.debugLine="ActMosaico.UpdateCentral=True";
mostCurrent._actmosaico._updatecentral = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 357;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 358;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 17;BA.debugLine="Private ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Dim Name As String";
mostCurrent._name = "";
 //BA.debugLineNum = 19;BA.debugLine="Dim Description As String";
mostCurrent._description = "";
 //BA.debugLineNum = 20;BA.debugLine="Dim Icon As Int";
_icon = 0;
 //BA.debugLineNum = 21;BA.debugLine="Dim IpI As String";
mostCurrent._ipi = "";
 //BA.debugLineNum = 22;BA.debugLine="Dim IpO As String";
mostCurrent._ipo = "";
 //BA.debugLineNum = 23;BA.debugLine="Dim PortIn As Int";
_portin = 0;
 //BA.debugLineNum = 24;BA.debugLine="Dim PortOut As Int";
_portout = 0;
 //BA.debugLineNum = 25;BA.debugLine="Dim Mail As String";
mostCurrent._mail = "";
 //BA.debugLineNum = 26;BA.debugLine="Dim Pass As String";
mostCurrent._pass = "";
 //BA.debugLineNum = 27;BA.debugLine="Dim ConexionSegura As Boolean";
_conexionsegura = false;
 //BA.debugLineNum = 34;BA.debugLine="Private CmdNew As Button";
mostCurrent._cmdnew = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return "";
}
public static String  _guardacentral() throws Exception{
arduino.automatizacion.excontrol.PRO.valorescomunes._arduino _c = null;
 //BA.debugLineNum = 317;BA.debugLine="Sub GuardaCentral";
 //BA.debugLineNum = 318;BA.debugLine="If ValoresComunes.Centrales .IndexOf (Name)=-1 Th";
if (mostCurrent._valorescomunes._centrales.IndexOf((Object)(mostCurrent._name))==-1) { 
 //BA.debugLineNum = 319;BA.debugLine="ValoresComunes.Centrales .Add (Name)";
mostCurrent._valorescomunes._centrales.Add((Object)(mostCurrent._name));
 };
 //BA.debugLineNum = 325;BA.debugLine="If InitialName<>\"\" Then";
if ((_initialname).equals("") == false) { 
 //BA.debugLineNum = 326;BA.debugLine="ValoresComunes.CambiarNombreCentral(InitialName,";
mostCurrent._valorescomunes._cambiarnombrecentral(mostCurrent.activityBA,_initialname,mostCurrent._name);
 };
 //BA.debugLineNum = 333;BA.debugLine="Dim c As Arduino";
_c = new arduino.automatizacion.excontrol.PRO.valorescomunes._arduino();
 //BA.debugLineNum = 334;BA.debugLine="c.Nombre  =Name";
_c.Nombre = mostCurrent._name;
 //BA.debugLineNum = 335;BA.debugLine="c.IpIn =IpI";
_c.IpIn = mostCurrent._ipi;
 //BA.debugLineNum = 338;BA.debugLine="c.IpOut =IpO";
_c.IpOut = mostCurrent._ipo;
 //BA.debugLineNum = 340;BA.debugLine="c.PuertoIn =PortIn";
_c.PuertoIn = _portin;
 //BA.debugLineNum = 341;BA.debugLine="c.PuertoOut =PortOut";
_c.PuertoOut = _portout;
 //BA.debugLineNum = 342;BA.debugLine="c.descripcion =Description";
_c.Descripcion = mostCurrent._description;
 //BA.debugLineNum = 343;BA.debugLine="c.Icon =Icon";
_c.Icon = _icon;
 //BA.debugLineNum = 344;BA.debugLine="c.Mail =Mail";
_c.mail = mostCurrent._mail;
 //BA.debugLineNum = 345;BA.debugLine="c.Password  =Pass";
_c.Password = mostCurrent._pass;
 //BA.debugLineNum = 346;BA.debugLine="c.ConexionSegura=ConexionSegura";
_c.ConexionSegura = _conexionsegura;
 //BA.debugLineNum = 347;BA.debugLine="ValoresComunes.GuardaCentral (c)";
mostCurrent._valorescomunes._guardacentral(mostCurrent.activityBA,_c);
 //BA.debugLineNum = 349;BA.debugLine="ValoresComunes.GuardarCentrales";
mostCurrent._valorescomunes._guardarcentrales(mostCurrent.activityBA);
 //BA.debugLineNum = 352;BA.debugLine="End Sub";
return "";
}
public static String  _inicianewcentral() throws Exception{
boolean _disponible = false;
int _c = 0;
 //BA.debugLineNum = 120;BA.debugLine="Sub IniciaNewCentral";
 //BA.debugLineNum = 121;BA.debugLine="Dim disponible As Boolean";
_disponible = false;
 //BA.debugLineNum = 122;BA.debugLine="disponible = False";
_disponible = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 123;BA.debugLine="Dim c As Int";
_c = 0;
 //BA.debugLineNum = 124;BA.debugLine="c=1";
_c = (int) (1);
 //BA.debugLineNum = 125;BA.debugLine="Do While disponible = False";
while (_disponible==anywheresoftware.b4a.keywords.Common.False) {
 //BA.debugLineNum = 126;BA.debugLine="Name= \"Unit \"&c";
mostCurrent._name = "Unit "+BA.NumberToString(_c);
 //BA.debugLineNum = 127;BA.debugLine="If File.Exists (File.DirInternal ,\"Conect\" & Nam";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Conect"+mostCurrent._name)==anywheresoftware.b4a.keywords.Common.False) { 
_disponible = anywheresoftware.b4a.keywords.Common.True;};
 //BA.debugLineNum = 128;BA.debugLine="c=c+1";
_c = (int) (_c+1);
 }
;
 //BA.debugLineNum = 130;BA.debugLine="IpI= \"192.168.1.200\"";
mostCurrent._ipi = "192.168.1.200";
 //BA.debugLineNum = 131;BA.debugLine="IpO= \"192.168.1.200\"";
mostCurrent._ipo = "192.168.1.200";
 //BA.debugLineNum = 132;BA.debugLine="PortIn= 5000";
_portin = (int) (5000);
 //BA.debugLineNum = 133;BA.debugLine="PortOut= 5000";
_portout = (int) (5000);
 //BA.debugLineNum = 134;BA.debugLine="Description=ValoresComunes.GetLanStringDefault  (";
mostCurrent._description = mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"PC","Push to connect");
 //BA.debugLineNum = 135;BA.debugLine="Mail =\"\"";
mostCurrent._mail = "";
 //BA.debugLineNum = 136;BA.debugLine="Pass =\"\"";
mostCurrent._pass = "";
 //BA.debugLineNum = 137;BA.debugLine="ConexionSegura =False";
_conexionsegura = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 138;BA.debugLine="Icon=0";
_icon = (int) (0);
 //BA.debugLineNum = 140;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
int _result = 0;
anywheresoftware.b4a.agraham.dialogs.InputDialog _dial = null;
anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog _di = null;
 //BA.debugLineNum = 192;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 194;BA.debugLine="If Value <100 Then";
if ((double)(BA.ObjectToNumber(_value))<100) { 
 //BA.debugLineNum = 195;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 197;BA.debugLine="Select  Value";
switch (BA.switchObjectToInt(_value,(Object)(0),(Object)(1),(Object)(2),(Object)(3),(Object)(4),(Object)(5),(Object)(6),(Object)(7),(Object)(8),(Object)(9),(Object)(10),(Object)(11))) {
case 0: {
 //BA.debugLineNum = 199;BA.debugLine="Dim dial As InputDialog";
_dial = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 200;BA.debugLine="dial.Input = Name";
_dial.setInput(mostCurrent._name);
 //BA.debugLineNum = 202;BA.debugLine="Result = dial.Show ( ValoresComunes.GetLanStri";
_result = _dial.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg119"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg104"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 203;BA.debugLine="If Result= DialogResponse.POSITIVE Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 204;BA.debugLine="If dial.Input =\"\" Then Return";
if ((_dial.getInput()).equals("")) { 
if (true) return "";};
 //BA.debugLineNum = 205;BA.debugLine="If ValoresComunes.Centrales .IsInitialized Th";
if (mostCurrent._valorescomunes._centrales.IsInitialized()) { 
 //BA.debugLineNum = 206;BA.debugLine="If ValoresComunes.Centrales .IndexOf (dial.I";
if (mostCurrent._valorescomunes._centrales.IndexOf((Object)(_dial.getInput()))!=-1) { 
 //BA.debugLineNum = 207;BA.debugLine="Msgbox(ValoresComunes.GetLanString (\"reg121";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg121")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg120")),mostCurrent.activityBA);
 //BA.debugLineNum = 208;BA.debugLine="Return";
if (true) return "";
 };
 };
 //BA.debugLineNum = 211;BA.debugLine="Name=dial.Input";
mostCurrent._name = _dial.getInput();
 };
 break; }
case 1: {
 //BA.debugLineNum = 214;BA.debugLine="Dim dial As InputDialog";
_dial = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 215;BA.debugLine="dial.Input = Description";
_dial.setInput(mostCurrent._description);
 //BA.debugLineNum = 217;BA.debugLine="Result = dial.Show ( ValoresComunes.GetLanStri";
_result = _dial.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg112"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg104"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 218;BA.debugLine="If Result= DialogResponse.POSITIVE Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 219;BA.debugLine="If dial.Input =\"\" Then Return";
if ((_dial.getInput()).equals("")) { 
if (true) return "";};
 //BA.debugLineNum = 220;BA.debugLine="Description=dial.Input";
mostCurrent._description = _dial.getInput();
 };
 break; }
case 2: {
 //BA.debugLineNum = 223;BA.debugLine="RefrescaIcoPantalla";
_refrescaicopantalla();
 //BA.debugLineNum = 224;BA.debugLine="Return";
if (true) return "";
 break; }
case 3: {
 //BA.debugLineNum = 226;BA.debugLine="Dim dial As InputDialog";
_dial = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 227;BA.debugLine="dial.Input = IpI";
_dial.setInput(mostCurrent._ipi);
 //BA.debugLineNum = 229;BA.debugLine="Result = dial.Show ( ValoresComunes.GetLanStri";
_result = _dial.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg168"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg104"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 230;BA.debugLine="If Result= DialogResponse.POSITIVE Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 231;BA.debugLine="IpI =dial.Input";
mostCurrent._ipi = _dial.getInput();
 };
 break; }
case 4: {
 //BA.debugLineNum = 237;BA.debugLine="Dim dial As InputDialog";
_dial = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 238;BA.debugLine="dial.Input = IpO";
_dial.setInput(mostCurrent._ipo);
 //BA.debugLineNum = 240;BA.debugLine="Result = dial.Show ( ValoresComunes.GetLanStri";
_result = _dial.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg115"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg104"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 241;BA.debugLine="If Result= DialogResponse.POSITIVE Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 242;BA.debugLine="IpO =dial.Input";
mostCurrent._ipo = _dial.getInput();
 };
 break; }
case 5: {
 //BA.debugLineNum = 245;BA.debugLine="Dim di As NumberDialog";
_di = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 246;BA.debugLine="di.Digits =5";
_di.setDigits((int) (5));
 //BA.debugLineNum = 247;BA.debugLine="di.Number = PortIn";
_di.setNumber(_portin);
 //BA.debugLineNum = 248;BA.debugLine="Result= di.Show (ValoresComunes.GetLanString (";
_result = _di.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg122"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Ok"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 249;BA.debugLine="If Result= DialogResponse.POSITIVE Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 250;BA.debugLine="PortIn =di.Number";
_portin = _di.getNumber();
 };
 break; }
case 6: {
 //BA.debugLineNum = 253;BA.debugLine="Dim di As NumberDialog";
_di = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 254;BA.debugLine="di.Digits =5";
_di.setDigits((int) (5));
 //BA.debugLineNum = 255;BA.debugLine="di.Number = PortOut";
_di.setNumber(_portout);
 //BA.debugLineNum = 256;BA.debugLine="Result= di.Show (ValoresComunes.GetLanString (";
_result = _di.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg123"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Ok"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 257;BA.debugLine="If Result= DialogResponse.POSITIVE Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 258;BA.debugLine="PortOut =di.Number";
_portout = _di.getNumber();
 };
 break; }
case 7: {
 //BA.debugLineNum = 262;BA.debugLine="Dim dial As InputDialog";
_dial = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 263;BA.debugLine="dial.Input = Mail";
_dial.setInput(mostCurrent._mail);
 //BA.debugLineNum = 265;BA.debugLine="Result = dial.Show ( ValoresComunes.GetLanStri";
_result = _dial.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg100"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg104"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 266;BA.debugLine="If Result= DialogResponse.POSITIVE Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 267;BA.debugLine="Mail=dial.Input";
mostCurrent._mail = _dial.getInput();
 };
 break; }
case 8: {
 //BA.debugLineNum = 270;BA.debugLine="Dim dial As InputDialog";
_dial = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 271;BA.debugLine="dial.Input = \"\"";
_dial.setInput("");
 //BA.debugLineNum = 273;BA.debugLine="Result = dial.Show ( ValoresComunes.GetLanStri";
_result = _dial.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg101"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg104"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 274;BA.debugLine="If Result= DialogResponse.POSITIVE Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 275;BA.debugLine="If dial.Input .Length =8 Then";
if (_dial.getInput().length()==8) { 
 //BA.debugLineNum = 276;BA.debugLine="Pass=dial.Input";
mostCurrent._pass = _dial.getInput();
 }else {
 //BA.debugLineNum = 278;BA.debugLine="Msgbox(\"Len = 8\",ValoresComunes.GetLanString";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Len = 8"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg120")),mostCurrent.activityBA);
 };
 };
 break; }
case 9: {
 //BA.debugLineNum = 285;BA.debugLine="If ConexionSegura Then";
if (_conexionsegura) { 
 //BA.debugLineNum = 286;BA.debugLine="ConexionSegura=False";
_conexionsegura = anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 288;BA.debugLine="If Pass.Length =8 Then";
if (mostCurrent._pass.length()==8) { 
 //BA.debugLineNum = 289;BA.debugLine="ConexionSegura=True";
_conexionsegura = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 291;BA.debugLine="Msgbox(ValoresComunes.GetLanStringDefault(\"p";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"passr","password required")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg120")),mostCurrent.activityBA);
 };
 };
 break; }
case 10: {
 //BA.debugLineNum = 297;BA.debugLine="ValoresComunes.Circuitos (0).Rango =1000";
mostCurrent._valorescomunes._circuitos[(int) (0)].Rango = (int) (1000);
 //BA.debugLineNum = 298;BA.debugLine="ValoresComunes.Sensores  (0).Rango =1000";
mostCurrent._valorescomunes._sensores[(int) (0)].Rango = (int) (1000);
 //BA.debugLineNum = 299;BA.debugLine="ValoresComunes.GuardarConfigCircuitos";
mostCurrent._valorescomunes._guardarconfigcircuitos(mostCurrent.activityBA);
 //BA.debugLineNum = 300;BA.debugLine="ValoresComunes.GuardarConfigSensores";
mostCurrent._valorescomunes._guardarconfigsensores(mostCurrent.activityBA);
 //BA.debugLineNum = 302;BA.debugLine="ToastMessageShow(ValoresComunes.GetLanStringDe";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"reg139","COMPLETED")),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 303;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 304;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 11: {
 //BA.debugLineNum = 307;BA.debugLine="StartActivity(ActWifis)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actwifis.getObject()));
 break; }
}
;
 //BA.debugLineNum = 309;BA.debugLine="RefrescaPantalla";
_refrescapantalla();
 }else {
 //BA.debugLineNum = 311;BA.debugLine="Icon =Value-100";
_icon = (int) ((double)(BA.ObjectToNumber(_value))-100);
 //BA.debugLineNum = 312;BA.debugLine="RefrescaPantalla";
_refrescapantalla();
 };
 //BA.debugLineNum = 316;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Public InitialName As String";
_initialname = "";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _refrescaicopantalla() throws Exception{
int _c = 0;
 //BA.debugLineNum = 172;BA.debugLine="Sub RefrescaIcoPantalla";
 //BA.debugLineNum = 173;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 174;BA.debugLine="Dim c As Int";
_c = 0;
 //BA.debugLineNum = 175;BA.debugLine="For c=0 To 6";
{
final int step3 = 1;
final int limit3 = (int) (6);
_c = (int) (0) ;
for (;(step3 > 0 && _c <= limit3) || (step3 < 0 && _c >= limit3) ;_c = ((int)(0 + _c + step3))  ) {
 //BA.debugLineNum = 176;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"Icon","Select Icon")),BA.ObjectToCharSequence(""),(android.graphics.Bitmap)(mostCurrent._valorescomunes._getcentralimg(mostCurrent.activityBA,_c,anywheresoftware.b4a.keywords.Common.True).getObject()),(Object)(_c+100));
 }
};
 //BA.debugLineNum = 184;BA.debugLine="End Sub";
return "";
}
public static String  _refrescapantalla() throws Exception{
 //BA.debugLineNum = 141;BA.debugLine="Sub RefrescaPantalla";
 //BA.debugLineNum = 142;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 143;BA.debugLine="ListView1.AddTwoLinesAndBitmap2(Name,ValoresComun";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._name),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg111")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._config(mostCurrent.activityBA).getObject()),(Object)(0));
 //BA.debugLineNum = 144;BA.debugLine="ListView1.AddTwoLinesAndBitmap2(Description, Valo";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._description),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg112")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._config(mostCurrent.activityBA).getObject()),(Object)(1));
 //BA.debugLineNum = 145;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComunes.L";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._lanstring.GetDefault((Object)("Icon"),(Object)("Select Icon"))),BA.ObjectToCharSequence(""),(android.graphics.Bitmap)(mostCurrent._valorescomunes._getcentralimg(mostCurrent.activityBA,_icon,anywheresoftware.b4a.keywords.Common.True).getObject()),(Object)(2));
 //BA.debugLineNum = 149;BA.debugLine="ListView1.AddTwoLinesAndBitmap2(IpI,ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._ipi),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg168")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._config(mostCurrent.activityBA).getObject()),(Object)(3));
 //BA.debugLineNum = 150;BA.debugLine="ListView1.AddTwoLinesAndBitmap2(IpO,ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._ipo),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg115")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._config(mostCurrent.activityBA).getObject()),(Object)(4));
 //BA.debugLineNum = 151;BA.debugLine="ListView1.AddTwoLinesAndBitmap2(PortIn,ValoresCom";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_portin),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg116")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._config(mostCurrent.activityBA).getObject()),(Object)(5));
 //BA.debugLineNum = 152;BA.debugLine="ListView1.AddTwoLinesAndBitmap2(PortOut,ValoresCo";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_portout),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg117")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._config(mostCurrent.activityBA).getObject()),(Object)(6));
 //BA.debugLineNum = 154;BA.debugLine="ListView1.AddTwoLinesAndBitmap2( Mail,ValoresComu";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._mail),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg100")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._config(mostCurrent.activityBA).getObject()),(Object)(7));
 //BA.debugLineNum = 155;BA.debugLine="If Pass.Length >7  Then";
if (mostCurrent._pass.length()>7) { 
 //BA.debugLineNum = 156;BA.debugLine="ListView1.AddTwoLinesAndBitmap2(\"********\",Valor";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence("********"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg101")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._config(mostCurrent.activityBA).getObject()),(Object)(8));
 }else {
 //BA.debugLineNum = 158;BA.debugLine="ListView1.AddTwoLinesAndBitmap2(\"\",ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(""),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg101")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._config(mostCurrent.activityBA).getObject()),(Object)(8));
 };
 //BA.debugLineNum = 161;BA.debugLine="If ConexionSegura Then";
if (_conexionsegura) { 
 //BA.debugLineNum = 162;BA.debugLine="ListView1.AddTwoLinesAndBitmap2(ValoresComunes.G";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg102")),BA.ObjectToCharSequence(""),(android.graphics.Bitmap)(mostCurrent._valorescomunes._checkon(mostCurrent.activityBA).getObject()),(Object)(9));
 }else {
 //BA.debugLineNum = 164;BA.debugLine="ListView1.AddTwoLinesAndBitmap2(ValoresComunes.G";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg103")),BA.ObjectToCharSequence(""),(android.graphics.Bitmap)(mostCurrent._valorescomunes._checkoff(mostCurrent.activityBA).getObject()),(Object)(9));
 };
 //BA.debugLineNum = 167;BA.debugLine="ListView1.AddTwoLinesAndBitmap2(ValoresComunes.Ge";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"RSC","Reload system configuration")),BA.ObjectToCharSequence(""),(android.graphics.Bitmap)(mostCurrent._valorescomunes._config(mostCurrent.activityBA).getObject()),(Object)(10));
 //BA.debugLineNum = 168;BA.debugLine="ListView1.AddTwoLinesAndBitmap2(ValoresComunes.Ge";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"SiW","Set internal Wifi")),BA.ObjectToCharSequence(""),(android.graphics.Bitmap)(mostCurrent._valorescomunes._config(mostCurrent.activityBA).getObject()),(Object)(11));
 //BA.debugLineNum = 170;BA.debugLine="End Sub";
return "";
}
}
