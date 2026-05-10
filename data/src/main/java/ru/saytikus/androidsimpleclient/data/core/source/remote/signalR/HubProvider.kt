package ru.saytikus.androidsimpleclient.data.core.source.remote.signalR

import eu.lepicekmichal.signalrkore.AutomaticReconnect
import eu.lepicekmichal.signalrkore.HubConnection
import eu.lepicekmichal.signalrkore.HubConnectionBuilder
import eu.lepicekmichal.signalrkore.HubConnectionState
import eu.lepicekmichal.signalrkore.Logger
import eu.lepicekmichal.signalrkore.OnValue1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.domain.chat.ChatConnectionState
import ru.saytikus.androidsimpleclient.domain.common.encryptedSettings.EncryptedSettings
import ru.saytikus.androidsimpleclient.domain.common.interfaces.ISingleObjectRepository
import ru.saytikus.androidsimpleclient.domain.settings.ISettingsRepository

@Single
class HubProvider(

    private val _settingsRepo: ISettingsRepository,

    @Named("EncryptedSettingsRepository")
    private val _encryptedSettingsRepository: ISingleObjectRepository<EncryptedSettings>

) : IHubProvider {

    private var _connection = runBlocking {
         createConnection()
    }

    private val _scope = CoroutineScope(Dispatchers.IO)



    override fun connectionState() = _connection.connectionState.map {
        when(it) {
            HubConnectionState.CONNECTING -> {
                ChatConnectionState.CONNECTING
            }

            HubConnectionState.CONNECTED -> {
                ChatConnectionState.CONNECTED
            }

            HubConnectionState.DISCONNECTED -> {
                ChatConnectionState.DISCONNECTED
            }

            HubConnectionState.RECONNECTING -> {
                ChatConnectionState.RECONNECTING
            }
        }
    }



    init {

        _scope.launch {
            _settingsRepo.observeSettings()
                .distinctUntilChanged()
                .onEach {
                    _connection = createConnection()
                }
                .collect()
        }
    }

    override suspend fun connect() = withContext(Dispatchers.IO) {
        _connection.start()
    }

    override suspend fun disconnect() = withContext(Dispatchers.IO) {
        _connection.stop()
    }

    override suspend fun send(
        method: String,

        message: Any

    ) = withContext(Dispatchers.IO) {
        _connection.send(method, message)
    }

    override suspend fun <ResponseType : Any> sendAwait(
        method: String,

        responseSerializer: KSerializer<ResponseType>,

        message: Any

    ) = withContext(Dispatchers.IO) {
        return@withContext _connection.invoke(
            method,
            responseSerializer,
            message
        )
    }

    override fun <ResponseType : Any> messageFlow(
        method: String,

        messageSerializer: KSerializer<ResponseType>

    ): Flow<OnValue1<ResponseType>> {
        return _connection.on(method, messageSerializer)
    }


    private suspend fun createConnection(): HubConnection {
        return HubConnectionBuilder.create("http://${_settingsRepo.getOnce().responseServerHostAddress}:8080/hubs/chat") {
            // create hub connection with auto reconnect, auth token in header and logger

            automaticReconnect = AutomaticReconnect.Active

            accessTokenProvider = { _encryptedSettingsRepository.getOnce().authenticationToken }

            logger = Logger { severity, message, cause ->
                // TODO replace println by logger
                when(severity) {
                    Logger.Severity.INFO -> println("SIGNALR INFO: $message")
                    Logger.Severity.WARNING -> println("SIGNALR WARNING: $message")
                    Logger.Severity.ERROR -> println("SIGNALR ERROR: $message, cause: $cause")
                }
            }
        }
    }
}