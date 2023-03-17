package com.jetpack.codechallenge.ui.home

import com.jetpack.codechallenge.models.SchoolDetails
import com.jetpack.codechallenge.ui.home.models.SchoolItem

sealed interface SchoolUiState {
    object Empty: SchoolUiState
    object Loading : SchoolUiState
    class DisplaySchools(val data: List<SchoolItem>) : SchoolUiState
    class DisplaySchoolDetails(val data: SchoolItem) : SchoolUiState
    object Error : SchoolUiState

}