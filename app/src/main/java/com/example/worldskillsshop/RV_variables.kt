package com.example.worldskillsshop

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RV_variables(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var but: Button? = null
    init {
        but = itemView.findViewById(R.id.basket_but)
    }
}