package com.morphoss.xo_speak.listeners;

import com.morphoss.xo_speak.MainActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class OnMouthStyleSelectedListener implements OnItemSelectedListener {

	private static final String TAG = "MouthStyleListener";
	public static int style = 1;
	private MainActivity mActivity;
	
	public OnMouthStyleSelectedListener(MainActivity activity) {
		mActivity = activity;
	}
	
	@Override
	public void onItemSelected(AdapterView parent, View view, int pos, long id) {

		if(parent.getItemAtPosition(pos).toString().contains("Simple")){
			Log.d(TAG, "Simple style mouth");
			style = 1;
		}
		if(parent.getItemAtPosition(pos).toString().contains("Waveform")){
			Log.d(TAG, "Waveform style mouth");
			style = 2;
		}
		if(parent.getItemAtPosition(pos).toString().contains("smile")){
			Log.d(TAG, "Shining smile style mouth");
			style = 3;
		}
		mActivity.refreshFace();
	}

	@Override
	public void onNothingSelected(AdapterView parent) {

	}
}
