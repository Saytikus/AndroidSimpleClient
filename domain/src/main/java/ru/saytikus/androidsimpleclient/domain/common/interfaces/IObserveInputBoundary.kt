package ru.saytikus.androidsimpleclient.domain.common.interfaces

interface IObserveInputBoundary<ReturnType> {
    operator fun invoke(): ReturnType
}