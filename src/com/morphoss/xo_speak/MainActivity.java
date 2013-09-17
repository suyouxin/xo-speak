package com.morphoss.xo_speak;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private Button btnSpeak;
    private EditText txtBox;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tts = new TextToSpeech(this, this);
        btnSpeak = (Button) findViewById(R.id.speakOutButton);
        txtBox = (EditText) findViewById(R.id.editText);
 
        btnSpeak.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View v) {
                repeatText();
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
                btnSpeak.setEnabled(true);
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

}
