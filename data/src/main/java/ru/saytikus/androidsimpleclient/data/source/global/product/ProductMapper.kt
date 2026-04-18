package ru.saytikus.androidsimpleclient.data.source.global.product

import ru.saytikus.androidsimpleclient.domain.product.Product

fun Product.toDto(): ProductDto =
    ProductDto(
        id,
        name,
        price,
        inStock
    )

fun ProductDto.toDomain(): Product =
    Product(
        id,
        name,
        price,
        inStock
    )