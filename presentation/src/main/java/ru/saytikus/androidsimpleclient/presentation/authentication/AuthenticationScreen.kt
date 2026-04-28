package ru.saytikus.androidsimpleclient.presentation.authentication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.saytikus.androidsimpleclient.presentation.common.components.AppInputTextField
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationScreen(
    state: AuthenticationState,
    onAction: (AuthenticationAction) -> Unit
) {
    val c = ColorProvider.colors


    // TODO fix temp solution
    if(state.isAuthenticationSuccessfully) {
        onAction(AuthenticationAction.OnSignInSuccessfully)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = ColorProvider.backgroundBrush())
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),

            verticalArrangement = Arrangement.Center,

            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = "Login Screen",
                fontSize = 30.sp,
                color = c.textPrimary
            )

            Text(
                text = state.authenticationError ?: "",
                fontSize = 14.sp,
                color = c.errorColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .heightIn(min = 200.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = c.cardBackground),
                border = BorderStroke(
                    width = 1.dp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(c.cardBorderStart, c.cardBorderEnd)
                    )
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {

                AppInputTextField(
                    "Username or Email",
                    state.usernameOrEmail,
                    onValueChange = { onAction(AuthenticationAction.OnUsernameOrEmailChange(it)) },
                    placeholder = "username or email",
                    Icons.Rounded.Person,
                    KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    colors = c,
                    error = null
                )

                AppInputTextField(
                    "Password",
                    state.password,
                    onValueChange = { onAction(AuthenticationAction.onPasswordChange(it)) },
                    placeholder = "••••••••",
                    Icons.Rounded.Lock,
                    KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    onDone = { onAction(AuthenticationAction.OnSignInButtonClick) },
                    colors = c,
                    error = null
                )

                Button(
                    modifier = Modifier,
                    onClick = { onAction(AuthenticationAction.OnSignInButtonClick) },
                    colors = ButtonColors(
                        containerColor = c.accent,
                        contentColor = c.textPrimary,

                        disabledContainerColor = c.textSecondary,
                        disabledContentColor = c.textPrimary
                    ),
                    enabled = state.authenticationError == null &&
                              state.usernameOrEmail.isNotBlank() &&
                              state.password.isNotBlank()
                ) {
                    Text("Sign In")
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Do not have an account?",
                    color = c.textSecondary,
                    fontSize = 13.sp
                )
                Text(
                    text = "Register",
                    color = c.accent.copy(alpha = 0.85f),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        onAction(AuthenticationAction.OnRegisterButtonClick)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun AuthenticationScreenPreviewLight() {

    AndroidSimpleClientTheme(

        content = {
            AuthenticationScreen(
                state = AuthenticationState(
                    "TestProfileLogin",
                    "12345678"
                ),
                onAction = {}
            )
        }
    )
}

@Preview
@Composable
private fun AuthenticationScreenPreviewDark() {
    AndroidSimpleClientTheme(

        previewDarkTheme = true,

        content = {
            AuthenticationScreen(
                state = AuthenticationState(
                    "TestProfileLogin",
                    "12345678"
                ),
                onAction = {}
            )
        }
    )
}

@Preview
@Composable
private fun AuthenticationScreenPreviewLightError() {
    AndroidSimpleClientTheme(

        content = {
            AuthenticationScreen(
                state = AuthenticationState(
                    "TestProfileLogin",
                    "12345678",
                    "Bla-bla-bla-bla"
                ),
                onAction = {}
            )
        }
    )
}

@Preview
@Composable
private fun AuthenticationScreenPreviewDarkError() {
    AndroidSimpleClientTheme(

        previewDarkTheme = true,

        content = {
            AuthenticationScreen(
                state = AuthenticationState(
                    "TestProfileLogin",
                    "12345678",
                    "Bla-bla-bla-bla"
                ),
                onAction = {}
            )
        }
    )
}
