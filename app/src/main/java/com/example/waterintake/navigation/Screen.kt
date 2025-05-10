package com.example.waterintake.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Home : Screen("home")
    object Track : Screen("track")
    object Settings : Screen("settings")
} 