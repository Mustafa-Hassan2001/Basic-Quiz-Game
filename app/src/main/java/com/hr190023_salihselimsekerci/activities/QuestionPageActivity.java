package com.hr190023_salihselimsekerci.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.hr190023_salihselimsekerci.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hr190023_salihselimsekerci.utils.DialogUtils;
import com.hr190023_salihselimsekerci.utils.IntentUtils;
import com.hr190023_salihselimsekerci.utils.SharedPrefsUtils;

public class QuestionPageActivity extends AppCompatActivity {

    TextView titleTv;
    TextView questionTv;
    TextView AnswerA;
    TextView AnswerB;
    TextView AnswerC;
    TextView scoreCount;
    TextView levelTxt;
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);

        questionTv = findViewById(R.id.questionTv);
        titleTv = findViewById(R.id.titleTv);
        AnswerA = findViewById(R.id.answerA);
        AnswerB = findViewById(R.id.answerB);
        AnswerC = findViewById(R.id.answerC);
        levelTxt = findViewById(R.id.levelTxt);
        scoreCount = findViewById(R.id.scoreCount);

        /*sharedprefsden gerekli veriler çekiliyor */
        int questionCount = SharedPrefsUtils.getIntegerPreference(QuestionPageActivity.this, "questionCount", 1);
        String category = SharedPrefsUtils.getStringPreference(QuestionPageActivity.this, "category");
        int score = SharedPrefsUtils.getIntegerPreference(QuestionPageActivity.this, "score", 0);
        int questionLenght =getApplicationContext().getResources().getIdentifier(
                category + "SoruAdedi", "integer", getPackageName());

        /*SharedPrefsUtils.setStringPreference(CategoriesMenuActivity.this, "category", CategoryName);




        DOĞRUSU



        SharedPrefsUtils.setStringPreference(CategoriesMenuActivity.this, Constants.CATEGORY_MOVE_NAME, CategoryName);



        SharedPrefsUtils.setStringPreference(CategoriesMenuActivity.this, SharedPrefsUtils.CATEGORY_MOVE_NAME, CategoryName);  */


        titleTv.setText(category);
        levelTxt.append(questionCount+"/"+getResources().getString(questionLenght));
        scoreCount.append(String.valueOf(score));


        //string.xml den doğru cevap çekiliyor.
        int Answer =getApplicationContext().getResources().getIdentifier(
                category + questionCount, "string", getPackageName());


        questionTv.setText(getResources().getString(Answer));


        List<String> Answers = getAnswers(category,questionCount);
        Answers = mixList(Answers);

        AnswerA.setText(Answers.get(0));
        AnswerB.setText(Answers.get(1));
        AnswerC.setText(Answers.get(2));

        AnswerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AnswerA.getText().equals(checkTrue(category, questionCount)))
                {
                    AnswerA.setBackgroundResource(R.color.trueButtonColor);
                    waitAndContinue(true, category, score, questionCount);
                }
                else
                {
                    AnswerA.setBackgroundResource(R.color.wrongButtonColor);
                    waitAndContinue(false, category, score, questionCount);
                }
            }
        });


        AnswerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AnswerB.getText().equals(checkTrue(category, questionCount)))
                {
                    AnswerB.setBackgroundResource(R.color.trueButtonColor);
                    waitAndContinue(true, category, score, questionCount);
                }
                else
                {
                    AnswerB.setBackgroundResource(R.color.wrongButtonColor);
                    waitAndContinue(false, category, score, questionCount);
                }
            }
        });


        AnswerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AnswerC.getText().equals(checkTrue(category, questionCount)))
                {
                    AnswerC.setBackgroundResource(R.color.trueButtonColor);
                    waitAndContinue(true, category, score, questionCount);
                }
                else
                {
                    AnswerC.setBackgroundResource(R.color.wrongButtonColor);
                    waitAndContinue(false, category, score, questionCount);
                }
            }
        });
    }

    //                                                                                    FUNCTIONS

    //List Functions
    List<String> getAnswers(String title, int savedInt) //string.xml den soruları çekip oluşturduğumuz listeye atıyoruz ve bu listeyi döndürüyoruz.
    {
        List<String> Answers = new ArrayList<>();
        for(int i=1; i<4; i++)
        {
            int soru =getApplicationContext().getResources().getIdentifier(
                    title + savedInt + i, "string", getPackageName());
            Answers.add(getResources().getString(soru));
        }
        return Answers;
    }

    List<String> mixList(List<String> Answers)          //Parametre olarak verilen listenin içindeki değerleri karıştırıyoruz, örndeğin 0 indeksli eleman 2 indeksli eleman oluyor vs.
    {                                                   //Bu şekilde aynı soruya gelindiğinde her seferde şıklar aynı olmuyor, yerlerini karıştırıyoruz.
        Random rand = new Random();

        for (int i = 0; i < Answers.size(); i++) {
            int randomIndexToSwap = rand.nextInt(Answers.size());
            String temp = Answers.get(randomIndexToSwap);
            Answers.set(randomIndexToSwap, Answers.get(i));
            Answers.set(i, temp);
        }

        return Answers;
    }


    //About Answers

    String checkTrue(String title, int questionCount)        //Sorunun doğru şıkkını döndürüyor.
    {
        int soru =getApplicationContext().getResources().getIdentifier(
                title + questionCount + "3", "string", getPackageName());
        return getResources().getString(soru);
    }


    void waitAndContinue(boolean isAnswerTrue, String category, int score, int questionCount){ //1 saniye bekleyip cevap doğruysa, tamam mı devam mı diye soruyor. Yanlışsa, direkt gameover sahnesine atıyor.

        int questionLenght =getApplicationContext().getResources().getIdentifier(
                category + "SoruAdedi", "integer", getPackageName());

        countDownTimer = new CountDownTimer(1*1000, 1000) {
            @Override
            public void onTick(long millSecondsLeftToFinish){

            }

            @Override
            public void onFinish(){
                if(isAnswerTrue)
                {
                    SharedPrefsUtils.setIntegerPreference(QuestionPageActivity.this, "questionCount", questionCount+1);
                    SharedPrefsUtils.setIntegerPreference(QuestionPageActivity.this, "score", score+100);

                    if(questionCount == getResources().getInteger(questionLenght)) //eğer doğru sayısı, string.xmldeki soru sayısına eşitse bu bloğa giriyor.
                        IntentUtils.GoToActivity(QuestionPageActivity.this, CongratulationsActivity.class);
                    else
                        DialogUtils.ShowDialog(QuestionPageActivity.this, "Cevap Doğru", "Devam etmek istiyor musunuz?",
                                "Devam", "Çıkış", QuestionPageActivity.class, CategoriesMenuActivity.class);
                }
                else
                    IntentUtils.GoToActivity(QuestionPageActivity.this, GameOverActivity.class);
            }
        };
        countDownTimer.start();
    }

}