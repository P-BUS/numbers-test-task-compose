package com.example.numbers.ui.screens.search

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navigateToNumberFact: (Int) -> Unit,
    viewModel: SearchViewModel
) {
    val fact = viewModel.currentFact.collectAsStateWithLifecycle("kuku")
    LaunchedEffect(Unit) {
        viewModel.retrieveRandomFact()
    }
    Text(text = fact.value.toString())
}