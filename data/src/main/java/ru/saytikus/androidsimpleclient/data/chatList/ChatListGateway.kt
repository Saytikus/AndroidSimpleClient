package ru.saytikus.androidsimpleclient.data.chatList

import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.chatList.souce.remote.IChatListService
import ru.saytikus.androidsimpleclient.data.chatList.souce.remote.toDomain
import ru.saytikus.androidsimpleclient.data.core.source.remote.interfaces.IRetrofitProvider
import ru.saytikus.androidsimpleclient.domain.chatList.IChatListGateway
import ru.saytikus.androidsimpleclient.domain.chatList.ChatListItem
import ru.saytikus.androidsimpleclient.domain.common.dto.MbError
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.valueObject.DomainError
import java.io.IOException
import java.net.SocketTimeoutException


@Single
class ChatListGateway(

    private val retrofitProvider: IRetrofitProvider

) : IChatListGateway {

    private val _service: IChatListService
        get() = retrofitProvider.retrofit().create(IChatListService::class.java)


    override suspend fun getProfileChats(): MbResult<List<ChatListItem>> {
        val result = runCatching { _service.getProfileChats() }

        if (result.isFailure) {
            val exception = result.exceptionOrNull()

            if (exception == null) {
                println("ChatGateway::getProfileChats: catch null exception.")
                return MbResult.Failure(MbError(DomainError.GatewayError.UnknownError))
            }

            println("ChatGateway::getProfileChats: receive exception: ${exception.toString()}.")

            if (exception is SocketTimeoutException) {
                return MbResult.Failure(MbError(DomainError.GatewayError.Timeout))
            }

            if (exception is IOException) {
                return MbResult.Failure(MbError(DomainError.GatewayError.NoChannel))
            }

            if (exception is IllegalArgumentException) {
                return MbResult.Failure(MbError(DomainError.MapError.UnexpectedFormat))
            }

        }

        val response = result.getOrNull()

        if (response == null) {
            println("ChatGateway::getProfileChats: response in null.")
            return MbResult.Failure(MbError(DomainError.GatewayError.UnknownError))
        }

        val answer = response.body()

        if(answer == null) {
            println("ChatGateway::getProfileChats: answer in null.")
            return MbResult.Failure(MbError(DomainError.GatewayError.UnknownError))
        }

        if (!response.isSuccessful) {
            println("ChatGateway::getProfileChats: response not successfully.")
            return MbResult.Failure(
                MbError(
                    DomainError.GatewayError.RequestError(
                        response.code(),
                        response.errorBody()?.string()
                    )
                )
            )
        }

        return MbResult.Success(answer.map { it.toDomain() })
    }
}