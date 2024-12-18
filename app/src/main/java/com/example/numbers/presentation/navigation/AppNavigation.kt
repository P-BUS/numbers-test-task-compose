package com.example.numbers.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.numbers.presentation.screens.numberfact.NumberFactScreen
import com.example.numbers.presentation.screens.numberfact.NumberFactViewModel
import com.example.numbers.presentation.screens.search.SearchScreen
import com.example.numbers.presentation.screens.search.SearchViewModel

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        navController = navController,
        startDestination = NavSearchScreen
    ) {
        composable<NavSearchScreen> {
            val searchViewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(
                viewModel = searchViewModel,
                navigateToNumberFact = { number, fact ->
                    navController.navigate(NavNumberFactScreen(number, fact)) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<NavNumberFactScreen> { backStackEntry ->
            val numberFactViewModel = hiltViewModel<NumberFactViewModel>()
            val numberFactScreen: NavNumberFactScreen = backStackEntry.toRoute()
            NumberFactScreen(
                viewModel = numberFactViewModel,
                number = numberFactScreen.number,
                fact = numberFactScreen.fact,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}