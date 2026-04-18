package ru.saytikus.androidsimpleclient.domain.common.dto

import ru.saytikus.androidsimpleclient.domain.common.valueObject.DomainError

data class MbError(

    val error: DomainError,

    val exception: Exception? = null
)