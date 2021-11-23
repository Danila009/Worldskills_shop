package com.example.worldskillsshop

import android.content.Context
import com.example.worldskillsshop.ui.notifications.NotificationsFragment

fun openAdsA(con:Context){
    val Intent = android.content.Intent(con, addingAdsA::class.java)
    con.startActivity(Intent)
}

fun openHome(con:Context){
    val Intent = android.content.Intent(con, NotificationsFragment::class.java)
    con.startActivity(Intent)
}