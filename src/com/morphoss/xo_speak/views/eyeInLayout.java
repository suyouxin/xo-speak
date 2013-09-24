package com.morphoss.xo_speak.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

public class eyeInLayout extends View implements SurfaceHolder.Callback {
	private int mEye1X;
	private int mEye1Y;
	private int mEye2X;
	private int mEye2Y;
	
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
		int width = this.getWidth()/2;
		int height = this.getHeight()/4;
		mEye1X = width+height;
		mEye1Y = (int) (1.2*height);
		mEye2X = width-height;
		mEye2Y = (int) (1.2*height);
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
        	Log.d("debug", "x: " + event.getX() + " y: " + event.getY());
        	calcEye((int)event.getX(), (int)event.getY());
        	this.invalidate();
        	break;
        default:
        	break;
           
		}
		return false;
	}

    private void calcEye(int touchX, int touchY) {
    	int width = this.getWidth()/2;
		int height = this.getHeight()/4;
		int radius = width/8;
    	
		int X1 = width+height;
		int Y1 = height;
		int X2 = width-height;
		int Y2 = height;
		
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
