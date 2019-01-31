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

public class actalarmas2 extends Activity implements B4AActivity{
	public static actalarmas2 mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actalarmas2");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actalarmas2).");
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
		activityBA = new BA(this, layout, processBA, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actalarmas2");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "arduino.automatizacion.excontrol.PRO.actalarmas2", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actalarmas2) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actalarmas2) Resume **");
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
		return actalarmas2.class;
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
        BA.LogInfo("** Activity (actalarmas2) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (actalarmas2) Resume **");
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
public static anywheresoftware.b4a.phone.Phone.PhoneWakeState _pw = null;
public anywheresoftware.b4a.objects.collections.List _timetable = null;
public static byte _circuitomostrado = (byte)0;
public anywheresoftware.b4a.objects.ButtonWrapper _cmdseleccion = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public static byte[] _tramaenviada = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgcargando = null;
public anywheresoftware.b4a.objects.AnimationWrapper _anicargando = null;
public static boolean _paqueteenviado = false;
public anywheresoftware.b4a.agraham.dialogs.InputDialog.ColorPickerDialog _dlg1 = null;
public anywheresoftware.b4a.agraham.dialogs.InputDialog.ColorPickerDialog _dlg2 = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listcir = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listcon = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listescenas = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listfunciones = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
public anywheresoftware.b4a.objects.TabHostWrapper _tabconfig = null;
public anywheresoftware.b4a.objects.ButtonWrapper _cmdbarok = null;
public anywheresoftware.b4a.objects.ButtonWrapper _cmdbarcancel = null;
public anywheresoftware.b4a.objects.PanelWrapper _panbarra = null;
public arduino.automatizacion.excontrol.PRO.mbvseekbar _barra = null;
public arduino.automatizacion.excontrol.PRO.acthorarios2._horario _hr = null;
public static boolean _enablemenu = false;
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
 //BA.debugLineNum = 52;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 54;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = Fal";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 55;BA.debugLine="Activity.RemoveAllViews";
mostCurrent._activity.RemoveAllViews();
 //BA.debugLineNum = 57;BA.debugLine="Activity.LoadLayout (\"FrmHorario\")";
mostCurrent._activity.LoadLayout("FrmHorario",mostCurrent.activityBA);
 //BA.debugLineNum = 59;BA.debugLine="Activity.AddMenuItem(ValoresComunes.GetLanString";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg185")),"mnuNew");
 //BA.debugLineNum = 60;BA.debugLine="Activity.AddMenuItem(ValoresComunes.GetLanString";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg128")),"mnuSave");
 //BA.debugLineNum = 62;BA.debugLine="ListView1.Height = PerYToCurrent(89)";
mostCurrent._listview1.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (89),mostCurrent.activityBA));
 //BA.debugLineNum = 63;BA.debugLine="ListView1.Width =Activity.Width";
mostCurrent._listview1.setWidth(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 66;BA.debugLine="CmdSeleccion.Top=PerYToCurrent(90)";
mostCurrent._cmdseleccion.setTop(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (90),mostCurrent.activityBA));
 //BA.debugLineNum = 67;BA.debugLine="CmdSeleccion.Height =PerYToCurrent(10)";
mostCurrent._cmdseleccion.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (10),mostCurrent.activityBA));
 //BA.debugLineNum = 68;BA.debugLine="CmdSeleccion.Width =Activity.Width";
mostCurrent._cmdseleccion.setWidth(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 82;BA.debugLine="Dim h As Int";
_h = 0;
 //BA.debugLineNum = 83;BA.debugLine="h=75dip";
_h = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75));
 //BA.debugLineNum = 86;BA.debugLine="ListView1.TwoLinesAndBitmap .ItemHeight =h";
mostCurrent._listview1.getTwoLinesAndBitmap().setItemHeight(_h);
 //BA.debugLineNum = 87;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.Width=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setWidth(_h);
 //BA.debugLineNum = 88;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.height=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setHeight(_h);
 //BA.debugLineNum = 90;BA.debugLine="ListView1.TwoLinesAndBitmap.Label .Left =h + h/9";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setLeft((int) (_h+_h/(double)9));
 //BA.debugLineNum = 91;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.height  =h/1.8";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setHeight((int) (_h/(double)1.8));
 //BA.debugLineNum = 92;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.Gravity = Bit.O";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.BOTTOM));
 //BA.debugLineNum = 94;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel .Left =Li";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setLeft(mostCurrent._listview1.getTwoLinesAndBitmap().Label.getLeft());
 //BA.debugLineNum = 95;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.height";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setHeight((int) (_h-mostCurrent._listview1.getTwoLinesAndBitmap().Label.getHeight()));
 //BA.debugLineNum = 96;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.top   = L";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setTop(mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.getHeight());
 //BA.debugLineNum = 97;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.Gravity =";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 99;BA.debugLine="ValoresComunes.LoadPaleta(dlg1  ,dlg2)";
mostCurrent._valorescomunes._loadpaleta(mostCurrent.activityBA,mostCurrent._dlg1,mostCurrent._dlg2);
 //BA.debugLineNum = 101;BA.debugLine="IniBarDialog";
_inibardialog();
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 185;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 186;BA.debugLine="If UserClosed Then";
if (_userclosed) { 
 //BA.debugLineNum = 188;BA.debugLine="UDPSocket1.Close";
_udpsocket1.Close();
 //BA.debugLineNum = 189;BA.debugLine="pw.ReleaseKeepAlive";
_pw.ReleaseKeepAlive();
 //BA.debugLineNum = 190;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 192;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
int _i = 0;
byte[] _data = null;
String _trama = "";
 //BA.debugLineNum = 104;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 105;BA.debugLine="If ValoresComunes.CloseApp =True Then";
if (mostCurrent._valorescomunes._closeapp==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 106;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 107;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 109;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = True";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 110;BA.debugLine="If Main.MyLan.IsInitialized = False Then Main.My";
if (mostCurrent._main._mylan.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._main._mylan.Initialize(processBA,(int) (0),"");};
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
 //BA.debugLineNum = 120;BA.debugLine="If UDPSocket1.IsInitialized = False Then  Valore";
if (_udpsocket1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._valorescomunes._iniudp(mostCurrent.activityBA,_udpsocket1);};
 //BA.debugLineNum = 121;BA.debugLine="Timetable.Initialize";
mostCurrent._timetable.Initialize();
 //BA.debugLineNum = 122;BA.debugLine="CircuitoMostrado=100";
_circuitomostrado = (byte) (100);
 //BA.debugLineNum = 124;BA.debugLine="Activity.Title = ValoresComunes.GetLanString (\"T";
mostCurrent._activity.setTitle(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"TC")));
 //BA.debugLineNum = 126;BA.debugLine="EnableMenu =False";
_enablemenu = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 128;BA.debugLine="CmdSeleccion.Enabled =False";
mostCurrent._cmdseleccion.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 132;BA.debugLine="AniCargando.InitializeRotateCenter( \"AniCargando";
mostCurrent._anicargando.InitializeRotateCenter(mostCurrent.activityBA,"AniCargando",(float) (0),(float) (180),(android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 133;BA.debugLine="AniCargando.Duration = 1000";
mostCurrent._anicargando.setDuration((long) (1000));
 //BA.debugLineNum = 134;BA.debugLine="AniCargando.RepeatCount =5";
mostCurrent._anicargando.setRepeatCount((int) (5));
 //BA.debugLineNum = 135;BA.debugLine="AniCargando.RepeatMode = AniCargando.REPEAT_R";
mostCurrent._anicargando.setRepeatMode(mostCurrent._anicargando.REPEAT_REVERSE);
 //BA.debugLineNum = 138;BA.debugLine="listCir.Initialize (\"listNewHor\")";
mostCurrent._listcir.Initialize(mostCurrent.activityBA,"listNewHor");
 //BA.debugLineNum = 139;BA.debugLine="ListCon.Initialize (\"listNewHor\")";
mostCurrent._listcon.Initialize(mostCurrent.activityBA,"listNewHor");
 //BA.debugLineNum = 140;BA.debugLine="listEscenas.Initialize (\"listNewHor\")";
mostCurrent._listescenas.Initialize(mostCurrent.activityBA,"listNewHor");
 //BA.debugLineNum = 141;BA.debugLine="listFunciones.Initialize (\"listNewHor\")";
mostCurrent._listfunciones.Initialize(mostCurrent.activityBA,"listNewHor");
 //BA.debugLineNum = 142;BA.debugLine="TabConfig.Initialize (\"TabConfig\")";
mostCurrent._tabconfig.Initialize(mostCurrent.activityBA,"TabConfig");
 //BA.debugLineNum = 143;BA.debugLine="pnl.Initialize(\"pnl\")";
mostCurrent._pnl.Initialize(mostCurrent.activityBA,"pnl");
 //BA.debugLineNum = 144;BA.debugLine="pnl.Color= Colors.Black";
mostCurrent._pnl.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 146;BA.debugLine="TabConfig.AddTabWithIcon2 (\"\", ValoresComunes.Bo";
mostCurrent._tabconfig.AddTabWithIcon2("",(android.graphics.Bitmap)(mostCurrent._valorescomunes._bombillades(mostCurrent.activityBA).getObject()),(android.graphics.Bitmap)(mostCurrent._valorescomunes._bombillades(mostCurrent.activityBA).getObject()),(android.view.View)(mostCurrent._listcir.getObject()));
 //BA.debugLineNum = 147;BA.debugLine="TabConfig.AddTabWithIcon2 (\"\", ValoresComunes.sc";
mostCurrent._tabconfig.AddTabWithIcon2("",(android.graphics.Bitmap)(mostCurrent._valorescomunes._scene2(mostCurrent.activityBA).getObject()),(android.graphics.Bitmap)(mostCurrent._valorescomunes._scene2(mostCurrent.activityBA).getObject()),(android.view.View)(mostCurrent._listescenas.getObject()));
 //BA.debugLineNum = 148;BA.debugLine="TabConfig.AddTabWithIcon2 (\"\", ValoresComunes.Ch";
mostCurrent._tabconfig.AddTabWithIcon2("",(android.graphics.Bitmap)(mostCurrent._valorescomunes._checkon(mostCurrent.activityBA).getObject()),(android.graphics.Bitmap)(mostCurrent._valorescomunes._checkon(mostCurrent.activityBA).getObject()),(android.view.View)(mostCurrent._listcon.getObject()));
 //BA.debugLineNum = 149;BA.debugLine="TabConfig.AddTabWithIcon2 (\"\", ValoresComunes.Fu";
mostCurrent._tabconfig.AddTabWithIcon2("",(android.graphics.Bitmap)(mostCurrent._valorescomunes._funciones(mostCurrent.activityBA).getObject()),(android.graphics.Bitmap)(mostCurrent._valorescomunes._funciones(mostCurrent.activityBA).getObject()),(android.view.View)(mostCurrent._listfunciones.getObject()));
 //BA.debugLineNum = 151;BA.debugLine="pnl.AddView(TabConfig , 0,  0,   PerXToCurrent(7";
mostCurrent._pnl.AddView((android.view.View)(mostCurrent._tabconfig.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (70),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (20),mostCurrent.activityBA));
 //BA.debugLineNum = 153;BA.debugLine="Dim I As Int";
_i = 0;
 //BA.debugLineNum = 154;BA.debugLine="For I =0 To 9";
{
final int step36 = 1;
final int limit36 = (int) (9);
_i = (int) (0) ;
for (;(step36 > 0 && _i <= limit36) || (step36 < 0 && _i >= limit36) ;_i = ((int)(0 + _i + step36))  ) {
 //BA.debugLineNum = 156;BA.debugLine="If ValoresComunes.Scenes (I).Nombre<>\"\" Then 	l";
if ((mostCurrent._valorescomunes._scenes[_i].Nombre).equals("") == false) { 
mostCurrent._listescenas.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._scenes[_i].Nombre),BA.ObjectToCharSequence(mostCurrent._valorescomunes._scenes[_i].Descripcion),(android.graphics.Bitmap)(mostCurrent._valorescomunes._scene2(mostCurrent.activityBA).getObject()),(Object)(_i+30));};
 //BA.debugLineNum = 157;BA.debugLine="If ValoresComunes.Condicionados (I).Nombre <> \"";
if ((mostCurrent._valorescomunes._condicionados[_i].Nombre).equals("") == false) { 
mostCurrent._listcon.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._condicionados[_i].Nombre),BA.ObjectToCharSequence(mostCurrent._valorescomunes._condicionados[_i].Descripcion),(android.graphics.Bitmap)(mostCurrent._valorescomunes._checkoff(mostCurrent.activityBA).getObject()),(Object)(_i+40));};
 //BA.debugLineNum = 158;BA.debugLine="If ValoresComunes.Functions (I).Nombre<>\"\" Then";
