package ru.saytikus.androidsimpleclient.data.core.source.remote.signalR

import eu.lepicekmichal.signalrkore.HubConnection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.KSerializer


internal class HubSubscribeHandlerRegistry {


    private val mutex = Mutex()

    // subscribe handlers dictionary with method name key
    private val handlers = mutableMapOf<String, SubscribeHandler>()

    // handler scope's jobs
    private val jobs = mutableMapOf<String, Job>()

    // scope for handlers
    private var handlerScope: CoroutineScope = newScope()



    suspend fun subscribe(
        method: String,

        connection: HubConnection?,

        action: suspend () -> Unit,

        ) = register(NoArgSubscribeHandler(method, action), connection)

    suspend fun <T1> subscribe(
        method: String,

        s1: KSerializer<T1>,

        connection: HubConnection?,

        action: suspend (T1) -> Unit,

        ) = register(OneArgSubscribeHandler(method, s1, action), connection)

    suspend fun <T1, T2> subscribe(
        method: String,

        s1: KSerializer<T1>,

        s2: KSerializer<T2>,

        connection: HubConnection?,

        action: suspend (T1, T2) -> Unit,

        ) = register(
        TwoArgSubscribeHandler(
            method,
            s1,
            s2,
            action
        ),
        connection
    )

    suspend fun <T1, T2, T3> subscribe(
        method: String,

        s1: KSerializer<T1>,

        s2: KSerializer<T2>,

        s3: KSerializer<T3>,

        connection: HubConnection?,

        action: suspend (T1, T2, T3) -> Unit,

        ) = register(
        ThreeArgSubscribeHandler(
            method,
            s1,
            s2,
            s3,
            action
        ),
        connection
    )

    suspend fun <T1, T2, T3, T4> subscribe(
        method: String,

        s1: KSerializer<T1>,

        s2: KSerializer<T2>,

        s3: KSerializer<T3>,

        s4: KSerializer<T4>,

        connection: HubConnection?,

        action: suspend (T1, T2, T3, T4) -> Unit,

        ) = register(
        FourArgSubscribeHandler(
            method,
            s1,
            s2,
            s3,
            s4,
            action
        ),
        connection
    )

    // cancel old connection's jobs and create jobs for new connection
    suspend fun reSubscribeAll(newConnection: HubConnection) {
        mutex.withLock {
            cancelScopeLocked()
            handlers.values.forEach { handler ->
                jobs[handler.method] = handler.subscribe(newConnection, handlerScope)
            }
        }
    }

    // full clear
    suspend fun cancelAll() {
        mutex.withLock {
            cancelScopeLocked()
            handlers.clear()
        }
    }


    // register handler
    private suspend fun register(handler: SubscribeHandler, connection: HubConnection?) {
        mutex.withLock {
            jobs[handler.method]?.cancel()
            handlers[handler.method] = handler
            if (connection != null) {
                jobs[handler.method] = handler.subscribe(connection, handlerScope)
            }
        }
    }

    // clear jobs and scope
    private fun cancelScopeLocked() {
        handlerScope.cancel()
        handlerScope = newScope()
        jobs.clear()
    }

    // create new scope
    private fun newScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.IO + SupervisorJob())
    }
}