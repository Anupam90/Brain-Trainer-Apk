package com.example.anupam.braintrariner;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;
    GridLayout gridLayout;


    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswers;
    int score = 0;
    int numberOfQuestions = 0;

    public void playAgain(View view)
    {
        //playAgainButton.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestions();
        new CountDownTimer(30100, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                sumTextView.setVisibility(View.INVISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
                gridLayout.setVisibility(View.INVISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText(" Your score : " +Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();
    }

    public void generateQuestions()
    {
        Random rand = new Random();
        int num1 = rand.nextInt(21);
        int num2 = rand.nextInt(21);
        sumTextView.setText(Integer.toString(num1) + " + " + Integer.toString(num2));

        locationOfCorrectAnswers = rand.nextInt(4);
        int inCorrectAns;

        answers.clear();

        for(int i=0; i<4 ; i++)
        {
            if(i == locationOfCorrectAnswers)
            {
                answers.add(num1 + num2);
            }
            else
            {
                inCorrectAns = rand.nextInt(41);
                while (inCorrectAns == num1 + num2)
                {
                    inCorrectAns = rand.nextInt(41);
                }
                answers.add(inCorrectAns);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswers)))
        {
            score++;
            resultTextView.setText("Correct!");
        }
        else
        {
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestions();
    }

    public void start(View view)
    {
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
    }
}
