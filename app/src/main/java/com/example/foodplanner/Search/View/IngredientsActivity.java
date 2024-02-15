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
import com.example.foodplanner.Search.Presenter.AreaActivityPresenter;
import com.example.foodplanner.Search.Presenter.AreaActivityPresenterInter;
import com.example.foodplanner.Search.Presenter.IngredientsActivityPresenter;
import com.example.foodplanner.db.FavLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class IngredientsActivity extends AppCompatActivity implements IngredientActivityInter{
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
                MealRepository.getInstance(
                        MealsRemoteDataSource.getInstance()
                        , FavLocalDataSource.getInstance(this)));

        ingredientsActivityPresenter.getAllIngredientsPres();

        linearManager = new LinearLayoutManager(this);
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        ingredientsAdapter =
                new IngredientsAdapter(this, new ArrayList<>());
        ingredientsRecyclerView.setLayoutManager(linearManager);
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);


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