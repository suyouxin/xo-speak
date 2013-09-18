package com.morphoss.xo_speak;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class CustomOnItemSelectedListener implements OnItemSelectedListener {

	private static final String TAG = "CustomOnItemSelectedListener";
	
	  public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {

			 String language = parent.getItemAtPosition(pos).toString();
			 //need to set the language that has been selected
		  }
		 
		  @Override
		  public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
		  }

}
