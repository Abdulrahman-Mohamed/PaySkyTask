package com.example.payskytask.appFeature.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.payskytask.appFeature.data.model.DataModelItem

@Database(entities = [DataModelItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}