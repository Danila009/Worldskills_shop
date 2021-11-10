package com.example.worldskillsshop.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.util.ArrayList

class UserDBManager(val context: Context) {
    val AdsDBHelper = com.example.worldskillsshop.db.UserDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = AdsDBHelper.writableDatabase
    }

    fun insertToDb(NAME:String,EMAIL:String,PASSWORD:String){
        val values = ContentValues().apply{
            put(UserDBNameClass.NAME, NAME)
            put(UserDBNameClass.EMAIL, EMAIL)
            put(UserDBNameClass.PASSWORD, PASSWORD)
        }
        db?.insert(UserDBNameClass.TABLE_USER, null, values)
    }

    @SuppressLint("Range")
    fun readDbDate(id:String): ArrayList<String> {
        val dataList = ArrayList<String>()

        val cursor = db?.query(
            UserDBNameClass.TABLE_USER, null, null, null,
            null, null, null)

        while (cursor?.moveToNext()!!){

            val NAME = cursor.getString(cursor.getColumnIndex(UserDBNameClass.NAME))
            val EMAIL = cursor.getString(cursor.getColumnIndex(UserDBNameClass.EMAIL))
            val PASSWORD = cursor.getString(cursor.getColumnIndex(UserDBNameClass.PASSWORD))
            val ID = cursor.getString(cursor.getColumnIndex(UserDBNameClass.ID))

            when(id){
                "NAME" -> dataList.add(NAME)
                "EMAIL" -> dataList.add(EMAIL)
                "PASSWORD" -> dataList.add(PASSWORD)
                "Id" -> dataList.add(ID)
            }
        }
        cursor.close()
        return dataList
    }

    fun closeDb(){
        AdsDBHelper.close()
    }
}