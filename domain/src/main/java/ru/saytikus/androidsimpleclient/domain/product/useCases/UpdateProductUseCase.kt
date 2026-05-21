package ru.saytikus.androidsimpleclient.domain.product.useCases

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.product.Product


class UpdateProductUseCase constructor(

): IInputBoundary<MbResult<Boolean>, Product> {

    override suspend fun invoke(cmd: Product): MbResult<Boolean> {
        TODO("Not yet implemented")
    }
}