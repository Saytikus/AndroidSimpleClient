package ru.saytikus.androidsimpleclient.presentation.authentication

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Named
import ru.saytikus.androidsimpleclient.domain.authentication.answers.A2SignInProfileAnswer
import ru.saytikus.androidsimpleclient.domain.authentication.commands.C2SignInProfileCommand
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary

class AuthenticationViewModel(

    @Named("SignInProfileUseCase")
    private val signInProfileCase:
    IInputBoundary<MbResult<A2SignInProfileAnswer>, C2SignInProfileCommand>

) : ViewModel() {

    private val _stateFlow: MutableStateFlow<AuthenticationState> =
        MutableStateFlow(AuthenticationState())

    val stateFlow: StateFlow<AuthenticationState> = _stateFlow.asStateFlow()


    fun onUsernameOrEmailChange(newValue: String) {
        viewModelScope.launch {
            _stateFlow.update {
                it.copy(usernameOrEmail = newValue)
            }
        }
    }

    fun onPasswordChange(newValue: String) {
        viewModelScope.launch {
            _stateFlow.update {
                it.copy(password = newValue)
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
                        it.copy(authenticationError = result.error.error.toString()) // TODO fix error get
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