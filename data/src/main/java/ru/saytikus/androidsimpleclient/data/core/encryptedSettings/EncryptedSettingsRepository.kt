package ru.saytikus.androidsimpleclient.data.core.encryptedSettings

import eu.anifantakis.lib.ksafe.KSafe
import eu.anifantakis.lib.ksafe.invoke
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import ru.saytikus.androidsimpleclient.domain.common.dto.MbError
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.encryptedSettings.EncryptedSettings
import ru.saytikus.androidsimpleclient.domain.common.interfaces.ISingleObjectRepository
import ru.saytikus.androidsimpleclient.domain.common.valueObject.DomainError


class EncryptedSettingsRepository(

    private val ksafe: KSafe

) : ISingleObjectRepository<EncryptedSettings> {

    private var _encryptedSettingsDto by ksafe(EncryptedSettingsDto())

    private val _settingsFlow = MutableStateFlow(_encryptedSettingsDto)

    // TODO logger


    override suspend fun update(entity: EncryptedSettings): MbResult<Unit> {
        val result = runCatching {
            _encryptedSettingsDto = entity.toDto()
            _settingsFlow.value = _encryptedSettingsDto
        }

        if(result.isFailure) {

            val error = MbError(
                DomainError.RepositoryError.MightyDataError(
                    result.exceptionOrNull()?.message
                )
            )

            println("Encrypted Settings Repo catch exception with update: $error")
            return MbResult.Failure(
                MbError(
                    DomainError.RepositoryError.MightyDataError(result.exceptionOrNull()?.message)
                )
            )
        }

        return MbResult.Success(Unit)
    }

    override suspend fun getOnce(): EncryptedSettings {

        var encSettings = EncryptedSettingsDto().toDomain()
        val result = runCatching {
            encSettings = _encryptedSettingsDto.toDomain()
        }

        if(result.isFailure) {

            val error = MbError(
                DomainError.RepositoryError.MightyDataError(
                    result.exceptionOrNull()?.message
                )
            )

            println("Encrypted Settings Repo catch exception with getOnce: $error")
        }

        return encSettings
    }

    override suspend fun reset() {
        val result = runCatching {
            _encryptedSettingsDto = EncryptedSettingsDto()
            _settingsFlow.value = _encryptedSettingsDto
        }

        if(result.isFailure) {

            val error = MbError(
                DomainError.RepositoryError.MightyDataError(
                    result.exceptionOrNull()?.message
                )
            )

            println("Encrypted Settings Repo catch exception with reset: $error")
        }
    }

    override fun observe(): Flow<EncryptedSettings> = _settingsFlow.map {
        it.toDomain()
    }
}