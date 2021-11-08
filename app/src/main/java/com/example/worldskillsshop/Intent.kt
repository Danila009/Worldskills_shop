package com.example.worldskillsshop

import android.content.Context
import android.view.View
import android.widget.SearchView
import androidx.appcompat.view.menu.ActionMenuItemView

class Intent {

    fun open(con:Context){
        val Intent = android.content.Intent(con, addingAdsA::class.java).apply{


        }
        con.startActivity(Intent)
    }
}