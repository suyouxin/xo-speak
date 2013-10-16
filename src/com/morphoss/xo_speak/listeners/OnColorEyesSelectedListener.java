package com.morphoss.xo_speak.listeners;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.morphoss.xo_speak.MainActivity;
import com.morphoss.xo_speak.R;

public class OnColorEyesSelectedListener implements OnItemSelectedListener {

	public static int colorEyes = 1;
	private static final String TAG = "OnColorEyesListener";
	private MainActivity mActivity;
	
	public OnColorEyesSelectedListener(MainActivity activity) {
		mActivity = activity;
	}
	
	
	@Override
	public void onItemSelected(AdapterView parent, View view, int pos, long id) {
		
		
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.eyes_black))){
			Log.d(TAG, "black color");
			colorEyes = 1;
		}
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.eyes_blue))){
			Log.d(TAG, "blue color");
			colorEyes = 2;
		}
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.eyes_green))){
			Log.d(TAG, "green color");
			colorEyes = 3;
		}
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.eyes_brown))){
			Log.d(TAG, "brown color");
			colorEyes = 4;
		}
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.eyes_gray))){
			Log.d(TAG, "gray color");
			colorEyes = 5;
		}
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.eyes_red))){
			Log.d(TAG, "red color");
			colorEyes = 6;
		}
		mActivity.refreshFace();
	}

	@Override
	public void onNothingSelected(AdapterView parent) {

	}
}
