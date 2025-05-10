package com.example.waterintake.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.waterintake.model.Screen
import com.example.waterintake.ui.screens.HomeScreen
import com.example.waterintake.ui.screens.TrackWaterScreen
import com.example.waterintake.ui.screens.WelcomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.name
    ) {
        composable(Screen.Welcome.name) {
            WelcomeScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.name) {
                        popUpTo(Screen.Welcome.name) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Home.name) {
            HomeScreen(
                onNavigateToTrackWater = {
                    navController.navigate(Screen.TrackWater.name)
                }
            )
        }
        
        composable(Screen.TrackWater.name) {
            TrackWaterScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
} 