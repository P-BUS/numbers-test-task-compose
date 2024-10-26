package com.example.numbers.data.remote

import com.example.numbers.data.model.NumberFactDto

interface NumberFactsRemoteDataSource {

    suspend fun getNumberFact(number: String): ApiResult<NumberFactDto>

    suspend fun getRandomNumberFact(): ApiResult<NumberFactDto>
}