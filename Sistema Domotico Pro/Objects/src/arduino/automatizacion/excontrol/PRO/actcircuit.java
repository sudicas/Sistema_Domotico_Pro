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

public class actcircuit extends Activity implements B4AActivity{
	public static actcircuit mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actcircuit");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actcircuit).");
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
		activityBA = new BA(this, layout, processBA, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actcircuit");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "arduino.automatizacion.excontrol.PRO.actcircuit", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actcircuit) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actcircuit) Resume **");
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
		return actcircuit.class;
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
        BA.LogInfo("** Activity (actcircuit) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (actcircuit) Resume **");
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
public static anywheresoftware.b4a.objects.Timer _timer1 = null;
public static int _selectscene = 0;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public arduino.automatizacion.excontrol.PRO.slidemenu _sm = null;
public static byte[] _ultimatrama = null;
public static int _conttim = 0;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgcargando = null;
public anywheresoftware.b4a.objects.AnimationWrapper _anicargando = null;
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
public static String  _activaresperarespuesta() throws Exception{
 //BA.debugLineNum = 743;BA.debugLine="Sub ActivarEsperaRespuesta()";
 //BA.debugLineNum = 744;BA.debugLine="ImgCargando.Visible =True";
mostCurrent._imgcargando.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 745;BA.debugLine="AniCargando.Start (ImgCargando)";
mostCurrent._anicargando.Start((android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 747;BA.debugLine="ListView1.Enabled =False";
mostCurrent._listview1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 749;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
int _h = 0;
 //BA.debugLineNum = 100;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 104;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = Fals";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 108;BA.debugLine="If Main.MyLan.IsInitialized = False Then  Main.My";
if (mostCurrent._main._mylan.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._main._mylan.Initialize(processBA,(int) (0),"");};
 //BA.debugLineNum = 110;BA.debugLine="Activity.LoadLayout (\"FrmCircuitos\")";
mostCurrent._activity.LoadLayout("FrmCircuitos",mostCurrent.activityBA);
 //BA.debugLineNum = 114;BA.debugLine="Activity.AddMenuItem(ValoresComunes.GetLanString";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg130")),"mnuSaveScenes");
 //BA.debugLineNum = 130;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 132;BA.debugLine="Timer1.Initialize (\"Timer1\",100)";
_timer1.Initialize(processBA,"Timer1",(long) (100));
 };
 //BA.debugLineNum = 141;BA.debugLine="ListView1.height = 100%y - 52dip";
mostCurrent._listview1.setHeight((int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (52))));
 //BA.debugLineNum = 142;BA.debugLine="ListView1.Width =Activity.Width";
mostCurrent._listview1.setWidth(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 143;BA.debugLine="ListView1.Top =52dip";
mostCurrent._listview1.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (52)));
 //BA.debugLineNum = 147;BA.debugLine="Dim h As Int";
_h = 0;
 //BA.debugLineNum = 148;BA.debugLine="h=75dip";
_h = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75));
 //BA.debugLineNum = 151;BA.debugLine="ListView1.TwoLinesAndBitmap .ItemHeight =h";
mostCurrent._listview1.getTwoLinesAndBitmap().setItemHeight(_h);
 //BA.debugLineNum = 152;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.Width=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setWidth(_h);
 //BA.debugLineNum = 153;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.height=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setHeight(_h);
 //BA.debugLineNum = 155;BA.debugLine="ListView1.TwoLinesAndBitmap.Label .Left =h + h/9";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setLeft((int) (_h+_h/(double)9));
 //BA.debugLineNum = 156;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.height  =h/1.8";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setHeight((int) (_h/(double)1.8));
 //BA.debugLineNum = 157;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.Gravity = Bit.O";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.BOTTOM));
 //BA.debugLineNum = 159;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel .Left =Li";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setLeft(mostCurrent._listview1.getTwoLinesAndBitmap().Label.getLeft());
 //BA.debugLineNum = 160;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.height";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setHeight((int) (_h-mostCurrent._listview1.getTwoLinesAndBitmap().Label.getHeight()));
 //BA.debugLineNum = 161;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.top   = L";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setTop(mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.getHeight());
 //BA.debugLineNum = 162;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.Gravity =";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 168;BA.debugLine="ValoresComunes.LoadPaleta(dlg1  ,dlg2)";
mostCurrent._valorescomunes._loadpaleta(mostCurrent.activityBA,mostCurrent._dlg1,mostCurrent._dlg2);
 //BA.debugLineNum = 170;BA.debugLine="ImgCargando.Top = 100%y - ImgCargando.height";
mostCurrent._imgcargando.setTop((int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-mostCurrent._imgcargando.getHeight()));
 //BA.debugLineNum = 172;BA.debugLine="sm.Initialize(Activity, Me,  \"SlideMenu\",  42dip,";
mostCurrent._sm._initialize(mostCurrent.activityBA,mostCurrent._activity,actcircuit.getObject(),"SlideMenu",anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (42)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)));
 //BA.debugLineNum = 174;BA.debugLine="ValoresComunes.BuldMenus (sm,2)";
mostCurrent._valorescomunes._buldmenus(mostCurrent.activityBA,mostCurrent._sm,(int) (2));
 //BA.debugLineNum = 176;BA.debugLine="IniBarDialog";
_inibardialog();
 //BA.debugLineNum = 178;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 951;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 954;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK And sm.isVisib";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK && mostCurrent._sm._isvisible()) { 
 //BA.debugLineNum = 955;BA.debugLine="sm.Hide";
mostCurrent._sm._hide();
 //BA.debugLineNum = 956;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 960;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_MENU Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_MENU) { 
 //BA.debugLineNum = 961;BA.debugLine="If sm.isVisible Then sm.Hide Else sm.Show";
if (mostCurrent._sm._isvisible()) { 
mostCurrent._sm._hide();}
else {
mostCurrent._sm._show();};
 //BA.debugLineNum = 962;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 964;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 200;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 202;BA.debugLine="Timer1.Enabled =False";
_timer1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 203;BA.debugLine="UDPSocket1.Close";
_udpsocket1.Close();
 //BA.debugLineNum = 204;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 206;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
byte[] _data = null;
String _trama = "";
 //BA.debugLineNum = 207;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 210;BA.debugLine="If ValoresComunes.CloseApp =True Then";
