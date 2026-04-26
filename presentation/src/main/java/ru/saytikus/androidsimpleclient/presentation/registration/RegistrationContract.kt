package ru.saytikus.androidsimpleclient.presentation.registration

import kotlinx.serialization.Serializable


/**
 * Object used for a type safe destination to a Registration route
 */
@Serializable
object RegistrationDestination

/**
 * UI State that represents RegistrationScreen
 **/
data class RegistrationState(
    val username: String = "",

    val email: String = "",

    val password: String = "",

    val displayName: String = "",

    val usernameError: String? = null,

    val emailError: String? = null,

    val passwordError: String? = null,

    val displayNameError: String? = null
)

sealed interface RegistrationNavigation {

    data object Settings : RegistrationNavigation
}

/**
 * Registration Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface RegistrationAction {
    data class OnUsernameChange(val newValue: String) : RegistrationAction

    data class OnEmailChange(val newValue: String) : RegistrationAction

    data class OnPasswordChange(val newValue: String) : RegistrationAction

    data class OnDisplayNameChange(val newValue: String) : RegistrationAction

    data object OnRegistrationSubmit : RegistrationAction

    data object OnSignInClick : RegistrationAction

    data object DEBUG_onSettingsButtonClick : RegistrationAction
}

