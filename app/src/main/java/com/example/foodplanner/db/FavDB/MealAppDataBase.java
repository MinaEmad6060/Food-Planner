package com.example.foodplanner.db.FavDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.Model.Meal;

@Database(entities = {Meal.class}, version = 1)
public abstract class MealAppDataBase extends RoomDatabase {
    private static MealAppDataBase instance = null;
    public abstract InterFavDAO getMealDAO();
    public static synchronized MealAppDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MealAppDataBase.class, "mealsDataBase").build();
        }
        return instance;
    }
}


