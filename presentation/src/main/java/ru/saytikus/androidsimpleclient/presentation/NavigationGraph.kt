package ru.saytikus.androidsimpleclient.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.saytikus.androidsimpleclient.presentation.authentication.AuthenticationDestination
import ru.saytikus.androidsimpleclient.presentation.authentication.AuthenticationNavigation
import ru.saytikus.androidsimpleclient.presentation.authentication.AuthenticationRoute
import ru.saytikus.androidsimpleclient.presentation.chat.chatList.ChatListDestination
import ru.saytikus.androidsimpleclient.presentation.chat.chatList.ChatListNavigation
import ru.saytikus.androidsimpleclient.presentation.chat.chatList.ChatListRoute
import ru.saytikus.androidsimpleclient.presentation.chat.createChat.CreateChatDestination
import ru.saytikus.androidsimpleclient.presentation.chat.createChat.CreateChatNavigation
import ru.saytikus.androidsimpleclient.presentation.chat.createChat.CreateChatRoute
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
                            navController.navigate(ChatListDestination) {
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

        composable<ChatListDestination> {
            ChatListRoute(
                onNavigate = { nav ->
                    when(nav) {

                        ChatListNavigation.Settings -> {
                            navController.navigate(SettingsDestination)
                        }

                        ChatListNavigation.AddChat -> navController.navigate(
                            CreateChatDestination
                        )
                    }
                }
            )
        }

        composable<CreateChatDestination> {
            CreateChatRoute(
                onNavigate = { nav ->
                    when(nav) {
                        CreateChatNavigation.CreatedChat -> navController.navigate(
                            ChatListDestination
                        )    // TODO fix after add chat screen
                    }
                }
            )
        }
    }
}