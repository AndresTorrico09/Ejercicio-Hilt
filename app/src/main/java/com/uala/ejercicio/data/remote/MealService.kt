package com.uala.ejercicio.data.remote

import com.uala.ejercicio.data.entities.MealList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MealService {
    @GET("json/v1/1/search.php?s")
    suspend fun getAllMeals() : Response<MealList>
}