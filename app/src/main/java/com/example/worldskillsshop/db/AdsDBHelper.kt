package com.example.worldskillsshop.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdsDBHelper(context: Context): SQLiteOpenHelper(context, AdsDBNameClass.DATABASE_NAME,null, AdsDBNameClass.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(AdsDBNameClass.CREAT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(AdsDBNameClass.SQL_DELETE_TABLE)
        onCreate(db)
    }

}