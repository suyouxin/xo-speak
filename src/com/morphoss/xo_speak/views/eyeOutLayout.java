package com.morphoss.xo_speak.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;

import com.morphoss.xo_speak.OnNumberEyesSelectedListener;

public class eyeOutLayout extends View implements SurfaceHolder.Callback{

	private static final String TAG = "eyeOutLayout";
	public static int shapeEyes = 1;
	
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
		int w = this.getWidth()/2;
		int h = this.getHeight()/4;
		int r = w/6;
		int r1 = (int) (w/5.5);
		pIn.setColor(Color.WHITE);
		pOut.setColor(Color.BLACK);
		pIn.setStyle(Paint.Style.FILL); 
		pOut.setStyle(Paint.Style.STROKE);
		pOut.setStrokeWidth(4.0f);
		Log.d(TAG, "number of eyes : "+OnNumberEyesSelectedListener.numberEyes);
		Log.d(TAG, "style of eyes : "+shapeEyes);
		if(OnNumberEyesSelectedListener.numberEyes == 1){
			//one eye
			int w1 = w;
			if(shapeEyes == 1){
				canvas.drawCircle(w1, h, r, pIn);
				canvas.drawCircle(w1, h,r, pOut);
			}
			if(shapeEyes == 2){
				canvas.drawRect(w1+r1, h-r1, w1-r1, h+r1, pIn);
				canvas.drawRect(w1+r1, h-r1, w1-r1, h+r1, pOut);
			}
			if(shapeEyes == 3){
				//canvas.drawRect(w1+r1, h-r1, w1-r1, h+r1, pIn);
				//canvas.drawRect(w1+r1, h-r1, w1-r1, h+r1, pOut);
			}
			
		}
		if(OnNumberEyesSelectedListener.numberEyes == 2){
			//two eyes
			int w21 = w+h;
			int w22 = w-h;
			if(shapeEyes == 1){
				canvas.drawCircle(w21, h, r, pIn);
				canvas.drawCircle(w21, h,r, pOut);
				canvas.drawCircle(w22, h, r, pIn);
				canvas.drawCircle(w22, h, r, pOut);
			}
			if(shapeEyes == 2){
				canvas.drawRect(w21+r1, h-r1, w21-r1, h+r1, pIn);
				canvas.drawRect(w21+r1, h-r1, w21-r1, h+r1, pOut);
				canvas.drawRect(w22+r1, h-r1, w22-r1, h+r1, pIn);
				canvas.drawRect(w22+r1, h-r1, w22-r1, h+r1, pOut);
			}
		}	
		if(OnNumberEyesSelectedListener.numberEyes == 3){
			//three eyes
			int w31 = w-2*h;
			int w32 = w;
			int w33 = w+2*h;
			if(shapeEyes == 1){
			canvas.drawCircle(w31, h, r, pIn);
			canvas.drawCircle(w31, h,r, pOut);
			canvas.drawCircle(w32, h, r, pIn);
			canvas.drawCircle(w32, h, r, pOut);
			canvas.drawCircle(w33, h, r, pIn);
			canvas.drawCircle(w33, h, r, pOut);
			}
			if(shapeEyes == 2){
				canvas.drawRect(w31+r1, h-r1, w31-r1, h+r1, pIn);
				canvas.drawRect(w31+r1, h-r1, w31-r1, h+r1, pOut);
				canvas.drawRect(w32+r1, h-r1, w32-r1, h+r1, pIn);
				canvas.drawRect(w32+r1, h-r1, w32-r1, h+r1, pOut);
				canvas.drawRect(w33+r1, h-r1, w33-r1, h+r1, pIn);
				canvas.drawRect(w33+r1, h-r1, w33-r1, h+r1, pOut);
			}
		}
		if(OnNumberEyesSelectedListener.numberEyes == 4){
			//four eyes
			int w41 = w-3*h;
			int w42 = w-h;
			int w43 = w+h;
			int w44 = w+3*h;	
			if(shapeEyes == 1){
				canvas.drawCircle(w41, h, r, pIn);
				canvas.drawCircle(w41, h, r, pOut);
				canvas.drawCircle(w42, h, r, pIn);
				canvas.drawCircle(w42, h, r, pOut);
				canvas.drawCircle(w43, h, r, pIn);
				canvas.drawCircle(w43, h, r, pOut);
				canvas.drawCircle(w44, h, r, pIn);
				canvas.drawCircle(w44, h, r, pOut);
			}
			if(shapeEyes == 2){
				canvas.drawRect(w41+r1, h-r1, w41-r1, h+r1, pIn);
				canvas.drawRect(w41+r1, h-r1, w41-r1, h+r1, pOut);
				canvas.drawRect(w42+r1, h-r1, w42-r1, h+r1, pIn);
				canvas.drawRect(w42+r1, h-r1, w42-r1, h+r1, pOut);
				canvas.drawRect(w43+r1, h-r1, w43-r1, h+r1, pIn);
				canvas.drawRect(w43+r1, h-r1, w43-r1, h+r1, pOut);
				canvas.drawRect(w44+r1, h-r1, w44-r1, h+r1, pIn);
				canvas.drawRect(w44+r1, h-r1, w44-r1, h+r1, pOut);
			}
		}
		if(OnNumberEyesSelectedListener.numberEyes == 5){
			//five eyes
			int w51 = w-3*h;
			int w52 = (int) (w-1.5*h);
			int w53 = w;
			int w54 = (int) (w+1.5*h);
			int w55 = w+3*h;
			int r5 = (int) (r/1.2);
			int r6 = (int) (r1/1.2);
			if(shapeEyes == 1){
				canvas.drawCircle(w51, h, r5, pIn);
				canvas.drawCircle(w51, h, r5, pOut);
				canvas.drawCircle(w52, h, r5, pIn);
				canvas.drawCircle(w52, h, r5, pOut);
				canvas.drawCircle(w53, h, r5, pIn);
				canvas.drawCircle(w53, h, r5, pOut);
				canvas.drawCircle(w54, h, r5, pIn);
				canvas.drawCircle(w54, h, r5, pOut);
				canvas.drawCircle(w55, h, r5, pIn);
				canvas.drawCircle(w55, h, r5, pOut);
			}
			if(shapeEyes == 2){
				canvas.drawRect(w51+r6, h-r6, w51-r6, h+r6, pIn);
				canvas.drawRect(w51+r6, h-r6, w51-r6, h+r6, pOut);
				canvas.drawRect(w52+r6, h-r6, w52-r6, h+r6, pIn);
				canvas.drawRect(w52+r6, h-r6, w52-r6, h+r6, pOut);
				canvas.drawRect(w53+r6, h-r6, w53-r6, h+r6, pIn);
				canvas.drawRect(w53+r6, h-r6, w53-r6, h+r6, pOut);
				canvas.drawRect(w54+r6, h-r6, w54-r6, h+r6, pIn);
				canvas.drawRect(w54+r6, h-r6, w54-r6, h+r6, pOut);
				canvas.drawRect(w55+r6, h-r6, w55-r6, h+r6, pIn);
				canvas.drawRect(w55+r6, h-r6, w55-r6, h+r6, pOut);
			}
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
