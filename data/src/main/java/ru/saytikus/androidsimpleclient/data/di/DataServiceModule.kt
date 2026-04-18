package ru.saytikus.androidsimpleclient.data.di

import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.source.global.common.interfaces.IRetrofitProvider
import ru.saytikus.androidsimpleclient.data.source.global.product.IProductService

@Module
class DataServiceModule() {

    @Single
    fun provideIProductService(retrofitProvider: IRetrofitProvider): IProductService =
        retrofitProvider.retrofit().create(IProductService::class.java)
}