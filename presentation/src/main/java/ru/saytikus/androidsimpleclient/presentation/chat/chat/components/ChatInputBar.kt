package ru.saytikus.androidsimpleclient.presentation.chat.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors

@Composable
internal fun ChatInputBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSendClick: () -> Unit,
    colors: AppColors
) {
    val bottomBarBackground = Brush.verticalGradient(
        colors = listOf(
            colors.backgroundStart.copy(alpha = 0.75f),
            colors.backgroundStart.copy(alpha = 0.95f)
        )
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(brush = bottomBarBackground)
            .drawBehind {
                drawLine(
                    color = Color(0xFFE94560).copy(alpha = 0.12f),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1.dp.toPx()
                )
            }
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .background(
                    color = Color.White.copy(alpha = 0.07f),
                    shape = RoundedCornerShape(20.dp)
                )
                .border(
                    1.dp,
                    Color.White.copy(alpha = 0.09f),
                    RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 14.dp, vertical = 9.dp)
        ) {
            BasicTextField(
                value = text,
                onValueChange = onTextChange,
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = colors.textPrimary,
                    lineHeight = 20.sp
                ),
                maxLines = 5,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Send,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                keyboardActions = KeyboardActions(
                    onSend = { if (text.isNotBlank()) onSendClick() }
                ),
                decorationBox = { innerTextField ->
                    if (text.isEmpty()) {
                        Text(
                            text = "Message…",
                            fontSize = 14.sp,
                            color = colors.textSecondary
                        )
                    }
                    innerTextField()
                }
            )
        }

        SendButton(
            enabled = text.isNotBlank(),
            onClick = onSendClick,
            colors = colors
        )
    }
}

@Preview(name = "ChatInputBar")
@Composable
private fun ChatInputBarLightEmpty() {
    AndroidSimpleClientTheme(
        previewDarkTheme = false,
        content = {
            ChatInputBar(
                text = "",
                onTextChange = {},
                onSendClick = {},
                colors = LightAppColors
            )
        }
    )
}

@Preview(name = "ChatInputBar")
@Composable
private fun ChatInputBarDarkWithText() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true,
        content = {
            ChatInputBar(
                text = "Hey, how are you?",
                onTextChange = {},
                onSendClick = {},
                colors = DarkAppColors
            )
        }
    )
}
