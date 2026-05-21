package ru.saytikus.androidsimpleclient.domain.core.features.encryptedSettings.useCases

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.features.encryptedSettings.EncryptedSettings
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.core.interfaces.ISingleObjectRepository

class UpdateEncryptedSettingsUseCase(

    private val encryptedSettingsRepo: ISingleObjectRepository<EncryptedSettings>

): IInputBoundary<MbResult<Unit>, EncryptedSettings> {

    override suspend fun invoke(cmd: EncryptedSettings): MbResult<Unit> {
        return encryptedSettingsRepo.update(cmd)
    }
}