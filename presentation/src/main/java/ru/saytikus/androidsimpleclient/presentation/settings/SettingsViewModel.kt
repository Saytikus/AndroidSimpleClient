package ru.saytikus.androidsimpleclient.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Named
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IObserveInputBoundary
import ru.saytikus.androidsimpleclient.domain.settings.Settings
import ru.saytikus.androidsimpleclient.domain.settings.dto.SaveResponseServerHostAddressCommand

@KoinViewModel
class SettingsViewModel(

    @Named("SaveResponseServerHostAddressUseCase")
    private val saveHostAddressCase:
    IInputBoundary<MbResult<Unit>, SaveResponseServerHostAddressCommand>,

    @Named("ObserveSettingsUseCase")
    private val observeSettingsCase:
    IObserveInputBoundary<Flow<Settings>>

) : ViewModel() {

    private val _stateFlow: MutableStateFlow<SettingsState> = MutableStateFlow(SettingsState())

    val stateFlow: StateFlow<SettingsState> = _stateFlow.asStateFlow()


    init {
        observeSettingsCase()
            .distinctUntilChanged()
            .onEach { settings ->
                _stateFlow.update {
                    it.copy(settings = settings)
                }
            }
            .launchIn(viewModelScope)

    }

    fun onHostAddressChanged(newValue: String) {
        if(isPartialIPv4(newValue)) {
            _stateFlow.update { it.copy(newHostAddress = newValue) }
        }
    }

    fun onHostAddressSaveButtonClicked(newValue: String) {
        viewModelScope.launch {
            saveHostAddressCase(SaveResponseServerHostAddressCommand(newValue))
        }
    }

    private fun isPartialIPv4(input: String): Boolean {
        if (input.isEmpty()) return true

        val regex = Regex("^\\d{0,3}(\\.\\d{0,3}){0,3}$")
        if (!regex.matches(input)) return false

        val parts = input.split(".")
        return parts.all {
            it.isEmpty() || (it.toIntOrNull()?.let { num -> num in 0..255 } ?: false)
        }
    }
}