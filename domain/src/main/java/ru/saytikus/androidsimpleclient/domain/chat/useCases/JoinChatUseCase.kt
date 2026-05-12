package ru.saytikus.androidsimpleclient.domain.chat.useCases

import ru.saytikus.androidsimpleclient.domain.chat.IChatGateway
import ru.saytikus.androidsimpleclient.domain.chat.dto.JoinChatCommand
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class JoinChatUseCase(

    private val chatGateway: IChatGateway

) : IInputBoundary<MbResult<Unit>, JoinChatCommand> {

    override suspend fun invoke(cmd: JoinChatCommand): MbResult<Unit> {
        return chatGateway.joinChat(cmd)
    }
}