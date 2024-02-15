package com.example.foodplanner.Model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.db.FavLocalDataSource;
import com.example.foodplanner.db.InterFavLocalDataSource;
import com.example.foodplanner.network.CallBackInter;
import com.example.foodplanner.network.MealAPI;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.example.foodplanner.network.MealsRemoteDataSourceInter;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class MealRepository implements MealRepositoryInter{

    FavLocalDataSource favLocalDataSource;
    InterFavLocalDataSource interFavLocalDataSource;
    static MealsRemoteDataSourceInter interProductsRemoteDataSource;

    private static MealRepository productsRepository =null;

    private MealRepository(MealsRemoteDataSourceInter interProductsRemoteDataSource
                            ,InterFavLocalDataSource interFavLocalDataSource) {
        this.interProductsRemoteDataSource = interProductsRemoteDataSource;
        this.interFavLocalDataSource = interFavLocalDataSource;
    }

    public static MealRepository getInstance(
            MealsRemoteDataSourceInter interProductsRemoteDataSource
            ,FavLocalDataSource favLocalDataSource){
        if(productsRepository==null){
            productsRepository = new MealRepository(
                    interProductsRemoteDataSource,favLocalDataSource);
        }
        return productsRepository;
    }


    @Override
    public void getAllMeals(CallBackInter interCallBack) {
        interProductsRemoteDataSource.makeNetworkCall(interCallBack);
    }

    @Override
    public void getRandomMeal(CallBackInter interCallBack) {
        interProductsRemoteDataSource.makeNetworkCall(interCallBack);
    }

    @Override
    public Flowable<List<Meal>> getStoredProducts() {
        return interFavLocalDataSource.getAllMealsData();
    }

    @Override
    public void insertProduct(Meal meal) {
        interFavLocalDataSource.insertMeal(meal);
    }

    @Override
    public void deleteProduct(Meal meal) {
        interFavLocalDataSource.deleteMeal(meal);
    }

    @Override
    public void getAllCategoriesRepo(CallBackInter interCallBack) {
        interProductsRemoteDataSource.makeNetworkCall(interCallBack);
    }

    @Override
    public Observable<MealList> getSearchMealsRepo(String query) {
        return interProductsRemoteDataSource.getSearchMealsRemote(query);
    }

//    @Override
//    public void getSearchMealsRepo(CallBackInter interCallBack) {
//        interProductsRemoteDataSource.makeNetworkCall(interCallBack);
//    }

//    public Flowable<List<Product>> getStoredProducts(){
//        return interProductsLocalDataSource.getAllProductsData();
//    }
////    public LiveData<List<Product>> getStoredProducts(){
////        return interProductsLocalDataSource.getAllProductsData();
////    }
//
//    public void insertProduct(Product product){
//        interProductsLocalDataSource.insert(product);
//    }
//
//    public void deleteProduct(Product product){
//        interProductsLocalDataSource.delete(product);
//    }

}
