package com.example.numbers.presentation.screens.search

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numbers.data.repository.NumberFactsRepository
import com.example.numbers.domain.model.NumberFact
import com.example.numbers.presentation.navigation.NavigationAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "FactsViewModel"

@Immutable
data class SearchUiState(
    val factsList: List<NumberFact> = emptyList(),
    val currentFact: NumberFact = NumberFact("", ""),

    val isLoading: Boolean = true,
    val error: String? = null,
    val navigationAction: NavigationAction = NavigationAction.None
)

sealed class SearchAction {
    data class OnNumberSubmit(val number: String) : SearchAction()
    data class OnItemClick(val item: NumberFact) : SearchAction()
    data object OnRandomFactClick : SearchAction()
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: NumberFactsRepository
) : ViewModel() {

    private val _factsList: StateFlow<List<NumberFact>> =
        repository.factsStream
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = listOf()
            )
    private val _isLoading = MutableStateFlow(false)
    private val _error = MutableStateFlow(null) // not used here but it is "must have" for an app
    private val _navigationAction =
        MutableStateFlow<NavigationAction>(NavigationAction.None) // simple solution to not create more complex navigation with manager

    val uiState: StateFlow<SearchUiState> = combine(
        _factsList,
        _isLoading,
        _error,
        _navigationAction
    ) { factsList, isLoading, error, navigationAction ->
        SearchUiState(
            factsList = factsList,
            isLoading = isLoading,
            error = error,
            navigationAction = navigationAction,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SearchUiState()
    )

    val uiAction: (SearchAction) -> Unit = { action ->
        when (action) {
            is SearchAction.OnNumberSubmit -> retrieveFact(action.number)
            is SearchAction.OnRandomFactClick -> retrieveRandomFact()
            is SearchAction.OnItemClick -> _navigationAction.update {
                NavigationAction.NavigateToNumberFact(action.item)
            }
        }
    }

    private fun retrieveFact(number: String) {
        viewModelScope.launch {
            // usually we retrieve it from UseCase
            // TODO: to add loading state handle
            repository.retrieveFact(number)
        }
    }

    private fun retrieveRandomFact() {
        viewModelScope.launch {
            // usually we retrieve it from UseCase
            // TODO: to add loading state handle
            repository.retrieveRandomFact()
        }
    }

    fun handleAction() {
        _navigationAction.update { NavigationAction.None }
    }
}