package com.morphoss.xo_speak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
 
public class MoveActivity extends Activity implements AnimationListener{
 
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
        
        AnimationSet as1 = new AnimationSet(true);
        as1.setFillAfter(true);

        float dist = -1.5f;
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
        float rotation = -200;
        RotateAnimation ra1 = new RotateAnimation(-300, rotation,rtype,0.5f , rtype,0.5f );
        ra1.setDuration(duration);
        as1.addAnimation(ra1);

        imageToMove.startAnimation(as1); // in my app Object1 is an ImageButton
        
        
        
        
        
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
