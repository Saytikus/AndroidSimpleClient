package ru.saytikus.androidsimpleclient.presentation.chatList

import kotlinx.serialization.Serializable
import ru.saytikus.androidsimpleclient.domain.chatList.ChatListItem
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


/**
 * Object used for a type safe destination to a ChatList route
 */
@Serializable
object ChatListDestination

/**
 * UI State that represents ChatListScreen
 **/
data class ChatListState(
    val chats: List<ChatListItem>,

    val searchQuery: String = "",

    val isRefreshing: Boolean = false
)

/**
 * ChatList Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface ChatListAction {

    data class OnChatClick
    @OptIn(ExperimentalUuidApi::class)
    constructor(val chatId: Uuid) : ChatListAction

    data class OnSearchQueryChange(val searchQuery: String) : ChatListAction

    data object OnChatsRefresh : ChatListAction
}

