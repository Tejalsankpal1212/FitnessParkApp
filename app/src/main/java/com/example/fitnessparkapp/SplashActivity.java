package com.example.fitnessparkapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private static final String SHARED_PREFS="sharedprefs";
    private static final String MEMBER_ID="memberid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences  sharedPreferences= getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                String memberid= sharedPreferences.getString("MEMBER_ID","");
                if(memberid == "")
                {
                    Intent register=new Intent(SplashActivity.this,RegisterActivity.class);
                    startActivity(register);
                }
                else
                {
                    Intent home=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(home);
                }
            }
        },4000);
    }
}