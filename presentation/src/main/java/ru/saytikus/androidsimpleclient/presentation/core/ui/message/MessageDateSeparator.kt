package ru.saytikus.androidsimpleclient.presentation.core.ui.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors

@Composable
fun MessageDateSeparator(
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
            fontSize = 12.sp,
            color = colors.textPrimary,
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            colors.otherBubbleBackgroundStart,
                            colors.otherBubbleBackgroundEnd
                        )
                    ),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Preview(name = "MessageDateSeparator")
@Composable
private fun MessageDateSeparatorLight() {
    AndroidSimpleClientTheme(
        previewDarkTheme = false,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush()),
                contentAlignment = Alignment.Center
            ) {
                MessageDateSeparator(date = "16 May", colors = LightAppColors)
            }
        }
    )
}

@Preview(name = "MessageDateSeparator")
@Composable
private fun MessageDateSeparatorDark() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush()),
                contentAlignment = Alignment.Center
            ) {
                MessageDateSeparator(date = "16 May", colors = DarkAppColors)
            }
        }
    )
}
