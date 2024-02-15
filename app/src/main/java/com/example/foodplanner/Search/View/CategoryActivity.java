package com.example.foodplanner.Search.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.foodplanner.HomeScreen.View.HomeActivity;
import com.example.foodplanner.R;

public class CategoryActivity extends AppCompatActivity {

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        btnBack=findViewById(R.id.btn_category_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferData = new Intent(CategoryActivity.this, HomeActivity.class);
                startActivity(transferData);
            }
        });


    }
}