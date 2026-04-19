package ru.saytikus.androidsimpleclient.presentation.settings

import kotlinx.serialization.Serializable
import ru.saytikus.androidsimpleclient.domain.settings.Settings
import ru.saytikus.androidsimpleclient.domain.settings.SettingsDefaults


/**
 * Object used for a type safe destination to a Settings route
 */
@Serializable
object SettingsDestination

/**
 * UI State that represents SettingsScreen
 **/
data class SettingsState (
    val newHostAddress: String = "",
    val settings: Settings = SettingsDefaults.defaultSettings,
    val isDarkTheme: Boolean = true
)

/**
 * Settings Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface SettingsAction {
    data class OnHostAddressChanged(val newValue: String) : SettingsAction

    data class OnHostAddressSaveButtonClicked(val newValue: String) : SettingsAction
}

