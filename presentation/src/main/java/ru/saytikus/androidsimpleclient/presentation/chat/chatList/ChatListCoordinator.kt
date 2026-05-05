package ru.saytikus.androidsimpleclient.presentation.chat.chatList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.androidx.compose.koinViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ChatListCoordinator(

    private val onNavigate: (ChatListNavigation) -> Unit,

    val viewModel: ChatListViewModel
) {

    val screenStateFlow = viewModel.stateFlow
    fun handle(action: ChatListAction) {
        when (action) {

            is ChatListAction.OnChatClick -> TODO()

            ChatListAction.OnChatsRefresh -> viewModel.onChatRefresh()

            is ChatListAction.OnSearchQueryChange -> TODO()

            ChatListAction.OnSettingsButtonClick -> onNavigate(
                ChatListNavigation.Settings
            )

            ChatListAction.OnAddChatButtonClick -> TODO()
        }
    }


}

@Composable
fun rememberChatListCoordinator(
    onNavigate: (ChatListNavigation) -> Unit,
    viewModel: ChatListViewModel = koinViewModel()
): ChatListCoordinator {
    return remember(viewModel) {
        ChatListCoordinator(
            onNavigate,
            viewModel = viewModel
        )
    }
}