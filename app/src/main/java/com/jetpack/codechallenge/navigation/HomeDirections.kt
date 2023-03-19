package com.jetpack.codechallenge.navigation

import androidx.navigation.NamedNavArgument

object HomeDirections {

    val schools = object : NavCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "schools"
        override var navigationPath: String
            get() = ""
            set(value) {}

    }
}