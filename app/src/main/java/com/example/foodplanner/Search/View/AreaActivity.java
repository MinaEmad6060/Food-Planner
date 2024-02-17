package com.example.foodplanner.Search.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.foodplanner.HomeScreen.View.HomeActivity;
import com.example.foodplanner.Model.Area;
import com.example.foodplanner.Model.MealRepository;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Presenter.AreaActivityPresenter;
import com.example.foodplanner.Search.Presenter.AreaActivityPresenterInter;
import com.example.foodplanner.Search.View.Area.MealsOfAreaActivity;
import com.example.foodplanner.db.FavDB.FavLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class AreaActivity extends AppCompatActivity implements AreaActivityInter{
    public static final String CATEGORY_Area = "CategoryActivity";

    LinearLayoutManager linearManager;

    AreaActivityPresenterInter areaActivityPresenterInter;

    RecyclerView areasRecyclerView;

    AreaAdapter areaAdapter;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        btnBack=findViewById(R.id.btn_area_back);

        areasRecyclerView =findViewById(R.id.Area_Recycler_List);
        areaActivityPresenterInter = new AreaActivityPresenter(
                this,
                MealRepository.getInstance(
                        MealsRemoteDataSource.getInstance()
                        , FavLocalDataSource.getInstance(this)));

        areaActivityPresenterInter.getAllAreasPres();

        linearManager = new LinearLayoutManager(this);
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        areaAdapter =
                new AreaAdapter(this, new ArrayList<>());
        areasRecyclerView.setLayoutManager(linearManager);
        areasRecyclerView.setAdapter(areaAdapter);
        areaAdapter.setClickMealArea((view, position) -> {
            String mealName = areaAdapter.getItemArea(position).getAreaName();
            Intent intent = new Intent(getApplicationContext(),
                    MealsOfAreaActivity.class);
            intent.putExtra(CATEGORY_Area,mealName);
            startActivity(intent);
        });



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferData = new Intent(AreaActivity.this, HomeActivity.class);
                startActivity(transferData);
            }
        });
    }
    @Override
    public void showAreas(List<Area> areaList) {
        areaAdapter.setMyList(areaList);
        areaAdapter.notifyDataSetChanged();
    }
}