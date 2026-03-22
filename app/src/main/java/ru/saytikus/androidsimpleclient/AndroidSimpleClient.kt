package ru.saytikus.androidsimpleclient

import android.app.Application
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.KoinApplication
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module
import org.koin.ksp.generated.startKoin

@KoinApplication
class AndroidSimpleClient : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(PresentationModule().module)
        }
    }

}

@Module
@Configuration
@ComponentScan("ru.saytikus.presentation")
class PresentationModule