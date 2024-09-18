package com.jetpack.codechallenge.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.codechallenge.navigation.HomeDirections
import com.jetpack.codechallenge.navigation.NavCommand
import com.jetpack.codechallenge.navigation.SchoolInfoDirections
import com.jetpack.codechallenge.navigation.NavigationManager
import com.jetpack.codechallenge.ui.home.models.SchoolItem
import com.jetpack.codechallenge.usecase.SchoolsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    val listingUiState get() = _listingUiState.asStateFlow()

    private val _selectedSchool = MutableStateFlow<String?>(null)
    val selectedSchool: StateFlow<String?> = _selectedSchool.asStateFlow()

    private val _navCommand = MutableStateFlow<NavCommand?>(null)
    val navCommand: StateFlow<NavCommand?> = _navCommand.asStateFlow()

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
    fun navigateToMovieDetails(schoolItem: SchoolItem) {

        viewModelScope.launch (dispatcher)  {
          /*  val command: NavCommand = SchoolInfoDirections.schoolInfo
            command.navigationPath = "schoolinfo/${schoolItem.dbn}"
            Log.e("?????","command: $command")*/
            _selectedSchool.tryEmit(schoolItem.dbn)
            //navigationManager.navigate(command)
        }
    }

    fun navigateToSchoolInfo() {
        viewModelScope.launch {
            val command = SchoolInfoDirections.schoolInfo
            command.navigationPath = "schoolinfo/${"1sds"}"
            _navCommand.tryEmit(command)
        }
    }
}