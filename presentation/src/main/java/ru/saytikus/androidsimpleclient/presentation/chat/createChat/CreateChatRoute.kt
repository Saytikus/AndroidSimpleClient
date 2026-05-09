package ru.saytikus.androidsimpleclient.presentation.chat.createChat


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun CreateChatRoute(
    onNavigate: (CreateChatNavigation) -> Unit,
    coordinator: CreateChatCoordinator = rememberCreateChatCoordinator(onNavigate)
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(CreateChatState())

    // UI Actions
    val actionsHandler: (CreateChatAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    // UI Rendering
    CreateChatScreen(
        state = uiState,
        onAction = actionsHandler
    )
}


