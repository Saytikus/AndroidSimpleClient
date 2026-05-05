package ru.saytikus.androidsimpleclient.domain.chat.useCases

import ru.saytikus.androidsimpleclient.domain.chat.IChatGateway
import ru.saytikus.androidsimpleclient.domain.chat.entities.ChatListItem
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary

class GetProfileChatsUseCase(

    private val chatGateway: IChatGateway

) : IInputBoundary<MbResult<List<ChatListItem>>, Unit> {

    override suspend fun invoke(cmd: Unit): MbResult<List<ChatListItem>> {
        return chatGateway.getProfileChats()
    }
}