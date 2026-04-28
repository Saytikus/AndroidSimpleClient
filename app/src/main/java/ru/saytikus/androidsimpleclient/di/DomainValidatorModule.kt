package ru.saytikus.androidsimpleclient.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.saytikus.androidsimpleclient.data.registration.validators.EmailValidator
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IValidator
import ru.saytikus.androidsimpleclient.domain.common.profile.validators.DisplayNameValidator
import ru.saytikus.androidsimpleclient.domain.common.profile.validators.PasswordValidator
import ru.saytikus.androidsimpleclient.domain.common.profile.validators.UsernameValidator

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