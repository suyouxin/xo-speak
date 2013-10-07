package com.morphoss.xo_speak.layout;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class EyeInside extends EyeOutside {
	public int ballX;
	public int ballY;
	
	public int ballRadius;
	
	public EyeInside() {
		
	}

	@Override
	public void draw(Canvas canvas) {
		Paint p = new Paint();

		p.setColor(Color.BLACK);
		p.setStyle(Paint.Style.FILL);
		canvas.drawCircle(this.ballX, this.ballY, this.ballRadius, p);
	}
}
