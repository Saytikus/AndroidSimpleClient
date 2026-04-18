package ru.saytikus.androidsimpleclient.data.source.global.common.interfaces

import retrofit2.Retrofit

interface IRetrofitProvider {

    fun retrofit(): Retrofit
}