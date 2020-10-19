package com.uala.ejercicio.ui.meals

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uala.ejercicio.data.entities.Meal
import com.uala.ejercicio.databinding.ItemMealBinding

class MealsAdapter(private val listener: MealItemListener) : RecyclerView.Adapter<MealViewHolder>() {

    interface MealItemListener {
        fun onClickedMeal(mealId: Int)
    }

    private val items = ArrayList<Meal>()

    fun setItems(items: ArrayList<Meal>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding: ItemMealBinding = ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) = holder.bind(items[position])
}

class MealViewHolder(private val itemBinding: ItemMealBinding, private val listener: MealsAdapter.MealItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var meal: Meal

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Meal) {
        this.meal = item
        itemBinding.name.text = item.strMeal
    }

    override fun onClick(v: View?) {
//        listener.onClickedMeal(meal.idMeal)
    }
}
