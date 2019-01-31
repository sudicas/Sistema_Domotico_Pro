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

public class actmenu extends Activity implements B4AActivity{
	public static actmenu mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actmenu");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actmenu).");
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
		activityBA = new BA(this, layout, processBA, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actmenu");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "arduino.automatizacion.excontrol.PRO.actmenu", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actmenu) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actmenu) Resume **");
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
		return actmenu.class;
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
        BA.LogInfo("** Activity (actmenu) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (actmenu) Resume **");
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
public static int _typeconfig = 0;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public static byte[] _tramaenviada = null;
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
public arduino.automatizacion.excontrol.PRO.acthistorico _acthistorico = null;
public arduino.automatizacion.excontrol.PRO.actcamara _actcamara = null;
public arduino.automatizacion.excontrol.PRO.actnotification _actnotification = null;
public arduino.automatizacion.excontrol.PRO.actscene _actscene = null;
public arduino.automatizacion.excontrol.PRO.actfunciones _actfunciones = null;
public arduino.automatizacion.excontrol.PRO.actcondicionados _actcondicionados = null;
public arduino.automatizacion.excontrol.PRO.actselectsensores _actselectsensores = null;
public arduino.automatizacion.excontrol.PRO.actconfigsensors _actconfigsensors = null;
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
 //BA.debugLineNum = 25;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 26;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = Fals";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 27;BA.debugLine="Activity.LoadLayout (\"FrmMenu\")";
mostCurrent._activity.LoadLayout("FrmMenu",mostCurrent.activityBA);
 //BA.debugLineNum = 30;BA.debugLine="ListView1.height = 100%y";
mostCurrent._listview1.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 31;BA.debugLine="ListView1.Width =Activity.Width";
mostCurrent._listview1.setWidth(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 34;BA.debugLine="Dim h As Int";
_h = 0;
 //BA.debugLineNum = 35;BA.debugLine="h=75dip";
_h = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75));
 //BA.debugLineNum = 38;BA.debugLine="ListView1.TwoLinesAndBitmap .ItemHeight =h";
mostCurrent._listview1.getTwoLinesAndBitmap().setItemHeight(_h);
 //BA.debugLineNum = 39;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.Width=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setWidth(_h);
 //BA.debugLineNum = 40;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.height=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setHeight(_h);
 //BA.debugLineNum = 42;BA.debugLine="ListView1.TwoLinesAndBitmap.Label .Left =h + h/9";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setLeft((int) (_h+_h/(double)9));
 //BA.debugLineNum = 43;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.height  =h/1.8";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setHeight((int) (_h/(double)1.8));
 //BA.debugLineNum = 44;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.Gravity = Bit.O";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.BOTTOM));
 //BA.debugLineNum = 46;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel .Left =Li";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setLeft(mostCurrent._listview1.getTwoLinesAndBitmap().Label.getLeft());
 //BA.debugLineNum = 47;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.height";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setHeight((int) (_h-mostCurrent._listview1.getTwoLinesAndBitmap().Label.getHeight()));
 //BA.debugLineNum = 48;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.top   = L";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setTop(mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.getHeight());
 //BA.debugLineNum = 49;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.Gravity =";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 161;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 162;BA.debugLine="UDPSocket1.Close";
_udpsocket1.Close();
 //BA.debugLineNum = 163;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 164;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 53;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 54;BA.debugLine="If ValoresComunes.CloseApp =True Then";
