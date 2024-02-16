package com.example.foodplanner.Search.Presenter.Area;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.foodplanner.Model.AreasList;
import com.example.foodplanner.Model.MealList;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.Search.View.Area.MealsOfAreaViewInter;
import com.example.foodplanner.Search.View.AreaActivityInter;
import com.example.foodplanner.Search.View.Category.MealsOfCategoryViewInter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class MealsOfAreaActivityPresenter implements MealsOfAreaActivityPresenterInter {

    private static final String TAG = "MealsOFAreaActivityPresenter";
    MealRepositoryInter mealRepositoryInter;
    MealsOfAreaViewInter mealsOfAreaViewInter;

    public MealsOfAreaActivityPresenter(MealsOfAreaViewInter mealsOfAreaViewInter,
                                        MealRepositoryInter interProductsRepository) {
        this.mealsOfAreaViewInter = mealsOfAreaViewInter;
        this.mealRepositoryInter = interProductsRepository;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAllMealsOfAreaPres(String category) {
        Observable<MealList> observable =
                mealRepositoryInter.getAllMealsOfAreasRepo(category);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealList -> {
                            Log.i(TAG, "AreasPres: "+mealList.meals.size());
                            mealsOfAreaViewInter.showMealsOfArea(mealList.meals);
                        },
                        err -> Log.i(TAG, "AreasPres: error")
                );
    }
}
