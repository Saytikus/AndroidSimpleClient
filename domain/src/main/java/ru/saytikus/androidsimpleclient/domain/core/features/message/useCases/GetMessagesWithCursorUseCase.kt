package ru.saytikus.androidsimpleclient.domain.core.features.message.useCases

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.features.message.IMessageGateway
import ru.saytikus.androidsimpleclient.domain.core.features.message.model.GetMessagesWithCursorCommand
import ru.saytikus.androidsimpleclient.domain.core.features.message.model.MessagesWithCursor
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary

class GetMessagesWithCursorUseCase(

    private val messageGateway: IMessageGateway

) : IInputBoundary<MbResult<MessagesWithCursor>, GetMessagesWithCursorCommand> {

    override suspend fun invoke(cmd: GetMessagesWithCursorCommand): MbResult<MessagesWithCursor> {
        return messageGateway.getMessagesWithCursor(cmd)
    }
}