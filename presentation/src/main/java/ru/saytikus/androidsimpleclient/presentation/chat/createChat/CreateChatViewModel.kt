package ru.saytikus.androidsimpleclient.presentation.chat.createChat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Named
import ru.saytikus.androidsimpleclient.domain.chat.dto.CreatePrivateChatAnswer
import ru.saytikus.androidsimpleclient.domain.chat.dto.CreatePrivateChatCommand
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.profileSearch.ProfileSearchConstants
import ru.saytikus.androidsimpleclient.domain.common.profileSearch.answers.ProfileSearchAnswer
import ru.saytikus.androidsimpleclient.domain.common.profileSearch.commands.ProfileSearchCommand
import ru.saytikus.androidsimpleclient.presentation.core.ui.search.SearchState
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@KoinViewModel
class CreateChatViewModel(

    @Named("SearchProfilesUseCase")
    private val searchProfilesCase:
    IInputBoundary<MbResult<ProfileSearchAnswer>, ProfileSearchCommand>,

    @Named("CreatePrivateChatUseCase")
    private val createPrivateChatCase:
    IInputBoundary<MbResult<CreatePrivateChatAnswer>, CreatePrivateChatCommand>

) : ViewModel() {

    private val _stateFlow: MutableStateFlow<CreateChatState> = MutableStateFlow(CreateChatState())

    val stateFlow: StateFlow<CreateChatState> = _stateFlow.asStateFlow()


    fun onQueryChange(newQuery: String) {

        if (newQuery.isEmpty()) {
            // update empty state
            _stateFlow.update {
                it.copy(
                    searchState = it.searchState.copy(
                        query = newQuery,
                        isLoading = false,
                        items = emptyList(),
                        error = null
                    )
                )
            }
        }


        // update state with loading
        _stateFlow.update {
            it.copy(
                searchState = it.searchState.copy(
                    query = newQuery,
                    isLoading = true,
                    error = null
                )
            )
        }

        viewModelScope.launch {
            val searchProfilesResult = searchProfilesCase(
                ProfileSearchCommand(
                    q = newQuery,
                    limit = ProfileSearchConstants.PROFILES_LIMIT_DEFAULT
                )
            )

            when (searchProfilesResult) {

                is MbResult.Failure -> {
                    // update state with error
                    _stateFlow.update {
                        it.copy(
                            searchState = it.searchState.copy(
                                isLoading = false,
                                items = emptyList(),
                                error = "Search error" // TODO: add error strings
                            )
                        )
                    }
                }

                is MbResult.Success -> {

                    if (searchProfilesResult.response.query != newQuery) {
                        // update state with not equals query error
                        _stateFlow.update {
                            it.copy(
                                searchState = it.searchState.copy(
                                    isLoading = false,
                                    items = emptyList(),
                                    error = "Error: query in server response not equals requested query"
                                )
                            )
                        }

                        return@launch
                    }

                    // update state with profiles from search
                    _stateFlow.update {
                        it.copy(
                            searchState = it.searchState.copy(
                                isLoading = false,
                                items = searchProfilesResult.response.items,
                                error = null
                            )
                        )
                    }
                }
            }
        }
    }

    fun onProfileClick(profileId: Uuid) {

        viewModelScope.launch {
            val createPrivateChatResult = createPrivateChatCase(CreatePrivateChatCommand(profileId))

            when(createPrivateChatResult) {

                is MbResult.Failure -> {
                    // TODO snackbar
                }

                is MbResult.Success -> {
                    _stateFlow.update {
                        it.copy(
                            searchState = SearchState(),
                            isCreateChatSuccessfully = true
                        )
                    }
                }
            }
        }
    }
}