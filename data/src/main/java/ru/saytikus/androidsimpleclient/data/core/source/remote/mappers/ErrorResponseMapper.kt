package ru.saytikus.androidsimpleclient.data.core.source.remote.mappers

import kotlinx.serialization.json.Json
import okhttp3.ResponseBody
import ru.saytikus.androidsimpleclient.data.core.source.remote.dto.ErrorResponseDto

fun ResponseBody?.deserialize(): ErrorResponseDto? {
    return this?.string()?.let { Json.decodeFromString<ErrorResponseDto>(it) }
}