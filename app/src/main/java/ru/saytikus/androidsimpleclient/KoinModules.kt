package ru.saytikus.androidsimpleclient

import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IObserveInputBoundary
import ru.saytikus.androidsimpleclient.domain.product.Product
import ru.saytikus.androidsimpleclient.domain.product.useCases.GetAllProductsUseCase
import ru.saytikus.androidsimpleclient.domain.registration.answers.A1RegisterUserAnswer
import ru.saytikus.androidsimpleclient.domain.registration.commands.C1RegisterUserCommand
import ru.saytikus.androidsimpleclient.domain.registration.useCases.RegisterUserUseCase
import ru.saytikus.androidsimpleclient.domain.settings.Settings
import ru.saytikus.androidsimpleclient.domain.settings.dto.SaveResponseServerHostAddressCommand
import ru.saytikus.androidsimpleclient.domain.settings.useCase.ObserveSettingsUseCase
import ru.saytikus.androidsimpleclient.domain.settings.useCase.SaveResponseServerHostAddressUseCase


val DomainModule = module {
    single<IInputBoundary<MbResult<List<Product>>, Unit>>(named("GetAllProductsUseCase")) {
        GetAllProductsUseCase(get())
    }

    single<IInputBoundary<MbResult<Unit>, SaveResponseServerHostAddressCommand>>(named("SaveResponseServerHostAddressUseCase")) {
        SaveResponseServerHostAddressUseCase(get())
    }

    single<IObserveInputBoundary<Flow<Settings>>>(named("ObserveSettingsUseCase")) {
        ObserveSettingsUseCase(get())
    }

    single<IInputBoundary<MbResult<A1RegisterUserAnswer>, C1RegisterUserCommand>> {
        RegisterUserUseCase(get())
    }
}

@Module
@Configuration
@ComponentScan("ru.saytikus.androidsimpleclient.presentation")
class PresentationModule

@Module
@Configuration
@ComponentScan("ru.saytikus.androidsimpleclient.data")
class DataModule