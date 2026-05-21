package ru.saytikus.androidsimpleclient.domain.chat.useCases

import ru.saytikus.androidsimpleclient.domain.chat.IChatGateway
import ru.saytikus.androidsimpleclient.domain.chat.model.ChatListItem
import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary

class GetProfileChatsUseCase(

    private val chatGateway: IChatGateway

) : IInputBoundary<MbResult<List<ChatListItem>>, Unit> {

    override suspend fun invoke(cmd: Unit): MbResult<List<ChatListItem>> {
        return chatGateway.getProfileChats()
    }
}