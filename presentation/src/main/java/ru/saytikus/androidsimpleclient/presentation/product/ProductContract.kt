package ru.saytikus.androidsimpleclient.presentation.product

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable
import ru.saytikus.androidsimpleclient.domain.product.Product


/**
 * Object used for a type safe destination to a Product route
 */
@Serializable
object ProductDestination

/**
 * UI State that represents ProductScreen
 **/
@Immutable
data class ProductState(
    val products: List<Product> = emptyList(),
    val isProductRefreshActive: Boolean = false,
    val isProductRefresh: Boolean = false
)

/**
 * UI navigation event
 */
sealed interface ProductNavigation {
    data object Settings: ProductNavigation
}

/**
 * Product Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface ProductAction {
    data object OnProductRefreshRequested : ProductAction

    data object onSettingsButtonClicked : ProductAction
}

