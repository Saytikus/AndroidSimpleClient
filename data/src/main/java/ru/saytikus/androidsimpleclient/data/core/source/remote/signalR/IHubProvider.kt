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

    suspend fun <ArgumentType : Any> sendAwait(
        method: String,

        message: ArgumentType,

        serializer: KSerializer<ArgumentType>
    )

    suspend fun <ArgumentType1 : Any, ArgumentType2 : Any> sendAwait(
        method: String,

        message1: ArgumentType1,

        serializer1: KSerializer<ArgumentType1>,

        message2: ArgumentType2,

        serializer2: KSerializer<ArgumentType2>
    )

    suspend fun <
            ArgumentType1 : Any,
            ArgumentType2 : Any,
            ArgumentType3 : Any
            > sendAwait(
        method: String,

        message1: ArgumentType1,

        serializer1: KSerializer<ArgumentType1>,

        message2: ArgumentType2,

        serializer2: KSerializer<ArgumentType2>,

        message3: ArgumentType3,

        serializer3: KSerializer<ArgumentType3>
    )

    suspend fun <
            ArgumentType1 : Any,
            ArgumentType2 : Any,
            ArgumentType3 : Any,
            ArgumentType4 : Any
            > sendAwait(
        method: String,

        message1: ArgumentType1,

        serializer1: KSerializer<ArgumentType1>,

        message2: ArgumentType2,

        serializer2: KSerializer<ArgumentType2>,

        message3: ArgumentType3,

        serializer3: KSerializer<ArgumentType3>,

        message4: ArgumentType4,

        serializer4: KSerializer<ArgumentType4>
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