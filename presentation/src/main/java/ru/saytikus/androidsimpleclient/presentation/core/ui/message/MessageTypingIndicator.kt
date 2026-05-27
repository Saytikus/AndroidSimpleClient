package ru.saytikus.androidsimpleclient.presentation.core.ui.message

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors

@Composable
fun MessageTypingIndicator(
    avatarIcon: ImageVector,
    colors: AppColors
) {
    val infiniteTransition = rememberInfiniteTransition(label = "typing")

    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
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

        Row(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            colors.otherBubbleBackgroundStart,
                            colors.otherBubbleBackgroundEnd
                        )
                    ),
                    shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 4.dp)
                )
                .border(
                    1.dp,
                    colors.otherBubbleBorder,
                    RoundedCornerShape(16.dp, 16.dp, 16.dp, 4.dp)
                )
                .padding(horizontal = 14.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(3) { index ->
                val offsetY by infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = -5f,
                    animationSpec = infiniteRepeatable(
                        animation = keyframes {
                            durationMillis = 1200
                            0f at 0
                            -5f at 200 + index * 150
                            0f at 400 + index * 150
                            0f at 1200
                        },
                        repeatMode = RepeatMode.Restart
                    ),
                    label = "dot_$index"
                )
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .offset(y = offsetY.dp)
                        .background(
                            color = colors.textPrimary.copy(0.6f),
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@Preview(name = "MessageTypingIndicator")
@Composable
private fun MessageTypingIndicatorLight() {
    AndroidSimpleClientTheme(
        previewDarkTheme = false,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(14.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                MessageTypingIndicator(
                    avatarIcon = Icons.Rounded.Person,
                    colors = LightAppColors
                )
            }
        }
    )
}

@Preview(name = "MessageTypingIndicator")
@Composable
private fun MessageTypingIndicatorDark() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(14.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                MessageTypingIndicator(
                    avatarIcon = Icons.Rounded.Person,
                    colors = DarkAppColors
                )
            }
        }
    )
}
