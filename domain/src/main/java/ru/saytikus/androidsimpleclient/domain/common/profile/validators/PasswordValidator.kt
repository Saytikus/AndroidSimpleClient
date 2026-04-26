package ru.saytikus.androidsimpleclient.domain.common.profile.validators

import ru.saytikus.androidsimpleclient.domain.common.dto.ValidateResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IValidator
import ru.saytikus.androidsimpleclient.domain.common.profile.validators.rules.PasswordValidationRule
import ru.saytikus.androidsimpleclient.domain.common.valueObject.ValidateError

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