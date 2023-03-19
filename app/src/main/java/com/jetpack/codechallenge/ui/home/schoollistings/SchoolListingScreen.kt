package com.jetpack.codechallenge.ui.home.schoollistings

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.jetpack.codechallenge.ui.home.SchoolUiState
import com.jetpack.codechallenge.ui.home.SchoolViewModel
import com.jetpack.codechallenge.ui.home.models.SchoolItem
import com.jetpack.codechallenge.ui.themes.AppTheme
import com.jetpack.codechallenge.util.view.*
import org.koin.androidx.compose.getViewModel

@Composable
fun SchoolListingScreen(
    viewModel: SchoolViewModel = getViewModel(),
    onNavigateToDetails: (SchoolItem) -> Unit
) {
    viewModel.loadSchools()
    AppTheme {
        val state = viewModel.listingUiState.collectAsState()
        MovieListingContent(state.value, onNavigateToDetails)
    }
}

@Composable
fun MovieListingContent(
    state: SchoolUiState = SchoolUiState.Empty,
    onNavigateToDetails: (SchoolItem) -> Unit = {},
    ) {
    val context = LocalContext.current
    when (state) {
        SchoolUiState.Loading -> {
            AppCircularProgressBar()
        }
        is SchoolUiState.DisplaySchools -> {
            DisplaySchoolList(state.data, onNavigateToDetails)
        }
        SchoolUiState.Empty -> {
            AppBodyText("There are no Movies")
        }
        is SchoolUiState.Error -> {
            Toast.makeText(context, "Some thing went error", Toast.LENGTH_SHORT).show()
        }
        else -> {
            Toast.makeText(context, "Else", Toast.LENGTH_SHORT).show()
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DisplaySchoolList(
    movies: List<SchoolItem>,
    onNavigateToDetails: (SchoolItem) -> Unit
) {
    AppLazyColumn(
        items = movies,
        onItemClick = onNavigateToDetails,
        key = { dbn }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                AppTitleText(text = it.name)
                AppBodyText(text = it.email)
                AppBodyText(text = it.phoneNumber)
                AppBodyText(text = it.address)
            }
        }
    }
}

@Preview
@Composable
fun ListingScreenPreview() {
    val list = mutableListOf<SchoolItem>()
    list += SchoolItem(
        dbn = "1",
        name = "Clinton School Writers & Artists, M.S. 260",
        email = "vijaya@gmail.com"
    )
    list += SchoolItem(
        dbn = "2",
        name = "Clinton School Writers & Artists, M.S. 260",
        email = "vijaya@gmail.com"
    )
    list += SchoolItem(
        dbn = "3",
        name = "Clinton School Writers & Artists, M.S. 260",
        email = "vijaya@gmail.com"
    )
    list += SchoolItem(
        dbn = "4",
        name = "Clinton School Writers & Artists, M.S. 260",
        email = "vijaya@gmail.com"
    )
    list += SchoolItem(
        dbn = "5",
        name = "Clinton School Writers & Artists, M.S. 260",
        email = "vijaya@gmail.com"
    )
    AppTheme {
        MovieListingContent(state = SchoolUiState.DisplaySchools(list))
    }
}

