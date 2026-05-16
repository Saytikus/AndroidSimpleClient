package ru.saytikus.androidsimpleclient.presentation.chat.chatList

import kotlinx.serialization.Serializable
import ru.saytikus.androidsimpleclient.domain.chat.model.ChatListItem
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
data class ChatListState @OptIn(ExperimentalUuidApi::class) constructor(
    val chats: List<ChatListItem> = emptyList(),

    val ownerProfileId: Uuid = Uuid.NIL,

    val searchQuery: String = "",

    val isRefreshing: Boolean = false,

    val refreshError: Boolean = false
)

sealed interface ChatListNavigation {

    data object Settings : ChatListNavigation

    data object AddChat : ChatListNavigation

    data class Chat @OptIn(ExperimentalUuidApi::class) constructor(
        val chatId: Uuid
    ) : ChatListNavigation
}

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

    data object OnSettingsButtonClick : ChatListAction

    data object OnAddChatButtonClick : ChatListAction
}

