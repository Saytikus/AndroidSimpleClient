package ru.saytikus.androidsimpleclient.domain.product.useCases

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.product.Product


class GetProductUseCase constructor(

): IInputBoundary<MbResult<Product>, Int> {

    override suspend fun invoke(cmd: Int): MbResult<Product> {
        TODO("Not yet implemented")
    }
}