if (mostCurrent._valorescomunes._closeapp==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 211;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 212;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 215;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = True";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 220;BA.debugLine="ImgCargando.Bitmap= ValoresComunes.Cargando";
mostCurrent._imgcargando.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._cargando(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 221;BA.debugLine="If Main.MyLan.IsInitialized = False Then  Main.M";
if (mostCurrent._main._mylan.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._main._mylan.Initialize(processBA,(int) (0),"");};
 //BA.debugLineNum = 223;BA.debugLine="If Timer1.Interval <> 100 Then Timer1.Initialize";
if (_timer1.getInterval()!=100) { 
_timer1.Initialize(processBA,"Timer1",(long) (100));};
 //BA.debugLineNum = 224;BA.debugLine="Timer1.Enabled = True";
_timer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 226;BA.debugLine="AniCargando.InitializeRotateCenter( \"AniCargando";
mostCurrent._anicargando.InitializeRotateCenter(mostCurrent.activityBA,"AniCargando",(float) (0),(float) (180),(android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 227;BA.debugLine="AniCargando.Duration = 1000";
mostCurrent._anicargando.setDuration((long) (1000));
 //BA.debugLineNum = 228;BA.debugLine="AniCargando.RepeatCount = -1";
mostCurrent._anicargando.setRepeatCount((int) (-1));
 //BA.debugLineNum = 229;BA.debugLine="AniCargando.RepeatMode = AniCargando.REPEAT_REVE";
mostCurrent._anicargando.setRepeatMode(mostCurrent._anicargando.REPEAT_REVERSE);
 //BA.debugLineNum = 231;BA.debugLine="If UDPSocket1.IsInitialized =False Then ValoresC";
if (_udpsocket1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._valorescomunes._iniudp(mostCurrent.activityBA,_udpsocket1);};
 //BA.debugLineNum = 233;BA.debugLine="ListView1.Visible=False";
mostCurrent._listview1.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 234;BA.debugLine="ActivarEsperaRespuesta";
_activaresperarespuesta();
 //BA.debugLineNum = 236;BA.debugLine="If ValoresComunes.Circuitos (0).Rango =1000 Then";
if (mostCurrent._valorescomunes._circuitos[(int) (0)].Rango==1000) { 
 //BA.debugLineNum = 237;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 238;BA.debugLine="Dim Trama As String";
_trama = "";
 //BA.debugLineNum = 239;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 240;BA.debugLine="Trama = \"CARG\" & ValoresComunes.Central.Passwo";
_trama = "CARG"+mostCurrent._valorescomunes._central.Password;
 }else {
 //BA.debugLineNum = 242;BA.debugLine="Trama = \"CARG\"";
_trama = "CARG";
 };
 //BA.debugLineNum = 245;BA.debugLine="data = Trama.GetBytes(\"UTF8\")";
_data = _trama.getBytes("UTF8");
 //BA.debugLineNum = 246;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 }else {
 //BA.debugLineNum = 250;BA.debugLine="If SelectScene =0 Then";
if (_selectscene==0) { 
 //BA.debugLineNum = 252;BA.debugLine="LecturaDatos";
_lecturadatos();
 }else {
 //BA.debugLineNum = 255;BA.debugLine="SeleccionarEscena(SelectScene-1)";
_seleccionarescena((int) (_selectscene-1));
 //BA.debugLineNum = 256;BA.debugLine="SelectScene=0";
_selectscene = (int) (0);
 };
 };
 }else {
 //BA.debugLineNum = 264;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 265;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 };
 //BA.debugLineNum = 267;BA.debugLine="End Sub";
return "";
}
public static String  _actualizavalores() throws Exception{
int _i = 0;
double _value = 0;
double _val1 = 0;
double _val2 = 0;
int _pos = 0;
String _l1 = "";
arduino.automatizacion.excontrol.PRO.valorescomunes._circuito _se = null;
String _unidad = "";
String _s = "";
 //BA.debugLineNum = 301;BA.debugLine="Sub ActualizaValores()";
 //BA.debugLineNum = 303;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 306;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 307;BA.debugLine="For i =0 To 29";
{
final int step3 = 1;
final int limit3 = (int) (29);
_i = (int) (0) ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
 //BA.debugLineNum = 313;BA.debugLine="If ValoresComunes.Circuitos (i).Nombre <> \"\" And";
if ((mostCurrent._valorescomunes._circuitos[_i].Nombre).equals("") == false && mostCurrent._valorescomunes._circuitos[_i].Rango>0 && mostCurrent._valorescomunes._circuitos[_i].Rango<1000) { 
 //BA.debugLineNum = 318;BA.debugLine="If ValoresComunes.Circuitos (i).Rango=29 Then '";
if (mostCurrent._valorescomunes._circuitos[_i].Rango==29) { 
 //BA.debugLineNum = 319;BA.debugLine="Dim Value  As Double";
_value = 0;
 //BA.debugLineNum = 320;BA.debugLine="Value=ValoresComunes.Sensores (  ValoresComune";
_value = mostCurrent._valorescomunes._sensores[mostCurrent._valorescomunes._circuitos[_i].DeviceNumber].Value/(double)10;
 //BA.debugLineNum = 321;BA.debugLine="ListView1.AddTwoLinesAndBitmap2  (ValoresComun";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString(mostCurrent._valorescomunes._circuitos[_i].Value)+"ºC"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._sensores[mostCurrent._valorescomunes._circuitos[_i].DeviceNumber].Nombre+" "+BA.NumberToString(_value)+"ºC"),(android.graphics.Bitmap)(mostCurrent._valorescomunes._temperatura(mostCurrent.activityBA).getObject()),(Object)(_i));
 }else if(mostCurrent._valorescomunes._circuitos[_i].Rango==30) { 
 //BA.debugLineNum = 324;BA.debugLine="Dim Val1  As Double";
_val1 = 0;
 //BA.debugLineNum = 325;BA.debugLine="Dim Val2  As Double";
_val2 = 0;
 //BA.debugLineNum = 328;BA.debugLine="Val1= (ValoresComunes.Circuitos (i).Value +150";
_val1 = (mostCurrent._valorescomunes._circuitos[_i].Value+150)/(double)10;
 //BA.debugLineNum = 329;BA.debugLine="Val2=ValoresComunes.Sensores (  ValoresComunes";
_val2 = mostCurrent._valorescomunes._sensores[mostCurrent._valorescomunes._circuitos[_i].DeviceNumber].Value/(double)10;
 //BA.debugLineNum = 330;BA.debugLine="ListView1.AddTwoLinesAndBitmap2  (ValoresComun";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString(_val1)+"ºC"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._sensores[mostCurrent._valorescomunes._circuitos[_i].DeviceNumber].Nombre+" "+BA.NumberToString(_val2)+"ºC"),(android.graphics.Bitmap)(mostCurrent._valorescomunes._temperatura(mostCurrent.activityBA).getObject()),(Object)(_i));
 }else if(mostCurrent._valorescomunes._circuitos[_i].Rango==31) { 
 //BA.debugLineNum = 333;BA.debugLine="Dim Value  As Double";
_value = 0;
 //BA.debugLineNum = 334;BA.debugLine="Value=ValoresComunes.Sensores (  ValoresComune";
_value = mostCurrent._valorescomunes._sensores[mostCurrent._valorescomunes._circuitos[_i].DeviceNumber].Value/(double)10;
 //BA.debugLineNum = 335;BA.debugLine="ListView1.AddTwoLinesAndBitmap2  (ValoresComun";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Nombre+" : -"+BA.NumberToString(mostCurrent._valorescomunes._circuitos[_i].Value)+"ºC"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._sensores[mostCurrent._valorescomunes._circuitos[_i].DeviceNumber].Nombre+" "+BA.NumberToString(_value)+"ºC"),(android.graphics.Bitmap)(mostCurrent._valorescomunes._temperatura(mostCurrent.activityBA).getObject()),(Object)(_i));
 }else if(mostCurrent._valorescomunes._circuitos[_i].Rango==33) { 
 //BA.debugLineNum = 338;BA.debugLine="Dim pos As Int";
_pos = 0;
 //BA.debugLineNum = 339;BA.debugLine="pos=ValoresComunes.Circuitos (i).DeviceNumber";
_pos = mostCurrent._valorescomunes._circuitos[_i].DeviceNumber;
 //BA.debugLineNum = 341;BA.debugLine="If pos<ValoresComunes.Sensores.Length  And pos";
if (_pos<mostCurrent._valorescomunes._sensores.length && _pos>-1) { 
 //BA.debugLineNum = 342;BA.debugLine="Dim Value  As Double";
_value = 0;
 //BA.debugLineNum = 344;BA.debugLine="If ValoresComunes.Sensores(pos).Rango = 1  Or";
if (mostCurrent._valorescomunes._sensores[_pos].Rango==1 || mostCurrent._valorescomunes._sensores[_pos].Rango==2 || mostCurrent._valorescomunes._sensores[_pos].Rango==5) { 
 //BA.debugLineNum = 345;BA.debugLine="Value=ValoresComunes.Sensores (  pos ).Value";
_value = mostCurrent._valorescomunes._sensores[_pos].Value/(double)10;
 }else {
 //BA.debugLineNum = 347;BA.debugLine="Value=ValoresComunes.Sensores (  pos ).Value";
_value = mostCurrent._valorescomunes._sensores[_pos].Value;
 };
 //BA.debugLineNum = 350;BA.debugLine="ListView1.AddTwoLinesAndBitmap2  (ValoresComu";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString(_value)+mostCurrent._valorescomunes._unidadsensor(mostCurrent.activityBA,_pos)),BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Descripcion),(android.graphics.Bitmap)(mostCurrent._valorescomunes._iconosensor(mostCurrent.activityBA,_pos).getObject()),(Object)(_i));
 };
 }else if(mostCurrent._valorescomunes._circuitos[_i].Rango==57 || mostCurrent._valorescomunes._circuitos[_i].Rango==58) { 
 //BA.debugLineNum = 357;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Nombre),BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Descripcion),(android.graphics.Bitmap)(mostCurrent._valorescomunes._iconocircuito(mostCurrent.activityBA,_i,mostCurrent._valorescomunes._circuitos[_i].Value).getObject()),(Object)(_i));
 }else if(mostCurrent._valorescomunes._circuitos[_i].Rango>52 && mostCurrent._valorescomunes._circuitos[_i].Rango<57) { 
 //BA.debugLineNum = 361;BA.debugLine="Dim l1 As String";
_l1 = "";
 //BA.debugLineNum = 362;BA.debugLine="Dim se As Circuito";
_se = new arduino.automatizacion.excontrol.PRO.valorescomunes._circuito();
 //BA.debugLineNum = 363;BA.debugLine="Dim unidad As String";
_unidad = "";
 //BA.debugLineNum = 365;BA.debugLine="If ValoresComunes.Circuitos(i).DeviceNumber <";
if (mostCurrent._valorescomunes._circuitos[_i].DeviceNumber<mostCurrent._valorescomunes._sensores.length) { 
 //BA.debugLineNum = 366;BA.debugLine="se=ValoresComunes.Sensores( ValoresComunes.Ci";
_se = mostCurrent._valorescomunes._sensores[mostCurrent._valorescomunes._circuitos[_i].DeviceNumber];
 //BA.debugLineNum = 369;BA.debugLine="unidad=ValoresComunes.UnidadSensor(ValoresCom";
_unidad = mostCurrent._valorescomunes._unidadsensor(mostCurrent.activityBA,mostCurrent._valorescomunes._circuitos[_i].DeviceNumber);
 //BA.debugLineNum = 373;BA.debugLine="Select Case ValoresComunes.Circuitos (i).Rang";
switch (BA.switchObjectToInt(mostCurrent._valorescomunes._circuitos[_i].Rango,(int) (53),(int) (54),(int) (55),(int) (56),(int) (57))) {
case 0: {
 //BA.debugLineNum = 376;BA.debugLine="l1=ValoresComunes.Circuitos (i).Nombre  & \"";
_l1 = mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString(mostCurrent._valorescomunes._circuitos[_i].Value)+_unidad;
 break; }
case 1: {
 //BA.debugLineNum = 378;BA.debugLine="l1=ValoresComunes.Circuitos (i).Nombre  & \"";
_l1 = mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString(mostCurrent._valorescomunes._circuitos[_i].Value)+_unidad;
 break; }
case 2: {
 //BA.debugLineNum = 381;BA.debugLine="l1=ValoresComunes.Circuitos (i).Nombre  & \"";
_l1 = mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString((mostCurrent._valorescomunes._circuitos[_i].Value*10))+_unidad;
 break; }
case 3: {
 //BA.debugLineNum = 383;BA.debugLine="l1=ValoresComunes.Circuitos (i).Nombre  & \"";
_l1 = mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString((mostCurrent._valorescomunes._circuitos[_i].Value*100))+_unidad;
 break; }
case 4: {
 //BA.debugLineNum = 385;BA.debugLine="l1=ValoresComunes.Circuitos (i).Nombre  & \"";
_l1 = mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString((mostCurrent._valorescomunes._circuitos[_i].Value*1000))+_unidad;
 break; }
}
;
 //BA.debugLineNum = 387;BA.debugLine="Dim Value  As Double";
_value = 0;
 //BA.debugLineNum = 390;BA.debugLine="If se.Rango = 1  Or se.Rango =2   Or se.Rango";
if (_se.Rango==1 || _se.Rango==2 || _se.Rango==5) { 
 //BA.debugLineNum = 391;BA.debugLine="Value=se.Value /10";
_value = _se.Value/(double)10;
 }else {
 //BA.debugLineNum = 393;BA.debugLine="Value=se.Value";
_value = _se.Value;
 };
 //BA.debugLineNum = 397;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (l1 ,se.Nombr";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_l1),BA.ObjectToCharSequence(_se.Nombre+" : "+BA.NumberToString(_value)+_unidad),(android.graphics.Bitmap)(mostCurrent._valorescomunes._iconosensor(mostCurrent.activityBA,mostCurrent._valorescomunes._circuitos[_i].DeviceNumber).getObject()),(Object)(_i));
 }else {
 //BA.debugLineNum = 399;BA.debugLine="Select Case ValoresComunes.Circuitos (i).Rang";
switch (BA.switchObjectToInt(mostCurrent._valorescomunes._circuitos[_i].Rango,(int) (53),(int) (54),(int) (55),(int) (56),(int) (57))) {
case 0: {
 //BA.debugLineNum = 402;BA.debugLine="l1=ValoresComunes.Circuitos (i).Nombre  & \"";
_l1 = mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString(mostCurrent._valorescomunes._circuitos[_i].Value);
 break; }
case 1: {
 //BA.debugLineNum = 404;BA.debugLine="l1=ValoresComunes.Circuitos (i).Nombre  & \"";
_l1 = mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString(mostCurrent._valorescomunes._circuitos[_i].Value);
 break; }
case 2: {
 //BA.debugLineNum = 407;BA.debugLine="l1=ValoresComunes.Circuitos (i).Nombre  & \"";
_l1 = mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString((mostCurrent._valorescomunes._circuitos[_i].Value*10));
 break; }
case 3: {
 //BA.debugLineNum = 409;BA.debugLine="l1=ValoresComunes.Circuitos (i).Nombre  & \"";
_l1 = mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString((mostCurrent._valorescomunes._circuitos[_i].Value*100));
 break; }
case 4: {
 //BA.debugLineNum = 411;BA.debugLine="l1=ValoresComunes.Circuitos (i).Nombre  & \"";
_l1 = mostCurrent._valorescomunes._circuitos[_i].Nombre+" : "+BA.NumberToString((mostCurrent._valorescomunes._circuitos[_i].Value*1000));
 break; }
}
;
 //BA.debugLineNum = 413;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (l1,ValoresCo";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(_l1),BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Descripcion),(android.graphics.Bitmap)(mostCurrent._valorescomunes._sensorgenerico(mostCurrent.activityBA).getObject()),(Object)(_i));
 };
 }else if(mostCurrent._valorescomunes._circuitos[_i].Rango==34 || mostCurrent._valorescomunes._circuitos[_i].Rango==36) { 
 //BA.debugLineNum = 424;BA.debugLine="ListView1.AddTwoLinesAndBitmap2  (ValoresComun";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Nombre+" - "+BA.NumberToString(mostCurrent._valorescomunes._circuitos[_i].Value)+"%"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Descripcion),(android.graphics.Bitmap)(mostCurrent._valorescomunes._persiana(mostCurrent.activityBA).getObject()),(Object)(_i));
 }else {
 //BA.debugLineNum = 427;BA.debugLine="Dim s As String";
_s = "";
 //BA.debugLineNum = 428;BA.debugLine="If ValoresComunes.Circuitos (i).Value=250 Then";
if (mostCurrent._valorescomunes._circuitos[_i].Value==250) { 
 //BA.debugLineNum = 429;BA.debugLine="s=ValoresComunes.GetLanString (\"reg140\")";
_s = mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg140");
 }else {
 //BA.debugLineNum = 431;BA.debugLine="s= ValoresComunes.Circuitos (i).Descripcion";
_s = mostCurrent._valorescomunes._circuitos[_i].Descripcion;
 };
 //BA.debugLineNum = 433;BA.debugLine="ListView1.AddTwoLinesAndBitmap2 (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_i].Nombre),BA.ObjectToCharSequence(_s),(android.graphics.Bitmap)(mostCurrent._valorescomunes._iconocircuito(mostCurrent.activityBA,_i,mostCurrent._valorescomunes._circuitos[_i].Value).getObject()),(Object)(_i));
 };
 };
 }
};
 //BA.debugLineNum = 441;BA.debugLine="End Sub";
return "";
}
public static String  _barra_on(byte _pos) throws Exception{
 //BA.debugLineNum = 1049;BA.debugLine="Sub Barra_On(Pos As Byte)";
 //BA.debugLineNum = 1050;BA.debugLine="ListView1.Enabled  =False";
mostCurrent._listview1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1051;BA.debugLine="BarCir=Pos";
_barcir = _pos;
 //BA.debugLineNum = 1052;BA.debugLine="Barra.Value = ValoresComunes.Circuitos(Pos).Value";
mostCurrent._barra._setvalue(mostCurrent._valorescomunes._circuitos[(int) (_pos)].Value);
 //BA.debugLineNum = 1053;BA.debugLine="Activity.AddView (PanBarra,0,0, Activity.Width ,A";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._panbarra.getObject()),(int) (0),(int) (0),mostCurrent._activity.getWidth(),mostCurrent._activity.getHeight());
 //BA.debugLineNum = 1055;BA.debugLine="End Sub";
return "";
}
public static String  _btnshow_click() throws Exception{
 //BA.debugLineNum = 1045;BA.debugLine="Sub btnShow_Click";
 //BA.debugLineNum = 1046;BA.debugLine="sm.Show";
mostCurrent._sm._show();
 //BA.debugLineNum = 1047;BA.debugLine="End Sub";
return "";
}
public static String  _cmdbarcancel_click() throws Exception{
 //BA.debugLineNum = 1096;BA.debugLine="Sub CmdBarCancel_Click";
 //BA.debugLineNum = 1097;BA.debugLine="ListView1.Enabled =True";
mostCurrent._listview1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1098;BA.debugLine="PanBarra.RemoveView";
mostCurrent._panbarra.RemoveView();
 //BA.debugLineNum = 1099;BA.debugLine="End Sub";
return "";
}
public static String  _cmdbarok_click() throws Exception{
int _longtrama = 0;
byte[] _data = null;
 //BA.debugLineNum = 1073;BA.debugLine="Sub CmdBarOk_Click";
 //BA.debugLineNum = 1074;BA.debugLine="PanBarra.RemoveView";
mostCurrent._panbarra.RemoveView();
 //BA.debugLineNum = 1075;BA.debugLine="ListView1.Enabled =True";
mostCurrent._listview1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1077;BA.debugLine="Dim LongTrama As Int";
_longtrama = 0;
 //BA.debugLineNum = 1078;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 1079;BA.debugLine="LongTrama= 14";
_longtrama = (int) (14);
 }else {
 //BA.debugLineNum = 1081;BA.debugLine="LongTrama= 6";
_longtrama = (int) (6);
 };
 //BA.debugLineNum = 1083;BA.debugLine="Dim data(LongTrama) As Byte";
_data = new byte[_longtrama];
;
 //BA.debugLineNum = 1084;BA.debugLine="data(0)=83";
_data[(int) (0)] = (byte) (83);
 //BA.debugLineNum = 1085;BA.debugLine="data(1)=86";
_data[(int) (1)] = (byte) (86);
 //BA.debugLineNum = 1086;BA.debugLine="data(2)=65";
_data[(int) (2)] = (byte) (65);
 //BA.debugLineNum = 1087;BA.debugLine="data(3)=76";
_data[(int) (3)] = (byte) (76);
 //BA.debugLineNum = 1088;BA.debugLine="data(4)= BarCir +1";
_data[(int) (4)] = (byte) (_barcir+1);
 //BA.debugLineNum = 1089;BA.debugLine="data (5)=Barra.currentValue   +1";
_data[(int) (5)] = (byte) (mostCurrent._barra._currentvalue+1);
 //BA.debugLineNum = 1091;BA.debugLine="ActivarEsperaRespuesta";
_activaresperarespuesta();
 //BA.debugLineNum = 1092;BA.debugLine="If ValoresComunes.central.ConexionSegura Then Val";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
mostCurrent._valorescomunes._completartrama(mostCurrent.activityBA,_data);};
 //BA.debugLineNum = 1093;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 //BA.debugLineNum = 1095;BA.debugLine="End Sub";
