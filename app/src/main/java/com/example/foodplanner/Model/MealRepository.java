package com.example.foodplanner.Model;

import com.example.foodplanner.db.FavDB.FavLocalDataSource;
import com.example.foodplanner.db.FavDB.InterFavLocalDataSource;
import com.example.foodplanner.db.PlanDB.InterPlanLocalDataSource;
import com.example.foodplanner.db.PlanDB.PlanLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSourceInter;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class MealRepository implements MealRepositoryInter{

    InterFavLocalDataSource interFavLocalDataSource;

    InterPlanLocalDataSource interPlanLocalDataSource;
    static MealsRemoteDataSourceInter interMealsRemoteDataSource;

    private static MealRepository favMealsRepository =null;
    private static MealRepository planMealsRepository =null;


    private MealRepository(MealsRemoteDataSourceInter interMealsRemoteDataSource
                            ,InterFavLocalDataSource interFavLocalDataSource) {
        this.interMealsRemoteDataSource = interMealsRemoteDataSource;
        this.interFavLocalDataSource = interFavLocalDataSource;
    }

    private MealRepository(MealsRemoteDataSourceInter interMealsRemoteDataSource
            ,InterPlanLocalDataSource interPlanLocalDataSource) {
        this.interMealsRemoteDataSource = interMealsRemoteDataSource;
        this.interPlanLocalDataSource = interPlanLocalDataSource;
    }

    public static MealRepository getFavInstance(
            MealsRemoteDataSourceInter interProductsRemoteDataSource
            ,FavLocalDataSource favLocalDataSource){
        if(favMealsRepository ==null){
            favMealsRepository = new MealRepository(
                    interProductsRemoteDataSource,favLocalDataSource);
        }
        return favMealsRepository;
    }

    public static MealRepository getPlanInstance(
            MealsRemoteDataSourceInter interProductsRemoteDataSource
            , PlanLocalDataSource PlanLocalDataSource){
        if(planMealsRepository ==null){
            planMealsRepository = new MealRepository(
                    interProductsRemoteDataSource,PlanLocalDataSource);
        }
        return planMealsRepository;
    }

    @Override
    public Observable<MealList> getSearchMealsRepo(String query) {
        return interMealsRemoteDataSource.getSearchMealsRemote(query);
    }

    @Override
    public Observable<CategoryList> getAllCategoriesRepo() {
        return interMealsRemoteDataSource.getAllCategoriesRemote();
    }

    @Override
    public Observable<AreasList> getAllAreasRepo() {
        return interMealsRemoteDataSource.getAllAreasRemote();    }

    @Override
    public Observable<IngredientsList> getAllIngredientRepo() {
        return interMealsRemoteDataSource.getAllIngredientRemote();
    }

    @Override
    public Observable<MealList> getMealsOfCategoryRepo(String category) {
        return interMealsRemoteDataSource.getMealsOfCategoryRemote(category);
    }

    @Override
    public Observable<MealList> getAllMealsOfAreasRepo(String category) {
        return interMealsRemoteDataSource.getMealsOfAreaRemote(category);
    }

    @Override
    public Observable<MealList> getAllMealsIngredientRepo(String category) {
        return interMealsRemoteDataSource.getMealsOfIngredientsRemote(category);
    }


    @Override
    public Observable<MealList> getRandomMealRepo() {
        return interMealsRemoteDataSource.getRandomMealRemote();
    }


    //fav
    @Override
    public Flowable<List<Meal>> getStoredMeals() {
        return interFavLocalDataSource.getAllMealsData();
    }
    @Override
    public void deleteAllFavMeals() {
        interFavLocalDataSource.deleteAllFavData();
    }

    @Override
    public void insertMeals(Meal meal) {
        interFavLocalDataSource.insertMealData(meal);
    }

    @Override
    public void deleteMeals(Meal meal) {
        interFavLocalDataSource.deleteMealData(meal);
    }



    //plan
    @Override
    public Observable<List<String>> getDayMealsRepo(String columnName) {
        return interPlanLocalDataSource.getDayMealsData(columnName);
    }

    @Override
    public void insertDayMeal(Plan plan) {
        interPlanLocalDataSource.insertDayMealData(plan);
    }

    @Override
    public void deleteSatMeal(String mealDetails) {
        interPlanLocalDataSource.deleteSatMealData(mealDetails);
    }

    @Override
    public void deleteSunMeal(String mealDetails) {
        interPlanLocalDataSource.deleteSunMealData(mealDetails);
    }

    @Override
    public void deleteMonMeal(String mealDetails) {
        interPlanLocalDataSource.deleteMonMealData(mealDetails);
    }

    @Override
    public void deleteTueMeal(String mealDetails) {
        interPlanLocalDataSource.deleteTueMealData(mealDetails);
    }

    @Override
    public void deleteWedMeal(String mealDetails) {
        interPlanLocalDataSource.deleteWedMealData(mealDetails);
    }

    @Override
    public void deleteThMeal(String mealDetails) {
        interPlanLocalDataSource.deleteThMealData(mealDetails);
    }

    @Override
    public void deleteFriMeal(String mealDetails) {
        interPlanLocalDataSource.deleteFriMealData(mealDetails);
    }

}
