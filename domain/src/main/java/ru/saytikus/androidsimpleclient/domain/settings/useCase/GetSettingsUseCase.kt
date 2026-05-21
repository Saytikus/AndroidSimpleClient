package ru.saytikus.androidsimpleclient.domain.settings.useCase

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.settings.ISettingsRepository
import ru.saytikus.androidsimpleclient.domain.settings.Settings

class GetSettingsUseCase(

    private val settingsRepo: ISettingsRepository

) : IInputBoundary<MbResult<Settings>, Unit> {

    override suspend fun invoke(cmd: Unit): MbResult<Settings> {
        return MbResult.Success(settingsRepo.getOnce())
    }
}