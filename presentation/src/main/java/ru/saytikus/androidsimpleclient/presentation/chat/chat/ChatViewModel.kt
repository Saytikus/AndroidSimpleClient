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
import ru.saytikus.androidsimpleclient.domain.chat.dto.JoinChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.dto.LeaveChatCommand
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IObserveInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.message.model.MessageEvent
import ru.saytikus.androidsimpleclient.domain.common.message.model.SendMessageCommand
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
    IObserveInputBoundary<Flow<MessageEvent>>

) : ViewModel() {

    @OptIn(ExperimentalUuidApi::class)
    private val _stateFlow: MutableStateFlow<ChatState> = MutableStateFlow(ChatState())

    val stateFlow: StateFlow<ChatState> = _stateFlow.asStateFlow()


    private val chatId: Uuid = Uuid.parse(
        checkNotNull(savedStateHandle["chatId"])
    )


    init {

        viewModelScope.launch {
            joinChatCase(JoinChatCommand(chatId))
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
    }

    fun onSendButtonClick() {
        viewModelScope.launch {
            sendMessageCase(
                SendMessageCommand(
                    chatId,
                    stateFlow.value.inputText
                )
            )
        }
    }

    fun onInputChange(newValue: String) {
        // TODO validator
        _stateFlow.update {
            it.copy(inputText = newValue)
        }
    }

    fun onDispose() {
        viewModelScope.launch {
            leaveChatCase(LeaveChatCommand(chatId))
        }
    }
}