package com.oyatech.expensestracker

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oyatech.expensestracker.data.model.Expenses
import com.oyatech.expensestracker.databinding.ExpensesLayoutBinding

class BudgetDetailAdapter(val context: Context) :
    ListAdapter<Expenses, BudgetDetailAdapter.DetailViewHolder>(DiffCalls) {
    companion object DiffCalls : ItemCallback<Expenses>() {
        override fun areItemsTheSame(oldItem: Expenses, newItem: Expenses): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Expenses, newItem: Expenses): Boolean {
            return oldItem.value == newItem.value
        }

    }
    class DetailViewHolder(private val holder: ExpensesLayoutBinding) :
        RecyclerView.ViewHolder(holder.root) {

        fun bindingView(expenses: Expenses) {
            expenses.let {
                holder.expensesName.text = it.name
                holder.budget.text = it.value.toString()
                holder.amountSpent.text = it.expenseSpent.toString()

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding = ExpensesLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
       holder.bindingView(getItem(position))
    }
}