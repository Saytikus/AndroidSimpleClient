package ru.saytikus.androidsimpleclient.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider.colors

@Composable
fun cardBorderStroke(
    borderStart: Color = colors.cardBorderStart,
    borderEnd: Color = colors.cardBorderEnd

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