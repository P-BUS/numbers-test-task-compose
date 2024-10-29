package com.example.numbers.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.numbers.R
import com.example.numbers.presentation.navigation.NavigationAction
import com.example.numbers.presentation.screens.components.BaseOutlinedButton
import com.example.numbers.presentation.screens.components.DefaultInputField
import com.example.numbers.presentation.screens.components.FactItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
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
            .background(color = Color.Black)
            .padding(horizontal = 30.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            DefaultInputField(
                onInputComplete = {
                    scope.scrollToFirstItem(
                        lastIndex = uiState.factsList.lastIndex,
                        listStateProvider = { listState }
                    )
                },
                onValueChange = { newValue ->
                    number = newValue
                },
                textHint = stringResource(R.string.enter_the_number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            BaseOutlinedButton(
                buttonText = stringResource(R.string.get_fact),
                onButtonClick = {
                    uiAction(SearchAction.OnNumberSubmit(number))
                    scope.scrollToFirstItem(
                        lastIndex = uiState.factsList.lastIndex,
                        listStateProvider = { listState }
                    )
                },
                isButtonEnabled = number.isNotEmpty()
            )
            Spacer(modifier = Modifier.height(8.dp))
            BaseOutlinedButton(
                buttonText = stringResource(R.string.get_fact_random),
                onButtonClick = {
                    uiAction(SearchAction.OnRandomFactClick)
                    scope.scrollToFirstItem(
                        lastIndex = uiState.factsList.lastIndex,
                        listStateProvider = { listState }
                    )
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn(
                state = listState,
                reverseLayout = true,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = uiState.factsList) { item ->
                    FactItem(
                        // we can pass number here also but it is already in the fact text
                        fact = item.fact,
                        onItemClick = { uiAction(SearchAction.OnItemClick(item)) }
                    )
                }
            }
        }
    }
}

fun CoroutineScope.scrollToFirstItem(lastIndex: Int, listStateProvider: () -> LazyListState) {
    this.launch {
        delay(500)
        if (lastIndex >= 0) listStateProvider().animateScrollToItem(lastIndex)
    }
}