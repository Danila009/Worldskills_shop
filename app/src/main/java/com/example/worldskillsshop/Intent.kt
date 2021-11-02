package com.example.worldskillsshop

import android.content.Context

class Intent {

    fun open(con:Context){
        val Intent = android.content.Intent(con, addingAdsA::class.java).apply{


        }
        con.startActivity(Intent)
    }
}