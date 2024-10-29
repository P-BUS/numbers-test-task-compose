package com.example.numbers.presentation.navigation

import com.example.numbers.domain.model.NumberFact

sealed class NavigationAction {
    data class NavigateToNumberFact(val numberFact: NumberFact) : NavigationAction()
    data object GoBack : NavigationAction()
    data object None : NavigationAction()
}