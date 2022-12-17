package com.oyatech.expensestracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyatech.expensestracker.data.model.BudgetViewModel
import com.oyatech.expensestracker.data.model.BudgetViewModelFactory
import com.oyatech.expensestracker.databinding.FragmentBudgetDetailsBinding
import com.oyatech.expensestracker.databinding.FragmentLogInBinding

class BudgetDetailsFragment : Fragment() {
val TAG = "Details"
var _binding:FragmentBudgetDetailsBinding? = null
    val binding get() = _binding!!

 private val args:  BudgetDetailsFragmentArgs by navArgs()
    private val detailAdapter: BudgetDetailAdapter by lazy {
        BudgetDetailAdapter(requireContext())
    }
    private val viewModel: BudgetViewModel by activityViewModels {
        BudgetViewModelFactory(requireContext())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       /* val layout = requireActivity().findViewById<CardView>(R.id.monthly_expense_layout)
        layout.setCardBackgroundColor(resources.getColor(R.color.ash))*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBudgetDetailsBinding.inflate(LayoutInflater.from(context),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
val action = args.position
      val recyclerView =  binding.recyclerView
            recyclerView.layoutManager = LinearLayoutManager(context)
        detailAdapter.submitList(viewModel.allExpenses)
        recyclerView.adapter = detailAdapter


        Log.i(TAG, "onViewCreated: $action")

    }
    override fun onDestroy() {
            super.onDestroy()
        _binding = null
    }
}