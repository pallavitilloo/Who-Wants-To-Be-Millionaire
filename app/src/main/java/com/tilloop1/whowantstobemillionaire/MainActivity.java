package com.tilloop1.whowantstobemillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/*************************************************************************************************
 * Class MainActivity is the activity that handles the game quiz. It is called by HomeActivity when
 * the user clicks on the Play button.
 * @author Pallavi Tilloo
 * @date 04-29-2021
 ************************************************************************************************/
public class MainActivity extends AppCompatActivity {

    // Class members
    Handler handler;
    RadioGroup rgOptions;
    Button btnQuit, btnSubmit;
    TextView tvQuestion, tvAmount;
    RadioButton rb1, rb2, rb3, rb4;

    static int current_ques = 0;
    final int LAST_QUESTION = 15, TIME_LIMIT = 3000;  // The time delay in milliseconds.
    // The levels for the Prize Money Amount
    String[] levels = {"100", "200", "400", "800", "1,600", "3,200", "6,400",
                    "12,500", "25,000", "50,000", "100,000", "200,000", "300,000",
                    "500,000", "1,000,000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // Get views from the layout and initialize to be used later
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnQuit = (Button) findViewById(R.id.btnQuit);
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        rgOptions = (RadioGroup) findViewById(R.id.rgOptions);
        rb1 = (RadioButton) findViewById(R.id.rbOption1);
        rb2 = (RadioButton) findViewById(R.id.rbOption2);
        rb3 = (RadioButton) findViewById(R.id.rbOption3);
        rb4 = (RadioButton) findViewById(R.id.rbOption4);
        tvAmount = (TextView) findViewById(R.id.tvAmt);

        // Amount needs to hidden when activity first loads
        tvAmount.setVisibility(View.INVISIBLE);

        // Initially current question will be 1
        current_ques = 1;

        // Quit option needs to be shown only at Questions 5 and 10
        btnQuit.setVisibility(View.INVISIBLE);
    }

