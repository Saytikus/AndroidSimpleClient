package ru.saytikus.androidsimpleclient.presentation.chat.chat.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors

@Composable
internal fun ChatTopBarButton(
    icon: ImageVector,
    iconSize: Dp,
    colors: AppColors,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.90f else 1f,
        animationSpec = spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessHigh),
        label = "scale"
    )

    Box(
        modifier = Modifier
            .size(32.dp)
            .scale(scale)
            .background(
                color = colors.accent.copy(alpha = 0.10f),
                shape = RoundedCornerShape(9.dp)
            )
            .border(1.dp, colors.accent.copy(alpha = 0.18f), RoundedCornerShape(9.dp))
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        tryAwaitRelease()
                        isPressed = false
                    },
                    onTap = { onClick() }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = colors.accent.copy(alpha = 0.80f),
            modifier = Modifier.size(iconSize)
        )
    }
}

@Preview(name = "ChatTopBarButton")
@Composable
private fun ChatTopBarButtonLight() {
    AndroidSimpleClientTheme(
        previewDarkTheme = false,
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ChatTopBarButton(
                    icon = Icons.Rounded.ArrowBackIos,
                    iconSize = 16.dp,
                    colors = LightAppColors,
                    onClick = {}
                )
                ChatTopBarButton(
                    icon = Icons.Rounded.MoreHoriz,
                    iconSize = 18.dp,
                    colors = LightAppColors,
                    onClick = {}
                )
            }
        }
    )
}

@Preview(name = "ChatTopBarButton")
@Composable
private fun ChatTopBarButtonDark() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true,
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ChatTopBarButton(
                    icon = Icons.Rounded.ArrowBackIos,
                    iconSize = 16.dp,
                    colors = DarkAppColors,
                    onClick = {}
                )
                ChatTopBarButton(
                    icon = Icons.Rounded.MoreHoriz,
                    iconSize = 18.dp,
                    colors = DarkAppColors,
                    onClick = {}
                )
            }
        }
    )
}
