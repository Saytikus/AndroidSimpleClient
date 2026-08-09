package ru.saytikus.androidsimpleclient.presentation.connection

import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.presentation.core.IAppLifecycleAction

@Single
class DisconnectFromHubWithPauseAction(

    @Named("DisconnectFromHubUseCase")
    private val disconnectCase: IInputBoundary<Unit, Unit>

) : IAppLifecycleAction {

    override val name = "DisconnectFromHubWithPauseAction"

    override suspend fun onAppPause() {
        disconnectCase(Unit)
    }
}