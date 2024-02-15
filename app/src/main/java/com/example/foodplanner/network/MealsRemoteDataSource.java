package com.example.foodplanner.network;

import android.util.Log;

import com.example.foodplanner.Model.CategoryList;
import com.example.foodplanner.Model.MealList;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
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
    MealAPI mealAPI;

    private static MealsRemoteDataSource connectToProduct=null;

    public MealsRemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSON_URL_RETROFIT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        //Log.i(TAG, "Retrofit: ");

        mealAPI = retrofit.create(MealAPI.class);
    }


    public static MealsRemoteDataSource getInstance(){
        if(connectToProduct==null){
            connectToProduct = new MealsRemoteDataSource();
        }
        return connectToProduct;
    }



    @Override
    public void makeNetworkCall(CallBackInter interCallBack){

        //Log.i(TAG, "proAPI: ");
//        mealAPI.getAllCategoriesAPI().enqueue(new Callback<CategoryList>() {
//            @Override
//            public void onResponse(Call<CategoryList> call,
//                                   Response<CategoryList> response) {
//                Log.i(TAG, "Categories Response: ");
//                if (response.isSuccessful()) {
//                    interCallBack.onSuccessCategory(response.body().categories);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CategoryList> call, Throwable t) {
//                Log.i(TAG, "Categories Failure: ");
//            }
//        });
//
//        mealAPI.getRandomMealAPI().enqueue(new Callback<MealList>() {
//            @Override
//            public void onResponse(Call<MealList> call,
//                                   Response<MealList> response) {
//                Log.i(TAG, "Random Response: ");
//                if (response.isSuccessful()) {
//                    interCallBack.onSuccessRandom(response.body().meals);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MealList> call, Throwable t) {
//                Log.i(TAG, "Random Failure: ");
//            }
//        });
    }





    @Override
    public Observable<MealList> getRandomMealRemote() {
        Observable<MealList> observable= mealAPI.getRandomMealAPI();
        return observable.subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<MealList> getMealsOfCategoryRemote(String category) {
        Observable<MealList> observable= mealAPI.getMealsOfCategoryMealsAPI(category);
        return observable.subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<MealList> getSearchMealsRemote(String query) {
        Observable<MealList> observable= mealAPI.getSearchMealsAPI(query);
        return observable.subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<CategoryList> getAllCategoriesRemote() {
        Observable<CategoryList> observable= mealAPI.getAllCategoriesAPI();
        return observable.subscribeOn(Schedulers.io());
    }
}
