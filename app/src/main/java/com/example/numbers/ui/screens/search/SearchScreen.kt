package com.example.numbers.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.numbers.R
import com.example.numbers.ui.navigation.NavigationAction
import com.example.numbers.ui.screens.components.BaseOutlinedButton
import com.example.numbers.ui.screens.components.DefaultInputField
import com.example.numbers.ui.screens.components.FactItem
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    navigateToNumberFact: (number: String, fact: String) -> Unit,
    viewModel: SearchViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiAction = viewModel.uiAction

    // simple solution for small app to not create app-event-effect
    LaunchedEffect(uiState) {
        when (val action = uiState.navigationAction) {
            is NavigationAction.NavigateToNumberFact -> navigateToNumberFact(
                action.numberFact.number,
                action.numberFact.fact,
            )

            else -> {}
        }
        viewModel.handleAction()
    }

    if (uiState.isLoading) {
        // TODO: to create loading layout
    } else {
        SearchLayout(
            uiState = uiState,
            uiAction = uiAction,
        )
    }
}

@Composable
fun SearchLayout(
    uiState: SearchUiState,
    uiAction: (SearchAction) -> Unit,
) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var number by remember { mutableStateOf<String>("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            DefaultInputField(
                onInputComplete = {
                    scope.launch {
                        listState.animateScrollToItem(uiState.factsList.lastIndex)
                    }
                },
                onValueChange = { newValue ->
                    number = newValue
                },
                textHint = stringResource(R.string.enter_the_number)
            )
            BaseOutlinedButton(
                buttonText = stringResource(R.string.get_fact),
                onButtonClick = {
                    uiAction(SearchAction.OnNumberSubmit(number))
                    scope.launch {
                        listState.animateScrollToItem(uiState.factsList.lastIndex)
                    }
                },
                isButtonEnabled = number.isNotEmpty()
            )
            BaseOutlinedButton(
                buttonText = stringResource(R.string.get_fact_random),
                onButtonClick = {
                    uiAction(SearchAction.OnRandomFactClick)
                    scope.launch {
                        listState.animateScrollToItem(uiState.factsList.lastIndex)
                    }
                }
            )
            LazyColumn(
                state = listState,
                reverseLayout = true
            ) {
                items(uiState.factsList) { item ->
                    FactItem(
                        number = item.number,
                        fact = item.fact,
                        onItemClick = { uiAction(SearchAction.OnItemClick(item)) }
                    )
                }
            }
        }
    }
}