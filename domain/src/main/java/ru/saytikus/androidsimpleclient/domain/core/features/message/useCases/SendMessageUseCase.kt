package ru.saytikus.androidsimpleclient.domain.core.features.message.useCases

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.features.message.IMessageGateway
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.core.features.message.model.SendMessageCommand

class SendMessageUseCase(
    private val messageGateway: IMessageGateway

) : IInputBoundary<MbResult<Unit>, SendMessageCommand> {

    override suspend fun invoke(cmd: SendMessageCommand): MbResult<Unit> {
        return messageGateway.sendMessage(cmd)
    }
}