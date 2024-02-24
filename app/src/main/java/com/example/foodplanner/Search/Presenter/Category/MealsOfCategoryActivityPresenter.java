package com.example.foodplanner.Search.Presenter.Category;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.foodplanner.Model.CategoryList;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealList;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.Search.View.Category.MealsOfCategoryViewInter;
import com.example.foodplanner.Search.View.CategoryViewInter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class MealsOfCategoryActivityPresenter implements
        MealsOfCategoryActivityPresenterInter {
    private static final String TAG = "CategoryActivityPresent";
    MealRepositoryInter mealRepositoryInter;
    MealsOfCategoryViewInter mealsOfCategoryViewInter;

    public MealsOfCategoryActivityPresenter(MealsOfCategoryViewInter mealsOfCategoryViewInter,
                                            MealRepositoryInter interProductsRepository) {
        this.mealsOfCategoryViewInter = mealsOfCategoryViewInter;
        this.mealRepositoryInter = interProductsRepository;
    }


    @SuppressLint("CheckResult")
    @Override
    public void getAllMealsOfCategoriesPres(String category) {
        Observable<MealList> observable =
                mealRepositoryInter.getMealsOfCategoryRepo(category);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealList -> {
                            Log.i(TAG, "CategoriesPres: "+mealList.meals.size());
                            mealsOfCategoryViewInter.showMealsOfCategories(mealList.meals);
                        },
                        err -> {}
                );
    }

    @Override
    public void addFavMeal(Meal meal) {
        mealRepositoryInter.insertMeals(meal);
    }
}
