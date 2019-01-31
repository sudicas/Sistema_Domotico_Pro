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

public class actconescena extends Activity implements B4AActivity{
	public static actconescena mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actconescena");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actconescena).");
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
		activityBA = new BA(this, layout, processBA, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actconescena");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "arduino.automatizacion.excontrol.PRO.actconescena", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actconescena) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actconescena) Resume **");
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
		return actconescena.class;
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
        BA.LogInfo("** Activity (actconescena) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (actconescena) Resume **");
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
public static int[] _valores = null;
public static byte _numeroscene = (byte)0;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _cmdguardar = null;
public anywheresoftware.b4a.objects.ButtonWrapper _cmdterminar = null;
public static byte[] _tramaenviada = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgcargando = null;
public anywheresoftware.b4a.objects.AnimationWrapper _anicargando = null;
public static boolean _paqueteenviado = false;
public anywheresoftware.b4a.agraham.dialogs.InputDialog.ColorPickerDialog _dlg1 = null;
public anywheresoftware.b4a.agraham.dialogs.InputDialog.ColorPickerDialog _dlg2 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _cmdbarok = null;
public anywheresoftware.b4a.objects.ButtonWrapper _cmdbarcancel = null;
public anywheresoftware.b4a.objects.PanelWrapper _panbarra = null;
public arduino.automatizacion.excontrol.PRO.mbvseekbar _barra = null;
public static byte _barcir = (byte)0;
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
 //BA.debugLineNum = 38;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 40;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = Fals";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 41;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 };
 //BA.debugLineNum = 49;BA.debugLine="Dim he As Int = 40dip";
_he = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40));
 //BA.debugLineNum = 51;BA.debugLine="Activity.LoadLayout (\"frmconfig\")";
mostCurrent._activity.LoadLayout("frmconfig",mostCurrent.activityBA);
 //BA.debugLineNum = 52;BA.debugLine="Activity.Title = ValoresComunes.Scenes (NumeroSce";
mostCurrent._activity.setTitle(BA.ObjectToCharSequence(mostCurrent._valorescomunes._scenes[(int) (_numeroscene-1)].Nombre));
 //BA.debugLineNum = 53;BA.debugLine="ListView1.Height = Activity.Height - he";
mostCurrent._listview1.setHeight((int) (mostCurrent._activity.getHeight()-_he));
 //BA.debugLineNum = 54;BA.debugLine="ListView1.Width =Activity.Width";
mostCurrent._listview1.setWidth(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 57;BA.debugLine="CmdGuardar.Top=Activity.Height-he";
mostCurrent._cmdguardar.setTop((int) (mostCurrent._activity.getHeight()-_he));
 //BA.debugLineNum = 58;BA.debugLine="CmdGuardar.Left = Activity.Width   - (he+4dip)";
mostCurrent._cmdguardar.setLeft((int) (mostCurrent._activity.getWidth()-(_he+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4)))));
 //BA.debugLineNum = 59;BA.debugLine="CmdGuardar.Height =he";
mostCurrent._cmdguardar.setHeight(_he);
 //BA.debugLineNum = 60;BA.debugLine="CmdGuardar.Width =he' Activity.Width";
mostCurrent._cmdguardar.setWidth(_he);
 //BA.debugLineNum = 61;BA.debugLine="CmdGuardar.Text = \"\"'ValoresComunes.GetLanString";
