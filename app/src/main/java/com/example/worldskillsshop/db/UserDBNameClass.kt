package com.example.worldskillsshop.db

import android.provider.BaseColumns

object UserDBNameClass {

    const val TABLE_USER = "User"

    const val NAME = "name"
    const val EMAIL = "email"
    const val PASSWORD = "password"
    const val PRICE = "price"
    const val IMAGE = "image"

    const val ID = BaseColumns._ID

    const val DATABASE_VERSION = 8  //версия
    const val DATABASE_NAME = "UserDb.db"  //Название

    const val CREAT_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_USER("+
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$NAME TEXT,$EMAIL TEXT,$PASSWORD TEXT,$PRICE TEXT,$IMAGE TEXT)"
    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_USER"
}