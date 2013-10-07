package com.morphoss.xo_speak.layout;

import java.util.ArrayList;

public class EyeAlgorithm {
	int w;
	int h;
	int r;
	
	public void calc(ArrayList<EyeOutside> eyes, int canvasWidth, int canvasHeight) {
		w = canvasWidth / 2;
		h = canvasHeight / 4;
		r = w / 6;
		
		for (EyeOutside eye : eyes) {
			eye.centerX = w;
			eye.centerY = h;
			eye.radius = r;
		}
	}
}
