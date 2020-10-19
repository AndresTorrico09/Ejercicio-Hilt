package com.uala.ejercicio.data.remote

import javax.inject.Inject

class MealRemoteDataSource @Inject constructor(
    private val mealService: MealService
): BaseDataSource() {

    suspend fun getMeals() = getResult { mealService.getAllMeals() }
}