package ru.saytikus.androidsimpleclient.domain.product.useCases

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.product.Product


class UpdateProductUseCase constructor(

): IInputBoundary<MbResult<Boolean>, Product> {

    override suspend fun invoke(cmd: Product): MbResult<Boolean> {
        TODO("Not yet implemented")
    }
}