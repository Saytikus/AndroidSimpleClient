package ru.saytikus.androidsimpleclient.data.source.global.common

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import ru.saytikus.androidsimpleclient.data.source.global.common.interfaces.IRetrofitProvider

@Single
class RetrofitProvider(

) : IRetrofitProvider {

    private val _retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.10.10.58:8080") // TODO: url and client
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    override fun retrofit(): Retrofit {
        return _retrofit
    }


}