if ((mostCurrent._valorescomunes._functions[_i].Nombre).equals("") == false) { 
mostCurrent._listfunciones.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._functions[_i].Nombre),BA.ObjectToCharSequence(mostCurrent._valorescomunes._functions[_i].Descripcion),(android.graphics.Bitmap)(mostCurrent._valorescomunes._funciones(mostCurrent.activityBA).getObject()),(Object)(_i+50));};
 }
};
 //BA.debugLineNum = 162;BA.debugLine="For I =0 To 29";
{
final int step41 = 1;
final int limit41 = (int) (29);
_i = (int) (0) ;
for (;(step41 > 0 && _i <= limit41) || (step41 < 0 && _i >= limit41) ;_i = ((int)(0 + _i + step41))  ) {
 //BA.debugLineNum = 163;BA.debugLine="If ValoresComunes.Circuitos  (I).Nombre <> \"\" A";
if ((mostCurrent._valorescomunes._circuitos[_i].Nombre).equals("") == false && mostCurrent._valorescomunes._circuitos[_i].Rango>0 && mostCurrent._valorescomunes._circuitos[_i].Rango!=33) { 
mostCurrent._listcir.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Nombre),BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Descripcion),(android.graphics.Bitmap)(mostCurrent._valorescomunes._iconocircuito(mostCurrent.activityBA,_i,(int) (1)).getObject()),(Object)(_i));};
 }
};
 //BA.debugLineNum = 167;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 168;BA.debugLine="Dim trama As String";
_trama = "";
 //BA.debugLineNum = 169;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 170;BA.debugLine="trama = \"RETRIGGER\" & ValoresComunes.Central.Pa";
_trama = "RETRIGGER"+mostCurrent._valorescomunes._central.Password;
 }else {
 //BA.debugLineNum = 172;BA.debugLine="trama = \"RETRIGGER\"";
_trama = "RETRIGGER";
 };
 //BA.debugLineNum = 175;BA.debugLine="data =trama.GetBytes (\"UTF8\")";
_data = _trama.getBytes("UTF8");
 //BA.debugLineNum = 176;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 }else {
 //BA.debugLineNum = 178;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 179;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 };
 //BA.debugLineNum = 183;BA.debugLine="End Sub";
