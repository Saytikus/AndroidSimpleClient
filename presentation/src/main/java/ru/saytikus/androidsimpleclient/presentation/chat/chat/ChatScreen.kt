package ru.saytikus.androidsimpleclient.presentation.chat.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.saytikus.androidsimpleclient.domain.core.message.model.Message
import ru.saytikus.androidsimpleclient.presentation.chat.chat.components.ChatDateSeparator
import ru.saytikus.androidsimpleclient.presentation.chat.chat.components.ChatInputBar
import ru.saytikus.androidsimpleclient.presentation.chat.chat.components.ChatTopBar
import ru.saytikus.androidsimpleclient.presentation.chat.chat.components.TypingIndicator
import ru.saytikus.androidsimpleclient.presentation.chat.chat.components.preview.previewMessageList
import ru.saytikus.androidsimpleclient.presentation.chat.chat.components.preview.previewOwnerChatUuid
import ru.saytikus.androidsimpleclient.presentation.core.ui.message.MessageItem
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

    LaunchedEffect(state.messages.size) {
        if (state.messages.isNotEmpty()) {
            listState.animateScrollToItem(state.messages.lastIndex)
        }
    }

    val firstVisibleMessageId by remember {
        derivedStateOf {
            val index = listState.firstVisibleItemIndex
            state.messages.getOrNull(index)?.id
        }
    }
    LaunchedEffect(firstVisibleMessageId) {
        firstVisibleMessageId?.let {
            onAction(ChatAction.OnFirstVisibleMessageChanged(it))
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
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(
                    start = 14.dp,
                    end = 14.dp,
                    top = 12.dp,
                    bottom = 12.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (state.isLoadingMore) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = c.accent,
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                val grouped = state.messages.groupByDate()
                grouped.forEach { (date, msgs) ->
                    item(key = "date_$date") {
                        ChatDateSeparator(date = date, colors = c)
                    }
                    itemsIndexed(
                        items = msgs,
                        key = { _, msg -> msg.id.toString() }
                    ) { index, message ->
                        val prevMessage = msgs.getOrNull(index - 1)
                        val isOwn = message.senderId == state.ownerProfileId
                        val prevIsOwn = prevMessage?.senderId == state.ownerProfileId
                        val showAvatar = !isOwn && (prevMessage == null || prevIsOwn)
                        MessageItem(
                            message = message,
                            isOwn = isOwn,
                            showAvatar = showAvatar,
                            avatarIcon = state.avatarIcon,
                            colors = c
                        )
                    }
                }

                if (state.isTyping) {
                    item(key = "typing") {
                        TypingIndicator(
                            avatarIcon = state.avatarIcon,
                            colors = c
                        )
                    }
                }
            }
        }
    }
}

private fun List<Message>.groupByDate(): Map<String, List<Message>> {
    return groupBy { message ->
        message.createdAt.take(10)
    }.mapKeys { (dateStr, _) ->
        formatDateKey(dateStr)
    }
}

private fun formatDateKey(isoDate: String): String {
    return try {
        val parts = isoDate.split("-")
        val month = parts[1].toInt()
        val day = parts[2].toInt()
        val months = listOf("", "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
        "$day ${months.getOrElse(month) { "" }}"
    } catch (e: Exception) {
        isoDate
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
