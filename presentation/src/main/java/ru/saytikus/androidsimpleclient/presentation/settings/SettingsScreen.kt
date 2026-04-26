package ru.saytikus.androidsimpleclient.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.saytikus.androidsimpleclient.presentation.settings.components.ServerAddressCard
import ru.saytikus.androidsimpleclient.presentation.settings.components.SettingsSectionHeader
import ru.saytikus.androidsimpleclient.presentation.settings.components.ThemeToggleCard
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.theme.LocalAppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    state: SettingsState,
    onAction: (SettingsAction) -> Unit
) {
    val c = ColorProvider.colors

    val topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    val toggleTheme = LocalAppColors.current.toggle

    Scaffold(
        modifier = Modifier
            .nestedScroll(topBarScrollBehavior.nestedScrollConnection),
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = c.textPrimary,
                ),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(c.accent, c.accent.copy(alpha = 0.7f)),
                                        radius = 60f
                                    ),
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Settings,
                                contentDescription = null,
                                tint = c.avatar,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Text(
                            text = "Settings",
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            letterSpacing = 0.5.sp,
                            color = c.textPrimary
                        )
                    }
                },
                scrollBehavior = topBarScrollBehavior
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = ColorProvider.backgroundBrush())
        ) {
            // Blob 1
            Box(
                modifier = Modifier
                    .offset(x = (-60).dp, y = 100.dp)
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
                    .offset(x = 80.dp, y = 80.dp)
                    .size(350.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(c.blob2, Color.Transparent),
                            radius = 600f
                        ),
                        shape = CircleShape
                    )
            )



            LazyColumn(

            ) {
                item {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        Spacer(modifier = Modifier.height(4.dp))

                        SettingsSectionHeader(title = "Server connection")

                        ServerAddressCard(
                            newHostAddress = state.newHostAddress,
                            savedHostAddress = state.settings.responseServerHostAddress,
                            onHostAddressChanged = { onAction(SettingsAction.OnHostAddressChanged(it)) },
                            onHostAddressSaveButtonClicked = { onAction(SettingsAction.OnHostAddressSaveButtonClicked(it)) },
                            colors = c
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        SettingsSectionHeader(title = "Appearance")

                        ThemeToggleCard(
                            LocalAppColors.current.isDark,
                            onToggle = {
                                toggleTheme()
                            },
                            colors = c
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

        }
    }
}

@Composable
@Preview(name = "SettingsLight")
private fun SettingsScreenPreviewLight(
    @PreviewParameter(SettingsStatePreviewParameterProvider::class)
    state: SettingsState
) {
    AndroidSimpleClientTheme {
        SettingsScreen(
            state = state,
            onAction = {}
        )
    }
}

@Composable
@Preview(name = "SettingsDark")
private fun SettingsScreenPreviewDark(
    @PreviewParameter(SettingsStatePreviewParameterProvider::class)
    state: SettingsState
) {
    AndroidSimpleClientTheme(
        previewDarkTheme = true
    ) {
        SettingsScreen(
            state = state,
            onAction = {}
        )
    }
}

/**
 * PreviewParameter Provider for SettingsScreen Preview
 * Add values to the sequence to see the preview in different states
 **/
class SettingsStatePreviewParameterProvider : PreviewParameterProvider<SettingsState> {
    override val values: Sequence<SettingsState>
        get() = sequenceOf(
            SettingsState(),
        )
}
