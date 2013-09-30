package com.morphoss.xo_speak.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.View;

public class MouthLayout extends View implements SurfaceHolder.Callback {

	private static Paint pIn = new Paint();
	private static Paint pOut = new Paint();
	public static int valueY;
	private int w;
	private int h;
	public MouthLayout(Context context) {
		super(context);
	}
	public MouthLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public MouthLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {

		w = this.getMeasuredWidth();
		h = this.getMeasuredHeight();
		PointF mPoint1 = new PointF(w/3, 2*h/3);
	    PointF mPoint2 = new PointF(2*w/3, 2*h/3);
	    Path myPathOutUp = new Path();
	    Path myPathOutDown = new Path();
	    Path myPathInUp = new Path();
	    Path myPathInDown = new Path();
	    
	    pOut.setAntiAlias(true);
	    pOut.setStyle(Style.STROKE);
	    pOut.setStrokeWidth(5.0f);
	    pOut.setColor(Color.BLACK);
	    
	    pIn.setAntiAlias(true);
	    pIn.setStyle(Style.FILL);
	    pIn.setColor(Color.LTGRAY);

	    valueY = h/6;
	    myPathOutUp = drawCurveUp(canvas, pOut, mPoint1, mPoint2, valueY);
	    canvas.drawPath(myPathOutUp, pOut);
	    myPathOutDown = drawCurveDown(canvas, pOut, mPoint1, mPoint2, valueY);
	    canvas.drawPath(myPathOutDown, pOut);
	    
	    myPathInUp = drawCurveUp(canvas, pIn, mPoint1, mPoint2, valueY);
	    canvas.drawPath(myPathInUp, pIn);
	    myPathInDown = drawCurveDown(canvas, pIn, mPoint1, mPoint2, valueY);
	    canvas.drawPath(myPathInDown, pIn);

	}

	private Path drawCurveUp(Canvas canvas, Paint paint, PointF mPointa, PointF mPointb, int valueY) {
		w = this.getMeasuredWidth();
		h = this.getMeasuredHeight();
	    Path myPath = new Path();
	    myPath.moveTo(mPointa.x, mPointa.y);
	    myPath.quadTo( mPointa.x+w/6, mPointa.y+valueY, mPointb.x, mPointb.y);
	    return myPath;  
	}
	private Path drawCurveDown(Canvas canvas, Paint paint, PointF mPointa, PointF mPointb, int valueY) {
		w = this.getMeasuredWidth();
		h = this.getMeasuredHeight();
	    Path myPath = new Path();
	    myPath.moveTo(mPointa.x, mPointa.y);
	    myPath.quadTo( mPointa.x+w/6, mPointa.y-valueY, mPointb.x, mPointb.y);
	    return myPath;  
	}

	public void mouthSpeaking(double i){
		w = this.getMeasuredWidth();
		h = this.getMeasuredHeight();
		valueY = (int) (h/(4 + i));
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
