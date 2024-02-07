package com.example.foodplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodplanner.R;

import android.app.Activity;
import android.os.Bundle;

import com.example.foodplanner.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {


    ActivityHomeBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activityMainBinding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        replacementFragment(new HomeFragment());


        activityMainBinding.buttomNav.setOnItemSelectedListener(item -> {

            if(item.getItemId()==R.id.Home){
                replacementFragment(new HomeFragment());
            }else if(item.getItemId()==R.id.Plan){
                replacementFragment(new PlanOfWeekFragment());
            }else if(item.getItemId()==R.id.Favourate){
                replacementFragment(new FavouriteFragment());
            }else if(item.getItemId()==R.id.Search){
                replacementFragment(new SearchFragment());
            }

            return true;
        });
    }

    private void replacementFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Frame_Layout,fragment);
    }
}