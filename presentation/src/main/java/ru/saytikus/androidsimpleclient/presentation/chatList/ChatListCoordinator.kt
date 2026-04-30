package ru.saytikus.androidsimpleclient.presentation.chatList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.androidx.compose.koinViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ChatListCoordinator(
    val viewModel: ChatListViewModel
) {
    val screenStateFlow = viewModel.stateFlow
    fun handle(action: ChatListAction) {
        when (action) {
            ChatListAction.OnClick -> { /* Handle action */
            }
        }
    }


}

@Composable
fun rememberChatListCoordinator(
    viewModel: ChatListViewModel = koinViewModel()
): ChatListCoordinator {
    return remember(viewModel) {
        ChatListCoordinator(
            viewModel = viewModel
        )
    }
}