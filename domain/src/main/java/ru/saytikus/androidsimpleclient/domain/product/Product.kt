package ru.saytikus.androidsimpleclient.domain.product

data class Product(
    val id: Int,
    val name: String,
    val price: Int,
    val inStock: Boolean
)