package com.example.numbers.ui.screens.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numbers.domain_doineedthis.model.NumberFact
import com.example.numbers.domain_doineedthis.model.NumberFactsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

const val TAG = "FactsViewModel"

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: NumberFactsRepository
) : ViewModel() {

    val numberFacts: SharedFlow<List<NumberFact>> =
        repository.factsStream
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = listOf()
            )

    private val _currentFact = MutableSharedFlow<NumberFact>(
        replay = 1,
        extraBufferCapacity = 0,
        BufferOverflow.DROP_OLDEST
    )
    val currentFact: SharedFlow<NumberFact> = _currentFact.asSharedFlow()

    fun retrieveFact(number: String) {
        viewModelScope.launch {
            try {
                repository.retrieveFact(number)
            } catch (exeption: IOException) {
                Log.e(TAG, "IO Exception $exeption, you might not have internet connection")
            }
        }
    }

    fun retrieveRandomFact() {
        viewModelScope.launch {
            try {
                repository.retrieveRandomFact()
            } catch (exeption: IOException) {
                Log.e(TAG, "IO Exception $exeption, you might not have internet connection")
            }
        }
    }

    fun updateCurrentFact(currentFact: NumberFact) {
        viewModelScope.launch {
            _currentFact.emit(currentFact)
        }
    }
}