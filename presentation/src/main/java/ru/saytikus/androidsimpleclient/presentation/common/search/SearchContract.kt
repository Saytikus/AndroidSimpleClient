package ru.saytikus.androidsimpleclient.presentation.common.search


/**
 * UI State that represents ProfileSearchScreen
 **/
data class SearchState<T>(
    val query: String = "",
    val isLoading: Boolean = false,
    val items: List<T> = emptyList(),
    val error: String? = null
)

/**
 * ProfileSearch Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface ProfileSearchAction {
    data class OnQueryChange(val query: String) : ProfileSearchAction
}

