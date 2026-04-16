package ru.saytikus.androidsimpleclient.domain.product.useCases

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.INoCmdInputBoundary
import ru.saytikus.androidsimpleclient.domain.product.Product


class GetAllProductsUseCase constructor(

): INoCmdInputBoundary<MbResult<List<Product>>> {

    override suspend fun invoke(cmd: Unit): MbResult<List<Product>> {
        TODO("Not yet implemented")
    }
}