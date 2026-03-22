package ru.saytikus.presentation.product

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.compose.viewmodel.koinViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class ProductCoordinator(
    val viewModel: ProductViewModel
) {
    val screenStateFlow = viewModel.stateFlow
    fun handle(action: ProductAction) {
        when (action) {
            ProductAction.OnClick -> { /* Handle action */
            }
        }
    }


}

@Composable
fun rememberProductCoordinator(
    viewModel: ProductViewModel= koinViewModel()
): ProductCoordinator {
    return remember(viewModel) {
        ProductCoordinator(
            viewModel = viewModel
        )
    }
}