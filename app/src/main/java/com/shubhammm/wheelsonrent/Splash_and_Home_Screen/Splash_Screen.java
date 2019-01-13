package com.shubhammm.wheelsonrent.Splash_and_Home_Screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.shubhammm.wheelsonrent.R;
import com.shubhammm.wheelsonrent.User.Login;

public class Splash_Screen extends AppCompatActivity {

    private static int SPLASH = 1700;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        Window window = getWindow();
        //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        window.setStatusBarColor(getColor(R.color.light_brown));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash_Screen.this,Login.class);
                startActivity(i);
                finish();
            }
        },SPLASH);


    }
}
