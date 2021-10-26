package com.example.worldskillsshop.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.util.ArrayList

class MuDbManager(context: Context) {

    val MuDbHelper = MuDbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = MuDbHelper.writableDatabase
    }

    fun insertToDb(productPhoto:String, price:String, title:String){
        val values = ContentValues().apply {
            put(MyDbNameClass.productPhoto, productPhoto)
            put(MyDbNameClass.COLUMN_NAME_price, price)
            put(MyDbNameClass.COLUMN_NAME_title, title)
        }

        db?.insert(MyDbNameClass.TABLE_NAME, null, values)
    }


    fun readDbDate():ArrayList<String>{

        val dataList = ArrayList<String>()

        val cursor = db?.query(
            MyDbNameClass.TABLE_NAME, null,null, null,
            null,null, null)


            while (cursor?.moveToNext()!!){
                val dataText = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE))
                dataList.add(dataText.toString())
            }
        cursor.close()
        return dataList

    }


    fun closeDb(){
        MuDbHelper.close()
    }
}