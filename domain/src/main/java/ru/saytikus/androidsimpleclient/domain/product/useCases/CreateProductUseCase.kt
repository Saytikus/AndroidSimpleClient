package ru.saytikus.androidsimpleclient.domain.product.useCases

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IInputBoundary
import ru.saytikus.androidsimpleclient.domain.product.commands.CreateProductCommand


class CreateProductUseCase constructor(

): IInputBoundary<MbResult<Boolean>, CreateProductCommand> {

    override suspend fun invoke(cmd: CreateProductCommand): MbResult<Boolean> {
        TODO("Not yet implemented")
    }
}