package ru.saytikus.androidsimpleclient.presentation.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember


import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun ChatRoute(
    coordinator: ChatCoordinator = rememberChatCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(ChatState())

    // UI Actions
    val actionsHandler: (ChatAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    // UI Rendering
    ChatScreen(
        state = uiState,
        onAction = actionsHandler
    )
}


