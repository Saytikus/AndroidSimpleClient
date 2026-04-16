package ru.saytikus.androidsimpleclient

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module

@Module
@Configuration
@ComponentScan("ru.saytikus.androidsimpleclient.presentation")
class PresentationModule