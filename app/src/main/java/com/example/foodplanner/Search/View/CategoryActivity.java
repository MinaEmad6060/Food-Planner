package com.example.foodplanner.Search.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.foodplanner.HomeScreen.View.HomeActivity;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.MealRepository;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Presenter.CategoryFragmentPresenter;
import com.example.foodplanner.Search.Presenter.SearchFragmentPresenter;
import com.example.foodplanner.Search.Presenter.SearchFragmentPresenterInter;
import com.example.foodplanner.db.FavLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements CategoryViewInter{
    LinearLayoutManager linearManager;

    CategoryFragmentPresenter categoryFragmentPresenter;
    RecyclerView categoriesRecyclerView;

    CategoriesAdapter categoriesAdapter;
    ImageView btnBack;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        btnBack=findViewById(R.id.btn_category_back);
        categoriesRecyclerView=findViewById(R.id.Cat_Recycler_List);
        categoryFragmentPresenter = new CategoryFragmentPresenter(
                this,
                MealRepository.getInstance(
                        MealsRemoteDataSource.getInstance()
                        , FavLocalDataSource.getInstance(this)));

        categoryFragmentPresenter.getAllCategoriesPres();

        linearManager = new LinearLayoutManager(this);
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoriesAdapter =
                new CategoriesAdapter(this, new ArrayList<>());
        categoriesRecyclerView.setLayoutManager(linearManager);
        categoriesRecyclerView.setAdapter(categoriesAdapter);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferData = new Intent(CategoryActivity.this, HomeActivity.class);
                startActivity(transferData);
            }
        });


    }

    public void showCategories(List<Category> categories) {
        categoriesAdapter.setMyList(categories);
        categoriesAdapter.notifyDataSetChanged();
    }
}