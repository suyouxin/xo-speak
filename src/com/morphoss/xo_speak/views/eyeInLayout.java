package com.morphoss.xo_speak.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

public class eyeInLayout extends View implements SurfaceHolder.Callback {
	private int mEye1X;
	private int mEye1Y;
	private int mEye2X;
	private int mEye2Y;
	public static final String TAG = "eyeInLayout";
	
	private Paint p = new Paint();
	
	public eyeInLayout(Context context) {
		super(context);
	}
	
	public eyeInLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public eyeInLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = this.getMeasuredWidth()/2;
		int height = this.getMeasuredHeight()/4;
		mEye1X = width+height;
		mEye1Y = (int) (1.2*height);
		mEye2X = width-height;
		mEye2Y = (int) (1.2*height);
		Log.d(TAG,"eyeInLayout onMeasure w: " + width + " h: " + height);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// smooths
		int width = this.getWidth()/2;
		int height = this.getHeight()/4;
		p.setColor(Color.BLACK);
		p.setStyle(Paint.Style.FILL); 
		int radius = width/18;
		canvas.drawCircle(mEye1X, mEye1Y,radius, p);
		canvas.drawCircle(mEye2X, mEye2Y, radius, p);
	}
	
	@Override
	public boolean onTouchEvent (MotionEvent event) {
		
		switch ( event.getAction() ) {
        case MotionEvent.ACTION_DOWN:
        case MotionEvent.ACTION_MOVE:
        	Log.d("debug", "x: " + event.getX() + " y: " + event.getY());
        	calcEye((int)event.getX(), (int)event.getY());
        	this.invalidate();
        	break;

        default:
        	break;
           
		}
		return true;
	}

    public void calcEye(int touchX, int touchY) {
    	int width = this.getMeasuredWidth()/2;
		int height = this.getMeasuredHeight()/4;
		int radius = width/8;
    	
		int X1 = width+height;
		int Y1 = height;
		int X2 = width-height;
		int Y2 = height;
		
		if(Y1 < touchY){
			Log.d(TAG,"Y1 < touchY");
		double centerToPoint1 = Math.sqrt(Math.pow(touchX-X1, 2)+Math.pow(touchY-Y1, 2));
		double centerToPoint2 = Math.sqrt(Math.pow(touchX-X2, 2)+Math.pow(touchY-Y2, 2));
		int projX1 = (int) (X1 + ((radius*(touchX-X1))/centerToPoint1));
		int projX2 = (int) (X2 + ((radius*(touchX-X2))/centerToPoint2));
		int projY1 = (int) (Y1 + Math.sqrt(Math.pow(radius, 2) - Math.pow(projX1 - X1, 2)));
		int projY2 = (int) (Y2 + Math.sqrt(Math.pow(radius, 2) - Math.pow(projX2 - X2, 2)));
		mEye1X = projX1;
		mEye1Y = projY1;
		mEye2X = projX2;
		mEye2Y = projY2;
		}
		if(Y1 > touchY){
			Log.d(TAG,"Y1 > touchY, touchX > X1");
			double centerToPoint3 = Math.sqrt(Math.pow(touchX-X1, 2)+Math.pow(Y1-touchY, 2));
			double centerToPoint4 = Math.sqrt(Math.pow(touchX-X2, 2)+Math.pow(Y2-touchY, 2));
			int projX3 = (int) (X1 + ((radius*(touchX-X1))/centerToPoint3));
			int projX4 = (int) (X2 + ((radius*(touchX-X2))/centerToPoint4));
			int projY3 = (int) (Y1 - Math.sqrt(Math.pow(radius, 2) - Math.pow(projX3 - X1, 2)));
			int projY4 = (int) (Y2 - Math.sqrt(Math.pow(radius, 2) - Math.pow(projX4 - X2, 2)));
			Log.d(TAG,"X3 : "+projX3+" Y3 : "+projY3);
			Log.d(TAG,"X4 : "+projX4+" Y4 : "+projY4);
			mEye1X = projX3;
			mEye1Y = projY3;
			mEye2X = projX4;
			mEye2Y = projY4;
		}
		
    }
	
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
 
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setWillNotDraw(false);   //Allows us to use invalidate() to call onDraw()
        postInvalidate();
    }
 
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {}

}
