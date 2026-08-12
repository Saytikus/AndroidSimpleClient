package ru.saytikus.androidsimpleclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigationRouter(coordinator: AppNavigationCoordinator) {
    val navController = rememberNavController()
    var startDestination by remember { mutableStateOf<Any?>(null) }

    LaunchedEffect(Unit) {
        startDestination = coordinator.startDestination()
    }

    LaunchedEffect(navController) {
        coordinator.commands.collect { command ->
            when (command) {
                is NavigationCommand.Navigate -> navController.navigate(command.destination)

                NavigationCommand.NavigateBack -> navController.popBackStack()

                is NavigationCommand.ReplaceRoot -> navController.navigate(command.destination) {
                    popUpTo(0) { inclusive = true }
                }
            }
        }
    }

    startDestination?.let {
        AppNavigationGraph(
            navController,
            it,
            coordinator
        )
    }
}