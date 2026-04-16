package ru.saytikus.androidsimpleclient.domain.common.interfaces


typealias INoCmdInputBoundary<ReturnType> = IInputBoundary<ReturnType, Unit>

suspend fun <ReturnType> INoCmdInputBoundary<ReturnType>.invoke(): ReturnType = invoke(Unit)