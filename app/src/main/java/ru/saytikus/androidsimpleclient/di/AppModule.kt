package ru.saytikus.androidsimpleclient.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single


@Module
@Configuration
@ComponentScan("ru.saytikus.androidsimpleclient.lifecycle")
class AppModule {

    @Single
    fun provideAppScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)
}