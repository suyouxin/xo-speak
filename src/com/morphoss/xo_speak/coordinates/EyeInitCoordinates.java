package com.morphoss.xo_speak.coordinates;

import java.util.ArrayList;

import com.morphoss.xo_speak.layout.EyeOutside;

public class EyeInitCoordinates {
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
