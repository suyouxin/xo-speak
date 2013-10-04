package com.morphoss.xo_speak.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;

import com.morphoss.xo_speak.OnNumberEyesSelectedListener;

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
		int w = this.getWidth()/2;
		int h = this.getHeight()/4;
		int r = w/6;
		pIn.setColor(Color.WHITE);
		pOut.setColor(Color.BLACK);
		pIn.setStyle(Paint.Style.FILL); 
		pOut.setStyle(Paint.Style.STROKE);
		pOut.setStrokeWidth(4.0f);
		Log.d(TAG, "number of eyes : "+OnNumberEyesSelectedListener.numberEyes);
		if(OnNumberEyesSelectedListener.numberEyes == 1){
			//one eye
			int w1 = w;
			canvas.drawCircle(w1, h, r, pIn);
			canvas.drawCircle(w1, h,r, pOut);
		}
		if(OnNumberEyesSelectedListener.numberEyes == 2){
			//two eyes
			int w21 = w+h;
			int w22 = w-h;
			canvas.drawCircle(w21, h, r, pIn);
			canvas.drawCircle(w21, h,r, pOut);
			canvas.drawCircle(w22, h, r, pIn);
			canvas.drawCircle(w22, h, r, pOut);
		}	
		if(OnNumberEyesSelectedListener.numberEyes == 3){
			//three eyes
			int w31 = w-2*h;
			int w32 = w;
			int w33 = w+2*h;
			canvas.drawCircle(w31, h, r, pIn);
			canvas.drawCircle(w31, h,r, pOut);
			canvas.drawCircle(w32, h, r, pIn);
			canvas.drawCircle(w32, h, r, pOut);
			canvas.drawCircle(w33, h, r, pIn);
			canvas.drawCircle(w33, h, r, pOut);
		}
		if(OnNumberEyesSelectedListener.numberEyes == 4){
			//four eyes
			int w41 = w-3*h;
			int w42 = w-h;
			int w43 = w+h;
			int w44 = w+3*h;	
			canvas.drawCircle(w41, h, r, pIn);
			canvas.drawCircle(w41, h, r, pOut);
			canvas.drawCircle(w42, h, r, pIn);
			canvas.drawCircle(w42, h, r, pOut);
			canvas.drawCircle(w43, h, r, pIn);
			canvas.drawCircle(w43, h, r, pOut);
			canvas.drawCircle(w44, h, r, pIn);
			canvas.drawCircle(w44, h, r, pOut);
		}
		if(OnNumberEyesSelectedListener.numberEyes == 5){
			//five eyes
			int w51 = w-3*h;
			int w52 = (int) (w-1.5*h);
			int w53 = w;
			int w54 = (int) (w+1.5*h);
			int w55 = w+3*h;
			int r2 = (int) (r/1.2);
			canvas.drawCircle(w51, h, r2, pIn);
			canvas.drawCircle(w51, h, r2, pOut);
			canvas.drawCircle(w52, h, r2, pIn);
			canvas.drawCircle(w52, h, r2, pOut);
			canvas.drawCircle(w53, h, r2, pIn);
			canvas.drawCircle(w53, h, r2, pOut);
			canvas.drawCircle(w54, h, r2, pIn);
			canvas.drawCircle(w54, h, r2, pOut);
			canvas.drawCircle(w55, h, r2, pIn);
			canvas.drawCircle(w55, h, r2, pOut);
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
