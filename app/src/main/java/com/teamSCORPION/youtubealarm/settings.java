package com.teamSCORPION.youtubealarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Calendar;

public class settings extends AppCompatActivity {

    Button btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        ConstraintLayout main_layout;
        main_layout = findViewById(R.id.settings_layout);

        btn_home = findViewById(R.id.btn_home);

        final Calendar[] rightNow = {Calendar.getInstance()};
        final int currentHourIn24Format = rightNow[0].get(Calendar.HOUR_OF_DAY);
        final int[] currentHourIn12Format = {rightNow[0].get(Calendar.HOUR)};
        final int[] currentMinute = {rightNow[0].get(Calendar.MINUTE)};
        rightNow[0] = Calendar.getInstance();
        currentHourIn12Format[0] = rightNow[0].get(Calendar.HOUR_OF_DAY);
        currentMinute[0] = rightNow[0].get(Calendar.MINUTE);

        if(currentHourIn24Format > 3 & currentHourIn24Format < 12){
            main_layout.setBackgroundColor(Color.parseColor("#f3989d"));
        }else if(currentHourIn24Format > 11 & currentHourIn24Format < 17){
            main_layout.setBackgroundColor(Color.parseColor("#d63447"));
        }else if(currentHourIn24Format > 16 & currentHourIn24Format < 21){
            main_layout.setBackgroundColor(Color.parseColor("#febc6e"));
        }else {
            main_layout.setBackgroundColor(Color.parseColor("#202020"));
        }

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settings.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}