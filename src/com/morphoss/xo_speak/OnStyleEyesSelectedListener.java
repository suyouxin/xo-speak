package com.morphoss.xo_speak;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.morphoss.xo_speak.views.eyeInLayout;
import com.morphoss.xo_speak.views.eyeOutLayout;

public class OnStyleEyesSelectedListener extends Activity implements OnItemSelectedListener {

	public static int numberEyes = 2;
	private static final String TAG = "StyleEyesListener";
	private eyeInLayout mEyesIn;
	private eyeOutLayout mEyesOut;
	
	public OnStyleEyesSelectedListener(eyeOutLayout eyeOut, eyeInLayout eyeIn) {
		mEyesOut = eyeOut;
		mEyesIn = eyeIn;
	}
	
	@Override
	public void onItemSelected(AdapterView parent, View view, int pos, long id) {
		
		
		if(parent.getItemAtPosition(pos).toString().contains("Round")){
			eyeOutLayout.shapeEyes = 1;
		}
		if(parent.getItemAtPosition(pos).toString().contains("Square")){
			eyeOutLayout.shapeEyes = 2;
		}
		if(parent.getItemAtPosition(pos).toString().contains("Glasses")){
			eyeOutLayout.shapeEyes = 3;
		}
		mEyesOut.invalidate(); 
		mEyesIn.invalidate();
	}

	@Override
	public void onNothingSelected(AdapterView parent) {
	}
}
