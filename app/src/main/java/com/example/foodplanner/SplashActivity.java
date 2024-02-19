package com.example.foodplanner;

import static com.example.foodplanner.Online.LoginFragment.SHARED_PREF;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.HomeScreen.View.HomeActivity;
import com.example.foodplanner.Online.StartActivity;

public class SplashActivity extends AppCompatActivity {


    private static final String TAG = "SplashActivity";
    TextView myText;
    LottieAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        myText=findViewById(R.id.welcome);
        animationView=findViewById(R.id.Lottie_Img);
        SharedPreferences sharedPreferences =
                getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("name","");
        Log.i(TAG, "userName: "+userName);


        myText.animate().translationY(-1100).setDuration(2700).setStartDelay(1) ;
        animationView.animate().translationX(2000).setDuration(2002).setStartDelay(2900);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(userName.equals("")){
                        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }

                }
            }, 3000);

    }
}