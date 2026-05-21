package ru.saytikus.androidsimpleclient.domain.core.message.useCases

import kotlinx.coroutines.flow.Flow
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IObserveInputBoundary
import ru.saytikus.androidsimpleclient.domain.core.message.IMessageGateway
import ru.saytikus.androidsimpleclient.domain.core.message.model.MessageEvent

class ObserveMessageEventsUseCase(

    private val messageGateway: IMessageGateway

) : IObserveInputBoundary<Flow<MessageEvent>> {

    override fun invoke(): Flow<MessageEvent> = messageGateway.messageEvents
}