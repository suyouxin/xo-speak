package com.morphoss.xo_speak.layout;

import com.morphoss.xo_speak.listeners.OnColorEyesSelectedListener;

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
		Paint pIn = new Paint();

		if(OnColorEyesSelectedListener.colorEyes == 1){
		p.setColor(Color.BLACK);
		}
		if(OnColorEyesSelectedListener.colorEyes == 2){
			//blue
			p.setColor(Color.rgb(0, 51, 104));
			}
		if(OnColorEyesSelectedListener.colorEyes == 3){
			//green
			p.setColor(Color.rgb(42, 69, 28));
			}
		if(OnColorEyesSelectedListener.colorEyes == 4){
			//brown
			p.setColor(Color.rgb(66, 42, 16));
			}
		if(OnColorEyesSelectedListener.colorEyes == 5){
			//gray
			p.setColor(Color.rgb(48, 49, 43));
			}
		if(OnColorEyesSelectedListener.colorEyes == 6){
			//red
			p.setColor(Color.rgb(148, 0, 14));
			}
		pIn.setColor(Color.WHITE);
		p.setStyle(Paint.Style.FILL);
		pIn.setStyle(Paint.Style.FILL);
		canvas.drawCircle(this.ballX, this.ballY, this.ballRadius, p);
		canvas.drawCircle(this.ballX, this.ballY+2, this.ballRadius/4, pIn);
	}
}
