package ru.saytikus.androidsimpleclient.presentation.authentication

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
fun AuthenticationScreen(
    state: AuthenticationState,
    onAction: (AuthenticationAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Authentication")
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
@Preview(name = "Authentication")
private fun AuthenticationScreenPreview(
    @PreviewParameter(AuthenticationStatePreviewParameterProvider::class)
    state: AuthenticationState
) {
    AuthenticationScreen(
        state = state,
        onAction = {}
    )
}

/**
 * PreviewParameter Provider for AuthenticationScreen Preview
 * Add values to the sequence to see the preview in different states
 **/
class AuthenticationStatePreviewParameterProvider : PreviewParameterProvider<AuthenticationState> {
    override val values: Sequence<AuthenticationState>
        get() = sequenceOf(
            AuthenticationState(),
        )
}
