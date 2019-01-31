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

public class actconfigfreetxt extends Activity implements B4AActivity{
	public static actconfigfreetxt mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actconfigfreetxt");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actconfigfreetxt).");
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
		activityBA = new BA(this, layout, processBA, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actconfigfreetxt");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "arduino.automatizacion.excontrol.PRO.actconfigfreetxt", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actconfigfreetxt) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actconfigfreetxt) Resume **");
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
		return actconfigfreetxt.class;
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
        BA.LogInfo("** Activity (actconfigfreetxt) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (actconfigfreetxt) Resume **");
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
public anywheresoftware.b4a.objects.ButtonWrapper _cmdguardar = null;
public anywheresoftware.b4a.objects.ButtonWrapper _cmdnew = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _cmdterminar = null;
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
public arduino.automatizacion.excontrol.PRO.actmenu _actmenu = null;
public arduino.automatizacion.excontrol.PRO.actconfignotification _actconfignotification = null;
public arduino.automatizacion.excontrol.PRO.actwifis _actwifis = null;
public arduino.automatizacion.excontrol.PRO.actfreetxt _actfreetxt = null;
public arduino.automatizacion.excontrol.PRO.actconfigvoice _actconfigvoice = null;
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
 //BA.debugLineNum = 22;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 24;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = Fals";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 26;BA.debugLine="Activity.LoadLayout (\"FrmConfig2\")";
mostCurrent._activity.LoadLayout("FrmConfig2",mostCurrent.activityBA);
 //BA.debugLineNum = 27;BA.debugLine="Activity.Title =ValoresComunes.GetLanStringDefaul";
mostCurrent._activity.setTitle(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"CsS","Configure Status Screen")));
 //BA.debugLineNum = 29;BA.debugLine="Dim he As Int = 40dip";
_he = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40));
 //BA.debugLineNum = 31;BA.debugLine="ListView1.Height = Activity.Height - he";
mostCurrent._listview1.setHeight((int) (mostCurrent._activity.getHeight()-_he));
 //BA.debugLineNum = 32;BA.debugLine="ListView1.Width =Activity.Width";
mostCurrent._listview1.setWidth(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 36;BA.debugLine="CmdGuardar.Top=Activity.Height-he";
mostCurrent._cmdguardar.setTop((int) (mostCurrent._activity.getHeight()-_he));
 //BA.debugLineNum = 37;BA.debugLine="CmdGuardar.Left = Activity.Width   - (he+4dip)";
mostCurrent._cmdguardar.setLeft((int) (mostCurrent._activity.getWidth()-(_he+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4)))));
 //BA.debugLineNum = 38;BA.debugLine="CmdGuardar.Height =he";
mostCurrent._cmdguardar.setHeight(_he);
 //BA.debugLineNum = 39;BA.debugLine="CmdGuardar.Width =he' Activity.Width";
mostCurrent._cmdguardar.setWidth(_he);
 //BA.debugLineNum = 40;BA.debugLine="CmdGuardar.Text = \"\"'ValoresComunes.GetLanString";
