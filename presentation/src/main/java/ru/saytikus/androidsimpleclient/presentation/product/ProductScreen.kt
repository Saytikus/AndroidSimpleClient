package ru.saytikus.androidsimpleclient.presentation.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import ru.saytikus.androidsimpleclient.domain.product.Product
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    state: ProductState, onAction: (ProductAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Products")
                })
        }
    )
    { innerPadding ->

        val pullState = rememberPullToRefreshState()

        PullToRefreshBox(
            contentAlignment = Alignment.Center,
            state = pullState,
            isRefreshing = state.isProductRefresh,
            onRefresh = {
                onAction(ProductAction.OnProductRefresh)
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            indicator = {
                PullToRefreshDefaults.Indicator(
                    state = pullState,
                    isRefreshing = state.isProductRefresh
                )
            }

        ) {

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 5.dp),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(
                    MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.onPrimary
                )
            ) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 5.dp)
                ) {

                    if (state.products.isEmpty() && !state.isProductRefresh) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillParentMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Products are not requested",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSecondary
                                )
                            }
                        }
                    } else {

                        itemsIndexed(
                            items = state.products
                        ) { index, product ->

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                val modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp)

                                Button(
                                    modifier = modifier,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary
                                    ),
                                    onClick = {
                                        // TODO
                                    }) {
                                    Text(product.name)
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(name = "ProductScreenLight")
private fun ProductScreenPreviewLight(
    @PreviewParameter(ProductStatePreviewParameterProvider::class) state: ProductState
) {
    AndroidSimpleClientTheme(
           content = {
               ProductScreen(
                   state = state, onAction = {})
           }
    )

}

@Composable
@Preview(name = "ProductScreenDark")
private fun ProductScreenPreviewDark(
    @PreviewParameter(ProductStatePreviewParameterProvider::class) state: ProductState
) {
    AndroidSimpleClientTheme(
        darkTheme = true,
        content = {
            ProductScreen(
                state = state, onAction = {})
        }
    )

}

/**
 * PreviewParameter Provider for ProductScreen Preview
 * Add values to the sequence to see the preview in different states
 **/
class ProductStatePreviewParameterProvider : PreviewParameterProvider<ProductState> {
    override val values: Sequence<ProductState>
        get() = sequenceOf(
            ProductState(
                products = listOf(
                    Product(0, "Milk", 2, true),
                    Product(100, "Olive oil", 14, true),
                    Product(20, "Bread", 1, false)
                )
            ),
        )
}
