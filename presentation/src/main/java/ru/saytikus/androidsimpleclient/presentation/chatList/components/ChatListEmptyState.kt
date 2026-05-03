package ru.saytikus.androidsimpleclient.presentation.chatList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors
import kotlin.uuid.ExperimentalUuidApi

@Composable
fun ChatEmptyState(colors: AppColors) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .background(colors.backgroundEnd, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Chat,
                    contentDescription = null,
                    tint = colors.accent,
                    modifier = Modifier.size(36.dp)
                )
            }
            Text(
                text = "No chats yet",
                color = colors.accent,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Pull down to refresh",
                color = colors.textPrimary,
                fontSize = 12.sp
            )
        }
    }
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun ChatEmptyStateLight() {
    AndroidSimpleClientTheme(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                ChatEmptyState(
                    LightAppColors,
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun ChatEmptyStateDark() {
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
                ChatEmptyState(
                    DarkAppColors,
                )
            }
        }
    )
}