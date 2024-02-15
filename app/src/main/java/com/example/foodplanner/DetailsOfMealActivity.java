package com.example.foodplanner;

import static com.example.foodplanner.HomeScreen.View.HomeFragment.EXTRA_MEAL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class DetailsOfMealActivity extends AppCompatActivity {


    private static final String TAG = "MealDetails";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_meal);

        Intent listenMessage = getIntent();

        String message = listenMessage.getStringExtra(EXTRA_MEAL);
        Log.i(TAG, "message: "+message);


    }
}