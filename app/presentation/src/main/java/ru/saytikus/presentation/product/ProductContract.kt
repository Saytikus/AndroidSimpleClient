package ru.saytikus.presentation.product

import kotlinx.serialization.Serializable


/**
 * Object used for a type safe destination to a Product route
 */
@Serializable
object ProductDestination

/**
 * UI State that represents ProductScreen
 **/
class ProductState

/**
 * Product Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface ProductAction {
    data object OnClick : ProductAction
}

