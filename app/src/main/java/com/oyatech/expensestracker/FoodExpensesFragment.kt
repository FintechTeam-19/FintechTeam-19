package com.oyatech.expensestracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.oyatech.expensestracker.data.model.BudgetViewModel
import com.oyatech.expensestracker.data.model.BudgetViewModelFactory
import com.oyatech.expensestracker.data.model.Expenses
import com.oyatech.expensestracker.databinding.FragmentFoodExpensesBinding


class FoodExpensesFragment : Fragment() {
    private var _binding: FragmentFoodExpensesBinding? = null
    private val binding get() = _binding!!
    val viewModel: BudgetViewModel by activityViewModels {
        BudgetViewModelFactory(requireContext())
    }

    private var count = 1
    var expenses = arrayListOf<Expenses>()
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
        binding.next.setOnClickListener {
            //Intent to details screen passing the id of the user
            val value = binding.expenseAmount.text.toString()
            val name = binding.expenseName.text.toString()

          with( viewModel){
              addExpense(name,value.toDouble())
          }

            if (count==1){
                binding.next.text = "Save"
            }
            if (count == 2) {
                with(viewModel) {
                    addBudget(budget.income.toString(), budget.month, allExpenses,balance)
                    val action =
                    findNavController().navigate(R.id.action_foodExpensesFragment_to_historyFragment)
                }
            }
            clearFields()
            count ++

        }
        binding.save.setOnClickListener {
            findNavController().navigate(R.id.action_foodExpensesFragment_to_dashBoardFragment)
        }
    }

    fun clearFields() {
        binding.expenseAmount.setText("")
        binding.expenseName.setText("")
    }

}