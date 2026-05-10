package ru.saytikus.androidsimpleclient.data.chat

import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.chat.souce.remote.IChatService
import ru.saytikus.androidsimpleclient.data.chat.souce.remote.toDomain
import ru.saytikus.androidsimpleclient.data.core.handleRetrofitServiceResult
import ru.saytikus.androidsimpleclient.data.core.source.remote.retrofit.interfaces.IRetrofitProvider
import ru.saytikus.androidsimpleclient.domain.chat.IChatGateway
import ru.saytikus.androidsimpleclient.domain.chat.dto.CreatePrivateChatAnswer
import ru.saytikus.androidsimpleclient.domain.chat.dto.CreatePrivateChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.entities.ChatListItem
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import kotlin.uuid.ExperimentalUuidApi


@Single
class ChatGateway(

    private val retrofitProvider: IRetrofitProvider

) : IChatGateway {

    private val _service: IChatService
        get() = retrofitProvider.retrofit().create(IChatService::class.java)


    override suspend fun getProfileChats(): MbResult<List<ChatListItem>> {
        println("Gateway call ChatListGateway::getProfileChats")

        val result = runCatching { _service.getProfileChats() }

        val answer = handleRetrofitServiceResult(result)

        return if(answer is MbResult.Success) MbResult.Success(answer.response.map { it.toDomain() })
        else answer as MbResult.Failure
    }

    @OptIn(ExperimentalUuidApi::class)
    override suspend fun createPrivateChat(cmd: CreatePrivateChatCommand): MbResult<CreatePrivateChatAnswer> {
        println("Gateway call ChatListGateway::createPrivateChat")

        val result = runCatching { _service.createPrivateChat(cmd.selectedUserId) }

        val answer = handleRetrofitServiceResult(result)

        return if(answer is MbResult.Success) MbResult.Success(answer.response.toDomain())
        else answer as MbResult.Failure
    }
}