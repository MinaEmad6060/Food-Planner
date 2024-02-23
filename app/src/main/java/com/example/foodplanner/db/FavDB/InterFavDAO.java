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
    Flowable<List<Meal>> getAllMeals();

    @Query("DELETE FROM meal_table")
    void deleteAllFav();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Meal meal);

    @Delete
    void delete(Meal meal);
}
