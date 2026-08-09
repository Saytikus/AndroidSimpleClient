package ru.saytikus.androidsimpleclient.domain.connection.useCases

import ru.saytikus.androidsimpleclient.domain.connection.IConnectionGateway
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary

// TODO: separate IInputBoundary interface without params
class ConnectToHubUseCase(

    private val connectionGateway: IConnectionGateway

) : IInputBoundary<Unit, Unit> {

    override suspend fun invoke(cmd: Unit) {
        connectionGateway.connect()
    }
}