return "";
}
public static String  _addcirtoscr(int _p,arduino.automatizacion.excontrol.PRO.acthorarios2._horario _h) throws Exception{
String _hor = "";
double _val1 = 0;
 //BA.debugLineNum = 732;BA.debugLine="Sub AddCirToScr(p As Int , H As Horario)";
 //BA.debugLineNum = 734;BA.debugLine="Dim hor As String";
_hor = "";
 //BA.debugLineNum = 735;BA.debugLine="hor= H.Minuto";
_hor = BA.NumberToString(_h.Minuto);
 //BA.debugLineNum = 736;BA.debugLine="If hor.Length <2 Then";
if (_hor.length()<2) { 
 //BA.debugLineNum = 737;BA.debugLine="hor= H.Hora & \":0\" & hor";
_hor = BA.NumberToString(_h.Hora)+":0"+_hor;
 }else {
 //BA.debugLineNum = 739;BA.debugLine="hor= H.Hora & \":\" & hor";
_hor = BA.NumberToString(_h.Hora)+":"+_hor;
 };
 //BA.debugLineNum = 742;BA.debugLine="If H.Circuito < 30  Then";
if (_h.Circuito<30) { 
 //BA.debugLineNum = 746;BA.debugLine="If ValoresComunes.Circuitos (H.Circuito).Rango=2";
if (mostCurrent._valorescomunes._circuitos[(int) (_h.Circuito)].Rango==29) { 
 //BA.debugLineNum = 748;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (hor & \" : \"& H";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_hor+" : "+BA.NumberToString(_h.Salida)+"ºC"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[(int) (_h.Circuito)].Nombre),(android.graphics.Bitmap)(mostCurrent._valorescomunes._temperatura(mostCurrent.activityBA).getObject()),(Object)(_p));
 }else if(mostCurrent._valorescomunes._circuitos[(int) (_h.Circuito)].Rango==30) { 
 //BA.debugLineNum = 751;BA.debugLine="Dim val1   As Double";
_val1 = 0;
 //BA.debugLineNum = 752;BA.debugLine="val1= (H.Salida +150)/10";
_val1 = (_h.Salida+150)/(double)10;
 //BA.debugLineNum = 754;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (hor & \" : \"& v";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_hor+" : "+BA.NumberToString(_val1)+"ºC"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[(int) (_h.Circuito)].Nombre),(android.graphics.Bitmap)(mostCurrent._valorescomunes._temperatura(mostCurrent.activityBA).getObject()),(Object)(_p));
 }else if(mostCurrent._valorescomunes._circuitos[(int) (_h.Circuito)].Rango==31) { 
 //BA.debugLineNum = 756;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (hor & \" : - \"&";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_hor+" : - "+BA.NumberToString(_h.Salida)+"ºC"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[(int) (_h.Circuito)].Nombre),(android.graphics.Bitmap)(mostCurrent._valorescomunes._temperatura(mostCurrent.activityBA).getObject()),(Object)(_p));
 }else if(mostCurrent._valorescomunes._circuitos[(int) (_h.Circuito)].Rango==53 || mostCurrent._valorescomunes._circuitos[(int) (_h.Circuito)].Rango==54) { 
 //BA.debugLineNum = 760;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (hor & \" : \"& H";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_hor+" : "+BA.NumberToString(_h.Salida)),BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[(int) (_h.Circuito)].Nombre),(android.graphics.Bitmap)(mostCurrent._valorescomunes._iconocircuito(mostCurrent.activityBA,(int) (_h.Circuito),_h.Salida).getObject()),(Object)(_p));
 }else if(mostCurrent._valorescomunes._circuitos[(int) (_h.Circuito)].Rango==55) { 
 //BA.debugLineNum = 764;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (hor & \" : \"& (";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_hor+" : "+BA.NumberToString((_h.Salida*10))),BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[(int) (_h.Circuito)].Nombre),(android.graphics.Bitmap)(mostCurrent._valorescomunes._iconocircuito(mostCurrent.activityBA,(int) (_h.Circuito),_h.Salida).getObject()),(Object)(_p));
 }else if(mostCurrent._valorescomunes._circuitos[(int) (_h.Circuito)].Rango==56) { 
 //BA.debugLineNum = 768;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (hor & \" : \"& (";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_hor+" : "+BA.NumberToString((_h.Salida*100))),BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[(int) (_h.Circuito)].Nombre),(android.graphics.Bitmap)(mostCurrent._valorescomunes._iconocircuito(mostCurrent.activityBA,(int) (_h.Circuito),_h.Salida).getObject()),(Object)(_p));
 }else {
 //BA.debugLineNum = 771;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (hor,ValoresCom";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_hor),BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[(int) (_h.Circuito)].Nombre),(android.graphics.Bitmap)(mostCurrent._valorescomunes._iconocircuito(mostCurrent.activityBA,(int) (_h.Circuito),_h.Salida).getObject()),(Object)(_p));
 };
 //BA.debugLineNum = 773;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 781;BA.debugLine="If H.Circuito>29 And H.Circuito <40 Then";
if (_h.Circuito>29 && _h.Circuito<40) { 
 //BA.debugLineNum = 782;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (hor,ValoresComu";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_hor),BA.ObjectToCharSequence(mostCurrent._valorescomunes._scenes[(int) (_h.Circuito-30)].Nombre),(android.graphics.Bitmap)(mostCurrent._valorescomunes._home(mostCurrent.activityBA).getObject()),(Object)(_p));
 };
 //BA.debugLineNum = 787;BA.debugLine="If H.Circuito>39 And H.Circuito <50 Then";
if (_h.Circuito>39 && _h.Circuito<50) { 
 //BA.debugLineNum = 788;BA.debugLine="If H.Salida=1 Then";
if (_h.Salida==1) { 
 //BA.debugLineNum = 789;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (hor,ValoresCom";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_hor),BA.ObjectToCharSequence(mostCurrent._valorescomunes._condicionados[(int) (_h.Circuito-40)].Nombre),(android.graphics.Bitmap)(mostCurrent._valorescomunes._checkon(mostCurrent.activityBA).getObject()),(Object)(_p));
 }else {
 //BA.debugLineNum = 792;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (hor,ValoresCom";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_hor),BA.ObjectToCharSequence(mostCurrent._valorescomunes._condicionados[(int) (_h.Circuito-40)].Nombre),(android.graphics.Bitmap)(mostCurrent._valorescomunes._checkoff(mostCurrent.activityBA).getObject()),(Object)(_p));
 };
 //BA.debugLineNum = 794;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 800;BA.debugLine="If H.Circuito>49 And H.Circuito <60 Then";
if (_h.Circuito>49 && _h.Circuito<60) { 
 //BA.debugLineNum = 801;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (hor,ValoresComu";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_hor),BA.ObjectToCharSequence(mostCurrent._valorescomunes._functions[(int) (_h.Circuito-50)].Nombre),(android.graphics.Bitmap)(mostCurrent._valorescomunes._funciones(mostCurrent.activityBA).getObject()),(Object)(_p));
 };
 //BA.debugLineNum = 803;BA.debugLine="End Sub";
return "";
}
public static String  _anicargando_animationend() throws Exception{
 //BA.debugLineNum = 193;BA.debugLine="Sub AniCargando_AnimationEnd";
 //BA.debugLineNum = 195;BA.debugLine="If PaqueteEnviado = True Then";
if (_paqueteenviado==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 196;BA.debugLine="AniCargando.Start (ImgCargando)";
mostCurrent._anicargando.Start((android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 197;BA.debugLine="SendTrama(TramaEnviada)";
_sendtrama(_tramaenviada);
 };
 //BA.debugLineNum = 199;BA.debugLine="End Sub";
return "";
}
public static String  _barra_on(byte _pos) throws Exception{
 //BA.debugLineNum = 859;BA.debugLine="Sub Barra_On(Pos As Byte)";
 //BA.debugLineNum = 860;BA.debugLine="ListView1.Enabled =False";
mostCurrent._listview1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 861;BA.debugLine="EnableMenu =False";
_enablemenu = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 862;BA.debugLine="CmdSeleccion.Enabled =False";
mostCurrent._cmdseleccion.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 863;BA.debugLine="Barra.Value = 50";
mostCurrent._barra._setvalue((int) (50));
 //BA.debugLineNum = 864;BA.debugLine="Activity.AddView (PanBarra,0,0, Activity.Width ,A";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._panbarra.getObject()),(int) (0),(int) (0),mostCurrent._activity.getWidth(),mostCurrent._activity.getHeight());
 //BA.debugLineNum = 866;BA.debugLine="End Sub";
return "";
}
public static String  _cmdbarcancel_click() throws Exception{
 //BA.debugLineNum = 893;BA.debugLine="Sub CmdBarCancel_Click";
 //BA.debugLineNum = 894;BA.debugLine="ListView1.Enabled =True";
mostCurrent._listview1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 895;BA.debugLine="EnableMenu =True";
_enablemenu = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 896;BA.debugLine="CmdSeleccion.Enabled =True";
mostCurrent._cmdseleccion.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 898;BA.debugLine="PanBarra.RemoveView";
mostCurrent._panbarra.RemoveView();
 //BA.debugLineNum = 899;BA.debugLine="End Sub";
return "";
}
public static String  _cmdbarok_click() throws Exception{
 //BA.debugLineNum = 884;BA.debugLine="Sub CmdBarOk_Click";
 //BA.debugLineNum = 885;BA.debugLine="ListView1.Enabled =True";
mostCurrent._listview1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 886;BA.debugLine="EnableMenu =True";
_enablemenu = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 887;BA.debugLine="CmdSeleccion.Enabled =True";
mostCurrent._cmdseleccion.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 888;BA.debugLine="hr.Salida =Barra.currentValue";
mostCurrent._hr.Salida = mostCurrent._barra._currentvalue;
 //BA.debugLineNum = 889;BA.debugLine="PanBarra.RemoveView";
mostCurrent._panbarra.RemoveView();
 //BA.debugLineNum = 891;BA.debugLine="FinalizaNewHorario( hr)";
_finalizanewhorario(mostCurrent._hr);
 //BA.debugLineNum = 892;BA.debugLine="End Sub";
return "";
}
public static String  _cmdseleccion_click() throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
anywheresoftware.b4a.objects.collections.List _lstval = null;
int _pos = 0;
int _i = 0;
 //BA.debugLineNum = 248;BA.debugLine="Sub CmdSeleccion_Click";
 //BA.debugLineNum = 249;BA.debugLine="Dim lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 250;BA.debugLine="Dim lstVal As List";
_lstval = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 251;BA.debugLine="lstVal.Initialize";
_lstval.Initialize();
 //BA.debugLineNum = 252;BA.debugLine="lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 253;BA.debugLine="Dim Pos As Int";
_pos = 0;
 //BA.debugLineNum = 261;BA.debugLine="lst.Add (ValoresComunes.GetLanStringDefault  (\"re";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"reg89","ALL CIRCUITS")));
 //BA.debugLineNum = 262;BA.debugLine="lst.Add (ValoresComunes.GetLanStringDefault (\"reg";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"reg151","Circuit")));
 //BA.debugLineNum = 263;BA.debugLine="lst.Add (ValoresComunes.GetLanStringDefault (\"reg";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"reg152","Scene")));
 //BA.debugLineNum = 264;BA.debugLine="lst.Add (ValoresComunes.GetLanStringDefault (\"reg";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"reg131","Conditioned")));
 //BA.debugLineNum = 265;BA.debugLine="lst.Add (ValoresComunes.LanString .GetDefault(\"re";
_lst.Add(mostCurrent._valorescomunes._lanstring.GetDefault((Object)("reg186"),(Object)("Functions")));
 //BA.debugLineNum = 266;BA.debugLine="Pos =InputList(lst,ValoresComunes.GetLanString (\"";
_pos = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),_pos,mostCurrent.activityBA);
 //BA.debugLineNum = 268;BA.debugLine="If Pos <0 Then Return";
if (_pos<0) { 
if (true) return "";};
 //BA.debugLineNum = 270;BA.debugLine="lst.Clear";
_lst.Clear();
 //BA.debugLineNum = 272;BA.debugLine="Select Case Pos";
switch (_pos) {
case 0: {
 //BA.debugLineNum = 274;BA.debugLine="CircuitoMostrado=100";
_circuitomostrado = (byte) (100);
 //BA.debugLineNum = 275;BA.debugLine="CmdSeleccion.Text = ValoresComunes.GetLanString";
mostCurrent._cmdseleccion.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg89")));
 //BA.debugLineNum = 276;BA.debugLine="RefrescaPantalla";
_refrescapantalla();
 //BA.debugLineNum = 277;BA.debugLine="Return";
if (true) return "";
 break; }
case 1: {
 //BA.debugLineNum = 279;BA.debugLine="For I =0 To 29'circuitos";
{
final int step21 = 1;
final int limit21 = (int) (29);
_i = (int) (0) ;
for (;(step21 > 0 && _i <= limit21) || (step21 < 0 && _i >= limit21) ;_i = ((int)(0 + _i + step21))  ) {
 //BA.debugLineNum = 280;BA.debugLine="If ValoresComunes.Circuitos (I).Nombre <> \"\" A";
if ((mostCurrent._valorescomunes._circuitos[_i].Nombre).equals("") == false && mostCurrent._valorescomunes._circuitos[_i].Rango>0) { 
 //BA.debugLineNum = 281;BA.debugLine="lst.Add (ValoresComunes.Circuitos (I).Nombre)";
_lst.Add((Object)(mostCurrent._valorescomunes._circuitos[_i].Nombre));
 //BA.debugLineNum = 282;BA.debugLine="lstVal.Add (I)";
_lstval.Add((Object)(_i));
 };
 }
};
 break; }
case 2: {
 //BA.debugLineNum = 286;BA.debugLine="For I =0 To 9";
{
final int step28 = 1;
final int limit28 = (int) (9);
_i = (int) (0) ;
for (;(step28 > 0 && _i <= limit28) || (step28 < 0 && _i >= limit28) ;_i = ((int)(0 + _i + step28))  ) {
 //BA.debugLineNum = 287;BA.debugLine="If ValoresComunes.Scenes  (I).Nombre <> \"\" The";
if ((mostCurrent._valorescomunes._scenes[_i].Nombre).equals("") == false) { 
 //BA.debugLineNum = 288;BA.debugLine="lst.Add (ValoresComunes.Scenes  (I).Nombre)";
_lst.Add((Object)(mostCurrent._valorescomunes._scenes[_i].Nombre));
 //BA.debugLineNum = 289;BA.debugLine="lstVal.Add (I+30)";
_lstval.Add((Object)(_i+30));
 };
 }
};
 break; }
case 3: {
 //BA.debugLineNum = 293;BA.debugLine="For I =0 To 9";
{
final int step35 = 1;
final int limit35 = (int) (9);
_i = (int) (0) ;
for (;(step35 > 0 && _i <= limit35) || (step35 < 0 && _i >= limit35) ;_i = ((int)(0 + _i + step35))  ) {
 //BA.debugLineNum = 294;BA.debugLine="If ValoresComunes.Condicionados   (I).Nombre <";
if ((mostCurrent._valorescomunes._condicionados[_i].Nombre).equals("") == false) { 
 //BA.debugLineNum = 295;BA.debugLine="lst.Add (ValoresComunes.Condicionados  (I).No";
_lst.Add((Object)(mostCurrent._valorescomunes._condicionados[_i].Nombre));
 //BA.debugLineNum = 296;BA.debugLine="lstVal.Add (I+40)";
_lstval.Add((Object)(_i+40));
 };
 }
};
 break; }
case 4: {
 //BA.debugLineNum = 300;BA.debugLine="For I =0 To 9";
{
final int step42 = 1;
final int limit42 = (int) (9);
_i = (int) (0) ;
for (;(step42 > 0 && _i <= limit42) || (step42 < 0 && _i >= limit42) ;_i = ((int)(0 + _i + step42))  ) {
 //BA.debugLineNum = 301;BA.debugLine="If ValoresComunes.Functions(I).Nombre <> \"\" Th";
if ((mostCurrent._valorescomunes._functions[_i].Nombre).equals("") == false) { 
 //BA.debugLineNum = 302;BA.debugLine="lst.Add (ValoresComunes.Functions   (I).Nombr";
_lst.Add((Object)(mostCurrent._valorescomunes._functions[_i].Nombre));
 //BA.debugLineNum = 303;BA.debugLine="lstVal.Add (I+50)";
_lstval.Add((Object)(_i+50));
 };
 }
};
 break; }
}
;
 //BA.debugLineNum = 311;BA.debugLine="Pos =InputList(lst,ValoresComunes.GetLanString (\"";
_pos = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),(int) (0),mostCurrent.activityBA);
 //BA.debugLineNum = 313;BA.debugLine="If Pos <0 Then Return";
