package com.morphoss.xo_speak.views;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.morphoss.xo_speak.coordinates.EyeInitCoordinates;
import com.morphoss.xo_speak.layout.EyeInside;
import com.morphoss.xo_speak.layout.EyeOutside;

public class eyeInView extends View implements SurfaceHolder.Callback {
	
	ArrayList<EyeInside> mEyeInside;
	ArrayList<EyeInitCoordinates> mCoordinates;
	public static final String TAG = "eyeInView";
	
	public eyeInView(Context context) {
		super(context);
	}
	
	public eyeInView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public eyeInView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setEyes(ArrayList<EyeOutside> eyes) {
		int w = this.getMeasuredWidth()/2;
		mEyeInside = new ArrayList<EyeInside>();
		for (EyeOutside eyeball : eyes) {
			EyeInside insideEye = eyeball.getInsideEye();
			insideEye.ballX = insideEye.centerX;
			insideEye.ballY = insideEye.centerY+15;
			insideEye.ballRadius = w/16;
			mEyeInside.add(insideEye);
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		for (EyeInside eyeball : mEyeInside) {
			eyeball.draw(canvas);
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
		for (EyeOutside eyeOut : eyeOutView.mEyeOutside) {
			for(EyeInside eye : mEyeInside){
			int Y = eye.centerY;
			int r = (int) (eyeOut.radius/2.3);
			int radiusBall = (int) (eye.radius/2.5);
			if(Y < touchY){
			double centerToPoint = Math.sqrt(Math.pow(touchX-eye.centerX, 2)+Math.pow(touchY-Y, 2));	
			int projX = (int) (eye.centerX + ((r*(touchX-eye.centerX))/centerToPoint));
			int projY = (int) (Y + Math.sqrt(Math.pow(r, 2) - Math.pow(projX - eye.centerX, 2)));
			eye.ballX = projX;
			eye.ballY = projY;
			eye.ballRadius = radiusBall;
			}
			if(Y > touchY){
			double centerToPoint = Math.sqrt(Math.pow(touchX-eye.centerX, 2)+Math.pow(Y-touchY, 2));	
			int projX = (int) (eye.centerX + ((r*(touchX-eye.centerX))/centerToPoint));
			int projY = (int) (Y - Math.sqrt(Math.pow(r, 2) - Math.pow(projX - eye.centerX, 2)));
			eye.ballX = projX;
			eye.ballY = projY;
			eye.ballRadius = radiusBall;
			}
			}
		}
		
    }
	
    public void moveEye(double i) {
		for (EyeInside eye : mEyeInside) {
			int r = eye.radius;
			int Y = eye.centerY;
			eye.ballY = Y;
			eye.ballY += i;
			if(eye.ballY >= Y+r ){
				eye.ballY = Y - r;
			}
			eye.ballX = eye.centerX;
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
