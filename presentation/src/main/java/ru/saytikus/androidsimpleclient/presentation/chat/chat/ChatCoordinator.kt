package ru.saytikus.androidsimpleclient.presentation.chat.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.androidx.compose.koinViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ChatCoordinator(
    private val onNavigate: (ChatNavigation) -> Unit,
    val viewModel: ChatViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun handle(action: ChatAction) {
        when (action) {
            is ChatAction.OnInputChange -> { /* Handle */ }
            ChatAction.OnSendClick -> { /* Handle */ }
            ChatAction.OnBackClick -> onNavigate(ChatNavigation.Back)
            ChatAction.OnMenuClick -> { /* Handle */ }
            is ChatAction.OnFirstVisibleMessageChanged -> { /* Handle */ }
        }
    }
}

@Composable
fun rememberChatCoordinator(
    onNavigate: (ChatNavigation) -> Unit,
    viewModel: ChatViewModel = koinViewModel()
): ChatCoordinator {
    return remember(viewModel) {
        ChatCoordinator(
            onNavigate = onNavigate,
            viewModel = viewModel
        )
    }
}
