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

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
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
		activityBA = new BA(this, layout, processBA, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "arduino.automatizacion.excontrol.PRO.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
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
		return main.class;
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
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (main) Resume **");
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
public static anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper _mylan = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
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

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (actcentral.mostCurrent != null);
vis = vis | (actmosaico.mostCurrent != null);
vis = vis | (actcomandos.mostCurrent != null);
vis = vis | (actconfigsetponint.mostCurrent != null);
vis = vis | (actconsignas.mostCurrent != null);
vis = vis | (actcircuit.mostCurrent != null);
vis = vis | (actsensors.mostCurrent != null);
vis = vis | (actenablehorarios.mostCurrent != null);
vis = vis | (actdiaespecial.mostCurrent != null);
vis = vis | (actconescena.mostCurrent != null);
vis = vis | (acthorarios2.mostCurrent != null);
vis = vis | (actalarmas2.mostCurrent != null);
vis = vis | (actvoice.mostCurrent != null);
vis = vis | (acthistorico.mostCurrent != null);
vis = vis | (actcamara.mostCurrent != null);
vis = vis | (actnotification.mostCurrent != null);
vis = vis | (actscene.mostCurrent != null);
vis = vis | (actfunciones.mostCurrent != null);
vis = vis | (actcondicionados.mostCurrent != null);
vis = vis | (actselectsensores.mostCurrent != null);
vis = vis | (actconfigsensors.mostCurrent != null);
vis = vis | (actmenu.mostCurrent != null);
vis = vis | (actconfignotification.mostCurrent != null);
vis = vis | (actwifis.mostCurrent != null);
vis = vis | (actfreetxt.mostCurrent != null);
vis = vis | (actconfigvoice.mostCurrent != null);
vis = vis | (actconfigfreetxt.mostCurrent != null);
vis = vis | (actcentrales.mostCurrent != null);
vis = vis | (actconfigescenas.mostCurrent != null);
vis = vis | (actconfigfunciones.mostCurrent != null);
vis = vis | (actconfigcondicionados.mostCurrent != null);
vis = vis | (actconfigcir.mostCurrent != null);
vis = vis | (actpersianas.mostCurrent != null);
vis = vis | (actconfigcomandoscomu.mostCurrent != null);
vis = vis | (actconfigcomandos.mostCurrent != null);
vis = vis | (actpersonalicon.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
int _result = 0;
anywheresoftware.b4a.phone.Phone.PhoneIntents _p = null;
anywheresoftware.b4a.agraham.dialogs.InputDialog.FileDialog _d = null;
com.AB.ABZipUnzip.ABZipUnzip _myzip = null;
String _ruta = "";
int _h = 0;
 //BA.debugLineNum = 34;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 37;BA.debugLine="If MyLan.IsInitialized = False Then  MyLan.Initia";
if (_mylan.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_mylan.Initialize(processBA,(int) (0),"");};
 //BA.debugLineNum = 43;BA.debugLine="Activity.LoadLayout (\"FrmInicio\")";
mostCurrent._activity.LoadLayout("FrmInicio",mostCurrent.activityBA);
 //BA.debugLineNum = 47;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 50;BA.debugLine="If File.Exists ( File.DirInternal ,\"Centrales\")";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Centrales")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 51;BA.debugLine="Dim result As Int";
_result = 0;
 //BA.debugLineNum = 52;BA.debugLine="result = Msgbox2(ValoresComunes.GetLanString (\"";
_result = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"cdt")),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"pp")),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Y"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"N"),"",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 //BA.debugLineNum = 53;BA.debugLine="If result = DialogResponse.Positive Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 54;BA.debugLine="Dim P As PhoneIntents";
_p = new anywheresoftware.b4a.phone.Phone.PhoneIntents();
 //BA.debugLineNum = 55;BA.debugLine="StartActivity(P.OpenBrowser(\"http://domotica-a";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_p.OpenBrowser("http://domotica-arduino.es/blog/download-arduino-home-automation-arduino-apps/")));
 //BA.debugLineNum = 56;BA.debugLine="ValoresComunes.CloseApp=True";
mostCurrent._valorescomunes._closeapp = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 57;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 60;BA.debugLine="result = Msgbox2(ValoresComunes.GetLanString (\"";
_result = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"RBU")+"?"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"LCF")),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Y"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"N"),"",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
 //BA.debugLineNum = 61;BA.debugLine="If result = DialogResponse.Positive Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 62;BA.debugLine="Dim d As FileDialog";
_d = new anywheresoftware.b4a.agraham.dialogs.InputDialog.FileDialog();
 //BA.debugLineNum = 63;BA.debugLine="d.FilePath = File.DirDefaultExternal";
_d.setFilePath(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal());
 //BA.debugLineNum = 64;BA.debugLine="d.ShowOnlyFolders= False";
