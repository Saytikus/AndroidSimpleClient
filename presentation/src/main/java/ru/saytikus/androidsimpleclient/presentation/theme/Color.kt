package ru.saytikus.androidsimpleclient.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val LightColorScheme = lightColorScheme(
    primaryFixed = Color(155,155,155,  255), // Button pressed
    primary = Color(185,185,185,  255), // Button
    tertiary = Color(195, 195, 195, 255), // Screen header
    secondary = Color(210, 210, 210, 255), // Card, Column, Row
    surface = Color(210, 210, 210, 255), // TopBar
    surfaceContainer = Color(210, 210, 210, 255), // BottomBar
    background = Color(235, 235, 235, 255), // Application

    onPrimary = Color(65, 65, 65, 255), // Text Button
    onTertiary = Color(65, 65, 65, 255), // Text screen header
    onSecondary = Color(65, 65, 65, 255), // Text BottomBar
    onSurface = Color(65, 65, 65, 255), // Text TopBar
    onBackground = Color(65, 65, 65, 255), // Text application

    error = Color(240, 19, 12, 255) // Error
)

val DarkColorScheme = darkColorScheme(
    primaryFixed = Color(100,100,100,  255), // Button pressed
    primary = Color(70,70,70,  255), // Button
    tertiary = Color(60, 60, 60, 255), // Screen header
    secondary = Color(45, 45, 45, 255), // Card, Column, Row
    surface = Color(45, 45, 45, 255), // TopBar
    surfaceContainer = Color(45, 45, 45, 255), // BottomBar
    background = Color(20, 20, 20, 255), // Application

    onPrimary = Color(190, 190, 190, 255), // Text Button
    onTertiary = Color(190, 190, 190, 255), // Text screen header
    onSecondary = Color(190, 190, 190, 255), // Text BottomBar
    onSurface = Color(190, 190, 190, 255), // Text TopBar
    onBackground = Color(190, 190, 190, 255), // Text application

    error = Color(200, 19, 12, 255) // Error
)