package ru.saytikus.androidsimpleclient.domain.chat.useCases

import ru.saytikus.androidsimpleclient.domain.chat.IChatGateway
import ru.saytikus.androidsimpleclient.domain.chat.answers.A3ChatListItem
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary

class GetProfileChatsUseCase(

    private val chatGateway: IChatGateway

) : IInputBoundary<MbResult<List<A3ChatListItem>>, Unit> {

    override suspend fun invoke(cmd: Unit): MbResult<List<A3ChatListItem>> {
        return chatGateway.getProfileChats()
    }
}