return "";
}
public static String  _desactivarespera() throws Exception{
 //BA.debugLineNum = 893;BA.debugLine="Sub desactivarEspera()";
 //BA.debugLineNum = 894;BA.debugLine="AniCargando.Stop(ImgCargando)";
mostCurrent._anicargando.Stop((android.view.View)(mostCurrent._imgcargando.getObject()));
 //BA.debugLineNum = 895;BA.debugLine="ImgCargando.Visible =False";
mostCurrent._imgcargando.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 896;BA.debugLine="ListView1.Enabled =True";
mostCurrent._listview1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 897;BA.debugLine="If ListView1.Visible=False Then ListView1.Visible";
if (mostCurrent._listview1.getVisible()==anywheresoftware.b4a.keywords.Common.False) { 
mostCurrent._listview1.setVisible(anywheresoftware.b4a.keywords.Common.True);};
 //BA.debugLineNum = 898;BA.debugLine="ActualizaValores";
_actualizavalores();
 //BA.debugLineNum = 899;BA.debugLine="End Sub";
return "";
}
public static int  _getrgbcolor() throws Exception{
int _res = 0;
int _v = 0;
int _b = 0;
 //BA.debugLineNum = 447;BA.debugLine="Sub GetRGBcolor() As Int";
 //BA.debugLineNum = 448;BA.debugLine="Dim res As Int";
_res = 0;
 //BA.debugLineNum = 449;BA.debugLine="res=DialogResponse.CANCEL";
_res = anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL;
 //BA.debugLineNum = 451;BA.debugLine="Do While res=DialogResponse.CANCEL";
while (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL) {
 //BA.debugLineNum = 452;BA.debugLine="dlg1.RGB=8388736";
mostCurrent._dlg1.setRGB((int) (8388736));
 //BA.debugLineNum = 453;BA.debugLine="res = dlg1.Show(ValoresComunes.GetLanString (\"re";
_res = mostCurrent._dlg1.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg96"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Ok"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg94"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 454;BA.debugLine="If res=DialogResponse.POSITIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 455;BA.debugLine="Dim v As Int";
_v = 0;
 //BA.debugLineNum = 456;BA.debugLine="v= dlg1.RGB";
_v = mostCurrent._dlg1.getRGB();
 //BA.debugLineNum = 457;BA.debugLine="Dim B As Int";
_b = 0;
 //BA.debugLineNum = 458;BA.debugLine="For B=0 To 14";
{
final int step10 = 1;
final int limit10 = (int) (14);
_b = (int) (0) ;
for (;(step10 > 0 && _b <= limit10) || (step10 < 0 && _b >= limit10) ;_b = ((int)(0 + _b + step10))  ) {
 //BA.debugLineNum = 459;BA.debugLine="If v = dlg1.GetPaletteAt(B) Then	Return B+1";
if (_v==mostCurrent._dlg1.GetPaletteAt(_b)) { 
if (true) return (int) (_b+1);};
 }
};
 }else if(_res==anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL) { 
 //BA.debugLineNum = 463;BA.debugLine="dlg2.RGB=14423100";
mostCurrent._dlg2.setRGB((int) (14423100));
 //BA.debugLineNum = 464;BA.debugLine="res = dlg2.Show(ValoresComunes.GetLanString (\"r";
_res = mostCurrent._dlg2.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg96"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Ok"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg94"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 466;BA.debugLine="If res=DialogResponse.POSITIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 467;BA.debugLine="Dim v As Int";
_v = 0;
 //BA.debugLineNum = 468;BA.debugLine="v= dlg2.RGB";
_v = mostCurrent._dlg2.getRGB();
 //BA.debugLineNum = 469;BA.debugLine="Dim B As Int";
_b = 0;
 //BA.debugLineNum = 470;BA.debugLine="For B=0 To 14";
{
final int step20 = 1;
final int limit20 = (int) (14);
_b = (int) (0) ;
for (;(step20 > 0 && _b <= limit20) || (step20 < 0 && _b >= limit20) ;_b = ((int)(0 + _b + step20))  ) {
 //BA.debugLineNum = 471;BA.debugLine="If v = dlg2.GetPaletteAt(B) Then	Return B+16";
if (_v==mostCurrent._dlg2.GetPaletteAt(_b)) { 
if (true) return (int) (_b+16);};
 }
};
 }else if(_res!=anywheresoftware.b4a.keywords.Common.DialogResponse.CANCEL) { 
 //BA.debugLineNum = 475;BA.debugLine="Return -1";
if (true) return (int) (-1);
 };
 }else {
 //BA.debugLineNum = 480;BA.debugLine="Return -1";
if (true) return (int) (-1);
 };
 }
