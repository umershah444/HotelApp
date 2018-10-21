package com.example.umer.hotelapp;

import android.content.Intent;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView imageView=(ImageView) findViewById(R.id.splash);
        imageView.setImageResource(R.drawable.logo);

        final Intent intent=new Intent(this,HomeActivity.class);
        boolean handler=new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               startActivity(intent);
            }
        },3000);
    }
}