if (mostCurrent._valorescomunes._closeapp==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 55;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 56;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 58;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = True";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 59;BA.debugLine="If Main.MyLan.IsInitialized = False Then Main.My";
if (mostCurrent._main._mylan.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._main._mylan.Initialize(processBA,(int) (0),"");};
 //BA.debugLineNum = 60;BA.debugLine="If UDPSocket1.IsInitialized = False Then  Valore";
if (_udpsocket1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._valorescomunes._iniudp(mostCurrent.activityBA,_udpsocket1);};
 //BA.debugLineNum = 62;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 63;BA.debugLine="Select Case TypeConfig";
switch (_typeconfig) {
case 0: {
 //BA.debugLineNum = 65;BA.debugLine="ListView1.AddTwoLinesAndBitmap2  ( ValoresComu";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"TC")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"STC")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._alarma(mostCurrent.activityBA).getObject()),(Object)(0));
 //BA.debugLineNum = 66;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"ST")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SWP")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._reloj(mostCurrent.activityBA).getObject()),(Object)(1));
 //BA.debugLineNum = 67;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"ET")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"ECT")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._relook(mostCurrent.activityBA).getObject()),(Object)(2));
 //BA.debugLineNum = 68;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SSD")+BA.NumberToString(1)),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SPD")+BA.NumberToString(1)),(android.graphics.Bitmap)(mostCurrent._valorescomunes._calendario(mostCurrent.activityBA).getObject()),(Object)(3));
 //BA.debugLineNum = 69;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SSD")+BA.NumberToString(2)),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SPD")+BA.NumberToString(2)),(android.graphics.Bitmap)(mostCurrent._valorescomunes._calendario(mostCurrent.activityBA).getObject()),(Object)(4));
 //BA.debugLineNum = 71;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg97")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SPD")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._delete(mostCurrent.activityBA).getObject()),(Object)(21));
 //BA.debugLineNum = 72;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg98")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SPD")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._delete(mostCurrent.activityBA).getObject()),(Object)(22));
 //BA.debugLineNum = 76;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SDT")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SIT")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._fechahora(mostCurrent.activityBA).getObject()),(Object)(6));
 //BA.debugLineNum = 77;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SST")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"ITS")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._fechahora(mostCurrent.activityBA).getObject()),(Object)(7));
 //BA.debugLineNum = 79;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SS")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SNV")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._scene2(mostCurrent.activityBA).getObject()),(Object)(5));
 //BA.debugLineNum = 82;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"CBU")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"GCF")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._configup(mostCurrent.activityBA).getObject()),(Object)(8));
 //BA.debugLineNum = 83;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"RBU")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"RBU")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._configdo(mostCurrent.activityBA).getObject()),(Object)(9));
 //BA.debugLineNum = 85;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SCN")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"MND")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._on(mostCurrent.activityBA).getObject()),(Object)(10));
 //BA.debugLineNum = 87;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SSU")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SUD")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._persiana(mostCurrent.activityBA).getObject()),(Object)(11));
 //BA.debugLineNum = 89;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SCD")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"MND")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._checkon(mostCurrent.activityBA).getObject()),(Object)(12));
 //BA.debugLineNum = 90;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SCO")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"MND")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._comando(mostCurrent.activityBA).getObject()),(Object)(13));
 //BA.debugLineNum = 94;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"LsTT","Language Settings")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"SltL","Select language")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._voice(mostCurrent.activityBA).getObject()),(Object)(14));
 //BA.debugLineNum = 96;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"Status","Status")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"SetSta","Configure states screen")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._sensor(mostCurrent.activityBA).getObject()),(Object)(15));
 //BA.debugLineNum = 98;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SAU")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"NHA")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._home(mostCurrent.activityBA).getObject()),(Object)(16));
 //BA.debugLineNum = 101;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"MNU")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"MNU")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._config(mostCurrent.activityBA).getObject()),(Object)(18));
 //BA.debugLineNum = 103;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"pp")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"VIS")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._icono(mostCurrent.activityBA).getObject()),(Object)(19));
 //BA.debugLineNum = 104;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"dcg")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"cdt")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._arduino(mostCurrent.activityBA).getObject()),(Object)(20));
 break; }
case 1: {
 //BA.debugLineNum = 107;BA.debugLine="ListView1.AddTwoLinesAndBitmap2  ( ValoresComu";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"TC")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"STC")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._alarma(mostCurrent.activityBA).getObject()),(Object)(0));
 //BA.debugLineNum = 108;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"ST")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SWP")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._reloj(mostCurrent.activityBA).getObject()),(Object)(1));
 //BA.debugLineNum = 109;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"ET")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"ECT")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._relook(mostCurrent.activityBA).getObject()),(Object)(2));
 //BA.debugLineNum = 110;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SSD")+BA.NumberToString(1)),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SPD")+BA.NumberToString(1)),(android.graphics.Bitmap)(mostCurrent._valorescomunes._calendario(mostCurrent.activityBA).getObject()),(Object)(3));
 //BA.debugLineNum = 111;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SSD")+BA.NumberToString(2)),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SPD")+BA.NumberToString(2)),(android.graphics.Bitmap)(mostCurrent._valorescomunes._calendario(mostCurrent.activityBA).getObject()),(Object)(4));
 //BA.debugLineNum = 114;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SDT")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SIT")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._fechahora(mostCurrent.activityBA).getObject()),(Object)(6));
 //BA.debugLineNum = 115;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SST")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"ITS")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._fechahora(mostCurrent.activityBA).getObject()),(Object)(7));
 //BA.debugLineNum = 117;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg97")),BA.ObjectToCharSequence(""),(android.graphics.Bitmap)(mostCurrent._valorescomunes._delete(mostCurrent.activityBA).getObject()),(Object)(21));
 //BA.debugLineNum = 118;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg98")),BA.ObjectToCharSequence(""),(android.graphics.Bitmap)(mostCurrent._valorescomunes._delete(mostCurrent.activityBA).getObject()),(Object)(22));
 break; }
case 2: {
 //BA.debugLineNum = 121;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"CBU")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"GCF")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._configup(mostCurrent.activityBA).getObject()),(Object)(8));
 //BA.debugLineNum = 122;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"RBU")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"RBU")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._configdo(mostCurrent.activityBA).getObject()),(Object)(9));
 break; }
case 3: {
 //BA.debugLineNum = 126;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"CsS","Configure Status Screen")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SNV")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._sensor(mostCurrent.activityBA).getObject()),(Object)(30));
 //BA.debugLineNum = 127;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"CuF","Configure User Functons")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SNV")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._funciones(mostCurrent.activityBA).getObject()),(Object)(31));
 //BA.debugLineNum = 129;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"dev","devices")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"reg124","Setting Central")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._home(mostCurrent.activityBA).getObject()),(Object)(32));
 break; }
case 4: {
 break; }
case 5: {
 break; }
}
;
 }else {
 //BA.debugLineNum = 153;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 154;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 };
 //BA.debugLineNum = 159;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 18;BA.debugLine="Dim ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Dim TramaEnviada() As Byte";
_tramaenviada = new byte[(int) (0)];
;
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _result = 0;
anywheresoftware.b4a.objects.collections.List _li = null;
int _longtrama = 0;
byte[] _data = null;
String _trama = "";
anywheresoftware.b4a.agraham.dialogs.InputDialog.FileDialog _d = null;
String _ruta = "";
com.AB.ABZipUnzip.ABZipUnzip _myzip = null;
anywheresoftware.b4a.phone.Phone.PhoneIntents _p = null;
int _rsp = 0;
String _s = "";
 //BA.debugLineNum = 168;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 169;BA.debugLine="Select  Value";
switch (BA.switchObjectToInt(_value,(Object)(0),(Object)(1),(Object)(2),(Object)(3),(Object)(4),(Object)(5),(Object)(6),(Object)(7),(Object)(8),(Object)(9),(Object)(10),(Object)(11),(Object)(12),(Object)(13),(Object)(14),(Object)(15),(Object)(16),(Object)(17),(Object)(18),(Object)(19),(Object)(20),(Object)(21),(Object)(22),(Object)(23),(Object)(30),(Object)(31),(Object)(32))) {
case 0: {
 //BA.debugLineNum = 171;BA.debugLine="StartActivity(ActAlarmas2)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actalarmas2.getObject()));
 break; }
case 1: {
 //BA.debugLineNum = 175;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 176;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 178;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg51\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg51")));
 //BA.debugLineNum = 179;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg52\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg52")));
 //BA.debugLineNum = 180;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg53\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg53")));
 //BA.debugLineNum = 181;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg54\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg54")));
 //BA.debugLineNum = 182;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg55\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg55")));
 //BA.debugLineNum = 183;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg56\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg56")));
 //BA.debugLineNum = 184;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg57\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg57")));
 //BA.debugLineNum = 185;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"SPDI\") &";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SPDI")+" 1"));
 //BA.debugLineNum = 186;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"SPDI\") &";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SPDI")+" 2"));
 //BA.debugLineNum = 187;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"Cancel\")";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel")));
 //BA.debugLineNum = 188;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 189;BA.debugLine="Result =InputList(Lst,ValoresComunes.GetLanStri";
_result = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),(int) (0),mostCurrent.activityBA);
 //BA.debugLineNum = 190;BA.debugLine="If Result > -1 And Result < 9 Then";
if (_result>-1 && _result<9) { 
 //BA.debugLineNum = 191;BA.debugLine="Dim li As List";
_li = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 192;BA.debugLine="li.Initialize";
_li.Initialize();
 //BA.debugLineNum = 193;BA.debugLine="li.Add (Result)";
_li.Add((Object)(_result));
 //BA.debugLineNum = 195;BA.debugLine="ActHorarios2.Days  = li";
mostCurrent._acthorarios2._days = _li;
 //BA.debugLineNum = 196;BA.debugLine="StartActivity(ActHorarios2)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._acthorarios2.getObject()));
 };
 break; }
