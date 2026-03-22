package ru.saytikus.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.saytikus.presentation.product.ProductDestination
import ru.saytikus.presentation.product.ProductRoute
import ru.saytikus.presentation.product.rememberProductCoordinator

@Composable
fun NavigationGraph() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ProductDestination

    ) {
        composable<ProductDestination> {
            ProductRoute(rememberProductCoordinator())
        }
    }
}