mostCurrent._cmdguardar.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 62;BA.debugLine="CmdGuardar.SetBackgroundImage(ValoresComunes.CmdI";
mostCurrent._cmdguardar.SetBackgroundImage((android.graphics.Bitmap)(mostCurrent._valorescomunes._cmdimgsave(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 64;BA.debugLine="CmdTerminar.Top=Activity.Height-he";
mostCurrent._cmdterminar.setTop((int) (mostCurrent._activity.getHeight()-_he));
 //BA.debugLineNum = 65;BA.debugLine="CmdTerminar.Height =he";
mostCurrent._cmdterminar.setHeight(_he);
 //BA.debugLineNum = 66;BA.debugLine="CmdTerminar.Width =he";
mostCurrent._cmdterminar.setWidth(_he);
 //BA.debugLineNum = 67;BA.debugLine="CmdTerminar.Left =Activity.Width   - ((he  * 2)";
mostCurrent._cmdterminar.setLeft((int) (mostCurrent._activity.getWidth()-((_he*2)+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)))));
 //BA.debugLineNum = 68;BA.debugLine="CmdTerminar.SetBackgroundImage(ValoresComunes.Cmd";
mostCurrent._cmdterminar.SetBackgroundImage((android.graphics.Bitmap)(mostCurrent._valorescomunes._cmdimgback(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 69;BA.debugLine="CmdTerminar.Text = \"\"";
mostCurrent._cmdterminar.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 71;BA.debugLine="Dim h As Int";
_h = 0;
 //BA.debugLineNum = 72;BA.debugLine="h=75dip";
_h = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75));
 //BA.debugLineNum = 75;BA.debugLine="ListView1.TwoLinesAndBitmap .ItemHeight =h";
mostCurrent._listview1.getTwoLinesAndBitmap().setItemHeight(_h);
 //BA.debugLineNum = 76;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.Width=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setWidth(_h);
 //BA.debugLineNum = 77;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.height=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setHeight(_h);
 //BA.debugLineNum = 79;BA.debugLine="ListView1.TwoLinesAndBitmap.Label .Left =h + h/9";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setLeft((int) (_h+_h/(double)9));
 //BA.debugLineNum = 80;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.height  =h/1.8";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setHeight((int) (_h/(double)1.8));
 //BA.debugLineNum = 81;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.Gravity = Bit.O";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.BOTTOM));
 //BA.debugLineNum = 83;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel .Left =Li";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setLeft(mostCurrent._listview1.getTwoLinesAndBitmap().Label.getLeft());
 //BA.debugLineNum = 84;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.height";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setHeight((int) (_h-mostCurrent._listview1.getTwoLinesAndBitmap().Label.getHeight()));
 //BA.debugLineNum = 85;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.top   = L";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setTop(mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.getHeight());
 //BA.debugLineNum = 86;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.Gravity =";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 88;BA.debugLine="ValoresComunes.LoadPaleta(dlg1  ,dlg2)";
mostCurrent._valorescomunes._loadpaleta(mostCurrent.activityBA,mostCurrent._dlg1,mostCurrent._dlg2);
 //BA.debugLineNum = 90;BA.debugLine="IniBarDialog 'Iniciamos dialog barra";
_inibardialog();
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 93;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 95;BA.debugLine="UDPSocket1.Close";
_udpsocket1.Close();
 //BA.debugLineNum = 96;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 97;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
int _longtrama = 0;
byte[] _data = null;
 //BA.debugLineNum = 98;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 99;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = True";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 101;BA.debugLine="If Main.MyLan.IsInitialized = False Then Main.My";
if (mostCurrent._main._mylan.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._main._mylan.Initialize(processBA,(int) (0),"");};
 //BA.debugLineNum = 102;BA.debugLine="If UDPSocket1.IsInitialized = False Then";
if (_udpsocket1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 103;BA.debugLine="ValoresComunes.IniUDP(UDPSocket1)";
mostCurrent._valorescomunes._iniudp(mostCurrent.activityBA,_udpsocket1);
 };
 //BA.debugLineNum = 106;BA.debugLine="If ImgCargando.IsInitialized = False Then";
if (mostCurrent._imgcargando.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 107;BA.debugLine="ImgCargando.Initialize (\"ImgCargando\")";
mostCurrent._imgcargando.Initialize(mostCurrent.activityBA,"ImgCargando");
 //BA.debugLineNum = 108;BA.debugLine="ImgCargando.Bitmap = ValoresComunes.Cargando";
mostCurrent._imgcargando.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._cargando(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 109;BA.debugLine="ImgCargando.Gravity = Gravity.FILL";
mostCurrent._imgcargando.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 110;BA.debugLine="Activity.AddView (ImgCargando,  PerXToCurrent(4";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._imgcargando.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (40),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (35),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (20),mostCurrent.activityBA));
 };
 //BA.debugLineNum = 113;BA.debugLine="CmdGuardar.Enabled =False";
mostCurrent._cmdguardar.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 117;BA.debugLine="AniCargando.InitializeRotateCenter( \"AniCargando";
mostCurrent._anicargando.InitializeRotateCenter(mostCurrent.activityBA,"AniCargando",(float) (0),(float) (180),(android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 118;BA.debugLine="AniCargando.Duration  =   1000";
mostCurrent._anicargando.setDuration((long) (1000));
 //BA.debugLineNum = 119;BA.debugLine="AniCargando.RepeatCount =5";
mostCurrent._anicargando.setRepeatCount((int) (5));
 //BA.debugLineNum = 120;BA.debugLine="AniCargando.RepeatMode = AniCargando.REPEAT_REVE";
mostCurrent._anicargando.setRepeatMode(mostCurrent._anicargando.REPEAT_REVERSE);
 //BA.debugLineNum = 121;BA.debugLine="Dim LongTrama As Int";
_longtrama = 0;
 //BA.debugLineNum = 122;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 123;BA.debugLine="LongTrama= 13";
_longtrama = (int) (13);
 }else {
 //BA.debugLineNum = 125;BA.debugLine="LongTrama= 5";
_longtrama = (int) (5);
 };
 //BA.debugLineNum = 127;BA.debugLine="Dim data(LongTrama) As Byte";
_data = new byte[_longtrama];
;
 //BA.debugLineNum = 128;BA.debugLine="data(0)=82";
_data[(int) (0)] = (byte) (82);
 //BA.debugLineNum = 129;BA.debugLine="data(1)=69";
_data[(int) (1)] = (byte) (69);
 //BA.debugLineNum = 130;BA.debugLine="data(2)=83";
_data[(int) (2)] = (byte) (83);
 //BA.debugLineNum = 131;BA.debugLine="data(3)=67";
_data[(int) (3)] = (byte) (67);
 //BA.debugLineNum = 132;BA.debugLine="If NumeroScene <1 Or NumeroScene >10 Then Numero";
if (_numeroscene<1 || _numeroscene>10) { 
_numeroscene = (byte) (1);};
 //BA.debugLineNum = 133;BA.debugLine="data(4)= NumeroScene";
_data[(int) (4)] = _numeroscene;
 //BA.debugLineNum = 134;BA.debugLine="If ValoresComunes.central.ConexionSegura Then Va";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
mostCurrent._valorescomunes._completartrama(mostCurrent.activityBA,_data);};
 //BA.debugLineNum = 135;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 }else {
 //BA.debugLineNum = 137;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 138;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 };
 //BA.debugLineNum = 140;BA.debugLine="End Sub";
return "";
}
public static String  _actualizavalores() throws Exception{
int _i = 0;
double _val1 = 0;
String _l1 = "";
arduino.automatizacion.excontrol.PRO.valorescomunes._circuito _se = null;
String _unidad = "";
String _s = "";
 //BA.debugLineNum = 151;BA.debugLine="Sub ActualizaValores()";
 //BA.debugLineNum = 152;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 156;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 157;BA.debugLine="For i =0 To 29";
{
final int step3 = 1;
final int limit3 = (int) (29);
_i = (int) (0) ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
 //BA.debugLineNum = 159;BA.debugLine="If ValoresComunes.Circuitos (i).Nombre <> \"\" And";
if ((mostCurrent._valorescomunes._circuitos[_i].Nombre).equals("") == false && mostCurrent._valorescomunes._circuitos[_i].Rango>0 && mostCurrent._valorescomunes._circuitos[_i].Rango<1000) { 
 //BA.debugLineNum = 165;BA.debugLine="If ValoresComunes.Circuitos (i).Rango=29 Then";
if (mostCurrent._valorescomunes._circuitos[_i].Rango==29) { 
 //BA.debugLineNum = 167;BA.debugLine="If Valores(i)>240  Or Valores(i)<0 Then";
if (_valores[_i]>240 || _valores[_i]<0) { 
 //BA.debugLineNum = 168;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComun";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Nombre+" : ####ºC"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg140")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._temperaturades(mostCurrent.activityBA).getObject()),(Object)(_i));
 //BA.debugLineNum = 169;BA.debugLine="Valores(i)=250";
_valores[_i] = (int) (250);
 }else {
 //BA.debugLineNum = 171;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComun";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString(_valores[_i])+"ºC"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Descripcion),(android.graphics.Bitmap)(mostCurrent._valorescomunes._temperatura(mostCurrent.activityBA).getObject()),(Object)(_i));
 };
 }else if(mostCurrent._valorescomunes._circuitos[_i].Rango==30) { 
 //BA.debugLineNum = 177;BA.debugLine="If Valores(i)>170  Or Valores(i)<0 Then";
if (_valores[_i]>170 || _valores[_i]<0) { 
 //BA.debugLineNum = 178;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComun";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Nombre+" : ####ºC"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Descripcion),(android.graphics.Bitmap)(mostCurrent._valorescomunes._temperaturades(mostCurrent.activityBA).getObject()),(Object)(_i));
 //BA.debugLineNum = 179;BA.debugLine="Valores(i)=250";
_valores[_i] = (int) (250);
 }else {
 //BA.debugLineNum = 181;BA.debugLine="Dim Val1  As Double";
_val1 = 0;
 //BA.debugLineNum = 182;BA.debugLine="Val1= (Valores(i) +150)/10";
_val1 = (_valores[_i]+150)/(double)10;
 //BA.debugLineNum = 183;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComun";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString(_val1)+"ºC"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Descripcion),(android.graphics.Bitmap)(mostCurrent._valorescomunes._temperatura(mostCurrent.activityBA).getObject()),(Object)(_i));
 };
 }else if(mostCurrent._valorescomunes._circuitos[_i].Rango==31) { 
 //BA.debugLineNum = 189;BA.debugLine="If Valores(i)>240  Or Valores(i)<0 Then";
if (_valores[_i]>240 || _valores[_i]<0) { 
 //BA.debugLineNum = 190;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComun";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Nombre+" : ####ºC"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg140")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._temperaturades(mostCurrent.activityBA).getObject()),(Object)(_i));
 //BA.debugLineNum = 191;BA.debugLine="Valores(i)=250";
_valores[_i] = (int) (250);
 }else {
 //BA.debugLineNum = 193;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComun";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Nombre+" : -"+BA.NumberToString(_valores[_i])+"ºC"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg92")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._temperatura(mostCurrent.activityBA).getObject()),(Object)(_i));
 };
 }else if(mostCurrent._valorescomunes._circuitos[_i].Rango==33) { 
 //BA.debugLineNum = 196;BA.debugLine="Valores(i)=255";
_valores[_i] = (int) (255);
 }else if(mostCurrent._valorescomunes._circuitos[_i].Rango>52 && mostCurrent._valorescomunes._circuitos[_i].Rango<57) { 
 //BA.debugLineNum = 199;BA.debugLine="Dim l1 As String";
_l1 = "";
 //BA.debugLineNum = 200;BA.debugLine="Dim se As Circuito";
_se = new arduino.automatizacion.excontrol.PRO.valorescomunes._circuito();
 //BA.debugLineNum = 201;BA.debugLine="Dim unidad As String";
_unidad = "";
 //BA.debugLineNum = 205;BA.debugLine="se=ValoresComunes.Sensores( ValoresComunes.Cir";
_se = mostCurrent._valorescomunes._sensores[mostCurrent._valorescomunes._circuitos[_i].DeviceNumber];
 //BA.debugLineNum = 208;BA.debugLine="unidad=ValoresComunes.UnidadSensor(ValoresComu";
_unidad = mostCurrent._valorescomunes._unidadsensor(mostCurrent.activityBA,mostCurrent._valorescomunes._circuitos[_i].DeviceNumber);
 //BA.debugLineNum = 212;BA.debugLine="Select Case ValoresComunes.Circuitos (i).Rango";
switch (BA.switchObjectToInt(mostCurrent._valorescomunes._circuitos[_i].Rango,(int) (53),(int) (54),(int) (55),(int) (56),(int) (57))) {
case 0: {
 //BA.debugLineNum = 215;BA.debugLine="l1=ValoresComunes.Circuitos (i).Nombre  & \"";
_l1 = mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString(_valores[_i])+_unidad;
 break; }
case 1: {
 //BA.debugLineNum = 217;BA.debugLine="l1=ValoresComunes.Circuitos (i).Nombre  & \"";
_l1 = mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString(_valores[_i])+_unidad;
 break; }
case 2: {
 //BA.debugLineNum = 220;BA.debugLine="l1=ValoresComunes.Circuitos (i).Nombre  & \"";
_l1 = mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString((_valores[_i]*10))+_unidad;
 break; }
case 3: {
 //BA.debugLineNum = 222;BA.debugLine="l1=ValoresComunes.Circuitos (i).Nombre  & \"";
_l1 = mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString((_valores[_i]*100))+_unidad;
 break; }
case 4: {
 //BA.debugLineNum = 224;BA.debugLine="l1=ValoresComunes.Circuitos (i).Nombre  & \"";
_l1 = mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString((_valores[_i]*1000))+_unidad;
 break; }
}
;
 //BA.debugLineNum = 228;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (l1 ,se.Nombre";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_l1),BA.ObjectToCharSequence(_se.Nombre),(android.graphics.Bitmap)(mostCurrent._valorescomunes._iconocircuito(mostCurrent.activityBA,_i,_valores[_i]).getObject()),(Object)(_i));
 }else if(mostCurrent._valorescomunes._circuitos[_i].Rango==34 || mostCurrent._valorescomunes._circuitos[_i].Rango==36) { 
 //BA.debugLineNum = 234;BA.debugLine="If Valores(i)>100  Or Valores(i)<0 Then";
if (_valores[_i]>100 || _valores[_i]<0) { 
 //BA.debugLineNum = 235;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComun";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Nombre),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg140")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._persianades(mostCurrent.activityBA).getObject()),(Object)(_i));
 //BA.debugLineNum = 236;BA.debugLine="Valores(i)=250";
_valores[_i] = (int) (250);
 }else {
 //BA.debugLineNum = 238;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComun";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Nombre+" - "+BA.NumberToString(_valores[_i])+"%"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Descripcion),(android.graphics.Bitmap)(mostCurrent._valorescomunes._persiana(mostCurrent.activityBA).getObject()),(Object)(_i));
 };
 }else {
 //BA.debugLineNum = 243;BA.debugLine="Dim s As String";
_s = "";
 //BA.debugLineNum = 244;BA.debugLine="If Valores(i)=250 Then";
if (_valores[_i]==250) { 
 //BA.debugLineNum = 245;BA.debugLine="s=ValoresComunes.GetLanString (\"reg140\")";
_s = mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg140");
 }else {
 //BA.debugLineNum = 247;BA.debugLine="s= ValoresComunes.Circuitos (i).Descripcion";
_s = mostCurrent._valorescomunes._circuitos[_i].Descripcion;
 };
 //BA.debugLineNum = 249;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Nombre),BA.ObjectToCharSequence(_s),(android.graphics.Bitmap)(mostCurrent._valorescomunes._iconocircuito(mostCurrent.activityBA,_i,_valores[_i]).getObject()),(Object)(_i));
 };
 }else {
 //BA.debugLineNum = 255;BA.debugLine="Valores(i)=250";
_valores[_i] = (int) (250);
 };
 }
};
 //BA.debugLineNum = 259;BA.debugLine="End Sub";
