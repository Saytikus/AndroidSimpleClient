package ru.saytikus.androidsimpleclient.presentation.chatList

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatListViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ChatListState> = MutableStateFlow(ChatListState())

    val stateFlow: StateFlow<ChatListState> = _stateFlow.asStateFlow()


}