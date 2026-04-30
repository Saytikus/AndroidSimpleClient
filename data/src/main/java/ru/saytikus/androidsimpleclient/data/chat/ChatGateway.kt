package ru.saytikus.androidsimpleclient.data.chat

import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.chat.souce.remote.IChatService
import ru.saytikus.androidsimpleclient.data.chat.souce.remote.toDomain
import ru.saytikus.androidsimpleclient.data.core.source.remote.interfaces.IRetrofitProvider
import ru.saytikus.androidsimpleclient.domain.chat.IChatGateway
import ru.saytikus.androidsimpleclient.domain.chat.answers.A3ChatListItem
import ru.saytikus.androidsimpleclient.domain.common.dto.MbError
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.valueObject.DomainError
import java.io.IOException
import java.net.SocketTimeoutException


@Single
class ChatGateway(

    private val retrofitProvider: IRetrofitProvider

) : IChatGateway {

    private val _service: IChatService
        get() = retrofitProvider.retrofit().create(IChatService::class.java)


    override suspend fun getProfileChats(): MbResult<List<A3ChatListItem>> {
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