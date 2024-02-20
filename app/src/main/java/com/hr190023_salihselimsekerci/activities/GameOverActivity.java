package com.hr190023_salihselimsekerci.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hr190023_salihselimsekerci.R;

import com.hr190023_salihselimsekerci.utils.IntentUtils;
import com.hr190023_salihselimsekerci.utils.SharedPrefsUtils;

public class GameOverActivity extends AppCompatActivity {

    TextView trueAnswers;
    TextView scores;
    TextView btnBackMenu;
    TextView btnQuit;

    /*Yapı olarak CongratulationsActivity ile aynı bir sayfa*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        btnBackMenu = findViewById(R.id.btnBackMenuGameOver);
        btnQuit = findViewById(R.id.btnQuitGameOver);
        trueAnswers = findViewById(R.id.trueAnswersGameOver);
        scores = findViewById(R.id.scoreTxtGameOver);

        int questionCount = SharedPrefsUtils.getIntegerPreference(GameOverActivity.this, "questionCount", 0);
        int score = SharedPrefsUtils.getIntegerPreference(GameOverActivity.this, "score", 0);
        String category = SharedPrefsUtils.getStringPreference(GameOverActivity.this, "category");

        int questionLenght =getApplicationContext().getResources().getIdentifier(
                category + "SoruAdedi", "integer", getPackageName());

        trueAnswers.append(String.valueOf(questionCount-1)+"/"+getResources().getString(questionLenght));
        scores.append(String.valueOf(score));

        btnBackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.GoToActivity(GameOverActivity.this, CategoriesMenuActivity.class);
            }
        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}