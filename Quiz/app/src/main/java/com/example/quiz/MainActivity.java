package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button yesBtn;
    private Button noBtn;
    private Button showAnswer;
    private TextView questionTextView;
    private Question[] questions = {
            new Question(R.string.question, true),
            new Question(R.string.question1, true),
            new Question(R.string.question2, false),
            new Question(R.string.question3, false),
            new Question(R.string.question4, true),
            new Question(R.string.question5, false),
            new Question(R.string.question6, true),
            new Question(R.string.question7, true),
            new Question(R.string.question8, false),
            new Question(R.string.question9, false)

    };
    private int questionIndex = 0;
    private int userScore = 0;
    private boolean[] userAnswers =new boolean[questions.length];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SYSTEM INFO", "Метод onCreate() запущен");
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null)
            questionIndex = savedInstanceState.getInt("question", 0);

        questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(questions[questionIndex].getQuestion());
        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        showAnswer = findViewById(R.id.showAnswer);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, questions[questionIndex].isAnswer()?R.string.correct:R.string.incorrect, Toast.LENGTH_SHORT).show();
                userAnswers[questionIndex]=true;
                if (questions[questionIndex].isAnswer()) {
                    userScore=userScore+1;
                    Toast.makeText(MainActivity.this, R.string.correct, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
                }
                //Записываем ответ пользователя в массив.
                // Записываем в строку "Вопрос - ваш ответ да/нет в массив
                // Суммируем правильные ответы в переменную int
                // Проверяем,что вопрос последний
                //Если вопрос последний, то создаем интент
                // Добавляем дополнения в Интент (массив с вопросами и овтетами)
                // Добавляем дополнение в Интент с числом правильных ответов (Int с числом правильных ответов)
                // Запускаем активность
                // Считаем баллы,вывести строки из массива, отобразив вопросы и ответы пользователя в TextView
                // Нужно сохранять ответы пользователя

                if (questionIndex < questions.length-1) {
                    questionIndex=questionIndex + 1;
                    questionTextView.setText(questions[questionIndex].getQuestion());

                }else {
                    ShowResults();
                }


            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this,questions[questionIndex].isAnswer()?R.string.correct):R.string.correct,R.string.incorrect);
                userAnswers[questionIndex]=false;
                if (!questions[questionIndex].isAnswer()) {
                    userScore=userScore+1;
                    Toast.makeText(MainActivity.this, R.string.correct, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
                }
                //Записываем ответ пользователя в массив.
                // Записываем в строку "Вопос - ваш ответ да/нет в массив
                // Суммируем правильные ответы в переменную int
                // Проверяем,что вопрос последний
                //Если вопрос последний, то создаем интент
                // Добавляем дополнения в Интент (массив с вопросами и овтетами)
                // Добавляем дополнение в Интент с числом правильных ответов (Int с числом правильных ответов)
                // Запускаем активность
                // Считаем баллы,вывести строки из массива, отобразив вопросы и ответы пользователя в TextView
                // Нужно сохранять ответы пользователя

                if (questionIndex < questions.length-1) {
                    questionIndex=questionIndex + 1;
                    questionTextView.setText(questions[questionIndex].getQuestion());

                } else {
                    ShowResults();
                                    }
            }
        });
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                intent.putExtra("answer", questions[questionIndex].isAnswer());
                startActivity(intent);
            }
        });
    }

    private void ShowResults() {
        Intent intent = new Intent(MainActivity.this, QuizResultActivity.class);
        intent.putExtra("UserAnswers", userAnswers);
        intent.putExtra("Questions", questions);
        intent.putExtra("UserScore", userScore);
        intent.putExtra("Numberofquestions", questions.length);
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("SYSTEM INFO", "Метод onStart() запущен");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("SYSTEM INFO", "Метод onResume() запущен");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d("SYSTEM INFO", "Метод onSaveInstanceState() запущен");
        savedInstanceState.putInt("question", questionIndex);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("SYSTEM INFO", "Метод onPause() запущен");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("SYSTEM INFO", "Метод onStop() запущен");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SYSTEM INFO", "Метод onDestroy() запущен");
    }

}