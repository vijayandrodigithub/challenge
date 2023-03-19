package com.jetpack.codechallenge.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object SchoolDetailDirections {

    const val DBN_ID = "dbn_id"

    val movieDetails = object : NavCommand {
        override val arguments: List<NamedNavArgument>
            get() = listOf(navArgument(DBN_ID) { type = NavType.IntType })
        override val destination: String
            get() = "movie_details/{$DBN_ID}"
        override var navigationPath: String = ""
            get() = ""
    }
}