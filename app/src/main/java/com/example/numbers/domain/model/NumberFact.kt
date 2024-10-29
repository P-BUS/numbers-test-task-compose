package com.example.numbers.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NumberFact(
    val number: String,
    val fact: String
)
