package com.example.foodplanner.ConnectOnline.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodplanner.HomeScreen.View.HomeActivity;
import com.example.foodplanner.R;

public class StartActivity extends AppCompatActivity {


    Button btnGuest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnGuest=findViewById(R.id.btn_guest);


        btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(StartActivity.this, HomeActivity.class);
                startActivity(nextPage);
            }
        });
    }
}