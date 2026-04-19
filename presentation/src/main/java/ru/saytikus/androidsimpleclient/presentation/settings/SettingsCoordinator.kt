package ru.saytikus.androidsimpleclient.presentation.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.androidx.compose.koinViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class SettingsCoordinator(
    val viewModel: SettingsViewModel
) {
    val screenStateFlow = viewModel.stateFlow
    fun handle(action: SettingsAction) {
        when (action) {
            is SettingsAction.OnHostAddressChanged -> {
                viewModel.onHostAddressChanged(action.newValue)
            }

            is SettingsAction.OnHostAddressSaveButtonClicked -> {
                viewModel.onHostAddressSaveButtonClicked(action.newValue)
            }
        }
    }


}

@Composable
fun rememberSettingsCoordinator(
    viewModel: SettingsViewModel = koinViewModel()
): SettingsCoordinator {
    return remember(viewModel) {
        SettingsCoordinator(
            viewModel = viewModel
        )
    }
}