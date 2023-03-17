package com.jetpack.codechallenge.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.codechallenge.usecase.SchoolsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SchoolViewModel(
    private val schoolsUseCase: SchoolsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _listingUiState = MutableStateFlow<SchoolUiState>(SchoolUiState.Loading)
    val listingUiState get() = _listingUiState

    fun loadSchools() {
        viewModelScope.launch(dispatcher) {
            val movies = schoolsUseCase.fetchSchools()
            val state =
                if (movies.isEmpty()) SchoolUiState.Empty else SchoolUiState.DisplaySchools(data = movies)
            _listingUiState.tryEmit(state)
        }
    }

    fun navigateToMovieDetails(it: Any) {
        TODO("Not yet implemented")
    }

}