return "";
}
public static String  _anicargando_animationend() throws Exception{
 //BA.debugLineNum = 141;BA.debugLine="Sub AniCargando_AnimationEnd";
 //BA.debugLineNum = 143;BA.debugLine="If PaqueteEnviado = True Then";
if (_paqueteenviado==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 144;BA.debugLine="AniCargando.Start (ImgCargando)";
mostCurrent._anicargando.Start((android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 145;BA.debugLine="SendTrama(TramaEnviada)";
_sendtrama(_tramaenviada);
 };
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
return "";
}
public static String  _barra_on(byte _pos) throws Exception{
 //BA.debugLineNum = 684;BA.debugLine="Sub Barra_On(Pos As Byte)";
 //BA.debugLineNum = 685;BA.debugLine="ListView1.Enabled =False";
mostCurrent._listview1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 686;BA.debugLine="CmdGuardar.Enabled =False";
mostCurrent._cmdguardar.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 687;BA.debugLine="CmdTerminar.Enabled =False";
mostCurrent._cmdterminar.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 689;BA.debugLine="BarCir=Pos";
_barcir = _pos;
 //BA.debugLineNum = 690;BA.debugLine="If Valores(Pos)<101 Then";
if (_valores[(int) (_pos)]<101) { 
 //BA.debugLineNum = 691;BA.debugLine="Barra.Value = Valores(Pos)";
mostCurrent._barra._setvalue(_valores[(int) (_pos)]);
 }else {
 //BA.debugLineNum = 693;BA.debugLine="Barra.Value =0";
mostCurrent._barra._setvalue((int) (0));
 };
 //BA.debugLineNum = 697;BA.debugLine="Activity.AddView (PanBarra,0,0, Activity.Width ,A";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._panbarra.getObject()),(int) (0),(int) (0),mostCurrent._activity.getWidth(),mostCurrent._activity.getHeight());
 //BA.debugLineNum = 699;BA.debugLine="End Sub";
return "";
}
public static String  _cmdbarcancel_click() throws Exception{
 //BA.debugLineNum = 728;BA.debugLine="Sub CmdBarCancel_Click";
 //BA.debugLineNum = 729;BA.debugLine="ListView1.Enabled =True";
mostCurrent._listview1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 730;BA.debugLine="CmdGuardar.Enabled =True";
mostCurrent._cmdguardar.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 731;BA.debugLine="CmdTerminar.Enabled =True";
mostCurrent._cmdterminar.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 732;BA.debugLine="PanBarra.RemoveView";
mostCurrent._panbarra.RemoveView();
 //BA.debugLineNum = 733;BA.debugLine="End Sub";
return "";
}
public static String  _cmdbarok_click() throws Exception{
 //BA.debugLineNum = 717;BA.debugLine="Sub CmdBarOk_Click";
 //BA.debugLineNum = 718;BA.debugLine="ListView1.Enabled =True";
mostCurrent._listview1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 719;BA.debugLine="CmdGuardar.Enabled =True";
mostCurrent._cmdguardar.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 720;BA.debugLine="CmdTerminar.Enabled =True";
mostCurrent._cmdterminar.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 721;BA.debugLine="PanBarra.RemoveView";
mostCurrent._panbarra.RemoveView();
 //BA.debugLineNum = 722;BA.debugLine="Valores(BarCir)=Barra.currentValue";
_valores[(int) (_barcir)] = mostCurrent._barra._currentvalue;
 //BA.debugLineNum = 723;BA.debugLine="ActualizaValores";
_actualizavalores();
 //BA.debugLineNum = 727;BA.debugLine="End Sub";
return "";
}
public static String  _cmdguardar_click() throws Exception{
int _longtrama = 0;
byte[] _data = null;
int _i = 0;
 //BA.debugLineNum = 660;BA.debugLine="Sub CmdGuardar_Click";
 //BA.debugLineNum = 663;BA.debugLine="Dim LongTrama As Int";
_longtrama = 0;
 //BA.debugLineNum = 664;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 665;BA.debugLine="LongTrama= 43";
_longtrama = (int) (43);
 }else {
 //BA.debugLineNum = 667;BA.debugLine="LongTrama= 35";
_longtrama = (int) (35);
 };
 //BA.debugLineNum = 670;BA.debugLine="Dim data(LongTrama) As Byte";
_data = new byte[_longtrama];
;
 //BA.debugLineNum = 671;BA.debugLine="data(0)=87";
_data[(int) (0)] = (byte) (87);
 //BA.debugLineNum = 672;BA.debugLine="data(1)=69";
_data[(int) (1)] = (byte) (69);
 //BA.debugLineNum = 673;BA.debugLine="data(2)=83";
_data[(int) (2)] = (byte) (83);
 //BA.debugLineNum = 674;BA.debugLine="data(3)=67";
_data[(int) (3)] = (byte) (67);
 //BA.debugLineNum = 675;BA.debugLine="data(4)=NumeroScene";
_data[(int) (4)] = _numeroscene;
 //BA.debugLineNum = 676;BA.debugLine="Dim I As Int";
_i = 0;
 //BA.debugLineNum = 678;BA.debugLine="For I =0 To 29";
{
final int step14 = 1;
final int limit14 = (int) (29);
_i = (int) (0) ;
for (;(step14 > 0 && _i <= limit14) || (step14 < 0 && _i >= limit14) ;_i = ((int)(0 + _i + step14))  ) {
 //BA.debugLineNum = 679;BA.debugLine="data(I+5)=Valores(I)+1";
_data[(int) (_i+5)] = (byte) (_valores[_i]+1);
 }
};
 //BA.debugLineNum = 681;BA.debugLine="If ValoresComunes.central.ConexionSegura Then Val";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
mostCurrent._valorescomunes._completartrama(mostCurrent.activityBA,_data);};
 //BA.debugLineNum = 682;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 //BA.debugLineNum = 683;BA.debugLine="End Sub";
return "";
}
public static String  _cmdterminar_click() throws Exception{
 //BA.debugLineNum = 657;BA.debugLine="Sub CmdTerminar_Click";
 //BA.debugLineNum = 658;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 659;BA.debugLine="End Sub";
return "";
}
public static int  _getrgbcolor() throws Exception{
int _res = 0;
int _v = 0;
int _b = 0;
 //BA.debugLineNum = 519;BA.debugLine="Sub GetRGBcolor() As Int";
 //BA.debugLineNum = 520;BA.debugLine="Dim res As Int";
_res = 0;
 //BA.debugLineNum = 521;BA.debugLine="res=DialogResponse.CANCEL";
_res = anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL;
 //BA.debugLineNum = 523;BA.debugLine="Do While res=DialogResponse.CANCEL";
while (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL) {
 //BA.debugLineNum = 524;BA.debugLine="dlg1.RGB=8388736";
mostCurrent._dlg1.setRGB((int) (8388736));
 //BA.debugLineNum = 525;BA.debugLine="res = dlg1.Show(ValoresComunes.GetLanString (\"re";
_res = mostCurrent._dlg1.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg96"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Ok"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg94"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 526;BA.debugLine="If res=DialogResponse.POSITIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 527;BA.debugLine="Dim v As Int";
_v = 0;
 //BA.debugLineNum = 528;BA.debugLine="v= dlg1.RGB";
_v = mostCurrent._dlg1.getRGB();
 //BA.debugLineNum = 529;BA.debugLine="Dim b As Int";
_b = 0;
 //BA.debugLineNum = 530;BA.debugLine="For b=0 To 14";
{
final int step10 = 1;
final int limit10 = (int) (14);
_b = (int) (0) ;
for (;(step10 > 0 && _b <= limit10) || (step10 < 0 && _b >= limit10) ;_b = ((int)(0 + _b + step10))  ) {
 //BA.debugLineNum = 531;BA.debugLine="If v = dlg1.GetPaletteAt(b) Then	Return b+1";
if (_v==mostCurrent._dlg1.GetPaletteAt(_b)) { 
if (true) return (int) (_b+1);};
 }
};
 }else if(_res==anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL) { 
 //BA.debugLineNum = 535;BA.debugLine="dlg2.RGB=14423100";
mostCurrent._dlg2.setRGB((int) (14423100));
 //BA.debugLineNum = 536;BA.debugLine="res = dlg2.Show(ValoresComunes.GetLanString (\"r";
_res = mostCurrent._dlg2.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg96"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Ok"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg94"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 538;BA.debugLine="If res=DialogResponse.POSITIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 539;BA.debugLine="Dim v As Int";
_v = 0;
 //BA.debugLineNum = 540;BA.debugLine="v= dlg2.RGB";
_v = mostCurrent._dlg2.getRGB();
 //BA.debugLineNum = 541;BA.debugLine="Dim b As Int";
_b = 0;
 //BA.debugLineNum = 542;BA.debugLine="For b=0 To 14";
{
final int step20 = 1;
final int limit20 = (int) (14);
_b = (int) (0) ;
for (;(step20 > 0 && _b <= limit20) || (step20 < 0 && _b >= limit20) ;_b = ((int)(0 + _b + step20))  ) {
 //BA.debugLineNum = 543;BA.debugLine="If v = dlg2.GetPaletteAt(b) Then	Return b+16";
if (_v==mostCurrent._dlg2.GetPaletteAt(_b)) { 
if (true) return (int) (_b+16);};
 }
};
 }else if(_res!=anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL) { 
 //BA.debugLineNum = 547;BA.debugLine="Return -1";
if (true) return (int) (-1);
 };
 }else {
 //BA.debugLineNum = 552;BA.debugLine="Return -1";
if (true) return (int) (-1);
 };
 }
