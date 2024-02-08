package com.example.foodplanner.HomeScreen.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.HomeScreen.Presenter.HomeScreenPresenter;
import com.example.foodplanner.HomeScreen.Presenter.HomeScreenPresenterInter;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealRepository;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.R;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.example.foodplanner.network.MealsRemoteDataSourceInter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment
            implements HomeFragmentInter{

    RecyclerView recyclerView;
    LinearLayoutManager linearManager;


    //HomeActivity homeActivity=new HomeActivity();
    HomeFragmentAdapter homeFragmentAdapter;

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

        homeScreenPresenterInter = new HomeScreenPresenter(
                this,
                MealRepository.getInstance(
                        MealsRemoteDataSource.getInstance()));

        homeScreenPresenterInter.getMeals();

        recyclerView=view.findViewById(R.id.Chicken_View);
        linearManager = new LinearLayoutManager(view.getContext());
        linearManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homeFragmentAdapter =
                new HomeFragmentAdapter(view.getContext(), new ArrayList<>());
        recyclerView.setLayoutManager(linearManager);
        recyclerView.setAdapter(homeFragmentAdapter);
    }

    @Override
    public void showData(List<Meal> meals) {
        homeFragmentAdapter.setMyList(meals);
        homeFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErr(String err) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(homeActivity);
//        builder.setMessage(err).setTitle("An Error Occurred");
//        AlertDialog dialog = builder.create();
//        dialog.show();
    }

}