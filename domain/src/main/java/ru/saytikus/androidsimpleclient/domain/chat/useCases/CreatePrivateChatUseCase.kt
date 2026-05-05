package ru.saytikus.androidsimpleclient.domain.chat.useCases

import ru.saytikus.androidsimpleclient.domain.chat.IChatGateway
import ru.saytikus.androidsimpleclient.domain.chat.dto.CreatePrivateChatAnswer
import ru.saytikus.androidsimpleclient.domain.chat.dto.CreatePrivateChatCommand
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class CreatePrivateChatUseCase(

    private val gateway: IChatGateway

) : IInputBoundary<MbResult<CreatePrivateChatAnswer>, CreatePrivateChatCommand> {

    override suspend fun invoke(cmd: CreatePrivateChatCommand): MbResult<CreatePrivateChatAnswer> {
        return gateway.createPrivateChat(cmd)
    }
}