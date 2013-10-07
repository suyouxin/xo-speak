package com.morphoss.xo_speak;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class EyeBallCircle extends EyeBall {
	
	public EyeBallCircle() {
		
	}

	@Override
	public void draw(Canvas canvas) {
		Paint pIn = new Paint();
		Paint pOut = new Paint();
		pOut.setColor(Color.BLACK);
		pIn.setColor(Color.WHITE);
		pOut.setStyle(Paint.Style.FILL);
		pIn.setStyle(Paint.Style.FILL);
		
		canvas.drawCircle(this.centerX, this.centerY, this.radius+4, pOut);
		canvas.drawCircle(this.centerX, this.centerY, this.radius, pIn);
	}
	
}