package com.example.foodplanner.Search.Presenter;

import android.annotation.SuppressLint;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealList;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.Search.View.SearchViewInter;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class SearchFragmentPresenter implements SearchFragmentPresenterInter{

    private SearchViewInter searchViewInter;

    private static MealRepositoryInter mealRepositoryInter;


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
}
