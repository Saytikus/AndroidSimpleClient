package ru.saytikus.androidsimpleclient.di

import eu.anifantakis.lib.ksafe.KSafe
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.saytikus.androidsimpleclient.data.core.encryptedSettings.EncryptedSettingsRepository
import ru.saytikus.androidsimpleclient.domain.common.encryptedSettings.EncryptedSettings
import ru.saytikus.androidsimpleclient.domain.common.interfaces.ISingleObjectRepository

val dataCoreModule = module {

    single { KSafe(androidContext(), "encrypted_settings") }

    single<ISingleObjectRepository<EncryptedSettings>>(named("EncryptedSettingsRepository")) {
        EncryptedSettingsRepository(get())
    }
}