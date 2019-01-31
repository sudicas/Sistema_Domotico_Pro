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

public class actconsignas extends Activity implements B4AActivity{
	public static actconsignas mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actconsignas");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actconsignas).");
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
		activityBA = new BA(this, layout, processBA, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actconsignas");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "arduino.automatizacion.excontrol.PRO.actconsignas", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actconsignas) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actconsignas) Resume **");
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
		return actconsignas.class;
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
        BA.LogInfo("** Activity (actconsignas) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (actconsignas) Resume **");
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
public static anywheresoftware.b4a.objects.SocketWrapper.UDPSocket _udpsocket1 = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public arduino.automatizacion.excontrol.PRO.slidemenu _sm = null;
public static int[] _valores = null;
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
int _h = 0;
 //BA.debugLineNum = 30;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 31;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = Fals";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 32;BA.debugLine="Activity.LoadLayout (\"frmadministrador\")";
mostCurrent._activity.LoadLayout("frmadministrador",mostCurrent.activityBA);
 //BA.debugLineNum = 34;BA.debugLine="Activity.Title =ValoresComunes.GetLanString (\"reg";
mostCurrent._activity.setTitle(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg133")));
 //BA.debugLineNum = 37;BA.debugLine="ListView1.height = 100%y - 52dip";
mostCurrent._listview1.setHeight((int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (52))));
 //BA.debugLineNum = 38;BA.debugLine="ListView1.Width =Activity.Width";
mostCurrent._listview1.setWidth(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 39;BA.debugLine="ListView1.Top =52dip";
mostCurrent._listview1.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (52)));
 //BA.debugLineNum = 44;BA.debugLine="Dim h As Int";
_h = 0;
 //BA.debugLineNum = 45;BA.debugLine="h=75dip";
_h = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75));
 //BA.debugLineNum = 48;BA.debugLine="ListView1.TwoLinesAndBitmap .ItemHeight =h";
mostCurrent._listview1.getTwoLinesAndBitmap().setItemHeight(_h);
 //BA.debugLineNum = 49;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.Width=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setWidth(_h);
 //BA.debugLineNum = 50;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.height=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setHeight(_h);
 //BA.debugLineNum = 52;BA.debugLine="ListView1.TwoLinesAndBitmap.Label .Left =h + h/9";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setLeft((int) (_h+_h/(double)9));
 //BA.debugLineNum = 53;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.height  =h/1.8";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setHeight((int) (_h/(double)1.8));
 //BA.debugLineNum = 54;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.Gravity = Bit.O";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.BOTTOM));
 //BA.debugLineNum = 56;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel .Left =Li";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setLeft(mostCurrent._listview1.getTwoLinesAndBitmap().Label.getLeft());
 //BA.debugLineNum = 57;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.height";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setHeight((int) (_h-mostCurrent._listview1.getTwoLinesAndBitmap().Label.getHeight()));
 //BA.debugLineNum = 58;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.top   = L";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setTop(mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.getHeight());
 //BA.debugLineNum = 59;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.Gravity =";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 61;BA.debugLine="sm.Initialize(Activity, Me,  \"SlideMenu\", 42dip,";
mostCurrent._sm._initialize(mostCurrent.activityBA,mostCurrent._activity,actconsignas.getObject(),"SlideMenu",anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (42)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)));
 //BA.debugLineNum = 62;BA.debugLine="ValoresComunes.BuldMenus (sm,9)";
mostCurrent._valorescomunes._buldmenus(mostCurrent.activityBA,mostCurrent._sm,(int) (9));
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 386;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 388;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK And sm.isVisib";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK && mostCurrent._sm._isvisible()) { 
 //BA.debugLineNum = 389;BA.debugLine="sm.Hide";
mostCurrent._sm._hide();
 //BA.debugLineNum = 390;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 394;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_MENU Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_MENU) { 
 //BA.debugLineNum = 395;BA.debugLine="If sm.isVisible Then sm.Hide Else sm.Show";
