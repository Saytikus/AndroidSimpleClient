package ru.saytikus.androidsimpleclient.data.chat

import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.chat.souce.remote.IChatService
import ru.saytikus.androidsimpleclient.data.chat.souce.remote.toDomain
import ru.saytikus.androidsimpleclient.data.core.handleHubResult
import ru.saytikus.androidsimpleclient.data.core.handleRetrofitServiceResult
import ru.saytikus.androidsimpleclient.data.core.source.remote.retrofit.interfaces.IRetrofitProvider
import ru.saytikus.androidsimpleclient.data.core.source.remote.signalR.IHubProvider
import ru.saytikus.androidsimpleclient.data.core.source.remote.signalR.sendAwait
import ru.saytikus.androidsimpleclient.domain.chat.IChatGateway
import ru.saytikus.androidsimpleclient.domain.chat.dto.CreatePrivateChatAnswer
import ru.saytikus.androidsimpleclient.domain.chat.dto.CreatePrivateChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.dto.JoinChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.dto.LeaveChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.entities.ChatListItem
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import kotlin.uuid.ExperimentalUuidApi


@OptIn(ExperimentalUuidApi::class)
@Single
class ChatGateway(

    private val _retrofitProvider: IRetrofitProvider,

    private val _hubProvider: IHubProvider

) : IChatGateway {

    private val _service: IChatService
        get() = _retrofitProvider.retrofit().create(IChatService::class.java)


    override suspend fun getProfileChats(): MbResult<List<ChatListItem>> {
        println("Gateway call ChatListGateway::getProfileChats")

        val result = runCatching { _service.getProfileChats() }

        val answer = handleRetrofitServiceResult(result)

        return if (answer is MbResult.Success) MbResult.Success(answer.response.map { it.toDomain() })
        else answer as MbResult.Failure
    }

    @OptIn(ExperimentalUuidApi::class)
    override suspend fun createPrivateChat(cmd: CreatePrivateChatCommand): MbResult<CreatePrivateChatAnswer> {
        println("Gateway call ChatListGateway::createPrivateChat")

        val result = runCatching { _service.createPrivateChat(cmd.selectedUserId) }

        val answer = handleRetrofitServiceResult(result)

        return if (answer is MbResult.Success) MbResult.Success(answer.response.toDomain())
        else answer as MbResult.Failure
    }

    override suspend fun joinChat(cmd: JoinChatCommand): MbResult<Unit> {
        println("Gateway call ChatListGateway::joinChat")

        val result = runCatching {
            _hubProvider.sendAwait(
                method = ChatHubMethods.JOIN_CHAT,
                message = cmd.chatId.toString()
            )
        }

        val answer = handleHubResult(result)

        return answer
    }

    override suspend fun leaveChat(cmd: LeaveChatCommand): MbResult<Unit> {
        println("Gateway call ChatListGateway::leaveChat")

        val result = runCatching {
            _hubProvider.sendAwait(
                method = ChatHubMethods.LEAVE_CHAT,
                message = cmd.chatId.toString()
            )
        }

        val answer = handleHubResult(result)

        return answer
    }
}