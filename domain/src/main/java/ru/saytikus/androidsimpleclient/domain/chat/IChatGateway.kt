package ru.saytikus.androidsimpleclient.domain.chat

import kotlinx.coroutines.flow.Flow
import ru.saytikus.androidsimpleclient.domain.chat.dto.CreatePrivateChatAnswer
import ru.saytikus.androidsimpleclient.domain.chat.dto.CreatePrivateChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.dto.JoinChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.dto.LeaveChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.model.ChatEvent
import ru.saytikus.androidsimpleclient.domain.chat.model.ChatListItem
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
interface IChatGateway {

    val chatEvents: Flow<ChatEvent>


    suspend fun getProfileChats(): MbResult<List<ChatListItem>>

    suspend fun createPrivateChat(cmd: CreatePrivateChatCommand): MbResult<CreatePrivateChatAnswer>

    suspend fun joinChat(cmd: JoinChatCommand): MbResult<Unit>

    suspend fun leaveChat(cmd: LeaveChatCommand): MbResult<Unit>
}