package ru.saytikus.androidsimpleclient.data.core.source.remote.signalR

import eu.lepicekmichal.signalrkore.HubConnection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.KSerializer


internal sealed class SubscribeHandler(val method: String) {
    abstract fun subscribe(connection: HubConnection, scope: CoroutineScope): Job
}

internal class NoArgSubscribeHandler(
    method: String,

    private val action: suspend () -> Unit,
) : SubscribeHandler(method) {
    override fun subscribe(connection: HubConnection, scope: CoroutineScope): Job =
        connection.on(method)
            .onEach { action() }
            .catch { e -> println("Handler error [$method]: $e") }
            .launchIn(scope)
}

internal class OneArgSubscribeHandler<ResponseType1>(
    method: String,
    private val s1: KSerializer<ResponseType1>,
    private val action: suspend (ResponseType1) -> Unit,
) : SubscribeHandler(method) {

    override fun subscribe(connection: HubConnection, scope: CoroutineScope): Job =
        connection.on(method, s1)
            .onEach { (v1) -> action(v1) }
            .catch { e -> println("Handler error [$method]: $e") }
            .launchIn(scope)
}

internal class TwoArgSubscribeHandler<ResponseType1, ResponseType2>(
    method: String,

    private val s1: KSerializer<ResponseType1>,

    private val s2: KSerializer<ResponseType2>,

    private val action: suspend (ResponseType1, ResponseType2) -> Unit,
) : SubscribeHandler(method) {

    override fun subscribe(connection: HubConnection, scope: CoroutineScope): Job =
        connection.on(method, s1, s2)
            .onEach { (v1, v2) -> action(v1, v2) }
            .catch { e -> println("Handler error [$method]: $e") }
            .launchIn(scope)
}

internal class ThreeArgSubscribeHandler<ResponseType1, ResponseType2, ResponseType3>(
    method: String,

    private val s1: KSerializer<ResponseType1>,

    private val s2: KSerializer<ResponseType2>,

    private val s3: KSerializer<ResponseType3>,

    private val action: suspend (
        ResponseType1,
        ResponseType2,
        ResponseType3
    ) -> Unit,

    ) : SubscribeHandler(method) {

    override fun subscribe(connection: HubConnection, scope: CoroutineScope): Job =
        connection.on(method, s1, s2, s3)
            .onEach { (v1, v2, v3) -> action(v1, v2, v3) }
            .catch { e -> println("Handler error [$method]: $e") }
            .launchIn(scope)
}

internal class FourArgSubscribeHandler<ResponseType1, ResponseType2, ResponseType3, ResponseType4>(
    method: String,

    private val s1: KSerializer<ResponseType1>,

    private val s2: KSerializer<ResponseType2>,

    private val s3: KSerializer<ResponseType3>,

    private val s4: KSerializer<ResponseType4>,

    private val action: suspend (
        ResponseType1,
        ResponseType2,
        ResponseType3,
        ResponseType4
    ) -> Unit,

    ) : SubscribeHandler(method) {

    override fun subscribe(connection: HubConnection, scope: CoroutineScope): Job =
        connection.on(method, s1, s2, s3, s4)
            .onEach { (v1, v2, v3, v4) -> action(v1, v2, v3, v4) }
            .catch { e -> println("Handler error [$method]: $e") }
            .launchIn(scope)
}