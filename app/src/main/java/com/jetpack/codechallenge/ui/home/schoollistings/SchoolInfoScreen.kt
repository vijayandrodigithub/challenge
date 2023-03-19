package com.jetpack.codechallenge.ui.home.schoollistings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jetpack.codechallenge.ui.home.SchoolUiState
import com.jetpack.codechallenge.ui.home.SchoolViewModel
import com.jetpack.codechallenge.ui.themes.AppTheme
import com.jetpack.codechallenge.util.view.AppBodyText
import com.jetpack.codechallenge.util.view.AppTitleText
import org.koin.androidx.compose.getViewModel

@Composable
fun MovieDetailsScreen(id: String?, viewModel: SchoolViewModel = getViewModel()) {
    AppTheme {
        val state by viewModel.listingUiState.collectAsState()
        viewModel.fetchSchoolDetails(id ?: "")
        SchoolInfoScreen(state)
    }
}

@Composable
private fun SchoolInfoScreen(state: SchoolUiState) {
    if (state is SchoolUiState.DisplaySchoolDetails)
        Column(Modifier.background(color = Color.Red)) {
            AppTitleText(text = state.data.dbn)
            AppBodyText(text = state.data.address)
            AppBodyText(text = state.data.phoneNumber)
        }
}