;
 //BA.debugLineNum = 483;BA.debugLine="Return -1";
if (true) return (int) (-1);
 //BA.debugLineNum = 484;BA.debugLine="End Sub";
return 0;
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 14;BA.debugLine="Dim ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Dim sm As SlideMenu";
mostCurrent._sm = new arduino.automatizacion.excontrol.PRO.slidemenu();
 //BA.debugLineNum = 19;BA.debugLine="Dim UltimaTrama() As Byte";
_ultimatrama = new byte[(int) (0)];
;
 //BA.debugLineNum = 20;BA.debugLine="Dim ContTim As Int";
_conttim = 0;
 //BA.debugLineNum = 21;BA.debugLine="Dim ImgCargando As ImageView";
mostCurrent._imgcargando = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Dim AniCargando As  Animation";
mostCurrent._anicargando = new anywheresoftware.b4a.objects.AnimationWrapper();
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
 //BA.debugLineNum = 1056;BA.debugLine="Sub IniBarDialog(  )";
 //BA.debugLineNum = 1057;BA.debugLine="CmdBarOk.Initialize(\"CmdBarOk\")";
mostCurrent._cmdbarok.Initialize(mostCurrent.activityBA,"CmdBarOk");
 //BA.debugLineNum = 1058;BA.debugLine="CmdBarCancel.Initialize (\"CmdBarCancel\")";
mostCurrent._cmdbarcancel.Initialize(mostCurrent.activityBA,"CmdBarCancel");
 //BA.debugLineNum = 1061;BA.debugLine="CmdBarOk.Text =ValoresComunes.GetLanString (\"reg8";
mostCurrent._cmdbarok.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83")));
 //BA.debugLineNum = 1062;BA.debugLine="CmdBarCancel.Text =ValoresComunes.GetLanString (\"";
mostCurrent._cmdbarcancel.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel")));
 //BA.debugLineNum = 1063;BA.debugLine="PanBarra.Initialize(\"PanBarra\")";
mostCurrent._panbarra.Initialize(mostCurrent.activityBA,"PanBarra");
 //BA.debugLineNum = 1064;BA.debugLine="PanBarra.Color =  Colors.Black";
mostCurrent._panbarra.setColor(anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 1065;BA.debugLine="Barra.Initialize( PanBarra,  Me,\"bar_Click\",  50%";
mostCurrent._barra._initialize(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ActivityWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ActivityWrapper(), (anywheresoftware.b4a.BALayout)(mostCurrent._panbarra.getObject())),actcircuit.getObject(),"bar_Click",(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (50),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (36))),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (72)),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (75),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (33)),anywheresoftware.b4a.keywords.Common.Colors.DarkGray,anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0x00),(int) (0x99),(int) (0xcc)),anywheresoftware.b4a.keywords.Common.True,(int) (70),(int) (100),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 1066;BA.debugLine="Barra.CustomizeText(Colors.RGB(0x33,0xb5,0xe5),22";
mostCurrent._barra._customizetext(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0x33),(int) (0xb5),(int) (0xe5)),(int) (22),(anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD)));
 //BA.debugLineNum = 1067;BA.debugLine="Barra.stepValue =1";
mostCurrent._barra._stepvalue = (int) (1);
 //BA.debugLineNum = 1069;BA.debugLine="PanBarra.AddView (CmdBarOk, 5%x,85%y,44%x,14%y)";
mostCurrent._panbarra.AddView((android.view.View)(mostCurrent._cmdbarok.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (44),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (14),mostCurrent.activityBA));
 //BA.debugLineNum = 1070;BA.debugLine="PanBarra.AddView (CmdBarCancel,  51%x ,85%y, 44%x";
mostCurrent._panbarra.AddView((android.view.View)(mostCurrent._cmdbarcancel.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (51),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (44),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (14),mostCurrent.activityBA));
 //BA.debugLineNum = 1072;BA.debugLine="End Sub";
return "";
}
public static String  _lecturadatos() throws Exception{
byte[] _data = null;
String _trama = "";
 //BA.debugLineNum = 268;BA.debugLine="Sub LecturaDatos()";
 //BA.debugLineNum = 270;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 271;BA.debugLine="Dim Trama As String";
_trama = "";
 //BA.debugLineNum = 272;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 273;BA.debugLine="Trama = \"VACT\" & ValoresComunes.Central.Password";
_trama = "VACT"+mostCurrent._valorescomunes._central.Password;
 }else {
 //BA.debugLineNum = 275;BA.debugLine="Trama = \"VACT\"";
_trama = "VACT";
 };
 //BA.debugLineNum = 278;BA.debugLine="data = Trama.GetBytes(\"UTF8\")";
_data = _trama.getBytes("UTF8");
 //BA.debugLineNum = 281;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 //BA.debugLineNum = 282;BA.debugLine="End Sub";
return "";
}
public static String  _lecturasensores() throws Exception{
byte[] _data = null;
String _trama = "";
 //BA.debugLineNum = 283;BA.debugLine="Sub LecturaSensores()";
 //BA.debugLineNum = 286;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 287;BA.debugLine="Dim Trama As String";
_trama = "";
 //BA.debugLineNum = 288;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 289;BA.debugLine="Trama = \"EESE\" & ValoresComunes.Central.Password";
_trama = "EESE"+mostCurrent._valorescomunes._central.Password;
 }else {
 //BA.debugLineNum = 291;BA.debugLine="Trama = \"EESE\"";
_trama = "EESE";
 };
 //BA.debugLineNum = 294;BA.debugLine="data = Trama.GetBytes(\"UTF8\")";
_data = _trama.getBytes("UTF8");
 //BA.debugLineNum = 297;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 //BA.debugLineNum = 299;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
int _newval = 0;
anywheresoftware.b4a.objects.collections.List _lst = null;
anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog _dialogo = null;
int _result = 0;
int _longtrama = 0;
byte[] _data = null;
 //BA.debugLineNum = 485;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 488;BA.debugLine="Dim NewVal As Int";
_newval = 0;
 //BA.debugLineNum = 489;BA.debugLine="NewVal=-1";
_newval = (int) (-1);
 //BA.debugLineNum = 491;BA.debugLine="Select  ValoresComunes.Circuitos (Value).Rango";
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
 //BA.debugLineNum = 493;BA.debugLine="If ValoresComunes.Circuitos(Value).Value =0 The";
if (mostCurrent._valorescomunes._circuitos[(int)(BA.ObjectToNumber(_value))].Value==0) { 
 //BA.debugLineNum = 494;BA.debugLine="NewVal=1";
_newval = (int) (1);
 }else {
 //BA.debugLineNum = 496;BA.debugLine="NewVal=0";
_newval = (int) (0);
 };
 break; }
