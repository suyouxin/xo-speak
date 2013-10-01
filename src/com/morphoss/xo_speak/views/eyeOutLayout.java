package com.morphoss.xo_speak.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.View;

public class eyeOutLayout extends View implements SurfaceHolder.Callback{

	private static final String TAG = "eyeOutLayout";
	
	public eyeOutLayout(Context context) {
		super(context);
	}
	
	public eyeOutLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public eyeOutLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		Paint pIn = new Paint();
		Paint pOut = new Paint();
		// smooths
		int width = this.getWidth()/2;
		int height = this.getHeight()/4;
		pIn.setColor(Color.WHITE);
		pOut.setColor(Color.BLACK);
		pIn.setStyle(Paint.Style.FILL); 
		pOut.setStyle(Paint.Style.STROKE);
		pOut.setStrokeWidth(4.0f);
		int w1 = width+height;
		int w2 = width-height;
		int r1 = width/18;
		int r2 = width/18;
		canvas.drawCircle(width+height, height, width/5, pIn);
		canvas.drawCircle(width+height, height,width/5, pOut);
		canvas.drawCircle(width-height, height, width/5, pIn);
		canvas.drawCircle(width-height, height, width/5, pOut);
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
