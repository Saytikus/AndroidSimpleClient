package ru.saytikus.androidsimpleclient.data.core.source.remote.retrofit.interfaces

import retrofit2.Retrofit

interface IRetrofitProvider {

    fun retrofit(): Retrofit
}