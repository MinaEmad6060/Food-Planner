package com.example.foodplanner.db;

import android.content.Context;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class FavLocalDataSource implements InterFavLocalDataSource {

    private InterFavDAO dao;
//    private static ProductsLocalDataSource localSource = null;
    private Flowable<List<Meal>> storedProducts;
    //private LiveData<List<Product>> storedProducts;

    private static FavLocalDataSource connectToMeal =null;

    private FavLocalDataSource(Context context){
        AppDataBase db= AppDataBase.getInstance(context.getApplicationContext());
        dao = db.getMealDAO();
        storedProducts=dao.getAllMeals();
    }
    public static FavLocalDataSource getInstance(Context context){
        if(connectToMeal ==null){
            connectToMeal = new FavLocalDataSource(context);
        }
        return connectToMeal;
    }
    @Override
    public Flowable<List<Meal>> getAllMealsData() {
        return storedProducts;
    }
//    public LiveData<List<Product>> getAllProductsData() {
//        return storedProducts;
//    }

    @Override
    public void insertMeal(Meal meal) {
        new Thread(){
            @Override
            public void run() {
                dao.insert(meal);
            }
        }.start();
    }

    @Override
    public void deleteMeal(Meal meal) {
        new Thread(){
            @Override
            public void run() {
                dao.delete(meal);
            }
        }.start();
    }
}
