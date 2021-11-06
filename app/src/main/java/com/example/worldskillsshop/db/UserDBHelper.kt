package com.example.worldskillsshop.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDBHelper(context: Context): SQLiteOpenHelper(context, UserDBNameClass.DATABASE_NAME_USER,null, UserDBNameClass.DATABASE_VERSION_USER) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(UserDBNameClass.DATABASE_NAME_USER)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(UserDBNameClass.DATABASE_NAME_USER)
        onCreate(p0)
    }
}