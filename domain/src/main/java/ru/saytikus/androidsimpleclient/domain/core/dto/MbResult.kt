package ru.saytikus.androidsimpleclient.domain.core.dto

sealed class MbResult<out T> {

    data class Success<T>( val response: T) : MbResult<T>()

    data class Failure( val error: MbError) : MbResult<Nothing>()
}