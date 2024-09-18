package com.jetpack.codechallenge.util.view
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jetpack.codechallenge.navigation.HomeDirections
import com.jetpack.codechallenge.navigation.SchoolInfoDirections.DBN_ID
import com.jetpack.codechallenge.ui.home.SchoolViewModel
import com.jetpack.codechallenge.ui.home.schoollistings.SchoolDetailsScreen
import com.jetpack.codechallenge.ui.home.schoollistings.SchoolListingScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    naviController: NavHostController = rememberNavController(),
    startDestination: String = "schools".addGraphPostfix(),
    viewModel: SchoolViewModel
) {
    NavHost(
        modifier = modifier,
        navController = naviController,
        startDestination = startDestination
    ) {
        mainGraph(viewModel)
    }
}

private fun String.addGraphPostfix(): String {
    return this.plus("_graph")
}

fun NavGraphBuilder.mainGraph(viewModel: SchoolViewModel) {
    navigation(
        startDestination = "schools",
        route = HomeDirections.schools.destination.addGraphPostfix()
    ) {
        composable("schools") {
            SchoolListingScreen( viewModel,
                onNavigateToDetails = {
                    viewModel.navigateToMovieDetails(it)
                })
        }

        composable(
            "schoolinfo/{DBN_ID}",
            arguments = listOf(navArgument(DBN_ID) { type = NavType.StringType })
        ) {
            SchoolDetailsScreen(id = it.arguments?.getString(DBN_ID), viewModel)
        }
    }

}