    /********************************************************************************************
     * Function that gets String resource from Strings.xml as per the supplied string
     * dynamically
     * @param aString String ID of the resource to be retrieved
     * @return Value of the STRING from Strings.xml
     ********************************************************************************************/
    private String getStringResourceByName(String aString) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }

    /********************************************************************************************
     * Function that gets the resource mapped to the supplied String value
     * @param aString String value for the resource id
     * @return Integer value of the ID from which resource can be retrieved
     ********************************************************************************************/
    private int getResourceByName(String aString){
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "id", packageName);
        return resId;
    }

    /********************************************************************************************
     * Function that shows the next question in queue on the MainACtivity screen. It gets the
     * current question number and forms the IDs for the resources for next question and options.
     * These are then supplied to fetch the respective resources.
     ********************************************************************************************/
    private void ShowNextQuestion(){

        // To form the question id e.g. question_2
        StringBuilder strQuesID = new StringBuilder();
        strQuesID.append("question_");
        strQuesID.append(++current_ques);
        tvQuestion.setText(getStringResourceByName(strQuesID.toString()));

        // To form the string to get Option values e.g. option_2_a
        StringBuilder strRBOption = new StringBuilder();
        strRBOption.append("option_");
        strRBOption.append(current_ques);
        String strTemp = strRBOption.toString().concat("_a");

        // Set the text as per the resource value and set checked as FALSE as it is a new question
        rb1.setText(getStringResourceByName(strTemp));
        rb1.setChecked(false);

        // To form the string to get Option values e.g. option_2_b
        strTemp = strRBOption.toString().concat("_b");
        rb2.setText(getStringResourceByName(strTemp));
        rb2.setChecked(false);

        // To form the string to get Option values e.g. option_2_c
        strTemp = strRBOption.toString().concat("_c");
        rb3.setText(getStringResourceByName(strTemp));
        rb3.setChecked(false);

        // To form the string to get Option values e.g. option_2_d
        strTemp = strRBOption.toString().concat("_d");
        rb4.setText(getStringResourceByName(strTemp));
        rb4.setChecked(false);

        // Check if current question is 5 or 10
        if(current_ques == 5 || current_ques == 10){
            // Show QUIT Button then
            btnQuit.setVisibility(View.VISIBLE);
        }
        else
        {
            // Hide QUIT Button then
            btnQuit.setVisibility(View.INVISIBLE);
        }
        // Submit will be disabled whenever it is clicked, and enabled when next question is showed
        btnSubmit.setEnabled(true);
    }

    /********************************************************************************************
     * Function which is called on the click of the Quit button. Whatever prize money user has won
     * will be retained.
     * @param view View that called the function
     *******************************************************************************************/
    public void QuitGame(View view){
        //Form a new intent
        Intent i =  new Intent(this, WinActivity.class);
        Bundle bundle = new Bundle();

        //Put value of the current amount from Levels array and pass to the activity WinActivity
        bundle.putString("prize_amt", levels[current_ques-1]);
        i.putExtras(bundle);

        //Start the WinActivity
        startActivity(i);
    }

    /*********************************************************************************************
     * Function that checks which option out of the Radio buttons is selected.
     * RadioGroup.getSelectedId does not work for this layout as Radiobuttons are indirect children
     * of the RadioGroup
     * @return RadioButton that is selected, if none, shows error message and returns null
     *******************************************************************************************/
    private RadioButton getSelectedOption() {

        // Return whichever Radio button is checked
        if(rb1.isChecked())
            return rb1;
        else if(rb2.isChecked())
            return rb2;
        else if(rb3.isChecked())
            return rb3;
        else if(rb4.isChecked())
            return rb4;
        else
        {
            // No option is selected
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.no_option_selected), Toast.LENGTH_SHORT);
            toast.show();
            return null;
        }
    }

    /**********************************************************************************************
     * Function that gets called when Submit button is pressed
     * @param view The view that called it
     *********************************************************************************************/
    public void Submit(View view){

        Bundle bundle = new Bundle();

        // Form the resource ID of the answer - retrieved from Strings.xml
        StringBuilder strAnsBldr = new StringBuilder();

        // To get the answer from Strings.xml, we will form the resource ID and get the value
        String answer_id, answer_text;
        strAnsBldr.append("answer_");
        strAnsBldr.append(current_ques);

        answer_id = strAnsBldr.toString();      //Eg. answer_id = "answer_13" for Ques 13
        answer_text  = getStringResourceByName(answer_id);    // Get the answer from Strings.xml

        // Get the radio button that is checked
        RadioButton rbSelection = getSelectedOption();

        if(rbSelection != null){

            //Check if the user selection is equal to the answer
            if(rbSelection.getText().equals(answer_text)) {

                // Correct answer
                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.toast_correct) + levels[current_ques - 1], Toast.LENGTH_SHORT);
                toast.show();

                // Check if this the last question
                if (current_ques != LAST_QUESTION) {

                    // Update Amount won into the TextView
                    StringBuilder strAmount = new StringBuilder();
                    strAmount.append(getString(R.string.won));
                    strAmount.append("$");

                    //This will get the amount for the current question.
                    // Current_ques-1 is used as Array index starts from 0 while our question numbers start from 1
                    strAmount.append(levels[current_ques - 1]);

                    // Set the text for the TextView to show the amount won by user
                    tvAmount.setText(strAmount.toString());
                    tvAmount.setVisibility(View.VISIBLE);

                    //Disable the submit button to avoid multiple clicks
                    btnSubmit.setEnabled(false);

                    // To ensure that the toast gets visible and after delay next question is shown
                    handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ShowNextQuestion();
                        }
                    }, TIME_LIMIT);

                }   // End of IF this is not the last question
                else {

                    // This is the last question and user has given correct answer
                    // Wait for toast to show and then start WinActivity

                    handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // New intent to start WinActivity
                            Intent intent = new Intent(getApplicationContext(), WinActivity.class);
                            Bundle new_bundle = new Bundle();

                            // Pass the last amount in levels array to the bundle
                            new_bundle.putString("prize_amt", levels[14]);
                            intent.putExtras(new_bundle);

                            // Start the activity WinActivity
                            startActivity(intent);
                        }

                    }, TIME_LIMIT);

                } // End of ELSE - executed when the current question is the last question

            } //End of IF user gave correct answer

            else{

                //Incorrect answer chosen, so the user loses
                Toast toast = Toast.makeText( getApplicationContext(),getString(R.string.toast_wrong), Toast.LENGTH_SHORT);
                toast.show();

                // Wait for the toast to be shown

                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Start the Lose Activity since the user lost the game
                        Intent i =  new Intent(getApplicationContext(), LoseActivity.class);
                        startActivity(i);
                    }
                }, TIME_LIMIT);

            } //End of else part where user gave incorrect answer

        } // End of If condition to check if radio button is selected

    }  //End of Submit function
}

/******************************************        End of class    *******************************/