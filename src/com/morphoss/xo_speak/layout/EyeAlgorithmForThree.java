package com.morphoss.xo_speak.layout;

import java.util.ArrayList;

public 	class EyeAlgorithmForThree extends EyeAlgorithm {
	@Override
	public void calc(ArrayList<EyeOutside> eyes, int canvasWidth, int canvasHeight) {
		super.calc(eyes, canvasWidth, canvasHeight);
		
		eyes.get(0).centerX = w-2*h;
		eyes.get(1).centerX = w;
		eyes.get(2).centerX = w+2*h;
	}
}
