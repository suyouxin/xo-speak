package com.morphoss.xo_speak.layout;

import android.graphics.Canvas;

public class EyeOutside {
		public int centerX;
		public int centerY;
		
		public int radius;
		
		public EyeOutside() {
			
		}
		
		public void draw(Canvas canvas) {
			
		}
		
		public EyeInside getInsideEye() {
			EyeInside inside = new EyeInside();
			inside.centerX = centerX;
			inside.centerY = centerY;
			inside.radius = radius;
			return inside;
		}

	}
	
	