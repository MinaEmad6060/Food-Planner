package com.example.foodplanner.Search.Presenter;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.Search.View.CategoryActivity;
import com.example.foodplanner.Search.View.CategoryViewInter;
import com.example.foodplanner.Search.View.SearchViewInter;
import com.example.foodplanner.network.CallBackInter;


import java.util.List;

public class CategoryFragmentPresenter implements CategoryFragmentPresenterInter, CallBackInter {


    SearchViewInter searchViewInter;

    MealRepositoryInter mealRepositoryInter;
    CategoryViewInter categoryViewInter;

    public CategoryFragmentPresenter(CategoryViewInter categoryViewInter,
                                   MealRepositoryInter interProductsRepository) {
        this.categoryViewInter = categoryViewInter;
        this.mealRepositoryInter = interProductsRepository;
    }


    @Override
    public void onSuccessChicken(List<Meal> meals) {

    }

    @Override
    public void onSuccessBeef(List<Meal> meals) {

    }

    @Override
    public void onSuccessSeaFood(List<Meal> meals) {

    }

    @Override
    public void onSuccessCategory(List<Category> categories) {
        categoryViewInter.showCategories(categories);
    }

    @Override
    public void onSuccessSearch(List<Meal> meals) {

    }

    @Override
    public void onSuccessRandom(List<Meal> meals) {

    }

    @Override
    public void onFail(String err) {

    }

    @Override
    public void getAllCategoriesPres() {
        mealRepositoryInter.getAllCategoriesRepo(this);
    }
}
