package com.morphoss.xo_speak;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.morphoss.xo_speak.views.eyeInLayout;
import com.morphoss.xo_speak.views.eyeOutLayout;

public class OnNumberEyesSelectedListener implements OnItemSelectedListener {

	public static int numberEyes = 2;
	private static final String TAG = "NumberEyesListener";
	private MainActivity mActivity;
	
	public OnNumberEyesSelectedListener(MainActivity activity) {
		mActivity = activity;
	}
	
	
	@Override
	public void onItemSelected(AdapterView parent, View view, int pos, long id) {
		
		
		if(parent.getItemAtPosition(pos).toString().contains("One")){
			Log.d(TAG, "one eye selected");
			numberEyes = 1;
		}
		if(parent.getItemAtPosition(pos).toString().contains("Two")){
			Log.d(TAG, "two eyes selected");
			numberEyes = 2;
		}
		if(parent.getItemAtPosition(pos).toString().contains("Three")){
			Log.d(TAG, "three eyes selected");
			numberEyes = 3;
		}
		if(parent.getItemAtPosition(pos).toString().contains("Four")){
			Log.d(TAG, "four eyes selected");
			numberEyes = 4;
		}
		if(parent.getItemAtPosition(pos).toString().contains("Five")){
			Log.d(TAG, "five eyes selected");
			numberEyes = 5;
		}
		mActivity.refreshFace();
	}

	@Override
	public void onNothingSelected(AdapterView parent) {

	}

}
