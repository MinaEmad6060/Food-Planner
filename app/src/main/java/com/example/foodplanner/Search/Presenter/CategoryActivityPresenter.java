package com.example.foodplanner.Search.Presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.foodplanner.Model.CategoryList;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.Search.View.CategoryViewInter;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class CategoryActivityPresenter implements CategoryActivityPresenterInter {
    private static final String TAG = "CategoryActivityPresent";
    MealRepositoryInter mealRepositoryInter;
    CategoryViewInter categoryViewInter;

    public CategoryActivityPresenter(CategoryViewInter categoryViewInter,
                                     MealRepositoryInter interProductsRepository) {
        this.categoryViewInter = categoryViewInter;
        this.mealRepositoryInter = interProductsRepository;
    }


    @SuppressLint("CheckResult")
    @Override
    public void getAllCategoriesPres() {
        Observable<CategoryList> observable =
                mealRepositoryInter.getAllCategoriesRepo();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        categoryList -> {
                            Log.i(TAG, "CategoriesPres: "+categoryList.categories.size());
                            categoryViewInter.showCategories(categoryList.categories);
                        },
                        err -> {}
                );
    }
}
