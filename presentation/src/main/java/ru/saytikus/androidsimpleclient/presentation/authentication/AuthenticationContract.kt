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
class AuthenticationState

/**
 * Authentication Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface AuthenticationAction {
    data object OnClick : AuthenticationAction
}