case 14: {
 //BA.debugLineNum = 499;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 500;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 501;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg71\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg71")));
 //BA.debugLineNum = 502;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg72\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg72")));
 //BA.debugLineNum = 503;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg73\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg73")));
 //BA.debugLineNum = 506;BA.debugLine="NewVal =InputList(Lst,ValoresComunes.GetLanStri";
_newval = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),mostCurrent._valorescomunes._circuitos[(int)(BA.ObjectToNumber(_value))].Value,mostCurrent.activityBA);
 break; }
case 15: 
case 16: {
 //BA.debugLineNum = 508;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 509;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 510;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg71\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg71")));
 //BA.debugLineNum = 511;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg72\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg72")));
 //BA.debugLineNum = 512;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg73\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg73")));
 //BA.debugLineNum = 513;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg74\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg74")));
 //BA.debugLineNum = 515;BA.debugLine="NewVal =InputList(Lst,ValoresComunes.GetLanStri";
_newval = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),mostCurrent._valorescomunes._circuitos[(int)(BA.ObjectToNumber(_value))].Value,mostCurrent.activityBA);
 break; }
case 17: {
 //BA.debugLineNum = 518;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 519;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 520;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg71\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg71")));
 //BA.debugLineNum = 521;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg95\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg95")));
 //BA.debugLineNum = 522;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg96\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg96")));
 //BA.debugLineNum = 524;BA.debugLine="NewVal =InputList(Lst,ValoresComunes.GetLanStri";
_newval = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),(int) (0),mostCurrent.activityBA);
 //BA.debugLineNum = 526;BA.debugLine="If NewVal>0 Then";
if (_newval>0) { 
 //BA.debugLineNum = 527;BA.debugLine="If NewVal=1 Then";
if (_newval==1) { 
 //BA.debugLineNum = 528;BA.debugLine="NewVal=199";
_newval = (int) (199);
 }else {
 //BA.debugLineNum = 530;BA.debugLine="NewVal=GetRGBcolor";
_newval = _getrgbcolor();
 };
 };
 //BA.debugLineNum = 534;BA.debugLine="If NewVal<0 Then Return";
if (_newval<0) { 
if (true) return "";};
 break; }
case 18: {
 //BA.debugLineNum = 538;BA.debugLine="Barra_On(Value)";
_barra_on((byte)(BA.ObjectToNumber(_value)));
 //BA.debugLineNum = 539;BA.debugLine="Return";
if (true) return "";
 break; }
