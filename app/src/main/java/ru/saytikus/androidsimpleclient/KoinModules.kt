package ru.saytikus.androidsimpleclient

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.dsl.module
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.product.Product
import ru.saytikus.androidsimpleclient.domain.product.useCases.GetAllProductsUseCase
import ru.saytikus.androidsimpleclient.domain.common.interfaces.INoCmdInputBoundary


/*@Module
@Configuration
@ComponentScan("ru.saytikus.androidsimpleclient.domain")
class DomainModule*/
val DomainModule = module {
    single<INoCmdInputBoundary<MbResult<List<Product>>>> {
        GetAllProductsUseCase(get())
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