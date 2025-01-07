package com.jetpack.codechallenge.ui.home.schoollistings

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.jetpack.codechallenge.ui.home.SchoolUiState
import com.jetpack.codechallenge.ui.home.SchoolViewModel
import com.jetpack.codechallenge.ui.home.models.SchoolItem
import com.jetpack.codechallenge.ui.themes.AppTheme
import com.jetpack.codechallenge.util.view.*

/**
 * SchoolListingScreen display list of schools from NY Api data
 */
@Composable
fun SchoolListingScreen(
    viewModel: SchoolViewModel,
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
            Toast.makeText(context, "Some thing is went wrong", Toast.LENGTH_SHORT).show()
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
        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 8.dp,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    AppTitleText(text = it.name, color = Color.Green)
                    AppBodyText(text = "Email: " + it.email, color = Color.DarkGray)
                    AppBodyText(text = "PhoneNo: " + it.phoneNumber, color = Color.DarkGray)
                    AppBodyText(text = "Address: " + it.address, color = Color.DarkGray, maxLines = 2)
                }
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

