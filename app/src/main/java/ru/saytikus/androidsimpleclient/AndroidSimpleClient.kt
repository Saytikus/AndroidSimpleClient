package ru.saytikus.androidsimpleclient

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.annotation.KoinApplication
import org.koin.core.logger.Level
import org.koin.ksp.generated.module
import org.koin.ksp.generated.startKoin
import ru.saytikus.androidsimpleclient.di.DataModule
import ru.saytikus.androidsimpleclient.di.DomainUseCaseModule
import ru.saytikus.androidsimpleclient.di.DomainValidatorModule
import ru.saytikus.androidsimpleclient.di.LocalStorageModule
import ru.saytikus.androidsimpleclient.di.PresentationModule

@KoinApplication
class AndroidSimpleClient : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            if(BuildConfig.DEBUG) androidLogger(Level.DEBUG) else androidLogger(Level.ERROR)

            androidContext(this@AndroidSimpleClient)

            modules(
                listOf(
                    LocalStorageModule().module,
                    DataModule().module,
                    DomainValidatorModule,
                    DomainUseCaseModule,
                    PresentationModule().module
                )
            )
        }
    }

}