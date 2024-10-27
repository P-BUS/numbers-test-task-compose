package com.example.numbers.domain_doineedthis.model

import kotlinx.serialization.Serializable

@Serializable
data class NumberFact(
    val number: String,
    val fact: String
)