mostCurrent._cmdguardar.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 41;BA.debugLine="CmdGuardar.SetBackgroundImage(ValoresComunes.CmdI";
mostCurrent._cmdguardar.SetBackgroundImage((android.graphics.Bitmap)(mostCurrent._valorescomunes._cmdimgsave(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 43;BA.debugLine="CmdTerminar.Top=Activity.Height-he";
mostCurrent._cmdterminar.setTop((int) (mostCurrent._activity.getHeight()-_he));
 //BA.debugLineNum = 44;BA.debugLine="CmdTerminar.Height =he";
mostCurrent._cmdterminar.setHeight(_he);
 //BA.debugLineNum = 45;BA.debugLine="CmdTerminar.Width =he";
mostCurrent._cmdterminar.setWidth(_he);
 //BA.debugLineNum = 46;BA.debugLine="CmdTerminar.Left =Activity.Width   - ((he  * 2)";
mostCurrent._cmdterminar.setLeft((int) (mostCurrent._activity.getWidth()-((_he*2)+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)))));
 //BA.debugLineNum = 47;BA.debugLine="CmdTerminar.SetBackgroundImage(ValoresComunes.Cmd";
mostCurrent._cmdterminar.SetBackgroundImage((android.graphics.Bitmap)(mostCurrent._valorescomunes._cmdimgback(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 48;BA.debugLine="CmdTerminar.Text = \"\"";
mostCurrent._cmdterminar.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 50;BA.debugLine="CmdNew.Top=Activity.Height-he";
mostCurrent._cmdnew.setTop((int) (mostCurrent._activity.getHeight()-_he));
 //BA.debugLineNum = 51;BA.debugLine="CmdNew.Height =he";
mostCurrent._cmdnew.setHeight(_he);
 //BA.debugLineNum = 52;BA.debugLine="CmdNew.Width =he";
mostCurrent._cmdnew.setWidth(_he);
 //BA.debugLineNum = 53;BA.debugLine="CmdNew.Left =Activity.Width   - ((he  * 3)   +12d";
mostCurrent._cmdnew.setLeft((int) (mostCurrent._activity.getWidth()-((_he*3)+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12)))));
 //BA.debugLineNum = 54;BA.debugLine="CmdNew.SetBackgroundImage(ValoresComunes.CmdImgNe";
mostCurrent._cmdnew.SetBackgroundImage((android.graphics.Bitmap)(mostCurrent._valorescomunes._cmdimgnew(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 55;BA.debugLine="CmdNew.Text = \"\"";
mostCurrent._cmdnew.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 78;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 79;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 80;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 60;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 61;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = True";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 62;BA.debugLine="If ValoresComunes.NombreSensor.IsInitialized = F";
if (mostCurrent._valorescomunes._nombresensor.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 63;BA.debugLine="If File.Exists ( File.DirInternal ,\"Sensores\"&";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Sensores"+mostCurrent._valorescomunes._central.Nombre)) { 
 //BA.debugLineNum = 65;BA.debugLine="ValoresComunes.CargaNombreSensores";
mostCurrent._valorescomunes._carganombresensores(mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 67;BA.debugLine="ValoresComunes.NombreSensor .Initialize";
mostCurrent._valorescomunes._nombresensor.Initialize();
 };
 };
 //BA.debugLineNum = 71;BA.debugLine="RefrescaPantalla";
_refrescapantalla();
 }else {
 //BA.debugLineNum = 73;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 74;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 };
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public static String  _cmdguardar_click() throws Exception{
 //BA.debugLineNum = 96;BA.debugLine="Sub CmdGuardar_Click";
 //BA.debugLineNum = 97;BA.debugLine="File.WriteList(File.DirInternal ,\"Sensores\" & Val";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Sensores"+mostCurrent._valorescomunes._central.Nombre,mostCurrent._valorescomunes._nombresensor);
 //BA.debugLineNum = 98;BA.debugLine="ToastMessageShow(\"Full saved\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Full saved"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 99;BA.debugLine="End Sub";
return "";
}
public static String  _cmdnew_click() throws Exception{
anywheresoftware.b4a.agraham.dialogs.InputDialog _dialog = null;
int _result = 0;
 //BA.debugLineNum = 100;BA.debugLine="Sub CmdNew_Click";
 //BA.debugLineNum = 103;BA.debugLine="Dim Dialog As InputDialog";
_dialog = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 105;BA.debugLine="Dialog.InputType =  Dialog.INPUT_TYPE_TEXT";
_dialog.setInputType(_dialog.INPUT_TYPE_TEXT);
 //BA.debugLineNum = 106;BA.debugLine="Dialog.Input = \"\"";
_dialog.setInput("");
 //BA.debugLineNum = 107;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 108;BA.debugLine="Result = Dialog.Show ( \"New Value\",\"Edit Name\",\"";
_result = _dialog.Show("New Value","Edit Name","Accept","Cancel","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 110;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 111;BA.debugLine="ValoresComunes.NombreSensor .Add (Dialog.Input)";
mostCurrent._valorescomunes._nombresensor.Add((Object)(_dialog.getInput()));
 //BA.debugLineNum = 112;BA.debugLine="If ActFreeTxt.Sensores.IsInitialized Then ActFr";
if (mostCurrent._actfreetxt._sensores.IsInitialized()) { 
mostCurrent._actfreetxt._sensores.Add((Object)(_dialog.getInput()));};
 //BA.debugLineNum = 114;BA.debugLine="ListView1.AddTwoLinesAndBitmap   (Dialog.Input,";
mostCurrent._listview1.AddTwoLinesAndBitmap(BA.ObjectToCharSequence(_dialog.getInput()),BA.ObjectToCharSequence(""),(android.graphics.Bitmap)(mostCurrent._valorescomunes._sensor(mostCurrent.activityBA).getObject()));
 };
 //BA.debugLineNum = 117;BA.debugLine="End Sub";
return "";
}
public static String  _cmdterminar_click() throws Exception{
 //BA.debugLineNum = 133;BA.debugLine="Sub CmdTerminar_Click";
 //BA.debugLineNum = 134;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 135;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Dim CmdGuardar As Button";
mostCurrent._cmdguardar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Dim CmdNew As Button";
mostCurrent._cmdnew = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Dim ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private CmdTerminar As Button";
mostCurrent._cmdterminar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
anywheresoftware.b4a.agraham.dialogs.InputDialog _dialog = null;
int _result = 0;
 //BA.debugLineNum = 84;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 85;BA.debugLine="Dim Dialog As InputDialog";
_dialog = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 87;BA.debugLine="Dialog.InputType =  Dialog.INPUT_TYPE_TEXT";
_dialog.setInputType(_dialog.INPUT_TYPE_TEXT);
 //BA.debugLineNum = 88;BA.debugLine="Dialog.Input = ValoresComunes.NombreSensor .Get";
_dialog.setInput(BA.ObjectToString(mostCurrent._valorescomunes._nombresensor.Get(_position)));
 //BA.debugLineNum = 89;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 90;BA.debugLine="Result = Dialog.Show ( \"New Value\",\"Edit Name\",\"";
_result = _dialog.Show("New Value","Edit Name","Accept","Cancel","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 91;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 92;BA.debugLine="ValoresComunes.NombreSensor .set (Position,Dial";
mostCurrent._valorescomunes._nombresensor.Set(_position,(Object)(_dialog.getInput()));
 };
 //BA.debugLineNum = 94;BA.debugLine="RefrescaPantalla";
_refrescapantalla();
 //BA.debugLineNum = 95;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _refrescapantalla() throws Exception{
int _c = 0;
 //BA.debugLineNum = 118;BA.debugLine="Sub RefrescaPantalla";
 //BA.debugLineNum = 119;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 120;BA.debugLine="Dim c As Int";
_c = 0;
 //BA.debugLineNum = 123;BA.debugLine="For c=0 To ValoresComunes.NombreSensor.Size -1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._valorescomunes._nombresensor.getSize()-1);
_c = (int) (0) ;
for (;(step3 > 0 && _c <= limit3) || (step3 < 0 && _c >= limit3) ;_c = ((int)(0 + _c + step3))  ) {
 //BA.debugLineNum = 125;BA.debugLine="ListView1.AddTwoLinesAndBitmap  (ValoresComunes.";
mostCurrent._listview1.AddTwoLinesAndBitmap(BA.ObjectToCharSequence(mostCurrent._valorescomunes._nombresensor.Get(_c)),BA.ObjectToCharSequence(""),(android.graphics.Bitmap)(mostCurrent._valorescomunes._sensor(mostCurrent.activityBA).getObject()));
 }
};
 //BA.debugLineNum = 128;BA.debugLine="End Sub";
return "";
}
}
