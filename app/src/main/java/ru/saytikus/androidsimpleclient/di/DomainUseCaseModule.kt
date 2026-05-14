package ru.saytikus.androidsimpleclient.di

import kotlinx.coroutines.flow.Flow
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.saytikus.androidsimpleclient.domain.authentication.SignInProfileUseCase
import ru.saytikus.androidsimpleclient.domain.authentication.answers.A2SignInProfileAnswer
import ru.saytikus.androidsimpleclient.domain.authentication.commands.C2SignInProfileCommand
import ru.saytikus.androidsimpleclient.domain.chat.dto.CreatePrivateChatAnswer
import ru.saytikus.androidsimpleclient.domain.chat.dto.CreatePrivateChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.dto.JoinChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.dto.LeaveChatCommand
import ru.saytikus.androidsimpleclient.domain.chat.model.ChatListItem
import ru.saytikus.androidsimpleclient.domain.chat.useCases.CreatePrivateChatUseCase
import ru.saytikus.androidsimpleclient.domain.chat.useCases.GetProfileChatsUseCase
import ru.saytikus.androidsimpleclient.domain.chat.useCases.JoinChatUseCase
import ru.saytikus.androidsimpleclient.domain.chat.useCases.LeaveChatUseCase
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.encryptedSettings.EncryptedSettings
import ru.saytikus.androidsimpleclient.domain.common.encryptedSettings.useCases.UpdateEncryptedSettingsUseCase
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IObserveInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.message.model.MessageEvent
import ru.saytikus.androidsimpleclient.domain.common.message.model.SendMessageCommand
import ru.saytikus.androidsimpleclient.domain.common.message.useCases.ObserveMessageEventsUseCase
import ru.saytikus.androidsimpleclient.domain.common.message.useCases.SendMessageUseCase
import ru.saytikus.androidsimpleclient.domain.common.profile.Profile
import ru.saytikus.androidsimpleclient.domain.common.profile.SaveProfileUseCase
import ru.saytikus.androidsimpleclient.domain.common.profileSearch.answers.ProfileSearchAnswer
import ru.saytikus.androidsimpleclient.domain.common.profileSearch.commands.ProfileSearchCommand
import ru.saytikus.androidsimpleclient.domain.common.profileSearch.useCases.SearchProfilesUseCase
import ru.saytikus.androidsimpleclient.domain.product.Product
import ru.saytikus.androidsimpleclient.domain.product.useCases.GetAllProductsUseCase
import ru.saytikus.androidsimpleclient.domain.registration.answers.A1RegisterProfileAnswer
import ru.saytikus.androidsimpleclient.domain.registration.commands.C1RegisterProfileCommand
import ru.saytikus.androidsimpleclient.domain.registration.useCases.RegisterProfileUseCase
import ru.saytikus.androidsimpleclient.domain.settings.Settings
import ru.saytikus.androidsimpleclient.domain.settings.dto.SaveResponseServerHostAddressCommand
import ru.saytikus.androidsimpleclient.domain.settings.useCase.ObserveSettingsUseCase
import ru.saytikus.androidsimpleclient.domain.settings.useCase.SaveResponseServerHostAddressUseCase
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
val DomainUseCaseModule = module {
    single<IInputBoundary<MbResult<List<Product>>, Unit>>(named("GetAllProductsUseCase")) {
        GetAllProductsUseCase(get())
    }

    single<IInputBoundary<MbResult<Unit>, SaveResponseServerHostAddressCommand>>(named("SaveResponseServerHostAddressUseCase")) {
        SaveResponseServerHostAddressUseCase(get())
    }

    single<IObserveInputBoundary<Flow<Settings>>>(named("ObserveSettingsUseCase")) {
        ObserveSettingsUseCase(get())
    }

    single<IInputBoundary<MbResult<A1RegisterProfileAnswer>, C1RegisterProfileCommand>>(named("RegisterProfileUseCase")) {
        RegisterProfileUseCase(get())
    }

    single<IInputBoundary<MbResult<Unit>, Profile>>(named("SaveProfileUseCase")) {
        SaveProfileUseCase(get())
    }

    single<IInputBoundary<MbResult<A2SignInProfileAnswer>, C2SignInProfileCommand>>(named("SignInProfileUseCase")) {
        SignInProfileUseCase(get())
    }

    single<IInputBoundary<MbResult<Unit>, EncryptedSettings>>(named("UpdateEncryptedSettingsUseCase")) {
        UpdateEncryptedSettingsUseCase(get(named("EncryptedSettingsRepository")))
    }

    single<IInputBoundary<MbResult<List<ChatListItem>>, Unit>>(named("GetProfileChatsUseCase")) {
        GetProfileChatsUseCase(get())
    }

    single<IInputBoundary<MbResult<ProfileSearchAnswer>, ProfileSearchCommand>>(named("SearchProfilesUseCase")) {
        SearchProfilesUseCase(get())
    }

    single<IInputBoundary<MbResult<CreatePrivateChatAnswer>, CreatePrivateChatCommand>>(named("CreatePrivateChatUseCase")) {
        CreatePrivateChatUseCase(get())
    }

    single<IInputBoundary<MbResult<Unit>, JoinChatCommand>>(named("JoinChatUseCase")) {
        JoinChatUseCase(get())
    }

    single<IInputBoundary<MbResult<Unit>, LeaveChatCommand>>(named("LeaveChatUseCase")) {
        LeaveChatUseCase(get())
    }

    single<IObserveInputBoundary<Flow<MessageEvent>>>(named("ObserveMessageEventsUseCase")) {
        ObserveMessageEventsUseCase(get())
    }

    single<IInputBoundary<MbResult<Unit>, SendMessageCommand>>(named("SendMessageUseCase")) {
        SendMessageUseCase(get())
    }
}