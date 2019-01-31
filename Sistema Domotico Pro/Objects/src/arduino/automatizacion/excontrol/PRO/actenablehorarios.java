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

public class actenablehorarios extends Activity implements B4AActivity{
	public static actenablehorarios mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actenablehorarios");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actenablehorarios).");
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
		activityBA = new BA(this, layout, processBA, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actenablehorarios");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "arduino.automatizacion.excontrol.PRO.actenablehorarios", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actenablehorarios) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actenablehorarios) Resume **");
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
		return actenablehorarios.class;
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
        BA.LogInfo("** Activity (actenablehorarios) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (actenablehorarios) Resume **");
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
public static byte[] _valores = null;
public static anywheresoftware.b4a.objects.SocketWrapper.UDPSocket _udpsocket1 = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _cmdguardar = null;
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
int _he = 0;
int _h = 0;
 //BA.debugLineNum = 44;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 45;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = Fals";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 46;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 };
 //BA.debugLineNum = 51;BA.debugLine="Dim he As Int = 40dip";
_he = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40));
 //BA.debugLineNum = 53;BA.debugLine="Activity.AddMenuItem(ValoresComunes.GetLanString";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg149")),"mnuAllOff");
 //BA.debugLineNum = 54;BA.debugLine="Activity.AddMenuItem(ValoresComunes.GetLanString";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg150")),"mnuAllOn");
 //BA.debugLineNum = 58;BA.debugLine="Activity.LoadLayout (\"frmconfig\")";
mostCurrent._activity.LoadLayout("frmconfig",mostCurrent.activityBA);
 //BA.debugLineNum = 60;BA.debugLine="Activity.Title =ValoresComunes.GetLanString (\"ET\"";
mostCurrent._activity.setTitle(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"ET")));
 //BA.debugLineNum = 61;BA.debugLine="ListView1.Height = Activity.Height - he";
mostCurrent._listview1.setHeight((int) (mostCurrent._activity.getHeight()-_he));
 //BA.debugLineNum = 62;BA.debugLine="ListView1.Width =Activity.Width";
mostCurrent._listview1.setWidth(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 64;BA.debugLine="CmdGuardar.Top=Activity.Height-he";
mostCurrent._cmdguardar.setTop((int) (mostCurrent._activity.getHeight()-_he));
 //BA.debugLineNum = 65;BA.debugLine="CmdGuardar.Left = Activity.Width   - (he+4dip)";
mostCurrent._cmdguardar.setLeft((int) (mostCurrent._activity.getWidth()-(_he+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4)))));
 //BA.debugLineNum = 66;BA.debugLine="CmdGuardar.Height =he";
mostCurrent._cmdguardar.setHeight(_he);
 //BA.debugLineNum = 67;BA.debugLine="CmdGuardar.Width =he' Activity.Width";
mostCurrent._cmdguardar.setWidth(_he);
 //BA.debugLineNum = 68;BA.debugLine="CmdGuardar.Text = \"\"'ValoresComunes.GetLanString";
