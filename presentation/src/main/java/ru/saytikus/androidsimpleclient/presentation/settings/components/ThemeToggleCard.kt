package ru.saytikus.androidsimpleclient.presentation.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults.colors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.saytikus.androidsimpleclient.presentation.common.cardBorderStroke
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors

@Composable
fun ThemeToggleCard(
    isDark: Boolean,
    onToggle: () -> Unit,
    colors: AppColors
) {

    val iconColor   = if (isDark) colors.textPrimary else Color(0xFFFBC02D).copy(alpha = 0.9f)
    val iconBgStart = if (isDark) colors.textPrimary.copy(alpha = 0.12f) else Color(0xFFFFD166).copy(alpha = 0.17f)
    val iconBgEnd   = if (isDark) colors.textPrimary.copy(alpha = 0.02f) else Color(0xFFFFAA00).copy(alpha = 0.04f)
    val iconBorder  = if (isDark) Color(0xFF7B68B5).copy(alpha = 0.45f) else Color(0xFF6A9FD4).copy(alpha = 0.60f)

    Card(
        onClick = { onToggle() },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = colors.cardBackground),
        border = cardBorderStroke(),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(iconBgStart, iconBgEnd),
                            radius = 80f
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(1.dp, iconBorder, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.DarkMode,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(22.dp)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Dark theme",
                    color = colors.textPrimary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
                Text(
                    text = "Tap to switch",
                    color = colors.textSecondary,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            // Switch
            Switch(
                checked = isDark,
                onCheckedChange = { onToggle() },
                colors = colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = colors.accent,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = colors.textSecondary.copy(alpha = 0.3f),
                    uncheckedBorderColor = Color.Transparent
                )
            )
        }
    }
}


@Preview(name = "Light", showBackground = true, backgroundColor = 0xFFF2F9F4)
@Composable
private fun ThemeToggleCardLight() {
    AndroidSimpleClientTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = ColorProvider.backgroundBrush())
                .padding(16.dp)
        ) {
            ThemeToggleCard(
                false,
                {  },
                LightAppColors
            )
        }
    }
}

@Preview(name = "Dark", showBackground = true, backgroundColor = 0xFF1A1A2E)
@Composable
private fun ThemeToggleCardDark() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = ColorProvider.backgroundBrush())
                .padding(16.dp)
        ) {
            ThemeToggleCard(
                true,
                {  },
                DarkAppColors
            )
        }
    }
}