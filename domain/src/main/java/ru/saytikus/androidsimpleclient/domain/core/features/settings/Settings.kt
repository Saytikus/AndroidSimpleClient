package ru.saytikus.androidsimpleclient.domain.core.features.settings

data class Settings(
    val responseServerHostAddress: String = "0.0.0.0",
    val activeUserId: String? = null
)