mostCurrent._cmdguardar.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 69;BA.debugLine="CmdGuardar.SetBackgroundImage(ValoresComunes.CmdI";
mostCurrent._cmdguardar.SetBackgroundImage((android.graphics.Bitmap)(mostCurrent._valorescomunes._cmdimgsave(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 71;BA.debugLine="CmdTerminar.Top=Activity.Height-he";
mostCurrent._cmdterminar.setTop((int) (mostCurrent._activity.getHeight()-_he));
 //BA.debugLineNum = 72;BA.debugLine="CmdTerminar.Height =he";
mostCurrent._cmdterminar.setHeight(_he);
 //BA.debugLineNum = 73;BA.debugLine="CmdTerminar.Width =he";
mostCurrent._cmdterminar.setWidth(_he);
 //BA.debugLineNum = 74;BA.debugLine="CmdTerminar.Left =Activity.Width   - ((he  * 2)";
mostCurrent._cmdterminar.setLeft((int) (mostCurrent._activity.getWidth()-((_he*2)+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)))));
 //BA.debugLineNum = 75;BA.debugLine="CmdTerminar.SetBackgroundImage(ValoresComunes.Cmd";
mostCurrent._cmdterminar.SetBackgroundImage((android.graphics.Bitmap)(mostCurrent._valorescomunes._cmdimgback(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 76;BA.debugLine="CmdTerminar.Text = \"\"";
mostCurrent._cmdterminar.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 79;BA.debugLine="Dim h As Int";
_h = 0;
 //BA.debugLineNum = 80;BA.debugLine="h=75dip";
_h = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75));
 //BA.debugLineNum = 83;BA.debugLine="ListView1.TwoLinesAndBitmap .ItemHeight =h";
mostCurrent._listview1.getTwoLinesAndBitmap().setItemHeight(_h);
 //BA.debugLineNum = 84;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.Width=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setWidth(_h);
 //BA.debugLineNum = 85;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.height=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setHeight(_h);
 //BA.debugLineNum = 87;BA.debugLine="ListView1.TwoLinesAndBitmap.Label .Left =h + h/9";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setLeft((int) (_h+_h/(double)9));
 //BA.debugLineNum = 88;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.height  =h/1.8";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setHeight((int) (_h/(double)1.8));
 //BA.debugLineNum = 89;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.Gravity = Bit.O";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.BOTTOM));
 //BA.debugLineNum = 91;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel .Left =Li";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setLeft(mostCurrent._listview1.getTwoLinesAndBitmap().Label.getLeft());
 //BA.debugLineNum = 92;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.height";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setHeight((int) (_h-mostCurrent._listview1.getTwoLinesAndBitmap().Label.getHeight()));
 //BA.debugLineNum = 93;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.top   = L";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setTop(mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.getHeight());
 //BA.debugLineNum = 94;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.Gravity =";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 97;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 98;BA.debugLine="UDPSocket1.Close";
_udpsocket1.Close();
 //BA.debugLineNum = 99;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 100;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
byte[] _data = null;
String _trama = "";
 //BA.debugLineNum = 101;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 102;BA.debugLine="If ValoresComunes.CloseApp =True Then";
if (mostCurrent._valorescomunes._closeapp==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 103;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 104;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 106;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = True";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 109;BA.debugLine="If Main.MyLan.IsInitialized = False Then Main.My";
if (mostCurrent._main._mylan.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._main._mylan.Initialize(processBA,(int) (0),"");};
 //BA.debugLineNum = 110;BA.debugLine="If UDPSocket1.IsInitialized = False Then	Valores";
if (_udpsocket1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._valorescomunes._iniudp(mostCurrent.activityBA,_udpsocket1);};
 //BA.debugLineNum = 112;BA.debugLine="If ImgCargando.IsInitialized = False Then";
if (mostCurrent._imgcargando.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 113;BA.debugLine="ImgCargando.Initialize (\"ImgCargando\")";
mostCurrent._imgcargando.Initialize(mostCurrent.activityBA,"ImgCargando");
 //BA.debugLineNum = 114;BA.debugLine="ImgCargando.Bitmap = ValoresComunes.Cargando";
mostCurrent._imgcargando.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._cargando(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 115;BA.debugLine="ImgCargando.Gravity = Gravity.FILL";
mostCurrent._imgcargando.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 116;BA.debugLine="Activity.AddView (ImgCargando,  PerXToCurrent(4";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._imgcargando.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (40),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA));
 };
 //BA.debugLineNum = 120;BA.debugLine="CmdGuardar.Enabled =False";
mostCurrent._cmdguardar.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 124;BA.debugLine="AniCargando.InitializeRotateCenter( \"AniCargando";
mostCurrent._anicargando.InitializeRotateCenter(mostCurrent.activityBA,"AniCargando",(float) (0),(float) (180),(android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 125;BA.debugLine="AniCargando.Duration  =   1000";
mostCurrent._anicargando.setDuration((long) (1000));
 //BA.debugLineNum = 126;BA.debugLine="AniCargando.RepeatCount =5";
mostCurrent._anicargando.setRepeatCount((int) (5));
 //BA.debugLineNum = 127;BA.debugLine="AniCargando.RepeatMode = AniCargando.REPEAT_R";
mostCurrent._anicargando.setRepeatMode(mostCurrent._anicargando.REPEAT_REVERSE);
 //BA.debugLineNum = 129;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 130;BA.debugLine="Dim trama As String";
_trama = "";
 //BA.debugLineNum = 131;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 132;BA.debugLine="trama = \"ENABLEHOR\" & ValoresComunes.Central.Pa";
_trama = "ENABLEHOR"+mostCurrent._valorescomunes._central.Password;
 }else {
 //BA.debugLineNum = 134;BA.debugLine="trama = \"ENABLEHOR\"";
_trama = "ENABLEHOR";
 };
 //BA.debugLineNum = 137;BA.debugLine="data =trama.GetBytes (\"UTF8\")";
_data = _trama.getBytes("UTF8");
 //BA.debugLineNum = 138;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 }else {
 //BA.debugLineNum = 140;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 141;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 };
 //BA.debugLineNum = 143;BA.debugLine="End Sub";
return "";
}
public static String  _actualizavalores() throws Exception{
String _nombre = "";
String _descripcion = "";
int _i = 0;
 //BA.debugLineNum = 153;BA.debugLine="Sub ActualizaValores()";
 //BA.debugLineNum = 154;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 155;BA.debugLine="Dim Nombre, descripcion As String";
_nombre = "";
_descripcion = "";
 //BA.debugLineNum = 156;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 157;BA.debugLine="For i =0 To 59";
{
final int step4 = 1;
final int limit4 = (int) (59);
_i = (int) (0) ;
for (;(step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4) ;_i = ((int)(0 + _i + step4))  ) {
 //BA.debugLineNum = 159;BA.debugLine="If i<30 Then";
if (_i<30) { 
 //BA.debugLineNum = 160;BA.debugLine="Nombre=ValoresComunes.Circuitos (i).Nombre";
_nombre = mostCurrent._valorescomunes._circuitos[_i].Nombre;
 //BA.debugLineNum = 161;BA.debugLine="descripcion=ValoresComunes.Circuitos (i).descri";
_descripcion = mostCurrent._valorescomunes._circuitos[_i].Descripcion;
 }else if(_i<40) { 
 //BA.debugLineNum = 163;BA.debugLine="Nombre=ValoresComunes.Scenes(i-30).Nombre";
_nombre = mostCurrent._valorescomunes._scenes[(int) (_i-30)].Nombre;
 //BA.debugLineNum = 164;BA.debugLine="descripcion=ValoresComunes.Scenes(i-30).descri";
_descripcion = mostCurrent._valorescomunes._scenes[(int) (_i-30)].Descripcion;
 }else if(_i<50) { 
 //BA.debugLineNum = 166;BA.debugLine="Nombre=ValoresComunes.Condicionados (i-40).Nom";
_nombre = mostCurrent._valorescomunes._condicionados[(int) (_i-40)].Nombre;
 //BA.debugLineNum = 167;BA.debugLine="descripcion=ValoresComunes.Condicionados(i-40)";
_descripcion = mostCurrent._valorescomunes._condicionados[(int) (_i-40)].Descripcion;
 }else if(_i<60) { 
 //BA.debugLineNum = 169;BA.debugLine="Nombre=ValoresComunes.Functions  (i-50).Nombre";
_nombre = mostCurrent._valorescomunes._functions[(int) (_i-50)].Nombre;
 //BA.debugLineNum = 170;BA.debugLine="descripcion=ValoresComunes.Functions(i-50).des";
_descripcion = mostCurrent._valorescomunes._functions[(int) (_i-50)].Descripcion;
 };
 //BA.debugLineNum = 173;BA.debugLine="If Nombre <> \"\" Then";
if ((_nombre).equals("") == false) { 
 //BA.debugLineNum = 174;BA.debugLine="If Valores(i)=0   Then";
if (_valores[_i]==0) { 
 //BA.debugLineNum = 175;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (Nombre ,desc";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_nombre),BA.ObjectToCharSequence(_descripcion),(android.graphics.Bitmap)(mostCurrent._valorescomunes._checkoff(mostCurrent.activityBA).getObject()),(Object)(_i));
 }else {
 //BA.debugLineNum = 177;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 ( Nombre ,des";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_nombre),BA.ObjectToCharSequence(_descripcion),(android.graphics.Bitmap)(mostCurrent._valorescomunes._checkon(mostCurrent.activityBA).getObject()),(Object)(_i));
 };
 };
 }
};
 //BA.debugLineNum = 181;BA.debugLine="End Sub";
return "";
}
public static String  _anicargando_animationend() throws Exception{
 //BA.debugLineNum = 145;BA.debugLine="Sub AniCargando_AnimationEnd";
 //BA.debugLineNum = 147;BA.debugLine="If PaqueteEnviado = True Then";
if (_paqueteenviado==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 148;BA.debugLine="AniCargando.Start (ImgCargando)";
mostCurrent._anicargando.Start((android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 149;BA.debugLine="SendTrama(TramaEnviada)";
_sendtrama(_tramaenviada);
 };
 //BA.debugLineNum = 151;BA.debugLine="End Sub";
return "";
}
public static String  _cmdguardar_click() throws Exception{
int _longtrama = 0;
byte[] _data = null;
int _i = 0;
 //BA.debugLineNum = 297;BA.debugLine="Sub CmdGuardar_Click";
 //BA.debugLineNum = 300;BA.debugLine="Dim LongTrama As Int";
_longtrama = 0;
 //BA.debugLineNum = 301;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 302;BA.debugLine="LongTrama= 72";
_longtrama = (int) (72);
 }else {
 //BA.debugLineNum = 304;BA.debugLine="LongTrama= 64";
_longtrama = (int) (64);
 };
 //BA.debugLineNum = 306;BA.debugLine="Dim data(LongTrama) As Byte";
_data = new byte[_longtrama];
;
 //BA.debugLineNum = 308;BA.debugLine="data(0)=87";
_data[(int) (0)] = (byte) (87);
 //BA.debugLineNum = 309;BA.debugLine="data(1)=72";
_data[(int) (1)] = (byte) (72);
 //BA.debugLineNum = 310;BA.debugLine="data(2)=79";
_data[(int) (2)] = (byte) (79);
 //BA.debugLineNum = 311;BA.debugLine="data(3)=82";
_data[(int) (3)] = (byte) (82);
 //BA.debugLineNum = 312;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 314;BA.debugLine="For i =0 To 59";
{
final int step13 = 1;
final int limit13 = (int) (59);
_i = (int) (0) ;
for (;(step13 > 0 && _i <= limit13) || (step13 < 0 && _i >= limit13) ;_i = ((int)(0 + _i + step13))  ) {
 //BA.debugLineNum = 315;BA.debugLine="data(i+4)=Valores(i)+1";
_data[(int) (_i+4)] = (byte) (_valores[_i]+1);
 }
};
 //BA.debugLineNum = 317;BA.debugLine="If ValoresComunes.central.ConexionSegura Then Val";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
mostCurrent._valorescomunes._completartrama(mostCurrent.activityBA,_data);};
 //BA.debugLineNum = 318;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 //BA.debugLineNum = 319;BA.debugLine="End Sub";
return "";
}
public static String  _cmdterminar_click() throws Exception{
 //BA.debugLineNum = 294;BA.debugLine="Sub CmdTerminar_Click";
 //BA.debugLineNum = 295;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 296;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 15;BA.debugLine="Dim ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Dim CmdGuardar As Button";
mostCurrent._cmdguardar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Dim CmdTerminar As Button";
mostCurrent._cmdterminar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Dim TramaEnviada() As Byte";
_tramaenviada = new byte[(int) (0)];
;
 //BA.debugLineNum = 21;BA.debugLine="Dim ImgCargando As ImageView";
mostCurrent._imgcargando = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Dim AniCargando As  Animation";
mostCurrent._anicargando = new anywheresoftware.b4a.objects.AnimationWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Dim PaqueteEnviado As Boolean";
_paqueteenviado = false;
 //BA.debugLineNum = 26;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 184;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 186;BA.debugLine="If Valores(Value)=1 Then";
if (_valores[(int)(BA.ObjectToNumber(_value))]==1) { 
 //BA.debugLineNum = 187;BA.debugLine="Valores(Value)=0";
_valores[(int)(BA.ObjectToNumber(_value))] = (byte) (0);
 }else {
 //BA.debugLineNum = 189;BA.debugLine="Valores(Value)=1";
_valores[(int)(BA.ObjectToNumber(_value))] = (byte) (1);
 };
 //BA.debugLineNum = 191;BA.debugLine="ActualizaValores";
_actualizavalores();
 //BA.debugLineNum = 193;BA.debugLine="End Sub";
return "";
}
public static String  _mnualloff_click() throws Exception{
int _c = 0;
 //BA.debugLineNum = 36;BA.debugLine="Sub mnuAllOff_Click";
 //BA.debugLineNum = 38;BA.debugLine="Dim c As Int";
_c = 0;
 //BA.debugLineNum = 39;BA.debugLine="For c =0 To 59";
{
final int step2 = 1;
final int limit2 = (int) (59);
_c = (int) (0) ;
for (;(step2 > 0 && _c <= limit2) || (step2 < 0 && _c >= limit2) ;_c = ((int)(0 + _c + step2))  ) {
 //BA.debugLineNum = 40;BA.debugLine="Valores(c)=0";
_valores[_c] = (byte) (0);
 }
};
 //BA.debugLineNum = 42;BA.debugLine="ActualizaValores";
_actualizavalores();
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return "";
}
public static String  _mnuallon_click() throws Exception{
int _c = 0;
 //BA.debugLineNum = 28;BA.debugLine="Sub mnuAllOn_Click";
 //BA.debugLineNum = 29;BA.debugLine="Dim c As Int";
_c = 0;
 //BA.debugLineNum = 30;BA.debugLine="For c =0 To 59";
{
final int step2 = 1;
final int limit2 = (int) (59);
_c = (int) (0) ;
for (;(step2 > 0 && _c <= limit2) || (step2 < 0 && _c >= limit2) ;_c = ((int)(0 + _c + step2))  ) {
 //BA.debugLineNum = 31;BA.debugLine="Valores(c)=1";
_valores[_c] = (byte) (1);
 }
};
 //BA.debugLineNum = 33;BA.debugLine="ActualizaValores";
_actualizavalores();
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub process_globals";
 //BA.debugLineNum = 8;BA.debugLine="Dim Valores(60) As   Byte";
_valores = new byte[(int) (60)];
;
 //BA.debugLineNum = 9;BA.debugLine="Dim UDPSocket1 As UDPSocket";
_udpsocket1 = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket();
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public static boolean  _sendingtrama(byte[] _data) throws Exception{
anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet = null;
 //BA.debugLineNum = 283;BA.debugLine="Sub SendingTrama (data() As Byte) As Boolean";
 //BA.debugLineNum = 284;BA.debugLine="Try";
try { //BA.debugLineNum = 285;BA.debugLine="Dim Packet As UDPPacket";
_packet = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket();
 //BA.debugLineNum = 286;BA.debugLine="Packet.Initialize(data, ValoresComunes.ip, Valor";
_packet.Initialize(_data,mostCurrent._valorescomunes._ip,mostCurrent._valorescomunes._puerto);
 //BA.debugLineNum = 287;BA.debugLine="UDPSocket1.Send(Packet)";
_udpsocket1.Send(_packet);
 //BA.debugLineNum = 288;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 290;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 292;BA.debugLine="End Sub";
return false;
}
public static void  _sendtrama(byte[] _data) throws Exception{
ResumableSub_SendTrama rsub = new ResumableSub_SendTrama(null,_data);
rsub.resume(processBA, null);
}
public static class ResumableSub_SendTrama extends BA.ResumableSub {
public ResumableSub_SendTrama(arduino.automatizacion.excontrol.PRO.actenablehorarios parent,byte[] _data) {
this.parent = parent;
this._data = _data;
}
arduino.automatizacion.excontrol.PRO.actenablehorarios parent;
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
 //BA.debugLineNum = 238;BA.debugLine="Dim Resultado As Boolean";
_resultado = false;
 //BA.debugLineNum = 239;BA.debugLine="Dim Reintento As Int";
_reintento = 0;
 //BA.debugLineNum = 241;BA.debugLine="CmdGuardar.Enabled =False";
parent.mostCurrent._cmdguardar.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 242;BA.debugLine="ImgCargando.Visible =True";
parent.mostCurrent._imgcargando.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 243;BA.debugLine="AniCargando.Start (ImgCargando)";
parent.mostCurrent._anicargando.Start((android.view.View)(parent.mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 245;BA.debugLine="PaqueteEnviado =False";
parent._paqueteenviado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 246;BA.debugLine="TramaEnviada= data";
parent._tramaenviada = _data;
 //BA.debugLineNum = 248;BA.debugLine="Do 	While Resultado= False AND Reintento < 40";
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
 //BA.debugLineNum = 249;BA.debugLine="Dim ServerSocket1 As ServerSocket";
_serversocket1 = new anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper();
 //BA.debugLineNum = 250;BA.debugLine="Dim MyIp As String";
_myip = "";
 //BA.debugLineNum = 251;BA.debugLine="If ActMosaico.Forzar3g Then";
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
 //BA.debugLineNum = 252;BA.debugLine="MyIp=ServerSocket1.GetMyIP";
_myip = _serversocket1.GetMyIP();
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 254;BA.debugLine="MyIp=ServerSocket1.GetMyWifiIP";
_myip = _serversocket1.GetMyWifiIP();
 if (true) break;
;
 //BA.debugLineNum = 256;BA.debugLine="If MyIp  <> \"127.0.0.1\" Then";

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
 //BA.debugLineNum = 257;BA.debugLine="Resultado = True";
_resultado = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 13:
//C
this.state = 14;
 //BA.debugLineNum = 259;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 260;BA.debugLine="Sleep (200)";
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
 //BA.debugLineNum = 264;BA.debugLine="If Resultado = True Then";

case 15:
//if
this.state = 28;
if (_resultado==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 17;
}if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 265;BA.debugLine="Resultado =False";
_resultado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 266;BA.debugLine="Reintento =0";
_reintento = (int) (0);
 //BA.debugLineNum = 267;BA.debugLine="Do 	While Resultado= False AND Reintento < 40";
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
 //BA.debugLineNum = 268;BA.debugLine="Resultado = SendingTrama(data)";
_resultado = _sendingtrama(_data);
 //BA.debugLineNum = 269;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 270;BA.debugLine="If Resultado=False Then Sleep (200)";
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
 //BA.debugLineNum = 276;BA.debugLine="If Resultado = False  Then";

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
 //BA.debugLineNum = 277;BA.debugLine="ActMosaico.Conectado =False";
parent.mostCurrent._actmosaico._conectado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 278;BA.debugLine="StartActivity(ActMosaico)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._actmosaico.getObject()));
 if (true) break;

case 32:
//C
this.state = 33;
 //BA.debugLineNum = 280;BA.debugLine="PaqueteEnviado=True";
parent._paqueteenviado = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 33:
//C
this.state = -1;
;
 //BA.debugLineNum = 282;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _udp_packetarrived(anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet) throws Exception{
String _msg = "";
int _c = 0;
 //BA.debugLineNum = 195;BA.debugLine="Sub UDP_PacketArrived (Packet As UDPPacket)";
 //BA.debugLineNum = 196;BA.debugLine="Try";
try { //BA.debugLineNum = 197;BA.debugLine="Dim msg As String";
_msg = "";
 //BA.debugLineNum = 198;BA.debugLine="msg = BytesToString(Packet.Data, Packet.Offse";
_msg = anywheresoftware.b4a.keywords.Common.BytesToString(_packet.getData(),_packet.getOffset(),_packet.getLength(),"UTF8");
 //BA.debugLineNum = 200;BA.debugLine="If msg.Contains (\"ENHOR\") Then";
if (_msg.contains("ENHOR")) { 
 //BA.debugLineNum = 201;BA.debugLine="Dim  c As Int";
_c = 0;
 //BA.debugLineNum = 202;BA.debugLine="For c =0 To 59";
{
final int step6 = 1;
final int limit6 = (int) (59);
_c = (int) (0) ;
for (;(step6 > 0 && _c <= limit6) || (step6 < 0 && _c >= limit6) ;_c = ((int)(0 + _c + step6))  ) {
 //BA.debugLineNum = 203;BA.debugLine="Valores(c)=0";
_valores[_c] = (byte) (0);
 }
};
 //BA.debugLineNum = 205;BA.debugLine="For c = 5 To Packet.Length -1";
{
final int step9 = 1;
final int limit9 = (int) (_packet.getLength()-1);
_c = (int) (5) ;
for (;(step9 > 0 && _c <= limit9) || (step9 < 0 && _c >= limit9) ;_c = ((int)(0 + _c + step9))  ) {
 //BA.debugLineNum = 206;BA.debugLine="Valores(c - 5)= Packet.Data (c)-1";
_valores[(int) (_c-5)] = (byte) (_packet.getData()[_c]-1);
 //BA.debugLineNum = 207;BA.debugLine="If Valores(c - 5) <> 1 Then Valores(c - 5)= 0";
if (_valores[(int) (_c-5)]!=1) { 
_valores[(int) (_c-5)] = (byte) (0);};
 }
};
 //BA.debugLineNum = 210;BA.debugLine="ActualizaValores";
_actualizavalores();
 }else if((_msg).equals("REPETIRMSG")) { 
 //BA.debugLineNum = 214;BA.debugLine="SendTrama(TramaEnviada)";
_sendtrama(_tramaenviada);
 //BA.debugLineNum = 215;BA.debugLine="Return";
if (true) return "";
 }else if(_msg.contains("COMPLETED")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 220;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 226;BA.debugLine="PaqueteEnviado = False";
_paqueteenviado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 227;BA.debugLine="CmdGuardar.Enabled =True";
mostCurrent._cmdguardar.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 228;BA.debugLine="AniCargando.Stop(ImgCargando)";
mostCurrent._anicargando.Stop((android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 229;BA.debugLine="ImgCargando.Visible =False";
mostCurrent._imgcargando.setVisible(anywheresoftware.b4a.keywords.Common.False);
 } 
       catch (Exception e25) {
			processBA.setLastException(e25); };
 //BA.debugLineNum = 235;BA.debugLine="End Sub";
return "";
}
}
