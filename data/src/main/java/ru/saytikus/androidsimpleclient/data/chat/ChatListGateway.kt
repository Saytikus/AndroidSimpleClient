package ru.saytikus.androidsimpleclient.data.chat

import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.chat.souce.remote.IChatListService
import ru.saytikus.androidsimpleclient.data.chat.souce.remote.toDomain
import ru.saytikus.androidsimpleclient.data.core.handleRetrofitServiceResult
import ru.saytikus.androidsimpleclient.data.core.source.remote.interfaces.IRetrofitProvider
import ru.saytikus.androidsimpleclient.domain.chat.IChatListGateway
import ru.saytikus.androidsimpleclient.domain.chat.ChatListItem
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult


@Single
class ChatListGateway(

    private val retrofitProvider: IRetrofitProvider

) : IChatListGateway {

    private val _service: IChatListService
        get() = retrofitProvider.retrofit().create(IChatListService::class.java)


    override suspend fun getProfileChats(): MbResult<List<ChatListItem>> {
        println("Gateway call ChatListGateway::getProfileChats")

        val result = runCatching { _service.getProfileChats() }

        val answer = handleRetrofitServiceResult(result)

        return if(answer is MbResult.Success) MbResult.Success(answer.response.map { it.toDomain() })
        else answer as MbResult.Failure
    }
}