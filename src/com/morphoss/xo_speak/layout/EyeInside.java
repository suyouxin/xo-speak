package com.morphoss.xo_speak.layout;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class EyeInside extends EyeOutside {
	
	public EyeInside() {
		
	}

	@Override
	public void draw(Canvas canvas) {
		Paint p = new Paint();

		p.setColor(Color.BLACK);
		p.setStyle(Paint.Style.FILL);
		canvas.drawCircle(this.centerX, this.centerY, this.radius, p);
	}
}
