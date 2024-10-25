package com.example.numbers.data.local.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NumberFactsLocalDataSource @Inject constructor(
    private val database: FactsDao
) {
    fun getAllFacts(): Flow<List<NumberFactEntity>> =
        database.getAllFacts()

    suspend fun insertAll(fact: NumberFactEntity) =
        database.insertAll(fact)
}