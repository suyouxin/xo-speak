package com.morphoss.xo_speak;

import java.util.ArrayList;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.morphoss.xo_speak.layout.EyeOutside;
import com.morphoss.xo_speak.listeners.OnLanguageSelectedListener;
import com.morphoss.xo_speak.listeners.OnMouthStyleSelectedListener;
import com.morphoss.xo_speak.listeners.OnNumberEyesSelectedListener;
import com.morphoss.xo_speak.listeners.OnStyleEyesSelectedListener;
import com.morphoss.xo_speak.views.MouthLayout;
import com.morphoss.xo_speak.views.eyeInLayout;
import com.morphoss.xo_speak.views.eyeOutLayout;

public class MainActivity extends Activity implements
		TextToSpeech.OnInitListener, TextWatcher {

	private static TextToSpeech tts;
	private AutoCompleteTextView txtBox;
	private Spinner spinnerLanguage, spinnerNumberEyes, spinnerMouthStyle, spinnerStyleEyes;
	private ArrayList<String> localeNames = new ArrayList<String>();
	public static ArrayList<Locale> localeList = new ArrayList<Locale>();
	private static final String TAG = "MainActivity";
	private eyeOutLayout eyesOut;
	private eyeInLayout eyesIn;
	private MouthLayout mouth;
	private SeekBar pitchSlider, speedSlider;
	private TextView mSetPitch, mSetSpeed,textSavedMem1;
	private Handler h = new Handler();
	private ArrayList<String> item;

	@SuppressLint("ServiceCast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		localeList.clear();
		localeNames.clear();

		tts = new TextToSpeech(this, this);
		txtBox = (AutoCompleteTextView) findViewById(R.id.autocompleteText);
		
		pitchSlider = (SeekBar) findViewById(R.id.pitchSlider);
		mSetPitch = (TextView) findViewById(R.id.set_pitch);
		mSetSpeed = (TextView) findViewById(R.id.set_speed);
		speedSlider = (SeekBar) findViewById(R.id.speedSlider);
		eyesIn = (eyeInLayout) findViewById(R.id.eyeIn);
		eyesOut = (eyeOutLayout) findViewById(R.id.eyeOut);
		mouth = (MouthLayout) findViewById(R.id.mouth);
		textSavedMem1 = (TextView)findViewById(R.id.savedmem1);
		pitchSlider.setOnSeekBarChangeListener(new pitchListener());
		speedSlider.setOnSeekBarChangeListener(new speedListener());

		addListenerOnSpinnerLanguage();
		addListenerOnSpinnerEyes();
		addListenerOnSpinnerMouthStyle();
		addListenerOnSpinnerStyleEyes();
		// permit to remove the focus of the keyboard
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		txtBox.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				Log.d(TAG, "Key pressed on " + v.getClass().toString());
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					switch (keyCode) {
					case KeyEvent.KEYCODE_DPAD_CENTER:
					case KeyEvent.KEYCODE_ENTER:
						SavePreferences("MEM1", txtBox.getText().toString());
						LoadPreferences();
						InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(txtBox.getWindowToken(), 0);
						h.postDelayed(new Runnable() {
							public void run() {
								repeatText();
							}
						}, 1000);

						return true;
					default:
						break;
					}
				}
				return false;
			}
		});
		LoadPreferences();
	}
	   private void SavePreferences(String key, String value){
		    SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
		    SharedPreferences.Editor editor = sharedPreferences.edit();
		    editor.putString(key, value);
		    editor.commit();
		   }
		  
		   private void LoadPreferences(){
		    SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
		    String strSavedMem1 = sharedPreferences.getString("MEM1", "");
		    if(item!=null){
		    item.add(strSavedMem1);
		    txtBox.addTextChangedListener(this);
		    txtBox.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,item));
		    }
		    textSavedMem1.setText(strSavedMem1);
		   }
	private class pitchListener implements SeekBar.OnSeekBarChangeListener {

		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			double max = 20;
			// Log the progress
			Log.d("DEBUG", "Progress is: " + 100 * progress / max);
			// set textView's text
			mSetPitch.setText("" + 100 * progress / max + "%");
		}

		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		public void onStopTrackingTouch(SeekBar seekBar) {
			mSetPitch.setText(R.string.setpitch);
			double pitch = (pitchSlider.getProgress() + 1);
			pitch = pitch / 10;
			tts.setPitch((float) pitch);
		}

	}

	private class speedListener implements SeekBar.OnSeekBarChangeListener {

		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {

			double max = 50;
			// Log the progress
			Log.d("DEBUG", "Progress is: " + 100 * progress / max);
			// set textView's text
			mSetSpeed.setText("" + 100 * progress / max + "%");
		}

		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		public void onStopTrackingTouch(SeekBar seekBar) {
			mSetSpeed.setText(R.string.setspeed);
			double speed = (speedSlider.getProgress() + 1);
			speed = speed / 10;
			tts.setSpeechRate((float) speed);
		}

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

			String defaultLanguage = getResources().getString(
					R.string.default_language);
			localeNames.add(defaultLanguage);
			for (int i = 0; i < localeList.size(); i++) {
				if (localeList.get(i).getLanguage()
						.equals(localeList.get(i).toString())) {
					localeNames.add(localeList.get(i).getDisplayLanguage());
				}
			}
			spinnerLanguage = new Spinner(this);
			ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
					this, android.R.layout.simple_spinner_item, localeNames);
			spinnerArrayAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			spinnerLanguage = (Spinner) findViewById(R.id.spinnerLanguage);
			spinnerLanguage.setAdapter(spinnerArrayAdapter);

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "This Language is not supported");
			} else {
				txtBox.setEnabled(true);
				repeatText();
			}

			refreshFace();
		} else {
			Log.e("TTS", "Initilization Failed!");
		}
	}

	public void refreshFace() {
		int numberEyes = OnNumberEyesSelectedListener.numberEyes;
		int shapeEyes = OnStyleEyesSelectedListener.shapeEyes;
		ArrayList<EyeOutside> listEyeOut = eyesOut.createEyes(numberEyes, shapeEyes);
		eyesIn.setEyes(listEyeOut);
		eyesOut.invalidate(); 
		eyesIn.invalidate();
		mouth.invalidate();
	}
	
	private void repeatText() {

		String text = txtBox.getText().toString();
		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
		Thread thread = new Thread(new CheckTTSStillGoing());
		thread.start();
	}

	class CheckTTSStillGoing implements Runnable {

		@Override
		public void run() {

			double i = 0;
			double j = 0;

			while (true) {
				if (!tts.isSpeaking())
					break;
				eyesIn.moveEye(i * 0.01);
				mouth.mouthSpeaking(j * 0.001);
				h.post(new Runnable() {

					@Override
					public void run() {
						eyesIn.invalidate();
						mouth.invalidate();
					}
				});
				i = i + 0.5;
				j = j + 0.005;
				if (i > 3000)
					i = 0;
				if (j > 3000)
					j = 0;
			}
			Log.e("TTS", "speak finished");
		}

	}
	
	public void addListenerOnSpinnerLanguage() {
		spinnerLanguage = (Spinner) findViewById(R.id.spinnerLanguage);
		spinnerLanguage
				.setOnItemSelectedListener(new OnLanguageSelectedListener());
	}
	public void addListenerOnSpinnerEyes() {
		spinnerNumberEyes = (Spinner) findViewById(R.id.spinnerNumberEyes);
		spinnerNumberEyes
				.setOnItemSelectedListener(new OnNumberEyesSelectedListener(this));
	}

	public void addListenerOnSpinnerMouthStyle() {
		spinnerMouthStyle = (Spinner) findViewById(R.id.spinnerMouthStyle);
		spinnerMouthStyle
				.setOnItemSelectedListener(new OnMouthStyleSelectedListener(this));
	}
	public void addListenerOnSpinnerStyleEyes() {
		spinnerStyleEyes = (Spinner) findViewById(R.id.spinnerStyleEyes);
		spinnerStyleEyes
				.setOnItemSelectedListener(new OnStyleEyesSelectedListener(this));
	}
	public static TextToSpeech getTts() {
		return tts;
	}
	@Override
	public void afterTextChanged(Editable s) {
		
	}
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		
	}
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		
	}

}
