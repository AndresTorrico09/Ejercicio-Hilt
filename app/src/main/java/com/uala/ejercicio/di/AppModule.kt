package com.uala.ejercicio.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.uala.ejercicio.data.local.AppDatabase
import com.uala.ejercicio.data.local.MealDao
import com.uala.ejercicio.data.remote.MealRemoteDataSource
import com.uala.ejercicio.data.remote.MealService
import com.uala.ejercicio.data.repository.MealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideMealService(retrofit: Retrofit): MealService = retrofit.create(MealService::class.java)

    @Singleton
    @Provides
    fun provideMealRemoteDataSource(mealService: MealService) = MealRemoteDataSource(mealService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideMealDao(db: AppDatabase) = db.mealDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: MealRemoteDataSource,
                          localDataSource: MealDao
    ) = MealRepository(remoteDataSource, localDataSource)
}