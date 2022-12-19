package com.oyatech.expensestracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.oyatech.expensestracker.data.model.BudgetViewModel
import com.oyatech.expensestracker.data.model.BudgetViewModelFactory
import com.oyatech.expensestracker.data.model.Expenses
import com.oyatech.expensestracker.databinding.FragmentBudgetDetailsBinding
import com.oyatech.expensestracker.databinding.FragmentLogInBinding

class BudgetDetailsFragment : Fragment() {
    val TAG = "Details"
    var _binding: FragmentBudgetDetailsBinding? = null
    val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var detailAdapter: BudgetDetailAdapter
    private val args: BudgetDetailsFragmentArgs by navArgs()

    private val viewModel: BudgetViewModel by activityViewModels {
        BudgetViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailAdapter = BudgetDetailAdapter { it, position ->
            createDialog(it, position)
            Log.i(TAG, "onViewCreated: ${it.name}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =
            FragmentBudgetDetailsBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val position = args.position
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.userExpenses(position)
        viewModel.userExpensesLiveData.observe(viewLifecycleOwner) {

            detailAdapter.submitList(it)
        }

        recyclerView.adapter = detailAdapter


        Log.i(TAG, "onViewCreated: $position")

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun createDialog(expense: Expenses, position: Int) {
        val dialog = EditExpense(expense, position)

        activity?.let { dialog.show(it.supportFragmentManager, "Edit") }
    }

}