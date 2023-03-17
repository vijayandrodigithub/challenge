package com.jetpack.codechallenge.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.runtime.Composable
import com.jetpack.codechallenge.ui.themes.AppTheme
import com.jetpack.codechallenge.util.view.TopBar
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jetpack.codechallenge.ui.home.schoollistings.SchoolListingScreen
import com.jetpack.codechallenge.util.view.PREVIEW_CONFIG

class SchoolListingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                ScaffoldViews()
            }
        }
    }

    @Composable
    fun ScaffoldViews() {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopBar() },
            content = { paddingValues ->
                val modifier = Modifier.padding(paddingValues)
                SchoolListingScreen(modifier)
            })
    }


    @Preview(PREVIEW_CONFIG)
    @Composable
    fun DefaultPreview() {
        AppTheme {
            ScaffoldViews()
        }
    }
}