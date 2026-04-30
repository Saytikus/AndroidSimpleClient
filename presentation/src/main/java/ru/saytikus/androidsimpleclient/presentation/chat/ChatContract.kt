package ru.saytikus.androidsimpleclient.presentation.chat

import kotlinx.serialization.Serializable


/**
 * Object used for a type safe destination to a Chat route
 */
@Serializable
object ChatDestination

/**
 * UI State that represents ChatScreen
 **/
class ChatState

/**
 * Chat Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface ChatAction {
    data object OnClick : ChatAction
}

