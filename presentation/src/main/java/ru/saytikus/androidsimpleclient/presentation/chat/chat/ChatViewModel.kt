package ru.saytikus.androidsimpleclient.presentation.chat.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Named
import ru.saytikus.androidsimpleclient.domain.chat.dto.GetChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.dto.JoinChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.dto.LeaveChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.model.Chat
import ru.saytikus.androidsimpleclient.domain.chat.model.ChatEvent
import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.features.message.model.GetMessagesWithCursorCommand
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IObserveInputBoundary
import ru.saytikus.androidsimpleclient.domain.core.features.message.model.MessageEvent
import ru.saytikus.androidsimpleclient.domain.core.features.message.model.MessagesWithCursor
import ru.saytikus.androidsimpleclient.domain.core.features.message.model.SendMessageCommand
import ru.saytikus.androidsimpleclient.domain.core.features.settings.Settings
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@KoinViewModel
class ChatViewModel(

    savedStateHandle: SavedStateHandle,

    @Named("JoinChatUseCase")
    private val joinChatCase:
    IInputBoundary<MbResult<Unit>, JoinChatCommand>,

    @Named("LeaveChatUseCase")
    private val leaveChatCase:
    IInputBoundary<MbResult<Unit>, LeaveChatCommand>,

    @Named("SendMessageUseCase")
    private val sendMessageCase:
    IInputBoundary<MbResult<Unit>, SendMessageCommand>,

    @Named("ObserveMessageEventsUseCase")
    private val observeMessageEvents:
    IObserveInputBoundary<Flow<MessageEvent>>,

    @Named("GetChatUseCase")
    private val getChatCase:
    IInputBoundary<MbResult<Chat>, GetChatCommand>,

    @Named("GetSettingsUseCase")
    private val getSettingsCase:
    IInputBoundary<MbResult<Settings>, Unit>,

    @Named("GetMessagesWithCursorUseCase")
    private val getMessagesWithCursorCase:
    IInputBoundary<MbResult<MessagesWithCursor>, GetMessagesWithCursorCommand>,

    @Named("ObserveChatEventsUseCase")
    private val observeChatEventsCase:
    IObserveInputBoundary<Flow<ChatEvent>>


) : ViewModel() {

    @OptIn(ExperimentalUuidApi::class)
    private val _stateFlow: MutableStateFlow<ChatState> = MutableStateFlow(ChatState())

    val stateFlow: StateFlow<ChatState> = _stateFlow.asStateFlow()


    private val chatId: Uuid = Uuid.parse(
        checkNotNull(savedStateHandle["chatId"])
    )

    private lateinit var activeUserId: Uuid

    private lateinit var nextCursor: String

    private var hasMore: Boolean = false

    private companion object {
        const val PAGE_SIZE = 100
    }


    init {

        viewModelScope.launch {
            // TODO parse answer
            when(val activeUserIdResult = getSettingsCase(Unit)) {
                is MbResult.Failure -> {
                    // TODO
                }
                is MbResult.Success -> {
                    val userId = activeUserIdResult.response.activeUserId
                    if(userId == null) {
                        //TODO
                        return@launch
                    }

                    activeUserId = Uuid.parse(userId)
                }
            }

            joinChatCase(JoinChatCommand(chatId))

            when(val chat = getChatCase(GetChatCommand(chatId))) {
                is MbResult.Failure -> {
                    // TODO
                }

                is MbResult.Success -> {


                    _stateFlow.update {
                        it.copy(
                            chatName = if(chat.response.title == "NO_TITLE") chat.response.participants[1].displayName else chat.response.title,
                            ownerProfileId = activeUserId
                        ) // TODO fix magic string parse
                    }
                }
            }

            val getMessagesWithCursorResult = getMessagesWithCursorCase(
                GetMessagesWithCursorCommand(
                    chatId = chatId,
                    limit = PAGE_SIZE
                )
            )

            when(getMessagesWithCursorResult) {
                is MbResult.Failure -> {
                    // TODO
                    return@launch
                }

                is MbResult.Success -> {
                    nextCursor = getMessagesWithCursorResult.response.nextCursor
                    hasMore = getMessagesWithCursorResult.response.hasMore
                    _stateFlow.update {
                        it.copy(
                            messages = getMessagesWithCursorResult.response.items,
                            isLoadingMore = false
                        )
                    }
                }
            }
        }

        observeMessageEvents()
            .onEach { event ->
                when(event) {

                    is MessageEvent.MessageCreated -> {

                        _stateFlow.update {
                            it.copy(
                                messages = it.messages + event.message)
                        }
                    }

                }

            }
            .launchIn(viewModelScope)

        observeChatEventsCase()
            .onEach { event ->
                when(event) {

                    is ChatEvent.ChatCreatedEvent -> {
                        // TODO user notification
                    }

                    is ChatEvent.ChatListUpdatedEvent -> { /* NO-OP */ }

                    is ChatEvent.TypingChangedEvent -> {
                        if(event.chatId == chatId && event.userId != activeUserId) {
                            _stateFlow.update {
                                it.copy(isTyping = event.isTyping)
                            }
                        }
                    }

                    is ChatEvent.UserOnlineChangedEvent -> {
                        if(event.chatId == chatId) {
                            _stateFlow.update {
                                it.copy(isOnline = event.isOnline)
                            }
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun onSendButtonClick() {
        viewModelScope.launch {
            val sendMessageResult = sendMessageCase(
                SendMessageCommand(
                    chatId,
                    stateFlow.value.inputText
                )
            )

            when(sendMessageResult) {
                is MbResult.Failure -> {
                    // TODO user notification
                    return@launch
                }

                is MbResult.Success -> _stateFlow.update {
                    it.copy(inputText = "")
                }
            }
        }
    }

    fun onInputChange(newValue: String) {
        // TODO validator
        _stateFlow.update {
            it.copy(inputText = newValue)
        }
    }

    fun loadMoreMessages() {
        if (!hasMore || _stateFlow.value.isLoadingMore) return
        viewModelScope.launch {
            _stateFlow.update { it.copy(isLoadingMore = true) }
            when (val result = getMessagesWithCursorCase(
                GetMessagesWithCursorCommand(
                    chatId = chatId,
                    cursor = nextCursor,
                    limit = PAGE_SIZE
                )
            )) {
                is MbResult.Failure -> {
                    _stateFlow.update { it.copy(isLoadingMore = false) }
                }
                is MbResult.Success -> {
                    nextCursor = result.response.nextCursor
                    hasMore = result.response.hasMore
                    _stateFlow.update {
                        it.copy(
                            messages = result.response.items + it.messages,
                            isLoadingMore = false
                        )
                    }
                }
            }
        }
    }

    fun onDispose() {
        viewModelScope.launch {
            // TODO parse answer
            leaveChatCase(LeaveChatCommand(chatId))
        }
    }
}