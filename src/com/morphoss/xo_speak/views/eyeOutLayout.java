package com.morphoss.xo_speak.views;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.View;

import com.morphoss.xo_speak.EyeBall;
import com.morphoss.xo_speak.EyeBallCircle;
import com.morphoss.xo_speak.EyeBallSquare;

public class eyeOutLayout extends View implements SurfaceHolder.Callback{

	class EyeAlgorithm {
		int w;
		int h;
		int r;
		
		void calc(ArrayList<EyeBall> eyes, int canvasWidth, int canvasHeight) {
			w = canvasWidth / 2;
			h = canvasHeight / 4;
			r = w / 6;
			
			for (EyeBall eye : eyes) {
				eye.centerX = w;
				eye.centerY = h;
				eye.radius = r;
			}
		}
	}
	
	class EyeAlgorithmForOne extends EyeAlgorithm {
		
		@Override
		void calc(ArrayList<EyeBall> eyes, int canvasWidth, int canvasHeight) {
			super.calc(eyes, canvasWidth, canvasHeight);
		}
	}
	
	class EyeAlgorithmForTwo extends EyeAlgorithm {
		@Override
		void calc(ArrayList<EyeBall> eyes, int canvasWidth, int canvasHeight) {
			super.calc(eyes, canvasWidth, canvasHeight);
			
			eyes.get(0).centerX = w + h;
			eyes.get(1).centerX = w - h;
		}
	}
	
	class EyeAlgorithmForThree extends EyeAlgorithm {
		@Override
		void calc(ArrayList<EyeBall> eyes, int canvasWidth, int canvasHeight) {
			super.calc(eyes, canvasWidth, canvasHeight);
			
			eyes.get(0).centerX = w-2*h;
			eyes.get(1).centerX = w;
			eyes.get(2).centerX = w+2*h;
		}
	}
	class EyeAlgorithmForFour extends EyeAlgorithm {
		@Override
		void calc(ArrayList<EyeBall> eyes, int canvasWidth, int canvasHeight) {
			super.calc(eyes, canvasWidth, canvasHeight);		
			eyes.get(0).centerX = w - 3*h;
			eyes.get(1).centerX = w - h;
			eyes.get(2).centerX = w + h;
			eyes.get(3).centerX = w + 3*h;
		}
	}
	class EyeAlgorithmForFive extends EyeAlgorithm {
		@Override
		void calc(ArrayList<EyeBall> eyes, int canvasWidth, int canvasHeight) {
			super.calc(eyes, canvasWidth, canvasHeight);
			
			eyes.get(0).centerX = w-3*h;
			eyes.get(0).radius /= 1.2;
			eyes.get(1).centerX = (int) (w-1.5*h);
			eyes.get(1).radius /= 1.2;
			eyes.get(2).centerX = w;
			eyes.get(2).radius /= 1.2;
			eyes.get(3).centerX = (int) (w+1.5*h);
			eyes.get(3).radius /= 1.2;
			eyes.get(4).centerX = w+3*h;
			eyes.get(4).radius /= 1.2;
		}
	}
	
	
	
	ArrayList<EyeBall> mEyeballs;
	ArrayList<EyeAlgorithm> mAlgorithms;
	
	private static final String TAG = "eyeOutLayout";
	public static int shapeEyes = 1;
	
	public eyeOutLayout(Context context) {
		super(context);
		initAlgorithms();
	}
	
	public eyeOutLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAlgorithms();
	}
	public eyeOutLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAlgorithms();
	}
	
	public void initAlgorithms() {
		mAlgorithms = new ArrayList<EyeAlgorithm>();
		mAlgorithms.add(new EyeAlgorithmForOne());
		mAlgorithms.add(new EyeAlgorithmForTwo());
		mAlgorithms.add(new EyeAlgorithmForThree());
		mAlgorithms.add(new EyeAlgorithmForFour());
		mAlgorithms.add(new EyeAlgorithmForFive());
	}
	
	public ArrayList<EyeBall> createEyes(int numberEyes, int style) {
		ArrayList<EyeBall> eyes = new ArrayList<EyeBall>();
		
		for (int i = 0; i < numberEyes; i++) {
			// circle
			EyeBall eye = null;
			if (style == 1) {
				eye = new EyeBallCircle();
			}
			else if (style == 2) {
				eye = new EyeBallSquare();
			}
			eyes.add(eye);
		}
		calcEyePosition(eyes);
		mEyeballs = eyes;
		return eyes;
	}
	
	public void calcEyePosition(ArrayList<EyeBall> eyes) {
		EyeAlgorithm currentAlgorithm = mAlgorithms.get(eyes.size() - 1);
		currentAlgorithm.calc(eyes, this.getWidth(), this.getHeight());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		for (EyeBall eyeball : mEyeballs) {
			eyeball.draw(canvas);
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
