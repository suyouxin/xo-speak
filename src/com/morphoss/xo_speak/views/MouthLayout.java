package com.morphoss.xo_speak.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.View;

public class MouthLayout extends View implements SurfaceHolder.Callback {

	private static Paint p = new Paint();
	private int width;
	private int height;
	public MouthLayout(Context context) {
		super(context);
	}
	public MouthLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public MouthLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		// smooths
		width = this.getMeasuredWidth()/2;
		height = this.getMeasuredHeight()/4;
		p.setColor(Color.BLACK);
		p.setStyle(Paint.Style.STROKE); 
		p.setStrokeWidth(4.0f);
		canvas.drawLine(width-height, 3*height, width+height, 3*height, p);
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
