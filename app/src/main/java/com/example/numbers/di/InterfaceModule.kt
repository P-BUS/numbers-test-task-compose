package com.example.numbers.di

import com.example.numbers.data.local.database.NumberFactsLocalDataSource
import com.example.numbers.data.local.database.NumberFactsLocalDataSourceImpl
import com.example.numbers.data.remote.NumberFactsRemoteDataSource
import com.example.numbers.data.remote.NumberFactsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InterfacesModule {
    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        numberFactsRemoteDataSourceImpl: NumberFactsRemoteDataSourceImpl,
    ): NumberFactsRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        numberFactsLocalDataSourceImpl: NumberFactsLocalDataSourceImpl,
    ): NumberFactsLocalDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        numberFactsRemoteDataSourceImpl: NumberFactsRemoteDataSourceImpl,
    ): NumberFactsRemoteDataSource
}