_d.setShowOnlyFolders(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 65;BA.debugLine="d.FileFilter=\".piz\"";
_d.setFileFilter(".piz");
 //BA.debugLineNum = 66;BA.debugLine="result= d.Show (\"Select backup copy\", ValoresC";
_result = _d.Show(BA.ObjectToCharSequence("Select backup copy"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Ok"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 68;BA.debugLine="If result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 71;BA.debugLine="Dim myZip As  ABZipUnzip";
_myzip = new com.AB.ABZipUnzip.ABZipUnzip();
 //BA.debugLineNum = 72;BA.debugLine="Dim ruta As String";
_ruta = "";
 //BA.debugLineNum = 73;BA.debugLine="ruta =  d.FilePath & \"/\" & d.ChosenName";
_ruta = _d.getFilePath()+"/"+_d.getChosenName();
 //BA.debugLineNum = 74;BA.debugLine="If ruta.Length >0 And d.ChosenName.Length > 0";
if (_ruta.length()>0 && _d.getChosenName().length()>0) { 
 //BA.debugLineNum = 75;BA.debugLine="myZip.ABUnzip ( ruta, File.DirInternal)";
_myzip.ABUnzip(_ruta,anywheresoftware.b4a.keywords.Common.File.getDirInternal());
 //BA.debugLineNum = 77;BA.debugLine="ToastMessageShow(ValoresComunes.GetLanString";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg139")),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 78;BA.debugLine="ValoresComunes.CloseApp =True";
mostCurrent._valorescomunes._closeapp = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 79;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 }else {
 //BA.debugLineNum = 82;BA.debugLine="ActCentral.InitialName =\"\"";
mostCurrent._actcentral._initialname = "";
 //BA.debugLineNum = 83;BA.debugLine="ValoresComunes.Centrales .Initialize";
mostCurrent._valorescomunes._centrales.Initialize();
 //BA.debugLineNum = 84;BA.debugLine="ActCentral.InitialName =\"\"";
mostCurrent._actcentral._initialname = "";
 //BA.debugLineNum = 85;BA.debugLine="StartActivity(ActCentral)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actcentral.getObject()));
 //BA.debugLineNum = 86;BA.debugLine="Return";
if (true) return "";
 };
 }else {
 //BA.debugLineNum = 90;BA.debugLine="ActCentral.InitialName =\"\"";
mostCurrent._actcentral._initialname = "";
 //BA.debugLineNum = 91;BA.debugLine="ValoresComunes.Centrales .Initialize";
mostCurrent._valorescomunes._centrales.Initialize();
 //BA.debugLineNum = 92;BA.debugLine="ActCentral.InitialName =\"\"";
mostCurrent._actcentral._initialname = "";
 //BA.debugLineNum = 93;BA.debugLine="StartActivity(ActCentral)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actcentral.getObject()));
 //BA.debugLineNum = 94;BA.debugLine="Return";
if (true) return "";
 };
 };
 //BA.debugLineNum = 97;BA.debugLine="ValoresComunes.Centrales=  File.ReadList (File.D";
mostCurrent._valorescomunes._centrales = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Centrales");
 };
 //BA.debugLineNum = 102;BA.debugLine="ValoresComunes.Ip =\"\"";
mostCurrent._valorescomunes._ip = "";
 //BA.debugLineNum = 108;BA.debugLine="ListView1.height =Activity.Height";
mostCurrent._listview1.setHeight(mostCurrent._activity.getHeight());
 //BA.debugLineNum = 109;BA.debugLine="ListView1.Width =Activity.Width";
mostCurrent._listview1.setWidth(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 110;BA.debugLine="ListView1.Top =0";
mostCurrent._listview1.setTop((int) (0));
 //BA.debugLineNum = 111;BA.debugLine="ListView1.Left =0";
mostCurrent._listview1.setLeft((int) (0));
 //BA.debugLineNum = 115;BA.debugLine="Dim h As Int";
_h = 0;
 //BA.debugLineNum = 116;BA.debugLine="h=75dip";
_h = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75));
 //BA.debugLineNum = 119;BA.debugLine="ListView1.TwoLinesAndBitmap .ItemHeight =h";
mostCurrent._listview1.getTwoLinesAndBitmap().setItemHeight(_h);
 //BA.debugLineNum = 120;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.Width=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setWidth(_h);
 //BA.debugLineNum = 121;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.height=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setHeight(_h);
 //BA.debugLineNum = 123;BA.debugLine="ListView1.TwoLinesAndBitmap.Label .Left =h + h/9";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setLeft((int) (_h+_h/(double)9));
 //BA.debugLineNum = 124;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.height  =h/1.8";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setHeight((int) (_h/(double)1.8));
 //BA.debugLineNum = 125;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.Gravity = Bit.O";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.BOTTOM));
 //BA.debugLineNum = 127;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel .Left =Li";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setLeft(mostCurrent._listview1.getTwoLinesAndBitmap().Label.getLeft());
 //BA.debugLineNum = 128;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.height";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setHeight((int) (_h-mostCurrent._listview1.getTwoLinesAndBitmap().Label.getHeight()));
 //BA.debugLineNum = 129;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.top   = L";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setTop(mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.getHeight());
 //BA.debugLineNum = 130;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.Gravity =";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 137;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 180;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 182;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
