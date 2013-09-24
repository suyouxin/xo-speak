package com.morphoss.xo_speak;

import java.util.ArrayList;
import java.util.Locale;

import com.morphoss.xo_speak.views.eyeInLayout;
import com.morphoss.xo_speak.views.eyeOutLayout;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements
		TextToSpeech.OnInitListener {

	private static TextToSpeech tts;
	private EditText txtBox;
	private Spinner spinnerLanguage;
	private ArrayList<String> localeNames = new ArrayList<String>();
	public static ArrayList<Locale> localeList = new ArrayList<Locale>();
	private static final String TAG = "MainActivity";
	private View eyesIn;
	private float x =0;
	private float y =0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		localeList.clear();
		localeNames.clear();
		tts = new TextToSpeech(this, this);
		txtBox = (EditText) findViewById(R.id.editText);

		eyesIn = findViewById(R.id.eyeIn);
		addListenerOnSpinnerItemSelection();
		// permit to remove the focus of the keyboard
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		txtBox.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					switch (keyCode) {
					// when the user clicks on enter, it starts the method
					// repeatText();
					case KeyEvent.KEYCODE_DPAD_CENTER:
					case KeyEvent.KEYCODE_ENTER:
						repeatText();
						return true;
					default:
						break;
					}
				}
				return false;
			}
		});
	}

	@Override
	public void onDestroy() {
		// shutdown the tts when the activity is destroyed
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();
	}

	
	protected void moveCircle(int x, int y){
		AnimationSet as = new AnimationSet(true);
		as.setFillAfter(true);
		int duration = 2000;
		TranslateAnimation ta = new TranslateAnimation(0, x, 0, y);
		ta.setDuration(duration);
		as.addAnimation(ta);
		eyesIn.startAnimation(as);
	}
	@Override
	public void onInit(int status) {
		
		if (status == TextToSpeech.SUCCESS) {
			// set language of the tablet as default language
			int result = tts.setLanguage(Locale.getDefault());
			Locale[] locales = Locale.getAvailableLocales();
			for (Locale locale : locales) {
				// find the languages that are available for the tts
				if (tts.isLanguageAvailable(locale) == TextToSpeech.LANG_AVAILABLE) {
					localeList.add(locale);
				}
			}

			String defaultLanguage = getResources().getString(R.string.default_language);
			localeNames.add(defaultLanguage );
			for(int i=0; i<localeList.size(); i++){
				if(localeList.get(i).getLanguage().equals(localeList.get(i).toString())){
					localeNames.add(localeList.get(i).getDisplayLanguage());
				}
			}
			spinnerLanguage = new Spinner(this);
		    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
		            this, android.R.layout.simple_spinner_item, localeNames);
		    spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

		    spinnerLanguage = (Spinner) findViewById( R.id.spinnerLanguage);
		    spinnerLanguage.setAdapter(spinnerArrayAdapter);

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "This Language is not supported");
			} else {
				txtBox.setEnabled(true);
				repeatText();
			}

		} else {
			Log.e("TTS", "Initilization Failed!");
		}
	}

    
	private void repeatText() {

		String text = txtBox.getText().toString();
		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}

	public void addListenerOnSpinnerItemSelection() {
		spinnerLanguage = (Spinner) findViewById(R.id.spinnerLanguage);
		spinnerLanguage
				.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	}

	public static TextToSpeech getTts() {
		return tts;
	}

	
  

}
