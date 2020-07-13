package com.teamSCORPION.youtubealarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import static com.teamSCORPION.youtubealarm.R.drawable.m_add_btn;

public class MainActivity extends AppCompatActivity {

    float device_height, device_width;
    ImageView sticker, divider1, divider2, divider3;
    Bitmap morning, afternoon, evening, night;
    Bitmap morning_line, afternoon_line, evening_line, night_line;
    Drawable[] btn_options_id, btn_add_id;
    ConstraintLayout main_layout;
    Button btn_options, btn_add;
    TextView Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        final Calendar[] rightNow = {Calendar.getInstance()};
        final int currentHourIn24Format = rightNow[0].get(Calendar.HOUR_OF_DAY);
        final int[] currentHourIn12Format = {rightNow[0].get(Calendar.HOUR)};
        final int[] currentMinute = {rightNow[0].get(Calendar.MINUTE)};

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        device_height = metrics.heightPixels;
        device_width = metrics.widthPixels;

        btn_options_id = new Drawable[4];
        btn_options_id[0] = ResourcesCompat.getDrawable(getResources(), R.drawable.m_settings_btn, null);
        btn_options_id[1] = ResourcesCompat.getDrawable(getResources(), R.drawable.a_settings_btn, null);
        btn_options_id[2] = ResourcesCompat.getDrawable(getResources(), R.drawable.e_settings_btn, null);
        btn_options_id[3] = ResourcesCompat.getDrawable(getResources(), R.drawable.n_settings_btn, null);

        btn_add_id = new Drawable[4];
        btn_add_id[0] = ResourcesCompat.getDrawable(getResources(), R.drawable.m_add_btn, null);
        btn_add_id[1] = ResourcesCompat.getDrawable(getResources(), R.drawable.a_add_btn, null);
        btn_add_id[2] = ResourcesCompat.getDrawable(getResources(), R.drawable.e_add_btn, null);
        btn_add_id[3] = ResourcesCompat.getDrawable(getResources(), R.drawable.n_add_btn, null);

        morning_line = BitmapFactory.decodeResource(getResources(),R.drawable.m_line);
        afternoon_line = BitmapFactory.decodeResource(getResources(),R.drawable.a_line);
        evening_line = BitmapFactory.decodeResource(getResources(),R.drawable.e_line);
        night_line = BitmapFactory.decodeResource(getResources(),R.drawable.n_line);

        sticker = findViewById(R.id.sticker);
        main_layout = findViewById(R.id.main_layout);
        btn_options = findViewById(R.id.btn_options);
        btn_add = findViewById(R.id.button_add);
        Message = findViewById(R.id.textView_message);
        divider1 = findViewById(R.id.divider1);
        divider2 = findViewById(R.id.divider2);
        divider3 = findViewById(R.id.divider3);

        morning = BitmapFactory.decodeResource(getResources(),R.drawable.m_sticker);
        afternoon = BitmapFactory.decodeResource(getResources(),R.drawable.a_sticker);
        evening = BitmapFactory.decodeResource(getResources(),R.drawable.e_sticker);
        night = BitmapFactory.decodeResource(getResources(),R.drawable.n_sticker);

        if(currentHourIn24Format > 3 & currentHourIn24Format < 12){
            main_layout.setBackgroundColor(Color.parseColor("#f3989d"));
            sticker.setImageBitmap(morning);
            btn_options.setBackground(btn_options_id[0]);
            btn_add.setBackground(btn_add_id[0]);
            Message.setTextColor(Color.parseColor("#f2e3e4"));
            divider1.setImageBitmap(morning_line);
            divider2.setImageBitmap(morning_line);
            divider3.setImageBitmap(morning_line);
        }else if(currentHourIn24Format > 11 & currentHourIn24Format < 17){
            main_layout.setBackgroundColor(Color.parseColor("#febc6e"));
            sticker.setImageBitmap(afternoon);
            btn_options.setBackground(btn_options_id[1]);
            btn_add.setBackground(btn_add_id[1]);
            Message.setTextColor(Color.parseColor("#f9c3c3"));
            divider1.setImageBitmap(afternoon_line);
            divider2.setImageBitmap(afternoon_line);
            divider3.setImageBitmap(afternoon_line);
        }else if(currentHourIn24Format > 16 & currentHourIn24Format < 21){
            main_layout.setBackgroundColor(Color.parseColor("#febc6e"));
            sticker.setImageBitmap(evening);
            btn_options.setBackground(btn_options_id[2]);
            btn_add.setBackground(btn_add_id[2]);
            Message.setTextColor(Color.parseColor("#ffffff"));
            divider1.setImageBitmap(evening_line);
            divider2.setImageBitmap(evening_line);
            divider3.setImageBitmap(evening_line);
        }else {
            main_layout.setBackgroundColor(Color.parseColor("#202020"));
            sticker.setImageBitmap(night);
            btn_options.setBackground(btn_options_id[3]);
            btn_add.setBackground(btn_add_id[3]);
            Message.setTextColor(Color.parseColor("#c5c5c5"));
            divider1.setImageBitmap(night_line);
            divider2.setImageBitmap(night_line);
            divider3.setImageBitmap(night_line);
        }
    }
}