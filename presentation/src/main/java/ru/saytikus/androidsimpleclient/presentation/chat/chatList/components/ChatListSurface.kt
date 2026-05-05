package ru.saytikus.androidsimpleclient.presentation.chat.chatList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.saytikus.androidsimpleclient.domain.chat.entities.ChatListItem
import ru.saytikus.androidsimpleclient.presentation.chat.chatList.components.preview.previewChatList
import ru.saytikus.androidsimpleclient.presentation.chat.chatList.components.preview.previewOwnerUuid
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors
import kotlin.collections.lastIndex
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Composable
fun ChatListSurface(
    chats: List<ChatListItem>,
    ownerProfileId: Uuid,
    listState: LazyListState,
    onChatClick: (Uuid) -> Unit,
    colors: AppColors
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        chats.forEachIndexed { index, chat ->

            ChatListComponentItem(
                chat = chat,
                ownerProfileId = ownerProfileId,
                onClick = { onChatClick(chat.chatId) },
                colors = colors
            )

            if (index < chats.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.padding(start = 74.dp),
                    thickness = 1.dp,
                    color = colors.textPrimary.copy(alpha = 0.05f)
                )
            }
        }
    }

}


@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun ChatListSurfaceLight() {
    AndroidSimpleClientTheme(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                ChatListSurface(
                    previewChatList,
                    previewOwnerUuid,
                    LazyListState(),
                    { },
                    LightAppColors,
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun ChatListSurfaceDark() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                ChatListSurface(
                    previewChatList,
                    previewOwnerUuid,
                    LazyListState(),
                    { },
                    DarkAppColors,
                )
            }
        }
    )
}