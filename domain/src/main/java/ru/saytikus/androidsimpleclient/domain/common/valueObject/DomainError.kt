package ru.saytikus.androidsimpleclient.domain.common.valueObject

sealed class DomainError {

    sealed class GatewayError : DomainError() {
        data object NoChannel : GatewayError()
        data object Timeout : GatewayError()
        data class RequestError(val code: Int, val message: String?) : GatewayError()
    }

    sealed class MapError : DomainError() {
        data object UnexpectedFormat : MapError()
    }

    sealed class DomainValidateError : DomainError() {
        data class IncorrectAnswerData(val message: String) : DomainValidateError()
        data class ErrorAnswerCode(val code: Int) : DomainValidateError()
    }

    sealed class RepositoryError : DomainError() {
        data object EntityAlreadyExists : RepositoryError()
    }
}