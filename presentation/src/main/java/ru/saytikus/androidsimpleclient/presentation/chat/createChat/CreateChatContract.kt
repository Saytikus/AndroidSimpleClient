package ru.saytikus.androidsimpleclient.presentation.chat.createChat

import kotlinx.serialization.Serializable
import ru.saytikus.androidsimpleclient.domain.common.profileSearch.answers.ProfileSearchListItem
import ru.saytikus.androidsimpleclient.presentation.core.ui.search.SearchAction
import ru.saytikus.androidsimpleclient.presentation.core.ui.search.SearchState
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


/**
 * Object used for a type safe destination to a CreateChat route
 */
@Serializable
object CreateChatDestination

/**
 * UI State that represents CreateChatScreen
 **/
data class CreateChatState(

    val searchState: SearchState<ProfileSearchListItem> = SearchState(),

    val isCreateChatSuccessfully: Boolean = false
)

sealed interface CreateChatNavigation {

    data object CreatedChat : CreateChatNavigation
}

/**
 * CreateChat Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface CreateChatAction {

    data class OnSearchAction(val newSearchAction: SearchAction) : CreateChatAction

    @OptIn(ExperimentalUuidApi::class)
    data class OnProfileClick(val profileId: Uuid) : CreateChatAction

    data object OnCreateChatSuccessfully : CreateChatAction
}

