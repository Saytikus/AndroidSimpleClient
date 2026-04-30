package ru.saytikus.androidsimpleclient.presentation.chatList

import kotlinx.serialization.Serializable


/**
 * Object used for a type safe destination to a ChatList route
 */
@Serializable
object ChatListDestination

/**
 * UI State that represents ChatListScreen
 **/
class ChatListState

/**
 * ChatList Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface ChatListAction {
    data object OnClick : ChatListAction
}

