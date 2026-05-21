package ru.saytikus.androidsimpleclient.domain.product.useCases

import ru.saytikus.androidsimpleclient.domain.core.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IInputBoundary


class DeleteProductUseCase constructor(

): IInputBoundary<MbResult<Boolean>, Int> {

    override suspend fun invoke(cmd: Int): MbResult<Boolean> {
        TODO("Not yet implemented")
    }
}