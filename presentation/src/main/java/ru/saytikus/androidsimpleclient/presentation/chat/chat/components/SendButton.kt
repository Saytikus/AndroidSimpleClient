package ru.saytikus.androidsimpleclient.presentation.chat.chat.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors

@Composable
internal fun SendButton(
    enabled: Boolean,
    onClick: () -> Unit,
    colors: AppColors
) {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled) 0.88f else 1f,
        animationSpec = spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessHigh),
        label = "scale"
    )
    val accentAlpha by animateFloatAsState(
        targetValue = if (enabled) 1f else 0.35f,
        animationSpec = tween(200),
        label = "alpha"
    )

    Box(
        modifier = Modifier
            .size(40.dp)
            .scale(scale)
            .background(
                color = colors.accent.copy(alpha = 0.18f * accentAlpha),
                shape = CircleShape
            )
            .border(
                width = 1.5.dp,
                color = colors.accent.copy(alpha = 0.40f * accentAlpha),
                shape = CircleShape
            )
            .pointerInput(enabled) {
                if (enabled) {
                    detectTapGestures(
                        onPress = {
                            isPressed = true
                            tryAwaitRelease()
                            isPressed = false
                        },
                        onTap = { onClick() }
                    )
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(18.dp)) {
            val w = size.width
            val h = size.height
            val color = colors.accent.copy(alpha = accentAlpha)
            val strokeW = 2.2.dp.toPx()

            drawLine(
                color = color,
                start = Offset(w / 2f, h * 0.38f),
                end = Offset(w / 2f, h * 0.85f),
                strokeWidth = strokeW,
                cap = StrokeCap.Round
            )
            drawLine(
                color = color,
                start = Offset(w / 2f, h * 0.15f),
                end = Offset(w * 0.25f, h * 0.42f),
                strokeWidth = strokeW,
                cap = StrokeCap.Round
            )
            drawLine(
                color = color,
                start = Offset(w / 2f, h * 0.15f),
                end = Offset(w * 0.75f, h * 0.42f),
                strokeWidth = strokeW,
                cap = StrokeCap.Round
            )
        }
    }
}

@Preview(name = "SendButton")
@Composable
private fun SendButtonLight() {
    AndroidSimpleClientTheme(
        previewDarkTheme = false,
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SendButton(enabled = true, onClick = {}, colors = LightAppColors)
                SendButton(enabled = false, onClick = {}, colors = LightAppColors)
            }
        }
    )
}

@Preview(name = "SendButton")
@Composable
private fun SendButtonDark() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true,
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SendButton(enabled = true, onClick = {}, colors = DarkAppColors)
                SendButton(enabled = false, onClick = {}, colors = DarkAppColors)
            }
        }
    )
}
