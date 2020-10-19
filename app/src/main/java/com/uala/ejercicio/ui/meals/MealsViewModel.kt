package com.uala.ejercicio.ui.meals

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.uala.ejercicio.data.repository.MealRepository

class MealsViewModel @ViewModelInject constructor(
    private val repository: MealRepository
) : ViewModel() {

    val meals = repository.getMeals()
}
