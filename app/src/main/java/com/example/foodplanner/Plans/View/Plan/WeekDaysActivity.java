package com.example.foodplanner.Plans.View.Plan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.foodplanner.Plans.View.DetailsOfMealActivity;
import com.example.foodplanner.R;

public class WeekDaysActivity extends AppCompatActivity {


    public static final String EXTRA_DAY = "WeekDaysActivity";
    ImageView btnSat;
    ImageView btnSun;
    ImageView btnMon;
    ImageView btnTue;
    ImageView btnWed;
    ImageView btnTh;
    ImageView btnFri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_days);

        btnSat=findViewById(R.id.btn_saturday);
        btnSun=findViewById(R.id.btn_sunday);
        btnMon=findViewById(R.id.btn_monday);
        btnTue=findViewById(R.id.btn_tuesday);
        btnWed=findViewById(R.id.btn_wednesday);
        btnTh=findViewById(R.id.btn_thursday);
        btnFri=findViewById(R.id.btn_friday);


        btnSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeekDaysActivity.this,
                        AllMealsActivity.class);
                intent.putExtra(EXTRA_DAY,"saturday");
                startActivity(intent);
            }
        });


    }
}