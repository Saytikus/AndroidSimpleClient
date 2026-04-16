package ru.saytikus.androidsimpleclient.domain.product.commands

data class CreateProductCommand(
    val name: String,
    val price: Int,
    val inStock: Boolean
)
