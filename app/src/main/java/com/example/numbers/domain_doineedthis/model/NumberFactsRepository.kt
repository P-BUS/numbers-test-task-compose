package com.example.numbers.domain_doineedthis.model

import kotlinx.coroutines.flow.Flow

interface NumberFactsRepository {

    val factsStream: Flow<List<NumberFactModel>>

    suspend fun retrieveFact(number: String)

    suspend fun retrieveRandomFact()
}