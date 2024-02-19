package com.example.foodplanner.db.PlanDB;

import android.content.Context;

import com.example.foodplanner.Model.Plan;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanLocalDataSource implements InterPlanLocalDataSource {

    private InterPlanDAO dao;
    private Observable<List<String>> storedMeals;

    private Flowable<List<Plan>> storedPlans;

    PlanAppDataBase db;

    private static PlanLocalDataSource connectToMeal =null;

    private PlanLocalDataSource(Context context){
        db= PlanAppDataBase.getInstance(context.getApplicationContext());
        dao = db.getMealDAO();
        storedPlans=dao.getAllPlans();
    }
    public static PlanLocalDataSource getPlanInstance(Context context){
        if(connectToMeal ==null){
            connectToMeal = new PlanLocalDataSource(context);
        }
        return connectToMeal;
    }
    @Override
    public Observable<List<String>> getDayMealsData(String columnName) {
        checkDay(columnName);
        return storedMeals.subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<List<Plan>> getAllPlans() {
        return storedPlans.subscribeOn(Schedulers.io());
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
    public void deleteAllPlanData() {
        new Thread(){
            @Override
            public void run() {
                dao.deleteAllPlan();
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


    void checkDay(String day){

        switch (day){
            case "saturday":
                storedMeals =dao.getSaturdayMeals();
                break;
            case "sunday":
                storedMeals =dao.getSundayMeals();
                break;
            case "monday":
                storedMeals =dao.getMondayMeals();
                break;
            case "tuesday":
                storedMeals =dao.getTuesdayMeals();
                break;
            case "wednesday":
                storedMeals =dao.getWednesdayMeals();
                break;
            case "thursday":
                storedMeals =dao.getThursdayMeals();
                break;
            case "friday":
                storedMeals =dao.getFridayMeals();
                break;
        }
    }
}
