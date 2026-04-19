package ru.saytikus.androidsimpleclient.presentation.settings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SettingsViewModel() : ViewModel() {

    private val _stateFlow: MutableStateFlow<SettingsState> = MutableStateFlow(SettingsState())

    val stateFlow: StateFlow<SettingsState> = _stateFlow.asStateFlow()


}