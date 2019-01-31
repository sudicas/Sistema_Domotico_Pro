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

public class actconfigcir extends Activity implements B4AActivity{
	public static actconfigcir mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actconfigcir");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actconfigcir).");
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
		activityBA = new BA(this, layout, processBA, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actconfigcir");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "arduino.automatizacion.excontrol.PRO.actconfigcir", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actconfigcir) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actconfigcir) Resume **");
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
		return actconfigcir.class;
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
        BA.LogInfo("** Activity (actconfigcir) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (actconfigcir) Resume **");
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
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _cmdguardar = null;
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
public arduino.automatizacion.excontrol.PRO.actconfigfreetxt _actconfigfreetxt = null;
public arduino.automatizacion.excontrol.PRO.actcentrales _actcentrales = null;
public arduino.automatizacion.excontrol.PRO.actconfigescenas _actconfigescenas = null;
public arduino.automatizacion.excontrol.PRO.actconfigfunciones _actconfigfunciones = null;
public arduino.automatizacion.excontrol.PRO.actconfigcondicionados _actconfigcondicionados = null;
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
 //BA.debugLineNum = 23;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 24;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = Fals";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 25;BA.debugLine="Activity.LoadLayout (\"frmconfig\")";
mostCurrent._activity.LoadLayout("frmconfig",mostCurrent.activityBA);
 //BA.debugLineNum = 27;BA.debugLine="Dim he As Int = 40dip";
_he = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40));
 //BA.debugLineNum = 29;BA.debugLine="ListView1.Height = Activity.Height - he";
mostCurrent._listview1.setHeight((int) (mostCurrent._activity.getHeight()-_he));
 //BA.debugLineNum = 30;BA.debugLine="ListView1.Width =Activity.Width";
mostCurrent._listview1.setWidth(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 34;BA.debugLine="CmdGuardar.Top=Activity.Height-he";
mostCurrent._cmdguardar.setTop((int) (mostCurrent._activity.getHeight()-_he));
 //BA.debugLineNum = 35;BA.debugLine="CmdGuardar.Left = Activity.Width   - (he+4dip)";
mostCurrent._cmdguardar.setLeft((int) (mostCurrent._activity.getWidth()-(_he+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4)))));
 //BA.debugLineNum = 36;BA.debugLine="CmdGuardar.Height =he";
mostCurrent._cmdguardar.setHeight(_he);
 //BA.debugLineNum = 37;BA.debugLine="CmdGuardar.Width =he' Activity.Width";
mostCurrent._cmdguardar.setWidth(_he);
 //BA.debugLineNum = 38;BA.debugLine="CmdGuardar.Text = \"\"'ValoresComunes.GetLanString";
mostCurrent._cmdguardar.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 39;BA.debugLine="CmdGuardar.SetBackgroundImage(ValoresComunes.CmdI";
mostCurrent._cmdguardar.SetBackgroundImage((android.graphics.Bitmap)(mostCurrent._valorescomunes._cmdimgsave(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 41;BA.debugLine="CmdTerminar.Top=Activity.Height-he";
mostCurrent._cmdterminar.setTop((int) (mostCurrent._activity.getHeight()-_he));
 //BA.debugLineNum = 42;BA.debugLine="CmdTerminar.Height =he";
mostCurrent._cmdterminar.setHeight(_he);
 //BA.debugLineNum = 43;BA.debugLine="CmdTerminar.Width =he";
mostCurrent._cmdterminar.setWidth(_he);
 //BA.debugLineNum = 44;BA.debugLine="CmdTerminar.Left =Activity.Width   - ((he  * 2)";
mostCurrent._cmdterminar.setLeft((int) (mostCurrent._activity.getWidth()-((_he*2)+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)))));
 //BA.debugLineNum = 45;BA.debugLine="CmdTerminar.SetBackgroundImage(ValoresComunes.Cmd";
mostCurrent._cmdterminar.SetBackgroundImage((android.graphics.Bitmap)(mostCurrent._valorescomunes._cmdimgback(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 46;BA.debugLine="CmdTerminar.Text = \"\"";
mostCurrent._cmdterminar.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 59;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 60;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 49;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 50;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = True";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 51;BA.debugLine="ActualizaValores";
_actualizavalores();
 }else {
 //BA.debugLineNum = 53;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 54;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 };
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return "";
}
public static String  _actualizavalores() throws Exception{
int _c = 0;
 //BA.debugLineNum = 116;BA.debugLine="Sub ActualizaValores()";
 //BA.debugLineNum = 117;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 118;BA.debugLine="Dim c As Int";
_c = 0;
 //BA.debugLineNum = 119;BA.debugLine="For c =0 To 29";
{
final int step3 = 1;
final int limit3 = (int) (29);
_c = (int) (0) ;
for (;(step3 > 0 && _c <= limit3) || (step3 < 0 && _c >= limit3) ;_c = ((int)(0 + _c + step3))  ) {
 //BA.debugLineNum = 124;BA.debugLine="ListView1.AddTwoLines (ValoresComunes.Circuitos";
mostCurrent._listview1.AddTwoLines(BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_c].Nombre),BA.ObjectToCharSequence(mostCurrent._valorescomunes._circuitos[_c].Descripcion));
 }
};
 //BA.debugLineNum = 126;BA.debugLine="End Sub";
return "";
}
public static String  _cmdguardar_click() throws Exception{
 //BA.debugLineNum = 127;BA.debugLine="Sub CmdGuardar_Click";
 //BA.debugLineNum = 129;BA.debugLine="ValoresComunes.GuardarConfigCircuitos";
mostCurrent._valorescomunes._guardarconfigcircuitos(mostCurrent.activityBA);
 //BA.debugLineNum = 130;BA.debugLine="ToastMessageShow(ValoresComunes.GetLanString (\"r";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg139")),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 133;BA.debugLine="End Sub";
return "";
}
public static String  _cmdterminar_click() throws Exception{
 //BA.debugLineNum = 135;BA.debugLine="Sub CmdTerminar_Click";
 //BA.debugLineNum = 136;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 137;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 17;BA.debugLine="Dim ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Dim CmdGuardar As Button";
mostCurrent._cmdguardar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private CmdTerminar As Button";
mostCurrent._cmdterminar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _seleccion = 0;
anywheresoftware.b4a.agraham.dialogs.InputDialog _dialog = null;
int _result = 0;
anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog _dialogo = null;
 //BA.debugLineNum = 65;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 66;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 67;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 68;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg111\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg111")));
 //BA.debugLineNum = 69;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg112\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg112")));
 //BA.debugLineNum = 70;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg113\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg113")));
 //BA.debugLineNum = 71;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg114\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg114")));
 //BA.debugLineNum = 72;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"Cancel\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel")));
 //BA.debugLineNum = 74;BA.debugLine="Dim Seleccion As Int";
_seleccion = 0;
 //BA.debugLineNum = 75;BA.debugLine="Seleccion =InputList(Lst,ValoresComunes.GetLanStr";
_seleccion = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),(int) (0),mostCurrent.activityBA);
 //BA.debugLineNum = 76;BA.debugLine="Select Case Seleccion";
