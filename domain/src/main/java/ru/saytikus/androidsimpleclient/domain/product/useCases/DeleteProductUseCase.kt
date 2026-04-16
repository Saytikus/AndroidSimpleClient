package ru.saytikus.androidsimpleclient.domain.product.useCases

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary


class DeleteProductUseCase constructor(

): IInputBoundary<MbResult<Boolean>, Int> {

    override suspend fun invoke(cmd: Int): MbResult<Boolean> {
        TODO("Not yet implemented")
    }
}