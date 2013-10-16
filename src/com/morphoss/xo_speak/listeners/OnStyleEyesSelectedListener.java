package com.morphoss.xo_speak.listeners;

import com.morphoss.xo_speak.R;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.morphoss.xo_speak.MainActivity;

public class OnStyleEyesSelectedListener implements OnItemSelectedListener {

	public static int shapeEyes = 1;
	private static final String TAG = "StyleEyesListener";
	private MainActivity mActivity;
	
	public OnStyleEyesSelectedListener(MainActivity activity) {
		mActivity = activity;
	}
	
	@Override
	public void onItemSelected(AdapterView parent, View view, int pos, long id) {
		
		
		if(parent.getItemAtPosition(pos).equals(mActivity.getString(R.string.eyes_round))){
			shapeEyes = 1;
		}
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.eyes_square))){
			shapeEyes = 2;
		}
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.eyes_glasses))){
			shapeEyes = 3;
		}
		mActivity.refreshFace();
	}

	@Override
	public void onNothingSelected(AdapterView parent) {
	}
}
