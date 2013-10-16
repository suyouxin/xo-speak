package com.morphoss.xo_speak.layout;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class EyeShapeCircle extends EyeOutside {
	
	public EyeShapeCircle() {
		
	}

	@Override
	public void draw(Canvas canvas) {
		Paint pIn = new Paint();
		Paint pOut = new Paint();
		pIn.setAntiAlias(true);
		pOut.setAntiAlias(true);
		pOut.setColor(Color.BLACK);
		pIn.setColor(Color.WHITE);
		pOut.setStyle(Paint.Style.FILL);
		pIn.setStyle(Paint.Style.FILL);
		
		canvas.drawCircle(this.centerX, this.centerY, this.radius+6, pOut);
		canvas.drawCircle(this.centerX, this.centerY, this.radius, pIn);
	}
	
}