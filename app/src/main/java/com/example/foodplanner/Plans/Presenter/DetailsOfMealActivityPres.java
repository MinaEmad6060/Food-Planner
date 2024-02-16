package com.example.foodplanner.Plans.Presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.foodplanner.Model.CategoryList;
import com.example.foodplanner.Model.MealList;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.Plans.View.DetailsOfMealViewInter;
import com.example.foodplanner.Search.View.CategoryViewInter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class DetailsOfMealActivityPres implements DetailsOfMealActivityPresInter{

    private static final String TAG = "DetailsOfMealActivityPr";
    MealRepositoryInter mealRepositoryInter;
    DetailsOfMealViewInter detailsOfMealViewInter;

    public DetailsOfMealActivityPres(DetailsOfMealViewInter detailsOfMealViewInter,
                                     MealRepositoryInter interProductsRepository) {
        this.detailsOfMealViewInter = detailsOfMealViewInter;
        this.mealRepositoryInter = interProductsRepository;
    }


    @SuppressLint("CheckResult")
    @Override
    public void getAllMealDetailsPres(String query) {
        Observable<MealList> observable =
                mealRepositoryInter.getSearchMealsRepo(query);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealList -> {
                            Log.i(TAG, "CategoriesPres: "+mealList.meals.size());
                            detailsOfMealViewInter.showMealDetails(mealList.meals);
                        },
                        err -> {}
                );
    }
}
