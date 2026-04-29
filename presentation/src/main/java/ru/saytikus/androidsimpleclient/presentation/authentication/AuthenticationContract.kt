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

    val passwordError: String? = null,

    val authenticationError: String? = null,

    val isAuthenticationSuccessfully: Boolean = false
)

sealed interface AuthenticationNavigation {

    data object MainScreen : AuthenticationNavigation

    data object RegistrationScreen : AuthenticationNavigation
}

/**
 * Authentication Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
sealed interface AuthenticationAction {

    data class OnUsernameOrEmailChange(val newValue: String) : AuthenticationAction

    data class OnPasswordChange(val newValue: String) : AuthenticationAction

    data object OnSignInButtonClick : AuthenticationAction

    data object OnRegisterButtonClick : AuthenticationAction

    data object OnSignInSuccessfully : AuthenticationAction
}

