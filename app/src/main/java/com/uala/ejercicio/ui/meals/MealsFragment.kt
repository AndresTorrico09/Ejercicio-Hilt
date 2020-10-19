package com.uala.ejercicio.ui.meals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.uala.ejercicio.R
import com.uala.ejercicio.databinding.FragmentMealsBinding
import com.uala.ejercicio.utils.Resource
import com.uala.ejercicio.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealsFragment : Fragment(), MealsAdapter.MealItemListener {

    private var binding: FragmentMealsBinding by autoCleared()
    private val viewModel: MealsViewModel by viewModels()
    private lateinit var adapter: MealsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMealsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = MealsAdapter(this)
        binding.mealsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.mealsRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.meals.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedMeal(mealId: Int) {
//        findNavController().navigate(
//            R.id.action_mealsFragment_to_mealDetailFragment,
//            bundleOf("id" to mealId)
//        )
    }
}