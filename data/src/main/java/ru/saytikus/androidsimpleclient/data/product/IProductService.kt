package ru.saytikus.androidsimpleclient.data.product

import retrofit2.Response
import retrofit2.http.GET

interface IProductService {

    @GET("/api/Products")
    suspend fun products(): Response<List<ProductDto>>
}