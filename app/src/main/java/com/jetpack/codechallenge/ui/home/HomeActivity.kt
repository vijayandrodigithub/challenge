package com.jetpack.codechallenge.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.jetpack.codechallenge.navigation.NavigationManager
import com.jetpack.codechallenge.ui.themes.AppTheme
import com.jetpack.codechallenge.ui.themes.appColors
import com.jetpack.codechallenge.util.view.AppNavigation
import com.jetpack.codechallenge.util.view.PREVIEW_CONFIG
import com.jetpack.codechallenge.util.view.px
import org.koin.android.ext.android.inject


class HomeActivity: ComponentActivity() {

    private val navigationManager: NavigationManager by inject()
    private val viewModel: SchoolViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Scaffold()
            }
        }
    }

    @Composable
    fun Scaffold() {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val state by navigationManager.commandFlow.collectAsState()
        val naviController = rememberNavController()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopBar() },
            content = { paddingValues ->
                AppNavigation(
                    naviController = naviController,
                    modifier = Modifier.padding(paddingValues),
                    viewModel = viewModel
                )
            }
        )
        if (state.navigationPath.isNotEmpty()) {
            naviController.navigate(state.navigationPath)
        }
    }

    @Composable
    fun TopBar() {
        TopAppBar(
            title = {
                Text(text = "Top App Bar")
            },
            navigationIcon = {
                IconButton(onClick = { finish() }) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            },
            backgroundColor = appColors.blue.dark,
            elevation = 10.px
        )
    }

    @Preview(PREVIEW_CONFIG)
    @Composable
    fun DefaultPreview() {
        AppTheme {
            Scaffold()
        }
    }
}