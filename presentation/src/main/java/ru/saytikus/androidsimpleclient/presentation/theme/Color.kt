package ru.saytikus.androidsimpleclient.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColors(
    val backgroundStart: Color,
    val backgroundMid: Color,
    val backgroundEnd: Color,
    val blob1: Color,
    val blob2: Color,
    val accent: Color,
    val cardBackground: Color,
    val cardBorderStart: Color,
    val cardBorderEnd: Color,
    val avatarBackground: Color,
    val avatarBorder: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val topBarBadgeBg: Color,
    val emptyIconBg: Color,
    val emptyIconTint: Color,
    val pullIndicatorContainer: Color
)

val DarkAppColors = AppColors(
    backgroundStart    = Color(0xFF1A1A2E),
    backgroundMid      = Color(0xFF16213E),
    backgroundEnd      = Color(0xFF0F3460),
    blob1              = Color(0xFFE94560).copy(alpha = 0.15f),
    blob2              = Color(0xFF0F3460).copy(alpha = 0.60f),
    accent             = Color(0xFFE94560),
    cardBackground     = Color.White.copy(alpha = 0.07f),
    cardBorderStart    = Color(0xFFE94560).copy(alpha = 0.50f),
    cardBorderEnd      = Color.White.copy(alpha = 0.05f),
    avatarBackground   = Color(0xFFE94560).copy(alpha = 0.20f),
    avatarBorder       = Color(0xFFE94560).copy(alpha = 0.40f),
    textPrimary        = Color(0xFFE9EAED),
    textSecondary      = Color(0xFFE9EAED).copy(alpha = 0.40f),
    topBarBadgeBg      = Color(0xFFE9EAED).copy(alpha = 0.10f),
    emptyIconBg        = Color(0xFFE9EAED).copy(alpha = 0.06f),
    emptyIconTint      = Color(0xFFE9EAED).copy(alpha = 0.30f),
    pullIndicatorContainer = Color(0xFF1A1A2E)
)

val LightAppColors = AppColors(
    backgroundStart    = Color(0xFFF2F9F4),
    backgroundMid      = Color(0xFFE3F2E8),
    backgroundEnd      = Color(0xFFCFE8D6),
    blob1              = Color(0xFF2DBE7A).copy(alpha = 0.12f),
    blob2              = Color(0xFF52A872).copy(alpha = 0.18f),
    accent             = Color(0xFF1FA85A),
    cardBackground     = Color.White.copy(alpha = 0.70f),
    cardBorderStart    = Color(0xFF1FA85A).copy(alpha = 0.22f),
    cardBorderEnd      = Color(0xFF52A872).copy(alpha = 0.10f),
    avatarBackground   = Color(0xFF1FA85A).copy(alpha = 0.10f),
    avatarBorder       = Color(0xFF1FA85A).copy(alpha = 0.22f),
    textPrimary        = Color(0xFF1A2E22),
    textSecondary      = Color(0xFF1A2E22).copy(alpha = 0.42f),
    topBarBadgeBg      = Color(0xFF1A2E22).copy(alpha = 0.07f),
    emptyIconBg        = Color(0xFF1A2E22).copy(alpha = 0.05f),
    emptyIconTint      = Color(0xFF1A2E22).copy(alpha = 0.22f),
    pullIndicatorContainer = Color(0xFFF2F9F4)
)

val LocalAppColors = staticCompositionLocalOf { DarkAppColors }

object ColorProvider {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current
}