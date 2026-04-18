package ru.saytikus.androidsimpleclient.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.INoCmdInputBoundary
import ru.saytikus.androidsimpleclient.domain.product.Product

@KoinViewModel
class ProductViewModel(

    private val getProductsCase:
    INoCmdInputBoundary<MbResult<List<Product>>>

) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ProductState> = MutableStateFlow(ProductState())

    val stateFlow: StateFlow<ProductState> = _stateFlow.asStateFlow()


    fun refreshProducts() {
        viewModelScope.launch {

            _stateFlow.update { it.copy(isProductRefresh = true) }

            val refreshProductResult = getProductsCase(Unit)

            _stateFlow.update {

                // TODO: UI events
                ProductState(
                    products = when(refreshProductResult) {
                        is MbResult.Failure -> {
                            println("PRODUCT REFRESH ERROR!!!")
                            emptyList()
                        }
                        is MbResult.Success -> {
                            println("PRODUCT REFRESH SUCCESSFULLY!")
                            refreshProductResult.response
                        }
                    },
                    isProductRefreshActive = false,
                    isProductRefresh = false
                )
            }
        }
    }
}