case 2: {
 //BA.debugLineNum = 200;BA.debugLine="StartActivity(ActEnableHorarios)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actenablehorarios.getObject()));
 break; }
case 3: {
 //BA.debugLineNum = 202;BA.debugLine="ActDiaEspecial.TipoEspecial =1";
mostCurrent._actdiaespecial._tipoespecial = (byte) (1);
 //BA.debugLineNum = 203;BA.debugLine="StartActivity(ActDiaEspecial)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actdiaespecial.getObject()));
 break; }
case 4: {
 //BA.debugLineNum = 205;BA.debugLine="ActDiaEspecial.TipoEspecial =2";
mostCurrent._actdiaespecial._tipoespecial = (byte) (2);
 //BA.debugLineNum = 206;BA.debugLine="StartActivity(ActDiaEspecial)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actdiaespecial.getObject()));
 break; }
case 5: {
 //BA.debugLineNum = 208;BA.debugLine="StartActivity( ActConfigEscenas)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfigescenas.getObject()));
 break; }
case 6: {
 //BA.debugLineNum = 211;BA.debugLine="Dim LongTrama As Int";
_longtrama = 0;
 //BA.debugLineNum = 212;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 213;BA.debugLine="LongTrama= 20";
_longtrama = (int) (20);
 }else {
 //BA.debugLineNum = 215;BA.debugLine="LongTrama= 12";
_longtrama = (int) (12);
 };
 //BA.debugLineNum = 217;BA.debugLine="Dim DATA(LongTrama) As Byte";
