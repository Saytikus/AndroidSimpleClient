package ru.saytikus.androidsimpleclient.presentation.chatList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.saytikus.androidsimpleclient.presentation.R
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors

@Composable
fun AddChatButton(
    modifier: Modifier,
    onClick: () -> Unit,
    colors: AppColors
) {

    Box(
        modifier = modifier
            .size(62.dp)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        colors.accent.copy(alpha = 0.84f),
                        colors.accent.copy(alpha = 0.64f)
                    ),
                    radius = 100f
                ),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {

        IconButton(
            onClick = { onClick() }

            ) {

            Icon(
                modifier = Modifier
                    .size(36.dp),
                painter = painterResource(R.drawable.chat_add_on_24px),
                contentDescription = "Add Chat",
                tint = colors.avatar
            )
        }
    }
}


@Preview
@Composable
fun AddChatButtonLight() {
    AndroidSimpleClientTheme(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                AddChatButton(
                    Modifier,
                    { },
                    LightAppColors,
                )
            }
        }
    )
}

@Preview
@Composable
fun AddChatButtonDark() {
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
                AddChatButton(
                    Modifier,
                    { },
                    DarkAppColors
                )
            }
        }
    )
}