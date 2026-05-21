package ru.saytikus.androidsimpleclient.domain.core.profile.validators

import ru.saytikus.androidsimpleclient.domain.core.dto.ValidateResult
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IValidator
import ru.saytikus.androidsimpleclient.domain.core.profile.validators.rules.PasswordValidationRule
import ru.saytikus.androidsimpleclient.domain.core.valueObject.ValidateError

class PasswordValidator : IValidator<String> {

    override fun validate(value: String): ValidateResult {
        return when {

            value.length < PasswordValidationRule.MIN_LENGTH -> {
                ValidateResult.Error(
                    ValidateError("Password must contain at least " +
                            "${PasswordValidationRule.MIN_LENGTH} characters.")
                )
            }

            else -> {
                ValidateResult.Success
            }

        }
    }

}