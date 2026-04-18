package ru.saytikus.androidsimpleclient.domain.common.dto

sealed class MbResult<out T> {

    data class Success<T>( val response: T) : MbResult<T>()

    data class Failure( val error: MbError) : MbResult<Nothing>()
}