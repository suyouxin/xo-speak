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
    	int w = this.getMeasuredWidth()/2;
		int h = this.getMeasuredHeight()/4;
		int r = w/12;
		int Y = h;
		
		int X1 = w;
		int X21 = w+h;
		int X22 = w-h;
		int X31 = w-2*h;
		int X32 = w;
		int X33 = w+2*h;
		int X41 = w-3*h;
		int X42 = w-h;
		int X43 = w+h;
		int X44 = w+3*h;
		int X51 = w-3*h;
		int X52 = (int) (w-1.5*h);
		int X53 = w;
		int X54 = (int) (w+1.5*h);
		int X55 = w+3*h;		
		
		if(Y < touchY){
			Log.d(TAG,"Y < touchY");
		double centerToPoint1 = Math.sqrt(Math.pow(touchX-X1, 2)+Math.pow(touchY-Y, 2));
		double centerToPoint21 = Math.sqrt(Math.pow(touchX-X21, 2)+Math.pow(touchY-Y, 2));
		double centerToPoint22 = Math.sqrt(Math.pow(touchX-X22, 2)+Math.pow(touchY-Y, 2));
		double centerToPoint31 = Math.sqrt(Math.pow(touchX-X31, 2)+Math.pow(touchY-Y, 2));
		double centerToPoint32 = Math.sqrt(Math.pow(touchX-X32, 2)+Math.pow(touchY-Y, 2));
		double centerToPoint33 = Math.sqrt(Math.pow(touchX-X33, 2)+Math.pow(touchY-Y, 2));
		double centerToPoint41 = Math.sqrt(Math.pow(touchX-X41, 2)+Math.pow(touchY-Y, 2));
		double centerToPoint42 = Math.sqrt(Math.pow(touchX-X42, 2)+Math.pow(touchY-Y, 2));
		double centerToPoint43 = Math.sqrt(Math.pow(touchX-X43, 2)+Math.pow(touchY-Y, 2));
		double centerToPoint44 = Math.sqrt(Math.pow(touchX-X44, 2)+Math.pow(touchY-Y, 2));
		double centerToPoint51 = Math.sqrt(Math.pow(touchX-X51, 2)+Math.pow(touchY-Y, 2));
		double centerToPoint52 = Math.sqrt(Math.pow(touchX-X52, 2)+Math.pow(touchY-Y, 2));
		double centerToPoint53 = Math.sqrt(Math.pow(touchX-X53, 2)+Math.pow(touchY-Y, 2));
		double centerToPoint54 = Math.sqrt(Math.pow(touchX-X54, 2)+Math.pow(touchY-Y, 2));
		double centerToPoint55 = Math.sqrt(Math.pow(touchX-X55, 2)+Math.pow(touchY-Y, 2));
		
		int projX1 = (int) (X1 + ((r*(touchX-X1))/centerToPoint1));
		int projX21 = (int) (X21 + ((r*(touchX-X21))/centerToPoint21));
		int projX22 = (int) (X22 + ((r*(touchX-X22))/centerToPoint22));
		int projX31 = (int) (X31 + ((r*(touchX-X31))/centerToPoint31));
		int projX32 = (int) (X32 + ((r*(touchX-X32))/centerToPoint32));
		int projX33 = (int) (X33 + ((r*(touchX-X33))/centerToPoint33));
		int projX41 = (int) (X41 + ((r*(touchX-X41))/centerToPoint41));
		int projX42 = (int) (X42 + ((r*(touchX-X42))/centerToPoint42));
		int projX43 = (int) (X43 + ((r*(touchX-X43))/centerToPoint43));
		int projX44 = (int) (X44 + ((r*(touchX-X44))/centerToPoint44));
		int projX51 = (int) (X51 + ((r*(touchX-X51))/centerToPoint51));
		int projX52 = (int) (X52 + ((r*(touchX-X52))/centerToPoint52));
		int projX53 = (int) (X53 + ((r*(touchX-X53))/centerToPoint53));
		int projX54 = (int) (X54 + ((r*(touchX-X54))/centerToPoint54));
		int projX55 = (int) (X55 + ((r*(touchX-X55))/centerToPoint55));
		
		int projY = (int) (Y + Math.sqrt(Math.pow(r, 2) - Math.pow(projX21 - X21, 2)));
		
		mEyeY = projY;
		mEye1 = projX1;
		mEye21 = projX21;
		mEye22 = projX22;
		mEye31 = projX31;
		mEye32 = projX32;
		mEye33 = projX33;
		mEye41 = projX41;
		mEye42 = projX42;
		mEye43 = projX43;
		mEye44 = projX44;
		mEye51 = projX51;
		mEye52 = projX52;
		mEye53 = projX53;
		mEye54 = projX54;
		mEye55 = projX55;
		
		
		
		
		}
		if(Y > touchY){
			Log.d(TAG,"Y > touchY, touchX > X1");
			double centerToPoint1a = Math.sqrt(Math.pow(touchX-X1, 2)+Math.pow(Y-touchY, 2));
			double centerToPoint21a = Math.sqrt(Math.pow(touchX-X21, 2)+Math.pow(Y-touchY, 2));
			double centerToPoint22a = Math.sqrt(Math.pow(touchX-X22, 2)+Math.pow(Y-touchY, 2));
			double centerToPoint31a = Math.sqrt(Math.pow(touchX-X31, 2)+Math.pow(Y-touchY, 2));
			double centerToPoint32a = Math.sqrt(Math.pow(touchX-X32, 2)+Math.pow(Y-touchY, 2));
			double centerToPoint33a = Math.sqrt(Math.pow(touchX-X33, 2)+Math.pow(Y-touchY, 2));
			double centerToPoint41a = Math.sqrt(Math.pow(touchX-X41, 2)+Math.pow(Y-touchY, 2));
			double centerToPoint42a = Math.sqrt(Math.pow(touchX-X42, 2)+Math.pow(Y-touchY, 2));
			double centerToPoint43a = Math.sqrt(Math.pow(touchX-X43, 2)+Math.pow(Y-touchY, 2));
			double centerToPoint44a = Math.sqrt(Math.pow(touchX-X44, 2)+Math.pow(Y-touchY, 2));
			double centerToPoint51a = Math.sqrt(Math.pow(touchX-X51, 2)+Math.pow(Y-touchY, 2));
			double centerToPoint52a = Math.sqrt(Math.pow(touchX-X52, 2)+Math.pow(Y-touchY, 2));
			double centerToPoint53a = Math.sqrt(Math.pow(touchX-X53, 2)+Math.pow(Y-touchY, 2));
			double centerToPoint54a = Math.sqrt(Math.pow(touchX-X54, 2)+Math.pow(Y-touchY, 2));
			double centerToPoint55a = Math.sqrt(Math.pow(touchX-X55, 2)+Math.pow(Y-touchY, 2));
			
			int projX1a = (int) (X1 + ((r*(touchX-X1))/centerToPoint1a));
			int projX21a = (int) (X21 + ((r*(touchX-X21))/centerToPoint21a));
			int projX22a = (int) (X22 + ((r*(touchX-X22))/centerToPoint22a));
			int projX31a = (int) (X31 + ((r*(touchX-X31))/centerToPoint31a));
			int projX32a = (int) (X32 + ((r*(touchX-X32))/centerToPoint32a));
			int projX33a = (int) (X33 + ((r*(touchX-X33))/centerToPoint33a));
			int projX41a = (int) (X41 + ((r*(touchX-X41))/centerToPoint41a));
			int projX42a = (int) (X42 + ((r*(touchX-X42))/centerToPoint42a));
			int projX43a = (int) (X43 + ((r*(touchX-X43))/centerToPoint43a));
			int projX44a = (int) (X44 + ((r*(touchX-X44))/centerToPoint44a));
			int projX51a = (int) (X51 + ((r*(touchX-X51))/centerToPoint51a));
			int projX52a = (int) (X52 + ((r*(touchX-X52))/centerToPoint52a));
			int projX53a = (int) (X53 + ((r*(touchX-X53))/centerToPoint53a));
			int projX54a = (int) (X54 + ((r*(touchX-X54))/centerToPoint54a));
			int projX55a = (int) (X55 + ((r*(touchX-X55))/centerToPoint55a));
			
			int projY = (int) (Y - Math.sqrt(Math.pow(r, 2) - Math.pow(projX21a - X21, 2)));
			
			mEyeY = projY;
			mEye1 = projX1a;
			mEye21 = projX21a;
			mEye22 = projX22a;
			mEye31 = projX31a;
			mEye32 = projX32a;
			mEye33 = projX33a;
			mEye41 = projX41a;
			mEye42 = projX42a;
			mEye43 = projX43a;
			mEye44 = projX44a;
			mEye51 = projX51a;
			mEye52 = projX52a;
			mEye53 = projX53a;
			mEye54 = projX54a;
			mEye55 = projX55a;			
		}
		
    }
	
    public void moveEye(double i) {
    	int w = this.getMeasuredWidth()/2;
		int h = this.getMeasuredHeight()/4;
		int r = w/12;
		int Y = h;
		
		int X1 = w;
		int X21 = w+h;
		int X22 = w-h;
		int X31 = w-2*h;
		int X32 = w;
		int X33 = w+2*h;
		int X41 = w-3*h;
		int X42 = w-h;
		int X43 = w+h;
		int X44 = w+3*h;
		int X51 = w-3*h;
		int X52 = (int) (w-1.5*h);
		int X53 = w;
		int X54 = (int) (w+1.5*h);
		int X55 = w+3*h;	
		
		mEyeY = Y;
		mEyeY += i;
			if(mEyeY >= Y+r ){
				mEyeY = Y - r;
			}
		mEye1 = X1;	
		mEye21 = X21;
		mEye22 = X22;
		mEye31 = X31;
		mEye32 = X32;
		mEye33 = X33;
		mEye41 = X41;
		mEye42 = X42;
		mEye43 = X43;
		mEye44 = X44;
		mEye51 = X51;
		mEye52 = X52;
		mEye53 = X53;
		mEye54 = X54;
		mEye55 = X55;
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
