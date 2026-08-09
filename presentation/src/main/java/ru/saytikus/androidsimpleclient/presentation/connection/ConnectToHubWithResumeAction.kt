package ru.saytikus.androidsimpleclient.presentation.connection

import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.presentation.core.IAppLifecycleAction

@Single
class ConnectToHubWithResumeAction(

    @Named("ConnectToHubUseCase")
    private val connectCase: IInputBoundary<Unit, Unit>

) : IAppLifecycleAction {

    override val name = "ConnectToHubWithResumeAction"

    override suspend fun onAppResume() {
        connectCase(Unit)
    }
}