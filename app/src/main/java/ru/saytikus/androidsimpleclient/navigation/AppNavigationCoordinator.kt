package ru.saytikus.androidsimpleclient.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.runBlocking
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.features.profile.model.Profile
import ru.saytikus.androidsimpleclient.domain.core.features.settings.Settings
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.presentation.authentication.AuthenticationDestination
import ru.saytikus.androidsimpleclient.presentation.authentication.AuthenticationNavigation
import ru.saytikus.androidsimpleclient.presentation.chat.chat.ChatDestination
import ru.saytikus.androidsimpleclient.presentation.chat.chat.ChatNavigation
import ru.saytikus.androidsimpleclient.presentation.chat.chatList.ChatListDestination
import ru.saytikus.androidsimpleclient.presentation.chat.chatList.ChatListNavigation
import ru.saytikus.androidsimpleclient.presentation.chat.createChat.CreateChatDestination
import ru.saytikus.androidsimpleclient.presentation.chat.createChat.CreateChatNavigation
import ru.saytikus.androidsimpleclient.presentation.registration.RegistrationDestination
import ru.saytikus.androidsimpleclient.presentation.registration.RegistrationNavigation
import ru.saytikus.androidsimpleclient.presentation.settings.SettingsDestination
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Single
class AppNavigationCoordinator(

    @Named("GetSavedProfilesUseCase")
    private val getSavedProfilesCase: IInputBoundary<List<Profile>, Unit>,

    @Named("GetSettingsUseCase")
    private val getSettingsCase: IInputBoundary<MbResult<Settings>, Unit>
) {

    private val _commands = MutableSharedFlow<NavigationCommand>(extraBufferCapacity = 1)
    val commands = _commands.asSharedFlow()


    @OptIn(ExperimentalUuidApi::class)
    fun startDestination(): Any {

        val startDestination = runBlocking {
            val savedProfiles = getSavedProfilesCase(Unit)
            if(savedProfiles.isEmpty()) {
                println("start destination: saved profiles empty - select auth screen")
                return@runBlocking AuthenticationDestination
            }


            when(val settings = getSettingsCase(Unit)) {
                is MbResult.Failure -> {
                    println("start destination: error with get app settings - select auth screen")
                    return@runBlocking AuthenticationDestination
                }

                is MbResult.Success -> {
                    val activeProfileId = settings.response.activeProfileId
                    if(activeProfileId == null) {
                        println("start destination: no active profile - select auth screen")
                        return@runBlocking AuthenticationDestination
                    }

                    val activeConvertedProfileId = Uuid.parse(activeProfileId)

                    if(!savedProfiles.any { it.userId == activeConvertedProfileId }) {
                        // TODO maybe active user not save profile? add possibility to not save profile
                        println("start destination: ERROR (temp): active profile not saved - select auth screen")
                        return@runBlocking AuthenticationDestination
                    }

                    ChatListDestination
                }
            }
        }

        return startDestination
    }

    fun onRegistrationNavigate(navigation: RegistrationNavigation) {
        when (navigation) {
            RegistrationNavigation.Authentication -> {
                _commands.tryEmit(NavigationCommand.ReplaceRoot(AuthenticationDestination))
            }
        }
    }

    fun onAuthenticationNavigate(navigation: AuthenticationNavigation) {
        when (navigation) {

            AuthenticationNavigation.MainScreen -> {
                _commands.tryEmit(NavigationCommand.ReplaceRoot(ChatListDestination))
            }

            AuthenticationNavigation.RegistrationScreen -> {
                _commands.tryEmit(NavigationCommand.Navigate(RegistrationDestination))
            }
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    fun onChatListNavigate(navigation: ChatListNavigation) {
        when (navigation) {

            ChatListNavigation.Settings -> _commands.tryEmit(
                NavigationCommand.Navigate(
                    SettingsDestination
                )
            )

            ChatListNavigation.AddChat -> _commands.tryEmit(
                NavigationCommand.Navigate(
                    CreateChatDestination
                )
            )

            is ChatListNavigation.Chat -> _commands.tryEmit(
                NavigationCommand.Navigate(
                    ChatDestination(chatId = navigation.chatId.toString())
                )
            )
        }
    }

    fun onChatNavigate(navigation: ChatNavigation) {
        when (navigation) {
            ChatNavigation.Back -> _commands.tryEmit(NavigationCommand.NavigateBack)
        }
    }

    fun onCreateChatNavigate(navigation: CreateChatNavigation) {
        when (navigation) {
            is CreateChatNavigation.CreatedChat -> _commands.tryEmit(
                NavigationCommand.Navigate(
                    ChatDestination(navigation.newChatId)
                )
            )
        }
    }
}