package ru.saytikus.androidsimpleclient

import android.content.Context
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.saytikus.androidsimpleclient.data.core.profile.source.local.ProfileDao
import ru.saytikus.androidsimpleclient.data.core.source.local.ApplicationDatabase
import ru.saytikus.androidsimpleclient.data.registration.validators.EmailValidator
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.profile.Profile
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IObserveInputBoundary
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IValidator
import ru.saytikus.androidsimpleclient.domain.product.Product
import ru.saytikus.androidsimpleclient.domain.product.useCases.GetAllProductsUseCase
import ru.saytikus.androidsimpleclient.domain.registration.answers.A1RegisterProfileAnswer
import ru.saytikus.androidsimpleclient.domain.registration.commands.C1RegisterProfileCommand
import ru.saytikus.androidsimpleclient.domain.registration.useCases.RegisterProfileUseCase
import ru.saytikus.androidsimpleclient.domain.common.profile.SaveProfileUseCase
import ru.saytikus.androidsimpleclient.domain.common.profile.validators.DisplayNameValidator
import ru.saytikus.androidsimpleclient.domain.common.profile.validators.PasswordValidator
import ru.saytikus.androidsimpleclient.domain.common.profile.validators.UsernameValidator
import ru.saytikus.androidsimpleclient.domain.settings.Settings
import ru.saytikus.androidsimpleclient.domain.settings.dto.SaveResponseServerHostAddressCommand
import ru.saytikus.androidsimpleclient.domain.settings.useCase.ObserveSettingsUseCase
import ru.saytikus.androidsimpleclient.domain.settings.useCase.SaveResponseServerHostAddressUseCase
import kotlin.uuid.ExperimentalUuidApi



@OptIn(ExperimentalUuidApi::class)
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

    single<IInputBoundary<MbResult<A1RegisterProfileAnswer>, C1RegisterProfileCommand>>(named("RegisterProfileUseCase")) {
        RegisterProfileUseCase(get())
    }

    single<IInputBoundary<MbResult<Unit>, Profile>>(named("SaveProfileUseCase")) {
        SaveProfileUseCase(get())
    }
}

val DomainValidatorModule = module {
    single<IValidator<String>>(named("UsernameValidator")) {
        UsernameValidator()
    }

    single<IValidator<String>>(named("EmailValidator")) {
        EmailValidator()
    }

    single<IValidator<String>>(named("PasswordValidator")) {
        PasswordValidator()
    }

    single<IValidator<String>>(named("DisplayNameValidator")) {
        DisplayNameValidator()
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

@Module
@OptIn(ExperimentalUuidApi::class)
class LocalStorageModule {

    @Single
    fun provideDatabase(content: Context): ApplicationDatabase {
        return ApplicationDatabase.Companion.buildDatabase(content)
    }


    @Single
    fun provideProfileDao(db: ApplicationDatabase): ProfileDao =
        db.profileDao()
}