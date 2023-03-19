package com.jetpack.codechallenge.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.codechallenge.navigation.MovieDetailDirections
import com.jetpack.codechallenge.navigation.SchoolDetailDirections
import com.jetpack.codechallenge.navigation.NavigationManager
import com.jetpack.codechallenge.ui.home.models.SchoolItem
import com.jetpack.codechallenge.usecase.SchoolsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SchoolViewModel(
    private val schoolsUseCase: SchoolsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val navigationManager: NavigationManager,
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

    fun fetchSchoolDetails(id: String) {
        viewModelScope.launch(dispatcher) {
            val movie = schoolsUseCase.fetchSchoolInfo(id)
            _listingUiState.tryEmit(SchoolUiState.DisplaySchoolDetails(movie))
        }
    }

    fun navigateToMovieDetails(movieDetails: SchoolItem) {
        val command = MovieDetailDirections.movieDetails
        command.navigationPath = "movie_details/${movieDetails.dbn}"
        navigationManager.navigate(command)
    }

}