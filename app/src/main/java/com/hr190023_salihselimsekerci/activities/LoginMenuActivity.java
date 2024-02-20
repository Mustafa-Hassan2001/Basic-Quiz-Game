package com.hr190023_salihselimsekerci.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hr190023_salihselimsekerci.R;

import com.hr190023_salihselimsekerci.utils.DialogUtils;
import com.hr190023_salihselimsekerci.utils.IntentUtils;
import com.hr190023_salihselimsekerci.utils.SharedPrefsUtils;

public class LoginMenuActivity extends AppCompatActivity {

    Button btnNext;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_menu);
        btnNext = findViewById(R.id.ileriBtn);
        username = findViewById(R.id.username);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //textboxa yazılan değer name değişkenine atanıyor, trim ile baştaki ve sondaki boşlukları siliyoruz.
                String name = username.getText().toString().trim();

                if(nameCheck(name))
                {
                    SharedPrefsUtils.setStringPreference(LoginMenuActivity.this, "username", name);

                    IntentUtils.GoToActivity(LoginMenuActivity.this, CategoriesMenuActivity.class);
                }
                else
                {
                    DialogUtils.ShowDialog(LoginMenuActivity.this, "HATA!", "Lütfen geçerli bir oyuncu adı giriniz.", null, null, null, null);
                }


            }
        });

    }


    boolean nameCheck(String name){ //Burada girilen ismin boş kontrolu yapılıyor.

        if(name == null) return false;
        if(name.length() == 0) return false;

        return true;
    }

}