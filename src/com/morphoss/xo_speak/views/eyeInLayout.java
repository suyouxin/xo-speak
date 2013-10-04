package com.morphoss.xo_speak.views;

import com.morphoss.xo_speak.OnNumberEyesSelectedListener;

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
	private int mEyeY;
	private int mEye1;
	private int mEye21;
	private int mEye22;
	private int mEye31;
	private int mEye32;
	private int mEye33;
	private int mEye41;
	private int mEye42;
	private int mEye43;
	private int mEye44;
	private int mEye51;
	private int mEye52;
	private int mEye53;
	private int mEye54;
	private int mEye55;
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
		int w = this.getMeasuredWidth()/2;
		int h = this.getMeasuredHeight()/4;
		mEyeY = (int) (1.2*h);
		mEye1 = w;
		mEye21 = w+h;
		mEye22 = w-h;
		mEye31 = w-2*h;
		mEye32 = w;
		mEye33 = w+2*h;
		mEye41 = w-3*h;
		mEye42 = w-h;
		mEye43 = w+h;
		mEye44 = w+3*h;	
		mEye51 = w-3*h;
		mEye52 = (int) (w-1.5*h);
		mEye53 = w;
		mEye54 = (int) (w+1.5*h);
		mEye55 = w+3*h;
		Log.d(TAG,"eyeInLayout onMeasure w: " + w + " h: " + h);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// smooths
		int w = this.getWidth()/2;
		int h = this.getHeight()/4;
		p.setColor(Color.BLACK);
		p.setStyle(Paint.Style.FILL); 
		int r = w/19;
		Log.d(TAG, "number of eyes inside : "+OnNumberEyesSelectedListener.numberEyes);
		if(OnNumberEyesSelectedListener.numberEyes == 1){
			//one eye
			canvas.drawCircle(mEye1, mEyeY, r, p);
		}
		if(OnNumberEyesSelectedListener.numberEyes == 2){
			//two eyes
			canvas.drawCircle(mEye21, mEyeY, r, p);
			canvas.drawCircle(mEye22, mEyeY,r, p);
		}	
		if(OnNumberEyesSelectedListener.numberEyes == 3){
			//three eyes
			canvas.drawCircle(mEye31, mEyeY, r, p);
			canvas.drawCircle(mEye32, mEyeY,r, p);
			canvas.drawCircle(mEye33, mEyeY, r, p);
		}
		if(OnNumberEyesSelectedListener.numberEyes == 4){
			//four eyes
			canvas.drawCircle(mEye41, mEyeY, r, p);
			canvas.drawCircle(mEye42, mEyeY, r, p);
			canvas.drawCircle(mEye43, mEyeY, r, p);
			canvas.drawCircle(mEye44, mEyeY, r, p);
		}
		if(OnNumberEyesSelectedListener.numberEyes == 5){
			//five eyes
			int r2=(int) (r/1.2);
			canvas.drawCircle(mEye51, mEyeY, r2, p);
			canvas.drawCircle(mEye52, mEyeY, r2, p);
			canvas.drawCircle(mEye53, mEyeY, r2, p);
			canvas.drawCircle(mEye54, mEyeY, r2, p);
			canvas.drawCircle(mEye55, mEyeY, r2, p);
		}
	
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
		int radius = width/10;
    	
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
		mEye21 = projX1;
		mEyeY = projY1;
		mEye22 = projX2;
		mEyeY = projY2;
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
			mEye21 = projX3;
			mEyeY = projY3;
			mEye22 = projX4;
			mEyeY = projY4;
		}
		
    }
	
    public void moveEye(double i) {
    	int width = this.getMeasuredWidth()/2;
		int height = this.getMeasuredHeight()/4;
		int radius = width/8;
    	
		int X1 = width+height;
		int Y1 = height;
		int X2 = width-height;
		int Y2 = height;
		mEyeY = Y1;
		mEyeY = Y2;
		mEye21 = X1;
		mEye22 = X2;
			mEyeY += i;
			mEyeY += i;
			if(mEyeY >= Y1+radius || mEyeY >= Y2+radius){
				mEyeY = Y1 - radius;
				mEyeY = Y2 - radius;
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
