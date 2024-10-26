package com.example.numbers.di

import com.example.numbers.di.annutations.Dispatcher
import com.example.numbers.di.annutations.NumberFactDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Provides
    @Dispatcher(NumberFactDispatchers.IO)
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(NumberFactDispatchers.MAIN)
    fun mainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Dispatcher(NumberFactDispatchers.DEFAULT)
    fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}