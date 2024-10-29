package com.example.numbers.data.model.mapper

import com.example.numbers.data.local.database.NumberFactEntity
import com.example.numbers.data.model.NumberFactDto
import com.example.numbers.domain.model.NumberFact
import javax.inject.Inject

class NumberFactMapper @Inject constructor() {
    fun toDomainModel(factsList: List<NumberFactEntity>): List<NumberFact> {
        return factsList.map {
            NumberFact(
                fact = it.numberFact,
                number = it.number
            )
        }
    }

    fun toDatabaseModel(fact: NumberFactDto): NumberFactEntity {
        return NumberFactEntity(
            numberFact = fact.numberFact,
            number = fact.numberFact.findNumberInSentence()
        )
    }

    private fun String.findNumberInSentence(): String {
        val regex = Regex("\\d+")
        val result = regex.find(this)
        return result?.value.toString()
    }
}