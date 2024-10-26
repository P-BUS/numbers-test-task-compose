package com.example.numbers.data.remote

import com.example.numbers.data.model.NumberFactDto
import javax.inject.Inject

class NumberFactsRemoteDataSourceImpl @Inject constructor(
    private val factsApiService: NumberApiService
) : NumberFactsRemoteDataSource {
    override suspend fun getNumberFact(number: String): ApiResult<NumberFactDto> =
        handleApiResponse {
            factsApiService.getNumberFact(number = number)
        }

    override suspend fun getRandomNumberFact(): ApiResult<NumberFactDto> =
        handleApiResponse {
            factsApiService.getRandomNumberFact()
        }
}