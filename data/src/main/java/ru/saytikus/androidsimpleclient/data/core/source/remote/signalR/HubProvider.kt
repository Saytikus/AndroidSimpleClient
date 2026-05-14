package ru.saytikus.androidsimpleclient.data.core.source.remote.signalR

import eu.lepicekmichal.signalrkore.AutomaticReconnect
import eu.lepicekmichal.signalrkore.HubConnection
import eu.lepicekmichal.signalrkore.HubConnectionBuilder
import eu.lepicekmichal.signalrkore.HubConnectionState
import eu.lepicekmichal.signalrkore.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.KSerializer
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.domain.chat.ChatConnectionState
import ru.saytikus.androidsimpleclient.domain.common.encryptedSettings.EncryptedSettings
import ru.saytikus.androidsimpleclient.domain.common.interfaces.ISingleObjectRepository
import ru.saytikus.androidsimpleclient.domain.settings.ISettingsRepository
import kotlin.concurrent.atomics.ExperimentalAtomicApi

@Single
@OptIn(ExperimentalAtomicApi::class, ExperimentalCoroutinesApi::class)
class HubProvider(

    private val _settingsRepo: ISettingsRepository,

    @Named("EncryptedSettingsRepository")
    private val _encryptedSettingsRepository: ISingleObjectRepository<EncryptedSettings>

) : IHubProvider {




    private val _scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val _connectionState = MutableStateFlow(ChatConnectionState.DISCONNECTED)
    override val connectionState = _connectionState.asStateFlow()

    // hub connection
    private var _connection: HubConnection? = null
    private val _connectionMutex = Mutex()

    private var _stateCollectorJob: Job? = null

    // subscribe handlers registry
    private val _registry = HubSubscribeHandlerRegistry()



    init {
        _scope.launch {
            try {
                val connection = createConnection()
                _connectionMutex.withLock { _connection = connection }
                subscribeToConnectionState(connection)
                _registry.reSubscribeAll(connection)
            } catch (e: Exception) {
                println("HubProvider: initial connection creation failed: $e")
            }


            _settingsRepo.observeSettings()
                .map { it.responseServerHostAddress }
                .distinctUntilChanged()
                .collect { newAddress ->
                    println("HubProvider: host address changed to $newAddress, recreating connection")
                    val wasConnected = _connectionState.value == ChatConnectionState.CONNECTED
                    updateConnection(wasConnected)
                }
        }
    }



    override suspend fun connect() {
        val connection = _connectionMutex.withLock { _connection }
            ?: error("HubProvider: connection is not initialized yet")
        try {
            connection.start()
        } catch (e: Exception) {
            println("HubProvider: connect() failed: $e")
            throw e
        }
    }

    override suspend fun disconnect() {
        val connection = _connectionMutex.withLock { _connection } ?: return
        try {
            connection.stop()
        } catch (e: Exception) {
            println("HubProvider: disconnect() failed: $e")
        }
    }



    override suspend fun send(method: String, message: Any) {
        val connection = _connectionMutex.withLock { _connection }
            ?: error("HubProvider: connection is not initialized yet")
        try {
            connection.send(method, message)
        } catch (e: Exception) {
            println("HubProvider: send() failed [$method]: $e")
            throw e
        }
    }

    override suspend fun <T : Any> sendAwait(
        method: String,
        message: T,
        serializer: KSerializer<T>,
    ) {
        val connection = _connectionMutex.withLock { _connection }
            ?: error("HubProvider: connection is not initialized yet")
        try {
            connection.invoke(method, message, serializer)
        } catch (e: Exception) {
            println("HubProvider: sendAwait() failed [$method]: $e")
            throw e
        }
    }



    override fun subscribe(method: String, action: suspend () -> Unit) {
        _scope.launch { _registry.subscribe(method, currentConnection(), action) }
    }

    override fun <T1> subscribe(
        method: String,
        s1: KSerializer<T1>,
        action: suspend (T1) -> Unit,
    ) {
        _scope.launch { _registry.subscribe(method, s1, currentConnection(), action) }
    }

    override fun <T1, T2> subscribe(
        method: String,
        s1: KSerializer<T1>,
        s2: KSerializer<T2>,
        action: suspend (T1, T2) -> Unit,
    ) {
        _scope.launch { _registry.subscribe(method, s1, s2, currentConnection(), action) }
    }

    override fun <T1, T2, T3> subscribe(
        method: String,
        s1: KSerializer<T1>,
        s2: KSerializer<T2>,
        s3: KSerializer<T3>,
        action: suspend (T1, T2, T3) -> Unit,
    ) {
        _scope.launch { _registry.subscribe(method, s1, s2, s3, currentConnection(), action) }
    }

    override fun <T1, T2, T3, T4> subscribe(
        method: String,
        s1: KSerializer<T1>,
        s2: KSerializer<T2>,
        s3: KSerializer<T3>,
        s4: KSerializer<T4>,
        action: suspend (T1, T2, T3, T4) -> Unit,
    ) {
        _scope.launch { _registry.subscribe(method, s1, s2, s3, s4, currentConnection(), action) }
    }



    private suspend fun currentConnection() = _connectionMutex.withLock { _connection }

    private fun subscribeToConnectionState(connection: HubConnection) {
        _stateCollectorJob?.cancel()
        _stateCollectorJob = _scope.launch {
            connection.connectionState.collect { state ->
                _connectionState.value = when (state) {
                    HubConnectionState.CONNECTING   -> ChatConnectionState.CONNECTING
                    HubConnectionState.CONNECTED    -> ChatConnectionState.CONNECTED
                    HubConnectionState.DISCONNECTED -> ChatConnectionState.DISCONNECTED
                    HubConnectionState.RECONNECTING -> ChatConnectionState.RECONNECTING
                }
            }
        }
    }

    private suspend fun updateConnection(wasConnected: Boolean) {
        try {
            _connectionMutex.withLock { _connection }?.stop()

            val newConnection = createConnection()
            _connectionMutex.withLock { _connection = newConnection }

            subscribeToConnectionState(newConnection)
            _registry.reSubscribeAll(newConnection)

            if (wasConnected) {
                try {
                    newConnection.start()
                } catch (e: Exception) {
                    println("HubProvider: failed to restart after update: $e")
                }
            }
        } catch (e: Exception) {
            println("HubProvider: updateConnection failed: $e")
        }
    }

    private suspend fun createConnection(): HubConnection {
        val hostAddress = _settingsRepo.getOnce().responseServerHostAddress
        return HubConnectionBuilder.create("http://$hostAddress:8080/hubs/chat") {
            automaticReconnect = AutomaticReconnect.Active
            accessTokenProvider = { _encryptedSettingsRepository.getOnce().authenticationToken }
            logger = Logger { severity, message, cause ->
                when (severity) {
                    Logger.Severity.INFO    -> println("SIGNALR INFO: $message")
                    Logger.Severity.WARNING -> println("SIGNALR WARNING: $message")
                    Logger.Severity.ERROR   -> println("SIGNALR ERROR: $message, cause: $cause")
                }
            }
        }
    }
}