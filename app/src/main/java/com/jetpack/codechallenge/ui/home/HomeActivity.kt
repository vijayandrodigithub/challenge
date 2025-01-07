package com.jetpack.codechallenge.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ActivityScenario.launch
import com.jetpack.codechallenge.navigation.NavCommand
import com.jetpack.codechallenge.navigation.NavigationManager
import com.jetpack.codechallenge.navigation.SchoolInfoDirections
import com.jetpack.codechallenge.ui.themes.AppTheme
import com.jetpack.codechallenge.ui.themes.primaryLight
import com.jetpack.codechallenge.util.view.AppNavigation
import com.jetpack.codechallenge.util.view.PREVIEW_CONFIG
import com.jetpack.codechallenge.util.view.px
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


/**
 * HomeActivity Single activity handle SchoolListing and SchoolInfo screens using compose
 */
class HomeActivity: ComponentActivity() {

    private val navigationManager: NavigationManager by inject()
    private val viewModel: SchoolViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                InitScaffold()
            }
        }
    }

    @Composable
    fun InitScaffold() {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val state by navigationManager.commandFlow.collectAsState()
        val schoolSelect by viewModel.selectedSchool.collectAsState()
        val naviController = rememberNavController()

        val navCommand by viewModel.navCommand.collectAsState()

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
        navCommand?.let {
            navigationManager.navigate(it)
        }
        if (state.navigationPath.isNotEmpty()) {
            naviController.navigate(state.navigationPath)
        }
        schoolSelect?.let {
            viewModel.navigateToSchoolInfo()
        }

    }

    @Composable
    fun TopBar() {
        TopAppBar(
            title = {
                Text(text = "NY Schools", color = Color.White)
            },
            navigationIcon = {
                IconButton(onClick = { finish() }) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            },
            backgroundColor = primaryLight,
            elevation = 10.px
        )
    }

    @Preview(name = "Default", showBackground = true, showSystemUi = true, device = "spec:width=411dp,height=891dp")
    @Composable
    fun DefaultPreview() {
        AppTheme {
            InitScaffold()
        }
    }

    @Preview(name = "Mobile Landscape", showBackground = true, showSystemUi = true, device = "spec:width=1280dp,height=800dp,dpi=240")
    @Composable
    fun MobileLandscapePreview() {
        AppTheme {
            InitScaffold()
        }
    }

    @Preview(name = "Tablet", showBackground = true, widthDp = 800, heightDp = 1280, device = "spec:width=673dp,height=841dp")
    @Composable
    fun TabletPreview() {
        AppTheme {
            InitScaffold()
        }
    }
}