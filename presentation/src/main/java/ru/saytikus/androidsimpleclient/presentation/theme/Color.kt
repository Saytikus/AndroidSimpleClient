package ru.saytikus.androidsimpleclient.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
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
    val pullIndicatorContainer: Color,
    val avatar: Color,
    val errorColor: Color,
    val inputBarColor: Color,
    val barBackgroundStart: Color,
    val barBackgroundEnd: Color,

    val ownBubbleGradientStart: Color,
    val ownBubbleGradientEnd: Color,
    val ownBubbleBorder: Color,
    val ownBubbleTime: Color,
    val ownBubbleCheckRead: Color,
    val ownBubbleCheckUnread: Color,

    val otherBubbleBackgroundStart: Color,
    val otherBubbleBackgroundEnd: Color,
    val otherBubbleBorder: Color,
    val otherBubbleTime: Color
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
    emptyIconBg        = Color(0xFFE9EAED).copy(alpha = 0.13f),
    emptyIconTint      = Color(0xFFE9EAED).copy(alpha = 0.60f),
    pullIndicatorContainer = Color(0xFF1A1A2E),
    avatar = Color(0xFFE9EAED),
    errorColor = Color(0xFFFFB830),
    inputBarColor = Color.White.copy(alpha = 0.07f),
    barBackgroundStart = Color(0xFF1A1A2E).copy(alpha = 0.75f),
    barBackgroundEnd = Color(0xFF1A1A2E).copy(alpha = 0.95f),

    ownBubbleGradientStart = Color(0xFF643CB4).copy(alpha = 0.42f),
    ownBubbleGradientEnd   = Color(0xFF46288C).copy(alpha = 0.32f),
    ownBubbleBorder        = Color(0xFF825AD2).copy(alpha = 0.35f),
    ownBubbleTime          = Color(0xFFB496FF).copy(alpha = 0.65f),
    ownBubbleCheckRead     = Color(0xFFA078FF).copy(alpha = 0.85f),
    ownBubbleCheckUnread   = Color(0xFFA078FF).copy(alpha = 0.45f),

    otherBubbleBackgroundStart = Color(0xFFB42846).copy(alpha = 0.45f),
    otherBubbleBackgroundEnd  = Color(0xFF8C1E37).copy(alpha = 0.35f),
    otherBubbleBorder      = Color(0xFFE94560).copy(alpha = 0.28f),
    otherBubbleTime        = Color(0xFFE94560).copy(alpha = 0.55f)
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
    textSecondary      = Color(0xFF1A2E22).copy(alpha = 0.82f),
    topBarBadgeBg      = Color(0xFF1A2E22).copy(alpha = 0.07f),
    emptyIconBg        = Color(0xFF1A2E22).copy(alpha = 0.13f),
    emptyIconTint      = Color(0xFF1A2E22).copy(alpha = 0.6f),
    pullIndicatorContainer = Color(0xFFF2F9F4),
    avatar = Color(0xFFE9EAED),
    errorColor = Color(0xFFE94560).copy(alpha = 0.8f),
    inputBarColor = Color.White.copy(alpha = 0.70f),
    barBackgroundStart = Color(0xFFF2F9F4).copy(alpha = 0.15f),
    barBackgroundEnd = Color(0xFFF2F9F4).copy(alpha = 0.35f),

    ownBubbleGradientStart = Color(0xFF2D82DC).copy(alpha = 0.23f),
    ownBubbleGradientEnd   = Color(0xFF1E64BE).copy(alpha = 0.15f),
    ownBubbleBorder        = Color(0xFF2D82DC).copy(alpha = 0.30f),
    ownBubbleTime          = Color(0xFF2D82DC).copy(alpha = 0.65f),
    ownBubbleCheckRead     = Color(0xFF2D82DC).copy(alpha = 0.85f),
    ownBubbleCheckUnread   = Color(0xFF2D82DC).copy(alpha = 0.45f),

    otherBubbleBackgroundStart = Color(0xFF1FA85A).copy(alpha = 0.10f),
    otherBubbleBackgroundEnd  = Color(0xFF1FA85A).copy(alpha = 0.17f),
    otherBubbleBorder      = Color(0xFF1FA85A).copy(alpha = 0.30f),
    otherBubbleTime        = Color(0xFF1FA85A).copy(alpha = 0.65f)
)

val LocalAppColors = staticCompositionLocalOf { ThemeState(true) {  } }

object ColorProvider {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = if(LocalAppColors.current.isDark) DarkAppColors else LightAppColors

    @Composable
    fun backgroundBrush() = Brush.linearGradient(
        colors = listOf(
            colors.backgroundStart,
            colors.backgroundMid,
            colors.backgroundEnd)
    )
}