package ru.saytikus.androidsimpleclient.domain.core.interfaces

import ru.saytikus.androidsimpleclient.domain.core.dto.ValidateResult

interface IValidator<T> {

    fun validate(value: T): ValidateResult
}