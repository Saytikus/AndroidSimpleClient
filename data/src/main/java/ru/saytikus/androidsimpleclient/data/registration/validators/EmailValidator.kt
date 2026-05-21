package ru.saytikus.androidsimpleclient.data.registration.validators

import android.util.Patterns
import ru.saytikus.androidsimpleclient.domain.core.dto.ValidateResult
import ru.saytikus.androidsimpleclient.domain.core.features.profile.validators.IEmailValidator
import ru.saytikus.androidsimpleclient.domain.core.valueObject.ValidateError

class EmailValidator : IEmailValidator {

    override fun validate(value: String): ValidateResult {
        return when {
            !Patterns.EMAIL_ADDRESS.matcher(value).matches() -> {
                ValidateResult.Error(
                    ValidateError(
                        "Email is incorrect."
                    )
                )
            }

            else -> ValidateResult.Success
        }
    }
}