int _li = 0;
 //BA.debugLineNum = 138;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 139;BA.debugLine="If ValoresComunes.CloseApp Or ValoresComunes.Cent";
if (mostCurrent._valorescomunes._closeapp || mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 140;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 }else {
 //BA.debugLineNum = 142;BA.debugLine="If MyLan.IsInitialized = False Then  MyLan.Initi";
if (_mylan.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
_mylan.Initialize(processBA,(int) (0),"");};
 //BA.debugLineNum = 143;BA.debugLine="ListView1.height = 100%y - 52dip";
mostCurrent._listview1.setHeight((int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (52))));
 //BA.debugLineNum = 144;BA.debugLine="ListView1.Width =Activity.Width";
mostCurrent._listview1.setWidth(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 145;BA.debugLine="ListView1.Top =52dip";
mostCurrent._listview1.setTop(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (52)));
 //BA.debugLineNum = 149;BA.debugLine="If ValoresComunes.Centrales .Size >0 Then";
if (mostCurrent._valorescomunes._centrales.getSize()>0) { 
 //BA.debugLineNum = 151;BA.debugLine="If ValoresComunes.Centrales.Size =1 Then";
if (mostCurrent._valorescomunes._centrales.getSize()==1) { 
 //BA.debugLineNum = 152;BA.debugLine="StartActivity(ActMosaico)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actmosaico.getObject()));
 }else {
 //BA.debugLineNum = 154;BA.debugLine="If ListView1.Size <> ValoresComunes.Centrales";
if (mostCurrent._listview1.getSize()!=mostCurrent._valorescomunes._centrales.getSize()) { 
 //BA.debugLineNum = 155;BA.debugLine="ValoresComunes.IniciarIconosCentrales";
mostCurrent._valorescomunes._iniciariconoscentrales(mostCurrent.activityBA);
 };
 //BA.debugLineNum = 158;BA.debugLine="Dim li As Int";
_li = 0;
 //BA.debugLineNum = 159;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 161;BA.debugLine="For li = 0 To ValoresComunes.Centrales.Size -1";
{
final int step17 = 1;
final int limit17 = (int) (mostCurrent._valorescomunes._centrales.getSize()-1);
_li = (int) (0) ;
for (;(step17 > 0 && _li <= limit17) || (step17 < 0 && _li >= limit17) ;_li = ((int)(0 + _li + step17))  ) {
 //BA.debugLineNum = 162;BA.debugLine="ListView1.AddTwoLinesAndBitmap (ValoresComune";
mostCurrent._listview1.AddTwoLinesAndBitmap(BA.ObjectToCharSequence(mostCurrent._valorescomunes._centrales.Get(_li)),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"PC")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._getimgdevice(mostCurrent.activityBA,BA.ObjectToString(mostCurrent._valorescomunes._centrales.Get(_li)),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }
};
 };
 }else {
 //BA.debugLineNum = 168;BA.debugLine="ActCentral.InitialName =\"\"";
mostCurrent._actcentral._initialname = "";
 //BA.debugLineNum = 169;BA.debugLine="StartActivity(ActCentral)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actcentral.getObject()));
 };
 };
 //BA.debugLineNum = 175;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 25;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 26;BA.debugLine="Dim ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 184;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 186;BA.debugLine="ActMosaico.Conectado =False";
mostCurrent._actmosaico._conectado = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 187;BA.debugLine="ActMosaico.CentraltoConnect  = Position";
mostCurrent._actmosaico._centraltoconnect = _position;
 //BA.debugLineNum = 188;BA.debugLine="StartActivity(ActMosaico)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actmosaico.getObject()));
 //BA.debugLineNum = 189;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        anywheresoftware.b4a.samples.httputils2.httputils2service._process_globals();
main._process_globals();
actcentral._process_globals();
valorescomunes._process_globals();
actmosaico._process_globals();
actcomandos._process_globals();
actconfigsetponint._process_globals();
actconsignas._process_globals();
actcircuit._process_globals();
actsensors._process_globals();
actenablehorarios._process_globals();
actdiaespecial._process_globals();
actconescena._process_globals();
acthorarios2._process_globals();
actalarmas2._process_globals();
actvoice._process_globals();
acthistorico._process_globals();
actcamara._process_globals();
actnotification._process_globals();
actscene._process_globals();
actfunciones._process_globals();
actcondicionados._process_globals();
actselectsensores._process_globals();
actconfigsensors._process_globals();
actmenu._process_globals();
actconfignotification._process_globals();
actwifis._process_globals();
actfreetxt._process_globals();
actconfigvoice._process_globals();
actconfigfreetxt._process_globals();
actcentrales._process_globals();
actconfigescenas._process_globals();
actconfigfunciones._process_globals();
actconfigcondicionados._process_globals();
actconfigcir._process_globals();
actpersianas._process_globals();
actconfigcomandoscomu._process_globals();
actconfigcomandos._process_globals();
actpersonalicon._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub process_globals";
 //BA.debugLineNum = 17;BA.debugLine="Dim MyLan As ServerSocket";
_mylan = new anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper();
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
}
