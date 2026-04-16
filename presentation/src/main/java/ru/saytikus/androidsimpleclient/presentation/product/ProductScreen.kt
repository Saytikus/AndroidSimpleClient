package ru.saytikus.androidsimpleclient.presentation.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.tooling.preview.PreviewParameter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    state: ProductState,
    onAction: (ProductAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Product")
                }
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(innerPadding)
        ) {
            // TODO Add UI content here
        }
    }
}

@Composable
@Preview(name = "Product")
private fun ProductScreenPreview(
    @PreviewParameter(ProductStatePreviewParameterProvider::class)
    state: ProductState
) {
    ProductScreen(
        state = state,
        onAction = {}
    )
}

/**
 * PreviewParameter Provider for ProductScreen Preview
 * Add values to the sequence to see the preview in different states
 **/
class ProductStatePreviewParameterProvider : PreviewParameterProvider<ProductState> {
    override val values: Sequence<ProductState>
        get() = sequenceOf(
            ProductState(),
        )
}
