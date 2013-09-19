package com.morphoss.xo_speak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
 
public class MoveActivity extends Activity implements AnimationListener {
 
    ImageView imageToMove;
    Button btnStart;
    Button btnStart2;
    Button btnStart3;
 
    // Animation
    Animation animMove;
    Animation animSequential;
    Animation animRotate;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
 
        imageToMove = (ImageView) findViewById(R.id.imgMove);
        btnStart = (Button) findViewById(R.id.button);
        btnStart2 = (Button) findViewById(R.id.button2);
        btnStart3 = (Button) findViewById(R.id.button3);
        // load the animation
        animMove = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        animSequential = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sequential);
        animRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate); 
        // set animation listener
        animMove.setAnimationListener(this);
        animSequential.setAnimationListener(this);
        animRotate.setAnimationListener(this);
        
        // button click event
        btnStart.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View v) {
                imageToMove.setVisibility(View.VISIBLE);
                 
                // start the animation
                imageToMove.startAnimation(animMove);
            }
        });
        btnStart2.setOnClickListener(new View.OnClickListener() {
        	 
            @Override
            public void onClick(View v) {
                imageToMove.setVisibility(View.VISIBLE);
                 
                // start the animation
                imageToMove.startAnimation(animSequential);
            }
        });
        btnStart3.setOnClickListener(new View.OnClickListener() {
       	 
            @Override
            public void onClick(View v) {
                imageToMove.setVisibility(View.VISIBLE);
                 
                // start the animation
                imageToMove.startAnimation(animRotate);
            }
        });
 
    }
 
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	super.onBackPressed();
    	Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
    }
    @Override
    public void onAnimationEnd(Animation animation) {
        // Take any action after completing the animation
 
    }
 
    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub
 
    }
 
    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub
 
    }
 
}
