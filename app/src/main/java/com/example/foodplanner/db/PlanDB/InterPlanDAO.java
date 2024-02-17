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
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Plan;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface InterPlanDAO {
//    @Query("SELECT * FROM plan_table")
//    Flowable<List<Plan>> getAllMeals(); // Change return type to Flowable
    @Query("SELECT :columnName FROM plan_table")
    Flowable<List<String>> getDayMeals(String columnName);

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
