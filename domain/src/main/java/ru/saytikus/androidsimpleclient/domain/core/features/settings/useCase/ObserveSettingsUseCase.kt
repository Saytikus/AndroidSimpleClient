package ru.saytikus.androidsimpleclient.domain.core.features.settings.useCase

import kotlinx.coroutines.flow.Flow
import ru.saytikus.androidsimpleclient.domain.core.features.settings.ISettingsRepository
import ru.saytikus.androidsimpleclient.domain.core.features.settings.Settings
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IObserveInputBoundary

class ObserveSettingsUseCase(
    private val settingsRepo: ISettingsRepository

) : IObserveInputBoundary<Flow<Settings>> {

    override fun invoke(): Flow<Settings> = settingsRepo.observeSettings()
}