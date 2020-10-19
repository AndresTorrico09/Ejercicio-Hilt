package com.uala.ejercicio.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class Meal (
    @PrimaryKey
    val idMeal: String,
    val strMeal: String
)