package ru.saytikus.androidsimpleclient.presentation.chat.createChat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.saytikus.androidsimpleclient.domain.common.profileSearch.answers.ProfileSearchListItem
import ru.saytikus.androidsimpleclient.presentation.chat.createChat.components.CreateChatDisplayItem
import ru.saytikus.androidsimpleclient.presentation.core.ui.search.SearchContent
import ru.saytikus.androidsimpleclient.presentation.core.ui.search.SearchState
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalMaterial3Api::class, ExperimentalUuidApi::class)
@Composable
fun CreateChatScreen(
    state: CreateChatState,
    onAction: (CreateChatAction) -> Unit
) {

    val colors = ColorProvider.colors


    // create chat successfully end
    if(state.isCreateChatSuccessfully) {
        onAction(CreateChatAction.OnCreateChatSuccessfully)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                title = {
                    Text("Create chat")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = colors.textPrimary
                )
            )
        }

    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = ColorProvider.backgroundBrush())
                .padding(innerPadding)
        ) {

            // Blob 1
            Box(
                modifier = Modifier
                    .offset(x = (-60).dp, y = 100.dp)
                    .size(300.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(colors.blob1, Color.Transparent),
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
                            colors = listOf(colors.blob2, Color.Transparent),
                            radius = 600f
                        ),
                        shape = CircleShape
                    )
            )

            SearchContent(
                modifier = Modifier
                    .padding(20.dp),
                state = state.searchState,
                queryPlaceholder = "Users",
                onAction = { onAction(CreateChatAction.OnSearchAction(it)) },
                aboveListContent = {
                    Spacer(
                        modifier = Modifier
                            .padding(10.dp)
                    )
                } // TODO aad component for select chat type
            ) { item ->

                CreateChatDisplayItem(
                    profile = item,
                    onClick = { onAction(CreateChatAction.OnProfileClick(item.userId)) },
                    colors = colors
                )
            }
        }
    }
}

@OptIn(ExperimentalUuidApi::class)
val previewProfiles = listOf(
    ProfileSearchListItem(
        userId = Uuid.random(),
        username = "alex",
        displayName = "Alex Johnson",
        avatarUrl = null
    ),

    ProfileSearchListItem(
        userId = Uuid.random(),
        username = "kate",
        displayName = "Kate Smith",
        avatarUrl = null
    )
)

@OptIn(ExperimentalUuidApi::class)
@Preview(name = "CreateChat Light")
@Composable
private fun CreateChatScreenPreviewLight() {

    AndroidSimpleClientTheme(
        previewDarkTheme = false
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            CreateChatScreen(
                state = CreateChatState(
                    searchState = SearchState(
                        items = previewProfiles
                    )
                ),
                onAction = { }
            )
        }
    }
}

@OptIn(ExperimentalUuidApi::class)
@Preview(name = "CreateChat Dark")
@Composable
private fun CreateChatScreenPreviewDark() {

    AndroidSimpleClientTheme(
        previewDarkTheme = true
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            CreateChatScreen(
                state = CreateChatState(
                    searchState = SearchState(
                        items = previewProfiles
                    )
                ),
                onAction = { }
            )
        }
    }
}

@OptIn(ExperimentalUuidApi::class)
@Preview(name = "CreateChat Empty Light")
@Composable
private fun CreateChatScreenPreviewEmptyLight() {

    AndroidSimpleClientTheme(
        previewDarkTheme = false
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            CreateChatScreen(
                state = CreateChatState(),
                onAction = { }
            )
        }
    }
}

@OptIn(ExperimentalUuidApi::class)
@Preview(name = "CreateChat Empty Dark")
@Composable
private fun CreateChatScreenPreviewEmptyDark() {

    AndroidSimpleClientTheme(
        previewDarkTheme = true
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            CreateChatScreen(
                state = CreateChatState(),
                onAction = { }
            )
        }
    }
}