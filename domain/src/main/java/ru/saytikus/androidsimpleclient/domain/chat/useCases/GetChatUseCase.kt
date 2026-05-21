package ru.saytikus.androidsimpleclient.domain.chat.useCases

import ru.saytikus.androidsimpleclient.domain.chat.IChatGateway
import ru.saytikus.androidsimpleclient.domain.chat.dto.GetChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.model.Chat
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary

class GetChatUseCase(

    private val gateway: IChatGateway

) : IInputBoundary<MbResult<Chat>, GetChatCommand> {

    override suspend fun invoke(cmd: GetChatCommand): MbResult<Chat> {
        return gateway.getChat(cmd)
    }
}