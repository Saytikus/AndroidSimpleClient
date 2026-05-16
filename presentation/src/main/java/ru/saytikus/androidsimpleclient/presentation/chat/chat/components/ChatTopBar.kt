package ru.saytikus.androidsimpleclient.presentation.chat.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors

@Composable
internal fun ChatTopBar(
    chatName: String,
    isOnline: Boolean,
    isTyping: Boolean,
    avatarIcon: ImageVector,
    colors: AppColors,
    onBackClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    val topBarBackground = Brush.verticalGradient(
        colors = listOf(
            colors.backgroundStart.copy(alpha = 0.95f),
            colors.backgroundStart.copy(alpha = 0.75f)
        )
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(brush = topBarBackground)
            .drawBehind {
                drawLine(
                    color = Color(0xFFE94560).copy(alpha = 0.12f),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx()
                )
            }
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ChatTopBarButton(
            icon = Icons.Rounded.ArrowBackIos,
            iconSize = 16.dp,
            colors = colors,
            onClick = onBackClick
        )

        Box(
            modifier = Modifier
                .size(38.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF2D9CDB).copy(alpha = 0.50f),
                            Color(0xFF2D9CDB).copy(alpha = 0.12f)
                        ),
                        radius = 80f
                    ),
                    shape = CircleShape
                )
                .border(1.5.dp, Color(0xFF2D9CDB).copy(alpha = 0.35f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = avatarIcon,
                contentDescription = null,
                tint = Color(0xFF2D9CDB).copy(alpha = 0.95f),
                modifier = Modifier.size(17.dp)
            )
            if (isOnline) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .align(Alignment.BottomEnd)
                        .background(colors.accent, CircleShape)
                        .border(2.dp, colors.backgroundStart, CircleShape)
                )
            }
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = chatName,
                color = colors.textPrimary,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = when {
                    isTyping -> "typing…"
                    isOnline -> "online"
                    else     -> "offline"
                },
                color = when {
                    isTyping -> colors.accent
                    isOnline -> colors.accent
                    else     -> colors.textSecondary
                },
                fontSize = 11.sp,
                fontStyle = if (isTyping) FontStyle.Italic else FontStyle.Normal,
                modifier = Modifier.padding(top = 1.dp)
            )
        }

        ChatTopBarButton(
            icon = Icons.Rounded.MoreHoriz,
            iconSize = 18.dp,
            colors = colors,
            onClick = onMenuClick
        )
    }
}

@Preview(name = "ChatTopBar")
@Composable
private fun ChatTopBarLightOnline() {
    AndroidSimpleClientTheme(
        previewDarkTheme = false,
        content = {
            ChatTopBar(
                chatName = "Alice",
                isOnline = true,
                isTyping = false,
                avatarIcon = Icons.Rounded.Person,
                colors = LightAppColors,
                onBackClick = {},
                onMenuClick = {}
            )
        }
    )
}

@Preview(name = "ChatTopBar")
@Composable
private fun ChatTopBarDarkTyping() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true,
        content = {
            ChatTopBar(
                chatName = "Alice",
                isOnline = true,
                isTyping = true,
                avatarIcon = Icons.Rounded.Person,
                colors = DarkAppColors,
                onBackClick = {},
                onMenuClick = {}
            )
        }
    )
}

@Preview(name = "ChatTopBar")
@Composable
private fun ChatTopBarDarkOffline() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true,
        content = {
            ChatTopBar(
                chatName = "Bob",
                isOnline = false,
                isTyping = false,
                avatarIcon = Icons.Rounded.Person,
                colors = DarkAppColors,
                onBackClick = {},
                onMenuClick = {}
            )
        }
    )
}