_data = new byte[_longtrama];
;
 //BA.debugLineNum = 218;BA.debugLine="DATA(0)=83";
_data[(int) (0)] = (byte) (83);
 //BA.debugLineNum = 219;BA.debugLine="DATA(1)=69";
_data[(int) (1)] = (byte) (69);
 //BA.debugLineNum = 220;BA.debugLine="DATA(2)=84";
_data[(int) (2)] = (byte) (84);
 //BA.debugLineNum = 221;BA.debugLine="DATA(3)=70";
_data[(int) (3)] = (byte) (70);
 //BA.debugLineNum = 222;BA.debugLine="DATA(4)=72";
_data[(int) (4)] = (byte) (72);
 //BA.debugLineNum = 224;BA.debugLine="DATA(5)= DateTime.GetSecond (DateTime.Now )";
_data[(int) (5)] = (byte) (anywheresoftware.b4a.keywords.Common.DateTime.GetSecond(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 225;BA.debugLine="DATA(6)= DateTime.GetMinute  (DateTime.Now )";
_data[(int) (6)] = (byte) (anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 226;BA.debugLine="DATA(7)=DateTime.GetHour   (DateTime.Now )";
_data[(int) (7)] = (byte) (anywheresoftware.b4a.keywords.Common.DateTime.GetHour(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 227;BA.debugLine="DATA(8)= DateTime.GetDayOfWeek (DateTime.now)-1";
_data[(int) (8)] = (byte) (anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfWeek(anywheresoftware.b4a.keywords.Common.DateTime.getNow())-1);
 //BA.debugLineNum = 228;BA.debugLine="If DATA(8)=0 Then DATA(8)=7";
if (_data[(int) (8)]==0) { 
_data[(int) (8)] = (byte) (7);};
 //BA.debugLineNum = 229;BA.debugLine="DATA(9)=DateTime.GetDayOfMonth (DateTime.Now )";
_data[(int) (9)] = (byte) (anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfMonth(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 230;BA.debugLine="DATA(10)=DateTime.GetMonth (DateTime.Now )";
_data[(int) (10)] = (byte) (anywheresoftware.b4a.keywords.Common.DateTime.GetMonth(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 231;BA.debugLine="DATA(11)=DateTime.GetYear  (DateTime.Now )-2000";
_data[(int) (11)] = (byte) (anywheresoftware.b4a.keywords.Common.DateTime.GetYear(anywheresoftware.b4a.keywords.Common.DateTime.getNow())-2000);
 //BA.debugLineNum = 232;BA.debugLine="If ValoresComunes.central.ConexionSegura Then V";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
mostCurrent._valorescomunes._completartrama(mostCurrent.activityBA,_data);};
 //BA.debugLineNum = 233;BA.debugLine="SendTrama(DATA)";
_sendtrama(_data);
 break; }
case 7: {
 //BA.debugLineNum = 238;BA.debugLine="Dim DATA() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 239;BA.debugLine="Dim Trama As String";
_trama = "";
 //BA.debugLineNum = 240;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 241;BA.debugLine="Trama = \"ESTADOINST\" & ValoresComunes.Central.";
_trama = "ESTADOINST"+mostCurrent._valorescomunes._central.Password;
 }else {
 //BA.debugLineNum = 243;BA.debugLine="Trama = \"ESTADOINST\"";
_trama = "ESTADOINST";
 };
 //BA.debugLineNum = 246;BA.debugLine="DATA = Trama.GetBytes(\"UTF8\")";
_data = _trama.getBytes("UTF8");
 //BA.debugLineNum = 247;BA.debugLine="SendTrama(DATA)";
_sendtrama(_data);
 break; }
case 8: {
 //BA.debugLineNum = 249;BA.debugLine="Dim d As FileDialog";
_d = new anywheresoftware.b4a.agraham.dialogs.InputDialog.FileDialog();
 //BA.debugLineNum = 250;BA.debugLine="d.FilePath = File.DirDefaultExternal";
_d.setFilePath(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal());
 //BA.debugLineNum = 251;BA.debugLine="d.ShowOnlyFolders= True";
_d.setShowOnlyFolders(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 252;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 253;BA.debugLine="Result= d.Show (ValoresComunes.GetLanString (\"r";
_result = _d.Show(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg78")),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Ok"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 255;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 256;BA.debugLine="Dim ruta As String";
_ruta = "";
 //BA.debugLineNum = 257;BA.debugLine="ruta= d.FilePath";
_ruta = _d.getFilePath();
 //BA.debugLineNum = 258;BA.debugLine="Dim myZip As  ABZipUnzip";
_myzip = new com.AB.ABZipUnzip.ABZipUnzip();
 //BA.debugLineNum = 260;BA.debugLine="myZip.ABZipDirectory( File.DirInternal , d.Fil";
_myzip.ABZipDirectory(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),_d.getFilePath()+"/OLMHCONFIG.piz");
 //BA.debugLineNum = 261;BA.debugLine="ToastMessageShow(ValoresComunes.GetLanString (";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg139")),anywheresoftware.b4a.keywords.Common.True);
 };
 break; }
case 9: {
 //BA.debugLineNum = 264;BA.debugLine="Dim d As FileDialog";
_d = new anywheresoftware.b4a.agraham.dialogs.InputDialog.FileDialog();
 //BA.debugLineNum = 265;BA.debugLine="d.FilePath = File.DirDefaultExternal";
_d.setFilePath(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal());
 //BA.debugLineNum = 266;BA.debugLine="d.ShowOnlyFolders= False";
_d.setShowOnlyFolders(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 267;BA.debugLine="d.FileFilter=\".piz\"";
_d.setFileFilter(".piz");
 //BA.debugLineNum = 268;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 273;BA.debugLine="Result= d.Show ( \"Select backup copy\", \"Ok\",\"Ca";
_result = _d.Show(BA.ObjectToCharSequence("Select backup copy"),"Ok","Cancel","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 275;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 277;BA.debugLine="Dim myZip As  ABZipUnzip";
_myzip = new com.AB.ABZipUnzip.ABZipUnzip();
 //BA.debugLineNum = 278;BA.debugLine="Dim ruta As String";
_ruta = "";
 //BA.debugLineNum = 279;BA.debugLine="ruta =  d.FilePath & \"/\" & d.ChosenName";
_ruta = _d.getFilePath()+"/"+_d.getChosenName();
 //BA.debugLineNum = 280;BA.debugLine="If ruta.Length >0 And d.ChosenName.Length > 0";
if (_ruta.length()>0 && _d.getChosenName().length()>0) { 
 //BA.debugLineNum = 281;BA.debugLine="myZip.ABUnzip ( ruta, File.DirInternal)";
_myzip.ABUnzip(_ruta,anywheresoftware.b4a.keywords.Common.File.getDirInternal());
 //BA.debugLineNum = 298;BA.debugLine="ToastMessageShow(ValoresComunes.GetLanString";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg139")),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 299;BA.debugLine="ValoresComunes.CloseApp =True";
mostCurrent._valorescomunes._closeapp = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 300;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 };
 break; }
case 10: {
 //BA.debugLineNum = 305;BA.debugLine="StartActivity(ActConfigCir)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfigcir.getObject()));
 break; }
case 11: {
 //BA.debugLineNum = 307;BA.debugLine="StartActivity(ActPersianas)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actpersianas.getObject()));
 break; }
case 12: {
 //BA.debugLineNum = 309;BA.debugLine="StartActivity(ActConfigCondicionados)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfigcondicionados.getObject()));
 break; }
case 13: {
 //BA.debugLineNum = 311;BA.debugLine="StartActivity(ActConfigComandos)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfigcomandos.getObject()));
 break; }
case 14: {
 //BA.debugLineNum = 313;BA.debugLine="StartActivity(ActConfigVoice)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfigvoice.getObject()));
 break; }
case 15: {
 //BA.debugLineNum = 315;BA.debugLine="StartActivity(ActConfigSensors)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfigsensors.getObject()));
 break; }
case 16: {
 //BA.debugLineNum = 318;BA.debugLine="StartActivity(ActCentrales)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actcentrales.getObject()));
 break; }
case 17: {
 //BA.debugLineNum = 320;BA.debugLine="StartActivity(ActConfigComandosComu)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfigcomandoscomu.getObject()));
 break; }
case 18: {
 break; }
case 19: {
 //BA.debugLineNum = 325;BA.debugLine="Dim p As PhoneIntents";
_p = new anywheresoftware.b4a.phone.Phone.PhoneIntents();
 //BA.debugLineNum = 326;BA.debugLine="StartActivity(p.OpenBrowser(\"http://domotica-Ar";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_p.OpenBrowser("http://domotica-Arduino.es")));
 break; }
case 20: {
 //BA.debugLineNum = 328;BA.debugLine="Dim p As PhoneIntents";
_p = new anywheresoftware.b4a.phone.Phone.PhoneIntents();
 //BA.debugLineNum = 329;BA.debugLine="StartActivity(p.OpenBrowser(\"http://domotica-ar";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_p.OpenBrowser("http://domotica-arduino.es/download-arduino-home-automation-arduino-apps/")));
 break; }
case 21: {
 //BA.debugLineNum = 332;BA.debugLine="Dim Rsp As Int";
_rsp = 0;
 //BA.debugLineNum = 333;BA.debugLine="Rsp=Msgbox2(ValoresComunes.GetLanString (\"reg11";
_rsp = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg110")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg77")),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Y"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"N"),"",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 //BA.debugLineNum = 334;BA.debugLine="If Rsp=  DialogResponse.POSITIVE Then";
if (_rsp==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 335;BA.debugLine="Dim S As String";
_s = "";
 //BA.debugLineNum = 336;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 337;BA.debugLine="S=\"CLEARHORARIO\" & ValoresComunes.Central.Pas";
_s = "CLEARHORARIO"+mostCurrent._valorescomunes._central.Password;
 }else {
 //BA.debugLineNum = 339;BA.debugLine="S=\"CLEARHORARIO\"";
_s = "CLEARHORARIO";
 };
 //BA.debugLineNum = 342;BA.debugLine="Dim DATA() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 343;BA.debugLine="DATA= S.GetBytes (\"UTF8\")";
_data = _s.getBytes("UTF8");
 //BA.debugLineNum = 344;BA.debugLine="SendTrama(DATA)";
_sendtrama(_data);
 //BA.debugLineNum = 345;BA.debugLine="ProgressDialogShow(ValoresComunes.GetLanString";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg109")));
 };
 break; }
case 22: {
 //BA.debugLineNum = 350;BA.debugLine="Dim Rsp As Int";
_rsp = 0;
 //BA.debugLineNum = 351;BA.debugLine="Rsp=Msgbox2(ValoresComunes.GetLanString (\"reg10";
_rsp = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg108")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg77")),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Y"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"N"),"",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 //BA.debugLineNum = 352;BA.debugLine="If Rsp=  DialogResponse.POSITIVE Then";
if (_rsp==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 353;BA.debugLine="Dim S As String";
_s = "";
 //BA.debugLineNum = 354;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 355;BA.debugLine="S=\"CLEARESPCDAY\" & ValoresComunes.Central.Pas";
_s = "CLEARESPCDAY"+mostCurrent._valorescomunes._central.Password;
 }else {
 //BA.debugLineNum = 357;BA.debugLine="S=\"CLEARESPCDAY\"";
_s = "CLEARESPCDAY";
 };
 //BA.debugLineNum = 360;BA.debugLine="Dim DATA() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 361;BA.debugLine="DATA= S.GetBytes (\"UTF8\")";
_data = _s.getBytes("UTF8");
 //BA.debugLineNum = 362;BA.debugLine="SendTrama(DATA)";
_sendtrama(_data);
 //BA.debugLineNum = 363;BA.debugLine="ProgressDialogShow(ValoresComunes.GetLanString";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg109")));
 };
 break; }
case 23: {
 //BA.debugLineNum = 367;BA.debugLine="StartActivity(ActConfigNotification)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfignotification.getObject()));
 break; }
case 24: {
 //BA.debugLineNum = 371;BA.debugLine="StartActivity(ActConfigFreeTxt)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfigfreetxt.getObject()));
 break; }
case 25: {
 //BA.debugLineNum = 375;BA.debugLine="StartActivity(ActConfigFunciones)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfigfunciones.getObject()));
 break; }
case 26: {
 //BA.debugLineNum = 378;BA.debugLine="StartActivity(ActCentrales)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actcentrales.getObject()));
 break; }
}
;
 //BA.debugLineNum = 383;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="Dim UDPSocket1 As UDPSocket";
_udpsocket1 = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket();
 //BA.debugLineNum = 11;BA.debugLine="Dim TypeConfig As Int";
_typeconfig = 0;
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static boolean  _sendingtrama(byte[] _data) throws Exception{
anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet = null;
 //BA.debugLineNum = 424;BA.debugLine="Sub SendingTrama (data() As Byte) As Boolean";
 //BA.debugLineNum = 425;BA.debugLine="Try";
try { //BA.debugLineNum = 426;BA.debugLine="Dim Packet As UDPPacket";
_packet = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket();
 //BA.debugLineNum = 427;BA.debugLine="Packet.Initialize(data, ValoresComunes.ip, Valor";
_packet.Initialize(_data,mostCurrent._valorescomunes._ip,mostCurrent._valorescomunes._puerto);
 //BA.debugLineNum = 428;BA.debugLine="UDPSocket1.Send(Packet)";
_udpsocket1.Send(_packet);
 //BA.debugLineNum = 429;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 431;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 433;BA.debugLine="End Sub";
return false;
}
public static void  _sendtrama(byte[] _data) throws Exception{
ResumableSub_SendTrama rsub = new ResumableSub_SendTrama(null,_data);
rsub.resume(processBA, null);
}
public static class ResumableSub_SendTrama extends BA.ResumableSub {
public ResumableSub_SendTrama(arduino.automatizacion.excontrol.PRO.actmenu parent,byte[] _data) {
this.parent = parent;
this._data = _data;
}
arduino.automatizacion.excontrol.PRO.actmenu parent;
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
 //BA.debugLineNum = 385;BA.debugLine="Dim Resultado As Boolean";
_resultado = false;
 //BA.debugLineNum = 386;BA.debugLine="Dim Reintento As Int";
_reintento = 0;
 //BA.debugLineNum = 388;BA.debugLine="TramaEnviada=data";
parent._tramaenviada = _data;
 //BA.debugLineNum = 390;BA.debugLine="Do 	While Resultado= False And Reintento < 40";
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
 //BA.debugLineNum = 391;BA.debugLine="Dim ServerSocket1 As ServerSocket";
_serversocket1 = new anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper();
 //BA.debugLineNum = 392;BA.debugLine="Dim MyIp As String";
_myip = "";
 //BA.debugLineNum = 393;BA.debugLine="If ActMosaico.Forzar3g Then";
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
 //BA.debugLineNum = 394;BA.debugLine="MyIp=ServerSocket1.GetMyIP";
_myip = _serversocket1.GetMyIP();
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 396;BA.debugLine="MyIp=ServerSocket1.GetMyWifiIP";
_myip = _serversocket1.GetMyWifiIP();
 if (true) break;
;
 //BA.debugLineNum = 398;BA.debugLine="If MyIp  <> \"127.0.0.1\" Then";

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
 //BA.debugLineNum = 399;BA.debugLine="Resultado = True";
_resultado = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 13:
//C
this.state = 14;
 //BA.debugLineNum = 401;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 402;BA.debugLine="Sleep (200)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (200));
this.state = 32;
return;
case 32:
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
 //BA.debugLineNum = 406;BA.debugLine="If Resultado = True Then";

case 15:
//if
this.state = 28;
if (_resultado==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 17;
}if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 407;BA.debugLine="Resultado =False";
_resultado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 408;BA.debugLine="Reintento =0";
_reintento = (int) (0);
 //BA.debugLineNum = 409;BA.debugLine="Do 	While Resultado= False And Reintento < 40";
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
 //BA.debugLineNum = 410;BA.debugLine="Resultado = SendingTrama(data)";
_resultado = _sendingtrama(_data);
 //BA.debugLineNum = 411;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 412;BA.debugLine="If Resultado=False Then Sleep (200)";
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
this.state = 33;
return;
case 33:
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
 //BA.debugLineNum = 417;BA.debugLine="If Resultado = False  Then";

case 28:
//if
this.state = 31;
if (_resultado==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 30;
}if (true) break;

case 30:
//C
this.state = 31;
 //BA.debugLineNum = 418;BA.debugLine="ActMosaico.Conectado =False";
parent.mostCurrent._actmosaico._conectado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 419;BA.debugLine="StartActivity(ActMosaico)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._actmosaico.getObject()));
 if (true) break;

case 31:
//C
this.state = -1;
;
 //BA.debugLineNum = 423;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _udp_packetarrived(anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet) throws Exception{
String _msg = "";
String _txt = "";
 //BA.debugLineNum = 434;BA.debugLine="Sub UDP_PacketArrived (Packet As UDPPacket)";
 //BA.debugLineNum = 435;BA.debugLine="Try";
try { //BA.debugLineNum = 436;BA.debugLine="Dim msg As String";
_msg = "";
 //BA.debugLineNum = 437;BA.debugLine="msg = BytesToString(Packet.Data, Packet.Offset,";
_msg = anywheresoftware.b4a.keywords.Common.BytesToString(_packet.getData(),_packet.getOffset(),_packet.getLength(),"UTF8");
 //BA.debugLineNum = 439;BA.debugLine="If msg.Contains (\"ESTACT\") Then";
if (_msg.contains("ESTACT")) { 
 //BA.debugLineNum = 441;BA.debugLine="Dim Txt As String";
_txt = "";
 //BA.debugLineNum = 442;BA.debugLine="Select Packet.Data (6)";
switch (BA.switchObjectToInt(_packet.getData()[(int) (6)],(byte) (2),(byte) (3),(byte) (4),(byte) (5),(byte) (6),(byte) (7),(byte) (8),(byte) (9),(byte) (10))) {
case 0: {
 //BA.debugLineNum = 444;BA.debugLine="Txt =ValoresComunes.GetLanString (\"reg51\") &";
_txt = mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg51")+" -";
 break; }
case 1: {
 //BA.debugLineNum = 446;BA.debugLine="Txt =ValoresComunes.GetLanString (\"reg52\") &";
_txt = mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg52")+" -";
 break; }
case 2: {
 //BA.debugLineNum = 448;BA.debugLine="Txt =ValoresComunes.GetLanString (\"reg53\") &";
_txt = mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg53")+" -";
 break; }
case 3: {
 //BA.debugLineNum = 450;BA.debugLine="Txt =ValoresComunes.GetLanString (\"reg54\") &";
_txt = mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg54")+" -";
 break; }
case 4: {
 //BA.debugLineNum = 452;BA.debugLine="Txt =ValoresComunes.GetLanString (\"reg55\") &";
_txt = mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg55")+" -";
 break; }
case 5: {
 //BA.debugLineNum = 454;BA.debugLine="Txt =ValoresComunes.GetLanString (\"reg56\") &";
_txt = mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg56")+" -";
 break; }
case 6: {
 //BA.debugLineNum = 456;BA.debugLine="Txt =ValoresComunes.GetLanString (\"reg57\") &";
_txt = mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg57")+" -";
 break; }
case 7: {
 //BA.debugLineNum = 458;BA.debugLine="Txt = ValoresComunes.GetLanString (\"SPDI\") &";
_txt = mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SPDI")+"1 -";
 break; }
case 8: {
 //BA.debugLineNum = 460;BA.debugLine="Txt =ValoresComunes.GetLanString (\"SPDI\") & \"";
_txt = mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SPDI")+"2 -";
 break; }
}
;
 //BA.debugLineNum = 463;BA.debugLine="Txt= Txt & (Packet.Data (7)-1) & \":\" & (Packet.";
_txt = _txt+BA.NumberToString((_packet.getData()[(int) (7)]-1))+":"+BA.NumberToString((_packet.getData()[(int) (8)]-1))+anywheresoftware.b4a.keywords.Common.CRLF+BA.NumberToString((_packet.getData()[(int) (9)]-1))+"/"+BA.NumberToString((_packet.getData()[(int) (10)]-1))+"/"+BA.NumberToString((_packet.getData()[(int) (11)]-1));
 //BA.debugLineNum = 466;BA.debugLine="ToastMessageShow(Txt,True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(_txt),anywheresoftware.b4a.keywords.Common.True);
 }else if(_msg.contains("BORRADOS")) { 
 //BA.debugLineNum = 469;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 470;BA.debugLine="ToastMessageShow(ValoresComunes.GetLanString (\"";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg139")),anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 472;BA.debugLine="If msg =\"REPETIRMSG\" Then";
if ((_msg).equals("REPETIRMSG")) { 
 //BA.debugLineNum = 473;BA.debugLine="SendTrama(TramaEnviada)";
_sendtrama(_tramaenviada);
 }else {
 //BA.debugLineNum = 476;BA.debugLine="If msg = \"SETFHOK\" Then";
if ((_msg).equals("SETFHOK")) { 
 //BA.debugLineNum = 477;BA.debugLine="ToastMessageShow(ValoresComunes.GetLanString";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg139")),anywheresoftware.b4a.keywords.Common.True);
 }else {
 };
 };
 };
 } 
       catch (Exception e42) {
			processBA.setLastException(e42); };
 //BA.debugLineNum = 487;BA.debugLine="End Sub";
return "";
}
}
