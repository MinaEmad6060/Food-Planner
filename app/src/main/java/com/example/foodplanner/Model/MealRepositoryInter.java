package com.example.foodplanner.Model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.network.CallBackInter;

import java.util.List;

public interface MealRepositoryInter {
    void getAllProducts(CallBackInter interCallBack);

    //void insertProduct(Meal product);

    //LiveData<List<Meal>> getStoredProducts();

    //public void deleteProduct(Meal product);

}
