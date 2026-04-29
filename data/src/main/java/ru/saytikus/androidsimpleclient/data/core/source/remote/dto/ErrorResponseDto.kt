package ru.saytikus.androidsimpleclient.data.core.source.remote.dto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class ErrorResponseDto(
    val additionalProp1: String? = null,

    val additionalProp2: String? = null,

    val additionalProp3: String? = null,

    val detail: String? = null,

    val instance: String? = null,

    val status: Int,

    val title: String,

    val type: String

)