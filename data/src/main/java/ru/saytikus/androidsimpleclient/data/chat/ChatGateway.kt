package ru.saytikus.androidsimpleclient.data.chat

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.serialization.builtins.serializer
import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.chat.constants.ChatHubEvents
import ru.saytikus.androidsimpleclient.data.chat.constants.ChatHubMethods
import ru.saytikus.androidsimpleclient.data.chat.dto.ChatCreatedEventBodyDto
import ru.saytikus.androidsimpleclient.data.chat.souce.remote.IChatService
import ru.saytikus.androidsimpleclient.data.chat.souce.remote.toDomain
import ru.saytikus.androidsimpleclient.data.core.handleHubResult
import ru.saytikus.androidsimpleclient.data.core.handleRetrofitServiceResult
import ru.saytikus.androidsimpleclient.data.core.features.message.dto.MessageDto
import ru.saytikus.androidsimpleclient.data.core.features.message.source.remote.toDomain
import ru.saytikus.androidsimpleclient.data.core.source.remote.retrofit.interfaces.IRetrofitProvider
import ru.saytikus.androidsimpleclient.data.core.source.remote.signalR.IHubProvider
import ru.saytikus.androidsimpleclient.data.core.source.remote.signalR.sendAwait
import ru.saytikus.androidsimpleclient.domain.chat.IChatGateway
import ru.saytikus.androidsimpleclient.domain.chat.dto.CreatePrivateChatAnswer
import ru.saytikus.androidsimpleclient.domain.chat.dto.CreatePrivateChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.dto.GetChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.dto.JoinChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.dto.LeaveChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.model.Chat
import ru.saytikus.androidsimpleclient.domain.chat.model.ChatEvent
import ru.saytikus.androidsimpleclient.domain.chat.model.ChatListItem
import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@OptIn(ExperimentalUuidApi::class)
@Single
class ChatGateway(

    private val _retrofitProvider: IRetrofitProvider,

    private val _hubProvider: IHubProvider

) : IChatGateway {

    private val _service: IChatService
        get() = _retrofitProvider.retrofit().create(IChatService::class.java)


    private val _chatEvents = MutableSharedFlow<ChatEvent>(
        replay = 0,
        extraBufferCapacity = 10
    )

    override val chatEvents = _chatEvents.asSharedFlow()


    init {

        _hubProvider.subscribe(
            ChatHubEvents.CHAT_LIST_UPDATED,

            Uuid.serializer(),

            MessageDto.serializer()

        ) { chatId, messageDto ->
            println("ChatGateway received ${ChatHubEvents.CHAT_LIST_UPDATED}: $chatId, $messageDto")
            _chatEvents.emit(ChatEvent.ChatListUpdatedEvent(chatId, messageDto.toDomain()))
        }

        _hubProvider.subscribe(
            ChatHubEvents.CHAT_CREATED,

            Uuid.serializer(),

            ChatCreatedEventBodyDto.serializer()

        ) { chatId, bodyDto ->
            println("ChatGateway received ${ChatHubEvents.CHAT_CREATED}: $chatId, $bodyDto")
            _chatEvents.emit(ChatEvent.ChatCreatedEvent(chatId, bodyDto.toDomain()))
        }

        _hubProvider.subscribe(
            ChatHubEvents.USER_ONLINE_CHANGED,

            Uuid.serializer(),

            Boolean.serializer()

        ) { userId, isOnline ->
            println("ChatGateway received ${ChatHubEvents.USER_ONLINE_CHANGED}: $userId, $isOnline")
            _chatEvents.emit(ChatEvent.UserOnlineChangedEvent(userId, isOnline))
        }

        _hubProvider.subscribe(
            ChatHubEvents.TYPING_CHANGED,

            Uuid.serializer(),

            Uuid.serializer(),

            Boolean.serializer()

        ) { chatId, userId, isTyping ->
            println("ChatGateway received ${ChatHubEvents.TYPING_CHANGED}: $chatId, $userId, $isTyping")
            _chatEvents.emit(ChatEvent.TypingChangedEvent(chatId, userId, isTyping))
        }

    }



    override suspend fun getProfileChats(): MbResult<List<ChatListItem>> {
        println("Gateway call ChatListGateway::getProfileChats")

        val result = runCatching { _service.getProfileChats() }

        val answer = handleRetrofitServiceResult(result)

        return if (answer is MbResult.Success) MbResult.Success(answer.response.map { it.toDomain() })
        else answer as MbResult.Failure
    }

    @OptIn(ExperimentalUuidApi::class)
    override suspend fun createPrivateChat(cmd: CreatePrivateChatCommand): MbResult<CreatePrivateChatAnswer> {
        println("Gateway call ChatListGateway::createPrivateChat")

        val result = runCatching { _service.createPrivateChat(cmd.selectedUserId) }

        val answer = handleRetrofitServiceResult(result)

        return if (answer is MbResult.Success) MbResult.Success(answer.response.toDomain())
        else answer as MbResult.Failure
    }

    override suspend fun joinChat(cmd: JoinChatCommand): MbResult<Unit> {
        println("Gateway call ChatListGateway::joinChat")

        val result = runCatching {
            _hubProvider.sendAwait(
                method = ChatHubMethods.JOIN_CHAT,
                message = cmd.chatId.toString()
            )
        }

        val answer = handleHubResult(result)

        return answer
    }

    override suspend fun leaveChat(cmd: LeaveChatCommand): MbResult<Unit> {
        println("Gateway call ChatListGateway::leaveChat")

        val result = runCatching {
            _hubProvider.sendAwait(
                method = ChatHubMethods.LEAVE_CHAT,
                message = cmd.chatId.toString()
            )
        }

        val answer = handleHubResult(result)

        return answer
    }

    override suspend fun getChat(cmd: GetChatCommand): MbResult<Chat> {
        println("Gateway call ChatListGateway::getChat")

        val result = runCatching { _service.getChat(cmd.chatId) }

        val answer = handleRetrofitServiceResult(result)

        return if (answer is MbResult.Success) MbResult.Success(answer.response.toDomain())
        else answer as MbResult.Failure
    }
}