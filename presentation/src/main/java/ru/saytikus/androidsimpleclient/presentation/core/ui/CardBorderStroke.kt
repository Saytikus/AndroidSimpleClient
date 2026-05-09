package ru.saytikus.androidsimpleclient.presentation.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider

@Composable
fun cardBorderStroke(
    borderStart: Color = ColorProvider.colors.cardBorderStart,
    borderEnd: Color = ColorProvider.colors.cardBorderEnd

): BorderStroke {

    return BorderStroke(
        width = 1.dp,
        brush = Brush.horizontalGradient(
            colors = listOf(
                borderStart.copy(0.5f),
                borderEnd
            )
        )
    )
}