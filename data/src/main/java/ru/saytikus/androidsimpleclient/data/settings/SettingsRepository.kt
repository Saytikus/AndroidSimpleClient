package ru.saytikus.androidsimpleclient.data.settings

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.settings.ISettingsRepository
import ru.saytikus.androidsimpleclient.domain.settings.Settings
import ru.saytikus.androidsimpleclient.domain.settings.SettingsDefaults

private val Context.dataStore by preferencesDataStore(name = "settings")

@Single
class SettingsRepository(

    private val context: Context

) : ISettingsRepository {

    override suspend fun getOnce(): Settings {
        // TODO try catch
        val prefs = context.dataStore.data.first()

        val serverAddress = prefs[SettingsKeys.RESPONSE_SERVER_HOST_KEY]
        val activeUserId = prefs[SettingsKeys.ACTIVE_USER_ID_KEY]

        return Settings(
            serverAddress ?: SettingsDefaults.defaultSettings.responseServerHostAddress,
            activeUserId
        )
    }

    override suspend fun updateSettings(settings: Settings): MbResult<Unit> {
        // TODO handle errors
        setResponseServerHostAddress(settings.responseServerHostAddress)
        setActiveUserId(settings.activeUserId ?: "")
        return MbResult.Success(Unit)
    }

    override suspend fun setResponseServerHostAddress(newValue: String): MbResult<Unit> {
        // TODO try catch
        context.dataStore.edit { preferences ->
            preferences[SettingsKeys.RESPONSE_SERVER_HOST_KEY] = newValue
        }

        return MbResult.Success(Unit)
    }

    override fun observeSettings(): Flow<Settings> {
        return context.dataStore.data.map { preferences ->
            Settings(
                preferences[SettingsKeys.RESPONSE_SERVER_HOST_KEY]
                    ?: SettingsDefaults.defaultSettings.responseServerHostAddress,
                preferences[SettingsKeys.ACTIVE_USER_ID_KEY]

            )
        }.distinctUntilChanged()
    }

    private suspend fun setActiveUserId(newValue: String): MbResult<Unit> {
        // TODO try catch
        context.dataStore.edit { preferences ->
            preferences[SettingsKeys.ACTIVE_USER_ID_KEY] = newValue
        }

        return MbResult.Success(Unit)
    }
}