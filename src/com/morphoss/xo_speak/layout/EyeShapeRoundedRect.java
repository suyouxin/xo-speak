package com.morphoss.xo_speak.layout;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class EyeShapeRoundedRect extends EyeOutside {
	
	public EyeShapeRoundedRect() {
		
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
	    RectF rect1 = new RectF(this.centerX-this.radius-8, this.centerY-this.radius, this.centerX+this.radius+8, this.centerY+this.radius);
	    RectF rect2 = new RectF(this.centerX-this.radius, this.centerY-this.radius+8, this.centerX+this.radius, this.centerY+this.radius-8);
	    canvas.drawRoundRect(rect1, 15, 15, pOut);
	    canvas.drawRoundRect(rect2, 15, 15, pIn);
	}
}
