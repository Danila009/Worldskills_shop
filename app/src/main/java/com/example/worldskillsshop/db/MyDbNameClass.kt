package com.example.worldskillsshop.db

import android.provider.BaseColumns

object MyDbNameClass: BaseColumns
{
    const val TABLE_NAME = "my_table"
    const val COLUMN_NAME_TITLE = "title"
    const val productPhoto = "productPhoto"
    const val COLUMN_NAME_price = "price"
    const val COLUMN_NAME_title = "title"

    const val DATABASE_VERSION = 1  //версия
    const val DATABASE_NAME = "Registration.db"  //Название

    const val CREAT_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME ("+
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_price TEXT, $productPhoto TEXT, $COLUMN_NAME_title TEXT"



    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}