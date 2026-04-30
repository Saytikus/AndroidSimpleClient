package ru.saytikus.androidsimpleclient.presentation.chatList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue


import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun ChatListRoute(
    coordinator: ChatListCoordinator = rememberChatListCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(ChatListState())

    // UI Actions
    val actionsHandler: (ChatListAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    // UI Rendering
    ChatListScreen(
        state = uiState,
        onAction = actionsHandler
    )
}


