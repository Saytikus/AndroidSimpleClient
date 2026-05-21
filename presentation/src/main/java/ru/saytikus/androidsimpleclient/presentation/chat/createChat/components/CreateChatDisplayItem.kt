package ru.saytikus.androidsimpleclient.presentation.chat.createChat.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.saytikus.androidsimpleclient.domain.core.profileSearch.answers.ProfileSearchListItem
import ru.saytikus.androidsimpleclient.presentation.R
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Composable
fun CreateChatDisplayItem(
    profile: ProfileSearchListItem,
    onClick: () -> Unit,
    colors: AppColors
) {

    val nameColor = colors.textPrimary

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Аватар
        Box(
            modifier = Modifier
                .size(48.dp)
                .border(
                    1.dp,
                    colors.accent,
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = painterResource(R.drawable.person_24px), // TODO: profile icon
                contentDescription = null,
                tint = colors.accent,
                modifier = Modifier.size(36.dp)
            )
        }

        // Контент
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = profile.displayName ?: "Unknown name",
                    color = nameColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false)
                )

            }

            Spacer(modifier = Modifier.height(3.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = profile.username?.let { "@$it" }
                        ?: "Unknown username", // TODO: add highlight username part from query
                    color = colors.accent,
                    fontSize = 11.sp
                )
            }
        }
    }
}


@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun CreateChatDisplayItemLightFirst() {

    AndroidSimpleClientTheme(
        content = {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {

                CreateChatDisplayItem(
                    ProfileSearchListItem(
                        Uuid.generateV4(),
                        "TestUsername1",
                        "Test Name 1",
                        null
                    ),
                    { },
                    LightAppColors,
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun CreateChatDisplayItemDarkFirst() {

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

                CreateChatDisplayItem(
                    ProfileSearchListItem(
                        Uuid.generateV4(),
                        "TestUsername1",
                        "Test Name 1",
                        null
                    ),
                    { },
                    DarkAppColors,
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun CreateChatDisplayItemLightSecond() {

    AndroidSimpleClientTheme(
        content = {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {

                CreateChatDisplayItem(
                    ProfileSearchListItem(
                        Uuid.generateV4(),
                        null,
                        null,
                        null
                    ),
                    { },
                    LightAppColors,
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun CreateChatDisplayItemDarkSecond() {

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

                CreateChatDisplayItem(
                    ProfileSearchListItem(
                        Uuid.generateV4(),
                        null,
                        null,
                        null
                    ),
                    { },
                    DarkAppColors,
                )
            }
        }
    )
}