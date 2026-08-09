package ru.saytikus.androidsimpleclient.domain.connection.useCases

import kotlinx.coroutines.flow.Flow
import ru.saytikus.androidsimpleclient.domain.connection.ConnectionState
import ru.saytikus.androidsimpleclient.domain.connection.IConnectionGateway
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IObserveInputBoundary

class ObserveConnectionStateUseCase(

    private val connectionGateway: IConnectionGateway

) : IObserveInputBoundary<Flow<ConnectionState>> {

    override fun invoke(): Flow<ConnectionState> = connectionGateway.connectionState
}