package com.example.worldskillsshop.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.ArrayList

class UserDBManager(val context: Context) {
    val AdsDBHelper = com.example.worldskillsshop.db.UserDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = AdsDBHelper.writableDatabase
    }

    suspend fun insertToDb(NAME:String,EMAIL:String,PASSWORD:String,
                           IMAGE:String, PRICE:String, date:String) = withContext(Dispatchers.IO){
        val values = ContentValues().apply{
            put(UserDBNameClass.NAME, NAME)
            put(UserDBNameClass.EMAIL, EMAIL)
            put(UserDBNameClass.PASSWORD, PASSWORD)
            put(UserDBNameClass.IMAGE, IMAGE)
            put(UserDBNameClass.PRICE, PRICE)
            put(UserDBNameClass.date, date)
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
            val IMAGE = cursor.getString(cursor.getColumnIndex(UserDBNameClass.IMAGE))
            val PRICE = cursor.getString(cursor.getColumnIndex(UserDBNameClass.PRICE))

            when(id){
                "NAME" -> dataList.add(NAME)
                "EMAIL" -> dataList.add(EMAIL)
                "PASSWORD" -> dataList.add(PASSWORD)
                "Id" -> dataList.add(ID)
                "IMAGE" -> dataList.add(IMAGE)
                "PRICE" -> dataList.add(PRICE)
            }
        }
        cursor.close()
        return dataList
    }

    fun closeDb(){
        AdsDBHelper.close()
    }
}