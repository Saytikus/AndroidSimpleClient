package ru.saytikus.androidsimpleclient.presentation.registration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.androidx.compose.koinViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class RegistrationCoordinator(
    val viewModel: RegistrationViewModel,
    private val DEBUG_onSettingsNavigate: (RegistrationNavigation) -> Unit
) {
    val screenStateFlow = viewModel.stateFlow
    fun handle(action: RegistrationAction) {
        when (action) {
            is RegistrationAction.OnDisplayNameChange -> viewModel.onDisplayNameChanged(action.newValue)

            is RegistrationAction.OnEmailChange -> viewModel.onEmailChanged(action.newValue)
            
            is RegistrationAction.OnPasswordChange -> viewModel.onPasswordChanged(action.newValue)

            is RegistrationAction.OnUsernameChange -> viewModel.onUsernameChanged(action.newValue)

            is RegistrationAction.OnRegistrationSubmit -> viewModel.onRegisterProfileButtonClicked()

            is RegistrationAction.OnSignInClick -> TODO()

            is RegistrationAction.DEBUG_onSettingsButtonClick -> DEBUG_onSettingsNavigate(
                RegistrationNavigation.Settings
            )
        }
    }


}

@Composable
fun rememberRegistrationCoordinator(
    viewModel: RegistrationViewModel= koinViewModel(),
    DEBUG_onSettingsNavigate: (RegistrationNavigation) -> Unit
): RegistrationCoordinator {
    return remember(viewModel) {
        RegistrationCoordinator(
            viewModel = viewModel,
            DEBUG_onSettingsNavigate
        )
    }
}