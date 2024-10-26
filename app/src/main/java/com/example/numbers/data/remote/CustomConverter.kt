package com.example.numbers.data.remote

import com.example.numbers.data.model.NumberFactDto
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import javax.inject.Inject

class NumberFactConverterFactory @Inject constructor() : Converter.Factory() {

    fun create(): NumberFactConverterFactory {
        return NumberFactConverterFactory()
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return if (type == NumberFactDto::class.java) {
            Converter { value: ResponseBody ->
                val responseString = value.string().trim()
                NumberFactDto(
                    numberFact = responseString,
                    number = null
                )
            }
        } else {
            null
        }
    }
}