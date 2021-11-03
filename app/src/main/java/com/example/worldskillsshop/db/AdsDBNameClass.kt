package com.example.worldskillsshop.db

import android.provider.BaseColumns

object AdsDBNameClass: BaseColumns {

    const val TABLE_NAME = "my_table"

    const val AddImage = "Photo"
    const val AddImage_1 = "Photo"
    const val AddImage_2 = "Photo"
    const val AddImage_3 = "Photo"
    const val PriceADS = "price"
    const val COLUMN_titleADS = "title"
    const val Phone = "Phone"
    const val Description = "Description"


    const val DATABASE_VERSION = 5  //версия
    const val DATABASE_NAME = "MyLessonDb.db"  //Название

    const val CREAT_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME ("+
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$PriceADS TEXT, $AddImage TEXT, $COLUMN_titleADS TEXT, $Phone TEXT, $Description TEXT, $AddImage_1 TEXT, $AddImage_2 TEXT, $AddImage_3 TEXT )"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}