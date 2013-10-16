package com.morphoss.xo_speak.views;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
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
import com.morphoss.xo_speak.listeners.OnNumberEyesSelectedListener;
import com.morphoss.xo_speak.listeners.OnStyleEyesSelectedListener;

@SuppressLint("DrawAllocation")
public class eyeOutView extends View implements SurfaceHolder.Callback{

	
	public static ArrayList<EyeOutside> mEyeOutside;
	ArrayList<EyeInitCoordinates> mCoordinates;
	
	private static final String TAG = "eyeOutView";
	//by default the shape of the eyes is "Round"
	public static int shapeEyes = 1;
	
	public eyeOutView(Context context) {
		super(context);
		initCoordinates();
	}
	
	public eyeOutView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initCoordinates();
	}
	public eyeOutView(Context context, AttributeSet attrs, int defStyle) {
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
		
		Paint pLine = new Paint();
		Paint pEyebrow = new Paint();
		
		pLine.setAntiAlias(true);
		pEyebrow.setAntiAlias(true);
		
		Path mPath = new Path();
		pEyebrow.setColor(Color.BLACK);
		pEyebrow.setStyle(Paint.Style.STROKE);
		pEyebrow.setStrokeWidth(6.0f);
		pLine.setColor(Color.BLACK);
		pLine.setStyle(Paint.Style.STROKE);
		pLine.setStrokeWidth(12.0f);
		
		
		for (EyeOutside eyeball : mEyeOutside) {
			eyeball.draw(canvas);
			PointF mPoint1 = new PointF(eyeball.centerX-eyeball.radius, eyeball.centerY-eyeball.radius-25);
			PointF mPoint2 = new PointF(eyeball.centerX+eyeball.radius, eyeball.centerY-eyeball.radius-25);
			mPath = drawCurveUp(canvas, pEyebrow, mPoint1, mPoint2, 27, eyeball.radius);
			canvas.drawPath(mPath, pEyebrow);
		}
		
		if(OnStyleEyesSelectedListener.shapeEyes == 3){
			for(int i=0; i<mEyeOutside.size()-1; i++){
				EyeOutside eye1 = mEyeOutside.get(i);
				EyeOutside eye2 = mEyeOutside.get(i+1);
				if(OnNumberEyesSelectedListener.numberEyes == 2){
				canvas.drawLine(eye1.centerX-eye1.radius, eye1.centerY, eye2.centerX+eye2.radius, eye2.centerY, pLine);
				}else{
					canvas.drawLine(eye1.centerX+eye1.radius, eye1.centerY, eye2.centerX-eye2.radius, eye2.centerY, pLine);
				}
			}
		}
	}
	private Path drawCurveUp(Canvas canvas, Paint paint, PointF mPointa,
			PointF mPointb, double valueY, int centerCurve) {
		Path myPath = new Path();
		myPath.moveTo(mPointa.x, mPointa.y);
		myPath.quadTo(mPointa.x + centerCurve, (float) (mPointa.y - valueY), mPointb.x,
				mPointb.y);
		return myPath;
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
