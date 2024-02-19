package com.example.foodplanner.db.FavDB;

import android.content.Context;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavLocalDataSource implements InterFavLocalDataSource {

    private InterFavDAO dao;
//    private static ProductsLocalDataSource localSource = null;
    private Flowable<List<Meal>> storedMeals;
    //private LiveData<List<Product>> storedProducts;

    private static FavLocalDataSource connectToMeal =null;
    MealAppDataBase db;
    Context context;


    private FavLocalDataSource(Context context){
        this.context=context;
        db= MealAppDataBase.getInstance(context.getApplicationContext());
        dao = db.getMealDAO();
    }
    public static FavLocalDataSource getInstance(Context context){
        if(connectToMeal ==null){
            connectToMeal = new FavLocalDataSource(context);
        }
        return connectToMeal;
    }
    @Override
    public Flowable<List<Meal>> getAllMealsData() {
        storedMeals =dao.getAllMeals();
        return storedMeals.subscribeOn(Schedulers.io());
    }
    public void deleteAllFavData() {
        new Thread(){
            @Override
            public void run() {
                dao.deleteAllFav();
            }
        }.start();
    }

    @Override
    public void insertMealData(Meal meal) {
        new Thread(){
            @Override
            public void run() {
                dao.insert(meal);
            }
        }.start();
    }

    @Override
    public void deleteMealData(Meal meal) {
        new Thread(){
            @Override
            public void run() {
                dao.delete(meal);
            }
        }.start();
    }
}
