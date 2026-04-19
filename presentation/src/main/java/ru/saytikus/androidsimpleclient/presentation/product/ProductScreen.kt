package ru.saytikus.androidsimpleclient.presentation.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Inventory2
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.saytikus.androidsimpleclient.domain.product.Product
import ru.saytikus.androidsimpleclient.presentation.theme.AndroidSimpleClientTheme
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider
import ru.saytikus.androidsimpleclient.presentation.product.components.ProductItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    state: ProductState,
    onAction: (ProductAction) -> Unit
) {
    val c = ColorProvider.colors

    val listState = rememberLazyListState()

    val canScrollForward by remember { derivedStateOf { listState.canScrollForward } }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = c.textPrimary
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
                                imageVector = Icons.Rounded.ShoppingBag,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Text(
                            text = "Product Menu",
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            letterSpacing = 0.5.sp,
                            color = c.textPrimary
                        )
                    }
                },
                actions = {
                    Button(
                        onClick = { onAction(ProductAction.onSettingsButtonClicked) },
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        colors = ButtonColors(
                            contentColor = ColorProvider.colors.textPrimary,
                            containerColor = Color.Transparent,
                            disabledContainerColor = ColorProvider.colors.backgroundStart,
                            disabledContentColor = ColorProvider.colors.backgroundStart
                        ),

                        ) {
                        Column(
                            modifier = Modifier
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally),
                                contentDescription = null,
                                tint = ColorProvider.colors.textPrimary
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->

        val pullState = rememberPullToRefreshState()

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

            PullToRefreshBox(
                contentAlignment = Alignment.Center,
                state = pullState,
                isRefreshing = state.isProductRefresh,
                onRefresh = { onAction(ProductAction.OnProductRefreshRequested) },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                indicator = {
                    PullToRefreshDefaults.Indicator(
                        state = pullState,
                        isRefreshing = state.isProductRefresh,
                        color = c.accent,
                        containerColor = c.pullIndicatorContainer
                    )
                }
            ) {
                Column(modifier = Modifier.fillMaxSize()) {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(4.dp)
                                    .height(20.dp)
                                    .background(c.accent, RoundedCornerShape(2.dp))
                            )
                            Text(
                                text = "Available Products",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 16.sp,
                                letterSpacing = 0.3.sp,
                                color = c.textPrimary
                            )
                        }
                        Text(
                            text = "Pull to refresh",
                            color = c.textSecondary,
                            fontSize = 11.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                    ) {
                        LazyColumn(
                            state = listState,
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            contentPadding = PaddingValues(top = 8.dp, bottom = 64.dp)
                        ) {
                            if (state.products.isEmpty() && !state.isProductRefresh) {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(400.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.spacedBy(12.dp)
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(72.dp)
                                                    .background(c.emptyIconBg, CircleShape),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Rounded.Inventory2,
                                                    contentDescription = null,
                                                    tint = c.emptyIconTint,
                                                    modifier = Modifier.size(36.dp)
                                                )
                                            }
                                            Text(
                                                text = "No products yet",
                                                color = c.textPrimary,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Medium
                                            )
                                            Text(
                                                text = "Pull down to load",
                                                color = c.textSecondary,
                                                fontSize = 12.sp
                                            )
                                        }
                                    }
                                }
                            } else {
                                itemsIndexed(items = state.products) { index, product ->
                                    ProductItemCard(
                                        index = index,
                                        product = product,
                                        colors = c
                                    )
                                }
                            }
                        }

                        // Fade + стрелка — было products.isNotEmpty(), стало canScrollForward
                        if (canScrollForward) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp)
                                    .align(Alignment.BottomCenter)
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                Color.Transparent,
                                                c.backgroundEnd.copy(alpha = 0.95f)
                                            )
                                        )
                                    )
                            )
                            Icon(
                                imageVector = Icons.Rounded.KeyboardArrowDown,
                                contentDescription = null,
                                tint = c.accent.copy(alpha = 0.6f),
                                modifier = Modifier
                                    .size(26.dp)
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 4.dp)
                            )
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
    AndroidSimpleClientTheme {
        ProductScreen(
            state = state,
            onAction = {}
        )
    }
}

@Composable
@Preview(name = "ProductScreenDark")
private fun ProductScreenPreviewDark(
    @PreviewParameter(ProductStatePreviewParameterProvider::class) state: ProductState
) {
    AndroidSimpleClientTheme(
        previewDarkTheme = true
    ) {
        ProductScreen(
            state = state,
            onAction = {}
        )
    }
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
