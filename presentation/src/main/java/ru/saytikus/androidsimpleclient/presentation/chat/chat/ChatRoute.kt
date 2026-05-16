package ru.saytikus.androidsimpleclient.presentation.chat.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlin.uuid.ExperimentalUuidApi


@OptIn(ExperimentalUuidApi::class)
@Composable
fun ChatRoute(
    onNavigate: (ChatNavigation) -> Unit,
    coordinator: ChatCoordinator = rememberChatCoordinator(onNavigate)
) {
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(ChatState())

    val actionsHandler: (ChatAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    ChatScreen(
        state = uiState,
        onAction = actionsHandler
    )
}
