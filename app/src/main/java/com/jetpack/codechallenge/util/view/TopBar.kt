package com.jetpack.codechallenge.util.view

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.jetpack.codechallenge.ui.themes.appColors

@Composable
    fun TopBar() {
        TopAppBar(
            title = {
                Text(text = "Top App Bar")
            },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            },
            backgroundColor = appColors.blue.dark,
            elevation = 10.px
        )
    }