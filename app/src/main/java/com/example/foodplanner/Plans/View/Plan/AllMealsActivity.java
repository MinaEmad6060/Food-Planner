package com.example.foodplanner.Plans.View.Plan;

import static com.example.foodplanner.HomeScreen.View.HomeFragment.EXTRA_MEAL;
import static com.example.foodplanner.Plans.View.Plan.WeekDaysActivity.EXTRA_DAY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.foodplanner.HomeScreen.View.HomeCategoryAdapter;
import com.example.foodplanner.HomeScreen.View.HomeFragment;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Plans.Presenter.Plan.AllMealsPresInter;
import com.example.foodplanner.Plans.View.DetailsOfMealActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Presenter.SearchFragmentPresenterInter;

import java.util.ArrayList;

public class AllMealsActivity extends AppCompatActivity
            implements OnAddPlanMealListener{


    private static final String TAG = "AllMealsActivity";

    RecyclerView MealsRecyclerView;


    SearchFragmentPresenterInter searchFragmentPresenterInter;
    HomeCategoryAdapter homeAdapter;

    LinearLayoutManager linearManagerSearch;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_meals);

        Intent listenMessage = getIntent();
        message = listenMessage.getStringExtra(EXTRA_DAY);
        Log.i(TAG, "message: "+message);

        MealsRecyclerView =findViewById(R.id.allMeals_Recycler_List);
        linearManagerSearch = new LinearLayoutManager(AllMealsActivity.this);
        linearManagerSearch.setOrientation(LinearLayoutManager.VERTICAL);
        homeAdapter = new HomeCategoryAdapter(AllMealsActivity.this, new ArrayList<>()
                ,new HomeFragment()
                ,this);
        MealsRecyclerView.setLayoutManager(linearManagerSearch);
        MealsRecyclerView.setAdapter(homeAdapter);
        homeAdapter.setClickMeal((view1, position) -> {
            String mealName = homeAdapter.getItem(position).getName();
            Intent intent = new Intent(AllMealsActivity.this,
                    DetailsOfMealActivity.class);
            intent.putExtra(EXTRA_MEAL,mealName);
            startActivity(intent);
        });


        searchFragmentPresenterInter.getSearchMealsPres("");

    }

    @Override
    public void onPlanMealClick(Meal meal,String day) {
        day=message;
        searchFragmentPresenterInter.addPlanMeal(meal,day);
    }
}