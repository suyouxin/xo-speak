package com.morphoss.xo_speak.coordinates;

import java.util.ArrayList;

import com.morphoss.xo_speak.layout.EyeOutside;

public 	class EyeCoordinatesForFour extends EyeInitCoordinates {
	@Override
	public void calc(ArrayList<EyeOutside> eyes, int canvasWidth, int canvasHeight) {
		super.calc(eyes, canvasWidth, canvasHeight);		
		eyes.get(0).centerX = (int) (w - 2.5*h);
		eyes.get(1).centerX = w - h;
		eyes.get(2).centerX = w + h;
		eyes.get(3).centerX = (int) (w + 2.5*h);
	}
}
