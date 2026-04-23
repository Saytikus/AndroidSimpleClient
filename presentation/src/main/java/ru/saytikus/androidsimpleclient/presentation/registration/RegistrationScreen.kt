package ru.saytikus.androidsimpleclient.presentation.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.tooling.preview.PreviewParameter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    state: RegistrationState,
    onAction: (RegistrationAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Registration")
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
@Preview(name = "Registration")
private fun RegistrationScreenPreview(
    @PreviewParameter(RegistrationStatePreviewParameterProvider::class)
    state: RegistrationState
) {
    RegistrationScreen(
        state = state,
        onAction = {}
    )
}

/**
 * PreviewParameter Provider for RegistrationScreen Preview
 * Add values to the sequence to see the preview in different states
 **/
class RegistrationStatePreviewParameterProvider : PreviewParameterProvider<RegistrationState> {
    override val values: Sequence<RegistrationState>
        get() = sequenceOf(
            RegistrationState(),
        )
}
