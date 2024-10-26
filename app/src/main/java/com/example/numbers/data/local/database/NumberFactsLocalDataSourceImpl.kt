package com.example.numbers.data.local.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NumberFactsLocalDataSourceImpl @Inject constructor(
    private val database: FactsDao
) : NumberFactsLocalDataSource {
    override fun getAllFacts(): Flow<List<NumberFactEntity>> =
        database.getAllFacts()

    override suspend fun insertAll(fact: NumberFactEntity) =
        database.insertAll(fact)
}