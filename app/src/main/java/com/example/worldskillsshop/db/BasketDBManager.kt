package com.example.worldskillsshop.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BasketDBManager(context: Context) {

    val Helper = BasketDBHelper(context)
    var db:SQLiteDatabase? = null

    fun openDb(){
        db = Helper.writableDatabase
    }

    suspend fun insertDb(COLUMN_titleADS:String, PriceADS:String, Description:String, Phone: String,
                         AddImage:String, AddImage_1:String, AddImage_2:String, AddImage_3:String,
                         Time:String, Viewing:String) = withContext(Dispatchers.IO){

        val values = ContentValues().apply {

            put(BasketDBNameClass.COLUMN_titleADS, COLUMN_titleADS)
            put(BasketDBNameClass.PriceADS, PriceADS)
            put(BasketDBNameClass.Description, Description)
            put(BasketDBNameClass.Phone, Phone)
            put(BasketDBNameClass.Time, Time)
            put(BasketDBNameClass.AddImageA, AddImage)
            put(BasketDBNameClass.AddImageB, AddImage_1)
            put(BasketDBNameClass.AddImageC, AddImage_2)
            put(BasketDBNameClass.AddImageD, AddImage_3)
            put(BasketDBNameClass.Viewing, Viewing)
        }
        db?.insert(BasketDBNameClass.TABLE_NAME,null,values)
    }

    @SuppressLint("Range", "Recycle")
     fun readDbDate(id:String):ArrayList<String>{
        val dataList = ArrayList<String>()

        val cursor = db?.query(
            BasketDBNameClass.TABLE_NAME, null,null, null,
            null,null, null)

        while (cursor?.moveToNext()!!){
            val COLUMN_titleADS = cursor.getString(cursor.getColumnIndex(BasketDBNameClass.COLUMN_titleADS))
            val AddImage = cursor.getString(cursor.getColumnIndex(BasketDBNameClass.AddImageA))
            val AddImage_1 = cursor.getString(cursor.getColumnIndex(BasketDBNameClass.AddImageB))
            val AddImage_2 = cursor.getString(cursor.getColumnIndex(BasketDBNameClass.AddImageC))
            val AddImage_3 = cursor.getString(cursor.getColumnIndex(BasketDBNameClass.AddImageD))
            val PriceADS = cursor.getString(cursor.getColumnIndex(BasketDBNameClass.PriceADS))
            val Description = cursor.getString(cursor.getColumnIndex(BasketDBNameClass.Description))
            val Phone = cursor.getString(cursor.getColumnIndex(BasketDBNameClass.Phone))
            val ID = cursor.getString(cursor.getColumnIndex(BasketDBNameClass.ID))
            val Time = cursor.getString(cursor.getColumnIndex(BasketDBNameClass.Time))
            val Viewing = cursor.getString(cursor.getColumnIndex(BasketDBNameClass.Viewing))
            Log.d("sdfssA",ID.toString())

            when(id){
                "Id" -> dataList.add(ID)
                "Phone" -> dataList.add(Phone)
                "PriceADS" -> dataList.add(PriceADS)
                "Description" -> dataList.add(Description)
                "COLUMN_titleADS" -> dataList.add(COLUMN_titleADS)
                "AddImage" -> dataList.add(AddImage)
                "AddImage_1" -> dataList.add(AddImage_1)
                "AddImage_2" -> dataList.add(AddImage_2)
                "AddImage_3" -> dataList.add(AddImage_3)
                "Time" -> dataList.add(Time)
                "Viewing" -> dataList.add(Viewing)
            }
        }
        cursor.close()
        return dataList
    }


     fun removeDb(id:String){

         val selection = BaseColumns._ID+"=$id"
        db?.delete(BasketDBNameClass.TABLE_NAME,selection,null)
    }

    fun closeDb(){
        db?.close()
    }
}