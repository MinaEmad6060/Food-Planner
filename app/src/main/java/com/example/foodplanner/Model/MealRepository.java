package com.example.foodplanner.Model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.network.CallBackInter;
import com.example.foodplanner.network.MealAPI;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.example.foodplanner.network.MealsRemoteDataSourceInter;

import java.util.List;

public class MealRepository implements MealRepositoryInter{


    static MealsRemoteDataSourceInter interProductsRemoteDataSource;

    private static MealRepository productsRepository =null;

    private MealRepository(MealsRemoteDataSourceInter interProductsRemoteDataSource) {
        this.interProductsRemoteDataSource = interProductsRemoteDataSource;
    }

    public static MealRepository getInstance(
            MealsRemoteDataSourceInter interProductsRemoteDataSource){
        if(productsRepository==null){
            productsRepository = new MealRepository(
                    interProductsRemoteDataSource);
        }
        return productsRepository;
    }


    @Override
    public void getAllMeals(CallBackInter interCallBack) {
        interProductsRemoteDataSource.makeNetworkCall(interCallBack);
    }

    @Override
    public void getRandomMeal(CallBackInter interCallBack) {
        interProductsRemoteDataSource.makeNetworkCall(interCallBack);
    }

}
