package com.example.worldskillsshop.db

import android.provider.BaseColumns

object BasketDBNameClass {

    const val TABLE_NAME = "Basket"

    const val AddImageA = "PhotoA"
    const val AddImageB = "PhotoB"
    const val AddImageC = "PhotoC"
    const val AddImageD = "PhotoD"
    const val PriceADS = "price"
    const val COLUMN_titleADS = "title"
    const val Phone = "Phone"
    const val Description = "Description"
    const val Time = "time"
    const val Viewing = "viewing"
    const val ID = BaseColumns._ID

    const val DATABASE_VERSION = 1  //версия
    const val DATABASE_NAME = "BasketDb.db"  //Название

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME(" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY,${PriceADS} TEXT,${Description} TEXT,${Phone} TEXT,${COLUMN_titleADS} TEXT,${AddImageA} TEXT,${AddImageB} TEXT,${AddImageC} TEXT, $AddImageD TEXT, $Time TEXT, $Viewing TEXT)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}