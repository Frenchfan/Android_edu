package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.os.Parcel;
import android.os.Parcelable;

public class QuizResultActivity extends AppCompatActivity {
    TextView finalResultsView;
    private int userScore;
    private boolean[] userAnswers;
    private int numberofQuestions;
    private Question[] questions;
    private String toprint="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        finalResultsView=findViewById(R.id.FinalResultsView);
        Question[] questions= (Question[]) getIntent().getSerializableExtra("Questions");
        numberofQuestions=getIntent().getIntExtra("Numberofquestions",0);
        userAnswers= getIntent().getBooleanArrayExtra("UserAnswers");
        finalResultsView.setMovementMethod(new ScrollingMovementMethod());

        userScore=getIntent().getIntExtra("UserScore",0);





        toprint=toprint+"\n"+"За прохождение теста Вы заработали "+userScore+" баллов"+"\n"+"\n";
        for (int i = 0; i < numberofQuestions; i++) {
            toprint=toprint+("Вопрос: "+ "\n"+getString(questions[i].getQuestion())+"\n"+"Ваш ответ: "+userAnswers[i]+"\n"+"\n");
        }

        finalResultsView.setText(toprint);





    }
}