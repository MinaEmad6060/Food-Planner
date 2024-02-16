package com.example.foodplanner.Search.Presenter.Ingredients;

import android.annotation.SuppressLint;

import com.example.foodplanner.Model.IngredientsList;
import com.example.foodplanner.Model.MealList;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.Search.View.IngredientActivityInter;
import com.example.foodplanner.Search.View.Ingredients.MealsOfIngredientsViewInter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class MealsOfIngredientsActivityPresenter implements MealsOfIngredientsActivityPresenterInter {

    MealRepositoryInter mealRepositoryInter;
    MealsOfIngredientsViewInter mealsOfIngredientsViewInter;

    public MealsOfIngredientsActivityPresenter(MealsOfIngredientsViewInter mealsOfIngredientsViewInter,
                                               MealRepositoryInter interProductsRepository) {
        this.mealsOfIngredientsViewInter = mealsOfIngredientsViewInter;
        this.mealRepositoryInter = interProductsRepository;
    }
    @SuppressLint("CheckResult")
    @Override
    public void getAllMealsOfIngredientsPres(String category) {
        Observable<MealList> observable =
                mealRepositoryInter.getAllMealsIngredientRepo(category);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealList -> {
                            mealsOfIngredientsViewInter.showMealsOfIngredients(mealList.meals);
                        },
                        err -> {}
                );
    }
}
