package ru.saytikus.androidsimpleclient.domain.settings.useCase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.saytikus.androidsimpleclient.domain.common.dto.MbError
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.valueObject.DomainError
import ru.saytikus.androidsimpleclient.domain.settings.ISettingsRepository
import ru.saytikus.androidsimpleclient.domain.settings.dto.SaveResponseServerHostAddressCommand

class SaveResponseServerHostAddressUseCase(
    private val settingsRepo: ISettingsRepository

) : IInputBoundary<MbResult<Unit>, SaveResponseServerHostAddressCommand> {

    override suspend fun invoke(cmd: SaveResponseServerHostAddressCommand): MbResult<Unit> =
        withContext(Dispatchers.IO) {

            // empty check
            if(cmd.responseServerHostAddress.isEmpty()) {
                return@withContext MbResult.Failure(MbError(DomainError.MapError.UnexpectedFormat))
            }

            // ipv4 check
            if(!isIpv4Address(cmd.responseServerHostAddress)) {
                return@withContext MbResult.Failure(MbError(DomainError.MapError.UnexpectedFormat))
            }

            return@withContext settingsRepo.setResponseServerHostAddress(cmd.responseServerHostAddress)
        }

    private fun isIpv4Address(ipv4String: String): Boolean {
        val parts = ipv4String.split(".")
        if (parts.size != 4) return false

        return parts.all { part ->
            part.toIntOrNull()?.let { it in 0..255 && it.toString() == part } ?: false
        }
    }
}