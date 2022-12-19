package com.oyatech.expensestracker

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.oyatech.expensestracker.data.model.BudgetViewModel
import com.oyatech.expensestracker.data.model.BudgetViewModelFactory
import com.oyatech.expensestracker.data.model.Expenses
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@SuppressLint("SimpleDateFormat")
fun getMonth(): String {
    val month = Calendar.getInstance()
    val format = SimpleDateFormat("MMMM")
    return format.format(month.time).uppercase(Locale.ROOT)

}

class DiffCallBack<T> : ItemCallback<DiffCallBack<T>>() {
    override fun areItemsTheSame(oldItem: DiffCallBack<T>, newItem: DiffCallBack<T>): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: DiffCallBack<T>,
        newItem: DiffCallBack<T>
    ): Boolean {
        return oldItem.equals(newItem)
    }

}

class EditExpense(private val expenses: Expenses,val position: Int) : DialogFragment() {

    val viewModel: BudgetViewModel by activityViewModels {
        BudgetViewModelFactory(requireContext())
    }
    interface IupdateExpense {
        fun saveButtonClick(expenses: Expenses)
        fun cancelButtonClick()
    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }

        val editView = layoutInflater.inflate(R.layout.edit_expenses, null)
        val name = editView.findViewById<EditText>(R.id.expense_name)
        val value = editView.findViewById<EditText>(R.id.expense_amount)
        val save = editView.findViewById<MaterialButton>(R.id.save_edit)
        val cancel = editView.findViewById<MaterialButton>(R.id.cancel)

        builder?.setView(editView)
        name.setText(expenses.name)
        value.setText(expenses.value.toString())
        val dialog: AlertDialog? = builder?.create()
        dialog?.setTitle("Edit ${expenses.name} expense")
        dialog?.setCanceledOnTouchOutside(false)
        save.setOnClickListener {
           val expenses = Expenses(name.text.toString(),value.text.toString().toDouble())
            viewModel.userExpenses[position] = expenses

            dialog?.cancel()
        }
        cancel.setOnClickListener {
            dialog?.cancel()
        }

        return dialog!!

    }



}

fun Context.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG * 3).show()
}

fun View.makeSnack(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG * 3).show()
}