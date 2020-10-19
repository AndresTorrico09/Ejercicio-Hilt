package com.uala.ejercicio.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uala.ejercicio.data.entities.Meal

@Dao
interface MealDao {

    @Query("SELECT * FROM meals")
    fun getAllMeals() : LiveData<List<Meal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(meals: List<Meal>)

}