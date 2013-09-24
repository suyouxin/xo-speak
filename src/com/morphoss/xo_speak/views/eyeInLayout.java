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
	protected void onDraw(Canvas canvas) {
		// smooths
		int width = this.getWidth()/2;
		int height = this.getHeight()/4;
		p.setColor(Color.BLACK);
		p.setStyle(Paint.Style.FILL); 
		int radius = width/12;
		canvas.drawCircle(mEye1X, mEye1Y,radius, p);
		canvas.drawCircle(mEye2X, mEye2Y, radius, p);
	}
	
	@Override
	public boolean onTouchEvent (MotionEvent event) {
		
		switch ( event.getAction() ) {
        case MotionEvent.ACTION_DOWN:
        	Log.d("debug", "x: " + event.getX() + " y: " + event.getY());
        	calcEye((int)event.getX(), (int)event.getY(), 0 ,0);
        	this.invalidate();
        	break;
        default:
        	break;
           
		}
		return false;
	}

    private void calcEye(int touchX, int touchY, int centerX, int centerY) {
    	int width = this.getWidth()/2;
		int height = this.getHeight()/4;
		int radius = width/12;
    	
		int X1 = width+height;
		int Y1 = height;
		int X2 = width-height;
		int Y2 = height;
		
		int x = touchX;
		int x2 = touchX;
    	if (touchX > X1 + radius)
    		x = X1 + radius;
    	if (touchX < X1 - radius)
    		x = X1 - radius;
    	if(touchX > X2 + radius)
    		x2 = X2 + radius;
    	if (touchX < X2 - radius)
    		x2 = X2 - radius;
    	
    	double y = Math.sqrt(Math.pow(radius, 2) - Math.pow(x - X1, 2)) + Y1;
    	double y2 = Math.sqrt(Math.pow(radius, 2) - Math.pow(x2 - X2, 2)) + Y2;
    	mEye1X = x;
		mEye1Y = (int)y;
		mEye2X = x2;
		mEye2Y = (int)y;
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
