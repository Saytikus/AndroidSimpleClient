package ru.saytikus.androidsimpleclient.presentation.authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember


import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun AuthenticationRoute(
    coordinator: AuthenticationCoordinator = rememberAuthenticationCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(AuthenticationState())

    // UI Actions
    val actionsHandler: (AuthenticationAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    // UI Rendering
    AuthenticationScreen(
        state = uiState,
        onAction = actionsHandler
    )
}


