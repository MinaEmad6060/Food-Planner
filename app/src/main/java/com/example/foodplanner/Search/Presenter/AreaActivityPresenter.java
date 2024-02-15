package com.example.foodplanner.Search.Presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.foodplanner.Model.AreasList;
import com.example.foodplanner.Model.CategoryList;
import com.example.foodplanner.Model.MealList;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.Search.View.AreaActivityInter;
import com.example.foodplanner.Search.View.CategoryViewInter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class AreaActivityPresenter implements AreaActivityPresenterInter{

    private static final String TAG = "AreaActivityPresenter";
    MealRepositoryInter mealRepositoryInter;
    AreaActivityInter areaActivityInter;

    public AreaActivityPresenter(AreaActivityInter areaActivityInter,
                                     MealRepositoryInter interProductsRepository) {
        this.areaActivityInter = areaActivityInter;
        this.mealRepositoryInter = interProductsRepository;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getAllAreasPres() {
        Observable<AreasList> observable =
                mealRepositoryInter.getAllAreasRepo();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        areasList -> {
                            Log.i(TAG, "AreasPres: "+areasList.meals.size());
                            areaActivityInter.showAreas(areasList.meals);
                        },
                        err -> Log.i(TAG, "AreasPres: error")
                );
    }
}
