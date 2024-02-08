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
    public void makeNetworkCall(CallBackInter interCallBack, String query){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSON_URL_RETROFIT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i(TAG, "Retrofit: ");

        MealAPI mealAPI = retrofit.create(MealAPI.class);
        Log.i(TAG, "proAPI: ");

        mealAPI.getByCategoryMealsAPI(query).enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(Call<MealList> call,
                                   Response<MealList> response) {
                Log.i(TAG, "onResponse: ");
                if (response.isSuccessful()) {
                    //Log.i(TAG, "response.isSuccessful: "+ response.body().meals.size());
                    interCallBack.onSuccess(response.body().meals, query);
                }
            }

            @Override
            public void onFailure(Call<MealList> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
                //interCallBack.onFail(t.getMessage());
            }
        });

        mealAPI.getRandomMealAPI().enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(Call<MealList> call,
                                   Response<MealList> response) {
                Log.i(TAG, "onResponseRandom: ");
                if (response.isSuccessful()) {
                    //Log.i(TAG, "response.isSuccessful: "+ response.body().meals.size());
                    interCallBack.onSuccessRandom(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealList> call, Throwable t) {
                Log.i(TAG, "onFailureRandom: ");
                //interCallBack.onFail(t.getMessage());
            }
        });
    }

}
