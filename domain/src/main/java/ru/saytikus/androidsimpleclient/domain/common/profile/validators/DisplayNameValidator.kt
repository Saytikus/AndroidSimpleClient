package ru.saytikus.androidsimpleclient.domain.common.profile.validators

import ru.saytikus.androidsimpleclient.domain.common.dto.ValidateResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IValidator
import ru.saytikus.androidsimpleclient.domain.common.profile.validators.rules.DisplayNameValidationRule
import ru.saytikus.androidsimpleclient.domain.common.valueObject.ValidateError

class DisplayNameValidator : IValidator<String> {

    override fun validate(value: String): ValidateResult {
        return when {

            value.length > DisplayNameValidationRule.MAX_LENGTH -> {
                ValidateResult.Error(
                    ValidateError("Display name must contain at least " +
                            "${DisplayNameValidationRule.MAX_LENGTH} characters")
                )
            }

            else -> {
                ValidateResult.Success
            }
        }
    }
}