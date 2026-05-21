package ru.saytikus.androidsimpleclient.domain.settings.useCase

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.settings.ISettingsRepository
import ru.saytikus.androidsimpleclient.domain.settings.Settings

class SaveSettingsUseCase(

    private val settingsRepo: ISettingsRepository

) : IInputBoundary<MbResult<Unit>, Settings> {

    override suspend fun invoke(cmd: Settings): MbResult<Unit> {
        return settingsRepo.updateSettings(cmd)
    }
}