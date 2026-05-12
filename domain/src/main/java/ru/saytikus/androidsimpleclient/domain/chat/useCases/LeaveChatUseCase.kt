package ru.saytikus.androidsimpleclient.domain.chat.useCases

import ru.saytikus.androidsimpleclient.domain.chat.IChatGateway
import ru.saytikus.androidsimpleclient.domain.chat.dto.LeaveChatCommand
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class LeaveChatUseCase(

    private val chatGateway: IChatGateway

) : IInputBoundary<MbResult<Unit>, LeaveChatCommand> {

    override suspend fun invoke(cmd: LeaveChatCommand): MbResult<Unit> {
        return chatGateway.leaveChat(cmd)
    }
}