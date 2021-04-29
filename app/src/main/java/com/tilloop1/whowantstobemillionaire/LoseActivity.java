package com.tilloop1.whowantstobemillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**************************************************************************************************
 * Class LoseActivity shows activity when the user loses the game
 * @author Pallavi Tilloo
 * @date 04-29-2021
 *************************************************************************************************/

public class LoseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);
        getSupportActionBar().hide();
    }

    /**********************************************************************************************
     * Function that is called when HOME button
     * @param view: The view that called the button
     **********************************************************************************************/
    public void Home(View view) {
        Intent i =  new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}
/**************************************  End of class *******************************************/