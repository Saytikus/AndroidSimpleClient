package ru.saytikus.androidsimpleclient.presentation.common.search

import androidx.compose.runtime.Composable


@Composable
fun <T> SearchContent(
    state: SearchState<T>,
    onAction: (ProfileSearchAction) -> Unit,
    itemContent: @Composable (T) -> Unit
) {

}