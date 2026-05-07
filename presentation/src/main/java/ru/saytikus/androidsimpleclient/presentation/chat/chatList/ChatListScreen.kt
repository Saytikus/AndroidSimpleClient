package ru.saytikus.androidsimpleclient.presentation.chat.chatList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.saytikus.androidsimpleclient.presentation.chat.chatList.components.AddChatButton
import ru.saytikus.androidsimpleclient.presentation.chat.chatList.components.ChatEmptyState
import ru.saytikus.androidsimpleclient.presentation.chat.chatList.components.ChatListComponentItem
import ru.saytikus.androidsimpleclient.presentation.chat.chatList.components.preview.previewChatList
import ru.saytikus.androidsimpleclient.presentation.common.displayItem.DisplayItemList
import ru.saytikus.androidsimpleclient.presentation.common.search.SearchBar
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalUuidApi::class)
@Composable
fun ChatListScreen(
    state: ChatListState,
    onAction: (ChatListAction) -> Unit
) {
    val c = ColorProvider.colors

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = c.textPrimary
                ),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(c.accent, c.accent.copy(alpha = 0.7f)),
                                        radius = 60f
                                    ),
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Chat,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        Text(
                            text = "Chats",
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            letterSpacing = 0.5.sp,
                            color = c.textPrimary
                        )
                    }
                },
                actions = {
                    Button(
                        onClick = { onAction(ChatListAction.OnSettingsButtonClick) },
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        colors = ButtonColors(
                            contentColor = ColorProvider.colors.textPrimary,
                            containerColor = Color.Transparent,
                            disabledContainerColor = ColorProvider.colors.backgroundStart,
                            disabledContentColor = ColorProvider.colors.backgroundStart
                        ),

                        ) {
                        Column(
                            modifier = Modifier
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally),
                                contentDescription = null,
                                tint = ColorProvider.colors.textPrimary
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = ColorProvider.backgroundBrush())
        ) {
            // Blob 1
            Box(
                modifier = Modifier
                    .offset(x = (-60).dp, y = 100.dp)
                    .size(300.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(c.blob1, Color.Transparent),
                            radius = 500f
                        ),
                        shape = CircleShape
                    )
            )
            // Blob 2
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 80.dp, y = 80.dp)
                    .size(350.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(c.blob2, Color.Transparent),
                            radius = 600f
                        ),
                        shape = CircleShape
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 12.dp)
            ) {
                Spacer(modifier = Modifier.height(4.dp))

                SearchBar(
                    query = state.searchQuery,
                    onQueryChange = {
                        onAction(ChatListAction.OnSearchQueryChange(it))
                    },
                    colors = c,
                    "Chats"
                )

                Spacer(modifier = Modifier.height(16.dp))

                val pullState = rememberPullToRefreshState()
                val listState = rememberLazyListState()

                PullToRefreshBox(
                    state = pullState,
                    isRefreshing = state.isRefreshing,
                    onRefresh = { onAction(ChatListAction.OnChatsRefresh) },
                    modifier = Modifier
                        .fillMaxSize(),
                    indicator = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            PullToRefreshDefaults.Indicator(
                                state = pullState,
                                isRefreshing = state.isRefreshing,
                                color = c.accent,
                                containerColor = c.pullIndicatorContainer
                            )
                        }
                    }
                ) {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        if (state.chats.isEmpty() && !state.isRefreshing) {
                            item {
                                ChatEmptyState(colors = c)
                            }
                        } else {
                            item {

                                DisplayItemList(
                                    items = state.chats,
                                    colors = c
                                ) { chat ->

                                    ChatListComponentItem(
                                        chat = chat,

                                        ownerProfileId = state.ownerProfileId,

                                        onClick = {
                                            onAction(
                                                ChatListAction.OnChatClick(
                                                    chat.chatId
                                                )
                                            )
                                        },

                                        colors = c
                                    )
                                }
                            }
                        }
                    }
                }
            }

            AddChatButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = (-10).dp, y = (-210).dp),

                onClick = { onAction(ChatListAction.OnAddChatButtonClick) },

                colors = c
            )
        }
    }
}

@OptIn(ExperimentalUuidApi::class)
@Composable
@Preview(name = "ChatList")
private fun ChatListScreenPreviewLightEmpty() {
    AndroidSimpleClientTheme(
        previewDarkTheme = false,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ChatListScreen(
                    ChatListState(),
                    { }
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Composable
@Preview(name = "ChatList")
private fun ChatListScreenPreviewDarkEmpty() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ChatListScreen(
                    ChatListState(),
                    { }
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Composable
@Preview(name = "ChatList")
private fun ChatListScreenPreviewLight() {
    AndroidSimpleClientTheme(
        previewDarkTheme = false,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ChatListScreen(
                    ChatListState(
                        chats = previewChatList
                    ),
                    { }
                )
            }
        }
    )
}

@OptIn(ExperimentalUuidApi::class)
@Composable
@Preview(name = "ChatList")
private fun ChatListScreenPreviewDark() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ChatListScreen(
                    ChatListState(
                        chats = previewChatList
                    ),
                    { }
                )
            }
        }
    )
}