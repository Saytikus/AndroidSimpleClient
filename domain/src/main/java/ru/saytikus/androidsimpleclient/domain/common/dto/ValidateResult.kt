package ru.saytikus.androidsimpleclient.domain.common.dto

import ru.saytikus.androidsimpleclient.domain.common.valueObject.ValidateError

sealed class ValidateResult {

    data object Success : ValidateResult()

    data class Error(val error: ValidateError) : ValidateResult()
}