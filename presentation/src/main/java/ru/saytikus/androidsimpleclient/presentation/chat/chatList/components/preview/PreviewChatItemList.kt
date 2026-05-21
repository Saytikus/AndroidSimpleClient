package ru.saytikus.androidsimpleclient.presentation.chat.chatList.components.preview

import ru.saytikus.androidsimpleclient.domain.chat.model.ChatListItem
import ru.saytikus.androidsimpleclient.domain.core.message.model.Message
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
val previewOwnerUuid = Uuid.generateV4()

@OptIn(ExperimentalUuidApi::class)
val previewChatList: List<ChatListItem> =
    listOf(
        ChatListItem(
            Uuid.generateV4(),
            "type1",
            null,
            "Corn",
            null,
            0,
            "2026-04-30T18:00:18.023Z"
        ),

        ChatListItem(
            Uuid.generateV4(),
            "type2",
            "JeJo JoJe",
            null,
            null,
            0,
            "2026-02-30T14:00:18.023Z"
        ),

        ChatListItem(
            Uuid.generateV4(),
            "type2",
            "Vov Viv Vav",
            null,
            Message(
                Uuid.generateV4(),
                Uuid.generateV4(),
                "Popa",
                "Abracadabra",
                "2026-02-30T14:00:18.023Z",
                true
            ),
            0,
            "2026-02-30T14:00:18.023Z"
        ),

        ChatListItem(
            Uuid.generateV4(),
            "type1",
            null,
            "Cucumber",
            Message(
                Uuid.generateV4(),
                Uuid.generateV4(),
                "Cucumber",
                "Abracadabra",
                "2026-02-30T14:00:18.023Z",
                false
            ),
            1,
            "2026-02-30T14:00:18.023Z"
        ),

        ChatListItem(
            Uuid.generateV4(),
            "type1",
            null,
            "Boba",
            Message(
                Uuid.generateV4(),
                previewOwnerUuid,
                "Popa",
                "Abracadabra",
                "2026-02-30T14:00:18.023Z",
                true
            ),
            0,
            "2026-02-30T14:00:18.023Z"
        )
    )