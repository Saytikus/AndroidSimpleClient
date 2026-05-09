package ru.saytikus.androidsimpleclient.presentation.core.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.saytikus.androidsimpleclient.presentation.core.ui.displayItem.DisplayItemSurfaceList
import ru.saytikus.androidsimpleclient.presentation.core.ui.search.components.SearchBar
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider


@Composable
fun <T> SearchContent(
    modifier: Modifier = Modifier,

    state: SearchState<T>,

    queryPlaceholder: String,

    onAction: (SearchAction) -> Unit,

    itemContent: @Composable (T) -> Unit,

    aboveListContent: @Composable (() -> Unit)? = null
) {

    val c = ColorProvider.colors


    Column(
        modifier = modifier
    ) {

        SearchBar(
            state.query,
            { onAction(SearchAction.OnQueryChange(it)) },
            c,
            queryPlaceholder
        )

        aboveListContent?.invoke()

        when {

            state.isLoading -> {

            }

            state.error != null -> {

            }

            state.items.isEmpty() -> {

            }

            else -> {

                DisplayItemSurfaceList(
                    items = state.items,
                    colors = c,
                    itemContent = itemContent
                )
            }
        }
    }
}