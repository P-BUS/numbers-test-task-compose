package com.example.numbers.di

import com.example.numbers.data.remote.NumberFactsRemoteDataSource
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
    abstract fun bindConfigAPI(
        numberFactsRemoteDataSource: NumberFactsRemoteDataSource,
    ): NumberFactsRemoteDataSource
}