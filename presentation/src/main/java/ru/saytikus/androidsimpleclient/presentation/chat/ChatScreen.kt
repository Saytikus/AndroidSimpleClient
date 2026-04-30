package ru.saytikus.androidsimpleclient.presentation.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.SmallTopBar

@Composable
fun ChatScreen(
    state: ChatState,
    onAction: (ChatAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Chat")
                }
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(innerPadding)
        ) {
            // TODO Add UI content here
        }
    }
}

@Composable
@Preview(name = "Chat")
private fun ChatScreenPreview(
    @PreviewParameter(ChatStatePreviewParameterProvider::class)
    state: ChatState
) {
    ChatScreen(
        state = state,
        onAction = {}
    )
}

/**
 * PreviewParameter Provider for ChatScreen Preview
 * Add values to the sequence to see the preview in different states
 **/
class ChatStatePreviewParameterProvider : PreviewParameterProvider<ChatState> {
    override val values: Sequence<ChatState>
        get() = sequenceOf(
            ChatState(),
        )
}
