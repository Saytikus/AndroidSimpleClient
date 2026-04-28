package ru.saytikus.androidsimpleclient.presentation.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
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


        }
    }
}

@Preview
@Composable
private fun AuthenticationScreenPreviewLight() {
    AuthenticationScreen(
        state = AuthenticationState(
            "TestProfileLogin",
            "12345678"
        ),
        onAction = {}
    )
}

@Preview
@Composable
private fun AuthenticationScreenPreviewDark() {
    AuthenticationScreen(
        state = AuthenticationState(
            "TestProfileLogin",
            "12345678"
        ),
        onAction = {}
    )
}

@Preview
@Composable
private fun AuthenticationScreenPreviewLightError() {
    AuthenticationScreen(
        state = AuthenticationState(
            "TestProfileLogin",
            "12345678",
            "Bla-bla-bla-bla"
        ),
        onAction = {}
    )
}

@Preview
@Composable
private fun AuthenticationScreenPreviewDarkError() {
    AuthenticationScreen(
        state = AuthenticationState(
            "TestProfileLogin",
            "12345678",
            "Bla-bla-bla-bla"
        ),
        onAction = {}
    )
}