switch (_seleccion) {
case 0: {
 //BA.debugLineNum = 78;BA.debugLine="Dim Dialog As InputDialog";
_dialog = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 80;BA.debugLine="Dialog.InputType =  Dialog.INPUT_TYPE_TEXT";
_dialog.setInputType(_dialog.INPUT_TYPE_TEXT);
 //BA.debugLineNum = 81;BA.debugLine="Dialog.Input = ValoresComunes.Circuitos (Positi";
_dialog.setInput(mostCurrent._valorescomunes._circuitos[_position].Nombre);
 //BA.debugLineNum = 82;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 83;BA.debugLine="Result = Dialog.Show ( ValoresComunes.GetLanStr";
_result = _dialog.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg111"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 84;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 85;BA.debugLine="ValoresComunes.Circuitos(Position).nombre =Dia";
mostCurrent._valorescomunes._circuitos[_position].Nombre = _dialog.getInput();
 };
 break; }
case 1: {
 //BA.debugLineNum = 88;BA.debugLine="Dim Dialog As InputDialog";
_dialog = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 90;BA.debugLine="Dialog.InputType =  Dialog.INPUT_TYPE_TEXT";
_dialog.setInputType(_dialog.INPUT_TYPE_TEXT);
 //BA.debugLineNum = 91;BA.debugLine="Dialog.Input = ValoresComunes.Circuitos (Positi";
_dialog.setInput(mostCurrent._valorescomunes._circuitos[_position].Descripcion);
 //BA.debugLineNum = 92;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 93;BA.debugLine="Result = Dialog.Show ( ValoresComunes.GetLanStr";
_result = _dialog.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg112"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 94;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 95;BA.debugLine="ValoresComunes.Circuitos(Position).Descripcion";
mostCurrent._valorescomunes._circuitos[_position].Descripcion = _dialog.getInput();
 };
 break; }
case 2: {
 //BA.debugLineNum = 98;BA.debugLine="Dim Dialogo As NumberDialog";
_dialogo = new anywheresoftware.b4a.agraham.dialogs.InputDialog.NumberDialog();
 //BA.debugLineNum = 99;BA.debugLine="Dialogo.Digits =3";
_dialogo.setDigits((int) (3));
 //BA.debugLineNum = 100;BA.debugLine="Dialogo.Number =ValoresComunes.Circuitos (Posit";
_dialogo.setNumber(mostCurrent._valorescomunes._circuitos[_position].Rango);
 //BA.debugLineNum = 102;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 103;BA.debugLine="Result = Dialogo.Show (ValoresComunes.GetLanStr";
_result = _dialogo.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 105;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 106;BA.debugLine="ValoresComunes.Circuitos(Position).Rango =Dial";
mostCurrent._valorescomunes._circuitos[_position].Rango = _dialogo.getNumber();
 };
 break; }
case 3: {
 //BA.debugLineNum = 109;BA.debugLine="ValoresComunes.Circuitos(Position).nombre =\"\"";
mostCurrent._valorescomunes._circuitos[_position].Nombre = "";
 //BA.debugLineNum = 110;BA.debugLine="ValoresComunes.Circuitos(Position).Descripcion";
mostCurrent._valorescomunes._circuitos[_position].Descripcion = "";
 //BA.debugLineNum = 111;BA.debugLine="ValoresComunes.Circuitos(Position).Rango =1";
mostCurrent._valorescomunes._circuitos[_position].Rango = (int) (1);
 break; }
}
;
 //BA.debugLineNum = 114;BA.debugLine="ActualizaValores";
_actualizavalores();
 //BA.debugLineNum = 115;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
}
