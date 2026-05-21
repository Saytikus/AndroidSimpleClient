package ru.saytikus.androidsimpleclient.presentation.chat.chat.components.preview

import ru.saytikus.androidsimpleclient.domain.core.message.model.Message
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
val previewOwnerChatUuid: Uuid = Uuid.parse("00000000-0000-0000-0000-000000000001")

@OptIn(ExperimentalUuidApi::class)
val previewOtherChatUuid: Uuid = Uuid.parse("00000000-0000-0000-0000-000000000002")

@OptIn(ExperimentalUuidApi::class)
val previewMessageList: List<Message> = listOf(
    Message(
        id = Uuid.parse("00000000-0000-0000-0000-000000000010"),
        senderId = previewOtherChatUuid,
        senderName = "Alice",
        text = "Hey! Did you push the fix?",
        createdAt = "2026-05-16T12:40:00.000Z",
        isRead = true
    ),
    Message(
        id = Uuid.parse("00000000-0000-0000-0000-000000000011"),
        senderId = previewOwnerChatUuid,
        senderName = null,
        text = "Yeah, just merged it",
        createdAt = "2026-05-16T12:41:00.000Z",
        isRead = true
    ),
    Message(
        id = Uuid.parse("00000000-0000-0000-0000-000000000012"),
        senderId = previewOtherChatUuid,
        senderName = "Alice",
        text = "Amazing, I'll test it now",
        createdAt = "2026-05-16T12:41:30.000Z",
        isRead = true
    ),
    Message(
        id = Uuid.parse("00000000-0000-0000-0000-000000000013"),
        senderId = previewOwnerChatUuid,
        senderName = null,
        text = "Let me know if anything breaks",
        createdAt = "2026-05-16T12:43:00.000Z",
        isRead = false
    )
)
