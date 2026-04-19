package ru.saytikus.androidsimpleclient.domain.settings.useCase

import kotlinx.coroutines.flow.Flow
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IObserveInputBoundary
import ru.saytikus.androidsimpleclient.domain.settings.ISettingsRepository
import ru.saytikus.androidsimpleclient.domain.settings.Settings

class ObserveSettingsUseCase(
    private val settingsRepo: ISettingsRepository

) : IObserveInputBoundary<Flow<Settings>> {

    override fun invoke(): Flow<Settings> = settingsRepo.observeSettings()
}