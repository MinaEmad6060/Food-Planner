package com.example.foodplanner.Plans.View.Plan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.foodplanner.HomeScreen.View.HomeActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.View.AreaActivity;

public class MyWeekDaysActivity extends AppCompatActivity {

    public static final String EXTRA_MY_DAY = "MyWeekDaysActivity";

    private static final String TAG = "checkDay";

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
        setContentView(R.layout.activity_my_week_days);

        btnSat=findViewById(R.id.btn_saturday_MyWeek);
        btnSun=findViewById(R.id.btn_sunday_MyWeek);
        btnMon=findViewById(R.id.btn_monday_MyWeek);
        btnTue=findViewById(R.id.btn_tuesday_MyWeek);
        btnWed=findViewById(R.id.btn_wednesday_MyWeek);
        btnTh=findViewById(R.id.btn_thursday_MyWeek);
        btnFri=findViewById(R.id.btn_friday_MyWeek);
        btnBack=findViewById(R.id.btn_PlanOfWeek_back);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferData = new Intent(MyWeekDaysActivity.this, HomeActivity.class);
                startActivity(transferData);
            }
        });




        btnSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyWeekDaysActivity.this,
                        MyPlanActivity.class);
                intent.putExtra(EXTRA_MY_DAY,"saturday");
                Log.i(TAG, "1: "+EXTRA_MY_DAY);
                startActivity(intent);
            }
        });
        btnSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyWeekDaysActivity.this,
                        MyPlanActivity.class);
                intent.putExtra(EXTRA_MY_DAY,"sunday");
                startActivity(intent);
            }
        });

        btnMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyWeekDaysActivity.this,
                        MyPlanActivity.class);
                intent.putExtra(EXTRA_MY_DAY,"monday");
                startActivity(intent);
            }
        });
        btnTue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyWeekDaysActivity.this,
                        MyPlanActivity.class);
                intent.putExtra(EXTRA_MY_DAY,"tuesday");
                startActivity(intent);
            }
        });
        btnWed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyWeekDaysActivity.this,
                        MyPlanActivity.class);
                intent.putExtra(EXTRA_MY_DAY,"wednesday");
                startActivity(intent);
            }
        });
        btnTh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyWeekDaysActivity.this,
                        MyPlanActivity.class);
                intent.putExtra(EXTRA_MY_DAY,"thursday");
                startActivity(intent);
            }
        });
        btnFri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyWeekDaysActivity.this,
                        MyPlanActivity.class);
                intent.putExtra(EXTRA_MY_DAY,"friday");
                startActivity(intent);
            }
        });


    }
}