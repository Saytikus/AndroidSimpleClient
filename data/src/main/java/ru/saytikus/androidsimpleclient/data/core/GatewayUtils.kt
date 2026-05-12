package ru.saytikus.androidsimpleclient.data.core

import retrofit2.Response
import ru.saytikus.androidsimpleclient.domain.common.dto.MbError
import ru.saytikus.androidsimpleclient.domain.common.dto.MbResult
import ru.saytikus.androidsimpleclient.domain.common.valueObject.DomainError
import java.io.IOException
import java.net.SocketTimeoutException


fun <T> handleRetrofitServiceResult(result: Result<Response<T>>): MbResult<T> {

    if (result.isFailure) {
        val exception = result.exceptionOrNull()

        if (exception == null) {
            println("handleGatewayResult: catch null exception.")
            return MbResult.Failure(MbError(DomainError.GatewayError.UnknownError))
        }

        println("handleGatewayResult: receive exception: ${exception.toString()}.")

        if (exception is SocketTimeoutException) {
            return MbResult.Failure(MbError(DomainError.GatewayError.Timeout))
        }

        if (exception is IOException) {
            return MbResult.Failure(MbError(DomainError.GatewayError.NoChannel))
        }

        if (exception is IllegalArgumentException) {
            return MbResult.Failure(MbError(DomainError.MapError.UnexpectedFormat))
        }

    }

    val response = result.getOrNull()

    if (response == null) {
        println("handleGatewayResult: response in null.")
        return MbResult.Failure(MbError(DomainError.GatewayError.UnknownError))
    }

    val answer = response.body()

    if (!response.isSuccessful) {
        println("handleGatewayResult: response not successfully.")
        return MbResult.Failure(
            MbError(
                DomainError.GatewayError.RequestError(
                    response.code(),
                    response.errorBody()?.string()
                )
            )
        )
    }

    if(answer == null) {
        println("handleGatewayResult: answer in null.")
        return MbResult.Failure(MbError(DomainError.GatewayError.UnknownError))
    }

    println("handleGatewayResult: received answer: $answer")

    return MbResult.Success(answer)
}

fun <T> handleHubResult(result: Result<T>): MbResult<T> {

    if (result.isFailure) {
        val exception = result.exceptionOrNull()

        if (exception == null) {
            println("handleHubResult: catch null exception.")
            return MbResult.Failure(MbError(DomainError.GatewayError.UnknownError))
        }

        println("handleHubResult: receive exception: ${exception.toString()}.")

        if (exception is SocketTimeoutException) {
            return MbResult.Failure(MbError(DomainError.GatewayError.Timeout))
        }

        if (exception is IOException) {
            return MbResult.Failure(MbError(DomainError.GatewayError.NoChannel))
        }

        if (exception is IllegalArgumentException) {
            return MbResult.Failure(MbError(DomainError.MapError.UnexpectedFormat))
        }

        // TODO parse Exception
    }

    val answer = result.getOrNull()

    if (answer == null) {
        println("handleHubResult: answer is null.")
        return MbResult.Failure(MbError(DomainError.GatewayError.UnknownError))
    }

    return MbResult.Success(answer)
}