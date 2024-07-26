package com.example.scratcher.ui.activities.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scratcher.ui.activities.activation_screen.ActivationScreen
import com.example.scratcher.ui.activities.scratch_screen.ScratchScreen
import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    object Main : Routes

    @Serializable
    object ActivationScreen : Routes

    @Serializable
    object ScratchScreen : Routes

}

@Composable
fun NavHost(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = Routes.Main,
    ) {
        composable<Routes.Main> {
            MainScreen()
        }
        composable<Routes.ActivationScreen> {
            ActivationScreen()
        }
        composable<Routes.ScratchScreen> {
            ScratchScreen()
        }
    }

}