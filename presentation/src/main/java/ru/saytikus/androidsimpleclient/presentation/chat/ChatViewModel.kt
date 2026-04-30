package ru.saytikus.androidsimpleclient.presentation.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ChatState> = MutableStateFlow(ChatState())

    val stateFlow: StateFlow<ChatState> = _stateFlow.asStateFlow()


}