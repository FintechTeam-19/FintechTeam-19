package com.oyatech.expensestracker.data.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BudgetViewModel (context: Context): ViewModel() {

    private val budgetList: MutableList<Budget> = mutableListOf()
    var budgetListLive:MutableLiveData<MutableList<Budget>> = MutableLiveData()
    var allExpenses = mutableListOf<Expenses>()

    var budget = Budget(0.0,null)

var userExpenses = mutableListOf<Expenses>()
  var userExpensesLiveData:MutableLiveData<MutableList<Expenses>>  = MutableLiveData()
    var balance = 0.0

    private fun getNewBudget( month:String,spent:Double,income:Double,balance:Double, expenses: MutableList<Expenses>?):Budget{
        return Budget(income = income, month = month, amountSpent = spent,balance = balance, expenses = expenses)

    }


    fun  addBudget(
        income: String,
        month:String, expenses: MutableList<Expenses>?,
        spent:Double=0.0,
        balance:Double=0.0){
        val monthlyBudget = getNewBudget(month = month, spent = spent, income = income.toDouble(),balance = balance, expenses = expenses)
        insertBudget(monthlyBudget)
    }

    private fun insertBudget(monthlyBudget: Budget) {
       budgetList.add(monthlyBudget)
        budgetListLive.value = budgetList
        amountSpent(monthlyBudget)
    }

   private fun updateExpense(expenses: Expenses){


    }
    fun newBudget(){

    }
   private fun amountSpent(budget: Budget){
        budget.let {
            it.expenses?.forEach { expense ->
                budget.amountSpent += expense.value
            }
           balance(budget)
        }
    }

    private fun getExpense(name:String, value:Double):Expenses{
        return Expenses(name=name,value = value)
    }

    fun addExpense(name: String,value: Double){
        val expense = getExpense(name = name, value = value)
        allExpenses.add(expense)

    }

    private fun balance(budget: Budget){
        balance = budget.income - budget.amountSpent
        Log.i("ViewModel", "balance: $balance")
    }

    fun userExpenses(position: Int){
        userExpenses = budgetList[position].expenses!!
        userExpensesLiveData.value = userExpenses
    }
    fun updateExpense (){
        userExpensesLiveData.value = userExpenses
    }
}



//ViewModel Factory
class BudgetViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BudgetViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return BudgetViewModel(context) as T
        }
throw IllegalArgumentException("unknown viewModel")
    }


}
