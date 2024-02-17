package com.example.foodplanner.Search.View.Category;

import static com.example.foodplanner.HomeScreen.View.HomeFragment.EXTRA_MEAL;
import static com.example.foodplanner.Search.View.CategoryActivity.CATEGORY;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.HomeScreen.View.HomeCategoryAdapter;
import com.example.foodplanner.HomeScreen.View.HomeFragment;
import com.example.foodplanner.HomeScreen.View.OnAddMealListener;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealRepository;
import com.example.foodplanner.Plans.View.DetailsOfMealActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Presenter.Category.MealsOfCategoryActivityPresenter;
import com.example.foodplanner.Search.Presenter.Category.MealsOfCategoryActivityPresenterInter;
import com.example.foodplanner.Search.View.CategoryActivity;
import com.example.foodplanner.db.FavDB.FavLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class MealsOfCategoryActivity extends AppCompatActivity
        implements MealsOfCategoryViewInter, OnAddMealListener {

    private static final String TAG = "MealsOfCategoryActivity";
    LinearLayoutManager linearManager;

    MealsOfCategoryActivityPresenterInter mealsOfCategoryActivityPresenterInter;
    RecyclerView mealsOfCategoriesRecyclerView;

    HomeCategoryAdapter homeCategoryAdapter;
    ImageView btnBack;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_of_categories);
        btnBack=findViewById(R.id.btn_Meals_Of_category_back);
        mealsOfCategoriesRecyclerView =findViewById(R.id.Meals_Of_Category_Recycler_List);

        Intent listenMessage = getIntent();
        String message = listenMessage.getStringExtra(CATEGORY);
        Log.i(TAG, "message: "+message);

        mealsOfCategoryActivityPresenterInter = new MealsOfCategoryActivityPresenter(
                this,
                MealRepository.getInstance(
                        MealsRemoteDataSource.getInstance()
                        , FavLocalDataSource.getInstance(this)));

        mealsOfCategoryActivityPresenterInter.getAllMealsOfCategoriesPres(message);


        linearManager = new LinearLayoutManager(this);
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeCategoryAdapter =
                new HomeCategoryAdapter(this, new ArrayList<>()
                        ,new HomeFragment()
                ,this);
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
                Intent transferData = new Intent(MealsOfCategoryActivity.this, CategoryActivity.class);
                startActivity(transferData);
            }
        });


    }

    public void showMealsOfCategories(List<Meal> meals) {
        homeCategoryAdapter.setMyList(meals);
        homeCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMealClick(Meal meal) {

    }
}