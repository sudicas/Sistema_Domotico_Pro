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

public class actconfigsensors extends Activity implements B4AActivity{
	public static actconfigsensors mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actconfigsensors");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actconfigsensors).");
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
		activityBA = new BA(this, layout, processBA, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actconfigsensors");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "arduino.automatizacion.excontrol.PRO.actconfigsensors", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actconfigsensors) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actconfigsensors) Resume **");
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
		return actconfigsensors.class;
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
        BA.LogInfo("** Activity (actconfigsensors) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (actconfigsensors) Resume **");
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
 //BA.debugLineNum = 21;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 25;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = Fals";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 29;BA.debugLine="Activity.LoadLayout (\"FrmSensores\")";
mostCurrent._activity.LoadLayout("FrmSensores",mostCurrent.activityBA);
 //BA.debugLineNum = 30;BA.debugLine="ListView1.height = 100%y";
mostCurrent._listview1.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 31;BA.debugLine="ListView1.Width =Activity.Width";
mostCurrent._listview1.setWidth(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 32;BA.debugLine="ListView1.Top =0";
mostCurrent._listview1.setTop((int) (0));
 //BA.debugLineNum = 41;BA.debugLine="Dim h As Int";
_h = 0;
 //BA.debugLineNum = 42;BA.debugLine="h=75dip";
_h = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75));
 //BA.debugLineNum = 45;BA.debugLine="ListView1.TwoLinesAndBitmap .ItemHeight =h";
mostCurrent._listview1.getTwoLinesAndBitmap().setItemHeight(_h);
 //BA.debugLineNum = 46;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.Width=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setWidth(_h);
 //BA.debugLineNum = 47;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.height=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setHeight(_h);
 //BA.debugLineNum = 49;BA.debugLine="ListView1.TwoLinesAndBitmap.Label .Left =h + h/9";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setLeft((int) (_h+_h/(double)9));
 //BA.debugLineNum = 50;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.height  =h/1.8";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setHeight((int) (_h/(double)1.8));
 //BA.debugLineNum = 51;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.Gravity = Bit.O";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.BOTTOM));
 //BA.debugLineNum = 53;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel .Left =Li";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setLeft(mostCurrent._listview1.getTwoLinesAndBitmap().Label.getLeft());
 //BA.debugLineNum = 54;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.height";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setHeight((int) (_h-mostCurrent._listview1.getTwoLinesAndBitmap().Label.getHeight()));
 //BA.debugLineNum = 55;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.top   = L";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setTop(mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.getHeight());
 //BA.debugLineNum = 56;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.Gravity =";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 77;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 78;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 79;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 63;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 65;BA.debugLine="If ValoresComunes.CloseApp =True Then";
if (mostCurrent._valorescomunes._closeapp==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 66;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 67;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 69;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = True";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 70;BA.debugLine="ActualizaValores";
_actualizavalores();
 }else {
 //BA.debugLineNum = 72;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 73;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
 };
 //BA.debugLineNum = 75;BA.debugLine="End Sub";
return "";
}
public static String  _actualizavalores() throws Exception{
int _i = 0;
 //BA.debugLineNum = 80;BA.debugLine="Sub ActualizaValores()";
 //BA.debugLineNum = 81;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 82;BA.debugLine="Dim i As Int";
_i = 0;
 //BA.debugLineNum = 84;BA.debugLine="For i =0 To ValoresComunes.Sensores .Length  -1";
{
final int step3 = 1;
final int limit3 = (int) (mostCurrent._valorescomunes._sensores.length-1);
_i = (int) (0) ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
 //BA.debugLineNum = 86;BA.debugLine="If ValoresComunes.Sensores (i).Rango >0 AND  Va";
if (mostCurrent._valorescomunes._sensores[_i].Rango>0 && mostCurrent._valorescomunes._sensores[_i].Rango<250) { 
mostCurrent._listview1.AddTwoLinesAndBitmap2(BA.ObjectToCharSequence(mostCurrent._valorescomunes._sensores[_i].Nombre),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"SNV")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._iconosensor(mostCurrent.activityBA,_i).getObject()),(Object)(_i));};
 }
};
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 17;BA.debugLine="Dim ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
anywheresoftware.b4a.objects.collections.List _lst = null;
int _seleccion = 0;
anywheresoftware.b4a.agraham.dialogs.InputDialog _dialog = null;
int _result = 0;
int _val = 0;
 //BA.debugLineNum = 93;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 94;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 95;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 96;BA.debugLine="Lst.Add (ValoresComunes.GetLanString (\"reg111\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg111")));
 //BA.debugLineNum = 97;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg112\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg112")));
 //BA.debugLineNum = 98;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"reg113\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg113")));
 //BA.debugLineNum = 100;BA.debugLine="Lst.Add(ValoresComunes.GetLanString (\"Cancel\"))";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel")));
 //BA.debugLineNum = 104;BA.debugLine="Dim Seleccion As Int";
_seleccion = 0;
 //BA.debugLineNum = 105;BA.debugLine="Seleccion =InputList(Lst,ValoresComunes.GetLanStr";
