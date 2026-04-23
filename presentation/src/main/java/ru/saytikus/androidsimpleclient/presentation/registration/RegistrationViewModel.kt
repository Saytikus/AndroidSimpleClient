package ru.saytikus.androidsimpleclient.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Named
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.entities.Profile
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.registration.answers.A1RegisterProfileAnswer
import ru.saytikus.androidsimpleclient.domain.registration.commands.C1RegisterProfileCommand
import ru.saytikus.androidsimpleclient.presentation.registration.model.ProfileData
import kotlin.uuid.ExperimentalUuidApi

@KoinViewModel
class RegistrationViewModel(

    @Named("RegisterProfileUseCase")
    private val registerProfileCase:
    IInputBoundary<MbResult<A1RegisterProfileAnswer>, C1RegisterProfileCommand>,

    @Named("SaveProfileUseCase")
    private val saveProfileCase:
    IInputBoundary<MbResult<Unit>, Profile>

) : ViewModel() {


    private val _stateFlow: MutableStateFlow<RegistrationState> =
        MutableStateFlow(RegistrationState())

    val stateFlow: StateFlow<RegistrationState> = _stateFlow.asStateFlow()

    @OptIn(ExperimentalUuidApi::class)
    fun onRegisterProfileButtonClicked(profileData: ProfileData) {
        // TODO validate data case

        viewModelScope.launch {

            var registerAnswer: A1RegisterProfileAnswer
            val registerResult = registerProfileCase(C1RegisterProfileCommand(
                profileData.username,
                profileData.email,
                profileData.password,
                profileData.displayName
            ))

            when(registerResult) {
                is MbResult.Failure -> {
                    // tODO user notification
                    return@launch
                }

                is MbResult.Success ->
                    registerAnswer = registerResult.response
            }

            val saveRegisteredProfileResult = saveProfileCase(
                Profile(
                    registerAnswer.userId,
                    registerAnswer.username,
                    registerAnswer.email,
                    registerAnswer.displayName
                )
            )

            when(saveRegisteredProfileResult) {
                is MbResult.Failure -> {
                    // tODO user notification
                    return@launch
                }

                is MbResult.Success -> {
                    // tODO user notification
                }
            }
        }

    }
}