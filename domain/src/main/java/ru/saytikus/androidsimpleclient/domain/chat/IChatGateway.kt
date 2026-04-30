package ru.saytikus.androidsimpleclient.domain.chat

import ru.saytikus.androidsimpleclient.domain.chat.answers.A3ChatListItem
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult

interface IChatGateway {

    suspend fun getProfileChats(): MbResult<List<A3ChatListItem>>
}