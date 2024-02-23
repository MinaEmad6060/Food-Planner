package com.example.foodplanner.Search.View.Ingredients;

import static com.example.foodplanner.HomeScreen.View.HomeFragment.EXTRA_MEAL;
import static com.example.foodplanner.Search.View.IngredientsActivity.CATEGORY_Ingredients;

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
import com.example.foodplanner.HomeScreen.View.OnAddMealListener;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealRepository;
import com.example.foodplanner.Plans.View.DetailsOfMealActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Presenter.Ingredients.MealsOfIngredientsActivityPresenter;
import com.example.foodplanner.Search.Presenter.Ingredients.MealsOfIngredientsActivityPresenterInter;
import com.example.foodplanner.Search.View.IngredientsActivity;
import com.example.foodplanner.db.FavDB.FavLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class MealsOfIngredientActivity extends AppCompatActivity
        implements MealsOfIngredientsViewInter, OnAddMealListener {
    private static final String TAG = "MealsOfIngredientsActiv";
    LinearLayoutManager linearManager;

    MealsOfIngredientsActivityPresenterInter mealsOfIngredientsActivityPresenterInter;
    RecyclerView mealsOfIngredientsRecyclerView;

    HomeCategoryAdapter homeCategoryAdapter;
    ImageView btnBack;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_of_ingredients);
        btnBack=findViewById(R.id.btn_Meals_Of_ingredients_back);
        mealsOfIngredientsRecyclerView=findViewById(R.id.Meals_Of_Ingredient_Recycler_List);


        Intent listenMessage = getIntent();
        String message = listenMessage.getStringExtra(CATEGORY_Ingredients);
        Log.i(TAG, "message: "+message);

        mealsOfIngredientsActivityPresenterInter =
                new MealsOfIngredientsActivityPresenter(
                        this,
                        MealRepository.getFavInstance(
                                MealsRemoteDataSource.getInstance()
                                , FavLocalDataSource.getInstance(this)));

        mealsOfIngredientsActivityPresenterInter.getAllMealsOfIngredientsPres(message);

        linearManager = new LinearLayoutManager(this);
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeCategoryAdapter =
                new HomeCategoryAdapter(this, new ArrayList<>(), new HomeFragment(),
                        this);
        mealsOfIngredientsRecyclerView.setLayoutManager(linearManager);
        mealsOfIngredientsRecyclerView.setAdapter(homeCategoryAdapter);
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
                Intent transferData = new Intent(MealsOfIngredientActivity.this, IngredientsActivity.class);
                startActivity(transferData);
            }
        });


    }


    @Override
    public void showMealsOfIngredients(List<Meal> meals) {
        homeCategoryAdapter.setMyList(meals);
        homeCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMealClick(Meal meal) {

    }
}