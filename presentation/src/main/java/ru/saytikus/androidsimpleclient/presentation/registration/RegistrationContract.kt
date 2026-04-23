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
class RegistrationState

/**
 * Registration Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface RegistrationAction {
    data object OnClick : RegistrationAction
}

