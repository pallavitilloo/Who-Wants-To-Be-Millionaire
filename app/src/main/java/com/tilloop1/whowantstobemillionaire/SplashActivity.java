package com.tilloop1.whowantstobemillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/************************************************************************************
 *
 *  The Splash Screen activity shows the user a Splash screen with an image.
 *  After some time, the user is taken to the main activity screen
 * @author Pallavi Tilloo
 *
 *************************************************************************************/

public class SplashActivity extends AppCompatActivity {

    ImageView splash_image;
    Animation topAnimation, downAnimation;
    Handler handler;
    TextView tvWelcome;
    final int TIME_LIMIT = 8000;     // The time delay after splash screen in milliseconds.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        //  This code is to animate the splash image
        splash_image = findViewById(R.id.logo_id);
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        topAnimation.setFillAfter(true);
        splash_image.startAnimation(topAnimation);


        // postDelayed(Runnable, time) method is used to start the second activity with a delay
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },TIME_LIMIT);
    }
}