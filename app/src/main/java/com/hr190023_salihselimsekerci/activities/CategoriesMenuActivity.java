package com.hr190023_salihselimsekerci.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hr190023_salihselimsekerci.R;
import com.hr190023_salihselimsekerci.utils.DialogUtils;
import com.hr190023_salihselimsekerci.utils.IntentUtils;
import com.hr190023_salihselimsekerci.utils.SharedPrefsUtils;

public class CategoriesMenuActivity extends AppCompatActivity {

    Button quitBtn;
    Button geogBtn;
    Button techBtn;
    Button histBtn;
    TextView wlcText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_menu);

        quitBtn = findViewById(R.id.btnQuit);
        wlcText = findViewById(R.id.textWelcome);
        geogBtn = findViewById(R.id.btnGeography);
        techBtn = findViewById(R.id.btnTech);
        histBtn = findViewById(R.id.btnHistory);

        String username = SharedPrefsUtils.getStringPreference(CategoriesMenuActivity.this, "username");

        wlcText.append(username);

        geogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToQuestions(geogBtn.getText().toString());
            }
        });

        techBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToQuestions(techBtn.getText().toString());
            }
        });

        histBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToQuestions(histBtn.getText().toString());
            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.ShowDialog(CategoriesMenuActivity.this, "Çıkış", "Uygulamadan Çıkılsın Mı?",
                        "Hayır", "Evet", null, null);
            }
        });
    }


    void goToQuestions(String CategoryName){    //Sorular sayfasına geçiş fonksiyonu, burada verilen parametreyi sharedprefse koyup sorular sayfasına gönderiyoruz, hangi kategorinin seçildiğini anlayabilmek için.

        SharedPrefsUtils.setStringPreference(CategoriesMenuActivity.this, "category", CategoryName);
        SharedPrefsUtils.setIntegerPreference(CategoriesMenuActivity.this, "questionCount", 1);
        SharedPrefsUtils.setIntegerPreference(CategoriesMenuActivity.this, "score", 0);

        IntentUtils.GoToActivity(CategoriesMenuActivity.this, QuestionPageActivity.class);
    }
}