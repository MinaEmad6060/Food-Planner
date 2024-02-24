package com.example.foodplanner.Plans.View.Plan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.foodplanner.HomeScreen.View.HomeActivity;
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

    ImageView btnBack;



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
        btnBack=findViewById(R.id.btn_makePlanOfWeek_back);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferData = new Intent(WeekDaysActivity.this, HomeActivity.class);
                startActivity(transferData);
            }
        });


        btnSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeekDaysActivity.this,
                        AllMealsActivity.class);
                intent.putExtra(EXTRA_DAY,"saturday");
                startActivity(intent);
            }
        });
        btnSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeekDaysActivity.this,
                        AllMealsActivity.class);
                intent.putExtra(EXTRA_DAY,"sunday");
                startActivity(intent);
            }
        });

        btnMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeekDaysActivity.this,
                        AllMealsActivity.class);
                intent.putExtra(EXTRA_DAY,"monday");
                startActivity(intent);
            }
        });
        btnTue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeekDaysActivity.this,
                        AllMealsActivity.class);
                intent.putExtra(EXTRA_DAY,"tuesday");
                startActivity(intent);
            }
        });
        btnWed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeekDaysActivity.this,
                        AllMealsActivity.class);
                intent.putExtra(EXTRA_DAY,"wednesday");
                startActivity(intent);
            }
        });
        btnTh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeekDaysActivity.this,
                        AllMealsActivity.class);
                intent.putExtra(EXTRA_DAY,"thursday");
                startActivity(intent);
            }
        });
        btnFri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeekDaysActivity.this,
                        AllMealsActivity.class);
                intent.putExtra(EXTRA_DAY,"friday");
                startActivity(intent);
            }
        });



    }
}