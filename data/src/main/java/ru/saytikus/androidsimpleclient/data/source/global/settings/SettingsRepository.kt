package ru.saytikus.androidsimpleclient.data.source.global.settings

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
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
        return Settings(
            prefs[SettingsKeys.RESPONSE_SERVER_HOST_KEY] ?:
            SettingsDefaults.defaultSettings.responseServerHostAddress
        )
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
                    ?: SettingsDefaults.defaultSettings.responseServerHostAddress
            )
        }.distinctUntilChanged()
    }
}