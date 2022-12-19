package com.oyatech.expensestracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.card.MaterialCardView
import com.oyatech.expensestracker.data.model.BudgetViewModel
import com.oyatech.expensestracker.data.model.BudgetViewModelFactory
import com.oyatech.expensestracker.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    val budgetAdapter: HistoryAdapter by lazy {
        HistoryAdapter(requireContext())
    }
    private val viewModel: BudgetViewModel by activityViewModels {
        BudgetViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layout: MaterialCardView? =
            activity?.findViewById(R.id.monthly_layout)

        val recycle = binding.recyclerView
        recycle.layoutManager = LinearLayoutManager(requireContext())

        viewModel.budgetListLive?.observe(viewLifecycleOwner) {

            budgetAdapter.submitList(it)

        }
        recycle.adapter = budgetAdapter
        Log.i("History", "onViewCreated: ${viewModel.budget.hashCode()}")

    }



}