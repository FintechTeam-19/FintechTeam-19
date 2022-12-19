package com.oyatech.expensestracker.data.model

import com.oyatech.expensestracker.getMonth

data class Budget(var income:Double, var expenses:MutableList<Expenses>?, val month:String = getMonth(), var amountSpent: Double =0.0, var balance:Double =0.0 )

data class Expenses(var name:String, var value:Double,var expenseSpent: Double=0.0)

//to get the balance you need to add all the expenses and subtract from the income