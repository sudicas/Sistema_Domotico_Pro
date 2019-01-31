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

public class actmosaico extends Activity implements B4AActivity{
	public static actmosaico mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actmosaico");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actmosaico).");
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
		activityBA = new BA(this, layout, processBA, "arduino.automatizacion.excontrol.PRO", "arduino.automatizacion.excontrol.PRO.actmosaico");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "arduino.automatizacion.excontrol.PRO.actmosaico", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actmosaico) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actmosaico) Resume **");
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
		return actmosaico.class;
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
        BA.LogInfo("** Activity (actmosaico) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (actmosaico) Resume **");
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
public static boolean _updatecentral = false;
public static boolean _forzar3g = false;
public static boolean _conectado = false;
public static int _centraltoconnect = 0;
public static wifi.MLwifi _wifi = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listview1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd2 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd3 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd4 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd5 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd6 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd7 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd8 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd9 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd10 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd11 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd12 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label10 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label7 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label8 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label9 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label11 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label12 = null;
public anywheresoftware.b4a.objects.TabHostWrapper _tabconfig = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd01 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd010 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd011 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd012 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd02 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd03 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd04 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd05 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd06 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd07 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd08 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _cmd09 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label01 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label010 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label011 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label012 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label02 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label03 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label04 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label05 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label06 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label07 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label08 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label09 = null;
public anywheresoftware.b4a.samples.httputils2.httputils2service _httputils2service = null;
public arduino.automatizacion.excontrol.PRO.main _main = null;
public arduino.automatizacion.excontrol.PRO.actcentral _actcentral = null;
public arduino.automatizacion.excontrol.PRO.valorescomunes _valorescomunes = null;
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

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
int _h = 0;
 //BA.debugLineNum = 85;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 87;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = Fals";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 92;BA.debugLine="Activity.LoadLayout(\"FrmTab\")";
mostCurrent._activity.LoadLayout("FrmTab",mostCurrent.activityBA);
 //BA.debugLineNum = 94;BA.debugLine="ListView1.Initialize (\"ListView1\")";
mostCurrent._listview1.Initialize(mostCurrent.activityBA,"ListView1");
 //BA.debugLineNum = 95;BA.debugLine="Dim h As Int";
_h = 0;
 //BA.debugLineNum = 96;BA.debugLine="h=75dip";
_h = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75));
 //BA.debugLineNum = 99;BA.debugLine="ListView1.TwoLinesAndBitmap .ItemHeight =h";
mostCurrent._listview1.getTwoLinesAndBitmap().setItemHeight(_h);
 //BA.debugLineNum = 100;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.Width=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setWidth(_h);
 //BA.debugLineNum = 101;BA.debugLine="ListView1.TwoLinesAndBitmap.ImageView.height=h";
mostCurrent._listview1.getTwoLinesAndBitmap().ImageView.setHeight(_h);
 //BA.debugLineNum = 103;BA.debugLine="ListView1.TwoLinesAndBitmap.Label .Left =h + h/9";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setLeft((int) (_h+_h/(double)9));
 //BA.debugLineNum = 104;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.height  =h/1.8";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setHeight((int) (_h/(double)1.8));
 //BA.debugLineNum = 105;BA.debugLine="ListView1.TwoLinesAndBitmap.Label.Gravity = Bit.O";
mostCurrent._listview1.getTwoLinesAndBitmap().Label.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.BOTTOM));
 //BA.debugLineNum = 107;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel .Left =Li";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setLeft(mostCurrent._listview1.getTwoLinesAndBitmap().Label.getLeft());
 //BA.debugLineNum = 108;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.height";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setHeight((int) (_h-mostCurrent._listview1.getTwoLinesAndBitmap().Label.getHeight()));
 //BA.debugLineNum = 109;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.top   = L";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setTop(mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.getHeight());
 //BA.debugLineNum = 110;BA.debugLine="ListView1.TwoLinesAndBitmap.SecondLabel.Gravity =";
mostCurrent._listview1.getTwoLinesAndBitmap().SecondLabel.setGravity(anywheresoftware.b4a.keywords.Common.Bit.Or(anywheresoftware.b4a.keywords.Common.Gravity.LEFT,anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 115;BA.debugLine="TabConfig.Width = Activity.Width";
mostCurrent._tabconfig.setWidth(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 116;BA.debugLine="TabConfig.Height =Activity.Height";
mostCurrent._tabconfig.setHeight(mostCurrent._activity.getHeight());
 //BA.debugLineNum = 118;BA.debugLine="If ValoresComunes.Centrales.Size > 1 Then";
if (mostCurrent._valorescomunes._centrales.getSize()>1) { 
 //BA.debugLineNum = 119;BA.debugLine="TabConfig.AddTabWithIcon2 (\"\"  ,ValoresComunes.M";
mostCurrent._tabconfig.AddTabWithIcon2("",(android.graphics.Bitmap)(mostCurrent._valorescomunes._mnuhome(mostCurrent.activityBA).getObject()),(android.graphics.Bitmap)(mostCurrent._valorescomunes._mnuhome(mostCurrent.activityBA).getObject()),(android.view.View)(mostCurrent._listview1.getObject()));
 }else {
 };
 //BA.debugLineNum = 126;BA.debugLine="TabConfig.AddTabWithIcon (\"\" ,ValoresComunes.MnuC";
mostCurrent._tabconfig.AddTabWithIcon(mostCurrent.activityBA,"",(android.graphics.Bitmap)(mostCurrent._valorescomunes._mnucircuitos(mostCurrent.activityBA).getObject()),(android.graphics.Bitmap)(mostCurrent._valorescomunes._mnucircuitos(mostCurrent.activityBA).getObject()),"FrmMosaico.bal");
 //BA.debugLineNum = 127;BA.debugLine="TabConfig.AddTabWithIcon (\"\", ValoresComunes.MnuC";
mostCurrent._tabconfig.AddTabWithIcon(mostCurrent.activityBA,"",(android.graphics.Bitmap)(mostCurrent._valorescomunes._mnuconfiglt(mostCurrent.activityBA).getObject()),(android.graphics.Bitmap)(mostCurrent._valorescomunes._mnuconfiglt(mostCurrent.activityBA).getObject()),"FrmMosaico2.bal");
 //BA.debugLineNum = 132;BA.debugLine="Cmd1.Bitmap =ValoresComunes.Bombillaon";
mostCurrent._cmd1.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._bombillaon(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 133;BA.debugLine="Cmd2.Bitmap =ValoresComunes.Temperatura";
mostCurrent._cmd2.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._temperatura(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 134;BA.debugLine="Cmd3.Bitmap =ValoresComunes.scene2 'cambio";
mostCurrent._cmd3.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._scene2(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 136;BA.debugLine="Cmd4.Bitmap = ValoresComunes.SensorGenerico";
mostCurrent._cmd4.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._sensorgenerico(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 141;BA.debugLine="Cmd5.Bitmap =ValoresComunes.Funciones";
mostCurrent._cmd5.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._funciones(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 142;BA.debugLine="Cmd6.Bitmap =ValoresComunes.CheckOn";
mostCurrent._cmd6.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._checkon(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 143;BA.debugLine="Cmd7.Bitmap =ValoresComunes.AlmOff";
mostCurrent._cmd7.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._almoff(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 144;BA.debugLine="Cmd8.Bitmap =ValoresComunes.Comando";
mostCurrent._cmd8.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._comando(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 145;BA.debugLine="Cmd9.Bitmap =ValoresComunes.ImgGrafico";
mostCurrent._cmd9.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._imggrafico(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 146;BA.debugLine="Cmd10.Bitmap =ValoresComunes.Sensor";
mostCurrent._cmd10.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._sensor(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 147;BA.debugLine="Cmd11.Bitmap =ValoresComunes.voice";
mostCurrent._cmd11.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._voice(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 148;BA.debugLine="Cmd12.Bitmap =ValoresComunes.ImgEnd";
mostCurrent._cmd12.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._imgend(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 150;BA.debugLine="Label1.Text = ValoresComunes.GetLanString (\"reg15";
mostCurrent._label1.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg151").toUpperCase()));
 //BA.debugLineNum = 151;BA.debugLine="Label2.Text = ValoresComunes.GetLanString (\"Senso";
mostCurrent._label2.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Sensor").toUpperCase()));
 //BA.debugLineNum = 152;BA.debugLine="Label3.Text = ValoresComunes.GetLanString (\"reg15";
mostCurrent._label3.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg152").toUpperCase()));
 //BA.debugLineNum = 154;BA.debugLine="Label4.Text = ValoresComunes.GetLanStringDefault";
mostCurrent._label4.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"reg133","Setpoint").toUpperCase()));
 //BA.debugLineNum = 155;BA.debugLine="Label5.Text = ValoresComunes.GetLanStringDefault";
mostCurrent._label5.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"Function","Function").toUpperCase()));
 //BA.debugLineNum = 156;BA.debugLine="Label6.Text = ValoresComunes.GetLanString (\"reg13";
mostCurrent._label6.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg131").toUpperCase()));
 //BA.debugLineNum = 158;BA.debugLine="Label7.Text = ValoresComunes.GetLanString (\"reg13";
mostCurrent._label7.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg135").toUpperCase()));
 //BA.debugLineNum = 159;BA.debugLine="Label8.Text = ValoresComunes.GetLanString (\"reg13";
mostCurrent._label8.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg132").toUpperCase()));
 //BA.debugLineNum = 160;BA.debugLine="Label9.Text = ValoresComunes.GetLanStringDefault";
mostCurrent._label9.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"Chart","Chart").toUpperCase()));
 //BA.debugLineNum = 162;BA.debugLine="Label10.Text = ValoresComunes.GetLanStringDefault";
mostCurrent._label10.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"Status","Status").toUpperCase()));
 //BA.debugLineNum = 163;BA.debugLine="Label11.Text = ValoresComunes.GetLanString (\"reg1";
mostCurrent._label11.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg155").toUpperCase()));
 //BA.debugLineNum = 164;BA.debugLine="Label12.Text = ValoresComunes.GetLanStringDefault";
mostCurrent._label12.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"close","Close").toUpperCase()));
 //BA.debugLineNum = 168;BA.debugLine="Cmd01.Bitmap =ValoresComunes.Reloj";
mostCurrent._cmd01.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._reloj(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 169;BA.debugLine="Label01.Text = ValoresComunes.GetLanString (\"reg1";
mostCurrent._label01.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg153").toUpperCase()));
 //BA.debugLineNum = 171;BA.debugLine="Cmd02.Bitmap =ValoresComunes.scene2";
mostCurrent._cmd02.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._scene2(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 172;BA.debugLine="Label02.Text =ValoresComunes.GetLanString (\"reg15";
mostCurrent._label02.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg152").toUpperCase()));
 //BA.debugLineNum = 174;BA.debugLine="Cmd03.Bitmap =ValoresComunes.ConfigUp";
mostCurrent._cmd03.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._configup(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 175;BA.debugLine="Label03.Text = ValoresComunes.GetLanStringDefault";
mostCurrent._label03.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"Backup","Backup").toUpperCase()));
 //BA.debugLineNum = 178;BA.debugLine="Cmd04.Bitmap =ValoresComunes.Persiana";
mostCurrent._cmd04.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._persiana(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 179;BA.debugLine="Label04.Text = ValoresComunes.GetLanStringDefault";
mostCurrent._label04.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"sTs","Shutter").toUpperCase()));
 //BA.debugLineNum = 181;BA.debugLine="Cmd05.Bitmap =ValoresComunes.Bombillaon 'cambio";
mostCurrent._cmd05.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._bombillaon(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 182;BA.debugLine="Label05.Text =ValoresComunes.GetLanStringDefault";
mostCurrent._label05.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"reg151","Circuit").toUpperCase()));
 //BA.debugLineNum = 184;BA.debugLine="Cmd06.Bitmap =ValoresComunes.Temperatura";
mostCurrent._cmd06.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._temperatura(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 185;BA.debugLine="Label06.Text =ValoresComunes.GetLanString (\"Senso";
mostCurrent._label06.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"Sensor").toUpperCase()));
 //BA.debugLineNum = 188;BA.debugLine="Cmd07.Bitmap =ValoresComunes.CheckOn";
