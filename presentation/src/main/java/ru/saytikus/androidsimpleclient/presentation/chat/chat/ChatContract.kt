package ru.saytikus.androidsimpleclient.presentation.chat.chat

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable
import ru.saytikus.androidsimpleclient.domain.core.features.message.model.Message
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


/**
 * Object used for a type safe destination to a Chat route
 */
@Serializable
data class ChatDestination(val chatId: String)

sealed interface ChatNavigation {
    data object Back : ChatNavigation
}

/**
 * UI State that represents ChatScreen
 **/
@OptIn(ExperimentalUuidApi::class)
data class ChatState(
    val chatName: String = "",

    val ownerProfileId: Uuid = Uuid.NIL,

    val isOnline: Boolean = false,

    val isTyping: Boolean = false,

    val messages: List<Message> = emptyList(),

    val inputText: String = "",

    val isLoadingMore: Boolean = false,

    val avatarIcon: ImageVector = Icons.Rounded.Person
)

/**
 * Chat Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
sealed interface ChatAction {
    data class OnInputChange(val text: String) : ChatAction
    data object OnSendClick : ChatAction
    data object OnBackClick : ChatAction
    data class OnFirstVisibleMessageChanged @OptIn(ExperimentalUuidApi::class) constructor(
        val messageId: Uuid
    ) : ChatAction
}
