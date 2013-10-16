package com.morphoss.xo_speak.listeners;

import com.morphoss.xo_speak.R;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.morphoss.xo_speak.MainActivity;

public class OnNoseStyleSelectedListener implements OnItemSelectedListener {

	private static final String TAG = "OnNoseStyleListener";
	private MainActivity mActivity;
	
	public OnNoseStyleSelectedListener(MainActivity activity) {
		mActivity = activity;
		
	}
	
	
	@Override
	public void onItemSelected(AdapterView parent, View view, int pos, long id) {
		
		
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.nose_choice1))){
			Log.d(TAG, "Nose's style: 1");
			mActivity.nose.setImageResource(R.drawable.nose1);
		}
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.nose_choice2))){
			Log.d(TAG, "Nose's style: 2");
			mActivity.nose.setImageResource(R.drawable.nose2);
		}
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.nose_choice3))){
			Log.d(TAG, "Nose's style: 3");
			mActivity.nose.setImageResource(R.drawable.nose3);
		}
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.nose_choice4))){
			Log.d(TAG, "Nose's style: 4");
			mActivity.nose.setImageResource(R.drawable.nose4);
		}
		if(parent.getItemAtPosition(pos).toString().equals(mActivity.getString(R.string.nose_choice5))){
			Log.d(TAG, "Nose's style: 5");
			mActivity.nose.setImageResource(R.drawable.nose5);
		}
		mActivity.refreshFace();
	}

	@Override
	public void onNothingSelected(AdapterView parent) {

	}
}
