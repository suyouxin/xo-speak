package com.morphoss.xo_speak.views;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.View;

import com.morphoss.xo_speak.coordinates.EyeCoordinatesForFive;
import com.morphoss.xo_speak.coordinates.EyeCoordinatesForFour;
import com.morphoss.xo_speak.coordinates.EyeCoordinatesForOne;
import com.morphoss.xo_speak.coordinates.EyeCoordinatesForThree;
import com.morphoss.xo_speak.coordinates.EyeCoordinatesForTwo;
import com.morphoss.xo_speak.coordinates.EyeInitCoordinates;
import com.morphoss.xo_speak.layout.EyeOutside;
import com.morphoss.xo_speak.layout.EyeShapeCircle;
import com.morphoss.xo_speak.layout.EyeShapeRoundedRect;
import com.morphoss.xo_speak.layout.EyeShapeSquare;

public class eyeOutLayout extends View implements SurfaceHolder.Callback{

	
	ArrayList<EyeOutside> mEyeOutside;
	ArrayList<EyeInitCoordinates> mCoordinates;
	
	private static final String TAG = "eyeOutLayout";
	//by default the shape of the eyes is "Round"
	public static int shapeEyes = 1;
	
	public eyeOutLayout(Context context) {
		super(context);
		initCoordinates();
	}
	
	public eyeOutLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initCoordinates();
	}
	public eyeOutLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initCoordinates();
	}
	
	public void initCoordinates() {
		mCoordinates = new ArrayList<EyeInitCoordinates>();
		mCoordinates.add(new EyeCoordinatesForOne());
		mCoordinates.add(new EyeCoordinatesForTwo());
		mCoordinates.add(new EyeCoordinatesForThree());
		mCoordinates.add(new EyeCoordinatesForFour());
		mCoordinates.add(new EyeCoordinatesForFive());
	}
	
	public ArrayList<EyeOutside> createEyes(int numberEyes, int style) {
		ArrayList<EyeOutside> eyes = new ArrayList<EyeOutside>();
		
		for (int i = 0; i < numberEyes; i++) {
			EyeOutside eye = null;
			if (style == 1) {
				//shape = round
				eye = new EyeShapeCircle();
			}
			else if (style == 2) {
				//shape = square
				eye = new EyeShapeSquare();
			}else if (style == 3) {
				//shape = glasses
				eye = new EyeShapeRoundedRect();
			}
			eyes.add(eye);
		}
		calcEyePosition(eyes);
		mEyeOutside = eyes;
		return eyes;
	}
	
	public void calcEyePosition(ArrayList<EyeOutside> eyes) {
		EyeInitCoordinates currentCoordinates = mCoordinates.get(eyes.size() - 1);
		currentCoordinates.calc(eyes, this.getWidth(), this.getHeight());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		for (EyeOutside eyeball : mEyeOutside) {
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
