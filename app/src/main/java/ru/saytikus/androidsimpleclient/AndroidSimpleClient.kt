package ru.saytikus.androidsimpleclient

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.annotation.KoinApplication
import org.koin.core.logger.Level
import org.koin.ksp.generated.module
import org.koin.ksp.generated.startKoin

@KoinApplication
class AndroidSimpleClient : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            if(BuildConfig.DEBUG) androidLogger(Level.DEBUG) else androidLogger(Level.ERROR)

            androidContext(this@AndroidSimpleClient)

            modules(
                listOf(
                    DataModule().module,
                    DomainModule,
                    PresentationModule().module
                )
            )
        }
    }

}