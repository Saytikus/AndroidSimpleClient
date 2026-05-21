package ru.saytikus.androidsimpleclient.domain.core.dto

import ru.saytikus.androidsimpleclient.domain.core.valueObject.ValidateError

sealed class ValidateResult {

    data object Success : ValidateResult()

    data class Error(val error: ValidateError) : ValidateResult()
}