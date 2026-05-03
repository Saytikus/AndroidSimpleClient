package ru.saytikus.androidsimpleclient.presentation.chatList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors

@Composable
fun ChatListSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    colors: AppColors
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colors.cardBackground,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                1.dp,
                colors.textPrimary.copy(alpha = 0.07f),
                RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = null,
            tint = colors.textSecondary,
            modifier = Modifier.size(16.dp)
        )

        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.weight(1f),
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = colors.textPrimary
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                autoCorrectEnabled = false
            ),
            decorationBox = { innerTextField ->
                if (query.isEmpty()) {
                    Text(
                        text = "Search...",
                        fontSize = 14.sp,
                        color = colors.textSecondary
                    )
                }
                innerTextField()
            },
            cursorBrush = SolidColor(colors.textPrimary)
        )

        if (query.isNotEmpty()) {

            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Clear",
                tint = colors.textSecondary,
                modifier = Modifier
                    .size(16.dp)
                    .clickable { onQueryChange("") }
            )
        }
    }
}


@Preview
@Composable
fun ChatListSearchBarLight() {
    AndroidSimpleClientTheme(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                ChatListSearchBar(
                    "",
                    {  },
                    LightAppColors,
                )
            }
        }
    )
}

@Preview
@Composable
fun ChatListSearchBarDark() {
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
                ChatListSearchBar(
                    "",
                    {  },
                    DarkAppColors
                )
            }
        }
    )
}