mostCurrent._cmd07.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._checkon(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 189;BA.debugLine="Label07.Text =ValoresComunes.GetLanString (\"reg13";
mostCurrent._label07.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg131").toUpperCase()));
 //BA.debugLineNum = 191;BA.debugLine="Cmd08.Bitmap =ValoresComunes.Comando";
mostCurrent._cmd08.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._comando(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 192;BA.debugLine="Label08.Text =ValoresComunes.GetLanString (\"reg13";
mostCurrent._label08.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg132").toUpperCase()));
 //BA.debugLineNum = 196;BA.debugLine="Cmd09.Bitmap =ValoresComunes.AlmOff";
mostCurrent._cmd09.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._almoff(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 197;BA.debugLine="Label09.Text =ValoresComunes.GetLanString (\"reg13";
mostCurrent._label09.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg135").toUpperCase()));
 //BA.debugLineNum = 199;BA.debugLine="Cmd010.Bitmap =ValoresComunes.Voice";
mostCurrent._cmd010.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._voice(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 200;BA.debugLine="Label010.Text = ValoresComunes.GetLanString (\"reg";
mostCurrent._label010.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg155").toUpperCase()));
 //BA.debugLineNum = 205;BA.debugLine="Cmd011.Bitmap = ValoresComunes.SensorGenerico";
mostCurrent._cmd011.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._sensorgenerico(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 206;BA.debugLine="Label011.Text = ValoresComunes.GetLanStringDefaul";
mostCurrent._label011.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"reg133","Setpoint").toUpperCase()));
 //BA.debugLineNum = 208;BA.debugLine="Cmd012.Bitmap =ValoresComunes.Config";
mostCurrent._cmd012.setBitmap((android.graphics.Bitmap)(mostCurrent._valorescomunes._config(mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 209;BA.debugLine="Label012.Text =ValoresComunes.GetLanString (\"reg1";
mostCurrent._label012.setText(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg104").toUpperCase()));
 //BA.debugLineNum = 210;BA.debugLine="If Activity.Height > Activity.Width Then";
if (mostCurrent._activity.getHeight()>mostCurrent._activity.getWidth()) { 
 //BA.debugLineNum = 211;BA.debugLine="PantallaVertical";
_pantallavertical();
 }else {
 //BA.debugLineNum = 213;BA.debugLine="PantallaHorizontal";
_pantallahorizontal();
 };
 //BA.debugLineNum = 217;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 1023;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 1025;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 1027;BA.debugLine="ValoresComunes.CloseApp =True";
mostCurrent._valorescomunes._closeapp = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 1028;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 1029;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 1032;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 650;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 652;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
int _li = 0;
 //BA.debugLineNum = 618;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 619;BA.debugLine="If ValoresComunes.CloseApp =True Then";
if (mostCurrent._valorescomunes._closeapp==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 620;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 621;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 623;BA.debugLine="If ValoresComunes.Centrales .IsInitialized = True";
if (mostCurrent._valorescomunes._centrales.IsInitialized()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 624;BA.debugLine="If Conectado= False Then IniciarConexionCentral(";
if (_conectado==anywheresoftware.b4a.keywords.Common.False) { 
_iniciarconexioncentral(_centraltoconnect);};
 //BA.debugLineNum = 627;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 628;BA.debugLine="Dim li As Int";
_li = 0;
 //BA.debugLineNum = 630;BA.debugLine="If ListView1.Size <> ValoresComunes.Centrales .S";
if (mostCurrent._listview1.getSize()!=mostCurrent._valorescomunes._centrales.getSize() || _updatecentral) { 
 //BA.debugLineNum = 631;BA.debugLine="ValoresComunes.IniciarIconosCentrales";
mostCurrent._valorescomunes._iniciariconoscentrales(mostCurrent.activityBA);
 };
 //BA.debugLineNum = 635;BA.debugLine="ListView1.Clear";
mostCurrent._listview1.Clear();
 //BA.debugLineNum = 638;BA.debugLine="For li = 0 To ValoresComunes.Centrales.Size -1";
{
final int step13 = 1;
final int limit13 = (int) (mostCurrent._valorescomunes._centrales.getSize()-1);
_li = (int) (0) ;
for (;(step13 > 0 && _li <= limit13) || (step13 < 0 && _li >= limit13) ;_li = ((int)(0 + _li + step13))  ) {
 //BA.debugLineNum = 639;BA.debugLine="ListView1.AddTwoLinesAndBitmap (ValoresComunes.";
mostCurrent._listview1.AddTwoLinesAndBitmap(BA.ObjectToCharSequence(mostCurrent._valorescomunes._centrales.Get(_li)),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"PC")),(android.graphics.Bitmap)(mostCurrent._valorescomunes._getimgdevice(mostCurrent.activityBA,BA.ObjectToString(mostCurrent._valorescomunes._centrales.Get(_li)),anywheresoftware.b4a.keywords.Common.True).getObject()));
 }
};
 }else {
 //BA.debugLineNum = 645;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 648;BA.debugLine="End Sub";
return "";
}
public static String  _addnewssid() throws Exception{
anywheresoftware.b4a.objects.collections.List _l = null;
 //BA.debugLineNum = 975;BA.debugLine="Sub AddNewSsid( )";
 //BA.debugLineNum = 976;BA.debugLine="Dim l As List";
_l = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 977;BA.debugLine="If File.Exists ( File.DirInternal ,\"SSID\" & Valor";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SSID"+mostCurrent._valorescomunes._central.Nombre)) { 
 //BA.debugLineNum = 978;BA.debugLine="l = File.ReadList (File.DirInternal ,\"SSID\" & Va";
_l = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SSID"+mostCurrent._valorescomunes._central.Nombre);
 }else {
 //BA.debugLineNum = 980;BA.debugLine="l.Initialize";
_l.Initialize();
 };
 //BA.debugLineNum = 983;BA.debugLine="l.Add (wifi.ssid)";
_l.Add((Object)(_wifi.SSID()));
 //BA.debugLineNum = 984;BA.debugLine="File.WriteList (File.DirInternal ,\"SSID\" & Valore";
anywheresoftware.b4a.keywords.Common.File.WriteList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SSID"+mostCurrent._valorescomunes._central.Nombre,_l);
 //BA.debugLineNum = 986;BA.debugLine="End Sub";
return "";
}
public static String  _cargaipservidor() throws Exception{
anywheresoftware.b4a.samples.httputils2.httpjob _job1 = null;
String _url = "";
String _xml = "";
anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest _request = null;
 //BA.debugLineNum = 832;BA.debugLine="Sub CargaIpServidor";
 //BA.debugLineNum = 833;BA.debugLine="Private job1 As HttpJob";
_job1 = new anywheresoftware.b4a.samples.httputils2.httpjob();
 //BA.debugLineNum = 834;BA.debugLine="If ValoresComunes.Central .mail.Contains (\"@\") An";
if (mostCurrent._valorescomunes._central.mail.contains("@") && mostCurrent._valorescomunes._central.Password.length()==8) { 
 //BA.debugLineNum = 837;BA.debugLine="If ValoresComunes.OldMail = ValoresComunes.centr";
if ((mostCurrent._valorescomunes._oldmail).equals(mostCurrent._valorescomunes._central.mail) && (mostCurrent._valorescomunes._central.Password).equals(mostCurrent._valorescomunes._oldpass)) { 
 //BA.debugLineNum = 838;BA.debugLine="ValoresComunes.Ip= ValoresComunes.OldExtIp";
mostCurrent._valorescomunes._ip = mostCurrent._valorescomunes._oldextip;
 //BA.debugLineNum = 839;BA.debugLine="ValoresComunes.Puerto=ValoresComunes.Central .P";
mostCurrent._valorescomunes._puerto = mostCurrent._valorescomunes._central.PuertoOut;
 //BA.debugLineNum = 840;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 }else {
 //BA.debugLineNum = 844;BA.debugLine="job1.Initialize(\"Job1\",  Me)";
_job1._initialize(processBA,"Job1",actmosaico.getObject());
 //BA.debugLineNum = 846;BA.debugLine="Dim URL As String";
_url = "";
 //BA.debugLineNum = 847;BA.debugLine="URL = \"http://ex-connect.es/IpGet.asmx\"";
_url = "http://ex-connect.es/IpGet.asmx";
 //BA.debugLineNum = 848;BA.debugLine="Dim XML As String";
_xml = "";
 //BA.debugLineNum = 851;BA.debugLine="XML = XML & \"<soap12:Envelope xmlns:xsi='http:/";
_xml = _xml+"<soap12:Envelope xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:soap12='http://www.w3.org/2003/05/soap-envelope'>";
 //BA.debugLineNum = 852;BA.debugLine="XML = XML & \"<soap12:Body>\"";
_xml = _xml+"<soap12:Body>";
 //BA.debugLineNum = 853;BA.debugLine="XML = XML & \"<GetIp xmlns='http://ex-connect.es";
_xml = _xml+"<GetIp xmlns='http://ex-connect.es/'>";
 //BA.debugLineNum = 854;BA.debugLine="XML = XML & \"<mail>\" & ValoresComunes.Central .";
_xml = _xml+"<mail>"+mostCurrent._valorescomunes._central.mail+"</mail>";
 //BA.debugLineNum = 855;BA.debugLine="XML = XML & \"<key>\" & ValoresComunes.Central .P";
_xml = _xml+"<key>"+mostCurrent._valorescomunes._central.Password+"</key>";
 //BA.debugLineNum = 856;BA.debugLine="XML = XML & \"</GetIp>\"";
_xml = _xml+"</GetIp>";
 //BA.debugLineNum = 857;BA.debugLine="XML = XML & \"</soap12:Body>\"";
_xml = _xml+"</soap12:Body>";
 //BA.debugLineNum = 858;BA.debugLine="XML = XML & \"</soap12:Envelope>\"";
_xml = _xml+"</soap12:Envelope>";
 //BA.debugLineNum = 859;BA.debugLine="XML = XML.Replace(\"'\", Chr(34))";
_xml = _xml.replace("'",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34))));
 //BA.debugLineNum = 860;BA.debugLine="Dim request As OkHttpRequest";
_request = new anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest();
 //BA.debugLineNum = 861;BA.debugLine="request.InitializePost2( URL, XML.GetBytes(\"UTF";
_request.InitializePost2(_url,_xml.getBytes("UTF8"));
 //BA.debugLineNum = 864;BA.debugLine="request.SetHeader(\"Content-Type\", \"text/xml\")";
_request.SetHeader("Content-Type","text/xml");
 //BA.debugLineNum = 865;BA.debugLine="request.Timeout = 10000";
_request.setTimeout((int) (10000));
 //BA.debugLineNum = 867;BA.debugLine="job1.Initialize(\"Job1\", Me)";
_job1._initialize(processBA,"Job1",actmosaico.getObject());
 //BA.debugLineNum = 868;BA.debugLine="job1.PostString(URL, XML)";
_job1._poststring(_url,_xml);
 //BA.debugLineNum = 869;BA.debugLine="job1.GetRequest.SetHeader( \"Job1\",  URL)";
_job1._getrequest().SetHeader("Job1",_url);
 //BA.debugLineNum = 870;BA.debugLine="job1.GetRequest.SetContentType(\"text/xml; chars";
_job1._getrequest().SetContentType("text/xml; charset=utf-8");
 };
 }else {
 //BA.debugLineNum = 879;BA.debugLine="ValoresComunes.ip  = ValoresComunes.Central .IpO";
mostCurrent._valorescomunes._ip = mostCurrent._valorescomunes._central.IpOut;
 //BA.debugLineNum = 880;BA.debugLine="ValoresComunes.Puerto=ValoresComunes.Central.Pue";
mostCurrent._valorescomunes._puerto = mostCurrent._valorescomunes._central.PuertoOut;
 //BA.debugLineNum = 882;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 };
 //BA.debugLineNum = 884;BA.debugLine="End Sub";
return "";
}
public static String  _cmd01_click() throws Exception{
 //BA.debugLineNum = 771;BA.debugLine="Sub Cmd01_Click";
 //BA.debugLineNum = 773;BA.debugLine="ActMenu.TypeConfig =1";
mostCurrent._actmenu._typeconfig = (int) (1);
 //BA.debugLineNum = 774;BA.debugLine="StartActivity(ActMenu)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actmenu.getObject()));
 //BA.debugLineNum = 775;BA.debugLine="End Sub";
return "";
}
public static String  _cmd010_click() throws Exception{
 //BA.debugLineNum = 735;BA.debugLine="Sub Cmd010_Click";
 //BA.debugLineNum = 736;BA.debugLine="StartActivity(ActConfigVoice)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfigvoice.getObject()));
 //BA.debugLineNum = 737;BA.debugLine="End Sub";
return "";
}
public static String  _cmd011_click() throws Exception{
 //BA.debugLineNum = 731;BA.debugLine="Sub Cmd011_Click";
 //BA.debugLineNum = 733;BA.debugLine="StartActivity(ActConfigSetponint)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfigsetponint.getObject()));
 //BA.debugLineNum = 734;BA.debugLine="End Sub";
return "";
}
public static String  _cmd012_click() throws Exception{
 //BA.debugLineNum = 725;BA.debugLine="Sub Cmd012_Click";
 //BA.debugLineNum = 728;BA.debugLine="ActMenu.TypeConfig =3";
mostCurrent._actmenu._typeconfig = (int) (3);
 //BA.debugLineNum = 729;BA.debugLine="StartActivity(ActMenu)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actmenu.getObject()));
 //BA.debugLineNum = 730;BA.debugLine="End Sub";
return "";
}
public static String  _cmd02_click() throws Exception{
 //BA.debugLineNum = 768;BA.debugLine="Sub Cmd02_Click";
 //BA.debugLineNum = 769;BA.debugLine="StartActivity( ActConfigEscenas)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfigescenas.getObject()));
 //BA.debugLineNum = 770;BA.debugLine="End Sub";
return "";
}
public static String  _cmd03_click() throws Exception{
 //BA.debugLineNum = 763;BA.debugLine="Sub Cmd03_Click";
 //BA.debugLineNum = 765;BA.debugLine="ActMenu.TypeConfig =2";
mostCurrent._actmenu._typeconfig = (int) (2);
 //BA.debugLineNum = 766;BA.debugLine="StartActivity(ActMenu)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actmenu.getObject()));
 //BA.debugLineNum = 767;BA.debugLine="End Sub";
return "";
}
public static String  _cmd04_click() throws Exception{
 //BA.debugLineNum = 760;BA.debugLine="Sub Cmd04_Click";
 //BA.debugLineNum = 761;BA.debugLine="StartActivity(ActPersianas)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actpersianas.getObject()));
 //BA.debugLineNum = 762;BA.debugLine="End Sub";
return "";
}
public static String  _cmd05_click() throws Exception{
 //BA.debugLineNum = 757;BA.debugLine="Sub Cmd05_Click";
 //BA.debugLineNum = 758;BA.debugLine="StartActivity(ActConfigCir)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfigcir.getObject()));
 //BA.debugLineNum = 759;BA.debugLine="End Sub";
return "";
}
public static String  _cmd06_click() throws Exception{
 //BA.debugLineNum = 753;BA.debugLine="Sub Cmd06_Click";
 //BA.debugLineNum = 755;BA.debugLine="StartActivity(ActConfigSensors)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfigsensors.getObject()));
 //BA.debugLineNum = 756;BA.debugLine="End Sub";
return "";
}
public static String  _cmd07_click() throws Exception{
 //BA.debugLineNum = 749;BA.debugLine="Sub Cmd07_Click";
 //BA.debugLineNum = 750;BA.debugLine="StartActivity(ActConfigCondicionados)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfigcondicionados.getObject()));
 //BA.debugLineNum = 752;BA.debugLine="End Sub";
return "";
}
public static String  _cmd08_click() throws Exception{
 //BA.debugLineNum = 746;BA.debugLine="Sub Cmd08_Click";
 //BA.debugLineNum = 747;BA.debugLine="StartActivity(ActConfigComandos)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfigcomandos.getObject()));
 //BA.debugLineNum = 748;BA.debugLine="End Sub";
return "";
}
public static String  _cmd09_click() throws Exception{
 //BA.debugLineNum = 738;BA.debugLine="Sub Cmd09_Click";
 //BA.debugLineNum = 740;BA.debugLine="StartActivity(ActConfigNotification)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconfignotification.getObject()));
 //BA.debugLineNum = 744;BA.debugLine="End Sub";
return "";
}
public static String  _cmd1_click() throws Exception{
 //BA.debugLineNum = 718;BA.debugLine="Sub Cmd1_Click";
 //BA.debugLineNum = 721;BA.debugLine="StartActivity(ActCircuit)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actcircuit.getObject()));
 //BA.debugLineNum = 722;BA.debugLine="End Sub";
return "";
}
public static String  _cmd10_click() throws Exception{
 //BA.debugLineNum = 667;BA.debugLine="Sub Cmd10_Click";
 //BA.debugLineNum = 671;BA.debugLine="If File.Exists ( File.DirInternal ,\"Sensores\" & V";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"Sensores"+mostCurrent._valorescomunes._central.Nombre)) { 
 //BA.debugLineNum = 672;BA.debugLine="StartActivity(ActFreeTxt)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actfreetxt.getObject()));
 }else {
 //BA.debugLineNum = 674;BA.debugLine="ToastMessageShow(ValoresComunes.GetLanString (\"r";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg127")),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 677;BA.debugLine="End Sub";
return "";
}
public static String  _cmd11_click() throws Exception{
 //BA.debugLineNum = 662;BA.debugLine="Sub Cmd11_Click";
 //BA.debugLineNum = 663;BA.debugLine="StartActivity(ActVoice)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actvoice.getObject()));
 //BA.debugLineNum = 666;BA.debugLine="End Sub";
return "";
}
public static String  _cmd12_click() throws Exception{
 //BA.debugLineNum = 656;BA.debugLine="Sub Cmd12_Click";
 //BA.debugLineNum = 657;BA.debugLine="ValoresComunes.CloseApp =True";
mostCurrent._valorescomunes._closeapp = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 658;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 661;BA.debugLine="End Sub";
return "";
}
public static String  _cmd2_click() throws Exception{
 //BA.debugLineNum = 713;BA.debugLine="Sub Cmd2_Click";
 //BA.debugLineNum = 715;BA.debugLine="StartActivity(ActSensors)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actsensors.getObject()));
 //BA.debugLineNum = 717;BA.debugLine="End Sub";
return "";
}
public static String  _cmd3_click() throws Exception{
 //BA.debugLineNum = 709;BA.debugLine="Sub Cmd3_Click";
 //BA.debugLineNum = 710;BA.debugLine="StartActivity(ActScene)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actscene.getObject()));
 //BA.debugLineNum = 712;BA.debugLine="End Sub";
return "";
}
public static String  _cmd4_click() throws Exception{
 //BA.debugLineNum = 700;BA.debugLine="Sub Cmd4_Click";
 //BA.debugLineNum = 702;BA.debugLine="If ValoresComunes.SetPoint .Size > 0 Then";
if (mostCurrent._valorescomunes._setpoint.getSize()>0) { 
 //BA.debugLineNum = 703;BA.debugLine="StartActivity(ActConsignas)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actconsignas.getObject()));
 }else {
 //BA.debugLineNum = 706;BA.debugLine="ToastMessageShow(ValoresComunes.GetLanString (\"r";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg127")),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 708;BA.debugLine="End Sub";
return "";
}
public static String  _cmd5_click() throws Exception{
 //BA.debugLineNum = 696;BA.debugLine="Sub Cmd5_Click";
 //BA.debugLineNum = 697;BA.debugLine="StartActivity(ActFunciones)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actfunciones.getObject()));
 //BA.debugLineNum = 699;BA.debugLine="End Sub";
return "";
}
public static String  _cmd6_click() throws Exception{
 //BA.debugLineNum = 691;BA.debugLine="Sub Cmd6_Click";
 //BA.debugLineNum = 694;BA.debugLine="StartActivity(ActCondicionados)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actcondicionados.getObject()));
 //BA.debugLineNum = 695;BA.debugLine="End Sub";
return "";
}
public static String  _cmd7_click() throws Exception{
 //BA.debugLineNum = 686;BA.debugLine="Sub Cmd7_Click";
 //BA.debugLineNum = 689;BA.debugLine="StartActivity(ActNotification)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actnotification.getObject()));
 //BA.debugLineNum = 690;BA.debugLine="End Sub";
return "";
}
public static String  _cmd8_click() throws Exception{
 //BA.debugLineNum = 682;BA.debugLine="Sub Cmd8_Click";
 //BA.debugLineNum = 684;BA.debugLine="StartActivity(ActComandos)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actcomandos.getObject()));
 //BA.debugLineNum = 685;BA.debugLine="End Sub";
return "";
}
public static String  _cmd9_click() throws Exception{
 //BA.debugLineNum = 679;BA.debugLine="Sub Cmd9_Click";
 //BA.debugLineNum = 680;BA.debugLine="StartActivity(ActSelectSensores)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actselectsensores.getObject()));
 //BA.debugLineNum = 681;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 22;BA.debugLine="Dim ListView1 As ListView";
mostCurrent._listview1 = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private Cmd1 As ImageView";
mostCurrent._cmd1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private Cmd2 As ImageView";
mostCurrent._cmd2 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private Cmd3 As ImageView";
mostCurrent._cmd3 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private Cmd4 As ImageView";
mostCurrent._cmd4 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private Cmd5 As ImageView";
mostCurrent._cmd5 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private Cmd6 As ImageView";
mostCurrent._cmd6 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private Cmd7 As ImageView";
mostCurrent._cmd7 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private Cmd8 As ImageView";
mostCurrent._cmd8 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private Cmd9 As ImageView";
mostCurrent._cmd9 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private Cmd10 As ImageView";
mostCurrent._cmd10 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private Cmd11 As ImageView";
mostCurrent._cmd11 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private Cmd12 As ImageView";
mostCurrent._cmd12 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private Label2 As Label";
mostCurrent._label2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private Label3 As Label";
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private Label10 As Label";
mostCurrent._label10 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private Label4 As Label";
mostCurrent._label4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private Label5 As Label";
mostCurrent._label5 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private Label6 As Label";
mostCurrent._label6 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private Label7 As Label";
mostCurrent._label7 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private Label8 As Label";
mostCurrent._label8 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private Label9 As Label";
mostCurrent._label9 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private Label11 As Label";
mostCurrent._label11 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private Label12 As Label";
mostCurrent._label12 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private TabConfig As TabHost";
mostCurrent._tabconfig = new anywheresoftware.b4a.objects.TabHostWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private Cmd01 As ImageView";
mostCurrent._cmd01 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private Cmd010 As ImageView";
mostCurrent._cmd010 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private Cmd011 As ImageView";
mostCurrent._cmd011 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private Cmd012 As ImageView";
mostCurrent._cmd012 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 55;BA.debugLine="Private Cmd02 As ImageView";
mostCurrent._cmd02 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 56;BA.debugLine="Private Cmd03 As ImageView";
mostCurrent._cmd03 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 57;BA.debugLine="Private Cmd04 As ImageView";
mostCurrent._cmd04 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 58;BA.debugLine="Private Cmd05 As ImageView";
mostCurrent._cmd05 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Private Cmd06 As ImageView";
mostCurrent._cmd06 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Private Cmd07 As ImageView";
mostCurrent._cmd07 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Private Cmd08 As ImageView";
mostCurrent._cmd08 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 62;BA.debugLine="Private Cmd09 As ImageView";
mostCurrent._cmd09 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 63;BA.debugLine="Private Label01 As Label";
mostCurrent._label01 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 64;BA.debugLine="Private Label010 As Label";
mostCurrent._label010 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 65;BA.debugLine="Private Label011 As Label";
mostCurrent._label011 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 66;BA.debugLine="Private Label012 As Label";
mostCurrent._label012 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 67;BA.debugLine="Private Label02 As Label";
mostCurrent._label02 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 68;BA.debugLine="Private Label03 As Label";
mostCurrent._label03 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 69;BA.debugLine="Private Label04 As Label";
mostCurrent._label04 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 70;BA.debugLine="Private Label05 As Label";
mostCurrent._label05 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 71;BA.debugLine="Private Label06 As Label";
mostCurrent._label06 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 72;BA.debugLine="Private Label07 As Label";
mostCurrent._label07 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 73;BA.debugLine="Private Label08 As Label";
mostCurrent._label08 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 74;BA.debugLine="Private Label09 As Label";
mostCurrent._label09 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return "";
}
public static String  _iniciarconexioncentral(int _numerocentral) throws Exception{
 //BA.debugLineNum = 987;BA.debugLine="Sub IniciarConexionCentral(NumeroCentral As Int)";
 //BA.debugLineNum = 990;BA.debugLine="Conectado=True";
_conectado = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 994;BA.debugLine="ValoresComunes.Central.Nombre   = ValoresComunes.";
mostCurrent._valorescomunes._central.Nombre = BA.ObjectToString(mostCurrent._valorescomunes._centrales.Get(_numerocentral));
 //BA.debugLineNum = 995;BA.debugLine="ValoresComunes.CargaCircuitos";
mostCurrent._valorescomunes._cargacircuitos(mostCurrent.activityBA);
 //BA.debugLineNum = 996;BA.debugLine="ValoresComunes.CargaAlarmas";
mostCurrent._valorescomunes._cargaalarmas(mostCurrent.activityBA);
 //BA.debugLineNum = 997;BA.debugLine="ValoresComunes.CargaSensores";
mostCurrent._valorescomunes._cargasensores(mostCurrent.activityBA);
 //BA.debugLineNum = 998;BA.debugLine="ValoresComunes.CargaScenes";
mostCurrent._valorescomunes._cargascenes(mostCurrent.activityBA);
 //BA.debugLineNum = 999;BA.debugLine="ValoresComunes.CargaFuciones";
mostCurrent._valorescomunes._cargafuciones(mostCurrent.activityBA);
 //BA.debugLineNum = 1000;BA.debugLine="ValoresComunes.CargaCondicionados";
mostCurrent._valorescomunes._cargacondicionados(mostCurrent.activityBA);
 //BA.debugLineNum = 1001;BA.debugLine="ValoresComunes.CargaComandos";
mostCurrent._valorescomunes._cargacomandos(mostCurrent.activityBA);
 //BA.debugLineNum = 1002;BA.debugLine="ValoresComunes.CargaNombreSensores";
mostCurrent._valorescomunes._carganombresensores(mostCurrent.activityBA);
 //BA.debugLineNum = 1003;BA.debugLine="ValoresComunes.CargaSetPoint";
mostCurrent._valorescomunes._cargasetpoint(mostCurrent.activityBA);
 //BA.debugLineNum = 1005;BA.debugLine="ValoresComunes.CargaIp";
mostCurrent._valorescomunes._cargaip(mostCurrent.activityBA);
 //BA.debugLineNum = 1006;BA.debugLine="IniciarRed";
_iniciarred();
 //BA.debugLineNum = 1015;BA.debugLine="If TabConfig.TabCount < 3 Then";
if (mostCurrent._tabconfig.getTabCount()<3) { 
 //BA.debugLineNum = 1016;BA.debugLine="TabConfig.CurrentTab = 0";
mostCurrent._tabconfig.setCurrentTab((int) (0));
 }else {
 //BA.debugLineNum = 1018;BA.debugLine="TabConfig.CurrentTab = 1";
mostCurrent._tabconfig.setCurrentTab((int) (1));
 };
 //BA.debugLineNum = 1022;BA.debugLine="End Sub";
return "";
}
public static String  _iniciarconexionexterna() throws Exception{
 //BA.debugLineNum = 963;BA.debugLine="Sub IniciarConexionExterna";
 //BA.debugLineNum = 964;BA.debugLine="ProgressDialogShow(\"Starting...\")";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow(mostCurrent.activityBA,BA.ObjectToCharSequence("Starting..."));
 //BA.debugLineNum = 965;BA.debugLine="Forzar3g = True";
_forzar3g = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 967;BA.debugLine="CargaIpServidor";
_cargaipservidor();
 //BA.debugLineNum = 968;BA.debugLine="End Sub";
return "";
}
public static String  _iniciarconexioninterna() throws Exception{
 //BA.debugLineNum = 969;BA.debugLine="Sub IniciarConexionInterna";
 //BA.debugLineNum = 970;BA.debugLine="Forzar3g = False";
_forzar3g = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 972;BA.debugLine="ValoresComunes.ip= ValoresComunes.Central .IpIn";
mostCurrent._valorescomunes._ip = mostCurrent._valorescomunes._central.IpIn;
 //BA.debugLineNum = 973;BA.debugLine="ValoresComunes.Puerto= ValoresComunes.Central.Pue";
mostCurrent._valorescomunes._puerto = mostCurrent._valorescomunes._central.PuertoIn;
 //BA.debugLineNum = 974;BA.debugLine="End Sub";
return "";
}
public static void  _iniciarred() throws Exception{
ResumableSub_IniciarRed rsub = new ResumableSub_IniciarRed(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_IniciarRed extends BA.ResumableSub {
public ResumableSub_IniciarRed(arduino.automatizacion.excontrol.PRO.actmosaico parent) {
this.parent = parent;
}
arduino.automatizacion.excontrol.PRO.actmosaico parent;
int _reintento = 0;
boolean _resultado = false;
anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper _serversocket1 = null;
anywheresoftware.b4a.objects.collections.List _l = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
                return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 889;BA.debugLine="Dim Reintento As Int";
_reintento = 0;
 //BA.debugLineNum = 890;BA.debugLine="Dim Resultado As Boolean";
_resultado = false;
 //BA.debugLineNum = 893;BA.debugLine="Do 	While Resultado= False And Reintento < 60";
if (true) break;

case 1:
//do while
this.state = 10;
while (_resultado==anywheresoftware.b4a.keywords.Common.False && _reintento<60) {
this.state = 3;
if (true) break;
}
if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 894;BA.debugLine="Dim ServerSocket1 As ServerSocket";
_serversocket1 = new anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper();
 //BA.debugLineNum = 896;BA.debugLine="If ServerSocket1.GetMyIP  <> \"127.0.0.1\" Then";
if (true) break;

case 4:
//if
this.state = 9;
if ((_serversocket1.GetMyIP()).equals("127.0.0.1") == false) { 
this.state = 6;
}else {
this.state = 8;
}if (true) break;

case 6:
//C
this.state = 9;
 //BA.debugLineNum = 897;BA.debugLine="Resultado = True";
_resultado = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 899;BA.debugLine="Reintento = Reintento +1";
_reintento = (int) (_reintento+1);
 //BA.debugLineNum = 900;BA.debugLine="Sleep (100)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (100));
this.state = 31;
return;
case 31:
//C
this.state = 9;
;
 if (true) break;

case 9:
//C
this.state = 1;
;
 if (true) break;
;
 //BA.debugLineNum = 904;BA.debugLine="If Resultado = False Then";

case 10:
//if
this.state = 13;
if (_resultado==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 12;
}if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 905;BA.debugLine="Msgbox(ValoresComunes.GetLanString (\"CNA\"),Valor";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(parent.mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"CNA")),BA.ObjectToCharSequence(parent.mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"NEE")),mostCurrent.activityBA);
 //BA.debugLineNum = 906;BA.debugLine="ValoresComunes.CloseApp =True";
parent.mostCurrent._valorescomunes._closeapp = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 907;BA.debugLine="Activity.Finish";
parent.mostCurrent._activity.Finish();
 if (true) break;
;
 //BA.debugLineNum = 913;BA.debugLine="If ServerSocket1.GetMyWifiIP  <> \"127.0.0.1\"   Th";

case 13:
//if
this.state = 30;
if ((_serversocket1.GetMyWifiIP()).equals("127.0.0.1") == false) { 
this.state = 15;
}else {
this.state = 29;
}if (true) break;

case 15:
//C
this.state = 16;
 //BA.debugLineNum = 916;BA.debugLine="If File.Exists ( File.DirInternal ,\"SSID\" & Valo";
if (true) break;

case 16:
//if
this.state = 27;
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SSID"+parent.mostCurrent._valorescomunes._central.Nombre)) { 
this.state = 18;
}else {
this.state = 26;
}if (true) break;

case 18:
//C
this.state = 19;
 //BA.debugLineNum = 917;BA.debugLine="Dim l As List = File.ReadList (File.DirInternal";
_l = new anywheresoftware.b4a.objects.collections.List();
_l = anywheresoftware.b4a.keywords.Common.File.ReadList(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SSID"+parent.mostCurrent._valorescomunes._central.Nombre);
 //BA.debugLineNum = 920;BA.debugLine="If l.IndexOf (wifi.ssid)>-1 Then";
if (true) break;

case 19:
//if
this.state = 24;
if (_l.IndexOf((Object)(parent._wifi.SSID()))>-1) { 
this.state = 21;
}else {
this.state = 23;
}if (true) break;

case 21:
//C
this.state = 24;
 //BA.debugLineNum = 921;BA.debugLine="IniciarConexionInterna";
_iniciarconexioninterna();
 if (true) break;

case 23:
//C
this.state = 24;
 //BA.debugLineNum = 924;BA.debugLine="SelectConexionType";
_selectconexiontype();
 if (true) break;

case 24:
//C
this.state = 27;
;
 if (true) break;

case 26:
//C
this.state = 27;
 //BA.debugLineNum = 930;BA.debugLine="SelectConexionType";
_selectconexiontype();
 if (true) break;

case 27:
//C
this.state = 30;
;
 if (true) break;

case 29:
//C
this.state = 30;
 //BA.debugLineNum = 937;BA.debugLine="IniciarConexionExterna";
_iniciarconexionexterna();
 if (true) break;

case 30:
//C
this.state = -1;
;
 //BA.debugLineNum = 940;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _jobdone(anywheresoftware.b4a.samples.httputils2.httpjob _job) throws Exception{
String _resultsoapxml = "";
int _posini = 0;
int _posfin = 0;
String _r = "";
 //BA.debugLineNum = 776;BA.debugLine="Sub JobDone (Job As HttpJob)";
 //BA.debugLineNum = 777;BA.debugLine="If Job.Success Then";
if (_job._success) { 
 //BA.debugLineNum = 779;BA.debugLine="Try";
try { //BA.debugLineNum = 780;BA.debugLine="Dim resultSoapXML As String";
_resultsoapxml = "";
 //BA.debugLineNum = 785;BA.debugLine="resultSoapXML = Job.GetString";
_resultsoapxml = _job._getstring();
 //BA.debugLineNum = 787;BA.debugLine="Dim PosIni As Int";
_posini = 0;
 //BA.debugLineNum = 788;BA.debugLine="Dim PosFin As Int";
_posfin = 0;
 //BA.debugLineNum = 789;BA.debugLine="PosIni =  resultSoapXML.IndexOf (\"<GetIpResult>";
_posini = (int) (_resultsoapxml.indexOf("<GetIpResult>")+13);
 //BA.debugLineNum = 790;BA.debugLine="PosFin=  resultSoapXML.IndexOf (\"</GetIpResult>";
_posfin = _resultsoapxml.indexOf("</GetIpResult>");
 //BA.debugLineNum = 791;BA.debugLine="Dim R As String";
_r = "";
 //BA.debugLineNum = 792;BA.debugLine="R =resultSoapXML.SubString2 (PosIni,PosFin)";
_r = _resultsoapxml.substring(_posini,_posfin);
 //BA.debugLineNum = 793;BA.debugLine="If R.Contains (\"!!\") Then";
if (_r.contains("!!")) { 
 //BA.debugLineNum = 794;BA.debugLine="Msgbox(R,ValoresComunes.GetLanString (\"reg120\"";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence(_r),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg120")),mostCurrent.activityBA);
 //BA.debugLineNum = 795;BA.debugLine="If R.Contains (\"EXPIRED SUBCRIPCION\") Then";
if (_r.contains("EXPIRED SUBCRIPCION")) { 
 };
 }else {
 //BA.debugLineNum = 806;BA.debugLine="ValoresComunes.Ip =R";
mostCurrent._valorescomunes._ip = _r;
 //BA.debugLineNum = 807;BA.debugLine="ValoresComunes.Puerto= ValoresComunes.Central.";
mostCurrent._valorescomunes._puerto = mostCurrent._valorescomunes._central.PuertoOut;
 //BA.debugLineNum = 809;BA.debugLine="ValoresComunes.OldMail = ValoresComunes.Centra";
mostCurrent._valorescomunes._oldmail = mostCurrent._valorescomunes._central.mail;
 //BA.debugLineNum = 810;BA.debugLine="ValoresComunes.OldPass    = ValoresComunes.Cen";
mostCurrent._valorescomunes._oldpass = mostCurrent._valorescomunes._central.Password;
 //BA.debugLineNum = 811;BA.debugLine="ValoresComunes.OldExtIp=R";
mostCurrent._valorescomunes._oldextip = _r;
 //BA.debugLineNum = 812;BA.debugLine="ToastMessageShow(\"IP = \" & R, True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("IP = "+_r),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 816;BA.debugLine="ValoresComunes.Puerto=ValoresComunes.Central.Pu";
mostCurrent._valorescomunes._puerto = mostCurrent._valorescomunes._central.PuertoOut;
 //BA.debugLineNum = 819;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 } 
       catch (Exception e26) {
			processBA.setLastException(e26); //BA.debugLineNum = 821;BA.debugLine="Msgbox(\"Ip not Aviable\",ValoresComunes.GetLanSt";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Ip not Aviable"),BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstring(mostCurrent.activityBA,"reg120")),mostCurrent.activityBA);
 //BA.debugLineNum = 823;BA.debugLine="Activity.finish";
mostCurrent._activity.Finish();
 };
 };
 //BA.debugLineNum = 828;BA.debugLine="Job.Release";
_job._release();
 //BA.debugLineNum = 830;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 218;BA.debugLine="Sub ListView1_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 219;BA.debugLine="IniciarConexionCentral(Position )";
_iniciarconexioncentral(_position);
 //BA.debugLineNum = 221;BA.debugLine="End Sub";
return "";
}
public static String  _pantallahorizontal() throws Exception{
int _a = 0;
int _t = 0;
int _l = 0;
int _s = 0;
uk.co.martinpearman.b4a.tabhostextras.TabHostExtras _tabextra = null;
int _tabsize = 0;
 //BA.debugLineNum = 332;BA.debugLine="Sub PantallaHorizontal";
 //BA.debugLineNum = 333;BA.debugLine="Dim a,t,l,s As Int";
_a = 0;
_t = 0;
_l = 0;
_s = 0;
 //BA.debugLineNum = 336;BA.debugLine="Dim TabExtra As TabHostExtras";
_tabextra = new uk.co.martinpearman.b4a.tabhostextras.TabHostExtras();
 //BA.debugLineNum = 337;BA.debugLine="Dim TabSize As Int";
_tabsize = 0;
 //BA.debugLineNum = 339;BA.debugLine="TabSize =TabExtra.getTabHeight(TabConfig)";
_tabsize = _tabextra.getTabHeight((android.widget.TabHost)(mostCurrent._tabconfig.getObject()));
 //BA.debugLineNum = 342;BA.debugLine="a=  (TabConfig.Height- TabSize)  /4";
_a = (int) ((mostCurrent._tabconfig.getHeight()-_tabsize)/(double)4);
 //BA.debugLineNum = 343;BA.debugLine="t= (TabConfig.Height- TabSize) /3.2";
_t = (int) ((mostCurrent._tabconfig.getHeight()-_tabsize)/(double)3.2);
 //BA.debugLineNum = 344;BA.debugLine="l=TabConfig.Width /4";
_l = (int) (mostCurrent._tabconfig.getWidth()/(double)4);
 //BA.debugLineNum = 345;BA.debugLine="s=( TabConfig.Width - (a*4))/8.5";
_s = (int) ((mostCurrent._tabconfig.getWidth()-(_a*4))/(double)8.5);
 //BA.debugLineNum = 351;BA.debugLine="Cmd1.height =a";
mostCurrent._cmd1.setHeight(_a);
 //BA.debugLineNum = 352;BA.debugLine="Cmd1.Width =a";
mostCurrent._cmd1.setWidth(_a);
 //BA.debugLineNum = 353;BA.debugLine="Cmd1.Top =0";
mostCurrent._cmd1.setTop((int) (0));
 //BA.debugLineNum = 354;BA.debugLine="Cmd1.Left =s";
mostCurrent._cmd1.setLeft(_s);
 //BA.debugLineNum = 355;BA.debugLine="Label1.Width =a +s";
mostCurrent._label1.setWidth((int) (_a+_s));
 //BA.debugLineNum = 356;BA.debugLine="Label1.Top =a";
mostCurrent._label1.setTop(_a);
 //BA.debugLineNum = 357;BA.debugLine="Label1.Left =s/2";
mostCurrent._label1.setLeft((int) (_s/(double)2));
 //BA.debugLineNum = 361;BA.debugLine="Cmd2.height = a";
mostCurrent._cmd2.setHeight(_a);
 //BA.debugLineNum = 362;BA.debugLine="Cmd2.Width =a";
mostCurrent._cmd2.setWidth(_a);
 //BA.debugLineNum = 363;BA.debugLine="Cmd2.Top =0";
mostCurrent._cmd2.setTop((int) (0));
 //BA.debugLineNum = 364;BA.debugLine="Cmd2.Left =l+s";
mostCurrent._cmd2.setLeft((int) (_l+_s));
 //BA.debugLineNum = 365;BA.debugLine="Label2.Width =a +s";
mostCurrent._label2.setWidth((int) (_a+_s));
 //BA.debugLineNum = 366;BA.debugLine="Label2.Top =a";
mostCurrent._label2.setTop(_a);
 //BA.debugLineNum = 367;BA.debugLine="Label2.Left =l+(s/2)";
mostCurrent._label2.setLeft((int) (_l+(_s/(double)2)));
 //BA.debugLineNum = 370;BA.debugLine="Cmd3.height = a";
mostCurrent._cmd3.setHeight(_a);
 //BA.debugLineNum = 371;BA.debugLine="Cmd3.Width =a";
mostCurrent._cmd3.setWidth(_a);
 //BA.debugLineNum = 372;BA.debugLine="Cmd3.Top =0";
mostCurrent._cmd3.setTop((int) (0));
 //BA.debugLineNum = 373;BA.debugLine="Cmd3.Left =l*2+s";
mostCurrent._cmd3.setLeft((int) (_l*2+_s));
 //BA.debugLineNum = 374;BA.debugLine="Label3.Width =a +s";
mostCurrent._label3.setWidth((int) (_a+_s));
 //BA.debugLineNum = 375;BA.debugLine="Label3.Top =a";
mostCurrent._label3.setTop(_a);
 //BA.debugLineNum = 376;BA.debugLine="Label3.Left =(l*2)+(s/2)";
mostCurrent._label3.setLeft((int) ((_l*2)+(_s/(double)2)));
 //BA.debugLineNum = 379;BA.debugLine="Cmd4.height = a";
mostCurrent._cmd4.setHeight(_a);
 //BA.debugLineNum = 380;BA.debugLine="Cmd4.Width =a";
mostCurrent._cmd4.setWidth(_a);
 //BA.debugLineNum = 381;BA.debugLine="Cmd4.Top =0";
mostCurrent._cmd4.setTop((int) (0));
 //BA.debugLineNum = 382;BA.debugLine="Cmd4.Left =3*l+s";
mostCurrent._cmd4.setLeft((int) (3*_l+_s));
 //BA.debugLineNum = 383;BA.debugLine="Label4.Width =a +s";
mostCurrent._label4.setWidth((int) (_a+_s));
 //BA.debugLineNum = 384;BA.debugLine="Label4.Top =a";
mostCurrent._label4.setTop(_a);
 //BA.debugLineNum = 385;BA.debugLine="Label4.Left =(l*3)+(s/2)";
mostCurrent._label4.setLeft((int) ((_l*3)+(_s/(double)2)));
 //BA.debugLineNum = 390;BA.debugLine="Cmd5.height =a";
mostCurrent._cmd5.setHeight(_a);
 //BA.debugLineNum = 391;BA.debugLine="Cmd5.Width =a";
mostCurrent._cmd5.setWidth(_a);
 //BA.debugLineNum = 392;BA.debugLine="Cmd5.Top =t";
mostCurrent._cmd5.setTop(_t);
 //BA.debugLineNum = 393;BA.debugLine="Cmd5.Left =s";
mostCurrent._cmd5.setLeft(_s);
 //BA.debugLineNum = 396;BA.debugLine="Label5.Width =a +s";
mostCurrent._label5.setWidth((int) (_a+_s));
 //BA.debugLineNum = 397;BA.debugLine="Label5.Top =a+t";
mostCurrent._label5.setTop((int) (_a+_t));
 //BA.debugLineNum = 398;BA.debugLine="Label5.Left =s/2";
mostCurrent._label5.setLeft((int) (_s/(double)2));
 //BA.debugLineNum = 401;BA.debugLine="Cmd6.height = a";
mostCurrent._cmd6.setHeight(_a);
 //BA.debugLineNum = 402;BA.debugLine="Cmd6.Width =a";
mostCurrent._cmd6.setWidth(_a);
 //BA.debugLineNum = 403;BA.debugLine="Cmd6.Top =t";
mostCurrent._cmd6.setTop(_t);
 //BA.debugLineNum = 404;BA.debugLine="Cmd6.Left =l+s";
mostCurrent._cmd6.setLeft((int) (_l+_s));
 //BA.debugLineNum = 406;BA.debugLine="Label6.Width =a +s";
mostCurrent._label6.setWidth((int) (_a+_s));
 //BA.debugLineNum = 407;BA.debugLine="Label6.Top =a+t";
mostCurrent._label6.setTop((int) (_a+_t));
 //BA.debugLineNum = 408;BA.debugLine="Label6.Left =l+(s/2)";
mostCurrent._label6.setLeft((int) (_l+(_s/(double)2)));
 //BA.debugLineNum = 411;BA.debugLine="Cmd7.height = a";
mostCurrent._cmd7.setHeight(_a);
 //BA.debugLineNum = 412;BA.debugLine="Cmd7.Width =a";
mostCurrent._cmd7.setWidth(_a);
 //BA.debugLineNum = 413;BA.debugLine="Cmd7.Top =t";
mostCurrent._cmd7.setTop(_t);
 //BA.debugLineNum = 414;BA.debugLine="Cmd7.Left =l*2+s";
mostCurrent._cmd7.setLeft((int) (_l*2+_s));
 //BA.debugLineNum = 416;BA.debugLine="Label7.Width =a +s";
mostCurrent._label7.setWidth((int) (_a+_s));
 //BA.debugLineNum = 417;BA.debugLine="Label7.Top =a+t";
mostCurrent._label7.setTop((int) (_a+_t));
 //BA.debugLineNum = 418;BA.debugLine="Label7.Left =(l*2)+(s/2)";
mostCurrent._label7.setLeft((int) ((_l*2)+(_s/(double)2)));
 //BA.debugLineNum = 420;BA.debugLine="Cmd8.height = a";
mostCurrent._cmd8.setHeight(_a);
 //BA.debugLineNum = 421;BA.debugLine="Cmd8.Width =a";
mostCurrent._cmd8.setWidth(_a);
 //BA.debugLineNum = 422;BA.debugLine="Cmd8.Top =t";
mostCurrent._cmd8.setTop(_t);
 //BA.debugLineNum = 423;BA.debugLine="Cmd8.Left =3*l+s";
mostCurrent._cmd8.setLeft((int) (3*_l+_s));
 //BA.debugLineNum = 425;BA.debugLine="Label8.Width =a +s";
mostCurrent._label8.setWidth((int) (_a+_s));
 //BA.debugLineNum = 426;BA.debugLine="Label8.Top =a+t";
mostCurrent._label8.setTop((int) (_a+_t));
 //BA.debugLineNum = 427;BA.debugLine="Label8.Left =(l*3)+(s/2)";
mostCurrent._label8.setLeft((int) ((_l*3)+(_s/(double)2)));
 //BA.debugLineNum = 432;BA.debugLine="Cmd9.height =a";
mostCurrent._cmd9.setHeight(_a);
 //BA.debugLineNum = 433;BA.debugLine="Cmd9.Width =a";
mostCurrent._cmd9.setWidth(_a);
 //BA.debugLineNum = 434;BA.debugLine="Cmd9.Top =t*2";
mostCurrent._cmd9.setTop((int) (_t*2));
 //BA.debugLineNum = 435;BA.debugLine="Cmd9.Left =s";
mostCurrent._cmd9.setLeft(_s);
 //BA.debugLineNum = 438;BA.debugLine="Label9.Width =a +s";
mostCurrent._label9.setWidth((int) (_a+_s));
 //BA.debugLineNum = 439;BA.debugLine="Label9.Top =a+t*2";
mostCurrent._label9.setTop((int) (_a+_t*2));
 //BA.debugLineNum = 440;BA.debugLine="Label9.Left =s/2";
mostCurrent._label9.setLeft((int) (_s/(double)2));
 //BA.debugLineNum = 443;BA.debugLine="Cmd10.height = a";
mostCurrent._cmd10.setHeight(_a);
 //BA.debugLineNum = 444;BA.debugLine="Cmd10.Width =a";
mostCurrent._cmd10.setWidth(_a);
 //BA.debugLineNum = 445;BA.debugLine="Cmd10.Top =t*2";
mostCurrent._cmd10.setTop((int) (_t*2));
 //BA.debugLineNum = 446;BA.debugLine="Cmd10.Left =l+s";
mostCurrent._cmd10.setLeft((int) (_l+_s));
 //BA.debugLineNum = 448;BA.debugLine="Label10.Width =a +s";
mostCurrent._label10.setWidth((int) (_a+_s));
 //BA.debugLineNum = 449;BA.debugLine="Label10.Top =a+t*2";
mostCurrent._label10.setTop((int) (_a+_t*2));
 //BA.debugLineNum = 450;BA.debugLine="Label10.Left =l+(s/2)";
mostCurrent._label10.setLeft((int) (_l+(_s/(double)2)));
 //BA.debugLineNum = 456;BA.debugLine="Cmd11.height = a";
mostCurrent._cmd11.setHeight(_a);
 //BA.debugLineNum = 457;BA.debugLine="Cmd11.Width =a";
mostCurrent._cmd11.setWidth(_a);
 //BA.debugLineNum = 458;BA.debugLine="Cmd11.Top =t*2";
mostCurrent._cmd11.setTop((int) (_t*2));
 //BA.debugLineNum = 459;BA.debugLine="Cmd11.Left =l*2+s";
mostCurrent._cmd11.setLeft((int) (_l*2+_s));
 //BA.debugLineNum = 461;BA.debugLine="Label11.Width =a +s";
mostCurrent._label11.setWidth((int) (_a+_s));
 //BA.debugLineNum = 462;BA.debugLine="Label11.Top =a+t*2";
mostCurrent._label11.setTop((int) (_a+_t*2));
 //BA.debugLineNum = 463;BA.debugLine="Label11.Left =(l*2)+(s/2)";
mostCurrent._label11.setLeft((int) ((_l*2)+(_s/(double)2)));
 //BA.debugLineNum = 465;BA.debugLine="Cmd12.height = a";
mostCurrent._cmd12.setHeight(_a);
 //BA.debugLineNum = 466;BA.debugLine="Cmd12.Width =a";
mostCurrent._cmd12.setWidth(_a);
 //BA.debugLineNum = 467;BA.debugLine="Cmd12.Top =t*2";
mostCurrent._cmd12.setTop((int) (_t*2));
 //BA.debugLineNum = 468;BA.debugLine="Cmd12.Left =3*l+s";
mostCurrent._cmd12.setLeft((int) (3*_l+_s));
 //BA.debugLineNum = 470;BA.debugLine="Label12.Width =a +s";
mostCurrent._label12.setWidth((int) (_a+_s));
 //BA.debugLineNum = 471;BA.debugLine="Label12.Top =a+t*2";
mostCurrent._label12.setTop((int) (_a+_t*2));
 //BA.debugLineNum = 472;BA.debugLine="Label12.Left =(l*3)+(s/2)";
mostCurrent._label12.setLeft((int) ((_l*3)+(_s/(double)2)));
 //BA.debugLineNum = 475;BA.debugLine="PintaPantalla2";
_pintapantalla2();
 //BA.debugLineNum = 477;BA.debugLine="End Sub";
return "";
}
public static String  _pantallavertical() throws Exception{
uk.co.martinpearman.b4a.tabhostextras.TabHostExtras _tabextra = null;
int _tabsize = 0;
int _a = 0;
int _t = 0;
int _l = 0;
int _s = 0;
 //BA.debugLineNum = 478;BA.debugLine="Sub PantallaVertical";
 //BA.debugLineNum = 480;BA.debugLine="Dim TabExtra As TabHostExtras";
_tabextra = new uk.co.martinpearman.b4a.tabhostextras.TabHostExtras();
 //BA.debugLineNum = 481;BA.debugLine="Dim TabSize As Int";
_tabsize = 0;
 //BA.debugLineNum = 483;BA.debugLine="TabSize =TabExtra.getTabHeight(TabConfig)";
_tabsize = _tabextra.getTabHeight((android.widget.TabHost)(mostCurrent._tabconfig.getObject()));
 //BA.debugLineNum = 486;BA.debugLine="Dim a,t,l,s As Int";
_a = 0;
_t = 0;
_l = 0;
_s = 0;
 //BA.debugLineNum = 489;BA.debugLine="a=  (Activity.Width)  /4";
_a = (int) ((mostCurrent._activity.getWidth())/(double)4);
 //BA.debugLineNum = 490;BA.debugLine="t= (TabConfig.Height- TabSize) /4";
_t = (int) ((mostCurrent._tabconfig.getHeight()-_tabsize)/(double)4);
 //BA.debugLineNum = 499;BA.debugLine="s= Activity.Width - (a*3)";
_s = (int) (mostCurrent._activity.getWidth()-(_a*3));
 //BA.debugLineNum = 500;BA.debugLine="s=s/7";
_s = (int) (_s/(double)7);
 //BA.debugLineNum = 501;BA.debugLine="l=(2*s)+a";
_l = (int) ((2*_s)+_a);
 //BA.debugLineNum = 503;BA.debugLine="Cmd1.height =a";
mostCurrent._cmd1.setHeight(_a);
 //BA.debugLineNum = 504;BA.debugLine="Cmd1.Width =a";
mostCurrent._cmd1.setWidth(_a);
 //BA.debugLineNum = 505;BA.debugLine="Cmd1.Top =0";
mostCurrent._cmd1.setTop((int) (0));
 //BA.debugLineNum = 506;BA.debugLine="Cmd1.Left =s";
mostCurrent._cmd1.setLeft(_s);
 //BA.debugLineNum = 508;BA.debugLine="Label1.Width =a+s";
mostCurrent._label1.setWidth((int) (_a+_s));
 //BA.debugLineNum = 509;BA.debugLine="Label1.Top =a";
mostCurrent._label1.setTop(_a);
 //BA.debugLineNum = 510;BA.debugLine="Label1.Left =s/2";
mostCurrent._label1.setLeft((int) (_s/(double)2));
 //BA.debugLineNum = 516;BA.debugLine="Cmd2.height = a";
mostCurrent._cmd2.setHeight(_a);
 //BA.debugLineNum = 517;BA.debugLine="Cmd2.Width =a";
mostCurrent._cmd2.setWidth(_a);
 //BA.debugLineNum = 518;BA.debugLine="Cmd2.Top =0";
mostCurrent._cmd2.setTop((int) (0));
 //BA.debugLineNum = 519;BA.debugLine="Cmd2.Left =l+s";
mostCurrent._cmd2.setLeft((int) (_l+_s));
 //BA.debugLineNum = 520;BA.debugLine="Label2.Width =a+s";
mostCurrent._label2.setWidth((int) (_a+_s));
 //BA.debugLineNum = 521;BA.debugLine="Label2.Top =a";
mostCurrent._label2.setTop(_a);
 //BA.debugLineNum = 523;BA.debugLine="Label2.Left =l+(s/2)";
mostCurrent._label2.setLeft((int) (_l+(_s/(double)2)));
 //BA.debugLineNum = 528;BA.debugLine="Cmd3.height = a";
mostCurrent._cmd3.setHeight(_a);
 //BA.debugLineNum = 529;BA.debugLine="Cmd3.Width =a";
mostCurrent._cmd3.setWidth(_a);
 //BA.debugLineNum = 530;BA.debugLine="Cmd3.Top =0";
mostCurrent._cmd3.setTop((int) (0));
 //BA.debugLineNum = 531;BA.debugLine="Cmd3.Left =(l*2)+s";
mostCurrent._cmd3.setLeft((int) ((_l*2)+_s));
 //BA.debugLineNum = 532;BA.debugLine="Label3.Width =a+s";
mostCurrent._label3.setWidth((int) (_a+_s));
 //BA.debugLineNum = 533;BA.debugLine="Label3.Top =a";
mostCurrent._label3.setTop(_a);
 //BA.debugLineNum = 534;BA.debugLine="Label3.Left =(l*2)+(s/2)";
mostCurrent._label3.setLeft((int) ((_l*2)+(_s/(double)2)));
 //BA.debugLineNum = 536;BA.debugLine="Cmd4.height = a";
mostCurrent._cmd4.setHeight(_a);
 //BA.debugLineNum = 537;BA.debugLine="Cmd4.Width =a";
mostCurrent._cmd4.setWidth(_a);
 //BA.debugLineNum = 538;BA.debugLine="Cmd4.Top =t";
mostCurrent._cmd4.setTop(_t);
 //BA.debugLineNum = 539;BA.debugLine="Cmd4.Left =s";
mostCurrent._cmd4.setLeft(_s);
 //BA.debugLineNum = 540;BA.debugLine="Label4.Width =a+s";
mostCurrent._label4.setWidth((int) (_a+_s));
 //BA.debugLineNum = 541;BA.debugLine="Label4.Top =a+t";
mostCurrent._label4.setTop((int) (_a+_t));
 //BA.debugLineNum = 542;BA.debugLine="Label4.Left =(s/2)";
mostCurrent._label4.setLeft((int) ((_s/(double)2)));
 //BA.debugLineNum = 544;BA.debugLine="Cmd5.height = a";
mostCurrent._cmd5.setHeight(_a);
 //BA.debugLineNum = 545;BA.debugLine="Cmd5.Width =a";
mostCurrent._cmd5.setWidth(_a);
 //BA.debugLineNum = 546;BA.debugLine="Cmd5.Top =t";
mostCurrent._cmd5.setTop(_t);
 //BA.debugLineNum = 547;BA.debugLine="Cmd5.Left =l+s";
mostCurrent._cmd5.setLeft((int) (_l+_s));
 //BA.debugLineNum = 548;BA.debugLine="Label5.Width =a+s";
mostCurrent._label5.setWidth((int) (_a+_s));
 //BA.debugLineNum = 549;BA.debugLine="Label5.Top =a+t";
mostCurrent._label5.setTop((int) (_a+_t));
 //BA.debugLineNum = 550;BA.debugLine="Label5.Left =l+(s/2)";
mostCurrent._label5.setLeft((int) (_l+(_s/(double)2)));
 //BA.debugLineNum = 552;BA.debugLine="Cmd6.height = a";
mostCurrent._cmd6.setHeight(_a);
 //BA.debugLineNum = 553;BA.debugLine="Cmd6.Width =a";
mostCurrent._cmd6.setWidth(_a);
 //BA.debugLineNum = 554;BA.debugLine="Cmd6.Top =t";
mostCurrent._cmd6.setTop(_t);
 //BA.debugLineNum = 555;BA.debugLine="Cmd6.Left =(l*2)+s";
mostCurrent._cmd6.setLeft((int) ((_l*2)+_s));
 //BA.debugLineNum = 556;BA.debugLine="Label6.Width =a+s";
mostCurrent._label6.setWidth((int) (_a+_s));
 //BA.debugLineNum = 557;BA.debugLine="Label6.Top =a+t";
mostCurrent._label6.setTop((int) (_a+_t));
 //BA.debugLineNum = 558;BA.debugLine="Label6.Left =(l*2)+(s/2)";
mostCurrent._label6.setLeft((int) ((_l*2)+(_s/(double)2)));
 //BA.debugLineNum = 561;BA.debugLine="Cmd7.height = a";
mostCurrent._cmd7.setHeight(_a);
 //BA.debugLineNum = 562;BA.debugLine="Cmd7.Width =a";
mostCurrent._cmd7.setWidth(_a);
 //BA.debugLineNum = 563;BA.debugLine="Cmd7.Top =t*2";
mostCurrent._cmd7.setTop((int) (_t*2));
 //BA.debugLineNum = 564;BA.debugLine="Cmd7.Left =s";
mostCurrent._cmd7.setLeft(_s);
 //BA.debugLineNum = 565;BA.debugLine="Label7.Width =a+s";
mostCurrent._label7.setWidth((int) (_a+_s));
 //BA.debugLineNum = 566;BA.debugLine="Label7.Top =t*2+a";
mostCurrent._label7.setTop((int) (_t*2+_a));
 //BA.debugLineNum = 567;BA.debugLine="Label7.Left =s/2";
mostCurrent._label7.setLeft((int) (_s/(double)2));
 //BA.debugLineNum = 569;BA.debugLine="Cmd8.height =a";
mostCurrent._cmd8.setHeight(_a);
 //BA.debugLineNum = 570;BA.debugLine="Cmd8.Width =a";
mostCurrent._cmd8.setWidth(_a);
 //BA.debugLineNum = 571;BA.debugLine="Cmd8.Top =t*2";
mostCurrent._cmd8.setTop((int) (_t*2));
 //BA.debugLineNum = 572;BA.debugLine="Cmd8.Left =l+s";
mostCurrent._cmd8.setLeft((int) (_l+_s));
 //BA.debugLineNum = 573;BA.debugLine="Label8.Width =a+s";
mostCurrent._label8.setWidth((int) (_a+_s));
 //BA.debugLineNum = 574;BA.debugLine="Label8.Top =t*2+a";
mostCurrent._label8.setTop((int) (_t*2+_a));
 //BA.debugLineNum = 575;BA.debugLine="Label8.Left =(s/2)+l";
mostCurrent._label8.setLeft((int) ((_s/(double)2)+_l));
 //BA.debugLineNum = 577;BA.debugLine="Cmd9.height = a";
mostCurrent._cmd9.setHeight(_a);
 //BA.debugLineNum = 578;BA.debugLine="Cmd9.Width =a";
mostCurrent._cmd9.setWidth(_a);
 //BA.debugLineNum = 579;BA.debugLine="Cmd9.Top =t*2";
mostCurrent._cmd9.setTop((int) (_t*2));
 //BA.debugLineNum = 580;BA.debugLine="Cmd9.Left =(l*2)+s";
mostCurrent._cmd9.setLeft((int) ((_l*2)+_s));
 //BA.debugLineNum = 582;BA.debugLine="Label9.Width =a+s";
mostCurrent._label9.setWidth((int) (_a+_s));
 //BA.debugLineNum = 583;BA.debugLine="Label9.Top =t*2+a";
mostCurrent._label9.setTop((int) (_t*2+_a));
 //BA.debugLineNum = 584;BA.debugLine="Label9.Left =(l*2)+(s/2)";
mostCurrent._label9.setLeft((int) ((_l*2)+(_s/(double)2)));
 //BA.debugLineNum = 586;BA.debugLine="Cmd10.height = a";
mostCurrent._cmd10.setHeight(_a);
 //BA.debugLineNum = 587;BA.debugLine="Cmd10.Width =a";
mostCurrent._cmd10.setWidth(_a);
 //BA.debugLineNum = 588;BA.debugLine="Cmd10.Top =t*3";
mostCurrent._cmd10.setTop((int) (_t*3));
 //BA.debugLineNum = 589;BA.debugLine="Cmd10.Left =s";
mostCurrent._cmd10.setLeft(_s);
 //BA.debugLineNum = 593;BA.debugLine="Label10.Width =a+s";
mostCurrent._label10.setWidth((int) (_a+_s));
 //BA.debugLineNum = 594;BA.debugLine="Label10.Top =t*3+a";
mostCurrent._label10.setTop((int) (_t*3+_a));
 //BA.debugLineNum = 595;BA.debugLine="Label10.Left =s/2";
mostCurrent._label10.setLeft((int) (_s/(double)2));
 //BA.debugLineNum = 598;BA.debugLine="Cmd11.height = a";
mostCurrent._cmd11.setHeight(_a);
 //BA.debugLineNum = 599;BA.debugLine="Cmd11.Width =a";
mostCurrent._cmd11.setWidth(_a);
 //BA.debugLineNum = 600;BA.debugLine="Cmd11.Top =t*3";
mostCurrent._cmd11.setTop((int) (_t*3));
 //BA.debugLineNum = 601;BA.debugLine="Cmd11.Left =l+s";
mostCurrent._cmd11.setLeft((int) (_l+_s));
 //BA.debugLineNum = 603;BA.debugLine="Label11.Width =a+s";
mostCurrent._label11.setWidth((int) (_a+_s));
 //BA.debugLineNum = 604;BA.debugLine="Label11.Top =t*3+a";
mostCurrent._label11.setTop((int) (_t*3+_a));
 //BA.debugLineNum = 605;BA.debugLine="Label11.Left =(s/2)+l";
mostCurrent._label11.setLeft((int) ((_s/(double)2)+_l));
 //BA.debugLineNum = 607;BA.debugLine="Cmd12.height = a";
mostCurrent._cmd12.setHeight(_a);
 //BA.debugLineNum = 608;BA.debugLine="Cmd12.Width =a";
mostCurrent._cmd12.setWidth(_a);
 //BA.debugLineNum = 609;BA.debugLine="Cmd12.Top =t*3";
mostCurrent._cmd12.setTop((int) (_t*3));
 //BA.debugLineNum = 610;BA.debugLine="Cmd12.Left =(l*2)+s";
mostCurrent._cmd12.setLeft((int) ((_l*2)+_s));
 //BA.debugLineNum = 612;BA.debugLine="Label12.Width =a+s";
mostCurrent._label12.setWidth((int) (_a+_s));
 //BA.debugLineNum = 613;BA.debugLine="Label12.Top =t*3+a";
mostCurrent._label12.setTop((int) (_t*3+_a));
 //BA.debugLineNum = 614;BA.debugLine="Label12.Left =(s/2)+(l*2)";
mostCurrent._label12.setLeft((int) ((_s/(double)2)+(_l*2)));
 //BA.debugLineNum = 616;BA.debugLine="PintaPantalla2";
_pintapantalla2();
 //BA.debugLineNum = 617;BA.debugLine="End Sub";
return "";
}
public static String  _pintapantalla2() throws Exception{
 //BA.debugLineNum = 222;BA.debugLine="Private Sub PintaPantalla2";
 //BA.debugLineNum = 223;BA.debugLine="Cmd01.height=Cmd1.height";
mostCurrent._cmd01.setHeight(mostCurrent._cmd1.getHeight());
 //BA.debugLineNum = 224;BA.debugLine="Cmd01.Width=Cmd1.Width";
mostCurrent._cmd01.setWidth(mostCurrent._cmd1.getWidth());
 //BA.debugLineNum = 225;BA.debugLine="Cmd01.Top=Cmd1.Top";
mostCurrent._cmd01.setTop(mostCurrent._cmd1.getTop());
 //BA.debugLineNum = 226;BA.debugLine="Cmd01.Left=Cmd1.Left";
mostCurrent._cmd01.setLeft(mostCurrent._cmd1.getLeft());
 //BA.debugLineNum = 228;BA.debugLine="Label01.Width=Label1.Width";
mostCurrent._label01.setWidth(mostCurrent._label1.getWidth());
 //BA.debugLineNum = 229;BA.debugLine="Label01.Top=Label1.Top";
mostCurrent._label01.setTop(mostCurrent._label1.getTop());
 //BA.debugLineNum = 230;BA.debugLine="Label01.Left=Label1.Left";
mostCurrent._label01.setLeft(mostCurrent._label1.getLeft());
 //BA.debugLineNum = 233;BA.debugLine="Cmd02.height=Cmd2.height";
mostCurrent._cmd02.setHeight(mostCurrent._cmd2.getHeight());
 //BA.debugLineNum = 234;BA.debugLine="Cmd02.Width=Cmd2.Width";
mostCurrent._cmd02.setWidth(mostCurrent._cmd2.getWidth());
 //BA.debugLineNum = 235;BA.debugLine="Cmd02.Top=Cmd2.Top";
mostCurrent._cmd02.setTop(mostCurrent._cmd2.getTop());
 //BA.debugLineNum = 236;BA.debugLine="Cmd02.Left=Cmd2.Left";
mostCurrent._cmd02.setLeft(mostCurrent._cmd2.getLeft());
 //BA.debugLineNum = 238;BA.debugLine="Label02.Width=Label2.Width";
mostCurrent._label02.setWidth(mostCurrent._label2.getWidth());
 //BA.debugLineNum = 239;BA.debugLine="Label02.Top=Label2.Top";
mostCurrent._label02.setTop(mostCurrent._label2.getTop());
 //BA.debugLineNum = 240;BA.debugLine="Label02.Left=Label2.Left";
mostCurrent._label02.setLeft(mostCurrent._label2.getLeft());
 //BA.debugLineNum = 242;BA.debugLine="Cmd03.height=Cmd3.height";
mostCurrent._cmd03.setHeight(mostCurrent._cmd3.getHeight());
 //BA.debugLineNum = 243;BA.debugLine="Cmd03.Width=Cmd3.Width";
mostCurrent._cmd03.setWidth(mostCurrent._cmd3.getWidth());
 //BA.debugLineNum = 244;BA.debugLine="Cmd03.Top=Cmd3.Top";
mostCurrent._cmd03.setTop(mostCurrent._cmd3.getTop());
 //BA.debugLineNum = 245;BA.debugLine="Cmd03.Left=Cmd3.Left";
mostCurrent._cmd03.setLeft(mostCurrent._cmd3.getLeft());
 //BA.debugLineNum = 247;BA.debugLine="Label03.Width=Label3.Width";
mostCurrent._label03.setWidth(mostCurrent._label3.getWidth());
 //BA.debugLineNum = 248;BA.debugLine="Label03.Top=Label3.Top";
mostCurrent._label03.setTop(mostCurrent._label3.getTop());
 //BA.debugLineNum = 249;BA.debugLine="Label03.Left=Label3.Left";
mostCurrent._label03.setLeft(mostCurrent._label3.getLeft());
 //BA.debugLineNum = 251;BA.debugLine="Cmd04.height=Cmd4.height";
mostCurrent._cmd04.setHeight(mostCurrent._cmd4.getHeight());
 //BA.debugLineNum = 252;BA.debugLine="Cmd04.Width=Cmd4.Width";
mostCurrent._cmd04.setWidth(mostCurrent._cmd4.getWidth());
 //BA.debugLineNum = 253;BA.debugLine="Cmd04.Top=Cmd4.Top";
mostCurrent._cmd04.setTop(mostCurrent._cmd4.getTop());
 //BA.debugLineNum = 254;BA.debugLine="Cmd04.Left=Cmd4.Left";
mostCurrent._cmd04.setLeft(mostCurrent._cmd4.getLeft());
 //BA.debugLineNum = 256;BA.debugLine="Label04.Width=Label4.Width";
mostCurrent._label04.setWidth(mostCurrent._label4.getWidth());
 //BA.debugLineNum = 257;BA.debugLine="Label04.Top=Label4.Top";
mostCurrent._label04.setTop(mostCurrent._label4.getTop());
 //BA.debugLineNum = 258;BA.debugLine="Label04.Left=Label4.Left";
mostCurrent._label04.setLeft(mostCurrent._label4.getLeft());
 //BA.debugLineNum = 260;BA.debugLine="Cmd05.height=Cmd5.height";
mostCurrent._cmd05.setHeight(mostCurrent._cmd5.getHeight());
 //BA.debugLineNum = 261;BA.debugLine="Cmd05.Width=Cmd5.Width";
mostCurrent._cmd05.setWidth(mostCurrent._cmd5.getWidth());
 //BA.debugLineNum = 262;BA.debugLine="Cmd05.Top=Cmd5.Top";
mostCurrent._cmd05.setTop(mostCurrent._cmd5.getTop());
 //BA.debugLineNum = 263;BA.debugLine="Cmd05.Left=Cmd5.Left";
mostCurrent._cmd05.setLeft(mostCurrent._cmd5.getLeft());
 //BA.debugLineNum = 265;BA.debugLine="Label05.Width=Label5.Width";
mostCurrent._label05.setWidth(mostCurrent._label5.getWidth());
 //BA.debugLineNum = 266;BA.debugLine="Label05.Top=Label5.Top";
mostCurrent._label05.setTop(mostCurrent._label5.getTop());
 //BA.debugLineNum = 267;BA.debugLine="Label05.Left=Label5.Left";
mostCurrent._label05.setLeft(mostCurrent._label5.getLeft());
 //BA.debugLineNum = 269;BA.debugLine="Cmd06.height=Cmd6.height";
mostCurrent._cmd06.setHeight(mostCurrent._cmd6.getHeight());
 //BA.debugLineNum = 270;BA.debugLine="Cmd06.Width=Cmd6.Width";
mostCurrent._cmd06.setWidth(mostCurrent._cmd6.getWidth());
 //BA.debugLineNum = 271;BA.debugLine="Cmd06.Top=Cmd6.Top";
mostCurrent._cmd06.setTop(mostCurrent._cmd6.getTop());
 //BA.debugLineNum = 272;BA.debugLine="Cmd06.Left=Cmd6.Left";
mostCurrent._cmd06.setLeft(mostCurrent._cmd6.getLeft());
 //BA.debugLineNum = 274;BA.debugLine="Label06.Width=Label6.Width";
mostCurrent._label06.setWidth(mostCurrent._label6.getWidth());
 //BA.debugLineNum = 275;BA.debugLine="Label06.Top=Label6.Top";
mostCurrent._label06.setTop(mostCurrent._label6.getTop());
 //BA.debugLineNum = 276;BA.debugLine="Label06.Left=Label6.Left";
mostCurrent._label06.setLeft(mostCurrent._label6.getLeft());
 //BA.debugLineNum = 278;BA.debugLine="Cmd07.height=Cmd7.height";
mostCurrent._cmd07.setHeight(mostCurrent._cmd7.getHeight());
 //BA.debugLineNum = 279;BA.debugLine="Cmd07.Width=Cmd7.Width";
mostCurrent._cmd07.setWidth(mostCurrent._cmd7.getWidth());
 //BA.debugLineNum = 280;BA.debugLine="Cmd07.Top=Cmd7.Top";
mostCurrent._cmd07.setTop(mostCurrent._cmd7.getTop());
 //BA.debugLineNum = 281;BA.debugLine="Cmd07.Left=Cmd7.Left";
mostCurrent._cmd07.setLeft(mostCurrent._cmd7.getLeft());
 //BA.debugLineNum = 283;BA.debugLine="Label07.Width=Label7.Width";
mostCurrent._label07.setWidth(mostCurrent._label7.getWidth());
 //BA.debugLineNum = 284;BA.debugLine="Label07.Top=Label7.Top";
mostCurrent._label07.setTop(mostCurrent._label7.getTop());
 //BA.debugLineNum = 285;BA.debugLine="Label07.Left=Label7.Left";
mostCurrent._label07.setLeft(mostCurrent._label7.getLeft());
 //BA.debugLineNum = 287;BA.debugLine="Cmd08.height=Cmd8.height";
mostCurrent._cmd08.setHeight(mostCurrent._cmd8.getHeight());
 //BA.debugLineNum = 288;BA.debugLine="Cmd08.Width=Cmd8.Width";
mostCurrent._cmd08.setWidth(mostCurrent._cmd8.getWidth());
 //BA.debugLineNum = 289;BA.debugLine="Cmd08.Top=Cmd8.Top";
mostCurrent._cmd08.setTop(mostCurrent._cmd8.getTop());
 //BA.debugLineNum = 290;BA.debugLine="Cmd08.Left=Cmd8.Left";
mostCurrent._cmd08.setLeft(mostCurrent._cmd8.getLeft());
 //BA.debugLineNum = 292;BA.debugLine="Label08.Width=Label8.Width";
mostCurrent._label08.setWidth(mostCurrent._label8.getWidth());
 //BA.debugLineNum = 293;BA.debugLine="Label08.Top=Label8.Top";
mostCurrent._label08.setTop(mostCurrent._label8.getTop());
 //BA.debugLineNum = 294;BA.debugLine="Label08.Left=Label8.Left";
mostCurrent._label08.setLeft(mostCurrent._label8.getLeft());
 //BA.debugLineNum = 296;BA.debugLine="Cmd09.height=Cmd9.height";
mostCurrent._cmd09.setHeight(mostCurrent._cmd9.getHeight());
 //BA.debugLineNum = 297;BA.debugLine="Cmd09.Width=Cmd9.Width";
mostCurrent._cmd09.setWidth(mostCurrent._cmd9.getWidth());
 //BA.debugLineNum = 298;BA.debugLine="Cmd09.Top=Cmd9.Top";
mostCurrent._cmd09.setTop(mostCurrent._cmd9.getTop());
 //BA.debugLineNum = 299;BA.debugLine="Cmd09.Left=Cmd9.Left";
mostCurrent._cmd09.setLeft(mostCurrent._cmd9.getLeft());
 //BA.debugLineNum = 301;BA.debugLine="Label09.Width=Label9.Width";
mostCurrent._label09.setWidth(mostCurrent._label9.getWidth());
 //BA.debugLineNum = 302;BA.debugLine="Label09.Top=Label9.Top";
mostCurrent._label09.setTop(mostCurrent._label9.getTop());
 //BA.debugLineNum = 303;BA.debugLine="Label09.Left=Label9.Left";
mostCurrent._label09.setLeft(mostCurrent._label9.getLeft());
 //BA.debugLineNum = 305;BA.debugLine="Cmd010.height=Cmd10.height";
mostCurrent._cmd010.setHeight(mostCurrent._cmd10.getHeight());
 //BA.debugLineNum = 306;BA.debugLine="Cmd010.Width=Cmd10.Width";
mostCurrent._cmd010.setWidth(mostCurrent._cmd10.getWidth());
 //BA.debugLineNum = 307;BA.debugLine="Cmd010.Top=Cmd10.Top";
mostCurrent._cmd010.setTop(mostCurrent._cmd10.getTop());
 //BA.debugLineNum = 308;BA.debugLine="Cmd010.Left=Cmd10.Left";
mostCurrent._cmd010.setLeft(mostCurrent._cmd10.getLeft());
 //BA.debugLineNum = 310;BA.debugLine="Label010.Width=Label10.Width";
mostCurrent._label010.setWidth(mostCurrent._label10.getWidth());
 //BA.debugLineNum = 311;BA.debugLine="Label010.Top=Label10.Top";
mostCurrent._label010.setTop(mostCurrent._label10.getTop());
 //BA.debugLineNum = 312;BA.debugLine="Label010.Left=Label10.Left";
mostCurrent._label010.setLeft(mostCurrent._label10.getLeft());
 //BA.debugLineNum = 314;BA.debugLine="Cmd011.height=Cmd11.height";
mostCurrent._cmd011.setHeight(mostCurrent._cmd11.getHeight());
 //BA.debugLineNum = 315;BA.debugLine="Cmd011.Width=Cmd11.Width";
mostCurrent._cmd011.setWidth(mostCurrent._cmd11.getWidth());
 //BA.debugLineNum = 316;BA.debugLine="Cmd011.Top=Cmd11.Top";
mostCurrent._cmd011.setTop(mostCurrent._cmd11.getTop());
 //BA.debugLineNum = 317;BA.debugLine="Cmd011.Left=Cmd11.Left";
mostCurrent._cmd011.setLeft(mostCurrent._cmd11.getLeft());
 //BA.debugLineNum = 319;BA.debugLine="Label011.Width=Label11.Width";
mostCurrent._label011.setWidth(mostCurrent._label11.getWidth());
 //BA.debugLineNum = 320;BA.debugLine="Label011.Top=Label11.Top";
mostCurrent._label011.setTop(mostCurrent._label11.getTop());
 //BA.debugLineNum = 321;BA.debugLine="Label011.Left=Label11.Left";
mostCurrent._label011.setLeft(mostCurrent._label11.getLeft());
 //BA.debugLineNum = 323;BA.debugLine="Cmd012.height=Cmd12.height";
mostCurrent._cmd012.setHeight(mostCurrent._cmd12.getHeight());
 //BA.debugLineNum = 324;BA.debugLine="Cmd012.Width=Cmd12.Width";
mostCurrent._cmd012.setWidth(mostCurrent._cmd12.getWidth());
 //BA.debugLineNum = 325;BA.debugLine="Cmd012.Top=Cmd12.Top";
mostCurrent._cmd012.setTop(mostCurrent._cmd12.getTop());
 //BA.debugLineNum = 326;BA.debugLine="Cmd012.Left=Cmd12.Left";
mostCurrent._cmd012.setLeft(mostCurrent._cmd12.getLeft());
 //BA.debugLineNum = 328;BA.debugLine="Label012.Width=Label12.Width";
mostCurrent._label012.setWidth(mostCurrent._label12.getWidth());
 //BA.debugLineNum = 329;BA.debugLine="Label012.Top=Label12.Top";
mostCurrent._label012.setTop(mostCurrent._label12.getTop());
 //BA.debugLineNum = 330;BA.debugLine="Label012.Left=Label12.Left";
mostCurrent._label012.setLeft(mostCurrent._label12.getLeft());
 //BA.debugLineNum = 331;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 11;BA.debugLine="Dim UpdateCentral As Boolean";
_updatecentral = false;
 //BA.debugLineNum = 12;BA.debugLine="Dim Forzar3g As Boolean";
_forzar3g = false;
 //BA.debugLineNum = 13;BA.debugLine="Dim Conectado As Boolean";
_conectado = false;
 //BA.debugLineNum = 14;BA.debugLine="Dim CentraltoConnect As Int";
_centraltoconnect = 0;
 //BA.debugLineNum = 15;BA.debugLine="Dim wifi As MLwifi";
_wifi = new wifi.MLwifi();
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
public static String  _selectconexiontype() throws Exception{
int _r = 0;
anywheresoftware.b4a.objects.collections.List _lst = null;
 //BA.debugLineNum = 941;BA.debugLine="Sub SelectConexionType";
 //BA.debugLineNum = 942;BA.debugLine="Dim R As Int";
_r = 0;
 //BA.debugLineNum = 943;BA.debugLine="Dim Lst As List";
_lst = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 944;BA.debugLine="Lst.Initialize";
_lst.Initialize();
 //BA.debugLineNum = 945;BA.debugLine="Lst.Add (ValoresComunes.GetLanStringDefault  (\"IC";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"ICW","Internal connection Wifi")));
 //BA.debugLineNum = 946;BA.debugLine="Lst.Add(ValoresComunes.GetLanStringDefault (\"ECW\"";
_lst.Add((Object)(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"ECW","Extermal connection Wifi")));
 //BA.debugLineNum = 947;BA.debugLine="R=-1";
_r = (int) (-1);
 //BA.debugLineNum = 948;BA.debugLine="Do	While R<0";
while (_r<0) {
 //BA.debugLineNum = 949;BA.debugLine="R =InputList(Lst,ValoresComunes.GetLanStringDefa";
_r = anywheresoftware.b4a.keywords.Common.InputList(_lst,BA.ObjectToCharSequence(mostCurrent._valorescomunes._getlanstringdefault(mostCurrent.activityBA,"CMW","Connection mode  for this wifi network ")),(int) (0),mostCurrent.activityBA);
 }
;
 //BA.debugLineNum = 955;BA.debugLine="If R=0 Then";
if (_r==0) { 
 //BA.debugLineNum = 956;BA.debugLine="AddNewSsid";
_addnewssid();
 //BA.debugLineNum = 957;BA.debugLine="IniciarConexionInterna";
_iniciarconexioninterna();
 }else {
 //BA.debugLineNum = 959;BA.debugLine="IniciarConexionExterna";
_iniciarconexionexterna();
 };
 //BA.debugLineNum = 962;BA.debugLine="End Sub";
return "";
}
public static String  _tabconfig_click() throws Exception{
 //BA.debugLineNum = 1036;BA.debugLine="Sub TabConfig_Click";
 //BA.debugLineNum = 1038;BA.debugLine="End Sub";
return "";
}
public static String  _tabhost1_tabchanged() throws Exception{
 //BA.debugLineNum = 1033;BA.debugLine="Sub TabHost1_TabChanged";
 //BA.debugLineNum = 1035;BA.debugLine="End Sub";
return "";
}
}
