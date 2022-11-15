package com.oyatech.expensestracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.oyatech.expensestracker.databinding.FragmentDashBoardBinding
import com.oyatech.expensestracker.databinding.FragmentFoodExpensesBinding


class FoodExpensesFragment : Fragment() {
    private var _binding: FragmentFoodExpensesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFoodExpensesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.save.setOnClickListener {
            //Intent to details screen passing the id of the user
val action = FoodExpensesFragmentDirections.actionFoodExpensesFragmentToBudgetDetailsFragment(1)
      findNavController().navigate(action)
       }
    }
}