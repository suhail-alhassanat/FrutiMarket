package com.suhail.frutimarket.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.Toast;

import com.suhail.frutimarket.R;
import com.suhail.frutimarket.models.SharedPreferencesClient;

public class SplachActivity extends AppCompatActivity {
    private   SharedPreferences preferences;
    private  final String IS_FIRST_TIME_KEY="is_first_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        preferences =getSharedPreferences("preference", Context.MODE_PRIVATE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isFirstTime()){
                startActivity(new Intent(SplachActivity.this, IntroActivity.class));
                updateFirstTimeValue();
                finish();
            }
                else
                {
                    startActivity(new Intent(SplachActivity.this,LoginActivity.class));
                    finish();
                }
            }
        }, 1500);

    }
    public boolean isFirstTime(){
        if(preferences.contains(IS_FIRST_TIME_KEY))
            return false;
        return true;
    }
    public void updateFirstTimeValue(){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt(IS_FIRST_TIME_KEY,1);
        editor.apply();
    }
}