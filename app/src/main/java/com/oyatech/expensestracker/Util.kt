package com.oyatech.expensestracker

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.oyatech.expensestracker.data.model.Budget
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@SuppressLint("SimpleDateFormat")
fun getMonth():String{
  val month = Calendar.getInstance()
  val format = SimpleDateFormat("MMMM")
 return format.format(month.time).uppercase(Locale.ROOT)

 class DiffCallBack<T>:ItemCallback<DiffCallBack<T>>(){
        override fun areItemsTheSame(oldItem: DiffCallBack<T>, newItem: DiffCallBack<T>): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(
            oldItem: DiffCallBack<T>,
            newItem: DiffCallBack<T>
        ): Boolean {
        return    oldItem == newItem
        }

    }
}