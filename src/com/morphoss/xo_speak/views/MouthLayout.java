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
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;

import com.morphoss.xo_speak.listeners.OnMouthStyleSelectedListener;

public class MouthLayout extends View implements SurfaceHolder.Callback {

	private static Paint pIn = new Paint();
	private static Paint pOut = new Paint();
	public static double valueY = 30;
	private int w;
	private int h;
	private static final String TAG = "MouthLayout";

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
		PointF mPoint1 = new PointF(w / 3, 2 * h / 3);
		PointF mPoint2 = new PointF(2 * w / 3, 2 * h / 3);
		Path myPathOutUp = new Path();
		Path myPathOutDown = new Path();
		Path myPathInUp = new Path();
		Path myPathInDown = new Path();
		pOut.setAntiAlias(true);
		pOut.setStyle(Style.STROKE);
		pOut.setStrokeWidth(8.0f);
		pOut.setColor(Color.BLACK);

		pIn.setAntiAlias(true);
		pIn.setStyle(Style.FILL);
		pIn.setColor(Color.LTGRAY);

		Log.d(TAG, "value of mouth style : "
				+ OnMouthStyleSelectedListener.style);
		if (OnMouthStyleSelectedListener.style == 1) {

			myPathOutUp = drawCurveUp(canvas, pOut, mPoint1, mPoint2, valueY,
					w / 6);
			canvas.drawPath(myPathOutUp, pOut);
			myPathOutDown = drawCurveDown(canvas, pOut, mPoint1, mPoint2,
					valueY, w / 6);
			canvas.drawPath(myPathOutDown, pOut);

			myPathInUp = drawCurveUp(canvas, pIn, mPoint1, mPoint2, valueY,
					w / 6);
			canvas.drawPath(myPathInUp, pIn);
			myPathInDown = drawCurveDown(canvas, pIn, mPoint1, mPoint2, valueY,
					w / 6);
			canvas.drawPath(myPathInDown, pIn);
		}
		if (OnMouthStyleSelectedListener.style == 2) {
			PointF mPointa = new PointF(w/3, 2 * h / 3);
			PointF mPointb = new PointF(6*w/15, 2 * h / 3);
			PointF mPointc = new PointF(7*w/15, 2 * h / 3);
			PointF mPointd = new PointF(8*w/15, 2 * h / 3);
			PointF mPointe = new PointF(3*w/5, 2 * h / 3);
			PointF mPointf = new PointF(2*w/3, 2 * h / 3);
			myPathOutUp = drawCurveUp(canvas, pOut, mPointa, mPointb, valueY,
					w / 28);
			canvas.drawPath(myPathOutUp, pOut);
			myPathOutDown = drawCurveDown(canvas, pOut, mPointb, mPointc,
					valueY, w / 28);
			canvas.drawPath(myPathOutDown, pOut);
			myPathOutUp = drawCurveUp(canvas, pOut, mPointc, mPointd, valueY,
					w / 28);
			canvas.drawPath(myPathOutUp, pOut);
			myPathOutDown = drawCurveDown(canvas, pOut, mPointd, mPointe,
					valueY, w / 28);
			canvas.drawPath(myPathOutDown, pOut);
			myPathOutUp = drawCurveUp(canvas, pOut, mPointe, mPointf, valueY,
					w / 28);
			canvas.drawPath(myPathOutUp, pOut);


		}
	}

	private Path drawCurveUp(Canvas canvas, Paint paint, PointF mPointa,
			PointF mPointb, double valueY, int centerCurve) {
		w = this.getMeasuredWidth();
		h = this.getMeasuredHeight();
		Path myPath = new Path();
		myPath.moveTo(mPointa.x, mPointa.y);
		myPath.quadTo(mPointa.x + centerCurve, (float) (mPointa.y + valueY), mPointb.x,
				mPointb.y);
		return myPath;
	}

	private Path drawCurveDown(Canvas canvas, Paint paint, PointF mPointa,
			PointF mPointb, double valueY, int centerCurve) {
		w = this.getMeasuredWidth();
		h = this.getMeasuredHeight();
		Path myPath = new Path();
		myPath.moveTo(mPointa.x, mPointa.y);
		myPath.quadTo(mPointa.x + centerCurve, (float) (mPointa.y - valueY), mPointb.x,
				mPointb.y);
		return myPath;
	}

	public void mouthSpeaking(int i) {
		w = this.getMeasuredWidth();
		h = this.getMeasuredHeight();
		if (i % 2 == 0)
			valueY = 80;
		else
			valueY = 30;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		setWillNotDraw(false); // Allows us to use invalidate() to call onDraw()
		postInvalidate();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}

}
