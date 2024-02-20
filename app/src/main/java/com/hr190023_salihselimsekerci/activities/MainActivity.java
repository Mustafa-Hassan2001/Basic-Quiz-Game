package com.hr190023_salihselimsekerci.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.hr190023_salihselimsekerci.R;

import com.hr190023_salihselimsekerci.utils.IntentUtils;

public class MainActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerStart();
    }

    protected void onStart(){
        super.onStart();
    }

    void timerStart(){  //3 saniye bekleyip, login menuye geçiliyor

        countDownTimer = new CountDownTimer(3*1000, 1000) {
            @Override
            public void onTick(long millSecondsLeftToFinish){
            }

            @Override
            public void onFinish(){
                IntentUtils.GoToActivity(MainActivity.this, LoginMenuActivity.class);
            }
        };
        countDownTimer.start();
    }
}