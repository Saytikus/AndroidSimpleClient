package ru.saytikus.androidsimpleclient.domain.chatList.useCases

import ru.saytikus.androidsimpleclient.domain.chatList.IChatListGateway
import ru.saytikus.androidsimpleclient.domain.chatList.ChatListItem
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary

class GetProfileChatsUseCase(

    private val chatGateway: IChatListGateway

) : IInputBoundary<MbResult<List<ChatListItem>>, Unit> {

    override suspend fun invoke(cmd: Unit): MbResult<List<ChatListItem>> {
        return chatGateway.getProfileChats()
    }
}