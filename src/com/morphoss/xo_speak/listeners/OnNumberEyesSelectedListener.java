package com.morphoss.xo_speak.listeners;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.morphoss.xo_speak.MainActivity;
import com.morphoss.xo_speak.R;

public class OnNumberEyesSelectedListener implements OnItemSelectedListener {

	public static int numberEyes = 2;
	private static final String TAG = "NumberEyesListener";
	private MainActivity mActivity;
	
	public OnNumberEyesSelectedListener(MainActivity activity) {
		mActivity = activity;
	}
	
	
	@Override
	public void onItemSelected(AdapterView parent, View view, int pos, long id) {
		
		
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.eyes_one))){
			Log.d(TAG, "one eye selected");
			numberEyes = 1;
		}
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.eyes_two))){
			Log.d(TAG, "two eyes selected");
			numberEyes = 2;
		}
		if(parent.getItemAtPosition(pos).equals(mActivity.getString(R.string.eyes_three))){
			Log.d(TAG, "three eyes selected");
			numberEyes = 3;
		}
		if(parent.getItemAtPosition(pos).equals(mActivity.getString(R.string.eyes_four))){
			Log.d(TAG, "four eyes selected");
			numberEyes = 4;
		}
		if(parent.getItemAtPosition(pos).equals(mActivity.getString(R.string.eyes_five))){
			Log.d(TAG, "five eyes selected");
			numberEyes = 5;
		}
		mActivity.refreshFace();
	}

	@Override
	public void onNothingSelected(AdapterView parent) {

	}

}
