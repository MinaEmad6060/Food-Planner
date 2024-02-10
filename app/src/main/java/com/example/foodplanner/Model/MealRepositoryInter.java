package com.example.foodplanner.Model;

import com.example.foodplanner.network.CallBackInter;

public interface MealRepositoryInter {
    void getAllMeals(CallBackInter interCallBack);

    void getRandomMeal(CallBackInter interCallBack);

    //void insertProduct(Meal product);

    //LiveData<List<Meal>> getStoredProducts();

    //public void deleteProduct(Meal product);

}
