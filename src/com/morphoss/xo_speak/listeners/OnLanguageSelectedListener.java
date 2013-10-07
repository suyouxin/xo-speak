package com.morphoss.xo_speak.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.morphoss.xo_speak.MainActivity;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class OnLanguageSelectedListener implements OnItemSelectedListener {

	private static final String TAG = "CustomOnItemSelectedListener";
	

	public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
			
			 String language = parent.getItemAtPosition(pos).toString();
			 Locale loc = Locale.getDefault();
			 
			 for(int i=0; i<MainActivity.localeList.size(); i++){
				 if(language.contains(MainActivity.localeList.get(i).getDisplayName())){
					 loc = MainActivity.localeList.get(i);
				 }
				 if(language.equals("Default")) loc = Locale.getDefault();
			 }
			//need to set the language that has been selected
			 MainActivity.getTts().setLanguage(loc);
		  }
		 
		  @Override
		  public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
		  }

}
