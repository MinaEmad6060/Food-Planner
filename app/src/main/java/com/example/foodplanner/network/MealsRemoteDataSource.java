package com.example.foodplanner.network;

import com.example.foodplanner.Model.AreasList;
import com.example.foodplanner.Model.CategoryList;
import com.example.foodplanner.Model.IngredientsList;
import com.example.foodplanner.Model.MealList;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSource implements MealsRemoteDataSourceInter{

    private static final String TAG = "ConnectToProductAPI";

    private static final String JSON_URL_RETROFIT =
            "https://www.themealdb.com/api/json/v1/1/";
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
    public Observable<MealList> getRandomMealRemote() {
        Observable<MealList> observable= mealAPI.getRandomMealAPI();
        return observable.subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<MealList> getMealsOfCategoryRemote(String category) {
        Observable<MealList> observable= mealAPI.getMealsOfCategoryAPI(category);
        return observable.subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<MealList> getMealsOfAreaRemote(String area) {
        Observable<MealList> observable= mealAPI.getMealsOfAreaAPI(area);
        return observable.subscribeOn(Schedulers.io());    }

    @Override
    public Observable<MealList> getMealsOfIngredientsRemote(String ingredient) {
        Observable<MealList> observable= mealAPI.getMealsOfIngredientsAPI(ingredient);
        return observable.subscribeOn(Schedulers.io());    }

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

    @Override
    public Observable<AreasList> getAllAreasRemote() {
        Observable<AreasList> observable= mealAPI.getAllAreasAPI("");
        return observable.subscribeOn(Schedulers.io());    }

    @Override
    public Observable<IngredientsList> getAllIngredientRemote() {
        Observable<IngredientsList> observable= mealAPI.getAllIngredientsAPI("");
        return observable.subscribeOn(Schedulers.io());
    }

//    @Override
//    public Observable<MealList> getAllMealsOfCategoriesRemote() {
//        Observable<MealList> observable= mealAPI.getMealsOfCategoryAPI();
//        return observable.subscribeOn(Schedulers.io());    }
}
