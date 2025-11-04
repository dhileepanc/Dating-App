package com.datingapp.app.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.datingapp.app.utils.Constants
import dagger.hilt.android.internal.Contexts

class DbHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "db"
        private const val DATABASE_VERSION ="1"

        private const val TABLE_RECENT_CHATS = "recent_chats"

        private const val TABLE_MESSAGES = "messages"
        private const val TABLE_USER_DETAIL ="user_detail"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_USER_DETAIL_TABLE = "CREATE TABLE"+ TABLE_USER_DETAIL +"("+ Constants.TAG_USER_ID+"TEXT"+Constants.TAG_USER_NAME+"TEXT" +")";
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {

    }
}