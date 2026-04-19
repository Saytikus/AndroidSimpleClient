package ru.saytikus.androidsimpleclient.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.saytikus.androidsimpleclient.presentation.product.ProductDestination
import ru.saytikus.androidsimpleclient.presentation.product.ProductNavigation
import ru.saytikus.androidsimpleclient.presentation.product.ProductRoute
import ru.saytikus.androidsimpleclient.presentation.settings.SettingsDestination
import ru.saytikus.androidsimpleclient.presentation.settings.SettingsRoute

@Composable
fun NavigationGraph() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ProductDestination

    ) {
        composable<ProductDestination> {
            ProductRoute(
                onNavigate = { nav ->
                    when(nav) {
                        ProductNavigation.Settings -> {
                            navController.navigate(SettingsDestination)
                        }
                    }
                }
            )
        }

        composable<SettingsDestination> {
            SettingsRoute()
        }
    }
}