package ru.saytikus.androidsimpleclient.presentation.core.ui.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import ru.saytikus.androidsimpleclient.domain.core.features.message.model.Message
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

private const val LOAD_MORE_INDEX_THRESHOLD = 15

@OptIn(ExperimentalUuidApi::class)
@Composable
fun MessageList(
    messages: List<Message>,
    ownerProfileId: Uuid,
    avatarIcon: ImageVector,
    isLoadingMore: Boolean,
    isTyping: Boolean,
    colors: AppColors,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    onFirstVisibleMessageChanged: (Uuid) -> Unit = {},
    onLoadMore: () -> Unit = {}
) {
    val updatedMessages by rememberUpdatedState(messages)
    val updatedOnFirstVisible by rememberUpdatedState(onFirstVisibleMessageChanged)
    val updatedOnLoadMore by rememberUpdatedState(onLoadMore)

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                updatedMessages.getOrNull(index)?.id?.let { updatedOnFirstVisible(it) }
            }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .map { it <= LOAD_MORE_INDEX_THRESHOLD }
            .distinctUntilChanged()
            .filter { it }
            .collect { updatedOnLoadMore() }
    }

    LazyColumn(
        state = listState,
        modifier = modifier,
        contentPadding = PaddingValues(
            start = 14.dp,
            end = 14.dp,
            top = 12.dp,
            bottom = 12.dp
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (isLoadingMore) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = colors.accent,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        val grouped = messages.groupByDate()
        grouped.forEach { (date, msgs) ->
            item(key = "date_$date") {
                MessageDateSeparator(date = date, colors = colors)
            }
            itemsIndexed(
                items = msgs,
                key = { _, msg -> msg.id.toString() }
            ) { index, message ->
                val prevMessage = msgs.getOrNull(index - 1)
                val isOwn = message.senderId == ownerProfileId
                val prevIsOwn = prevMessage?.senderId == ownerProfileId
                val showAvatar = !isOwn && (prevMessage == null || prevIsOwn)
                MessageItem(
                    message = message,
                    isOwn = isOwn,
                    showAvatar = showAvatar,
                    avatarIcon = avatarIcon,
                    colors = colors
                )
            }
        }

        if (isTyping) {
            item(key = "typing") {
                MessageTypingIndicator(
                    avatarIcon = avatarIcon,
                    colors = colors
                )
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
        val months = listOf(
            "", "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        )
        "$day ${months.getOrElse(month) { "" }}"
    } catch (e: Exception) {
        isoDate
    }
}

@OptIn(ExperimentalUuidApi::class)
@Preview(name = "MessageList")
@Composable
private fun MessageListPreviewLight() {
    val ownerUuid = Uuid.parse("00000000-0000-0000-0000-000000000001")
    val otherUuid = Uuid.parse("00000000-0000-0000-0000-000000000002")
    val messages = listOf(
        Message(
            id = Uuid.parse("00000000-0000-0000-0000-000000000010"),
            senderId = otherUuid,
            senderName = "Alice",
            text = "Hey! Did you push the fix?",
            createdAt = "2026-05-16T12:40:00.000Z",
            isRead = true
        ),
        Message(
            id = Uuid.parse("00000000-0000-0000-0000-000000000011"),
            senderId = ownerUuid,
            senderName = null,
            text = "Yeah, just merged it",
            createdAt = "2026-05-16T12:41:00.000Z",
            isRead = true
        ),
        Message(
            id = Uuid.parse("00000000-0000-0000-0000-000000000012"),
            senderId = otherUuid,
            senderName = "Alice",
            text = "Amazing, I'll test it now",
            createdAt = "2026-05-16T12:41:30.000Z",
            isRead = true
        ),
        Message(
            id = Uuid.parse("00000000-0000-0000-0000-000000000013"),
            senderId = ownerUuid,
            senderName = null,
            text = "Let me know if anything breaks",
            createdAt = "2026-05-16T12:43:00.000Z",
            isRead = false
        )
    )
    AndroidSimpleClientTheme(
        previewDarkTheme = false,
        content = {
            MessageList(
                messages = messages,
                ownerProfileId = ownerUuid,
                avatarIcon = Icons.Rounded.Person,
                isLoadingMore = false,
                isTyping = true,
                colors = LightAppColors,
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = ColorProvider.backgroundBrush())
            )
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview(name = "MessageList")
@Composable
private fun MessageListPreviewDark() {
    val ownerUuid = Uuid.parse("00000000-0000-0000-0000-000000000001")
    val otherUuid = Uuid.parse("00000000-0000-0000-0000-000000000002")
    val messages = listOf(
        Message(
            id = Uuid.parse("00000000-0000-0000-0000-000000000010"),
            senderId = otherUuid,
            senderName = "Alice",
            text = "Hey! Did you push the fix?",
            createdAt = "2026-05-16T12:40:00.000Z",
            isRead = true
        ),
        Message(
            id = Uuid.parse("00000000-0000-0000-0000-000000000011"),
            senderId = ownerUuid,
            senderName = null,
            text = "Yeah, just merged it",
            createdAt = "2026-05-16T12:41:00.000Z",
            isRead = true
        ),
        Message(
            id = Uuid.parse("00000000-0000-0000-0000-000000000012"),
            senderId = otherUuid,
            senderName = "Alice",
            text = "Amazing, I'll test it now",
            createdAt = "2026-05-16T12:41:30.000Z",
            isRead = true
        ),
        Message(
            id = Uuid.parse("00000000-0000-0000-0000-000000000013"),
            senderId = ownerUuid,
            senderName = null,
            text = "Let me know if anything breaks",
            createdAt = "2026-05-16T12:43:00.000Z",
            isRead = false
        )
    )
    AndroidSimpleClientTheme(
        previewDarkTheme = true,
        content = {
            MessageList(
                messages = messages,
                ownerProfileId = ownerUuid,
                avatarIcon = Icons.Rounded.Person,
                isLoadingMore = true,
                isTyping = false,
                colors = DarkAppColors,
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = ColorProvider.backgroundBrush())
            )
        }
    )
}
