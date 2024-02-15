package com.example.foodplanner.Search.Presenter;

import com.example.foodplanner.HomeScreen.View.HomeFragmentInter;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.Search.View.CategoryViewInter;
import com.example.foodplanner.Search.View.SearchFragment;
import com.example.foodplanner.Search.View.SearchViewInter;
import com.example.foodplanner.network.CallBackInter;

import java.util.List;

public class SearchFragmentPresenter implements SearchFragmentPresenterInter, CallBackInter {

    private SearchViewInter searchViewInter;

    private static MealRepositoryInter mealRepositoryInter;


    public SearchFragmentPresenter(SearchViewInter interAllProductsView,
                               MealRepositoryInter interProductsRepository) {
        this.searchViewInter = interAllProductsView;
        this.mealRepositoryInter = interProductsRepository;
    }


    @Override
    public void onSuccessSearch(List<Meal> meals) {
        searchViewInter.showSearchMeals(meals);
    }


    @Override
    public void getAllCategoriesPres() {
        mealRepositoryInter.getAllCategoriesRepo(this);
    }

    @Override
    public void getSearchMealsPres() {
        mealRepositoryInter.getSearchMealsRepo(this);
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

    }

    @Override
    public void onSuccessRandom(List<Meal> meals) {

    }

    @Override
    public void onFail(String err) {

    }
}
