package com.morphoss.xo_speak.coordinates;

import java.util.ArrayList;

import com.morphoss.xo_speak.layout.EyeOutside;

public 	class EyeCoordinatesForFive extends EyeInitCoordinates {
	@Override
	public void calc(ArrayList<EyeOutside> eyes, int canvasWidth, int canvasHeight) {
		super.calc(eyes, canvasWidth, canvasHeight);
		
		eyes.get(0).centerX = w-3*h;
		eyes.get(0).radius /= 1.2;
		eyes.get(1).centerX = (int) (w-1.5*h);
		eyes.get(1).radius /= 1.2;
		eyes.get(2).centerX = w;
		eyes.get(2).radius /= 1.2;
		eyes.get(3).centerX = (int) (w+1.5*h);
		eyes.get(3).radius /= 1.2;
		eyes.get(4).centerX = w+3*h;
		eyes.get(4).radius /= 1.2;
	}
}

