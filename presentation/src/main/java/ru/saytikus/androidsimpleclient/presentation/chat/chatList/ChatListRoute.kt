package ru.saytikus.androidsimpleclient.presentation.chat.chatList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue


import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlin.uuid.ExperimentalUuidApi


@OptIn(ExperimentalUuidApi::class)
@Composable
fun ChatListRoute(
    onNavigate: (ChatListNavigation) -> Unit,
    coordinator: ChatListCoordinator = rememberChatListCoordinator(onNavigate)
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


