package ru.saytikus.androidsimpleclient.domain.core.features.settings.useCase

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.features.settings.ISettingsRepository
import ru.saytikus.androidsimpleclient.domain.core.features.settings.Settings
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary

class GetSettingsUseCase(

    private val settingsRepo: ISettingsRepository

) : IInputBoundary<MbResult<Settings>, Unit> {

    override suspend fun invoke(cmd: Unit): MbResult<Settings> {
        return MbResult.Success(settingsRepo.getOnce())
    }
}