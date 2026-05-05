package ru.saytikus.androidsimpleclient.presentation.chat.chatList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Named
import ru.saytikus.androidsimpleclient.domain.chat.entities.ChatListItem
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
@KoinViewModel
class ChatListViewModel(

    @Named("GetProfileChatsUseCase")
    private val getChatsCase:
    IInputBoundary<MbResult<List<ChatListItem>>, Unit>

) : ViewModel() {


    private val _stateFlow: MutableStateFlow<ChatListState> = MutableStateFlow(ChatListState())

    val stateFlow: StateFlow<ChatListState> = _stateFlow.asStateFlow()



    init {

        onChatRefresh()
    }

    fun onChatRefresh() {

        viewModelScope.launch {

            _stateFlow.update {
                it.copy(isRefreshing = true)
            }

            when (val getChatsResult = getChatsCase(Unit)) {

                is MbResult.Failure -> _stateFlow.update {
                    println(getChatsResult.error)
                    it.copy(
                        chats = emptyList(),
                        isRefreshing = false,
                        refreshError = true
                    )
                }

                is MbResult.Success -> _stateFlow.update {
                    it.copy(
                        chats = getChatsResult.response,
                        isRefreshing = false,
                        refreshError = false
                    )
                }
            }
        }
    }
}