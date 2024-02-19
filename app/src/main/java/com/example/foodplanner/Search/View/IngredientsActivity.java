package com.example.foodplanner.Search.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.foodplanner.HomeScreen.View.HomeActivity;
import com.example.foodplanner.Model.Ingredient;
import com.example.foodplanner.Model.MealRepository;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Presenter.IngredientsActivityPresenter;
import com.example.foodplanner.Search.View.Ingredients.MealsOfIngredientActivity;
import com.example.foodplanner.db.FavDB.FavLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class IngredientsActivity extends AppCompatActivity
        implements IngredientActivityInter{
    public static final String CATEGORY_Ingredients = "CategoryActivity";
    LinearLayoutManager linearManager;

    IngredientsActivityPresenter ingredientsActivityPresenter;

    RecyclerView ingredientsRecyclerView;

    IngredientsAdapter ingredientsAdapter;
    ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        btnBack=findViewById(R.id.btn_ingredients_back);


        ingredientsRecyclerView =findViewById(R.id.Ingredients_Recycler_List);
        ingredientsActivityPresenter = new IngredientsActivityPresenter(
                this,
                MealRepository.getFavInstance(
                        MealsRemoteDataSource.getInstance()
                        , FavLocalDataSource.getInstance(this)));

        ingredientsActivityPresenter.getAllIngredientsPres();

        linearManager = new LinearLayoutManager(this);
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        ingredientsAdapter =
                new IngredientsAdapter(this, new ArrayList<>());
        ingredientsRecyclerView.setLayoutManager(linearManager);
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);
        ingredientsAdapter.setClickMealIngredints((view, position) -> {
            String mealName = ingredientsAdapter.getItemIngredient(position).getIngredientName();
            Intent intent = new Intent(getApplicationContext(),
                    MealsOfIngredientActivity.class);
            intent.putExtra(CATEGORY_Ingredients,mealName);
            startActivity(intent);
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferData = new Intent(IngredientsActivity.this, HomeActivity.class);
                startActivity(transferData);
            }
        });
    }

    @Override
    public void showIngredients(List<Ingredient> ingredientList) {
        ingredientsAdapter.setMyList(ingredientList);
        ingredientsAdapter.notifyDataSetChanged();
    }
}