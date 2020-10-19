package com.uala.ejercicio.data.repository

import com.uala.ejercicio.data.local.MealDao
import com.uala.ejercicio.data.remote.MealRemoteDataSource
import com.uala.ejercicio.utils.performGetOperation
import javax.inject.Inject

class MealRepository @Inject constructor(
    private val remoteDataSource: MealRemoteDataSource,
    private val localDataSource: MealDao
) {
    fun getMeals() = performGetOperation(
        databaseQuery = { localDataSource.getAllMeals() },
        networkCall = { remoteDataSource.getMeals() },
        saveCallResult = { localDataSource.insertAll(it.meals) }
    )
}