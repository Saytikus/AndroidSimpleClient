package ru.saytikus.androidsimpleclient.data.core.source.remote.signalR

import eu.lepicekmichal.signalrkore.OnValue1
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.KSerializer
import ru.saytikus.androidsimpleclient.domain.chat.ChatConnectionState

interface IHubProvider {

    fun connectionState(): Flow<ChatConnectionState>

    suspend fun connect()

    suspend fun disconnect()

    suspend fun send(
        method: String,

        message: Any
    )

    suspend fun <ResponseType : Any> sendAwait(
        method: String,

        responseSerializer: KSerializer<ResponseType>,

        message: Any

    )

    fun <ResponseType : Any> messageFlow(
        method: String,

        messageSerializer: KSerializer<ResponseType>
    ): Flow<OnValue1<ResponseType>>
}