package ru.saytikus.androidsimpleclient.presentation.authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.androidx.compose.koinViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class AuthenticationCoordinator(
    val viewModel: AuthenticationViewModel
) {
    val screenStateFlow = viewModel.stateFlow
    fun handle(action: AuthenticationAction) {
        when (action) {

            AuthenticationAction.OnSignInButtonClick -> viewModel.onSignInButtonClicked()

            is AuthenticationAction.OnUsernameOrEmailChange -> viewModel.onUsernameOrEmailChange(action.newValue)

            is AuthenticationAction.onPasswordChange -> viewModel.onPasswordChange(action.newValue)
        }
    }


}

@Composable
fun rememberAuthenticationCoordinator(
    viewModel: AuthenticationViewModel = koinViewModel()
): AuthenticationCoordinator {
    return remember(viewModel) {
        AuthenticationCoordinator(
            viewModel = viewModel
        )
    }
}