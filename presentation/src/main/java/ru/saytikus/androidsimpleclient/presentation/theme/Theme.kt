package ru.saytikus.androidsimpleclient.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


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
        val view = LocalView.current
        if(!view.isInEditMode) {
            val window = (view.context as Activity).window

            val controller = WindowCompat.getInsetsController(window, view)

            controller.isAppearanceLightStatusBars = !isDark.value
            controller.isAppearanceLightNavigationBars = !isDark.value
        }


        content()
    }
}