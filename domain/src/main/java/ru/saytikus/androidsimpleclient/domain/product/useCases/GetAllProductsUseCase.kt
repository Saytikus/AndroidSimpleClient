package ru.saytikus.androidsimpleclient.domain.product.useCases

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.product.IProductGateway
import ru.saytikus.androidsimpleclient.domain.product.Product


class GetAllProductsUseCase constructor(
    private val productGateway: IProductGateway

): IInputBoundary<MbResult<List<Product>>, Unit> {

    override suspend fun invoke(cmd: Unit): MbResult<List<Product>> {
        return productGateway.getUsers()
    }
}