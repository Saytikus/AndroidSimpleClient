package ru.saytikus.androidsimpleclient.presentation.settings

import kotlinx.serialization.Serializable


/**
 * Object used for a type safe destination to a Settings route
 */
@Serializable
object SettingsDestination

/**
 * UI State that represents SettingsScreen
 **/
class SettingsState

/**
 * Settings Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface SettingsAction {
    data object OnClick : SettingsAction
}

