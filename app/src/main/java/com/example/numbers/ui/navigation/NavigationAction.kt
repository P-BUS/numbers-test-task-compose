package com.example.numbers.ui.navigation

sealed class NavigationAction {
    data class NavigateToNumberFact(val number: Int) : NavigationAction()
    data object GoBack : NavigationAction()
    data object None : NavigationAction()
}