;
 //BA.debugLineNum = 555;BA.debugLine="Return -1";
if (true) return (int) (-1);
 //BA.debugLineNum = 556;BA.debugLine="End Sub";
return 0;
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
 //BA.debugLineNum = 26;BA.debugLine="Dim dlg1 As ColorPickerDialog";
mostCurrent._dlg1 = new anywheresoftware.b4a.agraham.dialogs.InputDialog.ColorPickerDialog();
 //BA.debugLineNum = 27;BA.debugLine="Dim dlg2 As ColorPickerDialog";
mostCurrent._dlg2 = new anywheresoftware.b4a.agraham.dialogs.InputDialog.ColorPickerDialog();
 //BA.debugLineNum = 30;BA.debugLine="Dim CmdBarOk As Button";
mostCurrent._cmdbarok = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Dim CmdBarCancel As Button";
mostCurrent._cmdbarcancel = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Dim PanBarra As Panel";
mostCurrent._panbarra = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Dim Barra As mbVSeekBar";
mostCurrent._barra = new arduino.automatizacion.excontrol.PRO.mbvseekbar();
 //BA.debugLineNum = 34;BA.debugLine="Dim BarCir As Byte";
_barcir = (byte)0;
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
return "";
}
public static String  _inibardialog() throws Exception{
 //BA.debugLineNum = 700;BA.debugLine="Sub IniBarDialog(  )";
 //BA.debugLineNum = 701;BA.debugLine="CmdBarOk.Initialize(\"CmdBarOk\")";
mostCurrent._cmdbarok.Initialize(mostCurrent.activityBA,"CmdBarOk");
 //BA.debugLineNum = 702;BA.debugLine="CmdBarCancel.Initialize (\"CmdBarCancel\")";
mostCurrent._cmdbarcancel.Initialize(mostCurrent.activityBA,"CmdBarCancel");
 //BA.debugLineNum = 705;BA.debugLine="CmdBarOk.Text =ValoresComunes.GetLanString (\"reg8";
mostCurrent._cmdbarok.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83")));
 //BA.debugLineNum = 706;BA.debugLine="CmdBarCancel.Text =ValoresComunes.GetLanString (\"";
mostCurrent._cmdbarcancel.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel")));
 //BA.debugLineNum = 707;BA.debugLine="PanBarra.Initialize(\"PanBarra\")";
mostCurrent._panbarra.Initialize(mostCurrent.activityBA,"PanBarra");
 //BA.debugLineNum = 708;BA.debugLine="PanBarra.Color =  Colors.Black";
mostCurrent._panbarra.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 709;BA.debugLine="Barra.Initialize( PanBarra,  Me,\"bar_Click\",  50%";
mostCurrent._barra._initialize(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ActivityWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ActivityWrapper(), (anywheresoftware.b4a.BALayout)(mostCurrent._panbarra.getObject())),actconescena.getObject(),"bar_Click",(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (36))),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (72)),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (75),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (33)),anywheresoftware.b4a.keywords.Common.Colors.DarkGray,anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0x00),(int) (0x99),(int) (0xcc)),anywheresoftware.b4a.keywords.Common.True,(int) (70),(int) (100),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 710;BA.debugLine="Barra.CustomizeText(Colors.RGB(0x33,0xb5,0xe5),22";
mostCurrent._barra._customizetext(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0x33),(int) (0xb5),(int) (0xe5)),(int) (22),(anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD)));
 //BA.debugLineNum = 711;BA.debugLine="Barra.stepValue =1";
