package ru.saytikus.androidsimpleclient.presentation.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue


import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun SettingsRoute(
    coordinator: SettingsCoordinator = rememberSettingsCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(SettingsState())

    // UI Actions
    val actionsHandler: (SettingsAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    // UI Rendering
    SettingsScreen(
        state = uiState,
        onAction = actionsHandler
    )
}


