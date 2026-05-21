package ru.saytikus.androidsimpleclient.domain.core.features.settings.useCase

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.features.settings.ISettingsRepository
import ru.saytikus.androidsimpleclient.domain.core.features.settings.Settings
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary

class SaveSettingsUseCase(

    private val settingsRepo: ISettingsRepository

) : IInputBoundary<MbResult<Unit>, Settings> {

    override suspend fun invoke(cmd: Settings): MbResult<Unit> {
        return settingsRepo.updateSettings(cmd)
    }
}