if (_pos<0) { 
if (true) return "";};
 //BA.debugLineNum = 314;BA.debugLine="CircuitoMostrado= lstVal.Get (Pos)";
_circuitomostrado = (byte)(BA.ObjectToNumber(_lstval.Get(_pos)));
 //BA.debugLineNum = 315;BA.debugLine="CmdSeleccion.Text = lst.Get (Pos)";
mostCurrent._cmdseleccion.setText(BA.ObjectToCharSequence(_lst.Get(_pos)));
 //BA.debugLineNum = 317;BA.debugLine="RefrescaPantalla";
_refrescapantalla();
 //BA.debugLineNum = 318;BA.debugLine="End Sub";
return "";
}
public static String  _finalizanewhorario(arduino.automatizacion.excontrol.PRO.acthorarios2._horario _h) throws Exception{
anywheresoftware.b4a.agraham.dialogs.InputDialog.TimeDialog _dialog = null;
int _resp = 0;
int _p = 0;
arduino.automatizacion.excontrol.PRO.acthorarios2._horario _hcmp = null;
 //BA.debugLineNum = 620;BA.debugLine="Sub FinalizaNewHorario( H As Horario )";
 //BA.debugLineNum = 621;BA.debugLine="Dim Dialog As TimeDialog";
_dialog = new anywheresoftware.b4a.agraham.dialogs.InputDialog.TimeDialog();
 //BA.debugLineNum = 622;BA.debugLine="Dim Resp As Int";
_resp = 0;
 //BA.debugLineNum = 623;BA.debugLine="Dialog.Is24Hours =True";
_dialog.setIs24Hours(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 627;BA.debugLine="Dialog.Hour =  DateTime.GetHour(DateTime.Now)";
_dialog.setHour(anywheresoftware.b4a.keywords.Common.DateTime.GetHour(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 628;BA.debugLine="Dialog.Minute =  DateTime.GetMinute ( DateTime.No";
_dialog.setMinute(anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 630;BA.debugLine="Resp= Dialog.Show (ValoresComunes.GetLanString (\"";
_resp = _dialog.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg79"),"",mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Ok"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 631;BA.debugLine="If Resp = DialogResponse.POSITIVE Then";
if (_resp==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 632;BA.debugLine="H.Hora = Dialog.Hour";
_h.Hora = (byte) (_dialog.getHour());
 //BA.debugLineNum = 633;BA.debugLine="H.Minuto = Dialog.Minute";
_h.Minuto = (byte) (_dialog.getMinute());
 //BA.debugLineNum = 635;BA.debugLine="Dim p As Int";
_p = 0;
 //BA.debugLineNum = 636;BA.debugLine="If Timetable.Size =0 Then";
if (mostCurrent._timetable.getSize()==0) { 
 //BA.debugLineNum = 637;BA.debugLine="Timetable.Add (H)";
mostCurrent._timetable.Add((Object)(_h));
 }else {
 //BA.debugLineNum = 639;BA.debugLine="For p=0 To Timetable.Size -1";
{
final int step14 = 1;
final int limit14 = (int) (mostCurrent._timetable.getSize()-1);
_p = (int) (0) ;
for (;(step14 > 0 && _p <= limit14) || (step14 < 0 && _p >= limit14) ;_p = ((int)(0 + _p + step14))  ) {
 //BA.debugLineNum = 640;BA.debugLine="Dim Hcmp As Horario";
_hcmp = new arduino.automatizacion.excontrol.PRO.acthorarios2._horario();
 //BA.debugLineNum = 641;BA.debugLine="Hcmp = Timetable.Get (p)";
_hcmp = (arduino.automatizacion.excontrol.PRO.acthorarios2._horario)(mostCurrent._timetable.Get(_p));
 //BA.debugLineNum = 642;BA.debugLine="If (Hcmp.Hora > H.Hora  )    Then";
if ((_hcmp.Hora>_h.Hora)) { 
 //BA.debugLineNum = 644;BA.debugLine="Timetable.InsertAt   (p,H)";
mostCurrent._timetable.InsertAt(_p,(Object)(_h));
 //BA.debugLineNum = 645;BA.debugLine="RefrescaPantalla";
_refrescapantalla();
 //BA.debugLineNum = 646;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 648;BA.debugLine="If (Hcmp.Hora = H.Hora  And Hcmp.Minuto   >= H";
if ((_hcmp.Hora==_h.Hora && _hcmp.Minuto>=_h.Minuto)) { 
 //BA.debugLineNum = 650;BA.debugLine="Timetable.InsertAt   (p,H)";
mostCurrent._timetable.InsertAt(_p,(Object)(_h));
 //BA.debugLineNum = 651;BA.debugLine="RefrescaPantalla";
_refrescapantalla();
 //BA.debugLineNum = 652;BA.debugLine="Return";
if (true) return "";
 };
 }
};
 //BA.debugLineNum = 655;BA.debugLine="Timetable.Add (H )";
mostCurrent._timetable.Add((Object)(_h));
 };
 //BA.debugLineNum = 657;BA.debugLine="RefrescaPantalla";
_refrescapantalla();
 };
 //BA.debugLineNum = 659;BA.debugLine="End Sub";
return "";
}
public static int  _getrgbcolor() throws Exception{
int _res = 0;
int _v = 0;
int _b = 0;
 //BA.debugLineNum = 201;BA.debugLine="Sub GetRGBcolor() As Int";
 //BA.debugLineNum = 202;BA.debugLine="Dim res As Int";
_res = 0;
 //BA.debugLineNum = 203;BA.debugLine="res=DialogResponse.CANCEL";
_res = anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL;
 //BA.debugLineNum = 205;BA.debugLine="Do While res=DialogResponse.CANCEL";
while (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL) {
 //BA.debugLineNum = 206;BA.debugLine="dlg1.RGB=8388736";
mostCurrent._dlg1.setRGB((int) (8388736));
 //BA.debugLineNum = 207;BA.debugLine="res = dlg1.Show(ValoresComunes.GetLanString (\"re";
_res = mostCurrent._dlg1.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg97"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Ok"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg94"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 208;BA.debugLine="If res=DialogResponse.POSITIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 209;BA.debugLine="Dim v As Int";
_v = 0;
 //BA.debugLineNum = 210;BA.debugLine="v= dlg1.RGB";
_v = mostCurrent._dlg1.getRGB();
 //BA.debugLineNum = 211;BA.debugLine="Dim b As Int";
_b = 0;
 //BA.debugLineNum = 212;BA.debugLine="For b=0 To 14";
{
final int step10 = 1;
final int limit10 = (int) (14);
_b = (int) (0) ;
for (;(step10 > 0 && _b <= limit10) || (step10 < 0 && _b >= limit10) ;_b = ((int)(0 + _b + step10))  ) {
 //BA.debugLineNum = 213;BA.debugLine="If v = dlg1.GetPaletteAt(b) Then	Return b+1";
if (_v==mostCurrent._dlg1.GetPaletteAt(_b)) { 
if (true) return (int) (_b+1);};
 }
};
 }else if(_res==anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL) { 
 //BA.debugLineNum = 217;BA.debugLine="dlg2.RGB=14423100";
mostCurrent._dlg2.setRGB((int) (14423100));
 //BA.debugLineNum = 218;BA.debugLine="res = dlg2.Show(ValoresComunes.GetLanString (\"r";
_res = mostCurrent._dlg2.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg97"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Ok"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg94"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 220;BA.debugLine="If res=DialogResponse.POSITIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 221;BA.debugLine="Dim v As Int";
_v = 0;
 //BA.debugLineNum = 222;BA.debugLine="v= dlg2.RGB";
_v = mostCurrent._dlg2.getRGB();
 //BA.debugLineNum = 223;BA.debugLine="Dim b As Int";
_b = 0;
 //BA.debugLineNum = 224;BA.debugLine="For b=0 To 14";
{
final int step20 = 1;
final int limit20 = (int) (14);
_b = (int) (0) ;
for (;(step20 > 0 && _b <= limit20) || (step20 < 0 && _b >= limit20) ;_b = ((int)(0 + _b + step20))  ) {
 //BA.debugLineNum = 225;BA.debugLine="If v = dlg2.GetPaletteAt(b) Then	Return b+16";
if (_v==mostCurrent._dlg2.GetPaletteAt(_b)) { 
if (true) return (int) (_b+16);};
 }
};
 }else if(_res!=anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL) { 
 //BA.debugLineNum = 229;BA.debugLine="Return -1";
if (true) return (int) (-1);
 };
 }else {
 //BA.debugLineNum = 234;BA.debugLine="Return -1";
if (true) return (int) (-1);
 };
 }
;
 //BA.debugLineNum = 237;BA.debugLine="Return -1";
if (true) return (int) (-1);
 //BA.debugLineNum = 238;BA.debugLine="End Sub";
return 0;
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Dim Timetable As List";
mostCurrent._timetable = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 17;BA.debugLine="Dim CircuitoMostrado As Byte";
_circuitomostrado = (byte)0;
 //BA.debugLineNum = 21;BA.debugLine="Dim CmdSeleccion As Button";
mostCurrent._cmdseleccion = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Dim ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Dim TramaEnviada() As Byte";
_tramaenviada = new byte[(int) (0)];
;
 //BA.debugLineNum = 26;BA.debugLine="Dim ImgCargando As ImageView";
mostCurrent._imgcargando = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Dim AniCargando As  Animation";
mostCurrent._anicargando = new anywheresoftware.b4a.objects.AnimationWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Dim PaqueteEnviado As Boolean";
_paqueteenviado = false;
 //BA.debugLineNum = 31;BA.debugLine="Dim dlg1 As ColorPickerDialog";
mostCurrent._dlg1 = new anywheresoftware.b4a.agraham.dialogs.InputDialog.ColorPickerDialog();
 //BA.debugLineNum = 32;BA.debugLine="Dim dlg2 As ColorPickerDialog";
mostCurrent._dlg2 = new anywheresoftware.b4a.agraham.dialogs.InputDialog.ColorPickerDialog();
 //BA.debugLineNum = 34;BA.debugLine="Dim listCir As ListView";
mostCurrent._listcir = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Dim ListCon As ListView";
mostCurrent._listcon = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Dim listEscenas As ListView";
mostCurrent._listescenas = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Dim listFunciones As ListView";
mostCurrent._listfunciones = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Dim pnl As Panel";
mostCurrent._pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Dim TabConfig As TabHost";
mostCurrent._tabconfig = new anywheresoftware.b4a.objects.TabHostWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Dim CmdBarOk As Button";
mostCurrent._cmdbarok = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Dim CmdBarCancel As Button";
mostCurrent._cmdbarcancel = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Dim PanBarra As Panel";
mostCurrent._panbarra = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Dim Barra As mbVSeekBar";
mostCurrent._barra = new arduino.automatizacion.excontrol.PRO.mbvseekbar();
 //BA.debugLineNum = 48;BA.debugLine="Dim hr As Horario";
mostCurrent._hr = new arduino.automatizacion.excontrol.PRO.acthorarios2._horario();
 //BA.debugLineNum = 50;BA.debugLine="Dim EnableMenu  As Boolean";
_enablemenu = false;
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return "";
}
public static String  _inibardialog() throws Exception{
 //BA.debugLineNum = 867;BA.debugLine="Sub IniBarDialog(  )";
 //BA.debugLineNum = 868;BA.debugLine="CmdBarOk.Initialize(\"CmdBarOk\")";
mostCurrent._cmdbarok.Initialize(mostCurrent.activityBA,"CmdBarOk");
 //BA.debugLineNum = 869;BA.debugLine="CmdBarCancel.Initialize (\"CmdBarCancel\")";
mostCurrent._cmdbarcancel.Initialize(mostCurrent.activityBA,"CmdBarCancel");
 //BA.debugLineNum = 872;BA.debugLine="CmdBarOk.Text =ValoresComunes.GetLanString (\"reg8";
mostCurrent._cmdbarok.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83")));
 //BA.debugLineNum = 873;BA.debugLine="CmdBarCancel.Text =ValoresComunes.GetLanString (\"";
mostCurrent._cmdbarcancel.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel")));
 //BA.debugLineNum = 874;BA.debugLine="PanBarra.Initialize(\"PanBarra\")";
mostCurrent._panbarra.Initialize(mostCurrent.activityBA,"PanBarra");
 //BA.debugLineNum = 875;BA.debugLine="PanBarra.Color =  Colors.Black";
mostCurrent._panbarra.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 876;BA.debugLine="Barra.Initialize( PanBarra,  Me,\"bar_Click\",  50%";
mostCurrent._barra._initialize(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ActivityWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ActivityWrapper(), (anywheresoftware.b4a.BALayout)(mostCurrent._panbarra.getObject())),actalarmas2.getObject(),"bar_Click",(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (36))),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (72)),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (75),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (33)),anywheresoftware.b4a.keywords.Common.Colors.DarkGray,anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0x00),(int) (0x99),(int) (0xcc)),anywheresoftware.b4a.keywords.Common.True,(int) (70),(int) (100),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 877;BA.debugLine="Barra.CustomizeText(Colors.RGB(0x33,0xb5,0xe5),22";
mostCurrent._barra._customizetext(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0x33),(int) (0xb5),(int) (0xe5)),(int) (22),(anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD)));
 //BA.debugLineNum = 878;BA.debugLine="Barra.stepValue =1";
