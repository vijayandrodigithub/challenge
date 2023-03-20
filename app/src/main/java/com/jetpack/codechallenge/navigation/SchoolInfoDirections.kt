package com.jetpack.codechallenge.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object SchoolInfoDirections {
    const val DBN_ID = "dbn_id"
    val schoolInfo = object : NavCommand {
        override val arguments: List<NamedNavArgument>
            get() = listOf(navArgument(DBN_ID) { type = NavType.StringType })
        override val destination: String
            get() = "school_info/{$DBN_ID}"
        override var navigationPath : String
            get() = ""
            set(value) {}
    }
}