package ru.saytikus.androidsimpleclient.domain.chat

import ru.saytikus.androidsimpleclient.domain.chat.dto.CreatePrivateChatAnswer
import ru.saytikus.androidsimpleclient.domain.chat.dto.CreatePrivateChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.entities.ChatListItem
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult

interface IChatGateway {

    suspend fun getProfileChats(): MbResult<List<ChatListItem>>

    suspend fun createPrivateChat(cmd: CreatePrivateChatCommand): MbResult<CreatePrivateChatAnswer>
}