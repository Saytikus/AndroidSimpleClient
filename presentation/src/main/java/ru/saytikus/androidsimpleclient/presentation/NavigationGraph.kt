package ru.saytikus.androidsimpleclient.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.saytikus.androidsimpleclient.presentation.authentication.AuthenticationDestination
import ru.saytikus.androidsimpleclient.presentation.authentication.AuthenticationNavigation
import ru.saytikus.androidsimpleclient.presentation.authentication.AuthenticationRoute
import ru.saytikus.androidsimpleclient.presentation.product.ProductDestination
import ru.saytikus.androidsimpleclient.presentation.product.ProductNavigation
import ru.saytikus.androidsimpleclient.presentation.product.ProductRoute
import ru.saytikus.androidsimpleclient.presentation.registration.RegistrationDestination
import ru.saytikus.androidsimpleclient.presentation.registration.RegistrationNavigation
import ru.saytikus.androidsimpleclient.presentation.registration.RegistrationRoute
import ru.saytikus.androidsimpleclient.presentation.settings.SettingsDestination
import ru.saytikus.androidsimpleclient.presentation.settings.SettingsRoute

@Composable
fun NavigationGraph() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RegistrationDestination

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

        composable<RegistrationDestination> {
            RegistrationRoute(
                onNavigate = { nav ->
                    when(nav) {
                        RegistrationNavigation.Settings -> {
                            navController.navigate(SettingsDestination)
                        }

                        RegistrationNavigation.Authentication -> {
                            navController.navigate(AuthenticationDestination) {
                                popUpTo<RegistrationDestination>() { inclusive = true }
                            }
                        }
                    }
                }
            )
        }

        composable<AuthenticationDestination> {
            AuthenticationRoute(
                onNavigate = { nav ->
                    when(nav) {

                        AuthenticationNavigation.MainScreen -> {
                            navController.navigate(ProductDestination) {
                                popUpTo<AuthenticationDestination>() { inclusive = true }
                            }
                        }

                        AuthenticationNavigation.RegistrationScreen -> {
                            navController.navigate(RegistrationDestination)
                        }
                    }
                }
            )
        }
    }
}