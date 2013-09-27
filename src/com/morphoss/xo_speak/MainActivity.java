package com.morphoss.xo_speak;

import java.util.ArrayList;
import java.util.Locale;

import bitoflife.chatterbean.ChatterBean;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements
		TextToSpeech.OnInitListener {

	private static TextToSpeech tts;
	private EditText txtBox;
	private Spinner spinnerLanguage;
	private ArrayList<String> localeNames = new ArrayList<String>();
	public static ArrayList<Locale> localeList = new ArrayList<Locale>();
	private static final String TAG = "MainActivity";

	private ChatterBean mCb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		localeList.clear();
		localeNames.clear();
		tts = new TextToSpeech(this, this);
		txtBox = (EditText) findViewById(R.id.editText);

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
						// repeatText();
					    String text = txtBox.getText().toString();
					    repeatText(robotTest(text));
						return true;

					default:
						break;
					}
				}
				return false;
			}
		});
		
		robotInit();
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

	@Override
	public void onInit(int status) {
		
		if (status == TextToSpeech.SUCCESS) {
			// set language of the tablet as default language
			int result = tts.setLanguage(Locale.getDefault());
			Locale[] locales = Locale.getAvailableLocales();
			for (Locale locale : locales) {
				Log.d(TAG,
						"language available for Locale : "
								+ locale.getDisplayName());

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
				String text = txtBox.getText().toString();
				repeatText(text);
			}

		} else {
			Log.e("TTS", "Initilization Failed!");
		}

	}

	private void repeatText(String words) {

		// String text = txtBox.getText().toString();
	    Log.d("Test", words);
		tts.speak(words, TextToSpeech.QUEUE_FLUSH, null);
	}

	public void addListenerOnSpinnerItemSelection() {
		spinnerLanguage = (Spinner) findViewById(R.id.spinnerLanguage);
		spinnerLanguage
				.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	}

	public static TextToSpeech getTts() {
		return tts;
	}
	
	public void StartMove(View view) {
		Intent intent = new Intent(this, MoveActivity.class);
		startActivity(intent);
		finish();
	}          
	
	public void robotInit() {
	    mCb = new ChatterBean(getResources().getAssets());
        mCb.init();
	}

	public String robotTest(String question) {
	    /*
        Log.d("Test", mCb.respond("How are you"));
        Log.d("Test", mCb.respond("who are you"));
        Log.d("Test", mCb.respond("what is the weather"));
        Log.d("Test", mCb.respond("au canada"));
        Log.d("Test", mCb.respond("a robot"));*/
        return mCb.respond(question);
	}
}
