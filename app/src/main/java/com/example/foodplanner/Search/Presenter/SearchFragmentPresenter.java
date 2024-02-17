package com.example.foodplanner.Search.Presenter;

import android.annotation.SuppressLint;
import android.content.Context;
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
    Context context;

    public SearchFragmentPresenter(SearchViewInter interAllMealsView,
                               MealRepositoryInter interMealsRepository) {
        this.searchViewInter = interAllMealsView;
        this.mealRepositoryInter = interMealsRepository;
    }


    @SuppressLint("CheckResult")
    @Override
    public void getSearchMealsPres(String query) {
        Observable<MealList> observable =
                mealRepositoryInter.getSearchMealsRepo(query);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealList -> {
                            Log.i(TAG_mealDetails, "AllmealList: "+
                                    mealList.meals.get(0).getName());
                            Log.i(TAG_mealDetails, "AllmealList: "+
                                    mealList.meals.get(1).getName());
                            searchViewInter.showSearchMeals(mealList.meals);
                        },
                        err -> Log.i(TAG_mealDetails, "AllMealList: failure ")
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
                break;
            case "sunday":
                plan.setSunday(mealDetails);
                break;
            case "monday":
                plan.setMonday(mealDetails);
                break;
            case "tuesday":
                plan.setTuesday(mealDetails);
                break;
            case "wednesday":
                plan.setWednesday(mealDetails);
                break;
            case "thursday":
                plan.setThursday(mealDetails);
                break;
            case "friday":
                plan.setFriday(mealDetails);
                break;
        }
        mealRepositoryInter.insertDayMeal(plan);
    }
}
