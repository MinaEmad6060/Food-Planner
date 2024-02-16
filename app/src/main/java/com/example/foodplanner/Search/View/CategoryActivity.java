package com.example.foodplanner.Search.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.foodplanner.HomeScreen.View.HomeActivity;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.MealRepository;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Presenter.CategoryActivityPresenter;
import com.example.foodplanner.Search.Presenter.CategoryActivityPresenterInter;
import com.example.foodplanner.Search.View.Category.MealsOfCategoryActivity;
import com.example.foodplanner.db.FavLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements CategoryViewInter{

    public static final String CATEGORY = "CategoryActivity";
    LinearLayoutManager linearManager;

    CategoryActivityPresenterInter categoryActivityPresenterInter;
    RecyclerView categoriesRecyclerView;

    CategoryAdapter categoryAdapter;
    ImageView btnBack;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        btnBack=findViewById(R.id.btn_category_back);
        categoriesRecyclerView=findViewById(R.id.Cat_Recycler_List);
        categoryActivityPresenterInter = new CategoryActivityPresenter(
                this,
                MealRepository.getInstance(
                        MealsRemoteDataSource.getInstance()
                        , FavLocalDataSource.getInstance(this)));

        categoryActivityPresenterInter.getAllCategoriesPres();

        linearManager = new LinearLayoutManager(this);
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryAdapter =
                new CategoryAdapter(this, new ArrayList<>());
        categoriesRecyclerView.setLayoutManager(linearManager);
        categoriesRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.setClickMeal((view, position) -> {
            String mealName = categoryAdapter.getItemCategory(position).getName();
            Intent intent = new Intent(getApplicationContext(),
                    MealsOfCategoryActivity.class);
            intent.putExtra(CATEGORY,mealName);
            startActivity(intent);
        });




        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferData = new Intent(CategoryActivity.this, HomeActivity.class);
                startActivity(transferData);
            }
        });

    }

    public void showCategories(List<Category> categories) {
        categoryAdapter.setMyList(categories);
        categoryAdapter.notifyDataSetChanged();
    }
}