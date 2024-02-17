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

package com.example.foodplanner.db.FavDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface InterFavDAO {
    @Query("SELECT * FROM meal_table")
    Flowable<List<Meal>> getAllMeals(); // Change return type to Flowable
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Meal meal);
    @Delete
    void delete(Meal meal);
}
