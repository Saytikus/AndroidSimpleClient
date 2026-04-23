package ru.saytikus.androidsimpleclient.presentation.registration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember


import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun RegistrationRoute(
    coordinator: RegistrationCoordinator = rememberRegistrationCoordinator()
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


