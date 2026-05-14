package ru.saytikus.androidsimpleclient.data.core.source.remote.signalR

import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.KSerializer
import ru.saytikus.androidsimpleclient.domain.chat.ChatConnectionState

interface IHubProvider {

    val connectionState: Flow<ChatConnectionState>


    suspend fun connect()

    suspend fun disconnect()

    suspend fun send(
        method: String,

        message: Any
    )

    suspend fun <T : Any> sendAwait(
        method: String,
        message: T,
        serializer: KSerializer<T>
    )

    fun subscribe(method: String, action: suspend () -> Unit)

    fun <T1> subscribe(
        method: String,

        s1: KSerializer<T1>,

        action: suspend (T1) -> Unit,
    )

    fun <T1, T2> subscribe(
        method: String,

        s1: KSerializer<T1>,

        s2: KSerializer<T2>,

        action: suspend (T1, T2) -> Unit,
    )

    fun <T1, T2, T3> subscribe(
        method: String,

        s1: KSerializer<T1>,

        s2: KSerializer<T2>,

        s3: KSerializer<T3>,

        action: suspend (T1, T2, T3) -> Unit,
    )

    fun <T1, T2, T3, T4> subscribe(
        method: String,

        s1: KSerializer<T1>,

        s2: KSerializer<T2>,

        s3: KSerializer<T3>,

        s4: KSerializer<T4>,

        action: suspend (T1, T2, T3, T4) -> Unit,
    )
}