package ru.saytikus.androidsimpleclient.presentation.authentication

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthenticationViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<AuthenticationState> =
        MutableStateFlow(AuthenticationState())

    val stateFlow: StateFlow<AuthenticationState> = _stateFlow.asStateFlow()


}