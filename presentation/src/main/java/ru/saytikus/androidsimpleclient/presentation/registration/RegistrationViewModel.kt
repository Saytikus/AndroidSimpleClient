package ru.saytikus.androidsimpleclient.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Named
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.dto.ValidateResult
import ru.saytikus.androidsimpleclient.domain.common.profile.Profile
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IValidator
import ru.saytikus.androidsimpleclient.domain.registration.answers.A1RegisterProfileAnswer
import ru.saytikus.androidsimpleclient.domain.registration.commands.C1RegisterProfileCommand
import kotlin.uuid.ExperimentalUuidApi

@KoinViewModel
class RegistrationViewModel(

    @Named("RegisterProfileUseCase")
    private val registerProfileCase:
    IInputBoundary<MbResult<A1RegisterProfileAnswer>, C1RegisterProfileCommand>,

    @Named("SaveProfileUseCase")
    private val saveProfileCase:
    IInputBoundary<MbResult<Unit>, Profile>,

    @Named("UsernameValidator")
    private val usernameValidator: IValidator<String>,

    @Named("EmailValidator")
    private val emailValidator: IValidator<String>,

    @Named("PasswordValidator")
    private val passwordValidator: IValidator<String>,

    @Named("DisplayNameValidator")
    private val displayNameValidator: IValidator<String>


) : ViewModel() {


    private val _stateFlow: MutableStateFlow<RegistrationState> =
        MutableStateFlow(RegistrationState())

    val stateFlow: StateFlow<RegistrationState> = _stateFlow.asStateFlow()




    fun onUsernameChanged(newValue: String) {
        when(val validateResult = usernameValidator.validate(newValue)) {
            is ValidateResult.Error -> _stateFlow.update {
                it.copy(
                    username = newValue,
                    usernameError = validateResult.error.reasonMessage,
                )
            }

            ValidateResult.Success -> _stateFlow.update {
                it.copy(
                    username = newValue,
                    usernameError = null
                )
            }
        }
    }

    fun onEmailChanged(newValue: String) {
        when(val validateResult = emailValidator.validate(newValue)) {
            is ValidateResult.Error -> _stateFlow.update {
                it.copy(
                    email = newValue,
                    emailError = validateResult.error.reasonMessage,
                )
            }

            ValidateResult.Success -> _stateFlow.update {
                it.copy(
                    email = newValue,
                    emailError = null
                )
            }
        }
    }

    fun onPasswordChanged(newValue: String) {
        when(val validateResult = passwordValidator.validate(newValue)) {
            is ValidateResult.Error -> _stateFlow.update {
                it.copy(
                    password = newValue,
                    passwordError = validateResult.error.reasonMessage,
                )
            }

            ValidateResult.Success -> _stateFlow.update {
                it.copy(
                    password = newValue,
                    passwordError = null
                )
            }
        }
    }

    fun onDisplayNameChanged(newValue: String) {
        when(val validateResult = displayNameValidator.validate(newValue)) {
            is ValidateResult.Error -> _stateFlow.update {
                it.copy(
                    displayName = newValue,
                    displayNameError = validateResult.error.reasonMessage,
                )
            }

            ValidateResult.Success -> _stateFlow.update {
                it.copy(
                    displayName = newValue,
                    displayNameError = null
                )
            }
        }
    }

    fun onRegisterProfileButtonClicked() {
        registerProfile(stateFlow.value)
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun registerProfile(state: RegistrationState) {

        viewModelScope.launch {

            var registerAnswer: A1RegisterProfileAnswer
            val registerResult = registerProfileCase(C1RegisterProfileCommand(
                state.username,
                state.email,
                state.password,
                state.displayName
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