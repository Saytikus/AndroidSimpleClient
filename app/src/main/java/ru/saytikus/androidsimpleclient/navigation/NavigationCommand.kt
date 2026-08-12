package ru.saytikus.androidsimpleclient.navigation

sealed interface NavigationCommand {

    data class Navigate(val destination: Any) : NavigationCommand

    data class ReplaceRoot(val destination: Any) : NavigationCommand

    data object NavigateBack : NavigationCommand
}