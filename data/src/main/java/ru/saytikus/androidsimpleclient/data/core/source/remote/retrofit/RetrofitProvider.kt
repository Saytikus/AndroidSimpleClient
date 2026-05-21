package ru.saytikus.androidsimpleclient.data.core.source.remote.retrofit

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import ru.saytikus.androidsimpleclient.data.core.source.remote.retrofit.interceptors.AuthorizationInterceptor
import ru.saytikus.androidsimpleclient.data.core.source.remote.retrofit.interfaces.IRetrofitProvider
import ru.saytikus.androidsimpleclient.domain.core.features.settings.ISettingsRepository
import ru.saytikus.androidsimpleclient.domain.core.features.settings.Settings

@Single
class RetrofitProvider(
    private val settingsRepo: ISettingsRepository,

    private val authInterceptor: AuthorizationInterceptor

) : IRetrofitProvider {

    @Volatile
    private var _retrofit: Retrofit = runBlocking {
        buildRetrofit(
            Settings().responseServerHostAddress,
            authInterceptor
            )
    }

    private val scope = CoroutineScope(Dispatchers.IO)

    init {

        scope.launch {
            settingsRepo.observeSettings()
                .map { it.responseServerHostAddress }
                .onEach {
                    _retrofit = buildRetrofit(
                        it,
                        authInterceptor
                        )
                }
                .collect()
        }
    }

    override fun retrofit(): Retrofit {
        return _retrofit
    }

    private fun buildRetrofit(
        hostAddress: String,
        authInterceptor: AuthorizationInterceptor
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl("http://$hostAddress:8080") // TODO: url
            .client(buildClient(authInterceptor))
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    private fun buildClient(authInterceptor: AuthorizationInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }
}