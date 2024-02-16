package com.example.foodplanner.Search.View.Area;

import static com.example.foodplanner.HomeScreen.View.HomeFragment.EXTRA_MEAL;
import static com.example.foodplanner.Search.View.AreaActivity.CATEGORY_Area;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.HomeScreen.View.HomeActivity;
import com.example.foodplanner.HomeScreen.View.HomeCategoryAdapter;
import com.example.foodplanner.HomeScreen.View.HomeFragment;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealRepository;
import com.example.foodplanner.Plans.View.DetailsOfMealActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Presenter.Area.MealsOfAreaActivityPresenter;
import com.example.foodplanner.Search.Presenter.Area.MealsOfAreaActivityPresenterInter;
import com.example.foodplanner.Search.Presenter.Category.MealsOfCategoryActivityPresenterInter;
import com.example.foodplanner.Search.Presenter.CategoryActivityPresenter;
import com.example.foodplanner.Search.Presenter.CategoryActivityPresenterInter;
import com.example.foodplanner.Search.View.CategoryAdapter;
import com.example.foodplanner.Search.View.CategoryViewInter;
import com.example.foodplanner.db.FavLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class MealsOfAreaActivity extends AppCompatActivity
        implements MealsOfAreaViewInter {

    private static final String TAG = "MealsOfAreaActivity";
    LinearLayoutManager linearManager;

    MealsOfAreaActivityPresenterInter mealsOfAreaActivityPresenterInter;
    RecyclerView mealsOfCategoriesRecyclerView;

    HomeCategoryAdapter homeCategoryAdapter;
    ImageView btnBack;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_of_area);
        btnBack=findViewById(R.id.btn_Meals_Of_area_back);
        mealsOfCategoriesRecyclerView=findViewById(R.id.Meals_Of_Area_Recycler_List);


        Intent listenMessage = getIntent();
        String message = listenMessage.getStringExtra(CATEGORY_Area);
        Log.i(TAG, "message: "+message);

        mealsOfAreaActivityPresenterInter = new MealsOfAreaActivityPresenter(
                this,
                MealRepository.getInstance(
                        MealsRemoteDataSource.getInstance()
                        , FavLocalDataSource.getInstance(this)));

        mealsOfAreaActivityPresenterInter.getAllMealsOfAreaPres(message);

        linearManager = new LinearLayoutManager(this);
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeCategoryAdapter =
                new HomeCategoryAdapter(this, new ArrayList<>(), new HomeFragment());
        mealsOfCategoriesRecyclerView.setLayoutManager(linearManager);
        mealsOfCategoriesRecyclerView.setAdapter(homeCategoryAdapter);
        homeCategoryAdapter.setClickMeal((view1, position) -> {
            String mealName = homeCategoryAdapter.getItem(position).getName();
            Intent intent = new Intent(this,
                    DetailsOfMealActivity.class);
            intent.putExtra(EXTRA_MEAL,mealName);
            startActivity(intent);
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferData = new Intent(MealsOfAreaActivity.this, HomeActivity.class);
                startActivity(transferData);
            }
        });


    }

    @Override
    public void showMealsOfArea(List<Meal> meals) {
        homeCategoryAdapter.setMyList(meals);
        homeCategoryAdapter.notifyDataSetChanged();
    }
}