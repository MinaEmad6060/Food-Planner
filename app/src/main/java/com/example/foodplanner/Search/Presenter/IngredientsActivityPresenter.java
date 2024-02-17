package com.example.foodplanner.Search.Presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.foodplanner.Model.AreasList;
import com.example.foodplanner.Model.Ingredient;
import com.example.foodplanner.Model.IngredientsList;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.Search.View.AreaActivityInter;
import com.example.foodplanner.Search.View.IngredientActivityInter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class IngredientsActivityPresenter implements IngredientsActivityPresenterInter{

    MealRepositoryInter mealRepositoryInter;
    IngredientActivityInter ingredientActivityInter;

    public IngredientsActivityPresenter(IngredientActivityInter ingredientActivityInter,
                                 MealRepositoryInter interProductsRepository) {
        this.ingredientActivityInter = ingredientActivityInter;
        this.mealRepositoryInter = interProductsRepository;
    }
    @SuppressLint("CheckResult")
    @Override
    public void getAllIngredientsPres() {
        Observable<IngredientsList> observable =
                mealRepositoryInter.getAllIngredientRepo();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ingredientsList -> {
                            ingredientActivityInter.showIngredients(ingredientsList.meals);
                        },
                        err -> {}
                );
    }

}
