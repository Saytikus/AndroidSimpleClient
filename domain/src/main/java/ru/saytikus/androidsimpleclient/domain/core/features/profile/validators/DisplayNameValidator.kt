package ru.saytikus.androidsimpleclient.domain.core.features.profile.validators

import ru.saytikus.androidsimpleclient.domain.core.dto.ValidateResult
import ru.saytikus.androidsimpleclient.domain.core.features.profile.validators.rules.DisplayNameValidationRule
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IValidator
import ru.saytikus.androidsimpleclient.domain.core.valueObject.ValidateError

class DisplayNameValidator : IValidator<String> {

    override fun validate(value: String): ValidateResult {
        return when {

            value.length > DisplayNameValidationRule.MAX_LENGTH -> {
                ValidateResult.Error(
                    ValidateError("Display name must contain at least " +
                            "${DisplayNameValidationRule.MAX_LENGTH} characters.")
                )
            }

            else -> {
                ValidateResult.Success
            }
        }
    }
}