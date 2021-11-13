package com.example.worldskillsshop

import android.content.Context
import android.content.Intent.ACTION_DIAL
import android.net.Uri
import android.view.View
import android.widget.SearchView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat.startActivity

class Intent {

    fun open(con:Context){
        val Intent = android.content.Intent(con, addingAdsA::class.java).apply{
        }
        con.startActivity(Intent)
    }
}