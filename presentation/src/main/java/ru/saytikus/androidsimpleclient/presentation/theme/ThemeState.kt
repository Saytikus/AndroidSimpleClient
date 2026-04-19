package ru.saytikus.androidsimpleclient.presentation.theme

data class ThemeState(
    val isDark: Boolean,
    val toggle: () -> Unit
)
