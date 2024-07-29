package com.example.scratcher.ui.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scratcher.ui.ui.activation_screen.ActivationScreen
import com.example.scratcher.ui.ui.scratch_screen.ScratchScreen
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
            MainScreen(navController)
        }
        composable<Routes.ActivationScreen> {
            ActivationScreen(navController)
        }
        composable<Routes.ScratchScreen> {
            ScratchScreen(navController)
        }
    }

}

