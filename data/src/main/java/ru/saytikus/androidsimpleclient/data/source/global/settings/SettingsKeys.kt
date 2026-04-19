package ru.saytikus.androidsimpleclient.data.source.global.settings

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

object SettingsKeys {

    val RESPONSE_SERVER_HOST_KEY: Preferences.Key<String> =
        stringPreferencesKey("response_server_host_address")
}
