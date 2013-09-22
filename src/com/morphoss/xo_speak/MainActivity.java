package com.morphoss.xo_speak;

import java.util.ArrayList;
import java.util.Locale;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
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
	private ImageView eyeLeft;
	private ImageView eyeRight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		localeList.clear();
		localeNames.clear();
		tts = new TextToSpeech(this, this);
		txtBox = (EditText) findViewById(R.id.editText);

		eyeLeft = (ImageView) findViewById(R.id.eyeLeftInside);
		eyeRight = (ImageView) findViewById(R.id.eyeRightInside);
		AnimationSet as1 = new AnimationSet(true);
        as1.setFillAfter(true);
        float dist = 1.0f;
        // The following is too slow just to inspect the animation
        int duration = 3000; // 5 seconds
        // Tried the following: RELATIVE_TO_SELF and RELATIVE_TO_PARENT but no difference
        int ttype = Animation.RELATIVE_TO_SELF; // Type of translation
        // Move to X: distance , Y: distance
        TranslateAnimation ta1 = new TranslateAnimation( ttype,0,ttype,dist,ttype,0, ttype,0); 
        ta1.setDuration(duration);
        // Add Translation to the set
        as1.addAnimation(ta1);

        // Rotate around its center
       int rtype = Animation.RELATIVE_TO_SELF;
        float rotation = 200;
        RotateAnimation ra1 = new RotateAnimation(300, rotation,rtype,0.5f , rtype,0.5f );
        ra1.setDuration(duration);
        as1.addAnimation(ra1);

        eyeLeft.startAnimation(as1); 
        eyeRight.startAnimation(as1);
        
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

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // MotionEvent object holds X-Y values

            Log.d(TAG, "");
 
        return super.onKeyUp(keyCode, event);
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
	
	public void StartMove(View view) {
		Intent intent = new Intent(this, MoveActivity.class);
		startActivity(intent);
		finish();
	}          

}
