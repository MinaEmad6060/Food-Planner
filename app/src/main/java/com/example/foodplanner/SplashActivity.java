package com.example.foodplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.Online.StartActivity;

public class SplashActivity extends AppCompatActivity {

    TextView myText;
    LottieAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        myText=findViewById(R.id.welcome);
        animationView=findViewById(R.id.Lottie_Img);


        myText.animate().translationY(-1000).setDuration(2700).setStartDelay(1) ;
        animationView.animate().translationX(2000).setDuration(2002).setStartDelay(2900);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                    startActivity(intent);
                }
            }, 3000);

    }
}