mostCurrent._barra._stepvalue = (int) (1);
 //BA.debugLineNum = 880;BA.debugLine="PanBarra.AddView (CmdBarOk, 5%x,85%y,44%x,14%y)";
mostCurrent._panbarra.AddView((android.view.View)(mostCurrent._cmdbarok.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (44),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (14),mostCurrent.activityBA));
 //BA.debugLineNum = 881;BA.debugLine="PanBarra.AddView (CmdBarCancel,  51%x ,85%y, 44%x";
mostCurrent._panbarra.AddView((android.view.View)(mostCurrent._cmdbarcancel.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (51),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (44),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (14),mostCurrent.activityBA));
 //BA.debugLineNum = 883;BA.debugLine="End Sub";
return "";
}
public static String  _listnewhor_itemclick(int _position,Object _value) throws Exception{
arduino.automatizacion.excontrol.PRO.acthorarios2._horario _h = null;
int _pos = 0;
int _newval = 0;
anywheresoftware.b4a.objects.collections.List _lst = null;
anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog _dialogo = null;
int _result = 0;
 //BA.debugLineNum = 320;BA.debugLine="Sub listNewHor_ItemClick (Position As Int, Value A";
 //BA.debugLineNum = 325;BA.debugLine="pnl.RemoveView";
mostCurrent._pnl.RemoveView();
 //BA.debugLineNum = 326;BA.debugLine="EnableMenu=True";
_enablemenu = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 328;BA.debugLine="Dim H As Horario";
_h = new arduino.automatizacion.excontrol.PRO.acthorarios2._horario();
 //BA.debugLineNum = 330;BA.debugLine="Dim Pos As Int";
_pos = 0;
 //BA.debugLineNum = 331;BA.debugLine="If CircuitoMostrado < 60 Then  Pos= CircuitoMostr";
if (_circuitomostrado<60) { 
_pos = (int) (_circuitomostrado);};
 //BA.debugLineNum = 333;BA.debugLine="If Pos>=0 Then";
if (_pos>=0) { 
 //BA.debugLineNum = 334;BA.debugLine="H.Circuito = Value";
_h.Circuito = (byte)(BA.ObjectToNumber(_value));
 }else {
 //BA.debugLineNum = 336;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 339;BA.debugLine="Dim NewVal As Int";
_newval = 0;
 //BA.debugLineNum = 340;BA.debugLine="NewVal=-1";
_newval = (int) (-1);
 //BA.debugLineNum = 342;BA.debugLine="If H.Circuito <30 Then";
if (_h.Circuito<30) { 
 //BA.debugLineNum = 343;BA.debugLine="Select  ValoresComunes.Circuitos (H.Circuito).Ra";
switch (BA.switchObjectToInt(mostCurrent._valorescomunes._circuitos[(int) (_h.Circuito)].Rango,(int) (1),(int) (44),(int) (2),(int) (45),(int) (1),(int) (3),(int) (7),(int) (8),(int) (13),(int) (19),(int) (24),(int) (39),(int) (15),(int) (25),(int) (43),(int) (51),(int) (57),(int) (58),(int) (4),(int) (5),(int) (53),(int) (54),(int) (55),(int) (56),(int) (29),(int) (30),(int) (31),(int) (14),(int) (52),(int) (34),(int) (35),(int) (36))) {
case 0: {
 //BA.debugLineNum = 345;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 346;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 347;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg71\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg71")));
 //BA.debugLineNum = 348;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg70\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg70")));
 //BA.debugLineNum = 349;BA.debugLine="NewVal =InputList(Lst,ValoresComunes.GetLanStri";
_newval = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),(int) (0),mostCurrent.activityBA);
 break; }
case 1: {
 //BA.debugLineNum = 351;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 352;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 353;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg71\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg71")));
 //BA.debugLineNum = 354;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg72\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg72")));
 //BA.debugLineNum = 355;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg73\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg73")));
 //BA.debugLineNum = 356;BA.debugLine="NewVal =InputList(Lst,ValoresComunes.GetLanStri";
_newval = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),(int) (0),mostCurrent.activityBA);
 break; }
case 2: 
case 3: {
 //BA.debugLineNum = 360;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 361;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 362;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg71\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg71")));
 //BA.debugLineNum = 363;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg72\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg72")));
 //BA.debugLineNum = 364;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg73\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg73")));
 //BA.debugLineNum = 365;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg74\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg74")));
 //BA.debugLineNum = 367;BA.debugLine="NewVal =InputList(Lst,ValoresComunes.GetLanStri";
_newval = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),(int) (0),mostCurrent.activityBA);
 break; }
case 4: 
case 5: 
case 6: 
case 7: 
case 8: 
case 9: 
case 10: 
case 11: 
case 12: 
case 13: 
case 14: 
case 15: 
case 16: 
case 17: {
 //BA.debugLineNum = 371;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 372;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 373;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg71\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg71")));
 //BA.debugLineNum = 374;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg70\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg70")));
 //BA.debugLineNum = 375;BA.debugLine="NewVal =InputList(Lst,ValoresComunes.GetLanStri";
_newval = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),(int) (0),mostCurrent.activityBA);
 break; }
