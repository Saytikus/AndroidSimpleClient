package ru.saytikus.androidsimpleclient.presentation.registration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun RegistrationRoute(
    onNavigate: (RegistrationNavigation) -> Unit,
    coordinator: RegistrationCoordinator = rememberRegistrationCoordinator(
        onNavigate = onNavigate
    )
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


