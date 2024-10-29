package com.example.numbers.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object NavSearchScreen

@Serializable
data class NavNumberFactScreen(val number: String, val fact: String)