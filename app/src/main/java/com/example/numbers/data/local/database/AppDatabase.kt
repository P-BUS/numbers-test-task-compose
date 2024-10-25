package com.example.numbers.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NumberFactEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun factsDao(): FactsDao
}