case 18: {
 //BA.debugLineNum = 379;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 380;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 381;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg71\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg71")));
 //BA.debugLineNum = 382;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg95\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg95")));
 //BA.debugLineNum = 383;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg96\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg96")));
 //BA.debugLineNum = 385;BA.debugLine="NewVal =InputList(Lst,ValoresComunes.GetLanStri";
_newval = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),(int) (0),mostCurrent.activityBA);
 //BA.debugLineNum = 387;BA.debugLine="If NewVal>0 Then";
if (_newval>0) { 
 //BA.debugLineNum = 388;BA.debugLine="If NewVal=1 Then";
if (_newval==1) { 
 //BA.debugLineNum = 389;BA.debugLine="NewVal=199";
_newval = (int) (199);
 }else {
 //BA.debugLineNum = 391;BA.debugLine="NewVal=GetRGBcolor";
_newval = _getrgbcolor();
 };
 };
 //BA.debugLineNum = 395;BA.debugLine="If NewVal<0 Then Return";
if (_newval<0) { 
if (true) return "";};
 break; }
case 19: {
 //BA.debugLineNum = 398;BA.debugLine="hr=H";
mostCurrent._hr = _h;
 //BA.debugLineNum = 399;BA.debugLine="Barra_On(Value)";
_barra_on((byte)(BA.ObjectToNumber(_value)));
 //BA.debugLineNum = 400;BA.debugLine="Return";
if (true) return "";
 break; }
case 20: {
 //BA.debugLineNum = 404;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 405;BA.debugLine="Dialogo.Digits =3";
_dialogo.setDigits((int) (3));
 //BA.debugLineNum = 407;BA.debugLine="Dialogo.Number = 0";
_dialogo.setNumber((int) (0));
 //BA.debugLineNum = 409;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 410;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 412;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 413;BA.debugLine="If Dialogo.Number< 101 Then";
if (_dialogo.getNumber()<101) { 
 //BA.debugLineNum = 414;BA.debugLine="NewVal =Dialogo.Number";
_newval = _dialogo.getNumber();
 }else {
 //BA.debugLineNum = 416;BA.debugLine="Msgbox(\"Max 100\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max 100"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 417;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 420;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 21: {
 //BA.debugLineNum = 424;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 425;BA.debugLine="Dialogo.Digits =3";
_dialogo.setDigits((int) (3));
 //BA.debugLineNum = 427;BA.debugLine="Dialogo.Number = 0";
_dialogo.setNumber((int) (0));
 //BA.debugLineNum = 429;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 430;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 432;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 433;BA.debugLine="If Dialogo.Number< 201 Then";
if (_dialogo.getNumber()<201) { 
 //BA.debugLineNum = 434;BA.debugLine="NewVal =Dialogo.Number";
_newval = _dialogo.getNumber();
 }else {
 //BA.debugLineNum = 436;BA.debugLine="Msgbox(\"Max 200\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max 200"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 437;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 440;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 22: {
 //BA.debugLineNum = 443;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 444;BA.debugLine="Dialogo.Digits =4";
_dialogo.setDigits((int) (4));
 //BA.debugLineNum = 446;BA.debugLine="Dialogo.Number = 0";
_dialogo.setNumber((int) (0));
 //BA.debugLineNum = 448;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 449;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 451;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 452;BA.debugLine="If Dialogo.Number< 2001 Then";
if (_dialogo.getNumber()<2001) { 
 //BA.debugLineNum = 453;BA.debugLine="NewVal =Dialogo.Number/10";
_newval = (int) (_dialogo.getNumber()/(double)10);
 }else {
 //BA.debugLineNum = 455;BA.debugLine="Msgbox(\"Max 2000\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max 2000"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 456;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 459;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 23: {
 //BA.debugLineNum = 462;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 463;BA.debugLine="Dialogo.Digits =5";
_dialogo.setDigits((int) (5));
 //BA.debugLineNum = 465;BA.debugLine="Dialogo.Number = 0";
_dialogo.setNumber((int) (0));
 //BA.debugLineNum = 467;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 468;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 470;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 471;BA.debugLine="If Dialogo.Number< 20001 Then";
if (_dialogo.getNumber()<20001) { 
 //BA.debugLineNum = 472;BA.debugLine="NewVal =Dialogo.Number/100";
_newval = (int) (_dialogo.getNumber()/(double)100);
 }else {
 //BA.debugLineNum = 474;BA.debugLine="Msgbox(\"Max 20000\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max 20000"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 475;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 478;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 24: {
 //BA.debugLineNum = 484;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 485;BA.debugLine="Dialogo.Digits =3";
_dialogo.setDigits((int) (3));
 //BA.debugLineNum = 486;BA.debugLine="Dialogo.Number = 20";
_dialogo.setNumber((int) (20));
 //BA.debugLineNum = 488;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 489;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 491;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 493;BA.debugLine="If Dialogo.Number< 241 Then";
if (_dialogo.getNumber()<241) { 
 //BA.debugLineNum = 494;BA.debugLine="NewVal =Dialogo.Number";
_newval = _dialogo.getNumber();
 }else {
 //BA.debugLineNum = 496;BA.debugLine="Msgbox(\"Max 240\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max 240"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 497;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 502;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 25: {
 //BA.debugLineNum = 508;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 509;BA.debugLine="Dialogo.Digits =3";
_dialogo.setDigits((int) (3));
 //BA.debugLineNum = 510;BA.debugLine="Dialogo.Decimal =1";
_dialogo.setDecimal((int) (1));
 //BA.debugLineNum = 512;BA.debugLine="Dialogo.Number = 220";
_dialogo.setNumber((int) (220));
 //BA.debugLineNum = 514;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 515;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStri";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 517;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 519;BA.debugLine="If Dialogo.Number< 321 AND Dialogo.number> 149";
if (_dialogo.getNumber()<321 && _dialogo.getNumber()>149) { 
 //BA.debugLineNum = 520;BA.debugLine="NewVal =(Dialogo.Number-150)";
_newval = (int) ((_dialogo.getNumber()-150));
 }else {
 //BA.debugLineNum = 522;BA.debugLine="Msgbox(\"Only 15º to 32º\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Only 15º to 32º"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 523;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 527;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 26: {
 //BA.debugLineNum = 530;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 531;BA.debugLine="Dialogo.Digits =4";
_dialogo.setDigits((int) (4));
 //BA.debugLineNum = 533;BA.debugLine="Dialogo.Number = -1";
_dialogo.setNumber((int) (-1));
 //BA.debugLineNum = 534;BA.debugLine="Dialogo.ShowSign = True";
_dialogo.setShowSign(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 535;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 536;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStri";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 538;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 539;BA.debugLine="If Dialogo.Number < 1  And Dialogo.Number  > -2";
if (_dialogo.getNumber()<1 && _dialogo.getNumber()>-241) { 
 //BA.debugLineNum = 540;BA.debugLine="NewVal =Dialogo.Number * -1";
_newval = (int) (_dialogo.getNumber()*-1);
 }else {
 //BA.debugLineNum = 542;BA.debugLine="Msgbox(\"Max -240\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max -240"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 543;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 546;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 27: 
case 28: {
 //BA.debugLineNum = 549;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 550;BA.debugLine="Dialogo.Digits =3";
_dialogo.setDigits((int) (3));
 //BA.debugLineNum = 553;BA.debugLine="Dialogo.Number = 0";
_dialogo.setNumber((int) (0));
 //BA.debugLineNum = 555;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 556;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg84"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 558;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 559;BA.debugLine="If Dialogo.Number < 241 And Dialogo.Number >0";
if (_dialogo.getNumber()<241 && _dialogo.getNumber()>0) { 
 //BA.debugLineNum = 560;BA.debugLine="NewVal =Dialogo.Number";
_newval = _dialogo.getNumber();
 }else {
 //BA.debugLineNum = 562;BA.debugLine="Msgbox(ValoresComunes.GetLanString (\"reg85\"),";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg85")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg86")),mostCurrent.activityBA);
 //BA.debugLineNum = 563;BA.debugLine="Return";
if (true) return "";
 };
 };
 break; }
case 29: {
 //BA.debugLineNum = 568;BA.debugLine="hr=H";
mostCurrent._hr = _h;
 //BA.debugLineNum = 569;BA.debugLine="Barra_On(Value)";
_barra_on((byte)(BA.ObjectToNumber(_value)));
 //BA.debugLineNum = 570;BA.debugLine="Return";
if (true) return "";
 break; }
case 30: 
case 31: {
 //BA.debugLineNum = 574;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 575;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 576;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg80\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg80")));
 //BA.debugLineNum = 577;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg82\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg82")));
 //BA.debugLineNum = 578;BA.debugLine="NewVal =InputList(Lst,ValoresComunes.GetLanStri";
_newval = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),(int) (0),mostCurrent.activityBA);
 //BA.debugLineNum = 579;BA.debugLine="If NewVal = 1 Then";
if (_newval==1) { 
 //BA.debugLineNum = 580;BA.debugLine="NewVal=100";
_newval = (int) (100);
 }else {
 //BA.debugLineNum = 582;BA.debugLine="NewVal=0";
_newval = (int) (0);
 };
 break; }
}
;
 }else {
 //BA.debugLineNum = 587;BA.debugLine="Select  H.Circuito";
switch (BA.switchObjectToInt(_h.Circuito,(byte) (30),(byte) (31),(byte) (32),(byte) (33),(byte) (34),(byte) (35),(byte) (36),(byte) (37),(byte) (38),(byte) (39),(byte) (40),(byte) (41),(byte) (42),(byte) (43),(byte) (44),(byte) (45),(byte) (46),(byte) (47),(byte) (48),(byte) (49),(byte) (50),(byte) (51),(byte) (52),(byte) (53),(byte) (54),(byte) (55),(byte) (56),(byte) (57),(byte) (58),(byte) (59))) {
case 0: 
case 1: 
case 2: 
case 3: 
case 4: 
case 5: 
case 6: 
case 7: 
case 8: 
case 9: {
 //BA.debugLineNum = 589;BA.debugLine="NewVal= H.Circuito - 29";
_newval = (int) (_h.Circuito-29);
 break; }
case 10: 
case 11: 
case 12: 
case 13: 
case 14: 
case 15: 
case 16: 
case 17: 
case 18: 
case 19: {
 //BA.debugLineNum = 592;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 593;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 594;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg71\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg71")));
 //BA.debugLineNum = 595;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg70\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg70")));
 //BA.debugLineNum = 596;BA.debugLine="NewVal =InputList(Lst,ValoresComunes.GetLanStri";
_newval = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),(int) (0),mostCurrent.activityBA);
 break; }
case 20: 
case 21: 
case 22: 
case 23: 
case 24: 
case 25: 
case 26: 
case 27: 
case 28: 
case 29: {
 //BA.debugLineNum = 601;BA.debugLine="NewVal= H.Circuito - 49";
_newval = (int) (_h.Circuito-49);
 break; }
default: {
 //BA.debugLineNum = 605;BA.debugLine="Return";
if (true) return "";
 break; }
}
;
 };
 //BA.debugLineNum = 611;BA.debugLine="If NewVal < 0 Then Return";
if (_newval<0) { 
if (true) return "";};
 //BA.debugLineNum = 612;BA.debugLine="H.Salida =NewVal";
_h.Salida = _newval;
 //BA.debugLineNum = 613;BA.debugLine="FinalizaNewHorario( H )";
_finalizanewhorario(_h);
 //BA.debugLineNum = 618;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
int _r = 0;
 //BA.debugLineNum = 240;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 241;BA.debugLine="Dim R As Int";
_r = 0;
 //BA.debugLineNum = 242;BA.debugLine="R=Msgbox2(ValoresComunes.GetLanString (\"reg65\"),V";
_r = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg65")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg66")),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Y"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"N"),"",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 //BA.debugLineNum = 243;BA.debugLine="If R = DialogResponse.POSITIVE Then";
if (_r==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 244;BA.debugLine="Timetable.RemoveAt (Value)";
mostCurrent._timetable.RemoveAt((int)(BA.ObjectToNumber(_value)));
 //BA.debugLineNum = 245;BA.debugLine="RefrescaPantalla";
_refrescapantalla();
 };
 //BA.debugLineNum = 247;BA.debugLine="End Sub";
