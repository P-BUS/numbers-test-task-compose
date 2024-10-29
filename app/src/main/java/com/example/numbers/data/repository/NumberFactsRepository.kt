package com.example.numbers.data.repository

import com.example.numbers.domain.model.NumberFact
import kotlinx.coroutines.flow.Flow

interface NumberFactsRepository {

    val factsStream: Flow<List<NumberFact>>

    suspend fun retrieveFact(number: String)

    suspend fun retrieveRandomFact()
}