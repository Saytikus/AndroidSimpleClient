package ru.saytikus.androidsimpleclient.domain.common.encryptedSettings.useCases

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.encryptedSettings.EncryptedSettings
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.interfaces.ISingleObjectRepository

class UpdateEncryptedSettingsUseCase(

    private val encryptedSettingsRepo: ISingleObjectRepository<EncryptedSettings>

): IInputBoundary<MbResult<Unit>, EncryptedSettings> {

    override suspend fun invoke(cmd: EncryptedSettings): MbResult<Unit> {
        return encryptedSettingsRepo.update(cmd)
    }
}