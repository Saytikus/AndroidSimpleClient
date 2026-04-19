package ru.saytikus.androidsimpleclient.data.source.global.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import ru.saytikus.androidsimpleclient.data.source.global.common.interfaces.IRetrofitProvider
import ru.saytikus.androidsimpleclient.domain.settings.ISettingsRepository

@Single
class RetrofitProvider(
    private val settingsRepo: ISettingsRepository

) : IRetrofitProvider {

    private var _retrofit: Retrofit = runBlocking {
        buildRetrofit(settingsRepo.getOnce().responseServerHostAddress)
    }

    private val scope = CoroutineScope(Dispatchers.IO)

    init {

        scope.launch {
            settingsRepo.observeSettings()
                .distinctUntilChanged()
                .onEach {
                    _retrofit = buildRetrofit(it.responseServerHostAddress)
                }
                .collect()
        }
    }

    override fun retrofit(): Retrofit {
        return _retrofit
    }

    private fun buildRetrofit(hostAddress: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://$hostAddress:8080") // TODO: url
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}