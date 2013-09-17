package com.morphoss.xo_speak;

import java.util.Locale;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class CustomOnItemSelectedListener implements OnItemSelectedListener {

	  public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
			 String language = parent.getItemAtPosition(pos).toString();
			 if(language.contains("Chi")){
				 MainActivity.getTts().setLanguage(Locale.CHINESE);
			 }
			 if(language.contains("Deu")){
				 MainActivity.getTts().setLanguage(Locale.GERMAN);
			 }
			 if(language.contains("Can")){
				 MainActivity.getTts().setLanguage(Locale.CANADA);
			 }
			 if(language.contains("Kin")){
				 MainActivity.getTts().setLanguage(Locale.UK);
			 }
			 if(language.contains("Sta")){
				 MainActivity.getTts().setLanguage(Locale.US);
			 }
			 if(language.contains("Fra")){
				 MainActivity.getTts().setLanguage(Locale.FRENCH);
			 }
			 if(language.contains("Ita")){
				 MainActivity.getTts().setLanguage(Locale.ITALIAN);
			 }
			 if(language.contains("Jap")){
				 MainActivity.getTts().setLanguage(Locale.JAPANESE);
			 }
			 
		  }
		 
		  @Override
		  public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
		  }

}
