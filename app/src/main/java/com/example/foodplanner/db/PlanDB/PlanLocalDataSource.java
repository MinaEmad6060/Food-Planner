package com.example.foodplanner.db.PlanDB;

import android.content.Context;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Plan;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanLocalDataSource implements InterPlanLocalDataSource {

    private InterPlanDAO dao;
//    private static ProductsLocalDataSource localSource = null;
    private Flowable<List<String>> storedProducts;
    //private LiveData<List<Product>> storedProducts;

    String columnName="";

    private static PlanLocalDataSource connectToMeal =null;

    private PlanLocalDataSource(Context context){
        PlanAppDataBase db= PlanAppDataBase.getInstance(context.getApplicationContext());
        dao = db.getMealDAO();
        storedProducts=dao.getDayMeals(columnName);
    }
    public static PlanLocalDataSource getInstance(Context context){
        if(connectToMeal ==null){
            connectToMeal = new PlanLocalDataSource(context);
        }
        return connectToMeal;
    }
    @Override
    public Flowable<List<String>> getDayMealsData(String columnName) {
        this.columnName=columnName;
        return storedProducts.subscribeOn(Schedulers.io());
    }

    @Override
    public void insertDayMealData(Plan plan) {
        new Thread(){
            @Override
            public void run() {
                dao.insert(plan);
            }
        }.start();
    }


    @Override
    public void deleteSatMealData(String mealDetails) {
        new Thread(){
            @Override
            public void run() {
                dao.deleteSat(mealDetails);
            }
        }.start();
    }

    @Override
    public void deleteSunMealData(String mealDetails) {
        new Thread(){
            @Override
            public void run() {
                dao.deleteSun(mealDetails);
            }
        }.start();
    }

    @Override
    public void deleteMonMealData(String mealDetails) {
        new Thread(){
            @Override
            public void run() {
                dao.deleteMon(mealDetails);
            }
        }.start();
    }

    @Override
    public void deleteTueMealData(String mealDetails) {
        new Thread(){
            @Override
            public void run() {
                dao.deleteTue(mealDetails);
            }
        }.start();
    }

    @Override
    public void deleteWedMealData(String mealDetails) {
        new Thread(){
            @Override
            public void run() {
                dao.deleteWed(mealDetails);
            }
        }.start();
    }

    @Override
    public void deleteThMealData(String mealDetails) {
        new Thread(){
            @Override
            public void run() {
                dao.deleteFri(mealDetails);
            }
        }.start();
    }

    @Override
    public void deleteFriMealData(String mealDetails) {
        new Thread(){
            @Override
            public void run() {
                dao.deleteFri(mealDetails);
            }
        }.start();
    }

//    @Override
//    public void deleteFromDayMeal(String columnName,String mealDetails) {
//        new Thread(){
//            @Override
//            public void run() {
//                dao.deleteFromDay(columnName,mealDetails);
//            }
//        }.start();
//    }
}
