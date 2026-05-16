package ru.saytikus.androidsimpleclient.presentation.core.ui.message

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.DoneAll
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import ru.saytikus.androidsimpleclient.domain.common.message.model.Message
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors

@Composable
fun MessageItem(
    message: Message,
    isOwn: Boolean,
    showAvatar: Boolean,
    avatarIcon: ImageVector,
    colors: AppColors
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isOwn) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        if (!isOwn) {
            if (showAvatar) {
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(28.dp)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFF2D9CDB).copy(alpha = 0.40f),
                                    Color(0xFF2D9CDB).copy(alpha = 0.10f)
                                ),
                                radius = 60f
                            ),
                            shape = CircleShape
                        )
                        .border(1.dp, Color(0xFF2D9CDB).copy(alpha = 0.25f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = avatarIcon,
                        contentDescription = null,
                        tint = Color(0xFF2D9CDB).copy(alpha = 0.90f),
                        modifier = Modifier.size(13.dp)
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(36.dp))
            }
        }

        Column(
            horizontalAlignment = if (isOwn) Alignment.End else Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .widthIn(max = 260.dp)
                    .background(
                        brush = if (isOwn) {
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFB42846).copy(alpha = 0.45f),
                                    Color(0xFF8C1E37).copy(alpha = 0.35f)
                                )
                            )
                        } else {
                            Brush.linearGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.09f),
                                    Color.White.copy(alpha = 0.06f)
                                )
                            )
                        },
                        shape = if (isOwn) {
                            RoundedCornerShape(16.dp, 16.dp, 4.dp, 16.dp)
                        } else {
                            RoundedCornerShape(16.dp, 16.dp, 16.dp, 4.dp)
                        }
                    )
                    .border(
                        width = 1.dp,
                        color = if (isOwn)
                            Color(0xFFE94560).copy(alpha = 0.25f)
                        else
                            Color.White.copy(alpha = 0.10f),
                        shape = if (isOwn) {
                            RoundedCornerShape(16.dp, 16.dp, 4.dp, 16.dp)
                        } else {
                            RoundedCornerShape(16.dp, 16.dp, 16.dp, 4.dp)
                        }
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {

                Text(
                    text = message.text,
                    color = colors.textPrimary,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(3.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formatMessageTime(message.createdAt),
                        color = colors.textPrimary.copy(alpha = 0.35f),
                        fontSize = 10.sp
                    )
                    if (isOwn) {
                        Icon(
                            imageVector = if (message.isRead)
                                Icons.Rounded.DoneAll else Icons.Rounded.Done,
                            contentDescription = null,
                            tint = if (message.isRead)
                                Color(0xFFE94560).copy(alpha = 0.70f)
                            else
                                colors.textPrimary.copy(alpha = 0.35f),
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }
            }
        }
    }
}

fun formatMessageTime(createdAt: String): String {
    return try {
        createdAt.substringAfter("T").take(5)
    } catch (e: Exception) {
        ""
    }
}

@OptIn(ExperimentalUuidApi::class)
private val previewOwnerUuid = Uuid.parse("00000000-0000-0000-0000-000000000001")

@OptIn(ExperimentalUuidApi::class)
private val previewOtherUuid = Uuid.parse("00000000-0000-0000-0000-000000000002")

@OptIn(ExperimentalUuidApi::class)
private val previewReceivedMessage = Message(
    id = Uuid.parse("00000000-0000-0000-0000-000000000010"),
    senderId = previewOtherUuid,
    senderName = "Alice",
    text = "Hey! Did you push the fix?",
    createdAt = "2026-05-16T12:40:00.000Z",
    isRead = true
)

@OptIn(ExperimentalUuidApi::class)
private val previewOwnMessageRead = Message(
    id = Uuid.parse("00000000-0000-0000-0000-000000000011"),
    senderId = previewOwnerUuid,
    senderName = null,
    text = "Yeah, just merged it",
    createdAt = "2026-05-16T12:41:00.000Z",
    isRead = true
)

@OptIn(ExperimentalUuidApi::class)
private val previewOwnMessageUnread = Message(
    id = Uuid.parse("00000000-0000-0000-0000-000000000012"),
    senderId = previewOwnerUuid,
    senderName = null,
    text = "Let me know if anything breaks",
    createdAt = "2026-05-16T12:43:00.000Z",
    isRead = false
)

@OptIn(ExperimentalUuidApi::class)
@Preview(name = "MessageItem")
@Composable
private fun MessageItemLight() {
    AndroidSimpleClientTheme(
        previewDarkTheme = false,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                MessageItem(
                    message = previewReceivedMessage,
                    isOwn = false,
                    showAvatar = true,
                    avatarIcon = Icons.Rounded.Person,
                    colors = LightAppColors
                )
                MessageItem(
                    message = previewOwnMessageRead,
                    isOwn = true,
                    showAvatar = false,
                    avatarIcon = Icons.Rounded.Person,
                    colors = LightAppColors
                )
                MessageItem(
                    message = previewOwnMessageUnread,
                    isOwn = true,
                    showAvatar = false,
                    avatarIcon = Icons.Rounded.Person,
                    colors = LightAppColors
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview(name = "MessageItem")
@Composable
private fun MessageItemDark() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                MessageItem(
                    message = previewReceivedMessage,
                    isOwn = false,
                    showAvatar = true,
                    avatarIcon = Icons.Rounded.Person,
                    colors = DarkAppColors
                )
                MessageItem(
                    message = previewOwnMessageRead,
                    isOwn = true,
                    showAvatar = false,
                    avatarIcon = Icons.Rounded.Person,
                    colors = DarkAppColors
                )
                MessageItem(
                    message = previewOwnMessageUnread,
                    isOwn = true,
                    showAvatar = false,
                    avatarIcon = Icons.Rounded.Person,
                    colors = DarkAppColors
                )
            }
        }
    )
}
