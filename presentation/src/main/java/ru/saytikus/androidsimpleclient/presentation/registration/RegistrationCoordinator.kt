package ru.saytikus.androidsimpleclient.presentation.registration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.androidx.compose.koinViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class RegistrationCoordinator(
    val viewModel: RegistrationViewModel
) {
    val screenStateFlow = viewModel.stateFlow
    fun handle(action: RegistrationAction) {
        when (action) {
            RegistrationAction.OnClick -> { /* Handle action */
            }
        }
    }


}

@Composable
fun rememberRegistrationCoordinator(
    viewModel: RegistrationViewModel= koinViewModel()
): RegistrationCoordinator {
    return remember(viewModel) {
        RegistrationCoordinator(
            viewModel = viewModel
        )
    }
}