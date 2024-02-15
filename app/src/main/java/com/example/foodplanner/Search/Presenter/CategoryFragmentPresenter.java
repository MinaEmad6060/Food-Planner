package com.example.foodplanner.Search.Presenter;

import android.annotation.SuppressLint;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.CategoryList;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.Search.View.CategoryViewInter;
import com.example.foodplanner.Search.View.SearchViewInter;


import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class CategoryFragmentPresenter implements CategoryFragmentPresenterInter{

    MealRepositoryInter mealRepositoryInter;
    CategoryViewInter categoryViewInter;

    public CategoryFragmentPresenter(CategoryViewInter categoryViewInter,
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
                            categoryViewInter.showCategories(categoryList.categories);
                        },
                        err -> {}
                );
    }
}
