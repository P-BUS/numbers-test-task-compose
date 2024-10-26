package com.example.numbers.data.remote

import com.example.numbers.data.model.NumberFactDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "http://numbersapi.com/"
const val ENDPOINT_RANDOM_NUMBER = "random/math"

interface NumberApiService {
    @GET("{number}")
    suspend fun getNumberFact(
        @Path("number") number: String = ""
    ): Response<NumberFactDto>

    @GET(ENDPOINT_RANDOM_NUMBER)
    suspend fun getRandomNumberFact(): Response<NumberFactDto>
}