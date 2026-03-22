package ru.saytikus.presentation.product

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember


import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun ProductRoute(
    coordinator: ProductCoordinator = rememberProductCoordinator()
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


