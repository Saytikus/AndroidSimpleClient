package ru.saytikus.androidsimpleclient.presentation.chat.chatList.components.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.saytikus.androidsimpleclient.presentation.chat.chatList.components.ChatListComponentItem
import ru.saytikus.androidsimpleclient.presentation.core.ui.search.SearchContent
import ru.saytikus.androidsimpleclient.presentation.core.ui.search.SearchState
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
private val previewSearchState = SearchState(
    query = "",
    items = previewChatList,
    isLoading = false,
    error = null
)

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun SearchContentChatLightPreview() {
    AndroidSimpleClientTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = ColorProvider.backgroundBrush())
        ) {
            SearchContent(
                Modifier.padding(16.dp),
                state = previewSearchState,
                queryPlaceholder = "Search chats...",
                onAction = {},
                itemContent = { chat ->
                    ChatListComponentItem(
                        chat = chat,
                        ownerProfileId = previewOwnerUuid,
                        onClick = {},
                        colors = LightAppColors
                    )
                },
                aboveListContent = {
                    Spacer(modifier = Modifier.height(30.dp))
                }
            )
        }
    }
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun SearchContentChatDarkPreview() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = ColorProvider.backgroundBrush())
        ) {
            SearchContent(
                Modifier.padding(16.dp),
                state = previewSearchState,
                queryPlaceholder = "Search chats...",
                onAction = {},
                itemContent = { chat ->
                    ChatListComponentItem(
                        chat = chat,
                        ownerProfileId = previewOwnerUuid,
                        onClick = {},
                        colors = DarkAppColors
                    )
                },
                aboveListContent = {
                    Spacer(modifier = Modifier.height(30.dp))
                }
            )
        }
    }
}