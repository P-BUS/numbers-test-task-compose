package com.example.numbers.data.repository

import android.util.Log
import com.example.numbers.data.remote.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val TAG = "NumberRepository"

class NumberFactsRepository @Inject constructor(
    private val network: NumberFactsRemoteDataSource,
    private val database: NumberFactsLocalDataSource,
) {

    val factsStream: Flow<List<NumberFactModel>> =
        database.getAllFacts()
            .filterNotNull()
            .map {
                it.asDomainModel()
            }

    suspend fun retrieveFact(number: String) {
        withContext(dispatchers.io) {
            when (val response = network.getNumberFact(number)) {
                is ApiResult.Success -> {
                    val numberFact = response.data
                    database.insertAll(numberFact.asDatabaseModel())
                }

                is ApiResult.Error -> Log.e(TAG, "${response.code} ${response.message}")
                is ApiResult.Exception -> Log.e(TAG, "${response.e.cause} ${response.e.message}")
            }
        }
    }

    suspend fun retrieveRandomFact() {
        withContext(dispatchers.io) {
            when (val response = network.getRandomNumberFact()) {
                is ApiResult.Success -> {
                    val numberFact = response.data
                    database.insertAll(numberFact.asDatabaseModel())
                }

                is ApiResult.Error -> Log.e(TAG, "${response.code} ${response.message}")
                is ApiResult.Exception -> Log.e(TAG, "${response.e.cause} ${response.e.message}")
            }
        }
    }
}