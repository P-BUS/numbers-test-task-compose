package com.example.numbers.data.repository

import android.util.Log
import com.example.numbers.data.local.database.NumberFactsLocalDataSource
import com.example.numbers.data.model.mapper.NumberFactMapper
import com.example.numbers.data.remote.ApiResult
import com.example.numbers.data.remote.NumberFactsRemoteDataSource
import com.example.numbers.di.annutations.Dispatcher
import com.example.numbers.di.annutations.NumberFactDispatchers
import com.example.numbers.domain.model.NumberFact
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val TAG = "NumberRepository"

class NumberFactsRepositoryImpl @Inject constructor(
    private val network: NumberFactsRemoteDataSource,
    private val database: NumberFactsLocalDataSource,
    private val mapper: NumberFactMapper,
    @Dispatcher(NumberFactDispatchers.IO) private val dispatcherIO: CoroutineDispatcher,
) : NumberFactsRepository {

    override val factsStream: Flow<List<NumberFact>> =
        database.getAllFacts()
            .filterNotNull()
            .map {
                mapper.toDomainModel(it)
            }

    override suspend fun retrieveFact(number: String) {
        withContext(dispatcherIO) {
            when (val response = network.getNumberFact(number)) {
                is ApiResult.Success -> {
                    val numberFact = response.data
                    database.insertAll(
                        mapper.toDatabaseModel(numberFact)
                    )
                }
                // TODO: to pass errors to screen level
                is ApiResult.Error -> Log.e(TAG, "${response.code} ${response.message}")
                is ApiResult.Exception -> Log.e(TAG, "${response.e.cause} ${response.e.message}")
            }
        }
    }

    override suspend fun retrieveRandomFact() {
        withContext(dispatcherIO) {
            when (val response = network.getRandomNumberFact()) {
                is ApiResult.Success -> {
                    val numberFact = response.data
                    database.insertAll(
                        mapper.toDatabaseModel(numberFact)
                    )
                }
                // TODO: to pass errors to screen level
                is ApiResult.Error -> Log.e(TAG, "${response.code} ${response.message}")
                is ApiResult.Exception -> Log.e(TAG, "${response.e.cause} ${response.e.message}")
            }
        }
    }
}