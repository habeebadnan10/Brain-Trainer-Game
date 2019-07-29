package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rand = new Random();

    int a, b, operatorRand, cPositions;
    int correctRes, wrongRes1, wrongRes2, wrongRes3;
    int score,attempts = 0;

    TextView timerView, questionTextView, resultView, scoreTextView;
    Button c1, c2, c3, c4, goButton;
    androidx.gridlayout.widget.GridLayout gridLayout;

    String msg, aString , bString, scoreString, correctResString, wrongRes1String, wrongRes2String, wrongRes3String, timerString;

    public void start(){

        //hide go button
        goButton = findViewById(R.id.goButton);
        goButton.setVisibility(View.INVISIBLE);
        //unhide timer view
        timerView = findViewById(R.id.timerTextView);
        timerView.setVisibility(View.VISIBLE);
        //unhide score view
        scoreTextView = findViewById( R.id.scoreTextView);
        scoreTextView.setVisibility(View.VISIBLE);
        //unhide questionView
        questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setVisibility(View.VISIBLE);
        //reset resultView
        resultView = findViewById(R.id.resultTextView);
        resultView.setText("Guess!");
        //unhide choices
        gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setVisibility(View.VISIBLE);


        new CountDownTimer(30000,1000){

            public void onTick(long l){

                timerString = Long.toString(l/1000);
                timerString = timerString.length() < 2 ? "0"+timerString : timerString;

                timerView.setText("00:"+ timerString);

            }

            public void onFinish(){

                Log.i("timer","Finished");

                //hide questionView
                questionTextView = findViewById(R.id.questionTextView);
                questionTextView.setVisibility(View.INVISIBLE);
                //hide choices
                gridLayout = findViewById(R.id.gridLayout);
                gridLayout.setVisibility(View.INVISIBLE);
                //pop up play again
                goButton = findViewById(R.id.goButton);
                goButton.setVisibility(View.VISIBLE);

            }

        }.start();

        nextQ();

    }

    public void goButton(View view){

        score = 0;
        attempts = 0;
        //reset score
        changeScore();
        start();


    }

    public void changeScore(){

        scoreTextView = findViewById(R.id.scoreTextView);
        scoreString = String.valueOf(score);
        scoreTextView.setText(scoreString + "/" + attempts);

    }

    public void nextQ(){

        a = rand.nextInt(20) + 1;
        b = rand.nextInt(20) + 1;
        operatorRand = rand.nextInt(4);
        cPositions = rand.nextInt(4);

        questionTextView = findViewById(R.id.questionTextView);

         aString = Integer.toString(a);
         bString = Integer.toString(b);

        Log.i("rand numbers", aString+" "+bString);

         c1 = findViewById(R.id.c1Button);
         c2 = findViewById(R.id.c2Button);
         c3 = findViewById(R.id.c3Button);
         c4 = findViewById(R.id.c4Button);

        switch (operatorRand){

            case 0 : correctRes = a + b;
                questionTextView.setText(aString+ " + " +bString );
                break;

            case 1 : correctRes = a - b;
                questionTextView.setText(aString+ " - " +bString );
                break;

            case 2 : correctRes = a * b;
                questionTextView.setText(aString+ " * " +bString );
                break;

            case 3 : correctRes = a / b;
                questionTextView.setText(aString+ " / " +bString );
                break;

            default: questionTextView.setText(aString+ " = " +bString );

        }

        Log.i("Correct Answer", " "+correctRes);

        wrongRes1 = correctRes - rand.nextInt(10) + 2;
        wrongRes2 = correctRes + rand.nextInt(20) + 1;
        wrongRes3 = correctRes * rand.nextInt(4) + 1;

        if(correctRes == wrongRes1 || correctRes == wrongRes2 || correctRes == wrongRes3 || wrongRes1 == wrongRes2 || wrongRes1 == wrongRes3 || wrongRes2 == wrongRes3){

            Log.i("same values first", " correctRes : "+correctRes+" wrongRes1 : "+wrongRes1+" wrongRes2 : "+wrongRes2+" wrongRes3 : "+wrongRes3  );

            nextQ();

        }

         correctResString = Integer.toString(correctRes);
         wrongRes1String = Integer.toString(wrongRes1);
         wrongRes2String = Integer.toString(wrongRes2);
         wrongRes3String = Integer.toString(wrongRes3);

        switch (cPositions) {

            case 0 :
                c1.setTag(0);c2.setTag(1);c3.setTag(1);c4.setTag(1);
                c1.setText(correctResString);
                c2.setText(wrongRes1String);
                c3.setText(wrongRes2String);
                c4.setText(wrongRes3String);
                break;

            case 1 :
                c1.setTag(1);c2.setTag(0);c3.setTag(1);c4.setTag(1);
                c1.setText(wrongRes3String);
                c2.setText(correctResString);
                c3.setText(wrongRes1String);
                c4.setText(wrongRes2String);
                break;

            case 2 :
                c1.setTag(1);c2.setTag(1);c3.setTag(0);c4.setTag(1);
                c1.setText(wrongRes3String);
                c2.setText(wrongRes2String);
                c3.setText(correctResString);
                c4.setText(wrongRes1String);
                break;

            case 3 :
                c1.setTag(1);c2.setTag(1);c3.setTag(1);c4.setTag(0);
                c1.setText(wrongRes3String);
                c2.setText(wrongRes2String);
                c3.setText(wrongRes1String);
                c4.setText(correctResString);
                break;
        }

    }

    public void c1Click(View view){

        attempts+=1;

        int tag = ((Integer) view.getTag()).intValue();

        if(tag == 0){

            msg = "Correct ;)";
            score+=1;
            resultView = findViewById(R.id.resultTextView);
            resultView.setText(msg);
            changeScore();
            nextQ();

        }else{

            msg = "Wrong :(";
            resultView = findViewById(R.id.resultTextView);
            resultView.setText(msg);
            changeScore();
            nextQ();
        }


    }

    public void c2Click(View view){

        attempts+=1;
        int tag = ((Integer) view.getTag()).intValue();

        if(tag == 0){

            msg = "Correct ;)";
            score+=1;
            resultView = findViewById(R.id.resultTextView);
            resultView.setText(msg);
            changeScore();
            nextQ();

        }else{

            msg = "Wrong :(";
            resultView = findViewById(R.id.resultTextView);
            resultView.setText(msg);
            changeScore();
            nextQ();
        }

    }

    public void c3Click(View view){

        attempts+=1;
        int tag = ((Integer) view.getTag()).intValue();

        if(tag == 0){

            msg = "Correct ;)";
            score+=1;
            resultView = findViewById(R.id.resultTextView);
            resultView.setText(msg);
            changeScore();
            nextQ();

        }else{

            msg = "Wrong :(";
            resultView = findViewById(R.id.resultTextView);
            resultView.setText(msg);
            changeScore();
            nextQ();
        }

    }

    public void c4Click(View view){

        attempts+=1;
        int tag = ((Integer) view.getTag()).intValue();

        if(tag == 0){

            msg = "Correct ;)";
            score+=1;
            resultView = findViewById(R.id.resultTextView);
            resultView.setText(msg);
            changeScore();
            nextQ();

        }else{

            msg = "Wrong :(";
            resultView = findViewById(R.id.resultTextView);
            resultView.setText(msg);
            changeScore();
            nextQ();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
