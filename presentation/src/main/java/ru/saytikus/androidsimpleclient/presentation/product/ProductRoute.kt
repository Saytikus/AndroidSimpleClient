package ru.saytikus.androidsimpleclient.presentation.product

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue


import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun ProductRoute(
    onNavigate: (ProductNavigation) -> Unit,
    coordinator: ProductCoordinator = rememberProductCoordinator(onNavigate = onNavigate)
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle(ProductState())

    // UI Actions
    val actionsHandler: (ProductAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    // UI Rendering
    ProductScreen(
        state = uiState,
        onAction = actionsHandler
    )
}


