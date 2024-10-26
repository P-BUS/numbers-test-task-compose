package com.example.numbers.ui.navigation

import com.example.numbers.domain_doineedthis.model.NumberFact

sealed class NavigationAction {
    data class NavigateToNumberFact(val number: NumberFact) : NavigationAction()
    data object GoBack : NavigationAction()
    data object None : NavigationAction()
}