package ru.saytikus.androidsimpleclient.presentation.settings.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.saytikus.androidsimpleclient.presentation.common.cardBorderStroke
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors

@Composable
fun ServerAddressCard(
    newHostAddress: String,
    savedHostAddress: String,
    onHostAddressChanged: (String) -> Unit,
    onHostAddressSaveButtonClicked: (String) -> Unit,
    colors: AppColors
) {
    val localFocusManager = LocalFocusManager.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = colors.cardBackground),
        border = cardBorderStroke(),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "HOST ADDRESS",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.8.sp,
                color = colors.accent.copy(alpha = 0.9f)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Поле ввода с префиксом и суффиксом
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 1.dp,
                            color = colors.textPrimary.copy(alpha = 0.10f),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(colors.textPrimary.copy(alpha = 0.05f))
                            .padding(horizontal = 10.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = "http://",
                            fontSize = 13.sp,
                            fontFamily = FontFamily.Monospace,
                            color = colors.accent.copy(alpha = 0.7f)
                        )
                    }

                    BasicTextField(
                        value = newHostAddress,
                        onValueChange = onHostAddressChanged,
                        modifier = Modifier
                            .weight(1f)
                            .background(colors.textPrimary.copy(alpha = 0.03f))
                            .padding(horizontal = 10.dp, vertical = 12.dp),
                        textStyle = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily.Monospace,
                            color = colors.textPrimary
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Uri,
                            imeAction = ImeAction.Done,
                            autoCorrectEnabled = false
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                localFocusManager.clearFocus()
                                onHostAddressSaveButtonClicked(newHostAddress)
                            }
                        ),
                        decorationBox = { innerTextField ->
                            Box {
                                if (newHostAddress.isEmpty()) {
                                    Text(
                                        text = "input ipv4",
                                        fontSize = 12.sp,
                                        fontFamily = FontFamily.Monospace,
                                        color = colors.textSecondary
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )

                    Box(
                        modifier = Modifier
                            .background(colors.textPrimary.copy(alpha = 0.05f))
                            .padding(horizontal = 10.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = ":8080",
                            fontSize = 13.sp,
                            fontFamily = FontFamily.Monospace,
                            color = colors.accent.copy(alpha = 0.7f)
                        )
                    }
                }

                // Кнопка сохранения
                var isPressed by remember { mutableStateOf(false) }

                val scale by animateFloatAsState(
                    targetValue = if (isPressed) 0.88f else 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessHigh
                    ),
                    label = "scale"
                )
                val backgroundAlpha by animateFloatAsState(
                    targetValue = if (isPressed) 0.55f else 0.25f,
                    animationSpec = tween(100),
                    label = "bgAlpha"
                )
                val borderAlpha by animateFloatAsState(
                    targetValue = if (isPressed) 0.8f else 0.35f,
                    animationSpec = tween(100),
                    label = "borderAlpha"
                )

                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .scale(scale)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    colors.accent.copy(alpha = backgroundAlpha),
                                    colors.accent.copy(alpha = backgroundAlpha * 0.3f)
                                ),
                                radius = 80f
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            1.dp,
                            colors.accent.copy(alpha = borderAlpha),
                            RoundedCornerShape(12.dp)
                        )
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = {
                                    isPressed = true
                                    tryAwaitRelease()
                                    isPressed = false
                                },
                                onTap = {
                                    localFocusManager.clearFocus()
                                    onHostAddressSaveButtonClicked(newHostAddress)
                                }
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    val iconOffset by animateFloatAsState(
                        targetValue = if (isPressed) 3f else 0f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessHigh
                        ),
                        label = "iconOffset"
                    )

                    Icon(
                        imageVector = Icons.Rounded.ChevronRight,
                        contentDescription = "Save host",
                        tint = colors.accent,
                        modifier = Modifier
                            .size(22.dp)
                            .offset(x = iconOffset.dp) // стрелка "прыгает" вправо при нажатии
                    )
                }
            }

            Text(
                text = "current: http://${savedHostAddress.ifEmpty { "…" }}:8080/",
                fontSize = 11.sp,
                fontFamily = FontFamily.Monospace,
                color = colors.textSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview(name = "Light", showBackground = true, backgroundColor = 0xFFF2F9F4)
@Composable
private fun ServerAddressCardLightPreview() {
    AndroidSimpleClientTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = ColorProvider.backgroundBrush())
                .padding(16.dp)
        ) {
            ServerAddressCard(
                newHostAddress = "192.168.1.100",
                savedHostAddress = "0.0.0.0",
                onHostAddressChanged = {},
                onHostAddressSaveButtonClicked = {},
                colors = LightAppColors
            )
        }
    }
}

@Preview(name = "Dark", showBackground = true, backgroundColor = 0xFF1A1A2E)
@Composable
private fun ServerAddressCardDarkPreview() {
    AndroidSimpleClientTheme(
        previewDarkTheme = true
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = ColorProvider.backgroundBrush())
                .padding(16.dp)
        ) {
            ServerAddressCard(
                newHostAddress = "",
                savedHostAddress = "192.168.0.0",
                onHostAddressChanged = {},
                onHostAddressSaveButtonClicked = {},
                colors = DarkAppColors
            )
        }
    }
}
