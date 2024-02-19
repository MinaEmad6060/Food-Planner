package com.example.foodplanner.Plans.View.Plan;

import static com.example.foodplanner.HomeScreen.View.HomeFragment.EXTRA_MEAL;
import static com.example.foodplanner.Plans.View.Plan.WeekDaysActivity.EXTRA_DAY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.foodplanner.HomeScreen.View.HomeCategoryAdapter;
import com.example.foodplanner.HomeScreen.View.HomeFragment;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealRepository;
import com.example.foodplanner.Plans.View.DetailsOfMealActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Presenter.SearchFragmentPresenter;
import com.example.foodplanner.Search.Presenter.SearchFragmentPresenterInter;
import com.example.foodplanner.Search.View.SearchViewInter;
import com.example.foodplanner.db.PlanDB.PlanLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class AllMealsActivity extends AppCompatActivity
            implements OnAddPlanMealListener, SearchViewInter {


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

        searchFragmentPresenterInter = new SearchFragmentPresenter(
                this,
                MealRepository.getPlanInstance(
                        MealsRemoteDataSource.getInstance()
                        , PlanLocalDataSource.getPlanInstance(AllMealsActivity.this)));


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
        Toast.makeText(AllMealsActivity.this, "Added to Plan!", Toast.LENGTH_SHORT).show();
        day=message;
        searchFragmentPresenterInter.addPlanMeal(meal,day);
    }

    @Override
    public void showSearchMeals(List<Meal> searchMeals) {
        homeAdapter.setMyList(searchMeals);
        homeAdapter.notifyDataSetChanged();
    }
}