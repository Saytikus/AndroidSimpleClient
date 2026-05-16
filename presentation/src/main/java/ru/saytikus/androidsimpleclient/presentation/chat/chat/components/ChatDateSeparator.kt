package ru.saytikus.androidsimpleclient.presentation.chat.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors

@Composable
internal fun ChatDateSeparator(
    date: String,
    colors: AppColors
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date,
            fontSize = 11.sp,
            color = colors.textSecondary,
            modifier = Modifier
                .background(
                    color = colors.textPrimary.copy(alpha = 0.05f),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 10.dp, vertical = 3.dp)
        )
    }
}

@Preview(name = "ChatDateSeparator")
@Composable
private fun ChatDateSeparatorLight() {
    AndroidSimpleClientTheme(
        previewDarkTheme = false,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush()),
                contentAlignment = Alignment.Center
            ) {
                ChatDateSeparator(date = "16 May", colors = LightAppColors)
            }
        }
    )
}

@Preview(name = "ChatDateSeparator")
@Composable
private fun ChatDateSeparatorDark() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush()),
                contentAlignment = Alignment.Center
            ) {
                ChatDateSeparator(date = "16 May", colors = DarkAppColors)
            }
        }
    )
}
