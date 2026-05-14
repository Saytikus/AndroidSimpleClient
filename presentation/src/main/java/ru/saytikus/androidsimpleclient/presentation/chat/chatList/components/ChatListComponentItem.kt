package ru.saytikus.androidsimpleclient.presentation.chat.chatList.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import ru.saytikus.androidsimpleclient.domain.chat.model.ChatListItem
import ru.saytikus.androidsimpleclient.presentation.R
import ru.saytikus.androidsimpleclient.presentation.chat.chatList.components.preview.previewChatList
import ru.saytikus.androidsimpleclient.presentation.chat.chatList.components.preview.previewOwnerUuid
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Composable
fun ChatListComponentItem(
    chat: ChatListItem,
    ownerProfileId: Uuid,
    onClick: () -> Unit,
    colors: AppColors
) {

    val nameColor = colors.textPrimary
    val previewColor = colors.textPrimary.copy(alpha = 0.75f)
    val badgeContainerColor = colors.accent

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

            val chatIconResource =
                if(chat.companionName == null) R.drawable.groups_24px
                else R.drawable.person_24px

            Icon(
                painter = painterResource(chatIconResource), // TODO: chat icon
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
                val chatTitle: String =
                    if (chat.title != null)                 chat.title!!
                    else if(chat.companionName != null)     chat.companionName!!
                    else "Chat Title"

                Text(
                    text = chatTitle,
                    color = nameColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false)
                )

                chat.lastMessage?.createdAt?.let {
                    Text(
                        text = it,
                        color = previewColor,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(start = 6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(3.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    val prefixText = when {
                        chat.lastMessage?.senderId == ownerProfileId -> ""
                        chat.lastMessage?.senderName != null -> "${chat.lastMessage?.senderName}: "
                        else -> null
                    }

                    if (prefixText != null) {
                        Text(
                            text = prefixText,
                            color = colors.accent,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            maxLines = 1
                        )
                    }

                    chat.lastMessage?.text?.let {
                        Text(
                            text = it,
                            color = previewColor,
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                if (chat.unreadCount > 0) {

                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        modifier = Modifier
                            .background(
                                color = badgeContainerColor,
                                shape = RoundedCornerShape(9.dp)
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {

                        Text(
                            text = if (chat.unreadCount > 99) "99+" else "${chat.unreadCount}",
                            color = colors.avatar,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}



@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun ChatListComponentItemLightFirst() {
    AndroidSimpleClientTheme(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                ChatListComponentItem(
                    previewChatList[0],
                    Uuid.generateV4(),
                    {  },
                    LightAppColors,
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun ChatListComponentItemDarkFirst() {
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
                ChatListComponentItem(
                    previewChatList[0],
                    Uuid.generateV4(),
                    {  },
                    DarkAppColors,
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun ChatListComponentItemLightSecond() {
    AndroidSimpleClientTheme(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                ChatListComponentItem(
                    previewChatList[1],
                    Uuid.generateV4(),
                    {  },
                    LightAppColors,
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun ChatListComponentItemDarkSecond() {
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
                ChatListComponentItem(
                    previewChatList[1],
                    Uuid.generateV4(),
                    {  },
                    DarkAppColors,
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun ChatListComponentItemLightThird() {
    AndroidSimpleClientTheme(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                ChatListComponentItem(
                    previewChatList[2],
                    Uuid.generateV4(),
                    {  },
                    LightAppColors,
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun ChatListComponentItemDarkThird() {
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
                ChatListComponentItem(
                    previewChatList[2],
                    Uuid.generateV4(),
                    {  },
                    DarkAppColors,
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun ChatListComponentItemLightFourth() {
    AndroidSimpleClientTheme(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                ChatListComponentItem(
                    previewChatList[3],
                    Uuid.generateV4(),
                    {  },
                    LightAppColors,
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun ChatListComponentItemDarkFourth() {
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
                ChatListComponentItem(
                    previewChatList[3],
                    Uuid.generateV4(),
                    {  },
                    DarkAppColors,
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun ChatListComponentItemLightFifth() {
    AndroidSimpleClientTheme(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {

                val ownerId = Uuid.generateV4()
                ChatListComponentItem(
                    previewChatList[4],
                    ownerId,
                    {  },
                    LightAppColors,
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
fun ChatListComponentItemDarkFifth() {
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
                ChatListComponentItem(
                    previewChatList[4],
                    previewOwnerUuid,
                    {  },
                    DarkAppColors,
                )
            }
        }
    )
}