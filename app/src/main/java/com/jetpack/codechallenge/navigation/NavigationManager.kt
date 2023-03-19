package com.jetpack.codechallenge.navigation

import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager {

    private val _commandFlow = MutableStateFlow(HomeDirections.schools)
    val commandFlow = _commandFlow

    fun navigate(directions: NavCommand) {
        _commandFlow.tryEmit(directions)
    }
}