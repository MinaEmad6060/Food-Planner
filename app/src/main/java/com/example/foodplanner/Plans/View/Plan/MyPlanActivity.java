package com.example.foodplanner.Plans.View.Plan;

import static com.example.foodplanner.HomeScreen.View.HomeFragment.EXTRA_MEAL;
import static com.example.foodplanner.Plans.View.Plan.MyWeekDaysActivity.EXTRA_MY_DAY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
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

public class MyPlanActivity extends AppCompatActivity
        implements OnRemovePlanMealListener, SearchViewInter {

    private static final String TAG = "AllMealsActivity";

    RecyclerView MealsRecyclerView;


    SearchFragmentPresenterInter searchFragmentPresenterInter;
    HomeCategoryAdapter homeAdapter;

    LinearLayoutManager linearManagerSearch;
    String message;

    TextView dayPlan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan);
        dayPlan=findViewById(R.id.myPlanText);

        Intent listenMessage = getIntent();
        message = listenMessage.getStringExtra(EXTRA_MY_DAY);
        Log.i(TAG, "message: "+message);

        dayPlan.setText(message+" "+"plan");



        searchFragmentPresenterInter = new SearchFragmentPresenter(
                this,
                MealRepository.getPlanInstance(
                        MealsRemoteDataSource.getInstance()
                        , PlanLocalDataSource.getPlanInstance(MyPlanActivity.this)));



        MealsRecyclerView =findViewById(R.id.myPlan_Recycler_List);
        linearManagerSearch = new LinearLayoutManager(MyPlanActivity.this);
        linearManagerSearch.setOrientation(LinearLayoutManager.VERTICAL);
        homeAdapter = new HomeCategoryAdapter(MyPlanActivity.this, new ArrayList<>()
                ,new HomeFragment()
                ,this);
        MealsRecyclerView.setLayoutManager(linearManagerSearch);
        MealsRecyclerView.setAdapter(homeAdapter);
        homeAdapter.setClickMeal((view1, position) -> {
            String mealName = homeAdapter.getItem(position).getName();
            Intent intent = new Intent(MyPlanActivity.this,
                    DetailsOfMealActivity.class);
            intent.putExtra(EXTRA_MEAL,mealName);
            startActivity(intent);
        });

        searchFragmentPresenterInter.getDayMealsPres(message);

    }


    @Override
    public void showSearchMeals(List<Meal> meals) {
        homeAdapter.setMyList(meals);
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRemovePlanMealClick(Meal meal) {
        Toast.makeText(MyPlanActivity.this, "Remove from Plan!", Toast.LENGTH_SHORT).show();
        String mealDetails = meal.getName() + "," + meal.getThumbnail();
        searchFragmentPresenterInter.removePlanMeal(mealDetails,message);
        searchFragmentPresenterInter.getDayMealsPres(message);
    }
}