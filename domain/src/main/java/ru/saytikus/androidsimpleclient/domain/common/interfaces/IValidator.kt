package ru.saytikus.androidsimpleclient.domain.common.interfaces

import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.dto.ValidateResult

interface IValidator<T> {

    fun validate(value: T): ValidateResult
}