mostCurrent._barra._stepvalue = (int) (1);
 //BA.debugLineNum = 713;BA.debugLine="PanBarra.AddView (CmdBarOk, 5%x,85%y,44%x,14%y)";
mostCurrent._panbarra.AddView((android.view.View)(mostCurrent._cmdbarok.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (44),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (14),mostCurrent.activityBA));
 //BA.debugLineNum = 714;BA.debugLine="PanBarra.AddView (CmdBarCancel,  51%x ,85%y, 44%x";
mostCurrent._panbarra.AddView((android.view.View)(mostCurrent._cmdbarcancel.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (51),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (44),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (14),mostCurrent.activityBA));
 //BA.debugLineNum = 716;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
int _newval = 0;
anywheresoftware.b4a.objects.collections.List _lst = null;
anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog _dialogo = null;
int _result = 0;
 //BA.debugLineNum = 262;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 263;BA.debugLine="Dim NewVal As Int";
_newval = 0;
 //BA.debugLineNum = 264;BA.debugLine="NewVal=-1";
_newval = (int) (-1);
 //BA.debugLineNum = 265;BA.debugLine="Select  ValoresComunes.Circuitos (Value).Rango";
switch (BA.switchObjectToInt(mostCurrent._valorescomunes._circuitos[(int)(BA.ObjectToNumber(_value))].Rango,(int) (1),(int) (3),(int) (7),(int) (8),(int) (13),(int) (19),(int) (24),(int) (39),(int) (15),(int) (25),(int) (43),(int) (51),(int) (57),(int) (58),(int) (44),(int) (2),(int) (45),(int) (4),(int) (5),(int) (14),(int) (52),(int) (53),(int) (54),(int) (55),(int) (56),(int) (29),(int) (30),(int) (31),(int) (34),(int) (35),(int) (36))) {
case 0: 
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
case 12: 
case 13: {
 //BA.debugLineNum = 268;BA.debugLine="If Valores(Value)=0 Then";
if (_valores[(int)(BA.ObjectToNumber(_value))]==0) { 
 //BA.debugLineNum = 269;BA.debugLine="NewVal=1";
_newval = (int) (1);
 }else {
 //BA.debugLineNum = 271;BA.debugLine="NewVal=0";
_newval = (int) (0);
 };
 break; }
case 14: {
 //BA.debugLineNum = 274;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 275;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 276;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg71\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg71")));
 //BA.debugLineNum = 277;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg72\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg72")));
 //BA.debugLineNum = 278;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg73\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg73")));
 //BA.debugLineNum = 281;BA.debugLine="NewVal =InputList(Lst,ValoresComunes.GetLanStri";
_newval = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),_valores[(int)(BA.ObjectToNumber(_value))],mostCurrent.activityBA);
 break; }
