package ru.saytikus.androidsimpleclient.data.source.global.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Int,
    val name: String,
    val price: Int,
    val inStock: Boolean
)
