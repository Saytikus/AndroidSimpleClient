package ru.saytikus.androidsimpleclient.domain.core.features.message

import kotlinx.coroutines.flow.Flow
import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.features.message.model.MessageEvent
import ru.saytikus.androidsimpleclient.domain.core.features.message.model.SendMessageCommand
import kotlin.uuid.ExperimentalUuidApi

interface IMessageGateway {

    val messageEvents: Flow<MessageEvent>


    @OptIn(ExperimentalUuidApi::class)
    suspend fun sendMessage(cmd: SendMessageCommand): MbResult<Unit>
}