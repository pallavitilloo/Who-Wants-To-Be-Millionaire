package com.tilloop1.whowantstobemillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/*************************************************************************************************
 *
 ************************************************************************************************/
public class MainActivity extends AppCompatActivity {

    TextView tvQuestion;
    RadioGroup rgOptions;
    RadioButton rb1, rb2, rb3, rb4;
    static int current_ques = 0;
    static long amount = 50;
    long[] levels = {100, 200, 500, 1000, 2000, 5000, 10000,
                    20000, 50000, 100000, 200000, 500000, 1000000};
    TextView tvAmount;
    final int LAST_QUESTION = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        rgOptions = (RadioGroup) findViewById(R.id.rgOptions);
        rb1 = (RadioButton) findViewById(R.id.rbOption1);
        rb2 = (RadioButton) findViewById(R.id.rbOption2);
        rb3 = (RadioButton) findViewById(R.id.rbOption3);
        rb4 = (RadioButton) findViewById(R.id.rbOption4);
        tvAmount = (TextView) findViewById(R.id.tvAmt);
        tvAmount.setVisibility(View.INVISIBLE);
        current_ques = 1;
    }

    /********************************************************************************************
     *
     * @param aString
     * @return
     ********************************************************************************************/
    private String getStringResourceByName(String aString) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }

    /********************************************************************************************
     *
     * @param aString
     * @return
     ********************************************************************************************/
    private int getResourceByName(String aString){
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "id", packageName);
        return resId;
    }

    /********************************************************************************************
     *
     ********************************************************************************************/
    private void ShowNextQuestion(){
        StringBuilder strQuesID = new StringBuilder();
        strQuesID.append("question_");
        strQuesID.append(++current_ques);
        tvQuestion.setText(getStringResourceByName(strQuesID.toString()));

        StringBuilder strRBOption = new StringBuilder();
        strRBOption.append("option_");
        strRBOption.append(current_ques);
        String strTemp = strRBOption.toString().concat("_a");
        rb1.setText(getStringResourceByName(strTemp));
        rb1.setChecked(false);

        strTemp = strRBOption.toString().concat("_b");
        rb2.setText(getStringResourceByName(strTemp));
        rb2.setChecked(false);

        strTemp = strRBOption.toString().concat("_c");
        rb3.setText(getStringResourceByName(strTemp));
        rb3.setChecked(false);

        strTemp = strRBOption.toString().concat("_d");
        rb4.setText(getStringResourceByName(strTemp));
        rb4.setChecked(false);
    }

    /**********************************************************************************************
     *
     * @return
     *********************************************************************************************/
    private RadioButton getSelectedOption() {
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
            tvQuestion.setError("You need to select one option!");
            return null;
        }
    }

    /**********************************************************************************************
     *
     * @param view
     *********************************************************************************************/
    public void Submit(View view){

        Bundle bundle = new Bundle();
        Intent i;

        StringBuilder strAnsBldr = new StringBuilder();
        StringBuilder strOptionBldr = new StringBuilder();

        // To get the answer from Strings.xml, we will form the resource ID and get the value
        String answer_id, answer_text, strRGOptions;
        strAnsBldr.append("answer_");
        strAnsBldr.append(current_ques);
        answer_id = strAnsBldr.toString();      //Eg. answer_id = "answer_13" for Ques 13
        answer_text  = getStringResourceByName(answer_id);    // Get the answer from Strings.xml

        RadioButton rbSelection = getSelectedOption();
        if(rbSelection != null){

            //Check if the user selection is equal to the answer
            if(rbSelection.getText().equals(answer_text)){

                // Correct answer
                Toast toast = Toast.makeText(getApplicationContext(), "Your answer is correct! You won $"+String.valueOf(levels[current_ques-1]), Toast.LENGTH_LONG);
                toast.show();

                //Update Amount won
                StringBuilder strAmount = new StringBuilder();
                strAmount.append(getString(R.string.won));
                strAmount.append("$");
                strAmount.append(levels[current_ques-1]);
                tvAmount.setText(strAmount.toString());
                tvAmount.setVisibility(View.VISIBLE);
                if(current_ques != LAST_QUESTION) {
                    ShowNextQuestion();
                }
                else{
                    //Celebration!!
                    i =  new Intent(this, WinActivity.class);
                    startActivity(i);
                }
            }
            else{
                //Incorrect - exit the game

                Toast toast = Toast.makeText( getApplicationContext(),"Sorry! You lost the game!", Toast.LENGTH_LONG);
                toast.show();

                i =  new Intent(this, SplashActivity.class);
                startActivity(i);
            }
        }
    }
}