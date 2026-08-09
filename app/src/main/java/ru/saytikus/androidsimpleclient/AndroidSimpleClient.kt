package ru.saytikus.androidsimpleclient

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.annotation.KoinApplication
import org.koin.core.logger.Level
import org.koin.ksp.generated.startKoin
import ru.saytikus.androidsimpleclient.di.DomainUseCaseModule
import ru.saytikus.androidsimpleclient.di.DomainValidatorModule
import ru.saytikus.androidsimpleclient.di.dataCoreModule
import ru.saytikus.androidsimpleclient.lifecycle.AppLifecycleObserver

@KoinApplication
class AndroidSimpleClient : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            if(BuildConfig.DEBUG) androidLogger(Level.DEBUG) else androidLogger(Level.ERROR)

            androidContext(this@AndroidSimpleClient)

            modules(
                listOf(
                    dataCoreModule,
                    DomainValidatorModule,
                    DomainUseCaseModule,
                )
            )
        }

        ProcessLifecycleOwner.get().lifecycle.addObserver(getKoin().get<AppLifecycleObserver>())
    }
}