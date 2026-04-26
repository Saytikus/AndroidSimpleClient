package ru.saytikus.androidsimpleclient.domain.common.profile.validators

import ru.saytikus.androidsimpleclient.domain.common.dto.ValidateResult
import ru.saytikus.androidsimpleclient.domain.common.interfaces.IValidator
import ru.saytikus.androidsimpleclient.domain.common.profile.validators.rules.UsernameValidationRule
import ru.saytikus.androidsimpleclient.domain.common.valueObject.ValidateError


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