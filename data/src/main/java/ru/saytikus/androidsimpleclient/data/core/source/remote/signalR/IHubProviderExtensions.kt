package ru.saytikus.androidsimpleclient.data.core.source.remote.signalR

import kotlinx.serialization.serializer

suspend inline fun <reified ArgumentType1 : Any> IHubProvider.sendAwait(method: String, message: ArgumentType1) =
    sendAwait(method, message, serializer<ArgumentType1>())

suspend inline fun <
        reified ArgumentType1 : Any,

        reified ArgumentType2 : Any

        > IHubProvider.sendAwait(

    method: String,

    message1: ArgumentType1,

    message2: ArgumentType2
) = sendAwait(

    method,

    message1,

    serializer<ArgumentType1>(),

    message2,

    serializer<ArgumentType2>()
)

suspend inline fun <
        reified ArgumentType1 : Any,

        reified ArgumentType2 : Any,

        reified ArgumentType3 : Any

        > IHubProvider.sendAwait(

    method: String,

    message1: ArgumentType1,

    message2: ArgumentType2,

    message3: ArgumentType3

) = sendAwait(

    method,

    message1,

    serializer<ArgumentType1>(),

    message2,

    serializer<ArgumentType2>(),

    message3,

    serializer<ArgumentType3>()
)

suspend inline fun <
        reified ArgumentType1 : Any,

        reified ArgumentType2 : Any,

        reified ArgumentType3 : Any,

        reified ArgumentType4 : Any

        > IHubProvider.sendAwait(

    method: String,

    message1: ArgumentType1,

    message2: ArgumentType2,

    message3: ArgumentType3,

    message4: ArgumentType4

) = sendAwait(

    method,

    message1,

    serializer<ArgumentType1>(),

    message2,

    serializer<ArgumentType2>(),

    message3,

    serializer<ArgumentType3>(),

    message4,

    serializer<ArgumentType4>()
)