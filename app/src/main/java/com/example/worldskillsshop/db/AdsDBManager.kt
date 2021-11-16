package com.example.worldskillsshop.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.ArrayList

class AdsDBManager(val context: Context) {

    val AdsDBHelper = com.example.worldskillsshop.db.AdsDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = AdsDBHelper.writableDatabase
    }

     suspend fun insertToDb(COLUMN_titleADS:String, PriceADS:String, Description:String, Phone: String,
                            AddImage:String, AddImage_1:String, AddImage_2:String, AddImage_3:String,
                            Time:String, Viewing:String) = withContext(Dispatchers.IO){
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
            put(AdsDBNameClass.Viewing, Viewing)
        }

        db?.insert(AdsDBNameClass.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    suspend fun readDbDate(id:String, SearchText:String): ArrayList<String> = withContext(Dispatchers.IO) {
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
            val Viewing = cursor.getString(cursor.getColumnIndex(AdsDBNameClass.Viewing))

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
            item.Viewing = Viewing

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
        return@withContext dataList

    }

    suspend fun updateItem(COLUMN_titleADS:String, PriceADS:String, Description:String, Phone: String,
                           AddImage:String, AddImage_1:String,AddImage_2:String,AddImage_3:String,
                           Time:String, Id:Int, Viewing:String) = withContext(Dispatchers.IO){
        val selection = BaseColumns._ID + "=$Id"
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
            put(AdsDBNameClass.Viewing, Viewing)
        }

        db?.update(AdsDBNameClass.TABLE_NAME,values, selection,null)
    }

    fun closeDb(){
        AdsDBHelper.close()
    }
}