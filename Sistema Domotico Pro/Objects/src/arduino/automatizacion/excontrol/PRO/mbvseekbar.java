package arduino.automatizacion.excontrol.PRO;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class mbvseekbar extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "arduino.automatizacion.excontrol.PRO.mbvseekbar");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", arduino.automatizacion.excontrol.PRO.mbvseekbar.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.PanelWrapper _mbbackground = null;
public anywheresoftware.b4a.objects.PanelWrapper _mbbtn = null;
public anywheresoftware.b4a.objects.ActivityWrapper _mbactivity = null;
public String _mbeventname = "";
public Object _mbmodule = null;
public anywheresoftware.b4a.objects.LabelWrapper _mblabel = null;
public double _halfbtnheight = 0;
public boolean _textvisible = false;
public com.AB.ABExtDrawing.ABExtDrawing.ABPaint _bgpaint = null;
public com.AB.ABExtDrawing.ABExtDrawing _mbline = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _cvs_disabled = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _cvs_normal = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _cvs_pressed = null;
public int _linecolor = 0;
public int _btncolor = 0;
public double _radius = 0;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _cvsmbbackground = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _arearect = null;
public int _currentvalue = 0;
public boolean _maxtoup = false;
public int _maxvalue = 0;
public int _stepvalue = 0;
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
public arduino.automatizacion.excontrol.PRO.actconfigcir _actconfigcir = null;
public arduino.automatizacion.excontrol.PRO.actpersianas _actpersianas = null;
public arduino.automatizacion.excontrol.PRO.actconfigcomandoscomu _actconfigcomandoscomu = null;
public arduino.automatizacion.excontrol.PRO.actconfigcomandos _actconfigcomandos = null;
public arduino.automatizacion.excontrol.PRO.actpersonalicon _actpersonalicon = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Private mbBackground As Panel";
_mbbackground = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 8;BA.debugLine="Private mbBtn As Panel";
_mbbtn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 9;BA.debugLine="Private mbActivity As Activity";
_mbactivity = new anywheresoftware.b4a.objects.ActivityWrapper();
 //BA.debugLineNum = 10;BA.debugLine="Private mbEventName As String";
_mbeventname = "";
 //BA.debugLineNum = 11;BA.debugLine="Private mbModule As Object";
_mbmodule = new Object();
 //BA.debugLineNum = 12;BA.debugLine="Private mbLabel As Label";
_mblabel = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Private halfBtnHeight As Double";
_halfbtnheight = 0;
 //BA.debugLineNum = 14;BA.debugLine="Private textVisible As Boolean";
_textvisible = false;
 //BA.debugLineNum = 15;BA.debugLine="Private bgPaint As ABPaint";
_bgpaint = new com.AB.ABExtDrawing.ABExtDrawing.ABPaint();
 //BA.debugLineNum = 16;BA.debugLine="Private mbLine As ABExtDrawing";
_mbline = new com.AB.ABExtDrawing.ABExtDrawing();
 //BA.debugLineNum = 17;BA.debugLine="Private cvs_disabled As Canvas";
_cvs_disabled = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private cvs_normal As Canvas";
_cvs_normal = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private cvs_pressed As Canvas";
_cvs_pressed = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private lineColor As Int";
_linecolor = 0;
 //BA.debugLineNum = 21;BA.debugLine="Private btnColor As Int";
_btncolor = 0;
 //BA.debugLineNum = 22;BA.debugLine="Private radius As Double";
_radius = 0;
 //BA.debugLineNum = 23;BA.debugLine="Private cvsmbBackground As Canvas";
_cvsmbbackground = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private areaRect As Rect";
_arearect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Public currentValue As Int";
_currentvalue = 0;
 //BA.debugLineNum = 26;BA.debugLine="Public maxtoUp As Boolean";
_maxtoup = false;
 //BA.debugLineNum = 27;BA.debugLine="Public  maxValue As Int";
_maxvalue = 0;
 //BA.debugLineNum = 28;BA.debugLine="Public stepValue As Int = 1 'default is 1";
