package com.oyatech.expensestracker

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oyatech.expensestracker.data.model.Budget
import com.oyatech.expensestracker.data.UserViewModel
import com.oyatech.expensestracker.databinding.MonthlyBudgetBinding

class HistoryAdapter(val context: Context) :
    ListAdapter<Budget, HistoryAdapter.ViewHolder>(DiffCallback) {
    companion object DiffCallback : ItemCallback<Budget>() {


        override fun areItemsTheSame(oldItem: Budget, newItem: Budget): Boolean {
            return oldItem == newItem
        }


        override fun areContentsTheSame(oldItem: Budget, newItem: Budget): Boolean {
            return oldItem.month == newItem.month
        }

    }

    class ViewHolder(private var binding: MonthlyBudgetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binding(budget: Budget) {
            with(binding) {
                month.text = budget.month
                income.text = budget.income.toString()
                amountSpent.text = budget.amountSpent.toString()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = MonthlyBudgetBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(viewHolder)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val budget = getItem(position)
        holder.binding(budget)

        holder.itemView.setOnClickListener {
            val action =
                HistoryFragmentDirections.actionHistoryFragmentToBudgetDetailsFragment(position)
            it.findNavController().navigate(action)
            Toast.makeText(context, "Clicked", Toast.LENGTH_LONG * 2).show()
        }
    }
}