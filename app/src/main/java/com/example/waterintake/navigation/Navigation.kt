package com.example.waterintake.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.waterintake.ui.screens.home.HomeScreen
import com.example.waterintake.ui.screens.track.TrackWaterScreen
import com.example.waterintake.ui.screens.settings.SettingsScreen
import com.example.waterintake.ui.screens.welcome.WelcomeScreen

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Home : Screen("home")
    object TrackWater : Screen("track_water")
    object Settings : Screen("settings")
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    val actions = remember(navController) { NavigationActions(navController) }

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onGetStarted = actions.navigateToHome
            )
        }
        
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToTrack = actions.navigateToTrackWater,
                onNavigateToSettings = actions.navigateToSettings
            )
        }
        
        composable(Screen.TrackWater.route) {
            TrackWaterScreen(
                onNavigateBack = actions.navigateBack
            )
        }
        
        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateBack = actions.navigateBack
            )
        }
    }
}

class NavigationActions(private val navController: NavHostController) {
    val navigateToHome = {
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Welcome.route) { inclusive = true }
        }
    }
    
    val navigateToTrackWater = {
        navController.navigate(Screen.TrackWater.route)
    }
    
    val navigateToSettings = {
        navController.navigate(Screen.Settings.route)
    }
    
    val navigateBack = {
        navController.popBackStack()
    }
} 