if (mostCurrent._sm._isvisible()) { 
mostCurrent._sm._hide();}
else {
mostCurrent._sm._show();};
 //BA.debugLineNum = 396;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 398;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 111;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 113;BA.debugLine="UDPSocket1.Close";
_udpsocket1.Close();
 //BA.debugLineNum = 114;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 115;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
String _t = "";
 //BA.debugLineNum = 66;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 67;BA.debugLine="If ValoresComunes.CloseApp =True Then";
if (mostCurrent._valorescomunes._closeapp==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 68;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 69;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 71;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = True";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 74;BA.debugLine="If ValoresComunes.SetPoint .Size >0 Then";
if (mostCurrent._valorescomunes._setpoint.getSize()>0) { 
 //BA.debugLineNum = 75;BA.debugLine="If ImgCargando.IsInitialized = False Then";
if (mostCurrent._imgcargando.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 76;BA.debugLine="ImgCargando.Initialize (\"ImgCargando\")";
mostCurrent._imgcargando.Initialize(mostCurrent.activityBA,"ImgCargando");
 //BA.debugLineNum = 77;BA.debugLine="ImgCargando.Bitmap = ValoresComunes.Cargando";
mostCurrent._imgcargando.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._cargando(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 78;BA.debugLine="ImgCargando.Gravity = Gravity.FILL";
mostCurrent._imgcargando.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 79;BA.debugLine="Activity.AddView (ImgCargando,  PerXToCurrent(";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._imgcargando.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (40),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA));
 };
 //BA.debugLineNum = 81;BA.debugLine="If UDPSocket1.IsInitialized = False Then  Valor";
if (_udpsocket1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._valorescomunes._iniudp(mostCurrent.activityBA,_udpsocket1);};
 //BA.debugLineNum = 82;BA.debugLine="AniCargando.InitializeRotateCenter( \"AniCargand";
mostCurrent._anicargando.InitializeRotateCenter(mostCurrent.activityBA,"AniCargando",(float) (0),(float) (180),(android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 83;BA.debugLine="AniCargando.Duration = 1000";
mostCurrent._anicargando.setDuration((long) (1000));
 //BA.debugLineNum = 84;BA.debugLine="AniCargando.RepeatCount =5";
mostCurrent._anicargando.setRepeatCount((int) (5));
 //BA.debugLineNum = 85;BA.debugLine="AniCargando.RepeatMode = AniCargando.REPEAT_";
mostCurrent._anicargando.setRepeatMode(mostCurrent._anicargando.REPEAT_REVERSE);
 //BA.debugLineNum = 89;BA.debugLine="Dim T As String";
_t = "";
 //BA.debugLineNum = 90;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 91;BA.debugLine="T=\"SETPOINT\" & ValoresComunes.Central.Password";
_t = "SETPOINT"+mostCurrent._valorescomunes._central.Password;
 }else {
 //BA.debugLineNum = 93;BA.debugLine="T=\"SETPOINT\"";
_t = "SETPOINT";
 };
 //BA.debugLineNum = 96;BA.debugLine="SendTrama(T.GetBytes (\"UTF8\") )";
_sendtrama(_t.getBytes("UTF8"));
 }else {
 //BA.debugLineNum = 98;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 99;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 };
 }else {
 //BA.debugLineNum = 106;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 107;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 };
 //BA.debugLineNum = 109;BA.debugLine="End Sub";
return "";
}
public static String  _anicargando_animationend() throws Exception{
 //BA.debugLineNum = 318;BA.debugLine="Sub AniCargando_AnimationEnd";
 //BA.debugLineNum = 320;BA.debugLine="If PaqueteEnviado = True Then";
if (_paqueteenviado==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 321;BA.debugLine="AniCargando.Start (ImgCargando)";
mostCurrent._anicargando.Start((android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 322;BA.debugLine="SendTrama(TramaEnviada)";
_sendtrama(_tramaenviada);
 };
 //BA.debugLineNum = 324;BA.debugLine="End Sub";
return "";
}
public static String  _btnshow_click() throws Exception{
 //BA.debugLineNum = 399;BA.debugLine="Sub btnShow_Click";
 //BA.debugLineNum = 400;BA.debugLine="sm.Show";
mostCurrent._sm._show();
 //BA.debugLineNum = 401;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 18;BA.debugLine="Dim ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Dim sm As SlideMenu";
mostCurrent._sm = new arduino.automatizacion.excontrol.PRO.slidemenu();
 //BA.debugLineNum = 21;BA.debugLine="Dim Valores(10) As Int";
_valores = new int[(int) (10)];
;
 //BA.debugLineNum = 24;BA.debugLine="Dim TramaEnviada() As Byte";
_tramaenviada = new byte[(int) (0)];
;
 //BA.debugLineNum = 25;BA.debugLine="Dim ImgCargando As ImageView";
mostCurrent._imgcargando = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Dim AniCargando As  Animation";
mostCurrent._anicargando = new anywheresoftware.b4a.objects.AnimationWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Dim PaqueteEnviado As Boolean";
_paqueteenviado = false;
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
arduino.automatizacion.excontrol.PRO.valorescomunes._sp _spo = null;
anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog _dialogo = null;
String _ma = "";
int _result = 0;
int _longtrama = 0;
byte[] _data = null;
short _val = (short)0;
 //BA.debugLineNum = 120;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 121;BA.debugLine="Dim Spo As Sp";
_spo = new arduino.automatizacion.excontrol.PRO.valorescomunes._sp();
 //BA.debugLineNum = 122;BA.debugLine="Spo= ValoresComunes.SetPoint.Get  (Position)";
_spo = (arduino.automatizacion.excontrol.PRO.valorescomunes._sp)(mostCurrent._valorescomunes._setpoint.Get(_position));
 //BA.debugLineNum = 125;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 126;BA.debugLine="Dim Ma As String = Spo.Maximo";
_ma = BA.NumberToString(_spo.Maximo);
 //BA.debugLineNum = 128;BA.debugLine="Dialogo.Digits =Ma.Length";
_dialogo.setDigits(_ma.length());
 //BA.debugLineNum = 131;BA.debugLine="Dialogo.Number = Valores(Position)";
_dialogo.setNumber(_valores[_position]);
 //BA.debugLineNum = 133;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 134;BA.debugLine="Result = Dialogo.Show(ValoresComunes.GetLanString";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"reg90","New Value"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 136;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 139;BA.debugLine="If Dialogo.number < Spo.Maximo  +1  And   Dialog";
if (_dialogo.getNumber()<_spo.Maximo+1 && _dialogo.getNumber()>_spo.Minimo-1) { 
 //BA.debugLineNum = 140;BA.debugLine="Valores(Position)= Dialogo.Number";
_valores[_position] = _dialogo.getNumber();
 //BA.debugLineNum = 141;BA.debugLine="Dim LongTrama As Int";
_longtrama = 0;
 //BA.debugLineNum = 142;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 143;BA.debugLine="LongTrama= 15";
_longtrama = (int) (15);
 }else {
 //BA.debugLineNum = 145;BA.debugLine="LongTrama= 7";
_longtrama = (int) (7);
 };
 //BA.debugLineNum = 148;BA.debugLine="Dim data(LongTrama) As Byte";
_data = new byte[_longtrama];
;
 //BA.debugLineNum = 149;BA.debugLine="data(0)= 87	'W";
_data[(int) (0)] = (byte) (87);
 //BA.debugLineNum = 150;BA.debugLine="data(1)= 67	'C";
_data[(int) (1)] = (byte) (67);
 //BA.debugLineNum = 151;BA.debugLine="data(2)= 79	'O";
_data[(int) (2)] = (byte) (79);
 //BA.debugLineNum = 152;BA.debugLine="data(3)= 87	'W";
_data[(int) (3)] = (byte) (87);
 //BA.debugLineNum = 153;BA.debugLine="data(4)= Position + 1";
_data[(int) (4)] = (byte) (_position+1);
 //BA.debugLineNum = 155;BA.debugLine="Dim VAL As Short =Bit.And( Dialogo.Number ,  0x";
_val = (short) (anywheresoftware.b4a.keywords.Common.Bit.And(_dialogo.getNumber(),(int) (0xff00)));
 //BA.debugLineNum = 156;BA.debugLine="VAL=Bit.ShiftRight (VAL,8)";
_val = (short) (anywheresoftware.b4a.keywords.Common.Bit.ShiftRight((int) (_val),(int) (8)));
 //BA.debugLineNum = 157;BA.debugLine="data(5)= VAL";
_data[(int) (5)] = (byte) (_val);
 //BA.debugLineNum = 158;BA.debugLine="If data(5)<255 Then data(5)=data(5)+1";
if (_data[(int) (5)]<255) { 
_data[(int) (5)] = (byte) (_data[(int) (5)]+1);};
 //BA.debugLineNum = 160;BA.debugLine="data(6)= Bit.And( Dialogo.Number ,  0x00FF )'";
_data[(int) (6)] = (byte) (anywheresoftware.b4a.keywords.Common.Bit.And(_dialogo.getNumber(),(int) (0x00ff)));
 //BA.debugLineNum = 161;BA.debugLine="If data(6)<255 Then data(6)=data(6)+1";
if (_data[(int) (6)]<255) { 
_data[(int) (6)] = (byte) (_data[(int) (6)]+1);};
 //BA.debugLineNum = 168;BA.debugLine="If ValoresComunes.central.ConexionSegura Then V";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
mostCurrent._valorescomunes._completartrama(mostCurrent.activityBA,_data);};
 //BA.debugLineNum = 169;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 }else {
 //BA.debugLineNum = 171;BA.debugLine="Msgbox(ValoresComunes.GetLanString (\"reg142\"),V";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg142")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg120")),mostCurrent.activityBA);
 };
 };
 //BA.debugLineNum = 175;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="Dim UDPSocket1 As UDPSocket";
_udpsocket1 = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket();
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _refrescapantalla() throws Exception{
int _c = 0;
arduino.automatizacion.excontrol.PRO.valorescomunes._sp _s = null;
 //BA.debugLineNum = 177;BA.debugLine="Sub RefrescaPantalla";
 //BA.debugLineNum = 178;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 179;BA.debugLine="Dim c As Int";
_c = 0;
 //BA.debugLineNum = 180;BA.debugLine="For c=0 To ValoresComunes.SetPoint .Size -1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._valorescomunes._setpoint.getSize()-1);
_c = (int) (0) ;
for (;(step3 > 0 && _c <= limit3) || (step3 < 0 && _c >= limit3) ;_c = ((int)(0 + _c + step3))  ) {
 //BA.debugLineNum = 181;BA.debugLine="Dim s As Sp";
_s = new arduino.automatizacion.excontrol.PRO.valorescomunes._sp();
 //BA.debugLineNum = 182;BA.debugLine="s= ValoresComunes.SetPoint.get (c)";
_s = (arduino.automatizacion.excontrol.PRO.valorescomunes._sp)(mostCurrent._valorescomunes._setpoint.Get(_c));
 //BA.debugLineNum = 183;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 ( s.Nombre  ,Val";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_s.Nombre),BA.ObjectToCharSequence(_valores[_c]),(android.graphics.Bitmap)(mostCurrent._valorescomunes._icon(mostCurrent.activityBA,_s.Icon).getObject()),(Object)(_c));
 }
};
 //BA.debugLineNum = 188;BA.debugLine="End Sub";
return "";
}
public static boolean  _sendingtrama(byte[] _data) throws Exception{
anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet = null;
 //BA.debugLineNum = 193;BA.debugLine="Sub SendingTrama (data() As Byte) As Boolean";
 //BA.debugLineNum = 194;BA.debugLine="Try";
try { //BA.debugLineNum = 195;BA.debugLine="Dim Packet As UDPPacket";
_packet = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket();
 //BA.debugLineNum = 196;BA.debugLine="Packet.Initialize(data, ValoresComunes.ip, Valor";
_packet.Initialize(_data,mostCurrent._valorescomunes._ip,mostCurrent._valorescomunes._puerto);
 //BA.debugLineNum = 197;BA.debugLine="UDPSocket1.Send(Packet)";
_udpsocket1.Send(_packet);
 //BA.debugLineNum = 198;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 200;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 202;BA.debugLine="End Sub";
return false;
}
public static void  _sendtrama(byte[] _data) throws Exception{
ResumableSub_SendTrama rsub = new ResumableSub_SendTrama(null,_data);
rsub.resume(processBA, null);
}
public static class ResumableSub_SendTrama extends BA.ResumableSub {
public ResumableSub_SendTrama(arduino.automatizacion.excontrol.PRO.actconsignas parent,byte[] _data) {
this.parent = parent;
this._data = _data;
}
arduino.automatizacion.excontrol.PRO.actconsignas parent;
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
 //BA.debugLineNum = 204;BA.debugLine="Dim Resultado As Boolean";
_resultado = false;
 //BA.debugLineNum = 205;BA.debugLine="Dim Reintento As Int";
_reintento = 0;
 //BA.debugLineNum = 207;BA.debugLine="AniCargando.Start (ImgCargando)";
parent.mostCurrent._anicargando.Start((android.view.View)(parent.mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 208;BA.debugLine="PaqueteEnviado =False";
parent._paqueteenviado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 209;BA.debugLine="ListView1.Enabled =False";
parent.mostCurrent._listview1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 211;BA.debugLine="TramaEnviada= data";
parent._tramaenviada = _data;
 //BA.debugLineNum = 214;BA.debugLine="Do 	While Resultado= False And Reintento < 40";
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
 //BA.debugLineNum = 215;BA.debugLine="Dim ServerSocket1 As ServerSocket";
_serversocket1 = new anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper();
 //BA.debugLineNum = 216;BA.debugLine="Dim MyIp As String";
_myip = "";
 //BA.debugLineNum = 217;BA.debugLine="If ActMosaico.Forzar3g Then";
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
 //BA.debugLineNum = 218;BA.debugLine="MyIp=ServerSocket1.GetMyIP";
_myip = _serversocket1.GetMyIP();
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 220;BA.debugLine="MyIp=ServerSocket1.GetMyWifiIP";
_myip = _serversocket1.GetMyWifiIP();
 if (true) break;
;
 //BA.debugLineNum = 222;BA.debugLine="If MyIp  <> \"127.0.0.1\" Then";

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
 //BA.debugLineNum = 223;BA.debugLine="Resultado = True";
_resultado = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 13:
//C
this.state = 14;
 //BA.debugLineNum = 225;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 226;BA.debugLine="Sleep (200)";
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
 //BA.debugLineNum = 230;BA.debugLine="If Resultado = True Then";

case 15:
//if
this.state = 28;
if (_resultado==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 17;
}if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 231;BA.debugLine="Resultado =False";
_resultado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 232;BA.debugLine="Reintento =0";
_reintento = (int) (0);
 //BA.debugLineNum = 233;BA.debugLine="Do 	While Resultado= False And Reintento < 40";
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
 //BA.debugLineNum = 234;BA.debugLine="Resultado = SendingTrama(data)";
_resultado = _sendingtrama(_data);
 //BA.debugLineNum = 235;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 236;BA.debugLine="If Resultado=False Then Sleep (200)";
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
 //BA.debugLineNum = 241;BA.debugLine="If Resultado = False  Then";

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
 //BA.debugLineNum = 242;BA.debugLine="ActMosaico.Conectado =False";
parent.mostCurrent._actmosaico._conectado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 243;BA.debugLine="StartActivity(ActMosaico)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._actmosaico.getObject()));
 if (true) break;

case 32:
//C
this.state = 33;
 //BA.debugLineNum = 245;BA.debugLine="PaqueteEnviado=True";
parent._paqueteenviado = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 33:
//C
this.state = -1;
;
 //BA.debugLineNum = 247;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _slidemenu_click(Object _item) throws Exception{
 //BA.debugLineNum = 325;BA.debugLine="Sub SlideMenu_Click(Item As Object)";
 //BA.debugLineNum = 326;BA.debugLine="If Item>99 And  Item < 200 Then";
if ((double)(BA.ObjectToNumber(_item))>99 && (double)(BA.ObjectToNumber(_item))<200) { 
 //BA.debugLineNum = 327;BA.debugLine="ActMosaico.Conectado =False";
mostCurrent._actmosaico._conectado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 328;BA.debugLine="ActMosaico.CentraltoConnect  =Item-100";
mostCurrent._actmosaico._centraltoconnect = (int) ((double)(BA.ObjectToNumber(_item))-100);
 //BA.debugLineNum = 329;BA.debugLine="StartActivity(ActMosaico)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actmosaico.getObject()));
 //BA.debugLineNum = 330;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else if((double)(BA.ObjectToNumber(_item))>199 && (double)(BA.ObjectToNumber(_item))<299) { 
 }else {
 //BA.debugLineNum = 338;BA.debugLine="Select Case Item";
switch (BA.switchObjectToInt(_item,(Object)(1),(Object)(2),(Object)(3),(Object)(4),(Object)(5),(Object)(6),(Object)(7),(Object)(8),(Object)(9),(Object)(10),(Object)(11),(Object)(98))) {
case 0: {
 //BA.debugLineNum = 341;BA.debugLine="StartActivity(ActMosaico)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actmosaico.getObject()));
 //BA.debugLineNum = 342;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 1: {
 //BA.debugLineNum = 344;BA.debugLine="StartActivity(ActCircuit)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actcircuit.getObject()));
 //BA.debugLineNum = 345;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 2: {
 //BA.debugLineNum = 347;BA.debugLine="StartActivity(ActScene)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actscene.getObject()));
 //BA.debugLineNum = 348;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 3: {
 //BA.debugLineNum = 350;BA.debugLine="StartActivity(ActNotification)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actnotification.getObject()));
 //BA.debugLineNum = 351;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 4: {
 //BA.debugLineNum = 353;BA.debugLine="StartActivity(ActCondicionados)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actcondicionados.getObject()));
 //BA.debugLineNum = 354;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 5: {
 //BA.debugLineNum = 356;BA.debugLine="StartActivity(ActComandos)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actcomandos.getObject()));
 //BA.debugLineNum = 357;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 6: {
 //BA.debugLineNum = 359;BA.debugLine="StartActivity(ActSensors)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actsensors.getObject()));
 //BA.debugLineNum = 360;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 7: {
 //BA.debugLineNum = 362;BA.debugLine="If File.Exists ( File.DirInternal ,\"Sensores\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Sensores"+mostCurrent._valorescomunes._central.Nombre)) { 
 //BA.debugLineNum = 363;BA.debugLine="StartActivity(ActFreeTxt)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actfreetxt.getObject()));
 //BA.debugLineNum = 364;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else {
 //BA.debugLineNum = 366;BA.debugLine="ToastMessageShow(ValoresComunes.GetLanString";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg127")),anywheresoftware.b4a.keywords.Common.True);
 };
 break; }
case 8: {
 break; }
case 9: {
 //BA.debugLineNum = 372;BA.debugLine="ValoresComunes.CloseApp =True";
mostCurrent._valorescomunes._closeapp = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 373;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 10: {
 //BA.debugLineNum = 375;BA.debugLine="StartActivity(ActFunciones)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actfunciones.getObject()));
 //BA.debugLineNum = 376;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 11: {
 //BA.debugLineNum = 379;BA.debugLine="StartActivity(ActSelectSensores)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actselectsensores.getObject()));
 //BA.debugLineNum = 380;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
}
;
 };
 //BA.debugLineNum = 385;BA.debugLine="End Sub";
return "";
}
public static String  _udp_packetarrived(anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet) throws Exception{
String _msg = "";
int _c = 0;
int _s = 0;
short _b1 = (short)0;
short _b2 = (short)0;
 //BA.debugLineNum = 248;BA.debugLine="Sub UDP_PacketArrived (Packet As UDPPacket)";
 //BA.debugLineNum = 249;BA.debugLine="Try";
try { //BA.debugLineNum = 250;BA.debugLine="Dim msg As String";
_msg = "";
 //BA.debugLineNum = 251;BA.debugLine="msg = BytesToString(Packet.data, Packet.Offse";
_msg = anywheresoftware.b4a.keywords.Common.BytesToString(_packet.getData(),_packet.getOffset(),_packet.getLength(),"UTF8");
 //BA.debugLineNum = 253;BA.debugLine="If msg.Contains (\"SEPOI\") Then";
if (_msg.contains("SEPOI")) { 
 //BA.debugLineNum = 264;BA.debugLine="Dim c As Int";
_c = 0;
 //BA.debugLineNum = 265;BA.debugLine="Dim s As Int";
_s = 0;
 //BA.debugLineNum = 266;BA.debugLine="s=0";
_s = (int) (0);
 //BA.debugLineNum = 267;BA.debugLine="c=5";
_c = (int) (5);
 //BA.debugLineNum = 268;BA.debugLine="Do While c<  Packet.Length";
while (_c<_packet.getLength()) {
 //BA.debugLineNum = 270;BA.debugLine="Dim B1 As Short";
_b1 = (short)0;
 //BA.debugLineNum = 271;BA.debugLine="Dim B2 As Short";
_b2 = (short)0;
 //BA.debugLineNum = 274;BA.debugLine="B1= Bit.And (Packet.data (c) , 0xff)";
_b1 = (short) (anywheresoftware.b4a.keywords.Common.Bit.And((int) (_packet.getData()[_c]),(int) (0xff)));
 //BA.debugLineNum = 275;BA.debugLine="If B1<255 Then B1=B1-1";
if (_b1<255) { 
_b1 = (short) (_b1-1);};
 //BA.debugLineNum = 279;BA.debugLine="c=c+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 281;BA.debugLine="B2=Bit.And (Packet.data (c) , 0xff)";
_b2 = (short) (anywheresoftware.b4a.keywords.Common.Bit.And((int) (_packet.getData()[_c]),(int) (0xff)));
 //BA.debugLineNum = 282;BA.debugLine="If B2<255 Then B2=B2-1";
if (_b2<255) { 
_b2 = (short) (_b2-1);};
 //BA.debugLineNum = 283;BA.debugLine="c=c+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 285;BA.debugLine="B1= Bit.Or(Bit.ShiftLeft ( B2,8),B1)";
_b1 = (short) (anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Bit.ShiftLeft((int) (_b2),(int) (8)),(int) (_b1)));
 //BA.debugLineNum = 286;BA.debugLine="Valores (s) =B1";
_valores[_s] = (int) (_b1);
 //BA.debugLineNum = 291;BA.debugLine="s=s+1";
_s = (int) (_s+1);
 }
;
 //BA.debugLineNum = 297;BA.debugLine="RefrescaPantalla";
_refrescapantalla();
 }else {
 //BA.debugLineNum = 301;BA.debugLine="If msg =\"REPETIRMSG\" Then";
if ((_msg).equals("REPETIRMSG")) { 
 //BA.debugLineNum = 302;BA.debugLine="SendTrama(TramaEnviada)";
_sendtrama(_tramaenviada);
 //BA.debugLineNum = 303;BA.debugLine="Return";
if (true) return "";
 }else {
 };
 };
 //BA.debugLineNum = 308;BA.debugLine="PaqueteEnviado = False";
_paqueteenviado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 310;BA.debugLine="ListView1.Enabled =True";
mostCurrent._listview1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 311;BA.debugLine="AniCargando.Stop(ImgCargando)";
mostCurrent._anicargando.Stop((android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 312;BA.debugLine="ImgCargando.Visible =False";
mostCurrent._imgcargando.setVisible(anywheresoftware.b4a.keywords.Common.False);
 } 
       catch (Exception e35) {
			processBA.setLastException(e35); };
 //BA.debugLineNum = 317;BA.debugLine="End Sub";
return "";
}
}
