package com.example.foodplanner.Plans.View.Plan;

import static com.example.foodplanner.Online.LoginFragment.SHARED_PREF;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodplanner.HomeScreen.View.HomeActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.View.IngredientsActivity;

public class PlanOfWeekFragment extends Fragment {


    Button btnNewPlan;
    Button btnMyPlan;

    HomeActivity homeActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeActivity=(HomeActivity)getActivity();
        SharedPreferences sharedPreferences =
                homeActivity.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("name","");
        if(userName.equals("")){
            Toast.makeText(homeActivity, "Login to make a Plan",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(homeActivity.getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan_of_week, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnNewPlan=view.findViewById(R.id.btn_new_plan);
        btnMyPlan=view.findViewById(R.id.btn_my_plan);


        btnNewPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferData = new Intent(view.getContext(),
                        WeekDaysActivity.class);
                startActivity(transferData);
            }
        });

        btnMyPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferData = new Intent(view.getContext(),
                        MyWeekDaysActivity.class);
                startActivity(transferData);
            }
        });

    }
}