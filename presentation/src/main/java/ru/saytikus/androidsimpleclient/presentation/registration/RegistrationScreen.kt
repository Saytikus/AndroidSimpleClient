package ru.saytikus.androidsimpleclient.presentation.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.saytikus.androidsimpleclient.presentation.BuildConfig
import ru.saytikus.androidsimpleclient.presentation.registration.components.RegistrationButton
import ru.saytikus.androidsimpleclient.presentation.registration.components.RegistrationFormCard
import ru.saytikus.androidsimpleclient.presentation.registration.components.RegistrationIconZone
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    state: RegistrationState,
    onAction: (RegistrationAction) -> Unit
) {
    val c = ColorProvider.colors

    // registration end with sucessfully operation
    if(state.isRegistrationSuccessfully) {
        onAction(RegistrationAction.OnRegistrationSuccessfully)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = ColorProvider.backgroundBrush())
    ) {
        // Blob 1
        Box(
            modifier = Modifier
                .offset(x = (-70).dp, y = 40.dp)
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
                .offset(x = 70.dp, y = 60.dp)
                .size(360.dp)
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
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            RegistrationIconZone(colors = c)

            Spacer(modifier = Modifier.height(28.dp))

            RegistrationFormCard(
                state = state,
                onAction = onAction,
                colors = c
            )

            Spacer(modifier = Modifier.height(16.dp))

            RegistrationButton(
                onClick = { onAction(RegistrationAction.OnRegistrationSubmit) },
                colors = c,
                isEnabled = state.usernameError == null &&
                        state.emailError == null &&
                        state.passwordError == null &&
                        state.displayNameError == null &&
                        state.username.isNotBlank() &&
                        state.email.isNotBlank() &&
                        state.password.isNotBlank() &&
                        state.displayName.isNotBlank()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account?",
                    color = c.textSecondary,
                    fontSize = 13.sp
                )
                Text(
                    text = "Sign in",
                    color = c.accent.copy(alpha = 0.85f),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        onAction(RegistrationAction.OnSignInClick)
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))


            // debug button to navigate to settings for set server ip
            if(BuildConfig.DEBUG) {
                Box(modifier = Modifier
                    .clickable(
                        onClick = { onAction(RegistrationAction.DEBUG_onSettingsButtonClick) }
                    )
                    .background(color = ColorProvider.colors.accent.copy(0.5f))
                ) {
                    Column(
                        modifier = Modifier
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .size(48.dp),
                            contentDescription = null,
                            tint = ColorProvider.colors.textPrimary
                        )

                        Text(
                            "DEBUG BUTTON",
                            color = ColorProvider.colors.textPrimary)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RegistrationScreenLightEmpty() {
    AndroidSimpleClientTheme(
        content = {
            RegistrationScreen(
                RegistrationState(),
                { }
            )
        }
    )
}

@Preview
@Composable
fun RegistrationScreenDarkEmpty() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true,
        content = {
            RegistrationScreen(
                RegistrationState(),
                { }
            )
        }
    )
}

@Preview
@Composable
fun RegistrationScreenLightCorrectData() {
    AndroidSimpleClientTheme(
        content = {
            RegistrationScreen(
                RegistrationState(
                    username = "Kirill",
                    email = "kirill@gmail.com",
                    password = "123456",
                    displayName = "Kirill"
                ),
                { }
            )
        }
    )
}

@Preview
@Composable
fun RegistrationScreenDarkCorrectData() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true,
        content = {
            RegistrationScreen(
                RegistrationState(
                    username = "Kirill",
                    email = "kirill@gmail.com",
                    password = "123456",
                    displayName = "Kirill"
                ),
                { }
            )
        }
    )
}
