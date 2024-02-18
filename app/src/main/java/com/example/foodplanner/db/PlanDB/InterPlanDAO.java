//package com.example.productsmvp.db;
//
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.OnConflictStrategy;
//import androidx.room.Query;
//
//import com.example.productsmvp.model.Product;
//
//import java.util.List;
//
//@Dao
//public interface InterProductDAO {
//    @Query("SELECT * FROM product_table")
//    LiveData<List<Product>> getAllProducts();
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void insertProduct (Product product);
//    @Delete
//    void deleteProduct (Product product);
//}
//

package com.example.foodplanner.db.PlanDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.Model.Plan;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;


@Dao
public interface InterPlanDAO {
//    @Query("SELECT * FROM plan_table")
//    Flowable<List<Plan>> getAllMeals(); // Change return type to Flowable
    @Query("SELECT saturday FROM plan_table")
    Observable<List<String>> getSaturdayMeals();
    @Query("SELECT sunday FROM plan_table")
    Observable<List<String>> getSundayMeals();
    @Query("SELECT monday FROM plan_table")
    Observable<List<String>> getMondayMeals();
    @Query("SELECT tuesday FROM plan_table")
    Observable<List<String>> getTuesdayMeals();
    @Query("SELECT wednesday FROM plan_table")
    Observable<List<String>> getWednesdayMeals();
    @Query("SELECT thursday FROM plan_table")
    Observable<List<String>> getThursdayMeals();
    @Query("SELECT friday FROM plan_table")
    Observable<List<String>> getFridayMeals();



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Plan plan);



    @Query("UPDATE plan_table SET saturday = '' WHERE saturday = :mealDetails")
    void deleteSat(String mealDetails);
    @Query("UPDATE plan_table SET sunday = '' WHERE sunday = :mealDetails")
    void deleteSun(String mealDetails);
    @Query("UPDATE plan_table SET monday = '' WHERE monday = :mealDetails")
    void deleteMon(String mealDetails);
    @Query("UPDATE plan_table SET tuesday = '' WHERE tuesday = :mealDetails")
    void deleteTue(String mealDetails);
    @Query("UPDATE plan_table SET wednesday = '' WHERE wednesday = :mealDetails")
    void deleteWed(String mealDetails);
    @Query("UPDATE plan_table SET thursday = '' WHERE thursday = :mealDetails")
    void deleteTh(String mealDetails);
    @Query("UPDATE plan_table SET friday = '' WHERE friday = :mealDetails")
    void deleteFri(String mealDetails);

//    @Query("UPDATE plan_table SET :columnName = '' WHERE :columnName = :mealDetails")
//    void deleteFromDay(String columnName,String mealDetails);
}
