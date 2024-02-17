package com.example.foodplanner.Search.Presenter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.Toast;

import com.example.foodplanner.HomeScreen.View.HomeActivity;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealList;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.Search.View.SearchViewInter;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class SearchFragmentPresenter implements SearchFragmentPresenterInter{
    private static final String TAG_mealDetails = "SearchFragmentPresenter";
    private SearchViewInter searchViewInter;
    private static MealRepositoryInter mealRepositoryInter;

    HomeActivity homeActivity;


    public SearchFragmentPresenter(SearchViewInter interAllProductsView,
                               MealRepositoryInter interProductsRepository) {
        this.searchViewInter = interAllProductsView;
        this.mealRepositoryInter = interProductsRepository;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getSearchMealsPres(String query) {
        Observable<MealList> observable =
                mealRepositoryInter.getSearchMealsRepo(query);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealList -> {
                            searchViewInter.showSearchMeals(mealList.meals);
                        },
                        err -> {}
                );
    }

    @Override
    public void addPlanMeal(Meal meal,String day) {

        String mealDetails=meal.getName()+","+meal.getThumbnail();
        Log.i(TAG_mealDetails, "mealDetails: "+mealDetails);


        Plan plan=new Plan();

        switch (day){
            case "saturday":
                plan.setSaturday(mealDetails);
            case "sunday":
                plan.setSunday(mealDetails);
            case "monday":
                plan.setMonday(mealDetails);
            case "tuesday":
                plan.setTuesday(mealDetails);
            case "wednesday":
                plan.setWednesday(mealDetails);
            case "thursday":
                plan.setThursday(mealDetails);
            case "friday":
                plan.setFriday(mealDetails);
//            default:
//                Toast.makeText(, "", Toast.LENGTH_SHORT).show();
        }

        mealRepositoryInter.insertDayMeal(plan);
    }
}
