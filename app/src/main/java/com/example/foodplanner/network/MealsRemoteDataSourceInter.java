package com.example.foodplanner.network;

public interface MealsRemoteDataSourceInter {
    void makeNetworkCall(CallBackInter interCallBack, String query);
}
