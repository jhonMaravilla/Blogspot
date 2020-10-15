package com.kotlin.blogspot.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.blogspot.model.AccountProperties
import com.kotlin.blogspot.model.AuthToken

@Database(entities = [AuthToken::class, AccountProperties::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getAuthTokenDao(): AuthTokenDao

    abstract fun getAccountPropertiesDao(): AccountPropertiesDao

    companion object{
        val DATABASE_NAME: String = "app_db"
    }

}