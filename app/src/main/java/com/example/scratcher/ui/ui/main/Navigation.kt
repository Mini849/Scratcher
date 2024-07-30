package com.example.scratcher.ui.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scratcher.ui.ui.activation_screen.ActivationScreen
import com.example.scratcher.ui.ui.scratch_screen.ScratchScreen
import com.example.scratcher.ui.ui.ScratchViewModel
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
            val viewModel = getCreateScratchViewModel(it, navController)
            MainScreen(navController, viewModel)
        }
        composable<Routes.ActivationScreen> {
            val viewModel = getCreateScratchViewModel(it, navController)
            ActivationScreen(navController, viewModel)
        }
        composable<Routes.ScratchScreen> {
            val viewModel = getCreateScratchViewModel(it, navController)
            ScratchScreen(navController, viewModel)
        }
    }

}

@Composable
private fun getCreateScratchViewModel(navBackStackEntry: NavBackStackEntry, navController: NavHostController): ScratchViewModel {
    val parentEntry = remember(navBackStackEntry) {
        navController.getBackStackEntry(Routes.Main)
    }
    return hiltViewModel<ScratchViewModel>(parentEntry)
}

