package ru.saytikus.androidsimpleclient.presentation.chat.createChat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.androidx.compose.koinViewModel
import ru.saytikus.androidsimpleclient.presentation.core.ui.search.SearchAction
import kotlin.uuid.ExperimentalUuidApi

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
@OptIn(ExperimentalUuidApi::class)
class CreateChatCoordinator(
    private val onNavigate: (CreateChatNavigation) -> Unit,
    val viewModel: CreateChatViewModel
) {

    val screenStateFlow = viewModel.stateFlow

    fun handle(action: CreateChatAction) {
        when (action) {

            is CreateChatAction.OnSearchAction -> {

                when(action.newSearchAction) {

                    is SearchAction.OnQueryChange -> viewModel.onQueryChange(action.newSearchAction.query)

                    is SearchAction.OnRetry -> viewModel.onQueryChange(action.newSearchAction.query)
                }
            }

            is CreateChatAction.OnProfileClick -> viewModel.onProfileClick(action.profileId)

            is CreateChatAction.OnCreateChatSuccessfully -> onNavigate(CreateChatNavigation.CreatedChat)
        }
    }


}

@Composable
fun rememberCreateChatCoordinator(
    onNavigate: (CreateChatNavigation) -> Unit,
    viewModel: CreateChatViewModel = koinViewModel()
): CreateChatCoordinator {
    return remember(viewModel) {
        CreateChatCoordinator(
            onNavigate,
            viewModel = viewModel
        )
    }
}