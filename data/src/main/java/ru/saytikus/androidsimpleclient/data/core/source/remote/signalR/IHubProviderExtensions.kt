package ru.saytikus.androidsimpleclient.data.core.source.remote.signalR

import kotlinx.serialization.serializer

suspend inline fun <reified T : Any> IHubProvider.sendAwait(method: String, message: T) =
    sendAwait(method, message, serializer<T>())