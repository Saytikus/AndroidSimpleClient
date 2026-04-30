package ru.saytikus.androidsimpleclient.presentation.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.androidx.compose.koinViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ChatCoordinator(
    val viewModel: ChatViewModel
) {
    val screenStateFlow = viewModel.stateFlow
    fun handle(action: ChatAction) {
        when (action) {
            ChatAction.OnClick -> { /* Handle action */
            }
        }
    }


}

@Composable
fun rememberChatCoordinator(
    viewModel: ChatViewModel = koinViewModel()
): ChatCoordinator {
    return remember(viewModel) {
        ChatCoordinator(
            viewModel = viewModel
        )
    }
}