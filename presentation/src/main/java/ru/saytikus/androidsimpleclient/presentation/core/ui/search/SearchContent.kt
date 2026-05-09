package ru.saytikus.androidsimpleclient.presentation.core.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.saytikus.androidsimpleclient.presentation.core.ui.displayItem.DisplayItemSurfaceList
import ru.saytikus.androidsimpleclient.presentation.core.ui.search.components.SearchBar
import ru.saytikus.androidsimpleclient.presentation.core.ui.search.components.SearchEmptyPrompt
import ru.saytikus.androidsimpleclient.presentation.core.ui.search.components.SearchError
import ru.saytikus.androidsimpleclient.presentation.core.ui.search.components.SearchLoading
import ru.saytikus.androidsimpleclient.presentation.core.ui.search.components.SearchNoResults
import ru.saytikus.androidsimpleclient.presentation.theme.ColorProvider


@Composable
fun <T> SearchContent(
    modifier: Modifier = Modifier,

    state: SearchState<T>,

    queryPlaceholder: String,

    onAction: (SearchAction) -> Unit,
    
    aboveListContent: @Composable (() -> Unit)? = null,

    itemContent: @Composable (T) -> Unit
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
                SearchLoading(colors = c)
            }

            state.error != null -> {
                SearchError(
                    error = state.error,
                    onRetry = { onAction(SearchAction.OnRetry(state.query)) },
                    colors = c
                )
            }

            state.items.isEmpty() && state.query.isEmpty() -> {
                SearchEmptyPrompt(colors = c)
            }

            state.items.isEmpty() -> {
                SearchNoResults(query = state.query, colors = c)
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