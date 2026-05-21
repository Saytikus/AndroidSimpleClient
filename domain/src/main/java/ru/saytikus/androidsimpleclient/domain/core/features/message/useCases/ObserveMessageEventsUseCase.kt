package ru.saytikus.androidsimpleclient.domain.core.features.message.useCases

import kotlinx.coroutines.flow.Flow
import ru.saytikus.androidsimpleclient.domain.core.features.message.IMessageGateway
import ru.saytikus.androidsimpleclient.domain.core.features.message.model.MessageEvent
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IObserveInputBoundary

class ObserveMessageEventsUseCase(

    private val messageGateway: IMessageGateway

) : IObserveInputBoundary<Flow<MessageEvent>> {

    override fun invoke(): Flow<MessageEvent> = messageGateway.messageEvents
}