package com.morphoss.xo_speak.listeners;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.morphoss.xo_speak.MainActivity;

public class OnColorEyesSelectedListener implements OnItemSelectedListener {

	public static int colorEyes = 1;
	private static final String TAG = "OnColorEyesListener";
	private MainActivity mActivity;
	
	public OnColorEyesSelectedListener(MainActivity activity) {
		mActivity = activity;
	}
	
	
	@Override
	public void onItemSelected(AdapterView parent, View view, int pos, long id) {
		
		
		if(parent.getItemAtPosition(pos).toString().contains("Black")){
			Log.d(TAG, "one eye selected");
			colorEyes = 1;
		}
		if(parent.getItemAtPosition(pos).toString().contains("Blue")){
			Log.d(TAG, "two eyes selected");
			colorEyes = 2;
		}
		if(parent.getItemAtPosition(pos).toString().contains("Green")){
			Log.d(TAG, "three eyes selected");
			colorEyes = 3;
		}
		if(parent.getItemAtPosition(pos).toString().contains("Brown")){
			Log.d(TAG, "four eyes selected");
			colorEyes = 4;
		}
		if(parent.getItemAtPosition(pos).toString().contains("Gray")){
			Log.d(TAG, "five eyes selected");
			colorEyes = 5;
		}
		if(parent.getItemAtPosition(pos).toString().contains("Red")){
			Log.d(TAG, "five eyes selected");
			colorEyes = 6;
		}
		mActivity.refreshFace();
	}

	@Override
	public void onNothingSelected(AdapterView parent) {

	}
}
