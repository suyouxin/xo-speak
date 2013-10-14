package com.morphoss.xo_speak.listeners;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.morphoss.xo_speak.MainActivity;
import com.morphoss.xo_speak.views.eyeInView;
import com.morphoss.xo_speak.views.eyeOutView;

public class OnStyleEyesSelectedListener implements OnItemSelectedListener {

	public static int shapeEyes = 1;
	private static final String TAG = "StyleEyesListener";
	private MainActivity mActivity;
	
	public OnStyleEyesSelectedListener(MainActivity activity) {
		mActivity = activity;
	}
	
	@Override
	public void onItemSelected(AdapterView parent, View view, int pos, long id) {
		
		
		if(parent.getItemAtPosition(pos).toString().contains("Round")){
			shapeEyes = 1;
		}
		if(parent.getItemAtPosition(pos).toString().contains("Square")){
			shapeEyes = 2;
		}
		if(parent.getItemAtPosition(pos).toString().contains("Glasses")){
			shapeEyes = 3;
		}
		mActivity.refreshFace();
	}

	@Override
	public void onNothingSelected(AdapterView parent) {
	}
}
