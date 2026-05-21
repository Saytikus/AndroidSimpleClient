package ru.saytikus.androidsimpleclient.domain.core.profile.validators

import ru.saytikus.androidsimpleclient.domain.core.dto.ValidateResult
import ru.saytikus.androidsimpleclient.domain.core.interfaces.IValidator
import ru.saytikus.androidsimpleclient.domain.core.profile.validators.rules.UsernameValidationRule
import ru.saytikus.androidsimpleclient.domain.core.valueObject.ValidateError


class UsernameValidator : IValidator<String> {

    override fun validate(value: String): ValidateResult {

        return when {

            value.length < UsernameValidationRule.MIN_LENGTH -> {
                ValidateResult.Error(
                    ValidateError("Username must contain at least " +
                            "${UsernameValidationRule.MIN_LENGTH} characters.")
                )
            }

            value.length > UsernameValidationRule.MAX_LENGTH -> {
                ValidateResult.Error(
                    ValidateError(
                        "Username must contain less then " +
                                "${UsernameValidationRule.MAX_LENGTH} characters."
                    )
                )
            }

            else -> {
                ValidateResult.Success
            }
        }

    }
}