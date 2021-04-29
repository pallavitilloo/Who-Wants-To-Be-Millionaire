package com.tilloop1.whowantstobemillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**************************************************************************************************
 * Class HomeActivity shows the user options to either Play the game or check the Rules
 * @author Pallavi Tilloo
 * @date 04-29-2021
 *************************************************************************************************/

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
    }

    /**********************************************************************************************
     * Function that is called when PLAY button is pressed
     * @param view: The view that called the button
     **********************************************************************************************/
    public void Play(View view){
        Intent i =  new Intent(this, MainActivity.class);
        startActivity(i);
    }

    /**********************************************************************************************
     * Function that is called when LEVELS button is clicked
     * @param view: The view that called the button
     **********************************************************************************************/
    public void Rules(View view){
        Intent i =  new Intent(this, HelpActivity.class);
        startActivity(i);
    }
}
/**************************************  End of class *******************************************/