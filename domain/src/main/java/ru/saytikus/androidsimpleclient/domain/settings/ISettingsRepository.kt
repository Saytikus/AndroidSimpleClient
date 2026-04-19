package ru.saytikus.androidsimpleclient.domain.settings

import kotlinx.coroutines.flow.Flow
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult

interface ISettingsRepository {

    suspend fun getOnce(): Settings

    suspend fun setResponseServerHostAddress(newValue: String): MbResult<Unit>

    fun observeSettings(): Flow<Settings>
}