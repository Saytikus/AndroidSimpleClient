package ru.saytikus.androidsimpleclient.domain.core.dto

import ru.saytikus.androidsimpleclient.domain.core.valueObject.DomainError

data class MbError(

    val error: DomainError,

    val exception: Exception? = null
)