package com.example.foodplanner.network;

import com.example.foodplanner.Model.MealList;

import io.reactivex.rxjava3.core.Observable;

public interface MealsRemoteDataSourceInter {
    void makeNetworkCall(CallBackInter interCallBack);

    Observable<MealList> getSearchMealsRemote(String query);
}
