package com.example.worldskillsshop.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.util.ArrayList

class AdsDBManager(val context: Context) {

    val AdsDBHelper = com.example.worldskillsshop.db.AdsDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = AdsDBHelper.writableDatabase
    }

    fun insertToDb(COLUMN_titleADS:String, PriceADS:String, Description:String, Phone: String, AddImage:String, AddImage_1:String,AddImage_2:String,AddImage_3:String, Time:String){
        val values = ContentValues().apply {

            put(AdsDBNameClass.COLUMN_titleADS, COLUMN_titleADS)
            put(AdsDBNameClass.PriceADS, PriceADS)
            put(AdsDBNameClass.Description, Description)
            put(AdsDBNameClass.Phone, Phone)
            put(AdsDBNameClass.Time, Time)
            put(AdsDBNameClass.AddImageA, AddImage)
            put(AdsDBNameClass.AddImageB, AddImage_1)
            put(AdsDBNameClass.AddImageC, AddImage_2)
            put(AdsDBNameClass.AddImageD, AddImage_3)
        }

        db?.insert(AdsDBNameClass.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun readDbDate(id:String, SearchText:String): ArrayList<String> {
        val dataList = ArrayList<String>()
        val selector = "${AdsDBNameClass.COLUMN_titleADS} like ?"
        var cursor:Cursor? =null

        if (SearchText != "0"){
             cursor = db?.query(
                AdsDBNameClass.TABLE_NAME, null,selector, arrayOf(SearchText),
                null,null, null)
        }else if (SearchText =="0"){
             cursor = db?.query(
                AdsDBNameClass.TABLE_NAME, null,null, null,
                null,null, null)
        }


        while (cursor?.moveToNext()!!){
            val COLUMN_titleADS = cursor.getString(cursor.getColumnIndex(AdsDBNameClass.COLUMN_titleADS))
            val AddImage = cursor.getString(cursor.getColumnIndex(AdsDBNameClass.AddImageA))
            val AddImage_1 = cursor.getString(cursor.getColumnIndex(AdsDBNameClass.AddImageB))
            val AddImage_2 = cursor.getString(cursor.getColumnIndex(AdsDBNameClass.AddImageC))
            val AddImage_3 = cursor.getString(cursor.getColumnIndex(AdsDBNameClass.AddImageD))
            val PriceADS = cursor.getString(cursor.getColumnIndex(AdsDBNameClass.PriceADS))
            val Description = cursor.getString(cursor.getColumnIndex(AdsDBNameClass.Description))
            val Phone = cursor.getString(cursor.getColumnIndex(AdsDBNameClass.Phone))
            val ID = cursor.getString(cursor.getColumnIndex(AdsDBNameClass.ID))
            val Time = cursor.getString(cursor.getColumnIndex(AdsDBNameClass.Time))

            val item = ListItem()
            item.ID = ID
            item.Phone = Phone
            item.AddImage = AddImage
            item.AddImage_1 = AddImage_1
            item.AddImage_2 = AddImage_2
            item.AddImage_3 = AddImage_3
            item.PriceADS = PriceADS
            item.Description = Description
            item.COLUMN_titleADS = COLUMN_titleADS
            item.Time = Time

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
            }
        }
        cursor.close()
        return dataList

    }

    fun closeDb(){
        AdsDBHelper.close()

    }
}