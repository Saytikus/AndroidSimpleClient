package ru.saytikus.androidsimpleclient.domain.product

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult

interface IProductGateway {

    suspend fun getUsers(): MbResult<List<Product>>
}