case 15: 
case 16: {
 //BA.debugLineNum = 283;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 284;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 285;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg71\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg71")));
 //BA.debugLineNum = 286;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg72\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg72")));
 //BA.debugLineNum = 287;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg73\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg73")));
 //BA.debugLineNum = 288;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg74\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg74")));
 //BA.debugLineNum = 289;BA.debugLine="NewVal =InputList(Lst,ValoresComunes.GetLanStri";
_newval = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),_valores[(int)(BA.ObjectToNumber(_value))],mostCurrent.activityBA);
 break; }
case 17: {
 //BA.debugLineNum = 291;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 292;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 293;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg71\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg71")));
 //BA.debugLineNum = 294;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg95\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg95")));
 //BA.debugLineNum = 295;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg96\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg96")));
 //BA.debugLineNum = 297;BA.debugLine="NewVal =InputList(Lst,ValoresComunes.GetLanStri";
_newval = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),(int) (0),mostCurrent.activityBA);
 //BA.debugLineNum = 299;BA.debugLine="If NewVal>0 Then";
if (_newval>0) { 
 //BA.debugLineNum = 300;BA.debugLine="If NewVal=1 Then";
if (_newval==1) { 
 //BA.debugLineNum = 301;BA.debugLine="NewVal=199";
_newval = (int) (199);
 }else {
 //BA.debugLineNum = 303;BA.debugLine="NewVal=GetRGBcolor";
_newval = _getrgbcolor();
 };
 };
 //BA.debugLineNum = 307;BA.debugLine="If NewVal<0 Then Return";
if (_newval<0) { 
if (true) return "";};
 break; }
case 18: {
 //BA.debugLineNum = 309;BA.debugLine="Barra_On(Value)";
_barra_on((byte)(BA.ObjectToNumber(_value)));
 //BA.debugLineNum = 310;BA.debugLine="Return";
if (true) return "";
 break; }
case 19: 
case 20: {
 //BA.debugLineNum = 314;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 315;BA.debugLine="Dialogo.Digits =3";
_dialogo.setDigits((int) (3));
 //BA.debugLineNum = 317;BA.debugLine="If Valores(Value)<241 Then";
if (_valores[(int)(BA.ObjectToNumber(_value))]<241) { 
 //BA.debugLineNum = 318;BA.debugLine="Dialogo.Number = Valores(Value)";
_dialogo.setNumber(_valores[(int)(BA.ObjectToNumber(_value))]);
 };
 //BA.debugLineNum = 321;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 322;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg84"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 324;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 325;BA.debugLine="If Dialogo.Number < 241 And Dialogo.Number >=0";
if (_dialogo.getNumber()<241 && _dialogo.getNumber()>=0) { 
 //BA.debugLineNum = 326;BA.debugLine="NewVal =Dialogo.Number";
_newval = _dialogo.getNumber();
 }else {
 //BA.debugLineNum = 328;BA.debugLine="Msgbox(ValoresComunes.GetLanString (\"reg85\"),";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg85")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg86")),mostCurrent.activityBA);
 //BA.debugLineNum = 329;BA.debugLine="Return";
if (true) return "";
 };
 };
 break; }
