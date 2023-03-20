package com.jetpack.codechallenge.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.codechallenge.navigation.SchoolInfoDirections
import com.jetpack.codechallenge.navigation.NavigationManager
import com.jetpack.codechallenge.ui.home.models.SchoolItem
import com.jetpack.codechallenge.usecase.SchoolsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * SchoolViewModel used to fetch the school list and info date from schoolsUseCase
 */
class SchoolViewModel(
    private val schoolsUseCase: SchoolsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val navigationManager: NavigationManager,
) : ViewModel() {

    private val _listingUiState = MutableStateFlow<SchoolUiState>(SchoolUiState.Loading)
    val listingUiState get() = _listingUiState

    //Orientation mode rather than fetching data from api, can be retrieve standard way
    fun loadSchools() {
        viewModelScope.launch(dispatcher) {
            val schools = schoolsUseCase.fetchSchools()
            val state =
                if (schools.isEmpty()) SchoolUiState.Empty else SchoolUiState.DisplaySchools(data = schools)
            _listingUiState.tryEmit(state)
        }
    }

    fun fetchSchoolDetails(id: String) {
        viewModelScope.launch(dispatcher) {
            val movie = schoolsUseCase.fetchSchoolInfo(id)
            _listingUiState.tryEmit(SchoolUiState.DisplaySchoolDetails(movie))
        }
    }

    // There is problem on MutableStateFlow not trigger some time
    // it will take school Details Info screen bases on DBN value
    fun navigateToMovieDetails(movieDetails: SchoolItem) {
        viewModelScope.launch((Dispatchers.Main)) {
            val command = SchoolInfoDirections.schoolInfo
            command.navigationPath = "school_info/${movieDetails.dbn}"
            navigationManager.navigate(command)
        }
    }
}