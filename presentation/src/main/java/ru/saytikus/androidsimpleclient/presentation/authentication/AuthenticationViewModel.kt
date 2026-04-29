package ru.saytikus.androidsimpleclient.presentation.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Named
import ru.saytikus.androidsimpleclient.domain.authentication.answers.A2SignInProfileAnswer
import ru.saytikus.androidsimpleclient.domain.authentication.commands.C2SignInProfileCommand
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.dto.ValidateResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IValidator
import ru.saytikus.androidsimpleclient.domain.common.valueObject.DomainError

@KoinViewModel
class AuthenticationViewModel(

    @Named("SignInProfileUseCase")
    private val signInProfileCase:
    IInputBoundary<MbResult<A2SignInProfileAnswer>, C2SignInProfileCommand>,

    @Named("PasswordValidator")
    private val passwordValidator: IValidator<String>

) : ViewModel() {

    private val _stateFlow: MutableStateFlow<AuthenticationState> =
        MutableStateFlow(AuthenticationState())

    val stateFlow: StateFlow<AuthenticationState> = _stateFlow.asStateFlow()





    fun onUsernameOrEmailChange(newValue: String) {
        viewModelScope.launch {
            _stateFlow.update {
                it.copy(
                    usernameOrEmail = newValue,
                    authenticationError = null
                )
            }
        }
    }

    fun onPasswordChange(newValue: String) {
        when(val validateResult = passwordValidator.validate(newValue)) {
            is ValidateResult.Error -> _stateFlow.update {
                it.copy(
                    password = newValue,
                    passwordError = validateResult.error.reasonMessage,
                    authenticationError = null
                )
            }

            ValidateResult.Success -> _stateFlow.update {
                it.copy(
                    password = newValue,
                    passwordError = null,
                    authenticationError = null
                )
            }
        }
    }

    fun onSignInButtonClicked() {
        signInProfile(_stateFlow.value)
    }

    private fun signInProfile(state: AuthenticationState) {

        viewModelScope.launch {
            val result = signInProfileCase(
                C2SignInProfileCommand(
                    state.usernameOrEmail,
                    state.password
                )
            )

            when(result) {
                is MbResult.Failure -> {
                    _stateFlow.update {
                        val error = result.error.error as DomainError.GatewayError.RequestError
                        it.copy(authenticationError = error.message) // TODO fix error get
                    }
                }
                is MbResult.Success<*> -> {

                    // TODO save auth token

                    _stateFlow.update {
                        it.copy(isAuthenticationSuccessfully = true)
                    }
                }
            }
        }
    }
}