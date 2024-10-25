package com.example.numbers.di

import android.content.Context
import androidx.room.Room
import com.example.numbers.data.local.database.AppDatabase
import com.example.numbers.data.local.database.FactsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "facts_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object DaoModule {
        @Provides
        fun provideFactsDao(database: AppDatabase): FactsDao {
            return database.factsDao()
        }
    }
}