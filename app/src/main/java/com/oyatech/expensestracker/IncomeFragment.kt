package com.oyatech.expensestracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oyatech.expensestracker.data.model.BudgetViewModel
import com.oyatech.expensestracker.data.model.BudgetViewModelFactory
import com.oyatech.expensestracker.databinding.FragmentIncomeBinding

class IncomeFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentIncomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BudgetViewModel by activityViewModels {
        BudgetViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentIncomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.next.setOnClickListener(this)


    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.next -> {


                    val incomeEntry = binding.income.text.toString()
                    with(viewModel.budget) {
                        income = incomeEntry.toDouble()
                    }
                    val action =
                        IncomeFragmentDirections.actionIncomeFragmentToFoodExpensesFragment()
                    findNavController().navigate(action)
                }
            }
        }
    }

}