package ru.saytikus.androidsimpleclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.saytikus.androidsimpleclient.presentation.authentication.AuthenticationDestination
import ru.saytikus.androidsimpleclient.presentation.authentication.AuthenticationRoute
import ru.saytikus.androidsimpleclient.presentation.chat.chat.ChatDestination
import ru.saytikus.androidsimpleclient.presentation.chat.chat.ChatRoute
import ru.saytikus.androidsimpleclient.presentation.chat.chatList.ChatListDestination
import ru.saytikus.androidsimpleclient.presentation.chat.chatList.ChatListRoute
import ru.saytikus.androidsimpleclient.presentation.chat.createChat.CreateChatDestination
import ru.saytikus.androidsimpleclient.presentation.chat.createChat.CreateChatRoute
import ru.saytikus.androidsimpleclient.presentation.registration.RegistrationDestination
import ru.saytikus.androidsimpleclient.presentation.registration.RegistrationRoute
import ru.saytikus.androidsimpleclient.presentation.settings.SettingsDestination
import ru.saytikus.androidsimpleclient.presentation.settings.SettingsRoute
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
@Composable
fun AppNavigationGraph(

    navController: NavHostController,

    startDestination: Any,

    coordinator: AppNavigationCoordinator
) {

    NavHost(
        navController = navController,
        startDestination = startDestination

    ) {
        composable<SettingsDestination> {
            SettingsRoute()
        }

        composable<RegistrationDestination> {
            RegistrationRoute(onNavigate = coordinator::onRegistrationNavigate)
        }

        composable<AuthenticationDestination> {
            AuthenticationRoute(onNavigate = coordinator::onAuthenticationNavigate)
        }

        composable<ChatListDestination> {
            ChatListRoute(onNavigate = coordinator::onChatListNavigate)
        }

        composable<ChatDestination> {
            ChatRoute(onNavigate = coordinator::onChatNavigate)
        }

        composable<CreateChatDestination> {
            CreateChatRoute(onNavigate = coordinator::onCreateChatNavigate)
        }
    }
}