return "";
}
public static String  _mnunew_click() throws Exception{
 //BA.debugLineNum = 900;BA.debugLine="Sub mnuNew_Click";
 //BA.debugLineNum = 901;BA.debugLine="If EnableMenu Then";
if (_enablemenu) { 
 //BA.debugLineNum = 902;BA.debugLine="If Timetable.Size >11 Then";
if (mostCurrent._timetable.getSize()>11) { 
 //BA.debugLineNum = 903;BA.debugLine="Msgbox(ValoresComunes.GetLanString (\"reg91\"),Va";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg91")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg69")),mostCurrent.activityBA);
 //BA.debugLineNum = 904;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 907;BA.debugLine="Activity.AddView (pnl, 1%x, 0%y,  98%x, 100%y) '";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._pnl.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (1),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (0),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (98),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 908;BA.debugLine="TabConfig.Width = pnl.Width";
mostCurrent._tabconfig.setWidth(mostCurrent._pnl.getWidth());
 //BA.debugLineNum = 909;BA.debugLine="TabConfig.Height =pnl.Height";
mostCurrent._tabconfig.setHeight(mostCurrent._pnl.getHeight());
 //BA.debugLineNum = 912;BA.debugLine="TabConfig.CurrentTab = 0";
mostCurrent._tabconfig.setCurrentTab((int) (0));
 //BA.debugLineNum = 913;BA.debugLine="EnableMenu=False";
_enablemenu = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 915;BA.debugLine="End Sub";
return "";
}
public static String  _mnusave_click() throws Exception{
int _rsp = 0;
int _longtrama = 0;
byte[] _data = null;
int _c = 0;
int _pos = 0;
arduino.automatizacion.excontrol.PRO.acthorarios2._horario _h = null;
 //BA.debugLineNum = 916;BA.debugLine="Sub mnuSave_Click";
 //BA.debugLineNum = 917;BA.debugLine="If EnableMenu Then";
if (_enablemenu) { 
 //BA.debugLineNum = 918;BA.debugLine="Dim Rsp As Int";
_rsp = 0;
 //BA.debugLineNum = 920;BA.debugLine="Rsp = Msgbox2(ValoresComunes.GetLanString (\"reg7";
_rsp = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg76")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg77")),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Y"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"N"),"",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 //BA.debugLineNum = 922;BA.debugLine="If Rsp = DialogResponse.POSITIVE Then";
if (_rsp==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 924;BA.debugLine="Dim LongTrama As Int";
_longtrama = 0;
 //BA.debugLineNum = 925;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 926;BA.debugLine="LongTrama= 60";
_longtrama = (int) (60);
 }else {
 //BA.debugLineNum = 928;BA.debugLine="LongTrama= 52";
_longtrama = (int) (52);
 };
 //BA.debugLineNum = 930;BA.debugLine="Dim data(LongTrama) As Byte";
_data = new byte[_longtrama];
;
 //BA.debugLineNum = 931;BA.debugLine="data(0)=87";
_data[(int) (0)] = (byte) (87);
 //BA.debugLineNum = 932;BA.debugLine="data(1)=84";
_data[(int) (1)] = (byte) (84);
 //BA.debugLineNum = 933;BA.debugLine="data(2)=71";
_data[(int) (2)] = (byte) (71);
 //BA.debugLineNum = 934;BA.debugLine="data(3)=82";
_data[(int) (3)] = (byte) (82);
 //BA.debugLineNum = 936;BA.debugLine="Dim c,pos As Int";
_c = 0;
_pos = 0;
 //BA.debugLineNum = 937;BA.debugLine="pos=4";
_pos = (int) (4);
 //BA.debugLineNum = 938;BA.debugLine="For c =0 To 11";
{
final int step18 = 1;
final int limit18 = (int) (11);
_c = (int) (0) ;
for (;(step18 > 0 && _c <= limit18) || (step18 < 0 && _c >= limit18) ;_c = ((int)(0 + _c + step18))  ) {
 //BA.debugLineNum = 939;BA.debugLine="If c < Timetable.Size Then";
if (_c<mostCurrent._timetable.getSize()) { 
 //BA.debugLineNum = 940;BA.debugLine="Dim H As Horario";
_h = new arduino.automatizacion.excontrol.PRO.acthorarios2._horario();
 //BA.debugLineNum = 941;BA.debugLine="H= Timetable.Get (c)";
_h = (arduino.automatizacion.excontrol.PRO.acthorarios2._horario)(mostCurrent._timetable.Get(_c));
 //BA.debugLineNum = 942;BA.debugLine="data(pos)= H.Hora";
_data[_pos] = _h.Hora;
 //BA.debugLineNum = 943;BA.debugLine="data(pos+1)= H.Minuto";
_data[(int) (_pos+1)] = _h.Minuto;
 //BA.debugLineNum = 944;BA.debugLine="data(pos+2)= H.Circuito";
_data[(int) (_pos+2)] = _h.Circuito;
 //BA.debugLineNum = 945;BA.debugLine="data(pos+3)= H.Salida";
_data[(int) (_pos+3)] = (byte) (_h.Salida);
 }else {
 //BA.debugLineNum = 948;BA.debugLine="data(pos)= 66";
_data[_pos] = (byte) (66);
 //BA.debugLineNum = 949;BA.debugLine="data(pos+1)= 66";
_data[(int) (_pos+1)] = (byte) (66);
 //BA.debugLineNum = 950;BA.debugLine="data(pos+2)= 66";
_data[(int) (_pos+2)] = (byte) (66);
 //BA.debugLineNum = 951;BA.debugLine="data(pos+3)= 66";
_data[(int) (_pos+3)] = (byte) (66);
 };
 //BA.debugLineNum = 953;BA.debugLine="pos = pos + 4";
_pos = (int) (_pos+4);
 }
};
 //BA.debugLineNum = 955;BA.debugLine="If ValoresComunes.central.ConexionSegura Then V";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
mostCurrent._valorescomunes._completartrama(mostCurrent.activityBA,_data);};
 //BA.debugLineNum = 956;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 };
 };
 //BA.debugLineNum = 960;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim UDPSocket1 As UDPSocket";
_udpsocket1 = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket();
 //BA.debugLineNum = 10;BA.debugLine="Dim pw As PhoneWakeState 'Controlar suspension pa";
_pw = new anywheresoftware.b4a.phone.Phone.PhoneWakeState();
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public static String  _refrescapantalla() throws Exception{
int _p = 0;
arduino.automatizacion.excontrol.PRO.acthorarios2._horario _h = null;
 //BA.debugLineNum = 719;BA.debugLine="Sub RefrescaPantalla";
 //BA.debugLineNum = 720;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 721;BA.debugLine="Dim p As Int";
_p = 0;
 //BA.debugLineNum = 722;BA.debugLine="For p =0 To Timetable.Size -1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._timetable.getSize()-1);
_p = (int) (0) ;
for (;(step3 > 0 && _p <= limit3) || (step3 < 0 && _p >= limit3) ;_p = ((int)(0 + _p + step3))  ) {
 //BA.debugLineNum = 723;BA.debugLine="Dim H As Horario";
_h = new arduino.automatizacion.excontrol.PRO.acthorarios2._horario();
 //BA.debugLineNum = 724;BA.debugLine="H= Timetable.Get (p)";
_h = (arduino.automatizacion.excontrol.PRO.acthorarios2._horario)(mostCurrent._timetable.Get(_p));
 //BA.debugLineNum = 725;BA.debugLine="If CircuitoMostrado>59 Then";
if (_circuitomostrado>59) { 
 //BA.debugLineNum = 726;BA.debugLine="AddCirToScr(p,H)";
_addcirtoscr(_p,_h);
 }else {
 //BA.debugLineNum = 728;BA.debugLine="If H.Circuito = CircuitoMostrado Then AddCirToS";
if (_h.Circuito==_circuitomostrado) { 
_addcirtoscr(_p,_h);};
 };
 }
};
 //BA.debugLineNum = 731;BA.debugLine="End Sub";
