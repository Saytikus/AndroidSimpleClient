package ru.saytikus.androidsimpleclient.presentation.chat.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.annotation.KoinViewModel
import kotlin.uuid.ExperimentalUuidApi

@KoinViewModel
class ChatViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    @OptIn(ExperimentalUuidApi::class)
    private val _stateFlow: MutableStateFlow<ChatState> = MutableStateFlow(ChatState())

    val stateFlow: StateFlow<ChatState> = _stateFlow.asStateFlow()


}