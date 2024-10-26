package com.example.numbers.data.local.database

import kotlinx.coroutines.flow.Flow

interface NumberFactsLocalDataSource {

    fun getAllFacts(): Flow<List<NumberFactEntity>>

    suspend fun insertAll(fact: NumberFactEntity)
}