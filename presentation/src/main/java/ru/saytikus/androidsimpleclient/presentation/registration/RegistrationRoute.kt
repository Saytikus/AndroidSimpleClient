package ru.saytikus.androidsimpleclient.presentation.registration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun RegistrationRoute(
    DEBUG_onSettingsNavigate: (RegistrationNavigation) -> Unit,
    coordinator: RegistrationCoordinator = rememberRegistrationCoordinator(DEBUG_onSettingsNavigate = DEBUG_onSettingsNavigate)
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(RegistrationState())

    // UI Actions
    val actionsHandler: (RegistrationAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    // UI Rendering
    RegistrationScreen(
        state = uiState,
        onAction = actionsHandler
    )
}