case 19: 
case 20: {
 //BA.debugLineNum = 541;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 542;BA.debugLine="Dialogo.Digits =3";
_dialogo.setDigits((int) (3));
 //BA.debugLineNum = 545;BA.debugLine="Dialogo.Number = ValoresComunes.Circuitos(Value";
_dialogo.setNumber(mostCurrent._valorescomunes._circuitos[(int)(BA.ObjectToNumber(_value))].Value);
 //BA.debugLineNum = 547;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 548;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg84"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 550;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 551;BA.debugLine="If Dialogo.Number < 241 And Dialogo.Number >=0";
if (_dialogo.getNumber()<241 && _dialogo.getNumber()>=0) { 
 //BA.debugLineNum = 552;BA.debugLine="NewVal =Dialogo.Number";
_newval = _dialogo.getNumber();
 }else {
 //BA.debugLineNum = 554;BA.debugLine="Msgbox(ValoresComunes.GetLanString (\"reg85\"),";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg85")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg86")),mostCurrent.activityBA);
 //BA.debugLineNum = 555;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 558;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 21: {
 //BA.debugLineNum = 562;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 563;BA.debugLine="Dialogo.Digits =3";
_dialogo.setDigits((int) (3));
 //BA.debugLineNum = 565;BA.debugLine="Dialogo.Number = ValoresComunes.Circuitos(Value";
_dialogo.setNumber(mostCurrent._valorescomunes._circuitos[(int)(BA.ObjectToNumber(_value))].Value);
 //BA.debugLineNum = 567;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 568;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 570;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 571;BA.debugLine="If Dialogo.Number< 101 Then";
if (_dialogo.getNumber()<101) { 
 //BA.debugLineNum = 572;BA.debugLine="NewVal =Dialogo.Number";
_newval = _dialogo.getNumber();
 }else {
 //BA.debugLineNum = 574;BA.debugLine="Msgbox(\"Max 100\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max 100"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 575;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 578;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 22: {
 //BA.debugLineNum = 582;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 583;BA.debugLine="Dialogo.Digits =3";
_dialogo.setDigits((int) (3));
 //BA.debugLineNum = 585;BA.debugLine="Dialogo.Number = ValoresComunes.Circuitos(Value";
_dialogo.setNumber(mostCurrent._valorescomunes._circuitos[(int)(BA.ObjectToNumber(_value))].Value);
 //BA.debugLineNum = 587;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 588;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 590;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 591;BA.debugLine="If Dialogo.Number< 201 Then";
if (_dialogo.getNumber()<201) { 
 //BA.debugLineNum = 592;BA.debugLine="NewVal =Dialogo.Number";
_newval = _dialogo.getNumber();
 }else {
 //BA.debugLineNum = 594;BA.debugLine="Msgbox(\"Max 200\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max 200"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 595;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 598;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 23: {
 //BA.debugLineNum = 601;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 602;BA.debugLine="Dialogo.Digits =4";
_dialogo.setDigits((int) (4));
 //BA.debugLineNum = 604;BA.debugLine="Dialogo.Number = ValoresComunes.Circuitos(Value";
_dialogo.setNumber((int) (mostCurrent._valorescomunes._circuitos[(int)(BA.ObjectToNumber(_value))].Value*10));
 //BA.debugLineNum = 606;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 607;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 609;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 610;BA.debugLine="If Dialogo.Number< 2001 Then";
if (_dialogo.getNumber()<2001) { 
 //BA.debugLineNum = 611;BA.debugLine="NewVal =Dialogo.Number/10";
_newval = (int) (_dialogo.getNumber()/(double)10);
 }else {
 //BA.debugLineNum = 613;BA.debugLine="Msgbox(\"Max 2000\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max 2000"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 614;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 617;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 24: {
 //BA.debugLineNum = 620;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 621;BA.debugLine="Dialogo.Digits =5";
_dialogo.setDigits((int) (5));
 //BA.debugLineNum = 623;BA.debugLine="Dialogo.Number = ValoresComunes.Circuitos(Value";
_dialogo.setNumber((int) (mostCurrent._valorescomunes._circuitos[(int)(BA.ObjectToNumber(_value))].Value*100));
 //BA.debugLineNum = 625;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 626;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 628;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 629;BA.debugLine="If Dialogo.Number< 20001 Then";
if (_dialogo.getNumber()<20001) { 
 //BA.debugLineNum = 630;BA.debugLine="NewVal =Dialogo.Number/100";
_newval = (int) (_dialogo.getNumber()/(double)100);
 }else {
 //BA.debugLineNum = 632;BA.debugLine="Msgbox(\"Max 20000\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max 20000"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 633;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 636;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 25: {
 //BA.debugLineNum = 639;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 640;BA.debugLine="Dialogo.Digits =3";
_dialogo.setDigits((int) (3));
 //BA.debugLineNum = 642;BA.debugLine="Dialogo.Number = ValoresComunes.Circuitos(Value";
_dialogo.setNumber(mostCurrent._valorescomunes._circuitos[(int)(BA.ObjectToNumber(_value))].Value);
 //BA.debugLineNum = 644;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 645;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 647;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 648;BA.debugLine="If Dialogo.Number< 241 Then";
if (_dialogo.getNumber()<241) { 
 //BA.debugLineNum = 649;BA.debugLine="NewVal =Dialogo.Number";
_newval = _dialogo.getNumber();
 }else {
 //BA.debugLineNum = 651;BA.debugLine="Msgbox(\"Max 240\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max 240"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 652;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 655;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 26: {
 //BA.debugLineNum = 658;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 659;BA.debugLine="Dialogo.Digits =3";
_dialogo.setDigits((int) (3));
 //BA.debugLineNum = 660;BA.debugLine="Dialogo.Decimal =1";
_dialogo.setDecimal((int) (1));
 //BA.debugLineNum = 662;BA.debugLine="Dialogo.Number = (ValoresComunes.Circuitos(Valu";
_dialogo.setNumber((int) ((mostCurrent._valorescomunes._circuitos[(int)(BA.ObjectToNumber(_value))].Value+150)));
 //BA.debugLineNum = 664;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 665;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 667;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 669;BA.debugLine="If Dialogo.Number< 321 And Dialogo.number> 149";
if (_dialogo.getNumber()<321 && _dialogo.getNumber()>149) { 
 //BA.debugLineNum = 670;BA.debugLine="NewVal =(Dialogo.Number-150)";
_newval = (int) ((_dialogo.getNumber()-150));
 }else {
 //BA.debugLineNum = 672;BA.debugLine="Msgbox(\"Only 15º to 32º\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Only 15º to 32º"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 673;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 677;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 27: {
 //BA.debugLineNum = 680;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 681;BA.debugLine="Dialogo.Digits =4";
_dialogo.setDigits((int) (4));
 //BA.debugLineNum = 683;BA.debugLine="Dialogo.Number = ValoresComunes.Circuitos(Value";
_dialogo.setNumber((int) (mostCurrent._valorescomunes._circuitos[(int)(BA.ObjectToNumber(_value))].Value*-1));
 //BA.debugLineNum = 684;BA.debugLine="Dialogo.ShowSign = True";
_dialogo.setShowSign(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 685;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 686;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 688;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 689;BA.debugLine="If Dialogo.Number < 1  And Dialogo.Number  > -";
if (_dialogo.getNumber()<1 && _dialogo.getNumber()>-241) { 
 //BA.debugLineNum = 690;BA.debugLine="NewVal =Dialogo.Number * -1";
_newval = (int) (_dialogo.getNumber()*-1);
 }else {
 //BA.debugLineNum = 692;BA.debugLine="Msgbox(\"Max -240\",\"error\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Max -240"),BA.ObjectToCharSequence("error"),mostCurrent.activityBA);
 //BA.debugLineNum = 693;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 696;BA.debugLine="Return";
if (true) return "";
 };
 break; }
case 28: {
 //BA.debugLineNum = 699;BA.debugLine="Barra_On(Value)";
_barra_on((byte)(BA.ObjectToNumber(_value)));
 //BA.debugLineNum = 700;BA.debugLine="Return";
if (true) return "";
 break; }
case 29: 
case 30: {
 //BA.debugLineNum = 703;BA.debugLine="If ValoresComunes.Circuitos(Value).Value =0 The";
if (mostCurrent._valorescomunes._circuitos[(int)(BA.ObjectToNumber(_value))].Value==0) { 
 //BA.debugLineNum = 704;BA.debugLine="NewVal=100";
_newval = (int) (100);
 }else {
 //BA.debugLineNum = 706;BA.debugLine="NewVal=0";
_newval = (int) (0);
 };
 break; }
}
;
 //BA.debugLineNum = 716;BA.debugLine="If NewVal<0 Then Return";
if (_newval<0) { 
if (true) return "";};
 //BA.debugLineNum = 719;BA.debugLine="Dim LongTrama As Int";
_longtrama = 0;
 //BA.debugLineNum = 720;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 721;BA.debugLine="LongTrama= 14";
_longtrama = (int) (14);
 }else {
 //BA.debugLineNum = 723;BA.debugLine="LongTrama= 6";
_longtrama = (int) (6);
 };
 //BA.debugLineNum = 725;BA.debugLine="Dim data(LongTrama) As Byte";
_data = new byte[_longtrama];
;
 //BA.debugLineNum = 726;BA.debugLine="data(0)=83";
_data[(int) (0)] = (byte) (83);
 //BA.debugLineNum = 727;BA.debugLine="data(1)=86";
_data[(int) (1)] = (byte) (86);
 //BA.debugLineNum = 728;BA.debugLine="data(2)=65";
_data[(int) (2)] = (byte) (65);
 //BA.debugLineNum = 729;BA.debugLine="data(3)=76";
_data[(int) (3)] = (byte) (76);
 //BA.debugLineNum = 730;BA.debugLine="data(4)= Value +1";
_data[(int) (4)] = (byte) ((double)(BA.ObjectToNumber(_value))+1);
 //BA.debugLineNum = 731;BA.debugLine="data (5)=NewVal+1";
_data[(int) (5)] = (byte) (_newval+1);
 //BA.debugLineNum = 737;BA.debugLine="ActivarEsperaRespuesta";
_activaresperarespuesta();
 //BA.debugLineNum = 738;BA.debugLine="If ValoresComunes.central.ConexionSegura Then Val";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
mostCurrent._valorescomunes._completartrama(mostCurrent.activityBA,_data);};
 //BA.debugLineNum = 739;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 //BA.debugLineNum = 741;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemlongclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 444;BA.debugLine="Sub ListView1_ItemLongClick (Position As Int, Valu";
 //BA.debugLineNum = 445;BA.debugLine="StartActivity(ActVoice)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actvoice.getObject()));
 //BA.debugLineNum = 446;BA.debugLine="End Sub";
return "";
}
public static String  _mnusavescenes_click() throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _c = 0;
int _opcion = 0;
int _longtrama = 0;
byte[] _data = null;
int _i = 0;
 //BA.debugLineNum = 41;BA.debugLine="Sub mnuSaveScenes_Click";
 //BA.debugLineNum = 43;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 44;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 45;BA.debugLine="Dim C As Int";
_c = 0;
 //BA.debugLineNum = 46;BA.debugLine="For C =0 To 9";
{
final int step4 = 1;
final int limit4 = (int) (9);
_c = (int) (0) ;
for (;(step4 > 0 && _c <= limit4) || (step4 < 0 && _c >= limit4) ;_c = ((int)(0 + _c + step4))  ) {
 //BA.debugLineNum = 47;BA.debugLine="Lst.Add (ValoresComunes.Scenes (C).Nombre )";
_lst.Add((Object)(mostCurrent._valorescomunes._scenes[_c].Nombre));
 }
};
 //BA.debugLineNum = 50;BA.debugLine="Dim Opcion As Int";
_opcion = 0;
 //BA.debugLineNum = 51;BA.debugLine="Opcion =InputList( Lst,ValoresComunes.GetLanStrin";
_opcion = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg138")),(int) (0),mostCurrent.activityBA);
 //BA.debugLineNum = 53;BA.debugLine="If Opcion < 0 Then Return";
if (_opcion<0) { 
if (true) return "";};
 //BA.debugLineNum = 56;BA.debugLine="Dim LongTrama As Int";
_longtrama = 0;
 //BA.debugLineNum = 57;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 58;BA.debugLine="LongTrama= 43";
_longtrama = (int) (43);
 }else {
 //BA.debugLineNum = 60;BA.debugLine="LongTrama= 35";
_longtrama = (int) (35);
 };
 //BA.debugLineNum = 62;BA.debugLine="Dim data(LongTrama) As Byte";
_data = new byte[_longtrama];
;
 //BA.debugLineNum = 63;BA.debugLine="data(0)=87";
_data[(int) (0)] = (byte) (87);
 //BA.debugLineNum = 64;BA.debugLine="data(1)=69";
_data[(int) (1)] = (byte) (69);
 //BA.debugLineNum = 65;BA.debugLine="data(2)=83";
_data[(int) (2)] = (byte) (83);
 //BA.debugLineNum = 66;BA.debugLine="data(3)=67";
_data[(int) (3)] = (byte) (67);
 //BA.debugLineNum = 67;BA.debugLine="data(4)= Opcion +1";
_data[(int) (4)] = (byte) (_opcion+1);
 //BA.debugLineNum = 68;BA.debugLine="Dim I As Int";
_i = 0;
 //BA.debugLineNum = 70;BA.debugLine="For I =0 To 29";
{
final int step23 = 1;
final int limit23 = (int) (29);
_i = (int) (0) ;
for (;(step23 > 0 && _i <= limit23) || (step23 < 0 && _i >= limit23) ;_i = ((int)(0 + _i + step23))  ) {
 //BA.debugLineNum = 71;BA.debugLine="data(I+5)= ValoresComunes.Circuitos (I).Value +1";
_data[(int) (_i+5)] = (byte) (mostCurrent._valorescomunes._circuitos[_i].Value+1);
 }
};
 //BA.debugLineNum = 73;BA.debugLine="ActivarEsperaRespuesta";
_activaresperarespuesta();
 //BA.debugLineNum = 74;BA.debugLine="If ValoresComunes.central.ConexionSegura Then Val";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
mostCurrent._valorescomunes._completartrama(mostCurrent.activityBA,_data);};
 //BA.debugLineNum = 75;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub process_globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim UDPSocket1 As UDPSocket";
_udpsocket1 = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket();
 //BA.debugLineNum = 8;BA.debugLine="Dim Timer1 As Timer";
_timer1 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 9;BA.debugLine="Dim SelectScene As Int";
_selectscene = 0;
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public static String  _seleccionarescena(int _opcion) throws Exception{
int _longtrama = 0;
byte[] _data = null;
 //BA.debugLineNum = 77;BA.debugLine="Sub SeleccionarEscena(opcion As Int)";
 //BA.debugLineNum = 78;BA.debugLine="If opcion < 0 Then Return";
if (_opcion<0) { 
if (true) return "";};
 //BA.debugLineNum = 82;BA.debugLine="Dim LongTrama As Int";
_longtrama = 0;
 //BA.debugLineNum = 83;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 84;BA.debugLine="LongTrama= 13";
_longtrama = (int) (13);
 }else {
 //BA.debugLineNum = 86;BA.debugLine="LongTrama= 5";
_longtrama = (int) (5);
 };
 //BA.debugLineNum = 88;BA.debugLine="Dim data(LongTrama) As Byte";
_data = new byte[_longtrama];
;
 //BA.debugLineNum = 89;BA.debugLine="data(0)=83";
_data[(int) (0)] = (byte) (83);
 //BA.debugLineNum = 90;BA.debugLine="data(1)=83";
_data[(int) (1)] = (byte) (83);
 //BA.debugLineNum = 91;BA.debugLine="data(2)=67";
_data[(int) (2)] = (byte) (67);
 //BA.debugLineNum = 92;BA.debugLine="data(3)=69";
_data[(int) (3)] = (byte) (69);
 //BA.debugLineNum = 93;BA.debugLine="data(4)= opcion +1";
_data[(int) (4)] = (byte) (_opcion+1);
 //BA.debugLineNum = 96;BA.debugLine="If ValoresComunes.central.ConexionSegura Then Val";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
mostCurrent._valorescomunes._completartrama(mostCurrent.activityBA,_data);};
 //BA.debugLineNum = 97;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
public static boolean  _sendingtrama(byte[] _data) throws Exception{
anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet = null;
 //BA.debugLineNum = 965;BA.debugLine="Sub SendingTrama (data() As Byte) As Boolean";
 //BA.debugLineNum = 966;BA.debugLine="Try";
try { //BA.debugLineNum = 967;BA.debugLine="Dim Packet As UDPPacket";
_packet = new anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket();
 //BA.debugLineNum = 974;BA.debugLine="Packet.Initialize(data , ValoresComunes.ip,  Val";
_packet.Initialize(_data,mostCurrent._valorescomunes._ip,mostCurrent._valorescomunes._puerto);
 //BA.debugLineNum = 975;BA.debugLine="UDPSocket1.Send(Packet)";
_udpsocket1.Send(_packet);
 //BA.debugLineNum = 976;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 } 
       catch (Exception e7) {
			processBA.setLastException(e7); //BA.debugLineNum = 978;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 980;BA.debugLine="End Sub";
return false;
}
public static void  _sendtrama(byte[] _data) throws Exception{
ResumableSub_SendTrama rsub = new ResumableSub_SendTrama(null,_data);
rsub.resume(processBA, null);
}
public static class ResumableSub_SendTrama extends BA.ResumableSub {
public ResumableSub_SendTrama(arduino.automatizacion.excontrol.PRO.actcircuit parent,byte[] _data) {
this.parent = parent;
this._data = _data;
}
arduino.automatizacion.excontrol.PRO.actcircuit parent;
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
 //BA.debugLineNum = 901;BA.debugLine="Dim Resultado As Boolean";
_resultado = false;
 //BA.debugLineNum = 902;BA.debugLine="Dim Reintento As Int";
_reintento = 0;
 //BA.debugLineNum = 905;BA.debugLine="UltimaTrama=data";
parent._ultimatrama = _data;
 //BA.debugLineNum = 906;BA.debugLine="ContTim=0";
parent._conttim = (int) (0);
 //BA.debugLineNum = 908;BA.debugLine="Do 	While Resultado= False And Reintento < 40";
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
 //BA.debugLineNum = 909;BA.debugLine="Dim ServerSocket1 As ServerSocket";
_serversocket1 = new anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper();
 //BA.debugLineNum = 911;BA.debugLine="Dim MyIp As String";
_myip = "";
 //BA.debugLineNum = 912;BA.debugLine="If ActMosaico.Forzar3g Then";
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
 //BA.debugLineNum = 913;BA.debugLine="MyIp=ServerSocket1.GetMyIP";
_myip = _serversocket1.GetMyIP();
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 916;BA.debugLine="MyIp=ServerSocket1.GetMyWifiIP";
_myip = _serversocket1.GetMyWifiIP();
 if (true) break;
;
 //BA.debugLineNum = 918;BA.debugLine="If MyIp  <> \"127.0.0.1\" Then";

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
 //BA.debugLineNum = 919;BA.debugLine="Resultado = True";
_resultado = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 13:
//C
this.state = 14;
 //BA.debugLineNum = 921;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 922;BA.debugLine="Sleep (200)";
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
 //BA.debugLineNum = 926;BA.debugLine="If Resultado = True Then";

case 15:
//if
this.state = 28;
if (_resultado==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 17;
}if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 927;BA.debugLine="Resultado =False";
_resultado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 928;BA.debugLine="Reintento =0";
_reintento = (int) (0);
 //BA.debugLineNum = 929;BA.debugLine="Do 	While Resultado= False And Reintento < 40";
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
 //BA.debugLineNum = 930;BA.debugLine="Resultado = SendingTrama(data)";
_resultado = _sendingtrama(_data);
 //BA.debugLineNum = 931;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 932;BA.debugLine="If Resultado=False Then Sleep (200)";
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
 //BA.debugLineNum = 937;BA.debugLine="If Resultado = False  Then";

case 28:
//if
this.state = 31;
if (_resultado==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 30;
}if (true) break;

case 30:
//C
this.state = 31;
 //BA.debugLineNum = 938;BA.debugLine="ActMosaico.Conectado =False";
parent.mostCurrent._actmosaico._conectado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 939;BA.debugLine="StartActivity(ActMosaico)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(parent.mostCurrent._actmosaico.getObject()));
 if (true) break;

case 31:
//C
this.state = -1;
;
 //BA.debugLineNum = 950;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _slidemenu_click(Object _item) throws Exception{
 //BA.debugLineNum = 982;BA.debugLine="Sub SlideMenu_Click(Item As Object)";
 //BA.debugLineNum = 983;BA.debugLine="If Item>99 And  Item < 200 Then";
if ((double)(BA.ObjectToNumber(_item))>99 && (double)(BA.ObjectToNumber(_item))<200) { 
 //BA.debugLineNum = 984;BA.debugLine="ActMosaico.Conectado =False";
mostCurrent._actmosaico._conectado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 985;BA.debugLine="ActMosaico.CentraltoConnect  =Item-100";
mostCurrent._actmosaico._centraltoconnect = (int) ((double)(BA.ObjectToNumber(_item))-100);
 //BA.debugLineNum = 986;BA.debugLine="StartActivity(ActMosaico)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actmosaico.getObject()));
 //BA.debugLineNum = 987;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else if((double)(BA.ObjectToNumber(_item))>199 && (double)(BA.ObjectToNumber(_item))<299) { 
 }else {
 //BA.debugLineNum = 995;BA.debugLine="Select Case Item";
switch (BA.switchObjectToInt(_item,(Object)(1),(Object)(2),(Object)(3),(Object)(4),(Object)(5),(Object)(6),(Object)(7),(Object)(8),(Object)(9),(Object)(10),(Object)(11),(Object)(98))) {
case 0: {
 //BA.debugLineNum = 998;BA.debugLine="StartActivity(ActMosaico)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actmosaico.getObject()));
 //BA.debugLineNum = 999;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 1: {
 break; }
case 2: {
 //BA.debugLineNum = 1004;BA.debugLine="StartActivity(ActScene)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actscene.getObject()));
 //BA.debugLineNum = 1005;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 3: {
 //BA.debugLineNum = 1007;BA.debugLine="StartActivity(ActNotification)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actnotification.getObject()));
 //BA.debugLineNum = 1008;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 4: {
 //BA.debugLineNum = 1010;BA.debugLine="StartActivity(ActCondicionados)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actcondicionados.getObject()));
 //BA.debugLineNum = 1011;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 5: {
 //BA.debugLineNum = 1013;BA.debugLine="StartActivity(ActComandos)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actcomandos.getObject()));
 //BA.debugLineNum = 1014;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 6: {
 //BA.debugLineNum = 1016;BA.debugLine="StartActivity(ActSensors)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actsensors.getObject()));
 //BA.debugLineNum = 1017;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 7: {
 //BA.debugLineNum = 1019;BA.debugLine="If File.Exists ( File.DirInternal ,\"Sensores\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Sensores"+mostCurrent._valorescomunes._central.Nombre)) { 
 //BA.debugLineNum = 1020;BA.debugLine="StartActivity(ActFreeTxt)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actfreetxt.getObject()));
 //BA.debugLineNum = 1021;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else {
 //BA.debugLineNum = 1023;BA.debugLine="ToastMessageShow(ValoresComunes.GetLanString";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg127")),anywheresoftware.b4a.keywords.Common.True);
 };
 break; }
case 8: {
 //BA.debugLineNum = 1026;BA.debugLine="StartActivity(ActConsignas)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconsignas.getObject()));
 //BA.debugLineNum = 1027;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 9: {
 //BA.debugLineNum = 1029;BA.debugLine="ValoresComunes.CloseApp =True";
mostCurrent._valorescomunes._closeapp = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1030;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 10: {
 //BA.debugLineNum = 1032;BA.debugLine="StartActivity(ActFunciones)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actfunciones.getObject()));
 //BA.debugLineNum = 1033;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
case 11: {
 //BA.debugLineNum = 1037;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 break; }
}
;
 };
 //BA.debugLineNum = 1041;BA.debugLine="End Sub";
return "";
}
public static String  _timer1_tick() throws Exception{
 //BA.debugLineNum = 181;BA.debugLine="Sub Timer1_Tick";
 //BA.debugLineNum = 182;BA.debugLine="ContTim=ContTim+1";
_conttim = (int) (_conttim+1);
 //BA.debugLineNum = 183;BA.debugLine="If ImgCargando.Visible = False Then";
if (mostCurrent._imgcargando.getVisible()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 185;BA.debugLine="If ContTim >50 Then";
if (_conttim>50) { 
 //BA.debugLineNum = 187;BA.debugLine="LecturaDatos";
_lecturadatos();
 };
 }else {
 //BA.debugLineNum = 191;BA.debugLine="If ContTim=30 Then";
if (_conttim==30) { 
 //BA.debugLineNum = 192;BA.debugLine="SendTrama(UltimaTrama)";
_sendtrama(_ultimatrama);
 };
 };
 //BA.debugLineNum = 199;BA.debugLine="End Sub";
return "";
}
public static String  _udp_packetarrived(anywheresoftware.b4a.objects.SocketWrapper.UDPSocket.UDPPacket _packet) throws Exception{
String _msg = "";
int _c = 0;
int _v = 0;
int _s = 0;
short _b1 = (short)0;
short _b2 = (short)0;
byte[] _data = null;
String _trama = "";
 //BA.debugLineNum = 751;BA.debugLine="Sub UDP_PacketArrived (Packet As UDPPacket)";
 //BA.debugLineNum = 752;BA.debugLine="Try";
try { //BA.debugLineNum = 753;BA.debugLine="Dim msg As String";
_msg = "";
 //BA.debugLineNum = 754;BA.debugLine="msg = BytesToString(Packet.data, Packet.Offset,";
_msg = anywheresoftware.b4a.keywords.Common.BytesToString(_packet.getData(),_packet.getOffset(),_packet.getLength(),"UTF8");
 //BA.debugLineNum = 757;BA.debugLine="ContTim=0";
_conttim = (int) (0);
 //BA.debugLineNum = 758;BA.debugLine="If msg.Contains (\"VVAL\") Then		'Solo refresco, s";
if (_msg.contains("VVAL")) { 
 //BA.debugLineNum = 760;BA.debugLine="Dim  C As Int";
_c = 0;
 //BA.debugLineNum = 761;BA.debugLine="Dim V As Int";
_v = 0;
 //BA.debugLineNum = 763;BA.debugLine="For C = 4 To Packet.Length -1";
{
final int step8 = 1;
final int limit8 = (int) (_packet.getLength()-1);
_c = (int) (4) ;
for (;(step8 > 0 && _c <= limit8) || (step8 < 0 && _c >= limit8) ;_c = ((int)(0 + _c + step8))  ) {
 //BA.debugLineNum = 764;BA.debugLine="If C<34 Then";
if (_c<34) { 
 //BA.debugLineNum = 765;BA.debugLine="V=Packet.data (C)-1";
_v = (int) (_packet.getData()[_c]-1);
 //BA.debugLineNum = 769;BA.debugLine="If V < 0 Then";
if (_v<0) { 
 //BA.debugLineNum = 770;BA.debugLine="V=V +256";
_v = (int) (_v+256);
 };
 //BA.debugLineNum = 772;BA.debugLine="ValoresComunes.Circuitos(C-4).Value =V";
mostCurrent._valorescomunes._circuitos[(int) (_c-4)].Value = _v;
 };
 }
};
 //BA.debugLineNum = 781;BA.debugLine="If ListView1.Visible = False Then";
if (mostCurrent._listview1.getVisible()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 782;BA.debugLine="desactivarEspera";
_desactivarespera();
 };
 //BA.debugLineNum = 786;BA.debugLine="ActualizaValores";
_actualizavalores();
 //BA.debugLineNum = 795;BA.debugLine="LecturaSensores";
_lecturasensores();
 }else if(_msg.contains("VESC")) { 
 //BA.debugLineNum = 799;BA.debugLine="Dim  C As Int";
_c = 0;
 //BA.debugLineNum = 800;BA.debugLine="Dim S As Int";
_s = 0;
 //BA.debugLineNum = 802;BA.debugLine="C=4";
_c = (int) (4);
 //BA.debugLineNum = 804;BA.debugLine="Do While C<  Packet.Length";
while (_c<_packet.getLength()) {
 //BA.debugLineNum = 806;BA.debugLine="Dim B1 As Short";
_b1 = (short)0;
 //BA.debugLineNum = 807;BA.debugLine="Dim B2 As Short";
_b2 = (short)0;
 //BA.debugLineNum = 810;BA.debugLine="B1= Bit.And (Packet.data (C) , 0xff)";
_b1 = (short) (anywheresoftware.b4a.keywords.Common.Bit.And((int) (_packet.getData()[_c]),(int) (0xff)));
 //BA.debugLineNum = 811;BA.debugLine="If B1<255 Then B1=B1-1";
if (_b1<255) { 
_b1 = (short) (_b1-1);};
 //BA.debugLineNum = 815;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 817;BA.debugLine="B2=Bit.And (Packet.data (C) , 0xff)";
_b2 = (short) (anywheresoftware.b4a.keywords.Common.Bit.And((int) (_packet.getData()[_c]),(int) (0xff)));
 //BA.debugLineNum = 818;BA.debugLine="If B2<255 Then B2=B2-1";
if (_b2<255) { 
_b2 = (short) (_b2-1);};
 //BA.debugLineNum = 819;BA.debugLine="C=C+1";
_c = (int) (_c+1);
 //BA.debugLineNum = 820;BA.debugLine="B1= Bit.Or(Bit.ShiftLeft ( B2,8),B1)";
_b1 = (short) (anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Bit.ShiftLeft((int) (_b2),(int) (8)),(int) (_b1)));
 //BA.debugLineNum = 821;BA.debugLine="ValoresComunes.Sensores (S).Value =B1";
mostCurrent._valorescomunes._sensores[_s].Value = (int) (_b1);
 //BA.debugLineNum = 826;BA.debugLine="S=S+1";
_s = (int) (_s+1);
 }
;
 //BA.debugLineNum = 833;BA.debugLine="ActualizaValores";
_actualizavalores();
 //BA.debugLineNum = 834;BA.debugLine="Return";
if (true) return "";
 }else if(_msg.contains("EVAL")) { 
 //BA.debugLineNum = 838;BA.debugLine="Dim  C As Int";
_c = 0;
 //BA.debugLineNum = 839;BA.debugLine="For C = 4 To Packet.Length -1";
{
final int step43 = 1;
final int limit43 = (int) (_packet.getLength()-1);
_c = (int) (4) ;
for (;(step43 > 0 && _c <= limit43) || (step43 < 0 && _c >= limit43) ;_c = ((int)(0 + _c + step43))  ) {
 //BA.debugLineNum = 840;BA.debugLine="If C<34 Then";
if (_c<34) { 
 //BA.debugLineNum = 841;BA.debugLine="ValoresComunes.Circuitos(C - 4).Value = Packe";
mostCurrent._valorescomunes._circuitos[(int) (_c-4)].Value = (int) (_packet.getData()[_c]-1);
 //BA.debugLineNum = 842;BA.debugLine="If ValoresComunes.Circuitos(C-4).Value  < 0 T";
if (mostCurrent._valorescomunes._circuitos[(int) (_c-4)].Value<0) { 
 //BA.debugLineNum = 843;BA.debugLine="ValoresComunes.Circuitos(C-4).Value =Valores";
mostCurrent._valorescomunes._circuitos[(int) (_c-4)].Value = (int) (mostCurrent._valorescomunes._circuitos[(int) (_c-4)].Value+256);
 };
 };
 }
};
 //BA.debugLineNum = 847;BA.debugLine="desactivarEspera";
_desactivarespera();
 }else if(_msg.contains("VALC")) { 
 //BA.debugLineNum = 850;BA.debugLine="Dim  C As Int";
_c = 0;
 //BA.debugLineNum = 851;BA.debugLine="For C = 4 To Packet.Length -1";
{
final int step54 = 1;
final int limit54 = (int) (_packet.getLength()-1);
_c = (int) (4) ;
for (;(step54 > 0 && _c <= limit54) || (step54 < 0 && _c >= limit54) ;_c = ((int)(0 + _c + step54))  ) {
 //BA.debugLineNum = 852;BA.debugLine="If C<34 Then	ValoresComunes.Circuitos (C-4).ra";
if (_c<34) { 
mostCurrent._valorescomunes._circuitos[(int) (_c-4)].Rango = (int) (_packet.getData()[_c]-1);};
 }
};
 //BA.debugLineNum = 854;BA.debugLine="ValoresComunes.GuardarConfigCircuitos";
mostCurrent._valorescomunes._guardarconfigcircuitos(mostCurrent.activityBA);
 //BA.debugLineNum = 855;BA.debugLine="ActualizaValores";
_actualizavalores();
 //BA.debugLineNum = 856;BA.debugLine="Dim data() As Byte";
_data = new byte[(int) (0)];
;
 //BA.debugLineNum = 857;BA.debugLine="Dim Trama As String";
_trama = "";
 //BA.debugLineNum = 858;BA.debugLine="If ValoresComunes.central.ConexionSegura Then";
if (mostCurrent._valorescomunes._central.ConexionSegura) { 
 //BA.debugLineNum = 859;BA.debugLine="Trama = \"REDN\" & ValoresComunes.Central.Passwo";
_trama = "REDN"+mostCurrent._valorescomunes._central.Password;
 }else {
 //BA.debugLineNum = 861;BA.debugLine="Trama = \"REDN\"";
_trama = "REDN";
 };
 //BA.debugLineNum = 864;BA.debugLine="data = Trama.GetBytes(\"UTF8\")";
_data = _trama.getBytes("UTF8");
 //BA.debugLineNum = 867;BA.debugLine="SendTrama(data)";
_sendtrama(_data);
 //BA.debugLineNum = 868;BA.debugLine="Return";
if (true) return "";
 }else if(_msg.contains("VADN")) { 
 //BA.debugLineNum = 871;BA.debugLine="Dim  C As Int";
_c = 0;
 //BA.debugLineNum = 872;BA.debugLine="For C = 4 To Packet.Length -1";
{
final int step71 = 1;
final int limit71 = (int) (_packet.getLength()-1);
_c = (int) (4) ;
for (;(step71 > 0 && _c <= limit71) || (step71 < 0 && _c >= limit71) ;_c = ((int)(0 + _c + step71))  ) {
 //BA.debugLineNum = 873;BA.debugLine="If C<34 Then	ValoresComunes.Circuitos (C-4).De";
if (_c<34) { 
mostCurrent._valorescomunes._circuitos[(int) (_c-4)].DeviceNumber = (int) (_packet.getData()[_c]-1);};
 }
};
 //BA.debugLineNum = 875;BA.debugLine="ValoresComunes.GuardarConfigCircuitos";
mostCurrent._valorescomunes._guardarconfigcircuitos(mostCurrent.activityBA);
 //BA.debugLineNum = 876;BA.debugLine="ActualizaValores";
_actualizavalores();
 //BA.debugLineNum = 877;BA.debugLine="LecturaDatos";
_lecturadatos();
 //BA.debugLineNum = 878;BA.debugLine="Return";
if (true) return "";
 }else if((_msg).equals("COMPLETED")) { 
 //BA.debugLineNum = 880;BA.debugLine="ToastMessageShow(ValoresComunes.GetLanString (\"";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg139")),anywheresoftware.b4a.keywords.Common.True);
 }else if((_msg).equals("REPETIRMSG")) { 
 //BA.debugLineNum = 882;BA.debugLine="SendTrama(UltimaTrama)";
_sendtrama(_ultimatrama);
 //BA.debugLineNum = 883;BA.debugLine="Return";
if (true) return "";
 };
 } 
       catch (Exception e85) {
			processBA.setLastException(e85); };
 //BA.debugLineNum = 892;BA.debugLine="End Sub";
return "";
}
}
