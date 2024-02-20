package com.hr190023_salihselimsekerci.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hr190023_salihselimsekerci.R;

import com.hr190023_salihselimsekerci.utils.IntentUtils;
import com.hr190023_salihselimsekerci.utils.SharedPrefsUtils;

public class CongratulationsActivity extends AppCompatActivity {

    TextView trueAnswers;
    TextView scores;
    TextView btnQuit;
    TextView btnBackMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations);

        /*sharedprefsden gerekli veriler çekiliyor*/
        int questionCount = SharedPrefsUtils.getIntegerPreference(CongratulationsActivity.this, "questionCount", 0);
        int score = SharedPrefsUtils.getIntegerPreference(CongratulationsActivity.this, "score", 0);
        String category = SharedPrefsUtils.getStringPreference(CongratulationsActivity.this, "category");



        btnQuit = findViewById(R.id.btnQuitCongratulations);
        btnBackMenu = findViewById(R.id.btnBackMenuCongratulations);
        trueAnswers = findViewById(R.id.trueAnswersCong);
        scores = findViewById(R.id.txtScoreCongratulations);

        /*string.xml'den seçilen kategorinin soru adedi çekiliyor*/
        int questionLenght =getApplicationContext().getResources().getIdentifier(
                category + "SoruAdedi", "integer", getPackageName());

        /*skor ve doğru sayısı texte yazdırılıyor*/
        trueAnswers.append(String.valueOf(questionCount-1)+"/"+getResources().getString(questionLenght));
        scores.append(String.valueOf(score));

        btnBackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.GoToActivity(CongratulationsActivity.this, CategoriesMenuActivity.class);
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