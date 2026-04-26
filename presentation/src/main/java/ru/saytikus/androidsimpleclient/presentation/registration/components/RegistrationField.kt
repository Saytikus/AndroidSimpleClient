package ru.saytikus.androidsimpleclient.presentation.registration.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.AppColors
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.DarkAppColors
import ru.saytikus.androidsimpleclient.presentation.theme.LightAppColors

@Composable
fun RegistrationField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: ImageVector,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    colors: AppColors,
    isPassword: Boolean = false,
    onDone: (() -> Unit)? = null,
    error: String?
) {
    var passwordVisible by remember { mutableStateOf(false) }

    // TODO move to colors
    // error colors
    val borderColor = if (error != null)
        colors.textPrimary.copy(alpha = 0.07f)
    else
        colors.textPrimary.copy(alpha = 0.07f)

    val iconTint = if (error != null)
        Color(0xFFE94560).copy(alpha = 0.75f)
    else
        colors.accent.copy(alpha = 0.65f)


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if(error != null) {
                    colors.errorColor.copy(0.1f)
                } else {
                    colors.backgroundStart
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // field icons
        Box(
            modifier = Modifier
                .background(
                    brush =
                        Brush.radialGradient(
                            colors = listOf(
                                colors.accent.copy(alpha = 0.25f),
                                colors.accent.copy(alpha = 0.12f)
                            ),
                            radius = 100f
                        )
                )
                .padding(horizontal = 14.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colors.accent.copy(alpha = 0.65f),
                modifier = Modifier.size(15.dp)
            )
        }

        // vertical separator
        Box(
            modifier = Modifier
                .width(1.dp)
                .height(36.dp)
                .background(colors.textPrimary.copy(alpha = 0.07f))
        )

        // label + text field
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {

            Text(
                text = label.uppercase(),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = colors.accent.copy(alpha = 0.8f)
            )

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = colors.textPrimary
                ),
                singleLine = true,
                visualTransformation = if (isPassword && !passwordVisible)
                    PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction,
                    autoCorrectEnabled = false
                ),
                keyboardActions = KeyboardActions(
                    onDone = { onDone?.invoke() }
                ),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            fontSize = 14.sp,
                            color = colors.textSecondary
                        )
                    }
                    innerTextField()
                }
            )
        }

        // password visibility icon
        if (isPassword) {

            Box(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(28.dp)
                    .background(
                        color = colors.textPrimary.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { passwordVisible = !passwordVisible },
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = if (passwordVisible)
                        Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility,
                    contentDescription = null,
                    tint = colors.textSecondary,
                    modifier = Modifier.size(14.dp)
                )
            }
        }

        // error text under text field
        AnimatedVisibility(
            visible = error != null,
            enter = fadeIn(tween(200)) + expandVertically(tween(200)),
            exit = fadeOut(tween(150)) + shrinkVertically(tween(150))
        ) {
            Row(
                modifier = Modifier.padding(start = 44.dp, top = 4.dp, bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.ErrorOutline,
                    contentDescription = null,
                    tint = colors.errorColor,
                    modifier = Modifier.size(12.dp)
                )
                Text(
                    text = error ?: "",
                    fontSize = 11.sp,
                    color = colors.errorColor
                )
            }
        }
    }
}

@Preview
@Composable
fun RegistrationFieldLightSuccess() {
    AndroidSimpleClientTheme(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                RegistrationField(
                    "Email",
                    "example@email.com",
                    { },
                    "input data here",
                    Icons.Rounded.Email,
                    KeyboardType.Email,
                    ImeAction.Next,
                    LightAppColors,
                    false,
                    { },
                    null
                )
            }
        }
    )
}

@Preview
@Composable
fun RegistrationFieldDarkSuccess() {
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
                RegistrationField(
                    "Email",
                    "example@email.com",
                    { },
                    "input data here",
                    Icons.Rounded.Email,
                    KeyboardType.Email,
                    ImeAction.Next,
                    DarkAppColors,
                    false,
                    { },
                    null
                )
            }
        }
    )
}

@Preview
@Composable
fun RegistrationFieldLightError() {
    AndroidSimpleClientTheme(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = ColorProvider.backgroundBrush())
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                RegistrationField(
                    "Email",
                    "example@email.com",
                    { },
                    "input data here",
                    Icons.Rounded.Email,
                    KeyboardType.Email,
                    ImeAction.Next,
                    LightAppColors,
                    false,
                    { },
                    "Email is incorrect."
                )
            }
        }
    )
}

@Preview
@Composable
fun RegistrationFieldDarkError() {
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
                RegistrationField(
                    "Email",
                    "example@email.com",
                    { },
                    "input data here",
                    Icons.Rounded.Email,
                    KeyboardType.Email,
                    ImeAction.Next,
                    DarkAppColors,
                    false,
                    { },
                    "Email is incorrect."
                )
            }
        }
    )
}