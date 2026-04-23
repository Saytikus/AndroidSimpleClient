package ru.saytikus.androidsimpleclient.presentation.registration

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.annotation.KoinViewModel
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.registration.answers.A1RegisterProfileAnswer
import ru.saytikus.androidsimpleclient.domain.registration.commands.C1RegisterProfileCommand

@KoinViewModel
class RegistrationViewModel(

    private val registerUserCase:
    IInputBoundary<MbResult<A1RegisterProfileAnswer>, C1RegisterProfileCommand>

) : ViewModel() {


    private val _stateFlow: MutableStateFlow<RegistrationState> =
        MutableStateFlow(RegistrationState())

    val stateFlow: StateFlow<RegistrationState> = _stateFlow.asStateFlow()
}