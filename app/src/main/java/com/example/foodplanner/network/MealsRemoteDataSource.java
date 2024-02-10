package com.example.foodplanner.network;

import android.util.Log;

import com.example.foodplanner.Model.MealList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSource implements MealsRemoteDataSourceInter{

    private static final String TAG = "ConnectToProductAPI";

    private static final String JSON_URL_RETROFIT =
            "https://www.themealdb.com/api/json/v1/1/";
    CallBackInter interCallBack;

    private static MealsRemoteDataSource connectToProduct=null;

    public MealsRemoteDataSource() {
    }


    public static MealsRemoteDataSource getInstance(){
        if(connectToProduct==null){
            connectToProduct = new MealsRemoteDataSource();
        }
        return connectToProduct;
    }


    @Override
    public void makeNetworkCall(CallBackInter interCallBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSON_URL_RETROFIT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Log.i(TAG, "Retrofit: ");

        MealAPI mealAPI = retrofit.create(MealAPI.class);
        //Log.i(TAG, "proAPI: ");

        mealAPI.getChickenCategoryMealsAPI("Chicken").enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(Call<MealList> call,
                                   Response<MealList> response) {
                Log.i(TAG, "Category Chicken Response: ");
                if (response.isSuccessful()) {
                    interCallBack.onSuccessChicken(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealList> call, Throwable t) {
                Log.i(TAG, "Category Chicken Failure: ");
            }
        });

        mealAPI.getChickenCategoryMealsAPI("Beef").enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(Call<MealList> call,
                                   Response<MealList> response) {
                Log.i(TAG, "Category Beef Response: ");
                if (response.isSuccessful()) {
                    interCallBack.onSuccessBeef(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealList> call, Throwable t) {
                Log.i(TAG, "Category Beef Failure: ");
            }
        });

        mealAPI.getRandomMealAPI().enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(Call<MealList> call,
                                   Response<MealList> response) {
                Log.i(TAG, "Random Response: ");
                if (response.isSuccessful()) {
                    interCallBack.onSuccessRandom(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealList> call, Throwable t) {
                Log.i(TAG, "Random Failure: ");
            }
        });
    }
}