_seleccion = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),(int) (0),mostCurrent.activityBA);
 //BA.debugLineNum = 106;BA.debugLine="Select Case Seleccion";
switch (_seleccion) {
case 0: {
 //BA.debugLineNum = 108;BA.debugLine="Dim Dialog As InputDialog";
_dialog = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 110;BA.debugLine="Dialog.InputType =  Dialog.INPUT_TYPE_TEXT";
_dialog.setInputType(_dialog.INPUT_TYPE_TEXT);
 //BA.debugLineNum = 111;BA.debugLine="Dialog.Input = ValoresComunes.Sensores (Positio";
_dialog.setInput(mostCurrent._valorescomunes._sensores[_position].Nombre);
 //BA.debugLineNum = 112;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 113;BA.debugLine="Result = Dialog.Show ( ValoresComunes.GetLanStr";
_result = _dialog.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg111"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 114;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 115;BA.debugLine="ValoresComunes.Sensores (Position).nombre =Dia";
mostCurrent._valorescomunes._sensores[_position].Nombre = _dialog.getInput();
 //BA.debugLineNum = 116;BA.debugLine="ValoresComunes.GuardarConfigSensores";
mostCurrent._valorescomunes._guardarconfigsensores(mostCurrent.activityBA);
 };
 break; }
case 1: {
 //BA.debugLineNum = 119;BA.debugLine="Dim Dialog As InputDialog";
_dialog = new anywheresoftware.b4a.agraham.dialogs.InputDialog();
 //BA.debugLineNum = 121;BA.debugLine="Dialog.InputType =  Dialog.INPUT_TYPE_TEXT";
_dialog.setInputType(_dialog.INPUT_TYPE_TEXT);
 //BA.debugLineNum = 122;BA.debugLine="Dialog.Input = ValoresComunes.Sensores (Positio";
_dialog.setInput(mostCurrent._valorescomunes._sensores[_position].Descripcion);
 //BA.debugLineNum = 123;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 124;BA.debugLine="Result = Dialog.Show ( ValoresComunes.GetLanStr";
_result = _dialog.Show(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg90"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg112"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg83"),mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Cancel"),"",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 125;BA.debugLine="If Result=  DialogResponse.POSITIVE  Then";
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 126;BA.debugLine="ValoresComunes.Sensores(Position).Descripcion";
mostCurrent._valorescomunes._sensores[_position].Descripcion = _dialog.getInput();
 //BA.debugLineNum = 127;BA.debugLine="ValoresComunes.GuardarConfigSensores";
mostCurrent._valorescomunes._guardarconfigsensores(mostCurrent.activityBA);
 };
 break; }
case 2: {
 //BA.debugLineNum = 130;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 131;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 132;BA.debugLine="Lst.Add (\"Temperature\")";
_lst.Add((Object)("Temperature"));
 //BA.debugLineNum = 133;BA.debugLine="Lst.Add(\"Humidity \")";
_lst.Add((Object)("Humidity "));
 //BA.debugLineNum = 134;BA.debugLine="Lst.Add(\"Lux\")";
_lst.Add((Object)("Lux"));
 //BA.debugLineNum = 135;BA.debugLine="Lst.Add(\"Generic Sensor\")";
_lst.Add((Object)("Generic Sensor"));
 //BA.debugLineNum = 136;BA.debugLine="Lst.Add(\"Generic float\")";
_lst.Add((Object)("Generic float"));
 //BA.debugLineNum = 137;BA.debugLine="Lst.Add(\"tank\")";
_lst.Add((Object)("tank"));
 //BA.debugLineNum = 139;BA.debugLine="Dim Val As Int";
_val = 0;
 //BA.debugLineNum = 140;BA.debugLine="Val =InputList(Lst,ValoresComunes.GetLanStri";
_val = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg75")),(int) (mostCurrent._valorescomunes._sensores[_position].Rango-1),mostCurrent.activityBA);
 //BA.debugLineNum = 141;BA.debugLine="If Val>-1 Then";
if (_val>-1) { 
 //BA.debugLineNum = 142;BA.debugLine="Select Val";
switch (_val) {
case 5: {
 //BA.debugLineNum = 144;BA.debugLine="ValoresComunes.Sensores(Position).Rango   =";
mostCurrent._valorescomunes._sensores[_position].Rango = (int) (100);
 break; }
default: {
 //BA.debugLineNum = 146;BA.debugLine="ValoresComunes.Sensores(Position).Rango   =V";
mostCurrent._valorescomunes._sensores[_position].Rango = (int) (_val+1);
 break; }
}
;
 //BA.debugLineNum = 149;BA.debugLine="ValoresComunes.GuardarConfigSensores";
mostCurrent._valorescomunes._guardarconfigsensores(mostCurrent.activityBA);
 };
 break; }
}
;
 //BA.debugLineNum = 165;BA.debugLine="ActualizaValores";
_actualizavalores();
 //BA.debugLineNum = 166;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
}
