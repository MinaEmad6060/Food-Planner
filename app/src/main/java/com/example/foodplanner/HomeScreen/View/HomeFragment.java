package com.example.foodplanner.HomeScreen.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.DetailsOfMealActivity;
import com.example.foodplanner.HomeScreen.Presenter.HomeScreenPresenter;
import com.example.foodplanner.HomeScreen.Presenter.HomeScreenPresenterInter;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealRepository;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.R;
import com.example.foodplanner.db.FavLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.example.foodplanner.network.MealsRemoteDataSourceInter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment
            implements HomeFragmentInter{

    public static final String EXTRA_MEAL = "mealTag";
    public static final String EXTRA_POSITION = "position";
    RecyclerView chickenRecyclerView;
    RecyclerView beefRecyclerView;
    RecyclerView seaFoodRecyclerView;


    LinearLayoutManager linearManager;


    TextView randomMealName;


    View viewFrag;
    ImageView randomMealImg;

    //HomeActivity homeActivity=new HomeActivity();
    ChickenCategoryAdapter chickenCategoryAdapter;
    BeefCategoryAdapter beefCategoryAdapter;

    SeaFoodCategoryAdapter seaFoodCategoryAdapter;

    HomeScreenPresenterInter homeScreenPresenterInter;
    MealsRemoteDataSourceInter mealsRemoteDataSourceInter;
    MealRepositoryInter mealRepositoryInter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        randomMealName=view.findViewById(R.id.random_name);
        randomMealImg=view.findViewById(R.id.random_img);
        viewFrag=view;
        homeScreenPresenterInter = new HomeScreenPresenter(
                this,
                MealRepository.getInstance(
                        MealsRemoteDataSource.getInstance()
                        , FavLocalDataSource.getInstance(viewFrag.getContext())));

        //Random Meal
        homeScreenPresenterInter.getRandomMealPres();
        homeScreenPresenterInter.getMealsOfCategoryPres("Chicken");
        homeScreenPresenterInter.getMealsOfCategoryPres("Beef");
        homeScreenPresenterInter.getMealsOfCategoryPres("Seafood");



        //Chicken cat.
        chickenRecyclerView =view.findViewById(R.id.Chicken_View);
        linearManager = new LinearLayoutManager(view.getContext());
        linearManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        chickenCategoryAdapter =
                new ChickenCategoryAdapter(viewFrag.getContext(), new ArrayList<>(),this);
        chickenRecyclerView.setLayoutManager(linearManager);
        chickenRecyclerView.setAdapter(chickenCategoryAdapter);
        chickenCategoryAdapter.setClickMeal((view1, position) -> {
            TextView mealname = view.findViewById(R.id.wrapped_meal_name);
                        Intent intent = new Intent(viewFrag.getContext(),
                                DetailsOfMealActivity.class);
                        intent.putExtra(EXTRA_MEAL,mealname.getText().toString());
                        startActivity(intent);
                    });



        //Beef cat.
        beefRecyclerView =view.findViewById(R.id.Beef_view);
        linearManager = new LinearLayoutManager(view.getContext());
        linearManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        beefCategoryAdapter =
                new BeefCategoryAdapter(viewFrag.getContext(), new ArrayList<>());
        beefRecyclerView.setLayoutManager(linearManager);
        beefRecyclerView.setAdapter(beefCategoryAdapter);


        //Sea Food cat.
        seaFoodRecyclerView =view.findViewById(R.id.SeaFood_view);
        linearManager = new LinearLayoutManager(view.getContext());
        linearManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        seaFoodCategoryAdapter =
                new SeaFoodCategoryAdapter(viewFrag.getContext(), new ArrayList<>());
        seaFoodRecyclerView.setLayoutManager(linearManager);
        seaFoodRecyclerView.setAdapter(seaFoodCategoryAdapter);
    }

    @Override
    public void showChickenCategory(List<Meal> meals) {
        chickenCategoryAdapter.setMyList(meals);
        chickenCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showBeefCategory(List<Meal> meals) {
        beefCategoryAdapter.setMyList(meals);
        beefCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSeaFoodCategory(List<Meal> meals) {
        seaFoodCategoryAdapter.setMyList(meals);
        seaFoodCategoryAdapter.notifyDataSetChanged();
    }



    @Override
    public void showRandomMeal(List<Meal> meals) {
        randomMealName.setText(meals.get(0).getName());
            Glide.with(viewFrag.getContext()).load(meals.get(0).getThumbnail())
                    .into(randomMealImg);
    }

    @Override
    public void showErr(String err) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(homeActivity);
//        builder.setMessage(err).setTitle("An Error Occurred");
//        AlertDialog dialog = builder.create();
//        dialog.show();
    }

    @Override
    public void onProductClick(Meal meal) {
        homeScreenPresenterInter.addFavMeal(meal);
    }

}