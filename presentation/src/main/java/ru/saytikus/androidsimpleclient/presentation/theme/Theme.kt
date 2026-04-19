package ru.saytikus.androidsimpleclient.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun AndroidSimpleClientTheme(
    previewDarkTheme: Boolean? = null,
    content: @Composable () -> Unit
) {
    val isSystemInDarkTheme = previewDarkTheme ?: isSystemInDarkTheme()

    val isDark = remember { mutableStateOf(isSystemInDarkTheme) }

    CompositionLocalProvider(
        LocalAppColors provides ThemeState(
            isDark.value,
            { isDark.value = !isDark.value }
        )
    ) {
        content()
    }
}