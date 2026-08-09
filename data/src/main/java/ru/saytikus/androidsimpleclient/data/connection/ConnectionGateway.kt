package ru.saytikus.androidsimpleclient.data.connection

import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.core.source.remote.signalR.IHubProvider
import ru.saytikus.androidsimpleclient.domain.connection.ConnectionState
import ru.saytikus.androidsimpleclient.domain.connection.IConnectionGateway

@Single
class ConnectionGateway(

    private val hubProvider: IHubProvider

) : IConnectionGateway {

    override val connectionState: Flow<ConnectionState> = hubProvider.connectionState

    override suspend fun connect() {
        runCatching { hubProvider.connect() }.onFailure { println("Received exception with CONNECT TO HUB: $it") }
    }

    override suspend fun disconnect() {
        runCatching { hubProvider.disconnect() }.onFailure { println("Received exception with DISCONNECT FROM HUB: $it") }
    }
}