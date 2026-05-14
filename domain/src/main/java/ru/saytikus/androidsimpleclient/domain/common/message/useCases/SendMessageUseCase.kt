package ru.saytikus.androidsimpleclient.domain.common.message.useCases

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.message.IMessageGateway
import ru.saytikus.androidsimpleclient.domain.common.message.model.SendMessageCommand

class SendMessageUseCase(
    private val messageGateway: IMessageGateway

) : IInputBoundary<MbResult<Unit>, SendMessageCommand> {

    override suspend fun invoke(cmd: SendMessageCommand): MbResult<Unit> {
        return messageGateway.sendMessage(cmd)
    }
}