package com.example.parcial2eventos_robertoquilez

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "main") {
        composable("main") { MiHorarioApp() }
        composable("pharmacies") { PantallaListasFarmacias { navController.popBackStack() } }
        composable("map/{latitude}/{longitude}") { backStackEntry ->
            val latitude = backStackEntry.arguments?.getString("latitude")?.toDouble() ?: 0.0
            val longitude = backStackEntry.arguments?.getString("longitude")?.toDouble() ?: 0.0
            MapScreen(latitude, longitude)
        }
    }
}