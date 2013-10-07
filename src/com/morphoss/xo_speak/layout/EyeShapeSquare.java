package com.morphoss.xo_speak.layout;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class EyeShapeSquare extends EyeOutside {
	
	public EyeShapeSquare() {
		
	}

	@Override
	public void draw(Canvas canvas) {
		Paint pIn = new Paint();
		Paint pOut = new Paint();

		pOut.setColor(Color.BLACK);
		pIn.setColor(Color.WHITE);
		pOut.setStyle(Paint.Style.FILL);
		pIn.setStyle(Paint.Style.FILL); 
		canvas.drawRect(this.centerX+this.radius+4, this.centerY-this.radius, this.centerX-this.radius-4, this.centerY+this.radius, pOut);
		canvas.drawRect(this.centerX+this.radius, this.centerY-this.radius+4, this.centerX-this.radius, this.centerY+this.radius-4, pIn);
	}
}
