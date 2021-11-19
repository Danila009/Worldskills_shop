package com.example.worldskillsshop.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BasketDBHelper(context: Context): SQLiteOpenHelper(context,BasketDBNameClass.DATABASE_NAME,null,BasketDBNameClass.DATABASE_VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(BasketDBNameClass.CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(BasketDBNameClass.SQL_DELETE_TABLE)
        onCreate(p0)
    }
}