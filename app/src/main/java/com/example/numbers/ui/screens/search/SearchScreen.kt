package com.example.numbers.ui.screens.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.numbers.domain_doineedthis.model.NumberFact

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navigateToNumberFact: (NumberFact) -> Unit,
    viewModel: SearchViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiAction = viewModel.uiAction

    SearchLayout(
        uiState = uiState,
        uiAction = uiAction,
    )
}

@Composable
fun SearchLayout(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
    uiAction: (SearchAction) -> Unit,
) {

}