case 21: {
 //BA.debugLineNum = 335;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 336;BA.debugLine="Dialogo.Digits =3";
_dialogo.setDigits((int) (3));
 //BA.debugLineNum = 339;BA.debugLine="If Valores(Value)<101 Then";
if (_valores[(int)(BA.ObjectToNumber(_value))]<101) { 
 //BA.debugLineNum = 340;BA.debugLine="Dialogo.Number = Valores(Value)";
_dialogo.setNumber(_valores[(int)(BA.ObjectToNumber(_value))]);
 };
 //BA.debugLineNum = 343;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 344;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 346;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 347;BA.debugLine="If Dialogo.Number< 101 Then";
if (_dialogo.getNumber()<101) { 
 //BA.debugLineNum = 348;BA.debugLine="NewVal =Dialogo.Number";
_newval = _dialogo.getNumber();
 }else {
 //BA.debugLineNum = 350;BA.debugLine="Msgbox(\"Max 100\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max 100"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 351;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 354;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 22: {
 //BA.debugLineNum = 358;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 359;BA.debugLine="Dialogo.Digits =3";
_dialogo.setDigits((int) (3));
 //BA.debugLineNum = 360;BA.debugLine="If Valores(Value)<201 Then";
if (_valores[(int)(BA.ObjectToNumber(_value))]<201) { 
 //BA.debugLineNum = 361;BA.debugLine="Dialogo.Number =Valores(Value)' ValoresComunes";
_dialogo.setNumber(_valores[(int)(BA.ObjectToNumber(_value))]);
 };
 //BA.debugLineNum = 365;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 366;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 368;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 369;BA.debugLine="If Dialogo.Number< 201 Then";
if (_dialogo.getNumber()<201) { 
 //BA.debugLineNum = 370;BA.debugLine="NewVal =Dialogo.Number";
_newval = _dialogo.getNumber();
 }else {
 //BA.debugLineNum = 372;BA.debugLine="Msgbox(\"Max 200\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max 200"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 373;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 376;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 23: {
 //BA.debugLineNum = 379;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 380;BA.debugLine="Dialogo.Digits =4";
_dialogo.setDigits((int) (4));
 //BA.debugLineNum = 381;BA.debugLine="If Valores(Value)<2001 Then";
if (_valores[(int)(BA.ObjectToNumber(_value))]<2001) { 
 //BA.debugLineNum = 382;BA.debugLine="Dialogo.Number = Valores(Value)*10";
_dialogo.setNumber((int) (_valores[(int)(BA.ObjectToNumber(_value))]*10));
 };
 //BA.debugLineNum = 387;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 388;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 390;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 391;BA.debugLine="If Dialogo.Number< 201 Then";
if (_dialogo.getNumber()<201) { 
 //BA.debugLineNum = 392;BA.debugLine="NewVal =Dialogo.Number/10";
_newval = (int) (_dialogo.getNumber()/(double)10);
 }else {
 //BA.debugLineNum = 394;BA.debugLine="Msgbox(\"Max 2000\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max 2000"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 395;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 398;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 24: {
 //BA.debugLineNum = 401;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 402;BA.debugLine="Dialogo.Digits =5";
_dialogo.setDigits((int) (5));
 //BA.debugLineNum = 404;BA.debugLine="If Valores(Value)<201 Then";
if (_valores[(int)(BA.ObjectToNumber(_value))]<201) { 
 //BA.debugLineNum = 405;BA.debugLine="Dialogo.Number = Valores(Value)*100";
_dialogo.setNumber((int) (_valores[(int)(BA.ObjectToNumber(_value))]*100));
 };
 //BA.debugLineNum = 408;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 409;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 411;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 412;BA.debugLine="If Dialogo.Number< 20001 Then";
if (_dialogo.getNumber()<20001) { 
 //BA.debugLineNum = 413;BA.debugLine="NewVal =Dialogo.Number/100";
_newval = (int) (_dialogo.getNumber()/(double)100);
 }else {
 //BA.debugLineNum = 415;BA.debugLine="Msgbox(\"Max 20000\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max 20000"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 416;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 419;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 25: {
 //BA.debugLineNum = 426;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 427;BA.debugLine="Dialogo.Digits =3";
_dialogo.setDigits((int) (3));
 //BA.debugLineNum = 429;BA.debugLine="If Valores(Value)<241 Then";
if (_valores[(int)(BA.ObjectToNumber(_value))]<241) { 
 //BA.debugLineNum = 430;BA.debugLine="Dialogo.Number = Valores(Value)";
_dialogo.setNumber(_valores[(int)(BA.ObjectToNumber(_value))]);
 };
 //BA.debugLineNum = 434;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 435;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 437;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 438;BA.debugLine="If Dialogo.Number< 241 Then";
if (_dialogo.getNumber()<241) { 
 //BA.debugLineNum = 439;BA.debugLine="NewVal =Dialogo.Number";
_newval = _dialogo.getNumber();
 }else {
 //BA.debugLineNum = 441;BA.debugLine="Msgbox(\"Max 240\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max 240"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 442;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 445;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 26: {
 //BA.debugLineNum = 450;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 451;BA.debugLine="Dialogo.Digits =3";
_dialogo.setDigits((int) (3));
 //BA.debugLineNum = 452;BA.debugLine="Dialogo.Decimal =1";
_dialogo.setDecimal((int) (1));
 //BA.debugLineNum = 454;BA.debugLine="If Valores(Value)< 171 And Valores(Value)> 0 Th";
if (_valores[(int)(BA.ObjectToNumber(_value))]<171 && _valores[(int)(BA.ObjectToNumber(_value))]>0) { 
 //BA.debugLineNum = 455;BA.debugLine="Dialogo.Number = (Valores(Value) + 150)";
_dialogo.setNumber((int) ((_valores[(int)(BA.ObjectToNumber(_value))]+150)));
 }else {
 //BA.debugLineNum = 457;BA.debugLine="Dialogo.Number =220";
_dialogo.setNumber((int) (220));
 };
 //BA.debugLineNum = 462;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 463;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 465;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 467;BA.debugLine="If Dialogo.Number< 321 And Dialogo.number> 149";
if (_dialogo.getNumber()<321 && _dialogo.getNumber()>149) { 
 //BA.debugLineNum = 468;BA.debugLine="NewVal =(Dialogo.Number-150)";
_newval = (int) ((_dialogo.getNumber()-150));
 }else {
 //BA.debugLineNum = 470;BA.debugLine="Msgbox(\"Only 15º to 32º\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Only 15º to 32º"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 471;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 475;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 27: {
 //BA.debugLineNum = 479;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 480;BA.debugLine="Dialogo.Digits =4";
_dialogo.setDigits((int) (4));
 //BA.debugLineNum = 482;BA.debugLine="If Valores(Value)<241 Then";
if (_valores[(int)(BA.ObjectToNumber(_value))]<241) { 
 //BA.debugLineNum = 483;BA.debugLine="Dialogo.Number = Valores(Value)* -1";
_dialogo.setNumber((int) (_valores[(int)(BA.ObjectToNumber(_value))]*-1));
 }else {
 //BA.debugLineNum = 485;BA.debugLine="Dialogo.Number =-1";
_dialogo.setNumber((int) (-1));
 };
 //BA.debugLineNum = 488;BA.debugLine="Dialogo.ShowSign = True";
_dialogo.setShowSign(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 489;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 490;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 492;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 493;BA.debugLine="If Dialogo.Number < 1  And Dialogo.Number  > -";
if (_dialogo.getNumber()<1 && _dialogo.getNumber()>-241) { 
 //BA.debugLineNum = 494;BA.debugLine="NewVal =Dialogo.Number * -1";
_newval = (int) (_dialogo.getNumber()*-1);
 }else {
 //BA.debugLineNum = 496;BA.debugLine="Msgbox(\"Max -240\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max -240"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 497;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 500;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 28: {
 //BA.debugLineNum = 503;BA.debugLine="Barra_On(Value)";
_barra_on((byte)(BA.ObjectToNumber(_value)));
 //BA.debugLineNum = 504;BA.debugLine="Return";
if (true) return "";
 break; }
case 29: 
case 30: {
 //BA.debugLineNum = 507;BA.debugLine="If Valores(Value)=0 Then";
if (_valores[(int)(BA.ObjectToNumber(_value))]==0) { 
 //BA.debugLineNum = 508;BA.debugLine="NewVal=100";
_newval = (int) (100);
 }else {
 //BA.debugLineNum = 510;BA.debugLine="NewVal=0";
_newval = (int) (0);
 };
 break; }
}
;
 //BA.debugLineNum = 515;BA.debugLine="If NewVal < 0  Then 	Return";
if (_newval<0) { 
if (true) return "";};
 //BA.debugLineNum = 516;BA.debugLine="Valores(Value)=NewVal";
_valores[(int)(BA.ObjectToNumber(_value))] = _newval;
 //BA.debugLineNum = 517;BA.debugLine="ActualizaValores";
_actualizavalores();
 //BA.debugLineNum = 518;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemlongclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 557;BA.debugLine="Sub ListView1_ItemLongClick (Position As Int, Valu";
 //BA.debugLineNum = 559;BA.debugLine="Valores(Value)=250";
_valores[(int)(BA.ObjectToNumber(_value))] = (int) (250);
 //BA.debugLineNum = 560;BA.debugLine="ActualizaValores";
_actualizavalores();
 //BA.debugLineNum = 562;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub process_globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim UDPSocket1 As UDPSocket";
_udpsocket1 = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket();
 //BA.debugLineNum = 8;BA.debugLine="Dim Valores(30) As   Int";
_valores = new int[(int) (30)];
;
 //BA.debugLineNum = 9;BA.debugLine="Dim NumeroScene As Byte";
_numeroscene = (byte)0;
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public static boolean  _sendingtrama(byte[] _data) throws Exception{
anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet = null;
 //BA.debugLineNum = 646;BA.debugLine="Sub SendingTrama (data() As Byte) As Boolean";
 //BA.debugLineNum = 647;BA.debugLine="Try";
try { //BA.debugLineNum = 648;BA.debugLine="Dim Packet As UDPPacket";
_packet = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket();
 //BA.debugLineNum = 649;BA.debugLine="Packet.Initialize(data, ValoresComunes.ip, Valor";
_packet.Initialize(_data,mostCurrent._valorescomunes._ip,mostCurrent._valorescomunes._puerto);
 //BA.debugLineNum = 650;BA.debugLine="UDPSocket1.Send(Packet)";
_udpsocket1.Send(_packet);
 //BA.debugLineNum = 651;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 653;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 655;BA.debugLine="End Sub";
return false;
}
public static void  _sendtrama(byte[] _data) throws Exception{
ResumableSub_SendTrama rsub = new ResumableSub_SendTrama(null,_data);
rsub.resume(processBA, null);
}
public static class ResumableSub_SendTrama extends BA.ResumableSub {
public ResumableSub_SendTrama(arduino.automatizacion.excontrol.PRO.actconescena parent,byte[] _data) {
this.parent = parent;
this._data = _data;
}
arduino.automatizacion.excontrol.PRO.actconescena parent;
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
 //BA.debugLineNum = 600;BA.debugLine="Dim Resultado As Boolean";
_resultado = false;
 //BA.debugLineNum = 601;BA.debugLine="Dim Reintento As Int";
_reintento = 0;
 //BA.debugLineNum = 603;BA.debugLine="CmdGuardar.Enabled =False";
parent.mostCurrent._cmdguardar.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 604;BA.debugLine="ImgCargando.Visible =True";
parent.mostCurrent._imgcargando.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 605;BA.debugLine="AniCargando.Start (ImgCargando)";
parent.mostCurrent._anicargando.Start((android.view.View)(parent.mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 607;BA.debugLine="PaqueteEnviado =False";
parent._paqueteenviado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 608;BA.debugLine="TramaEnviada= data";
parent._tramaenviada = _data;
 //BA.debugLineNum = 610;BA.debugLine="Do 	While Resultado= False And Reintento < 40";
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
 //BA.debugLineNum = 611;BA.debugLine="Dim ServerSocket1 As ServerSocket";
_serversocket1 = new anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper();
 //BA.debugLineNum = 612;BA.debugLine="Dim MyIp As String";
_myip = "";
 //BA.debugLineNum = 613;BA.debugLine="If ActMosaico.Forzar3g Then";
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
 //BA.debugLineNum = 614;BA.debugLine="MyIp=ServerSocket1.GetMyIP";
_myip = _serversocket1.GetMyIP();
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 616;BA.debugLine="MyIp=ServerSocket1.GetMyWifiIP";
_myip = _serversocket1.GetMyWifiIP();
 if (true) break;
;
 //BA.debugLineNum = 618;BA.debugLine="If MyIp  <> \"127.0.0.1\" Then";

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
 //BA.debugLineNum = 619;BA.debugLine="Resultado = True";
_resultado = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 13:
//C
this.state = 14;
 //BA.debugLineNum = 621;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 622;BA.debugLine="Sleep (200)";
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
 //BA.debugLineNum = 627;BA.debugLine="If Resultado = True Then";

case 15:
//if
this.state = 28;
if (_resultado==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 17;
}if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 628;BA.debugLine="Resultado =False";
_resultado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 629;BA.debugLine="Reintento =0";
_reintento = (int) (0);
 //BA.debugLineNum = 630;BA.debugLine="Do 	While Resultado= False And Reintento < 40";
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
 //BA.debugLineNum = 631;BA.debugLine="Resultado = SendingTrama(data)";
_resultado = _sendingtrama(_data);
 //BA.debugLineNum = 632;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 633;BA.debugLine="If Resultado=False Then Sleep (200)";
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
 //BA.debugLineNum = 639;BA.debugLine="If Resultado = False  Then";

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
 //BA.debugLineNum = 640;BA.debugLine="ActMosaico.Conectado =False";
parent.mostCurrent._actmosaico._conectado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 641;BA.debugLine="StartActivity(ActMosaico)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._actmosaico.getObject()));
 if (true) break;

case 32:
//C
this.state = 33;
 //BA.debugLineNum = 643;BA.debugLine="PaqueteEnviado=True";
parent._paqueteenviado = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 33:
//C
this.state = -1;
;
 //BA.debugLineNum = 645;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _udp_packetarrived(anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet) throws Exception{
String _msg = "";
int _c = 0;
 //BA.debugLineNum = 563;BA.debugLine="Sub UDP_PacketArrived (Packet As UDPPacket)";
 //BA.debugLineNum = 564;BA.debugLine="Try";
try { //BA.debugLineNum = 565;BA.debugLine="Dim msg As String";
_msg = "";
 //BA.debugLineNum = 566;BA.debugLine="msg = BytesToString(Packet.Data, Packet.Offset,";
_msg = anywheresoftware.b4a.keywords.Common.BytesToString(_packet.getData(),_packet.getOffset(),_packet.getLength(),"UTF8");
 //BA.debugLineNum = 569;BA.debugLine="If msg.Contains (\"VESC\") Then";
if (_msg.contains("VESC")) { 
 //BA.debugLineNum = 570;BA.debugLine="Dim  c As Int";
_c = 0;
 //BA.debugLineNum = 571;BA.debugLine="For c = 5 To Packet.Length -1";
{
final int step6 = 1;
final int limit6 = (int) (_packet.getLength()-1);
_c = (int) (5) ;
for (;(step6 > 0 && _c <= limit6) || (step6 < 0 && _c >= limit6) ;_c = ((int)(0 + _c + step6))  ) {
 //BA.debugLineNum = 572;BA.debugLine="Valores(c - 5)= Packet.Data (c)-1";
_valores[(int) (_c-5)] = (int) (_packet.getData()[_c]-1);
 //BA.debugLineNum = 574;BA.debugLine="If Valores(c-5) < 0 Then";
if (_valores[(int) (_c-5)]<0) { 
 //BA.debugLineNum = 575;BA.debugLine="Valores(c-5)=Valores(c-5) +256";
_valores[(int) (_c-5)] = (int) (_valores[(int) (_c-5)]+256);
 };
 }
};
 //BA.debugLineNum = 579;BA.debugLine="ActualizaValores";
_actualizavalores();
 }else {
 //BA.debugLineNum = 582;BA.debugLine="If msg =\"REPETIRMSG\" Then";
if ((_msg).equals("REPETIRMSG")) { 
 //BA.debugLineNum = 583;BA.debugLine="SendTrama(TramaEnviada)";
_sendtrama(_tramaenviada);
 //BA.debugLineNum = 584;BA.debugLine="Return";
if (true) return "";
 }else {
 };
 };
 //BA.debugLineNum = 589;BA.debugLine="PaqueteEnviado = False";
_paqueteenviado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 590;BA.debugLine="CmdGuardar.Enabled =True";
mostCurrent._cmdguardar.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 591;BA.debugLine="AniCargando.Stop(ImgCargando)";
mostCurrent._anicargando.Stop((android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 592;BA.debugLine="ImgCargando.Visible =False";
mostCurrent._imgcargando.setVisible(anywheresoftware.b4a.keywords.Common.False);
 } 
       catch (Exception e25) {
			processBA.setLastException(e25); };
 //BA.debugLineNum = 597;BA.debugLine="End Sub";
return "";
}
}
