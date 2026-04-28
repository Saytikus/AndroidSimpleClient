package ru.saytikus.androidsimpleclient.presentation.authentication

import kotlinx.serialization.Serializable


/**
 * Object used for a type safe destination to a Authentication route
 */
@Serializable
object AuthenticationDestination

/**
 * UI State that represents AuthenticationScreen
 **/
data class AuthenticationState(
    val usernameOrEmail: String = "",

    val password: String = "",

    val authenticationError: String = "",

    val isAuthenticationSuccessfully: Boolean = false
)

sealed interface AuthenticationNavigation {

    data object MainScreen: AuthenticationNavigation
}

/**
 * Authentication Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
sealed interface AuthenticationAction {

    data class OnUsernameOrEmailChange(val newValue: String) : AuthenticationAction

    data class onPasswordChange(val newValue: String) : AuthenticationAction

    data object OnSignInButtonClick : AuthenticationAction
}

