package com.example.foodplanner.Model;

import com.example.foodplanner.db.FavLocalDataSource;
import com.example.foodplanner.db.InterFavLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSourceInter;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class MealRepository implements MealRepositoryInter{

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
    public Observable<MealList> getSearchMealsRepo(String query) {
        return interProductsRemoteDataSource.getSearchMealsRemote(query);
    }

    @Override
    public Observable<CategoryList> getAllCategoriesRepo() {
        return interProductsRemoteDataSource.getAllCategoriesRemote();
    }

    @Override
    public Observable<AreasList> getAllAreasRepo() {
        return interProductsRemoteDataSource.getAllAreasRemote();    }

    @Override
    public Observable<IngredientsList> getAllIngredientRepo() {
        return interProductsRemoteDataSource.getAllIngredientRemote();
    }

    @Override
    public Observable<MealList> getMealsOfCategoryRepo(String category) {
        return interProductsRemoteDataSource.getMealsOfCategoryRemote(category);
    }

    @Override
    public Observable<MealList> getRandomMealRepo() {
        return interProductsRemoteDataSource.getRandomMealRemote();
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


}
