package com.morphoss.xo_speak;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {

    private static TextToSpeech tts;
	private EditText txtBox;
    private Spinner spinnerLanguage;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tts = new TextToSpeech(this, this);
        txtBox = (EditText) findViewById(R.id.editText);
        
    	addListenerOnButton();
    	addListenerOnSpinnerItemSelection();
    	
        //permit to remove the focus of the keyboard
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        txtBox.setOnKeyListener(new OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                       //when the user clicks on enter, it starts the method repeatText();
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
        //shutdown the tts when the activity is destroyed
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

	@Override
	public void onInit(int status) {
		
        if (status == TextToSpeech.SUCCESS) {
        	//set English US as default language 
            int result = tts.setLanguage(Locale.US);
 
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
    	spinnerLanguage.setOnItemSelectedListener(new CustomOnItemSelectedListener());
      }
    // get the selected dropdown list value
    public void addListenerOnButton() {
   
  	spinnerLanguage = (Spinner) findViewById(R.id.spinnerLanguage);
   
    }
    
    public static TextToSpeech getTts() {
		return tts;
	}

}
