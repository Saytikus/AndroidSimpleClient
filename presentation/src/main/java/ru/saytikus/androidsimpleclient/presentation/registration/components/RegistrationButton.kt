package ru.saytikus.androidsimpleclient.presentation.registration.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors

@Composable
fun RegistrationButton(
    onClick: () -> Unit,
    colors: AppColors,
    isEnabled: Boolean
) {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessHigh
        ),
        label = "scale"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(52.dp)
            .scale(scale)

            .background(
                brush =
                if(!isEnabled) {
                    Brush.linearGradient(
                        colors = listOf(
                            colors.textSecondary.copy(0.5f),
                            colors.textSecondary.copy(0.5f)
                        )
                    )

                } else {
                    Brush.linearGradient(
                        colors = listOf(
                            colors.accent.copy(alpha = 0.85f),
                            colors.accent.copy(alpha = 0.55f)
                        )
                    )
                },

                shape = RoundedCornerShape(14.dp)
            )

            .border(
                1.dp,
                color =
                    if(!isEnabled) {
                        colors.textSecondary.copy(0.25f)

                    } else {
                        colors.accent.copy(alpha = 0.5f)
                    },
                RoundedCornerShape(14.dp)
            )

            .pointerInput(isEnabled) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        tryAwaitRelease()
                        isPressed = false
                    },
                    onTap = { onClick() }
                )
            },

        contentAlignment = Alignment.Center
    ) {

        Text(
            text = "Create account",
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.3.sp
        )
    }
}


@Preview
@Composable
fun RegistrationButtonLight() {
    AndroidSimpleClientTheme(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                RegistrationButton(
                    {  },
                    LightAppColors,
                    true
                )
            }
        }
    )
}

@Preview
@Composable
fun RegistrationButtonDark() {
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
                RegistrationButton(
                    {  },
                    DarkAppColors,
                    true
                )
            }
        }
    )
}

@Preview
@Composable
fun RegistrationButtonLightDisabled() {
    AndroidSimpleClientTheme(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                RegistrationButton(
                    {  },
                    LightAppColors,
                    false
                )
            }
        }
    )
}

@Preview
@Composable
fun RegistrationButtonDarkDisabled() {
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
                RegistrationButton(
                    {  },
                    DarkAppColors,
                    false
                )
            }
        }
    )
}