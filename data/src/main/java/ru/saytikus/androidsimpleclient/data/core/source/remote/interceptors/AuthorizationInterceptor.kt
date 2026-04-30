package ru.saytikus.androidsimpleclient.data.core.source.remote.interceptors

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import ru.saytikus.androidsimpleclient.data.core.source.remote.RetrofitEndpoints
import ru.saytikus.androidsimpleclient.domain.common.encryptedSettings.EncryptedSettings
import ru.saytikus.androidsimpleclient.domain.common.interfaces.ISingleObjectRepository

@Single
class AuthorizationInterceptor(

    @Named("EncryptedSettingsRepository")
    private val encryptedSettingsRepository: ISingleObjectRepository<EncryptedSettings>

) : Interceptor {

    private val publicEndpoints = listOf(
        RetrofitEndpoints.LOGIN,
        RetrofitEndpoints.REGISTER
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        // if public then no auth header
        val isPublic = publicEndpoints.any { url.contains(it) }

        val finalRequest =
            if(!isPublic) {

                val authToken = runBlocking { encryptedSettingsRepository.getOnce().authenticationToken }

                request.newBuilder()
                    .addHeader("Authorization", "Bearer $authToken")
                    .build()

            } else request

        return chain.proceed(finalRequest)
    }
}