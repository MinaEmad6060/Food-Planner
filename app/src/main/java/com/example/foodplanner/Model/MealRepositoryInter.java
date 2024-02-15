package com.example.foodplanner.Model;

import com.example.foodplanner.Search.Presenter.SearchFragmentPresenter;
import com.example.foodplanner.network.CallBackInter;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface MealRepositoryInter {
    void getAllMeals(CallBackInter interCallBack);

    void getRandomMeal(CallBackInter interCallBack);

    Flowable<List<Meal>> getStoredProducts();

    void insertProduct(Meal meal);

    public void deleteProduct(Meal meal);

    void  getAllCategoriesRepo(CallBackInter interCallBack);

    void getSearchMealsRepo(CallBackInter interCallBack);
}
