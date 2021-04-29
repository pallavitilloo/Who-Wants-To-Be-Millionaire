package com.tilloop1.whowantstobemillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

/*************************************************************************************************
 * Class WinActivity shows activity when the user wins the game or quits with a prize money
 * @author Pallavi Tilloo
 * @date 04-29-2021
 ************************************************************************************************/
public class WinActivity extends AppCompatActivity {

    // Class members
    ImageView center_image;
    Animation expand, slide;
    TextView tvAmt, tvCongrats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        getSupportActionBar().hide();

        // Get the TextView view for the amount to be shown on the screen
        tvAmt = (TextView) findViewById(R.id.tvAmt);

        // The calling activity passes the amount in the Bundle
        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            // Bundle contains something!
            String msg = bundle.getString("prize_amt");
            msg = "You won $" + msg;
            // Update message on the TextView
            tvAmt.setText(msg);
        }
        else
        {
            // If the Bundle contains nothing for some reason, go to HomeActivity
            startActivity(new Intent(this, HomeActivity.class));
        }

        //  This code is to animate the image and the message
        // Get the relevant views from the layout which need to be animated
        center_image = findViewById(R.id.logo_id);
        tvCongrats = (TextView) findViewById(R.id.tvCongrats);

        // Create animation and apply
        Animation animation = new AlphaAnimation(1, 0); //to change visibility from visible to invisible
        animation.setDuration(500); //1 second duration for each animation cycle
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE); //repeating indefinitely
        animation.setRepeatMode(Animation.REVERSE); //animation will start from end point once ended.
        center_image.startAnimation(animation); //to start animation
        tvCongrats.startAnimation(animation);
    }
}
/**************************************  End of class *******************************************/