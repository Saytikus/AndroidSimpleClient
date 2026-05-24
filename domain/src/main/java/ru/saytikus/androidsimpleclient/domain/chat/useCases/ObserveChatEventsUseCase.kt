package ru.saytikus.androidsimpleclient.domain.chat.useCases

import kotlinx.coroutines.flow.Flow
import ru.saytikus.androidsimpleclient.domain.chat.IChatGateway
import ru.saytikus.androidsimpleclient.domain.chat.model.ChatEvent
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IObserveInputBoundary

class ObserveChatEventsUseCase(

    private val chatGateway: IChatGateway

) : IObserveInputBoundary<Flow<ChatEvent>> {

    override fun invoke(): Flow<ChatEvent> = chatGateway.chatEvents
}