_stepvalue = (int) (1);
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public String  _customizetext(int _fontcolor,int _fontsize,anywheresoftware.b4a.keywords.constants.TypefaceWrapper _mytypeface) throws Exception{
 //BA.debugLineNum = 190;BA.debugLine="Public Sub CustomizeText(fontColor As Int,FontSize";
 //BA.debugLineNum = 191;BA.debugLine="If textVisible = True Then";
if (_textvisible==__c.True) { 
 //BA.debugLineNum = 192;BA.debugLine="mbLabel.Typeface = myTypeface";
_mblabel.setTypeface((android.graphics.Typeface)(_mytypeface.getObject()));
 //BA.debugLineNum = 193;BA.debugLine="mbLabel.TextColor = fontColor";
_mblabel.setTextColor(_fontcolor);
 //BA.debugLineNum = 194;BA.debugLine="mbLabel.TextSize = FontSize";
_mblabel.setTextSize((float) (_fontsize));
 };
 //BA.debugLineNum = 196;BA.debugLine="End Sub";
return "";
}
public String  _disable() throws Exception{
 //BA.debugLineNum = 225;BA.debugLine="Public Sub Disable";
 //BA.debugLineNum = 226;BA.debugLine="mbBtn.SetBackgroundImage(cvs_disabled.Bitmap)";
_mbbtn.SetBackgroundImage((android.graphics.Bitmap)(_cvs_disabled.getBitmap().getObject()));
 //BA.debugLineNum = 227;BA.debugLine="Redraw_Background(False)";
_redraw_background(__c.False);
 //BA.debugLineNum = 228;BA.debugLine="mbBackground.Enabled = False";
_mbbackground.setEnabled(__c.False);
 //BA.debugLineNum = 229;BA.debugLine="If textVisible = True Then";
if (_textvisible==__c.True) { 
 //BA.debugLineNum = 230;BA.debugLine="mbLabel.Visible = False";
_mblabel.setVisible(__c.False);
 };
 //BA.debugLineNum = 232;BA.debugLine="End Sub";
return "";
}
public String  _enable() throws Exception{
 //BA.debugLineNum = 217;BA.debugLine="Public Sub Enable";
 //BA.debugLineNum = 218;BA.debugLine="mbBtn.SetBackgroundImage(cvs_normal.Bitmap)";
_mbbtn.SetBackgroundImage((android.graphics.Bitmap)(_cvs_normal.getBitmap().getObject()));
 //BA.debugLineNum = 219;BA.debugLine="Redraw_Background(True)";
_redraw_background(__c.True);
 //BA.debugLineNum = 220;BA.debugLine="mbBackground.Enabled = True";
_mbbackground.setEnabled(__c.True);
 //BA.debugLineNum = 221;BA.debugLine="If textVisible = True Then";
if (_textvisible==__c.True) { 
 //BA.debugLineNum = 222;BA.debugLine="mbLabel.Visible = True";
_mblabel.setVisible(__c.True);
 };
 //BA.debugLineNum = 224;BA.debugLine="End Sub";
return "";
}
public Object  _initialize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ActivityWrapper _myactivity,Object _module,String _eventname,int _myleft,int _mytop,int _mywidth,int _myheight,double _mystrokewidth,int _mystrokecolor,int _mybtncolor,boolean _mytextvisible,int _initvalue,int _mymax,boolean _mymaxtoup) throws Exception{
innerInitialize(_ba);
com.AB.ABExtDrawing.ABExtDrawing _btndrawing = null;
com.AB.ABExtDrawing.ABExtDrawing.ABPaint _btnpaint = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp_disabled = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp_pressed = null;
 //BA.debugLineNum = 32;BA.debugLine="Public Sub Initialize(myActivity As Activity,Modul";
 //BA.debugLineNum = 34;BA.debugLine="mbActivity = myActivity";
_mbactivity = _myactivity;
 //BA.debugLineNum = 35;BA.debugLine="mbEventName = EventName";
_mbeventname = _eventname;
 //BA.debugLineNum = 36;BA.debugLine="mbModule = Module";
_mbmodule = _module;
 //BA.debugLineNum = 37;BA.debugLine="textVisible = myTextvisible";
_textvisible = _mytextvisible;
 //BA.debugLineNum = 38;BA.debugLine="maxValue = myMax";
_maxvalue = _mymax;
 //BA.debugLineNum = 39;BA.debugLine="lineColor = myStrokecolor";
_linecolor = _mystrokecolor;
 //BA.debugLineNum = 40;BA.debugLine="btnColor = myBtncolor";
_btncolor = _mybtncolor;
 //BA.debugLineNum = 41;BA.debugLine="radius = myWidth/2";
_radius = _mywidth/(double)2;
 //BA.debugLineNum = 42;BA.debugLine="maxtoUp = mymaxtoUp";
_maxtoup = _mymaxtoup;
 //BA.debugLineNum = 45;BA.debugLine="mbBackground.Initialize(\"mbBackground\")";
_mbbackground.Initialize(ba,"mbBackground");
 //BA.debugLineNum = 46;BA.debugLine="mbBackground.color = Colors.Transparent";
_mbbackground.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 47;BA.debugLine="mbBackground.Tag =\"mbvs\"";
_mbbackground.setTag((Object)("mbvs"));
 //BA.debugLineNum = 48;BA.debugLine="mbActivity.AddView(mbBackground,myLeft,myTop,myWi";
_mbactivity.AddView((android.view.View)(_mbbackground.getObject()),_myleft,_mytop,_mywidth,_myheight);
 //BA.debugLineNum = 49;BA.debugLine="areaRect.Initialize(0,0,mbBackground.Width, mbBac";
_arearect.Initialize((int) (0),(int) (0),_mbbackground.getWidth(),_mbbackground.getHeight());
 //BA.debugLineNum = 50;BA.debugLine="cvsmbBackground.Initialize(mbBackground)";
_cvsmbbackground.Initialize((android.view.View)(_mbbackground.getObject()));
 //BA.debugLineNum = 52;BA.debugLine="bgPaint.Initialize";
_bgpaint.Initialize();
 //BA.debugLineNum = 53;BA.debugLine="bgPaint.SetAntiAlias(True)";
_bgpaint.SetAntiAlias(__c.True);
 //BA.debugLineNum = 54;BA.debugLine="bgPaint.SetStyle(bgPaint.Style_STROKE)";
_bgpaint.SetStyle(_bgpaint.Style_STROKE);
 //BA.debugLineNum = 55;BA.debugLine="bgPaint.SetStrokeWidth(myStrokewidth)";
_bgpaint.SetStrokeWidth((float) (_mystrokewidth));
 //BA.debugLineNum = 56;BA.debugLine="bgPaint.SetAlpha(145)";
_bgpaint.SetAlpha((int) (145));
 //BA.debugLineNum = 57;BA.debugLine="bgPaint.SetColor(lineColor)";
_bgpaint.SetColor(_linecolor);
 //BA.debugLineNum = 58;BA.debugLine="mbLine.drawLine(cvsmbBackground,radius, radius, r";
_mbline.drawLine(_cvsmbbackground,(float) (_radius),(float) (_radius),(float) (_radius),(float) (_mbbackground.getHeight()-_radius),_bgpaint);
 //BA.debugLineNum = 62;BA.debugLine="mbBtn.Initialize(\"mbBtn\")";
_mbbtn.Initialize(ba,"mbBtn");
 //BA.debugLineNum = 63;BA.debugLine="mbBtn.color = Colors.Transparent";
_mbbtn.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 65;BA.debugLine="mbBackground.AddView(mbBtn, 0,0, myWidth,myWidth)";
_mbbackground.AddView((android.view.View)(_mbbtn.getObject()),(int) (0),(int) (0),_mywidth,_mywidth);
 //BA.debugLineNum = 68;BA.debugLine="Dim btnDrawing As ABExtDrawing";
_btndrawing = new com.AB.ABExtDrawing.ABExtDrawing();
 //BA.debugLineNum = 69;BA.debugLine="Dim btnPaint As ABPaint";
_btnpaint = new com.AB.ABExtDrawing.ABExtDrawing.ABPaint();
 //BA.debugLineNum = 70;BA.debugLine="btnPaint.Initialize";
_btnpaint.Initialize();
 //BA.debugLineNum = 71;BA.debugLine="btnPaint.SetAntiAlias(True)";
_btnpaint.SetAntiAlias(__c.True);
 //BA.debugLineNum = 74;BA.debugLine="Dim bmp_disabled As Bitmap";
_bmp_disabled = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 75;BA.debugLine="bmp_disabled.InitializeMutable(myWidth,myWidth)";
_bmp_disabled.InitializeMutable(_mywidth,_mywidth);
 //BA.debugLineNum = 76;BA.debugLine="cvs_disabled.Initialize2(bmp_disabled)";
_cvs_disabled.Initialize2((android.graphics.Bitmap)(_bmp_disabled.getObject()));
 //BA.debugLineNum = 77;BA.debugLine="btnPaint.SetStyle(btnPaint.Style_FILL)";
_btnpaint.SetStyle(_btnpaint.Style_FILL);
 //BA.debugLineNum = 78;BA.debugLine="btnPaint.SetColor(Colors.RGB(136,136,136))";
_btnpaint.SetColor(__c.Colors.RGB((int) (136),(int) (136),(int) (136)));
 //BA.debugLineNum = 79;BA.debugLine="btnPaint.SetAlpha(77)";
_btnpaint.SetAlpha((int) (77));
 //BA.debugLineNum = 80;BA.debugLine="btnDrawing.drawCircle(cvs_disabled,radius,radius,";
_btndrawing.drawCircle(_cvs_disabled,(float) (_radius),(float) (_radius),(float) (_radius),_btnpaint);
 //BA.debugLineNum = 81;BA.debugLine="btnPaint.SetColor(myBtncolor)";
_btnpaint.SetColor(_mybtncolor);
 //BA.debugLineNum = 82;BA.debugLine="btnPaint.SetAlpha(255)";
_btnpaint.SetAlpha((int) (255));
 //BA.debugLineNum = 83;BA.debugLine="btnDrawing.drawCircle(cvs_disabled,radius,radius,";
_btndrawing.drawCircle(_cvs_disabled,(float) (_radius),(float) (_radius),(float) (_mywidth/(double)9),_btnpaint);
 //BA.debugLineNum = 87;BA.debugLine="Dim bmp_pressed As Bitmap";
_bmp_pressed = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 88;BA.debugLine="bmp_pressed.InitializeMutable(myWidth,myWidth)";
_bmp_pressed.InitializeMutable(_mywidth,_mywidth);
 //BA.debugLineNum = 89;BA.debugLine="cvs_pressed.Initialize2(bmp_pressed)";
_cvs_pressed.Initialize2((android.graphics.Bitmap)(_bmp_pressed.getObject()));
 //BA.debugLineNum = 90;BA.debugLine="btnPaint.SetStyle(btnPaint.Style_FILL)";
_btnpaint.SetStyle(_btnpaint.Style_FILL);
 //BA.debugLineNum = 91;BA.debugLine="btnPaint.SetColor(myBtncolor)";
_btnpaint.SetColor(_mybtncolor);
 //BA.debugLineNum = 92;BA.debugLine="btnPaint.SetAlpha(154)";
_btnpaint.SetAlpha((int) (154));
 //BA.debugLineNum = 93;BA.debugLine="btnDrawing.drawCircle(cvs_pressed,radius,radius,r";
_btndrawing.drawCircle(_cvs_pressed,(float) (_radius),(float) (_radius),(float) (_radius),_btnpaint);
 //BA.debugLineNum = 94;BA.debugLine="btnPaint.SetStyle(btnPaint.Style_STROKE)";
_btnpaint.SetStyle(_btnpaint.Style_STROKE);
 //BA.debugLineNum = 95;BA.debugLine="btnPaint.SetStrokeWidth(2dip)";
_btnpaint.SetStrokeWidth((float) (__c.DipToCurrent((int) (2))));
 //BA.debugLineNum = 96;BA.debugLine="btnDrawing.drawCircle(cvs_pressed,radius,radius,r";
_btndrawing.drawCircle(_cvs_pressed,(float) (_radius),(float) (_radius),(float) (_radius-__c.DipToCurrent((int) (2))),_btnpaint);
 //BA.debugLineNum = 97;BA.debugLine="If myTextvisible = False Then";
if (_mytextvisible==__c.False) { 
 //BA.debugLineNum = 98;BA.debugLine="btnPaint.SetAlpha(255)";
_btnpaint.SetAlpha((int) (255));
 //BA.debugLineNum = 99;BA.debugLine="btnPaint.SetStyle(btnPaint.Style_FILL)";
_btnpaint.SetStyle(_btnpaint.Style_FILL);
 //BA.debugLineNum = 100;BA.debugLine="btnDrawing.drawCircle(cvs_pressed,radius,radius,m";
_btndrawing.drawCircle(_cvs_pressed,(float) (_radius),(float) (_radius),(float) (_mywidth/(double)6),_btnpaint);
 };
 //BA.debugLineNum = 103;BA.debugLine="cvs_normal.Initialize(mbBtn)";
_cvs_normal.Initialize((android.view.View)(_mbbtn.getObject()));
 //BA.debugLineNum = 104;BA.debugLine="btnPaint.SetStyle(btnPaint.Style_FILL)";
_btnpaint.SetStyle(_btnpaint.Style_FILL);
 //BA.debugLineNum = 105;BA.debugLine="btnPaint.SetColor(myBtncolor)";
_btnpaint.SetColor(_mybtncolor);
 //BA.debugLineNum = 106;BA.debugLine="btnPaint.SetAlpha(154)";
_btnpaint.SetAlpha((int) (154));
 //BA.debugLineNum = 107;BA.debugLine="btnDrawing.drawCircle(cvs_normal,radius,radius,ra";
_btndrawing.drawCircle(_cvs_normal,(float) (_radius),(float) (_radius),(float) (_radius),_btnpaint);
 //BA.debugLineNum = 109;BA.debugLine="If myTextvisible = False Then";
if (_mytextvisible==__c.False) { 
 //BA.debugLineNum = 110;BA.debugLine="btnPaint.SetAlpha(255)";
_btnpaint.SetAlpha((int) (255));
 //BA.debugLineNum = 111;BA.debugLine="btnDrawing.drawCircle(cvs_normal,radius,radius,m";
_btndrawing.drawCircle(_cvs_normal,(float) (_radius),(float) (_radius),(float) (_mywidth/(double)6),_btnpaint);
 };
 //BA.debugLineNum = 115;BA.debugLine="mbActivity.Invalidate";
_mbactivity.Invalidate();
 //BA.debugLineNum = 117;BA.debugLine="If textVisible = True Then";
if (_textvisible==__c.True) { 
 //BA.debugLineNum = 118;BA.debugLine="mbLabel.Initialize(\"\")";
_mblabel.Initialize(ba,"");
 //BA.debugLineNum = 119;BA.debugLine="mbBtn.AddView(mbLabel,0,0,mbBtn.Width,mbBtn.Heig";
_mbbtn.AddView((android.view.View)(_mblabel.getObject()),(int) (0),(int) (0),_mbbtn.getWidth(),_mbbtn.getHeight());
 //BA.debugLineNum = 120;BA.debugLine="mbLabel.Gravity = Bit.OR(Gravity.CENTER_HORIZONT";
_mblabel.setGravity(__c.Bit.Or(__c.Gravity.CENTER_HORIZONTAL,__c.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 121;BA.debugLine="CustomizeText(Colors.White,14,Typeface.DEFAULT)";
_customizetext(__c.Colors.White,(int) (14),(anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(__c.Typeface.DEFAULT)));
 //BA.debugLineNum = 122;BA.debugLine="mbLabel.Text = returnValue";
_mblabel.setText(BA.ObjectToCharSequence(_returnvalue()));
 };
 //BA.debugLineNum = 125;BA.debugLine="setValue (initValue)";
_setvalue(_initvalue);
 //BA.debugLineNum = 127;BA.debugLine="End Sub";
return null;
}
public String  _mbbackground_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 129;BA.debugLine="Private Sub mbBackground_Touch (Action As Int, X A";
 //BA.debugLineNum = 130;BA.debugLine="mbBtn.Top = Y - radius";
_mbbtn.setTop((int) (_y-_radius));
 //BA.debugLineNum = 131;BA.debugLine="Select Action";
switch (BA.switchObjectToInt(_action,_mbactivity.ACTION_DOWN,_mbactivity.ACTION_UP)) {
case 0: {
 //BA.debugLineNum = 133;BA.debugLine="mbBtn.SetBackgroundImage(cvs_pressed.Bitmap)";
_mbbtn.SetBackgroundImage((android.graphics.Bitmap)(_cvs_pressed.getBitmap().getObject()));
 break; }
case 1: {
 //BA.debugLineNum = 135;BA.debugLine="mbBtn.SetBackgroundImage(cvs_normal.Bitmap)";
_mbbtn.SetBackgroundImage((android.graphics.Bitmap)(_cvs_normal.getBitmap().getObject()));
 //BA.debugLineNum = 136;BA.debugLine="setValue(Round(returnValue/stepValue) * stepVal";
_setvalue((int) (__c.Round((double)(Double.parseDouble(_returnvalue()))/(double)_stepvalue)*_stepvalue));
 break; }
}
;
 //BA.debugLineNum = 141;BA.debugLine="If mbBtn.Top < 0 Then";
if (_mbbtn.getTop()<0) { 
 //BA.debugLineNum = 142;BA.debugLine="mbBtn.Top = 0";
_mbbtn.setTop((int) (0));
 };
 //BA.debugLineNum = 144;BA.debugLine="If (mbBtn.Top + mbBtn.Height) > mbBackground.He";
if ((_mbbtn.getTop()+_mbbtn.getHeight())>_mbbackground.getHeight()) { 
 //BA.debugLineNum = 145;BA.debugLine="mbBtn.Top =  mbBackground.Height - mbBtn.Heigh";
_mbbtn.setTop((int) (_mbbackground.getHeight()-_mbbtn.getHeight()));
 };
 //BA.debugLineNum = 149;BA.debugLine="Redraw_Background(True)";
_redraw_background(__c.True);
 //BA.debugLineNum = 150;BA.debugLine="currentValue = returnValue";
_currentvalue = (int)(Double.parseDouble(_returnvalue()));
 //BA.debugLineNum = 151;BA.debugLine="If textVisible = True Then";
if (_textvisible==__c.True) { 
 //BA.debugLineNum = 152;BA.debugLine="mbLabel.text = returnValue";
_mblabel.setText(BA.ObjectToCharSequence(_returnvalue()));
 };
 //BA.debugLineNum = 154;BA.debugLine="If SubExists(mbModule, mbEventName) Then";
if (__c.SubExists(ba,_mbmodule,_mbeventname)) { 
 //BA.debugLineNum = 155;BA.debugLine="CallSub2(mbModule, mbEventName,returnValue)";
__c.CallSubNew2(ba,_mbmodule,_mbeventname,(Object)(_returnvalue()));
 };
 //BA.debugLineNum = 157;BA.debugLine="End Sub";
return "";
}
public String  _redraw_background(boolean _enabled) throws Exception{
 //BA.debugLineNum = 158;BA.debugLine="Public Sub Redraw_Background(Enabled As Boolean)";
 //BA.debugLineNum = 159;BA.debugLine="cvsmbBackground.drawRect(areaRect, Colors.Transpa";
_cvsmbbackground.DrawRect((android.graphics.Rect)(_arearect.getObject()),__c.Colors.Transparent,__c.True,(float) (0));
 //BA.debugLineNum = 160;BA.debugLine="mbBackground.Invalidate";
_mbbackground.Invalidate();
 //BA.debugLineNum = 161;BA.debugLine="bgPaint.SetColor(lineColor)";
_bgpaint.SetColor(_linecolor);
 //BA.debugLineNum = 162;BA.debugLine="bgPaint.SetAlpha(145)";
_bgpaint.SetAlpha((int) (145));
 //BA.debugLineNum = 164;BA.debugLine="mbLine.drawLine(cvsmbBackground,radius, radius, r";
_mbline.drawLine(_cvsmbbackground,(float) (_radius),(float) (_radius),(float) (_radius),(float) (_mbbackground.getHeight()-_radius),_bgpaint);
 //BA.debugLineNum = 165;BA.debugLine="If Enabled = True Then 'if seekbar is enabled";
if (_enabled==__c.True) { 
 //BA.debugLineNum = 166;BA.debugLine="bgPaint.SetColor(btnColor)";
_bgpaint.SetColor(_btncolor);
 //BA.debugLineNum = 167;BA.debugLine="bgPaint.SetAlpha(255)";
_bgpaint.SetAlpha((int) (255));
 //BA.debugLineNum = 168;BA.debugLine="If (textVisible = True) Then 'if text is visibl";
if ((_textvisible==__c.True)) { 
 //BA.debugLineNum = 170;BA.debugLine="If maxtoUp = True Then ' max is set to top";
if (_maxtoup==__c.True) { 
 //BA.debugLineNum = 171;BA.debugLine="If mbBtn.Top < (mbBackground.Height - mbBtn.H";
if (_mbbtn.getTop()<(_mbbackground.getHeight()-_mbbtn.getHeight()-_radius)) { 
 //BA.debugLineNum = 172;BA.debugLine="mbLine.drawLine(cvsmbBackground,radius, mbBt";
_mbline.drawLine(_cvsmbbackground,(float) (_radius),(float) (_mbbtn.getTop()+_mbbtn.getHeight()),(float) (_radius),(float) (_mbbackground.getHeight()-_radius),_bgpaint);
 };
 }else {
 //BA.debugLineNum = 176;BA.debugLine="If mbBtn.Top > radius Then";
if (_mbbtn.getTop()>_radius) { 
 //BA.debugLineNum = 177;BA.debugLine="mbLine.drawLine(cvsmbBackground,radius, radi";
_mbline.drawLine(_cvsmbbackground,(float) (_radius),(float) (_radius),(float) (_radius),(float) (_mbbtn.getTop()),_bgpaint);
 };
 };
 }else {
 //BA.debugLineNum = 182;BA.debugLine="If maxtoUp = True Then ' max is at the top";
if (_maxtoup==__c.True) { 
 //BA.debugLineNum = 183;BA.debugLine="mbLine.drawLine(cvsmbBackground,radius, mbBt";
_mbline.drawLine(_cvsmbbackground,(float) (_radius),(float) (_mbbtn.getTop()+_radius),(float) (_radius),(float) (_mbbackground.getHeight()-_radius),_bgpaint);
 }else {
 //BA.debugLineNum = 185;BA.debugLine="mbLine.drawLine(cvsmbBackground,radius, radi";
_mbline.drawLine(_cvsmbbackground,(float) (_radius),(float) (_radius),(float) (_radius),(float) (_mbbtn.getTop()+_radius),_bgpaint);
 };
 };
 };
 //BA.debugLineNum = 189;BA.debugLine="End Sub";
return "";
}
public String  _returnvalue() throws Exception{
 //BA.debugLineNum = 197;BA.debugLine="Public Sub returnValue";
 //BA.debugLineNum = 198;BA.debugLine="If maxtoUp = True Then ' max is at the top";
if (_maxtoup==__c.True) { 
 //BA.debugLineNum = 199;BA.debugLine="Return  maxValue - Round (mbBtn.Top / ((mbBackgr";
if (true) return BA.NumberToString(_maxvalue-__c.Round(_mbbtn.getTop()/(double)((_mbbackground.getHeight()-_mbbtn.getHeight())/(double)_maxvalue)));
 }else {
 //BA.debugLineNum = 201;BA.debugLine="Return  Round (mbBtn.Top / ((mbBackground.Height";
if (true) return BA.NumberToString(__c.Round(_mbbtn.getTop()/(double)((_mbbackground.getHeight()-_mbbtn.getHeight())/(double)_maxvalue)));
 };
 //BA.debugLineNum = 203;BA.debugLine="End Sub";
return "";
}
public String  _setvalue(int _value) throws Exception{
 //BA.debugLineNum = 204;BA.debugLine="Public Sub setValue(Value As Int)";
 //BA.debugLineNum = 205;BA.debugLine="currentValue = Value";
_currentvalue = _value;
 //BA.debugLineNum = 207;BA.debugLine="If maxtoUp = True Then ' max is at the top";
if (_maxtoup==__c.True) { 
 //BA.debugLineNum = 208;BA.debugLine="mbBtn.Top = mbBackground.Height-mbBtn.Height - R";
_mbbtn.setTop((int) (_mbbackground.getHeight()-_mbbtn.getHeight()-__c.Round(_value*((_mbbackground.getHeight()-_mbbtn.getHeight())/(double)_maxvalue))));
 }else {
 //BA.debugLineNum = 210;BA.debugLine="mbBtn.Top = Round(Value * ((mbBackground.Height-";
_mbbtn.setTop((int) (__c.Round(_value*((_mbbackground.getHeight()-_mbbtn.getHeight())/(double)_maxvalue))));
 };
 //BA.debugLineNum = 212;BA.debugLine="Redraw_Background(True)";
_redraw_background(__c.True);
 //BA.debugLineNum = 213;BA.debugLine="If textVisible = True Then";
if (_textvisible==__c.True) { 
 //BA.debugLineNum = 214;BA.debugLine="mbLabel.text = returnValue";
_mblabel.setText(BA.ObjectToCharSequence(_returnvalue()));
 };
 //BA.debugLineNum = 216;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
