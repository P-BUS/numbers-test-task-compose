package com.example.numbers.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.numbers.ui.screens.numberfact.NumberFactScreen
import com.example.numbers.ui.screens.numberfact.NumberFactViewModel
import com.example.numbers.ui.screens.search.SearchScreen
import com.example.numbers.ui.screens.search.SearchViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        navController = navController,
        startDestination = Search
    ) {
        composable<Search> {
            val searchViewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(
                viewModel = searchViewModel,
                navigateToNumberFact = { number ->
                    navController.navigate(NumberFact(number))
                }
            )
        }
        composable<NumberFact> { backStackEntry ->
            val numberFactViewModel = hiltViewModel<NumberFactViewModel>()
            val numberFact: NumberFact = backStackEntry.toRoute()
            NumberFactScreen(
                viewModel = numberFactViewModel,
                number = numberFact.number,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}