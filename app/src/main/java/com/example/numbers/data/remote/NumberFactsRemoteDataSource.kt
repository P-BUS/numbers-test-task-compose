package com.example.numbers.data.remote

import com.example.numbers.data.NumberFactDto
import javax.inject.Inject

class NumberFactsRemoteDataSource @Inject constructor(
    private val factsApiService: NumberApiService
) {
    suspend fun getNumberFact(number: String): ApiResult<NumberFactDto> =
        handleApiResponse {
            factsApiService.getNumberFact(number = number)
        }

    suspend fun getRandomNumberFact(): ApiResult<NumberFactDto> =
        handleApiResponse {
            factsApiService.getRandomNumberFact()
        }
}