return "";
}
public static boolean  _sendingtrama(byte[] _data) throws Exception{
anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet = null;
 //BA.debugLineNum = 709;BA.debugLine="Sub SendingTrama (data() As Byte) As Boolean";
 //BA.debugLineNum = 710;BA.debugLine="Try";
try { //BA.debugLineNum = 711;BA.debugLine="Dim Packet As UDPPacket";
_packet = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket();
 //BA.debugLineNum = 712;BA.debugLine="Packet.Initialize(data, ValoresComunes.ip, Valor";
_packet.Initialize(_data,mostCurrent._valorescomunes._ip,mostCurrent._valorescomunes._puerto);
 //BA.debugLineNum = 713;BA.debugLine="UDPSocket1.Send(Packet)";
_udpsocket1.Send(_packet);
 //BA.debugLineNum = 714;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 716;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 718;BA.debugLine="End Sub";
return false;
}
public static void  _sendtrama(byte[] _data) throws Exception{
ResumableSub_SendTrama rsub = new ResumableSub_SendTrama(null,_data);
rsub.resume(processBA, null);
}
public static class ResumableSub_SendTrama extends BA.ResumableSub {
public ResumableSub_SendTrama(arduino.automatizacion.excontrol.PRO.actalarmas2 parent,byte[] _data) {
this.parent = parent;
this._data = _data;
}
arduino.automatizacion.excontrol.PRO.actalarmas2 parent;
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
 //BA.debugLineNum = 662;BA.debugLine="Dim Resultado As Boolean";
_resultado = false;
 //BA.debugLineNum = 663;BA.debugLine="Dim Reintento As Int";
_reintento = 0;
 //BA.debugLineNum = 666;BA.debugLine="EnableMenu =False";
parent._enablemenu = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 668;BA.debugLine="CmdSeleccion.Enabled =False";
parent.mostCurrent._cmdseleccion.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 669;BA.debugLine="AniCargando.Start (ImgCargando)";
parent.mostCurrent._anicargando.Start((android.view.View)(parent.mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 670;BA.debugLine="PaqueteEnviado =False";
parent._paqueteenviado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 672;BA.debugLine="TramaEnviada= data";
parent._tramaenviada = _data;
 //BA.debugLineNum = 675;BA.debugLine="Do 	While Resultado= False And Reintento < 40";
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
 //BA.debugLineNum = 676;BA.debugLine="Dim ServerSocket1 As ServerSocket";
_serversocket1 = new anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper();
 //BA.debugLineNum = 677;BA.debugLine="Dim MyIp As String";
_myip = "";
 //BA.debugLineNum = 678;BA.debugLine="If ActMosaico.Forzar3g Then";
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
 //BA.debugLineNum = 679;BA.debugLine="MyIp=ServerSocket1.GetMyIP";
_myip = _serversocket1.GetMyIP();
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 681;BA.debugLine="MyIp=ServerSocket1.GetMyWifiIP";
_myip = _serversocket1.GetMyWifiIP();
 if (true) break;
;
 //BA.debugLineNum = 683;BA.debugLine="If MyIp  <> \"127.0.0.1\" Then";

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
 //BA.debugLineNum = 684;BA.debugLine="Resultado = True";
_resultado = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 13:
//C
this.state = 14;
 //BA.debugLineNum = 686;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 687;BA.debugLine="Sleep (200)";
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
 //BA.debugLineNum = 691;BA.debugLine="If Resultado = True Then";

case 15:
//if
this.state = 28;
if (_resultado==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 17;
}if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 692;BA.debugLine="Resultado =False";
_resultado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 693;BA.debugLine="Reintento =0";
_reintento = (int) (0);
 //BA.debugLineNum = 694;BA.debugLine="Do 	While Resultado= False And Reintento < 40";
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
 //BA.debugLineNum = 695;BA.debugLine="Resultado = SendingTrama(data)";
_resultado = _sendingtrama(_data);
 //BA.debugLineNum = 696;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 697;BA.debugLine="If Resultado=False Then Sleep (200)";
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
 //BA.debugLineNum = 702;BA.debugLine="If Resultado = False  Then";

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
 //BA.debugLineNum = 703;BA.debugLine="ActMosaico.Conectado =False";
parent.mostCurrent._actmosaico._conectado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 704;BA.debugLine="StartActivity(ActMosaico)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._actmosaico.getObject()));
 if (true) break;

case 32:
//C
this.state = 33;
 //BA.debugLineNum = 706;BA.debugLine="PaqueteEnviado=True";
parent._paqueteenviado = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 33:
//C
this.state = -1;
;
 //BA.debugLineNum = 708;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _udp_packetarrived(anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet) throws Exception{
String _msg = "";
int[] _valores = null;
int _p = 0;
arduino.automatizacion.excontrol.PRO.acthorarios2._horario _hor = null;
 //BA.debugLineNum = 804;BA.debugLine="Sub UDP_PacketArrived (Packet As UDPPacket)";
 //BA.debugLineNum = 805;BA.debugLine="Try";
try { //BA.debugLineNum = 806;BA.debugLine="Dim msg As String";
_msg = "";
 //BA.debugLineNum = 807;BA.debugLine="msg = BytesToString(Packet.data, Packet.Offse";
_msg = anywheresoftware.b4a.keywords.Common.BytesToString(_packet.getData(),_packet.getOffset(),_packet.getLength(),"UTF8");
 //BA.debugLineNum = 810;BA.debugLine="If msg.Contains (\"TIGR\") Then";
if (_msg.contains("TIGR")) { 
 //BA.debugLineNum = 811;BA.debugLine="Timetable.Clear";
mostCurrent._timetable.Clear();
 //BA.debugLineNum = 812;BA.debugLine="Dim Valores(48) As Int";
_valores = new int[(int) (48)];
;
 //BA.debugLineNum = 813;BA.debugLine="Dim p As Int";
_p = 0;
 //BA.debugLineNum = 814;BA.debugLine="For p =4 To Packet.Length -1";
{
final int step8 = 1;
final int limit8 = (int) (_packet.getLength()-1);
_p = (int) (4) ;
for (;(step8 > 0 && _p <= limit8) || (step8 < 0 && _p >= limit8) ;_p = ((int)(0 + _p + step8))  ) {
 //BA.debugLineNum = 815;BA.debugLine="Valores(p-4)=Packet.data (p)-1";
_valores[(int) (_p-4)] = (int) (_packet.getData()[_p]-1);
 //BA.debugLineNum = 816;BA.debugLine="If Valores(p-4) < 0 Then";
if (_valores[(int) (_p-4)]<0) { 
 //BA.debugLineNum = 817;BA.debugLine="Valores(p-4)=Valores(p-4) +256";
_valores[(int) (_p-4)] = (int) (_valores[(int) (_p-4)]+256);
 };
 }
};
 //BA.debugLineNum = 821;BA.debugLine="p=0";
_p = (int) (0);
 //BA.debugLineNum = 823;BA.debugLine="Do While p < 45";
while (_p<45) {
 //BA.debugLineNum = 824;BA.debugLine="If Valores(p)<24 And Valores(p)>=0 And Valores";
if (_valores[_p]<24 && _valores[_p]>=0 && _valores[(int) (_p+1)]>=0 && _valores[(int) (_p+1)]<60 && _valores[(int) (_p+2)]>=0 && _valores[(int) (_p+2)]<=60 && _valores[(int) (_p+3)]>=0 && _valores[(int) (_p+3)]<=240) { 
 //BA.debugLineNum = 825;BA.debugLine="Dim Hor As Horario";
_hor = new arduino.automatizacion.excontrol.PRO.acthorarios2._horario();
 //BA.debugLineNum = 826;BA.debugLine="Hor.Hora = Valores(p)";
_hor.Hora = (byte) (_valores[_p]);
 //BA.debugLineNum = 827;BA.debugLine="Hor.minuto = Valores(p+1)";
_hor.Minuto = (byte) (_valores[(int) (_p+1)]);
 //BA.debugLineNum = 828;BA.debugLine="Hor.Circuito =Valores(p+2)";
_hor.Circuito = (byte) (_valores[(int) (_p+2)]);
 //BA.debugLineNum = 829;BA.debugLine="Hor.Salida = Valores(p+3)";
_hor.Salida = _valores[(int) (_p+3)];
 //BA.debugLineNum = 830;BA.debugLine="Timetable.Add (Hor)";
mostCurrent._timetable.Add((Object)(_hor));
 };
 //BA.debugLineNum = 832;BA.debugLine="p=p+4";
_p = (int) (_p+4);
 }
;
 //BA.debugLineNum = 834;BA.debugLine="RefrescaPantalla";
_refrescapantalla();
 }else {
 //BA.debugLineNum = 838;BA.debugLine="If msg =\"REPETIRMSG\" Then";
if ((_msg).equals("REPETIRMSG")) { 
 //BA.debugLineNum = 839;BA.debugLine="SendTrama(TramaEnviada)";
_sendtrama(_tramaenviada);
 //BA.debugLineNum = 840;BA.debugLine="Return";
if (true) return "";
 }else {
 };
 };
 //BA.debugLineNum = 846;BA.debugLine="PaqueteEnviado = False";
_paqueteenviado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 847;BA.debugLine="EnableMenu =True";
_enablemenu = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 849;BA.debugLine="CmdSeleccion.Enabled =True";
mostCurrent._cmdseleccion.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 850;BA.debugLine="AniCargando.Stop(ImgCargando)";
mostCurrent._anicargando.Stop((android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 851;BA.debugLine="ImgCargando.Visible =False";
mostCurrent._imgcargando.setVisible(anywheresoftware.b4a.keywords.Common.False);
 } 
       catch (Exception e40) {
			processBA.setLastException(e40); };
 //BA.debugLineNum = 857;BA.debugLine="End Sub";
return "";
}
}
