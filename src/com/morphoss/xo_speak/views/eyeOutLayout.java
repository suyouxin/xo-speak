package com.morphoss.xo_speak.views;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.View;

import com.morphoss.xo_speak.layout.EyeAlgorithm;
import com.morphoss.xo_speak.layout.EyeAlgorithmForFive;
import com.morphoss.xo_speak.layout.EyeAlgorithmForFour;
import com.morphoss.xo_speak.layout.EyeAlgorithmForOne;
import com.morphoss.xo_speak.layout.EyeAlgorithmForThree;
import com.morphoss.xo_speak.layout.EyeAlgorithmForTwo;
import com.morphoss.xo_speak.layout.EyeOutside;
import com.morphoss.xo_speak.layout.EyeShapeCircle;
import com.morphoss.xo_speak.layout.EyeShapeSquare;

public class eyeOutLayout extends View implements SurfaceHolder.Callback{

	
	ArrayList<EyeOutside> mEyeballs;
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
	
	public ArrayList<EyeOutside> createEyes(int numberEyes, int style) {
		ArrayList<EyeOutside> eyes = new ArrayList<EyeOutside>();
		
		for (int i = 0; i < numberEyes; i++) {
			// circle
			EyeOutside eye = null;
			if (style == 1) {
				eye = new EyeShapeCircle();
			}
			else if (style == 2) {
				eye = new EyeShapeSquare();
			}
			eyes.add(eye);
		}
		calcEyePosition(eyes);
		mEyeballs = eyes;
		return eyes;
	}
	
	public void calcEyePosition(ArrayList<EyeOutside> eyes) {
		EyeAlgorithm currentAlgorithm = mAlgorithms.get(eyes.size() - 1);
		currentAlgorithm.calc(eyes, this.getWidth(), this.getHeight());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		for (EyeOutside eyeball : mEyeballs) {
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
