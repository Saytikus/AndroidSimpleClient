package ru.saytikus.androidsimpleclient.presentation.core.ui.search


/**
 * UI State that represents SearchScreen
 **/
data class SearchState<T>(

    val query: String = "",

    val isLoading: Boolean = false,

    val items: List<T> = emptyList(),

    val error: String? = null
)

/**
 * Search Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface SearchAction {
    data class OnQueryChange(val query: String) : SearchAction

    data object OnRetry: SearchAction
}

