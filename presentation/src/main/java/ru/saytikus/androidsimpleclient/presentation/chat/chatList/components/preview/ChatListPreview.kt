package ru.saytikus.androidsimpleclient.presentation.chat.chatList.components.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.saytikus.androidsimpleclient.presentation.chat.chatList.components.ChatListComponentItem
import ru.saytikus.androidsimpleclient.presentation.common.displayItem.DisplayItemList
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun ChatListLight() {
    AndroidSimpleClientTheme(
        content = {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush()),
                contentAlignment = Alignment.Center
            ) {

                DisplayItemList(
                    items = previewChatList,
                    colors = LightAppColors
                ) { chat ->

                    ChatListComponentItem(
                        chat = chat,

                        ownerProfileId = previewOwnerUuid,

                        onClick = {},

                        colors = LightAppColors
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun ChatListDark() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true,
        content = {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush()),
                contentAlignment = Alignment.Center
            ) {

                DisplayItemList(
                    items = previewChatList,
                    colors = DarkAppColors
                ) { chat ->

                    ChatListComponentItem(
                        chat = chat,

                        ownerProfileId = previewOwnerUuid,

                        onClick = {},

                        colors = DarkAppColors
                    )
                }
            }
        }
    )
}