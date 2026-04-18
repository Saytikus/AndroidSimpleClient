package ru.saytikus.androidsimpleclient.domain.common.valueObject

sealed class DomainError {

    sealed class GatewayError : DomainError() {
        data object NoChannel : GatewayError()
        data object Timeout : GatewayError()
        data class RequestError(val code: Int, val message: String?) : GatewayError()
    }

    sealed class MapError {
        data object UnexpectedFormat : MapError()
    }
}