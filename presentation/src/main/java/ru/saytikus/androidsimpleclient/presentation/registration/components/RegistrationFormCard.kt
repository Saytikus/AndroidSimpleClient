package ru.saytikus.androidsimpleclient.presentation.registration.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Badge
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.saytikus.androidsimpleclient.presentation.registration.RegistrationAction
import ru.saytikus.androidsimpleclient.presentation.registration.RegistrationState
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors

@Composable
fun RegistrationFormCard(
    state: RegistrationState,
    onAction: (RegistrationAction) -> Unit,
    colors: AppColors
) {
    val focusManager = LocalFocusManager.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = colors.cardBackground),
        border = BorderStroke(
            width = 1.dp,
            brush = Brush.horizontalGradient(
                colors = listOf(colors.cardBorderStart, colors.cardBorderEnd)
            )
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {

        Column {
            RegistrationField(
                label = "Username",
                value = state.username,
                onValueChange = { onAction(RegistrationAction.OnUsernameChange(it)) },
                placeholder = "john_doe",
                icon = Icons.Rounded.Person,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                colors = colors,
                error = state.usernameError
            )

            RegistrationFieldDivider(colors)

            RegistrationField(
                label = "Email",
                value = state.email,
                onValueChange = { onAction(RegistrationAction.OnEmailChange(it)) },
                placeholder = "john@example.com",
                icon = Icons.Rounded.Email,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                colors = colors,
                error = state.emailError
            )

            RegistrationFieldDivider(colors)

            RegistrationField(
                label = "Password",
                value = state.password,
                onValueChange = { onAction(RegistrationAction.OnPasswordChange(it)) },
                placeholder = "••••••••",
                icon = Icons.Rounded.Lock,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
                isPassword = true,
                colors = colors,
                error = state.passwordError
            )

            RegistrationFieldDivider(colors)

            RegistrationField(
                label = "Display name",
                value = state.displayName,
                onValueChange = { onAction(RegistrationAction.OnDisplayNameChange(it)) },
                placeholder = "John Doe",
                icon = Icons.Rounded.Badge,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                onDone = { focusManager.clearFocus() },
                colors = colors,
                error = state.displayNameError
            )
        }
    }
}

@Preview
@Composable
fun RegistrationFormCardLight() {
    AndroidSimpleClientTheme(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                RegistrationFormCard(
                    RegistrationState(),
                    {  },
                    LightAppColors
                )
            }
        }
    )
}

@Preview
@Composable
fun RegistrationFormCardDark() {
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
                RegistrationFormCard(
                    RegistrationState(),
                    {  },
                    DarkAppColors
                )
            }
        }
    )
}