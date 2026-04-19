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

            if(cmd.responseServerHostAddress.isEmpty()) {
                return@withContext MbResult.Failure(MbError(DomainError.MapError.UnexpectedFormat))
            }
            // TODO ipv4 address check

            return@withContext settingsRepo.setResponseServerHostAddress(cmd.responseServerHostAddress)
        }
}