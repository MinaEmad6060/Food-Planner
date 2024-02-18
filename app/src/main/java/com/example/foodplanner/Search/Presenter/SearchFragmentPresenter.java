package com.example.foodplanner.Search.Presenter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.util.Log;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealList;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.Model.Plan;
import com.example.foodplanner.Search.View.SearchViewInter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class SearchFragmentPresenter implements SearchFragmentPresenterInter{
    private static final String TAG_mealDetails = "SearchFragmentPresenter";
    private static final String TAG_TEST = "TEST";

    private static final String TAG_mealDetails_new = "New";
    private SearchViewInter searchViewInter;
    List<String> mealStringList =new ArrayList<String>();
    List<Meal> mealList =new ArrayList<Meal>();

    private static MealRepositoryInter mealRepositoryInter;

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
                            //Log.i(TAG_mealDetails, "AllmealList: "+mealList.meals.get(0).getName());
                            //Log.i(TAG_mealDetails, "AllmealList: "+mealList.meals.get(1).getName());
                            searchViewInter.showSearchMeals(mealList.meals);
                        },
                        err -> Log.i(TAG_mealDetails, "AllMealList: failure "),
                        () -> Log.i(TAG_mealDetails, "getSearchMealsPres: ")
                );
    }


    @SuppressLint("CheckResult")
    public void getDayMealsPres(String columnName) {
        Observable<String> observable =
                mealRepositoryInter.getDayMealsRepo(columnName);
        observable
                .observeOn(AndroidSchedulers.mainThread()).map(mealInfo -> {
                    List<Meal> meals = new ArrayList<Meal>();
                    String[] s = mealInfo.split(",");
                    Meal meal = new Meal("",s[0],"","","",s[1]);
                    meals.add(meal);
                    // Transform each string into another object
                    return meals;
                })
                .subscribe(
                         mealsInfo -> {
                             Log.i(TAG_mealDetails_new, "Obs: "+ mealsInfo.get(0).getName());
                             searchViewInter.showSearchMeals(mealsInfo);
//                             check(mealsInfo);
                        },
                        err -> Log.i(TAG_mealDetails_new, "ObsError: failure "),
                        () -> Log.i(TAG_mealDetails_new, "ObsComp: ")
                );


        searchViewInter.showSearchMeals(mealList);
    }


    void check(String s){
        mealStringList.add(s);
        Log.i(TAG_mealDetails, "check: "+mealStringList.size());
    }



    List<Meal> convertStringToMeal(List<String> mealsString){
        //Log.i(TAG_mealDetails, "Size of String List: "+mealDetails.size());
        for(int i = 0; i< mealStringList.size(); i++){
            String[] infoOfMeal;
            Meal meal=new Meal("","","","","","");
            infoOfMeal= mealStringList.get(i).split(",");
            meal.setName(infoOfMeal[0]);
            meal.setThumbnail(infoOfMeal[1]);
            mealList.add(meal);
        }
        //Log.i(TAG_mealDetails, "Size of Meal List: "+mealList.size());


        return mealList;
    }

    @Override
    public void addPlanMeal(Meal meal,String day) {

        String mealDetails=meal.getName()+","+meal.getThumbnail();
        //Log.i(TAG_mealDetails, "mealDetails: "+mealDetails);


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

    @Override
    public void removePlanMeal(Meal meal) {

    }
}
