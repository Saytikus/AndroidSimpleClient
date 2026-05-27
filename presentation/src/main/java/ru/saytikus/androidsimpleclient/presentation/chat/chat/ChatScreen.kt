package ru.saytikus.androidsimpleclient.presentation.chat.chat

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.saytikus.androidsimpleclient.presentation.chat.chat.components.ChatInputBar
import ru.saytikus.androidsimpleclient.presentation.chat.chat.components.ChatTopBar
import ru.saytikus.androidsimpleclient.presentation.chat.chat.components.preview.previewMessageList
import ru.saytikus.androidsimpleclient.presentation.chat.chat.components.preview.previewOwnerChatUuid
import ru.saytikus.androidsimpleclient.presentation.core.ui.message.MessageList
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
@Composable
fun ChatScreen(
    state: ChatState,
    onAction: (ChatAction) -> Unit
) {
    val c = ColorProvider.colors
    val listState = rememberLazyListState()

    BackHandler {
        onAction(ChatAction.OnBackClick)
    }

    LaunchedEffect(state.messages.lastOrNull()?.id) {
        if (state.messages.isNotEmpty()) {
            listState.animateScrollToItem(Int.MAX_VALUE)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = ColorProvider.backgroundBrush())
    ) {
        Box(
            modifier = Modifier
                .offset(x = (-60).dp, y = 100.dp)
                .size(280.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(c.blob1, Color.Transparent),
                        radius = 500f
                    ),
                    shape = CircleShape
                )
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 60.dp, y = 60.dp)
                .size(300.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(c.blob2, Color.Transparent),
                        radius = 500f
                    ),
                    shape = CircleShape
                )
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                ChatTopBar(
                    chatName = state.chatName,
                    isOnline = state.isOnline,
                    isTyping = state.isTyping,
                    avatarIcon = state.avatarIcon,
                    colors = c,
                    onBackClick = { onAction(ChatAction.OnBackClick) }
                )
            },
            bottomBar = {
                ChatInputBar(
                    text = state.inputText,
                    onTextChange = { onAction(ChatAction.OnInputChange(it)) },
                    onSendClick = { onAction(ChatAction.OnSendClick) },
                    colors = c
                )
            }
        ) { innerPadding ->
            MessageList(
                messages = state.messages,
                ownerProfileId = state.ownerProfileId,
                avatarIcon = state.avatarIcon,
                isLoadingMore = state.isLoadingMore,
                isTyping = state.isTyping,
                colors = c,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                listState = listState,
                onFirstVisibleMessageChanged = {
                    onAction(ChatAction.OnFirstVisibleMessageChanged(it))
                },
                onLoadMore = { onAction(ChatAction.OnLoadMoreMessages) }
            )
        }
    }
}

@OptIn(ExperimentalUuidApi::class)
@Preview(name = "ChatScreen")
@Composable
private fun ChatScreenPreviewLight() {
    AndroidSimpleClientTheme(
        previewDarkTheme = false,
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                ChatScreen(
                    state = ChatState(
                        chatName = "Alice",
                        ownerProfileId = previewOwnerChatUuid,
                        isOnline = true,
                        isTyping = true,
                        messages = previewMessageList
                    ),
                    onAction = {}
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview(name = "ChatScreen")
@Composable
private fun ChatScreenPreviewDark() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true,
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                ChatScreen(
                    state = ChatState(
                        chatName = "Alice",
                        ownerProfileId = previewOwnerChatUuid,
                        isOnline = true,
                        isTyping = true,
                        messages = previewMessageList
                    ),
                    onAction = {}
                )
            }
        }
    )
}
