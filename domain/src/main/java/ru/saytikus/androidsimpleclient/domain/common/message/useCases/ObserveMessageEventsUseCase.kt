package ru.saytikus.androidsimpleclient.domain.common.message.useCases

import kotlinx.coroutines.flow.Flow
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IObserveInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.message.IMessageGateway
import ru.saytikus.androidsimpleclient.domain.common.message.model.MessageEvent

class ObserveMessageEventsUseCase(

    private val messageGateway: IMessageGateway

) : IObserveInputBoundary<Flow<MessageEvent>> {

    override fun invoke(): Flow<MessageEvent> = messageGateway.messageEvents
}