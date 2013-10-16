package com.morphoss.xo_speak.listeners;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.morphoss.xo_speak.MainActivity;
import com.morphoss.xo_speak.R;

public class OnMouthStyleSelectedListener implements OnItemSelectedListener {

	private static final String TAG = "MouthStyleListener";
	public static int style = 1;
	private MainActivity mActivity;
	
	public OnMouthStyleSelectedListener(MainActivity activity) {
		mActivity = activity;
	}
	
	@Override
	public void onItemSelected(AdapterView parent, View view, int pos, long id) {
		
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.simple_mouth))){
			Log.d(TAG, "Simple style mouth");
			style = 1;
		}
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.waveform))){
			Log.d(TAG, "Waveform style mouth");
			style = 2;
		}
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.mouth_teeth))){
			Log.d(TAG, "With teeth style mouth");
			style = 3;
		}
		mActivity.refreshFace();
	}

	@Override
	public void onNothingSelected(AdapterView parent) {

	}
}
