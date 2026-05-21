package ru.saytikus.androidsimpleclient.data.core.features.message

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.core.features.message.constants.MessageHubEvents
import ru.saytikus.androidsimpleclient.data.core.features.message.constants.MessageHubMethods
import ru.saytikus.androidsimpleclient.data.core.features.message.dto.MessageDto
import ru.saytikus.androidsimpleclient.data.core.features.message.source.remote.toDomain
import ru.saytikus.androidsimpleclient.data.core.handleHubResult
import ru.saytikus.androidsimpleclient.data.core.source.remote.signalR.IHubProvider
import ru.saytikus.androidsimpleclient.data.core.source.remote.signalR.sendAwait
import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.features.message.IMessageGateway
import ru.saytikus.androidsimpleclient.domain.core.features.message.model.MessageEvent
import ru.saytikus.androidsimpleclient.domain.core.features.message.model.SendMessageCommand
import kotlin.uuid.ExperimentalUuidApi


@Single
class MessageGateway(

    private val hubProvider: IHubProvider

) : IMessageGateway {

    private val _messageEvents = MutableSharedFlow<MessageEvent>(
        replay = 0,
        extraBufferCapacity = 10
    )
    override val messageEvents = _messageEvents.asSharedFlow()


    init {

        hubProvider.subscribe(
            MessageHubEvents.MESSAGE_CREATED,
            MessageDto.serializer()
        ) { messageDto ->
            println("MessageGateway received ${MessageHubEvents.MESSAGE_CREATED}: $messageDto")
            _messageEvents.emit(MessageEvent.MessageCreated(messageDto.toDomain()))
        }
    }
//
    @OptIn(ExperimentalUuidApi::class)
    override suspend fun sendMessage(cmd: SendMessageCommand): MbResult<Unit> {
        println("Gateway call MessageGateway::sendMessage")

        val result = runCatching {
            hubProvider.sendAwait(
                MessageHubMethods.SEND_MESSAGE,
                cmd.chatId,
                cmd.text
            )
        }

        val answer = handleHubResult(result)

        return answer
    }
}