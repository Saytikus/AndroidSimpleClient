package ru.saytikus.androidsimpleclient.domain.chat

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult

interface IChatListGateway {

    suspend fun getProfileChats(): MbResult<List<ChatListItem>>
}